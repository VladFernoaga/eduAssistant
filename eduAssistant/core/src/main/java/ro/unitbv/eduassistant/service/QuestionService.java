package ro.unitbv.eduassistant.service;

public interface QuestionService {

	void sendQuestionToRegisteredStudents(String lessonSessionKey, long questionId);
	
	String checkCorrectness(Long questId, String selectedRsp, long chatId);

}
