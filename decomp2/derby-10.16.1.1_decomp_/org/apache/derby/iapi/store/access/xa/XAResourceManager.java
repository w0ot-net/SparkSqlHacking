package org.apache.derby.iapi.store.access.xa;

import javax.transaction.xa.Xid;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

public interface XAResourceManager {
   void commit(ContextManager var1, Xid var2, boolean var3) throws StandardException;

   ContextManager find(Xid var1);

   void forget(ContextManager var1, Xid var2) throws StandardException;

   Xid[] recover(int var1) throws StandardException;

   void rollback(ContextManager var1, Xid var2) throws StandardException;
}
