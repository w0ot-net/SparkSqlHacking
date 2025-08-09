package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealScalarAggregateStatistics extends RealNoPutResultSetStatistics {
   public int rowsInput = 0;
   public boolean indexKeyOptimization;
   public ResultSetStatistics childResultSetStatistics;

   public RealScalarAggregateStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, boolean var13, int var14, double var15, double var17, ResultSetStatistics var19) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var15, var17);
      this.indexKeyOptimization = var13;
      this.rowsInput = var14;
      this.childResultSetStatistics = var19;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43Y00.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X21.U", new Object[0]) + " = " + this.rowsInput + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.indent + MessageService.getTextMessage("43Y01.U", new Object[0]) + " = " + this.indexKeyOptimization + "\n" + this.indent + MessageService.getTextMessage("43X05.U", new Object[0]) + ":\n" + this.childResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n";
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
      return MessageService.getTextMessage("43Y02.U", new Object[0]);
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
      return "AGGREGATION";
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, (String)null, (String)null, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, this.rowsInput, this.rowsSeen, (Integer)null, this.rowsFiltered, this.rowsInput - this.rowsFiltered, (Integer)null, this.indexKeyOptimization ? "Y" : "N", (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
