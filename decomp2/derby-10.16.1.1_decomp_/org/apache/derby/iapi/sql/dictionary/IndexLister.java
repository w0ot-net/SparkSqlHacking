package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public class IndexLister {
   private TableDescriptor tableDescriptor;
   private IndexRowGenerator[] indexRowGenerators;
   private long[] indexConglomerateNumbers;
   private String[] indexNames;
   private IndexRowGenerator[] distinctIndexRowGenerators;
   private long[] distinctIndexConglomerateNumbers;
   private String[] distinctIndexNames;

   public IndexLister(TableDescriptor var1) {
      this.tableDescriptor = var1;
   }

   public IndexRowGenerator[] getIndexRowGenerators() throws StandardException {
      if (this.indexRowGenerators == null) {
         this.getAllIndexes();
      }

      return (IndexRowGenerator[])ArrayUtil.copy(this.indexRowGenerators);
   }

   public long[] getIndexConglomerateNumbers() throws StandardException {
      if (this.indexConglomerateNumbers == null) {
         this.getAllIndexes();
      }

      return ArrayUtil.copy(this.indexConglomerateNumbers);
   }

   public IndexRowGenerator[] getDistinctIndexRowGenerators() throws StandardException {
      if (this.distinctIndexRowGenerators == null) {
         this.getAllIndexes();
      }

      return (IndexRowGenerator[])ArrayUtil.copy(this.distinctIndexRowGenerators);
   }

   public long[] getDistinctIndexConglomerateNumbers() throws StandardException {
      if (this.distinctIndexConglomerateNumbers == null) {
         this.getAllIndexes();
      }

      return ArrayUtil.copy(this.distinctIndexConglomerateNumbers);
   }

   public String[] getDistinctIndexNames() throws StandardException {
      if (this.distinctIndexNames == null) {
         this.getAllIndexes();
      }

      return (String[])ArrayUtil.copy(this.distinctIndexNames);
   }

   private void getAllIndexes() throws StandardException {
      int var1 = 0;
      ConglomerateDescriptor[] var2 = this.tableDescriptor.getConglomerateDescriptors();
      long[] var3 = new long[var2.length - 1];
      int var4 = 0;
      int var5 = var3.length - 1;

      for(int var6 = 0; var6 < var2.length; ++var6) {
         ConglomerateDescriptor var7 = var2[var6];
         if (var7.isIndex()) {
            long var9 = var7.getConglomerateNumber();

            int var8;
            for(var8 = 0; var8 < var4; ++var8) {
               if (var3[var8] == var9) {
                  var3[var5--] = (long)var6;
                  break;
               }
            }

            if (var8 == var4) {
               var3[var4++] = var9;
            }

            ++var1;
         }
      }

      this.indexRowGenerators = new IndexRowGenerator[var1];
      this.indexConglomerateNumbers = new long[var1];
      this.indexNames = new String[var1];
      this.distinctIndexRowGenerators = new IndexRowGenerator[var4];
      this.distinctIndexConglomerateNumbers = new long[var4];
      this.distinctIndexNames = new String[var4];
      int var11 = var3.length - 1;
      int var12 = 0;
      int var13 = -1;

      for(int var14 = -1; var12 < var2.length; ++var12) {
         ConglomerateDescriptor var10 = var2[var12];
         if (var10.isIndex()) {
            ++var13;
            this.indexRowGenerators[var13] = var10.getIndexDescriptor();
            this.indexConglomerateNumbers[var13] = var10.getConglomerateNumber();
            if (!var10.isConstraint()) {
               this.indexNames[var13] = var10.getConglomerateName();
            }

            if (var11 > var5 && var12 == (int)var3[var11]) {
               --var11;
            } else {
               ++var14;
               this.distinctIndexRowGenerators[var14] = this.indexRowGenerators[var13];
               this.distinctIndexConglomerateNumbers[var14] = this.indexConglomerateNumbers[var13];
               this.distinctIndexNames[var14] = this.indexNames[var13];
            }
         }
      }

   }
}
