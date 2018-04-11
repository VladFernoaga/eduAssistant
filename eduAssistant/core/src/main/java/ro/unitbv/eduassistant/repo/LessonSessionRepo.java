package ro.unitbv.eduassistant.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.unitbv.eduassistant.model.LessonSession;

public interface LessonSessionRepo  extends JpaRepository<LessonSession, Long>{

	Optional<LessonSession> findBySessionKey(String sessionKey); 
}
