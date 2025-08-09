package org.apache.derby.impl.jdbc;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

class EncryptedLOBFile extends LOBFile {
   private final int blockSize;
   private final byte[] tail;
   private int tailSize;
   private long currentPos;
   private final DataFactory df;

   EncryptedLOBFile(StorageFile var1, DataFactory var2) throws FileNotFoundException {
      super(var1);
      this.df = var2;
      this.blockSize = var2.getEncryptionBlockSize();
      this.tail = new byte[this.blockSize];
      this.tailSize = 0;
   }

   private byte[] getBlocks(long var1, int var3) throws IOException, StandardException {
      if (var3 < 0) {
         throw new IndexOutOfBoundsException(MessageService.getTextMessage("XJ071.S", new Object[]{var3}));
      } else {
         long var4 = var1 - var1 % (long)this.blockSize;
         long var6 = (var1 + (long)var3 + (long)this.blockSize - 1L) / (long)this.blockSize * (long)this.blockSize;
         byte[] var8 = new byte[(int)(var6 - var4)];
         super.seek(var4);
         super.read(var8, 0, var8.length);
         return var8;
      }
   }

   long length() throws IOException {
      return super.length() + (long)this.tailSize;
   }

   long getFilePointer() {
      return this.currentPos;
   }

   void seek(long var1) throws IOException {
      long var3 = super.length();
      if (var1 > var3 + (long)this.tailSize) {
         throw new IllegalArgumentException("Internal Error");
      } else {
         if (var1 < var3) {
            super.seek(var1);
         }

         this.currentPos = var1;
      }
   }

   void write(int var1) throws IOException, StandardException {
      long var2 = super.length();
      if (this.currentPos >= var2) {
         int var4 = (int)(this.currentPos - var2);
         this.tail[var4] = (byte)var1;
         if (var4 >= this.tailSize) {
            this.tailSize = var4 + 1;
         }

         if (this.tailSize == this.blockSize) {
            byte[] var5 = new byte[this.blockSize];
            this.df.encrypt(this.tail, 0, this.tailSize, var5, 0, false);
            super.seek(var2);
            super.write(var5);
            this.tailSize = 0;
         }
      } else {
         byte[] var6 = this.getBlocks(this.currentPos, 1);
         byte[] var7 = new byte[this.blockSize];
         this.df.decrypt(var6, 0, this.blockSize, var7, 0);
         var7[(int)(this.currentPos % (long)this.blockSize)] = (byte)var1;
         this.df.encrypt(var7, 0, this.blockSize, var6, 0, false);
         super.seek(this.currentPos - this.currentPos % (long)this.blockSize);
         super.write(var6);
      }

      ++this.currentPos;
   }

   void write(byte[] var1, int var2, int var3) throws IOException, StandardException {
      long var4 = super.length();
      if (this.currentPos < var4) {
         int var6 = (int)Math.max(0L, this.currentPos + (long)var3 - var4);
         long var7 = this.currentPos;
         byte[] var9 = this.getBlocks(this.currentPos, var3 - var6);
         byte[] var10 = new byte[var9.length];

         for(int var11 = 0; var11 < var9.length / this.blockSize; ++var11) {
            this.df.decrypt(var9, var11 * this.blockSize, this.blockSize, var10, var11 * this.blockSize);
         }

         System.arraycopy(var1, var2, var10, (int)(this.currentPos % (long)this.blockSize), var3 - var6);

         for(int var17 = 0; var17 < var9.length / this.blockSize; ++var17) {
            this.df.encrypt(var10, var17 * this.blockSize, this.blockSize, var9, var17 * this.blockSize, false);
         }

         super.seek(var7 - var7 % (long)this.blockSize);
         super.write(var9);
         this.currentPos = var7 + (long)var9.length;
         if (var6 == 0) {
            return;
         }

         var2 = var2 + var3 - var6;
         var3 = var6;
         this.currentPos = var4;
      }

      int var13 = (int)(this.currentPos - var4);
      int var14 = var13 + var3;
      if (var14 < this.blockSize) {
         System.arraycopy(var1, var2, this.tail, var13, var3);
         this.tailSize = Math.max(this.tailSize, var13 + var3);
         this.currentPos += (long)var3;
      } else {
         int var8 = var14 - var14 % this.blockSize;
         int var15 = var14 % this.blockSize;
         byte[] var16 = new byte[var8];
         System.arraycopy(this.tail, 0, var16, 0, var13);
         System.arraycopy(var1, var2, var16, var13, var8 - var13);
         byte[] var18 = new byte[var16.length];

         for(int var12 = 0; var12 < var18.length; var12 += this.blockSize) {
            this.df.encrypt(var16, var12, this.blockSize, var18, var12, false);
         }

         super.seek(var4);
         super.write(var18);
         System.arraycopy(var1, var2 + var3 - var15, this.tail, 0, var15);
         this.tailSize = var15;
         this.currentPos = (long)this.tailSize + var4 + (long)var18.length;
      }
   }

   void write(byte[] var1) throws IOException, StandardException {
      this.write(var1, 0, var1.length);
   }

   int readByte() throws IOException, StandardException {
      long var1 = super.length();
      if (this.currentPos >= var1 + (long)this.tailSize) {
         throw new EOFException();
      } else if (this.currentPos >= var1) {
         return this.tail[(int)(this.currentPos++ - var1)] & 255;
      } else {
         byte[] var3 = this.getBlocks(this.currentPos, 1);
         byte[] var4 = new byte[var3.length];
         this.df.decrypt(var3, 0, var3.length, var4, 0);
         return var4[(int)(this.currentPos++ % (long)this.blockSize)] & 255;
      }
   }

   int read(byte[] var1, int var2, int var3) throws IOException, StandardException {
      long var4 = super.length();
      if (this.currentPos >= var4) {
         int var10 = (int)Math.min((long)this.tailSize - this.currentPos + var4, (long)var3);
         if (var10 == 0 && var3 != 0) {
            return -1;
         } else {
            System.arraycopy(this.tail, (int)(this.currentPos - var4), var1, var2, var10);
            this.currentPos += (long)var10;
            return var10;
         }
      } else {
         int var6 = (int)Math.max(0L, this.currentPos + (long)var3 - var4);
         byte[] var7 = this.getBlocks(this.currentPos, var3 - var6);
         byte[] var8 = new byte[var7.length];

         for(int var9 = 0; var9 < var7.length; var9 += this.blockSize) {
            this.df.decrypt(var7, var9, this.blockSize, var8, var9);
         }

         System.arraycopy(var8, (int)(this.currentPos % (long)this.blockSize), var1, var2, var3 - var6);
         if (var6 == 0) {
            this.currentPos += (long)var3;
            return var3;
         } else {
            int var11 = Math.min(var6, this.tailSize);
            System.arraycopy(this.tail, 0, var1, var2 + var3 - var6, var11);
            this.currentPos += (long)(var3 - var6 + var11);
            return var3 - var6 + var11;
         }
      }
   }

   void setLength(long var1) throws IOException, StandardException {
      long var3 = super.length();
      if (var1 > var3 + (long)this.tailSize) {
         throw new IllegalArgumentException("Internal Error");
      } else {
         if (var1 < var3) {
            byte[] var5 = this.getBlocks(var1, 1);
            super.setLength(var1 - var1 % (long)this.blockSize);
            this.df.decrypt(var5, 0, this.blockSize, this.tail, 0);
            this.tailSize = (int)(var1 % (long)this.blockSize);
         } else {
            this.tailSize = (int)(var1 - var3);
         }

      }
   }
}
