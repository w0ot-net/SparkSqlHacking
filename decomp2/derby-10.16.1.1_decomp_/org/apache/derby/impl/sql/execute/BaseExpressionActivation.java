package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.types.BaseTypeIdImpl;
import org.apache.derby.catalog.types.UserDefinedTypeIdImpl;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public abstract class BaseExpressionActivation {
   BaseExpressionActivation() {
   }

   public static DataValueDescriptor minValue(DataValueDescriptor var0, DataValueDescriptor var1, DataValueDescriptor var2, DataValueDescriptor var3, int var4, int var5, int var6, int var7, boolean var8, int var9, int var10, int var11) throws StandardException {
      DataValueDescriptor var12;
      if (var5 == -1) {
         var12 = (new DataTypeDescriptor(new TypeId(var4, (BaseTypeIdImpl)null), var6, var7, var8, var9, var10, var11)).getNull();
      } else {
         var12 = (new TypeId(var4, new UserDefinedTypeIdImpl())).getNull();
      }

      DataValueDescriptor var13 = var0;
      if (var1 != null && (var0.isNull() || var12.lessThan(var1, var0).equals(true))) {
         var13 = var1;
      }

      if (var2 != null && (var13.isNull() || var12.lessThan(var2, var13).equals(true))) {
         var13 = var2;
      }

      if (var3 != null && (var13.isNull() || var12.lessThan(var3, var13).equals(true))) {
         var13 = var3;
      }

      return var13;
   }

   public static DataValueDescriptor maxValue(DataValueDescriptor var0, DataValueDescriptor var1, DataValueDescriptor var2, DataValueDescriptor var3, int var4, int var5, int var6, int var7, boolean var8, int var9, int var10, int var11) throws StandardException {
      DataValueDescriptor var12;
      if (var5 == -1) {
         var12 = (new DataTypeDescriptor(new TypeId(var4, (BaseTypeIdImpl)null), var6, var7, var8, var9, var10, var11)).getNull();
      } else {
         var12 = (new TypeId(var4, new UserDefinedTypeIdImpl())).getNull();
      }

      DataValueDescriptor var13 = var0;
      if (var1 != null && (var0.isNull() || var12.greaterThan(var1, var0).equals(true))) {
         var13 = var1;
      }

      if (var2 != null && (var13.isNull() || var12.greaterThan(var2, var13).equals(true))) {
         var13 = var2;
      }

      if (var3 != null && (var13.isNull() || var12.greaterThan(var3, var13).equals(true))) {
         var13 = var3;
      }

      return var13;
   }
}
