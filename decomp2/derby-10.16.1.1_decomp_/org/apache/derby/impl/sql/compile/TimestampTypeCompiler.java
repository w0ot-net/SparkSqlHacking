package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;

public class TimestampTypeCompiler extends BaseTypeCompiler {
   public boolean convertible(TypeId var1, boolean var2) {
      if (var1.isStringTypeId() && !var1.isLongConcatableTypeId()) {
         return true;
      } else {
         int var3 = var1.getJDBCTypeId();
         if (var3 == 93) {
            return true;
         } else {
            return var3 == 91 || var3 == 92;
         }
      }
   }

   public boolean compatible(TypeId var1) {
      if (var1.isStringTypeId() && !var1.isLongConcatableTypeId()) {
         return true;
      } else {
         return this.getStoredFormatIdFromTypeId() == var1.getTypeFormatId();
      }
   }

   public boolean storable(TypeId var1, ClassFactory var2) {
      int var3 = var1.getJDBCTypeId();
      return var3 != 93 && var3 != 1 && var3 != 12 ? var2.getClassInspector().assignableTo(var1.getCorrespondingJavaTypeName(), "java.sql.Timestamp") : true;
   }

   public String interfaceName() {
      return "org.apache.derby.iapi.types.DateTimeDataValue";
   }

   public String getCorrespondingPrimitiveTypeName() {
      return "java.sql.Timestamp";
   }

   public int getCastToCharWidth(DataTypeDescriptor var1) {
      return 26;
   }

   public double estimatedMemoryUsage(DataTypeDescriptor var1) {
      return (double)12.0F;
   }

   String nullMethodName() {
      return "getNullTimestamp";
   }
}
