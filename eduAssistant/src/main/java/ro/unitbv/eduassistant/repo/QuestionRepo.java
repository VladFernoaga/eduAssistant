package ro.unitbv.eduassistant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.unitbv.eduassistant.model.Question;

public interface QuestionRepo extends JpaRepository<Question, Long>{

}
