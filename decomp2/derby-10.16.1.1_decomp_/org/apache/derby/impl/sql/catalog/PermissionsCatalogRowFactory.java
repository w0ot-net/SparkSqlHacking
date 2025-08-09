package org.apache.derby.impl.sql.catalog;

import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.PermissionsDescriptor;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.shared.common.error.StandardException;

abstract class PermissionsCatalogRowFactory extends CatalogRowFactory {
   PermissionsCatalogRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
   }

   DataValueDescriptor getAuthorizationID(String var1) {
      return new SQLVarchar(var1);
   }

   DataValueDescriptor getNullAuthorizationID() {
      return new SQLVarchar();
   }

   String getAuthorizationID(ExecRow var1, int var2) throws StandardException {
      return var1.getColumn(var2).getString();
   }

   abstract ExecIndexRow buildIndexKeyRow(int var1, PermissionsDescriptor var2) throws StandardException;

   abstract int orPermissions(ExecRow var1, PermissionsDescriptor var2, boolean[] var3) throws StandardException;

   abstract int removePermissions(ExecRow var1, PermissionsDescriptor var2, boolean[] var3) throws StandardException;

   abstract void setUUIDOfThePassedDescriptor(ExecRow var1, PermissionsDescriptor var2) throws StandardException;
}
