package ro.unitbv.eduassistant.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ro.unitbv.eduassistant.util.ParserUtil;

@Getter
@Setter
@Entity
@Table(name = "question")
@EqualsAndHashCode(callSuper=false, of="id")
public class Question extends JsonbValue {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "question")
	private String question;

	@ManyToOne
	private Lesson lesson;
	
	@OneToMany(mappedBy="question")
	private List<Response> responses;
	

	public void setMultipeChoiceQuestion(MultipleChoiceQuestion quest) {
		try {
			this.jsonbColum = (JSONObject) ParserUtil.getJSONParser().parse(ParserUtil.getGson().toJson(quest));
		} catch (ParseException e) {
			LOGGER.error(() -> "The MultipleChoiceQuestion Object could not be parsed", e);
		}
	}

	public MultipleChoiceQuestion getMultipleChoiceQuestion() {
		return ParserUtil.getGson().fromJson(this.jsonbColum.toJSONString(), MultipleChoiceQuestion.class);
	}
	
	
}
