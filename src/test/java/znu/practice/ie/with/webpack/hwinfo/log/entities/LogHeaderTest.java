package znu.practice.ie.with.webpack.hwinfo.log.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.LogHeaderRepository;

@SpringBootTest
public class LogHeaderTest {

  @Autowired
  LogHeaderRepository logHeaderRepo;

  @Autowired
  LogHeaderTestData logHeaderTestData;

  @Autowired
  DescMstTestData descMstTestData;

  @Autowired
  DescMstGroupTestData descMstGroupTestData;

  @Test
  @Transactional
  public void save() {
    DescMstGroup descMstGroup = descMstGroupTestData
        .makeDescMstGroup("MSI MPG X570 GAMING EDGE WIFI (MS-7C37) (Nuvoton NCT7717U/NCT7718W)");
    DescMst descMst = descMstTestData.makeDescMst("External 1 [°C]", "°C", descMstGroup);

    LogHeader logHeader = new LogHeader();
    logHeader.setCol(44);
    logHeader.setDescMst(descMst);
    this.logHeaderRepo.save(logHeader);

    Optional<LogHeader> logHeaderSel = logHeaderRepo.findByDescMstDescMstIdAndCol(logHeader.getDescMst().getDescMstId(),
        logHeader.getCol());
    assertTrue(logHeaderSel.isPresent());
    LogHeader logHeaderE = logHeaderSel.get();
    assertEquals(logHeader.getDescMst().getDescMstId(), logHeaderE.getDescMst().getDescMstId());
    assertEquals(logHeader.getCol(), logHeaderE.getCol());
  }

  @Test
  @Transactional
  public void testData() {
    DescMstGroup descMstGroup = descMstGroupTestData
        .makeDescMstGroup("MSI MPG X570 GAMING EDGE WIFI (MS-7C37) (Nuvoton NCT7717U/NCT7718W)");
    DescMst descMst = descMstTestData.makeDescMst("External 1 [°C]", "°C", descMstGroup);

    LogHeader logHeader = logHeaderTestData.makeLogHeader(44, descMst);

    Optional<LogHeader> logHeaderSel = logHeaderRepo.findByDescMstDescMstIdAndCol(logHeader.getDescMst().getDescMstId(),
        logHeader.getCol());
    assertTrue(logHeaderSel.isPresent());
    LogHeader logHeaderE = logHeaderSel.get();
    assertEquals(logHeader.getDescMst().getDescMstId(), logHeaderE.getDescMst().getDescMstId());
    assertEquals(logHeader.getCol(), logHeaderE.getCol());
  }

}
