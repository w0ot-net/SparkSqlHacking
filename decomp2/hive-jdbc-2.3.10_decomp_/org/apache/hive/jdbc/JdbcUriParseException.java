package org.apache.hive.jdbc;

import java.sql.SQLException;

public class JdbcUriParseException extends SQLException {
   private static final long serialVersionUID = 0L;

   public JdbcUriParseException(Throwable cause) {
      super(cause);
   }

   public JdbcUriParseException(String msg) {
      super(msg);
   }

   public JdbcUriParseException(String msg, Throwable cause) {
      super(msg, cause);
   }
}
