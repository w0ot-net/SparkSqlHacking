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
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public final class UpdateFieldOperation extends LogicalPageOperation {
   protected int doMeSlot;
   protected int fieldId;
   protected transient ByteArray preparedLog;

   UpdateFieldOperation(RawTransaction var1, BasePage var2, int var3, int var4, int var5, Object var6, LogicalUndo var7) throws StandardException {
      super(var2, var7, var4);
      this.doMeSlot = var3;
      this.fieldId = var5;

      try {
         this.writeOptionalDataToBuffer(var1, var6);
      } catch (IOException var9) {
         throw StandardException.newException("XSDA4.S", var9, new Object[0]);
      }
   }

   public UpdateFieldOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeInt((DataOutput)var1, this.doMeSlot);
      CompressedNumber.writeInt((DataOutput)var1, this.fieldId);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.doMeSlot = CompressedNumber.readInt((DataInput)var1);
      this.fieldId = CompressedNumber.readInt((DataInput)var1);
   }

   public int getTypeFormatId() {
      return 109;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      this.page.storeField(var2, this.doMeSlot, this.fieldId, var3);
   }

   public void undoMe(Transaction var1, BasePage var2, int var3, LogInstant var4, LimitObjectInput var5) throws StandardException, IOException {
      int var6 = var2.findRecordById(var3, 0);
      var2.skipField(var5);
      var2.storeField(var4, var6, this.fieldId, var5);
      var2.setAuxObject((AuxObject)null);
   }

   public void restoreLoggedRow(Object[] var1, LimitObjectInput var2) throws StandardException, IOException {
      BasePage var3 = null;

      try {
         var3 = (BasePage)this.getContainer().getPage(this.getPageId().getPageNumber());
         var3.skipField(var2);
         var3.skipField(var2);
         var3.restoreRecordFromStream(var2, var1);
      } finally {
         if (var3 != null) {
            var3.unlatch();
            Object var7 = null;
         }

      }

   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException {
      int var5 = var2.findRecordById(this.recordId, 0);
      var2.skipField(var4);
      var2.storeField(var3, var5, this.fieldId, var4);
      var2.setAuxObject((AuxObject)null);
   }

   public ByteArray getPreparedLog() {
      return this.preparedLog;
   }

   private void writeOptionalDataToBuffer(RawTransaction var1, Object var2) throws StandardException, IOException {
      DynamicByteArrayOutputStream var3 = var1.getLogBuffer();
      int var4 = var3.getPosition();
      this.page.logColumn(this.doMeSlot, this.fieldId, var2, var3, 100);
      this.page.logField(this.doMeSlot, this.fieldId, var3);
      if (this.undo != null) {
         this.page.logRecord(this.doMeSlot, 0, this.recordId, (FormatableBitSet)null, var3, (RecordHandle)null);
      }

      int var5 = var3.getPosition() - var4;
      var3.setPosition(var4);
      this.preparedLog = new ByteArray(var3.getByteArray(), var4, var5);
   }

   public String toString() {
      return null;
   }
}
