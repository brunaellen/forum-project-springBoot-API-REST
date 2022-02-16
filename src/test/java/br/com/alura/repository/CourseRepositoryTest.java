package br.com.alura.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.model.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CourseRepositoryTest {
  
  @Autowired
  private CourseRepository courseRepository;
  
  @Autowired
  private TestEntityManager entityManager;

  @Test
  void findByName_givenAName_ShouldReturnARegisteredCourse() {
    String courseName = "HTML 5";
    Course htmlCourse = new Course(courseName, "programming");
    entityManager.persist(htmlCourse);
    
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
