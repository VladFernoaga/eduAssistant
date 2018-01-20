package ro.unitbv.eduassistant.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {

	private String question;
	
	private Map<Integer,String> variants;
	
	private int coorectVariant;
}
