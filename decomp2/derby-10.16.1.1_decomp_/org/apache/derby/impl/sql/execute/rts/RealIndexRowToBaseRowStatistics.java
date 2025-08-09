package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealIndexRowToBaseRowStatistics extends RealNoPutResultSetStatistics {
   public String tableName;
   public ResultSetStatistics childResultSetStatistics;
   public String colsAccessedFromHeap;

   public RealIndexRowToBaseRowStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, String var13, FormatableBitSet var14, double var15, double var17, ResultSetStatistics var19) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var15, var17);
      this.tableName = var13;
      this.colsAccessedFromHeap = var14 == null ? "{" + MessageService.getTextMessage("43X59.U", new Object[0]) + "}" : var14.toString();
      this.childResultSetStatistics = var19;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43X60.U", new Object[]{this.tableName}) + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.rowsSeen + "\n" + this.indent + MessageService.getTextMessage("43X61.U", new Object[0]) + " = " + this.colsAccessedFromHeap + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.childResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n";
   }

   public String getScanStatisticsText(String var1, int var2) {
      return var1 != null && !var1.equals(this.tableName) ? "" : this.getStatementExecutionPlanText(var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public Vector getChildren() {
      Vector var1 = new Vector();
      var1.addElement(this.childResultSetStatistics);
      return var1;
   }

   public String getNodeOn() {
      return MessageService.getTextMessage("43X62.U", new Object[]{this.tableName});
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43X63.U", new Object[0]);
   }

   ResultSetStatistics getChildResultSetStatistics() {
      return this.childResultSetStatistics;
   }

   public void accept(XPLAINVisitor var1) {
      int var2 = 0;
      if (this.childResultSetStatistics != null) {
         ++var2;
      }

      var1.setNumberOfChildren(var2);
      var1.visit(this);
      if (this.childResultSetStatistics != null) {
         this.childResultSetStatistics.accept(var1);
      }

   }

   public String getRSXplainType() {
      return "ROWIDSCAN";
   }

   public String getRSXplainDetails() {
      return "(" + this.resultSetNumber + ")," + this.tableName;
   }
}
