package org.apache.derby.impl.sql.execute.rts;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.iapi.util.PropertyUtil;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINSortPropsDescriptor;
import org.apache.derby.impl.sql.execute.xplain.XPLAINUtil;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealGroupedAggregateStatistics extends RealNoPutResultSetStatistics {
   public int rowsInput;
   public boolean hasDistinctAggregate;
   public boolean inSortedOrder;
   public ResultSetStatistics childResultSetStatistics;
   public Properties sortProperties;

   public RealGroupedAggregateStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, boolean var14, boolean var15, Properties var16, double var17, double var19, ResultSetStatistics var21) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var17, var19);
      this.rowsInput = var13;
      this.hasDistinctAggregate = var14;
      this.inSortedOrder = var15;
      this.childResultSetStatistics = var21;
      this.sortProperties = new Properties();
      Enumeration var22 = var16.keys();

      while(var22.hasMoreElements()) {
         String var23 = (String)var22.nextElement();
         this.sortProperties.put(var23, var16.get(var23));
      }

   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var2 = this.inSortedOrder ? "" : this.indent + MessageService.getTextMessage("43X40.U", new Object[0]) + ": \n" + PropertyUtil.sortProperties(this.sortProperties, this.subIndent);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43X41.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X21.U", new Object[0]) + " = " + this.rowsInput + "\n" + this.indent + MessageService.getTextMessage("43X42.U", new Object[0]) + " = " + this.hasDistinctAggregate + "\n" + this.indent + MessageService.getTextMessage("43X43.U", new Object[0]) + " = " + this.inSortedOrder + "\n" + var2 + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.indent + MessageService.getTextMessage("43X05.U", new Object[0]) + ":\n" + this.childResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n";
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
      return MessageService.getTextMessage("43X44.U", new Object[0]);
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
      return "GROUPBY";
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, (String)null, (String)null, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, this.rowsInput, this.rowsSeen, (Integer)null, this.rowsFiltered, (Integer)null, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }

   public Object getSortPropsDescriptor(Object var1) {
      Properties var2 = this.sortProperties;
      XPLAINSortPropsDescriptor var3 = new XPLAINSortPropsDescriptor((UUID)var1, (String)null, (Integer)null, (Integer)null, (Integer)null, (String)null, (String)null, XPLAINUtil.getYesNoCharFromBoolean(this.inSortedOrder), XPLAINUtil.getYesNoCharFromBoolean(this.hasDistinctAggregate));
      return XPLAINUtil.extractSortProps(var3, var2);
   }
}
