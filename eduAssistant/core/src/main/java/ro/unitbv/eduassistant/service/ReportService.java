package ro.unitbv.eduassistant.service;

import ro.unitbv.eduassistant.dto.report.AllLessonQuestionDto;
import ro.unitbv.eduassistant.dto.report.LessonOverviewDto;
import ro.unitbv.eduassistant.dto.report.QuestionInfoDto;
import ro.unitbv.eduassistant.dto.report.QuestionStatsDto;

public interface ReportService {

	AllLessonQuestionDto getAllLessionQuestionReport(Long lessonId);
	
	QuestionStatsDto getQuestionStats(Long questionId);

	LessonOverviewDto getLessonOverview(long l);

	QuestionInfoDto getQuestionInfo(Long questionId);
}
