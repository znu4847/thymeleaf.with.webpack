package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class HwSetting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long hwSettingId;

  @OneToMany(mappedBy = "hwSetting", cascade = CascadeType.ALL)
  private List<HwinfoLog> hwinfoLog;

  private String name;

  private String description;

  @CreationTimestamp
  private LocalDateTime registedDate;
}