package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealOnceResultSetStatistics extends RealNoPutResultSetStatistics {
   public int subqueryNumber;
   public int pointOfAttachment;
   public ResultSetStatistics childResultSetStatistics;

   public RealOnceResultSetStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, int var14, double var15, double var17, ResultSetStatistics var19) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var15, var17);
      this.subqueryNumber = var13;
      this.pointOfAttachment = var14;
      this.childResultSetStatistics = var19;
   }

   public String getStatementExecutionPlanText(int var1) {
      String var2 = this.pointOfAttachment == -1 ? ":" : MessageService.getTextMessage("43X00.U", new Object[0]) + " " + this.pointOfAttachment + "):";
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43X01.U", new Object[0]) + " " + this.subqueryNumber + "\n" + this.indent + MessageService.getTextMessage("43X92.U", new Object[0]) + var2 + "\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.rowsSeen + "\n" + this.indent + MessageService.getTextMessage("43X05.U", new Object[0]) + ":\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.childResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n" + this.indent + MessageService.getTextMessage("43X06.U", new Object[0]) + " " + this.subqueryNumber + "\n";
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
      return MessageService.getTextMessage("43X92.U", new Object[0]);
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
      return "ONCE";
   }

   public String getRSXplainDetails() {
      String var1 = this.pointOfAttachment == -1 ? "" : "ATTACHED:" + this.pointOfAttachment;
      return var1 + ";" + this.resultSetNumber;
   }
}
