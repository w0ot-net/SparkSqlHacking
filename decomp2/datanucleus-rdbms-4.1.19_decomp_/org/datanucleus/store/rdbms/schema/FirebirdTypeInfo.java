package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class FirebirdTypeInfo extends SQLTypeInfo {
   public static final int MAX_PRECISION_DECIMAL = 18;

   public FirebirdTypeInfo(ResultSet rs) {
      super(rs);
      if (this.typeName.equalsIgnoreCase("decimal")) {
         this.precision = 18;
      }

   }
}
