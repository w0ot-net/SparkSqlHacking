package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealRowResultSetStatistics extends RealNoPutResultSetStatistics {
   public int rowsReturned;

   public RealRowResultSetStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, double var14, double var16) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var14, var16);
      this.rowsReturned = var13;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43X99.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X81.U", new Object[0]) + " = " + this.rowsReturned + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n";
   }

   public String getScanStatisticsText(String var1, int var2) {
      return "";
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43X99.U", new Object[0]);
   }

   public void accept(XPLAINVisitor var1) {
      var1.setNumberOfChildren(0);
      var1.visit(this);
   }

   public String getRSXplainType() {
      return "ROW";
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, (String)null, (String)null, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, (Integer)null, this.rowsSeen, (Integer)null, this.rowsFiltered, this.rowsReturned, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
