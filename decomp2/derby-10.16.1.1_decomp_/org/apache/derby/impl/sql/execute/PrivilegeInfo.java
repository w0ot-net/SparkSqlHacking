package org.apache.derby.impl.sql.execute;

import java.util.List;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public abstract class PrivilegeInfo {
   public abstract void executeGrantRevoke(Activation var1, boolean var2, List var3) throws StandardException;

   protected void checkOwnership(String var1, TupleDescriptor var2, SchemaDescriptor var3, DataDictionary var4) throws StandardException {
      if (!var1.equals(var3.getAuthorizationId()) && !var1.equals(var4.getAuthorizationDatabaseOwner())) {
         throw StandardException.newException("42506", new Object[]{var1, var2.getDescriptorType(), var3.getSchemaName(), var2.getDescriptorName()});
      }
   }

   protected void addWarningIfPrivilegeNotRevoked(Activation var1, boolean var2, boolean var3, String var4) {
      if (!var2 && !var3) {
         var1.addWarning(StandardException.newWarning("01006", new Object[]{var4}));
      }

   }
}
