package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealNestedLoopLeftOuterJoinStatistics extends RealNestedLoopJoinStatistics {
   public int emptyRightRowsReturned;

   public RealNestedLoopLeftOuterJoinStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, int var14, int var15, long var16, double var18, double var20, String var22, ResultSetStatistics var23, ResultSetStatistics var24, int var25) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var13, var14, var15, var16, false, var18, var20, var22, var23, var24);
      this.emptyRightRowsReturned = var25;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      return var10000 + this.resultSetName + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X79.U", new Object[0]) + " = " + this.rowsSeenLeft + "\n" + this.indent + MessageService.getTextMessage("43X80.U", new Object[0]) + " = " + this.rowsSeenRight + "\n" + this.indent + MessageService.getTextMessage("43X88.U", new Object[0]) + " = " + this.emptyRightRowsReturned + "\n" + this.indent + MessageService.getTextMessage("43X32.U", new Object[0]) + " = " + this.rowsFiltered + "\n" + this.indent + MessageService.getTextMessage("43X81.U", new Object[0]) + " = " + this.rowsReturned + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.indent + MessageService.getTextMessage("43X82.U", new Object[0]) + ":\n" + this.leftResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n" + this.indent + MessageService.getTextMessage("43X83.U", new Object[0]) + ":\n" + this.rightResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n";
   }

   public String getScanStatisticsText(String var1, int var2) {
      String var10000 = this.leftResultSetStatistics.getScanStatisticsText(var1, var2);
      return var10000 + this.rightResultSetStatistics.getScanStatisticsText(var1, var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   protected void setNames() {
      this.nodeName = MessageService.getTextMessage("43X89.U", new Object[0]);
      this.resultSetName = MessageService.getTextMessage("43X90.U", new Object[0]);
   }

   public String getRSXplainType() {
      return "LONLJOIN";
   }

   public String getRSXplainDetails() {
      String var1 = "(" + this.resultSetNumber + "), " + this.resultSetName;
      if (this.oneRowRightSide) {
         var1 = var1 + ", EXISTS JOIN";
      }

      return var1;
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, (String)null, (String)null, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, (Integer)null, this.rowsSeenLeft, this.rowsSeenRight, this.rowsFiltered, this.rowsReturned, this.emptyRightRowsReturned, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
