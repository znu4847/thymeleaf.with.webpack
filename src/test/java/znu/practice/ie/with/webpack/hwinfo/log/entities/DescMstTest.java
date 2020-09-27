package znu.practice.ie.with.webpack.hwinfo.log.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstRepository;

@SpringBootTest
public class DescMstTest {

  @Autowired
  DescMstRepository repo;

  @Test
  public void save() {
    DescMst dm = new DescMst();
    dm.setName("External 1 [°C]");
    dm.setDataType("Temperature");
    dm.setUnit("°C");

    DescMst entity = repo.save(dm);

    Optional<DescMst> sel = repo.findById(entity.getDescMstId());

    assertTrue(sel.isPresent());
    assertEquals(dm.getName(), sel.get().getName());
    assertEquals(dm.getDataType(), sel.get().getDataType());
    assertEquals(dm.getUnit(), sel.get().getUnit());
  }
}
