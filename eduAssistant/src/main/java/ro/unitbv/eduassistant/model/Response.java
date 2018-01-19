package ro.unitbv.eduassistant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import lombok.Getter;
import lombok.Setter;
import ro.unitbv.eduassistant.util.ParserUtil;

@Getter
@Setter
@Entity
@Table(name = "response")
public class Response extends JsonbValue {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	private Registration registration;
	
	@ManyToOne 
	private Question question;
	
	public void setMultipeChoiceQuestion(MultipleChoiceResponse response) {
		try {
			this.jsonbColum = (JSONObject) ParserUtil.getJSONParser().parse(ParserUtil.getGson().toJson(response));
		} catch (ParseException e) {
			LOGGER.error(() -> "The MultipleChoiceResponse Object could not be parsed", e);
		}
	}

	public MultipleChoiceResponse getMultipleChoiceQuestion() {
		return ParserUtil.getGson().fromJson(this.jsonbColum.toJSONString(), MultipleChoiceResponse.class);
	}
}
