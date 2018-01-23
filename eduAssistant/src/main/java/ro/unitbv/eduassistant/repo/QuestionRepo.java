package ro.unitbv.eduassistant.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.unitbv.eduassistant.model.Question;

public interface QuestionRepo extends JpaRepository<Question, Long>{

	Optional<Question> findById(Long id);
}
