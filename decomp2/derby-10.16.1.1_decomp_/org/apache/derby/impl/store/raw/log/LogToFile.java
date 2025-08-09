package org.apache.derby.impl.store.raw.log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.SyncFailedException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.zip.CRC32;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.daemon.DaemonService;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.monitor.PersistentService;
import org.apache.derby.iapi.services.property.PersistentSet;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.DatabaseInstant;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.ScanHandle;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.log.LogScan;
import org.apache.derby.iapi.store.raw.log.Logger;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.store.raw.xact.TransactionFactory;
import org.apache.derby.iapi.store.raw.xact.TransactionId;
import org.apache.derby.iapi.store.replication.master.MasterFactory;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.io.WritableStorageFactory;
import org.apache.derby.shared.common.error.ErrorStringBuilder;
import org.apache.derby.shared.common.error.ShutdownException;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.info.ProductVersionHolder;

public final class LogToFile implements LogFactory, ModuleControl, ModuleSupportable, Serviceable {
   private static final long INT_LENGTH = 4L;
   private static int fid = 128;
   public static final int LOG_FILE_HEADER_SIZE = 24;
   protected static final int LOG_FILE_HEADER_PREVIOUS_LOG_INSTANT_OFFSET = 16;
   public static final int LOG_RECORD_OVERHEAD = 16;
   public static final String DBG_FLAG = null;
   public static final String DUMP_LOG_ONLY = null;
   public static final String DUMP_LOG_FROM_LOG_FILE = null;
   protected static final String LOG_SYNC_STATISTICS = "LogSyncStatistics";
   private static final int OBSOLETE_LOG_VERSION_NUMBER = 9;
   private static final int DEFAULT_LOG_SWITCH_INTERVAL = 1048576;
   private static final int LOG_SWITCH_INTERVAL_MIN = 100000;
   private static final int LOG_SWITCH_INTERVAL_MAX = 134217728;
   private static final int CHECKPOINT_INTERVAL_MIN = 100000;
   private static final int CHECKPOINT_INTERVAL_MAX = 134217728;
   private static final int DEFAULT_CHECKPOINT_INTERVAL = 10485760;
   private static final int DEFAULT_LOG_BUFFER_SIZE = 32768;
   private static final int LOG_BUFFER_SIZE_MIN = 8192;
   private static final int LOG_BUFFER_SIZE_MAX = 134217728;
   private int logBufferSize = 32768;
   private static final byte IS_BETA_FLAG = 1;
   private static final byte IS_DURABILITY_TESTMODE_NO_SYNC_FLAG = 2;
   private static boolean wasDBInDurabilityTestModeNoSync = false;
   private static final String DEFAULT_LOG_ARCHIVE_DIRECTORY = "DEFAULT";
   private int logSwitchInterval = 1048576;
   private int checkpointInterval = 10485760;
   String dataDirectory;
   private WritableStorageFactory logStorageFactory;
   private boolean logBeingFlushed;
   protected LogAccessFile logOut;
   private StorageRandomAccessFile firstLog = null;
   protected long endPosition = -1L;
   long lastFlush = 0L;
   long logFileNumber = -1L;
   long bootTimeLogFileNumber = -1L;
   long firstLogFileNumber = -1L;
   private long maxLogFileNumber = 2147483647L;
   private CheckpointOperation currentCheckpoint;
   long checkpointInstant;
   private DaemonService checkpointDaemon;
   private int myClientNumber;
   private volatile boolean checkpointDaemonCalled;
   private long logWrittenFromLastCheckPoint = 0L;
   private RawStoreFactory rawStoreFactory;
   protected DataFactory dataFactory;
   protected boolean ReadOnlyDB;
   private MasterFactory masterFactory;
   private boolean inReplicationMasterMode = false;
   private boolean inReplicationSlaveMode = false;
   private volatile StandardException replicationSlaveException = null;
   private boolean inReplicationSlavePreMode = false;
   private Object slaveRecoveryMonitor;
   private long allowedToReadFileNumber = -1L;
   private boolean keepAllLogs = PropertyUtil.getSystemBoolean("derby.storage.keepTransactionLog");
   private boolean databaseEncrypted;
   private boolean recoveryNeeded = true;
   private boolean inCheckpoint = false;
   private boolean inRedo = false;
   private boolean inLogSwitch = false;
   private boolean stopped = false;
   String logDevice;
   private boolean logNotSynced = false;
   private volatile boolean logArchived = false;
   private boolean logSwitchRequired = false;
   int test_logWritten = 0;
   int test_numRecordToFillLog = -1;
   private int mon_flushCalls;
   private int mon_syncCalls;
   private int mon_numLogFlushWaits;
   private boolean mon_LogSyncStatistics;
   private int mon_numBytesToLog;
   protected volatile StandardException corrupt;
   private boolean isFrozen;
   ProductVersionHolder jbmsVersion;
   private int onDiskMajorVersion;
   private int onDiskMinorVersion;
   private boolean onDiskBeta;
   private CRC32 checksum = new CRC32();
   private boolean isWriteSynced = false;
   private boolean jvmSyncErrorChecked = false;
   private volatile long logFileToBackup;
   private volatile boolean backupInProgress = false;
   public static final String TEST_LOG_SWITCH_LOG = null;
   public static final String TEST_LOG_INCOMPLETE_LOG_WRITE = null;
   public static final String TEST_LOG_PARTIAL_LOG_WRITE_NUM_BYTES = null;
   public static final String TEST_LOG_FULL = null;
   public static final String TEST_SWITCH_LOG_FAIL1 = null;
   public static final String TEST_SWITCH_LOG_FAIL2 = null;
   public static final String TEST_RECORD_TO_FILL_LOG = null;
   public static final String TEST_MAX_LOGFILE_NUMBER = null;
   private int action;
   private StorageFile activeFile;
   private File toFile;
   private String activePerms;

   public int getTypeFormatId() {
      return 128;
   }

   public StandardException markCorrupt(StandardException var1) {
      boolean var2 = false;
      synchronized(this) {
         if (this.corrupt == null && var1 != null) {
            this.corrupt = var1;
            var2 = true;
         }
      }

      if (this.corrupt == var1) {
         this.logErrMsg((Throwable)this.corrupt);
      }

      if (var2) {
         synchronized(this) {
            this.stopped = true;
            if (this.logOut != null) {
               try {
                  this.logOut.corrupt();
               } catch (IOException var6) {
               }
            }

            this.logOut = null;
         }

         if (this.dataFactory != null) {
            this.dataFactory.markCorrupt((StandardException)null);
         }
      }

      return var1;
   }

   private void checkCorrupt() throws StandardException {
      synchronized(this) {
         if (this.corrupt != null) {
            throw StandardException.newException("XSLAA.D", this.corrupt, new Object[0]);
         }
      }
   }

   public Logger getLogger() {
      return this.ReadOnlyDB ? null : new FileLogger(this);
   }

   public void setRawStoreFactory(RawStoreFactory var1) {
      this.rawStoreFactory = var1;
   }

