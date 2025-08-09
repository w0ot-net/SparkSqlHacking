package org.datanucleus.store.connection;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.xa.XAResource;
import org.datanucleus.util.StringUtils;

public abstract class AbstractManagedConnection implements ManagedConnection {
   protected Object conn;
   protected boolean closeOnRelease = true;
   protected boolean commitOnRelease = true;
   protected boolean locked = false;
   protected List listeners = new ArrayList();
   protected int useCount = 0;

   protected void incrementUseCount() {
      ++this.useCount;
   }

   public void release() {
      if (this.closeOnRelease) {
         --this.useCount;
         if (this.useCount == 0) {
            this.close();
         }
      }

   }

   public void transactionFlushed() {
      if (!this.listeners.isEmpty()) {
         ManagedConnectionResourceListener[] mcrls = (ManagedConnectionResourceListener[])this.listeners.toArray(new ManagedConnectionResourceListener[this.listeners.size()]);

         for(int i = 0; i < mcrls.length; ++i) {
            mcrls[i].transactionFlushed();
         }
      }

   }

   public void transactionPreClose() {
      if (!this.listeners.isEmpty()) {
         ManagedConnectionResourceListener[] mcrls = (ManagedConnectionResourceListener[])this.listeners.toArray(new ManagedConnectionResourceListener[this.listeners.size()]);

         for(int i = 0; i < mcrls.length; ++i) {
            mcrls[i].transactionPreClose();
         }
      }

   }

   public void setCloseOnRelease(boolean close) {
      this.closeOnRelease = close;
   }

   public void setCommitOnRelease(boolean commit) {
      this.commitOnRelease = commit;
   }

   public boolean closeOnRelease() {
      return this.closeOnRelease;
   }

   public boolean commitOnRelease() {
      return this.commitOnRelease;
   }

   public void addListener(ManagedConnectionResourceListener listener) {
      this.listeners.add(listener);
   }

   public void removeListener(ManagedConnectionResourceListener listener) {
      this.listeners.remove(listener);
   }

   public boolean isLocked() {
      return this.locked;
   }

   public synchronized void lock() {
      this.locked = true;
   }

   public synchronized void unlock() {
      this.locked = false;
   }

   public XAResource getXAResource() {
      return null;
   }

   public boolean closeAfterTransactionEnd() {
      return true;
   }

   public void setSavepoint(String name) {
   }

   public void releaseSavepoint(String name) {
   }

   public void rollbackToSavepoint(String name) {
   }

   public String toString() {
      return StringUtils.toJVMIDString(this) + " [conn=" + StringUtils.toJVMIDString(this.conn) + ", commitOnRelease=" + this.commitOnRelease + ", closeOnRelease=" + this.closeOnRelease + ", closeOnTxnEnd=" + this.closeAfterTransactionEnd() + "]";
   }
}
