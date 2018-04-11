package ro.unitbv.eduassistant.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.unitbv.eduassistant.model.Teacher;
import ro.unitbv.eduassistant.repo.TeacherRepo;
import ro.unitbv.eduassistant.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	 /** The Constant LOGGER. */
	  private static final Logger LOGGER = LogManager.getLogger();
	  
	private TeacherRepo teacherRepo;

	@Autowired
	public TeacherServiceImpl(TeacherRepo teacherRepo) {
		this.teacherRepo = teacherRepo;
	}

	@Override
	@Transactional
	public void singUp(String name) {
		Optional<Teacher> teacher = teacherRepo.findByName(name);
		if(teacher.isPresent()) {
			throw new IllegalArgumentException(
					String.format("The name %s allreay exist in the system please try with other name.", name));
		}else {
			Teacher teach = new Teacher();
			teach.setName(name);
			teacherRepo.save(teach);
			LOGGER.info(String.format("Teacher with name %s is singup sucessfuly",name)); 
		}
		
	}

}
