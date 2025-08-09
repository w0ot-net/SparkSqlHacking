package org.apache.derby.impl.jdbc;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UTFDataFormatException;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.ExceptionUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

final class LOBStreamControl {
   private LOBFile tmpFile;
   private byte[] dataBytes = new byte[0];
   private boolean isBytes = true;
   private final int bufferSize;
   private final EmbedConnection conn;
   private long updateCount;
   private static final int DEFAULT_BUF_SIZE = 4096;
   private static final int MAX_BUF_SIZE = 32768;

   LOBStreamControl(EmbedConnection var1) {
      this.conn = var1;
      this.updateCount = 0L;
      this.bufferSize = 4096;
   }

   LOBStreamControl(EmbedConnection var1, byte[] var2) throws IOException, StandardException {
      this.conn = var1;
      this.updateCount = 0L;
      this.bufferSize = Math.min(Math.max(4096, var2.length), 32768);
      this.write(var2, 0, var2.length, 0L);
   }

   private void init(byte[] var1, long var2) throws IOException, StandardException {
      Object var4 = findService("org.apache.derby.database.Database", this.conn.getDBName());
      DataFactory var5 = (DataFactory)findServiceModule(var4, "org.apache.derby.iapi.store.raw.data.DataFactory");
      StorageFile var6 = var5.getStorageFactory().createTemporaryFile("lob", (String)null);
      if (var5.databaseEncrypted()) {
         this.tmpFile = new EncryptedLOBFile(var6, var5);
      } else {
         this.tmpFile = new LOBFile(var6);
      }

      this.conn.addLobFile(this.tmpFile);
      this.isBytes = false;
      if (var2 != 0L) {
         this.write(var1, 0, (int)var2, 0L);
      }

      this.dataBytes = null;
   }

   private long updateData(byte[] var1, int var2, int var3, long var4) throws StandardException {
      if (this.dataBytes == null) {
         if ((int)var4 == 0) {
            this.dataBytes = new byte[var3];
            System.arraycopy(var1, var2, this.dataBytes, (int)var4, var3);
            return (long)var3;
         } else {
            throw StandardException.newException("XJ076.S", new Object[]{var4});
         }
      } else if (var4 > (long)this.dataBytes.length) {
         throw StandardException.newException("XJ076.S", new Object[]{var4});
      } else {
         if (var4 + (long)var3 < (long)this.dataBytes.length) {
            System.arraycopy(var1, var2, this.dataBytes, (int)var4, var3);
         } else {
            byte[] var6 = new byte[var3 + (int)var4];
            System.arraycopy(this.dataBytes, 0, var6, 0, (int)var4);
            System.arraycopy(var1, var2, var6, (int)var4, var3);
            this.dataBytes = var6;
         }

         return var4 + (long)var3;
      }
   }

   private void isValidPostion(long var1) throws IOException, StandardException {
      if (var1 < 0L) {
         throw StandardException.newException("XJ071.S", new Object[]{var1 + 1L});
      } else if (var1 > 2147483647L) {
         throw StandardException.newException("XJ076.S", new Object[]{var1 + 1L});
      } else {
         if (this.isBytes) {
            if (this.dataBytes == null) {
               if (var1 != 0L) {
                  throw StandardException.newException("XJ076.S", new Object[]{var1 + 1L});
               }
            } else if ((long)this.dataBytes.length < var1) {
               throw StandardException.newException("XJ076.S", new Object[]{var1 + 1L});
            }
         } else if (var1 > this.tmpFile.length()) {
            throw StandardException.newException("XJ076.S", new Object[]{var1 + 1L});
         }

      }
   }

   private void isValidOffset(int var1, int var2) throws StandardException {
      if (var1 < 0 || var1 > var2) {
         throw StandardException.newException("XJ078.S", new Object[]{var1});
      }
   }

   synchronized long write(int var1, long var2) throws IOException, StandardException {
      this.isValidPostion(var2);
      ++this.updateCount;
      if (this.isBytes) {
         if (var2 < (long)this.bufferSize) {
            byte[] var4 = new byte[]{(byte)var1};
            this.updateData(var4, 0, 1, var2);
            return var2 + 1L;
         }

         this.init(this.dataBytes, var2);
      }

      this.tmpFile.seek(var2);
      this.tmpFile.write(var1);
      return this.tmpFile.getFilePointer();
   }

