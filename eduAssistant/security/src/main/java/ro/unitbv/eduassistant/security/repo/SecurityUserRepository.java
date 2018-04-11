package ro.unitbv.eduassistant.security.repo;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import ro.unitbv.eduassistant.security.model.SecurityUser;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {

  Optional<SecurityUser> findByUsername(String username);

}
