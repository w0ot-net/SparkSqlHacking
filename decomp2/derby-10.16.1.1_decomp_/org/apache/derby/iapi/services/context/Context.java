package org.apache.derby.iapi.services.context;

import org.apache.derby.shared.common.error.StandardException;

public interface Context {
   ContextManager getContextManager();

   String getIdName();

   void cleanupOnError(Throwable var1) throws StandardException;

   void pushMe();

   void popMe();

   boolean isLastHandler(int var1);
}
