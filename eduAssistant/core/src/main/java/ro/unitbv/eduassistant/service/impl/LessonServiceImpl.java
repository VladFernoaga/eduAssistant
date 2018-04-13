package ro.unitbv.eduassistant.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.unitbv.eduassistant.api.exception.EduAssistantApiException;
import ro.unitbv.eduassistant.dto.LessonDto;
import ro.unitbv.eduassistant.dto.QuestionAddResponse;
import ro.unitbv.eduassistant.dto.QuestionDto;
import ro.unitbv.eduassistant.dto.VariantValueDto;
import ro.unitbv.eduassistant.model.Lesson;
import ro.unitbv.eduassistant.model.LessonSession;
import ro.unitbv.eduassistant.model.MultipleChoiceQuestion;
import ro.unitbv.eduassistant.model.Question;
import ro.unitbv.eduassistant.model.Teacher;
import ro.unitbv.eduassistant.model.VariantValue;
import ro.unitbv.eduassistant.repo.LessonRepo;
import ro.unitbv.eduassistant.repo.LessonSessionRepo;
import ro.unitbv.eduassistant.repo.QuestionRepo;
import ro.unitbv.eduassistant.repo.TeacherRepo;
import ro.unitbv.eduassistant.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	private LessonRepo lessonRepo;
	@Autowired
	private LessonSessionRepo lessionSessionRepo;
	@Autowired
	private TeacherRepo teacherRepo;
	@Autowired
	private QuestionRepo questionRepo;

	@Override
	public LessonDto addLesson(Long teacherId, LessonDto lessonDto) {

		Optional<Teacher> teacher = teacherRepo.findById(teacherId);
		if (teacher.isPresent()) {
			Lesson lesson = new Lesson();
			lesson.setName(lessonDto.getName());
			lesson.setDescription(lessonDto.getDescription());
			lesson.setTeacher(teacher.get());
			lessonRepo.save(lesson);
			return new LessonDto(lesson.getId(), lesson.getName(), lesson.getDescription());
		} else {
			throw new EduAssistantApiException(String
					.format("The given teacher with id: %s is not existing. The lesson cannot be created", teacherId));
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

	@Override
	public QuestionAddResponse addQuestion(QuestionDto questionDto, long lessonId) {

		Lesson lesson = lessonRepo.findById(lessonId).orElseThrow(
				() -> new IllegalArgumentException(String.format("The lesson id %s is invalid", lessonId)));

		Question question = new Question();
		question.setLesson(lesson);
		question.setQuestion(questionDto.getQuestion());

		MultipleChoiceQuestion mcQuest = new MultipleChoiceQuestion();
		mcQuest.setCorrectVriant(getCorrectVariant(questionDto.getVariants()));
		mcQuest.setVariants(questionDto.getVariants().stream()
				.map(v -> new VariantValue(v.getValue(), v.getHint())).collect(Collectors.toList()));
		question.setMultipeChoiceQuestion(mcQuest);
		
		questionRepo.save(question);
		return new QuestionAddResponse(question.getId(),question.getQuestion());
	}

	private String getCorrectVariant(List<VariantValueDto> variants) {
		List<VariantValueDto> correctVariants = variants.stream().filter(v -> v.getHint() == null).collect(Collectors.toList());
		if(correctVariants.size() == 0) {
			String msg = "The inserted Question has no correct Responses set.";
			LOGGER.error(msg);
			throw new EduAssistantApiException(msg);
		}
		if(correctVariants.size() > 1) {
			String msg = "The inserted Question has more than one correct Responses set.";
			LOGGER.error(msg);
			throw new EduAssistantApiException(msg);
		}
		return correctVariants.get(0).getValue();
	}
}
