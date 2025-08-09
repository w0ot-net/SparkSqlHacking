package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealDeleteVTIResultSetStatistics extends RealNoRowsResultSetStatistics {
   public int rowCount;

   public RealDeleteVTIResultSetStatistics(int var1, long var2, ResultSetStatistics var4) {
      super(var2, var4);
      this.rowCount = var1;
      this.sourceResultSetStatistics = var4;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43Y47.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X17.U", new Object[0]) + " = " + this.rowCount + "\n" + this.dumpTimeStats(this.indent) + (this.sourceResultSetStatistics == null ? "" : this.sourceResultSetStatistics.getStatementExecutionPlanText(1));
   }

   public String getScanStatisticsText(String var1, int var2) {
      return this.sourceResultSetStatistics == null ? "" : this.sourceResultSetStatistics.getScanStatisticsText(var1, var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43Y50.U", new Object[0]);
   }

   public void accept(XPLAINVisitor var1) {
      int var2 = 0;
      if (this.sourceResultSetStatistics != null) {
         ++var2;
      }

      var1.setNumberOfChildren(var2);
      var1.visit(this);
      if (this.sourceResultSetStatistics != null) {
         this.sourceResultSetStatistics.accept(var1);
      }

   }

   public String getRSXplainType() {
      return "DELETE";
   }

   public String getRSXplainDetails() {
      return "VTI";
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), (Integer)null, (Integer)null, (String)null, (String)null, (UUID)var2, (Double)null, (Double)null, this.rowCount, (String)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
