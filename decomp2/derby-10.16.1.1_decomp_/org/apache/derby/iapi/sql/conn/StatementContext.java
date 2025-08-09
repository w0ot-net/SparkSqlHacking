package org.apache.derby.iapi.sql.conn;

import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.depend.Dependency;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.shared.common.error.StandardException;

public interface StatementContext extends Context {
   void setInUse(boolean var1, boolean var2, boolean var3, String var4, ParameterValueSet var5, long var6);

   void clearInUse();

   void setSavePoint() throws StandardException;

   void resetSavePoint() throws StandardException;

   void clearSavePoint() throws StandardException;

   void setTopResultSet(ResultSet var1, NoPutResultSet[] var2) throws StandardException;

   void setSubqueryResultSet(int var1, NoPutResultSet var2, int var3) throws StandardException;

   NoPutResultSet[] getSubqueryTrackingArray() throws StandardException;

   void addDependency(Dependency var1) throws StandardException;

   boolean onStack();

   boolean inTrigger();

   boolean isAtomic();

   boolean inUse();

   boolean isForReadOnly();

   boolean isCancelled();

   void cancel();

   String getStatementText();

   void setSQLAllowed(short var1, boolean var2);

   short getSQLAllowed();

   void setSystemCode();

   boolean getSystemCode();

   void setParentRollback();

   void setActivation(Activation var1);

   Activation getActivation();

   SQLSessionContext getSQLSessionContext();

   void setSQLSessionContext(SQLSessionContext var1);

   boolean getStatementWasInvalidated();
}
