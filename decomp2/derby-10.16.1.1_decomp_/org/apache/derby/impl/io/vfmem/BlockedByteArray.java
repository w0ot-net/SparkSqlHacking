package org.apache.derby.impl.io.vfmem;

public class BlockedByteArray {
   private static final int _4K = 4096;
   private static final int _8K = 8192;
   private static final int _16K = 16384;
   private static final int _32K = 32768;
   private static final int DEFAULT_BLOCKSIZE = 4096;
   private static final int INITIAL_BLOCK_HOLDER_SIZE = 1024;
   private static final int MIN_HOLDER_GROWTH = 1024;
   private byte[][] blocks = new byte[1024][];
   private int blockSize;
   private int allocatedBlocks;
   private long length;

   public synchronized int read(long var1) {
      if (var1 < this.length) {
         int var3 = (int)(var1 / (long)this.blockSize);
         int var4 = (int)(var1 % (long)this.blockSize);
         return this.blocks[var3][var4] & 255;
      } else {
         return -1;
      }
   }

   public synchronized int read(long var1, byte[] var3, int var4, int var5) {
      if (var5 < 0) {
         throw new ArrayIndexOutOfBoundsException(var5);
      } else if (var1 >= this.length) {
         return -1;
      } else {
         var5 = (int)Math.min((long)var5, this.length - var1);
         int var6 = (int)(var1 / (long)this.blockSize);
         int var7 = (int)(var1 % (long)this.blockSize);

         int var8;
         for(var8 = 0; var8 < var5; var7 = 0) {
            int var9 = Math.min(var5 - var8, this.blockSize - var7);
            System.arraycopy(this.blocks[var6], var7, var3, var4 + var8, var9);
            var8 += var9;
            ++var6;
         }

         return var8;
      }
   }

   public synchronized long length() {
      return this.length;
   }

   public synchronized void setLength(long var1) {
      if (this.blockSize == 0) {
         this.checkBlockSize((int)Math.min(2147483647L, var1));
      }

      long var3 = (long)this.allocatedBlocks * (long)this.blockSize;
      if (var1 > var3) {
         this.increaseCapacity(var1);
      } else if (var1 < var3) {
         if (var1 <= 0L) {
            this.allocatedBlocks = 0;
            this.blocks = new byte[1024][];
         } else {
            int var5 = (int)(var1 / (long)this.blockSize) + 1;

            for(int var6 = var5; var6 <= this.allocatedBlocks; ++var6) {
               this.blocks[var6] = null;
            }

            this.allocatedBlocks = Math.min(this.allocatedBlocks, var5);
         }
      }

      this.length = Math.max(0L, var1);
   }

   public synchronized int writeBytes(long var1, byte[] var3, int var4, int var5) {
      if (this.blockSize == 0) {
         this.checkBlockSize(var5);
      }

      if (var5 < 0) {
         throw new ArrayIndexOutOfBoundsException(var5);
      } else {
         this.increaseCapacity(var1 + (long)var5);
         int var6 = (int)(var1 / (long)this.blockSize);
         int var7 = (int)(var1 % (long)this.blockSize);
         int var8 = 0;

         while(var8 < var5) {
            int var9 = Math.min(var5 - var8, this.blockSize - var7);
            System.arraycopy(var3, var4, this.blocks[var6], var7, var9);
            var8 += var9;
            var4 += var9;
            if (var8 < var5) {
               ++var6;
               var7 = 0;
            } else {
               var7 += var9;
            }
         }

         this.length = Math.max(this.length, var1 + (long)var5);
         return var8;
      }
   }

   public synchronized int writeByte(long var1, byte var3) {
      if (this.blockSize == 0) {
         this.checkBlockSize(0);
      }

      this.increaseCapacity(var1);
      int var4 = (int)(var1 / (long)this.blockSize);
      int var5 = (int)(var1 % (long)this.blockSize);
      this.blocks[var4][var5] = var3;
      this.length = Math.max(this.length, var1 + 1L);
      return 1;
   }

   synchronized BlockedByteArrayInputStream getInputStream() {
      return new BlockedByteArrayInputStream(this, 0L);
   }

   synchronized BlockedByteArrayOutputStream getOutputStream(long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException("Position cannot be negative: " + var1);
      } else {
         return new BlockedByteArrayOutputStream(this, var1);
      }
   }

   synchronized void release() {
      this.blocks = null;
      this.length = (long)(this.allocatedBlocks = -1);
   }

   private void checkBlockSize(int var1) {
      if (var1 != 4096 && var1 != 8192 && var1 != 16384 && var1 != 32768) {
         this.blockSize = 4096;
      } else {
         this.blockSize = var1;
      }

   }

   private void increaseCapacity(long var1) {
      if (var1 >= (long)this.allocatedBlocks * (long)this.blockSize) {
         int var3 = (int)(var1 / (long)this.blockSize) + 1;
         if (var3 > this.blocks.length) {
            int var4 = Math.max(this.blocks.length + this.blocks.length / 3, var3 + 1024);
            byte[][] var5 = this.blocks;
            this.blocks = new byte[var4][];
            System.arraycopy(var5, 0, this.blocks, 0, this.allocatedBlocks);
         }

         for(int var6 = this.allocatedBlocks; var6 < var3; ++var6) {
            this.blocks[var6] = new byte[this.blockSize];
         }

         this.allocatedBlocks = var3;
      }
   }
}
