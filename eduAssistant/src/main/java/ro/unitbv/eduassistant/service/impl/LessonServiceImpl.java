package ro.unitbv.eduassistant.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.unitbv.eduassistant.dto.LessonDto;
import ro.unitbv.eduassistant.model.Lesson;
import ro.unitbv.eduassistant.model.LessonSession;
import ro.unitbv.eduassistant.model.Teacher;
import ro.unitbv.eduassistant.repo.LessonRepo;
import ro.unitbv.eduassistant.repo.LessonSessionRepo;
import ro.unitbv.eduassistant.repo.TeacherRepo;
import ro.unitbv.eduassistant.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

	@Autowired
	private LessonRepo lessonRepo;
	@Autowired
	private LessonSessionRepo lessionSessionRepo;
	@Autowired
	private TeacherRepo teacherRepo;

	@Override
	public void createLesson(LessonDto lessonDto) {

		Optional<Teacher> teacher = teacherRepo.findById(lessonDto.getTeacherId());
		if (teacher.isPresent()) {
			Lesson lesson = new Lesson();
			lesson.setName(lessonDto.getName());
			lesson.setDescription(lessonDto.getDescription());
			lesson.setTeacher(teacher.get());
			lessonRepo.save(lesson);
		} else {
			throw new IllegalArgumentException(
					String.format("The given teacher with id: %s is not existing. The lesson cannot be created",
							lessonDto.getTeacherId()));
		}
	}

	@Override
	public String createSessionForLesson(Long lessonId) {
		Optional<Lesson> lesson = lessonRepo.findById(lessonId);
		if (lesson.isPresent()) {
			LessonSession lessonSession = new LessonSession();
			lessonSession.setLesson(lesson.get());
			String sessionKey = lesson.get().getTeacher().getId() + "_" + lessonId + "_"
					+ LocalDate.now().format(DateTimeFormatter.ISO_DATE) +"_"+ new Random().nextInt(1000);
			lessonSession.setSessionKey(sessionKey);
			lessionSessionRepo.save(lessonSession);
			return sessionKey;
		}
		throw new IllegalArgumentException(String
				.format("The given lession with id: %s is not existing. The session cannot be created", lessonId));
	}

}
