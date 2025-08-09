package org.apache.derby.iapi.store.access;

import java.util.Arrays;

public abstract class GlobalXact {
   protected int format_id;
   protected byte[] global_id;
   protected byte[] branch_id;

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof GlobalXact)) {
         return false;
      } else {
         GlobalXact var2 = (GlobalXact)var1;
         return Arrays.equals(var2.global_id, this.global_id) && Arrays.equals(var2.branch_id, this.branch_id) && var2.format_id == this.format_id;
      }
   }

   public String toString() {
      String var1 = "";
      String var2 = "";
      if (this.global_id != null) {
         int var3 = 0;

         for(int var4 = 0; var4 < this.global_id.length; ++var4) {
            var3 = this.global_id[var4] & 255;
            if (var3 < 16) {
               var1 = var1 + "0" + Integer.toHexString(var3);
            } else {
               var1 = var1 + Integer.toHexString(var3);
            }
         }
      }

      if (this.branch_id != null) {
         int var6 = 0;

         for(int var8 = 0; var8 < this.branch_id.length; ++var8) {
            var6 = this.branch_id[var8] & 255;
            if (var6 < 16) {
               var2 = var2 + "0" + Integer.toHexString(var6);
            } else {
               var2 = var2 + Integer.toHexString(var6);
            }
         }
      }

      return "(" + this.format_id + "," + var1 + "," + var2 + ")";
   }

   public int hashCode() {
      int var1 = this.global_id.length + this.branch_id.length + (this.format_id & 268435455);

      for(int var2 = 0; var2 < this.global_id.length; ++var2) {
         var1 += this.global_id[var2];
      }

      for(int var3 = 0; var3 < this.branch_id.length; ++var3) {
         var1 += this.branch_id[var3];
      }

      return var1;
   }
}
