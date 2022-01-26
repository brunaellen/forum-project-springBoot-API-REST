package br.com.alura.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.controller.dto.DetailsOfATopicDto;
import br.com.alura.controller.dto.TopicDto;
import br.com.alura.controller.form.TopicForm;
import br.com.alura.model.Topic;
import br.com.alura.repository.CourseRepository;
import br.com.alura.service.TopicService;

@RequestMapping("/topics")
@RestController
public class TopicController {
  
  @Autowired
  private TopicService topicService;
  
  @Autowired
  CourseRepository courseRepository;
  
  @GetMapping
  public List<TopicDto> listAll(String courseName) {
    if(courseName == null) {
      return topicService.getAllTopics();
    } else {
      return topicService.getTopicsByCourseName(courseName);
    }
  }
  
  @PostMapping
  public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicForm topicForm, String courseName, UriComponentsBuilder uriBuilder) {
    Topic topic = topicService.registerATopic(topicForm, courseName);
    
    URI uri = uriBuilder.path("topics/{id}").buildAndExpand(topic.getId()).toUri();
    return ResponseEntity.created(uri).body(new TopicDto(topic));
  }
  
  @GetMapping("/{id}")
  public DetailsOfATopicDto detail(@PathVariable Long id) {
    return topicService.getATopicDetails(id);
  }
}
