package ro.unitbv.eduassistant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MultipleChoiceResponse {
	
	private String response;
	private boolean correct;
}
