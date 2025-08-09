package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealScrollInsensitiveResultSetStatistics extends RealNoPutResultSetStatistics {
   public ResultSetStatistics childResultSetStatistics;
   public int numFromHashTable;
   public int numToHashTable;

   public RealScrollInsensitiveResultSetStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, int var14, double var15, double var17, ResultSetStatistics var19) {
      super(var1, var2, var3, var4, var6, var8, var10, var14, var15, var17);
      this.numFromHashTable = var12;
      this.numToHashTable = var13;
      this.childResultSetStatistics = var19;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43Y03.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.rowsSeen + "\n" + this.indent + MessageService.getTextMessage("43Y04.U", new Object[0]) + " = " + this.numFromHashTable + "\n" + this.indent + MessageService.getTextMessage("43Y05.U", new Object[0]) + " = " + this.numToHashTable + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.indent + MessageService.getTextMessage("43X05.U", new Object[0]) + ":\n" + this.childResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n";
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
      return MessageService.getTextMessage("43Y03.U", new Object[0]);
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
      return "SCROLL";
   }

   public String getRSXplainDetails() {
      return "(" + this.resultSetNumber + "), [" + this.numFromHashTable + ", " + this.numToHashTable + "]";
   }
}
