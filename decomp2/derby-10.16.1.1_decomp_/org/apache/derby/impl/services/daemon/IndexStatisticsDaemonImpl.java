package org.apache.derby.impl.services.daemon;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.StatisticsImpl;
import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.daemon.IndexStatisticsDaemon;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.StatisticsDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.GroupFetchScanController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.shared.common.error.ShutdownException;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

public class IndexStatisticsDaemonImpl implements IndexStatisticsDaemon, Runnable {
   private static final boolean AS_BACKGROUND_TASK = true;
   private static final boolean AS_EXPLICIT_TASK = false;
   private static final int MAX_QUEUE_LENGTH = PropertyUtil.getSystemInt("derby.storage.indexStats.debug.queueSize", 20);
   private final HeaderPrintWriter logStream;
   private final boolean doLog;
   private final boolean doTrace;
   private final boolean traceToDerbyLog;
   private final boolean traceToStdOut;
   private boolean daemonDisabled;
   private final ContextManager ctxMgr;
   public final boolean skipDisposableStats;
   private LanguageConnectionContext daemonLCC;
   private final Database db;
   private final String dbOwner;
   private final String databaseName;
   private final ArrayList queue;
   private Thread runningThread;
   private int errorsConsecutive;
   private long errorsUnknown;
   private long errorsKnown;
   private long wuProcessed;
   private long wuScheduled;
   private long wuRejectedDup;
   private long wuRejectedFQ;
   private long wuRejectedOther;
   private final long timeOfCreation;
   private long runTime;
   private final StringBuffer tsb;

   public IndexStatisticsDaemonImpl(HeaderPrintWriter var1, boolean var2, String var3, Database var4, String var5, String var6) {
      this.queue = new ArrayList(MAX_QUEUE_LENGTH);
      this.tsb = new StringBuffer();
      if (var1 == null) {
         throw new IllegalArgumentException("log stream cannot be null");
      } else {
         this.logStream = var1;
         this.doLog = var2;
         this.traceToDerbyLog = var3.equalsIgnoreCase("both") || var3.equalsIgnoreCase("log");
         this.traceToStdOut = var3.equalsIgnoreCase("both") || var3.equalsIgnoreCase("stdout");
         this.doTrace = this.traceToDerbyLog || this.traceToStdOut;
         boolean var7 = PropertyUtil.getSystemBoolean("derby.storage.indexStats.debug.keepDisposableStats");
         this.skipDisposableStats = this.dbAtLeast10_9(var4) && !var7;
         this.db = var4;
         this.dbOwner = var5;
         this.databaseName = var6;
         this.ctxMgr = getContextService().newContextManager();
         this.timeOfCreation = System.currentTimeMillis();
         this.trace(0, "created{log=" + var2 + ", traceLog=" + this.traceToDerbyLog + ", traceOut=" + this.traceToStdOut + ", createThreshold=" + TableDescriptor.ISTATS_CREATE_THRESHOLD + ", absdiffThreshold=" + TableDescriptor.ISTATS_ABSDIFF_THRESHOLD + ", lndiffThreshold=" + TableDescriptor.ISTATS_LNDIFF_THRESHOLD + ", queueLength=" + MAX_QUEUE_LENGTH + "}) -> " + var6);
      }
   }

   private boolean dbAtLeast10_9(Database var1) {
      try {
         return var1.getDataDictionary().checkVersion(210, (String)null);
      } catch (StandardException var3) {
         return false;
      }
   }

   public void schedule(TableDescriptor var1) {
      String var2 = var1.getIndexStatsUpdateReason();
      synchronized(this.queue) {
         if (this.acceptWork(var1)) {
            this.queue.add(var1);
            ++this.wuScheduled;
            String var10003 = var2 == null ? "" : ", reason=[" + var2 + "]";
            this.log(true, var1, "update scheduled" + var10003 + " (queueSize=" + this.queue.size() + ")");
            if (this.runningThread == null) {
               this.runningThread = BasicDaemon.getMonitor().getDaemonThread(this, "index-stat-thread", false);
               this.runningThread.start();
            }
         }

      }
   }

