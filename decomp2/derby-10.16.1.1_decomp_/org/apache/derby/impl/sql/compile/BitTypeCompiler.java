package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;

public class BitTypeCompiler extends BaseTypeCompiler {
   public boolean convertible(TypeId var1, boolean var2) {
      if (var1.getBaseTypeId().isAnsiUDT()) {
         return false;
      } else {
         return var1.isBitTypeId() || var1.isBlobTypeId() || var1.userType();
      }
   }

   public boolean compatible(TypeId var1) {
      return var1.isBlobTypeId() ? false : var1.isBitTypeId();
   }

   public boolean storable(TypeId var1, ClassFactory var2) {
      if (var1.isBlobTypeId()) {
         return false;
      } else {
         return var1.isBitTypeId() ? true : this.userTypeStorable(this.getTypeId(), var1, var2);
      }
   }

   public String interfaceName() {
      return "org.apache.derby.iapi.types.BitDataValue";
   }

   public String getCorrespondingPrimitiveTypeName() {
      return "byte[]";
   }

   public int getCastToCharWidth(DataTypeDescriptor var1) {
      return var1.getMaximumWidth();
   }

   String nullMethodName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 27 -> {
            return "getNullBit";
         }
         case 29 -> {
            return "getNullVarbit";
         }
         case 232 -> {
            return "getNullLongVarbit";
         }
         default -> {
            return null;
         }
      }
   }

   String dataValueMethodName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 27 -> {
            return "getBitDataValue";
         }
         case 29 -> {
            return "getVarbitDataValue";
         }
         case 232 -> {
            return "getLongVarbitDataValue";
         }
         default -> {
            return null;
         }
      }
   }
}
