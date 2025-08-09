package org.apache.derby.iapi.services.classfile;

class MemberTableHash {
   String name;
   String descriptor;
   int index;
   int hashCode;

   MemberTableHash(String var1, String var2, int var3) {
      this.name = var1;
      this.descriptor = var2;
      this.index = var3;
      if (var1 != null && var2 != null) {
         this.setHashCode();
      }

   }

   MemberTableHash(String var1, String var2) {
      this(var1, var2, -1);
   }

   void setHashCode() {
      this.hashCode = this.name.hashCode() + this.descriptor.hashCode();
   }

   public boolean equals(Object var1) {
      MemberTableHash var2 = (MemberTableHash)var1;
      if (var1 == null) {
         return false;
      } else {
         return this.name.equals(var2.name) && this.descriptor.equals(var2.descriptor);
      }
   }

   public int hashCode() {
      return this.hashCode;
   }
}
