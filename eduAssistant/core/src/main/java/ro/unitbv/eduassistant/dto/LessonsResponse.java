package ro.unitbv.eduassistant.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonsResponse {

	private List<LessonWithQuestionsDto> lessons;
}
