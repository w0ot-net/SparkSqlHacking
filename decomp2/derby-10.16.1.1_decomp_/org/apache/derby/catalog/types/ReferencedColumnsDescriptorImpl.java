package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.ReferencedColumns;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.shared.common.util.ArrayUtil;

public class ReferencedColumnsDescriptorImpl implements ReferencedColumns, Formatable {
   private int[] referencedColumns;
   private int[] referencedColumnsInTriggerAction;

   public ReferencedColumnsDescriptorImpl(int[] var1) {
      this.referencedColumns = ArrayUtil.copy(var1);
   }

   public ReferencedColumnsDescriptorImpl(int[] var1, int[] var2) {
      this.referencedColumns = ArrayUtil.copy(var1);
      this.referencedColumnsInTriggerAction = ArrayUtil.copy(var2);
   }

   public ReferencedColumnsDescriptorImpl() {
   }

   public int[] getReferencedColumnPositions() {
      return ArrayUtil.copy(this.referencedColumns);
   }

   public int[] getTriggerActionReferencedColumnPositions() {
      return ArrayUtil.copy(this.referencedColumnsInTriggerAction);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      int var3 = var1.readInt();
      int var2;
      if (var3 < 0) {
         var2 = var1.readInt();
         if (var2 < 0) {
            var2 = 0;
         } else {
            this.referencedColumns = new int[var2];
         }
      } else {
         var2 = var3;
         this.referencedColumns = new int[var3];
      }

      for(int var4 = 0; var4 < var2; ++var4) {
         this.referencedColumns[var4] = var1.readInt();
      }

      if (var3 < 0) {
         int var6 = var1.readInt();
         this.referencedColumnsInTriggerAction = new int[var6];

         for(int var5 = 0; var5 < var6; ++var5) {
            this.referencedColumnsInTriggerAction[var5] = var1.readInt();
         }
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      int var2 = this.referencedColumnsInTriggerAction == null ? this.referencedColumns.length : -1;
      if (var2 < 0) {
         var1.writeInt(var2);
         if (this.referencedColumns != null) {
            this.writeReferencedColumns(var1);
         } else {
            var1.writeInt(var2);
         }

         var1.writeInt(this.referencedColumnsInTriggerAction.length);

         for(int var3 = 0; var3 < this.referencedColumnsInTriggerAction.length; ++var3) {
            var1.writeInt(this.referencedColumnsInTriggerAction[var3]);
         }
      } else {
         this.writeReferencedColumns(var1);
      }

   }

   private void writeReferencedColumns(ObjectOutput var1) throws IOException {
      var1.writeInt(this.referencedColumns.length);

      for(int var2 = 0; var2 < this.referencedColumns.length; ++var2) {
         var1.writeInt(this.referencedColumns[var2]);
      }

   }

   public int getTypeFormatId() {
      return 205;
   }

   public String toString() {
      if (this.referencedColumns == null) {
         return "NULL";
      } else {
         StringBuffer var1 = new StringBuffer(60);
         var1.append('(');

         for(int var2 = 0; var2 < this.referencedColumns.length; ++var2) {
            if (var2 > 0) {
               var1.append(',');
            }

            var1.append(String.valueOf(this.referencedColumns[var2]));
         }

         var1.append(')');
         return var1.toString();
      }
   }
}
