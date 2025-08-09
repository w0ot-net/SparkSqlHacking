package com.univocity.parsers.common.routine;

import com.univocity.parsers.common.AbstractParser;
import com.univocity.parsers.common.AbstractWriter;
import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.CommonParserSettings;
import com.univocity.parsers.common.CommonWriterSettings;
import com.univocity.parsers.common.IterableResult;
import com.univocity.parsers.common.TextWritingException;
import com.univocity.parsers.common.fields.ColumnMapper;
import com.univocity.parsers.common.fields.ColumnMapping;
import com.univocity.parsers.common.processor.AbstractRowProcessor;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.common.processor.BeanProcessor;
import com.univocity.parsers.common.processor.BeanWriterProcessor;
import com.univocity.parsers.common.processor.RowProcessor;
import com.univocity.parsers.common.processor.RowWriterProcessor;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

public abstract class AbstractRoutines {
   private boolean keepResourcesOpen;
   private Writer previousOutput;
   private ColumnMapping columnMapper;
   private final String routineDescription;
   private CommonParserSettings parserSettings;
   private CommonWriterSettings writerSettings;

   protected abstract AbstractParser createParser(CommonParserSettings var1);

   protected abstract AbstractWriter createWriter(Writer var1, CommonWriterSettings var2);

   protected abstract CommonParserSettings createDefaultParserSettings();

   protected abstract CommonWriterSettings createDefaultWriterSettings();

   public AbstractRoutines(String routineDescription) {
      this(routineDescription, (CommonParserSettings)null, (CommonWriterSettings)null);
   }

   public AbstractRoutines(String routineDescription, CommonParserSettings parserSettings) {
      this(routineDescription, parserSettings, (CommonWriterSettings)null);
   }

   public AbstractRoutines(String routineDescription, CommonWriterSettings writerSettings) {
      this(routineDescription, (CommonParserSettings)null, writerSettings);
   }

   public AbstractRoutines(String routineDescription, CommonParserSettings parserSettings, CommonWriterSettings writerSettings) {
      this.keepResourcesOpen = false;
      this.columnMapper = new ColumnMapping();
      this.routineDescription = routineDescription;
      this.parserSettings = parserSettings;
      this.writerSettings = writerSettings;
   }

   private void validateWriterSettings() {
      if (this.writerSettings == null) {
         this.writerSettings = this.createDefaultWriterSettings();
      }

   }

   private void validateParserSettings() {
      if (this.parserSettings == null) {
         this.parserSettings = this.createDefaultParserSettings();
         this.parserSettings.setLineSeparatorDetectionEnabled(true);
      }

   }

   public final CommonParserSettings getParserSettings() {
      this.validateParserSettings();
      return this.parserSettings;
   }

   public final void setParserSettings(CommonParserSettings parserSettings) {
      this.parserSettings = parserSettings;
   }

   public final CommonWriterSettings getWriterSettings() {
      this.validateWriterSettings();
      return this.writerSettings;
   }

   public final void setWriterSettings(CommonWriterSettings writerSettings) {
      this.writerSettings = writerSettings;
   }

   protected void adjustColumnLengths(String[] headers, int[] lengths) {
   }

   public final void write(ResultSet rs, File output) {
      this.write(rs, output, (Charset)null);
   }

   public final void write(ResultSet rs, File output, String encoding) {
      this.write(rs, output, Charset.forName(encoding));
   }

   public final void write(ResultSet rs, File output, Charset encoding) {
      Writer writer = ArgumentUtils.newWriter(output, encoding);

      try {
         this.write(rs, writer);
      } finally {
         try {
            writer.close();
         } catch (Exception e) {
            throw new IllegalStateException("Error closing file: '" + output.getAbsolutePath() + "'", e);
         }
      }

   }

   public final void write(ResultSet rs, OutputStream output) {
      this.write(rs, ArgumentUtils.newWriter(output));
   }

   public final void write(ResultSet rs, OutputStream output, String encoding) {
      this.write(rs, ArgumentUtils.newWriter(output, encoding));
   }

   public final void write(ResultSet rs, OutputStream output, Charset encoding) {
      this.write(rs, ArgumentUtils.newWriter(output, encoding));
   }

