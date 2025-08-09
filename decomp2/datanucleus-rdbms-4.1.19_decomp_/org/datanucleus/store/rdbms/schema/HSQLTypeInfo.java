package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class HSQLTypeInfo extends SQLTypeInfo {
   public static final int MAX_PRECISION = Integer.MAX_VALUE;

   public HSQLTypeInfo(ResultSet rs) {
      super(rs);
      if (!this.typeName.equalsIgnoreCase("varchar") && !this.typeName.equalsIgnoreCase("char")) {
         if (this.typeName.equalsIgnoreCase("numeric")) {
            this.precision = Integer.MAX_VALUE;
         } else if (this.typeName.equalsIgnoreCase("text")) {
            this.dataType = -1;
         }
      } else {
         this.precision = Integer.MAX_VALUE;
      }

      if (this.precision > Integer.MAX_VALUE) {
         this.precision = Integer.MAX_VALUE;
      }

   }

   public HSQLTypeInfo(String typeName, short dataType, int precision, String literalPrefix, String literalSuffix, String createParams, int nullable, boolean caseSensitive, short searchable, boolean unsignedAttribute, boolean fixedPrecScale, boolean autoIncrement, String localTypeName, short minimumScale, short maximumScale, int numPrecRadix) {
      super(typeName, dataType, precision, literalPrefix, literalSuffix, createParams, nullable, caseSensitive, searchable, unsignedAttribute, fixedPrecScale, autoIncrement, localTypeName, minimumScale, maximumScale, numPrecRadix);
   }

   public boolean isCompatibleWith(RDBMSColumnInfo colInfo) {
      if (super.isCompatibleWith(colInfo)) {
         return true;
      } else {
         short colDataType = colInfo.getDataType();
         if ((this.dataType != 2005 || colDataType != -1) && (this.dataType != -1 || colDataType != 2005)) {
            return this.dataType == 2004 && colDataType == -4 || this.dataType == -4 && colDataType == 2004;
         } else {
            return true;
         }
      }
   }
}
