package ro.unitbv.eduassistant.dto.report;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonOverviewDto {

	private List<StudentLessonOverviewDto> data;
}
