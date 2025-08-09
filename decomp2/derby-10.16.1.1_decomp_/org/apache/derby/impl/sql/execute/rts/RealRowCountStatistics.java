package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealRowCountStatistics extends RealNoPutResultSetStatistics {
   public ResultSetStatistics childResultSetStatistics;

   public RealRowCountStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, double var13, double var15, ResultSetStatistics var17) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var13, var15);
      this.childResultSetStatistics = var17;
   }

   public String getStatementExecutionPlanText(int var1) {
      String var2 = "";
      this.initFormatInfo(var1);
      return var2 + this.indent + MessageService.getTextMessage("43X9B.U", new Object[0]) + " (" + this.resultSetNumber + "):\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.rowsSeen + "\n" + this.indent + MessageService.getTextMessage("43X32.U", new Object[0]) + " = " + this.rowsFiltered + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.indent + MessageService.getTextMessage("43X05.U", new Object[0]) + ":\n" + this.childResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth);
   }

   public String getScanStatisticsText(String var1, int var2) {
      return this.childResultSetStatistics.getScanStatisticsText(var1, var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public Vector getChildren() {
      Vector var1 = new Vector();
      var1.addElement(this.childResultSetStatistics);
      return var1;
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43X9A.U", new Object[0]);
   }

   public void accept(XPLAINVisitor var1) {
      var1.setNumberOfChildren(1);
      var1.visit(this);
      this.childResultSetStatistics.accept(var1);
   }

   public String getRSXplainType() {
      return "ROW-COUNT";
   }
}
