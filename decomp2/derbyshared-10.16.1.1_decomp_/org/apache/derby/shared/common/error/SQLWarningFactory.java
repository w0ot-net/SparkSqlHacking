package org.apache.derby.shared.common.error;

import java.sql.SQLWarning;
import org.apache.derby.shared.common.i18n.MessageService;

public class SQLWarningFactory {
   public static SQLWarning newSQLWarning(String var0, Object... var1) {
      return new SQLWarning(MessageService.getTextMessage(var0, var1), StandardException.getSQLStateFromIdentifier(var0), 10000);
   }
}
