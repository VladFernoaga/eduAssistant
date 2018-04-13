package ro.unitbv.eduassistant.service;

import ro.unitbv.eduassistant.dto.LessonDto;
import ro.unitbv.eduassistant.dto.QuestionAddResponse;
import ro.unitbv.eduassistant.dto.QuestionDto;

public interface LessonService {

	LessonDto addLesson(Long teacherId, LessonDto lessonDto);
	
	String createSessionForLesson(Long lessonId);
	
	QuestionAddResponse addQuestion(QuestionDto questionDto, long lessonId);
}
