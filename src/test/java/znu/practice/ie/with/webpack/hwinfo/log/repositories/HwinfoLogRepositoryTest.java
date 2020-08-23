package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.entities.HwSetting;
import znu.practice.ie.with.webpack.hwinfo.log.entities.HwinfoLog;
import znu.practice.ie.with.webpack.hwinfo.log.entities.SoftInfo;

@SpringBootTest
public class HwinfoLogRepositoryTest {

  @Autowired
  HwinfoLogRepository repo;

  @Autowired
  SoftInfoRepository siRepo;

  @Autowired
  HwSettingRepository hsRepo;

  @Test
  public void saveCascade() {
    HwinfoLog hwinfoLog = new HwinfoLog();
    LocalDateTime now = LocalDateTime.now();
    SoftInfo si = new SoftInfo();
    si.setName("test soft info");
    si.setDescription("hwinfoLog test - saveCascade");
    SoftInfo siEntity = siRepo.save(si);

    hwinfoLog.setSoftInfo(siEntity);

    HwSetting hs = new HwSetting();
    hs.setName("test hw setting");
    hs.setDescription("hwinfoLog test - saveCascade");
    hs.setRegistedDate(now);
    HwSetting hsEntity = hsRepo.save(hs);
    hwinfoLog.setHwSetting(hsEntity);

    hwinfoLog.setName("test log");
    hwinfoLog.setDescription("test hwinfo log");
    hwinfoLog.setRegistedDate(now);
    hwinfoLog.setUpdatedDate(now);

    HwinfoLog entity = repo.save(hwinfoLog);

    Optional<HwinfoLog> sel = repo.findById(entity.getId());

    assertTrue(sel.isPresent());
    assertEquals(hwinfoLog.getName(), sel.get().getName());
    assertEquals(hwinfoLog.getDescription(), sel.get().getDescription());

  }

}