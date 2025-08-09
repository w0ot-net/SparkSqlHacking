package org.apache.derby.impl.sql.catalog;

import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class SystemColumnImpl implements SystemColumn {
   private final String name;
   private final DataTypeDescriptor type;

   static SystemColumn getColumn(String var0, int var1, boolean var2) {
      return new SystemColumnImpl(var0, DataTypeDescriptor.getBuiltInDataTypeDescriptor(var1, var2));
   }

   static SystemColumn getColumn(String var0, int var1, boolean var2, int var3) {
      return new SystemColumnImpl(var0, DataTypeDescriptor.getBuiltInDataTypeDescriptor(var1, var2, var3));
   }

   static SystemColumn getIdentifierColumn(String var0, boolean var1) {
      return new SystemColumnImpl(var0, DataTypeDescriptor.getBuiltInDataTypeDescriptor(12, var1, 128));
   }

   static SystemColumn getUUIDColumn(String var0, boolean var1) {
      return new SystemColumnImpl(var0, DataTypeDescriptor.getBuiltInDataTypeDescriptor(1, var1, 36));
   }

   static SystemColumn getIndicatorColumn(String var0) {
      return new SystemColumnImpl(var0, DataTypeDescriptor.getBuiltInDataTypeDescriptor(1, false, 1));
   }

   static SystemColumn getJavaColumn(String var0, String var1, boolean var2) throws StandardException {
      TypeId var3 = TypeId.getUserDefinedTypeId(var1);
      DataTypeDescriptor var4 = new DataTypeDescriptor(var3, var2);
      return new SystemColumnImpl(var0, var4);
   }

   private SystemColumnImpl(String var1, DataTypeDescriptor var2) {
      this.name = var1;
      this.type = var2;
   }

   public String getName() {
      return this.name;
   }

   public DataTypeDescriptor getType() {
      return this.type;
   }
}
