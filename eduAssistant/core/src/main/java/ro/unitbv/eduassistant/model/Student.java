package ro.unitbv.eduassistant.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
	@SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 10)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name",unique=false)
	private String name;
	
	@Column(name = "chatbotId",unique=true)
	private Long chatbotId;
	
	@OneToMany(mappedBy="student")
	private List<Registration> registrations;
}
