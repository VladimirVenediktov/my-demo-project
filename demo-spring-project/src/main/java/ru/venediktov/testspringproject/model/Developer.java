package ru.venediktov.testspringproject.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Developer {

  @NotNull
  private String id;
  @NotNull
  @NotBlank
  private String name;
  private LocalDateTime created;
  private LocalDateTime modified;
  private boolean active;

  public Developer() {
    LocalDateTime date = LocalDateTime.now();
    this.id = UUID.randomUUID().toString();
    this.created = date;
    this.modified = date;
  }

  public Developer(String description) {
    this();
    this.name = description;
  }

}