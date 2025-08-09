package org.apache.derby.impl.store.raw.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.util.InterruptDetectedException;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.shared.common.error.StandardException;

class RAFContainer extends FileContainer {
   protected StorageRandomAccessFile fileData;
   protected boolean needsSync;
   private int actionCode;
   private static final int GET_FILE_NAME_ACTION = 1;
   private static final int CREATE_CONTAINER_ACTION = 2;
   private static final int REMOVE_FILE_ACTION = 3;
   private static final int OPEN_CONTAINER_ACTION = 4;
   private static final int STUBBIFY_ACTION = 5;
   private static final int GET_RANDOM_ACCESS_FILE_ACTION = 7;
   private static final int REOPEN_CONTAINER_ACTION = 8;
   private ContainerKey actionIdentity;
   private boolean actionStub;
   private boolean actionErrorOK;
   private boolean actionTryAlternatePath;
   private StorageFile actionFile;
   private LogInstant actionInstant;
   private boolean inBackup = false;
   private boolean inRemove = false;
   private String fileName;

   RAFContainer(BaseDataFileFactory var1) {
      super(var1);
   }

   public synchronized boolean isDirty() {
      return super.isDirty() || this.needsSync;
   }

   protected void removeContainer(LogInstant var1, boolean var2) throws StandardException {
      try {
         synchronized(this) {
            this.inRemove = true;

            while(this.inBackup) {
               try {
                  this.wait();
               } catch (InterruptedException var16) {
                  InterruptStatus.setInterrupted();
               }
            }
         }

         this.pageCache.discard(this.identity);
         this.stubbify(var1);
      } finally {
         synchronized(this) {
            this.inRemove = false;
            this.notifyAll();
         }
      }

   }

   void closeContainer() {
      if (this.fileData != null) {
         try {
            this.fileData.close();
         } catch (IOException var5) {
         } finally {
            this.fileData = null;
         }
      }

   }

   protected void readPage(long var1, byte[] var3) throws IOException, StandardException {
      long var4 = var1 * (long)this.pageSize;
      synchronized(this) {
         this.fileData.seek(var4);
         this.fileData.readFully(var3, 0, this.pageSize);
      }

      if (this.dataFactory.databaseEncrypted() && var1 != 0L) {
         this.decryptPage(var3, this.pageSize);
      }

   }

   protected void writePage(long var1, byte[] var3, boolean var4) throws IOException, StandardException {
      synchronized(this) {
         if (!this.getCommittedDropState()) {
            long var6 = var1 * (long)this.pageSize;
            byte[] var8 = null;
            if (this.dataFactory.databaseEncrypted() && var1 != 0L) {
               var8 = this.getEncryptionBuffer();
            }

            byte[] var9 = this.updatePageArray(var1, var3, var8, false);

            try {
               this.fileData.seek(var6);
               if (this.fileData.getFilePointer() != var6) {
                  this.padFile(this.fileData, var6);
               }

               this.dataFactory.writeInProgress();

               try {
                  this.fileData.write(var9, 0, this.pageSize);
               } finally {
                  this.dataFactory.writeFinished();
               }
            } catch (IOException var32) {
               if (!this.padFile(this.fileData, var6)) {
                  throw var32;
               }

               this.fileData.seek(var6);
               this.dataFactory.writeInProgress();

               try {
                  this.fileData.write(var9, 0, this.pageSize);
               } finally {
                  this.dataFactory.writeFinished();
               }
            }

            if (var4) {
               this.dataFactory.writeInProgress();

               try {
                  if (!this.dataFactory.dataNotSyncedAtAllocation) {
                     this.fileData.sync();
                  }
               } finally {
                  this.dataFactory.writeFinished();
               }
            } else {
               this.needsSync = true;
            }

         }
      }
   }

   protected byte[] updatePageArray(long var1, byte[] var3, byte[] var4, boolean var5) throws StandardException, IOException {
      if (var1 == 0L) {
         this.writeHeader(this.getIdentity(), var3);
         return var3;
      } else {
         return var4 == null || !this.dataFactory.databaseEncrypted() && !var5 ? var3 : this.encryptPage(var3, this.pageSize, var4, var5);
      }
   }

