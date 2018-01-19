package ro.unitbv.eduassistant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.unitbv.eduassistant.model.LessonSession;

public interface LessonSessionRepo  extends JpaRepository<LessonSession, Long>{

}
