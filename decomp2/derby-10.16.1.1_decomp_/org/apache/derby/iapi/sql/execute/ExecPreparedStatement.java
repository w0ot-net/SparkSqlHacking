package org.apache.derby.iapi.sql.execute;

import java.util.List;
import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.iapi.sql.PreparedStatement;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.shared.common.error.StandardException;

public interface ExecPreparedStatement extends PreparedStatement {
   void setSource(String var1);

   ConstantAction getConstantAction();

   Object getSavedObject(int var1);

   List getSavedObjects();

   Object getCursorInfo();

   GeneratedClass getActivationClass() throws StandardException;

   boolean upToDate(GeneratedClass var1) throws StandardException;

   void finish(LanguageConnectionContext var1);

   boolean needsSavepoint();

   ExecPreparedStatement getClone() throws StandardException;

   int getUpdateMode();

   ExecCursorTableReference getTargetTable();

   boolean hasUpdateColumns();

   boolean isUpdateColumn(String var1);

   void setValid();

   void setSPSAction();

   List getRequiredPermissionsList();

   int incrementExecutionCount();

   long getInitialRowCount(int var1, long var2);

   void setStalePlanCheckInterval(int var1);

   int getStalePlanCheckInterval();
}
