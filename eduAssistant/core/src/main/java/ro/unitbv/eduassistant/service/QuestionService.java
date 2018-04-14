package ro.unitbv.eduassistant.service;

public interface QuestionService {

	void sendQuestionToRegisteredStudents(String lessonSessionKey, long questionId);
	
	String checkCorrectness(Long lessonSessionId,Long questId, int selectedRspId, long chatId);

}
