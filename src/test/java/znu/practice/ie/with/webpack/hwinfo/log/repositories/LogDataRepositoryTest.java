package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMst;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogData;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogInfo;

@SpringBootTest
public class LogDataRepositoryTest {

  @Autowired
  LogDataRepository repo;

  @Autowired
  LogInfoRepository logInfoRepo;

  @Autowired
  DescMstRepository descMstRepo;

  @Test
  @Transactional
  public void savePtn1() {

    LogInfo logInfo = new LogInfo();
    logInfo.setFileName("ut-test.csv");
    LogInfo logInfoSv = logInfoRepo.save(logInfo);

    DescMst mst1 = new DescMst();
    mst1.setName("Core 6 Power (SMU) [W]");
    mst1.setUnit("W");
    mst1.setDataType("Watt");

    DescMst mst2 = new DescMst();
    mst2.setName("CPU [°C]");
    mst2.setUnit("°C");
    mst2.setDataType("temperature");

    List<DescMst> descList = Lists.newArrayList(mst1, mst2);

    List<DescMst> descSvList = Lists.newArrayList(descMstRepo.saveAll(descList));

    DescMst mst1Sv = descSvList.get(0);
    DescMst mst2Sv = descSvList.get(1);
    System.out.println("---- mst1 : " + mst1Sv.getId());

    LogData logData1 = new LogData();
    logData1.getId().setRowNo(1);
    logData1.setDescMst(mst1Sv);
    logData1.setLogInfo(logInfoSv);
    logData1.setValue("3.722");

    LogData logData2 = new LogData();
    logData2.getId().setRowNo(1);
    logData2.setDescMst(mst2Sv);
    logData2.setLogInfo(logInfoSv);
    logData2.setValue("61.4");
    List<LogData> logDataList = Lists.newArrayList(logData1, logData2);
    repo.saveAll(logDataList);

    List<LogData> select = repo.findByIdLogInfoId(logInfoSv.getId());
    System.out.println("---- save size : " + select.size());
  }

}