   synchronized long write(byte[] var1, int var2, int var3, long var4) throws IOException, StandardException {
      this.isValidPostion(var4);

      try {
         this.isValidOffset(var2, var1.length);
      } catch (StandardException var7) {
         if (var7.getSQLState().equals(ExceptionUtil.getSQLStateFromIdentifier("XJ078.S"))) {
            throw new ArrayIndexOutOfBoundsException(var7.getMessage());
         }

         throw var7;
      }

      ++this.updateCount;
      if (this.isBytes) {
         if (var4 + (long)var3 <= (long)this.bufferSize) {
            return this.updateData(var1, var2, var3, var4);
         }

         this.init(this.dataBytes, var4);
      }

      this.tmpFile.seek(var4);
      this.tmpFile.write(var1, var2, var3);
      return this.tmpFile.getFilePointer();
   }

   synchronized int read(long var1) throws IOException, StandardException {
      this.isValidPostion(var1);
      if (this.isBytes) {
         return (long)this.dataBytes.length == var1 ? -1 : this.dataBytes[(int)var1] & 255;
      } else {
         if (this.tmpFile.getFilePointer() != var1) {
            this.tmpFile.seek(var1);
         }

         try {
            return this.tmpFile.readByte() & 255;
         } catch (EOFException var4) {
            return -1;
         }
      }
   }

   private int readBytes(byte[] var1, int var2, int var3, long var4) {
      if (var4 >= (long)this.dataBytes.length) {
         return -1;
      } else {
         int var6 = this.dataBytes.length - (int)var4;
         int var7 = var3 > var6 ? var6 : var3;
         System.arraycopy(this.dataBytes, (int)var4, var1, var2, var7);
         return var7;
      }
   }

   synchronized int read(byte[] var1, int var2, int var3, long var4) throws IOException, StandardException {
      this.isValidPostion(var4);
      this.isValidOffset(var2, var1.length);
      if (this.isBytes) {
         return this.readBytes(var1, var2, var3, var4);
      } else {
         this.tmpFile.seek(var4);
         return this.tmpFile.read(var1, var2, var3);
      }
   }

   InputStream getInputStream(long var1) {
      return new LOBInputStream(this, var1);
   }

   OutputStream getOutputStream(long var1) {
      return new LOBOutputStream(this, var1);
   }

   long getLength() throws IOException {
      return this.isBytes ? (long)this.dataBytes.length : this.tmpFile.length();
   }

   synchronized void truncate(long var1) throws IOException, StandardException {
      this.isValidPostion(var1);
      if (this.isBytes) {
         byte[] var3 = new byte[(int)var1];
         System.arraycopy(this.dataBytes, 0, var3, 0, (int)var1);
         this.dataBytes = var3;
      } else if (var1 < (long)this.bufferSize) {
         this.dataBytes = new byte[(int)var1];
         this.read(this.dataBytes, 0, this.dataBytes.length, 0L);
         this.isBytes = true;
         this.releaseTempFile(this.tmpFile);
         this.tmpFile = null;
      } else {
         this.tmpFile.setLength(var1);
      }

   }

   synchronized void copyData(InputStream var1, long var2) throws IOException, StandardException {
      byte[] var4 = new byte[this.bufferSize];

      int var10;
      for(long var5 = 0L; var5 < var2; var5 += (long)var10) {
         var10 = (int)Math.min(var2 - var5, (long)this.bufferSize);
         var10 = var1.read(var4, 0, var10);
         if (var10 == -1) {
            if (var2 != Long.MAX_VALUE) {
               throw new EOFException(MessageService.getTextMessage("I029", new Object[]{var2, var5}));
            }
            break;
         }

         this.write(var4, 0, var10, var5);
      }

      long var11 = this.getLength();
      if (var2 == Long.MAX_VALUE && var11 > 2L) {
         byte[] var9 = new byte[3];
         this.read(var9, 0, 3, var11 - 3L);
         if ((var9[0] & 255) == 224 && (var9[1] & 255) == 0 && (var9[2] & 255) == 0) {
            this.truncate(var11 - 3L);
         }
      }

   }