   public void recover(DataFactory var1, TransactionFactory var2) throws StandardException {
      this.checkCorrupt();
      this.dataFactory = var1;
      if (this.firstLog != null) {
         this.logOut = new LogAccessFile(this, this.firstLog, this.logBufferSize);
      }

      if (this.inReplicationSlaveMode) {
         synchronized(this.slaveRecoveryMonitor) {
            while(this.inReplicationSlaveMode && this.allowedToReadFileNumber < this.bootTimeLogFileNumber) {
               if (this.replicationSlaveException != null) {
                  throw this.replicationSlaveException;
               }

               try {
                  this.slaveRecoveryMonitor.wait();
               } catch (InterruptedException var24) {
                  InterruptStatus.setInterrupted();
               }
            }
         }
      }

      if (!this.recoveryNeeded) {
         var2.useTransactionTable((Formatable)null);
         var2.resetTranId();
      } else {
         try {
            FileLogger var3 = (FileLogger)this.getLogger();
            if (this.checkpointInstant != 0L) {
               this.currentCheckpoint = this.findCheckpoint(this.checkpointInstant, var3);
            }

            long var4 = 0L;
            long var6 = 0L;
            long var8 = 0L;
            Object var10 = null;
            StreamLogScan var31;
            if (this.currentCheckpoint != null) {
               Object var11 = null;
               var2.useTransactionTable((Formatable)var11);
               var4 = this.currentCheckpoint.redoLWM();
               var6 = this.currentCheckpoint.undoLWM();
               if (var11 != null) {
                  var8 = this.checkpointInstant;
               }

               this.firstLogFileNumber = LogCounter.getLogFileNumber(var4);
               if (LogCounter.getLogFileNumber(var6) < this.firstLogFileNumber) {
                  this.firstLogFileNumber = LogCounter.getLogFileNumber(var6);
               }

               var31 = (StreamLogScan)this.openForwardsScan(var6, (LogInstant)null);
            } else {
               var2.useTransactionTable((Formatable)null);
               long var32 = LogCounter.makeLogInstantAsLong(this.bootTimeLogFileNumber, 24L);
               this.firstLogFileNumber = this.bootTimeLogFileNumber;
               var31 = (StreamLogScan)this.openForwardsScan(var32, (LogInstant)null);
            }

            RawTransaction var33 = var2.startTransaction(this.rawStoreFactory, getContextService().getCurrentContextManager(), "UserTransaction");
            var33.recoveryTransaction();
            this.inRedo = true;
            long var12 = var3.redo(var33, var2, var31, var4, var8);
            this.inRedo = false;
            this.logFileNumber = this.bootTimeLogFileNumber;
            StorageRandomAccessFile var14 = null;
            if (var12 != 0L) {
               this.logFileNumber = LogCounter.getLogFileNumber(var12);
               this.ReadOnlyDB = var1.isReadOnly();
               StorageFile var34 = this.getLogFileName(this.logFileNumber);
               if (!this.ReadOnlyDB) {
                  IOException var36 = null;

                  try {
                     if (this.isWriteSynced) {
                        var14 = this.openLogFileInWriteMode(var34);
                     } else {
                        var14 = this.privRandomAccessFile(var34, "rw");
                     }
                  } catch (IOException var22) {
                     var14 = null;
                     var36 = var22;
                  }

                  if (var14 == null || !this.privCanWrite(var34)) {
                     if (var14 != null) {
                        var14.close();
                     }

                     var14 = null;
                     Monitor.logTextMessage("L022");
                     if (var36 != null) {
                        Monitor.logThrowable(var36);
                     }

                     this.ReadOnlyDB = true;
                  }
               }

               if (!this.ReadOnlyDB) {
                  this.setEndPosition(LogCounter.getLogFilePosition(var12));
                  if (var31.isLogEndFuzzy()) {
                     var14.seek(this.endPosition);
                     long var37 = var14.length();
                     Monitor.logTextMessage("L010", var34, this.endPosition, var37);
                     long var18 = (var37 - this.endPosition) / (long)this.logBufferSize;
                     int var20 = (int)((var37 - this.endPosition) % (long)this.logBufferSize);
                     byte[] var21 = new byte[this.logBufferSize];

                     while(var18-- > 0L) {
                        var14.write(var21);
                     }

                     if (var20 != 0) {
                        var14.write(var21, 0, var20);
                     }

                     if (!this.isWriteSynced) {
                        this.syncFile(var14);
                     }
                  }

                  this.lastFlush = this.endPosition;
                  var14.seek(this.endPosition);
               }
            } else {
               Monitor.logTextMessage("L007");
               StorageFile var15 = this.getLogFileName(this.logFileNumber);
               if (this.privExists(var15) && !this.privDelete(var15)) {
                  var15 = this.getLogFileName(++this.logFileNumber);
               }

               IOException var16 = null;

               try {
                  var14 = this.privRandomAccessFile(var15, "rw");
               } catch (IOException var23) {
                  var14 = null;
                  var16 = var23;
               }

               if (var14 != null && this.privCanWrite(var15)) {
                  try {
                     if (!this.initLogFile(var14, this.logFileNumber, 0L)) {
                        throw this.markCorrupt(StandardException.newException("XSLAQ.D", new Object[]{var15.getPath()}));
                     }
                  } catch (IOException var25) {
                     throw this.markCorrupt(StandardException.newException("XSLA2.D", var25, new Object[0]));
                  }

                  this.setEndPosition(var14.getFilePointer());
                  this.lastFlush = this.endPosition;
                  if (this.isWriteSynced) {
                     this.preAllocateNewLogFile(var14);
                     var14.close();
                     var14 = this.openLogFileInWriteMode(var15);
                     var14.seek(this.endPosition);
                  }

                  this.logSwitchRequired = false;
               } else {
                  if (var14 != null) {
                     var14.close();
                  }

                  var14 = null;
                  Monitor.logTextMessage("L022");
                  if (var16 != null) {
                     Monitor.logThrowable(var16);
                  }

                  this.ReadOnlyDB = true;
               }
            }

            if (var14 != null) {
               if (this.logOut != null) {
                  this.logOut.close();
               }

               this.logOut = new LogAccessFile(this, var14, this.logBufferSize);
            }

            if (this.logSwitchRequired) {
               this.switchLogFile();
            }

            boolean var35 = var2.noActiveUpdateTransaction();
            if (this.ReadOnlyDB && !var35) {
               throw StandardException.newException("XSLAF.D", new Object[0]);
            }

            if (!var35) {
               var2.rollbackAllTransactions(var33, this.rawStoreFactory);
            }

            var2.handlePreparedXacts(this.rawStoreFactory);
            var33.close();
            this.dataFactory.postRecovery();
            var2.resetTranId();
            if (!this.ReadOnlyDB) {
               boolean var38 = true;
               if (this.currentCheckpoint != null && var35 && var4 != 0L && var6 != 0L && this.logFileNumber == LogCounter.getLogFileNumber(var4) && this.logFileNumber == LogCounter.getLogFileNumber(var6) && this.endPosition < LogCounter.getLogFilePosition(var4) + 1000L) {
                  var38 = false;
               }

               if (var38 && !this.checkpoint(this.rawStoreFactory, var1, var2, false)) {
                  this.flush(this.logFileNumber, this.endPosition);
               }
            }

            var3.close();
            this.recoveryNeeded = false;
         } catch (IOException var26) {
            throw this.markCorrupt(StandardException.newException("XSLA2.D", var26, new Object[0]));
         } catch (ClassNotFoundException var27) {
            throw this.markCorrupt(StandardException.newException("XSLA3.D", var27, new Object[0]));
         } catch (StandardException var28) {
            throw this.markCorrupt(var28);
         } catch (Throwable var29) {
            throw this.markCorrupt(StandardException.newException("XSLA6.D", var29, new Object[0]));
         }
      }

      this.checkpointDaemon = this.rawStoreFactory.getDaemon();
      if (this.checkpointDaemon != null) {
         this.myClientNumber = this.checkpointDaemon.subscribe(this, true);
         this.dataFactory.setupCacheCleaner(this.checkpointDaemon);
      }

   }

   public boolean checkpoint(RawStoreFactory var1, DataFactory var2, TransactionFactory var3, boolean var4) throws StandardException {
      if (this.inReplicationSlavePreMode) {
         return true;
      } else {
         boolean var5 = this.checkpointWithTran((RawTransaction)null, var1, var2, var3, var4);
         return var5;
      }
   }

   private boolean checkpointWithTran(RawTransaction var1, RawStoreFactory var2, DataFactory var3, TransactionFactory var4, boolean var5) throws StandardException {
      if (this.logOut == null) {
         return false;
      } else {
         boolean var9 = true;

         long var7;
         do {
            synchronized(this) {
               if (this.corrupt != null) {
                  throw StandardException.newException("XSLAA.D", this.corrupt, new Object[0]);
               }

               var7 = this.endPosition;
               if (!this.inCheckpoint) {
                  this.inCheckpoint = true;
                  break;
               }

               if (var5) {
                  while(this.inCheckpoint) {
                     try {
                        this.wait();
                     } catch (InterruptedException var34) {
                        InterruptStatus.setInterrupted();
                     }
                  }
               } else {
                  var9 = false;
               }
            }
         } while(var9);

         if (!var9) {
            return false;
         } else {
            boolean var10 = var1 == null;

            try {
               if (var7 > (long)this.logSwitchInterval) {
                  this.switchLogFile();
                  this.logWrittenFromLastCheckPoint = 0L;
               } else {
                  this.logWrittenFromLastCheckPoint = -this.endPosition;
               }

               if (var10) {
                  var1 = var4.startInternalTransaction(var2, getContextService().getCurrentContextManager());
               }

               LogCounter var6;
               long var11;
               long var13;
               synchronized(this) {
                  var13 = this.currentInstant();
                  var6 = new LogCounter(var13);
                  LogCounter var16 = (LogCounter)var4.firstUpdateInstant();
                  if (var16 == null) {
                     var11 = var13;
                  } else {
                     var11 = var16.getValueAsLong();
                  }
               }

               var3.checkpoint();
               Formatable var15 = var4.getTransactionTable();
               CheckpointOperation var38 = new CheckpointOperation(var13, var11, var15);
               var1.logAndDo(var38);
               LogCounter var17 = (LogCounter)var1.getLastLogInstant();
               if (var17 == null) {
                  throw StandardException.newException("XSLAI.D", new Object[0]);
               }

               this.flush(var17);
               var1.commit();
               if (var10) {
                  var1.close();
                  var1 = null;
               }

               if (!this.writeControlFile(this.getControlFileName(), var17.getValueAsLong())) {
                  throw StandardException.newException("XSLAE.D", new Object[]{this.getControlFileName()});
               }

               this.currentCheckpoint = var38;
               if (!this.logArchived()) {
                  this.truncateLog(this.currentCheckpoint);
               }

               if (!this.backupInProgress) {
                  var3.removeDroppedContainerFileStubs(var6);
               }
            } catch (IOException var35) {
               throw this.markCorrupt(StandardException.newException("XSLA2.D", var35, new Object[0]));
            } finally {
               synchronized(this) {
                  this.inCheckpoint = false;
                  this.notifyAll();
               }

               if (var1 != null && var10) {
                  try {
                     var1.commit();
                     var1.close();
                  } catch (StandardException var31) {
                     throw this.markCorrupt(StandardException.newException("XSLA3.D", var31, new Object[0]));
                  }
               }

            }

            return true;
         }
      }
   }

