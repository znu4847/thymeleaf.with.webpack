package znu.practice.ie.with.webpack.hwinfo.log.builder;

import java.util.List;

import lombok.Data;

@Data
public class RawLogData {

  List<RawDataAttribute> header;
  List<String[]> body;

}
