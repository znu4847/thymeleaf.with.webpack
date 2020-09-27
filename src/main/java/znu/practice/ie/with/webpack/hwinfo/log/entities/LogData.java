package znu.practice.ie.with.webpack.hwinfo.log.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;

@Data
@Entity
public class LogData {

  @EmbeddedId
  private LogDataId id = new LogDataId();

  // private Long logInfoId;
  @ManyToOne
  @MapsId("logInfoId")
  @JoinColumn(name = "log_info_id")
  private LogInfo logInfo;

  @ManyToOne
  @MapsId("descMstId")
  @JoinColumn(name = "desc_mst_id")
  private DescMst descMst;

  private String value;

}