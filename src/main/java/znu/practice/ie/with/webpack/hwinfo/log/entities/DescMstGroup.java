package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class DescMstGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long descMstGroupId;

  @Column(unique = true)
  private String name;

  @OneToMany(mappedBy = "descMstGroup")
  List<DescMst> descMstList;

  public void addDescMst(DescMst descMst) {

  }

}
