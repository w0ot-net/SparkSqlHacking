package au.com.bytecode.opencsv.bean;

import [Ljava.lang.String;;
import au.com.bytecode.opencsv.CSVReader;
import java.io.IOException;

public class ColumnPositionMappingStrategy extends HeaderColumnNameMappingStrategy {
   private String[] columnMapping = new String[0];

   public void captureHeader(CSVReader reader) throws IOException {
   }

   protected String getColumnName(int col) {
      return null != this.columnMapping && col < this.columnMapping.length ? this.columnMapping[col] : null;
   }

   public String[] getColumnMapping() {
      return this.columnMapping != null ? (String[])this.columnMapping.clone() : null;
   }

   public void setColumnMapping(String[] columnMapping) {
      this.columnMapping = columnMapping != null ? (String[])((String;)columnMapping).clone() : null;
   }
}
