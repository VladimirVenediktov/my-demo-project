package ru.venediktov.javacore.generics.wildcards;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Document {

  private String id;

  /**
   * Дата закрытия
   */
  private ZonedDateTime closeDate;

  public Document(String id) {
    this.id = id;
  }
}
