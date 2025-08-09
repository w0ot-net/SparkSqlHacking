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
import org.apache.derby.shared.common.util.ArrayUtil;

public class RealHashScanStatistics extends RealNoPutResultSetStatistics {
   public boolean isConstraint;
   public int hashtableSize;
   public int[] hashKeyColumns;
   public String isolationLevel;
   public String lockString;
   public String tableName;
   public String indexName;
   public String nextQualifiers;
   public String scanQualifiers;
   public String startPosition = null;
   public String stopPosition = null;
   public FormatableProperties scanProperties;

   public RealHashScanStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, String var13, String var14, boolean var15, int var16, int[] var17, String var18, String var19, Properties var20, String var21, String var22, String var23, String var24, double var25, double var27) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var25, var27);
      this.tableName = var13;
      this.indexName = var14;
      this.isConstraint = var15;
      this.hashtableSize = var16;
      this.hashKeyColumns = ArrayUtil.copy(var17);
      this.scanQualifiers = var18;
      this.nextQualifiers = var19;
      this.scanProperties = new FormatableProperties();
      if (var20 != null) {
         Enumeration var29 = var20.keys();

         while(var29.hasMoreElements()) {
            String var30 = (String)var29.nextElement();
            this.scanProperties.put(var30, var20.get(var30));
         }
      }

      this.startPosition = var21;
      this.stopPosition = var22;
      this.isolationLevel = var23;
      this.lockString = var24;
   }

   public String getStatementExecutionPlanText(int var1) {
      Object var3 = null;
      this.initFormatInfo(var1);
      String var2;
      if (this.indexName != null) {
         String var10000 = this.indent;
         var2 = var10000 + MessageService.getTextMessage("43X51.U", new Object[]{this.tableName, this.isConstraint ? "constraint" : "index", this.indexName});
      } else {
         String var9 = this.indent;
         var2 = var9 + MessageService.getTextMessage("43X52.U", new Object[]{this.tableName});
      }

      var2 = var2 + " " + MessageService.getTextMessage("43X27.U", new Object[]{this.isolationLevel, this.lockString}) + ": \n";
      String var10 = this.indent;
      String var4 = var10 + MessageService.getTextMessage("43X28.U", new Object[0]) + ": \n" + PropertyUtil.sortProperties(this.scanProperties, this.subIndent);
      String var5;
      if (this.hashKeyColumns.length == 1) {
         var10 = MessageService.getTextMessage("43X53.U", new Object[0]);
         var5 = var10 + " " + this.hashKeyColumns[0];
      } else {
         var10 = MessageService.getTextMessage("43X54.U", new Object[0]);
         var5 = var10 + " (" + this.hashKeyColumns[0];

         for(int var6 = 1; var6 < this.hashKeyColumns.length; ++var6) {
            var5 = var5 + "," + this.hashKeyColumns[var6];
         }

         var5 = var5 + ")";
      }

      return var2 + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X31.U", new Object[0]) + " = " + this.hashtableSize + "\n" + this.indent + var5 + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.rowsSeen + "\n" + this.indent + MessageService.getTextMessage("43X32.U", new Object[0]) + " = " + this.rowsFiltered + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + (this.rowsSeen > 0 ? this.subIndent + MessageService.getTextMessage("43X33.U", new Object[0]) + " = " + this.nextTime / (long)this.rowsSeen + "\n" : "") + "\n" + var4 + this.subIndent + MessageService.getTextMessage("43X34.U", new Object[0]) + ":\n" + StringUtil.ensureIndent(this.startPosition, var1 + 2) + "\n" + this.subIndent + MessageService.getTextMessage("43X35.U", new Object[0]) + ":\n" + StringUtil.ensureIndent(this.stopPosition, var1 + 2) + "\n" + this.subIndent + MessageService.getTextMessage("43X36.U", new Object[0]) + ":\n" + StringUtil.ensureIndent(this.scanQualifiers, var1 + 2) + "\n" + this.subIndent + MessageService.getTextMessage("43X37.U", new Object[0]) + ":\n" + StringUtil.ensureIndent(this.nextQualifiers, var1 + 2) + "\n" + this.dumpEstimatedCosts(this.subIndent);
   }

   public String getScanStatisticsText(String var1, int var2) {
      return var1 != null && !var1.equals(this.tableName) ? "" : this.getStatementExecutionPlanText(var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public String getNodeOn() {
      return MessageService.getTextMessage("43X38.U", new Object[]{this.tableName, this.indexName});
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43X55.U", new Object[0]);
   }

   public void accept(XPLAINVisitor var1) {
      var1.setNumberOfChildren(0);
      var1.visit(this);
   }

   public String getRSXplainType() {
      return "HASHSCAN";
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
      String var5 = XPLAINUtil.getHashKeyColumnNumberString(this.hashKeyColumns);
      XPLAINScanPropsDescriptor var6 = new XPLAINScanPropsDescriptor((UUID)var1, var3, var2, (String)null, var4, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (String)null, (Integer)null, (Integer)null, this.startPosition, this.stopPosition, this.scanQualifiers, this.nextQualifiers, var5, this.hashtableSize);
      FormatableProperties var7 = this.scanProperties;
      return XPLAINUtil.extractScanProps(var6, var7);
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      String var7 = XPLAINUtil.getLockModeCode(this.lockString);
      String var8 = XPLAINUtil.getLockGranularityCode(this.lockString);
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, var7, var8, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, (Integer)null, this.rowsSeen, (Integer)null, this.rowsFiltered, this.rowsSeen - this.rowsFiltered, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