   private boolean padFile(StorageRandomAccessFile var1, long var2) throws IOException, StandardException {
      long var4 = var1.length();
      if (var4 >= var2) {
         return false;
      } else {
         byte[] var6 = new byte[this.pageSize];
         var1.seek(var4);

         for(; var4 < var2; var4 += (long)this.pageSize) {
            this.dataFactory.writeInProgress();

            try {
               long var7 = var2 - var4;
               if (var7 > (long)this.pageSize) {
                  var7 = (long)this.pageSize;
               }

               var1.write(var6, 0, (int)var7);
            } finally {
               this.dataFactory.writeFinished();
            }
         }

         return true;
      }
   }

   public void clean(boolean var1) throws StandardException {
      boolean var2 = false;
      boolean var3 = false;
      int var4 = 120;

      while(!var3) {
         var3 = true;
         synchronized(this) {
            if (this.getCommittedDropState()) {
               this.clearDirty();
               return;
            }

            while(this.preDirty) {
               var2 = true;

               try {
                  this.wait();
               } catch (InterruptedException var10) {
                  InterruptStatus.setInterrupted();
               }
            }

            if (var2 && this.getCommittedDropState()) {
               this.clearDirty();
               return;
            }

            if (!var1 && this.isDirty()) {
               try {
                  this.writeRAFHeader(this.getIdentity(), this.fileData, false, true);
                  this.clearDirty();
               } catch (InterruptDetectedException var11) {
                  --var4;
                  if (var4 <= 0) {
                     throw StandardException.newException("XSDG9.D", var11, new Object[0]);
                  }

                  var3 = false;

                  try {
                     Thread.sleep(500L);
                  } catch (InterruptedException var9) {
                     InterruptStatus.setInterrupted();
                  }
               } catch (IOException var12) {
                  throw this.dataFactory.markCorrupt(StandardException.newException("XSDG3.D", var12, new Object[]{this.getIdentity() != null ? this.getIdentity().toString() : "unknown", "clean", this.fileName}));
               }
            }
         }
      }

   }

   private void clearDirty() {
      this.isDirty = false;
      this.needsSync = false;
   }

   protected int preAllocate(long var1, int var3) {
      int var4 = this.doPreAllocatePages(var1, var3);
      if (var4 > 0) {
         synchronized(this) {
            boolean var6 = false;

            try {
               this.dataFactory.writeInProgress();
               var6 = true;
               if (!this.dataFactory.dataNotSyncedAtAllocation) {
                  this.fileData.sync();
               }
            } catch (IOException var14) {
               var4 = 0;
            } catch (StandardException var15) {
               var4 = 0;
            } finally {
               if (var6) {
                  this.dataFactory.writeFinished();
               }

            }
         }
      }

      return var4;
   }

   protected void truncatePages(long var1) throws StandardException {
      synchronized(this) {
         boolean var4 = false;

         try {
            this.dataFactory.writeInProgress();
            var4 = true;
            this.fileData.setLength((var1 + 1L) * (long)this.pageSize);
         } catch (IOException var12) {
         } catch (StandardException var13) {
         } finally {
            if (var4) {
               this.dataFactory.writeFinished();
            }

         }

      }
   }

   private void writeRAFHeader(Object var1, StorageRandomAccessFile var2, boolean var3, boolean var4) throws IOException, StandardException {
      byte[] var5;
      if (var3) {
         var5 = new byte[this.pageSize];
      } else {
         var5 = this.getEmbryonicPage(var2, 0L);
      }

      this.writeHeader(var1, var2, var3, var5);
      if (var4) {
         this.dataFactory.writeInProgress();

         try {
            if (!this.dataFactory.dataNotSyncedAtCheckpoint) {
               var2.sync();
            }
         } finally {
            this.dataFactory.writeFinished();
         }
      }

   }

   protected void flushAll() throws StandardException {
      this.pageCache.clean(this.identity);
      this.clean(false);
   }

   synchronized StorageFile getFileName(ContainerKey var1, boolean var2, boolean var3, boolean var4) throws StandardException {
      this.actionCode = 1;
      this.actionIdentity = var1;
      this.actionStub = var2;
      this.actionErrorOK = var3;
      this.actionTryAlternatePath = var4;

      StorageFile var5;
      try {
         var5 = (StorageFile)this.run();
      } finally {
         this.actionIdentity = null;
      }

      return var5;
   }

