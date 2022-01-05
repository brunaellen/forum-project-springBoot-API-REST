package br.com.alura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
  Course findByName(String name);
}
