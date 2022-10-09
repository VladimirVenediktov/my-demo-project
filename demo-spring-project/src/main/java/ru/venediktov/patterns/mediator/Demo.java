package ru.venediktov.patterns.mediator;

public class Demo {

  public static void main(String[] args) {
    SimpleTextChat simpleTextChat = new SimpleTextChat();

    Admin admin = new Admin("ADMIN", simpleTextChat);
    SimpleUser user1 = new SimpleUser("USER_1", simpleTextChat);
    SimpleUser user2 = new SimpleUser("USER_2", simpleTextChat);

    simpleTextChat.addUser(user1);
    simpleTextChat.addUser(user2);
    simpleTextChat.setAdmin(admin);

    simpleTextChat.sendMessage("Всем привет!", user1);
    simpleTextChat.sendMessage("Не материтесь тут только!", admin);
  }

}
