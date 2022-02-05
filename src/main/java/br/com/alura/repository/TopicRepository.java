package br.com.alura.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

  Page<Topic> findByCourse_Name(String courseName, Pageable pagination);
}
