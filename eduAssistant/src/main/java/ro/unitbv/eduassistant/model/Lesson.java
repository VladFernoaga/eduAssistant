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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lesson")
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name",unique=true)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	private Teacher teacher;
	
	@OneToMany(mappedBy="lesson")
	private List<Question> questions;
	
	@OneToMany(mappedBy="lesson")
	private List<LessonSession> lessonSessions;
	
	
}