   private boolean acceptWork(TableDescriptor var1) {
      boolean var2 = !this.daemonDisabled && this.queue.size() < MAX_QUEUE_LENGTH;
      if (var2 && !this.queue.isEmpty()) {
         String var3 = var1.getName();
         String var4 = var1.getSchemaName();

         for(int var5 = 0; var5 < this.queue.size(); ++var5) {
            TableDescriptor var6 = (TableDescriptor)this.queue.get(var5);
            if (var6.tableNameEquals(var3, var4)) {
               var2 = false;
               break;
            }
         }
      }

      if (!var2) {
         String var7 = var1.getQualifiedName() + " rejected, ";
         if (this.daemonDisabled) {
            ++this.wuRejectedOther;
            var7 = var7 + "daemon disabled";
         } else if (this.queue.size() >= MAX_QUEUE_LENGTH) {
            ++this.wuRejectedFQ;
            var7 = var7 + "queue full";
         } else {
            ++this.wuRejectedDup;
            var7 = var7 + "duplicate";
         }

         this.trace(1, var7);
      }

      return var2;
   }

   private void generateStatistics(LanguageConnectionContext var1, TableDescriptor var2) throws StandardException {
      this.trace(1, "processing " + var2.getQualifiedName());
      boolean var3 = false;

      while(true) {
         try {
            this.updateIndexStatsMinion(var1, var2, (ConglomerateDescriptor[])null, true);
            return;
         } catch (StandardException var5) {
            if (!var5.isLockTimeout() || var3) {
               throw var5;
            }

            this.trace(1, "locks unavailable, retrying");
            var3 = true;
            var1.internalRollback();
            sleep(1000L);
         }
      }
   }

   private boolean isShuttingDown() {
      synchronized(this.queue) {
         if (!this.daemonDisabled && this.daemonLCC != null) {
            return !this.daemonLCC.getDatabase().isActive();
         } else {
            return true;
         }
      }
   }

   private void updateIndexStatsMinion(LanguageConnectionContext var1, TableDescriptor var2, ConglomerateDescriptor[] var3, boolean var4) throws StandardException {
      boolean var5 = var3 == null;
      if (var3 == null) {
         var3 = var2.getConglomerateDescriptors();
      }

      long[] var6 = new long[var3.length];
      ExecIndexRow[] var7 = new ExecIndexRow[var3.length];
      TransactionController var8 = var1.getTransactionExecute();
      ConglomerateController var9 = var8.openConglomerate(var2.getHeapConglomerateId(), false, 0, 6, var4 ? 1 : 4);
      UUID[] var10 = new UUID[var3.length];

      try {
         for(int var11 = 0; var11 < var3.length; ++var11) {
            if (!var3[var11].isIndex()) {
               var6[var11] = -1L;
            } else {
               IndexRowGenerator var12 = var3[var11].getIndexDescriptor();
               if (this.skipDisposableStats && var12.isUnique() && var12.numberOfOrderedColumns() == 1) {
                  var6[var11] = -1L;
               } else {
                  var6[var11] = var3[var11].getConglomerateNumber();
                  var10[var11] = var3[var11].getUUID();
                  var7[var11] = var12.getNullIndexRow(var2.getColumnDescriptorList(), var9.newRowLocationTemplate());
               }
            }
         }
      } finally {
         var9.close();
      }

      if (var5) {
         List var33 = var2.getStatistics();
         StatisticsDescriptor[] var35 = (StatisticsDescriptor[])var33.toArray(new StatisticsDescriptor[var33.size()]);

         for(int var13 = 0; var13 < var35.length; ++var13) {
            UUID var14 = var35[var13].getReferenceID();
            boolean var15 = false;

            for(int var16 = 0; var16 < var6.length; ++var16) {
               if (var14.equals(var10[var16])) {
                  var15 = true;
                  break;
               }
            }

            if (!var15) {
               UUID var10000 = var35[var13].getUUID();
               String var40 = "dropping disposable statistics entry " + var10000 + " for index " + var35[var13].getReferenceID() + " (cols=" + var35[var13].getColumnCount() + ")";
               this.logAlways(var2, (Throwable)null, var40);
               this.trace(1, var40 + " on table " + var35[var13].getTableUUID());
               DataDictionary var17 = var1.getDataDictionary();
               if (!var1.dataDictionaryInWriteMode()) {
                  var17.startWriting(var1);
               }

               var17.dropStatisticsDescriptors(var2.getUUID(), var35[var13].getReferenceID(), var8);
               if (var4) {
                  var1.internalCommit(true);
               }
            }
         }
      }

      long[][] var34 = new long[var6.length][3];
      int var36 = 0;

      for(int var37 = 0; var37 < var6.length; ++var37) {
         if (var6[var37] != -1L) {
            if (var4 && this.isShuttingDown()) {
               break;
            }

            var34[var36][0] = var6[var37];
            var34[var36][1] = System.currentTimeMillis();
            int var38 = var7[var37].nColumns() - 1;
            long[] var39 = new long[var38];
            KeyComparator var41 = new KeyComparator(var7[var37]);
            GroupFetchScanController var42 = var8.openGroupFetchScan(var6[var37], false, 0, 6, 1, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);

            try {
               int var18 = 0;
               boolean var19 = false;

               while((var18 = var41.fetchRows(var42)) > 0) {
                  if (var4 && this.isShuttingDown()) {
                     var19 = true;
                     break;
                  }

                  for(int var20 = 0; var20 < var18; ++var20) {
                     int var21 = var41.compareWithPrevKey(var20);
                     if (var21 >= 0) {
                        for(int var22 = var21; var22 < var38; ++var22) {
                           int var10002 = var39[var22]++;
                        }
                     }
                  }
               }

               if (var19) {
                  break;
               }

               var42.setEstimatedRowCount(var41.getRowCount());
            } finally {
               var42.close();
               Object var43 = null;
            }

            var34[var36++][2] = System.currentTimeMillis();
            int var45 = 0;

            while(true) {
               try {
                  this.writeUpdatedStats(var1, var2, var10[var37], var41.getRowCount(), var39, var4);
                  break;
               } catch (StandardException var30) {
                  ++var45;
                  if (!var30.isLockTimeout() || var45 >= 3) {
                     throw var30;
                  }

                  this.trace(2, "lock timeout when writing stats, retrying");
                  sleep((long)(100 * var45));
               }
            }
         }
      }

      this.log(var4, var2, fmtScanTimes(var34));
   }