   public final void write(ResultSet rs, Writer output) {
      this.validateWriterSettings();
      boolean hasWriterProcessor = this.writerSettings.getRowWriterProcessor() != null;
      AbstractWriter<W> writer = null;
      long rowCount = 0L;
      Object[] row = null;

      try {
         try {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            String[] headers = new String[columns];
            int[] lengths = new int[columns];

            for(int i = 1; i <= columns; ++i) {
               headers[i - 1] = md.getColumnLabel(i);
               int precision = md.getPrecision(i);
               int scale = md.getScale(i);
               int length;
               if (precision != 0 && scale != 0) {
                  length = precision + scale + 2;
               } else {
                  length = precision + scale;
               }

               lengths[i - 1] = length;
            }

            String[] userProvidedHeaders = this.writerSettings.getHeaders();
            if (userProvidedHeaders == null) {
               this.writerSettings.setHeaders(headers);
            } else {
               headers = userProvidedHeaders;
            }

            this.adjustColumnLengths(headers, lengths);
            writer = this.createWriter(output, this.writerSettings);
            if (this.writerSettings.isHeaderWritingEnabled()) {
               writer.writeHeaders();
            }

            for(Object[] var27 = new Object[columns]; rs.next(); ++rowCount) {
               for(int i = 1; i <= columns; ++i) {
                  var27[i - 1] = rs.getObject(i);
               }

               if (hasWriterProcessor) {
                  writer.processRecord(var27);
               } else {
                  writer.writeRow(var27);
               }
            }
         } finally {
            if (!this.keepResourcesOpen) {
               rs.close();
            }

         }
      } catch (Exception e) {
         throw new TextWritingException("Error writing data from result set", rowCount, row, e);
      } finally {
         this.close(writer);
      }

   }

   public final void parseAndWrite(Reader input, Writer output) {
      this.setRowWriterProcessor((RowWriterProcessor)null);
      this.setRowProcessor(this.createWritingRowProcessor(output));

      try {
         AbstractParser<P> parser = this.createParser(this.parserSettings);
         parser.parse(input);
      } finally {
         this.parserSettings.setRowProcessor((RowProcessor)null);
      }

   }

   private void setRowWriterProcessor(RowWriterProcessor rowWriterProcessor) {
      this.validateWriterSettings();
      this.writerSettings.setRowWriterProcessor(rowWriterProcessor);
   }

   private void setRowProcessor(RowProcessor rowProcessor) {
      this.validateParserSettings();
      this.parserSettings.setRowProcessor(rowProcessor);
   }

   private RowProcessor createWritingRowProcessor(Writer output) {
      // $FF: Couldn't be decompiled
   }

   private void close(AbstractWriter writer) {
      if (writer != null) {
         if (!this.keepResourcesOpen) {
            writer.close();
         } else {
            writer.flush();
         }
      }

   }

   public void writeAll(Iterable elements, Class beanType, File output, String... headers) {
      this.writeAll(elements, beanType, ArgumentUtils.newWriter(output), headers);
   }

   public void writeAll(Iterable elements, Class beanType, File output, String encoding, String[] headers) {
      this.writeAll(elements, beanType, ArgumentUtils.newWriter(output, encoding), headers);
   }

   public void writeAll(Iterable elements, Class beanType, File output, Charset encoding, String... headers) {
      this.writeAll(elements, beanType, ArgumentUtils.newWriter(output, encoding), headers);
   }

   public void writeAll(Iterable elements, Class beanType, OutputStream output, String... headers) {
      this.writeAll(elements, beanType, ArgumentUtils.newWriter(output), headers);
   }

   public void writeAll(Iterable elements, Class beanType, OutputStream output, String encoding, String[] headers) {
      this.writeAll(elements, beanType, ArgumentUtils.newWriter(output, encoding), headers);
   }

   public void writeAll(Iterable elements, Class beanType, OutputStream output, Charset encoding, String... headers) {
      this.writeAll(elements, beanType, ArgumentUtils.newWriter(output, encoding), headers);
   }

   public void writeAll(Iterable elements, Class beanType, Writer output, String... headers) {
      BeanWriterProcessor<T> processor = new BeanWriterProcessor(beanType);
      processor.setColumnMapper(this.columnMapper);
      this.setRowWriterProcessor(processor);

      try {
         if (headers.length > 0) {
            this.writerSettings.setHeaders(headers);
            this.writerSettings.setHeaderWritingEnabled(true);
         }

         if (this.keepResourcesOpen && this.previousOutput == output) {
            this.writerSettings.setHeaderWritingEnabled(false);
         }

         AbstractWriter<W> writer = this.createWriter(output, this.writerSettings);
         if (this.keepResourcesOpen) {
            writer.processRecords(elements);
            this.previousOutput = output;
         } else {
            writer.processRecordsAndClose(elements);
         }
      } finally {
         this.writerSettings.setRowWriterProcessor((RowWriterProcessor)null);
      }

   }

   public List parseAll(Class beanType, File input, int expectedBeanCount) {
      return this.parseAll(beanType, ArgumentUtils.newReader(input), expectedBeanCount);
   }

   public List parseAll(Class beanType, File input, String encoding, int expectedBeanCount) {
      return this.parseAll(beanType, ArgumentUtils.newReader(input, encoding), expectedBeanCount);
   }

   public List parseAll(Class beanType, File input, Charset encoding, int expectedBeanCount) {
      return this.parseAll(beanType, ArgumentUtils.newReader(input, encoding), expectedBeanCount);
   }

