package ru.venediktov.testspringproject.model;

public class DeveloperBuilder {

  private static DeveloperBuilder instance = new DeveloperBuilder();
  private String id = null;
  private String name = "";

  private DeveloperBuilder() {
  }

  public static DeveloperBuilder create() {
    return instance;
  }

  public DeveloperBuilder withName(String name) {
    this.name = name;
    return instance;
  }

  public DeveloperBuilder withId(String id) {
    this.id = id;
    return instance;
  }

  public Developer build() {
    Developer result = new Developer(this.name);
    if (id != null) {
      result.setId(id);
    }
    return result;
  }

}
