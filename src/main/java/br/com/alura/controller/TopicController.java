package br.com.alura.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@RequestMapping("/topics")
@RestController
public class TopicController {
  
  @Autowired
  private CourseRepository courseRepository;
  
  @Autowired
  private TopicRepository topicRepository;
  
  @GetMapping("/listAll")
  @Cacheable(value = "listOfTopics")
  public ResponseEntity<Page<TopicDto>> listAll(@PageableDefault(sort = "id", 
      direction = Direction.DESC, page = 0, size = 10) 
    Pageable pagination) {

    Page<Topic> allTopics = topicRepository.findAll(pagination);
    
    if(allTopics.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(TopicDto.convertATopicListToTopicDtoList(allTopics));
  }
  
  @GetMapping("/listByCourseName")
  @Cacheable(value = "topicsByCourseName")
  public ResponseEntity<Page<TopicDto>> listAllByCourseName(@RequestParam String courseName,
      @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10)
    Pageable pagination) {    
    
    if(courseName == null || courseName.isBlank()) {
      return ResponseEntity.badRequest().build();
    }
    
    Page<TopicDto> topicDtoList = TopicDto
        .convertATopicListToTopicDtoList(topicRepository
        .findByCourse_Name(courseName, pagination));
    
    if(topicDtoList.isEmpty()){
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(topicDtoList);
    }
  }

  @PostMapping("/register")
  @Transactional
  @CacheEvict(value = {"listOfTopics", "topicsByCourseName"}, allEntries = true)
  public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicFormDto topicForm, UriComponentsBuilder uriBuilder) {
    Optional<Course> course = courseRepository.findByName(topicForm.getCourseName());
    
    if(course.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Topic topic = topicRepository.save(new Topic(topicForm.getTitle(), topicForm.getMessage() , course.get()));
    TopicDto topicDto = new TopicDto(topic);

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
  @CacheEvict(value = {"listOfTopics", "topicsByCourseName"}, allEntries = true)
  public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicFormDto updateTopicFormDto) {
    Optional<Topic> topic = topicRepository.findById(id);
    
    if(topic.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    topic.get().setTitle(updateTopicFormDto.getTitle());
    topic.get().setMessage(updateTopicFormDto.getMessage());
    
    return ResponseEntity.ok(new TopicDto(topic.get())); 
  }
  
  @DeleteMapping("/delete/{id}")
  @Transactional
  @CacheEvict(value = {"listOfTopics", "topicsByCourseName"}, allEntries = true)
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Topic> topic = topicRepository.findById(id);
    
    if(topic.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    topicRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
