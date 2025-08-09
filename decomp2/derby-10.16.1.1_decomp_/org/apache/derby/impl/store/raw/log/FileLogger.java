package org.apache.derby.impl.store.raw.log;

import java.io.IOException;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.iapi.services.io.FormatIdOutputStream;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.RePreparable;
import org.apache.derby.iapi.store.raw.Undoable;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.log.Logger;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.store.raw.xact.TransactionFactory;
import org.apache.derby.iapi.store.raw.xact.TransactionId;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class FileLogger implements Logger {
   private LogRecord logRecord;
   protected byte[] encryptionBuffer;
   private DynamicByteArrayOutputStream logOutputBuffer;
   private FormatIdOutputStream logicalOut;
   private ArrayInputStream logIn;
   private LogToFile logFactory;

   public FileLogger(LogToFile var1) {
      this.logFactory = var1;
      this.logOutputBuffer = new DynamicByteArrayOutputStream(1024);
      this.logicalOut = new FormatIdOutputStream(this.logOutputBuffer);
      this.logIn = new ArrayInputStream();
      this.logRecord = new LogRecord();
   }

   public void close() throws IOException {
      if (this.logOutputBuffer != null) {
         this.logOutputBuffer.close();
         this.logOutputBuffer = null;
      }

      this.logIn = null;
      this.logFactory = null;
      this.logicalOut = null;
      this.logRecord = null;
   }

   public synchronized LogInstant logAndDo(RawTransaction var1, Loggable var2) throws StandardException {
      boolean var3 = false;
      boolean var4 = false;

      try {
         this.logOutputBuffer.reset();
         TransactionId var6 = var1.getId();
         this.logRecord.setValue(var6, var2);
         var4 = true;
         this.logicalOut.writeObject(this.logRecord);
         var4 = false;
         int var7 = 0;
         int var8 = 0;
         int var9 = 0;
         ByteArray var10 = var2.getPreparedLog();
         byte[] var5;
         if (var10 != null) {
            var5 = var10.getArray();
            var7 = var10.getLength();
            var8 = var10.getOffset();
            this.logIn.setData(var5);
            this.logIn.setPosition(var8);
            this.logIn.setLimit(var7);
         } else {
            var5 = null;
            var7 = 0;
         }

         this.logicalOut.writeInt(var7);
         var9 = this.logOutputBuffer.getPosition() + var7;
         LogInstant var11 = null;
         int var12 = 0;

         try {
            if (this.logFactory.databaseEncrypted()) {
               var12 = var9;
               if (var9 % this.logFactory.getEncryptionBlockSize() != 0) {
                  var12 = var9 + this.logFactory.getEncryptionBlockSize() - var9 % this.logFactory.getEncryptionBlockSize();
               }

               if (this.encryptionBuffer == null || this.encryptionBuffer.length < var12) {
                  this.encryptionBuffer = new byte[var12];
               }

               System.arraycopy(this.logOutputBuffer.getByteArray(), 0, this.encryptionBuffer, 0, var9 - var7);
               if (var7 > 0) {
                  System.arraycopy(var5, var8, this.encryptionBuffer, var9 - var7, var7);
               }

               this.logFactory.encrypt(this.encryptionBuffer, 0, var12, this.encryptionBuffer, 0);
            }

            if ((var2.group() & 3) != 0) {
               synchronized(this.logFactory) {
                  long var14 = 0L;
                  if (this.logFactory.databaseEncrypted()) {
                     var14 = this.logFactory.appendLogRecord(this.encryptionBuffer, 0, var12, (byte[])null, -1, 0);
                  } else {
                     var14 = this.logFactory.appendLogRecord(this.logOutputBuffer.getByteArray(), 0, var9, var5, var8, var7);
                  }

                  var11 = new LogCounter(var14);
                  var2.doMe(var1, var11, this.logIn);
               }
            } else {
               long var13 = 0L;
               if (this.logFactory.databaseEncrypted()) {
                  var13 = this.logFactory.appendLogRecord(this.encryptionBuffer, 0, var12, (byte[])null, -1, 0);
               } else {
                  var13 = this.logFactory.appendLogRecord(this.logOutputBuffer.getByteArray(), 0, var9, var5, var8, var7);
               }

               var11 = new LogCounter(var13);
               var2.doMe(var1, var11, this.logIn);
            }
         } catch (StandardException var24) {
            throw this.logFactory.markCorrupt(StandardException.newException("XSLA1.D", var24, new Object[]{var2}));
         } catch (IOException var25) {
            throw this.logFactory.markCorrupt(StandardException.newException("XSLA1.D", var25, new Object[]{var2}));
         } finally {
            this.logIn.clearLimit();
         }

         return var11;
      } catch (IOException var27) {
         if (var4) {
            throw StandardException.newException("XSLB1.S", var27, new Object[]{var2});
         } else {
            throw StandardException.newException("XSLB2.S", var27, new Object[]{var2});
         }
      }
   }

   public LogInstant logAndUndo(RawTransaction var1, Compensation var2, LogInstant var3, LimitObjectInput var4) throws StandardException {
      boolean var5 = false;

      try {
         this.logOutputBuffer.reset();
         TransactionId var6 = var1.getId();
         this.logRecord.setValue(var6, var2);
         var5 = true;
         this.logicalOut.writeObject(this.logRecord);
         var5 = false;
         this.logicalOut.writeLong(((LogCounter)var3).getValueAsLong());
         int var7 = this.logOutputBuffer.getPosition();
         long var8 = 0L;
         if (this.logFactory.databaseEncrypted()) {
            int var10 = var7;
            if (var7 % this.logFactory.getEncryptionBlockSize() != 0) {
               var10 = var7 + this.logFactory.getEncryptionBlockSize() - var7 % this.logFactory.getEncryptionBlockSize();
            }

            if (this.encryptionBuffer == null || this.encryptionBuffer.length < var10) {
               this.encryptionBuffer = new byte[var10];
            }

            System.arraycopy(this.logOutputBuffer.getByteArray(), 0, this.encryptionBuffer, 0, var7);
            this.logFactory.encrypt(this.encryptionBuffer, 0, var10, this.encryptionBuffer, 0);
            var8 = this.logFactory.appendLogRecord(this.encryptionBuffer, 0, var10, (byte[])null, 0, 0);
         } else {
            var8 = this.logFactory.appendLogRecord(this.logOutputBuffer.getByteArray(), 0, var7, (byte[])null, 0, 0);
         }

         LogCounter var18 = new LogCounter(var8);

         try {
            var2.doMe(var1, var18, var4);
         } catch (StandardException var12) {
            throw this.logFactory.markCorrupt(StandardException.newException("XSLA1.D", var12, new Object[]{var2}));
         } catch (IOException var13) {
            throw this.logFactory.markCorrupt(StandardException.newException("XSLA1.D", var13, new Object[]{var2}));
         }

         return var18;
      } catch (IOException var14) {
         if (var5) {
            throw StandardException.newException("XSLB1.S", var14, new Object[]{var2});
         } else {
            throw StandardException.newException("XSLB2.S", var14, new Object[]{var2});
         }
      }
   }

   public void flush(LogInstant var1) throws StandardException {
      this.logFactory.flush(var1);
   }

   public void flushAll() throws StandardException {
      this.logFactory.flushAll();
   }

   public void reprepare(RawTransaction var1, TransactionId var2, LogInstant var3, LogInstant var4) throws StandardException {
      int var5 = 0;
      int var6 = 0;
      RePreparable var7 = null;
      ArrayInputStream var8 = null;

      try {
         StreamLogScan var9;
         if (var4 == null) {
            var9 = (StreamLogScan)this.logFactory.openBackwardsScan(var3);
         } else {
            if (var4.lessThan(var3)) {
               return;
            }

            var9 = (StreamLogScan)this.logFactory.openBackwardsScan(((LogCounter)var4).getValueAsLong(), var3);
         }

         var8 = new ArrayInputStream(new byte[4096]);

         LogRecord var10;
         while((var10 = var9.getNextRecord(var8, var2, 0)) != null) {
            ++var6;
            if (var10.isCLR()) {
               ++var5;
               var10.skipLoggable();
               long var11 = var8.readLong();
               var9.resetPosition(new LogCounter(var11));
            } else if (var10.requiresPrepareLocks()) {
               var7 = var10.getRePreparable();
               if (var7 != null) {
                  var7.reclaimPrepareLocks(var1, var1.newLockingPolicy(1, 4, true));
               }
            }
         }

      } catch (ClassNotFoundException var23) {
         throw this.logFactory.markCorrupt(StandardException.newException("XSLA3.D", var23, new Object[0]));
      } catch (IOException var24) {
         throw this.logFactory.markCorrupt(StandardException.newException("XSLA5.D", var24, new Object[0]));
      } catch (StandardException var25) {
         throw this.logFactory.markCorrupt(StandardException.newException("XSLA8.D", var25, new Object[]{var2, var7, null}));
      } finally {
         if (var8 != null) {
            try {
               var8.close();
            } catch (IOException var22) {
               throw this.logFactory.markCorrupt(StandardException.newException("XSLA5.D", var22, new Object[]{var2}));
            }
         }

      }
   }

   public void undo(RawTransaction var1, TransactionId var2, LogInstant var3, LogInstant var4) throws StandardException {
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      Compensation var9 = null;
      Undoable var10 = null;
      ArrayInputStream var11 = null;

      try {
         StreamLogScan var8;
         if (var4 == null) {
            var8 = (StreamLogScan)this.logFactory.openBackwardsScan(var3);
         } else {
            if (var4.lessThan(var3)) {
               return;
            }

            long var12 = ((LogCounter)var4).getValueAsLong();
            var8 = (StreamLogScan)this.logFactory.openBackwardsScan(var12, var3);
         }

         var11 = new ArrayInputStream(new byte[4096]);

         LogRecord var30;
         while((var30 = var8.getNextRecord(var11, var2, 0)) != null) {
            ++var7;
            if (var30.isCLR()) {
               ++var6;
               var30.skipLoggable();
               long var13 = var11.readLong();
               var8.resetPosition(new LogCounter(var13));
            } else {
               var10 = var30.getUndoable();
               if (var10 != null) {
                  int var31 = var11.readInt();
                  int var14 = var11.getPosition();
                  var11.setLimit(var31);
                  var9 = var10.generateUndo(var1, var11);
                  ++var5;
                  if (var9 != null) {
                     var11.setLimit(var14, var31);
                     var1.logAndUndo(var9, new LogCounter(var8.getInstant()), var11);
                     var9.releaseResource(var1);
                     var9 = null;
                  }
               }
            }
         }

      } catch (ClassNotFoundException var25) {
         throw this.logFactory.markCorrupt(StandardException.newException("XSLA3.D", var25, new Object[0]));
      } catch (IOException var26) {
         throw this.logFactory.markCorrupt(StandardException.newException("XSLA5.D", var26, new Object[0]));
      } catch (StandardException var27) {
         throw this.logFactory.markCorrupt(StandardException.newException("XSLA8.D", var27, new Object[]{var2, var10, var9}));
      } finally {
         if (var9 != null) {
            var9.releaseResource(var1);
         }

         if (var11 != null) {
            try {
               var11.close();
            } catch (IOException var24) {
               throw this.logFactory.markCorrupt(StandardException.newException("XSLA5.D", var24, new Object[]{var2}));
            }
         }

      }
   }

   protected long redo(RawTransaction var1, TransactionFactory var2, StreamLogScan var3, long var4, long var6) throws IOException, StandardException, ClassNotFoundException {
      int var8 = 0;
      int var9 = 0;
      boolean var10 = false;
      int var11 = 0;
      int var12 = 0;
      int var13 = 0;
      Object var14 = null;
      long var15 = 0L;
      this.logIn.setData(this.logOutputBuffer.getByteArray());
      StreamLogScan var17 = null;
      Loggable var18 = null;
      long var19 = 0L;

      try {
         LogRecord var21;
         while((var21 = var3.getNextRecord(this.logIn, (TransactionId)null, 0)) != null) {
            ++var8;
            long var22 = 0L;
            var15 = var3.getInstant();
            var19 = var3.getLogRecordEnd();
            if (var4 == 0L || var15 >= var4 || var21.isFirst() || var21.isComplete() || var21.isPrepare()) {
               TransactionId var32 = var21.getTransactionId();
               if (!var2.findTransaction(var32, var1)) {
                  if (var4 != 0L && var15 < var4 && (var21.isPrepare() || var21.isComplete())) {
                     ++var13;
                     continue;
                  }

                  if (var6 == 0L && !var21.isFirst()) {
                     throw StandardException.newException("XSLAO.D", new Object[]{MessageService.getTextMessage("L012", new Object[]{var32})});
                  }

                  ++var12;
                  var1.setTransactionId(var21.getLoggable(), var32);
               } else {
                  if (var6 == 0L && var21.isFirst()) {
                     throw StandardException.newException("XSLAO.D", new Object[]{MessageService.getTextMessage("L013", new Object[]{var32})});
                  }

                  if (var21.isFirst()) {
                     ++var12;
                     continue;
                  }
               }

               var18 = var21.getLoggable();
               if (var18.needsRedo(var1)) {
                  ++var9;
                  if (var21.isCLR()) {
                     ++var11;
                     if (var22 == 0L) {
                        var22 = this.logIn.readLong();
                     }

                     if (var17 == null) {
                        var17 = (StreamLogScan)this.logFactory.openForwardsScan(var22, (LogInstant)null);
                     } else {
                        var17.resetPosition(new LogCounter(var22));
                     }

                     this.logIn.clearLimit();
                     LogRecord var24 = var17.getNextRecord(this.logIn, (TransactionId)null, 0);
                     Undoable var25 = var24.getUndoable();
                     ((Compensation)var18).setUndoOp(var25);
                  }

                  int var36 = this.logIn.readInt();
                  this.logIn.setLimit(var36);
                  var18.doMe(var1, new LogCounter(var15), this.logIn);
                  var18.releaseResource(var1);
                  var18 = null;
               }

               if (var21.isComplete()) {
                  ++var13;
                  var1.commit();
               }
            }
         }

         long var35 = var3.getLogRecordEnd();
         if (var35 != 0L && LogCounter.getLogFileNumber(var19) < LogCounter.getLogFileNumber(var35)) {
            var19 = var35;
         }
      } catch (StandardException var29) {
         throw StandardException.newException("XSLA7.D", var29, new Object[]{var18});
      } finally {
         var3.close();
         Object var31 = null;
         if (var17 != null) {
            var17.close();
            Object var34 = null;
         }

         if (var18 != null) {
            var18.releaseResource(var1);
         }

      }

      return var19;
   }

   protected Loggable readLogRecord(StreamLogScan var1, int var2) throws IOException, StandardException, ClassNotFoundException {
      Loggable var3 = null;
      ArrayInputStream var4 = new ArrayInputStream(new byte[var2]);
      LogRecord var5 = var1.getNextRecord(var4, (TransactionId)null, 0);
      if (var5 != null) {
         var3 = var5.getLoggable();
      }

      return var3;
   }
}