   protected StorageFile privGetFileName(ContainerKey var1, boolean var2, boolean var3, boolean var4) throws StandardException {
      StorageFile var5 = this.dataFactory.getContainerPath(var1, var2);
      if (!var5.exists() && var4) {
         var5 = this.dataFactory.getAlternateContainerPath(var1, var2);
      }

      if (!var5.exists()) {
         StorageFile var6 = var5.getParentDir();
         if (!var6.exists()) {
            synchronized(this.dataFactory) {
               if (!var6.exists()) {
                  if (!var6.mkdirs()) {
                     if (var3) {
                        return null;
                     }

                     throw StandardException.newException("XSDF3.S", new Object[]{var6});
                  }

                  try {
                     var6.limitAccessToOwner();
                  } catch (IOException var10) {
                     if (var3) {
                        return null;
                     }

                     throw StandardException.newException("XSDF3.S", var10, new Object[]{var6});
                  }
               }
            }
         }
      }

      return var5;
   }

   synchronized void createContainer(ContainerKey var1) throws StandardException {
      this.actionCode = 2;
      this.actionIdentity = var1;

      try {
         this.run();
      } finally {
         this.actionIdentity = null;
      }

   }

   private void copyFile(StorageFile var1, File var2) throws StandardException {
      boolean var3 = FileUtil.copyFile(this.dataFactory.getStorageFactory(), var1, var2);
      if (!var3) {
         throw StandardException.newException("XSRS5.S", new Object[]{var1, var2});
      }
   }

   private void removeFile(File var1) throws StandardException {
      boolean var2 = !var1.exists() || var1.delete();
      if (!var2) {
         throw StandardException.newException("XBM0R.D", new Object[]{var1});
      }
   }

   synchronized boolean removeFile(StorageFile var1) throws StandardException {
      this.actionCode = 3;
      this.actionFile = var1;

      boolean var2;
      try {
         var2 = this.run() != null;
      } finally {
         this.actionFile = null;
      }

      return var2;
   }

   private boolean privRemoveFile(StorageFile var1) throws StandardException {
      this.closeContainer();
      this.dataFactory.writeInProgress();

      boolean var2;
      try {
         if (!var1.exists()) {
            return true;
         }

         var2 = var1.delete();
      } finally {
         this.dataFactory.writeFinished();
      }

      return var2;
   }

   synchronized boolean openContainer(ContainerKey var1) throws StandardException {
      this.actionCode = 4;
      this.actionIdentity = var1;

      boolean var2;
      try {
         var2 = this.run() != null;
      } catch (Exception var6) {
         this.closeContainer();
         throw var6;
      } finally {
         this.actionIdentity = null;
      }

      return var2;
   }

   protected synchronized void reopenContainer(ContainerKey var1) throws StandardException {
      this.actionCode = 8;
      this.actionIdentity = var1;

      try {
         this.run();
      } catch (Exception var6) {
         this.closeContainer();
         throw var6;
      } finally {
         this.actionIdentity = null;
      }

   }

   private synchronized void stubbify(LogInstant var1) throws StandardException {
      this.setDroppedState(true);
      this.setCommittedDropState(true);
      this.actionIdentity = (ContainerKey)this.getIdentity();
      this.actionInstant = var1;
      this.actionCode = 5;

      try {
         this.run();
      } finally {
         this.actionIdentity = null;
         this.actionInstant = null;
      }

   }

