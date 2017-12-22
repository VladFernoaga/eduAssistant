package ro.unitbv.eduassistant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.unitbv.eduassistant.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

	Student findById(Long name);
	
	Student	findByName(String name);

}
