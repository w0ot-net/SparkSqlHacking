package org.apache.derby.iapi.sql.conn;

import java.util.HashMap;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;

public interface SQLSessionContext {
   void setRole(String var1);

   String getRole();

   void setUser(String var1);

   String getCurrentUser();

   void setDefaultSchema(SchemaDescriptor var1);

   SchemaDescriptor getDefaultSchema();

   HashMap getConstraintModes();

   void setConstraintModes(HashMap var1);

   void setDeferred(UUID var1, boolean var2);

   Boolean isDeferred(UUID var1);

   void resetConstraintModes();

   void setDeferredAll(Boolean var1);

   Boolean getDeferredAll();
}
