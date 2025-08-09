package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealCurrentOfStatistics extends RealNoPutResultSetStatistics {
   public RealCurrentOfStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, (double)0.0F, (double)0.0F);
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43X12.U", new Object[]{"getStatementExecutionPlanText", "CurrentOfResultSet\n"});
   }

   public String getScanStatisticsText(String var1, int var2) {
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43X12.U", new Object[]{"getScanStatisticsText", "CurrentOfResultSet\n"});
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public String getNodeName() {
      return "Current Of";
   }

   public void accept(XPLAINVisitor var1) {
      var1.setNumberOfChildren(0);
      var1.visit(this);
   }

   public String getRSXplainType() {
      return "CURRENT-OF";
   }
}
