package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

class TempRAFContainer extends RAFContainer {
   protected int inUseCount;

   TempRAFContainer(BaseDataFileFactory var1) {
      super(var1);
   }

   public Cacheable setIdentity(Object var1) throws StandardException {
      ContainerKey var2 = (ContainerKey)var1;
      if (var2.getSegmentId() != -1L) {
         FileContainer var3 = (FileContainer)this.dataFactory.newContainerObject();
         return var3.setIdent(var2);
      } else {
         return super.setIdentity(var2);
      }
   }

   public Cacheable createIdentity(Object var1, Object var2) throws StandardException {
      ContainerKey var3 = (ContainerKey)var1;
      if (var3.getSegmentId() != -1L) {
         Cacheable var4 = this.dataFactory.newContainerObject();
         return var4.createIdentity(var3, var2);
      } else {
         return this.createIdent(var3, var2);
      }
   }

   public void removeContainer(LogInstant var1, boolean var2) throws StandardException {
      this.pageCache.discard(this.identity);
      synchronized(this) {
         this.setDroppedState(true);
         this.setCommittedDropState(true);
         this.setDirty(false);
         this.needsSync = false;
      }

      this.removeFile(this.getFileName(this.identity, false, false, false));
   }

   protected int preAllocate(long var1, int var3) {
      return 0;
   }

   protected void writePage(long var1, byte[] var3, boolean var4) throws IOException, StandardException {
      if (!this.getDroppedState()) {
         super.writePage(var1, var3, false);
      }

      this.needsSync = false;
   }

   StorageFile getFileName(ContainerKey var1, boolean var2, boolean var3, boolean var4) {
      return this.privGetFileName(var1, var2, var3, var4);
   }

   protected StorageFile privGetFileName(ContainerKey var1, boolean var2, boolean var3, boolean var4) {
      return this.dataFactory.storageFactory.newStorageFile(this.dataFactory.storageFactory.getTempDir(), "T" + var1.getContainerId() + ".tmp");
   }

   public Page addPage(BaseContainerHandle var1, boolean var2) throws StandardException {
      BasePage var3 = this.newPage(var1, (RawTransaction)null, var1, var2);
      return var3;
   }

   public void truncate(BaseContainerHandle var1) throws StandardException {
      synchronized(this) {
         this.setDroppedState(true);
         this.setCommittedDropState(true);
         this.setDirty(false);
         this.needsSync = false;
      }

      while(!this.pageCache.discard(this.identity)) {
      }

      this.removeFile(this.getFileName(this.identity, false, true, false));
      this.createIdent(this.identity, this);
      this.addPage(var1, false).unlatch();
   }

   protected boolean use(BaseContainerHandle var1, boolean var2, boolean var3) throws StandardException {
      if (super.use(var1, var2, var3)) {
         ++this.inUseCount;
         return true;
      } else {
         return false;
      }
   }

   protected void letGo(BaseContainerHandle var1) {
      --this.inUseCount;
      super.letGo(var1);
   }

   public boolean isSingleUser() {
      return this.inUseCount == 1;
   }
}
