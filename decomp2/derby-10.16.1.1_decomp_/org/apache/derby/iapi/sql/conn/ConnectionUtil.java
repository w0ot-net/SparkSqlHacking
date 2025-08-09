package org.apache.derby.iapi.sql.conn;

import java.sql.SQLException;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.shared.common.i18n.MessageService;

public class ConnectionUtil {
   public static LanguageConnectionContext getCurrentLCC() throws SQLException {
      LanguageConnectionContext var0 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
      if (var0 == null) {
         throw new SQLException(MessageService.getTextMessage("08003", new Object[0]), "08003", 40000);
      } else {
         return var0;
      }
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }
}
