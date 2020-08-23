package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import znu.practice.ie.with.webpack.hwinfo.log.entities.HwinfoLog;
import znu.practice.ie.with.webpack.hwinfo.log.entities.SoftInfo;

@SpringBootTest
public class SoftInfoRepositoryTest {

  @Autowired
  SoftInfoRepository repo;

  @Autowired
  HwinfoLogRepository hlRepo;

  @Test
  @Transactional
  public void save() {
    SoftInfo si = new SoftInfo();
    si.setName("test soft");
    si.setDescription("test data");

    SoftInfo entity = repo.save(si);

    Optional<SoftInfo> sel = repo.findById(entity.getId());

    assertTrue(sel.isPresent());
    assertEquals(si.getName(), sel.get().getName());
    assertEquals(si.getDescription(), sel.get().getDescription());

  }

  @Test
  @Transactional
  public void findById() {

    SoftInfo si = repo.findById(2L).get();
    System.out.println("----------------");
    System.out.println("--- size : " + si.getHwinfoLog().size());
    System.out.println("----------------");
    si.getHwinfoLog().forEach(r -> System.out.println(r));
    System.out.println("----------------");

  }

  @Test
  public void findBySi() {

    List<HwinfoLog> sel = hlRepo.findBySoftInfoId(2L);
    System.out.println("----------------------");
    System.out.println(sel.size());
    System.out.println("----------------------");

  }

}