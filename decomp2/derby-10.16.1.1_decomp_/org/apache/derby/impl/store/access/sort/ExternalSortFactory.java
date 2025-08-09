package org.apache.derby.impl.store.access.sort;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.SortCostController;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.conglomerate.Sort;
import org.apache.derby.iapi.store.access.conglomerate.SortFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class ExternalSortFactory implements SortFactory, ModuleControl, ModuleSupportable, SortCostController {
   private boolean userSpecified;
   private int defaultSortBufferMax;
   private int sortBufferMax;
   private static final String IMPLEMENTATIONID = "sort external";
   private static final String FORMATUUIDSTRING = "D2976090-D9F5-11d0-B54D-00A024BF8879";
   private UUID formatUUID = null;
   private static final int DEFAULT_SORTBUFFERMAX = 1024;
   private static final int MINIMUM_SORTBUFFERMAX = 4;
   protected static final int DEFAULT_MEM_USE = 1048576;
   protected static final int DEFAULT_MAX_MERGE_RUN = 512;
   private static final int SORT_ROW_OVERHEAD = 44;

   public Properties defaultProperties() {
      return new Properties();
   }

   public boolean supportsImplementation(String var1) {
      return var1.equals("sort external");
   }

   public String primaryImplementationType() {
      return "sort external";
   }

   public boolean supportsFormat(UUID var1) {
      return var1.equals(this.formatUUID);
   }

   public UUID primaryFormat() {
      return this.formatUUID;
   }

   protected MergeSort getMergeSort() {
      return new MergeSort();
   }

   public Sort createSort(TransactionController var1, int var2, Properties var3, DataValueDescriptor[] var4, ColumnOrdering[] var5, SortObserver var6, boolean var7, long var8, int var10) throws StandardException {
      MergeSort var11 = this.getMergeSort();
      if (!this.userSpecified) {
         if (var10 > 0) {
            var10 += 44 + var4.length * 16 + 8;
            this.sortBufferMax = 1048576 / var10;
         } else {
            this.sortBufferMax = this.defaultSortBufferMax;
         }

         if (var8 > (long)this.sortBufferMax && (double)var8 * 1.1 < (double)(this.sortBufferMax * 2)) {
            this.sortBufferMax = (int)(var8 / 2L + var8 / 10L);
         }

         if (this.sortBufferMax < 4) {
            this.sortBufferMax = 4;
         }
      } else {
         this.sortBufferMax = this.defaultSortBufferMax;
      }

      var11.initialize(var4, var5, var6, var7, var8, this.sortBufferMax);
      return var11;
   }

   public SortCostController openSortCostController() throws StandardException {
      return this;
   }

   public void close() {
   }

   public double getSortCost(DataValueDescriptor[] var1, ColumnOrdering[] var2, boolean var3, long var4, long var6, int var8) throws StandardException {
      if (var4 == 0L) {
         return (double)0.0F;
      } else {
         double var9 = (double)1.0F + 0.32 * (double)var4 * Math.log((double)var4);
         return var9;
      }
   }

   public boolean canSupport(Properties var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = var1.getProperty("derby.access.Conglomerate.type");
         return var2 == null ? false : this.supportsImplementation(var2);
      }
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      UUIDFactory var3 = getMonitor().getUUIDFactory();
      this.formatUUID = var3.recreateUUID("D2976090-D9F5-11d0-B54D-00A024BF8879");
      this.defaultSortBufferMax = PropertyUtil.getSystemInt("derby.storage.sortBufferMax", 0, Integer.MAX_VALUE, 0);
      if (this.defaultSortBufferMax == 0) {
         this.userSpecified = false;
         this.defaultSortBufferMax = 1024;
      } else {
         this.userSpecified = true;
         if (this.defaultSortBufferMax < 4) {
            this.defaultSortBufferMax = 4;
         }
      }

   }

   public void stop() {
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
