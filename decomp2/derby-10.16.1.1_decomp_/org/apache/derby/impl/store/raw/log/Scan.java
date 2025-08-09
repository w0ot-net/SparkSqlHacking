package org.apache.derby.impl.store.raw.log;

import java.io.IOException;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.TransactionId;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.shared.common.error.StandardException;

public class Scan implements StreamLogScan {
   public static final byte FORWARD = 1;
   public static final byte BACKWARD = 2;
   public static final byte BACKWARD_FROM_LOG_END = 4;
   private StorageRandomAccessFile scan;
   private LogToFile logFactory;
   private long currentLogFileNumber;
   private long currentLogFileLength;
   private long knownGoodLogEnd;
   private long currentInstant;
   private long stopAt;
   private byte scanDirection;
   private boolean fuzzyLogEnd = false;

   public Scan(LogToFile var1, long var2, LogInstant var4, byte var5) throws IOException, StandardException {
      this.logFactory = var1;
      this.currentLogFileNumber = LogCounter.getLogFileNumber(var2);
      this.currentLogFileLength = -1L;
      this.knownGoodLogEnd = 0L;
      this.currentInstant = 0L;
      if (var4 != null) {
         this.stopAt = ((LogCounter)var4).getValueAsLong();
      } else {
         this.stopAt = 0L;
      }

      switch (var5) {
         case 1:
            this.scan = var1.getLogFileAtPosition(var2);
            this.scanDirection = 1;
            this.currentLogFileLength = this.scan.length();
            break;
         case 2:
            this.scan = var1.getLogFileAtPosition(var2);
            int var6 = this.scan.readInt();
            this.scan.seek(this.scan.getFilePointer() + (long)var6 + 16L - 4L);
            this.scanDirection = 2;
         case 3:
         default:
            break;
         case 4:
            this.scan = var1.getLogFileAtPosition(var2);
            this.scanDirection = 2;
      }

   }

   public LogRecord getNextRecord(ArrayInputStream var1, TransactionId var2, int var3) throws StandardException {
      if (this.scan == null) {
         return null;
      } else {
         LogRecord var4 = null;

         LogRecord var5;
         try {
            if (this.scanDirection == 2) {
               var4 = this.getNextRecordBackward(var1, var2, var3);
            } else if (this.scanDirection == 1) {
               var4 = this.getNextRecordForward(var1, var2, var3);
            }

            var5 = var4;
         } catch (IOException var10) {
            throw this.logFactory.markCorrupt(StandardException.newException("XSLA3.D", var10, new Object[0]));
         } catch (ClassNotFoundException var11) {
            throw this.logFactory.markCorrupt(StandardException.newException("XSLA3.D", var11, new Object[0]));
         } finally {
            if (var4 == null) {
               this.close();
            }

         }

         return var5;
      }
   }

