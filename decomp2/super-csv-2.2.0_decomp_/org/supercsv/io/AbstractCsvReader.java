package org.supercsv.io;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.Util;

public abstract class AbstractCsvReader implements ICsvReader {
   private final ITokenizer tokenizer;
   private final CsvPreference preferences;
   private final List columns = new ArrayList();
   private int rowNumber = 0;

   public AbstractCsvReader(Reader reader, CsvPreference preferences) {
      if (reader == null) {
         throw new NullPointerException("reader should not be null");
      } else if (preferences == null) {
         throw new NullPointerException("preferences should not be null");
      } else {
         this.preferences = preferences;
         this.tokenizer = new Tokenizer(reader, preferences);
      }
   }

   public AbstractCsvReader(ITokenizer tokenizer, CsvPreference preferences) {
      if (tokenizer == null) {
         throw new NullPointerException("tokenizer should not be null");
      } else if (preferences == null) {
         throw new NullPointerException("preferences should not be null");
      } else {
         this.preferences = preferences;
         this.tokenizer = tokenizer;
      }
   }

   public void close() throws IOException {
      this.tokenizer.close();
   }

   public String get(int n) {
      return (String)this.columns.get(n - 1);
   }

   public String[] getHeader(boolean firstLineCheck) throws IOException {
      if (firstLineCheck && this.tokenizer.getLineNumber() != 0) {
         throw new SuperCsvException(String.format("CSV header must be fetched as the first read operation, but %d lines have already been read", this.tokenizer.getLineNumber()));
      } else {
         return this.readRow() ? (String[])this.columns.toArray(new String[this.columns.size()]) : null;
      }
   }

   public int getLineNumber() {
      return this.tokenizer.getLineNumber();
   }

   public String getUntokenizedRow() {
      return this.tokenizer.getUntokenizedRow();
   }

   public int getRowNumber() {
      return this.rowNumber;
   }

   public int length() {
      return this.columns.size();
   }

   protected List getColumns() {
      return this.columns;
   }

   protected CsvPreference getPreferences() {
      return this.preferences;
   }

   protected boolean readRow() throws IOException {
      if (this.tokenizer.readColumns(this.columns)) {
         ++this.rowNumber;
         return true;
      } else {
         return false;
      }
   }

   protected List executeProcessors(List processedColumns, CellProcessor[] processors) {
      Util.executeCellProcessors(processedColumns, this.getColumns(), processors, this.getLineNumber(), this.getRowNumber());
      return processedColumns;
   }
}
