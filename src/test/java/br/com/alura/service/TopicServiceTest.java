package br.com.alura.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.alura.controller.dto.TopicDto;
import br.com.alura.controller.dto.TopicFormDto;
import br.com.alura.model.Course;
import br.com.alura.model.Topic;
import br.com.alura.repository.CourseRepository;
import br.com.alura.repository.TopicRepository;

@SpringBootTest(classes = {TopicService.class})
class TopicServiceTest {
  
  @Autowired
  private TopicService topicService;
  
  @MockBean
  private CourseRepository courseRepository;
  
  @Autowired
  @MockBean
  private TopicRepository topicRepository;
  
  @Test
  void getAllTopics_shouldReturnAListOfAllTopicDto() {
    Topic firstTopic = new Topic();
    Topic secondTopic = new Topic();
    firstTopic.setTitle("first title");
    List<Topic> listOfTopics = Arrays.asList(firstTopic, secondTopic);
    
    when(topicRepository.findAll()).thenReturn(listOfTopics);
    List<TopicDto> listAllTopicsDto = topicService.getAllTopics();

    assertThat(listAllTopicsDto).isNotNull();
    assertThat(listAllTopicsDto.size()).isEqualTo(2);
    assertThat(listAllTopicsDto.get(0)).hasFieldOrPropertyWithValue("title", "first title");
  }
  
  @Test
  void getAllTopics_shouldReturnAnEmptyListIfThereAreNoTopics() {
    assertThat(topicService.getAllTopics()).isEmpty();
  }
  
  @Test
  void getTopicsByCourseName_GivenACourseName_shouldReturnAListOfAllTopicDto() {
    Topic firstTopic = new Topic();
    Topic secondTopic = new Topic();
    firstTopic.setCourse(new Course("Spring Boot", "Programming"));
    secondTopic.setCourse(new Course("Spring Boot", "Front-end"));
    List<Topic> listOfTopics = Arrays.asList(firstTopic, secondTopic);
    
    when(topicRepository.findByCourse_Name("Spring Boot")).thenReturn(listOfTopics);
    List<TopicDto> listTopicsDtoByCourseName = topicService.getTopicsByCourseName("Spring Boot");
    assertThat(listTopicsDtoByCourseName).isNotNull();
    assertThat(listTopicsDtoByCourseName.size()).isEqualTo(2);
  }
  
  @Test
  void getTopicsByCourseName_shouldReturnAnEmptyList_IfThereAreNoMatchesForTheGivenCourseName() {
    assertThat(topicService.getTopicsByCourseName("Spring Boot")).isEmpty();
  }
  
  @Test
  void registerATopic_givenATopicFormAndCourse_shouldReturnTheSavedTopicAsATopicDto() {
    Course course = new Course("course name", "course category");   
    TopicFormDto topicFormDto = new TopicFormDto("title", "message", course.getName());

    Topic topic = new Topic(topicFormDto.getTitle(), topicFormDto.getMessage() , course);
    
    when(topicRepository.save(new Topic("title", "message" , course))).thenReturn(topic);
    
    assertThat(topicService.registerATopic(topicFormDto, course)).isInstanceOf(TopicDto.class);
    
    assertThat(topicService.registerATopic(topicFormDto, course))
      .hasFieldOrPropertyWithValue("title", "title");
    
    assertThat(topicService.registerATopic(topicFormDto, course))
    .hasFieldOrPropertyWithValue("message", "message");
  }
}




















