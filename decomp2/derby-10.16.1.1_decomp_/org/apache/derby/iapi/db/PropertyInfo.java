package org.apache.derby.iapi.db;

import java.sql.SQLException;
import org.apache.derby.iapi.security.Securable;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.Authorizer;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;

public final class PropertyInfo {
   public static void setDatabaseProperty(String var0, String var1) throws SQLException {
      LanguageConnectionContext var2 = ConnectionUtil.getCurrentLCC();

      try {
         SecurityUtil.authorize(Securable.SET_DATABASE_PROPERTY);
         Authorizer var3 = var2.getAuthorizer();
         var3.authorize((Activation)null, 5);
         TransactionController var4 = var2.getTransactionExecute();
         var4.setProperty(var0, var1, false);
      } catch (StandardException var5) {
         throw PublicAPI.wrapStandardException(var5);
      }
   }

   private PropertyInfo() {
   }
}
