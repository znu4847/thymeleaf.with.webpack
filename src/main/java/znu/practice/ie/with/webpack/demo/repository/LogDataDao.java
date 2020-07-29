package znu.practice.ie.with.webpack.demo.repository;

import org.springframework.data.repository.CrudRepository;

import znu.practice.ie.with.webpack.demo.entity.LogData;

public interface LogDataDao extends CrudRepository<LogData, Long> {

}