package ro.unitbv.eduassistant.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionInfoDto {

	private String questionName;
	private int expectedAnswers;
	private int correctAnswers;
	private int wrongAnswers;
	private int pendingAnswers;
}