   private LogRecord getNextRecordBackward(ArrayInputStream var1, TransactionId var2, int var3) throws StandardException, IOException, ClassNotFoundException {
      int var5 = LogRecord.formatOverhead() + LogRecord.maxGroupStoredSize();
      if (var2 != null) {
         var5 += LogRecord.maxTransactionIdStoredSize(var2);
      }

      long var8 = this.scan.getFilePointer();

      boolean var4;
      LogRecord var7;
      do {
         var4 = true;
         var7 = null;
         int var6 = -1;
         if (var8 == 24L) {
            if (this.stopAt != 0L && LogCounter.getLogFileNumber(this.stopAt) == this.currentLogFileNumber) {
               return null;
            }

            this.scan.seek(16L);
            long var10 = this.scan.readLong();
            this.scan.close();
            this.currentLogFileNumber = LogCounter.getLogFileNumber(var10);
            this.scan = this.logFactory.getLogFileAtPosition(var10);
            var8 = this.scan.getFilePointer();
            if (var8 == 24L) {
               continue;
            }
         }

         this.scan.seek(var8 - 4L);
         int var15 = this.scan.readInt();
         long var11 = var8 - (long)var15 - 16L;
         this.scan.seek(var11 + 4L);
         this.currentInstant = this.scan.readLong();
         if (this.currentInstant < this.stopAt && this.stopAt != 0L) {
            this.currentInstant = 0L;
            return null;
         }

         byte[] var13 = var1.getData();
         if (var13.length < var15) {
            var13 = new byte[var15];
            var1.setData(var13);
         }

         if (this.logFactory.databaseEncrypted()) {
            this.scan.readFully(var13, 0, var15);
            this.logFactory.decrypt(var13, 0, var15, var13, 0);
            var1.setLimit(0, var15);
         } else if (var3 == 0 && var2 == null) {
            this.scan.readFully(var13, 0, var15);
            var1.setLimit(0, var15);
         } else {
            var6 = var15 > var5 ? var5 : var15;
            this.scan.readFully(var13, 0, var6);
            var1.setLimit(0, var6);
         }

         var7 = (LogRecord)var1.readObject();
         if (var7.isChecksum()) {
            var4 = false;
         } else if (var3 != 0 || var2 != null) {
            if (var7.isChecksum()) {
               var4 = false;
            }

            if (var4 && var3 != 0 && (var3 & var7.group()) == 0) {
               var4 = false;
            }

            if (var4 && var2 != null) {
               TransactionId var14 = var7.getTransactionId();
               if (!var14.equals(var2)) {
                  var4 = false;
               }
            }

            if (var4 && !this.logFactory.databaseEncrypted() && var6 < var15) {
               int var16 = var1.getPosition();
               this.scan.readFully(var13, var6, var15 - var6);
               var1.setLimit(0, var15);
               var1.setPosition(var16);
            }
         }

         var8 = var11;
         this.scan.seek(var11);
      } while(!var4);

      return var7;
   }

