package org.datanucleus.api.jdo;

import javax.jdo.JDOUserException;
import javax.jdo.datastore.JDOConnection;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.NucleusConnection;
import org.datanucleus.util.Localiser;

public class JDOConnectionImpl implements JDOConnection {
   protected NucleusConnection nucConn = null;

   public JDOConnectionImpl(NucleusConnection nconn) {
      this.nucConn = nconn;
   }

   public void close() {
      try {
         this.nucConn.close();
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public Object getNativeConnection() {
      try {
         return this.nucConn.getNativeConnection();
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   protected void throwExceptionNotAvailable() {
      throw new JDOUserException(Localiser.msg("046001"));
   }

   protected void throwExceptionUnsupportedOperation(String methodName) {
      throw new JDOUserException(Localiser.msg("046000", new Object[]{methodName}));
   }
}
