package ro.unitbv.eduassistant.service;

public interface RegistrationService {

	String registerNewStudentInSession(String sessionKey, String name, Long chatbotId);
}
