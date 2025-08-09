package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.util.ArrayUtil;

public class RealDeleteCascadeResultSetStatistics extends RealDeleteResultSetStatistics {
   private ResultSetStatistics[] dependentTrackingArray;

   public RealDeleteCascadeResultSetStatistics(int var1, boolean var2, int var3, boolean var4, long var5, ResultSetStatistics var7, ResultSetStatistics[] var8) {
      super(var1, var2, var3, var4, var5, var7);
      this.dependentTrackingArray = (ResultSetStatistics[])ArrayUtil.copy(var8);
   }

   public String getStatementExecutionPlanText(int var1) {
      String var2 = "";
      this.initFormatInfo(var1);
      if (this.dependentTrackingArray != null) {
         boolean var3 = false;

         for(int var4 = 0; var4 < this.dependentTrackingArray.length; ++var4) {
            if (this.dependentTrackingArray[var4] != null) {
               if (!var3) {
                  String var10000 = this.indent;
                  var2 = var10000 + "\n" + MessageService.getTextMessage("43Y53.U", new Object[0]) + ":\n";
                  var3 = true;
               }

               var2 = var2 + this.dependentTrackingArray[var4].getStatementExecutionPlanText(this.sourceDepth);
            }
         }
      }

      String var5 = this.indent;
      return var5 + MessageService.getTextMessage("43Y52.U", new Object[0]) + " " + MessageService.getTextMessage(this.tableLock ? "43X14.U" : "43X15.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X16.U", new Object[0]) + ": " + this.deferred + "\n" + this.indent + MessageService.getTextMessage("43X17.U", new Object[0]) + " = " + this.rowCount + "\n" + this.indent + MessageService.getTextMessage("43X18.U", new Object[0]) + " = " + this.indexesUpdated + "\n" + this.dumpTimeStats(this.indent) + (this.sourceResultSetStatistics == null ? "" : this.sourceResultSetStatistics.getStatementExecutionPlanText(1)) + var2;
   }

   public String getScanStatisticsText(String var1, int var2) {
      String var3 = "";
      if (this.dependentTrackingArray != null) {
         for(int var4 = 0; var4 < this.dependentTrackingArray.length; ++var4) {
            if (this.dependentTrackingArray[var4] != null) {
               var3 = var3 + "\n" + MessageService.getTextMessage("43Y54.U", new Object[0]) + " " + var4 + "\n" + this.dependentTrackingArray[var4].getScanStatisticsText(var1, var2) + MessageService.getTextMessage("43Y55.U", new Object[0]) + " " + var4 + "\n\n";
            }
         }
      }

      return var3 + (this.sourceResultSetStatistics == null ? "" : this.sourceResultSetStatistics.getScanStatisticsText(var1, var2));
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43Y51.U", new Object[0]);
   }

   public void accept(XPLAINVisitor var1) {
      int var2 = 0;
      if (this.sourceResultSetStatistics != null) {
         ++var2;
      }

      if (this.dependentTrackingArray != null) {
         var2 += this.dependentTrackingArray.length;
      }

      var1.setNumberOfChildren(var2);
      var1.visit(this);
      if (this.sourceResultSetStatistics != null) {
         this.sourceResultSetStatistics.accept(var1);
      }

      if (this.dependentTrackingArray != null) {
         boolean var3 = false;

         for(int var4 = 0; var4 < this.dependentTrackingArray.length; ++var4) {
            if (this.dependentTrackingArray[var4] != null) {
               this.dependentTrackingArray[var4].accept(var1);
            }
         }
      }

   }

   public String getRSXplainDetails() {
      return "CASCADE";
   }
}
