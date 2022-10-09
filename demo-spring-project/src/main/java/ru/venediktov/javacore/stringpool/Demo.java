package ru.venediktov.javacore.stringpool;

/**
 * В силу неизменности класса String, в результате каждой операции со строкой
 * создаются новые экземпляры строк, а старые отбрасываются, порождая большое количество мусора.
 * Чтобы справиться с созданием временного мусора из-за модификаций объекта String,
 * можно использовать изменяемый класс StringBuffer.
 * Значение строки будет хранится в стеке,
 * StringBuffer - потокобезопасный, StringBuilder в Java работает быстрее StringBuffer’а,
 * благодаря несинхронизированности методов.
 */
public class Demo {

  public static void main(String[] args) {
    String s1 = "java";
    String s2 = "java";

    /* true потому что строки хранятся в String pool'е, и s1 и s2 указывают на одну и ту же строку из пула */
    System.out.println(s1 == s1);
    System.out.println(s1.equals(s2));
    /* при таком создании принудительно выделится отдельный участок памяти в куче, поэтому "==" выдаст нам false */
    String s3 = new String("java");
    System.out.println(s3 == s1);
    /* true - у строк equals переопределен и сравнивает последовательность символов в строке */
    System.out.println(s3.equals(s1));

    /* метод intern() у String - получит строку из пула строк, если такая там есть,
        в противном случае добавит в пул и вернет ее */
    String a = "INTERN";
    String b = new String("INTERN").intern();
    System.out.println(a == b);
  }

}
