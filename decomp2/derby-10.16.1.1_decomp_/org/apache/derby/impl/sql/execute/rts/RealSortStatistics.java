package org.apache.derby.impl.sql.execute.rts;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableProperties;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.iapi.util.PropertyUtil;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINSortPropsDescriptor;
import org.apache.derby.impl.sql.execute.xplain.XPLAINUtil;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealSortStatistics extends RealNoPutResultSetStatistics {
   public int rowsInput;
   public int rowsReturned;
   public boolean eliminateDuplicates;
   public boolean inSortedOrder;
   public ResultSetStatistics childResultSetStatistics;
   public FormatableProperties sortProperties;

   public RealSortStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, int var14, boolean var15, boolean var16, Properties var17, double var18, double var20, ResultSetStatistics var22) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var18, var20);
      this.rowsInput = var13;
      this.rowsReturned = var14;
      this.eliminateDuplicates = var15;
      this.inSortedOrder = var16;
      this.childResultSetStatistics = var22;
      this.sortProperties = new FormatableProperties();
      Enumeration var23 = var17.keys();

      while(var23.hasMoreElements()) {
         String var24 = (String)var23.nextElement();
         this.sortProperties.put(var24, var17.get(var24));
      }

   }

   public String getStatementExecutionPlanText(int var1) {
      this.initFormatInfo(var1);
      String var2 = this.inSortedOrder ? "" : this.indent + MessageService.getTextMessage("43X40.U", new Object[0]) + ": \n" + PropertyUtil.sortProperties(this.sortProperties, this.subIndent);
      String var10000 = this.indent;
      return var10000 + MessageService.getTextMessage("43Y06.U", new Object[0]) + ":\n" + this.indent + MessageService.getTextMessage("43X03.U", new Object[0]) + " = " + this.numOpens + "\n" + this.indent + MessageService.getTextMessage("43X21.U", new Object[0]) + " = " + this.rowsInput + "\n" + this.indent + MessageService.getTextMessage("43X81.U", new Object[0]) + " = " + this.rowsReturned + "\n" + this.indent + MessageService.getTextMessage("43Y07.U", new Object[0]) + " = " + this.eliminateDuplicates + "\n" + this.indent + MessageService.getTextMessage("43X43.U", new Object[0]) + " = " + this.inSortedOrder + "\n" + var2 + this.dumpTimeStats(this.indent, this.subIndent) + "\n" + this.dumpEstimatedCosts(this.subIndent) + "\n" + this.indent + MessageService.getTextMessage("43X05.U", new Object[0]) + ":\n" + this.childResultSetStatistics.getStatementExecutionPlanText(this.sourceDepth) + "\n";
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
      return MessageService.getTextMessage("43Y08.U", new Object[0]);
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
      return "SORT";
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, (String)null, (String)null, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, this.rowsInput, this.rowsSeen, (Integer)null, this.rowsFiltered, this.rowsReturned, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }

   public Object getSortPropsDescriptor(Object var1) {
      FormatableProperties var2 = this.sortProperties;
      XPLAINSortPropsDescriptor var3 = new XPLAINSortPropsDescriptor((UUID)var1, (String)null, (Integer)null, (Integer)null, (Integer)null, (String)null, XPLAINUtil.getYesNoCharFromBoolean(this.eliminateDuplicates), XPLAINUtil.getYesNoCharFromBoolean(this.inSortedOrder), (String)null);
      return XPLAINUtil.extractSortProps(var3, var2);
   }
}
