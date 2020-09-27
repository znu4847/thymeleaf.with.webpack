package znu.practice.ie.with.webpack.hwinfo.log.entities;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.HwSettingRepository;

@SpringBootTest
public class HwSettingTest {

  @Autowired
  HwSettingRepository repo;

  @Test
  public void savePtn1() {
    HwSetting hwSetting = new HwSetting();
    hwSetting.setDescription(randomAlphabetic(6));
    hwSetting.setName(randomAlphabetic(7));

    HwSetting hwSettingSv = repo.save(hwSetting);
    Optional<HwSetting> sel = repo.findById(hwSettingSv.getHwSettingId());
    assertTrue(sel.isPresent());
    HwSetting hwSettingE = sel.get();
    assertEquals(hwSetting.getDescription(), hwSettingE.getDescription());
    assertEquals(hwSetting.getName(), hwSettingE.getName());
    assertNotNull(hwSettingE.getRegistedDate());

  }

}
