package ro.unitbv.eduassistant.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ro.unitbv.eduassistant.security.model.JwtUser;

public interface LoginService extends UserDetailsService {

  JwtUser loadUserByUsername(String username) throws UsernameNotFoundException;
}
