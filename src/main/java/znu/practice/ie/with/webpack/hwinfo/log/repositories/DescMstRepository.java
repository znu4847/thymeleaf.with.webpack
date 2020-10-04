package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMst;

public interface DescMstRepository extends CrudRepository<DescMst, Long> {
  Optional<DescMst> findByDescMstGroupDescMstGroupIdAndNameAndUnit(Long descMstGroupId, String name, String unit);
}