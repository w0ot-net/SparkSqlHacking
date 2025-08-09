package org.apache.derby.impl.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.shared.common.error.StandardException;

class DirFile extends File implements StorageFile {
   private RandomAccessFile lockFileOpen;
   private FileChannel lockFileChannel;
   private FileLock dbLock;

   DirFile(String var1) {
      super(var1);
   }

   DirFile(String var1, String var2) {
      super(var1, var2);
   }

   DirFile(DirFile var1, String var2) {
      super(var1, var2);
   }

   public StorageFile getParentDir() {
      String var1 = this.getParent();
      return var1 == null ? null : new DirFile(var1);
   }

   public OutputStream getOutputStream() throws FileNotFoundException {
      return this.getOutputStream(false);
   }

   public OutputStream getOutputStream(boolean var1) throws FileNotFoundException {
      boolean var2 = this.exists();
      FileOutputStream var3 = new FileOutputStream(this, var1);
      if (!var2) {
         try {
            this.limitAccessToOwner();
         } catch (FileNotFoundException var6) {
            throw var6;
         } catch (IOException var7) {
            FileNotFoundException var5 = new FileNotFoundException();
            var5.initCause(var7);
            throw var5;
         }
      }

      return var3;
   }

   public InputStream getInputStream() throws FileNotFoundException {
      return new FileInputStream(this);
   }

   public synchronized int getExclusiveFileLock() throws StandardException {
      boolean var1 = false;

      byte var2;
      try {
         if (this.createNewFile()) {
            var1 = true;
         } else if (this.length() > 0L) {
            var1 = true;
         }

         if (!var1) {
            var2 = 0;
         } else {
            int var3 = 120;

            while(true) {
               this.lockFileOpen = new RandomAccessFile(this, "rw");
               this.limitAccessToOwner();
               this.lockFileChannel = this.lockFileOpen.getChannel();

               try {
                  this.dbLock = this.lockFileChannel.tryLock();
                  if (this.dbLock == null) {
                     this.lockFileChannel.close();
                     this.lockFileChannel = null;
                     this.lockFileOpen.close();
                     this.lockFileOpen = null;
                     var2 = 2;
                  } else {
                     this.lockFileOpen.writeInt(1);
                     this.lockFileChannel.force(true);
                     var2 = 1;
                  }
                  break;
               } catch (AsynchronousCloseException var6) {
                  InterruptStatus.setInterrupted();
                  this.lockFileOpen.close();
                  if (var3-- <= 0) {
                     throw var6;
                  }
               }
            }
         }
      } catch (IOException var7) {
         this.releaseExclusiveFileLock();
         var2 = 0;
      } catch (OverlappingFileLockException var8) {
         try {
            this.lockFileChannel.close();
            this.lockFileOpen.close();
         } catch (IOException var5) {
         }

         this.lockFileChannel = null;
         this.lockFileOpen = null;
         var2 = 2;
      }

      return var2;
   }

   public synchronized void releaseExclusiveFileLock() {
      try {
         if (this.dbLock != null) {
            this.dbLock.release();
            this.dbLock = null;
         }

         if (this.lockFileChannel != null) {
            this.lockFileChannel.close();
            this.lockFileChannel = null;
         }

         if (this.lockFileOpen != null) {
            this.lockFileOpen.close();
            this.lockFileOpen = null;
         }

         if (this.exists()) {
            this.delete();
         }
      } catch (IOException var2) {
      }

   }

   public StorageRandomAccessFile getRandomAccessFile(String var1) throws FileNotFoundException {
      return new DirRandomAccessFile(this, var1);
   }

   public boolean renameTo(StorageFile var1) {
      boolean var2 = super.renameTo((File)var1);

      for(int var3 = 1; !var2 && var3 <= 5; ++var3) {
         try {
            Thread.sleep((long)(1000 * var3));
         } catch (InterruptedException var5) {
            InterruptStatus.setInterrupted();
         }

         var2 = super.renameTo((File)var1);
      }

      return var2;
   }

   public boolean deleteAll() {
      if (!this.exists()) {
         return false;
      } else {
         String[] var1 = super.list();
         if (var1 != null) {
            String var2 = this.getPath();

            for(int var3 = 0; var3 < var1.length; ++var3) {
               if (!var1[var3].equals(".") && !var1[var3].equals("..")) {
                  DirFile var4 = new DirFile(var2, var1[var3]);
                  if (!var4.deleteAll()) {
                     return false;
                  }
               }
            }
         }

         return this.delete();
      }
   }

   public void limitAccessToOwner() throws IOException {
      FileUtil.limitAccessToOwner(this);
   }
}