   protected void backupContainer(BaseContainerHandle var1, String var2) throws StandardException {
      boolean var3 = false;
      File var4 = null;
      RandomAccessFile var5 = null;
      boolean var6 = false;
      BasePage var7 = null;

      while(!var3) {
         try {
            synchronized(this) {
               while(this.inRemove) {
                  try {
                     this.wait();
                  } catch (InterruptedException var34) {
                     InterruptStatus.setInterrupted();
                  }
               }

               if (this.getCommittedDropState()) {
                  var6 = true;
               }

               this.inBackup = true;
            }

            if (var6) {
               StorageFile var8 = this.getFileName((ContainerKey)this.getIdentity(), true, false, true);
               var4 = new File(var2, var8.getName());
               this.copyFile(var8, var4);
            } else {
               long var40 = this.getLastPageNumber(var1);
               if (var40 == -1L) {
                  return;
               }

               StorageFile var10 = this.getFileName((ContainerKey)this.getIdentity(), false, false, true);
               var4 = new File(var2, var10.getName());
               var5 = this.getRandomAccessFile(var4);
               byte[] var11 = null;
               if (this.dataFactory.databaseEncrypted()) {
                  var11 = new byte[this.pageSize];
               }

               for(long var12 = 0L; var12 <= var40; ++var12) {
                  var7 = this.getLatchedPage(var1, var12);
                  byte[] var14 = this.updatePageArray(var12, var7.getPageArray(), var11, false);
                  var5.write(var14, 0, this.pageSize);
                  var7.unlatch();
                  var7 = null;
                  synchronized(this) {
                     if (this.inRemove) {
                        break;
                     }
                  }
               }
            }

            if (!var6) {
               var5.getFD().sync();
               var5.close();
               var5 = null;
            }

            var3 = true;
         } catch (IOException var37) {
            throw StandardException.newException("XSDFH.S", var37, new Object[]{var4});
         } finally {
            synchronized(this) {
               this.inBackup = false;
               this.notifyAll();
            }

            if (var7 != null) {
               var7.unlatch();
               var7 = null;
            }

            if (!var3 && var4 != null) {
               if (var5 != null) {
                  try {
                     var5.close();
                     var5 = null;
                  } catch (IOException var32) {
                     throw StandardException.newException("XSDFH.S", var32, new Object[]{var4});
                  }
               }

               this.removeFile(var4);
            }

         }
      }

   }

   protected void encryptOrDecryptContainer(BaseContainerHandle var1, String var2, boolean var3) throws StandardException {
      BasePage var4 = null;
      StorageFile var5 = this.dataFactory.getStorageFactory().newStorageFile(var2);
      StorageRandomAccessFile var6 = null;

      try {
         long var7 = this.getLastPageNumber(var1);
         var6 = this.getRandomAccessFile(var5);
         byte[] var9 = null;
         if (var3) {
            var9 = new byte[this.pageSize];
         }

         for(long var10 = 0L; var10 <= var7; ++var10) {
            var4 = this.getLatchedPage(var1, var10);
            byte[] var12 = this.updatePageArray(var10, var4.getPageArray(), var9, true);
            var6.write(var12, 0, this.pageSize);
            var4.unlatch();
            var4 = null;
         }

         var6.sync();
         var6.close();
         var6 = null;
      } catch (IOException var20) {
         throw StandardException.newException("XSDG3.D", var20, new Object[]{this.getIdentity() != null ? this.getIdentity().toString() : "unknown", var3 ? "encrypt" : "decrypt", var2});
      } finally {
         if (var4 != null) {
            var4.unlatch();
            Object var23 = null;
         }

         if (var6 != null) {
            try {
               var6.close();
            } catch (IOException var19) {
               Object var25 = null;
               throw StandardException.newException("XSDG3.D", var19, new Object[]{this.getIdentity() != null ? this.getIdentity().toString() : "unknown", var3 ? "encrypt-close" : "decrypt-close", var2});
            }
         }

      }

   }

   private RandomAccessFile getRandomAccessFile(File var1) throws IOException {
      boolean var2 = var1.exists();
      RandomAccessFile var3 = new RandomAccessFile(var1, "rw");
      if (!var2) {
         FileUtil.limitAccessToOwner(var1);
      }

      return var3;
   }

   synchronized StorageRandomAccessFile getRandomAccessFile(StorageFile var1) throws StandardException {
      this.actionCode = 7;
      this.actionFile = var1;

      StorageRandomAccessFile var2;
      try {
         var2 = (StorageRandomAccessFile)this.run();
      } finally {
         this.actionFile = null;
      }

      return var2;
   }

