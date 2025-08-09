package org.sparkproject.jetty.plus.jndi;

import jakarta.transaction.UserTransaction;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.LinkRef;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.jndi.NamingUtil;

public class Transaction extends NamingEntry {
   private static final Logger LOG = LoggerFactory.getLogger(Transaction.class);
   public static final String USER_TRANSACTION = "UserTransaction";

   public static void bindToENC() throws NamingException {
      Transaction txEntry = (Transaction)NamingEntryUtil.lookupNamingEntry((Object)null, "UserTransaction");
      if (txEntry != null) {
         txEntry.bindToComp();
      } else {
         throw new NameNotFoundException("UserTransaction not found");
      }
   }

   public Transaction(UserTransaction userTransaction) throws NamingException {
      super("UserTransaction");
      this.save(userTransaction);
   }

   public void bindToENC(String localName) throws NamingException {
      InitialContext ic = new InitialContext();
      Context env = (Context)ic.lookup("java:comp/env");
      if (LOG.isDebugEnabled()) {
         LOG.debug("Binding java:comp/env{} to {}", this.getJndiName(), this._objectNameString);
      }

      NamingUtil.bind(env, localName, new LinkRef(this._objectNameString));
   }

   private void bindToComp() throws NamingException {
      InitialContext ic = new InitialContext();
      Context env = (Context)ic.lookup("java:comp");
      if (LOG.isDebugEnabled()) {
         LOG.debug("Binding java:comp/{} to {}", this.getJndiName(), this._objectNameString);
      }

      NamingUtil.bind(env, this.getJndiName(), new LinkRef(this._objectNameString));
   }

   public void unbindENC() {
      try {
         InitialContext ic = new InitialContext();
         Context env = (Context)ic.lookup("java:comp");
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unbinding java:comp/{}", this.getJndiName());
         }

         env.unbind(this.getJndiName());
      } catch (NamingException e) {
         LOG.warn("Unable to unbind java:comp/{}", this.getJndiName(), e);
      }

   }
}
