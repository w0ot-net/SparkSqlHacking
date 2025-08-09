package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.LogicalUndoable;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

abstract class LogicalPageOperation extends PageBasicOperation implements LogicalUndoable {
   protected LogicalUndo undo;
   protected int recordId;

   public LogicalPageOperation() {
   }

   LogicalPageOperation(BasePage var1, LogicalUndo var2, int var3) {
      super(var1);
      this.undo = var2;
      this.recordId = var3;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeInt((DataOutput)var1, this.recordId);
      var1.writeObject(this.undo);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.recordId = CompressedNumber.readInt((DataInput)var1);
      this.undo = (LogicalUndo)var1.readObject();
   }

   public Compensation generateUndo(Transaction var1, LimitObjectInput var2) throws StandardException, IOException {
      if (this.undo == null) {
         BasePage var4 = this.findpage(var1);
         var4.preDirty();
         return new LogicalUndoOperation(var4, this.recordId, this);
      } else {
         BasePage var3 = this.findLogicalPage(var1, this.undo, var2);
         var3.preDirty();
         return new LogicalUndoOperation(var3, this.recordId, this);
      }
   }

   public ContainerHandle getContainer() {
      return this.containerHdl;
   }

   public void resetRecordHandle(RecordHandle var1) {
      this.resetPageNumber(var1.getPageNumber());
      this.recordId = var1.getId();
   }

   public RecordHandle getRecordHandle() {
      return new RecordId(this.getPageId(), this.recordId);
   }

   public void reclaimPrepareLocks(Transaction var1, LockingPolicy var2) throws StandardException {
      ContainerHandle var3 = var1.openContainer(this.getPageId().getContainerId(), var2, 196);
      if (var3 != null) {
         var3.close();
      }

      var2.lockRecordForWrite(var1, this.getRecordHandle(), false, false);
      this.releaseResource(var1);
   }

   private BasePage findLogicalPage(Transaction var1, LogicalUndo var2, LimitObjectInput var3) throws StandardException, IOException {
      this.releaseResource(var1);
      boolean var4 = false;

      try {
         RawTransaction var5 = (RawTransaction)var1;
         this.containerHdl = var5.openDroppedContainer(this.getPageId().getContainerId(), (LockingPolicy)null);
         this.page = (BasePage)var2.findUndo(var1, this, var3);
         var4 = true;
      } finally {
         if (!var4 && this.containerHdl != null) {
            this.containerHdl.close();
            this.containerHdl = null;
         }

      }

      this.foundHere = true;
      return this.page;
   }

   public abstract void undoMe(Transaction var1, BasePage var2, int var3, LogInstant var4, LimitObjectInput var5) throws StandardException, IOException;
}
