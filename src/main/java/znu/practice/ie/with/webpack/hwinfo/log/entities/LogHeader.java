package znu.practice.ie.with.webpack.hwinfo.log.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class LogHeader {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long logHeaderId;

  @OneToOne
  @JoinColumn(name = "desc_mst_id")
  private DescMst descMst;

  @OneToOne(mappedBy = "logHeader")
  private LogData logData;

  @Column(nullable = false)
  private Integer col;

}
