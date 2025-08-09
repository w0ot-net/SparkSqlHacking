package org.apache.derby.impl.sql.execute.xplain;

import java.sql.SQLException;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.RunTimeStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

public class XPLAINDefaultVisitor implements XPLAINVisitor {
   public void visit(ResultSetStatistics var1) {
   }

   public void reset() {
   }

   public void doXPLAIN(RunTimeStatistics var1, Activation var2) {
      try {
         LanguageConnectionContext var3 = ConnectionUtil.getCurrentLCC();
         HeaderPrintWriter var4 = var3.getLogQueryPlan() ? Monitor.getStream() : null;
         if (var4 != null) {
            String var10001 = var3.getTransactionExecute().getTransactionIdString();
            var4.printlnWithHeader("(XID = " + var10001 + "), (SESSIONID = " + var3.getInstanceNumber() + "), " + var1.getStatementText() + " ******* " + var1.getStatementExecutionPlanText());
         }
      } catch (SQLException var5) {
         var5.printStackTrace();
      }

   }

   public void setNumberOfChildren(int var1) {
   }
}
