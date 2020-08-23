package znu.practice.ie.with.webpack.hwinfo.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import znu.practice.ie.with.webpack.hwinfo.log.entities.HwinfoLog;

@Service
@Transactional
public class LogReadService {

  public HwinfoLog readFile(File logFile) {

    return null;
  }

}