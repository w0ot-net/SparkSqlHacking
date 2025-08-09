package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;

public class BooleanTypeCompiler extends BaseTypeCompiler {
   public boolean convertible(TypeId var1, boolean var2) {
      return var1.isStringTypeId() || var1.isBooleanTypeId();
   }

   public boolean compatible(TypeId var1) {
      return this.convertible(var1, false);
   }

   public boolean storable(TypeId var1, ClassFactory var2) {
      return !var1.isBooleanTypeId() && !var1.isStringTypeId() ? this.userTypeStorable(this.getTypeId(), var1, var2) : true;
   }

   public String interfaceName() {
      return "org.apache.derby.iapi.types.BooleanDataValue";
   }

   public String getCorrespondingPrimitiveTypeName() {
      return "boolean";
   }

   public String getPrimitiveMethodName() {
      return "getBoolean";
   }

   public int getCastToCharWidth(DataTypeDescriptor var1) {
      return 5;
   }

   String nullMethodName() {
      return "getNullBoolean";
   }
}
