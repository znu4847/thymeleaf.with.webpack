package znu.practice.ie.with.webpack.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "log_list")
public class LogList {

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "log_name")
  private String logName;

  @Column(name = "log_info_id")
  private Long logInfoId;

  @Column(name = "game_info_id")
  private Long gameInfoId;

  @Column(name = "disp_temp_id")
  private Long dispTempId;

  @Column(name = "app_info_id")
  private Long appInfoId;

  @Column(name = "description")
  private String description;

  @Column(name = "registed_date")
  private LocalDateTime registedDate;

  @Column(name = "updated_date")
  private LocalDateTime updatedDate;

  @OneToOne
  LogInfo logInfo;

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder("LogList : ");
    str.append("id : " + id);
    str.append(", logName : " + logName);
    str.append(", logInfoId : " + logInfoId);
    str.append(", gameInfoId : " + gameInfoId);
    str.append(", dispTempId : " + dispTempId);
    str.append(", appInfoId : " + appInfoId);
    str.append(", description : " + description);
    str.append(", registedDate : " + registedDate.toString());
    str.append(", updatedDate : " + updatedDate.toString());
    return str.toString();
  }
}