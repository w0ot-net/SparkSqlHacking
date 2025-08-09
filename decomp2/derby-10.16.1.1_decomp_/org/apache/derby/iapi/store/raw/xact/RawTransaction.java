package org.apache.derby.iapi.store.raw.xact;

import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.services.monitor.DerbyObservable;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.GlobalTransactionId;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.data.RawContainerHandle;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public abstract class RawTransaction extends DerbyObservable implements Transaction {
   public static final Integer COMMIT = 0;
   public static final Integer ABORT = 1;
   public static final Integer SAVEPOINT_ROLLBACK = 2;
   public static final Integer LOCK_ESCALATE = 3;
   protected StandardException observerException;

   public abstract LockFactory getLockFactory();

   public abstract DataFactory getDataFactory();

   public abstract LogFactory getLogFactory();

   public abstract DynamicByteArrayOutputStream getLogBuffer();

   public abstract void logAndUndo(Compensation var1, LogInstant var2, LimitObjectInput var3) throws StandardException;

   public abstract void setTransactionId(Loggable var1, TransactionId var2);

   public abstract TransactionId getId();

   public abstract GlobalTransactionId getGlobalId();

   public abstract void addUpdateTransaction(int var1);

   public abstract void removeUpdateTransaction();

   public abstract void prepareTransaction();

   public abstract void setFirstLogInstant(LogInstant var1);

   public abstract LogInstant getFirstLogInstant();

   public abstract void setLastLogInstant(LogInstant var1);

   public abstract LogInstant getLastLogInstant();

   public void checkLogicalOperationOk() throws StandardException {
   }

   public boolean recoveryRollbackFirst() {
      return false;
   }

   public abstract void reprepare() throws StandardException;

   public void setObserverException(StandardException var1) {
      if (this.observerException == null) {
         this.observerException = var1;
      }

   }

   public abstract RawTransaction startNestedTopTransaction() throws StandardException;

   public abstract RawContainerHandle openDroppedContainer(ContainerKey var1, LockingPolicy var2) throws StandardException;

   public abstract void reCreateContainerForRedoRecovery(long var1, long var3, ByteArray var5) throws StandardException;

   protected abstract int statusForBeginXactLog();

   protected abstract int statusForEndXactLog();

   public abstract boolean inAbort();

   public abstract boolean handlesPostTerminationWork();

   public abstract void recoveryTransaction();

   public void notifyObservers(Object var1) {
      if (this.countObservers() != 0) {
         this.setChanged();
         super.notifyObservers(var1);
      }

   }

   public abstract boolean inRollForwardRecovery();

   public abstract void checkpointInRollForwardRecovery(LogInstant var1, long var2, long var4) throws StandardException;

   public abstract boolean blockBackup(boolean var1) throws StandardException;

   public abstract boolean isBlockingBackup();
}