   private void writeUpdatedStats(LanguageConnectionContext var1, TableDescriptor var2, UUID var3, long var4, long[] var6, boolean var7) throws StandardException {
      TransactionController var8 = var1.getTransactionExecute();
      this.trace(1, "writing new stats (xid=" + var8.getTransactionIdString() + ")");
      UUID var9 = var2.getUUID();
      DataDictionary var10 = var1.getDataDictionary();
      UUIDFactory var11 = var10.getUUIDFactory();
      this.setHeapRowEstimate(var8, var2.getHeapConglomerateId(), var4);
      if (!var1.dataDictionaryInWriteMode()) {
         var10.startWriting(var1);
      }

      var10.dropStatisticsDescriptors(var9, var3, var8);
      boolean var12 = false;
      if (var4 == 0L) {
         this.trace(2, "empty table, no stats written");
      } else {
         for(int var13 = 0; var13 < var6.length; ++var13) {
            StatisticsDescriptor var14 = new StatisticsDescriptor(var10, var11.createUUID(), var3, var9, "I", new StatisticsImpl(var4, var6[var13]), var13 + 1);
            var10.addDescriptor(var14, (TupleDescriptor)null, 14, true, var8);
         }

         ConglomerateDescriptor var15 = var10.getConglomerateDescriptor(var3);
         this.log(var7, var2, "wrote stats for index " + (var15 == null ? "n/a" : var15.getDescriptorName()) + " (" + var3 + "): rows=" + var4 + ", card=" + cardToStr(var6));
         if (var7 && var15 == null) {
            this.log(var7, var2, "rolled back index stats because index has been dropped");
            var1.internalRollback();
         }

         var12 = var15 == null;
      }

      if (!var12) {
         this.invalidateStatements(var1, var2, var7);
      }

      if (var7) {
         var1.internalCommit(true);
      }

   }

   private void invalidateStatements(LanguageConnectionContext var1, TableDescriptor var2, boolean var3) throws StandardException {
      DataDictionary var4 = var1.getDataDictionary();
      DependencyManager var5 = var4.getDependencyManager();
      int var6 = 0;

      while(true) {
         try {
            if (!var1.dataDictionaryInWriteMode()) {
               var4.startWriting(var1);
            }

            var5.invalidateFor(var2, 40, var1);
            this.trace(1, "invalidation completed");
            return;
         } catch (StandardException var8) {
            if (!var8.isLockTimeout() || !var3 || var6 >= 3) {
               this.trace(1, "invalidation failed");
               throw var8;
            }

            ++var6;
            if (var6 > 1) {
               this.trace(2, "releasing locks");
               var1.internalRollback();
            }

            this.trace(2, "lock timeout when invalidating");
            sleep((long)(100 * (1 + var6)));
         }
      }
   }

