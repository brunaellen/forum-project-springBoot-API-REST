package br.com.alura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.controller.dto.TopicDto;
import br.com.alura.controller.form.TopicForm;
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
  public List<TopicDto> listing(String courseName) {
    if(courseName == null) {
      return topicService.getAllTopics();
    } else {
      return topicService.getTopicsByCourseName(courseName);
    }
  }
  
  @PostMapping
  public void register(@RequestBody TopicForm topicForm, String courseName) {
    topicService.registerATopic(topicForm, courseName);
  }
}
