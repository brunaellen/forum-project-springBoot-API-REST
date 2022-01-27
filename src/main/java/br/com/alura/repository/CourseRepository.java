package br.com.alura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
  Optional<Course> findByName(String name);
}
