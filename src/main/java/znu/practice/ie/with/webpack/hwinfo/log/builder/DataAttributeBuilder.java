package znu.practice.ie.with.webpack.hwinfo.log.builder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import znu.practice.ie.with.webpack.hwinfo.log.entities.DataAttribute;
import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMst;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.DataAttributeRepository;

@Component
public class DataAttributeBuilder {

  @Autowired
  DataAttributeRepository dataAttributeRepo;

  public DataAttribute build(DescMst descMst, RawDataAttribute rawDataAttribute) {
    DataAttribute dataAttribute = new DataAttribute();
    dataAttribute.setColNo(rawDataAttribute.getColNo());
    dataAttribute.setDescMst(descMst);
    Optional<DataAttribute> dataAttributeSel = dataAttributeRepo
        .findByDescMstDescMstIdAndColNo(dataAttribute.getDescMst().getDescMstId(), dataAttribute.getColNo());
    if (dataAttributeSel.isPresent())
      dataAttribute = dataAttributeSel.get();
    else
      dataAttribute = dataAttributeRepo.save(dataAttribute);
    return dataAttribute;
  }
}
