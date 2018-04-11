package ro.unitbv.eduassistant.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ro.unitbv.eduassistant.model.Registration;

public interface RegistrationRepo extends JpaRepository<Registration, Long>{
	
	Optional<Registration> findById(Long id);
	
	@Query("Select r from Registration r where r.student.chatbotId = :chatId order by r.id")
	Optional<List<Registration>> getOrderedRegistrations(@Param("chatId") Long chatId);

}