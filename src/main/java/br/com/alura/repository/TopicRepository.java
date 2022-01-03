package br.com.alura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
