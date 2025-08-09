package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.IndexDescriptor;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.io.FormatableHashtable;
import org.apache.derby.shared.common.util.ArrayUtil;

public class IndexDescriptorImpl implements IndexDescriptor, Formatable {
   private boolean isUnique;
   private int[] baseColumnPositions;
   private boolean[] isAscending;
   private int numberOfOrderedColumns;
   private String indexType;
   private boolean isUniqueWithDuplicateNulls;
   private boolean isUniqueDeferrable;
   private boolean hasDeferrableChecking;

   public IndexDescriptorImpl(String var1, boolean var2, boolean var3, boolean var4, boolean var5, int[] var6, boolean[] var7, int var8) {
      this.indexType = var1;
      this.isUnique = var2;
      this.isUniqueWithDuplicateNulls = var3;
      this.isUniqueDeferrable = var4;
      this.hasDeferrableChecking = var5;
      this.baseColumnPositions = ArrayUtil.copy(var6);
      this.isAscending = ArrayUtil.copy(var7);
      this.numberOfOrderedColumns = var8;
   }

   public IndexDescriptorImpl() {
   }

   public boolean isUniqueWithDuplicateNulls() {
      return this.isUniqueWithDuplicateNulls;
   }

   public boolean hasDeferrableChecking() {
      return this.hasDeferrableChecking;
   }

   public boolean isUniqueDeferrable() {
      return this.isUniqueDeferrable;
   }

   public boolean isUnique() {
      return this.isUnique;
   }

   public int[] baseColumnPositions() {
      return ArrayUtil.copy(this.baseColumnPositions);
   }

   public int getKeyColumnPosition(int var1) {
      int var2 = 0;

      for(int var3 = 0; var3 < this.baseColumnPositions.length; ++var3) {
         if (this.baseColumnPositions[var3] == var1) {
            var2 = var3 + 1;
            break;
         }
      }

      return var2;
   }

   public int numberOfOrderedColumns() {
      return this.numberOfOrderedColumns;
   }

   public String indexType() {
      return this.indexType;
   }

   public boolean isAscending(Integer var1) {
      int var2 = var1 - 1;
      return var2 >= 0 && var2 < this.baseColumnPositions.length ? this.isAscending[var2] : false;
   }

   public boolean isDescending(Integer var1) {
      int var2 = var1 - 1;
      if (var2 >= 0 && var2 < this.baseColumnPositions.length) {
         return !this.isAscending[var2];
      } else {
         return false;
      }
   }

   public boolean[] isAscending() {
      return ArrayUtil.copy(this.isAscending);
   }

   public void setBaseColumnPositions(int[] var1) {
      this.baseColumnPositions = ArrayUtil.copy(var1);
   }

   public void setIsAscending(boolean[] var1) {
      this.isAscending = ArrayUtil.copy(var1);
   }

   public void setNumberOfOrderedColumns(int var1) {
      this.numberOfOrderedColumns = var1;
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder(60);
      if (!this.isUnique && !this.isUniqueDeferrable) {
         if (this.isUniqueWithDuplicateNulls) {
            var1.append("UNIQUE WITH DUPLICATE NULLS ");
         }
      } else {
         var1.append("UNIQUE ");
      }

      if (this.hasDeferrableChecking) {
         var1.append(" DEFERRABLE CHECKING ");
      }

      var1.append(this.indexType);
      var1.append(" (");

      for(int var2 = 0; var2 < this.baseColumnPositions.length; ++var2) {
         if (var2 > 0) {
            var1.append(", ");
         }

         var1.append(this.baseColumnPositions[var2]);
         if (!this.isAscending[var2]) {
            var1.append(" DESC");
         }
      }

      var1.append(")");
      return var1.toString();
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      FormatableHashtable var2 = (FormatableHashtable)var1.readObject();
      this.isUnique = var2.getBoolean("isUnique");
      int var3 = var2.getInt("keyLength");
      this.baseColumnPositions = new int[var3];
      this.isAscending = new boolean[var3];

      for(int var4 = 0; var4 < var3; ++var4) {
         this.baseColumnPositions[var4] = var2.getInt("bcp" + var4);
         this.isAscending[var4] = var2.getBoolean("isAsc" + var4);
      }

      this.numberOfOrderedColumns = var2.getInt("orderedColumns");
      this.indexType = (String)var2.get("indexType");
      if (var2.containsKey("isUniqueWithDuplicateNulls")) {
         this.isUniqueWithDuplicateNulls = var2.getBoolean("isUniqueWithDuplicateNulls");
      } else {
         this.isUniqueWithDuplicateNulls = false;
      }

      if (var2.containsKey("hasDeferrableChecking")) {
         this.hasDeferrableChecking = var2.getBoolean("hasDeferrableChecking");
      } else {
         this.hasDeferrableChecking = false;
      }

      if (var2.containsKey("isUniqueDeferrable")) {
         this.isUniqueDeferrable = var2.getBoolean("isUniqueDeferrable");
      } else {
         this.isUniqueDeferrable = false;
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      FormatableHashtable var2 = new FormatableHashtable();
      var2.putBoolean("isUnique", this.isUnique);
      var2.putInt("keyLength", this.baseColumnPositions.length);

      for(int var3 = 0; var3 < this.baseColumnPositions.length; ++var3) {
         var2.putInt("bcp" + var3, this.baseColumnPositions[var3]);
         var2.putBoolean("isAsc" + var3, this.isAscending[var3]);
      }

      var2.putInt("orderedColumns", this.numberOfOrderedColumns);
      var2.put("indexType", this.indexType);
      var2.putBoolean("isUniqueWithDuplicateNulls", this.isUniqueWithDuplicateNulls);
      var2.putBoolean("hasDeferrableChecking", this.hasDeferrableChecking);
      var2.putBoolean("isUniqueDeferrable", this.isUniqueDeferrable);
      var1.writeObject(var2);
   }

   public int getTypeFormatId() {
      return 387;
   }

   public boolean equals(Object var1) {
      boolean var2 = false;
      if (var1 instanceof IndexDescriptorImpl var3) {
         if (var3.isUnique == this.isUnique && var3.isUniqueWithDuplicateNulls == this.isUniqueWithDuplicateNulls && var3.baseColumnPositions.length == this.baseColumnPositions.length && var3.numberOfOrderedColumns == this.numberOfOrderedColumns && var3.indexType.equals(this.indexType)) {
            var2 = true;

            for(int var4 = 0; var4 < this.baseColumnPositions.length; ++var4) {
               if (var3.baseColumnPositions[var4] != this.baseColumnPositions[var4] || var3.isAscending[var4] != this.isAscending[var4]) {
                  var2 = false;
                  break;
               }
            }
         }
      }

      return var2;
   }

   public int hashCode() {
      int var1 = this.isUnique ? 1 : 2;
      var1 *= this.numberOfOrderedColumns;

      for(int var2 = 0; var2 < this.baseColumnPositions.length; ++var2) {
         var1 *= this.baseColumnPositions[var2];
      }

      var1 *= this.indexType.hashCode();
      return var1;
   }
}
