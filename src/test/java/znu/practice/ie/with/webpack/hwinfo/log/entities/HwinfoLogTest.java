package znu.practice.ie.with.webpack.hwinfo.log.entities;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.HwSettingRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.HwinfoLogRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.SoftInfoRepository;

@SpringBootTest
public class HwinfoLogTest {

  @Autowired
  HwinfoLogRepository repo;

  @Autowired
  SoftInfoRepository softInfoRepo;

  @Autowired
  HwSettingRepository hwSettingRepo;

  @Transactional
  @Test
  public void savePtn1_1() {

    HwinfoLog hwinfoLog = new HwinfoLog();
    hwinfoLog.setName(randomAlphabetic(5));
    hwinfoLog.setDescription(randomAlphabetic(6));
    hwinfoLog.setRegistedDate(LocalDateTime.now());
    hwinfoLog.setUpdatedDate(LocalDateTime.now());
    HwinfoLog hwinfoLogSv = repo.save(hwinfoLog);
    Optional<HwinfoLog> sel = repo.findById(hwinfoLogSv.getHwinfoLogId());

    assertTrue(sel.isPresent());
    HwinfoLog hwinfoLogE = sel.get();
    assertEquals(hwinfoLog.getName(), hwinfoLogE.getName());
    assertEquals(hwinfoLog.getDescription(), hwinfoLogE.getDescription());
    assertEquals(hwinfoLog.getRegistedDate(), hwinfoLogE.getRegistedDate());
    assertEquals(hwinfoLog.getUpdatedDate(), hwinfoLogE.getUpdatedDate());

  }

  @Test
  public void savePtn1_2() {

    HwinfoLog hwinfoLog = new HwinfoLog();
    hwinfoLog.setName(randomAlphabetic(5));
    hwinfoLog.setDescription(randomAlphabetic(6));
    HwinfoLog hwinfoLogSv = repo.save(hwinfoLog);
    Optional<HwinfoLog> sel = repo.findById(hwinfoLogSv.getHwinfoLogId());

    assertTrue(sel.isPresent());
    HwinfoLog hwinfoLogE = sel.get();
    assertEquals(hwinfoLog.getName(), hwinfoLogE.getName());
    assertEquals(hwinfoLog.getDescription(), hwinfoLogE.getDescription());
    assertNotNull(hwinfoLogE.getRegistedDate());
    assertNotNull(hwinfoLogE.getUpdatedDate());

  }

  // @Transactional
  @Test
  public void savePtn2() {

    HwSetting hwSetting = new HwSetting();
    hwSetting.setName(randomAlphabetic(4));
    hwSetting.setDescription(randomAlphabetic(7));
    hwSetting.setRegistedDate(LocalDateTime.now());
    HwSetting hwSettingSv = this.hwSettingRepo.save(hwSetting);

    HwinfoLog hwinfoLog = new HwinfoLog();
    hwinfoLog.setName(randomAlphabetic(5));
    hwinfoLog.setDescription(randomAlphabetic(6));
    hwinfoLog.setRegistedDate(LocalDateTime.now());
    hwinfoLog.setUpdatedDate(LocalDateTime.now());
    List<HwinfoLog> hwinfoLogList = Lists.newArrayList(hwinfoLog);

    hwSettingSv.setHwinfoLog(hwinfoLogList);
    hwinfoLog.setHwSetting(hwSettingSv);

    HwinfoLog hwinfoLogSv = repo.save(hwinfoLog);
    Optional<HwinfoLog> sel = repo.findById(hwinfoLogSv.getHwinfoLogId());
    assertTrue(sel.isPresent());
    HwinfoLog hwinfoLogE = sel.get();
    HwSetting hwSettingE = hwinfoLogE.getHwSetting();
    assertEquals(hwSettingSv.getHwSettingId(), hwSettingE.getHwSettingId());
    assertEquals(hwSettingSv.getName(), hwSettingE.getName());

  }

  @Test
  public void savePtn3() {

    HwSetting hwSetting = new HwSetting();
    hwSetting.setName(randomAlphabetic(4));
    hwSetting.setDescription(randomAlphabetic(7));
    hwSetting.setRegistedDate(LocalDateTime.now());
    HwSetting hwSettingSv = this.hwSettingRepo.save(hwSetting);

    SoftInfo softInfo = new SoftInfo();
    softInfo.setName(randomAlphabetic(4));
    softInfo.setDescription(randomAlphabetic(7));
    SoftInfo softInfoSv = this.softInfoRepo.save(softInfo);

    HwinfoLog hwinfoLog = new HwinfoLog();
    hwinfoLog.setName(randomAlphabetic(5));
    hwinfoLog.setDescription(randomAlphabetic(6));
    hwinfoLog.setRegistedDate(LocalDateTime.now());
    hwinfoLog.setUpdatedDate(LocalDateTime.now());
    List<HwinfoLog> hwinfoLogList = Lists.newArrayList(hwinfoLog);

    hwSettingSv.setHwinfoLog(hwinfoLogList);
    hwinfoLog.setHwSetting(hwSettingSv);
    softInfoSv.setHwinfoLog(hwinfoLogList);
    hwinfoLog.setSoftInfo(softInfo);

    HwinfoLog hwinfoLogSv = repo.save(hwinfoLog);
    Optional<HwinfoLog> sel = repo.findById(hwinfoLogSv.getHwinfoLogId());
    assertTrue(sel.isPresent());
    HwinfoLog hwinfoLogE = sel.get();
    HwSetting hwSettingE = hwinfoLogE.getHwSetting();
    assertEquals(hwSettingSv.getHwSettingId(), hwSettingE.getHwSettingId());
    assertEquals(hwSettingSv.getName(), hwSettingE.getName());

    SoftInfo softInfoE = hwinfoLogE.getSoftInfo();
    assertEquals(softInfoSv.getSoftInfoId(), softInfoE.getSoftInfoId());
    assertEquals(softInfoSv.getName(), softInfoE.getName());

  }

}
