package ru.venediktov.patterns.mediator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SimpleUser implements User{

  private String name;
  private Chat chat;

  @Override
  public void sendMessage(String message) {
    chat.sendMessage(message, this);
  }

  @Override
  public void getMessage(String message) {
    System.out.println(getName() + " received message: " + message);
  }

}
