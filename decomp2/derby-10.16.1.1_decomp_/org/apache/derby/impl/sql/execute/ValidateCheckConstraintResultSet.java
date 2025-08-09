package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.shared.common.error.ExceptionUtil;
import org.apache.derby.shared.common.error.StandardException;

final class ValidateCheckConstraintResultSet extends TableScanResultSet implements CursorResultSet, Cloneable {
   ValidateCheckConstraintResultSet(long var1, StaticCompiledOpenConglomInfo var3, Activation var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, int var20, boolean var21, int var22, int var23, boolean var24, double var25, double var27) throws StandardException {
      super(var1, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, var25, var27);
   }

   boolean loopControl(boolean var1) throws StandardException {
      try {
         this.scanController.fetch(this.candidate.getRowArray());
      } catch (StandardException var3) {
         if (!var3.getSQLState().equals(ExceptionUtil.getSQLStateFromIdentifier("XSAM6.S"))) {
            throw var3;
         }

         var1 = false;
      }

      return var1;
   }
}
