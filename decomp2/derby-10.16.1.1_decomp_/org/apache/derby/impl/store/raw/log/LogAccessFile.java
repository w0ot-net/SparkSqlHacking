package org.apache.derby.impl.store.raw.log;

import java.io.IOException;
import java.io.SyncFailedException;
import java.util.LinkedList;
import org.apache.derby.iapi.services.io.ArrayOutputStream;
import org.apache.derby.iapi.services.io.FormatIdOutputStream;
import org.apache.derby.iapi.store.raw.xact.TransactionId;
import org.apache.derby.iapi.store.replication.master.MasterFactory;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.shared.common.error.StandardException;

public class LogAccessFile {
   private static final int LOG_RECORD_FIXED_OVERHEAD_SIZE = 16;
   private static final int LOG_RECORD_HEADER_SIZE = 12;
   private static final int LOG_RECORD_TRAILER_SIZE = 4;
   private static final int LOG_NUMBER_LOG_BUFFERS = 3;
   private LinkedList freeBuffers;
   private LinkedList dirtyBuffers;
   private LogAccessFileBuffer currentBuffer;
   private boolean flushInProgress = false;
   private final StorageRandomAccessFile log;
   private final Object logFileSemaphore;
   static int mon_numWritesToLog;
   static int mon_numBytesToLog;
   MasterFactory masterFac;
   boolean inReplicationMasterMode = false;
   boolean inReplicationSlaveMode = false;
   private ArrayOutputStream logOutputBuffer;
   private FormatIdOutputStream logicalOut;
   private long checksumInstant = -1L;
   private int checksumLength;
   private int checksumLogRecordSize;
   private boolean writeChecksum;
   private ChecksumOperation checksumLogOperation;
   private LogRecord checksumLogRecord;
   private LogToFile logFactory;
   private boolean databaseEncrypted = false;

   public LogAccessFile(LogToFile var1, StorageRandomAccessFile var2, int var3) {
      var1.checkForReplication(this);
      this.log = var2;
      this.logFileSemaphore = var2;
      this.logFactory = var1;
      this.freeBuffers = new LinkedList();
      this.dirtyBuffers = new LinkedList();

      for(int var4 = 0; var4 < 3; ++var4) {
         LogAccessFileBuffer var5 = new LogAccessFileBuffer(var3);
         this.freeBuffers.addLast(var5);
      }

      this.currentBuffer = (LogAccessFileBuffer)this.freeBuffers.removeFirst();
      this.writeChecksum = var1.checkVersion(10, 1);
      if (this.inReplicationSlaveMode) {
         this.writeChecksum = false;
      }

      if (this.writeChecksum) {
         this.checksumLogOperation = new ChecksumOperation();
         this.checksumLogOperation.init();
         this.checksumLogRecord = new LogRecord();
         this.checksumLogRecord.setValue((TransactionId)null, this.checksumLogOperation);
         LogRecord var10001 = this.checksumLogRecord;
         this.checksumLength = LogRecord.getStoredSize(this.checksumLogOperation.group(), (TransactionId)null) + this.checksumLogOperation.getStoredSize();
         if (var1.databaseEncrypted()) {
            this.checksumLength = var1.getEncryptedDataLength(this.checksumLength);
            this.databaseEncrypted = true;
         }

         this.checksumLogRecordSize = this.checksumLength + 16;
         this.logOutputBuffer = new ArrayOutputStream();
         this.logicalOut = new FormatIdOutputStream(this.logOutputBuffer);
      } else {
         this.checksumLogRecordSize = 0;
      }

      this.currentBuffer.init(this.checksumLogRecordSize);
   }

   public void writeLogRecord(int var1, long var2, byte[] var4, int var5, byte[] var6, int var7, int var8) throws StandardException, IOException {
      int var9 = var1 + 16;
      if (var9 <= this.currentBuffer.bytes_free) {
         int var10 = this.appendLogRecordToBuffer(this.currentBuffer.buffer, this.currentBuffer.position, var1, var2, var4, var5, var6, var7, var8);
         this.currentBuffer.position = var10;
         LogAccessFileBuffer var10000 = this.currentBuffer;
         var10000.bytes_free -= var9;
         this.currentBuffer.greatest_instant = var2;
      } else {
         int var12 = this.checksumLogRecordSize + var9;
         byte[] var11 = new byte[var12];
         this.appendLogRecordToBuffer(var11, this.checksumLogRecordSize, var1, var2, var4, var5, var6, var7, var8);
         if (this.writeChecksum) {
            this.checksumLogOperation.reset();
            this.checksumLogOperation.update(var11, this.checksumLogRecordSize, var9);
            this.writeChecksumLogRecord(var11);
         }

         this.flushLogAccessFile();
         this.writeToLog(var11, 0, var12, var2);
      }

   }

