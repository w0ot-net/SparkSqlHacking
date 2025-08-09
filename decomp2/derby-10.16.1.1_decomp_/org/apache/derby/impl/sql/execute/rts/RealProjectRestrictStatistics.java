package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetTimingsDescriptor;
import org.apache.derby.impl.sql.execute.xplain.XPLAINUtil;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.util.ArrayUtil;

public class RealProjectRestrictStatistics extends RealNoPutResultSetStatistics {
   public boolean doesProjection;
   public boolean restriction;
   public long restrictionTime;
   public long projectionTime;
   public ResultSetStatistics childResultSetStatistics;
   public ResultSetStatistics[] subqueryTrackingArray;

   public RealProjectRestrictStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, long var13, long var15, ResultSetStatistics[] var17, boolean var18, boolean var19, double var20, double var22, ResultSetStatistics var24) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var20, var22);
      this.restriction = var18;
      this.doesProjection = var19;
      this.restrictionTime = var13;
      this.projectionTime = var15;
      this.subqueryTrackingArray = (ResultSetStatistics[])ArrayUtil.copy(var17);
      this.childResultSetStatistics = var24;
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

      return var2 + this.indent + MessageService.getTextMessage("43X93.U", new Object[0]) + " (" + this.resultSetNumber + "):\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.rowsSeen + "\n" + this.indent + MessageService.getTextMessage("43X32.U", new Object[0]) + " = " + this.rowsFiltered + "\n" + this.indent + MessageService.getTextMessage("43X94.U", new Object[0]) + " = " + this.restriction + "\n" + this.indent + MessageService.getTextMessage("43X95.U", new Object[0]) + " = " + this.doesProjection + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.subIndent + MessageService.getTextMessage("43X96.U", new Object[0]) + " = " + this.restrictionTime + "\n" + this.subIndent + MessageService.getTextMessage("43X97.U", new Object[0]) + " = " + this.projectionTime + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.indent + MessageService.getTextMessage("43X05.U", new Object[0]) + ":\n" + this.childResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth);
   }

   public String getScanStatisticsText(String var1, int var2) {
      String var3 = "";
      if (this.subqueryTrackingArray != null) {
         for(int var4 = 0; var4 < this.subqueryTrackingArray.length; ++var4) {
            if (this.subqueryTrackingArray[var4] != null) {
               var3 = var3 + "\n" + MessageService.getTextMessage("43X01.U", new Object[0]) + " " + var4 + "\n" + this.subqueryTrackingArray[var4].getScanStatisticsText(var1, var2) + MessageService.getTextMessage("43X06.U", new Object[0]) + " " + var4 + "\n\n";
            }
         }
      }

      return var3 + this.childResultSetStatistics.getScanStatisticsText(var1, var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public Vector getChildren() {
      Vector var1 = new Vector();
      var1.addElement(this.childResultSetStatistics);
      if (this.subqueryTrackingArray != null) {
         for(int var2 = 0; var2 < this.subqueryTrackingArray.length; ++var2) {
            if (this.subqueryTrackingArray[var2] != null) {
               var1.addElement(this.subqueryTrackingArray[var2]);
            }
         }
      }

      return var1;
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43X98.U", new Object[0]);
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
      if (this.restriction && this.doesProjection) {
         return "PROJECT-FILTER";
      } else if (this.doesProjection) {
         return "PROJECTION";
      } else {
         return this.restriction ? "FILTER" : "PROJECT-FILTER";
      }
   }

   public String getRSXplainDetails() {
      return this.resultSetNumber + ";";
   }

   public Object getResultSetTimingsDescriptor(Object var1) {
      return new XPLAINResultSetTimingsDescriptor((UUID)var1, this.constructorTime, this.openTime, this.nextTime, this.closeTime, this.getNodeTime(), XPLAINUtil.getAVGNextTime(this.nextTime, (long)this.rowsSeen), this.projectionTime, this.restrictionTime, (Long)null, (Long)null);
   }
}
