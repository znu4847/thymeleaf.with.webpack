package znu.practice.ie.with.webpack.hwinfo.log.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import znu.practice.ie.with.webpack.hwinfo.log.csv.CsvDataLoader;
import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMst;
import znu.practice.ie.with.webpack.hwinfo.log.entities.HwinfoLog;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogData;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogInfo;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.DescMstRepository;
import znu.practice.ie.with.webpack.hwinfo.log.repositories.HwinfoLogRepository;

@Service
public class FileService {

  @Autowired
  CsvDataLoader loader;

  @Autowired
  DescMstRepository descMstRepo;

  @Autowired
  HwinfoLogRepository hwinfoLogRepo;

  @Transactional
  public HwinfoLog save(MultipartFile multipartFile) {
    List<String[]> dataList = loader.loadManyToManyRelationship(multipartFile);

    // OneToOne
    HwinfoLog hwinfoLog = new HwinfoLog();
    LogInfo logInfo = new LogInfo();
    hwinfoLog.setLogInfo(logInfo);
    logInfo.setHwinfoLog(hwinfoLog);

    // LogData
    Map<Integer, DescMst> idxDescMstMap = Maps.newHashMap();
    String[] headers = dataList.remove(0);
    int idx = 0;
    // DescMst check
    for (String header : headers) {
      // Optional<DescMst> descMstSel = descMstRepo.findByName(header);
      Optional<DescMst> descMstSel = null;
      DescMst descMstE = null;
      if (!descMstSel.isPresent()) {
        DescMst descMst = new DescMst();
        String[] typeUnit = this.getTypeUnitArr(header);
        descMst.setName(header);
        descMst.setDataType(typeUnit[0]);
        descMst.setUnit(typeUnit[1]);
        DescMst descMstSV = descMstRepo.save(descMst);
        // descMst.setUnit();
        descMstE = descMstSV;
      } else {
        descMstE = descMstSel.get();
      }
      idxDescMstMap.put(idx, descMstE);
      idx++;
    }

    // create LogData
    int rowNo = 0;
    for (String[] row : dataList) {
      ++rowNo;
      for (int jdx = 0; jdx < row.length; jdx++) {
        DescMst descMst = idxDescMstMap.get(jdx);
        LogData logData = new LogData();
        logData.setValue(row[jdx]);
        logData.getId().setRowNo(rowNo);
        // ManyToOne
        // logData.setDescMst(descMst);
        // ManyToOne
        logData.setLogInfo(logInfo);
        // OneToMany
        logInfo.addLogData(logData);
      }
    }

    HwinfoLog hwinfoLogSv = this.hwinfoLogRepo.save(hwinfoLog);
    return this.hwinfoLogRepo.findById(hwinfoLogSv.getHwinfoLogId()).get();
  }

  String[] getTypeUnitArr(String header) {
    if (StringUtils.isEmpty(header)) {
      String[] typeUnit = { "", "" };
      return typeUnit;
    }
    Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(header);
    String type = "";
    String unit = "";
    if (m.find()) {
      type = header.substring(0, header.indexOf("[") - 1);
      unit = m.group(1);
    } else {
      type = header;
      unit = header;
    }
    String[] typeUnit = { type, unit };
    return typeUnit;
  }
}
