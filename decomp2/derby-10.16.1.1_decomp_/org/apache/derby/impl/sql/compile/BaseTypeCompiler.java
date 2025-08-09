package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

abstract class BaseTypeCompiler implements TypeCompiler {
   private TypeId correspondingTypeId;

   public String getPrimitiveMethodName() {
      return null;
   }

   public DataTypeDescriptor resolveArithmeticOperation(DataTypeDescriptor var1, DataTypeDescriptor var2, String var3) throws StandardException {
      throw StandardException.newException("42Y95", new Object[]{var3, var1.getTypeId().getSQLTypeName(), var2.getTypeId().getSQLTypeName()});
   }

   public void generateNull(MethodBuilder var1, int var2) {
      byte var3;
      if (this.pushCollationForDataValue(var2)) {
         var1.push(var2);
         var3 = 2;
      } else {
         var3 = 1;
      }

      var1.callMethod((short)185, (String)null, this.nullMethodName(), this.interfaceName(), var3);
   }

   public void generateDataValue(MethodBuilder var1, int var2, LocalField var3) {
      String var4 = this.interfaceName();
      if (var3 == null) {
         var1.pushNull(var4);
      } else {
         var1.getField(var3);
      }

      byte var5;
      if (this.pushCollationForDataValue(var2)) {
         var1.push(var2);
         var5 = 3;
      } else {
         var5 = 2;
      }

      var1.callMethod((short)185, (String)null, this.dataValueMethodName(), var4, var5);
      if (var3 != null) {
         var1.putField(var3);
      }

   }

   abstract String nullMethodName();

   String dataValueMethodName() {
      return "getDataValue";
   }

   boolean pushCollationForDataValue(int var1) {
      return false;
   }

   protected boolean userTypeStorable(TypeId var1, TypeId var2, ClassFactory var3) {
      return var2.userType() ? var3.getClassInspector().assignableTo(var1.getCorrespondingJavaTypeName(), var2.getCorrespondingJavaTypeName()) : false;
   }

   boolean numberConvertible(TypeId var1, boolean var2) {
      if (var1.getBaseTypeId().isAnsiUDT()) {
         return false;
      } else if (var1.isLongConcatableTypeId()) {
         return false;
      } else {
         boolean var3 = var1.isNumericTypeId() || var1.userType();
         if (var2) {
            var3 = var3 || var1.isFixedStringTypeId() && this.getTypeId().isFloatingPointTypeId();
         }

         var3 = var3 || var1.isFixedStringTypeId() && !this.getTypeId().isFloatingPointTypeId();
         return var3;
      }
   }

   boolean numberStorable(TypeId var1, TypeId var2, ClassFactory var3) {
      if (var2.getBaseTypeId().isAnsiUDT()) {
         return false;
      } else {
         return var2.isNumericTypeId() ? true : this.userTypeStorable(var1, var2, var3);
      }
   }

   protected TypeId getTypeId() {
      return this.correspondingTypeId;
   }

   protected TypeCompiler getTypeCompiler(TypeId var1) {
      return TypeCompilerFactoryImpl.staticGetTypeCompiler(var1);
   }

   void setTypeId(TypeId var1) {
      this.correspondingTypeId = var1;
   }

   protected int getStoredFormatIdFromTypeId() {
      return this.getTypeId().getTypeFormatId();
   }
}
