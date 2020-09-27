package znu.practice.ie.with.webpack.hwinfo.log.entities;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.SoftInfoRepository;

@SpringBootTest
public class SoftInfoTest {

  @Autowired
  SoftInfoRepository repo;

  @Test
  public void savePtn1() {
    SoftInfo softInfo = new SoftInfo();
    softInfo.setDescription(randomAlphabetic(8));
    softInfo.setName(randomAlphabetic(4));
    SoftInfo softInfoSv = repo.save(softInfo);

    Optional<SoftInfo> sel = repo.findById(softInfoSv.getSoftInfoId());
    assertTrue(sel.isPresent());
    SoftInfo softInfoE = sel.get();
    assertEquals(softInfo.getName(), softInfoE.getName());
    assertEquals(softInfo.getDescription(), softInfoE.getDescription());

  }

}
