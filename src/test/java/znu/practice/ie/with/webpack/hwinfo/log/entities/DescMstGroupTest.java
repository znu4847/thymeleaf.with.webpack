package znu.practice.ie.with.webpack.hwinfo.log.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstGroupRepository;

@SpringBootTest
public class DescMstGroupTest {

  @Autowired
  DescMstGroupRepository descMstGroupRepo;

  @Autowired
  DescMstGroupTestData descMstGroupTestData;

  @Test
  @Transactional
  public void savePtn1() {
    DescMstGroup descMstGroup = new DescMstGroup();
    descMstGroup.setName("CPU [#0]: AMD Ryzen 7 3800X");
    descMstGroup = descMstGroupRepo.save(descMstGroup);
    assertNotNull(descMstGroup.getDescMstGroupId());
    Optional<DescMstGroup> descMstGroupSel = descMstGroupRepo.findById(descMstGroup.getDescMstGroupId());
    assertTrue(descMstGroupSel.isPresent());
    DescMstGroup descMstGroupE = descMstGroupSel.get();
    assertEquals(descMstGroup.getName(), descMstGroupE.getName());
    System.out.println(descMstGroup.getDescMstGroupId());
  }

  @Test
  @Transactional
  public void testData() {
    DescMstGroup descMstGroup = descMstGroupTestData.makeDescMstGroup("CPU [#0]: AMD Ryzen 7 3800X");
    assertNotNull(descMstGroup.getDescMstGroupId());
    Optional<DescMstGroup> descMstGroupSel = descMstGroupRepo.findById(descMstGroup.getDescMstGroupId());
    assertTrue(descMstGroupSel.isPresent());
    DescMstGroup descMstGroupE = descMstGroupSel.get();
    assertEquals(descMstGroup.getName(), descMstGroupE.getName());
    System.out.println(descMstGroup.getDescMstGroupId());
    System.out.println(descMstGroup.getDescMstGroupId());
  }

}
