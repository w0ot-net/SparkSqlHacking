package org.apache.derby.iapi.store.raw;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;

public final class PageKey {
   private final ContainerKey container;
   private final long pageNumber;

   public PageKey(ContainerKey var1, long var2) {
      this.container = var1;
      this.pageNumber = var2;
   }

   public long getPageNumber() {
      return this.pageNumber;
   }

   public ContainerKey getContainerId() {
      return this.container;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      this.container.writeExternal(var1);
      CompressedNumber.writeLong((DataOutput)var1, this.pageNumber);
   }

   public static PageKey read(ObjectInput var0) throws IOException {
      ContainerKey var1 = ContainerKey.read(var0);
      long var2 = CompressedNumber.readLong((DataInput)var0);
      return new PageKey(var1, var2);
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof PageKey var2)) {
         return false;
      } else {
         return this.pageNumber == var2.pageNumber && this.container.equals(var2.container);
      }
   }

   public int hashCode() {
      int var1 = 7;
      var1 = 79 * var1 + this.container.hashCode();
      var1 = 79 * var1 + (int)(this.pageNumber ^ this.pageNumber >>> 32);
      return var1;
   }

   public String toString() {
      long var10000 = this.pageNumber;
      return "Page(" + var10000 + "," + this.container.toString() + ")";
   }
}
