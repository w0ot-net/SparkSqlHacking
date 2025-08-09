package org.apache.derby.vti;

import java.io.Serializable;

public interface VTIEnvironment {
   boolean isCompileTime();

   String getOriginalSQL();

   int getStatementIsolationLevel();

   void setSharedState(String var1, Serializable var2);

   Object getSharedState(String var1);
}
