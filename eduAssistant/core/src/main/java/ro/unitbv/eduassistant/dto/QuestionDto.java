package ro.unitbv.eduassistant.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionDto {

	private String question;
	private List<VariantValueDto> variants;
	
}
