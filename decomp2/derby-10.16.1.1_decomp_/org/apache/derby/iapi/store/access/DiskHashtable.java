package org.apache.derby.iapi.store.access;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Properties;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLInteger;
import org.apache.derby.shared.common.error.StandardException;

public class DiskHashtable {
   private final long rowConglomerateId;
   private ConglomerateController rowConglomerate;
   private final long btreeConglomerateId;
   private ConglomerateController btreeConglomerate;
   private final DataValueDescriptor[] btreeRow;
   private final int[] key_column_numbers;
   private final boolean remove_duplicates;
   private final TransactionController tc;
   private final DataValueDescriptor[] row;
   private final DataValueDescriptor[] scanKey = new DataValueDescriptor[]{new SQLInteger()};
   private int size;
   private boolean keepStatistics;
   private final boolean keepAfterCommit;

   public DiskHashtable(TransactionController var1, DataValueDescriptor[] var2, int[] var3, int[] var4, boolean var5, boolean var6) throws StandardException {
      this.tc = var1;
      this.key_column_numbers = var4;
      this.remove_duplicates = var5;
      this.keepAfterCommit = var6;
      LanguageConnectionContext var7 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
      this.keepStatistics = var7 != null && var7.getRunTimeStatisticsMode();
      this.row = new DataValueDescriptor[var2.length];

      for(int var8 = 0; var8 < this.row.length; ++var8) {
         this.row[var8] = var2[var8].getNewNull();
      }

      int var11 = var6 ? 3 : 1;
      this.rowConglomerateId = var1.createConglomerate("heap", var2, (ColumnOrdering[])null, var3, (Properties)null, var11);
      this.rowConglomerate = var1.openConglomerate(this.rowConglomerateId, var6, 4, 7, 0);
      this.btreeRow = new DataValueDescriptor[]{new SQLInteger(), this.rowConglomerate.newRowLocationTemplate()};
      Properties var9 = new Properties();
      var9.put("baseConglomerateId", String.valueOf(this.rowConglomerateId));
      var9.put("rowLocationColumn", "1");
      var9.put("allowDuplicates", "false");
      var9.put("nKeyFields", "2");
      var9.put("nUniqueColumns", "2");
      var9.put("maintainParentLinks", "false");
      int[] var10 = new int[]{0, 0};
      this.btreeConglomerateId = var1.createConglomerate("BTREE", this.btreeRow, (ColumnOrdering[])null, var10, var9, var11);
      this.btreeConglomerate = var1.openConglomerate(this.btreeConglomerateId, var6, 4, 7, 0);
   }

   public void close() throws StandardException {
      this.btreeConglomerate.close();
      this.rowConglomerate.close();
      this.tc.dropConglomerate(this.btreeConglomerateId);
      this.tc.dropConglomerate(this.rowConglomerateId);
   }

   public boolean put(Object var1, Object[] var2) throws StandardException {
      boolean var3 = false;
      if (this.remove_duplicates || this.keepStatistics) {
         var3 = this.getRemove(var1, false, true) != null;
         if (this.remove_duplicates && var3) {
            return false;
         }
      }

      this.rowConglomerate.insertAndFetchLocation((DataValueDescriptor[])var2, (RowLocation)this.btreeRow[1]);
      this.btreeRow[0].setValue(var1.hashCode());
      this.btreeConglomerate.insert(this.btreeRow);
      if (this.keepStatistics && !var3) {
         ++this.size;
      }

      return true;
   }

   public Object get(Object var1) throws StandardException {
      return this.getRemove(var1, false, false);
   }

