package org.apache.derby.impl.load;

import java.util.Locale;
import java.util.Properties;

class ControlInfo {
   static final String ESCAPE = "Escape";
   static final String DEFAULT_ESCAPE = "\\";
   static final String QUOTE = "Quote";
   static final String DEFAULT_QUOTE = "'";
   static final String COMMIT_COUNT = "CommitCount";
   static final String DEFAULT_COMMIT_COUNT = "0";
   static final String START_ROW = "StartRow";
   static final String DEFAULT_START_ROW = "1";
   static final String STOP_ROW = "StopRow";
   static final String DEFAULT_STOP_ROW = "0";
   static final String FIELD_SEPARATOR = "FieldSeparator";
   static final String DEFAULT_FIELD_SEPARATOR = ",";
   static final String RECORD_SEPARATOR = "RecordSeparator";
   static final String DEFAULT_RECORD_SEPARATOR = System.getProperty("line.separator");
   static final String COLUMN_DEFINITION = "ColumnDefinition";
   static final String DEFAULT_COLUMN_DEFINITION = "FALSE";
   static final String NULL_STRING = "Null";
   static final String DEFAULT_NULL_STRING = "NULL";
   static final String FORMAT = "Format";
   static final String DEFAULT_FORMAT = "ASCII_DELIMITED";
   static final String DB2_DELIMITED_FORMAT = "DB2_DELIMITED";
   static final String FIELD_START_DELIMITER = "FieldStartDelimiter";
   static final String DEFAULT_FIELD_START_DELIMITER = "\"";
   static final String FIELD_END_DELIMITER = "FieldEndDelimiter";
   static final String DEFAULT_FIELD_END_DELIMITER = "\"";
   static final String COLUMN_WIDTHS = "ColumnWidths";
   static final String MESSAGE_FILE = "MessageFile";
   static final String DEFAULT_VERSION = "1";
   static final String VERSION = "Version";
   static final String NEWLINE = "\n";
   static final String COMMA = ",";
   static final String SPACE = " ";
   static final String TAB = "\t";
   static final String CR = "\r";
   static final String LF = "\n";
   static final String CRLF = "\r\n";
   static final String LFCR = "\n\r";
   static final String FF = "\f";
   static final String EMPTY_LINE = "\n\n";
   static final String SEMICOLON = ";";
   static final String DATA_CODESET = "DataCodeset";
   static final String HAS_DELIMETER_AT_END = "HasDelimeterAtEnd";
   static final String INTERNAL_NONE = "None";
   static final String INTERNAL_TRUE = "True";
   static final String INTERNAL_FALSE = "False";
   static final String INTERNAL_TAB = "Tab";
   static final String INTERNAL_SPACE = "Space";
   static final String INTERNAL_CR = "CR";
   static final String INTERNAL_LF = "LF";
   static final String INTERNAL_CRLF = "CR-LF";
   static final String INTERNAL_LFCR = "LF-CR";
   static final String INTERNAL_COMMA = "Comma";
   static final String INTERNAL_SEMICOLON = "Semicolon";
   static final String INTERNAL_NEWLINE = "New Line";
   static final String INTERNAL_FF = "FF";
   static final String INTERNAL_EMPTY_LINE = "Empty line";
   private Properties currentProperties;

   public ControlInfo() throws Exception {
      this.getCurrentProperties();
      if (this.getFieldSeparator().indexOf(this.getRecordSeparator()) != -1) {
         throw LoadError.fieldAndRecordSeparatorsSubset();
      }
   }

   String getPropertyValue(String var1) throws Exception {
      return this.getCurrentProperties().getProperty(var1);
   }

   private void loadDefaultValues() {
      this.currentProperties = new Properties();
      this.currentProperties.put("FieldSeparator", ",");
      this.currentProperties.put("RecordSeparator", DEFAULT_RECORD_SEPARATOR);
      this.currentProperties.put("ColumnDefinition", "FALSE");
      this.currentProperties.put("Null", "NULL");
      this.currentProperties.put("Format", "ASCII_DELIMITED");
      this.currentProperties.put("FieldStartDelimiter", "\"");
      this.currentProperties.put("FieldEndDelimiter", "\"");
      this.currentProperties.put("Version", "1");
      this.currentProperties.put("HasDelimeterAtEnd", "False");
   }

   String getCurrentVersion() throws Exception {
      return "1";
   }

   String getFormat() throws Exception {
      return this.getCurrentProperties().getProperty("Format");
   }

   int[] getColumnWidths() {
      return null;
   }

   String getFieldSeparator() throws Exception {
      String var1 = this.getCurrentProperties().getProperty("FieldSeparator");
      var1 = this.mapFromUserFriendlyFieldDelimiters(var1);
      return var1;
   }

   String getFieldStartDelimiter() throws Exception {
      return this.getCurrentProperties().getProperty("FieldStartDelimiter");
   }

   String getFieldEndDelimiter() throws Exception {
      return this.getCurrentProperties().getProperty("FieldEndDelimiter");
   }

   String getRecordSeparator() throws Exception {
      String var1 = this.getCurrentProperties().getProperty("RecordSeparator");
      var1 = this.mapFromUserFriendlyRecordDelimiters(var1);
      return var1;
   }

   boolean getHasDelimiterAtEnd() throws Exception {
      String var1 = this.getCurrentProperties().getProperty("HasDelimeterAtEnd");
      return var1.equals("True");
   }

   String getHasDelimeterAtEndString() throws Exception {
      String var1 = this.getCurrentProperties().getProperty("HasDelimeterAtEnd");
      return var1;
   }

   String getNullString() throws Exception {
      return this.getCurrentProperties().getProperty("Null");
   }