   private int appendLogRecordToBuffer(byte[] var1, int var2, int var3, long var4, byte[] var6, int var7, byte[] var8, int var9, int var10) {
      var2 = this.writeInt(var3, var1, var2);
      var2 = this.writeLong(var4, var1, var2);
      int var11 = var3 - var10;
      System.arraycopy(var6, var7, var1, var2, var11);
      var2 += var11;
      if (var10 != 0) {
         System.arraycopy(var8, var9, var1, var2, var10);
         var2 += var10;
      }

      var2 = this.writeInt(var3, var1, var2);
      return var2;
   }

   private final int writeInt(int var1, byte[] var2, int var3) {
      var2[var3++] = (byte)(var1 >>> 24 & 255);
      var2[var3++] = (byte)(var1 >>> 16 & 255);
      var2[var3++] = (byte)(var1 >>> 8 & 255);
      var2[var3++] = (byte)(var1 & 255);
      return var3;
   }

   private final int writeLong(long var1, byte[] var3, int var4) {
      var3[var4++] = (byte)((int)(var1 >>> 56) & 255);
      var3[var4++] = (byte)((int)(var1 >>> 48) & 255);
      var3[var4++] = (byte)((int)(var1 >>> 40) & 255);
      var3[var4++] = (byte)((int)(var1 >>> 32) & 255);
      var3[var4++] = (byte)((int)(var1 >>> 24) & 255);
      var3[var4++] = (byte)((int)(var1 >>> 16) & 255);
      var3[var4++] = (byte)((int)(var1 >>> 8) & 255);
      var3[var4++] = (byte)((int)var1 & 255);
      return var4;
   }

   public void writeInt(int var1) {
      this.currentBuffer.position = this.writeInt(var1, this.currentBuffer.buffer, this.currentBuffer.position);
      LogAccessFileBuffer var10000 = this.currentBuffer;
      var10000.bytes_free -= 4;
   }

   public void writeLong(long var1) {
      this.currentBuffer.position = this.writeLong(var1, this.currentBuffer.buffer, this.currentBuffer.position);
      LogAccessFileBuffer var10000 = this.currentBuffer;
      var10000.bytes_free -= 8;
   }

   public void write(int var1) {
      this.currentBuffer.buffer[this.currentBuffer.position++] = (byte)var1;
      --this.currentBuffer.bytes_free;
   }

   public void write(byte[] var1, int var2, int var3) {
      System.arraycopy(var1, var2, this.currentBuffer.buffer, this.currentBuffer.position, var3);
      LogAccessFileBuffer var10000 = this.currentBuffer;
      var10000.bytes_free -= var3;
      var10000 = this.currentBuffer;
      var10000.position += var3;
   }

   protected void flushDirtyBuffers() throws IOException {
      LogAccessFileBuffer var1 = null;
      int var3 = 0;

      try {
         int var2;
         synchronized(this) {
            while(this.flushInProgress) {
               try {
                  this.wait();
               } catch (InterruptedException var20) {
                  InterruptStatus.setInterrupted();
               }
            }

            var2 = this.dirtyBuffers.size();
            if (var2 > 0) {
               var1 = (LogAccessFileBuffer)this.dirtyBuffers.removeFirst();
            }

            this.flushInProgress = true;
         }

         while(var3 < var2) {
            if (var1.position != 0) {
               this.writeToLog(var1.buffer, 0, var1.position, var1.greatest_instant);
            }

            ++var3;
            synchronized(this) {
               this.freeBuffers.addLast(var1);
               if (var3 < var2) {
                  var1 = (LogAccessFileBuffer)this.dirtyBuffers.removeFirst();
               } else {
                  int var5 = this.dirtyBuffers.size();
                  if (var5 > 0 && var3 <= 3) {
                     var2 += var5;
                     var1 = (LogAccessFileBuffer)this.dirtyBuffers.removeFirst();
                  }
               }
            }
         }
      } finally {
         synchronized(this) {
            this.flushInProgress = false;
            this.notifyAll();
         }
      }

   }

