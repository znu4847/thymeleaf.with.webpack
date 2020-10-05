package jdk;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ListTest {

  @Test
  public void removePtn1() {

    List<String> list = new ArrayList<>();
    list.add("a1");
    list.add("a2");
    list.add("a3");
    list.add("a4");
    list.add("a5");
    list.add("a6");
    list.add("a7");

    list.remove(0);
    String a6 = list.remove(list.size() - 2);
    String a7 = list.remove(list.size() - 1);
    System.out.println(a6 + ", " + a7);

    list.stream().forEach(System.out::println);

  }

}
