package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import znu.practice.ie.with.webpack.hwinfo.log.entities.LogHeader;

public interface LogHeaderRepository extends CrudRepository<LogHeader, Long> {
  Optional<LogHeader> findByDescMstDescMstIdAndCol(Long descMstId, Integer col);
}
