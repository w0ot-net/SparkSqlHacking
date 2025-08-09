package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;

public class LOBTypeCompiler extends BaseTypeCompiler {
   public boolean convertible(TypeId var1, boolean var2) {
      return var1.isBlobTypeId();
   }

   public boolean compatible(TypeId var1) {
      return this.convertible(var1, false);
   }

   public boolean storable(TypeId var1, ClassFactory var2) {
      return var1.isBlobTypeId();
   }

   public String interfaceName() {
      return "org.apache.derby.iapi.types.BitDataValue";
   }

   public String getCorrespondingPrimitiveTypeName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 440 -> {
            return "java.sql.Blob";
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
         case 440 -> {
            return "getNullBlob";
         }
         default -> {
            return null;
         }
      }
   }

   String dataValueMethodName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 440 -> {
            return "getBlobDataValue";
         }
         default -> {
            return null;
         }
      }
   }
}
