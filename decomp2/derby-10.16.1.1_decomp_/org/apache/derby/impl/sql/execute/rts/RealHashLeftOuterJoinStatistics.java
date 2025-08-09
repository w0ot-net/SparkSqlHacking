package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealHashLeftOuterJoinStatistics extends RealNestedLoopLeftOuterJoinStatistics {
   public RealHashLeftOuterJoinStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, int var14, int var15, long var16, double var18, double var20, String var22, ResultSetStatistics var23, ResultSetStatistics var24, int var25) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var13, var14, var15, var16, var18, var20, var22, var23, var24, var25);
   }

   protected void setNames() {
      this.nodeName = MessageService.getTextMessage("43X49.U", new Object[0]);
      this.resultSetName = MessageService.getTextMessage("43X50.U", new Object[0]);
   }

   public String getRSXplainType() {
      return "LOHASHJOIN";
   }

   public String getRSXplainDetails() {
      String var1 = "(" + this.resultSetNumber + ")" + this.resultSetName + ", ";
      if (this.oneRowRightSide) {
         var1 = var1 + ", EXISTS JOIN";
      }

      return var1;
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, (String)null, (String)null, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, (Integer)null, this.rowsSeenLeft, this.rowsSeenRight, this.rowsFiltered, this.rowsReturned, this.emptyRightRowsReturned, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }
}
