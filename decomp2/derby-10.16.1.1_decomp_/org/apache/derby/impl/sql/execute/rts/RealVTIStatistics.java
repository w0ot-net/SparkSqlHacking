package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealVTIStatistics extends RealNoPutResultSetStatistics {
   public String javaClassName;

   public RealVTIStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, String var13, double var14, double var16) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var14, var16);
      this.javaClassName = var13;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      String var2 = var10000 + MessageService.getTextMessage("43Y19.U", new Object[]{this.javaClassName}) + ":\n";
      return var2 + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.rowsSeen + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent);
   }

   public String getScanStatisticsText(String var1, int var2) {
      return this.getStatementExecutionPlanText(var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public String getNodeOn() {
      return MessageService.getTextMessage("43X75.U", new Object[]{this.javaClassName});
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43Y20.U", new Object[0]);
   }

   public void accept(XPLAINVisitor var1) {
      var1.setNumberOfChildren(0);
      var1.visit(this);
   }

   public String getRSXplainType() {
      return "VTI";
   }

   public String getRSXplainDetails() {
      return this.javaClassName + ", (" + this.resultSetNumber + ")";
   }
}
