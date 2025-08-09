package org.apache.derby.impl.store.raw.data;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.derby.iapi.services.io.CloneableStream;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.Resetable;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class OverflowInputStream extends BufferedByteHolderInputStream implements Resetable, CloneableStream {
   protected BaseContainerHandle owner;
   protected long overflowPage;
   protected int overflowId;
   protected long firstOverflowPage;
   protected int firstOverflowId;
   protected RecordHandle recordToLock;
   private boolean initialized = false;

   public OverflowInputStream(ByteHolder var1, BaseContainerHandle var2, long var3, int var5, RecordHandle var6) {
      super(var1);
      this.owner = var2;
      this.overflowPage = var3;
      this.overflowId = var5;
      this.firstOverflowPage = var3;
      this.firstOverflowId = var5;
      this.recordToLock = var6;
   }

   public void fillByteHolder() throws IOException {
      if (this.bh.available() == 0 && this.overflowPage != -1L) {
         this.bh.clear();

         try {
            BasePage var1 = (BasePage)this.owner.getPage(this.overflowPage);
            if (var1 == null) {
               throw new EOFException(MessageService.getTextMessage("D015", new Object[0]));
            }

            var1.restorePortionLongColumn(this);
            var1.unlatch();
            Object var3 = null;
         } catch (StandardException var2) {
            throw new IOException(var2);
         }

         this.bh.startReading();
      }

   }

   public void setOverflowPage(long var1) {
      this.overflowPage = var1;
   }

   public void setOverflowId(int var1) {
      this.overflowId = var1;
   }

   public long getOverflowPage() {
      return this.overflowPage;
   }

   public int getOverflowId() {
      return this.overflowId;
   }

   public void initStream() throws StandardException {
      if (!this.initialized) {
         if (this.owner.getTransaction() == null) {
            throw StandardException.newException("40XD0", new Object[0]);
         } else {
            LockingPolicy var1 = this.owner.getTransaction().newLockingPolicy(1, 2, true);
            this.owner = (BaseContainerHandle)this.owner.getTransaction().openContainer(this.owner.getId(), var1, this.owner.getMode());
            this.owner.getLockingPolicy().lockRecordForRead(this.owner.getTransaction(), this.owner, this.recordToLock, true, false);
            this.initialized = true;
         }
      }
   }

   public void resetStream() throws IOException, StandardException {
      this.owner.checkOpen();
      this.overflowPage = this.firstOverflowPage;
      this.overflowId = this.firstOverflowId;
      this.bh.clear();
      this.bh.startReading();
   }

   public void closeStream() {
      this.owner.close();
      this.initialized = false;
   }

   public InputStream cloneStream() {
      OverflowInputStream var1 = new OverflowInputStream(this.bh.cloneEmpty(), this.owner, this.firstOverflowPage, this.firstOverflowId, this.recordToLock);
      return var1;
   }
}
