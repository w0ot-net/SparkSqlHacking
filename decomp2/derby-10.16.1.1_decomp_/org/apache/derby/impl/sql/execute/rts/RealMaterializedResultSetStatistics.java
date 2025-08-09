package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetTimingsDescriptor;
import org.apache.derby.impl.sql.execute.xplain.XPLAINUtil;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealMaterializedResultSetStatistics extends RealNoPutResultSetStatistics {
   public ResultSetStatistics childResultSetStatistics;
   public long createTCTime;
   public long fetchTCTime;

   public RealMaterializedResultSetStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, long var12, long var14, int var16, double var17, double var19, ResultSetStatistics var21) {
      super(var1, var2, var3, var4, var6, var8, var10, var16, var17, var19);
      this.createTCTime = var12;
      this.fetchTCTime = var14;
      this.childResultSetStatistics = var21;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43X76.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X04.U", new Object[0]) + " = " + this.rowsSeen + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.subIndent + MessageService.getTextMessage("43X77.U", new Object[0]) + " = " + this.createTCTime + "\n" + this.subIndent + MessageService.getTextMessage("43X78.U", new Object[0]) + " = " + this.fetchTCTime + "\n" + this.indent + MessageService.getTextMessage("43X05.U", new Object[0]) + ":\n" + this.childResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n";
   }

   public String getScanStatisticsText(String var1, int var2) {
      return this.childResultSetStatistics.getScanStatisticsText(var1, var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public Vector getChildren() {
      Vector var1 = new Vector();
      var1.addElement(this.childResultSetStatistics);
      return var1;
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43X76.U", new Object[0]);
   }

   public void accept(XPLAINVisitor var1) {
      int var2 = 0;
      if (this.childResultSetStatistics != null) {
         ++var2;
      }

      var1.setNumberOfChildren(var2);
      var1.visit(this);
      if (this.childResultSetStatistics != null) {
         this.childResultSetStatistics.accept(var1);
      }

   }

   public String getRSXplainType() {
      return "MATERIALIZE";
   }

   public String getRSXplainDetails() {
      return "(" + this.resultSetNumber + ")";
   }

   public Object getResultSetTimingsDescriptor(Object var1) {
      return new XPLAINResultSetTimingsDescriptor((UUID)var1, this.constructorTime, this.openTime, this.nextTime, this.closeTime, this.getNodeTime(), XPLAINUtil.getAVGNextTime(this.nextTime, (long)this.rowsSeen), (Long)null, (Long)null, this.createTCTime, this.fetchTCTime);
   }
}