   public void flushLogAccessFile() throws IOException, StandardException {
      this.switchLogBuffer();
      this.flushDirtyBuffers();
   }

   public void switchLogBuffer() throws IOException, StandardException {
      synchronized(this) {
         if (this.currentBuffer.position != this.checksumLogRecordSize) {
            if (this.writeChecksum) {
               this.checksumLogOperation.reset();
               this.checksumLogOperation.update(this.currentBuffer.buffer, this.checksumLogRecordSize, this.currentBuffer.position - this.checksumLogRecordSize);
               this.writeChecksumLogRecord(this.currentBuffer.buffer);
            }

            this.dirtyBuffers.addLast(this.currentBuffer);
            if (this.freeBuffers.size() == 0) {
               this.flushDirtyBuffers();
            }

            this.currentBuffer = (LogAccessFileBuffer)this.freeBuffers.removeFirst();
            this.currentBuffer.init(this.checksumLogRecordSize);
         }
      }
   }

   public void syncLogAccessFile() throws IOException, StandardException {
      int var1 = 0;

      while(true) {
         try {
            synchronized(this) {
               this.log.sync();
               return;
            }
         } catch (SyncFailedException var6) {
            ++var1;

            try {
               Thread.sleep(200L);
            } catch (InterruptedException var5) {
               InterruptStatus.setInterrupted();
            }

            if (var1 > 20) {
               throw StandardException.newException("XSLA4.D", var6, new Object[0]);
            }
         }
      }
   }

   public void corrupt() throws IOException {
      synchronized(this.logFileSemaphore) {
         if (this.log != null) {
            this.log.close();
         }

      }
   }

   public void close() throws IOException, StandardException {
      this.flushLogAccessFile();
      synchronized(this.logFileSemaphore) {
         if (this.log != null) {
            this.log.close();
         }

      }
   }

   protected void setReplicationMasterRole(MasterFactory var1) {
      this.masterFac = var1;
      this.inReplicationMasterMode = true;
   }

   protected void stopReplicationMasterRole() {
      this.inReplicationMasterMode = false;
      this.masterFac = null;
   }

   protected void setReplicationSlaveRole() {
      this.inReplicationSlaveMode = true;
   }

   private void writeToLog(byte[] var1, int var2, int var3, long var4) throws IOException {
      synchronized(this.logFileSemaphore) {
         if (this.log != null) {
            int var7 = 0;

            while(true) {
               try {
                  this.log.write(var1, var2, var3);
                  if (this.inReplicationMasterMode) {
                     this.masterFac.appendLog(var4, var1, var2, var3);
                  }
                  break;
               } catch (IOException var10) {
                  if (var7 >= 5) {
                     throw var10;
                  }

                  ++var7;
               }
            }
         }

      }
   }

   protected long reserveSpaceForChecksum(int var1, long var2, long var4) throws StandardException, IOException {
      int var6 = var1 + 16;
      boolean var7 = false;
      if (this.currentBuffer.position == this.checksumLogRecordSize) {
         var7 = this.writeChecksum;
      } else if (var6 > this.currentBuffer.bytes_free) {
         this.switchLogBuffer();
         var7 = this.writeChecksum;
      }

      if (var7) {
         this.checksumInstant = LogCounter.makeLogInstantAsLong(var2, var4);
         return (long)this.checksumLogRecordSize;
      } else {
         return 0L;
      }
   }

   private void writeChecksumLogRecord(byte[] var1) throws IOException, StandardException {
      int var2 = 0;
      var2 = this.writeInt(this.checksumLength, var1, var2);
      var2 = this.writeLong(this.checksumInstant, var1, var2);
      this.logOutputBuffer.setData(var1);
      this.logOutputBuffer.setPosition(var2);
      this.logicalOut.writeObject(this.checksumLogRecord);
      if (this.databaseEncrypted) {
         this.logFactory.encrypt(var1, 12, this.checksumLength, var1, 12);
      }

      var2 = 12 + this.checksumLength;
      this.writeInt(this.checksumLength, var1, var2);
   }

   public int getChecksumLogRecordSize() {
      return this.checksumLogRecordSize;
   }

   protected void writeEndMarker(int var1) throws IOException, StandardException {
      this.flushLogAccessFile();
      byte[] var2 = this.currentBuffer.buffer;
      int var3 = 0;
      var3 = this.writeInt(var1, var2, var3);
      this.writeToLog(var2, 0, var3, -1L);
   }
}
