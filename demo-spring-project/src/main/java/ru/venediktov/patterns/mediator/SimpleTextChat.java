package ru.venediktov.patterns.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Посредник: инкапсуляция способа взаимодействия множества объектов.
 */
public class SimpleTextChat implements Chat{

  private Admin admin;
  private List<User> users = new ArrayList<>();

  public void setAdmin(Admin admin) {
    this.admin = admin;
  }

  public void addUser(User user) {
    this.users.add(user);
  }

  @Override
  public void sendMessage(String message, User user) {
    users.stream()
        .filter(u -> !u.equals(user))
        .forEach(u -> u.getMessage(message));
    admin.getMessage(message);
  }
}
