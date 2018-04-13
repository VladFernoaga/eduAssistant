package ro.unitbv.eduassistant.service;

public interface AuthorizationService {

	boolean isTeacherAuthToAccessLesson(long teacherId, long lessonId);
	
	long getTeacherIdBasedOn(String username);
}
