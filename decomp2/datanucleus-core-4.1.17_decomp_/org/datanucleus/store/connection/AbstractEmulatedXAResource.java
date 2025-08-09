package org.datanucleus.store.connection;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractEmulatedXAResource implements XAResource {
   protected ManagedConnection mconn;

   public AbstractEmulatedXAResource(ManagedConnection mconn) {
      this.mconn = mconn;
   }

   public void start(Xid xid, int flags) throws XAException {
      NucleusLogger.CONNECTION.debug(Localiser.msg("009017", this.mconn.toString(), xid.toString(), flags));
   }

   public int prepare(Xid xid) throws XAException {
      NucleusLogger.CONNECTION.debug(Localiser.msg("009018", this.mconn.toString(), xid.toString()));
      return 0;
   }

   public void commit(Xid xid, boolean onePhase) throws XAException {
      NucleusLogger.CONNECTION.debug(Localiser.msg("009019", this.mconn.toString(), xid.toString(), onePhase));
   }

   public void rollback(Xid xid) throws XAException {
      NucleusLogger.CONNECTION.debug(Localiser.msg("009021", this.mconn.toString(), xid.toString()));
   }

   public void end(Xid xid, int flags) throws XAException {
      NucleusLogger.CONNECTION.debug(Localiser.msg("009023", this.mconn.toString(), xid.toString(), flags));
   }

   public void forget(Xid xid) throws XAException {
   }

   public boolean isSameRM(XAResource xares) throws XAException {
      return this == xares;
   }

   public Xid[] recover(int arg0) throws XAException {
      throw new XAException("Unsupported operation");
   }

   public int getTransactionTimeout() throws XAException {
      return 0;
   }

   public boolean setTransactionTimeout(int timeout) throws XAException {
      return false;
   }
}
