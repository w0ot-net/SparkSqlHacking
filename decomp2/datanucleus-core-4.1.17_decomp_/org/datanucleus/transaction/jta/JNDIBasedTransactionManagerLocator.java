package org.datanucleus.transaction.jta;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.TransactionManager;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.Localiser;

public abstract class JNDIBasedTransactionManagerLocator implements TransactionManagerLocator {
   public abstract String getJNDIName();

   public TransactionManager getTransactionManager(ClassLoaderResolver clr) {
      try {
         InitialContext ctx = new InitialContext();

         try {
            return (TransactionManager)ctx.lookup(this.getJNDIName());
         } catch (Exception var4) {
            return null;
         }
      } catch (NamingException ne) {
         throw new NucleusException(Localiser.msg("015029"), ne);
      }
   }
}
