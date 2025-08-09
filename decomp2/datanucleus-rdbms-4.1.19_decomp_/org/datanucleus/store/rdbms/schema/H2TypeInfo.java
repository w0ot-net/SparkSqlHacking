package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class H2TypeInfo extends SQLTypeInfo {
   public H2TypeInfo(ResultSet rs) {
      super(rs);
   }

   public boolean isCompatibleWith(RDBMSColumnInfo colInfo) {
      if (super.isCompatibleWith(colInfo)) {
         return true;
      } else {
         short colDataType = colInfo.getDataType();
         return this.dataType == 1 && colDataType == 12 || this.dataType == 12 && colDataType == 1;
      }
   }
}
