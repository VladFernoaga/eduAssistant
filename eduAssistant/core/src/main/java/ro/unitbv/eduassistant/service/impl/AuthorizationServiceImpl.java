package ro.unitbv.eduassistant.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.unitbv.eduassistant.api.exception.EduAssistantApiException;
import ro.unitbv.eduassistant.model.Lesson;
import ro.unitbv.eduassistant.repo.LessonRepo;
import ro.unitbv.eduassistant.repo.TeacherRepo;
import ro.unitbv.eduassistant.service.AuthorizationService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{

	@Autowired
	private LessonRepo lessonRepo;
	@Autowired
	private TeacherRepo teacherRepo;
	
	@Override
	public boolean isTeacherAuthToAccessLesson(long teacherId, long lessonId) {
		boolean isAuth = false;
		Optional<Lesson> lesson = lessonRepo.findById(lessonId);
		if(lesson.isPresent()) {
			isAuth = lesson.get().getTeacher().getId().equals(teacherId);
		}
		return isAuth;
	}

	@Override
	public long getTeacherIdBasedOn(String username) {
		return teacherRepo.findByUsername(username).orElseThrow(() -> new EduAssistantApiException(
				String.format("The User with username: %s is not registered as teacher", username))).getId();
	}

}
