package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealNestedLoopJoinStatistics extends RealJoinResultSetStatistics {
   public boolean oneRowRightSide;
   public ResultSetStatistics leftResultSetStatistics;
   public ResultSetStatistics rightResultSetStatistics;
   protected String nodeName;
   public String resultSetName;

   public RealNestedLoopJoinStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, int var14, int var15, long var16, boolean var18, double var19, double var21, String var23, ResultSetStatistics var24, ResultSetStatistics var25) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var13, var14, var15, var16, var19, var21, var23);
      this.oneRowRightSide = var18;
      this.leftResultSetStatistics = var24;
      this.rightResultSetStatistics = var25;
      this.setNames();
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var2 = "";
      if (this.userSuppliedOptimizerOverrides != null) {
         String var10000 = this.indent;
         var2 = var10000 + MessageService.getTextMessage("43Y57.U", new Object[]{this.userSuppliedOptimizerOverrides});
         var2 = var2 + "\n";
      }

      return var2 + this.indent + this.resultSetName + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X79.U", new Object[0]) + " = " + this.rowsSeenLeft + "\n" + this.indent + MessageService.getTextMessage("43X80.U", new Object[0]) + " = " + this.rowsSeenRight + "\n" + this.indent + MessageService.getTextMessage("43X32.U", new Object[0]) + " = " + this.rowsFiltered + "\n" + this.indent + MessageService.getTextMessage("43X81.U", new Object[0]) + " = " + this.rowsReturned + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.indent + MessageService.getTextMessage("43X82.U", new Object[0]) + ":\n" + this.leftResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n" + this.indent + MessageService.getTextMessage("43X83.U", new Object[0]) + ":\n" + this.rightResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n";
   }

   public String getScanStatisticsText(String var1, int var2) {
      String var10000 = this.leftResultSetStatistics.getScanStatisticsText(var1, var2);
      return var10000 + this.rightResultSetStatistics.getScanStatisticsText(var1, var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public Vector getChildren() {
      Vector var1 = new Vector();
      var1.addElement(this.leftResultSetStatistics);
      var1.addElement(this.rightResultSetStatistics);
      return var1;
   }

   public String getNodeName() {
      return this.nodeName;
   }

   protected void setNames() {
      if (this.nodeName == null) {
         if (this.oneRowRightSide) {
            this.nodeName = MessageService.getTextMessage("43X84.U", new Object[0]);
            this.resultSetName = MessageService.getTextMessage("43X85.U", new Object[0]);
         } else {
            this.nodeName = MessageService.getTextMessage("43X86.U", new Object[0]);
            this.resultSetName = MessageService.getTextMessage("43X87.U", new Object[0]);
         }
      }

   }

   public void accept(XPLAINVisitor var1) {
      int var2 = 0;
      if (this.leftResultSetStatistics != null) {
         ++var2;
      }

      if (this.rightResultSetStatistics != null) {
         ++var2;
      }

      var1.setNumberOfChildren(var2);
      var1.visit(this);
      if (this.leftResultSetStatistics != null) {
         this.leftResultSetStatistics.accept(var1);
      }

      if (this.rightResultSetStatistics != null) {
         this.rightResultSetStatistics.accept(var1);
      }

   }

   public String getRSXplainType() {
      return "NLJOIN";
   }

   public String getRSXplainDetails() {
      String var1 = "(" + this.resultSetNumber + ")";
      if (this.oneRowRightSide) {
         var1 = var1 + ", EXISTS JOIN";
      }

      return var1;
   }
}
