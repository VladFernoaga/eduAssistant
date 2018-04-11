package ro.unitbv.eduassistant.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.unitbv.eduassistant.model.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Long>{

	Optional<Teacher> findById(Long name);
	
	Optional<Teacher>	findByName(String name);

}