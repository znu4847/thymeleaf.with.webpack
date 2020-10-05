package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class DataAttribute {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long dataAttributeId;

  @ManyToOne
  @JoinColumn(name = "desc_mst_id")
  private DescMst descMst;

  @OneToMany(mappedBy = "dataAttribute")
  private List<LogData> logDataList;

  @Column(nullable = false)
  private Integer colNo;

}
