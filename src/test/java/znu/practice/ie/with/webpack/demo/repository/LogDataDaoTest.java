package znu.practice.ie.with.webpack.demo.repository;

import java.util.List;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.demo.csv.CsvDataLoader;
import znu.practice.ie.with.webpack.demo.entity.LogData;

@SpringBootTest
public class LogDataDaoTest {

  @Autowired
  LogDataDao dao;

  @Autowired
  CsvDataLoader csvLoader;

  @Test
  public void save() {
    List<LogData> data = csvLoader.loadObjectList(LogData.class, "static/csv/ut-data/LogData.ut.01.csv");
    dao.saveAll(data);

    List<LogData> select = Lists.newArrayList(dao.findAll());
    for (LogData row : select) {
      System.out.println(row);
    }
  }

}