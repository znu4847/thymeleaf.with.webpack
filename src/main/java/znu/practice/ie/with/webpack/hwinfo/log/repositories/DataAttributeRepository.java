package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import znu.practice.ie.with.webpack.hwinfo.log.entities.DataAttribute;

public interface DataAttributeRepository extends CrudRepository<DataAttribute, Long> {
  Optional<DataAttribute> findByDescMstDescMstIdAndColNo(Long descMstId, Integer col);
}