   private void setHeapRowEstimate(TransactionController var1, long var2, long var4) throws StandardException {
      ScanController var6 = var1.openScan(var2, false, 0, 6, 1, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);

      try {
         var6.setEstimatedRowCount(var4);
      } finally {
         var6.close();
      }

   }

   public void run() {
      long var1 = System.currentTimeMillis();
      ContextService var3 = null;

      try {
         var3 = getContextService();
         var3.setCurrentContextManager(this.ctxMgr);
         this.processingLoop();
      } catch (ShutdownException var9) {
         this.trace(1, "swallowed shutdown exception: " + extractIstatInfo(var9));
         this.stop();
         this.ctxMgr.cleanupOnError(var9, this.db.isActive());
      } catch (RuntimeException var10) {
         if (!this.isShuttingDown()) {
            this.log(true, (TableDescriptor)null, var10, "runtime exception during normal operation");
            throw var10;
         }

         this.trace(1, "swallowed runtime exception during shutdown: " + extractIstatInfo(var10));
      } finally {
         if (var3 != null) {
            var3.resetCurrentContextManager(this.ctxMgr);
         }

         this.runTime += System.currentTimeMillis() - var1;
         this.trace(0, "worker thread exit");
      }

   }

   private void processingLoop() {
      if (this.daemonLCC == null) {
         try {
            this.daemonLCC = this.db.setupConnection(this.ctxMgr, this.dbOwner, (String)null, this.databaseName);
            this.daemonLCC.setIsolationLevel(1);
            this.daemonLCC.getTransactionExecute().setNoLockWait(true);
         } catch (StandardException var49) {
            this.log(true, (TableDescriptor)null, var49, "failed to initialize index statistics updater");
            return;
         }
      }

      TransactionController var1 = null;

      try {
         var1 = this.daemonLCC.getTransactionExecute();
         this.trace(0, "worker thread started (xid=" + var1.getTransactionIdString() + ")");
         Object var2 = null;
         long var3 = 0L;

         while(true) {
            TableDescriptor var58;
            synchronized(this.queue) {
               if (this.daemonDisabled) {
                  try {
                     var1.destroy();
                  } catch (ShutdownException var48) {
                  }

                  var1 = null;
                  this.daemonLCC = null;
                  this.queue.clear();
                  this.trace(1, "daemon disabled");
                  break;
               }

               if (this.queue.isEmpty()) {
                  this.trace(1, "queue empty");
                  break;
               }

               var58 = (TableDescriptor)this.queue.get(0);
            }

            try {
               var3 = System.currentTimeMillis();
               this.generateStatistics(this.daemonLCC, var58);
               ++this.wuProcessed;
               this.errorsConsecutive = 0;
               this.log(true, var58, "generation complete (" + (System.currentTimeMillis() - var3) + " ms)");
            } catch (StandardException var51) {
               ++this.errorsConsecutive;
               if (!this.handleFatalErrors(this.ctxMgr, var51)) {
                  boolean var6 = this.handleExpectedErrors(var58, var51);
                  if (!var6) {
                     this.handleUnexpectedErrors(var58, var51);
                  }

                  this.daemonLCC.internalRollback();
               }
            } finally {
               synchronized(this.queue) {
                  if (!this.queue.isEmpty()) {
                     this.queue.remove(0);
                  }
               }

               if (this.errorsConsecutive >= 50) {
                  this.log(true, (TableDescriptor)null, new IllegalStateException("degraded state"), "shutting down daemon, " + this.errorsConsecutive + " consecutive errors seen");
                  this.stop();
               }

            }
         }
      } catch (StandardException var54) {
         this.log(true, (TableDescriptor)null, var54, "thread died");
      } finally {
         synchronized(this.queue) {
            this.runningThread = null;
         }

         if (this.daemonLCC != null && !this.daemonLCC.isTransactionPristine()) {
            this.log(true, (TableDescriptor)null, "transaction not pristine - forcing rollback");

            try {
               this.daemonLCC.internalRollback();
            } catch (StandardException var46) {
               this.log(true, (TableDescriptor)null, var46, "forced rollback failed");
            }
         }

      }

   }

   public void runExplicitly(LanguageConnectionContext var1, TableDescriptor var2, ConglomerateDescriptor[] var3, String var4) throws StandardException {
      this.updateIndexStatsMinion(var1, var2, var3, false);
      String var10002 = var4 != null ? " (" + var4 + "): " : ": ";
      this.trace(0, "explicit run completed" + var10002 + var2.getQualifiedName());
   }

