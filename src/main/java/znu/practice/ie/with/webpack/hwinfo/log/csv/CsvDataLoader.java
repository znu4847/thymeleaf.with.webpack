package znu.practice.ie.with.webpack.hwinfo.log.csv;

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class CsvDataLoader {

  public <T> List<T> loadObjectList(final Class<T> type, final String fileName) {
    try {
      final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
      final CsvMapper mapper = new CsvMapper();
      mapper.registerModule(new JavaTimeModule());
      final File file = new ClassPathResource(fileName).getFile();
      final MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file);
      return readValues.readAll();
    } catch (final Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public List<String[]> loadManyToManyRelationship(final String fileName) {
    try {
      final CsvMapper mapper = new CsvMapper();
      final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withSkipFirstDataRow(true);
      mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
      final File file = new ClassPathResource(fileName).getFile();
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