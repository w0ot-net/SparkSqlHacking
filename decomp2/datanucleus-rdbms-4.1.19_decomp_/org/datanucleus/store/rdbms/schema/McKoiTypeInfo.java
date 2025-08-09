package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class McKoiTypeInfo extends SQLTypeInfo {
   public static final int MAX_PRECISION = Integer.MAX_VALUE;

   public McKoiTypeInfo(ResultSet rs) {
      super(rs);
      if (this.typeName.equalsIgnoreCase("varchar") || this.typeName.equalsIgnoreCase("char")) {
         this.precision = Integer.MAX_VALUE;
      }

      if (this.precision > Integer.MAX_VALUE) {
         this.precision = Integer.MAX_VALUE;
      }

   }
}
