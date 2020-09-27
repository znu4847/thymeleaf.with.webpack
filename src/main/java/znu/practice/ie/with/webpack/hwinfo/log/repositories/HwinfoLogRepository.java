package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import znu.practice.ie.with.webpack.hwinfo.log.entities.HwinfoLog;

public interface HwinfoLogRepository extends CrudRepository<HwinfoLog, Long> {

}