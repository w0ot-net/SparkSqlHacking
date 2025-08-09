package org.apache.derby.iapi.store.access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.LocatedRow;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.util.PropertyUtil;
import org.apache.derby.shared.common.error.StandardException;

public class BackingStoreHashtable {
   private TransactionController tc;
   private HashMap hash_table;
   private int[] key_column_numbers;
   private boolean remove_duplicates;
   private boolean skipNullKeyColumns;
   private Properties auxillary_runtimestats;
   private RowSource row_source;
   private long max_inmemory_rowcnt;
   private long inmemory_rowcnt;
   private long max_inmemory_size;
   private boolean keepAfterCommit;
   private static final int ARRAY_LIST_SIZE = ClassSize.estimateBaseFromCatalog(ArrayList.class);
   private DiskHashtable diskHashtable;

   private BackingStoreHashtable() {
   }

   public BackingStoreHashtable(TransactionController var1, RowSource var2, int[] var3, boolean var4, long var5, long var7, int var9, float var10, boolean var11, boolean var12) throws StandardException {
      this.key_column_numbers = var3;
      this.remove_duplicates = var4;
      this.row_source = var2;
      this.skipNullKeyColumns = var11;
      this.max_inmemory_rowcnt = var7;
      if (var7 > 0L) {
         this.max_inmemory_size = Long.MAX_VALUE;
      } else {
         this.max_inmemory_size = Runtime.getRuntime().totalMemory() / 100L;
      }

      this.tc = var1;
      this.keepAfterCommit = var12;
      if (var9 != -1) {
         this.hash_table = var10 == -1.0F ? new HashMap(var9) : new HashMap(var9, var10);
      } else {
         this.hash_table = var5 > 0L && var2 != null ? (var5 < this.max_inmemory_size ? new HashMap((int)var5) : null) : new HashMap();
      }

      DataValueDescriptor[] var14;
      if (var2 != null) {
         for(boolean var13 = var2.needsToClone(); (var14 = this.getNextRowFromRowSource()) != null; this.add_row_to_hash_table(var14, (RowLocation)null, var13)) {
            if (this.hash_table == null) {
               double var15 = (double)this.getEstimatedMemUsage(var14);
               this.hash_table = new HashMap((int)((double)this.max_inmemory_size / var15));
            }
         }
      }

      if (this.hash_table == null) {
         this.hash_table = new HashMap();
      }

   }

   public boolean includeRowLocations() {
      return false;
   }

   private DataValueDescriptor[] getNextRowFromRowSource() throws StandardException {
      DataValueDescriptor[] var1 = this.row_source.getNextRowFromRowSource();
      if (this.skipNullKeyColumns) {
         while(var1 != null) {
            int var2;
            for(var2 = 0; var2 < this.key_column_numbers.length && !var1[this.key_column_numbers[var2]].isNull(); ++var2) {
            }

            if (var2 == this.key_column_numbers.length) {
               return var1;
            }

            var1 = this.row_source.getNextRowFromRowSource();
         }
      }

      return var1;
   }

   private static DataValueDescriptor[] cloneRow(DataValueDescriptor[] var0) throws StandardException {
      DataValueDescriptor[] var1 = new DataValueDescriptor[var0.length];

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if (var0[var2] != null) {
            var1[var2] = var0[var2].cloneValue(false);
         }
      }

