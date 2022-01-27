package br.com.alura.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import br.com.alura.model.Course;
import br.com.alura.model.Topic;
import br.com.alura.repository.CourseRepository;
import br.com.alura.repository.TopicRepository;
import br.com.alura.service.TopicService;

@RequestMapping("/topics")
@RestController
public class TopicController {
  
  @Autowired
  private TopicService topicService;
  
  @Autowired
  private CourseRepository courseRepository;
  
  @Autowired
  private TopicRepository topicRepository;
  
  @GetMapping("/listAll")
  public ResponseEntity<List<TopicDto>> listAll() {
    List<TopicDto> allTopics = topicService.getAllTopics();
    
    if(allTopics.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(allTopics);
  }
  
  @GetMapping("/listByCourseName")
  public ResponseEntity<List<TopicDto>> listAllByCourseName(String courseName) {    
    if(courseName == null || courseName.isBlank()) {
      return ResponseEntity.badRequest().build();
    }
    
    List<TopicDto> topicDtoList = topicService.getTopicsByCourseName(courseName);
    
    if(topicDtoList.isEmpty()){
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(topicDtoList);
    }
  }

  @PostMapping("register")
  public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicFormDto topicForm, UriComponentsBuilder uriBuilder) {
    Optional<Course> course = courseRepository.findByName(topicForm.getCourseName());
    
    if(course.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    TopicDto topicDto = topicService.registerATopic(topicForm, course.get());

    URI uri = uriBuilder.path("topics/{id}").buildAndExpand(topicDto.getId()).toUri();
    return ResponseEntity.created(uri).body(topicDto);
  }

  @GetMapping("/details/{id}")
  public ResponseEntity<DetailsOfATopicDto> detail(@PathVariable Long id) {
    Optional<Topic> topic = topicRepository.findById(id);
    
    if(topic.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    DetailsOfATopicDto detailsOfATopicDto = new DetailsOfATopicDto(topic.get());
    detailsOfATopicDto.setAnswers(topic.get());

    return ResponseEntity.ok(detailsOfATopicDto);
  }
  
  @PutMapping("/update/{id}")
  @Transactional
  public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicFormDto updateTopicForm) {
    Optional<Topic> topic = topicRepository.findById(id);
    
    if(topic.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    topic.get().setTitle(updateTopicForm.getTitle());
    topic.get().setMessage(updateTopicForm.getMessage());
    
    return ResponseEntity.ok(new TopicDto(topic.get())); 
  }
  
  @DeleteMapping("delete/{id}")
  @Transactional
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Topic> topic = topicRepository.findById(id);
    
    if(topic.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    topicRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
