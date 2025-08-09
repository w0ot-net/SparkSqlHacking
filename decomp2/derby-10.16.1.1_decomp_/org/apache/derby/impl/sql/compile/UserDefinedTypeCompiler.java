package org.apache.derby.impl.sql.compile;

import org.apache.derby.catalog.types.UserDefinedTypeIdImpl;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;

public class UserDefinedTypeCompiler extends BaseTypeCompiler {
   public boolean convertible(TypeId var1, boolean var2) {
      if (this.getTypeId().getBaseTypeId().isAnsiUDT()) {
         if (!var1.getBaseTypeId().isAnsiUDT()) {
            return false;
         } else {
            UserDefinedTypeIdImpl var3 = (UserDefinedTypeIdImpl)this.getTypeId().getBaseTypeId();
            UserDefinedTypeIdImpl var4 = (UserDefinedTypeIdImpl)var1.getBaseTypeId();
            return var3.getSQLTypeName().equals(var4.getSQLTypeName());
         }
      } else {
         return true;
      }
   }

   public boolean compatible(TypeId var1) {
      return this.convertible(var1, false);
   }

   public boolean storable(TypeId var1, ClassFactory var2) {
      if (!var1.isUserDefinedTypeId()) {
         return false;
      } else {
         UserDefinedTypeIdImpl var3 = (UserDefinedTypeIdImpl)this.getTypeId().getBaseTypeId();
         UserDefinedTypeIdImpl var4 = (UserDefinedTypeIdImpl)var1.getBaseTypeId();
         if (var3.isAnsiUDT() != var4.isAnsiUDT()) {
            return false;
         } else {
            return var3.isAnsiUDT() ? var3.getSQLTypeName().equals(var4.getSQLTypeName()) : var2.getClassInspector().assignableTo(var1.getCorrespondingJavaTypeName(), this.getTypeId().getCorrespondingJavaTypeName());
         }
      }
   }

   public String interfaceName() {
      return "org.apache.derby.iapi.types.UserDataValue";
   }

   public String getCorrespondingPrimitiveTypeName() {
      return this.getTypeId().getCorrespondingJavaTypeName();
   }

   public int getCastToCharWidth(DataTypeDescriptor var1) {
      return -1;
   }

   String nullMethodName() {
      return "getNullObject";
   }

   public void generateDataValue(MethodBuilder var1, int var2, LocalField var3) {
      var1.upCast("java.lang.Object");
      super.generateDataValue(var1, var2, var3);
   }
}
