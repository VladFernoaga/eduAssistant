package ro.unitbv.eduassistant.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import ro.unitbv.eduassistant.model.Student;
import ro.unitbv.eduassistant.repo.LessonRepo;
import ro.unitbv.eduassistant.repo.QuestionRepo;
import ro.unitbv.eduassistant.repo.StudentRepo;
import ro.unitbv.eduassistant.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private LessonRepo lessonRepo;
	@Autowired
	private StudentRepo studRepo;
	@Autowired
	private QuestionRepo questRepo;

	@Override
	public AllLessonQuestionDto getAllLessionQuestionReport(Long lessonId) {

		Lesson lesson = lessonRepo.findById(lessonId)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Unknown lessonId: %s", lessonId)));

		AllLessonQuestionDto report = new AllLessonQuestionDto();
		report.setTotalStudNr(studRepo.count());

		for (Question quest : lesson.getQuestions()) {
			int id = Integer.valueOf(quest.getId() + "");

			List<Response> responses = quest.getResponses().stream()
					.filter(rsp -> rsp.getMultipleChoiceQuestion().isCorrect()).collect(Collectors.toList());
			Set<Long> registrationIdWithCorrectAnswers = new HashSet<>();
			responses.forEach(rsp -> registrationIdWithCorrectAnswers.add(rsp.getRegistration().getId()));
			
			int nrOfCorrectAnswers = registrationIdWithCorrectAnswers.size();
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
		}

		return report;
	}

	@Override
	public QuestionStatsDto getQuestionStats(Long questionId) {
		questRepo.findById(questionId)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Unknown questionId: %s", questionId)));

		List<StudentQuestionStatDto> data = new ArrayList<>();

		List<Student> students = studRepo.findAll();
		for (Student stud : students) {

			List<Registration> registrations = stud.getRegistrations();
			if (registrations != null && !registrations.isEmpty()) {

				List<Response> questionResponses = registrations.get(0).getResponses().stream()
						.filter(r -> r.getQuestion().getId().equals(questionId)).collect(Collectors.toList());
				Response finalResponse = null;

				questionResponses.sort((q1, q2) -> (int) (q1.getId() - q2.getId()));
				if (!questionResponses.isEmpty()) {
					finalResponse = questionResponses.get(questionResponses.size() - 1);
				}

				StudentQuestionStatDto studStatDto = new StudentQuestionStatDto();
				studStatDto.setName(stud.getName());
				if (finalResponse != null) {
					studStatDto.setStatus(finalResponse.getMultipleChoiceQuestion().isCorrect() ? "correct" : "wrong");
				} else {
					studStatDto.setStatus("pending");
				}
				data.add(studStatDto);
			}
		}
		return new QuestionStatsDto(data);
	}

	@Override
	public QuestionInfoDto getQuestionInfo(Long questionId) {
		Question question = questRepo.findById(questionId)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Unknown questionId: %s", questionId)));

		int expectedAmountOfAnswers = 0;
		int correctAmountOfAnswers = 0;
		int wrongAmountOfAnswers = 0;
		int pendingAmountOfAnswers = 0;
		List<Student> students = studRepo.findAll();
		for (Student stud : students) {
			List<Registration> registrations = stud.getRegistrations();
			if (registrations != null && !registrations.isEmpty()) {
				expectedAmountOfAnswers++;
				
				List<Response> questionResponses = registrations.get(0).getResponses().stream()
						.filter(r -> r.getQuestion().getId().equals(questionId)).collect(Collectors.toList());
				Response finalResponse = null;

				questionResponses.sort((q1, q2) -> (int) (q1.getId() - q2.getId()));
				if (!questionResponses.isEmpty()) {
					finalResponse = questionResponses.get(questionResponses.size() - 1);
				}

				if (finalResponse != null) {
					if(finalResponse.getMultipleChoiceQuestion().isCorrect()) {
						correctAmountOfAnswers++;
					}else {
						wrongAmountOfAnswers++;
					}
				} else {
					pendingAmountOfAnswers++;
				}
			}
		}
		return new QuestionInfoDto(question.getQuestion(),expectedAmountOfAnswers,correctAmountOfAnswers,wrongAmountOfAnswers,pendingAmountOfAnswers);
	}

	@Override
	public LessonOverviewDto getLessonOverview(long lessonId) {

		List<StudentLessonOverviewDto> data = new ArrayList<>();

		List<Student> students = studRepo.findAll();
		for (Student stud : students) {
			List<Registration> registrations = stud.getRegistrations();
			if (registrations != null && !registrations.isEmpty()) {

				Map<Question, List<Response>> questionResponsesMap = new HashMap<>();
				for (Response rsp : registrations.get(0).getResponses()) {
					Question quest = rsp.getQuestion();
					List<Response> questResps = questionResponsesMap.get(quest);
					if (questResps == null) {
						questResps = new ArrayList<>();
					}
					questResps.add(rsp);
					questionResponsesMap.put(quest, questResps);
				}

				StudentLessonOverviewDto studOverviewStat = new StudentLessonOverviewDto();
				studOverviewStat.setName(stud.getName());
				int totalNrRespondedQuestions = 0;
				int correctQuestions = 0;
				for (Question quest : questionResponsesMap.keySet()) {

					List<Response> questResps = questionResponsesMap.get(quest);
					questResps.sort((q1, q2) -> (int) (q1.getId() - q2.getId()));
					Response finalResponse = null;
					if (!questResps.isEmpty()) {
						finalResponse = questResps.get(questResps.size() - 1);
					}

					int rsp = 0;
					if (finalResponse != null) {
						rsp = finalResponse.getMultipleChoiceQuestion().isCorrect() ? 2 : 1;

						totalNrRespondedQuestions++;
						correctQuestions += finalResponse.getMultipleChoiceQuestion().isCorrect() ? 1 : 0;
					}

					switch (Integer.valueOf(quest.getId() + "")) {
					case 1:
						studOverviewStat.setQuestion1(rsp);
						break;
					case 2:
						studOverviewStat.setQuestion2(rsp);
						break;
					case 3:
						studOverviewStat.setQuestion3(rsp);
						break;
					case 4:
						studOverviewStat.setQuestion4(rsp);
						break;
					case 5:
						studOverviewStat.setQuestion5(rsp);
						break;
					default:
						break;
					}

				}

				// Calculate precente

				if (totalNrRespondedQuestions == 5) {
					double precent = ((double) correctQuestions) / 5;

					studOverviewStat.setProcent(NumberFormat.getPercentInstance().format(precent));
				} else {
					studOverviewStat.setProcent("");
				}
				data.add(studOverviewStat);

			}
		}

		return new LessonOverviewDto(data);
	}
}
