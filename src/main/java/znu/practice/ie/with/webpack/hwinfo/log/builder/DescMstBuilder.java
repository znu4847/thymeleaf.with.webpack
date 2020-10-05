package znu.practice.ie.with.webpack.hwinfo.log.builder;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMst;
import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMstGroup;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstGroupRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstRepository;

@Component
public class DescMstBuilder {

  @Autowired
  DescMstRepository descMstRepo;

  @Autowired
  DescMstGroupRepository descMstGroupRepo;

  public DescMst build(DescMstGroup descMstGroup, RawDataAttribute rawDataAttribute) {
    String[] typeUnitArr = this.getTypeUnitArr(rawDataAttribute.getAttribute());
    DescMst descMst = new DescMst();
    descMst.setName(rawDataAttribute.getAttribute());
    descMst.setDataType(typeUnitArr[0]);
    descMst.setUnit(typeUnitArr[1]);
    descMst.setDescMstGroup(descMstGroup);
    Optional<DescMst> descMstExt = descMstRepo.findByDescMstGroupDescMstGroupIdAndNameAndUnit(
        descMst.getDescMstGroup().getDescMstGroupId(), descMst.getName(), descMst.getUnit());
    if (descMstExt.isPresent())
      descMst = descMstExt.get();
    else
      descMstRepo.save(descMst);
    return descMst;
  }

  String[] getTypeUnitArr(String header) {
    if (StringUtils.isEmpty(header)) {
      String[] typeUnit = { "", "" };
      return typeUnit;
    }
    Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(header);
    String type = "";
    String unit = "";
    if (m.find()) {
      type = header.substring(0, header.indexOf("[") - 1);
      unit = m.group(1);
    } else {
      type = header;
      unit = header;
    }
    String[] typeUnit = { type, unit };
    return typeUnit;
  }
}
