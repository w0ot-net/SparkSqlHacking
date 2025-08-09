package org.apache.derby.iapi.services.daemon;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

public interface Serviceable {
   int DONE = 1;
   int REQUEUE = 2;

   int performWork(ContextManager var1) throws StandardException;

   boolean serviceASAP();

   boolean serviceImmediately();
}
