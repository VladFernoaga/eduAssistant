package ro.unitbv.eduassistant.dto;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper=true)
public class LessonWithQuestionsDto extends LessonDto{

	@Getter
	@Setter
	private List<QuestionDto> questions;
}
