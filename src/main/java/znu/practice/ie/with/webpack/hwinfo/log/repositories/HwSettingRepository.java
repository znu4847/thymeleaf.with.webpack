package znu.practice.ie.with.webpack.hwinfo.log.repositories;

import org.springframework.data.repository.CrudRepository;

import znu.practice.ie.with.webpack.hwinfo.log.entities.HwSetting;

public interface HwSettingRepository extends CrudRepository<HwSetting, Long> {

  // HwSetting findByName(String name);
}