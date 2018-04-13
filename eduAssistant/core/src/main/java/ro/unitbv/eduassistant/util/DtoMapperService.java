package ro.unitbv.eduassistant.util;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ro.unitbv.eduassistant.dto.LessonWithQuestionsDto;
import ro.unitbv.eduassistant.dto.QuestionDto;
import ro.unitbv.eduassistant.dto.VariantValueDto;
import ro.unitbv.eduassistant.model.Lesson;
import ro.unitbv.eduassistant.model.Question;
import ro.unitbv.eduassistant.model.VariantValue;

@Service
public class DtoMapperService {

	public LessonWithQuestionsDto mapTo(Lesson lesson) {
		LessonWithQuestionsDto lessonDto = new LessonWithQuestionsDto();
		lessonDto.setId(lesson.getId());
		lessonDto.setDescription(lesson.getDescription());
		lessonDto.setName(lesson.getName());
		lessonDto.setQuestions(lesson.getQuestions().parallelStream().map(this::mapTo).collect(Collectors.toList()));
		return lessonDto;
	}
	
	public QuestionDto mapTo(Question question) {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(question.getId());
		questionDto.setQuestion(question.getQuestion());
		questionDto.setVariants(question.getMultipleChoiceQuestion().getVariants().parallelStream().map(this::mapTo)
				.collect(Collectors.toList()));
		return questionDto;
	}
	
	public VariantValueDto mapTo(VariantValue variant) {
		VariantValueDto variantDto = new VariantValueDto();
		variantDto.setValue(variant.getValue());
		variantDto.setHint(variant.getHint());
		return variantDto;
	}
}
