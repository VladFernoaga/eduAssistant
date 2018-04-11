package ro.unitbv.eduassistant.security.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ro.unitbv.eduassistant.security.model.JwtUser;
import ro.unitbv.eduassistant.security.model.JwtUserFactory;
import ro.unitbv.eduassistant.security.model.SecurityUser;
import ro.unitbv.eduassistant.security.repo.SecurityUserRepository;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  private SecurityUserRepository userRepo;

  @Override
  public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<SecurityUser> user = userRepo.findByUsername(username);

    if (!user.isPresent()) {
      throw new UsernameNotFoundException(
          String.format("No user found with the username '%s'.", username));
    } else {
      return JwtUserFactory.create(user.get().getUsername(), user.get().getPassword(),
          user.get().getId());
    }
  }
}