   public void flush(LogInstant var1) throws StandardException {
      long var2;
      long var4;
      if (var1 == null) {
         var2 = 0L;
         var4 = 0L;
      } else {
         LogCounter var6 = (LogCounter)var1;
         var2 = var6.getLogFileNumber();
         var4 = var6.getLogFilePosition();
      }

      this.flush(var2, var4);
   }

   public void flushAll() throws StandardException {
      long var1;
      long var3;
      synchronized(this) {
         var1 = this.logFileNumber;
         var3 = this.endPosition;
      }

      this.flush(var1, var3);
   }

   private boolean verifyLogFormat(StorageFile var1, long var2) throws StandardException {
      boolean var4 = false;

      try {
         StorageRandomAccessFile var5 = this.privRandomAccessFile(var1, "r");
         var4 = this.verifyLogFormat(var5, var2);
         var5.close();
      } catch (IOException var6) {
      }

      return var4;
   }

   private boolean verifyLogFormat(StorageRandomAccessFile var1, long var2) throws StandardException {
      try {
         var1.seek(0L);
         int var4 = var1.readInt();
         int var5 = var1.readInt();
         long var6 = var1.readLong();
         if (var4 == fid && var6 == var2) {
            return true;
         } else {
            throw StandardException.newException("XSLAC.D", new Object[]{this.dataDirectory});
         }
      } catch (IOException var8) {
         throw StandardException.newException("XSLAM.D", var8, new Object[]{this.dataDirectory});
      }
   }

   private boolean initLogFile(StorageRandomAccessFile var1, long var2, long var4) throws IOException, StandardException {
      if (var1.length() != 0L) {
         return false;
      } else {
         var1.seek(0L);
         var1.writeInt(fid);
         var1.writeInt(9);
         var1.writeLong(var2);
         var1.writeLong(var4);
         this.syncFile(var1);
         return true;
      }
   }

   public void switchLogFile() throws StandardException {
      boolean var1 = false;
      synchronized(this) {
         while(this.logBeingFlushed | this.isFrozen) {
            try {
               this.wait();
            } catch (InterruptedException var12) {
               InterruptStatus.setInterrupted();
            }
         }

         if (this.endPosition != 24L) {
            StorageFile var3 = this.getLogFileName(this.logFileNumber + 1L);
            if (this.logFileNumber + 1L >= this.maxLogFileNumber) {
               throw StandardException.newException("XSLAK.D", new Object[]{this.maxLogFileNumber});
            } else {
               StorageRandomAccessFile var4 = null;

               try {
                  if (this.privExists(var3) && !this.privDelete(var3)) {
                     this.logErrMsg(MessageService.getTextMessage("L015", new Object[]{var3.getPath()}));
                     return;
                  }

                  try {
                     var4 = this.privRandomAccessFile(var3, "rw");
                  } catch (IOException var11) {
                     var4 = null;
                  }

                  if (var4 == null || !this.privCanWrite(var3)) {
                     if (var4 != null) {
                        var4.close();
                     }

                     Object var22 = null;
                     return;
                  }

                  if (this.initLogFile(var4, this.logFileNumber + 1L, LogCounter.makeLogInstantAsLong(this.logFileNumber, this.endPosition))) {
                     var1 = true;
                     this.logOut.writeEndMarker(0);
                     this.setEndPosition(this.endPosition + 4L);
                     this.inLogSwitch = true;
                     this.flush(this.logFileNumber, this.endPosition);
                     this.logOut.close();
                     this.logWrittenFromLastCheckPoint += this.endPosition;
                     this.setEndPosition(var4.getFilePointer());
                     this.lastFlush = this.endPosition;
                     if (this.isWriteSynced) {
                        this.preAllocateNewLogFile(var4);
                        var4.close();
                        var4 = this.openLogFileInWriteMode(var3);
                        var4.seek(this.endPosition);
                     }

                     this.logOut = new LogAccessFile(this, var4, this.logBufferSize);
                     Object var20 = null;
                     ++this.logFileNumber;
                  } else {
                     var4.close();
                     Object var21 = null;
                     if (this.privExists(var3)) {
                        this.privDelete(var3);
                     }

                     this.logErrMsg(MessageService.getTextMessage("L016", new Object[]{var3.getPath()}));
                     var3 = null;
                  }
               } catch (IOException var13) {
                  this.inLogSwitch = false;
                  this.logErrMsg(MessageService.getTextMessage("L017", new Object[]{var3.getPath(), var13.toString()}));

                  try {
                     if (var4 != null) {
                        var4.close();
                        Object var18 = null;
                     }
                  } catch (IOException var10) {
                  }

                  if (var3 != null && this.privExists(var3)) {
                     this.privDelete(var3);
                     var3 = null;
                  }

                  if (var1) {
                     this.logOut = null;
                     throw this.markCorrupt(StandardException.newException("XSLA2.D", var13, new Object[0]));
                  }
               }

               if (this.inReplicationSlaveMode) {
                  this.allowedToReadFileNumber = this.logFileNumber - 1L;
                  synchronized(this.slaveRecoveryMonitor) {
                     this.slaveRecoveryMonitor.notify();
                  }
               }

               this.inLogSwitch = false;
            }
         }
      }
   }

   private void flushBuffer(long var1, long var3) throws IOException, StandardException {
      synchronized(this) {
         if (var1 >= this.logFileNumber) {
            if (var3 >= this.lastFlush) {
               this.logOut.flushLogAccessFile();
            }
         }
      }
   }

   private void truncateLog(CheckpointOperation var1) {
      long var2;
      if ((var2 = this.getFirstLogNeeded(var1)) != -1L) {
         this.truncateLog(var2);
      }
   }

   private void truncateLog(long var1) {
      if (!this.keepAllLogs) {
         if (this.backupInProgress) {
            long var5 = this.logFileToBackup;
            if (var5 < var1) {
               var1 = var5;
            }
         }

         long var3 = this.firstLogFileNumber;

         for(this.firstLogFileNumber = var1; var3 < var1; ++var3) {
            Object var8 = null;

            try {
               StorageFile var9 = this.getLogFileName(var3);
               if (this.privDelete(var9)) {
               }
            } catch (StandardException var7) {
            }
         }

      }
   }

   private long getFirstLogNeeded(CheckpointOperation var1) {
      synchronized(this) {
         long var2 = var1 != null ? LogCounter.getLogFileNumber(var1.undoLWM()) : -1L;
         return var2;
      }
   }

   boolean writeControlFile(StorageFile var1, long var2) throws IOException, StandardException {
      StorageRandomAccessFile var4 = null;
      ByteArrayOutputStream var5 = new ByteArrayOutputStream(64);
      DataOutputStream var6 = new DataOutputStream(var5);
      var6.writeInt(fid);
      var6.writeInt(9);
      var6.writeLong(var2);
      if (this.onDiskMajorVersion == 0) {
         this.onDiskMajorVersion = this.jbmsVersion.getMajorVersion();
         this.onDiskMinorVersion = this.jbmsVersion.getMinorVersion();
         this.onDiskBeta = this.jbmsVersion.isBeta();
      }

      var6.writeInt(this.onDiskMajorVersion);
      var6.writeInt(this.onDiskMinorVersion);
      var6.writeInt(this.jbmsVersion.getBuildNumberAsInt());
      byte var7 = 0;
      if (this.onDiskBeta) {
         var7 = (byte)(var7 | 1);
      }

      if (this.logNotSynced || wasDBInDurabilityTestModeNoSync) {
         var7 = (byte)(var7 | 2);
      }

      var6.writeByte(var7);
      long var8 = 0L;
      var6.writeByte(0);
      var6.writeByte(0);
      var6.writeByte(0);
      var6.writeLong(var8);
      var6.flush();
      this.checksum.reset();
      this.checksum.update(var5.toByteArray(), 0, var5.size());
      var6.writeLong(this.checksum.getValue());
      var6.flush();

      try {
         this.checkCorrupt();

         try {
            var4 = this.privRandomAccessFile(var1, "rw");
         } catch (IOException var17) {
            var4 = null;
            boolean var11 = false;
            return var11;
         }

         if (!this.privCanWrite(var1)) {
            boolean var10 = false;
            return var10;
         } else {
            var4.seek(0L);
            var4.write(var5.toByteArray());
            this.syncFile(var4);
            var4.close();

            try {
               var4 = this.privRandomAccessFile(this.getMirrorControlFileName(), "rw");
            } catch (IOException var16) {
               var4 = null;
               boolean var19 = false;
               return var19;
            }

            var4.seek(0L);
            var4.write(var5.toByteArray());
            this.syncFile(var4);
            return true;
         }
      } finally {
         if (var4 != null) {
            var4.close();
         }

      }
   }

