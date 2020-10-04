package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstGroupRepository;

@Component
public class DescMstGroupTestData {

  @Autowired
  DescMstGroupRepository descMstGroupRepo;

  public DescMstGroup makeDescMstGroup(String name) {
    DescMstGroup descMstGroup = new DescMstGroup();
    descMstGroup.setName(name);
    Optional<DescMstGroup> descMstGroupExt = descMstGroupRepo.findByName(descMstGroup.getName());
    if (descMstGroupExt.isPresent())
      descMstGroup = descMstGroupExt.get();
    else
      descMstGroupRepo.save(descMstGroup);
    return descMstGroup;
  }

}
