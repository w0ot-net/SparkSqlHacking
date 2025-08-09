package org.apache.derby.impl.sql.execute.rts;

import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetTimingsDescriptor;
import org.apache.derby.shared.common.i18n.MessageService;

abstract class RealNoRowsResultSetStatistics implements ResultSetStatistics {
   protected String indent;
   protected String subIndent;
   protected int sourceDepth;
   public ResultSetStatistics sourceResultSetStatistics;
   public long executeTime;
   public long inspectOverall;
   public long inspectNum;
   public String inspectDesc;

   public RealNoRowsResultSetStatistics(long var1, ResultSetStatistics var3) {
      if (var3 instanceof RealBasicNoPutResultSetStatistics) {
         this.executeTime = var1 - ((RealBasicNoPutResultSetStatistics)var3).getTotalTime();
      }

   }

   protected void initFormatInfo(int var1) {
      char[] var2 = new char[var1];
      char[] var3 = new char[var1 + 1];
      this.sourceDepth = var1 + 1;

      for(var3[var1] = '\t'; var1 > 0; --var1) {
         var3[var1 - 1] = '\t';
         var2[var1 - 1] = '\t';
      }

      this.indent = new String(var2);
      this.subIndent = new String(var3);
   }

   protected String dumpTimeStats(String var1) {
      return var1 + MessageService.getTextMessage("43Y29.U", new Object[0]) + " = " + this.executeTime + "\n";
   }

   public Vector getChildren() {
      Vector var1 = new Vector();
      var1.addElement(this.sourceResultSetStatistics);
      return var1;
   }

   public abstract String getNodeName();

   public double getEstimatedRowCount() {
      return (double)0.0F;
   }

   public String getRSXplainDetails() {
      return null;
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), (Integer)null, (Integer)null, (String)null, (String)null, (UUID)var2, (Double)null, (Double)null, (Integer)null, (String)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }

   public Object getResultSetTimingsDescriptor(Object var1) {
      return new XPLAINResultSetTimingsDescriptor((UUID)var1, (Long)null, (Long)null, (Long)null, (Long)null, this.executeTime, (Long)null, (Long)null, (Long)null, (Long)null, (Long)null);
   }

   public Object getSortPropsDescriptor(Object var1) {
      return null;
   }

   public Object getScanPropsDescriptor(Object var1) {
      return null;
   }
}
