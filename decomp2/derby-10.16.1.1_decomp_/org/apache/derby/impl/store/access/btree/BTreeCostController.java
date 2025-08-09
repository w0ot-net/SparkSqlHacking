package org.apache.derby.impl.store.access.btree;

import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.StoreCostController;
import org.apache.derby.iapi.store.access.StoreCostResult;
import org.apache.derby.iapi.store.access.conglomerate.Conglomerate;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public class BTreeCostController extends OpenBTree implements StoreCostController {
   private static final double BTREE_CACHED_FETCH_BY_KEY_PER_LEVEL = 0.2705;
   private static final double BTREE_SORTMERGE_FETCH_BY_KEY_PER_LEVEL = 0.716;
   private static final double BTREE_UNCACHED_FETCH_BY_KEY_PER_LEVEL = 1.5715;
   TransactionManager init_xact_manager;
   Transaction init_rawtran;
   Conglomerate init_conglomerate;
   long num_pages;
   long num_rows;
   long page_size;
   int tree_height;

   public void init(TransactionManager var1, BTree var2, Transaction var3) throws StandardException {
      super.init(var1, var1, (ContainerHandle)null, var3, false, 8, 5, (BTreeLockingPolicy)null, var2, (LogicalUndo)null, (DynamicCompiledOpenConglomInfo)null);
      this.num_pages = this.container.getEstimatedPageCount(0);
      this.num_rows = this.container.getEstimatedRowCount(0) - this.num_pages;
      Properties var4 = new Properties();
      var4.put("derby.storage.pageSize", "");
      this.container.getContainerProperties(var4);
      this.page_size = (long)Integer.parseInt(var4.getProperty("derby.storage.pageSize"));
      this.tree_height = this.getHeight();
   }

   public void close() throws StandardException {
      super.close();
   }

   public double getFetchFromRowLocationCost(FormatableBitSet var1, int var2) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public double getFetchFromFullKeyCost(FormatableBitSet var1, int var2) throws StandardException {
      double var3;
      if ((var2 & 1) == 0) {
         var3 = 1.5715;
      } else {
         var3 = 0.716;
      }

      var3 *= (double)this.tree_height;
      return var3;
   }

   public void getScanCost(int var1, long var2, int var4, boolean var5, FormatableBitSet var6, DataValueDescriptor[] var7, DataValueDescriptor[] var8, int var9, DataValueDescriptor[] var10, int var11, boolean var12, int var13, StoreCostResult var14) throws StandardException {
      ControlRow var17 = null;
      long var18 = var2 < 0L ? this.num_rows : var2;

      try {
         float var15;
         if (var8 == null) {
            var15 = 0.0F;
         } else {
            SearchParameters var20 = new SearchParameters(var8, var9 == 1 ? 1 : -1, var7, this, true);
            var17 = ControlRow.get((OpenBTree)this, 1L).search(var20);
            var17.release();
            var17 = null;
            var15 = var20.left_fraction;
         }

         float var16;
         if (var10 == null) {
            var16 = 1.0F;
         } else {
            SearchParameters var33 = new SearchParameters(var10, var11 == 1 ? 1 : -1, var7, this, true);
            var17 = ControlRow.get((OpenBTree)this, 1L).search(var33);
            var17.release();
            var17 = null;
            var16 = var33.left_fraction;
         }

         float var34 = var16 - var15;
         if (var34 < 0.0F) {
            var34 = 0.0F;
         }

         if (var34 > 1.0F) {
            var34 = 1.0F;
         }

         float var21 = (float)var18 * var34;
         if (var21 < 1.0F) {
            var21 = 1.0F;
         }

         double var22 = this.getFetchFromFullKeyCost(var6, var13);
         var22 += (double)((float)this.num_pages * var34) * (double)1.5F;
         long var24 = (long)var21 - this.num_pages;
         if (var24 < 0L) {
            var24 = 0L;
         }

         if (var1 == 2) {
            var22 += (double)var24 * 0.12;
         } else {
            var22 += (double)var24 * 0.14;
         }

         long var26 = var18 == 0L ? 4L : this.num_pages * this.page_size / var18;
         var22 += (double)(var21 * (float)var26) * 0.004;
         var14.setEstimatedCost(var22);
         var14.setEstimatedRowCount((long)Math.round(var21));
      } finally {
         if (var17 != null) {
            var17.release();
         }

      }

   }

   public RowLocation newRowLocationTemplate() throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }
}