   private long readControlFile(StorageFile var1, Properties var2) throws IOException, StandardException {
      StorageRandomAccessFile var3 = null;
      ByteArrayInputStream var4 = null;
      DataInputStream var5 = null;
      var3 = this.privRandomAccessFile(var1, "r");
      boolean var6 = false;
      long var7 = 0L;
      long var9 = 0L;
      long var11 = var3.length();
      byte[] var13 = null;

      try {
         if (var11 < 16L) {
            var9 = -1L;
         } else if (var11 == 16L) {
            var13 = new byte[16];
            var3.readFully(var13);
         } else if (var11 > 16L) {
            var13 = new byte[(int)var3.length() - 8];
            var3.readFully(var13);
            var9 = var3.readLong();
            if (var9 != 0L) {
               this.checksum.reset();
               this.checksum.update(var13, 0, var13.length);
            }
         }

         if (var9 == this.checksum.getValue() || var9 == 0L) {
            var4 = new ByteArrayInputStream(var13);
            var5 = new DataInputStream(var4);
            if (var5.readInt() != fid) {
               throw StandardException.newException("XSLAC.D", new Object[]{this.dataDirectory});
            }

            int var14 = var5.readInt();
            var7 = var5.readLong();
            this.onDiskMajorVersion = var5.readInt();
            this.onDiskMinorVersion = var5.readInt();
            int var15 = var5.readInt();
            byte var16 = var5.readByte();
            wasDBInDurabilityTestModeNoSync = (var16 & 2) != 0;
            this.onDiskBeta = (var16 & 1) != 0;
            if (this.onDiskBeta && (!this.jbmsVersion.isBeta() || this.onDiskMajorVersion != this.jbmsVersion.getMajorVersion() || this.onDiskMinorVersion != this.jbmsVersion.getMinorVersion())) {
               boolean var17 = false;
               if (!var17) {
                  throw StandardException.newException("XSLAP.D", new Object[]{this.dataDirectory, ProductVersionHolder.simpleVersionString(this.onDiskMajorVersion, this.onDiskMinorVersion, this.onDiskBeta)});
               }
            }

            if (this.onDiskMajorVersion > this.jbmsVersion.getMajorVersion() || this.onDiskMajorVersion == this.jbmsVersion.getMajorVersion() && this.onDiskMinorVersion > this.jbmsVersion.getMinorVersion()) {
               throw StandardException.newException("XSLAN.D", new Object[]{this.dataDirectory, ProductVersionHolder.simpleVersionString(this.onDiskMajorVersion, this.onDiskMinorVersion, this.onDiskBeta)});
            }

            if (this.onDiskMajorVersion != this.jbmsVersion.getMajorVersion() || this.onDiskMinorVersion != this.jbmsVersion.getMinorVersion()) {
               var6 = true;
            }

            if (var9 == 0L && (this.onDiskMajorVersion > 3 || this.onDiskMinorVersion > 5 || this.onDiskMajorVersion == 0)) {
               var7 = 0L;
            }
         }
      } finally {
         if (var3 != null) {
            var3.close();
         }

         if (var4 != null) {
            var4.close();
         }

         if (var5 != null) {
            var5.close();
         }

      }

      if (var6 && isFullUpgrade(var2, ProductVersionHolder.simpleVersionString(this.onDiskMajorVersion, this.onDiskMinorVersion, this.onDiskBeta))) {
         this.onDiskMajorVersion = this.jbmsVersion.getMajorVersion();
         this.onDiskMinorVersion = this.jbmsVersion.getMinorVersion();
         this.onDiskBeta = this.jbmsVersion.isBeta();
         if (!this.writeControlFile(var1, var7)) {
            throw StandardException.newException("XSLAE.D", new Object[]{var1});
         }
      }

      return var7;
   }

   private void createLogDirectory() throws StandardException {
      StorageFile var1 = this.logStorageFactory.newStorageFile("log");
      if (this.privExists(var1)) {
         String[] var2 = this.privList(var1);
         if (var2 != null && var2.length != 0) {
            throw StandardException.newException("XSLAT.D", new Object[]{var1.getPath()});
         }
      } else {
         IOException var6 = null;
         boolean var3 = false;

         try {
            var3 = this.privMkdirs(var1);
         } catch (IOException var5) {
            var6 = var5;
         }

         if (!var3) {
            throw StandardException.newException("XSLAQ.D", var6, new Object[]{var1.getPath()});
         }

         this.createDataWarningFile();
      }

   }

   public void createDataWarningFile() throws StandardException {
      StorageFile var1 = this.logStorageFactory.newStorageFile("log", "README_DO_NOT_TOUCH_FILES.txt");
      if (!this.privExists(var1)) {
         OutputStreamWriter var2 = null;

         try {
            var2 = this.privGetOutputStreamWriter(var1);
            var2.write(MessageService.getTextMessage("M006", new Object[0]));
         } catch (IOException var12) {
         } finally {
            if (var2 != null) {
               try {
                  var2.close();
               } catch (IOException var11) {
               }
            }

         }
      }

   }

   public StorageFile getLogDirectory() throws StandardException {
      StorageFile var1 = null;
      var1 = this.logStorageFactory.newStorageFile("log");
      if (!this.privExists(var1)) {
         throw StandardException.newException("XSLAQ.D", new Object[]{var1.getPath()});
      } else {
         return var1;
      }
   }

   public String getCanonicalLogPath() {
      if (this.logDevice == null) {
         return null;
      } else {
         try {
            return this.logStorageFactory.getCanonicalName();
         } catch (IOException var2) {
            return null;
         }
      }
   }

   private StorageFile getControlFileName() throws StandardException {
      return this.logStorageFactory.newStorageFile(this.getLogDirectory(), "log.ctrl");
   }

   private StorageFile getMirrorControlFileName() throws StandardException {
      return this.logStorageFactory.newStorageFile(this.getLogDirectory(), "logmirror.ctrl");
   }

   private StorageFile getLogFileName(long var1) throws StandardException {
      return this.logStorageFactory.newStorageFile(this.getLogDirectory(), "log" + var1 + ".dat");
   }

   private CheckpointOperation findCheckpoint(long var1, FileLogger var3) throws IOException, StandardException, ClassNotFoundException {
      StreamLogScan var4 = (StreamLogScan)this.openForwardsScan(var1, (LogInstant)null);
      Loggable var5 = var3.readLogRecord(var4, 100);
      var4.close();
      return var5 instanceof CheckpointOperation ? (CheckpointOperation)var5 : null;
   }

   protected LogScan openBackwardsScan(long var1, LogInstant var3) throws IOException, StandardException {
      this.checkCorrupt();
      if (var1 == 0L) {
         return this.openBackwardsScan(var3);
      } else {
         this.flushBuffer(LogCounter.getLogFileNumber(var1), LogCounter.getLogFilePosition(var1));
         return new Scan(this, var1, var3, (byte)2);
      }
   }

   protected LogScan openBackwardsScan(LogInstant var1) throws IOException, StandardException {
      this.checkCorrupt();
      long var2;
      synchronized(this) {
         this.logOut.flushLogAccessFile();
         var2 = this.currentInstant();
      }

      return new Scan(this, var2, var1, (byte)4);
   }

   public ScanHandle openFlushedScan(DatabaseInstant var1, int var2) throws StandardException {
      return new FlushedScanHandle(this, var1, var2);
   }

   protected LogScan openForwardsScan(long var1, LogInstant var3) throws IOException, StandardException {
      this.checkCorrupt();
      if (var1 == 0L) {
         var1 = this.firstLogInstant();
      }

      if (var3 != null) {
         LogCounter var4 = (LogCounter)var3;
         this.flushBuffer(var4.getLogFileNumber(), var4.getLogFilePosition());
      } else {
         synchronized(this) {
            if (this.logOut != null) {
               this.logOut.flushLogAccessFile();
            }
         }
      }

      return new Scan(this, var1, var3, (byte)1);
   }

   protected StorageRandomAccessFile getLogFileAtBeginning(long var1) throws IOException, StandardException {
      if (this.inReplicationSlaveMode && this.allowedToReadFileNumber != -1L) {
         synchronized(this.slaveRecoveryMonitor) {
            while(this.inReplicationSlaveMode && var1 > this.allowedToReadFileNumber) {
               if (this.replicationSlaveException != null) {
                  throw this.replicationSlaveException;
               }

               try {
                  this.slaveRecoveryMonitor.wait();
               } catch (InterruptedException var6) {
                  InterruptStatus.setInterrupted();
               }
            }
         }
      }

      long var3 = LogCounter.makeLogInstantAsLong(var1, 24L);
      return this.getLogFileAtPosition(var3);
   }

