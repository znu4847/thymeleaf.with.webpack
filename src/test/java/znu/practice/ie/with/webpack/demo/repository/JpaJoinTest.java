package znu.practice.ie.with.webpack.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.demo.entity.LogList;
import znu.practice.ie.with.webpack.demo.entity.model.LogInformation;

@SpringBootTest
public class JpaJoinTest {

  @Autowired
  EntityManager em;

  @Test
  public void planeSelect() {

    List<LogList> select = em.createQuery("select li from LogList li", LogList.class).getResultList();
    for (LogList row : select) {
      System.out.println(row.getLogInfo() == null);
      System.out.println(row);
    }

  }

  // @Test
  // public void customEntity() {
  // List<LogInformation> select = em.createQuery("select li from LogList li",
  // LogInformation.class).getResultList();
  // for (LogInformation row : select) {
  // System.out.println(row);
  // }
  // }

  // @Test
  // public void implicitInnerJoin() {
  // List<LogList> select = em.createQuery("select ld.logList from LogData ld",
  // LogList.class).getResultList();
  // for (LogList row : select) {
  // System.out.println(row.getLogInfo() == null);
  // System.out.println(row);
  // }

  // }

}