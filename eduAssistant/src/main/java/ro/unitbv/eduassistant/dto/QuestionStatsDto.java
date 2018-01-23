package ro.unitbv.eduassistant.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionStatsDto {

	private List<StudentStatDto> data;
}
