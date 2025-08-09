package org.apache.derby.impl.store.raw.log;

import java.io.IOException;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.TransactionId;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class FlushedScan implements StreamLogScan {
   private StorageRandomAccessFile scan;
   LogToFile logFactory;
   boolean open;
   long currentLogFileNumber;
   long currentLogFileFirstUnflushedPosition;
   long currentInstant;
   long firstUnflushed = -1L;
   long firstUnflushedFileNumber;
   long firstUnflushedFilePosition;
   static final int LOG_REC_LEN_BYTE_LENGTH = 4;
   int nextRecordLength;
   boolean readNextRecordLength;

   public FlushedScan(LogToFile var1, long var2) throws StandardException {
      try {
         this.currentLogFileNumber = LogCounter.getLogFileNumber(var2);
         this.logFactory = var1;
         this.scan = var1.getLogFileAtPosition(var2);
         this.setFirstUnflushed();
         this.open = true;
         this.currentInstant = 0L;
      } catch (IOException var5) {
         throw var1.markCorrupt(StandardException.newException("XSLA2.D", var5, new Object[0]));
      }
   }

   public LogRecord getNextRecord(ArrayInputStream var1, TransactionId var2, int var3) throws StandardException {
      try {
         int var5 = LogRecord.formatOverhead() + LogRecord.maxGroupStoredSize();
         if (var2 != null) {
            var5 += LogRecord.maxTransactionIdStoredSize(var2);
         }

         while(this.open && this.positionToNextRecord()) {
            LogRecord var7 = null;
            boolean var4 = true;
            int var6 = -1;
            this.currentInstant = this.scan.readLong();
            byte[] var9 = var1.getData();
            if (var9.length < this.nextRecordLength) {
               var9 = new byte[this.nextRecordLength];
               var1.setData(var9);
            }

            if (this.logFactory.databaseEncrypted()) {
               this.scan.readFully(var9, 0, this.nextRecordLength);
               int var10 = this.logFactory.decrypt(var9, 0, this.nextRecordLength, var9, 0);
               var1.setLimit(0, var10);
            } else if (var3 == 0 && var2 == null) {
               this.scan.readFully(var9, 0, this.nextRecordLength);
               var1.setLimit(0, this.nextRecordLength);
            } else {
               var6 = this.nextRecordLength > var5 ? var5 : this.nextRecordLength;
               this.scan.readFully(var9, 0, var6);
               var1.setLimit(0, var6);
            }

            var7 = (LogRecord)var1.readObject();
            if (var3 != 0 || var2 != null) {
               if (var3 != 0 && (var3 & var7.group()) == 0) {
                  var4 = false;
               }

               if (var4 && var2 != null) {
                  TransactionId var15 = var7.getTransactionId();
                  if (!var15.equals(var2)) {
                     var4 = false;
                  }
               }

               if (var4 && !this.logFactory.databaseEncrypted() && var6 < this.nextRecordLength) {
                  int var16 = var1.getPosition();
                  this.scan.readFully(var9, var6, this.nextRecordLength - var6);
                  var1.setLimit(0, this.nextRecordLength);
                  var1.setPosition(var16);
               }
            }

            if (!var4 && !this.logFactory.databaseEncrypted()) {
               long var17 = LogCounter.getLogFilePosition(this.currentInstant) + (long)this.nextRecordLength + 16L;
               this.scan.seek(var17);
            } else {
               int var8 = this.scan.readInt();
            }

            if (var4) {
               return var7;
            }
         }

         return null;
      } catch (ClassNotFoundException var12) {
         throw this.logFactory.markCorrupt(StandardException.newException("XSLA3.D", var12, new Object[0]));
      } catch (IOException var13) {
         throw this.logFactory.markCorrupt(StandardException.newException("XSLA2.D", var13, new Object[0]));
      }
   }

   public void resetPosition(LogInstant var1) throws IOException {
   }

   public long getLogRecordEnd() {
      return 0L;
   }

   public boolean isLogEndFuzzy() {
      return false;
   }

   public long getInstant() {
      return this.currentInstant;
   }

   public LogInstant getLogInstant() {
      return this.currentInstant == 0L ? null : new LogCounter(this.currentInstant);
   }

   public void close() {
      if (this.scan != null) {
         try {
            this.scan.close();
         } catch (IOException var2) {
         }

         this.scan = null;
      }

      this.currentInstant = 0L;
      this.open = false;
   }

   private void setFirstUnflushed() throws StandardException, IOException {
      LogInstant var1 = this.logFactory.getFirstUnflushedInstant();
      this.firstUnflushed = ((LogCounter)var1).getValueAsLong();
      this.firstUnflushedFileNumber = LogCounter.getLogFileNumber(this.firstUnflushed);
      this.firstUnflushedFilePosition = LogCounter.getLogFilePosition(this.firstUnflushed);
      this.setCurrentLogFileFirstUnflushedPosition();
   }

   private void setCurrentLogFileFirstUnflushedPosition() throws IOException {
      if (this.currentLogFileNumber == this.firstUnflushedFileNumber) {
         this.currentLogFileFirstUnflushedPosition = this.firstUnflushedFilePosition;
      } else {
         if (this.currentLogFileNumber >= this.firstUnflushedFileNumber) {
            throw new IOException(MessageService.getTextMessage("L014", new Object[0]));
         }

         this.currentLogFileFirstUnflushedPosition = this.scan.length();
      }

   }

   private void switchLogFile() throws StandardException {
      try {
         this.readNextRecordLength = false;
         this.scan.close();
         this.scan = null;
         this.scan = this.logFactory.getLogFileAtBeginning(++this.currentLogFileNumber);
         this.setCurrentLogFileFirstUnflushedPosition();
      } catch (IOException var2) {
         throw this.logFactory.markCorrupt(StandardException.newException("XSLA2.D", var2, new Object[0]));
      }
   }

   private boolean currentLogFileHasUnflushedRecord() throws IOException {
      long var1 = this.scan.getFilePointer();
      if (!this.readNextRecordLength) {
         if (var1 + 4L > this.currentLogFileFirstUnflushedPosition) {
            return false;
         }

         this.nextRecordLength = this.scan.readInt();
         var1 += 4L;
         this.readNextRecordLength = true;
      }

      if (this.nextRecordLength == 0) {
         return false;
      } else {
         int var3 = this.nextRecordLength + 4;
         if (var1 + (long)var3 > this.currentLogFileFirstUnflushedPosition) {
            return false;
         } else {
            this.readNextRecordLength = false;
            return true;
         }
      }
   }

   private boolean positionToNextRecord() throws StandardException, IOException {
      if (this.currentLogFileHasUnflushedRecord()) {
         return true;
      } else {
         this.setFirstUnflushed();
         if (this.currentLogFileHasUnflushedRecord()) {
            return true;
         } else {
            while(this.currentLogFileNumber < this.firstUnflushedFileNumber) {
               this.switchLogFile();
               if (this.currentLogFileHasUnflushedRecord()) {
                  return true;
               }
            }

            this.currentInstant = 0L;
            return false;
         }
      }
   }
}
