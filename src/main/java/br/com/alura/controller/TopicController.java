package br.com.alura.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.controller.dto.DetailsOfATopicDto;
import br.com.alura.controller.dto.TopicDto;
import br.com.alura.controller.dto.TopicFormDto;
import br.com.alura.controller.dto.UpdateTopicFormDto;
import br.com.alura.model.Topic;
import br.com.alura.service.TopicService;

@RequestMapping("/topics")
@RestController
public class TopicController {
  
  @Autowired
  private TopicService topicService;
  
  @GetMapping
  public List<TopicDto> listAll(String courseName) {
    if(courseName == null) {
      return topicService.getAllTopics();
    } else {
      return topicService.getTopicsByCourseName(courseName);
    }
  }
  
  @PostMapping
  public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicFormDto topicForm, String courseName, UriComponentsBuilder uriBuilder) {
    Topic topic = topicService.registerATopic(topicForm, courseName);
    
    URI uri = uriBuilder.path("topics/{id}").buildAndExpand(topic.getId()).toUri();
    return ResponseEntity.created(uri).body(new TopicDto(topic));
  }
  
  @GetMapping("/{id}")
  public DetailsOfATopicDto detail(@PathVariable Long id) {
    return topicService.getATopicDetails(id);
  }
  
  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicFormDto updateTopicForm) {
    TopicDto topicDto = topicService.updateATopic(id, updateTopicForm);
    return ResponseEntity.ok(topicDto); 
  }
  
  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> delete(@PathVariable Long id) {
    topicService.deleteATopic(id);
    return ResponseEntity.ok().build();
  }
}
