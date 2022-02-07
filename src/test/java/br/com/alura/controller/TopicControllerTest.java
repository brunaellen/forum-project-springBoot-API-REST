package br.com.alura.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.controller.dto.TopicFormDto;
import br.com.alura.model.Course;
import br.com.alura.model.Topic;
import br.com.alura.model.User;
import br.com.alura.repository.CourseRepository;
import br.com.alura.repository.TopicRepository;


@WebMvcTest(controllers = {TopicController.class})
class TopicControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private CourseRepository courseRepository;
  
  @MockBean
  private TopicRepository topicRepository;
  
  @Test
  void listAll_shouldReturnStatusOk_And_ListAllTopics() throws Exception {
    List<Topic> allTopics = new ArrayList<>(); 
    Topic firstTopic = new Topic("topic title", "topic message", new Course());
    Topic secondTopic = new Topic("second title", "second message", new Course());
    allTopics.add(firstTopic);
    allTopics.add(secondTopic);
    
    Pageable pagination = PageRequest.of(0, 2, Direction.ASC, "title");
    Page<Topic> topicsWithPagination = new PageImpl<>(allTopics);
    
    when(topicRepository.findAll(pagination)).thenReturn(topicsWithPagination);
    
    mockMvc
    .perform(get("/topics/listAll?page=0&size=2&sort=title,asc"))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.content[0].title").value("topic title"))
    .andExpect(jsonPath("$.content[0].message").value("topic message"))
    .andExpect(jsonPath("$.content[1].title").value("second title"))
    .andExpect(jsonPath("$.content[1].message").value("second message"));
  }
  
  @Test
  void listAll_shouldReturnStatusNotFound_ifThereAreNoTopicsRegistered() throws Exception {
    List<Topic> allTopics = new ArrayList<>(); 
    Pageable pagination = PageRequest.of(0, 2, Direction.ASC, "title");
    Page<Topic> topicsWithPagination = new PageImpl<>(allTopics);
    
    when(topicRepository.findAll(pagination)).thenReturn(topicsWithPagination);
    
    mockMvc
      .perform(get("/topics/listAll?page=0&size=2&sort=title,asc"))
      .andDo(print())
      .andExpect(status().isNotFound());
  }

  @Test
  void listAllByCourseName_givenACourseName_shouldReturnStatusOk_And_ListAllTopics() throws Exception {
    List<Topic> allTopics = new ArrayList<>(); 
    Topic firstTopic = new Topic("topic title", "topic message", new Course());
    Topic secondTopic = new Topic("second title", "second message", new Course());
    allTopics.add(firstTopic);
    allTopics.add(secondTopic);
    
    Pageable pagination = PageRequest.of(0, 2, Direction.ASC, "title");
    Page<Topic> topicsWithPagination = new PageImpl<>(allTopics, pagination, 2L);
    
    when(topicRepository.findByCourse_Name("course name", pagination))
      .thenReturn(topicsWithPagination);
    
    mockMvc
    .perform(get("/topics/listByCourseName?courseName=course name&page=0&size=2&sort=title,asc"))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.content[0].title").value("topic title"))
    .andExpect(jsonPath("$.content[0].message").value("topic message"))
    .andExpect(jsonPath("$.content[1].title").value("second title"))
    .andExpect(jsonPath("$.content[1].message").value("second message"));
  }
  
  @Test
  void listAllByCourseName_shouldReturnStatusNotFound_ifThereAreNoTopicsRegistered() throws Exception {
    List<Topic> allTopics = new ArrayList<>(); 
    Pageable pagination = PageRequest.of(0, 2, Direction.ASC, "title");
    Page<Topic> topicsWithPagination = new PageImpl<>(allTopics);
    
    when(topicRepository.findByCourse_Name("course name", pagination))
    .thenReturn(topicsWithPagination);
    
    mockMvc
    .perform(get("/topics/listByCourseName?courseName=course name&page=0&size=2&sort=title,asc"))
    .andDo(print())
    .andExpect(status().isNotFound());
  }
  
  @Test
  void listAllByCourseName_shouldReturnStatusBadRequest_ifCourseNameParameterIsNull() throws Exception {  
    mockMvc
    .perform(get("/topics/listByCourseName"))
    .andDo(print())
    .andExpect(status().isBadRequest());
  }
  
  @Test
  void listAllByCourseName_shouldReturnStatusBadRequest_ifCourseNameParameterIsBlank() throws Exception {  
    mockMvc
      .perform(get("/topics/listByCourseName?courseName= "))
      .andDo(print())
      .andExpect(status().isBadRequest());
  }
  
  @Test
  void register_givenATopicFormDto_shouldReturnStatusCreated_and_TheRegisteredTopic() throws Exception {
    Course course = new Course("course name", "category");
    TopicFormDto topicFormDto = new TopicFormDto("title test", "just a message test", course.getName());
    
    Topic topic = new Topic(topicFormDto.getTitle(), topicFormDto.getMessage() , course);
    topic.setId(1L);
    
    when(courseRepository
        .findByName(topicFormDto.getCourseName()))
    .thenReturn(Optional.of(course));
    
    Topic t1 = new Topic(topicFormDto.getTitle(), topicFormDto.getMessage() , course);
    Topic t2 = new Topic(topicFormDto.getTitle(), topicFormDto.getMessage() , course);
    
    assertThat(t1.equals(t2)).isTrue();

    when(topicRepository
        .save(new Topic(topicFormDto.getTitle(), topicFormDto.getMessage() , course)))
      .thenReturn(topic);

    final String formDto = "{\n"
        + "\"title\": \"title test\",\n"
        + "\"message\": \"just a message test\",\n"
        + "\"courseName\": \"course name\"\n"
        + "}";
    
    mockMvc
      .perform(post("/topics/register")
          .content(formDto)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.title").value("title test"))
      .andExpect(jsonPath("$.message").value("just a message test"));
  }
  
  @Test
  void register_shouldReturnStatusNotFound_ifThereAreNoCourseThatMatchesTheGivenCourseName() throws Exception {
    final String formDto = "{\n"
        + "\"title\": \"title test\",\n"
        + "\"message\": \"just a message test\",\n"
        + "\"courseName\": \"course name\"\n"
        + "}";
    
    mockMvc
      .perform(post("/topics/register")
          .content(formDto)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isNotFound());
  }
  
  @Test
  void detail_givenAnId_shouldReturnTheTopicDetails() throws Exception {
    Topic topic = new Topic("topic title", "topic message", new User(), new Course());
    topic.setId(1L);
    
    when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
    
    mockMvc
    .perform(get("/topics/details/1"))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.title").value("topic title"))
    .andExpect(jsonPath("$.message").value("topic message"));
  }
  
  @Test
  void detail_shouldReturnStatusNotFound_ifThereIsNoTopicThatMatchesTheGivenId() throws Exception {
    mockMvc
    .perform(get("/topics/details/1"))
    .andDo(print())
    .andExpect(status().isNotFound());
  }
  
  @Test
  void update_givenAnUpdateTopicFormDto_shouldReturnStatusOk_and_theUpdatedTopic() throws Exception {
    Topic topic = new Topic("topic title", "topic message", new User(), new Course());
    topic.setId(1L);
    
    when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));

    final String updateTopicFormDto = "{\n"
        + "\"title\": \"title updated\",\n"
        + "\"message\": \"just a message updated\"\n"
        + "}";
    
    mockMvc
      .perform(put("/topics/update/1")
          .content(updateTopicFormDto)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title").value("title updated"))
      .andExpect(jsonPath("$.message").value("just a message updated"));
  }
  
  @Test
  void update_shouldReturnStatusNotFound_ifThereIsNoTopicThatMatchesTheGivenId() throws Exception {
    final String updateTopicFormDto = "{\n"
        + "\"title\": \"title updated\",\n"
        + "\"message\": \"just a message updated\"\n"
        + "}";
    
    mockMvc
    .perform(put("/topics/update/2")
      .content(updateTopicFormDto)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isNotFound());
  }
  
  @Test
  void delete_givenAnId_shouldReturnStatusOkIfTopicWasDeleted() throws Exception {
    Topic topic = new Topic("topic title", "topic message", new User(), new Course());
    topic.setId(1L);
    
    when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
    
    mockMvc
      .perform(delete("/topics/delete/1"))
      .andExpect(status().isOk());
  }
  
  @Test
  void delete_shouldReturnStatusNotFound_ifThereIsNoTopicThatMatchesTheGivenId() throws Exception {
    mockMvc
      .perform(delete("/topics/delete/2"))
      .andExpect(status().isNotFound());
  }
}
