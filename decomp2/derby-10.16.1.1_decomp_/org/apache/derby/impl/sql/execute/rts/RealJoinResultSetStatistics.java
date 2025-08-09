package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.catalog.UUID;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetTimingsDescriptor;
import org.apache.derby.impl.sql.execute.xplain.XPLAINUtil;
import org.apache.derby.shared.common.i18n.MessageService;

public abstract class RealJoinResultSetStatistics extends RealNoPutResultSetStatistics {
   public int rowsSeenLeft;
   public int rowsSeenRight;
   public int rowsReturned;
   public long restrictionTime;
   public String userSuppliedOptimizerOverrides;

   public RealJoinResultSetStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, int var14, int var15, long var16, double var18, double var20, String var22) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var18, var20);
      this.rowsSeenLeft = var13;
      this.rowsSeenRight = var14;
      this.rowsReturned = var15;
      this.restrictionTime = var16;
      this.userSuppliedOptimizerOverrides = var22;
   }

   public String getNodeName() {
      return MessageService.getTextMessage("43X70.U", new Object[0]);
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, (String)null, (String)null, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, (Integer)null, this.rowsSeenLeft, this.rowsSeenRight, this.rowsFiltered, this.rowsReturned, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }

   public Object getResultSetTimingsDescriptor(Object var1) {
      return new XPLAINResultSetTimingsDescriptor((UUID)var1, this.constructorTime, this.openTime, this.nextTime, this.closeTime, this.getNodeTime(), XPLAINUtil.getAVGNextTime(this.nextTime, (long)(this.rowsSeenLeft + this.rowsSeenRight)), (Long)null, (Long)null, (Long)null, (Long)null);
   }
}
