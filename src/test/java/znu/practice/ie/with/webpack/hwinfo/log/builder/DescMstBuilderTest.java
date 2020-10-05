package znu.practice.ie.with.webpack.hwinfo.log.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMst;
import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMstGroup;

@SpringBootTest
public class DescMstBuilderTest {

  @Autowired
  DescMstBuilder descMstBuilder;

  @Autowired
  DescMstGroupBuilder descMstGroupBuilder;

  @Test
  public void build() {

    String group = RandomStringUtils.randomAlphabetic(8);
    String attribute = RandomStringUtils.randomAlphabetic(6);
    RawDataAttribute rawDataAttribute = new RawDataAttribute();
    rawDataAttribute.setGroup(group);
    rawDataAttribute.setAttribute(attribute);

    DescMstGroup descMstGroup = this.descMstGroupBuilder.build(rawDataAttribute);
    DescMst descMst = descMstBuilder.build(descMstGroup, rawDataAttribute);

    assertNotNull(descMst);
    assertNotNull(descMst.getDescMstId());
    assertNotNull(descMst.getDescMstGroup());
    assertNotNull(descMst.getDescMstGroup().getDescMstGroupId());
    assertEquals(attribute, descMst.getName());
    assertEquals(group, descMst.getDescMstGroup().getName());

  }

}
