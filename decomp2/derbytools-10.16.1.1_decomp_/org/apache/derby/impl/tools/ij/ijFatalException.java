package org.apache.derby.impl.tools.ij;

import java.sql.SQLException;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;

public class ijFatalException extends RuntimeException {
   private static final String FatalException = LocalizedResource.getMessage("IJ_FataExceTerm");
   private SQLException e;

   public ijFatalException() {
      super(FatalException);
      this.e = null;
   }

   public ijFatalException(SQLException var1) {
      super(FatalException);
      this.e = var1;
   }

   public String getSQLState() {
      return this.e.getSQLState();
   }

   public String toString() {
      return LocalizedResource.getMessage("IJ_Fata01", this.e.getSQLState(), this.e.getMessage());
   }
}
