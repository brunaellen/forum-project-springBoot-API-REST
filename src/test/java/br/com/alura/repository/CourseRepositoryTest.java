package br.com.alura.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.alura.model.Course;

@DataJpaTest
class CourseRepositoryTest {
  
  @Autowired
  private CourseRepository courseRepository;

  @Test
  void findByName_givenAName_ShouldReturnARegisteredCourse() {
    String courseName = "HTML 5";
    Optional<Course> course = courseRepository.findByName(courseName);
    
    assertThat(course).isNotNull();
    assertThat(course.get().getName()).isEqualTo(courseName);
  }

  @Test
  void findByName_givenAName_ShouldReturnEmptyIfCourseNotFound() {
    String courseName = "course not found";
    Optional<Course> course = courseRepository.findByName(courseName);
    
    assertThat(course).isEmpty();
  }
}
