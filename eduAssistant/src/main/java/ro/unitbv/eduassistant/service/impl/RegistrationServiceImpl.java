package ro.unitbv.eduassistant.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ro.unitbv.eduassistant.model.LessonSession;
import ro.unitbv.eduassistant.model.Registration;
import ro.unitbv.eduassistant.model.Student;
import ro.unitbv.eduassistant.repo.LessonSessionRepo;
import ro.unitbv.eduassistant.repo.RegistrationRepo;
import ro.unitbv.eduassistant.repo.StudentRepo;
import ro.unitbv.eduassistant.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationRepo regRepo;
	
	@Autowired
	private StudentRepo studRepo;
	
	@Autowired
	private LessonSessionRepo lessonSessionRepo;
	
	@Override
	public String registerNewStudentInSession(String sessionKey, String name, Long chatbotId) {
		
		Optional<LessonSession> lessSes = lessonSessionRepo.findBySessionKey(sessionKey);
		if(!lessSes.isPresent()){
			return "*Unvalid sessionKey*";
		}
		
		Optional<Student> student = studRepo.findByChatbotId(chatbotId);
		if( !student.isPresent()){
			Student newStud = new Student();
			newStud.setChatbotId(chatbotId);
			newStud.setName(name);
			studRepo.save(newStud);
			student = Optional.of(newStud);
		}
		
		Registration registration = new Registration();
		registration.setLessonSession(lessSes.get());
		registration.setStudent(student.get());
		
		try{
			regRepo.save(registration);
		}catch( DataIntegrityViolationException e){
			return "*The user is allready registered in this session*";
		}
		
		return "User succesfully registered";
	}

}
