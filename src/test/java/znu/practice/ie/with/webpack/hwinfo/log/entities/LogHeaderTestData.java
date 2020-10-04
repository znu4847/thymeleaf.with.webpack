package znu.practice.ie.with.webpack.hwinfo.log.entities;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import znu.practice.ie.with.webpack.hwinfo.log.repositories.LogHeaderRepository;

@Component
public class LogHeaderTestData {

  @Autowired
  LogHeaderRepository logHeaderRepo;

  public LogHeader makeLogHeader(Integer col, DescMst descMst) {
    LogHeader logHeader = new LogHeader();
    logHeader.setCol(col);
    logHeader.setDescMst(descMst);
    Optional<LogHeader> logHeaderExt = logHeaderRepo.findByDescMstDescMstIdAndCol(logHeader.getDescMst().getDescMstId(),
        logHeader.getCol());
    if (logHeaderExt.isPresent())
      logHeader = logHeaderExt.get();
    else
      logHeader = logHeaderRepo.save(logHeader);
    return logHeader;
  }
}
