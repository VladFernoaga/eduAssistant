package ro.unitbv.eduassistant.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultipleChoiceQuestion {
	
	private List<String> variants;
	private String correctVriant;
}
