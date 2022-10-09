package ru.venediktov.testspringproject.controller;

import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.venediktov.testspringproject.model.Developer;
import ru.venediktov.testspringproject.model.DeveloperBuilder;
import ru.venediktov.testspringproject.repository.CommonRepository;
import ru.venediktov.testspringproject.validation.ToDoValidationError;
import ru.venediktov.testspringproject.validation.ToDoValidationErrorBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DeveloperJDBCController {

  private final CommonRepository<Developer> developerCommonRepository;

  // curl -s http://localhost:8888/api/developer | jq
  @GetMapping("/developer")
  public ResponseEntity<Iterable<Developer>> getToDos() {
    return ResponseEntity.ok(developerCommonRepository.findAll());
  }

  @GetMapping("/developer/{id}")
  public ResponseEntity<Developer> getToDoById(@PathVariable String id) {
    return ResponseEntity.ok(developerCommonRepository.findById(id));
  }

  @PatchMapping("/developer/{id}")
  public ResponseEntity<Developer> setCompleted(@PathVariable String id) {
    Developer result = developerCommonRepository.findById(id);
    result.setActive(true);
    developerCommonRepository.save(result);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .buildAndExpand(result.getId()).toUri();

    return ResponseEntity.ok().header("Location",location.toString()).build();
  }

  // curl -i -X POST -H "Content-type: application/json" -d '{"name":"Alex"}' http://localhost:8888/api/developer
  @RequestMapping(value="/developer", method = {RequestMethod.POST,RequestMethod.PUT})
  public ResponseEntity<?> createToDo(@Valid @RequestBody Developer developer, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
    }

    Developer result = developerCommonRepository.save(developer);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(result.getId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/developer/{id}")
  public ResponseEntity<Developer> deleteToDo(@PathVariable String id) {
    developerCommonRepository.delete(DeveloperBuilder.create().withId(id).build());
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/developer")
  public ResponseEntity<Developer> deleteToDo(@RequestBody Developer developer) {
    developerCommonRepository.delete(developer);
    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ToDoValidationError handleException(Exception exception) {
    return new ToDoValidationError(exception.getMessage());
  }

}
