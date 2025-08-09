package org.apache.derby.impl.store.access;

import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowSource;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.conglomerate.ScanManager;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class BackingStoreHashTableFromScan extends BackingStoreHashtable {
   private ScanManager open_scan;
   private boolean includeRowLocations;

   public BackingStoreHashTableFromScan(TransactionController var1, long var2, int var4, int var5, int var6, FormatableBitSet var7, DataValueDescriptor[] var8, int var9, Qualifier[][] var10, DataValueDescriptor[] var11, int var12, long var13, int[] var15, boolean var16, long var17, long var19, int var21, float var22, boolean var23, boolean var24, boolean var25, boolean var26) throws StandardException {
      super(var1, (RowSource)null, var15, var16, var17, var19, var21, var22, var24, var25);
      this.includeRowLocations = var26;
      this.open_scan = (ScanManager)var1.openScan(var2, false, var4, var5, var6, var7, var8, var9, var10, var11, var12);
      this.open_scan.fetchSet(var13, var15, this);
      if (var23) {
         Properties var27 = new Properties();
         this.open_scan.getScanInfo().getAllScanInfo(var27);
         this.setAuxillaryRuntimeStats(var27);
         Object var28 = null;
      }

   }

   public boolean includeRowLocations() {
      return this.includeRowLocations;
   }

   public void close() throws StandardException {
      this.open_scan.close();
      super.close();
   }
}
