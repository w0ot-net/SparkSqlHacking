package org.apache.derby.iapi.store.raw;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.Qualifier;

public final class FetchDescriptor {
   private int row_length;
   private FormatableBitSet validColumns;
   private Qualifier[][] qualifier_list;
   private int[] materialized_cols;
   private int maxFetchColumnId;
   private static final int ZERO_FILL_LENGTH = 100;
   private static final int[] zero_fill_array = new int[100];
   private int[] validColumnsArray;

   FetchDescriptor() {
   }

   public FetchDescriptor(int var1) {
      this.row_length = var1;
   }

   public FetchDescriptor(int var1, int var2) {
      this.row_length = var1;
      this.maxFetchColumnId = var2;
      this.validColumnsArray = new int[this.maxFetchColumnId + 1];
      this.validColumnsArray[var2] = 1;
   }

   public FetchDescriptor(int var1, FormatableBitSet var2, Qualifier[][] var3) {
      this.row_length = var1;
      this.qualifier_list = var3;
      if (this.qualifier_list != null) {
         this.materialized_cols = new int[this.row_length];
      }

      this.setValidColumns(var2);
   }

   public final FormatableBitSet getValidColumns() {
      return this.validColumns;
   }

   public final int[] getValidColumnsArray() {
      return this.validColumnsArray;
   }

   public final void setValidColumns(FormatableBitSet var1) {
      this.validColumns = var1;
      this.setMaxFetchColumnId();
      if (this.validColumns != null) {
         this.validColumnsArray = new int[this.maxFetchColumnId + 1];

         for(int var2 = this.maxFetchColumnId; var2 >= 0; --var2) {
            this.validColumnsArray[var2] = this.validColumns.isSet(var2) ? 1 : 0;
         }
      }

   }

   public final Qualifier[][] getQualifierList() {
      return this.qualifier_list;
   }

   public final int[] getMaterializedColumns() {
      return this.materialized_cols;
   }

   public final int getMaxFetchColumnId() {
      return this.maxFetchColumnId;
   }

   private final void setMaxFetchColumnId() {
      this.maxFetchColumnId = this.row_length - 1;
      if (this.validColumns != null) {
         int var1 = this.validColumns.getLength();
         if (var1 < this.maxFetchColumnId + 1) {
            this.maxFetchColumnId = var1 - 1;
         }

         while(this.maxFetchColumnId >= 0 && !this.validColumns.isSet(this.maxFetchColumnId)) {
            --this.maxFetchColumnId;
         }
      }

   }

   public final void reset() {
      int[] var1 = this.materialized_cols;
      if (var1 != null) {
         if (var1.length <= 100) {
            System.arraycopy(zero_fill_array, 0, var1, 0, var1.length);
         } else {
            int var2 = 0;

            int var4;
            for(int var3 = var1.length; var3 > 0; var2 += var4) {
               var4 = var3 > zero_fill_array.length ? zero_fill_array.length : var3;
               System.arraycopy(zero_fill_array, 0, var1, var2, var4);
               var3 -= var4;
            }
         }
      }

   }
}
