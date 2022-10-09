package ru.venediktov.testspringproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.venediktov.testspringproject.model.User;
import ru.venediktov.testspringproject.repository.UserDataJPARepository;

/**
 * По видео-уроку https://www.youtube.com/watch?v=7uxROJ1nduk&t=2955s
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {

  private final UserDataJPARepository userDataJPARepository;

  @GetMapping
  public Iterable<User> getAll() {
    return userDataJPARepository.findAll();
  }

  @GetMapping("/{id}")
  public User getById(@PathVariable String id) {
    return userDataJPARepository.findUserById(id).orElse(null);
  }
}
