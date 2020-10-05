package znu.practice.ie.with.webpack.hwinfo.log.builder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import znu.practice.ie.with.webpack.hwinfo.log.entities.DataAttribute;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogData;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogDataId;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogInfo;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.LogDataRepository;

@Component
public class LogDataBuilder {

  @Autowired
  LogDataRepository logDataRepo;

  public LogData build(LogInfo logInfo, DataAttribute dataAttribute, Integer rowIdx, String[] rowData) {

    LogData logData = new LogData();
    LogDataId id = logData.getId();
    id.setLogInfoId(logInfo.getLogInfoId());
    id.setDataAttributeId(dataAttribute.getDataAttributeId());
    id.setRowNo(rowIdx);
    logData.setValue(rowData[dataAttribute.getColNo()]);
    // ManyToOne
    logData.setDataAttribute(dataAttribute);
    // ManyToOne
    logData.setLogInfo(logInfo);
    // OneToMany
    logInfo.addLogData(logData);
    Optional<LogData> logDataSel = logDataRepo.findById(id);
    if (logDataSel.isPresent())
      logData = logDataSel.get();
    else
      logData = logDataRepo.save(logData);
    return logData;
  }

}
