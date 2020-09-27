package znu.practice.ie.with.webpack.hwinfo.log.entities;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.HwinfoLogRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.LogDataRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.LogInfoRepository;

@SpringBootTest
public class LogDataTest {

  @Autowired
  LogDataRepository dataRepo;

  @Autowired
  LogInfoRepository logInfoRepo;

  @Autowired
  HwinfoLogRepository hwinfoLogRepo;

  @Autowired
  DescMstRepository descMstRepo;

  @Test
  @Transactional
  public void savePtn1() {
    HwinfoLog hwinfoLog = new HwinfoLog();
    hwinfoLog.setDescription("logData save test");
    hwinfoLog.setName("0920-doom");

    LogInfo logInfo = new LogInfo();
    logInfo.setFileName("0920-doom.csv");
    // OneToOne
    hwinfoLog.setLogInfo(logInfo);
    // OneToOne
    logInfo.setHwinfoLog(hwinfoLog);

    // save LogInfo
    HwinfoLog hwinfoLogSv = hwinfoLogRepo.save(hwinfoLog);
    assertNotNull(hwinfoLogSv.getLogInfo().getLogInfoId());

    // create and save DescMst
    DescMst descMst = new DescMst();
    descMst.setDataType("%");
    descMst.setName("Core 0 T0 Usage");
    descMst.setUnit("%");
    DescMst descMstSv = descMstRepo.save(descMst);

    // create LogData
    LogData logData = new LogData();
    logData.setValue("19.4");
    logData.setLogInfo(hwinfoLogSv.getLogInfo());
    logData.setDescMst(descMstSv);

    LogDataId id = logData.getId();
    id.setRowNo(1);

    // save LogData
    LogData logDataSv = dataRepo.save(logData);
    Optional<LogData> logDataSel = dataRepo.findById(logDataSv.getId());

    assertTrue(logDataSel.isPresent());
    LogData logDataE = logDataSel.get();
    assertEquals(logData.getValue(), logDataE.getValue());
    assertEquals(logData.getDescMst().getDescMstId(), logDataE.getId().getDescMstId());
    assertEquals(logData.getLogInfo().getLogInfoId(), logDataE.getId().getLogInfoId());

  }

  @Test
  @Transactional
  public void savePtn2() {
    // create and save DescMst
    // desc1
    DescMst descMst1 = new DescMst();
    descMst1.setName("Core 0 T0 Effective Clock");
    descMst1.setUnit("MHz");
    DescMst descMst1Sv = descMstRepo.save(descMst1);

    // desc2
    DescMst descMst2 = new DescMst();
    descMst2.setName("Core 0 T1 Effective Clock");
    descMst2.setUnit("MHz");
    DescMst descMst2Sv = descMstRepo.save(descMst2);

    // row1, desc1
    LogData logData1_1 = new LogData();
    // row1, desc2
    LogData logData1_2 = new LogData();
    // row2, desc1
    LogData logData2_1 = new LogData();
    // row2, desc2
    LogData logData2_2 = new LogData();
    // value
    logData1_1.setValue("216.5");
    logData1_2.setValue("7.1");
    logData2_1.setValue("3442.9");
    logData2_2.setValue("2205.1");

    // parent1
    // ManyToOne
    logData1_1.setDescMst(descMst1Sv);
    logData1_2.setDescMst(descMst2Sv);
    logData2_1.setDescMst(descMst1Sv);
    logData2_2.setDescMst(descMst2Sv);

    // create LogInfo
    LogInfo logInfo = new LogInfo();
    logInfo.setFileName(randomAlphabetic(7));
    // parent2
    // ManyToOne
    logData1_1.setLogInfo(logInfo);
    logData1_2.setLogInfo(logInfo);
    logData2_1.setLogInfo(logInfo);
    logData2_2.setLogInfo(logInfo);
    // OneToMany
    logInfo.addLogData(logData1_1);
    logInfo.addLogData(logData1_2);
    logInfo.addLogData(logData2_1);
    logInfo.addLogData(logData2_2);
    // rowNo
    logData1_1.getId().setRowNo(1);
    logData1_2.getId().setRowNo(1);
    logData2_1.getId().setRowNo(2);
    logData2_2.getId().setRowNo(2);

    LogInfo logInfoSv = this.logInfoRepo.save(logInfo);
    Optional<LogInfo> sel = this.logInfoRepo.findById(logInfoSv.getLogInfoId());
    assertTrue(sel.isPresent());
    LogInfo logInfoE = sel.get();
    List<LogData> logDataListE = logInfoE.getLogDataList();
    for (LogData d : logDataListE) {
      LogDataId id = d.getId();
      assertNotNull(id.getDescMstId());
      assertNotNull(id.getLogInfoId());
      if (id.getRowNo() == 1 && id.getDescMstId() == descMst1Sv.getDescMstId()) {
        assertEquals(logData1_1.getValue(), d.getValue());
      } else if (id.getRowNo() == 1 && id.getDescMstId() == descMst2Sv.getDescMstId()) {
        assertEquals(logData1_2.getValue(), d.getValue());
      } else if (id.getRowNo() == 2 && id.getDescMstId() == descMst1Sv.getDescMstId()) {
        assertEquals(logData2_1.getValue(), d.getValue());
      } else if (id.getRowNo() == 2 && id.getDescMstId() == descMst2Sv.getDescMstId()) {
        assertEquals(logData2_2.getValue(), d.getValue());
      }
    }
  }

}