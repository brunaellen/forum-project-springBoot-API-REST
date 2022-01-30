package br.com.alura.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.controller.dto.TopicFormDto;
import br.com.alura.model.Course;
import br.com.alura.model.Topic;
import br.com.alura.repository.CourseRepository;
import br.com.alura.repository.TopicRepository;
import br.com.alura.service.TopicService;

@WebMvcTest(controllers = {TopicController.class})
class TopicControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private TopicService topicService;
  
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
    
    when(topicRepository.findAll()).thenReturn(allTopics);
    
    mockMvc
    .perform(get("/topics/listAll"))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.[0].title").value("topic title"))
    .andExpect(jsonPath("$.[0].message").value("topic message"))
    .andExpect(jsonPath("$.[1].title").value("second title"))
    .andExpect(jsonPath("$.[1].message").value("second message"));
  }
  
  @Test
  void listAll_shouldReturnStatusNotFound_ifThereAreNoTopicsRegistered() throws Exception {
    mockMvc
      .perform(get("/topics/listAll"))
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
    
    when(topicRepository.findByCourse_Name("course name")).thenReturn(allTopics);
    
    mockMvc
    .perform(get("/topics/listByCourseName?courseName=course name"))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.[0].title").value("topic title"))
    .andExpect(jsonPath("$.[0].message").value("topic message"))
    .andExpect(jsonPath("$.[1].title").value("second title"))
    .andExpect(jsonPath("$.[1].message").value("second message"));
  }
  
  @Test
  void listAllByCourseName_shouldReturnStatusNotFound_ifThereAreNoTopicsRegistered() throws Exception {
    mockMvc
    .perform(get("/topics/listByCourseName?courseName=course name"))
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
  void register_givenATopicForm_shouldReturnStatusCreated_and_TheRegisteredTopic() throws Exception {
    Course course = new Course("course name", "category");
    TopicFormDto topicFormDto = new TopicFormDto("title test", "just a message test", course.getName());
    
    Topic topic = new Topic(topicFormDto.getTitle(), topicFormDto.getMessage() , course);
    
    when(courseRepository
        .findByName(topicFormDto.getCourseName()))
    .thenReturn(Optional.of(course));
    
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
}
