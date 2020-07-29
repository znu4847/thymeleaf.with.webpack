package znu.practice.ie.with.webpack.demo.repository;

import org.springframework.data.repository.CrudRepository;

import znu.practice.ie.with.webpack.demo.entity.LogInfo;

public interface LogInfoDao extends CrudRepository<LogInfo, Long> {

}