package ro.unitbv.eduassistant.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.unitbv.eduassistant.dto.LessonDto;
import ro.unitbv.eduassistant.service.LessonService;

@RestController
public class LessonController {

	@Autowired
	private LessonService lessonService;
	
	@RequestMapping(value = "/lesson/{teacherId}", method = RequestMethod.POST)
	public ResponseEntity<LessonDto> createLesson(@PathVariable("teacherId") Long id, @RequestBody LessonDto lesson) {
		LessonDto lessonDto = lessonService.addLesson(id, lesson);
		return new ResponseEntity<LessonDto>(lessonDto, HttpStatus.CREATED);
	}
	
}
