package org.apache.derby.iapi.db;

import org.apache.derby.iapi.services.context.Context;

public interface DatabaseContext extends Context {
   String CONTEXT_ID = "Database";

   Database getDatabase();
}
