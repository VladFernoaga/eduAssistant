package ro.unitbv.eduassistant.service.impl;

//@Service
public class StudentServiceImpl /* implements StudentService*/ {

//	 /** The Constant LOGGER. */
//	  private static final Logger LOGGER = LogManager.getLogger();
//	  
//	private StudentRepo studentRepo;
//
//	@Autowired
//	public StudentServiceImpl(StudentRepo studentRepo) {
//		this.studentRepo = studentRepo;
//	}
//
//	@Override
//	@Transactional
//	public StudentDto addNewOrGetExisting(String name) {
//		Optional<Student> student = studentRepo.findByName(name);
//		if (student.isPresent()) {
//			LOGGER.info(() -> String.format("The student with name %s is allready present",name));
//			return toStudentDto(student.get());
//		} else {
//			LOGGER.info(() -> String.format("The student with name %s is not present",name));
//			Student stud = new Student();
//			stud.setName(name);
//			try {
//				studentRepo.save(stud);
//				return toStudentDto(stud);
//			} catch (DataIntegrityViolationException ex) {
//				throw new IllegalArgumentException(
//						String.format("The name %s allreay exist in this seesion please try with other name.", name));
//			}
//
//		}
//	}
//
//	private StudentDto toStudentDto(Student student) {
//		return new StudentDto(student.getName());
//	}

}