   String getColumnDefinition() throws Exception {
      return this.getCurrentProperties().getProperty("ColumnDefinition");
   }

   private String mapFromUserFriendlyFieldDelimiters(String var1) {
      if (var1.toUpperCase(Locale.ENGLISH).equals("Tab".toUpperCase(Locale.ENGLISH))) {
         return "\t";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("Space".toUpperCase(Locale.ENGLISH))) {
         return " ";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("CR".toUpperCase(Locale.ENGLISH))) {
         return "\r";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("LF".toUpperCase(Locale.ENGLISH))) {
         return "\n";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("CR-LF".toUpperCase(Locale.ENGLISH))) {
         return "\r\n";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("LF-CR".toUpperCase(Locale.ENGLISH))) {
         return "\n\r";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("Comma".toUpperCase(Locale.ENGLISH))) {
         return ",";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("Semicolon".toUpperCase(Locale.ENGLISH))) {
         return ";";
      } else {
         var1 = this.commonToFieldAndRecordDelimiters(var1, "\\n", '\n');
         var1 = this.commonToFieldAndRecordDelimiters(var1, "\\t", '\t');
         var1 = this.commonToFieldAndRecordDelimiters(var1, "\\r", '\r');
         var1 = this.commonToFieldAndRecordDelimiters(var1, "\\f", '\f');
         return var1;
      }
   }

   private String commonToFieldAndRecordDelimiters(String var1, String var2, char var3) {
      while(var1.indexOf(var2) != -1) {
         int var6 = var1.indexOf(var2);
         String var4 = var1.substring(0, var6);
         String var5 = var1.substring(var6 + 2);
         var1 = var4 + var3 + var5;
      }

      return var1;
   }

   private String mapFromUserFriendlyRecordDelimiters(String var1) {
      if (var1.equals("\n")) {
         var1 = "New Line";
      }

      if (var1.toUpperCase(Locale.ENGLISH).equals("New Line".toUpperCase(Locale.ENGLISH))) {
         return "\n";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("CR".toUpperCase(Locale.ENGLISH))) {
         return "\r";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("LF".toUpperCase(Locale.ENGLISH))) {
         return "\n";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("CR-LF".toUpperCase(Locale.ENGLISH))) {
         return "\r\n";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("LF-CR".toUpperCase(Locale.ENGLISH))) {
         return "\n\r";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("FF".toUpperCase(Locale.ENGLISH))) {
         return "\f";
      } else if (var1.toUpperCase(Locale.ENGLISH).equals("Empty line".toUpperCase(Locale.ENGLISH))) {
         return "\n\n";
      } else {
         var1 = this.commonToFieldAndRecordDelimiters(var1, "\\n", '\n');
         var1 = this.commonToFieldAndRecordDelimiters(var1, "\\t", '\t');
         var1 = this.commonToFieldAndRecordDelimiters(var1, "\\r", '\r');
         var1 = this.commonToFieldAndRecordDelimiters(var1, "\\f", '\f');
         return var1;
      }
   }

   String getDataCodeset() throws Exception {
      return this.getCurrentProperties().getProperty("DataCodeset");
   }

   Properties getCurrentProperties() throws Exception {
      if (this.currentProperties == null) {
         this.loadDefaultValues();
      }

      return this.currentProperties;
   }

   public void setColumnWidths(String var1) throws Exception {
      if (var1 != null) {
         this.currentProperties.setProperty("ColumnWidths", var1);
      }

   }

   public void setFieldSeparator(String var1) throws Exception {
      if (var1 != null) {
         this.currentProperties.setProperty("FieldSeparator", var1);
      }

   }

   public void setFieldStartDelimiter(String var1) throws Exception {
      if (var1 != null) {
         this.currentProperties.setProperty("FieldStartDelimiter", var1);
      }

   }

   public void setFieldEndDelimiter(String var1) throws Exception {
      if (var1 != null) {
         this.currentProperties.setProperty("FieldEndDelimiter", var1);
      }

   }

   public void setRecordSeparator(String var1) throws Exception {
      if (var1 != null) {
         this.currentProperties.setProperty("RecordSeparator", var1);
      }

   }

   public void setHasDelimiterAtEnd(String var1) throws Exception {
      if (var1 != null) {
         this.currentProperties.setProperty("HasDelimeterAtEnd", var1);
      }

   }

   public void setNullString(String var1) throws Exception {
      if (var1 != null) {
         this.currentProperties.setProperty("Null", var1);
      }

   }

   public void setcolumnDefinition(String var1) throws Exception {
      if (var1 != null) {
         this.currentProperties.setProperty("ColumnDefinition", var1);
      }

   }

   public void setDataCodeset(String var1) throws Exception {
      if (var1 != null) {
         this.currentProperties.setProperty("DataCodeset", var1);
      }

   }

   public void setCharacterDelimiter(String var1) throws Exception {
      if (var1 != null) {
         this.setFieldStartDelimiter(var1);
         this.setFieldEndDelimiter(var1);
      }

   }

   public void setControlProperties(String var1, String var2, String var3) throws Exception {
      this.setCharacterDelimiter(var1);
      this.setFieldSeparator(var2);
      this.setDataCodeset(var3);
      this.validateDelimiters();
   }

   private void validateDelimiters() throws Exception {
      char var1 = this.getFieldSeparator().charAt(0);
      char var2 = this.getFieldStartDelimiter().charAt(0);
      if (var2 == '.') {
         throw LoadError.periodAsCharDelimiterNotAllowed();
      } else if (var1 == var2 || var1 == '.' || Character.isSpaceChar(var1) || Character.isSpaceChar(var2) || Character.digit(var1, 16) != -1 || Character.digit(var2, 16) != -1) {
         throw LoadError.delimitersAreNotMutuallyExclusive();
      }
   }
}
