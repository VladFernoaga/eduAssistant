package ro.unitbv.eduassistant.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.unitbv.eduassistant.api.exception.EduAssistantApiException;
import ro.unitbv.eduassistant.dto.SessionDto;
import ro.unitbv.eduassistant.model.Lesson;
import ro.unitbv.eduassistant.model.LessonSession;
import ro.unitbv.eduassistant.repo.LessonRepo;
import ro.unitbv.eduassistant.repo.LessonSessionRepo;
import ro.unitbv.eduassistant.service.SessionService;
import ro.unitbv.eduassistant.util.Defaults;

@Service
public class SessionServiceImpl implements SessionService {

	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private LessonRepo lessonRepo;
	@Autowired
	private LessonSessionRepo lessonSessionRepo;

	@Override
	public SessionDto createSession(Long lessonId) {
		Lesson lesson = lessonRepo.findById(lessonId).orElseThrow(() -> new IllegalArgumentException(String
				.format("The given lession with id: %s is not existing. The session cannot be created", lessonId)));
		Optional<LessonSession> lessonSessionOp = lessonSessionRepo.findByLessonIdAndState(lessonId,
				Defaults.STATUS_OPEN);

		if (lessonSessionOp.isPresent()) {
			String err = String
					.format("The lesson with id: %s has allready a open session, use GET with same URI to get it.",lessonId);
			LOGGER.error(() -> err);
			throw new EduAssistantApiException(err);
		} else {
			LessonSession lessonSession = new LessonSession();
			lessonSession.setLesson(lesson);
			String sessionKey = lesson.getTeacher().getId() + "_" + lessonId + "_"
					+ LocalDate.now().format(DateTimeFormatter.ISO_DATE) + "_" + new Random().nextInt(1000);
			lessonSession.setSessionKey(sessionKey);
			lessonSession.setState(Defaults.STATUS_OPEN);
			lessonSessionRepo.save(lessonSession);
			return new SessionDto(lessonSession.getSessionKey(), lessonSession.getState());
		}
	}

	@Override
	public SessionDto closeSession(Long lessonId) {
		lessonRepo.findById(lessonId).orElseThrow(() -> new IllegalArgumentException(String
				.format("The given lession with id: %s is not existing. The session cannot be created", lessonId)));
		LessonSession lessonSession = lessonSessionRepo.findByLessonIdAndState(lessonId, Defaults.STATUS_OPEN)
				.orElseThrow(() -> new EduAssistantApiException(String.format(
						"The lesson with id: %s has no open session that can be closed, use POST with same URI to create a new one.",
						lessonId)));
		lessonSession.setState(Defaults.STATUS_CLOSE);
		lessonSessionRepo.save(lessonSession);
		return new SessionDto(lessonSession.getSessionKey(), lessonSession.getState());
	}

	@Override
	public SessionDto getSession(Long lessonId) {
		lessonRepo.findById(lessonId).orElseThrow(() -> new IllegalArgumentException(String
				.format("The given lession with id: %s is not existing. The session cannot be created", lessonId)));
		LessonSession lessonSession = lessonSessionRepo.findByLessonIdAndState(lessonId, Defaults.STATUS_OPEN)
				.orElseThrow(() -> new EduAssistantApiException(String.format(
						"The lesson with id: %s has no open session, use POST with same URI to create a new one.",
						lessonId)));

		return new SessionDto(lessonSession.getSessionKey(), lessonSession.getState());
	}
}
