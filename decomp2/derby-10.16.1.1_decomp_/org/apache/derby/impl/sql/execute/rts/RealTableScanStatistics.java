package org.apache.derby.impl.sql.execute.rts;

import java.util.Enumeration;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableProperties;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.iapi.util.PropertyUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINScanPropsDescriptor;
import org.apache.derby.impl.sql.execute.xplain.XPLAINUtil;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealTableScanStatistics extends RealNoPutResultSetStatistics {
   public boolean isConstraint;
   public boolean coarserLock;
   public int fetchSize;
   public String isolationLevel;
   public String tableName;
   public String userSuppliedOptimizerOverrides;
   public String indexName;
   public String lockString;
   public String qualifiers;
   public String startPosition;
   public String stopPosition;
   public FormatableProperties scanProperties;

   public RealTableScanStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, String var13, String var14, String var15, boolean var16, String var17, Properties var18, String var19, String var20, String var21, String var22, int var23, boolean var24, double var25, double var27) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var25, var27);
      this.tableName = var13;
      this.userSuppliedOptimizerOverrides = var14;
      this.indexName = var15;
      this.isConstraint = var16;
      this.qualifiers = var17;
      this.scanProperties = new FormatableProperties();
      Enumeration var29 = var18.keys();

      while(var29.hasMoreElements()) {
         String var30 = (String)var29.nextElement();
         this.scanProperties.put(var30, var18.get(var30));
      }

      this.startPosition = var19;
      this.stopPosition = var20;
      this.isolationLevel = var21;
      this.lockString = var22;
      this.fetchSize = var23;
      this.coarserLock = var24;
   }

   public String getStatementExecutionPlanText(int var1) {
      String var2 = "";
      Object var3 = null;
      this.initFormatInfo(var1);
      if (this.userSuppliedOptimizerOverrides != null) {
         String var10000 = this.indent;
         var2 = var10000 + MessageService.getTextMessage("43Y56.U", new Object[]{this.tableName, this.userSuppliedOptimizerOverrides});
         var2 = var2 + "\n";
      }

      if (this.indexName != null) {
         var2 = var2 + this.indent + MessageService.getTextMessage("43Y09.U", new Object[]{this.tableName, this.isConstraint ? "constraint" : "index", this.indexName});
      } else {
         var2 = var2 + this.indent + MessageService.getTextMessage("43Y10.U", new Object[]{this.tableName});
      }

      var2 = var2 + " " + MessageService.getTextMessage("43X72.U", new Object[]{this.isolationLevel, this.lockString});
      if (this.coarserLock) {
         var2 = var2 + " (" + MessageService.getTextMessage("43Y11.U", new Object[0]) + ")";
      }

      var2 = var2 + "\n";
      String var9 = this.indent;
      String var4 = var9 + MessageService.getTextMessage("43X28.U", new Object[0]) + ":\n" + PropertyUtil.sortProperties(this.scanProperties, this.subIndent);
      return var2 + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.rowsSeen + "\n" + this.indent + MessageService.getTextMessage("43X32.U", new Object[0]) + " = " + this.rowsFiltered + "\n" + this.indent + MessageService.getTextMessage("43Y12.U", new Object[0]) + " = " + this.fetchSize + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + (this.rowsSeen > 0 ? this.subIndent + MessageService.getTextMessage("43X33.U", new Object[0]) + " = " + this.nextTime / (long)this.rowsSeen + "\n" : "") + "\n" + var4 + this.subIndent + MessageService.getTextMessage("43X34.U", new Object[0]) + ":\n" + StringUtil.ensureIndent(this.startPosition, var1 + 2) + "\n" + this.subIndent + MessageService.getTextMessage("43X35.U", new Object[0]) + ":\n" + StringUtil.ensureIndent(this.stopPosition, var1 + 2) + "\n" + this.subIndent + MessageService.getTextMessage("43Y13.U", new Object[0]) + ":\n" + StringUtil.ensureIndent(this.qualifiers, var1 + 2) + "\n" + this.dumpEstimatedCosts(this.subIndent);
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
      if (this.indexName != null) {
         return this.isConstraint ? "CONSTRAINTSCAN" : "INDEXSCAN";
      } else {
         return "TABLESCAN";
      }
   }

   public String getRSXplainDetails() {
      if (this.indexName != null) {
         String var10000 = this.isConstraint ? "C: " : "I: ";
         return var10000 + this.indexName;
      } else {
         return "T: " + this.tableName;
      }
   }

   public Object getScanPropsDescriptor(Object var1) {
      String var2;
      String var3;
      if (this.indexName != null) {
         if (this.isConstraint) {
            var2 = "C";
            var3 = this.indexName;
         } else {
            var2 = "I";
            var3 = this.indexName;
         }
      } else {
         var2 = "T";
         var3 = this.tableName;
      }

      String var4 = XPLAINUtil.getIsolationLevelCode(this.isolationLevel);
      XPLAINScanPropsDescriptor var5 = new XPLAINScanPropsDescriptor((UUID)var1, var3, var2, (String)null, var4, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (String)null, (Integer)null, this.fetchSize, this.startPosition, this.stopPosition, this.qualifiers, (String)null, (String)null, (Integer)null);
      FormatableProperties var6 = this.scanProperties;
      return XPLAINUtil.extractScanProps(var5, var6);
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      String var7 = XPLAINUtil.getLockModeCode(this.lockString);
      String var8 = XPLAINUtil.getLockGranularityCode(this.lockString);
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, var7, var8, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, (Integer)null, this.rowsSeen, (Integer)null, this.rowsFiltered, this.rowsSeen - this.rowsFiltered, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
