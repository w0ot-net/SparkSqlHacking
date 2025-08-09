package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class IndexSetChanger {
   IndexRowGenerator[] irgs;
   long[] indexCIDS;
   private DynamicCompiledOpenConglomInfo[] indexDCOCIs;
   private StaticCompiledOpenConglomInfo[] indexSCOCIs;
   String[] indexNames;
   ConglomerateController baseCC;
   FormatableBitSet baseRowReadMap;
   TransactionController tc;
   TemporaryRowHolderImpl rowHolder;
   IndexChanger[] indexChangers;
   private int lockMode;
   boolean[] fixOnUpdate;
   boolean isOpen = false;
   private static final int NO_INDEXES = 0;
   private static final int UPDATE_INDEXES = 1;
   private static final int ALL_INDEXES = 2;
   private int whatIsOpen = 0;
   private int isolationLevel;
   private final Activation activation;

   public IndexSetChanger(IndexRowGenerator[] var1, long[] var2, StaticCompiledOpenConglomInfo[] var3, DynamicCompiledOpenConglomInfo[] var4, String[] var5, ConglomerateController var6, TransactionController var7, int var8, FormatableBitSet var9, int var10, Activation var11) throws StandardException {
      this.irgs = var1;
      this.indexCIDS = var2;
      this.indexSCOCIs = var3;
      this.indexDCOCIs = var4;
      this.indexNames = var5;
      this.baseCC = var6;
      this.tc = var7;
      this.lockMode = var8;
      this.baseRowReadMap = var9;
      this.isolationLevel = var10;
      this.activation = var11;
      this.indexChangers = new IndexChanger[var1.length];
   }

   public void open(boolean[] var1) throws StandardException {
      this.fixOnUpdate = var1;
      this.isOpen = true;
   }

   public void setRowHolder(TemporaryRowHolderImpl var1) {
      this.rowHolder = var1;
   }

   private void openIndexes(int var1) throws StandardException {
      if (this.whatIsOpen < var1) {
         for(int var2 = 0; var2 < this.indexChangers.length; ++var2) {
            if (var1 != 1 || this.fixOnUpdate[var2]) {
               if (this.indexChangers[var2] == null) {
                  this.indexChangers[var2] = new IndexChanger(this.irgs[var2], this.indexCIDS[var2], this.indexSCOCIs == null ? (StaticCompiledOpenConglomInfo)null : this.indexSCOCIs[var2], this.indexDCOCIs == null ? (DynamicCompiledOpenConglomInfo)null : this.indexDCOCIs[var2], this.indexNames == null ? null : this.indexNames[var2], this.baseCC, this.tc, this.lockMode, this.baseRowReadMap, this.isolationLevel, this.activation);
                  this.indexChangers[var2].setRowHolder(this.rowHolder);
               } else {
                  this.indexChangers[var2].setBaseCC(this.baseCC);
               }

               this.indexChangers[var2].open();
            }
         }

         this.whatIsOpen = var1;
      }
   }

   public void delete(ExecRow var1, RowLocation var2) throws StandardException {
      this.openIndexes(2);

      for(int var3 = 0; var3 < this.indexChangers.length; ++var3) {
         this.indexChangers[var3].delete(var1, var2);
      }

   }

   public void insert(ExecRow var1, RowLocation var2) throws StandardException {
      this.openIndexes(2);

      for(int var3 = 0; var3 < this.indexChangers.length; ++var3) {
         this.indexChangers[var3].insert(var1, var2);
      }

   }

   public void update(ExecRow var1, ExecRow var2, RowLocation var3) throws StandardException {
      this.openIndexes(1);

      for(int var4 = 0; var4 < this.indexChangers.length; ++var4) {
         if (this.fixOnUpdate[var4]) {
            this.indexChangers[var4].update(var1, var2, var3);
         }
      }

   }

   public void setBaseCC(ConglomerateController var1) {
      for(int var2 = 0; var2 < this.indexChangers.length; ++var2) {
         if (this.indexChangers[var2] != null) {
            this.indexChangers[var2].setBaseCC(var1);
         }
      }

      this.baseCC = var1;
   }

   public void finish() throws StandardException {
      for(int var1 = 0; var1 < this.indexChangers.length; ++var1) {
         if (this.indexChangers[var1] != null) {
            this.indexChangers[var1].finish();
         }
      }

   }

   public void close() throws StandardException {
      this.whatIsOpen = 0;

      for(int var1 = 0; var1 < this.indexChangers.length; ++var1) {
         if (this.indexChangers[var1] != null) {
            this.indexChangers[var1].close();
         }
      }

      this.fixOnUpdate = null;
      this.isOpen = false;
      this.rowHolder = null;
   }

   public String toString() {
      return null;
   }
}