   public List parseAll(Class beanType, InputStream input, int expectedBeanCount) {
      return this.parseAll(beanType, ArgumentUtils.newReader(input), expectedBeanCount);
   }

   public List parseAll(Class beanType, InputStream input, String encoding, int expectedBeanCount) {
      return this.parseAll(beanType, ArgumentUtils.newReader(input, encoding), expectedBeanCount);
   }

   public List parseAll(Class beanType, InputStream input, Charset encoding, int expectedBeanCount) {
      return this.parseAll(beanType, ArgumentUtils.newReader(input, encoding), expectedBeanCount);
   }

   public List parseAll(Class beanType, Reader input, int expectedBeanCount) {
      BeanListProcessor processor = new BeanListProcessor(beanType, expectedBeanCount);
      processor.setColumnMapper(this.columnMapper);
      this.setRowProcessor(processor);

      List var5;
      try {
         this.createParser(this.parserSettings).parse(input);
         var5 = processor.getBeans();
      } finally {
         this.parserSettings.setRowProcessor((RowProcessor)null);
      }

      return var5;
   }

   public List parseAll(Class beanType, File input) {
      return this.parseAll(beanType, (File)input, 0);
   }

   public List parseAll(Class beanType, File input, String encoding) {
      return this.parseAll(beanType, (File)input, (String)encoding, 0);
   }

   public List parseAll(Class beanType, File input, Charset encoding) {
      return this.parseAll(beanType, (File)input, (Charset)encoding, 0);
   }

   public List parseAll(Class beanType, InputStream input) {
      return this.parseAll(beanType, (InputStream)input, 0);
   }

   public List parseAll(Class beanType, InputStream input, String encoding) {
      return this.parseAll(beanType, (InputStream)input, (String)encoding, 0);
   }

   public List parseAll(Class beanType, InputStream input, Charset encoding) {
      return this.parseAll(beanType, (InputStream)input, (Charset)encoding, 0);
   }

   public List parseAll(Class beanType, Reader input) {
      return this.parseAll(beanType, (Reader)input, 0);
   }

   public IterableResult iterate(Class beanType, File input) {
      return this.iterate(beanType, ArgumentUtils.newReader(input));
   }

   public IterableResult iterate(Class beanType, File input, String encoding) {
      return this.iterate(beanType, ArgumentUtils.newReader(input, encoding));
   }

   public IterableResult iterate(Class beanType, File input, Charset encoding) {
      return this.iterate(beanType, ArgumentUtils.newReader(input, encoding));
   }

   public IterableResult iterate(Class beanType, InputStream input) {
      return this.iterate(beanType, ArgumentUtils.newReader(input));
   }

   public IterableResult iterate(Class beanType, InputStream input, String encoding) {
      return this.iterate(beanType, ArgumentUtils.newReader(input, encoding));
   }

   public IterableResult iterate(Class beanType, InputStream input, Charset encoding) {
      return this.iterate(beanType, ArgumentUtils.newReader(input, encoding));
   }

   public IterableResult iterate(Class beanType, Reader input) {
      // $FF: Couldn't be decompiled
   }

   public String toString() {
      return this.routineDescription;
   }

   public InputDimension getInputDimension(File input) {
      return this.getInputDimension(ArgumentUtils.newReader(input));
   }

   public InputDimension getInputDimension(File input, String encoding) {
      return this.getInputDimension(ArgumentUtils.newReader(input, encoding));
   }

   public InputDimension getInputDimension(InputStream input) {
      return this.getInputDimension(ArgumentUtils.newReader(input));
   }

   public InputDimension getInputDimension(InputStream input, String encoding) {
      return this.getInputDimension(ArgumentUtils.newReader(input, encoding));
   }

   public InputDimension getInputDimension(Reader input) {
      // $FF: Couldn't be decompiled
   }

   public boolean getKeepResourcesOpen() {
      return this.keepResourcesOpen;
   }

   public void setKeepResourcesOpen(boolean keepResourcesOpen) {
      this.keepResourcesOpen = keepResourcesOpen;
   }

   public ColumnMapper getColumnMapper() {
      return this.columnMapper;
   }

   public void setColumnMapper(ColumnMapper columnMapper) {
      this.columnMapper = columnMapper == null ? new ColumnMapping() : (ColumnMapping)columnMapper.clone();
   }

   // $FF: synthetic method
   static CommonWriterSettings access$000(AbstractRoutines x0) {
      return x0.writerSettings;
   }

   // $FF: synthetic method
   static void access$100(AbstractRoutines x0, AbstractWriter x1) {
      x0.close(x1);
   }

   // $FF: synthetic method
   static CommonParserSettings access$200(AbstractRoutines x0) {
      return x0.parserSettings;
   }
}
