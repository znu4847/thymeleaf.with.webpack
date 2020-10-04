package znu.practice.ie.with.webpack.hwinfo.log.csv;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CsvDataLoader {

  public <T> List<T> loadObjectList(final Class<T> type, final MultipartFile multipartFile) {
    try {
      final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
      final CsvMapper mapper = new CsvMapper();
      mapper.registerModule(new JavaTimeModule());

      InputStream initialStream = multipartFile.getInputStream();
      byte[] buffer = new byte[initialStream.available()];
      initialStream.read(buffer);

      File file = new File("src/main/resources/targetFile.tmp");
      multipartFile.transferTo(file);

      final MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file);
      return readValues.readAll();
    } catch (final Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public List<String[]> loadManyToManyRelationship(final MultipartFile multipartFile) {
    try {
      final CsvMapper mapper = new CsvMapper();
      final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withSkipFirstDataRow(false);
      mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

      File file = new File("src/main/resources/targetFile.tmp");
      multipartFile.transferTo(file);

      final MappingIterator<String[]> readValues = mapper.readerFor(String[].class).with(bootstrapSchema)
          .readValues(file);
      return readValues.readAll();
    } catch (final Exception e) {
      // logger.error("err");
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}