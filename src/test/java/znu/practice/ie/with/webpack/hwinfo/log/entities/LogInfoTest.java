package znu.practice.ie.with.webpack.hwinfo.log.entities;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.HwinfoLogRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.LogInfoRepository;

@SpringBootTest
public class LogInfoTest {

  @Autowired
  LogInfoRepository repo;

  @Autowired
  HwinfoLogRepository hwinfoLogRepo;

  @Test
  @Transactional
  public void savePtn1() {
    HwinfoLog hwinfoLog = new HwinfoLog();
    hwinfoLog.setDescription(randomAlphabetic(8));
    HwinfoLog hwinfoLogSv = hwinfoLogRepo.save(hwinfoLog);

    LogInfo logInfo = new LogInfo();
    logInfo.setFileName(randomAlphabetic(9));
    logInfo.setHwinfoLog(hwinfoLogSv);

    LogInfo logInfoSv = repo.save(logInfo);
    Optional<LogInfo> sel = repo.findById(logInfoSv.getLogInfoId());
    assertTrue(sel.isPresent());
    LogInfo logInfoE = sel.get();
    assertEquals(logInfo.getFileName(), logInfoE.getFileName());

  }

}
