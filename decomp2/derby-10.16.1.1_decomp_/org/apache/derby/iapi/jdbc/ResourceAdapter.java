package org.apache.derby.iapi.jdbc;

import javax.transaction.xa.XAException;
import org.apache.derby.iapi.store.access.xa.XAResourceManager;
import org.apache.derby.iapi.store.access.xa.XAXactId;

public interface ResourceAdapter {
   XAResourceManager getXAResourceManager();

   boolean isActive();

   Object findConnection(XAXactId var1);

   boolean addConnection(XAXactId var1, Object var2);

   Object removeConnection(XAXactId var1);

   void cancelXATransaction(XAXactId var1, String var2) throws XAException;
}
