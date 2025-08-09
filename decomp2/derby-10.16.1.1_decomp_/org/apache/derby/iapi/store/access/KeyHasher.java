package org.apache.derby.iapi.store.access;

public class KeyHasher {
   private final Object[] objects;

   public KeyHasher(int var1) {
      this.objects = new Object[var1];
   }

   public void setObject(int var1, Object var2) {
      this.objects[var1] = var2;
   }

   public Object getObject(int var1) {
      return this.objects[var1];
   }

   public static Object buildHashKey(Object[] var0, int[] var1) {
      if (var1.length == 1) {
         return var0[var1[0]];
      } else {
         KeyHasher var2 = new KeyHasher(var1.length);

         for(int var3 = 0; var3 < var1.length; ++var3) {
            var2.setObject(var3, var0[var1[var3]]);
         }

         return var2;
      }
   }

   public int hashCode() {
      int var1 = 0;

      for(int var2 = 0; var2 < this.objects.length; ++var2) {
         var1 += this.objects[var2].hashCode();
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof KeyHasher var2)) {
         return false;
      } else if (var2.objects.length != this.objects.length) {
         return false;
      } else {
         for(int var3 = 0; var3 < this.objects.length; ++var3) {
            if (!var2.objects[var3].equals(this.objects[var3])) {
               return false;
            }
         }

         return true;
      }
   }
}
