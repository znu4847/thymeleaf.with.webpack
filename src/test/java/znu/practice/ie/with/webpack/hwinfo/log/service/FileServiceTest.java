package znu.practice.ie.with.webpack.hwinfo.log.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import znu.practice.ie.with.webpack.hwinfo.log.entities.HwinfoLog;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogData;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogInfo;

@SpringBootTest
public class FileServiceTest {

  @Autowired
  FileService service;

  // @Test
  public void save() throws FileNotFoundException, IOException {

    File file = new File("src/test/resources/mock/data_sample.CSV");
    MockMultipartFile multipartFile = new MockMultipartFile("data_sample.CSV", new FileInputStream(file));
    HwinfoLog hwinfoLog = service.save(multipartFile);

    assertNotNull(hwinfoLog);
    LogInfo logInfo = hwinfoLog.getLogInfo();
    for (LogData logData : logInfo.getLogDataList()) {
      System.out.println(logData);
    }

  }

}