      return var1;
   }

   static DataValueDescriptor[] shallowCloneRow(DataValueDescriptor[] var0) throws StandardException {
      DataValueDescriptor[] var1 = new DataValueDescriptor[var0.length];

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if (var0[var2] != null) {
            var1[var2] = var0[var2].cloneHolder();
         }
      }

      return var1;
   }

   private void add_row_to_hash_table(DataValueDescriptor[] var1, RowLocation var2, boolean var3) throws StandardException {
      if (!this.spillToDisk(var1, var2)) {
         if (var3) {
            var1 = cloneRow(var1);
         }

         Object var4 = KeyHasher.buildHashKey(var1, this.key_column_numbers);
         Object var5 = !this.includeRowLocations() ? var1 : new LocatedRow(var1, var2);
         Object var6 = this.hash_table.put(var4, var5);
         if (var6 == null) {
            this.doSpaceAccounting(var5, false);
         } else if (!this.remove_duplicates) {
            RowList var7;
            if (var6 instanceof RowList) {
               this.doSpaceAccounting(var5, false);
               var7 = (RowList)var6;
            } else {
               var7 = new RowList(2);
               var7.add(var6);
               this.doSpaceAccounting(var5, true);
            }

            var7.add(var5);
            this.hash_table.put(var4, var7);
         }

      }
   }

   private void doSpaceAccounting(Object var1, boolean var2) {
      ++this.inmemory_rowcnt;
      if (this.max_inmemory_rowcnt <= 0L) {
         this.max_inmemory_size -= this.getEstimatedMemUsage(var1);
         if (var2) {
            this.max_inmemory_size -= (long)ARRAY_LIST_SIZE;
         }
      }

   }

   private boolean spillToDisk(DataValueDescriptor[] var1, RowLocation var2) throws StandardException {
      DataValueDescriptor[] var3 = null;
      if (this.diskHashtable == null) {
         if (this.max_inmemory_rowcnt > 0L) {
            if (this.inmemory_rowcnt < this.max_inmemory_rowcnt) {
               return false;
            }
         } else if (this.max_inmemory_size > this.getEstimatedMemUsage(!this.includeRowLocations() ? var1 : new LocatedRow(var1, var2))) {
            return false;
         }

         var3 = this.makeDiskRow(var1, var2);
         this.diskHashtable = new DiskHashtable(this.tc, var3, (int[])null, this.key_column_numbers, this.remove_duplicates, this.keepAfterCommit);
      }

      Object var4 = KeyHasher.buildHashKey(var1, this.key_column_numbers);
      Object var5 = this.hash_table.get(var4);
      if (var5 != null) {
         if (this.remove_duplicates) {
            return true;
         }

         if (var5 instanceof List) {
            List var6 = (List)var5;

            for(int var7 = var6.size() - 1; var7 >= 0; --var7) {
               this.diskHashtable.put(var4, this.makeDiskRow(var6.get(var7)));
            }
         } else {
            this.diskHashtable.put(var4, this.makeDiskRow(var5));
         }

         this.hash_table.remove(var4);
      }

      if (var3 == null) {
         var3 = this.makeDiskRow(var1, var2);
      }

      this.diskHashtable.put(var4, var3);
      return true;
   }

   private DataValueDescriptor[] makeDiskRow(Object var1) {
      DataValueDescriptor var2 = null;
      if (this.includeRowLocations()) {
         LocatedRow var3 = (LocatedRow)var1;
         var2 = this.makeDiskRow(var3.columnValues(), var3.rowLocation());
      } else {
         var2 = (DataValueDescriptor[])var1;
      }

      return var2;
   }

   private List makeInMemoryRows(List var1) {
      if (!this.includeRowLocations()) {
         return var1;
      } else {
         ArrayList var2 = new ArrayList();

         for(Object var4 : var1) {
            var2.add(this.makeInMemoryRow((DataValueDescriptor[])var4));
         }

         return var2;
      }
   }

   private Object makeInMemoryRow(DataValueDescriptor[] var1) {
      return !this.includeRowLocations() ? var1 : new LocatedRow(var1);
   }

   private DataValueDescriptor[] makeDiskRow(DataValueDescriptor[] var1, RowLocation var2) {
      return !this.includeRowLocations() ? var1 : LocatedRow.flatten(var1, var2);
   }

   private long getEstimatedMemUsage(Object var1) {
      long var2 = 0L;
      Object var4 = null;
      if (var1 instanceof DataValueDescriptor[] var10) {
         ;
      } else {
         LocatedRow var5 = (LocatedRow)var1;
         var10 = var5.columnValues();
         RowLocation var6 = var5.rowLocation();
         if (var6 != null) {
            var2 += (long)var5.rowLocation().estimateMemoryUsage();
            var2 += (long)ClassSize.refSize;
         }

         var2 += (long)ClassSize.refSize;
      }

      for(int var11 = 0; var11 < var10.length; ++var11) {
         var2 += (long)var10[var11].estimateMemoryUsage();
         var2 += (long)ClassSize.refSize;
      }

      var2 += (long)ClassSize.refSize;
      return var2;
   }

   public void close() throws StandardException {
      this.hash_table = null;
      if (this.diskHashtable != null) {
         this.diskHashtable.close();
         this.diskHashtable = null;
      }

   }

   public Enumeration elements() throws StandardException {
      return (Enumeration)(this.diskHashtable == null ? Collections.enumeration(this.hash_table.values()) : new BackingStoreHashtableEnumeration());
   }

   public Object get(Object var1) throws StandardException {
      Object var2 = this.hash_table.get(var1);
      if (this.diskHashtable != null && var2 == null) {
         Object var3 = this.diskHashtable.get(var1);
         if (var3 == null) {
            return null;
         } else {
            return var3 instanceof List ? this.makeInMemoryRows((List)var3) : this.makeInMemoryRow((DataValueDescriptor[])var3);
         }
      } else {
         return var2;
      }
   }

   public void getAllRuntimeStats(Properties var1) throws StandardException {
      if (this.auxillary_runtimestats != null) {
         PropertyUtil.copyProperties(this.auxillary_runtimestats, var1);
      }

   }

   public Object remove(Object var1) throws StandardException {
      Object var2 = this.hash_table.remove(var1);
      return var2 == null && this.diskHashtable != null ? this.diskHashtable.remove(var1) : var2;
   }

   public void setAuxillaryRuntimeStats(Properties var1) throws StandardException {
      this.auxillary_runtimestats = var1;
   }

   public boolean putRow(boolean var1, DataValueDescriptor[] var2, RowLocation var3) throws StandardException {
      if (this.skipNullKeyColumns) {
         for(int var4 = 0; var4 < this.key_column_numbers.length; ++var4) {
            if (var2[this.key_column_numbers[var4]].isNull()) {
               return false;
            }
         }
      }

      Object var5 = KeyHasher.buildHashKey(var2, this.key_column_numbers);
      if (this.remove_duplicates && this.get(var5) != null) {
         return false;
      } else {
         this.add_row_to_hash_table(var2, var3, var1);
         return true;
      }
   }

   public int size() throws StandardException {
      return this.diskHashtable == null ? this.hash_table.size() : this.hash_table.size() + this.diskHashtable.size();
   }

   private class BackingStoreHashtableEnumeration implements Enumeration {
      private Iterator memoryIterator;
      private Enumeration diskEnumeration;

      BackingStoreHashtableEnumeration() {
         this.memoryIterator = BackingStoreHashtable.this.hash_table.values().iterator();
         if (BackingStoreHashtable.this.diskHashtable != null) {
            try {
               this.diskEnumeration = BackingStoreHashtable.this.diskHashtable.elements();
            } catch (StandardException var3) {
               this.diskEnumeration = null;
            }
         }

      }

      public boolean hasMoreElements() {
         if (this.memoryIterator != null) {
            if (this.memoryIterator.hasNext()) {
               return true;
            }

            this.memoryIterator = null;
         }

         return this.diskEnumeration == null ? false : this.diskEnumeration.hasMoreElements();
      }

      public Object nextElement() throws NoSuchElementException {
         if (this.memoryIterator != null) {
            if (this.memoryIterator.hasNext()) {
               return this.memoryIterator.next();
            }

            this.memoryIterator = null;
         }

         return BackingStoreHashtable.this.makeInMemoryRow((DataValueDescriptor[])this.diskEnumeration.nextElement());
      }
   }

   private static class RowList extends ArrayList {
      private RowList(int var1) {
         super(var1);
      }
   }
}
