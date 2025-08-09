package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealSetOpResultSetStatistics extends RealNoPutResultSetStatistics {
   public int opType;
   public int rowsSeenLeft;
   public int rowsSeenRight;
   public int rowsReturned;
   public ResultSetStatistics leftResultSetStatistics;
   public ResultSetStatistics rightResultSetStatistics;

   public RealSetOpResultSetStatistics(int var1, int var2, int var3, int var4, long var5, long var7, long var9, long var11, int var13, int var14, int var15, int var16, double var17, double var19, ResultSetStatistics var21, ResultSetStatistics var22) {
      super(var2, var3, var4, var5, var7, var9, var11, var13, var17, var19);
      this.opType = var1;
      this.rowsSeenLeft = var14;
      this.rowsSeenRight = var15;
      this.rowsReturned = var16;
      this.leftResultSetStatistics = var21;
      this.rightResultSetStatistics = var22;
   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var2 = this.opType == 1 ? "INTERSECT ResultSet" : "EXCEPT ResultSet";
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage(var2, new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X79.U", new Object[0]) + " = " + this.rowsSeenLeft + "\n" + this.indent + MessageService.getTextMessage("43X80.U", new Object[0]) + " = " + this.rowsSeenRight + "\n" + this.indent + MessageService.getTextMessage("43X81.U", new Object[0]) + " = " + this.rowsReturned + "\n" + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.indent + MessageService.getTextMessage("43X82.U", new Object[0]) + ":\n" + this.leftResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n" + this.indent + MessageService.getTextMessage("43X83.U", new Object[0]) + ":\n" + this.rightResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n";
   }

   public String getScanStatisticsText(String var1, int var2) {
      String var10000 = this.leftResultSetStatistics.getScanStatisticsText(var1, var2);
      return var10000 + this.rightResultSetStatistics.getScanStatisticsText(var1, var2);
   }

   public String toString() {
      return this.getStatementExecutionPlanText(0);
   }

   public Vector getChildren() {
      Vector var1 = new Vector();
      var1.addElement(this.leftResultSetStatistics);
      var1.addElement(this.rightResultSetStatistics);
      return var1;
   }

   public String getNodeName() {
      String var1 = this.opType == 1 ? "INTERSECT" : "EXCEPT";
      return MessageService.getTextMessage(var1, new Object[0]);
   }

   public void accept(XPLAINVisitor var1) {
      int var2 = 0;
      if (this.leftResultSetStatistics != null) {
         ++var2;
      }

      if (this.rightResultSetStatistics != null) {
         ++var2;
      }

      var1.setNumberOfChildren(var2);
      var1.visit(this);
      if (this.leftResultSetStatistics != null) {
         this.leftResultSetStatistics.accept(var1);
      }

      if (this.rightResultSetStatistics != null) {
         this.rightResultSetStatistics.accept(var1);
      }

   }

   public String getRSXplainType() {
      return "SET";
   }

   public String getRSXplainDetails() {
      int var10000 = this.resultSetNumber;
      String var1 = "(" + var10000 + ")";
      var1 = var1 + (this.opType == 1 ? ", INTERSECT" : ", EXCEPT");
      return var1;
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, (String)null, (String)null, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, (Integer)null, this.rowsSeenLeft, this.rowsSeenRight, this.rowsFiltered, this.rowsReturned, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
