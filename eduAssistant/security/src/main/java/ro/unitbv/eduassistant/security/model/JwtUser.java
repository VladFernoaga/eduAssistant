package ro.unitbv.eduassistant.security.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {
  private static final long serialVersionUID = 1L;

  private final String username;
  private final String password;
  private final long id;

  public JwtUser(String username, String password, long id) {
    super();
    this.username = username;
    this.password = password;
    this.id = id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public long getIdUser() {
    return id;
  }
}
