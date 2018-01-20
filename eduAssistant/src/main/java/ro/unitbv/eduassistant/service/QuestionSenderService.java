package ro.unitbv.eduassistant.service;

public interface QuestionSenderService {

	void sendQuestionToRegisteredStudents(String lessonSessionKey, long questionId);

}
