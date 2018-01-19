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
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "registration",uniqueConstraints=@UniqueConstraint(columnNames={"lessonsession_id","student_id"}))
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private LessonSession lessonSession;
	
	@OneToMany(mappedBy="registration")
	private List<Response> responses;
}