   public Object run() throws StandardException {
      switch (this.actionCode) {
         case 1:
            return this.privGetFileName(this.actionIdentity, this.actionStub, this.actionErrorOK, this.actionTryAlternatePath);
         case 2:
            StorageFile var28 = this.privGetFileName(this.actionIdentity, false, false, false);
            if (var28.exists()) {
               throw StandardException.newException("XSDF0.S", new Object[]{var28});
            } else {
               try {
                  this.dataFactory.writeInProgress();

                  try {
                     this.fileData = var28.getRandomAccessFile("rw");
                     var28.limitAccessToOwner();
                  } finally {
                     this.dataFactory.writeFinished();
                  }

                  this.canUpdate = true;
                  this.writeRAFHeader(this.actionIdentity, this.fileData, true, this.actionIdentity.getSegmentId() != -1L);
                  return null;
               } catch (IOException var23) {
                  this.canUpdate = false;
                  boolean var34 = this.privRemoveFile(var28);
                  if (!var34) {
                     throw StandardException.newException("XSDF2.S", var23, new Object[]{var28, var23.toString()});
                  }

                  throw StandardException.newException("XSDF1.S", var23, new Object[]{var28});
               }
            }
         case 3:
            return this.privRemoveFile(this.actionFile) ? this : null;
         case 4:
            boolean var27 = false;
            StorageFile var30 = this.privGetFileName(this.actionIdentity, false, true, true);
            if (var30 == null) {
               return null;
            } else {
               if (!var30.exists()) {
                  var30 = this.privGetFileName(this.actionIdentity, true, true, true);
                  if (!var30.exists()) {
                     return null;
                  }

                  var27 = true;
               }

               this.canUpdate = false;
               if (!this.dataFactory.isReadOnly() && var30.canWrite()) {
                  this.canUpdate = true;
               }

               this.fileName = var30.toString();

               try {
                  this.fileData = var30.getRandomAccessFile(this.canUpdate ? "rw" : "r");
                  this.readHeader(this.getEmbryonicPage(this.fileData, 0L));
               } catch (IOException var24) {
                  if (var27) {
                     throw this.dataFactory.markCorrupt(StandardException.newException("XSDG3.D", var24, new Object[]{this.getIdentity() != null ? this.getIdentity().toString() : "unknown", "read", this.fileName}));
                  }

                  StorageFile var4 = this.privGetFileName(this.actionIdentity, true, true, true);
                  if (!var4.exists()) {
                     throw this.dataFactory.markCorrupt(StandardException.newException("XSDG3.D", var24, new Object[]{this.getIdentity() != null ? this.getIdentity().toString() : "unknown", "read", this.fileName}));
                  }

                  try {
                     this.privRemoveFile(var30);
                     this.fileData = var4.getRandomAccessFile(this.canUpdate ? "rw" : "r");
                     this.readHeader(this.getEmbryonicPage(this.fileData, 0L));
                  } catch (IOException var22) {
                     throw this.dataFactory.markCorrupt(StandardException.newException("XSDG3.D", var22, new Object[]{this.getIdentity() != null ? this.getIdentity().toString() : "unknown", "delete-stub", this.fileName}));
                  }
               }

               return this;
            }
         case 5:
            StorageFile var26 = this.privGetFileName(this.actionIdentity, false, false, true);
            StorageFile var29 = this.privGetFileName(this.actionIdentity, true, false, false);
            StorageRandomAccessFile var3 = null;

            try {
               if (!var29.exists()) {
                  var3 = var29.getRandomAccessFile("rw");
                  var29.limitAccessToOwner();
                  this.writeRAFHeader(this.actionIdentity, var3, true, true);
                  var3.close();
                  Object var33 = null;
               }

               this.dataFactory.flush(this.actionInstant);
               this.privRemoveFile(var26);
            } catch (IOException var18) {
               try {
                  if (var3 != null) {
                     var3.close();
                     var29.delete();
                     Object var31 = null;
                  }

                  if (this.fileData != null) {
                     this.fileData.close();
                     this.fileData = null;
                  }
               } catch (IOException var17) {
                  throw StandardException.newException("XSDF4.S", var17, new Object[]{var26, var18.toString()});
               }
            }

            this.dataFactory.stubFileToRemoveAfterCheckPoint(var29, this.actionInstant, this.getIdentity());
            return null;
         case 6:
         default:
            return null;
         case 7:
            try {
               boolean var25 = this.actionFile.exists();
               StorageRandomAccessFile var2 = this.actionFile.getRandomAccessFile("rw");
               if (!var25) {
                  this.actionFile.limitAccessToOwner();
               }

               return var2;
            } catch (IOException var16) {
               throw StandardException.newException("XSDF1.S", var16, new Object[]{this.actionFile.getPath()});
            }
         case 8:
            StorageFile var1 = this.privGetFileName(this.actionIdentity, false, true, true);
            synchronized(this) {
               try {
                  this.fileData = var1.getRandomAccessFile(this.canUpdate ? "rw" : "r");
               } catch (FileNotFoundException var20) {
                  throw this.dataFactory.markCorrupt(StandardException.newException("XSDG3.D", var20, new Object[]{this.getIdentity() != null ? this.getIdentity().toString() : "unknown", "read", this.fileName}));
               }

               return this;
            }
      }
   }
}
