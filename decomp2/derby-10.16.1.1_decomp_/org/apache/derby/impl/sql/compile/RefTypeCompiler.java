package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;

public class RefTypeCompiler extends BaseTypeCompiler {
   public String getCorrespondingPrimitiveTypeName() {
      return null;
   }

   public int getCastToCharWidth(DataTypeDescriptor var1) {
      return 0;
   }

   public boolean convertible(TypeId var1, boolean var2) {
      return false;
   }

   public boolean compatible(TypeId var1) {
      return this.convertible(var1, false);
   }

   public boolean storable(TypeId var1, ClassFactory var2) {
      return var1.isRefTypeId();
   }

   public String interfaceName() {
      return "org.apache.derby.iapi.types.RefDataValue";
   }

   String nullMethodName() {
      return "getNullRef";
   }
}
