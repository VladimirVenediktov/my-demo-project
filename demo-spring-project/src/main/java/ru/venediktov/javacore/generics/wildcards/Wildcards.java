package ru.venediktov.javacore.generics.wildcards;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Инвариантность — это отсутствие наследования между производными типами
 * Generics как раз такие
 *
 * UpperBoundedWildcard <? extends T> ковариантен
 *
 * LowerBoundedWildcard <? super T> контрвариантен
 */
public class Wildcards {

  public static void closeDocuments(Collection<? super ManualIncomeDocument> documents) {

    documents.forEach(document -> {
      Document doc = (Document) document;
      doc.setCloseDate(ZonedDateTime.now());
      System.out.println(document.toString());
    });
  }

  public static void main(String[] args) {

    Collection<Document> documents = new ArrayList<>();
    documents.add(new Document("1"));
    documents.add(new Document("2"));
    closeDocuments(documents);

    // а если список документов наследников, то нужно уже <? extends Document>
    Collection<IncomeDocument> incomeDocuments = new ArrayList<>();
    incomeDocuments.add(new IncomeDocument("3"));
    incomeDocuments.add(new IncomeDocument("4"));
    closeDocuments(incomeDocuments);

    // а если хотим проставлять дату зарытия только для созданных вручную документов,
    // то <? super ManualIncomeDocument>
    Collection<ManualIncomeDocument> manualIncomeDocumentCollections = new ArrayList<>();
    manualIncomeDocumentCollections.add(new ManualIncomeDocument("5"));
    manualIncomeDocumentCollections.add(new ManualIncomeDocument("6"));
    closeDocuments(manualIncomeDocumentCollections);

    Collection<AutoIncomeDocument> autoIncomeDocuments = new ArrayList<>();
    autoIncomeDocuments.add(new AutoIncomeDocument("7"));
    // ошибка компиляции
    //closeDocuments(autoIncomeDocuments);
  }

}
