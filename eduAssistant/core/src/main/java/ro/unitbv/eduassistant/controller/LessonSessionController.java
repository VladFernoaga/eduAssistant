package ro.unitbv.eduassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.unitbv.eduassistant.dto.LessonDto;
import ro.unitbv.eduassistant.dto.LessonSessionDto;
import ro.unitbv.eduassistant.dto.QuestionDto;
import ro.unitbv.eduassistant.service.LessonService;

@RestController
public class LessonSessionController {

	@Autowired
	private LessonService lessonService;

	@RequestMapping(value = "/lesson", method = RequestMethod.POST)
	public ResponseEntity<LessonSessionDto> createLesson(@RequestBody LessonDto lesson) {
		lessonService.createLesson(lesson);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/lesson/{lessonId}/session", method = RequestMethod.POST)
	public ResponseEntity<LessonSessionDto> createSession(@PathVariable("lessonId") long lessonId) {
		String lessonSessionID = lessonService.createSessionForLesson(lessonId);
		return ResponseEntity.ok().body(new LessonSessionDto(lessonSessionID));
	}

	@RequestMapping(value = "/lesson/{lessonId}/question", method = RequestMethod.POST)
	public ResponseEntity<Void> addQuestion(@PathVariable("lessonId") long lessonId, @RequestBody QuestionDto question) {
		lessonService.addQuestion(question, lessonId);
		return ResponseEntity.ok().build();
	}
}
