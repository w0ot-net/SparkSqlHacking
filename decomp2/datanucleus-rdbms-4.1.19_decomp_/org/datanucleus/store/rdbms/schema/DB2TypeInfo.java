package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class DB2TypeInfo extends SQLTypeInfo {
   public static final int DATALINK = 70;

   public DB2TypeInfo(ResultSet rs) {
      super(rs);
      if (this.typeName.equalsIgnoreCase("DATALINK")) {
         this.createParams = "";
      }

   }

   public DB2TypeInfo(String typeName, short dataType, int precision, String literalPrefix, String literalSuffix, String createParams, int nullable, boolean caseSensitive, short searchable, boolean unsignedAttribute, boolean fixedPrecScale, boolean autoIncrement, String localTypeName, short minimumScale, short maximumScale, int numPrecRadix) {
      super(typeName, dataType, precision, literalPrefix, literalSuffix, createParams, nullable, caseSensitive, searchable, unsignedAttribute, fixedPrecScale, autoIncrement, localTypeName, minimumScale, maximumScale, numPrecRadix);
   }
}
