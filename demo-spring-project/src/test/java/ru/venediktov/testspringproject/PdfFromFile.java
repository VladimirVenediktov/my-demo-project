package ru.venediktov.testspringproject;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PdfFromFile {

  @SneakyThrows
  @Test
  public void pdfBytes() {
    File file = new File("src/test/resources/functionaltests/testfiles/test-pdf.pdf");
    FileInputStream fis = new FileInputStream(file);

    ArrayList<Object> objects = new ArrayList<>();
    int i;
    while ((i = fis.read()) != -1) {
      objects.add(i);
    }
    Object[] data = objects.toArray();
    Assertions.assertNotNull(data);

    // 2-й
    byte[] bytes = Files.readAllBytes(file.toPath());
    Assertions.assertNotNull(bytes);
  }

}
