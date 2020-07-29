package znu.practice.ie.with.webpack.demo.repository;

import java.util.List;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.demo.csv.CsvDataLoader;
import znu.practice.ie.with.webpack.demo.entity.LogList;

@SpringBootTest
public class LogListDaoTest {

  @Autowired
  LogListDao dao;

  @Autowired
  CsvDataLoader csvLoader;

  @Test
  public void save() {
    long cnt = dao.deleteByLogNameStartsWith("ut_data");
    System.out.println("del cnt : " + cnt);
    List<LogList> data = csvLoader.loadObjectList(LogList.class, "static/csv/ut-data/LogList.ut.02.csv");
    dao.saveAll(data);

    List<LogList> select = Lists.newArrayList(dao.findAll());
    for (LogList row : select) {
      System.out.println(row);
    }

    long aftCnt = dao.deleteByLogNameStartsWith("ut_data");
    System.out.println("del cnt : " + aftCnt);
  }

}