   protected StorageRandomAccessFile getLogFileAtPosition(long var1) throws IOException, StandardException {
      this.checkCorrupt();
      long var3 = LogCounter.getLogFileNumber(var1);
      long var5 = LogCounter.getLogFilePosition(var1);
      StorageFile var7 = this.getLogFileName(var3);
      if (!this.privExists(var7)) {
         return null;
      } else {
         StorageRandomAccessFile var8 = null;

         try {
            var8 = this.privRandomAccessFile(var7, "r");
            if (!this.verifyLogFormat(var8, var3)) {
               var8.close();
               var8 = null;
            } else {
               var8.seek(var5);
            }

            return var8;
         } catch (IOException var12) {
            try {
               if (var8 != null) {
                  var8.close();
                  Object var13 = null;
               }
            } catch (IOException var11) {
            }

            throw var12;
         }
      }
   }

   public boolean canSupport(Properties var1) {
      String var2 = var1.getProperty("derby.__rt.storage.log");
      return var2 == null || !var2.equals("readonly");
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      String var3 = var2.getProperty("replication.slave.mode");
      if (var3 != null && var3.equals("slavemode")) {
         this.inReplicationSlaveMode = true;
         this.slaveRecoveryMonitor = new Object();
      } else if (var3 != null && var3.equals("slavepremode")) {
         this.inReplicationSlavePreMode = true;
      }

      this.dataDirectory = var2.getProperty("derby.__rt.serviceDirectory");
      this.logDevice = var2.getProperty("logDevice");
      if (this.logDevice != null) {
         String var4 = null;

         try {
            URL var5 = new URL(this.logDevice);
            var4 = var5.getFile();
         } catch (MalformedURLException var8) {
         }

         if (var4 != null) {
            this.logDevice = var4;
         }
      }

      if (var1) {
         this.getLogStorageFactory();
         this.createLogDirectory();
      } else if (!this.restoreLogs(var2)) {
         this.getLogStorageFactory();
         if (this.logDevice != null) {
            StorageFile var10 = this.logStorageFactory.newStorageFile("log");
            if (!this.privExists(var10)) {
               throw StandardException.newException("XSLAB.D", new Object[]{var10.getPath()});
            }
         }
      }

      this.logBufferSize = PropertyUtil.getSystemInt("derby.storage.logBufferSize", 8192, 134217728, 32768);
      this.jbmsVersion = getMonitor().getEngineVersion();
      String var11 = var2.getProperty("derby.storage.logArchiveMode");
      this.logArchived = Boolean.valueOf(var11);
      this.getLogFactoryProperties((PersistentSet)null);
      if (this.logStorageFactory.supportsWriteSync()) {
         this.isWriteSynced = !PropertyUtil.getSystemBoolean("derby.storage.fileSyncTransactionLog");
      } else {
         this.isWriteSynced = false;
      }

      if ("test".equalsIgnoreCase(PropertyUtil.getSystemProperty("derby.system.durability"))) {
         this.logNotSynced = true;
         this.isWriteSynced = false;
      }

      boolean var12 = var1;
      this.checkpointInstant = 0L;

      try {
         StorageFile var6 = this.getControlFileName();
         if (!var12) {
            if (this.privExists(var6)) {
               this.checkpointInstant = this.readControlFile(var6, var2);
               if (wasDBInDurabilityTestModeNoSync) {
                  Monitor.logMessage(MessageService.getTextMessage("L020", new Object[]{"derby.system.durability", "test"}));
               }

               if (this.checkpointInstant == 0L && this.privExists(this.getMirrorControlFileName())) {
                  this.checkpointInstant = this.readControlFile(this.getMirrorControlFileName(), var2);
               }
            } else if (this.logDevice != null) {
               throw StandardException.newException("XSLAB.D", new Object[]{var6.getPath()});
            }

            if (this.checkpointInstant != 0L) {
               this.logFileNumber = LogCounter.getLogFileNumber(this.checkpointInstant);
            } else {
               this.logFileNumber = 1L;
            }

            StorageFile var7 = this.getLogFileName(this.logFileNumber);
            if (!this.privExists(var7)) {
               if (this.logDevice != null) {
                  throw StandardException.newException("XSLAB.D", new Object[]{var6.getPath()});
               }

               this.logErrMsg(MessageService.getTextMessage("L018", new Object[]{var7.getPath()}));
               var12 = true;
            } else if (!this.verifyLogFormat(var7, this.logFileNumber)) {
               Monitor.logTextMessage("L008", var7);
               if (!this.privDelete(var7) && this.logFileNumber == 1L) {
                  this.logErrMsgForDurabilityTestModeNoSync();
                  throw StandardException.newException("XSLAC.D", new Object[]{this.dataDirectory});
               }

               var12 = true;
            }
         }

         if (var12) {
            if (this.writeControlFile(var6, 0L)) {
               this.firstLogFileNumber = 1L;
               this.logFileNumber = 1L;
               StorageFile var13 = this.getLogFileName(this.logFileNumber);
               if (this.privExists(var13)) {
                  Monitor.logTextMessage("L009", var13);
                  if (!this.privDelete(var13)) {
                     this.logErrMsgForDurabilityTestModeNoSync();
                     throw StandardException.newException("XSLAC.D", new Object[]{this.dataDirectory});
                  }
               }

               this.firstLog = this.privRandomAccessFile(var13, "rw");
               if (!this.initLogFile(this.firstLog, this.logFileNumber, 0L)) {
                  throw StandardException.newException("XSLAQ.D", new Object[]{var13.getPath()});
               }

               this.setEndPosition(this.firstLog.getFilePointer());
               this.lastFlush = this.firstLog.getFilePointer();
               if (this.isWriteSynced) {
                  this.preAllocateNewLogFile(this.firstLog);
                  this.firstLog.close();
                  this.firstLog = this.openLogFileInWriteMode(var13);
                  this.firstLog.seek(this.endPosition);
               }
            } else {
               Monitor.logTextMessage("L022");
               Monitor.logThrowable(new Exception("Error writing control file"));
               this.ReadOnlyDB = true;
               this.logOut = null;
               this.firstLog = null;
            }

            this.recoveryNeeded = false;
         } else {
            this.recoveryNeeded = true;
         }
      } catch (IOException var9) {
         throw Monitor.exceptionStartingModule(var9);
      }

      if (!this.checkVersion(10, 1)) {
         this.maxLogFileNumber = 4194303L;
      }

      this.bootTimeLogFileNumber = this.logFileNumber;
   }

   private void getLogStorageFactory() throws StandardException {
      if (this.logDevice == null) {
         DataFactory var1 = (DataFactory)findServiceModule(this, "org.apache.derby.iapi.store.raw.data.DataFactory");
         this.logStorageFactory = (WritableStorageFactory)var1.getStorageFactory();
      } else {
         try {
            PersistentService var3 = getMonitor().getServiceType(this);
            this.logStorageFactory = (WritableStorageFactory)var3.getStorageFactoryInstance(false, this.logDevice, (String)null, (String)null);
         } catch (IOException var2) {
            throw StandardException.newException("XSLAB.D", var2, new Object[]{this.logDevice});
         }
      }

   }

   public void stop() {
      if (this.checkpointDaemon != null) {
         this.checkpointDaemon.unsubscribe(this.myClientNumber);
         this.checkpointDaemon.stop();
      }

      synchronized(this) {
         this.stopped = true;
         if (this.logOut != null) {
            try {
               this.logOut.flushLogAccessFile();
               this.logOut.close();
            } catch (IOException var4) {
            } catch (StandardException var5) {
            }

            this.logOut = null;
         }
      }

      if (this.corrupt == null && !this.logArchived() && !this.keepAllLogs && !this.ReadOnlyDB) {
         this.deleteObsoleteLogfiles();
      }

      if (this.logDevice != null) {
         this.logStorageFactory.shutdown();
      }

      this.logStorageFactory = null;
   }

   private void deleteObsoleteLogfiles() {
      long var2 = this.getFirstLogNeeded(this.currentCheckpoint);
      if (var2 != -1L) {
         if (this.backupInProgress) {
            long var4 = this.logFileToBackup;
            if (var4 < var2) {
               var2 = var4;
            }
         }

         StorageFile var1;
         try {
            var1 = this.getLogDirectory();
         } catch (StandardException var9) {
            return;
         }

         String[] var10 = this.privList(var1);
         if (var10 != null) {
            Object var5 = null;

            for(int var8 = 0; var8 < var10.length; ++var8) {
               if (var10[var8].startsWith("log") && var10[var8].endsWith(".dat")) {
                  long var6 = Long.parseLong(var10[var8].substring(3, var10[var8].length() - 4));
                  if (var6 < var2) {
                     StorageFile var11 = this.logStorageFactory.newStorageFile(var1, var10[var8]);
                     if (this.privDelete(var11)) {
                     }
                  }
               }
            }
         }

      }
   }

   public boolean serviceASAP() {
      return false;
   }

   public boolean serviceImmediately() {
      return false;
   }

