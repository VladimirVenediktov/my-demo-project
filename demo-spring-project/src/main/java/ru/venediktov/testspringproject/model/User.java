package ru.venediktov.testspringproject.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ru.venediktov.testspringproject.serializers.ZoneDateTimeSerializer;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user", schema = "public")
public class User {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;
  private String firstName;
  private String lastName;
  private String login;
  private String password;
  private boolean isActive;
  private String role;

  /**
   * Дата входа в систему.
   */
  @JsonSerialize(using = ZoneDateTimeSerializer.class)
  private ZonedDateTime dateLogin;

}
