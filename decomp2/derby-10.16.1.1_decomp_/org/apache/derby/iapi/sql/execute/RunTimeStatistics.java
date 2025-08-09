package org.apache.derby.iapi.sql.execute;

import java.sql.Timestamp;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;

public interface RunTimeStatistics {
   long getCompileTimeInMillis();

   long getParseTimeInMillis();

   long getBindTimeInMillis();

   long getOptimizeTimeInMillis();

   long getGenerateTimeInMillis();

   long getExecuteTimeInMillis();

   Timestamp getBeginCompilationTimestamp();

   Timestamp getEndCompilationTimestamp();

   Timestamp getBeginExecutionTimestamp();

   Timestamp getEndExecutionTimestamp();

   String getStatementName();

   String getSPSName();

   String getStatementText();

   String getStatementExecutionPlanText();

   String getScanStatisticsText();

   String getScanStatisticsText(String var1);

   double getEstimatedRowCount();

   void acceptFromTopResultSet(XPLAINVisitor var1);
}
