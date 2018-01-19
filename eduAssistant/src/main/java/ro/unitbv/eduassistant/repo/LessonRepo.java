package ro.unitbv.eduassistant.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.unitbv.eduassistant.model.Lesson;

public interface LessonRepo  extends JpaRepository<Lesson, Long>{
	
	Optional<Lesson> findById(Long id);

}
