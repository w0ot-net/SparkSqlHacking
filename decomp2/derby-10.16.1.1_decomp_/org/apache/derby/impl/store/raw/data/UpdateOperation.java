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
import org.apache.derby.iapi.store.raw.AuxObject;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public final class UpdateOperation extends PhysicalPageOperation {
   protected int doMeSlot;
   protected int recordId;
   protected transient int nextColumn;
   protected transient ByteArray preparedLog;

   UpdateOperation(RawTransaction var1, BasePage var2, int var3, int var4, Object[] var5, FormatableBitSet var6, int var7, DynamicByteArrayOutputStream var8, int var9, RecordHandle var10) throws StandardException {
      super(var2);
      this.doMeSlot = var3;
      this.recordId = var4;
      this.nextColumn = -1;

      try {
         this.writeOptionalDataToBuffer(var1, var8, var5, var6, var7, var9, var10);
      } catch (IOException var12) {
         throw StandardException.newException("XSDA4.S", var12, new Object[0]);
      }
   }

   public UpdateOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeInt((DataOutput)var1, this.doMeSlot);
      CompressedNumber.writeInt((DataOutput)var1, this.recordId);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.doMeSlot = CompressedNumber.readInt((DataInput)var1);
      this.recordId = CompressedNumber.readInt((DataInput)var1);
   }

   public int getTypeFormatId() {
      return 108;
   }

   public int getNextStartColumn() {
      return this.nextColumn;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      this.page.storeRecord(var2, this.doMeSlot, false, var3);
   }

   public void undoMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException {
      int var5 = var2.findRecordById(this.recordId, 0);
      var2.skipRecord(var4);
      var2.storeRecord(var3, var5, false, var4);
      var2.setAuxObject((AuxObject)null);
   }

   public ByteArray getPreparedLog() {
      return this.preparedLog;
   }

   private void writeOptionalDataToBuffer(RawTransaction var1, DynamicByteArrayOutputStream var2, Object[] var3, FormatableBitSet var4, int var5, int var6, RecordHandle var7) throws StandardException, IOException {
      if (var5 == -1) {
         var2 = var1.getLogBuffer();
      }

      int var8 = var2.getPosition();
      this.nextColumn = this.page.logRow(this.doMeSlot, false, this.recordId, var3, var4, var2, 0, (byte)8, var5, var6, 100);
      FormatableBitSet var9 = var4;
      if (this.nextColumn != -1 && var4 != null) {
         int var10 = this.page.getHeaderAtSlot(this.doMeSlot).getNumberFields();
         var9 = new FormatableBitSet(var4);
         int var11 = this.nextColumn + var10;
         var9.grow(var11);

         for(int var12 = this.nextColumn; var12 < var11; ++var12) {
            var9.set(var12);
         }
      }

      this.page.logRecord(this.doMeSlot, 1, this.recordId, var9, var2, var7);
      var8 = var2.getBeginPosition();
      int var14 = var2.getPosition() - var8;
      var2.setPosition(var8);
      this.preparedLog = new ByteArray(var2.getByteArray(), var8, var14);
   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException {
      this.undoMe(var1, var2, var3, var4);
   }

   private RecordHandle getRecordHandle() {
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

   public String toString() {
      return null;
   }
}
