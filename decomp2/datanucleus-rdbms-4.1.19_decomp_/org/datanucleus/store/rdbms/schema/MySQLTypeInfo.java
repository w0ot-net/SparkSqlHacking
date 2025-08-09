package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class MySQLTypeInfo extends SQLTypeInfo {
   public MySQLTypeInfo(ResultSet rs) {
      super(rs);
      if (this.typeName.equalsIgnoreCase("FLOAT")) {
         this.dataType = 6;
      } else if (this.typeName.equalsIgnoreCase("CHAR")) {
         this.typeName = "CHAR(M) BINARY";
         this.createParams = "";
      } else if (this.typeName.equalsIgnoreCase("VARCHAR")) {
         this.typeName = "VARCHAR(M) BINARY";
         this.createParams = "";
      }

      this.fixAllowsPrecisionSpec();
   }

   public MySQLTypeInfo(String typeName, short dataType, int precision, String literalPrefix, String literalSuffix, String createParams, int nullable, boolean caseSensitive, short searchable, boolean unsignedAttribute, boolean fixedPrecScale, boolean autoIncrement, String localTypeName, short minimumScale, short maximumScale, int numPrecRadix) {
      super(typeName, dataType, precision, literalPrefix, literalSuffix, createParams, nullable, caseSensitive, searchable, unsignedAttribute, fixedPrecScale, autoIncrement, localTypeName, minimumScale, maximumScale, numPrecRadix);
      this.fixAllowsPrecisionSpec();
   }

   private void fixAllowsPrecisionSpec() {
      if (this.typeName.equalsIgnoreCase("LONG VARCHAR") || this.typeName.equalsIgnoreCase("BLOB") || this.typeName.equalsIgnoreCase("MEDIUMBLOB") || this.typeName.equalsIgnoreCase("LONGBLOB") || this.typeName.equalsIgnoreCase("MEDIUMTEXT") || this.typeName.equalsIgnoreCase("LONGTEXT") || this.typeName.equalsIgnoreCase("TEXT")) {
         this.allowsPrecisionSpec = false;
      }

   }

   public boolean isCompatibleWith(RDBMSColumnInfo colInfo) {
      if (super.isCompatibleWith(colInfo)) {
         return true;
      } else {
         short colDataType = colInfo.getDataType();
         if (isStringType(this.dataType) && isStringType(colDataType)) {
            return true;
         } else if (this.dataType == -7) {
            int colSize = colInfo.getColumnSize();
            return colDataType == -6 && colSize == 1;
         } else if ((this.dataType != 2004 || colDataType != -4) && (this.dataType != -4 || colDataType != 2004)) {
            return this.dataType == 2005 && colDataType == -1 || this.dataType == -1 && colDataType == 2005;
         } else {
            return true;
         }
      }
   }

   private static boolean isStringType(int type) {
      switch (type) {
         case -4:
         case -3:
         case -2:
         case -1:
         case 1:
         case 12:
            return true;
         case 0:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         default:
            return false;
      }
   }
}
