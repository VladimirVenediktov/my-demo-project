package ru.venediktov.patterns.command;

/**
 * Инкапсуляция всей информации, необходимой для выполнения действия
 * или вызова события в более позднее время.
 */
public class Demo {

  public static void main(String[] args) {
    Light light = new Light();
    Switch sw = new Switch(new TurnOnLightCommand(light), new TurnOffLightCommand(light));
    sw.on();
    sw.off();
  }

}
