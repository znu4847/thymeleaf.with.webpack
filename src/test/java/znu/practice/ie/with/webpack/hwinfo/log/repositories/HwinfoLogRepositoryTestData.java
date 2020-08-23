package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.entities.HwSetting;
import znu.practice.ie.with.webpack.hwinfo.log.entities.HwinfoLog;
import znu.practice.ie.with.webpack.hwinfo.log.entities.SoftInfo;

@SpringBootTest
public class HwinfoLogRepositoryTestData {

  @Autowired
  HwinfoLogRepository repo;

  @Autowired
  SoftInfoRepository siRepo;

  @Autowired
  HwSettingRepository hsRepo;

  LocalDateTime now = LocalDateTime.now();

  @Test
  public void siData() {
    SoftInfo si = new SoftInfo();
    si.setName("Doom");
    si.setDescription("spec test data 1");

    SoftInfo si2 = new SoftInfo();
    si2.setName("Overwatch");
    si2.setDescription("spec test data 2");

    SoftInfo si3 = new SoftInfo();
    si3.setName("Civilization 6");
    si3.setDescription("spec test data 3");

    List<SoftInfo> datas = Lists.newArrayList();
    datas.add(si);
    datas.add(si2);
    datas.add(si3);

    siRepo.saveAll(datas);

  }

  @Test
  public void hsData() {
    HwSetting hs = new HwSetting();
    hs.setName("ryzen 3800x, RX 3800XT");
    hs.setDescription("spec test data 1");
    hs.setRegistedDate(now);

    HwSetting hs2 = new HwSetting();
    hs2.setName("i7, RTX 2070");
    hs2.setDescription("spec test data 2");
    hs2.setRegistedDate(now);

    HwSetting hs3 = new HwSetting();
    hs3.setName("ryzen 3600, GTX 1660");
    hs3.setDescription("spec test data 3");
    hs3.setRegistedDate(now);

    List<HwSetting> datas = Lists.newArrayList();
    datas.add(hs);
    datas.add(hs2);
    datas.add(hs3);

    hsRepo.saveAll(datas);
  }

  @Test
  public void saveCascade() {

    HwSetting ry3800 = hsRepo.findByName("ryzen 3800x, RX 3800XT");
    HwSetting i7 = hsRepo.findByName("i7, RTX 2070");
    HwSetting ry3600 = hsRepo.findByName("ryzen 3600, GTX 1660");

    SoftInfo doom = siRepo.findByName("Doom");
    SoftInfo overwatch = siRepo.findByName("Overwatch");
    SoftInfo civil6 = siRepo.findByName("Civilization 6");

    List<HwinfoLog> datas = Lists.newArrayList();

    HwinfoLog log1 = new HwinfoLog();
    log1.setName("i7 doom test 1");
    log1.setDescription("spec test data");
    log1.setHwSetting(i7);
    log1.setSoftInfo(doom);

    HwinfoLog log2 = new HwinfoLog();
    log2.setName("i7 doom test 2");
    log2.setDescription("spec test data");
    log2.setHwSetting(i7);
    log2.setSoftInfo(doom);

    HwinfoLog log3 = new HwinfoLog();
    log3.setName("ryzen3800 doom test 1");
    log3.setDescription("spec test data");
    log3.setHwSetting(ry3800);
    log3.setSoftInfo(doom);

    HwinfoLog log4 = new HwinfoLog();
    log4.setName("i7 overwatch test 1");
    log4.setDescription("spec test data");
    log4.setHwSetting(i7);
    log4.setSoftInfo(overwatch);

    HwinfoLog log5 = new HwinfoLog();
    log5.setName("ryzen3800 overwatch test 1");
    log5.setDescription("spec test data");
    log5.setHwSetting(ry3800);
    log5.setSoftInfo(overwatch);

    datas.add(log1);
    datas.add(log2);
    datas.add(log3);
    datas.add(log4);
    datas.add(log5);

    repo.saveAll(datas);

  }

}