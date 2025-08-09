package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.AuxObject;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public final class DeleteOperation extends LogicalPageOperation {
   protected int doMeSlot;
   protected boolean delete;
   protected transient ByteArray preparedLog;

   DeleteOperation(RawTransaction var1, BasePage var2, int var3, int var4, boolean var5, LogicalUndo var6) throws StandardException {
      super(var2, var6, var4);
      this.doMeSlot = var3;
      this.delete = var5;

      try {
         this.writeOptionalDataToBuffer(var1);
      } catch (IOException var8) {
         throw StandardException.newException("XSDA4.S", var8, new Object[0]);
      }
   }

   public DeleteOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeInt((DataOutput)var1, this.doMeSlot);
      var1.writeBoolean(this.delete);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.doMeSlot = CompressedNumber.readInt((DataInput)var1);
      this.delete = var1.readBoolean();
   }

   public int getTypeFormatId() {
      return 101;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      this.page.setDeleteStatus(var2, this.doMeSlot, this.delete);
   }

   public void undoMe(Transaction var1, BasePage var2, int var3, LogInstant var4, LimitObjectInput var5) throws StandardException, IOException {
      int var6 = var2.findRecordById(var3, 0);
      var2.setDeleteStatus(var4, var6, !this.delete);
      var2.setAuxObject((AuxObject)null);
   }

   public void restoreLoggedRow(Object[] var1, LimitObjectInput var2) throws StandardException, IOException {
      Page var3 = null;

      try {
         var3 = this.getContainer().getPage(this.getPageId().getPageNumber());
         ((BasePage)var3).restoreRecordFromStream(var2, var1);
      } finally {
         if (var3 != null) {
            var3.unlatch();
            Object var7 = null;
         }

      }

   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException {
      int var5 = var2.findRecordById(this.recordId, 0);
      var2.setDeleteStatus(var3, var5, !this.delete);
      var2.setAuxObject((AuxObject)null);
   }

   public ByteArray getPreparedLog() {
      return this.preparedLog;
   }

   private void writeOptionalDataToBuffer(RawTransaction var1) throws StandardException, IOException {
      DynamicByteArrayOutputStream var2 = var1.getLogBuffer();
      int var3 = var2.getPosition();
      if (this.undo != null) {
         this.page.logRecord(this.doMeSlot, 0, this.recordId, (FormatableBitSet)null, var2, (RecordHandle)null);
      }

      int var4 = var2.getPosition() - var3;
      var2.setPosition(var3);
      this.preparedLog = new ByteArray(var2.getByteArray(), var3, var4);
   }

   public String toString() {
      return null;
   }
}
