package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class DerbyTypeInfo extends SQLTypeInfo {
   public DerbyTypeInfo(ResultSet rs) {
      super(rs);
      if (this.typeName.equalsIgnoreCase("DOUBLE")) {
         this.allowsPrecisionSpec = false;
      }

   }
}
