package ro.unitbv.eduassistant.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.unitbv.eduassistant.dto.SessionDto;
import ro.unitbv.eduassistant.service.AuthorizationService;
import ro.unitbv.eduassistant.service.SessionService;

@RestController
public class SessionController {

	/** The Constant LOGGER. */
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	private SessionService sessionService;
	@Autowired
	private AuthorizationService authService;
	
	@RequestMapping(value = "/lesson/{teacherUsername}/{lessonId}/session", method = RequestMethod.POST)
	public ResponseEntity<SessionDto> createSession(@PathVariable("teacherUsername") String username, @PathVariable("lessonId") long lessonId) {
		authService.getTeacherIdBasedOn(username);
		SessionDto session = sessionService.createSession(lessonId);
		return new ResponseEntity<SessionDto>(session, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/lesson/{teacherUsername}/{lessonId}/session", method = RequestMethod.PATCH)
	public ResponseEntity<SessionDto> closeSession(@PathVariable("teacherUsername") String username, @PathVariable("lessonId") long lessonId) {
		authService.getTeacherIdBasedOn(username);
		SessionDto session = sessionService.closeSession(lessonId);
		return new ResponseEntity<SessionDto>(session, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/lesson/{teacherUsername}/{lessonId}/session", method = RequestMethod.GET)
	public ResponseEntity<SessionDto> getSession(@PathVariable("teacherUsername") String username, @PathVariable("lessonId") long lessonId) {
		authService.getTeacherIdBasedOn(username);
		SessionDto session = sessionService.getSession(lessonId);
		return new ResponseEntity<SessionDto>(session, HttpStatus.CREATED);
	}
}
