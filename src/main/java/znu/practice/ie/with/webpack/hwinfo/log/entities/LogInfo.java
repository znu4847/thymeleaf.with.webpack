package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class LogInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "hwinfo_log_id")
  private HwinfoLog hwinfoLog;

  @OneToMany(mappedBy = "logInfo", cascade = CascadeType.ALL)
  private List<LogData> logDataList;

  private String fileName;

}