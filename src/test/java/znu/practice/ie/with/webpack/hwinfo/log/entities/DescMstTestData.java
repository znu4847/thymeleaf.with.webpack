package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstRepository;

@Component
public class DescMstTestData {

  @Autowired
  DescMstRepository descMstRepo;

  public DescMst makeDescMst(String name, String unit, DescMstGroup descMstGroup) {
    DescMst descMst = new DescMst();
    descMst.setName(name);
    descMst.setUnit(unit);
    descMst.setDescMstGroup(descMstGroup);
    Optional<DescMst> descMstExt = descMstRepo.findByDescMstGroupDescMstGroupIdAndNameAndUnit(
        descMst.getDescMstGroup().getDescMstGroupId(), descMst.getName(), descMst.getUnit());
    if (descMstExt.isPresent())
      descMst = descMstExt.get();
    else
      descMstRepo.save(descMst);
    return descMst;
  }
}
