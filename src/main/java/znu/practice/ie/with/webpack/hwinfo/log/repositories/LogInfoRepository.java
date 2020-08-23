package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import org.springframework.data.repository.CrudRepository;

import znu.practice.ie.with.webpack.hwinfo.log.entities.LogInfo;

public interface LogInfoRepository extends CrudRepository<LogInfo, Long> {

}