package znu.practice.ie.with.webpack.hwinfo.log.csv;

import java.util.List;

import lombok.Data;

@Data
public class CsvHeaderAndBody {

  String[] header;

  List<String[]> body;
}
