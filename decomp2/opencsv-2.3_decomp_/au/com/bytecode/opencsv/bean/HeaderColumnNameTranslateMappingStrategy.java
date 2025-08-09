package au.com.bytecode.opencsv.bean;

import java.util.HashMap;
import java.util.Map;

public class HeaderColumnNameTranslateMappingStrategy extends HeaderColumnNameMappingStrategy {
   private Map columnMapping = new HashMap();

   protected String getColumnName(int col) {
      return col < this.header.length ? (String)this.columnMapping.get(this.header[col].toUpperCase()) : null;
   }

   public Map getColumnMapping() {
      return this.columnMapping;
   }

   public void setColumnMapping(Map columnMapping) {
      for(String key : columnMapping.keySet()) {
         this.columnMapping.put(key.toUpperCase(), columnMapping.get(key));
      }

   }
}
