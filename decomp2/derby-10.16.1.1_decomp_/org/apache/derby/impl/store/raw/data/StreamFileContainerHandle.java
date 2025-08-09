package org.apache.derby.impl.store.raw.data;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.monitor.DerbyObservable;
import org.apache.derby.iapi.services.monitor.DerbyObserver;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.StreamContainerHandle;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

final class StreamFileContainerHandle implements StreamContainerHandle, DerbyObserver {
   private final UUID rawStoreId;
   protected final ContainerKey identity;
   protected boolean active;
   protected StreamFileContainer container;
   protected RawTransaction xact;
   private boolean hold;

   public StreamFileContainerHandle(UUID var1, RawTransaction var2, ContainerKey var3, boolean var4) {
      this.identity = var3;
      this.xact = var2;
      this.rawStoreId = var1;
      this.hold = var4;
   }

   public StreamFileContainerHandle(UUID var1, RawTransaction var2, StreamFileContainer var3, boolean var4) {
      this.identity = var3.getIdentity();
      this.xact = var2;
      this.rawStoreId = var1;
      this.hold = var4;
      this.container = var3;
   }

   public void getContainerProperties(Properties var1) throws StandardException {
      this.container.getContainerProperties(var1);
   }

   public boolean fetchNext(DataValueDescriptor[] var1) throws StandardException {
      return this.container.fetchNext(var1);
   }

   public void close() {
      if (this.xact != null) {
         this.active = false;
         this.container.close();
         this.container = null;
         this.xact.deleteObserver(this);
         this.xact = null;
      }
   }

   public void removeContainer() throws StandardException {
      this.container.removeContainer();
   }

   public ContainerKey getId() {
      return this.identity;
   }

   public void update(DerbyObservable var1, Object var2) {
      if (this.xact != null) {
         if (!var2.equals(RawTransaction.COMMIT) && !var2.equals(RawTransaction.ABORT) && !var2.equals(this.identity)) {
            if (!var2.equals(RawTransaction.SAVEPOINT_ROLLBACK)) {
               ;
            }
         } else {
            this.close();
         }
      }
   }

   public boolean useContainer() throws StandardException {
      if (!this.container.use(this)) {
         this.container = null;
         return false;
      } else {
         this.active = true;
         if (!this.hold) {
            this.xact.addObserver(this);
            this.xact.addObserver(new DropOnCommit(this.identity, true));
         }

         return true;
      }
   }

   public final RawTransaction getTransaction() {
      return this.xact;
   }

   public String toString() {
      return super.toString();
   }
}
