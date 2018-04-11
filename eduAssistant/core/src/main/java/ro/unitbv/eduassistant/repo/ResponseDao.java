package ro.unitbv.eduassistant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.unitbv.eduassistant.model.Response;

public interface ResponseDao extends JpaRepository<Response, Long>{

}
