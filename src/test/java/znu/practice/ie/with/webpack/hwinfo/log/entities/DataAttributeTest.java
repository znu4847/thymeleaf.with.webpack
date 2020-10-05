package znu.practice.ie.with.webpack.hwinfo.log.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.DataAttributeRepository;

@SpringBootTest
public class DataAttributeTest {

  @Autowired
  DataAttributeRepository dataAttributeRepo;

  @Autowired
  DataAttributeTestData dataAttributeTestData;

  @Autowired
  DescMstTestData descMstTestData;

  @Autowired
  DescMstGroupTestData descMstGroupTestData;

  @Test
  @Transactional
  public void save() {
    DescMstGroup descMstGroup = descMstGroupTestData
        .makeDescMstGroup("MSI MPG X570 GAMING EDGE WIFI (MS-7C37) (Nuvoton NCT7717U/NCT7718W)");
    DescMst descMst = descMstTestData.makeDescMst("External 1 [°C]", "°C", descMstGroup);

    DataAttribute dataAttribute = new DataAttribute();
    dataAttribute.setColNo(44);
    dataAttribute.setDescMst(descMst);
    this.dataAttributeRepo.save(dataAttribute);

    Optional<DataAttribute> dataAttributeSel = dataAttributeRepo
        .findByDescMstDescMstIdAndColNo(dataAttribute.getDescMst().getDescMstId(), dataAttribute.getColNo());
    assertTrue(dataAttributeSel.isPresent());
    DataAttribute dataAttributeE = dataAttributeSel.get();
    assertEquals(dataAttribute.getDescMst().getDescMstId(), dataAttributeE.getDescMst().getDescMstId());
    assertEquals(dataAttribute.getColNo(), dataAttributeE.getColNo());
  }

  @Test
  @Transactional
  public void testData() {
    DescMstGroup descMstGroup = descMstGroupTestData
        .makeDescMstGroup("MSI MPG X570 GAMING EDGE WIFI (MS-7C37) (Nuvoton NCT7717U/NCT7718W)");
    DescMst descMst = descMstTestData.makeDescMst("External 1 [°C]", "°C", descMstGroup);

    DataAttribute dataAttribute = dataAttributeTestData.makeDataAttribute(44, descMst);

    Optional<DataAttribute> dataAttributeSel = dataAttributeRepo
        .findByDescMstDescMstIdAndColNo(dataAttribute.getDescMst().getDescMstId(), dataAttribute.getColNo());
    assertTrue(dataAttributeSel.isPresent());
    DataAttribute dataAttributeE = dataAttributeSel.get();
    assertEquals(dataAttribute.getDescMst().getDescMstId(), dataAttributeE.getDescMst().getDescMstId());
    assertEquals(dataAttribute.getColNo(), dataAttributeE.getColNo());
  }

}
