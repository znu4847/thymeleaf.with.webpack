package znu.practice.ie.with.webpack.hwinfo.log.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class LogData {

  @EmbeddedId
  private LogDataId id = new LogDataId();

  @ManyToOne
  @MapsId("logInfoId")
  @JoinColumn(name = "log_info_id")
  private LogInfo logInfo;

  @ManyToOne
  @MapsId("dataAttributeId")
  @JoinColumn(name = "data_attribute_id")
  private DataAttribute dataAttribute;

  private String value;

}