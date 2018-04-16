package ro.unitbv.eduassistant.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.unitbv.eduassistant.dto.report.AllLessonQuestionDto;
import ro.unitbv.eduassistant.dto.report.LessonOverviewDto;
import ro.unitbv.eduassistant.dto.report.QuestionInfoDto;
import ro.unitbv.eduassistant.dto.report.QuestionStatsDto;
import ro.unitbv.eduassistant.dto.report.StudentLessonOverviewDto;
import ro.unitbv.eduassistant.dto.report.StudentQuestionStatDto;
import ro.unitbv.eduassistant.model.Lesson;
import ro.unitbv.eduassistant.model.Question;
import ro.unitbv.eduassistant.model.Registration;
import ro.unitbv.eduassistant.model.Response;
import ro.unitbv.eduassistant.repo.LessonRepo;
import ro.unitbv.eduassistant.repo.QuestionRepo;
import ro.unitbv.eduassistant.repo.RegistrationRepo;
import ro.unitbv.eduassistant.repo.ResponseDao;
import ro.unitbv.eduassistant.service.ReportService;
import ro.unitbv.eduassistant.util.Counter;
import ro.unitbv.eduassistant.util.Defaults;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private LessonRepo lessonRepo;
	@Autowired
	private QuestionRepo questRepo;
	@Autowired
	private ResponseDao responseDao;

	@Autowired
	private RegistrationRepo registrationRepo;

	@Override
	public AllLessonQuestionDto generateLessonReport(long lessonId, String sessionId) {
		Lesson lesson = lessonRepo.findById(lessonId)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Unknown lessonId: %s", lessonId)));

		AllLessonQuestionDto report = new AllLessonQuestionDto();
		report.setTotalStudNr(registrationRepo.getNumberOfRegistrations(sessionId));

		// TODO remove Id when the responses will be dynamic
		int id = 1;
		for (Question quest : lesson.getQuestions()) {
			// TODO remove Id when the responses will be dynamic
			if (id > 5) {
				break;
			}

			List<Response> correctResponses = responseDao
					.findResponsesForQuestionIdAndSessionId(quest.getId(), sessionId).stream()
					.filter(rsp -> rsp.getMultipleChoiceQuestion().isCorrect()).collect(Collectors.toList());

			int nrOfCorrectAnswers = correctResponses.stream().map(rsp -> rsp.getRegistration().getId())
					.collect(Collectors.toSet()).size();
			switch (id) {
			case 1:
				report.setQuestion1(nrOfCorrectAnswers);
				break;
			case 2:
				report.setQuestion2(nrOfCorrectAnswers);
				break;
			case 3:
				report.setQuestion3(nrOfCorrectAnswers);
				break;
			case 4:
				report.setQuestion4(nrOfCorrectAnswers);
				break;
			case 5:
				report.setQuestion5(nrOfCorrectAnswers);
				break;
			default:
				break;
			}
			// TODO remove Id when the responses will be dynamic
			id++;
		}

		return report;
	}

	@Override
	public QuestionStatsDto generateQuestionStats(long lessonId, long questionId, String sessionId) {
		questRepo.findByIdAndLessonId(questionId, lessonId).orElseThrow(() -> new IllegalArgumentException(
				String.format("Unknown questionId: %s for the lesson %s", questionId, lessonId)));

		List<StudentQuestionStatDto> data = new ArrayList<>();
		for (Registration registration : registrationRepo.getRegistrations(sessionId)) {
			StudentQuestionStatDto studStatDto = new StudentQuestionStatDto();
			studStatDto.setName(registration.getStudent().getName());

			Optional<Response> lastResponse = getLastResponse(registration, questionId);
			if (lastResponse.isPresent()) {
				studStatDto.setStatus(lastResponse.get().getMultipleChoiceQuestion().isCorrect()
						? Defaults.RESPONSE_STATUS_CORRECT : Defaults.RESPONSE_STATUS_WRONG);
			} else {
				studStatDto.setStatus(Defaults.RESPONSE_STATUS_PENDING);
			}
			data.add(studStatDto);
		}
		return new QuestionStatsDto(data);
	}

	@Override
	public QuestionInfoDto generateQuestionStatsInfo(long questionId, long lessonId, String sessionId) {
		Question question = questRepo.findByIdAndLessonId(questionId, lessonId)
				.orElseThrow(() -> new IllegalArgumentException(
						String.format("Unknown questionId: %s for the lesson %s", questionId, lessonId)));

		List<Registration> registrations = registrationRepo.getRegistrations(sessionId);
		Counter correctAnswers = new Counter();
		Counter wrongAnswers = new Counter();
		Counter pendingAnswers = new Counter();

		registrations.stream().map((r) -> this.getLastResponse(r, questionId)).forEach(lastResponse -> {
			if (lastResponse.isPresent()) {
				if (lastResponse.get().getMultipleChoiceQuestion().isCorrect()) {
					correctAnswers.increment();
				} else {
					wrongAnswers.increment();
				}
			} else {
				pendingAnswers.increment();
			}
		});
		return new QuestionInfoDto(question.getQuestion(), registrations.size(), correctAnswers.getIndex(),
				wrongAnswers.getIndex(), pendingAnswers.getIndex());
	}

	@Override
	public LessonOverviewDto generateLessonOverview(long lessonId, String sessionId) {

		List<StudentLessonOverviewDto> data = new ArrayList<>();
		for (Registration registration : registrationRepo.getRegistrations(sessionId)) {

			Map<Question, List<Response>> questionResponsesMap = new HashMap<>();
			for (Response response : registration.getResponses()) {
				if (questionResponsesMap.get(response.getQuestion()) == null) {
					questionResponsesMap.put(response.getQuestion(), new ArrayList<>(Arrays.asList(response)));
				} else {
					questionResponsesMap.get(response.getQuestion()).add(response);
				}
			}
			data.add(this.getStudentLessonOverview(questionResponsesMap, registration.getStudent().getName()));
		}
		return new LessonOverviewDto(data);
	}

	private StudentLessonOverviewDto getStudentLessonOverview(	Map<Question, List<Response>> questionResponsesMap, String studentName){
		
		StudentLessonOverviewDto studOverviewStat = new StudentLessonOverviewDto();
		studOverviewStat.setName(studentName);
		
		// TODO remove Id when the responses will be dynamic
		int id = 1;

		int respondedAnswers = 0;
		int correctAnswers = 0;
		for (Entry<Question, List<Response>> entry : questionResponsesMap.entrySet()) {
			// TODO remove Id when the responses will be dynamic
			if (id > 5) {
				break;
			}

			Optional<Response> lastResponse = getLastResponse(entry.getValue());
			int response = Defaults.NOT_ANSWERED;
			if (lastResponse.isPresent()) {
				response = lastResponse.get().getMultipleChoiceQuestion().isCorrect() ? Defaults.CORRECT_ANSWERE
						: Defaults.WRONG_ANSWERE;
				correctAnswers += response == Defaults.CORRECT_ANSWERE ? 1 : 0;
				respondedAnswers++;
			}

			switch (id) {
			case 1:
				studOverviewStat.setQuestion1(response);
				break;
			case 2:
				studOverviewStat.setQuestion2(response);
				break;
			case 3:
				studOverviewStat.setQuestion3(response);
				break;
			case 4:
				studOverviewStat.setQuestion4(response);
				break;
			case 5:
				studOverviewStat.setQuestion5(response);
				break;
			default:
				break;
			}

			// TODO remove Id when the responses will be dynamic
			id++;
		}

		// Calculate precente

		if (respondedAnswers == 5) {
			double precent = ((double) correctAnswers) / 5;

			studOverviewStat.setProcent(NumberFormat.getPercentInstance().format(precent));
		} else {
			studOverviewStat.setProcent("");
		}
		return studOverviewStat;
	}
	
	private Optional<Response> getLastResponse(Registration registration, long questionId) {
		List<Response> rsps = registration.getResponses().stream()
				.filter(r -> r.getQuestion().getId().equals(questionId)).collect(Collectors.toList());
		return getLastResponse(rsps);
	}

	private Optional<Response> getLastResponse(List<Response> responses) {
		responses.sort((q1, q2) -> (int) (q2.getId() - q1.getId()));
		return responses.isEmpty() ? Optional.empty() : Optional.of(responses.get(0));
	}

}
