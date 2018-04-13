package ro.unitbv.eduassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.unitbv.eduassistant.dto.report.AllLessonQuestionDto;
import ro.unitbv.eduassistant.dto.report.LessonOverviewDto;
import ro.unitbv.eduassistant.dto.report.QuestionInfoDto;
import ro.unitbv.eduassistant.dto.report.QuestionStatsDto;
import ro.unitbv.eduassistant.service.AuthorizationService;
import ro.unitbv.eduassistant.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;
	@Autowired
	private AuthorizationService authService;
	
	@RequestMapping(value = "/report/{teacherUsername}/{lessonId}/{sessionId}/allquestions", method = RequestMethod.GET)
	public ResponseEntity<AllLessonQuestionDto> allReportQuestions(@PathVariable("teacherUsername") String username,
			@PathVariable("lessonId") long lessonId, @PathVariable("sessionId") String sessionId) {
		authService.getTeacherIdBasedOn(username);
		//TODO IMPL
		
		return ResponseEntity.ok().body(reportService.getAllLessionQuestionReport(1L));
	}

	@RequestMapping(value = "/report/{teacherUsername}/{lessonId}/{sessionId}/question/{questionId}", method = RequestMethod.GET)
	public ResponseEntity<QuestionStatsDto> questionStats(@PathVariable("teacherUsername") String username,
			@PathVariable("lessonId") long lessonId, @PathVariable("sessionId") String sessionId,
			@PathVariable("questionId") Long questionId) {
		authService.getTeacherIdBasedOn(username);
		//TODO IMPL
		return ResponseEntity.ok().body(reportService.getQuestionStats(questionId));
	}

	@RequestMapping(value = "/report/{teacherUsername}/{lessonId}/{sessionId}/question/{id}/info", method = RequestMethod.GET)
	public ResponseEntity<QuestionInfoDto> questionStatsInfo(@PathVariable("teacherUsername") String username,
			@PathVariable("lessonId") long lessonId, @PathVariable("sessionId") String sessionId,
			@PathVariable("questionId") Long questionId) {
		authService.getTeacherIdBasedOn(username);
		//TODO IMPL
		return ResponseEntity.ok().body(reportService.getQuestionInfo(questionId));
	}

	@RequestMapping(value = "/report/{teacherUsername}/{lessonId}/{sessionId}overview", method = RequestMethod.GET)
	public ResponseEntity<LessonOverviewDto> lessonOverview(@PathVariable("teacherUsername") String username,
			@PathVariable("lessonId") long lessonId, @PathVariable("sessionId") String sessionId) {
		authService.getTeacherIdBasedOn(username);
		//TODO IMPL
		return ResponseEntity.ok().body(reportService.getLessonOverview(1L));
	}

}
