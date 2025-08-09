package org.apache.xbean.asm9;

public final class Handle {
   private final int tag;
   private final String owner;
   private final String name;
   private final String descriptor;
   private final boolean isInterface;

   /** @deprecated */
   @Deprecated
   public Handle(int tag, String owner, String name, String descriptor) {
      this(tag, owner, name, descriptor, tag == 9);
   }

   public Handle(int tag, String owner, String name, String descriptor, boolean isInterface) {
      this.tag = tag;
      this.owner = owner;
      this.name = name;
      this.descriptor = descriptor;
      this.isInterface = isInterface;
   }

   public int getTag() {
      return this.tag;
   }

   public String getOwner() {
      return this.owner;
   }

   public String getName() {
      return this.name;
   }

   public String getDesc() {
      return this.descriptor;
   }

   public boolean isInterface() {
      return this.isInterface;
   }

   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else if (!(object instanceof Handle)) {
         return false;
      } else {
         Handle handle = (Handle)object;
         return this.tag == handle.tag && this.isInterface == handle.isInterface && this.owner.equals(handle.owner) && this.name.equals(handle.name) && this.descriptor.equals(handle.descriptor);
      }
   }

   public int hashCode() {
      return this.tag + (this.isInterface ? 64 : 0) + this.owner.hashCode() * this.name.hashCode() * this.descriptor.hashCode();
   }

   public String toString() {
      return stringConcat$0(this.owner, this.name, this.descriptor, this.tag, this.isInterface ? " itf" : "");
   }

   // $FF: synthetic method
   private static String stringConcat$0(String var0, String var1, String var2, int var3, String var4) {
      return var0 + "." + var1 + var2 + " (" + var3 + var4 + ")";
   }
}
