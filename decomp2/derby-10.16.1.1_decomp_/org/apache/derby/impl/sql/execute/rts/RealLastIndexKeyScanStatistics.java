package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINScanPropsDescriptor;
import org.apache.derby.impl.sql.execute.xplain.XPLAINUtil;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealLastIndexKeyScanStatistics extends RealNoPutResultSetStatistics {
   public String isolationLevel;
   public String tableName;
   public String indexName;
   public String lockString;

   public RealLastIndexKeyScanStatistics(int var1, long var2, long var4, long var6, long var8, int var10, String var11, String var12, String var13, String var14, double var15, double var17) {
      super(var1, 1, 0, var2, var4, var6, var8, var10, var15, var17);
      this.tableName = var11;
      this.indexName = var12;
      this.isolationLevel = var13;
      this.lockString = var14;
   }

   public String getStatementExecutionPlanText(int var1) {
      Object var3 = null;
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      String var2 = var10000 + MessageService.getTextMessage("43X71.U", new Object[]{this.tableName, this.indexName});
      var2 = var2 + MessageService.getTextMessage("43X72.U", new Object[]{this.isolationLevel, this.lockString});
      var2 = var2 + "\n";
      return var2 + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.numOpens + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + (this.rowsSeen > 0 ? this.subIndent + MessageService.getTextMessage("43X33.U", new Object[0]) + " = " + this.nextTime / (long)this.numOpens + "\n" : "") + "\n" + this.dumpEstimatedCosts(this.subIndent);
   }

   public String getScanStatisticsText(String var1, int var2) {
      return var1 != null && !var1.equals(this.tableName) ? "" : this.getStatementExecutionPlanText(var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public String getNodeName() {
      return MessageService.getTextMessage(this.indexName == null ? "43X73.U" : "43X74.U", new Object[0]);
   }

   public String getNodeOn() {
      return this.indexName == null ? MessageService.getTextMessage("43X75.U", new Object[]{this.tableName}) : MessageService.getTextMessage("43X38.U", new Object[]{this.tableName, this.indexName});
   }

   public void accept(XPLAINVisitor var1) {
      var1.setNumberOfChildren(0);
      var1.visit(this);
   }

   public String getRSXplainType() {
      return "LASTINDEXKEYSCAN";
   }

   public String getRSXplainDetails() {
      return "I: " + this.indexName + ", T: " + this.tableName;
   }

   public Object getScanPropsDescriptor(Object var1) {
      String var2 = XPLAINUtil.getIsolationLevelCode(this.isolationLevel);
      XPLAINScanPropsDescriptor var3 = new XPLAINScanPropsDescriptor((UUID)var1, this.indexName, "I", (String)null, var2, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (String)null, (Integer)null, (Integer)null, (String)null, (String)null, (String)null, (String)null, (String)null, (Integer)null);
      return var3;
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      String var7 = XPLAINUtil.getLockModeCode(this.lockString);
      String var8 = XPLAINUtil.getLockGranularityCode(this.lockString);
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, var7, var8, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, (Integer)null, this.rowsSeen, (Integer)null, this.rowsFiltered, this.rowsSeen - this.rowsFiltered, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
