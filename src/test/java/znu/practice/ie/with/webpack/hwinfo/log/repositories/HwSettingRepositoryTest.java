package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.entities.HwSetting;

@SpringBootTest
public class HwSettingRepositoryTest {

  @Autowired
  HwSettingRepository repo;

  @Test
  public void save() {
    HwSetting hs = new HwSetting();
    hs.setName("test setting");
    hs.setDescription("test hw setting");
    hs.setRegistedDate(LocalDateTime.now());

    HwSetting entity = repo.save(hs);

    Optional<HwSetting> sel = repo.findById(entity.getId());

    assertTrue(sel.isPresent());
    assertEquals(hs.getName(), sel.get().getName());
    assertEquals(hs.getDescription(), sel.get().getDescription());
  }

}