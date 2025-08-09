package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;

public class CLOBTypeCompiler extends BaseTypeCompiler {
   public boolean convertible(TypeId var1, boolean var2) {
      return var1.isStringTypeId() || var1.isBooleanTypeId();
   }

   public boolean compatible(TypeId var1) {
      return this.convertible(var1, false);
   }

   public boolean storable(TypeId var1, ClassFactory var2) {
      return var1.isStringTypeId() || var1.isBooleanTypeId();
   }

   public String interfaceName() {
      return "org.apache.derby.iapi.types.StringDataValue";
   }

   public String getCorrespondingPrimitiveTypeName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 444 -> {
            return "java.sql.Clob";
         }
         default -> {
            return null;
         }
      }
   }

   public int getCastToCharWidth(DataTypeDescriptor var1) {
      return var1.getMaximumWidth();
   }

   String nullMethodName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 444 -> {
            return "getNullClob";
         }
         default -> {
            return null;
         }
      }
   }

   String dataValueMethodName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 444 -> {
            return "getClobDataValue";
         }
         default -> {
            return null;
         }
      }
   }

   boolean pushCollationForDataValue(int var1) {
      return var1 != 0;
   }
}
