package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Embeddable
@EqualsAndHashCode
public class LogDataId implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "log_info_id")
  private Long logInfoId;

  @Column(name = "data_attribute_id")
  private Long dataAttributeId;

  @Column(name = "row_no")
  private Integer rowNo;

}