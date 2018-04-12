package ro.unitbv.eduassistant.api.exception;

public class EduAssistantApiException extends RuntimeException{
	private static final long serialVersionUID = -4455556285305825345L;
	
	private String message;

	public EduAssistantApiException(String message) {
		super();
		this.message = message;
	} 
	
	public  EduAssistantApiException(String message, Throwable cause) {
		super(cause);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
