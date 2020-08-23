package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import znu.practice.ie.with.webpack.hwinfo.log.entities.SoftInfo;

public interface SoftInfoRepository extends JpaRepository<SoftInfo, Long>, JpaSpecificationExecutor<SoftInfo> {

  SoftInfo findByName(String name);

}