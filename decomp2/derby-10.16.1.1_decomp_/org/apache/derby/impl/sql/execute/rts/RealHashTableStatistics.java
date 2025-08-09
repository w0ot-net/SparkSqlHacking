package org.apache.derby.impl.sql.execute.rts;

import java.util.Enumeration;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableProperties;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINScanPropsDescriptor;
import org.apache.derby.impl.sql.execute.xplain.XPLAINUtil;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.util.ArrayUtil;

public class RealHashTableStatistics extends RealNoPutResultSetStatistics {
   public int hashtableSize;
   public int[] hashKeyColumns;
   public String isolationLevel;
   public String nextQualifiers;
   public FormatableProperties scanProperties;
   public ResultSetStatistics childResultSetStatistics;
   public ResultSetStatistics[] subqueryTrackingArray;

   public RealHashTableStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, int[] var14, String var15, Properties var16, double var17, double var19, ResultSetStatistics[] var21, ResultSetStatistics var22) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var17, var19);
      this.hashtableSize = var13;
      this.hashKeyColumns = ArrayUtil.copy(var14);
      this.nextQualifiers = var15;
      this.scanProperties = new FormatableProperties();
      if (var16 != null) {
         Enumeration var23 = var16.keys();

         while(var23.hasMoreElements()) {
            String var24 = (String)var23.nextElement();
            this.scanProperties.put(var24, var16.get(var24));
         }
      }

      this.subqueryTrackingArray = (ResultSetStatistics[])ArrayUtil.copy(var21);
      this.childResultSetStatistics = var22;
   }

   public String getStatementExecutionPlanText(int var1) {
      String var2 = "";
      this.initFormatInfo(var1);
      if (this.subqueryTrackingArray != null) {
         boolean var3 = false;

         for(int var4 = 0; var4 < this.subqueryTrackingArray.length; ++var4) {
            if (this.subqueryTrackingArray[var4] != null) {
               if (!var3) {
                  String var10000 = this.indent;
                  var2 = var10000 + MessageService.getTextMessage("43X56.U", new Object[0]) + ":\n";
                  var3 = true;
               }

               var2 = var2 + this.subqueryTrackingArray[var4].getStatementExecutionPlanText(this.sourceDepth);
            }
         }
      }

      this.initFormatInfo(var1);
      String var5;
      if (this.hashKeyColumns.length == 1) {
         String var8 = MessageService.getTextMessage("43X53.U", new Object[0]);
         var5 = var8 + " " + this.hashKeyColumns[0];
      } else {
         String var9 = MessageService.getTextMessage("43X54.U", new Object[0]);
         var5 = var9 + " (" + this.hashKeyColumns[0];

         for(int var7 = 1; var7 < this.hashKeyColumns.length; ++var7) {
            var5 = var5 + "," + this.hashKeyColumns[var7];
         }

         var5 = var5 + ")";
      }

      String var10 = this.indent;
      return var10 + MessageService.getTextMessage("43X57.U", new Object[0]) + " (" + this.resultSetNumber + "):\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X31.U", new Object[0]) + " = " + this.hashtableSize + "\n" + this.indent + var5 + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.rowsSeen + "\n" + this.indent + MessageService.getTextMessage("43X32.U", new Object[0]) + " = " + this.rowsFiltered + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + (this.rowsSeen > 0 ? this.subIndent + MessageService.getTextMessage("43X33.U", new Object[0]) + " = " + this.nextTime / (long)this.rowsSeen + "\n" : "") + "\n" + this.subIndent + MessageService.getTextMessage("43X37.U", new Object[0]) + ":\n" + this.nextQualifiers + "\n" + this.indent + MessageService.getTextMessage("43X05.U", new Object[0]) + ":\n" + this.childResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth);
   }

   public String getScanStatisticsText(String var1, int var2) {
      return var1 == null ? this.getStatementExecutionPlanText(var2) : (String)null;
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public String getNodeOn() {
      return "";
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43X58.U", new Object[0]);
   }

   public void accept(XPLAINVisitor var1) {
      int var2 = 0;
      if (this.childResultSetStatistics != null) {
         ++var2;
      }

      if (this.subqueryTrackingArray != null) {
         var2 += this.subqueryTrackingArray.length;
      }

      var1.setNumberOfChildren(var2);
      var1.visit(this);
      if (this.childResultSetStatistics != null) {
         this.childResultSetStatistics.accept(var1);
      }

      if (this.subqueryTrackingArray != null) {
         boolean var3 = false;

         for(int var4 = 0; var4 < this.subqueryTrackingArray.length; ++var4) {
            if (this.subqueryTrackingArray[var4] != null) {
               this.subqueryTrackingArray[var4].accept(var1);
            }
         }
      }

   }

   public String getRSXplainType() {
      return "HASHTABLE";
   }

   public String getRSXplainDetails() {
      return "(" + this.resultSetNumber + ")";
   }

   public Object getScanPropsDescriptor(Object var1) {
      FormatableProperties var2 = this.scanProperties;
      String var3 = XPLAINUtil.getIsolationLevelCode(this.isolationLevel);
      String var4 = XPLAINUtil.getHashKeyColumnNumberString(this.hashKeyColumns);
      XPLAINScanPropsDescriptor var5 = new XPLAINScanPropsDescriptor((UUID)var1, "Temporary HashTable", (String)null, (String)null, var3, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (String)null, (Integer)null, (Integer)null, (String)null, (String)null, (String)null, this.nextQualifiers, var4, this.hashtableSize);
      return XPLAINUtil.extractScanProps(var5, var2);
   }
}
