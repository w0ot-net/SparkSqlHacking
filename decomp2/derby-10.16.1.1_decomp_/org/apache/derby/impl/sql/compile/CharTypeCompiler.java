package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;

public final class CharTypeCompiler extends BaseTypeCompiler {
   public boolean convertible(TypeId var1, boolean var2) {
      if (var1.getBaseTypeId().isAnsiUDT()) {
         return false;
      } else if (!this.getTypeId().isLongVarcharTypeId()) {
         if (var2 && var1.isDoubleTypeId()) {
            return this.getTypeId().isStringTypeId();
         } else {
            return !var1.isFloatingPointTypeId() && !var1.isBitTypeId() && !var1.isBlobTypeId() && !var1.isXMLTypeId();
         }
      } else {
         return var1.isStringTypeId() || var1.isBooleanTypeId();
      }
   }

   public boolean compatible(TypeId var1) {
      return var1.isStringTypeId() || var1.isDateTimeTimeStampTypeId() && !this.getTypeId().isLongVarcharTypeId();
   }

   public boolean storable(TypeId var1, ClassFactory var2) {
      return this.convertible(var1, false) && !var1.isBlobTypeId() && !var1.isNumericTypeId() ? true : this.userTypeStorable(this.getTypeId(), var1, var2);
   }

   public String interfaceName() {
      return "org.apache.derby.iapi.types.StringDataValue";
   }

   public String getCorrespondingPrimitiveTypeName() {
      return "java.lang.String";
   }

   public int getCastToCharWidth(DataTypeDescriptor var1) {
      return var1.getMaximumWidth();
   }

   String nullMethodName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 5 -> {
            return "getNullChar";
         }
         case 13 -> {
            return "getNullVarchar";
         }
         case 230 -> {
            return "getNullLongvarchar";
         }
         default -> {
            return null;
         }
      }
   }

   boolean pushCollationForDataValue(int var1) {
      return var1 != 0;
   }

   String dataValueMethodName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 5 -> {
            return "getCharDataValue";
         }
         case 13 -> {
            return "getVarcharDataValue";
         }
         case 230 -> {
            return "getLongvarcharDataValue";
         }
         default -> {
            return null;
         }
      }
   }
}
