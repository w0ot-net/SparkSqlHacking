package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.impl.sql.execute.xplain.XPLAINUtil;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealInsertVTIResultSetStatistics extends RealNoRowsResultSetStatistics {
   public int rowCount;
   public boolean deferred;

   public RealInsertVTIResultSetStatistics(int var1, boolean var2, long var3, ResultSetStatistics var5) {
      super(var3, var5);
      this.rowCount = var1;
      this.deferred = var2;
      this.sourceResultSetStatistics = var5;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      if (this.sourceResultSetStatistics == null) {
         return "";
      } else {
         String var10000 = this.indent;
         return var10000 + MessageService.getTextMessage("43Y46.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X16.U", new Object[0]) + ": " + this.deferred + "\n" + this.indent + MessageService.getTextMessage("43X68.U", new Object[0]) + " = " + this.rowCount + "\n" + this.dumpTimeStats(this.indent) + (this.sourceResultSetStatistics == null ? "" : this.sourceResultSetStatistics.getStatementExecutionPlanText(1));
      }
   }

   public String getScanStatisticsText(String var1, int var2) {
      return this.sourceResultSetStatistics.getScanStatisticsText(var1, var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43Y49.U", new Object[0]);
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
      return "INSERT";
   }

   public String getRSXplainDetails() {
      return "VTI";
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), (Integer)null, (Integer)null, (String)null, (String)null, (UUID)var2, (Double)null, (Double)null, this.rowCount, XPLAINUtil.getYesNoCharFromBoolean(this.deferred), (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
