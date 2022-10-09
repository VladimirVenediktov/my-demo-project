package ru.venediktov.testspringproject.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.venediktov.testspringproject.model.ToDo;

public interface ToDoDataJPARepository extends CrudRepository<ToDo, String> {

  Optional<ToDo> findByDescription(String description);

}