   public void getLogFactoryProperties(PersistentSet var1) throws StandardException {
      String var2;
      String var3;
      if (var1 == null) {
         var2 = PropertyUtil.getSystemProperty("derby.storage.logSwitchInterval");
         var3 = PropertyUtil.getSystemProperty("derby.storage.checkpointInterval");
      } else {
         var2 = PropertyUtil.getServiceProperty(var1, "derby.storage.logSwitchInterval");
         var3 = PropertyUtil.getServiceProperty(var1, "derby.storage.checkpointInterval");
      }

      if (var2 != null) {
         this.logSwitchInterval = Integer.parseInt(var2);
         if (this.logSwitchInterval < 100000) {
            this.logSwitchInterval = 100000;
         } else if (this.logSwitchInterval > 134217728) {
            this.logSwitchInterval = 134217728;
         }
      }

      if (var3 != null) {
         this.checkpointInterval = Integer.parseInt(var3);
         if (this.checkpointInterval < 100000) {
            this.checkpointInterval = 100000;
         } else if (this.checkpointInterval > 134217728) {
            this.checkpointInterval = 134217728;
         }
      }

   }

   public int performWork(ContextManager var1) {
      synchronized(this) {
         if (this.corrupt != null) {
            return 1;
         }
      }

      AccessFactory var2 = (AccessFactory)getServiceModule(this, "org.apache.derby.iapi.store.access.AccessFactory");

      try {
         if (var2 != null) {
            TransactionController var3 = null;

            try {
               var3 = var2.getAndNameTransaction(var1, "SystemTransaction");
               this.getLogFactoryProperties(var3);
            } finally {
               if (var3 != null) {
                  var3.commit();
               }

            }
         }

         this.rawStoreFactory.checkpoint();
      } catch (StandardException var10) {
         Monitor.logTextMessage("L011");
         this.logErrMsg((Throwable)var10);
      } catch (ShutdownException var11) {
      }

      this.checkpointDaemonCalled = false;
      return 1;
   }

   public long appendLogRecord(byte[] var1, int var2, int var3, byte[] var4, int var5, int var6) throws StandardException {
      if (this.inReplicationSlavePreMode) {
         return LogCounter.makeLogInstantAsLong(this.logFileNumber, this.endPosition);
      } else {
         boolean var9 = false;
         if (this.ReadOnlyDB) {
            throw StandardException.newException("XSLAH.D", new Object[0]);
         } else if (var3 <= 0) {
            throw StandardException.newException("XSLB6.S", new Object[0]);
         } else {
            try {
               synchronized(this) {
                  if (this.corrupt != null) {
                     throw StandardException.newException("XSLAA.D", this.corrupt, new Object[0]);
                  } else if (this.logOut == null) {
                     throw StandardException.newException("XSLAJ.D", new Object[0]);
                  } else {
                     int var11 = this.logOut.getChecksumLogRecordSize();
                     if (this.endPosition + 16L + (long)var3 + 4L + (long)var11 >= 268435455L) {
                        this.switchLogFile();
                        if (this.endPosition + 16L + (long)var3 + 4L + (long)var11 >= 268435455L) {
                           throw StandardException.newException("XSLAL.D", new Object[]{this.logFileNumber, this.endPosition, var3, 268435455L});
                        }
                     }

                     this.setEndPosition(this.endPosition + this.logOut.reserveSpaceForChecksum(var3, this.logFileNumber, this.endPosition));
                     long var7 = LogCounter.makeLogInstantAsLong(this.logFileNumber, this.endPosition);
                     this.logOut.writeLogRecord(var3, var7, var1, var2, var4, var5, var6);
                     if (var6 != 0) {
                     }

                     this.setEndPosition(this.endPosition + (long)(var3 + 16));
                     return var7;
                  }
               }
            } catch (IOException var14) {
               throw this.markCorrupt(StandardException.newException("XSLA4.D", var14, new Object[0]));
            }
         }
      }
   }

   protected synchronized long currentInstant() {
      return LogCounter.makeLogInstantAsLong(this.logFileNumber, this.endPosition);
   }

   protected synchronized long endPosition() {
      return this.endPosition;
   }

   private synchronized long getLogFileNumber() {
      return this.logFileNumber;
   }

   private synchronized long firstLogInstant() {
      return LogCounter.makeLogInstantAsLong(this.firstLogFileNumber, 24L);
   }

   protected void flush(long var1, long var3) throws StandardException {
      long var5 = 0L;
      synchronized(this) {
         boolean var8;
         try {
            do {
               if (this.corrupt != null) {
                  throw StandardException.newException("XSLAA.D", this.corrupt, new Object[0]);
               }

               while(this.isFrozen) {
                  try {
                     this.wait();
                  } catch (InterruptedException var37) {
                     InterruptStatus.setInterrupted();
                  }
               }

               if (var3 == 0L || var1 < this.logFileNumber || var3 < this.lastFlush) {
                  return;
               }

               if (this.recoveryNeeded && this.inRedo && !this.inReplicationSlaveMode) {
                  return;
               }

               if (this.logBeingFlushed) {
                  var8 = true;

                  try {
                     this.wait();
                  } catch (InterruptedException var38) {
                     InterruptStatus.setInterrupted();
                  }
               } else {
                  var8 = false;
                  if (!this.isWriteSynced) {
                     this.logOut.flushLogAccessFile();
                  } else {
                     this.logOut.switchLogBuffer();
                  }

                  var5 = this.endPosition;
                  this.logBeingFlushed = true;
                  if (this.inReplicationMasterMode) {
                     this.masterFactory.flushedTo(LogCounter.makeLogInstantAsLong(var1, var3));
                  }
               }
            } while(var8);
         } catch (IOException var39) {
            throw this.markCorrupt(StandardException.newException("XSLA0.D", var39, new Object[]{this.getLogFileName(this.logFileNumber).getPath()}));
         } catch (NullPointerException var40) {
            throw var40;
         }
      }

      boolean var7 = false;

      try {
         if (this.isWriteSynced) {
            this.logOut.flushDirtyBuffers();
         } else if (!this.logNotSynced) {
            this.logOut.syncLogAccessFile();
         }

         var7 = true;
      } catch (SyncFailedException var33) {
         throw this.markCorrupt(StandardException.newException("XSLA0.D", var33, new Object[]{this.getLogFileName(this.logFileNumber).getPath()}));
      } catch (IOException var34) {
         throw this.markCorrupt(StandardException.newException("XSLA0.D", var34, new Object[]{this.getLogFileName(this.logFileNumber).getPath()}));
      } catch (NullPointerException var35) {
         throw var35;
      } finally {
         synchronized(this) {
            this.logBeingFlushed = false;
            if (var7) {
               this.lastFlush = var5;
            }

            this.notifyAll();
         }
      }

      if (this.logWrittenFromLastCheckPoint + var5 > (long)this.checkpointInterval && this.checkpointDaemon != null && !this.checkpointDaemonCalled && !this.inLogSwitch) {
         synchronized(this) {
            if (this.logWrittenFromLastCheckPoint + var5 > (long)this.checkpointInterval && this.checkpointDaemon != null && !this.checkpointDaemonCalled && !this.inLogSwitch) {
               this.checkpointDaemonCalled = true;
               this.checkpointDaemon.serviceNow(this.myClientNumber);
            }
         }
      } else if (var5 > (long)this.logSwitchInterval && !this.checkpointDaemonCalled && !this.inLogSwitch) {
         synchronized(this) {
            if (var5 > (long)this.logSwitchInterval && !this.checkpointDaemonCalled && !this.inLogSwitch) {
               this.inLogSwitch = true;
               this.switchLogFile();
            }
         }
      }

   }

   private void syncFile(StorageRandomAccessFile var1) throws StandardException {
      int var2 = 0;

      while(true) {
         try {
            var1.sync();
            return;
         } catch (IOException var6) {
            ++var2;

            try {
               Thread.sleep(200L);
            } catch (InterruptedException var5) {
               InterruptStatus.setInterrupted();
            }

            if (var2 > 20) {
               throw StandardException.newException("XSLA4.D", var6, new Object[0]);
            }
         }
      }
   }

   public LogScan openForwardsFlushedScan(LogInstant var1) throws StandardException {
      this.checkCorrupt();
      return new FlushedScan(this, ((LogCounter)var1).getValueAsLong());
   }

   public LogScan openForwardsScan(LogInstant var1, LogInstant var2) throws StandardException {
      try {
         long var3;
         if (var1 == null) {
            var3 = 0L;
         } else {
            var3 = ((LogCounter)var1).getValueAsLong();
         }

         return this.openForwardsScan(var3, var2);
      } catch (IOException var5) {
         throw this.markCorrupt(StandardException.newException("XSLA2.D", var5, new Object[0]));
      }
   }

   public final boolean databaseEncrypted() {
      return this.databaseEncrypted;
   }

   public void setDatabaseEncrypted(boolean var1, boolean var2) throws StandardException {
      if (var2) {
         this.flushAll();
      }

      this.databaseEncrypted = var1;
   }

   public void startNewLogFile() throws StandardException {
      this.switchLogFile();
   }

   public boolean isCheckpointInLastLogFile() throws StandardException {
      long var1 = LogCounter.getLogFileNumber(this.checkpointInstant) + 1L;
      StorageFile var3 = this.getLogFileName(var1);
      return !this.privExists(var3);
   }