   synchronized long copyUtf8Data(InputStream var1, long var2) throws IOException, StandardException {
      long var4 = 0L;
      int var6 = 0;
      int var7 = 0;

      int var9;
      for(byte[] var8 = new byte[this.bufferSize]; var4 < var2; var7 += var9) {
         var9 = var1.read(var8, 0, (int)Math.min((long)var8.length, var2 - var4));
         if (var9 == -1) {
            break;
         }

         for(; var6 < var9; ++var4) {
            int var10 = var8[var6] & 255;
            if ((var10 & 128) == 0) {
               ++var6;
            } else if ((var10 & 96) == 64) {
               var6 += 2;
            } else {
               if ((var10 & 112) != 96) {
                  throw new UTFDataFormatException("Invalid UTF-8 encoding: " + Integer.toHexString(var10) + ", charCount=" + var4 + ", offset=" + var6);
               }

               var6 += 3;
            }
         }

         var6 -= var9;
         this.write(var8, 0, var9, (long)var7);
      }

      long var12 = this.getLength();
      if (var12 > 2L) {
         byte[] var11 = new byte[3];
         this.read(var11, 0, 3, var12 - 3L);
         if ((var11[0] & 255) == 224 && (var11[1] & 255) == 0 && (var11[2] & 255) == 0) {
            this.truncate(var12 - 3L);
            --var4;
         }
      }

      if (var2 != Long.MAX_VALUE && var4 != var2) {
         throw new EOFException(MessageService.getTextMessage("I029", new Object[]{var2, var4}));
      } else {
         return var4;
      }
   }

   protected void finalize() throws Throwable {
      this.free();
   }

   private void deleteFile(StorageFile var1) {
      var1.delete();
   }

   void free() throws IOException {
      this.dataBytes = null;
      if (this.tmpFile != null) {
         this.releaseTempFile(this.tmpFile);
         this.tmpFile = null;
      }

   }

   private void releaseTempFile(LOBFile var1) throws IOException {
      this.conn.removeLobFile(var1);
      var1.close();
      this.deleteFile(var1.getStorageFile());
   }

   synchronized long replaceBytes(byte[] var1, long var2, long var4) throws IOException, StandardException {
      long var6 = this.getLength();
      if (this.isBytes) {
         long var8 = var6 - var4 + var2 + (long)var1.length;
         if (var8 > (long)this.bufferSize) {
            byte[] var10 = this.dataBytes;
            this.init(var10, var2);
            this.write(var1, 0, var1.length, this.getLength());
            if (var4 < var6) {
               this.write(var10, (int)var4, (int)(var6 - var4), this.getLength());
            }
         } else {
            byte[] var16 = new byte[(int)var8];
            System.arraycopy(this.dataBytes, 0, var16, 0, (int)var2);
            System.arraycopy(var1, 0, var16, (int)var2, var1.length);
            if (var4 < var6) {
               System.arraycopy(this.dataBytes, (int)var4, var16, (int)(var2 + (long)var1.length), (int)(var6 - var4));
            }

            this.dataBytes = var16;
         }
      } else {
         byte[] var15 = new byte[0];
         LOBFile var9 = this.tmpFile;
         this.init(var15, 0L);
         byte[] var17 = new byte[1024];
         long var11 = var2;
         var9.seek(0L);

         while(var11 != 0L) {
            int var13 = (int)Math.min(1024L, var11);
            int var14 = var9.read(var17, 0, var13);
            if (var14 == -1) {
               break;
            }

            this.tmpFile.write(var17, 0, var14);
            var11 -= (long)var14;
         }

         this.tmpFile.write(var1);
         var9.seek(var4);
         if (var4 < var6) {
            while(true) {
               int var18 = var9.read(var17, 0, 1024);
               if (var18 == -1) {
                  break;
               }

               this.tmpFile.write(var17, 0, var18);
            }
         }

         this.releaseTempFile(var9);
      }

      ++this.updateCount;
      return var2 + (long)var1.length;
   }

   long getUpdateCount() {
      return this.updateCount;
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }

   private static Object findService(String var0, String var1) {
      return Monitor.findService(var0, var1);
   }
}