   public void stop() {
      Thread var1 = null;
      boolean var2 = false;
      synchronized(this.queue) {
         if (!this.daemonDisabled) {
            var2 = true;
            StringBuffer var4 = new StringBuffer(100);
            var4.append("stopping daemon, active=").append(this.runningThread != null).append(", work/age=").append(this.runTime).append('/').append(System.currentTimeMillis() - this.timeOfCreation).append(' ');
            this.appendRunStats(var4);
            this.log(true, (TableDescriptor)null, var4.toString());
            if (this.runningThread == null && this.daemonLCC != null && !this.isShuttingDown()) {
               try {
                  this.daemonLCC.getTransactionExecute().destroy();
               } catch (ShutdownException var7) {
               }

               this.daemonLCC = null;
            }

            this.daemonDisabled = true;
            var1 = this.runningThread;
            this.runningThread = null;
            this.queue.clear();
         }
      }

      if (var1 != null) {
         while(true) {
            try {
               var1.join();
               break;
            } catch (InterruptedException var8) {
               InterruptStatus.setInterrupted();
            }
         }
      }

      if (var2) {
         this.ctxMgr.cleanupOnError(StandardException.normalClose(), false);
      }

   }

   private boolean handleFatalErrors(ContextManager var1, StandardException var2) {
      boolean var3 = false;
      if ("40XD1".equals(var2.getMessageId())) {
         var3 = true;
      } else if (this.isShuttingDown() || var2.getSeverity() >= 45000) {
         this.trace(1, "swallowed exception during shutdown: " + extractIstatInfo(var2));
         var3 = true;
         var1.cleanupOnError(var2, this.db.isActive());
      }

      if (var3) {
         this.daemonLCC.getDataDictionary().disableIndexStatsRefresher();
      }

      return var3;
   }

   private boolean handleExpectedErrors(TableDescriptor var1, StandardException var2) {
      String var3 = var2.getMessageId();
      if (!"XSAI2.S".equals(var3) && !"XSCH1.S".equals(var3) && !"XSDG9.D".equals(var3) && !var2.isLockTimeout()) {
         return false;
      } else {
         ++this.errorsKnown;
         this.log(true, var1, "generation aborted (reason: " + var3 + ") {" + extractIstatInfo(var2) + "}");
         return true;
      }
   }

   private boolean handleUnexpectedErrors(TableDescriptor var1, StandardException var2) {
      ++this.errorsUnknown;
      this.log(true, var1, var2, "generation failed");
      return true;
   }

   private static void sleep(long var0) {
      try {
         Thread.sleep(var0);
      } catch (InterruptedException var3) {
         InterruptStatus.setInterrupted();
      }

   }

   private static String fmtScanTimes(long[][] var0) {
      StringBuffer var1 = new StringBuffer("scan durations (");

      for(int var2 = 0; var2 < var0.length && var0[var2][0] > 0L; ++var2) {
         var1.append('c').append(var0[var2][0]).append('=');
         if (var0[var2][2] == 0L) {
            var1.append("ABORTED,");
         } else {
            long var3 = var0[var2][2] - var0[var2][1];
            var1.append(var3).append("ms,");
         }
      }

      var1.deleteCharAt(var1.length() - 1).append(")");
      return var1.toString();
   }

   private void log(boolean var1, TableDescriptor var2, String var3) {
      this.log(var1, var2, (Throwable)null, var3);
   }

   private void log(boolean var1, TableDescriptor var2, Throwable var3, String var4) {
      if (var1 && (this.doLog || var3 != null)) {
         this.logAlways(var2, var3, var4);
      }

   }

   private void logAlways(TableDescriptor var1, Throwable var2, String var3) {
      String var10000 = var1 == null ? "" : var1.getQualifiedName() + ": ";
      String var5 = "{istat} " + var10000 + var3;
      if (var2 != null) {
         PrintWriter var4 = new PrintWriter(this.logStream.getPrintWriter(), false);
         var4.print(this.logStream.getHeader().getHeader());
         var4.println(var5);
         var2.printStackTrace(var4);
         var4.flush();
      } else {
         this.logStream.printlnWithHeader(var5);
      }

   }

