package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMstGroup;

public interface DescMstGroupRepository extends CrudRepository<DescMstGroup, Long> {
  Optional<DescMstGroup> findByName(String name);
}
