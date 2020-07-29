package znu.practice.ie.with.webpack.demo.repository;

import java.util.List;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.demo.csv.CsvDataLoader;
import znu.practice.ie.with.webpack.demo.entity.LogInfo;

@SpringBootTest
public class LogInfoDaoTest {

  @Autowired
  LogInfoDao dao;

  @Autowired
  CsvDataLoader csvLoader;

  @Test
  public void save() {
    List<LogInfo> data = csvLoader.loadObjectList(LogInfo.class, "static/csv/ut-data/LogInfo.ut.01.csv");
    dao.saveAll(data);

    List<LogInfo> select = Lists.newArrayList(dao.findAll());
    for (LogInfo row : select) {
      System.out.println(row);
    }
  }

}