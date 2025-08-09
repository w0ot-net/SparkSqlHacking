package org.apache.derby.impl.sql.execute;

import java.util.Arrays;
import java.util.Collections;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class MultiProbeTableScanResultSet extends TableScanResultSet implements CursorResultSet {
   protected DataValueDescriptor[] probeValues;
   protected DataValueDescriptor[] origProbeValues;
   protected int probeValIndex;
   private int sortRequired;
   private boolean skipNextScan;

   MultiProbeTableScanResultSet(long var1, StaticCompiledOpenConglomInfo var3, Activation var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, DataValueDescriptor[] var13, int var14, String var15, String var16, String var17, boolean var18, boolean var19, int var20, int var21, int var22, boolean var23, int var24, boolean var25, double var26, double var28) throws StandardException {
      super(var1, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, 1, var25, var26, var28);
      this.origProbeValues = var13;
      this.sortRequired = var14;
   }

   public void openCore() throws StandardException {
      if (this.sortRequired == 3) {
         this.probeValues = this.origProbeValues;
      } else {
         DataValueDescriptor[] var1 = new DataValueDescriptor[this.origProbeValues.length];

         for(int var2 = 0; var2 < var1.length; ++var2) {
            var1[var2] = this.origProbeValues[var2].cloneValue(false);
         }

         if (this.sortRequired == 1) {
            Arrays.sort(var1);
         } else {
            Arrays.sort(var1, Collections.reverseOrder());
         }

         this.probeValues = var1;
      }

      this.probeValIndex = 0;
      super.openCore();
   }

   public void reopenCore() throws StandardException {
      this.reopenCore(false);
   }

   private void reopenCore(boolean var1) throws StandardException {
      if (!var1) {
         this.probeValIndex = 0;
      }

      super.reopenCore();
   }

   protected void reopenScanController() throws StandardException {
      long var1 = this.rowsThisScan;
      super.reopenScanController();
      this.rowsThisScan = var1;
   }

   void initStartAndStopKey() throws StandardException {
      super.initStartAndStopKey();
      if (this.probeValIndex == 0) {
         this.rowsThisScan = 0L;
      }

      DataValueDescriptor[] var1 = this.startPosition.getRowArray();
      DataValueDescriptor[] var2 = this.stopPosition.getRowArray();
      DataValueDescriptor var3 = this.getNextProbeValue();
      if (var3 != null) {
         var1[0] = var3;
         if (!this.sameStartStopPosition) {
            var2[0] = var1[0];
         }
      }

      this.skipNextScan = var3 == null;
   }

   protected boolean skipScan(ExecIndexRow var1, ExecIndexRow var2) throws StandardException {
      return this.skipNextScan || super.skipScan(var1, var2);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         this.checkCancellationFlag();

         ExecRow var1;
         for(var1 = super.getNextRowCore(); var1 == null && this.moreInListVals(); var1 = super.getNextRowCore()) {
            this.reopenCore(true);
         }

         return var1;
      }
   }

   public void close() throws StandardException {
      super.close();
   }

   private boolean moreInListVals() {
      return this.probeValIndex < this.probeValues.length;
   }

   private DataValueDescriptor getNextProbeValue() {
      int var1;
      for(var1 = this.probeValIndex; var1 > 0 && var1 < this.probeValues.length && this.probeValues[this.probeValIndex - 1].equals(this.probeValues[var1]); ++var1) {
      }

      this.probeValIndex = var1;
      return this.probeValIndex < this.probeValues.length ? this.probeValues[this.probeValIndex++] : null;
   }
}
