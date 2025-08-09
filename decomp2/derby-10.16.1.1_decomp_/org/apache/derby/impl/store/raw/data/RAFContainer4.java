package org.apache.derby.impl.store.raw.data;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.util.InterruptDetectedException;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.shared.common.error.StandardException;

class RAFContainer4 extends RAFContainer {
   private FileChannel ourChannel = null;
   private final Object channelCleanupMonitor = new Object();
   private volatile int threadsInPageIO = 0;
   private volatile boolean restoreChannelInProgress = false;
   private boolean giveUpIO = false;
   private final Object giveUpIOm = new Object();
   private int iosInProgress = 0;
   private ContainerKey currentIdentity;

   public RAFContainer4(BaseDataFileFactory var1) {
      super(var1);
   }

   private FileChannel getChannel(StorageRandomAccessFile var1) {
      return var1 instanceof RandomAccessFile ? ((RandomAccessFile)var1).getChannel() : null;
   }

   private FileChannel getChannel() {
      if (this.ourChannel == null) {
         this.ourChannel = this.getChannel(this.fileData);
      }

      return this.ourChannel;
   }

   synchronized boolean openContainer(ContainerKey var1) throws StandardException {
      this.currentIdentity = var1;
      return super.openContainer(var1);
   }

   synchronized void createContainer(ContainerKey var1) throws StandardException {
      this.currentIdentity = var1;
      super.createContainer(var1);
   }

   private void reopen() throws StandardException {
      this.ourChannel = null;
      this.reopenContainer(this.currentIdentity);
   }

   synchronized void closeContainer() {
      if (this.ourChannel != null) {
         try {
            this.ourChannel.close();
         } catch (IOException var5) {
         } finally {
            this.ourChannel = null;
         }
      }

      super.closeContainer();
   }

   protected void readPage(long var1, byte[] var3) throws IOException, StandardException {
      this.readPage(var1, var3, -1L);
   }

   private void readPage(long var1, byte[] var3, long var4) throws IOException, StandardException {
      boolean var6 = Thread.holdsLock(this);
      boolean var7 = Thread.holdsLock(this.allocCache);
      boolean var8 = var6 || var7;
      if (!var8) {
         synchronized(this.channelCleanupMonitor) {
            int var10 = 120;

            while(this.restoreChannelInProgress) {
               if (var10-- == 0) {
                  throw StandardException.newException("XSDG9.D", new Object[0]);
               }

               try {
                  this.channelCleanupMonitor.wait(500L);
               } catch (InterruptedException var28) {
                  InterruptStatus.setInterrupted();
               }
            }

            ++this.threadsInPageIO;
         }
      }

      boolean var9 = false;
      int var31 = 120;

      try {
         while(!var9) {
            try {
               if (var1 == 0L) {
                  synchronized(this) {
                     this.readPage0(var1, var3, var4);
                  }
               } else {
                  this.readPage0(var1, var3, var4);
               }

               var9 = true;
            } catch (ClosedChannelException var27) {
               this.handleClosedChannel(var27, var8, var31--);
            }
         }
      } finally {
         if (!var8) {
            synchronized(this.channelCleanupMonitor) {
               --this.threadsInPageIO;
            }
         }

      }

   }

   private void readPage0(long var1, byte[] var3, long var4) throws IOException, StandardException {
      FileChannel var6;
      synchronized(this) {
         var6 = this.getChannel();
      }

      if (var6 != null) {
         long var7 = var1 * (long)this.pageSize;
         ByteBuffer var9 = ByteBuffer.wrap(var3);

         try {
            if (var4 == -1L) {
               this.readFull(var9, var6, var7);
            } else {
               this.readFull(var9, var6, var4);
            }
         } finally {
            ;
         }

         if (this.dataFactory.databaseEncrypted() && var1 != 0L && var1 != -1L) {
            this.decryptPage(var3, this.pageSize);
         }
      } else {
         super.readPage(var1, var3);
      }

   }