   public void deleteLogFileAfterCheckpointLogFile() throws StandardException {
      long var1 = LogCounter.getLogFileNumber(this.checkpointInstant) + 1L;
      StorageFile var3 = this.getLogFileName(var1);
      if (this.privExists(var3) && !this.privDelete(var3)) {
         throw StandardException.newException("XBM0R.D", new Object[]{var3});
      }
   }

   public int encrypt(byte[] var1, int var2, int var3, byte[] var4, int var5) throws StandardException {
      return this.rawStoreFactory.encrypt(var1, var2, var3, var4, var5, false);
   }

   public int decrypt(byte[] var1, int var2, int var3, byte[] var4, int var5) throws StandardException {
      return this.rawStoreFactory.decrypt(var1, var2, var3, var4, var5);
   }

   public int getEncryptionBlockSize() {
      return this.rawStoreFactory.getEncryptionBlockSize();
   }

   public int getEncryptedDataLength(int var1) {
      return var1 % this.getEncryptionBlockSize() != 0 ? var1 + this.getEncryptionBlockSize() - var1 % this.getEncryptionBlockSize() : var1;
   }

   public synchronized LogInstant getFirstUnflushedInstant() {
      return new LogCounter(this.logFileNumber, this.lastFlush);
   }

   public synchronized long getFirstUnflushedInstantAsLong() {
      return LogCounter.makeLogInstantAsLong(this.logFileNumber, this.lastFlush);
   }

   public void freezePersistentStore() throws StandardException {
      synchronized(this) {
         this.isFrozen = true;
      }
   }

   public void unfreezePersistentStore() throws StandardException {
      synchronized(this) {
         this.isFrozen = false;
         this.notifyAll();
      }
   }

   public boolean logArchived() {
      return this.logArchived;
   }

   boolean checkVersion(int var1, int var2) {
      if (this.onDiskMajorVersion > var1) {
         return true;
      } else {
         return this.onDiskMajorVersion == var1 && this.onDiskMinorVersion >= var2;
      }
   }

   public boolean checkVersion(int var1, int var2, String var3) throws StandardException {
      boolean var4 = this.checkVersion(var1, var2);
      if (!var4 && var3 != null) {
         throw StandardException.newException("XCL47.S", new Object[]{var3, ProductVersionHolder.simpleVersionString(this.onDiskMajorVersion, this.onDiskMinorVersion, this.onDiskBeta), ProductVersionHolder.simpleVersionString(var1, var2, false)});
      } else {
         return var4;
      }
   }

   protected void logErrMsg(String var1) {
      this.logErrMsgForDurabilityTestModeNoSync();
      Monitor.logTextMessage("L001");
      Monitor.logMessage(var1);
      Monitor.logTextMessage("L002");
   }

   protected void logErrMsg(Throwable var1) {
      this.logErrMsgForDurabilityTestModeNoSync();
      if (this.corrupt != null) {
         Monitor.logTextMessage("L003");
         this.printErrorStack(this.corrupt);
         Monitor.logTextMessage("L004");
      }

      if (var1 != this.corrupt) {
         Monitor.logTextMessage("L005");
         this.printErrorStack(var1);
         Monitor.logTextMessage("L006");
      }

   }

   private void logErrMsgForDurabilityTestModeNoSync() {
      if (this.logNotSynced || wasDBInDurabilityTestModeNoSync) {
         Monitor.logTextMessage("L021", "derby.system.durability", "test");
      }

   }

   private void printErrorStack(Throwable var1) {
      ErrorStringBuilder var2 = new ErrorStringBuilder(Monitor.getStream().getHeader());
      var2.stackTrace(var1);
      Monitor.logMessage(var2.get().toString());
      var2.reset();
   }

   private long logtest_appendPartialLogRecord(byte[] var1, int var2, int var3, byte[] var4, int var5, int var6) throws StandardException {
      return 0L;
   }

   protected void testLogFull() throws IOException {
   }

   public StorageRandomAccessFile getLogFileToSimulateCorruption(long var1) throws IOException, StandardException {
      return null;
   }

   public boolean inReplicationMasterMode() {
      return this.inReplicationMasterMode;
   }

   public void enableLogArchiveMode() throws StandardException {
      if (!this.logArchived) {
         this.logArchived = true;
         AccessFactory var1 = (AccessFactory)getServiceModule(this, "org.apache.derby.iapi.store.access.AccessFactory");
         if (var1 != null) {
            TransactionController var2 = null;
            var2 = var1.getTransaction(getContextService().getCurrentContextManager());
            var2.setProperty("derby.storage.logArchiveMode", "true", true);
         }
      }

   }

   public void disableLogArchiveMode() throws StandardException {
      AccessFactory var1 = (AccessFactory)getServiceModule(this, "org.apache.derby.iapi.store.access.AccessFactory");
      if (var1 != null) {
         TransactionController var2 = null;
         var2 = var1.getTransaction(getContextService().getCurrentContextManager());
         var2.setProperty("derby.storage.logArchiveMode", "false", true);
      }

      this.logArchived = false;
   }

   public void deleteOnlineArchivedLogFiles() {
      this.deleteObsoleteLogfiles();
   }

   public void startLogBackup(File var1) throws StandardException {
      synchronized(this) {
         while(this.inCheckpoint) {
            try {
               this.wait();
            } catch (InterruptedException var6) {
               InterruptStatus.setInterrupted();
            }
         }

         this.backupInProgress = true;
         StorageFile var3 = this.getControlFileName();
         File var4 = new File(var1, var3.getName());
         if (!this.privCopyFile(var3, var4)) {
            throw StandardException.newException("XSRS5.S", new Object[]{var3, var4});
         }

         var3 = this.getMirrorControlFileName();
         var4 = new File(var1, var3.getName());
         if (!this.privCopyFile(var3, var4)) {
            throw StandardException.newException("XSRS5.S", new Object[]{var3, var4});
         }

         this.logFileToBackup = this.getFirstLogNeeded(this.currentCheckpoint);
      }

      this.backupLogFiles(var1, this.getLogFileNumber() - 1L);
   }

   private void backupLogFiles(File var1, long var2) throws StandardException {
      while(this.logFileToBackup <= var2) {
         StorageFile var4 = this.getLogFileName(this.logFileToBackup);
         File var5 = new File(var1, var4.getName());
         if (!this.privCopyFile(var4, var5)) {
            throw StandardException.newException("XSRS5.S", new Object[]{var4, var5});
         }

         ++this.logFileToBackup;
      }

   }

   public void endLogBackup(File var1) throws StandardException {
      this.flush(this.logFileNumber, this.endPosition);
      long var2;
      if (this.logArchived) {
         this.switchLogFile();
         var2 = this.getLogFileNumber() - 1L;
      } else {
         var2 = this.getLogFileNumber();
      }

      this.backupLogFiles(var1, var2);
      this.backupInProgress = false;
   }

   public void abortLogBackup() {
      this.backupInProgress = false;
   }

   public boolean inRFR() {
      if (this.recoveryNeeded) {
         boolean var1 = false;

         try {
            var1 = !this.privCanWrite(this.getControlFileName());
         } catch (StandardException var3) {
         }

         var1 = var1 || this.dataFactory != null && this.dataFactory.isReadOnly();
         return !var1;
      } else {
         return false;
      }
   }

   public void checkpointInRFR(LogInstant var1, long var2, long var4, DataFactory var6) throws StandardException {
      var6.checkpoint();

      try {
         if (!this.writeControlFile(this.getControlFileName(), ((LogCounter)var1).getValueAsLong())) {
            throw StandardException.newException("XSLAE.D", new Object[]{this.getControlFileName()});
         }
      } catch (IOException var8) {
         throw this.markCorrupt(StandardException.newException("XSLA2.D", var8, new Object[0]));
      }

      var6.removeDroppedContainerFileStubs(new LogCounter(var2));
      if (this.inReplicationSlaveMode) {
         this.truncateLog(LogCounter.getLogFileNumber(var4));
      }

   }

   public void startReplicationMasterRole(MasterFactory var1) throws StandardException {
      this.masterFactory = var1;
      synchronized(this) {
         this.inReplicationMasterMode = true;
         this.logOut.setReplicationMasterRole(var1);
      }
   }

   public void stopReplicationMasterRole() {
      this.inReplicationMasterMode = false;
      this.masterFactory = null;
      if (this.logOut != null) {
         this.logOut.stopReplicationMasterRole();
      }

   }

   public void stopReplicationSlaveRole() throws StandardException {
      if (!this.stopped) {
         this.flushAll();
      }

      this.replicationSlaveException = StandardException.newException("08006.D", new Object[0]);
      synchronized(this.slaveRecoveryMonitor) {
         this.slaveRecoveryMonitor.notify();
      }
   }

   protected void checkForReplication(LogAccessFile var1) {
      if (this.inReplicationMasterMode) {
         var1.setReplicationMasterRole(this.masterFactory);
      } else if (this.inReplicationSlaveMode) {
         var1.setReplicationSlaveRole();
      }

   }

