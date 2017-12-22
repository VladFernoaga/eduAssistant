package ro.unitbv.eduassistant.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ro.unitbv.eduassistant.dto.StudentDto;
import ro.unitbv.eduassistant.model.Student;
import ro.unitbv.eduassistant.repo.StudentRepo;
import ro.unitbv.eduassistant.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	 /** The Constant LOGGER. */
	  private static final Logger LOGGER = LogManager.getLogger();
	  
	private StudentRepo studentRepo;

	@Autowired
	public StudentServiceImpl(StudentRepo studentRepo) {
		this.studentRepo = studentRepo;
	}

	@Override
	@Transactional
	public StudentDto addNewOrGetExisting(String name) {
		Optional<Student> student = studentRepo.findById(name);
		if (student.isPresent()) {
			return toStudentDto(student.get());
		} else {
			Student stud = new Student();
			stud.setName(name);
			try {
				studentRepo.save(stud);
				return toStudentDto(student.get());
			} catch (DataIntegrityViolationException ex) {
				throw new IllegalArgumentException(
						String.format("The name %s allreay exist in this seesion please try with other name.", name));
			}

		}
	}

	private StudentDto toStudentDto(Student student) {
		return new StudentDto(student.getName());
	}

}