   protected void writePage(long var1, byte[] var3, boolean var4) throws IOException, StandardException {
      boolean var5 = Thread.holdsLock(this.allocCache);
      if (!var5) {
         synchronized(this.channelCleanupMonitor) {
            int var7 = 120;

            while(this.restoreChannelInProgress) {
               if (var7-- == 0) {
                  throw StandardException.newException("XSDG9.D", new Object[0]);
               }

               try {
                  this.channelCleanupMonitor.wait(500L);
               } catch (InterruptedException var25) {
                  InterruptStatus.setInterrupted();
               }
            }

            ++this.threadsInPageIO;
         }
      }

      boolean var6 = false;
      int var28 = 120;

      try {
         while(!var6) {
            try {
               if (var1 == 0L) {
                  synchronized(this) {
                     this.writePage0(var1, var3, var4);
                  }
               } else {
                  this.writePage0(var1, var3, var4);
               }

               var6 = true;
            } catch (ClosedChannelException var24) {
               this.handleClosedChannel(var24, var5, var28--);
            }
         }
      } finally {
         if (!var5) {
            synchronized(this.channelCleanupMonitor) {
               --this.threadsInPageIO;
            }
         }

      }

   }

   private void handleClosedChannel(ClosedChannelException var1, boolean var2, int var3) throws StandardException {
      if (var1 instanceof AsynchronousCloseException) {
         if (Thread.currentThread().isInterrupted() && this.recoverContainerAfterInterrupt(var1.toString(), var2)) {
            return;
         }

         this.awaitRestoreChannel(var1, var2);
      } else {
         InterruptStatus.noteAndClearInterrupt("ClosedChannelException", this.threadsInPageIO, this.hashCode());
         this.awaitRestoreChannel(var1, var2);
         if (var3 == 0) {
            throw StandardException.newException("XSDG9.D", new Object[0]);
         }
      }

   }

   private void awaitRestoreChannel(Exception var1, boolean var2) throws StandardException {
      if (var2) {
         synchronized(this.giveUpIOm) {
            if (this.giveUpIO) {
               throw StandardException.newException("XSDG9.D", new Object[0]);
            }
         }

         throw new InterruptDetectedException();
      } else {
         synchronized(this.channelCleanupMonitor) {
            --this.threadsInPageIO;
         }

         int var3 = -1;
         synchronized(this.channelCleanupMonitor) {
            while(this.restoreChannelInProgress) {
               ++var3;
               if (var3 > 120) {
                  throw StandardException.newException("XSDG9.D", var1, new Object[0]);
               }

               try {
                  this.channelCleanupMonitor.wait(500L);
               } catch (InterruptedException var9) {
                  InterruptStatus.setInterrupted();
               }
            }

            ++this.threadsInPageIO;
         }

         synchronized(this.giveUpIOm) {
            if (this.giveUpIO) {
               --this.threadsInPageIO;
               throw StandardException.newException("XSDG9.D", new Object[0]);
            }
         }

         if (var3 == -1) {
            try {
               Thread.sleep(500L);
            } catch (InterruptedException var8) {
               InterruptStatus.setInterrupted();
            }
         }

      }
   }

   private boolean recoverContainerAfterInterrupt(String var1, boolean var2) throws StandardException {
      if (var2 && this.restoreChannelInProgress) {
         InterruptStatus.noteAndClearInterrupt(var1, this.threadsInPageIO, this.hashCode());
         return false;
      } else {
         synchronized(this.channelCleanupMonitor) {
            if (this.restoreChannelInProgress) {
               InterruptStatus.noteAndClearInterrupt(var1, this.threadsInPageIO, this.hashCode());
               return false;
            }

            if (!var2) {
               --this.threadsInPageIO;
            }

            this.restoreChannelInProgress = true;
         }

         int var3 = 120;

         while(true) {
            label310: {
               synchronized(this.channelCleanupMonitor) {
                  if (this.threadsInPageIO != 0) {
                     if (var3-- == 0) {
                        this.restoreChannelInProgress = false;
                        this.channelCleanupMonitor.notifyAll();
                        throw StandardException.newException("XSDG9.D", new Object[0]);
                     }
                     break label310;
                  }
               }

               synchronized(this.channelCleanupMonitor) {
                  try {
                     InterruptStatus.noteAndClearInterrupt(var1, this.threadsInPageIO, this.hashCode());
                     synchronized(this) {
                        ;
                     }

                     synchronized(this) {
                        try {
                           this.reopen();
                        } catch (Exception var31) {
                           Exception var6 = var31;
                           synchronized(this.giveUpIOm){}

                           try {
                              this.giveUpIO = true;
                              throw StandardException.newException("XSDG9.D", var6, new Object[0]);
                           } finally {
                              ;
                           }
                        }
                     }

                     if (!var2) {
                        ++this.threadsInPageIO;
                     }
                  } finally {
                     this.restoreChannelInProgress = false;
                     this.channelCleanupMonitor.notifyAll();
                  }

                  return true;
               }
            }

            try {
               Thread.sleep(500L);
            } catch (InterruptedException var34) {
               InterruptStatus.setInterrupted();
            }
         }
      }
   }

