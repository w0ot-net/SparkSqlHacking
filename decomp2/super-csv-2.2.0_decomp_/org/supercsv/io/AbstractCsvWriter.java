package org.supercsv.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import org.supercsv.encoder.CsvEncoder;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;
import org.supercsv.util.Util;

public abstract class AbstractCsvWriter implements ICsvWriter {
   private final BufferedWriter writer;
   private final CsvPreference preference;
   private final CsvEncoder encoder;
   private int lineNumber = 0;
   private int rowNumber = 0;
   private int columnNumber = 0;

   public AbstractCsvWriter(Writer writer, CsvPreference preference) {
      if (writer == null) {
         throw new NullPointerException("writer should not be null");
      } else if (preference == null) {
         throw new NullPointerException("preference should not be null");
      } else {
         this.writer = new BufferedWriter(writer);
         this.preference = preference;
         this.encoder = preference.getEncoder();
      }
   }

   public void close() throws IOException {
      this.writer.close();
   }

   public void flush() throws IOException {
      this.writer.flush();
   }

   /** @deprecated */
   protected String escapeString(String csvElement) {
      CsvContext context = new CsvContext(this.lineNumber, this.rowNumber, this.columnNumber);
      String escapedCsv = this.encoder.encode(csvElement, context, this.preference);
      this.lineNumber = context.getLineNumber();
      return escapedCsv;
   }

   protected void incrementRowAndLineNo() {
      ++this.lineNumber;
      ++this.rowNumber;
   }

   public int getLineNumber() {
      return this.lineNumber;
   }

   public int getRowNumber() {
      return this.rowNumber;
   }

   protected void writeRow(List columns) throws IOException {
      this.writeRow(Util.objectListToStringArray(columns));
   }

   protected void writeRow(Object... columns) throws IOException {
      this.writeRow(Util.objectArrayToStringArray(columns));
   }

   protected void writeRow(String... columns) throws IOException {
      if (columns == null) {
         throw new NullPointerException(String.format("columns to write should not be null on line %d", this.lineNumber));
      } else if (columns.length == 0) {
         throw new IllegalArgumentException(String.format("columns to write should not be empty on line %d", this.lineNumber));
      } else {
         for(int i = 0; i < columns.length; ++i) {
            this.columnNumber = i + 1;
            if (i > 0) {
               this.writer.write(this.preference.getDelimiterChar());
            }

            String csvElement = columns[i];
            if (csvElement != null) {
               this.writer.write(this.escapeString(csvElement));
            }
         }

         this.writer.write(this.preference.getEndOfLineSymbols());
      }
   }

   public void writeComment(String comment) throws IOException {
      ++this.lineNumber;
      if (comment == null) {
         throw new NullPointerException(String.format("comment to write should not be null on line %d", this.lineNumber));
      } else {
         this.writer.write(comment);
         this.writer.write(this.preference.getEndOfLineSymbols());
      }
   }

   public void writeHeader(String... header) throws IOException {
      this.incrementRowAndLineNo();
      this.writeRow(header);
   }
}
