package ro.unitbv.eduassistant.service;

import ro.unitbv.eduassistant.dto.LessonDto;

public interface LessonService {

	void createLesson(LessonDto lessonDto);
	
	String createSessionForLesson(Long lessonId);
}
