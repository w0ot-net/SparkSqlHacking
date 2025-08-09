package org.apache.derby.impl.load;

import java.io.InputStream;
import java.io.Reader;

abstract class ExportWriteDataAbstract {
   protected ControlInfo controlFileReader;
   protected int[] columnLengths;
   protected String fieldSeparator;
   protected String recordSeparator;
   protected String nullString;
   protected String columnDefinition;
   protected String format;
   protected String fieldStartDelimiter;
   protected String fieldStopDelimiter;
   protected String dataCodeset;
   protected String dataLocale;
   protected boolean hasDelimiterAtEnd;
   protected boolean doubleDelimiter = true;

   protected void loadPropertiesInfo() throws Exception {
      this.fieldSeparator = this.controlFileReader.getFieldSeparator();
      this.recordSeparator = this.controlFileReader.getRecordSeparator();
      this.nullString = this.controlFileReader.getNullString();
      this.columnDefinition = this.controlFileReader.getColumnDefinition();
      this.format = this.controlFileReader.getFormat();
      this.fieldStartDelimiter = this.controlFileReader.getFieldStartDelimiter();
      this.fieldStopDelimiter = this.controlFileReader.getFieldEndDelimiter();
      this.dataCodeset = this.controlFileReader.getDataCodeset();
      this.hasDelimiterAtEnd = this.controlFileReader.getHasDelimiterAtEnd();
   }

   abstract void writeColumnDefinitionOptionally(String[] var1, String[] var2) throws Exception;

   public void setColumnLengths(int[] var1) {
      this.columnLengths = var1;
   }

   public abstract void writeData(String[] var1, boolean[] var2) throws Exception;

   abstract String writeBinaryColumnToExternalFile(InputStream var1) throws Exception;

   abstract String writeCharColumnToExternalFile(Reader var1) throws Exception;

   public abstract void noMoreRows() throws Exception;
}
