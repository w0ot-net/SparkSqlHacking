package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class SQLiteTypeInfo extends SQLTypeInfo {
   public SQLiteTypeInfo(ResultSet rs) {
      super(rs);
   }

   public SQLiteTypeInfo(String typeName, short dataType, int precision, String literalPrefix, String literalSuffix, String createParams, int nullable, boolean caseSensitive, short searchable, boolean unsignedAttribute, boolean fixedPrecScale, boolean autoIncrement, String localTypeName, short minimumScale, short maximumScale, int numPrecRadix) {
      super(typeName, dataType, precision, literalPrefix, literalSuffix, createParams, nullable, caseSensitive, searchable, unsignedAttribute, fixedPrecScale, autoIncrement, localTypeName, minimumScale, maximumScale, numPrecRadix);
   }

   public boolean isCompatibleWith(RDBMSColumnInfo colInfo) {
      return super.isCompatibleWith(colInfo);
   }
}
