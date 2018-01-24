package ro.unitbv.eduassistant.dto.report;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentLessonOverviewDto {

	private String name;
	private int question1;
	private int question2;
	private int question3;
	private int question4;
	private int question5;
	private String procent;
}
