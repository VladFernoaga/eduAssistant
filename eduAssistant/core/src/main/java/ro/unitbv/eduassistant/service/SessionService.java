package ro.unitbv.eduassistant.service;

import ro.unitbv.eduassistant.dto.SessionDto;

public interface SessionService {

	SessionDto createSession(Long lessonId);
	
	SessionDto closeSession(Long lessonId);
	
	SessionDto getSession(Long lessonId);
}
