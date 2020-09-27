package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class DescMst {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long descMstId;

  @OneToMany(mappedBy = "descMst")
  private List<LogData> logDataList;

  private String name;

  private String dataType;

  private String unit;

}