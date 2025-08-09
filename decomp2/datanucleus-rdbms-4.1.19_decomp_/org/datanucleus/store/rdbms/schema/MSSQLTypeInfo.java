package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class MSSQLTypeInfo extends SQLTypeInfo {
   public static final int NVARCHAR = -9;
   public static final int NTEXT = -10;
   public static final int UNIQUEIDENTIFIER = -11;

   public MSSQLTypeInfo(ResultSet rs) {
      super(rs);
      if (this.typeName.equalsIgnoreCase("uniqueidentifier")) {
         this.allowsPrecisionSpec = false;
      }

   }

   public MSSQLTypeInfo(String typeName, short dataType, int precision, String literalPrefix, String literalSuffix, String createParams, int nullable, boolean caseSensitive, short searchable, boolean unsignedAttribute, boolean fixedPrecScale, boolean autoIncrement, String localTypeName, short minimumScale, short maximumScale, int numPrecRadix) {
      super(typeName, dataType, precision, literalPrefix, literalSuffix, createParams, nullable, caseSensitive, searchable, unsignedAttribute, fixedPrecScale, autoIncrement, localTypeName, minimumScale, maximumScale, numPrecRadix);
   }

   public boolean isCompatibleWith(RDBMSColumnInfo colInfo) {
      if (super.isCompatibleWith(colInfo)) {
         return true;
      } else {
         short colDataType = colInfo.getDataType();
         switch (this.dataType) {
            case -11:
            case -4:
            case -3:
               return colDataType == -3 || colDataType == -4 || colDataType == -11;
            case -1:
               return colDataType == -10;
            case 12:
               return colDataType == -9;
            default:
               return false;
         }
      }
   }
}
