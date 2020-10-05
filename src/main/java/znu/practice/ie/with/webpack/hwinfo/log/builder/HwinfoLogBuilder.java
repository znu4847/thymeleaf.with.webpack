package znu.practice.ie.with.webpack.hwinfo.log.builder;

import java.util.List;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import znu.practice.ie.with.webpack.hwinfo.log.csv.CsvDataLoader;
import znu.practice.ie.with.webpack.hwinfo.log.entities.DataAttribute;
import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMst;
import znu.practice.ie.with.webpack.hwinfo.log.entities.DescMstGroup;
import znu.practice.ie.with.webpack.hwinfo.log.entities.HwinfoLog;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogData;
import znu.practice.ie.with.webpack.hwinfo.log.entities.LogInfo;

@Component
public class HwinfoLogBuilder {

  @Autowired
  CsvDataLoader csvDataLoader;

  @Autowired
  LogDataBuilder logDataBuilder;

  @Autowired
  DataAttributeBuilder dataAttributeBuilder;

  @Autowired
  DescMstGroupBuilder descMstGroupBuilder;

  @Autowired
  DescMstBuilder descMstBuilder;

  public HwinfoLog build(MultipartFile multipartFile) {

    HwinfoLog hwinfoLog = new HwinfoLog();
    LogInfo logInfo = new LogInfo();
    // OneToOne
    hwinfoLog.setLogInfo(logInfo);
    // OneToOne
    logInfo.setHwinfoLog(hwinfoLog);

    List<String[]> dataList = this.readFile(multipartFile);
    RawLogData rawLogData = this.convertRawLogData(dataList);
    List<DataAttribute> header = this.buildHeader(rawLogData.getHeader());
    this.buildLogData(rawLogData, logInfo, header);

    return hwinfoLog;
  }

  List<String[]> readFile(MultipartFile multipartFile) {
    List<String[]> dataList = csvDataLoader.loadManyToManyRelationship(multipartFile);
    return dataList;
  }

  RawLogData convertRawLogData(List<String[]> dataList) {
    String[] header = dataList.remove(0);
    String[] groups = dataList.remove(dataList.size() - 1);
    dataList.remove(dataList.size() - 1);

    RawLogData rawLogData = new RawLogData();
    rawLogData.setBody(dataList);

    List<RawDataAttribute> attrList = Lists.newArrayList();
    for (int i = 0; i < header.length; i++) {
      String group = groups[i];
      if (i == 0) {
        group = "DATE";
      } else if (i == 1) {
        group = "TIME";
      }
      RawDataAttribute rawDataAttribute = new RawDataAttribute();
      rawDataAttribute.setGroup(group);
      rawDataAttribute.setAttribute(header[i]);
      rawDataAttribute.setColNo(i);
      attrList.add(rawDataAttribute);
    }

    rawLogData.setHeader(attrList);
    return rawLogData;
  }

  List<DataAttribute> buildHeader(List<RawDataAttribute> rawHeader) {
    List<DataAttribute> header = Lists.newArrayList();
    for (RawDataAttribute attr : rawHeader) {
      DescMstGroup descMstGroup = this.descMstGroupBuilder.build(attr);
      DescMst descMst = this.descMstBuilder.build(descMstGroup, attr);
      DataAttribute dataAttribute = dataAttributeBuilder.build(descMst, attr);
      header.add(dataAttribute);
    }
    return header;
  }

  List<LogData> buildLogData(RawLogData rawLogData, LogInfo logInfo, List<DataAttribute> header) {
    List<String[]> dataList = rawLogData.getBody();
    List<LogData> logDataList = Lists.newArrayList();
    for (int rowIdx = 0; rowIdx < dataList.size(); rowIdx++) {
      String[] dataRow = dataList.get(rowIdx);
      for (DataAttribute dataAttribute : header) {
        LogData logData = this.logDataBuilder.build(logInfo, dataAttribute, rowIdx, dataRow);
        logDataList.add(logData);
      }
    }
    return logDataList;
  }

}
