package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealDistinctScalarAggregateStatistics extends RealScalarAggregateStatistics {
   public RealDistinctScalarAggregateStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, double var14, double var16, ResultSetStatistics var18) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, false, var13, var14, var16, var18);
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43X20.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X21.U", new Object[0]) + " = " + this.rowsInput + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.indent + MessageService.getTextMessage("43X05.U", new Object[0]) + ":\n" + this.childResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n";
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
      return MessageService.getTextMessage("43X22.U", new Object[0]);
   }

   public void accept(XPLAINVisitor var1) {
      var1.setNumberOfChildren(1);
      var1.visit(this);
      this.childResultSetStatistics.accept(var1);
   }

   public String getRSXplainDetails() {
      return "DISTINCT";
   }
}
