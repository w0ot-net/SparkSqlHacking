package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RePreparable;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.data.RawContainerHandle;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

abstract class PageBasicOperation implements Loggable, RePreparable {
   private PageKey pageId;
   private long pageVersion;
   protected transient BasePage page;
   protected transient RawContainerHandle containerHdl;
   protected transient boolean foundHere;

   PageBasicOperation(BasePage var1) {
      this.page = var1;
      this.pageId = var1.getPageId();
      this.pageVersion = var1.getPageVersion();
   }

   PageBasicOperation() {
   }

   public String toString() {
      return null;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      this.pageId.writeExternal(var1);
      CompressedNumber.writeLong((DataOutput)var1, this.pageVersion);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.pageId = PageKey.read(var1);
      this.pageVersion = CompressedNumber.readLong((DataInput)var1);
   }

   public final boolean needsRedo(Transaction var1) throws StandardException {
      if (this.findpage(var1) == null) {
         return false;
      } else {
         long var2 = this.page.getPageVersion();
         if (var2 == this.pageVersion) {
            return true;
         } else {
            this.releaseResource(var1);
            if (var2 > this.pageVersion) {
               return false;
            } else {
               throw StandardException.newException("XSDB4.D", new Object[]{this.pageId, var2, this.pageVersion});
            }
         }
      }
   }

   public void releaseResource(Transaction var1) {
      if (this.foundHere) {
         if (this.page != null) {
            this.page.unlatch();
            this.page = null;
         }

         if (this.containerHdl != null) {
            this.containerHdl.close();
            this.containerHdl = null;
         }

         this.foundHere = false;
      }
   }

   public int group() {
      return 384;
   }

   public ByteArray getPreparedLog() throws StandardException {
      return (ByteArray)null;
   }

   public void reclaimPrepareLocks(Transaction var1, LockingPolicy var2) throws StandardException {
   }

   protected final void resetPageNumber(long var1) {
      this.pageId = new PageKey(this.pageId.getContainerId(), var1);
   }

   protected final PageKey getPageId() {
      return this.pageId;
   }

   public final BasePage findpage(Transaction var1) throws StandardException {
      this.releaseResource(var1);
      RawTransaction var2 = (RawTransaction)var1;
      this.containerHdl = var2.openDroppedContainer(this.pageId.getContainerId(), (LockingPolicy)null);
      if (this.containerHdl == null) {
         throw StandardException.newException("40XD2", new Object[]{this.pageId.getContainerId()});
      } else {
         this.foundHere = true;
         if (this.containerHdl.getContainerStatus() == 4) {
            this.releaseResource(var1);
            return null;
         } else {
            StandardException var3 = null;

            try {
               this.page = (BasePage)this.containerHdl.getAnyPage(this.pageId.getPageNumber());
            } catch (StandardException var5) {
               var3 = var5;
            }

            if (this.page == null && var3 != null && this.pageVersion == 0L && PropertyUtil.getSystemBoolean("derby.storage.patchInitPageRecoverError")) {
               this.page = this.getPageForRedoRecovery(var1);
            }

            if (this.page == null && var3 != null && var2.inRollForwardRecovery()) {
               this.page = this.getPageForRedoRecovery(var1);
            }

            if (this.page == null) {
               if (var3 != null) {
                  throw var3;
               } else {
                  throw StandardException.newException("XSDB5.D", new Object[]{this.pageId});
               }
            } else {
               return this.page;
            }
         }
      }
   }

   protected BasePage getPageForRedoRecovery(Transaction var1) throws StandardException {
      return null;
   }

   public final Page getPage() {
      return this.page;
   }

   public final long getPageVersion() {
      return this.pageVersion;
   }

   public abstract void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException;
}
