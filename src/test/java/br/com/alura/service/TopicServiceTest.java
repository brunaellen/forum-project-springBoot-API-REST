package br.com.alura.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.alura.controller.dto.TopicDto;
import br.com.alura.model.Topic;
import br.com.alura.repository.TopicRepository;

@SpringBootTest
class TopicServiceTest {
  
  @Autowired
  private TopicService topicService;
  
  @Autowired
  @MockBean
  private TopicRepository topicRepository;
  
  @Test
  void getAllTopics_shouldReturnAListOfAllTopicDto() {
    List<Topic> listOfTopics = new ArrayList<>();
    Topic firstTopic = new Topic();
    Topic secondTopic = new Topic();
    firstTopic.setTitle("first title");
    listOfTopics = Arrays.asList(firstTopic, secondTopic);
    
    when(topicRepository.findAll()).thenReturn(listOfTopics);
    List<TopicDto> listAllTopicsDto = topicService.getAllTopics();

    assertThat(listAllTopicsDto).isNotNull();
    assertThat(listAllTopicsDto.size()).isEqualTo(2);
    assertThat(listAllTopicsDto.get(0)).hasFieldOrPropertyWithValue("title", "first title");
  }
}
