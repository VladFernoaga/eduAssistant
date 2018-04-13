package ro.unitbv.eduassistant.controller.v2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.unitbv.eduassistant.api.exception.EduAssistantApiException;
import ro.unitbv.eduassistant.dto.LessonDto;
import ro.unitbv.eduassistant.dto.QuestionAddResponse;
import ro.unitbv.eduassistant.dto.QuestionDto;
import ro.unitbv.eduassistant.service.AuthorizationService;
import ro.unitbv.eduassistant.service.LessonService;

@RestController
public class LessonController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	private LessonService lessonService;
	@Autowired
	private AuthorizationService authService;
	
	@RequestMapping(value = "/lesson/{teacherUsername}", method = RequestMethod.POST)
	public ResponseEntity<LessonDto> createLesson(@PathVariable("teacherUsername") String username, @RequestBody LessonDto lesson) {
		LessonDto lessonDto = lessonService.addLesson(authService.getTeacherIdBasedOn(username), lesson);
		return new ResponseEntity<LessonDto>(lessonDto, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/lesson/{teacherUsername}/{lessonId}/question", method = RequestMethod.POST)
	public ResponseEntity<QuestionAddResponse> addQuestion(@PathVariable("teacherUsername") String username, @PathVariable("lessonId") long lessonId, @RequestBody QuestionDto question) {
		
		if (!authService.isTeacherAuthToAccessLesson(authService.getTeacherIdBasedOn(username), lessonId)) {
			String errMsg = String.format(
					"The teacher with id: %s is authorizate to add Questions to the lesson with id: %s", username,
					lessonId);
			EduAssistantApiException ex = new EduAssistantApiException(errMsg);
			LOGGER.error(errMsg, ex);
			throw ex;
		}
		
		QuestionAddResponse rsp = lessonService.addQuestion(question, lessonId);
		return new ResponseEntity<QuestionAddResponse>(rsp, HttpStatus.CREATED);
	}
}
