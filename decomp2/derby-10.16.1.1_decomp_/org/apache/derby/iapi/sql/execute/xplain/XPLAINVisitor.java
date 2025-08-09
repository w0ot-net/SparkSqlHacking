package org.apache.derby.iapi.sql.execute.xplain;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.RunTimeStatistics;
import org.apache.derby.shared.common.error.StandardException;

public interface XPLAINVisitor {
   void reset();

   void doXPLAIN(RunTimeStatistics var1, Activation var2) throws StandardException;

   void visit(ResultSetStatistics var1);

   void setNumberOfChildren(int var1);
}
