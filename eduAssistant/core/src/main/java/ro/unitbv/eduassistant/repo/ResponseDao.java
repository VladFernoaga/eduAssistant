package ro.unitbv.eduassistant.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ro.unitbv.eduassistant.model.Response;

public interface ResponseDao extends JpaRepository<Response, Long>{

	@Query("Select r from Response r where r.question.id = :questionId and r.registration.lessonSession.sessionKey = :sessionId ")
	List<Response> findResponsesForQuestionIdAndSessionId(@Param("questionId") long questionId, @Param("sessionId") String sessionId);
	

}
