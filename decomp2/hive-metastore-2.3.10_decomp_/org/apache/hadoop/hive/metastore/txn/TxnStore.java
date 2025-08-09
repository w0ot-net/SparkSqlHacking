package org.apache.hadoop.hive.metastore.txn;

import com.google.common.annotations.VisibleForTesting;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Private;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Evolving;
import org.apache.hadoop.hive.common.classification.RetrySemantics.CannotRetry;
import org.apache.hadoop.hive.common.classification.RetrySemantics.Idempotent;
import org.apache.hadoop.hive.common.classification.RetrySemantics.ReadOnly;
import org.apache.hadoop.hive.common.classification.RetrySemantics.SafeToRetry;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.AbortTxnRequest;
import org.apache.hadoop.hive.metastore.api.AbortTxnsRequest;
import org.apache.hadoop.hive.metastore.api.AddDynamicPartitions;
import org.apache.hadoop.hive.metastore.api.CheckLockRequest;
import org.apache.hadoop.hive.metastore.api.CommitTxnRequest;
import org.apache.hadoop.hive.metastore.api.CompactionRequest;
import org.apache.hadoop.hive.metastore.api.CompactionResponse;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.GetOpenTxnsInfoResponse;
import org.apache.hadoop.hive.metastore.api.GetOpenTxnsResponse;
import org.apache.hadoop.hive.metastore.api.HeartbeatRequest;
import org.apache.hadoop.hive.metastore.api.HeartbeatTxnRangeRequest;
import org.apache.hadoop.hive.metastore.api.HeartbeatTxnRangeResponse;
import org.apache.hadoop.hive.metastore.api.HiveObjectType;
import org.apache.hadoop.hive.metastore.api.LockRequest;
import org.apache.hadoop.hive.metastore.api.LockResponse;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchLockException;
import org.apache.hadoop.hive.metastore.api.NoSuchTxnException;
import org.apache.hadoop.hive.metastore.api.OpenTxnRequest;
import org.apache.hadoop.hive.metastore.api.OpenTxnsResponse;
import org.apache.hadoop.hive.metastore.api.ShowCompactRequest;
import org.apache.hadoop.hive.metastore.api.ShowCompactResponse;
import org.apache.hadoop.hive.metastore.api.ShowLocksRequest;
import org.apache.hadoop.hive.metastore.api.ShowLocksResponse;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.api.TxnAbortedException;
import org.apache.hadoop.hive.metastore.api.TxnOpenException;
import org.apache.hadoop.hive.metastore.api.UnlockRequest;

@Private
@Evolving
public interface TxnStore {
   String INITIATED_RESPONSE = "initiated";
   String WORKING_RESPONSE = "working";
   String CLEANING_RESPONSE = "ready for cleaning";
   String FAILED_RESPONSE = "failed";
   String SUCCEEDED_RESPONSE = "succeeded";
   String ATTEMPTED_RESPONSE = "attempted";
   int TIMED_OUT_TXN_ABORT_BATCH_SIZE = 50000;

   void setConf(HiveConf var1);

   @ReadOnly
   GetOpenTxnsInfoResponse getOpenTxnsInfo() throws MetaException;

   @ReadOnly
   GetOpenTxnsResponse getOpenTxns() throws MetaException;

   @ReadOnly
   void countOpenTxns() throws MetaException;

   @Idempotent
   OpenTxnsResponse openTxns(OpenTxnRequest var1) throws MetaException;

   @Idempotent
   void abortTxn(AbortTxnRequest var1) throws NoSuchTxnException, MetaException, TxnAbortedException;

   @Idempotent
   void abortTxns(AbortTxnsRequest var1) throws NoSuchTxnException, MetaException;

   @Idempotent
   void commitTxn(CommitTxnRequest var1) throws NoSuchTxnException, TxnAbortedException, MetaException;

   @CannotRetry
   LockResponse lock(LockRequest var1) throws NoSuchTxnException, TxnAbortedException, MetaException;

   @SafeToRetry
   LockResponse checkLock(CheckLockRequest var1) throws NoSuchTxnException, NoSuchLockException, TxnAbortedException, MetaException;

   @Idempotent
   void unlock(UnlockRequest var1) throws NoSuchLockException, TxnOpenException, MetaException;

   @ReadOnly
   ShowLocksResponse showLocks(ShowLocksRequest var1) throws MetaException;

   @SafeToRetry
   void heartbeat(HeartbeatRequest var1) throws NoSuchTxnException, NoSuchLockException, TxnAbortedException, MetaException;

   @SafeToRetry
   HeartbeatTxnRangeResponse heartbeatTxnRange(HeartbeatTxnRangeRequest var1) throws MetaException;

   @Idempotent
   CompactionResponse compact(CompactionRequest var1) throws MetaException;

   @ReadOnly
   ShowCompactResponse showCompact(ShowCompactRequest var1) throws MetaException;

   @SafeToRetry
   void addDynamicPartitions(AddDynamicPartitions var1) throws NoSuchTxnException, TxnAbortedException, MetaException;

   @Idempotent
   void cleanupRecords(HiveObjectType var1, Database var2, Table var3, Iterator var4) throws MetaException;

   @Idempotent
   void performTimeOuts();

   @ReadOnly
   Set findPotentialCompactions(int var1) throws MetaException;

   @Idempotent
   void setRunAs(long var1, String var3) throws MetaException;

   @ReadOnly
   CompactionInfo findNextToCompact(String var1) throws MetaException;

   @SafeToRetry
   void markCompacted(CompactionInfo var1) throws MetaException;

   @ReadOnly
   List findReadyToClean() throws MetaException;

   @CannotRetry
   void markCleaned(CompactionInfo var1) throws MetaException;

   @CannotRetry
   void markFailed(CompactionInfo var1) throws MetaException;

   @SafeToRetry
   void cleanEmptyAbortedTxns() throws MetaException;

   @Idempotent
   void revokeFromLocalWorkers(String var1) throws MetaException;

   @Idempotent
   void revokeTimedoutWorkers(long var1) throws MetaException;

   @ReadOnly
   List findColumnsWithStats(CompactionInfo var1) throws MetaException;

   @Idempotent
   void setCompactionHighestTxnId(CompactionInfo var1, long var2) throws MetaException;

   @SafeToRetry
   void purgeCompactionHistory() throws MetaException;

   @SafeToRetry
   void performWriteSetGC();

   @ReadOnly
   boolean checkFailedCompactions(CompactionInfo var1) throws MetaException;

   @VisibleForTesting
   int numLocksInLockTable() throws SQLException, MetaException;

   @VisibleForTesting
   long setTimeout(long var1);

   @Idempotent
   MutexAPI getMutexAPI();

   @Idempotent
   void setHadoopJobId(String var1, long var2);

   public static enum MUTEX_KEY {
      Initiator,
      Cleaner,
      HouseKeeper,
      CompactionHistory,
      CheckLock,
      WriteSetCleaner,
      CompactionScheduler;
   }

   public interface MutexAPI {
      LockHandle acquireLock(String var1) throws MetaException;

      void acquireLock(String var1, LockHandle var2) throws MetaException;

      public interface LockHandle {
         void releaseLocks();
      }
   }
}
