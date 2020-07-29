package znu.practice.ie.with.webpack.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "log_data")
public class LogData {

  @Id
  @Column(name = "id")
  Long id;

  @Column(name = "log_info_id")
  Long logInfoId;

  @Column(name = "data_no")
  BigDecimal dataNo;

  @Column(name = "desc_mst_id")
  Long descMstId;

  @ManyToOne
  LogInfo logInfo;

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder("LogData - ");
    str.append("id : " + id);
    str.append(", logInfoId : " + logInfoId);
    str.append(", dataNo : " + dataNo);
    str.append(", descMstId : " + descMstId);
    return str.toString();
  }
}
