package znu.practice.ie.with.webpack.demo.entity.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class LogInformation {

  @Id
  Long id;

  String logName;

  Long logInfoId;

  Long gameInfoId;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("LogInformation - ");
    sb.append("id : " + id);
    sb.append(", logName : " + logName);
    sb.append(", logInfoId : " + logInfoId);
    sb.append(", gameInfoId : " + gameInfoId);
    return sb.toString();
  }

}