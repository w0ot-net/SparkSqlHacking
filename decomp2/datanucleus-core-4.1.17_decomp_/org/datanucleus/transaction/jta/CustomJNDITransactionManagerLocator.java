package org.datanucleus.transaction.jta;

import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.NucleusException;

public class CustomJNDITransactionManagerLocator extends JNDIBasedTransactionManagerLocator {
   protected String jndiLocation;

   public CustomJNDITransactionManagerLocator(NucleusContext nucleusCtx) {
      this.jndiLocation = nucleusCtx.getConfiguration().getStringProperty("datanucleus.jtaJndiLocation");
      if (this.jndiLocation == null) {
         (new NucleusException("NO Custom JNDI Location specified in configuration.")).setFatal();
      }

   }

   public String getJNDIName() {
      return this.jndiLocation;
   }
}
