package org.apache.derby.impl.io.vfmem;

import java.io.OutputStream;

public class BlockedByteArrayOutputStream extends OutputStream {
   private BlockedByteArray src;
   private long pos;

   public BlockedByteArrayOutputStream(BlockedByteArray var1, long var2) {
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

   public void write(int var1) {
      this.pos += (long)this.src.writeByte(this.pos, (byte)var1);
   }

   public void write(byte[] var1, int var2, int var3) {
      this.pos += (long)this.src.writeBytes(this.pos, var1, var2, var3);
   }

   public void close() {
      this.src = null;
   }
}
