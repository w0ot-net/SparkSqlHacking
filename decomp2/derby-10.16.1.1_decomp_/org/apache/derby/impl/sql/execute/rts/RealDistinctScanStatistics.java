package org.apache.derby.impl.sql.execute.rts;

import java.util.Properties;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.iapi.util.PropertyUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealDistinctScanStatistics extends RealHashScanStatistics {
   public RealDistinctScanStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, String var13, String var14, boolean var15, int var16, int[] var17, String var18, String var19, Properties var20, String var21, String var22, String var23, String var24, double var25, double var27) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, var25, var27);
   }

   public String getStatementExecutionPlanText(int var1) {
      Object var3 = null;
      this.initFormatInfo(var1);
      String var2;
      if (this.indexName != null) {
         String var10000 = this.indent;
         var2 = var10000 + MessageService.getTextMessage("43X23.U", new Object[]{this.tableName, this.isConstraint ? "constraint" : "index", this.indexName});
      } else {
         String var9 = this.indent;
         var2 = var9 + MessageService.getTextMessage("43X26.U", new Object[]{this.tableName});
      }

      var2 = var2 + " " + MessageService.getTextMessage("43X27.U", new Object[]{this.isolationLevel, this.lockString}) + ": \n";
      String var10 = this.indent;
      String var4 = var10 + MessageService.getTextMessage("43X28.U", new Object[0]) + ": \n" + PropertyUtil.sortProperties(this.scanProperties, this.subIndent);
      String var5;
      if (this.hashKeyColumns.length == 1) {
         var10 = MessageService.getTextMessage("43X29.U", new Object[0]);
         var5 = var10 + " " + this.hashKeyColumns[0];
      } else {
         var10 = MessageService.getTextMessage("43X30.U", new Object[0]);
         var5 = var10 + " (" + this.hashKeyColumns[0];

         for(int var6 = 1; var6 < this.hashKeyColumns.length; ++var6) {
            var5 = var5 + "," + this.hashKeyColumns[var6];
         }

         var5 = var5 + ")";
      }

      return var2 + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X31.U", new Object[0]) + " = " + this.hashtableSize + "\n" + this.indent + var5 + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.rowsSeen + "\n" + this.indent + MessageService.getTextMessage("43X32.U", new Object[0]) + " = " + this.rowsFiltered + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + (this.rowsSeen > 0 ? this.subIndent + MessageService.getTextMessage("43X33.U", new Object[0]) + " = " + this.nextTime / (long)this.rowsSeen + "\n" : "") + "\n" + var4 + this.subIndent + MessageService.getTextMessage("43X34.U", new Object[0]) + ":\n" + StringUtil.ensureIndent(this.startPosition, var1 + 2) + "\n" + this.subIndent + MessageService.getTextMessage("43X35.U", new Object[0]) + ":\n" + StringUtil.ensureIndent(this.stopPosition, var1 + 2) + "\n" + this.subIndent + MessageService.getTextMessage("43X36.U", new Object[0]) + ":\n" + StringUtil.ensureIndent(this.scanQualifiers, var1 + 2) + "\n" + this.subIndent + MessageService.getTextMessage("43X37.U", new Object[0]) + ":\n" + StringUtil.ensureIndent(this.nextQualifiers, var1 + 2) + "\n" + this.dumpEstimatedCosts(this.subIndent);
   }

   public String getScanStatisticsText(String var1, int var2) {
      return var1 != null && !var1.equals(this.tableName) ? "" : this.getStatementExecutionPlanText(var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public String getNodeOn() {
      return MessageService.getTextMessage("43X38.U", new Object[]{this.tableName, this.indexName});
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43X39.U", new Object[0]);
   }

   public void accept(XPLAINVisitor var1) {
      var1.setNumberOfChildren(0);
      var1.visit(this);
   }

   public String getRSXplainType() {
      return "DISTINCTSCAN";
   }
}
