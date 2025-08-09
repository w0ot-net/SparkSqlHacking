package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.sql.execute.xplain.XPLAINable;

public interface ResultSetStatistics extends XPLAINable {
   String getStatementExecutionPlanText(int var1);

   String getScanStatisticsText(String var1, int var2);

   double getEstimatedRowCount();
}
