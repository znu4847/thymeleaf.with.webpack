package znu.practice.ie.with.webpack.hwinfo.log.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import znu.practice.ie.with.webpack.hwinfo.log.entities.DataAttribute;
import znu.practice.ie.with.webpack.hwinfo.log.entities.HwinfoLog;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogData;

@SpringBootTest
public class HwinfoLogBuilderTest {

  @Autowired
  HwinfoLogBuilder builder;

  @Test
  public void readFile() throws IOException {
    File file = new File("src/test/resources/mock/data_sample.CSV");
    MockMultipartFile multipartFile = new MockMultipartFile("data_sample.CSV", new FileInputStream(file));
    List<String[]> dataList = builder.readFile(multipartFile);
    String[] headers = dataList.remove(0);
    String[] row1 = dataList.get(0);
    assertEquals(headers.length, row1.length);
    assertNotNull(dataList);
    assertTrue(dataList.size() > 0);
  }

  @Test
  public void convertRawLogData() throws IOException {
    File file = new File("src/test/resources/mock/data_sample.CSV");
    MockMultipartFile multipartFile = new MockMultipartFile("data_sample.CSV", new FileInputStream(file));
    List<String[]> dataList = builder.readFile(multipartFile);
    RawLogData rawLogData = builder.convertRawLogData(dataList);
    assertNotNull(rawLogData);
    assertNotNull(rawLogData.getHeader());
    assertNotNull(rawLogData.getBody());
    assertEquals(rawLogData.getHeader().size(), rawLogData.getBody().get(0).length);
  }

  @Test
  @Transactional
  public void buildHeader() throws IOException {
    File file = new File("src/test/resources/mock/data_sample.CSV");
    MockMultipartFile multipartFile = new MockMultipartFile("data_sample.CSV", new FileInputStream(file));
    List<String[]> dataList = builder.readFile(multipartFile);
    RawLogData rawLogData = builder.convertRawLogData(dataList);
    List<DataAttribute> dataAttrList = builder.buildHeader(rawLogData.getHeader());
    for (int i = 0; i < dataAttrList.size(); i++) {
      DataAttribute dataAttr = dataAttrList.get(i);
      RawDataAttribute rawAttr = rawLogData.getHeader().get(i);
      assertEquals(dataAttr.getColNo(), rawAttr.getColNo());
      assertEquals(dataAttr.getDescMst().getName(), rawAttr.getAttribute());
      assertEquals(dataAttr.getDescMst().getDescMstGroup().getName(), rawAttr.getGroup());
    }

  }

  // @Test
  // @Transactional
  public void buildData() throws IOException {
    File file = new File("src/test/resources/mock/data_sample.CSV");
    MockMultipartFile multipartFile = new MockMultipartFile("data_sample.CSV", new FileInputStream(file));
    HwinfoLog hwinfoLog = builder.build(multipartFile);
    List<LogData> logDataList = hwinfoLog.getLogInfo().getLogDataList();

    Map<Integer, List<LogData>> rowMap = new HashMap<>();
    for (LogData logData : logDataList) {
      Integer rowNo = logData.getId().getRowNo();
      rowMap.putIfAbsent(rowNo, Lists.newArrayList());
      rowMap.get(rowNo).add(logData);
    }
    List<LogData> row1 = rowMap.get(1);

    Map<Long, LogData> colMap = new HashMap<>();
    for (LogData col : row1) {
      colMap.put(col.getDataAttribute().getDataAttributeId(), col);
    }

    assertEquals(row1.size(), colMap.size());

  }

}
