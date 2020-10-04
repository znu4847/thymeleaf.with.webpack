package jdk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class StringPatternTest {

  @Test
  public void bracket() {
    String header = "Virtual Memory Available [MB]";
    Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(header);
    if (m.find()) {

      System.out.println(m.group(1));
      System.out.println(header);

      String type = header.substring(0, header.indexOf("[") - 1);
      System.out.println(type);
    }

  }

  @Test
  public void noBracket() {
    String header = "Date";
    Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(header);
    if (m.find()) {
      System.out.println(m.group(1));
      System.out.println(header);

      String type = header.substring(0, header.indexOf("[") - 1);
      System.out.println(type);

    }

  }

}
