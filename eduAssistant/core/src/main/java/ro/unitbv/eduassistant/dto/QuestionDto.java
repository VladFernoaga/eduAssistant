package ro.unitbv.eduassistant.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionDto {

	private long id;
	private String question;
	private List<VariantValueDto> variants;
	
}