   public void initializeReplicationSlaveRole() throws StandardException {
      try {
         while(this.getLogFileAtBeginning(this.logFileNumber + 1L) != null) {
            ++this.logFileNumber;
         }

         long var1 = LogCounter.makeLogInstantAsLong(this.logFileNumber, 24L);
         long var3 = 24L;
         StreamLogScan var5 = (StreamLogScan)this.openForwardsScan(var1, (LogInstant)null);

         for(ArrayInputStream var6 = new ArrayInputStream(); var5.getNextRecord(var6, (TransactionId)null, 0) != null; var3 = var5.getLogRecordEnd()) {
         }

         this.setEndPosition(LogCounter.getLogFilePosition(var3));
         StorageRandomAccessFile var7 = null;
         if (this.isWriteSynced) {
            var7 = this.openLogFileInWriteMode(this.getLogFileName(this.logFileNumber));
         } else {
            var7 = this.privRandomAccessFile(this.getLogFileName(this.logFileNumber), "rw");
         }

         this.logOut = new LogAccessFile(this, var7, this.logBufferSize);
         this.lastFlush = this.endPosition;
         var7.seek(this.endPosition);
      } catch (IOException var8) {
         throw StandardException.newException("XRE03", var8, new Object[0]);
      }
   }

   public void failoverSlave() {
      if (!this.stopped) {
         try {
            this.flushAll();
         } catch (StandardException var4) {
         }
      }

      this.inReplicationSlaveMode = false;
      synchronized(this.slaveRecoveryMonitor) {
         this.slaveRecoveryMonitor.notify();
      }
   }

   private boolean restoreLogs(Properties var1) throws StandardException {
      Object var2 = null;
      boolean var3 = false;
      boolean var4 = false;
      String var11 = var1.getProperty("createFrom");
      if (var11 != null) {
         var3 = true;
      } else {
         var11 = var1.getProperty("restoreFrom");
         if (var11 != null) {
            var4 = true;
         } else {
            var11 = var1.getProperty("rollForwardRecoveryFrom");
         }
      }

      if (var11 == null) {
         return false;
      } else {
         if (!var3 && this.logDevice == null) {
            this.logDevice = var1.getProperty("derby.storage.logDeviceWhenBackedUp");
         }

         this.getLogStorageFactory();
         StorageFile var5 = this.logStorageFactory.newStorageFile("log");
         if (var4 && this.logDevice != null && !this.privRemoveDirectory(var5) && !this.privDelete(var5)) {
            throw StandardException.newException("XSDG7.D", new Object[]{this.getLogDirPath(var5)});
         } else {
            if (var3 || var4) {
               this.createLogDirectory();
            }

            File var6 = new File(var11, "log");
            String[] var7 = this.privList(var6);
            if (var7 != null) {
               for(int var8 = 0; var8 < var7.length; ++var8) {
                  File var9 = new File(var6, var7[var8]);
                  StorageFile var10 = this.logStorageFactory.newStorageFile(var5, var7[var8]);
                  if (!this.privCopyFile(var9, var10)) {
                     throw StandardException.newException("XSLAR.D", new Object[]{var9, var10});
                  }
               }

               this.logSwitchRequired = true;
               return true;
            } else {
               throw StandardException.newException("XSLAS.D", new Object[]{var6});
            }
         }
      }
   }

   private void preAllocateNewLogFile(StorageRandomAccessFile var1) throws IOException, StandardException {
      int var2 = this.logSwitchInterval - 24;
      int var3 = this.logBufferSize * 2;
      byte[] var4 = new byte[var3];
      int var5 = var2 / var3;
      int var6 = var2 % var3;

      try {
         while(var5-- > 0) {
            var1.write(var4);
         }

         if (var6 != 0) {
            var1.write(var4, 0, var6);
         }

         this.syncFile(var1);
      } catch (IOException var8) {
      }

   }

   private StorageRandomAccessFile openLogFileInWriteMode(StorageFile var1) throws IOException {
      if (!this.jvmSyncErrorChecked && this.checkJvmSyncError(var1)) {
         this.isWriteSynced = false;
         return this.privRandomAccessFile(var1, "rw");
      } else {
         StorageRandomAccessFile var2 = this.privRandomAccessFile(var1, "rwd");
         return var2;
      }
   }

   private String getLogDirPath(StorageFile var1) {
      if (this.logDevice == null) {
         return var1.toString();
      } else {
         String var10000 = this.logDevice;
         return var10000 + this.logStorageFactory.getSeparator() + var1.toString();
      }
   }

   private boolean checkJvmSyncError(StorageFile var1) throws IOException {
      boolean var2 = false;
      StorageRandomAccessFile var3 = this.privRandomAccessFile(var1, "rw");
      var3.close();

      try {
         var3 = this.privRandomAccessFile(var1, "rws");
         var3.close();
      } catch (FileNotFoundException var5) {
         this.logErrMsg("LogToFile.checkJvmSyncError: Your JVM seems to have a problem with implicit syncing of log files. Will use explicit syncing instead.");
         var2 = true;
      }

      this.jvmSyncErrorChecked = true;
      return var2;
   }

   protected boolean privExists(StorageFile var1) {
      return this.runBooleanAction(0, var1);
   }

   protected boolean privDelete(StorageFile var1) {
      return this.runBooleanAction(1, var1);
   }

   private synchronized StorageRandomAccessFile privRandomAccessFile(StorageFile var1, String var2) throws IOException {
      this.action = 2;
      this.activeFile = var1;
      this.activePerms = var2;

      try {
         return (StorageRandomAccessFile)this.run();
      } catch (StandardException var4) {
         throw this.wrapSE(var4);
      }
   }

   private IOException wrapSE(Exception var1) {
      return new IOException(var1.getMessage(), var1);
   }

   private synchronized OutputStreamWriter privGetOutputStreamWriter(StorageFile var1) throws IOException {
      this.action = 10;
      this.activeFile = var1;

      try {
         return (OutputStreamWriter)this.run();
      } catch (StandardException var3) {
         throw this.wrapSE(var3);
      }
   }

   protected boolean privCanWrite(StorageFile var1) {
      return this.runBooleanAction(3, var1);
   }

   protected boolean privMkdirs(StorageFile var1) throws IOException {
      this.action = 4;
      this.activeFile = var1;

      try {
         return (Boolean)this.run();
      } catch (StandardException var3) {
         throw this.wrapSE(var3);
      }
   }

   private synchronized String[] privList(File var1) {
      this.action = 8;
      this.toFile = var1;

      try {
         return (String[])this.run();
      } catch (Exception var3) {
         return null;
      }
   }

   private synchronized String[] privList(StorageFile var1) {
      this.action = 5;
      this.activeFile = var1;

      try {
         return (String[])this.run();
      } catch (Exception var3) {
         return null;
      }
   }

   private synchronized boolean privCopyFile(StorageFile var1, File var2) throws StandardException {
      this.action = 6;
      this.activeFile = var1;
      this.toFile = var2;

      try {
         return (Boolean)this.run();
      } catch (Exception var4) {
         if (var4 instanceof StandardException) {
            throw (StandardException)var4.getCause();
         } else {
            return false;
         }
      }
   }

   private synchronized boolean privCopyFile(File var1, StorageFile var2) {
      this.action = 9;
      this.activeFile = var2;
      this.toFile = var1;

      try {
         return (Boolean)this.run();
      } catch (Exception var4) {
         return false;
      }
   }

   private boolean privRemoveDirectory(StorageFile var1) {
      return this.runBooleanAction(7, var1);
   }

   private synchronized boolean runBooleanAction(int var1, StorageFile var2) {
      this.action = var1;
      this.activeFile = var2;

      try {
         return (Boolean)this.run();
      } catch (Exception var4) {
         return false;
      }
   }

   private void setEndPosition(long var1) {
      this.endPosition = var1;
   }

   public final Object run() throws IOException, StandardException {
      switch (this.action) {
         case 0:
            return this.activeFile.exists();
         case 1:
            return this.activeFile.delete();
         case 2:
            boolean var1 = this.activeFile.exists();
            StorageRandomAccessFile var2 = this.activeFile.getRandomAccessFile(this.activePerms);
            if (!var1) {
               this.activeFile.limitAccessToOwner();
            }

            return var2;
         case 3:
            return this.activeFile.canWrite();
         case 4:
            boolean var3 = this.activeFile.mkdirs();
            if (var3) {
               this.activeFile.limitAccessToOwner();
            }

            return var3;
         case 5:
            return this.activeFile.list();
         case 6:
            return FileUtil.copyFile((StorageFactory)this.logStorageFactory, (StorageFile)this.activeFile, (File)this.toFile);
         case 7:
            return !this.activeFile.exists() || this.activeFile.deleteAll();
         case 8:
            return this.toFile.list();
         case 9:
            return FileUtil.copyFile(this.logStorageFactory, this.toFile, this.activeFile);
         case 10:
            return new OutputStreamWriter(this.activeFile.getOutputStream(), "UTF8");
         default:
            return null;
      }
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      return var1.toString();
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }

   private static Object getServiceModule(Object var0, String var1) {
      return Monitor.getServiceModule(var0, var1);
   }

   private static boolean isFullUpgrade(Properties var0, String var1) throws StandardException {
      return Monitor.isFullUpgrade(var0, var1);
   }
}
