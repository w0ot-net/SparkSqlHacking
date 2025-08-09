package org.apache.derby.impl.sql.execute;

import java.util.Enumeration;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class DistinctScanResultSet extends HashScanResultSet {
   Enumeration element = null;

   DistinctScanResultSet(long var1, StaticCompiledOpenConglomInfo var3, Activation var4, int var5, int var6, int var7, String var8, String var9, String var10, boolean var11, int var12, int var13, boolean var14, int var15, double var16, double var18) throws StandardException {
      super(var1, var3, var4, var5, var6, (GeneratedMethod)null, 0, (GeneratedMethod)null, 0, false, (Qualifier[][])null, (Qualifier[][])null, -1, -1.0F, -1, var7, var8, var9, var10, var11, false, var12, var13, var14, var15, false, var16, var18);
      this.eliminateDuplicates = true;
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecRow var1 = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen) {
            if (this.firstNext) {
               this.element = this.hashtable.elements();
               this.firstNext = false;
            }

            if (this.element.hasMoreElements()) {
               DataValueDescriptor[] var2 = this.unpackHashValue(this.element.nextElement());
               this.setCompatRow(this.compactRow, var2);
               ++this.rowsSeen;
               var1 = this.compactRow;
            }
         }

         this.setCurrentRow(var1);
         this.nextTime += this.getElapsedMillis(this.beginTime);
         return var1;
      }
   }
}