   private Object getRemove(Object var1, boolean var2, boolean var3) throws StandardException {
      int var4 = var1.hashCode();
      int var5 = 0;
      DataValueDescriptor[] var6 = null;
      ArrayList var7 = null;
      this.scanKey[0].setValue(var4);
      ScanController var8 = this.tc.openScan(this.btreeConglomerateId, false, var2 ? 4 : 0, 7, 1, (FormatableBitSet)null, this.scanKey, 1, (Qualifier[][])null, this.scanKey, -1);

      try {
         while(var8.fetchNext(this.btreeRow)) {
            if (this.rowConglomerate.fetch((RowLocation)this.btreeRow[1], this.row, (FormatableBitSet)null) && this.rowMatches(this.row, var1)) {
               if (var3) {
                  DiskHashtable var14 = this;
                  return var14;
               }

               DataValueDescriptor[] var9 = BackingStoreHashtable.shallowCloneRow(this.row);
               ++var5;
               if (var5 == 1) {
                  var6 = var9;
               } else {
                  if (var7 == null) {
                     var7 = new ArrayList(2);
                     var7.add(var6);
                  }

                  var7.add(var9);
               }

               if (var2) {
                  this.rowConglomerate.delete((RowLocation)this.btreeRow[1]);
                  var8.delete();
                  --this.size;
               }

               if (this.remove_duplicates) {
                  DataValueDescriptor[] var10 = var9;
                  return var10;
               }
            }
         }
      } finally {
         var8.close();
      }

      if (var7 == null) {
         return var6;
      } else {
         return var7;
      }
   }

   private boolean rowMatches(DataValueDescriptor[] var1, Object var2) {
      if (this.key_column_numbers.length == 1) {
         return var1[this.key_column_numbers[0]].equals(var2);
      } else {
         KeyHasher var3 = (KeyHasher)var2;

         for(int var4 = 0; var4 < this.key_column_numbers.length; ++var4) {
            if (!var1[this.key_column_numbers[var4]].equals(var3.getObject(var4))) {
               return false;
            }
         }

         return true;
      }
   }

   public Object remove(Object var1) throws StandardException {
      return this.getRemove(var1, true, false);
   }

   public int size() {
      return this.size;
   }

   public Enumeration elements() throws StandardException {
      return new ElementEnum();
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }

   private class ElementEnum implements Enumeration {
      private ScanController scan;
      private boolean hasMore;
      private RowLocation rowloc;

      ElementEnum() {
         try {
            this.scan = DiskHashtable.this.tc.openScan(DiskHashtable.this.rowConglomerateId, DiskHashtable.this.keepAfterCommit, 0, 7, 0, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
            this.hasMore = this.scan.next();
            if (!this.hasMore) {
               this.scan.close();
               this.scan = null;
            } else if (DiskHashtable.this.keepAfterCommit) {
               this.rowloc = DiskHashtable.this.rowConglomerate.newRowLocationTemplate();
               this.scan.fetchLocation(this.rowloc);
            }
         } catch (StandardException var5) {
            this.hasMore = false;
            if (this.scan != null) {
               try {
                  this.scan.close();
               } catch (StandardException var4) {
               }

               this.scan = null;
            }
         }

      }

      public boolean hasMoreElements() {
         return this.hasMore;
      }

      public Object nextElement() {
         if (!this.hasMore) {
            throw new NoSuchElementException();
         } else {
            try {
               if (this.scan.isHeldAfterCommit() && !this.scan.positionAtRowLocation(this.rowloc)) {
                  throw StandardException.newException("24000", new Object[0]);
               } else {
                  this.scan.fetch(DiskHashtable.this.row);
                  DataValueDescriptor[] var1 = BackingStoreHashtable.shallowCloneRow(DiskHashtable.this.row);
                  this.hasMore = this.scan.next();
                  if (!this.hasMore) {
                     this.scan.close();
                     this.scan = null;
                  } else if (DiskHashtable.this.keepAfterCommit) {
                     this.scan.fetchLocation(this.rowloc);
                  }

                  return var1;
               }
            } catch (StandardException var4) {
               if (this.scan != null) {
                  try {
                     this.scan.close();
                  } catch (StandardException var3) {
                  }

                  this.scan = null;
               }

               throw new NoSuchElementException();
            }
         }
      }
   }
}
