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
import ro.unitbv.eduassistant.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "/report/allquestions", method = RequestMethod.GET)
	public ResponseEntity<AllLessonQuestionDto> allReportQuestions() {
		return ResponseEntity.ok().body(reportService.getAllLessionQuestionReport(1L));
	}
	
	@RequestMapping(value = "/report/question/{id}", method = RequestMethod.GET)
	public ResponseEntity<QuestionStatsDto> questionStats(@PathVariable("id") Long questionId) {
		return ResponseEntity.ok().body(reportService.getQuestionStats(questionId));
	}
	
	@RequestMapping(value = "/report/question/{id}/info", method = RequestMethod.GET)
	public ResponseEntity<QuestionInfoDto> questionStatsInfo(@PathVariable("id") Long questionId) {
		return ResponseEntity.ok().body(reportService.getQuestionInfo(questionId));
	}
	
	@RequestMapping(value = "/report/overview", method = RequestMethod.GET)
	public ResponseEntity<LessonOverviewDto> lessonOverview() {
		return ResponseEntity.ok().body(reportService.getLessonOverview(1L));
	}

}
