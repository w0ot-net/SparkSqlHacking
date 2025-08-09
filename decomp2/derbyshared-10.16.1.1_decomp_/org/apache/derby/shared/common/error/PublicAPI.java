package org.apache.derby.shared.common.error;

import java.sql.SQLException;

public class PublicAPI {
   public static SQLException wrapStandardException(StandardException var0) {
      var0.markAsPublicAPI();
      return ExceptionFactory.getInstance().getSQLException(var0.getMessage(), var0.getMessageId(), (SQLException)null, var0.getSeverity(), var0, var0.getArguments());
   }
}
