package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.impl.sql.execute.xplain.XPLAINUtil;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealInsertResultSetStatistics extends RealNoRowsResultSetStatistics {
   public int rowCount;
   public boolean deferred;
   public int indexesUpdated;
   public boolean userSpecifiedBulkInsert;
   public boolean bulkInsertPerformed;
   public boolean tableLock;

   public RealInsertResultSetStatistics(int var1, boolean var2, int var3, boolean var4, boolean var5, boolean var6, long var7, ResultSetStatistics var9) {
      super(var7, var9);
      this.rowCount = var1;
      this.deferred = var2;
      this.indexesUpdated = var3;
      this.userSpecifiedBulkInsert = var4;
      this.bulkInsertPerformed = var5;
      this.tableLock = var6;
      this.sourceResultSetStatistics = var9;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var2;
      if (this.userSpecifiedBulkInsert) {
         if (this.bulkInsertPerformed) {
            String var10000 = this.indent;
            var2 = var10000 + MessageService.getTextMessage("43X64.U", new Object[0]);
         } else {
            String var4 = this.indent;
            var2 = var4 + MessageService.getTextMessage("43X65.U", new Object[0]);
         }
      } else {
         String var5 = this.indent;
         var2 = var5 + MessageService.getTextMessage("43X66.U", new Object[0]);
      }

      var2 = var2 + "\n";
      String var6 = this.indent;
      return var6 + MessageService.getTextMessage("43X67.U", new Object[0]) + " " + MessageService.getTextMessage(this.tableLock ? "43X14.U" : "43X15.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X16.U", new Object[0]) + ": " + this.deferred + "\n" + var2 + this.indent + MessageService.getTextMessage("43X68.U", new Object[0]) + " = " + this.rowCount + "\n" + this.indent + MessageService.getTextMessage("43X18.U", new Object[0]) + " = " + this.indexesUpdated + "\n" + this.dumpTimeStats(this.indent) + (this.sourceResultSetStatistics == null ? null : this.sourceResultSetStatistics.getStatementExecutionPlanText(1));
   }

   public String getScanStatisticsText(String var1, int var2) {
      return this.sourceResultSetStatistics == null ? null : this.sourceResultSetStatistics.getScanStatisticsText(var1, var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43X69.U", new Object[0]);
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
      return this.bulkInsertPerformed ? "BULK" : null;
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), (Integer)null, this.indexesUpdated, (String)null, this.tableLock ? "T" : "R", (UUID)var2, (Double)null, (Double)null, this.rowCount, XPLAINUtil.getYesNoCharFromBoolean(this.deferred), (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
