package ru.venediktov.patterns.command;

/**
 * Invoker
 */
public class Switch {
  private Command switchOn;
  private Command swithOff;

  public Switch(Command switchOn, Command swithOff) {
    this.switchOn = switchOn;
    this.swithOff = swithOff;
  }

  public void on() {
    switchOn.execute();
  }

  public void off() {
    swithOff.execute();
  }

}
