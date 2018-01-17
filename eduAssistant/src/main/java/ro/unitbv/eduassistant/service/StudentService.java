package ro.unitbv.eduassistant.service;

import ro.unitbv.eduassistant.dto.StudentDto;

public interface StudentService {

	StudentDto addNewOrGetExisting(String name);
}
