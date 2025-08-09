package org.apache.derby.impl.io.vfmem;

import java.io.InputStream;

class BlockedByteArrayInputStream extends InputStream {
   private BlockedByteArray src;
   private long pos;

   public BlockedByteArrayInputStream(BlockedByteArray var1, long var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("BlockedByteArray cannot be null");
      } else {
         this.src = var1;
         this.pos = var2;
      }
   }

   void setPosition(long var1) {
      this.pos = var1;
   }

   long getPosition() {
      return this.pos;
   }

   public int read() {
      int var1 = this.src.read(this.pos);
      if (var1 != -1) {
         ++this.pos;
      }

      return var1;
   }

   public int read(byte[] var1, int var2, int var3) {
      int var4 = this.src.read(this.pos, var1, var2, var3);
      if (var4 != -1) {
         this.pos += (long)var4;
      }

      return var4;
   }

   public void close() {
      this.src = null;
   }
}
