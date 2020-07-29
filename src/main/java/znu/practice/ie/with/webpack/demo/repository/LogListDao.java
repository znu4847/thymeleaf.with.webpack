package znu.practice.ie.with.webpack.demo.repository;

import org.springframework.data.repository.CrudRepository;

import znu.practice.ie.with.webpack.demo.entity.LogList;

public interface LogListDao extends CrudRepository<LogList, Long> {
  long deleteByLogNameStartsWith(String logName);
}