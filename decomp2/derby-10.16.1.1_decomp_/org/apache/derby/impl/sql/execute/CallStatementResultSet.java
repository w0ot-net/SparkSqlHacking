package org.apache.derby.impl.sql.execute;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.jdbc.ConnectionContext;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.shared.common.error.StandardException;

class CallStatementResultSet extends NoRowsResultSetImpl {
   private final GeneratedMethod methodCall;

   CallStatementResultSet(GeneratedMethod var1, Activation var2) {
      super(var2);
      this.methodCall = var1;
   }

   public void open() throws StandardException {
      this.setup();
      this.methodCall.invoke(this.activation);
   }

   public void close() throws StandardException {
      this.close(false);
      ResultSet[][] var1 = this.getActivation().getDynamicResults();
      if (var1 != null) {
         StandardException var2 = null;
         ConnectionContext var3 = null;

         for(int var4 = 0; var4 < var1.length; ++var4) {
            ResultSet[] var5 = var1[var4];
            ResultSet var6 = var5[0];
            if (var6 != null) {
               if (var3 == null) {
                  var3 = (ConnectionContext)this.lcc.getContextManager().getContext("JDBC_ConnectionContext");
               }

               try {
                  if (var3.processInaccessibleDynamicResult(var6)) {
                     var6.close();
                  }
               } catch (SQLException var12) {
                  if (var2 == null) {
                     StandardException var8 = StandardException.plainWrapException(var12);
                     var2 = var8;
                  }
               } finally {
                  var5[0] = null;
               }
            }
         }

         if (var2 != null) {
            throw var2;
         }
      }

   }

   public void cleanUp() throws StandardException {
      this.close();
   }
}
