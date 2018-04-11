package ro.unitbv.eduassistant.service;

import ro.unitbv.eduassistant.dto.LessonDto;
import ro.unitbv.eduassistant.dto.QuestionDto;

public interface LessonService {

	void createLesson(LessonDto lessonDto);
	
	String createSessionForLesson(Long lessonId);
	
	void addQuestion(QuestionDto questionDto, long lessonId);
}
