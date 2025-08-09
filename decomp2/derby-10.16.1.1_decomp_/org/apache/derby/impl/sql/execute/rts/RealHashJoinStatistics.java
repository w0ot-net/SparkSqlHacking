package org.apache.derby.impl.sql.execute.rts;

import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealHashJoinStatistics extends RealNestedLoopJoinStatistics {
   public RealHashJoinStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, int var13, int var14, int var15, long var16, boolean var18, double var19, double var21, String var23, ResultSetStatistics var24, ResultSetStatistics var25) {
      super(var1, var2, var3, var4, var6, var8, var10, var12, var13, var14, var15, var16, var18, var19, var21, var23, var24, var25);
   }

   protected void setNames() {
      if (this.oneRowRightSide) {
         this.nodeName = MessageService.getTextMessage("43X45.U", new Object[0]);
         this.resultSetName = MessageService.getTextMessage("43X46.U", new Object[0]);
      } else {
         this.nodeName = MessageService.getTextMessage("43X47.U", new Object[0]);
         this.resultSetName = MessageService.getTextMessage("43X48.U", new Object[0]);
      }

   }

   public String getRSXplainType() {
      return "HASHJOIN";
   }

   public String getRSXplainDetails() {
      String var1 = "(" + this.resultSetNumber + ")" + this.resultSetName + ", ";
      if (this.oneRowRightSide) {
         var1 = var1 + ", EXISTS JOIN";
      }

      return var1;
   }
}
