package ro.unitbv.eduassistant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.unitbv.eduassistant.dto.AllLessonQuestionDto;
import ro.unitbv.eduassistant.dto.QuestionStatsDto;
import ro.unitbv.eduassistant.model.Lesson;
import ro.unitbv.eduassistant.model.Question;
import ro.unitbv.eduassistant.repo.LessonRepo;
import ro.unitbv.eduassistant.repo.StudentRepo;
import ro.unitbv.eduassistant.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private LessonRepo lessonRepo;
	@Autowired
	private StudentRepo studRepo;
	
	@Override
	public AllLessonQuestionDto getAllLessionQuestionReport(Long lessonId) {
		
		Lesson lesson = lessonRepo.findById(lessonId).orElseThrow(()-> new IllegalArgumentException(String.format("Unknown lessonId: %s", lessonId)));

		AllLessonQuestionDto report = new AllLessonQuestionDto();
		report.setTotalStudNr(studRepo.count());
		
		for(Question quest: lesson.getQuestions()){
			int id = Integer.valueOf(quest.getId()+"");
			int nrOfCorrectAnswers = (int) quest.getResponses().stream().filter(rsp -> rsp.getMultipleChoiceQuestion().isCorrect()).count();
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
	public QuestionStatsDto getQuestionStats(Long lessonId, Long questionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
