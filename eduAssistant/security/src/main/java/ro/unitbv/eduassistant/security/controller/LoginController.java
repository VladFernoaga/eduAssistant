package ro.unitbv.eduassistant.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.unitbv.eduassistant.config.sec.JwtTokenUtil;
import ro.unitbv.eduassistant.security.dto.LoginDto;
import ro.unitbv.eduassistant.security.dto.LoginResponseDto;
import ro.unitbv.eduassistant.security.model.JwtUser;
import ro.unitbv.eduassistant.security.service.LoginService;

@RestController
@RequestMapping(value = "/")
public class LoginController {

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private LoginService userDetailsService;

  @RequestMapping(value = "/login/", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  public ResponseEntity<?> login(@RequestBody LoginDto loginDto, Device device)
      throws AuthenticationException {
    // Perform the security
    final Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Reload password post-security so we can generate token
    final JwtUser userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());

    final String token = jwtTokenUtil.generateToken(userDetails, device);

    return ResponseEntity
        .ok(new LoginResponseDto(token, userDetails.getIdUser(), userDetails.getUsername()));
  }

  @RequestMapping(value = "/checkIsAuthenticated", produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public boolean checkIsAuthenticated() {
    return true;
  }
}


