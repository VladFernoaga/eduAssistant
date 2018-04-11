package ro.unitbv.eduassistant.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.unitbv.eduassistant.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

	Optional<Student> findById(Long name);
	
	Optional<Student>	findByChatbotId(Long chatbotId);

}
