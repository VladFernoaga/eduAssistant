package ro.unitbv.eduassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.unitbv.eduassistant.dto.TeacherSingupDto;
import ro.unitbv.eduassistant.service.TeacherService;

@RestController
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@RequestMapping(value = "/teacher", method = RequestMethod.POST)
	public ResponseEntity<Void> getStudent(@RequestBody TeacherSingupDto data) {
		teacherService.singUp(data.getName());
		return ResponseEntity.ok().build();
	}

}
