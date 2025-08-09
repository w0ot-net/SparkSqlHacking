package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class NumericTypeCompiler extends BaseTypeCompiler {
   public String interfaceName() {
      return "org.apache.derby.iapi.types.NumberDataValue";
   }

   public String getCorrespondingPrimitiveTypeName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 6:
            return "double";
         case 7:
            return "int";
         case 8:
            return "float";
         case 10:
            return "short";
         case 11:
            return "long";
         case 195:
            return "byte";
         case 197:
         default:
            return null;
      }
   }

   public String getPrimitiveMethodName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 6:
            return "getDouble";
         case 7:
            return "getInt";
         case 8:
            return "getFloat";
         case 10:
            return "getShort";
         case 11:
            return "getLong";
         case 195:
            return "getByte";
         case 197:
         default:
            return null;
      }
   }

   public int getCastToCharWidth(DataTypeDescriptor var1) {
      int var2 = this.getStoredFormatIdFromTypeId();
      switch (var2) {
         case 6 -> {
            return 54;
         }
         case 7 -> {
            return 11;
         }
         case 8 -> {
            return 25;
         }
         case 10 -> {
            return 6;
         }
         case 11 -> {
            return 20;
         }
         case 195 -> {
            return 4;
         }
         case 197 -> {
            return var1.getPrecision() + 2;
         }
         default -> {
            return 0;
         }
      }
   }

   public DataTypeDescriptor resolveArithmeticOperation(DataTypeDescriptor var1, DataTypeDescriptor var2, String var3) throws StandardException {
      TypeId var10 = var1.getTypeId();
      TypeId var11 = var2.getTypeId();
      boolean var12 = true;
      if (!var11.isNumericTypeId()) {
         var12 = false;
      }

      if ("mod".equals(var3)) {
         switch (var10.getJDBCTypeId()) {
            default:
               var12 = false;
            case -6:
            case -5:
            case 4:
            case 5:
               switch (var11.getJDBCTypeId()) {
                  case -6:
                  case -5:
                  case 4:
                  case 5:
                     break;
                  default:
                     var12 = false;
               }
         }
      }

      if (!var12) {
         throw StandardException.newException("42Y95", new Object[]{var3, var1.getTypeId().getSQLTypeName(), var2.getTypeId().getSQLTypeName()});
      } else {
         NumericTypeCompiler var4;
         DataTypeDescriptor var5;
         if (var11.typePrecedence() > var10.typePrecedence()) {
            var5 = var2;
            var4 = (NumericTypeCompiler)this.getTypeCompiler(var11);
         } else {
            var5 = var1;
            var4 = (NumericTypeCompiler)this.getTypeCompiler(var10);
         }

         int var7 = var4.getPrecision(var3, var1, var2);
         int var8;
         int var9;
         if (var5.getTypeId().isDecimalTypeId()) {
            var8 = var4.getScale(var3, var1, var2);
            var9 = var8 > 0 ? var7 + 3 : var7 + 1;
            if (var9 < var7) {
               var9 = Integer.MAX_VALUE;
            }
         } else {
            var8 = 0;
            var9 = var5.getMaximumWidth();
         }

         boolean var6 = var1.isNullable() || var2.isNullable();
         return new DataTypeDescriptor(var5.getTypeId(), var7, var8, var6, var9);
      }
   }

   public boolean convertible(TypeId var1, boolean var2) {
      return this.numberConvertible(var1, var2);
   }

   public boolean compatible(TypeId var1) {
      return var1.isNumericTypeId();
   }

   public boolean storable(TypeId var1, ClassFactory var2) {
      return this.numberStorable(this.getTypeId(), var1, var2);
   }

   String dataValueMethodName() {
      return this.getStoredFormatIdFromTypeId() == 197 ? "getDecimalDataValue" : super.dataValueMethodName();
   }

   String nullMethodName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      switch (var1) {
         case 6 -> {
            return "getNullDouble";
         }
         case 7 -> {
            return "getNullInteger";
         }
         case 8 -> {
            return "getNullFloat";
         }
         case 10 -> {
            return "getNullShort";
         }
         case 11 -> {
            return "getNullLong";
         }
         case 195 -> {
            return "getNullByte";
         }
         case 197 -> {
            return "getNullDecimal";
         }
         default -> {
            return null;
         }
      }
   }

   private int getPrecision(String var1, DataTypeDescriptor var2, DataTypeDescriptor var3) {
      if (this.getStoredFormatIdFromTypeId() != 197) {
         return var2.getPrecision();
      } else {
         long var4 = (long)var2.getScale();
         long var6 = (long)var3.getScale();
         long var8 = (long)var2.getPrecision();
         long var10 = (long)var3.getPrecision();
         long var12;
         if (var1 == null) {
            var12 = (long)this.getScale(var1, var2, var3) + Math.max(var8 - var4, var10 - var6);
         } else if (var1.equals("*")) {
            var12 = var8 + var10;
         } else if (var1.equals("sum")) {
            var12 = var8 - var4 + var10 - var6 + (long)this.getScale(var1, var2, var3);
         } else if (var1.equals("/")) {
            var12 = Math.min(31L, (long)this.getScale(var1, var2, var3) + var8 - var4 + var10);
         } else {
            var12 = (long)this.getScale(var1, var2, var3) + Math.max(var8 - var4, var10 - var6) + 1L;
            if (var12 > 31L) {
               var12 = 31L;
            }
         }

         if (var12 > 2147483647L) {
            var12 = 2147483647L;
         }

         var12 = Math.min(31L, var12);
         return (int)var12;
      }
   }

   private int getScale(String var1, DataTypeDescriptor var2, DataTypeDescriptor var3) {
      long var6 = (long)var2.getScale();
      long var8 = (long)var3.getScale();
      long var10 = (long)var2.getPrecision();
      long var4;
      if ("*".equals(var1)) {
         var4 = var6 + var8;
      } else if ("/".equals(var1)) {
         var4 = Math.max(31L - var10 + var6 - var8, 0L);
      } else if ("avg".equals(var1)) {
         var4 = Math.max(Math.max(var6, var8), 4L);
      } else {
         var4 = Math.max(var6, var8);
      }

      if (var4 > 2147483647L) {
         var4 = 2147483647L;
      }

      var4 = Math.min(31L, var4);
      return (int)var4;
   }

   public void generateDataValue(MethodBuilder var1, int var2, LocalField var3) {
      if (this.getTypeId().isDecimalTypeId()) {
         var1.upCast("java.lang.Number");
      }

      super.generateDataValue(var1, var2, var3);
   }
}
