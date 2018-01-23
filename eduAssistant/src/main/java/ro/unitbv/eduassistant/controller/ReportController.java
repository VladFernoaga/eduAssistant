package ro.unitbv.eduassistant.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.unitbv.eduassistant.dto.AllLessonQuestionDto;
import ro.unitbv.eduassistant.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "/report/allquestions", method = RequestMethod.GET)
	public ResponseEntity<AllLessonQuestionDto> createLesson() {
		return ResponseEntity.ok().body(reportService.getAllLessionQuestionReport(1L));
	}
	
	@RequestMapping(value = "/report/1/question/{id}", method = RequestMethod.GET)
	public ResponseEntity<AllLessonQuestionDto> createLesson(@PathParam("id") Long questionId) {
		return ResponseEntity.ok().body(reportService.getAllLessionQuestionReport(1L));
	}
}
