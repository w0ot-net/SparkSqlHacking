package org.datanucleus.store.rdbms.datasource.dbcp;

/** @deprecated */
public class DbcpException extends RuntimeException {
   private static final long serialVersionUID = 2477800549022838103L;
   protected Throwable cause;

   public DbcpException() {
      this.cause = null;
   }

   public DbcpException(String message) {
      this(message, (Throwable)null);
   }

   public DbcpException(String message, Throwable cause) {
      super(message);
      this.cause = null;
      this.cause = cause;
   }

   public DbcpException(Throwable cause) {
      super(cause == null ? (String)null : cause.toString());
      this.cause = null;
      this.cause = cause;
   }

   public synchronized Throwable getCause() {
      return this.cause;
   }
}
