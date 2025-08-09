package org.apache.hive.jdbc;

import java.sql.SQLException;

public class ClosedOrCancelledStatementException extends SQLException {
   private static final long serialVersionUID = 0L;

   public ClosedOrCancelledStatementException(String msg) {
      super(msg);
   }
}
