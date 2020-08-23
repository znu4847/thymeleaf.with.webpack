package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class SoftInfo {

  public SoftInfo(Long id) {
    this.id = id;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "softInfo", cascade = CascadeType.ALL)
  private List<HwinfoLog> hwinfoLog;

  private String name;

  private String description;

  @Override
  public String toString() {
    return "SoftInfo [description=" + description + ", id=" + id + ", name=" + name + "]";
  }

}