package znu.practice.ie.with.webpack.hwinfo.log.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstRepository;

@SpringBootTest
public class DescMstTest {

  @Autowired
  DescMstRepository descMstRepo;

  @Autowired
  DescMstTestData descMstTestData;

  @Autowired
  DescMstGroupTestData descMstGroupTestData;

  @Test
  @Transactional
  public void save() {
    DescMstGroup descMstGroup = descMstGroupTestData.makeDescMstGroup("CPU [#0]: AMD Ryzen 7 3800X");

    DescMst descMst = new DescMst();
    descMst.setName("External 1 [°C]");
    descMst.setDataType("Temperature");
    descMst.setUnit("°C");
    descMst.setDescMstGroup(descMstGroup);
    descMstRepo.save(descMst);

    Optional<DescMst> descMstSel = descMstRepo.findById(descMst.getDescMstId());
    assertTrue(descMstSel.isPresent());
    DescMst descMstE = descMstSel.get();
    assertEquals(descMst.getName(), descMstE.getName());
    assertEquals(descMst.getDataType(), descMstE.getDataType());
    assertEquals(descMst.getUnit(), descMstE.getUnit());
  }

  @Test
  @Transactional
  public void testData() {
    DescMstGroup descMstGroup = descMstGroupTestData.makeDescMstGroup("CPU [#0]: AMD Ryzen 7 3800X");

    DescMst descMst = descMstTestData.makeDescMst("External 1 [°C]", "Temperature", descMstGroup);

    Optional<DescMst> descMstSel = descMstRepo.findById(descMst.getDescMstId());
    assertTrue(descMstSel.isPresent());
    DescMst descMstE = descMstSel.get();
    assertEquals(descMst.getName(), descMstE.getName());
    assertEquals(descMst.getDataType(), descMstE.getDataType());
    assertEquals(descMst.getUnit(), descMstE.getUnit());
  }

}
