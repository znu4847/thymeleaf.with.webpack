package znu.practice.ie.with.webpack.hwinfo.log.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import znu.practice.ie.with.webpack.hwinfo.log.entities.HwinfoLog;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogData;

@SpringBootTest
public class HwinfoLogBuilderTest {

  @Autowired
  HwinfoLogBuilder builder;

  @Test
  public void load() throws IOException {
    File file = new File("src/test/resources/mock/data_sample.CSV");
    MockMultipartFile multipartFile = new MockMultipartFile("data_sample.CSV", new FileInputStream(file));
    List<String[]> dataList = builder.readFile(multipartFile);
    String[] headers = dataList.remove(0);
    String[] row1 = dataList.get(0);
    assertEquals(headers.length, row1.length);

  }

  @Test
  public void header() throws IOException {
    File file = new File("src/test/resources/mock/data_sample.CSV");
    MockMultipartFile multipartFile = new MockMultipartFile("data_sample.CSV", new FileInputStream(file));
    List<String[]> dataList = builder.readFile(multipartFile);
    String[] headers = dataList.remove(0);
    Map<String, List<String>> headerMap = new HashMap<>();
    for (String header : headers) {
      headerMap.putIfAbsent(header, Lists.newArrayList());
      headerMap.get(header).add(header);
    }

    List<String> dupList = new ArrayList<>();
    for (String header : headers) {
      List<String> headerList = headerMap.get(header);
      if (headerList.size() > 1) {
        dupList.add(header);
      }
    }
    dupList.stream().forEach(System.out::println);

    // assertEquals(headers.length, headerSet.size());
  }

  @Test
  public void footer() throws IOException {
    File file = new File("src/test/resources/mock/data_sample.CSV");
    MockMultipartFile multipartFile = new MockMultipartFile("data_sample.CSV", new FileInputStream(file));
    List<String[]> dataList = builder.readFile(multipartFile);
    String[] headers = dataList.remove(0);
    String[] footer = dataList.remove(dataList.size() - 1);
    Set<String> footerSet = Sets.newHashSet();
    for (int idx = 0; idx < footer.length; idx++) {
      System.out.println(footer[idx]);
      footerSet.add(footer[idx]);
    }
    System.out.println("-------- groups");

    footerSet.stream().forEach(System.out::println);

  }

  public void header2() throws IOException {
    File file = new File("src/test/resources/mock/data_sample.CSV");
    MockMultipartFile multipartFile = new MockMultipartFile("data_sample.CSV", new FileInputStream(file));
    List<String[]> dataList = builder.readFile(multipartFile);
    String[] headers = dataList.remove(0);
    String[] footer = dataList.remove(dataList.size() - 1);
    for (int idx = 0; idx < footer.length; idx++) {
      System.out.println(footer[idx]);
    }

  }

  // @Test
  // @Transactional
  public void test1() throws IOException {
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
      colMap.put(col.getLogHeader().getLogHeaderId(), col);
    }

    assertEquals(row1.size(), colMap.size());

  }

}
