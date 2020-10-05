package znu.practice.ie.with.webpack.hwinfo.log.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import znu.practice.ie.with.webpack.hwinfo.log.csv.CsvDataLoader;
import znu.practice.ie.with.webpack.hwinfo.log.entities.DataAttribute;
import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMst;
import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMstGroup;

@SpringBootTest
public class DataAttributeBuilderTest {

  @Autowired
  CsvDataLoader csvDataLoader;

  @Autowired
  DataAttributeBuilder dataAttributeBuilder;

  @Autowired
  DescMstGroupBuilder descMstGroupBuilder;

  @Autowired
  DescMstBuilder descMstBuilder;

  @Test
  @Transactional
  public void build() throws IOException {
    File file = new File("src/test/resources/mock/data_sample.CSV");
    MockMultipartFile multipartFile = new MockMultipartFile("data_sample.CSV", new FileInputStream(file));
    List<String[]> dataList = csvDataLoader.loadManyToManyRelationship(multipartFile);
    String[] header = dataList.remove(0);
    String[] groups = dataList.remove(dataList.size() - 1);
    dataList.remove(dataList.size() - 1);

    RawLogData rawLogData = new RawLogData();
    rawLogData.body = dataList;

    List<RawDataAttribute> attrList = Lists.newArrayList();
    for (int i = 0; i < header.length; i++) {
      String group = groups[i];
      if (i == 0) {
        group = "DATE";
      } else if (i == 1) {
        group = "TIME";
      }
      RawDataAttribute rawDataAttribute = new RawDataAttribute();
      rawDataAttribute.setGroup(group);
      rawDataAttribute.setAttribute(header[i]);
      rawDataAttribute.setColNo(i);
      attrList.add(rawDataAttribute);
    }
    rawLogData.setHeader(attrList);

    for (RawDataAttribute attr : attrList) {
      DescMstGroup descMstGroup = this.descMstGroupBuilder.build(attr);
      DescMst descMst = this.descMstBuilder.build(descMstGroup, attr);
      DataAttribute dataAttribute = dataAttributeBuilder.build(descMst, attr);
      assertNotNull(dataAttribute);
      assertEquals(dataAttribute.getDescMst().getName(), attr.getAttribute());
      assertEquals(dataAttribute.getDescMst().getDescMstGroup().getName(), attr.getGroup());
    }
  }
}
