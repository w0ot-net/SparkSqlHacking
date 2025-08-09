package org.apache.derby.impl.store.access.heap;

import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.StoreCostController;
import org.apache.derby.iapi.store.access.StoreCostResult;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.conglomerate.GenericCostController;
import org.apache.derby.impl.store.access.conglomerate.OpenConglomerate;
import org.apache.derby.shared.common.error.StandardException;

public class HeapCostController extends GenericCostController implements StoreCostController {
   long num_pages;
   long num_rows;
   long page_size;
   long row_size;

   public void init(OpenConglomerate var1) throws StandardException {
      super.init(var1);
      ContainerHandle var2 = var1.getContainer();
      this.num_rows = var2.getEstimatedRowCount(0);
      if (this.num_rows == 0L) {
         this.num_rows = 1L;
      }

      this.num_pages = var2.getEstimatedPageCount(0);
      Properties var3 = new Properties();
      var3.put("derby.storage.pageSize", "");
      var2.getContainerProperties(var3);
      this.page_size = (long)Integer.parseInt(var3.getProperty("derby.storage.pageSize"));
      this.row_size = this.num_pages * this.page_size / this.num_rows;
   }

   public double getFetchFromRowLocationCost(FormatableBitSet var1, int var2) throws StandardException {
      double var3 = (double)this.row_size * 0.004;
      long var5 = this.row_size / this.page_size + 1L;
      if ((var2 & 1) == 0) {
         var3 += (double)1.5F * (double)var5;
      } else {
         var3 += 0.17 * (double)var5;
      }

      return var3;
   }

   public void getScanCost(int var1, long var2, int var4, boolean var5, FormatableBitSet var6, DataValueDescriptor[] var7, DataValueDescriptor[] var8, int var9, DataValueDescriptor[] var10, int var11, boolean var12, int var13, StoreCostResult var14) throws StandardException {
      long var15 = var2 < 0L ? this.num_rows : var2;
      double var17 = (double)this.num_pages * (double)1.5F;
      var17 += (double)(var15 * this.row_size) * 0.004;
      long var19 = var15 - this.num_pages;
      if (var19 < 0L) {
         var19 = 0L;
      }

      if (var1 == 2) {
         var17 += (double)var19 * 0.12;
      } else {
         var17 += (double)var19 * 0.14;
      }

      var14.setEstimatedCost(var17);
      var14.setEstimatedRowCount(var15);
   }
}
