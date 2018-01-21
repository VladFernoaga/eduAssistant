package ro.unitbv.eduassistant.service;

public interface QuestionService {

	void sendQuestionToRegisteredStudents(String lessonSessionKey, long questionId);
	
	boolean checkCorrectness(String question, String selectedVersion,long chatId);

}
