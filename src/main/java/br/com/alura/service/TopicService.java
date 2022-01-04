package br.com.alura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.controller.dto.TopicDto;
import br.com.alura.repository.TopicRepository;

@Service
public class TopicService {
  
  @Autowired
  private TopicRepository topicRepository;
  
  public List<TopicDto> getAllTopics() {
    return TopicDto.convertATopicListToTopicDtoList(topicRepository.findAll());
  }
}
