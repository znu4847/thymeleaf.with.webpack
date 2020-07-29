package znu.practice.ie.with.webpack.demo.csv;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.demo.entity.LogList;

@SpringBootTest
public class CsvLoaderTest {

  @Test
  public void ldConstruct() {
  }

  @Test
  public void test() {
    CsvDataLoader loader = new CsvDataLoader();

    List<LogList> list = loader.loadObjectList(LogList.class, "static/csv/ut-data/LogList.ut.02.csv");

    System.out.println(list.size());

  }

}