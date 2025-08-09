package org.apache.derby.impl.sql.execute.rts;

import java.sql.Timestamp;
import java.util.Vector;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.RunTimeStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.iapi.types.DataTypeUtilities;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.util.ArrayUtil;

public final class RunTimeStatisticsImpl implements RunTimeStatistics {
   public String statementText;
   public String statementName;
   public String spsName;
   public long parseTime;
   public long bindTime;
   public long optimizeTime;
   public long generateTime;
   public long compileTime;
   public long executeTime;
   public Timestamp beginCompilationTimestamp;
   public Timestamp endCompilationTimestamp;
   public Timestamp beginExecutionTimestamp;
   public Timestamp endExecutionTimestamp;
   public ResultSetStatistics topResultSetStatistics;
   public ResultSetStatistics[] subqueryTrackingArray;

   public RunTimeStatisticsImpl(String var1, String var2, String var3, long var4, long var6, long var8, long var10, long var12, long var14, Timestamp var16, Timestamp var17, Timestamp var18, Timestamp var19, ResultSetStatistics[] var20, ResultSetStatistics var21) {
      this.spsName = var1;
      this.statementName = var2;
      this.statementText = var3;
      this.compileTime = var4;
      this.parseTime = var6;
      this.bindTime = var8;
      this.optimizeTime = var10;
      this.generateTime = var12;
      this.executeTime = var14;
      this.beginCompilationTimestamp = DataTypeUtilities.clone(var16);
      this.endCompilationTimestamp = DataTypeUtilities.clone(var17);
      this.beginExecutionTimestamp = DataTypeUtilities.clone(var18);
      this.endExecutionTimestamp = DataTypeUtilities.clone(var19);
      this.subqueryTrackingArray = (ResultSetStatistics[])ArrayUtil.copy(var20);
      this.topResultSetStatistics = var21;
   }

   public long getCompileTimeInMillis() {
      return this.compileTime;
   }

   public long getParseTimeInMillis() {
      return this.parseTime;
   }

   public long getBindTimeInMillis() {
      return this.bindTime;
   }

   public long getOptimizeTimeInMillis() {
      return this.optimizeTime;
   }

   public long getGenerateTimeInMillis() {
      return this.generateTime;
   }

   public long getExecuteTimeInMillis() {
      return this.executeTime;
   }

   public Timestamp getBeginCompilationTimestamp() {
      return DataTypeUtilities.clone(this.beginCompilationTimestamp);
   }

   public Timestamp getEndCompilationTimestamp() {
      return DataTypeUtilities.clone(this.endCompilationTimestamp);
   }

   public Timestamp getBeginExecutionTimestamp() {
      return DataTypeUtilities.clone(this.beginExecutionTimestamp);
   }

   public Timestamp getEndExecutionTimestamp() {
      return DataTypeUtilities.clone(this.endExecutionTimestamp);
   }

   public String getStatementName() {
      return this.statementName;
   }

   public String getSPSName() {
      return this.spsName;
   }

   public String getStatementText() {
      return this.statementText;
   }

   public double getEstimatedRowCount() {
      return this.topResultSetStatistics == null ? (double)0.0F : this.topResultSetStatistics.getEstimatedRowCount();
   }

   public String getStatementExecutionPlanText() {
      if (this.topResultSetStatistics == null) {
         return (String)null;
      } else {
         String var1 = "";
         if (this.subqueryTrackingArray != null) {
            boolean var2 = false;

            for(int var3 = 0; var3 < this.subqueryTrackingArray.length; ++var3) {
               if (this.subqueryTrackingArray[var3] != null) {
                  if (!var2) {
                     var1 = MessageService.getTextMessage("43Y21.U", new Object[0]) + ":\n";
                     var2 = true;
                  }

                  var1 = var1 + this.subqueryTrackingArray[var3].getStatementExecutionPlanText(1);
               }
            }
         }

         return var1 + this.topResultSetStatistics.getStatementExecutionPlanText(0);
      }
   }

   public String getScanStatisticsText() {
      return this.topResultSetStatistics == null ? (String)null : this.topResultSetStatistics.getScanStatisticsText((String)null, 0);
   }

   public String getScanStatisticsText(String var1) {
      if (this.topResultSetStatistics == null) {
         return (String)null;
      } else {
         String var2 = this.topResultSetStatistics.getScanStatisticsText(var1, 0);
         return var2.equals("") ? null : var2;
      }
   }

   public String toString() {
      String var1 = this.spsName != null ? "Stored Prepared Statement Name: \n\t" + this.spsName + "\n" : "";
      return var1 + MessageService.getTextMessage("43Y22.U", new Object[0]) + ": \n\t" + this.statementName + "\n" + MessageService.getTextMessage("43Y23.U", new Object[0]) + ": \n\t" + this.statementText + "\n" + MessageService.getTextMessage("43Y24.U", new Object[0]) + ": " + this.parseTime + "\n" + MessageService.getTextMessage("43Y25.U", new Object[0]) + ": " + this.bindTime + "\n" + MessageService.getTextMessage("43Y26.U", new Object[0]) + ": " + this.optimizeTime + "\n" + MessageService.getTextMessage("43Y27.U", new Object[0]) + ": " + this.generateTime + "\n" + MessageService.getTextMessage("43Y28.U", new Object[0]) + ": " + this.compileTime + "\n" + MessageService.getTextMessage("43Y29.U", new Object[0]) + ": " + this.executeTime + "\n" + MessageService.getTextMessage("43Y30.U", new Object[0]) + " : " + this.beginCompilationTimestamp + "\n" + MessageService.getTextMessage("43Y31.U", new Object[0]) + " : " + this.endCompilationTimestamp + "\n" + MessageService.getTextMessage("43Y32.U", new Object[0]) + " : " + this.beginExecutionTimestamp + "\n" + MessageService.getTextMessage("43Y33.U", new Object[0]) + " : " + this.endExecutionTimestamp + "\n" + MessageService.getTextMessage("43Y44.U", new Object[0]) + ": \n" + this.getStatementExecutionPlanText();
   }

   public Vector getChildren() {
      Vector var1 = new Vector();
      var1.addElement(this.topResultSetStatistics);
      return var1;
   }

   public void acceptFromTopResultSet(XPLAINVisitor var1) {
      if (this.topResultSetStatistics != null) {
         this.topResultSetStatistics.accept(var1);
      }

   }
}