   private void writePage0(long var1, byte[] var3, boolean var4) throws IOException, StandardException {
      FileChannel var5;
      synchronized(this) {
         if (this.getCommittedDropState()) {
            return;
         }

         var5 = this.getChannel();
      }

      if (var5 == null) {
         super.writePage(var1, var3, var4);
      } else {
         long var6 = var1 * (long)this.pageSize;
         byte[] var8 = null;
         if (this.dataFactory.databaseEncrypted()) {
            var8 = new byte[this.pageSize];
         }

         byte[] var9 = this.updatePageArray(var1, var3, var8, false);
         ByteBuffer var10 = ByteBuffer.wrap(var9);
         this.dataFactory.writeInProgress();

         label209: {
            try {
               this.writeFull(var10, var5, var6);
               break label209;
            } catch (ClosedChannelException var32) {
               ClosedChannelException var11 = var32;
               synchronized(this) {
                  if (!this.getCommittedDropState()) {
                     throw var11;
                  }
               }
            } finally {
               this.dataFactory.writeFinished();
            }

            return;
         }

         if (var4) {
            this.dataFactory.writeInProgress();

            try {
               if (!this.dataFactory.dataNotSyncedAtAllocation) {
                  var5.force(false);
               }
            } finally {
               this.dataFactory.writeFinished();
            }
         } else {
            synchronized(this) {
               this.needsSync = true;
            }
         }
      }

   }

   void writeAtOffset(StorageRandomAccessFile var1, byte[] var2, long var3) throws IOException, StandardException {
      FileChannel var5 = this.getChannel(var1);
      if (var5 == null) {
         super.writeAtOffset(var1, var2, var3);
      } else {
         this.ourChannel = var5;
         boolean var6 = false;

         while(!var6) {
            synchronized(this) {
               var5 = this.getChannel();
            }

            try {
               this.writeFull(ByteBuffer.wrap(var2), var5, var3);
               var6 = true;
            } catch (ClosedChannelException var9) {
               this.handleClosedChannel(var9, true, -1);
            }
         }

      }
   }

   byte[] getEmbryonicPage(StorageRandomAccessFile var1, long var2) throws IOException, StandardException {
      FileChannel var4 = this.getChannel(var1);
      if (var4 != null) {
         byte[] var5 = new byte[204];
         this.readPage(-1L, var5, var2);
         return var5;
      } else {
         return super.getEmbryonicPage(var1, var2);
      }
   }

   private void readFull(ByteBuffer var1, FileChannel var2, long var3) throws IOException, StandardException {
      while(true) {
         if (var1.remaining() > 0) {
            if (var2.read(var1, var3 + (long)var1.position()) == -1) {
               throw new EOFException("Reached end of file while attempting to read a whole page.");
            }

            if (!Thread.currentThread().isInterrupted() || var2.isOpen()) {
               continue;
            }

            throw new ClosedByInterruptException();
         }

         return;
      }
   }

   private void writeFull(ByteBuffer var1, FileChannel var2, long var3) throws IOException {
      while(true) {
         if (var1.remaining() > 0) {
            var2.write(var1, var3 + (long)var1.position());
            if (!Thread.currentThread().isInterrupted() || var2.isOpen()) {
               continue;
            }

            throw new ClosedByInterruptException();
         }

         return;
      }
   }

   private static void debugTrace(String var0) {
   }
}
