package br.com.alura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.controller.dto.TopicDto;
import br.com.alura.service.TopicService;

@RestController
public class TopicController {
  
  @Autowired
  private TopicService topicService;
  
  @RequestMapping("/topics")
  @ResponseBody
  public List<TopicDto> listing(String courseName) {
    if(courseName == null) {
      return topicService.getAllTopics();
    } else {
      return topicService.getTopicsByCourseName(courseName);
    }
  }
}
