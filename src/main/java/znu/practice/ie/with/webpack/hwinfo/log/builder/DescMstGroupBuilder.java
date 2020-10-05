package znu.practice.ie.with.webpack.hwinfo.log.builder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMstGroup;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstGroupRepository;

@Component
public class DescMstGroupBuilder {

  @Autowired
  DescMstGroupRepository descMstGroupRepo;

  public DescMstGroup build(RawDataAttribute rawDataAttribute) {
    DescMstGroup descMstGroup = new DescMstGroup();
    descMstGroup.setName(rawDataAttribute.getGroup());
    Optional<DescMstGroup> descMstGroupSel = descMstGroupRepo.findByName(descMstGroup.getName());
    if (descMstGroupSel.isPresent())
      descMstGroup = descMstGroupSel.get();
    else
      descMstGroupRepo.save(descMstGroup);
    return descMstGroup;
  }

}
