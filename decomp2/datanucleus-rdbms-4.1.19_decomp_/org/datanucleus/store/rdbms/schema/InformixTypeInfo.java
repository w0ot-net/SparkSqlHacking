package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class InformixTypeInfo extends SQLTypeInfo {
   public InformixTypeInfo(ResultSet rs) {
      super(rs);
      if (this.dataType == 12) {
         this.precision = 255;
         this.typeName = "VARCHAR";
      }

   }
}
