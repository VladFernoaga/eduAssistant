package ro.unitbv.eduassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.unitbv.eduassistant.dto.SessionDto;
import ro.unitbv.eduassistant.service.AuthorizationService;
import ro.unitbv.eduassistant.service.QuestionService;
import ro.unitbv.eduassistant.service.SessionService;

@RestController
public class TriggerQuestionController {

	@Autowired
	private QuestionService senderService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private AuthorizationService authService;
	
	@RequestMapping(value = "/lesson/{teacherUsername}/{lessonid}/{questionId}/trigger", method = RequestMethod.GET)
	public ResponseEntity<Void> triggerQuestion(@PathVariable("teacherUsername") String username, @PathVariable("lessonid") long lessonId,
			@PathVariable("questionId") long questionId) {
		authService.getTeacherIdBasedOn(username);
		
		SessionDto session = sessionService.getSession(lessonId);
		senderService.sendQuestionToRegisteredStudents(session.getSessionId(), questionId);

		return ResponseEntity.ok().build();
	}

}
