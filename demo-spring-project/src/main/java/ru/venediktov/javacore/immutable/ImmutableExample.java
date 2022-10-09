package ru.venediktov.javacore.immutable;

import java.util.HashMap;
import java.util.Map;

public final class ImmutableExample {

  private final String field;
  private final Map<String, String> fieldMap;

  public ImmutableExample(String field, Map<String, String> fieldMap) {
    this.field = field;
    Map<String, String> deepCopy = new HashMap<>();

    for (String key : fieldMap.keySet()) {
      deepCopy.put(key, fieldMap.get(key));
    }
    this.fieldMap = deepCopy;
  }
  public String getField() {
    return field;
  }

  public Map<String, String> getFieldMap() {
    Map<String, String> deepCopy = new HashMap<>();

    for (String key : fieldMap.keySet()) {
      deepCopy.put(key, fieldMap.get(key));
    }
    return deepCopy;
  }

  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>();
    map.put("1", "firstValue");

    ImmutableExample immutableExample = new ImmutableExample("testField", map);
    immutableExample.getFieldMap().put("2", "secondValue");
    System.out.println(immutableExample.getFieldMap());

    map.put("3", "thirdValue");
    System.out.println(immutableExample.getFieldMap());
  }

}