   private LogRecord getNextRecordForward(ArrayInputStream var1, TransactionId var2, int var3) throws StandardException, IOException, ClassNotFoundException {
      long var4 = this.scan.getFilePointer();
      int var7 = LogRecord.formatOverhead() + LogRecord.maxGroupStoredSize();
      if (var2 != null) {
         var7 += LogRecord.maxTransactionIdStoredSize(var2);
      }

      boolean var6;
      LogRecord var17;
      do {
         var6 = true;
         Object var9 = null;
         int var8 = -1;
         if (var4 + 4L > this.currentLogFileLength) {
            if (var4 != this.currentLogFileLength) {
               this.fuzzyLogEnd = true;
            }

            return null;
         }

         int var10;
         for(var10 = this.scan.readInt(); var10 == 0 || var4 + (long)var10 + 16L > this.currentLogFileLength; var10 = this.scan.readInt()) {
            if (var10 != 0) {
               this.fuzzyLogEnd = true;
               this.scan.close();
               this.scan = null;
               return null;
            }

            if (this.stopAt != 0L && LogCounter.getLogFileNumber(this.stopAt) == this.currentLogFileNumber) {
               return null;
            }

            this.scan.close();
            this.scan = this.logFactory.getLogFileAtBeginning(++this.currentLogFileNumber);
            if (this.scan == null) {
               return null;
            }

            var4 = this.scan.getFilePointer();
            this.scan.seek(16L);
            long var11 = this.scan.readLong();
            if (var11 != this.knownGoodLogEnd) {
               return null;
            }

            this.scan.seek(var4);
            this.knownGoodLogEnd = LogCounter.makeLogInstantAsLong(this.currentLogFileNumber, var4);
            this.currentLogFileLength = this.scan.length();
            if (var4 + 4L >= this.currentLogFileLength) {
               return null;
            }
         }

         this.currentInstant = this.scan.readLong();
         if (this.currentInstant < this.knownGoodLogEnd) {
            this.fuzzyLogEnd = true;
            return null;
         }

         if (this.stopAt != 0L && this.currentInstant > this.stopAt) {
            this.currentInstant = 0L;
            return null;
         }

         byte[] var18 = var1.getData();
         if (var18.length < var10) {
            var18 = new byte[var10];
            var1.setData(var18);
         }

         if (this.logFactory.databaseEncrypted()) {
            this.scan.readFully(var18, 0, var10);
            int var12 = this.logFactory.decrypt(var18, 0, var10, var18, 0);
            var1.setLimit(0, var12);
         } else if (var3 == 0 && var2 == null) {
            this.scan.readFully(var18, 0, var10);
            var1.setLimit(0, var10);
         } else {
            var8 = var10 > var7 ? var7 : var10;
            this.scan.readFully(var18, 0, var8);
            var1.setLimit(0, var8);
         }

         var17 = (LogRecord)var1.readObject();
         if (var3 != 0 || var2 != null) {
            if (var3 != 0 && (var3 & var17.group()) == 0) {
               var6 = false;
            }

            if (var6 && var2 != null) {
               TransactionId var19 = var17.getTransactionId();
               if (!var19.equals(var2)) {
                  var6 = false;
               }
            }

            if (var6 && !this.logFactory.databaseEncrypted() && var8 < var10) {
               int var20 = var1.getPosition();
               this.scan.readFully(var18, var8, var10 - var8);
               var1.setLimit(0, var10);
               var1.setPosition(var20);
            }
         }

         if (!var6) {
            this.scan.seek(var4 - 4L);
         }

         int var21 = this.scan.readInt();
         if (var21 != var10 && var21 < var10 && var21 < var10) {
            this.fuzzyLogEnd = true;
            return null;
         }

         var4 += (long)(var10 + 16);
         this.knownGoodLogEnd = LogCounter.makeLogInstantAsLong(this.currentLogFileNumber, var4);
         this.scan.seek(var4);
         if (var17.isChecksum()) {
            var6 = false;
            Loggable var13 = var17.getLoggable();
            ChecksumOperation var14 = (ChecksumOperation)var13;
            int var15 = var14.getDataLength();
            if (var18.length < var15) {
               var18 = new byte[var15];
               var1.setData(var18);
               var1.setLimit(0, var15);
            }

            boolean var16 = false;
            if (var4 + (long)var15 <= this.currentLogFileLength) {
               this.scan.readFully(var18, 0, var15);
               if (var14.isChecksumValid(var18, 0, var15)) {
                  var16 = true;
               }
            }

            if (!var16) {
               this.fuzzyLogEnd = true;
               this.scan.close();
               this.scan = null;
               return null;
            }

            this.scan.seek(var4);
         }
      } while(!var6);

      return var17;
   }

   public void resetPosition(LogInstant var1) throws IOException, StandardException {
      long var2 = ((LogCounter)var1).getValueAsLong();
      if (var2 != 0L && (this.stopAt == 0L || this.scanDirection != 1 || var2 <= this.stopAt) && (this.scanDirection != 1 || var2 >= this.stopAt)) {
         long var4 = ((LogCounter)var1).getLogFileNumber();
         if (var4 != this.currentLogFileNumber) {
            this.scan.close();
            this.scan = this.logFactory.getLogFileAtPosition(var2);
            this.currentLogFileNumber = var4;
            if (this.scanDirection == 1) {
               this.currentLogFileLength = this.scan.length();
            }
         } else {
            long var6 = ((LogCounter)var1).getLogFilePosition();
            this.scan.seek(var6);
            this.currentLogFileLength = this.scan.length();
         }

         this.currentInstant = var2;
         this.knownGoodLogEnd = this.currentInstant;
      } else {
         this.close();
         throw StandardException.newException("XSLB8.S", new Object[]{var1, new LogCounter(this.stopAt)});
      }
   }

   public long getInstant() {
      return this.currentInstant;
   }

   public long getLogRecordEnd() {
      return this.knownGoodLogEnd;
   }

   public boolean isLogEndFuzzy() {
      return this.fuzzyLogEnd;
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

      this.logFactory = null;
      this.currentLogFileNumber = -1L;
      this.currentLogFileLength = -1L;
      this.currentInstant = 0L;
      this.stopAt = 0L;
      this.scanDirection = 0;
   }
}
