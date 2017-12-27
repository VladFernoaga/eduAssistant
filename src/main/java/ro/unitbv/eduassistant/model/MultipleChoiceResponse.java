package ro.unitbv.eduassistant.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultipleChoiceResponse {
	
	private String response;
	private boolean correct;
}
