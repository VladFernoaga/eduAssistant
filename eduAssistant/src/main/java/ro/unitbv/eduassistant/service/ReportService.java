package ro.unitbv.eduassistant.service;

import ro.unitbv.eduassistant.dto.AllLessonQuestionDto;
import ro.unitbv.eduassistant.dto.QuestionStatsDto;

public interface ReportService {

	AllLessonQuestionDto getAllLessionQuestionReport(Long lessonId);
	
	QuestionStatsDto getQuestionStats(Long lessonId, Long questionId);
}