   private synchronized void trace(int var1, String var2) {
      if (this.doTrace) {
         this.tsb.setLength(0);
         this.tsb.append("{istat,trace@").append(this.hashCode()).append("} ");

         for(int var3 = 0; var3 < var1; ++var3) {
            this.tsb.append("    ");
         }

         this.tsb.append(var2).append(' ');
         if (var1 == 0) {
            this.appendRunStats(this.tsb);
         }

         if (this.traceToDerbyLog && this.logStream != null) {
            this.logStream.printlnWithHeader(this.tsb.toString());
         }

         if (this.traceToStdOut) {
            System.out.println(this.tsb.toString());
         }
      }

   }

   private void appendRunStats(StringBuffer var1) {
      var1.append("[q/p/s=").append(this.queue.size()).append('/').append(this.wuProcessed).append('/').append(this.wuScheduled).append(",err:k/u/c=").append(this.errorsKnown).append('/').append(this.errorsUnknown).append('/').append(this.errorsConsecutive).append(",rej:f/d/o=").append(this.wuRejectedFQ).append('/').append(this.wuRejectedDup).append('/').append(this.wuRejectedOther).append(']');
   }

   private static String cardToStr(long[] var0) {
      if (var0.length == 1) {
         return "[" + Long.toString(var0[0]) + "]";
      } else {
         StringBuffer var1 = new StringBuffer("[");

         for(int var2 = 0; var2 < var0.length; ++var2) {
            var1.append(var0[var2]).append(',');
         }

         var1.deleteCharAt(var1.length() - 1).append(']');
         return var1.toString();
      }
   }

   private static String extractIstatInfo(Throwable var0) {
      String var1 = IndexStatisticsDaemonImpl.class.getName();
      StackTraceElement[] var2 = var0.getStackTrace();
      String var3 = "<no stacktrace>";
      String var4 = "";

      for(int var5 = 0; var5 < var2.length; ++var5) {
         StackTraceElement var6 = var2[var5];
         if (var6.getClassName().startsWith(var1)) {
            String var10000 = var6.getMethodName();
            var3 = var10000 + "#" + var6.getLineNumber();
            if (var5 > 0) {
               var6 = var2[var5 - 1];
               var3 = var3 + " -> " + var6.getClassName() + "." + var6.getMethodName() + "#" + var6.getLineNumber();
            }
            break;
         }
      }

      if (var0 instanceof StandardException) {
         var4 = ", SQLSTate=" + ((StandardException)var0).getSQLState();
      }

      Class var8 = var0.getClass();
      return "<" + var8 + ", msg=" + var0.getMessage() + var4 + "> " + var3;
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static class KeyComparator {
      private static final int FETCH_SIZE = 16;
      private final DataValueDescriptor[][] rowBufferArray = new DataValueDescriptor[16][];
      private DataValueDescriptor[] lastUniqueKey;
      private DataValueDescriptor[] curr;
      private DataValueDescriptor[] prev;
      private int rowsReadLastRead = -1;
      private long numRows;

      public KeyComparator(ExecIndexRow var1) {
         this.rowBufferArray[0] = var1.getRowArray();
         this.lastUniqueKey = var1.getRowArrayClone();
      }

      public int fetchRows(GroupFetchScanController var1) throws StandardException {
         if (this.rowsReadLastRead == 16) {
            this.curr = this.rowBufferArray[15];
            this.rowBufferArray[15] = this.lastUniqueKey;
            this.lastUniqueKey = this.curr;
         }

         this.rowsReadLastRead = var1.fetchNextGroup(this.rowBufferArray, (RowLocation[])null);
         return this.rowsReadLastRead;
      }

      public int compareWithPrevKey(int var1) throws StandardException {
         if (var1 > this.rowsReadLastRead) {
            throw new IllegalStateException("invalid access, rowsReadLastRead=" + this.rowsReadLastRead + ", index=" + var1 + ", numRows=" + this.numRows);
         } else {
            ++this.numRows;
            if (this.numRows == 1L) {
               return 0;
            } else {
               this.prev = var1 == 0 ? this.lastUniqueKey : this.rowBufferArray[var1 - 1];
               this.curr = this.rowBufferArray[var1];

               for(int var3 = 0; var3 < this.prev.length - 1; ++var3) {
                  DataValueDescriptor var2 = this.prev[var3];
                  if (var2.isNull() || this.prev[var3].compare(this.curr[var3]) != 0) {
                     return var3;
                  }
               }

               return -1;
            }
         }
      }

      public long getRowCount() {
         return this.numRows;
      }
   }
}
