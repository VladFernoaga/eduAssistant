package ro.unitbv.eduassistant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonDto {

	private String name;
	private String description;
	private long teacherId;
}
