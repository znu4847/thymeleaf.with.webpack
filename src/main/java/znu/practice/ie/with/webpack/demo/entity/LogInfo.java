package znu.practice.ie.with.webpack.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "log_info")
public class LogInfo {

  @Id
  @Column(name = "id")
  Long id;

  @Column(name = "file_name")
  String fileName;

  @Column(name = "valid")
  boolean valid;

  @Column(name = "registed_date")
  LocalDateTime registedDate;

  @Column(name = "updated_date")
  LocalDateTime updatedDate;

  @OneToOne
  LogList logList;

  @OneToMany(mappedBy = "logInfo")
  List<LogData> logData;

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder("LogInfo - ");
    str.append(" id : " + id);
    str.append(", fileName : " + fileName);
    str.append(", valid : " + valid);
    str.append(", registedDate : " + registedDate.toString());
    str.append(", updatedDate : " + updatedDate.toString());
    return str.toString();
  }

}
