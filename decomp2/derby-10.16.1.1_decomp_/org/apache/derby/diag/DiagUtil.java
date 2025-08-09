package org.apache.derby.diag;

import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;

abstract class DiagUtil {
   static void checkAccess() throws StandardException {
      LanguageConnectionContext var0 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
      DataDictionary var1 = var0.getDataDictionary();
      if (var1.usesSqlAuthorization()) {
         String var2 = var1.getAuthorizationDatabaseOwner();
         String var3 = var0.getStatementContext().getSQLSessionContext().getCurrentUser();
         if (!var2.equals(var3)) {
            throw StandardException.newException("4251D", new Object[0]);
         }
      }

   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }
}
