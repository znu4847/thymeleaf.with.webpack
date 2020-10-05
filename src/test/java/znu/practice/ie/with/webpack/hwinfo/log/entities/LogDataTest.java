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

import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstGroupRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.HwinfoLogRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.LogDataRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.DataAttributeRepository;
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

  @Autowired
  DescMstGroupRepository descMstGroupRepo;

  @Autowired
  DataAttributeRepository dataAttributeRepo;

  @Autowired
  DescMstTestData descMstTestData;

  @Autowired
  DataAttributeTestData dataAttributeTestData;

  /**
   * save with parents
   */
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
    hwinfoLogRepo.save(hwinfoLog);
    assertNotNull(hwinfoLog.getLogInfo().getLogInfoId());

    // select or create DescMstGroup
    DescMstGroup descMstGroup = makeDescMstGroup("CPU [#0]: AMD Ryzen 7 3800X");
    // select or create DescMstGroup
    DescMst descMst = descMstTestData.makeDescMst("Core 0 T0 Usage", "%", descMstGroup);
    // select or create DataAttribute
    DataAttribute dataAttribute = dataAttributeTestData.makeDataAttribute(44, descMst);

    // create LogData
    LogData logData = new LogData();
    logData.setValue("19.4");
    logData.setLogInfo(hwinfoLog.getLogInfo());
    logData.setDataAttribute(dataAttribute);

    LogDataId id = logData.getId();
    id.setRowNo(1);

    // save LogData
    dataRepo.save(logData);
    Optional<LogData> logDataSel = dataRepo.findById(logData.getId());

    assertTrue(logDataSel.isPresent());
    LogData logDataE = logDataSel.get();
    assertEquals(logData.getValue(), logDataE.getValue());
    assertEquals(logData.getDataAttribute().getDataAttributeId(), logDataE.getDataAttribute().getDataAttributeId());
    assertEquals(logData.getLogInfo().getLogInfoId(), logDataE.getId().getLogInfoId());

  }

  /**
   * save by parent
   */
  @Test
  @Transactional
  public void savePtn2() {

    // select or create DescMstGroup
    DescMstGroup descMstGroup = this.makeDescMstGroup("CPU [#0]: AMD Ryzen 7 3800X");

    // select or create DescMst
    DescMst descMst = descMstTestData.makeDescMst("Core 0 T0 Effective Clock", "MHz", descMstGroup);

    // select or create DataAttribute
    DataAttribute dataAttribute = dataAttributeTestData.makeDataAttribute(27, descMst);

    // create HwinfoLog
    HwinfoLog hwinfoLog = new HwinfoLog();
    hwinfoLog.setDescription("logData save test");
    hwinfoLog.setName("0920-doom");
    // create LogInfo
    LogInfo logInfo = new LogInfo();
    logInfo.setFileName("0920-doom.csv");
    // OneToOne
    hwinfoLog.setLogInfo(logInfo);
    // OneToOne
    logInfo.setHwinfoLog(hwinfoLog);

    // create LogData
    LogData logData = new LogData();
    logData.setValue("19.4");
    // ManyToOne
    logData.setLogInfo(logInfo);
    logInfo.addLogData(logData);
    // OneToOne
    logData.setDataAttribute(dataAttribute);
    // set rowNo
    LogDataId id = logData.getId();
    id.setRowNo(1);

    // save LogInfo
    hwinfoLogRepo.save(hwinfoLog);
    assertNotNull(hwinfoLog.getLogInfo().getLogInfoId());
    assertNotNull(logData.getId().getDataAttributeId());
    assertNotNull(logData.getId().getLogInfoId());

    Optional<LogData> logDataSel = dataRepo.findById(logData.getId());

    assertTrue(logDataSel.isPresent());
    LogData logDataE = logDataSel.get();
    assertEquals(logData.getValue(), logDataE.getValue());
    assertEquals(logData.getDataAttribute().getDataAttributeId(), logDataE.getDataAttribute().getDataAttributeId());
    assertEquals(logData.getLogInfo().getLogInfoId(), logDataE.getId().getLogInfoId());

  }

  /**
   * save multiple datas
   */
  @Test
  @Transactional
  public void savePtn3() {
    // select or create DescMstGroup
    DescMstGroup descMstGroup = makeDescMstGroup("CPU [#0]: AMD Ryzen 7 3800X");

    // create and save DescMst
    // desc1
    DescMst descMst1 = descMstTestData.makeDescMst("Core 0 T0 Effective Clock", "MHz", descMstGroup);
    // desc2
    DescMst descMst2 = descMstTestData.makeDescMst("Core 0 T1 Effective Clock", "MHz", descMstGroup);

    // attribute1
    DataAttribute dataAttribute1 = dataAttributeTestData.makeDataAttribute(27, descMst1);
    // attribute2
    DataAttribute dataAttribute2 = dataAttributeTestData.makeDataAttribute(27, descMst2);

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
    // rowNo
    logData1_1.getId().setRowNo(1);
    logData1_2.getId().setRowNo(1);
    logData2_1.getId().setRowNo(2);
    logData2_2.getId().setRowNo(2);

    // parent1
    // OneToOne
    logData1_1.setDataAttribute(dataAttribute1);
    logData1_2.setDataAttribute(dataAttribute2);
    logData2_1.setDataAttribute(dataAttribute1);
    logData2_2.setDataAttribute(dataAttribute2);

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

    LogInfo logInfoSv = this.logInfoRepo.save(logInfo);
    Optional<LogInfo> sel = this.logInfoRepo.findById(logInfoSv.getLogInfoId());
    assertTrue(sel.isPresent());
    LogInfo logInfoE = sel.get();
    List<LogData> logDataListE = logInfoE.getLogDataList();
    for (LogData d : logDataListE) {
      LogDataId id = d.getId();
      assertNotNull(id.getDataAttributeId());
      assertNotNull(id.getLogInfoId());
      if (id.getRowNo() == 1 && id.getDataAttributeId() == dataAttribute1.getDataAttributeId()) {
        assertEquals(logData1_1.getValue(), d.getValue());
      } else if (id.getRowNo() == 1 && id.getDataAttributeId() == dataAttribute2.getDataAttributeId()) {
        assertEquals(logData1_2.getValue(), d.getValue());
      } else if (id.getRowNo() == 2 && id.getDataAttributeId() == dataAttribute1.getDataAttributeId()) {
        assertEquals(logData2_1.getValue(), d.getValue());
      } else if (id.getRowNo() == 2 && id.getDataAttributeId() == dataAttribute2.getDataAttributeId()) {
        assertEquals(logData2_2.getValue(), d.getValue());
      }
    }
  }

  DescMstGroup makeDescMstGroup(String name) {
    DescMstGroup descMstGroup = new DescMstGroup();
    descMstGroup.setName(name);
    Optional<DescMstGroup> descMstGroupExt = descMstGroupRepo.findByName(descMstGroup.getName());
    if (descMstGroupExt.isPresent())
      descMstGroup = descMstGroupExt.get();
    else
      descMstGroupRepo.save(descMstGroup);
    return descMstGroup;
  }

}