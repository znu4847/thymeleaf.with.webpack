package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import znu.practice.ie.with.webpack.hwinfo.log.entities.LogData;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogDataId;

public interface LogDataRepository extends CrudRepository<LogData, LogDataId> {

  List<LogData> findByIdLogInfoId(Long logInfoId);

}