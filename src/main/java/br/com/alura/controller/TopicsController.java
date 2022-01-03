package br.com.alura.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.model.Course;
import br.com.alura.model.Topic;

@Controller
public class TopicsController {
  
  @RequestMapping("/topics")
  @ResponseBody
  public List<Topic> listing() {
    Topic topicExample = new Topic("query", "query using Spring",
        new Course("Spring", "Programming"));
    return Arrays.asList(topicExample, topicExample, topicExample);
  }
}
