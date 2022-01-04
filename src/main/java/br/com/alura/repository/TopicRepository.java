package br.com.alura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

  List<Topic> findByCourse_Name(String courseName);
}
