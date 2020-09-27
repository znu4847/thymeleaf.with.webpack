package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class HwinfoLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long hwinfoLogId;

  @OneToOne(mappedBy = "hwinfoLog", cascade = CascadeType.ALL)
  private LogInfo logInfo;

  // private Long softInfoId;
  @ManyToOne
  @JoinColumn(name = "soft_info_id")
  private SoftInfo softInfo;

  // private Long hwSettingId;
  @ManyToOne
  @JoinColumn(name = "hw_setting_id")
  private HwSetting hwSetting;

  private String name;

  private String description;

  @CreationTimestamp
  private LocalDateTime registedDate;

  @UpdateTimestamp
  private LocalDateTime updatedDate;

  @Override
  public String toString() {
    return "HwinfoLog [description=" + description + ", id=" + hwinfoLogId + ", name=" + name + ", registedDate="
        + registedDate + ", updatedDate=" + updatedDate + "]";
  }

}