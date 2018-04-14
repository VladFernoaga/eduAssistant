package ro.unitbv.eduassistant.service;

import ro.unitbv.eduassistant.dto.report.AllLessonQuestionDto;
import ro.unitbv.eduassistant.dto.report.LessonOverviewDto;
import ro.unitbv.eduassistant.dto.report.QuestionInfoDto;
import ro.unitbv.eduassistant.dto.report.QuestionStatsDto;

public interface ReportService {

	AllLessonQuestionDto generateLessonReport(long lessonId, String sessionId);
	
	QuestionStatsDto generateQuestionStats(long lessonId,long questionId, String sessionId);

	LessonOverviewDto generateLessonOverview(long lessonId, String sessionId);

	QuestionInfoDto generateQuestionStatsInfo(long questionId, long lessonId, String sessionId);
}
