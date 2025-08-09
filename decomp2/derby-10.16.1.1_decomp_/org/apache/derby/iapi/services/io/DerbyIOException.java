package org.apache.derby.iapi.services.io;

import java.io.IOException;

public final class DerbyIOException extends IOException {
   private final String sqlState;

   public DerbyIOException(String var1, String var2) {
      super(var1);
      this.sqlState = var2;
   }

   public String getSQLState() {
      return this.sqlState;
   }
}
