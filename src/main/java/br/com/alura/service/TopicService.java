package br.com.alura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.controller.dto.TopicDto;
import br.com.alura.controller.form.TopicForm;
import br.com.alura.model.Course;
import br.com.alura.model.Topic;
import br.com.alura.repository.CourseRepository;
import br.com.alura.repository.TopicRepository;

@Service
public class TopicService {
  
  @Autowired
  private TopicRepository topicRepository;
  
  @Autowired
  CourseRepository courseRepository;
  
  public List<TopicDto> getAllTopics() {
    return TopicDto.convertATopicListToTopicDtoList(topicRepository.findAll());
  }

  public List<TopicDto> getTopicsByCourseName(String courseName) {
    return TopicDto.convertATopicListToTopicDtoList(topicRepository.findByCourse_Name(courseName));
  }
  
  public Topic registerATopic(TopicForm topicForm,String courseName) {
    Course course = courseRepository.findByName(courseName);
    String title = topicForm.getTitle();
    String message = topicForm.getMessage();
    Topic topic = new Topic(title, message , course);
    return topicRepository.save(topic);
  }
}
