package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.services.context.Context;

public interface ExecutionContext extends Context {
   String CONTEXT_ID = "ExecutionContext";

   ExecutionFactory getExecutionFactory();
}
