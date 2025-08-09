package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealUnionResultSetStatistics extends RealNoPutResultSetStatistics {
   public int rowsSeenLeft;
   public int rowsSeenRight;
   public int rowsReturned;
   public ResultSetStatistics leftResultSetStatistics;
   public ResultSetStatistics rightResultSetStatistics;

   public RealUnionResultSetStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, int var14, int var15, double var16, double var18, ResultSetStatistics var20, ResultSetStatistics var21) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var16, var18);
      this.rowsSeenLeft = var13;
      this.rowsSeenRight = var14;
      this.rowsReturned = var15;
      this.leftResultSetStatistics = var20;
      this.rightResultSetStatistics = var21;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43Y14.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X79.U", new Object[0]) + " = " + this.rowsSeenLeft + "\n" + this.indent + MessageService.getTextMessage("43X80.U", new Object[0]) + " = " + this.rowsSeenRight + "\n" + this.indent + MessageService.getTextMessage("43X81.U", new Object[0]) + " = " + this.rowsReturned + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.indent + MessageService.getTextMessage("43X82.U", new Object[0]) + ":\n" + this.leftResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n" + this.indent + MessageService.getTextMessage("43X83.U", new Object[0]) + ":\n" + this.rightResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n";
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
      return "Union";
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
      return "UNION";
   }

   public String getRSXplainDetails() {
      return "(" + this.resultSetNumber + ")";
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, (String)null, (String)null, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, (Integer)null, this.rowsSeenLeft, this.rowsSeenRight, this.rowsFiltered, this.rowsReturned, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
