package ru.venediktov.testspringproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.venediktov.testspringproject.model.User;
import ru.venediktov.testspringproject.repository.UserDataJPARepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserDataJPARepository userDataJPARepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userDataJPARepository.findUserByLogin(userName).orElse(null);
    if (user == null) {
      throw new UsernameNotFoundException("Unknown user: " + userName);
    }
    UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
        .username(user.getLogin())
        .password(user.getPassword())
        .roles(user.getRole())
        .build();

    return userDetails;
  }
}
