package ro.unitbv.eduassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.unitbv.eduassistant.dto.StudentDto;
import ro.unitbv.eduassistant.service.StudentService;

@RestController
public class StudentController {
	
	private StudentService studentService;

	@Autowired
	public StudentController(StudentService teacherService) {
		this.studentService = teacherService;
	}

	  @RequestMapping(value = "/student/{name}", produces = MediaType.APPLICATION_JSON_VALUE,
		      method = RequestMethod.GET)
	public ResponseEntity<StudentDto> getStudent(@RequestParam("name") String studentName){
		return ResponseEntity.ok().body(studentService.addNewOrGetExisting(studentName));
	}
	  
}
