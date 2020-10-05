package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.DataAttributeRepository;

@Component
public class DataAttributeTestData {

  @Autowired
  DataAttributeRepository dataAttributeRepo;

  public DataAttribute makeDataAttribute(Integer col, DescMst descMst) {
    DataAttribute dataAttribute = new DataAttribute();
    dataAttribute.setColNo(col);
    dataAttribute.setDescMst(descMst);
    Optional<DataAttribute> dataAttributeExt = dataAttributeRepo
        .findByDescMstDescMstIdAndColNo(dataAttribute.getDescMst().getDescMstId(), dataAttribute.getColNo());
    if (dataAttributeExt.isPresent())
      dataAttribute = dataAttributeExt.get();
    else
      dataAttribute = dataAttributeRepo.save(dataAttribute);
    return dataAttribute;
  }
}
