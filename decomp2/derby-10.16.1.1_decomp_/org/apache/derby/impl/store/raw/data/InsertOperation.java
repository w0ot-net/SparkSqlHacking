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
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public final class InsertOperation extends LogicalPageOperation {
   protected int doMeSlot;
   protected byte insertFlag;
   protected transient int startColumn;
   protected transient ByteArray preparedLog;

   InsertOperation(RawTransaction var1, BasePage var2, int var3, int var4, Object[] var5, FormatableBitSet var6, LogicalUndo var7, byte var8, int var9, boolean var10, int var11, DynamicByteArrayOutputStream var12, int var13, int var14) throws StandardException {
      super(var2, var7, var4);
      this.doMeSlot = var3;
      this.insertFlag = var8;
      this.startColumn = var9;

      try {
         this.writeOptionalDataToBuffer(var1, var12, var5, var6, var10, var11, var13, var14);
      } catch (IOException var16) {
         throw StandardException.newException("XSDA4.S", var16, new Object[0]);
      }
   }

   public InsertOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeInt((DataOutput)var1, this.doMeSlot);
      var1.writeByte(this.insertFlag);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.doMeSlot = CompressedNumber.readInt((DataInput)var1);
      this.insertFlag = var1.readByte();
   }

   public int getTypeFormatId() {
      return 103;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      this.page.storeRecord(var2, this.doMeSlot, true, var3);
   }

   public void undoMe(Transaction var1, BasePage var2, int var3, LogInstant var4, LimitObjectInput var5) throws StandardException, IOException {
      int var6 = var2.findRecordById(var3, 0);
      RawTransaction var7 = (RawTransaction)var1;
      if ((this.insertFlag & 2) != 0) {
         var2.purgeRecord(var4, var6, var3);
         if (var7.handlesPostTerminationWork() && var2.isOverflowPage() && var2.recordCount() == 0) {
            ReclaimSpace var8 = new ReclaimSpace(2, (PageKey)var2.getIdentity(), var7.getDataFactory(), true);
            var7.addPostTerminationWork(var8);
         }
      } else {
         var2.setDeleteStatus(var4, var6, true);
         if (var7.handlesPostTerminationWork() && !var2.isOverflowPage() && var2.shouldReclaimSpace(var2.getPageNumber() == 1L ? 1 : 0, var6)) {
            ((BaseDataFileFactory)var7.getDataFactory()).insertUndoNotify(var7, var2.getPageKey());
         }
      }

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
      this.insertFlag = (byte)(this.insertFlag | 2);
      this.undoMe(var1, var2, this.recordId, var3, var4);
   }

   public ByteArray getPreparedLog() {
      return this.preparedLog;
   }

   public int getNextStartColumn() {
      return this.startColumn;
   }

   private void writeOptionalDataToBuffer(RawTransaction var1, DynamicByteArrayOutputStream var2, Object[] var3, FormatableBitSet var4, boolean var5, int var6, int var7, int var8) throws StandardException, IOException {
      Object var9 = null;
      DynamicByteArrayOutputStream var12;
      if (var2 != null) {
         var12 = var2;
      } else {
         var6 = -1;
         var7 = -1;
         var12 = var1.getLogBuffer();
      }

      if (var5) {
         this.startColumn = this.page.logLongColumn(this.doMeSlot, this.recordId, var3[0], var12);
      } else {
         this.startColumn = this.page.logRow(this.doMeSlot, true, this.recordId, var3, var4, var12, this.startColumn, this.insertFlag, var6, var7, var8);
      }

      int var10 = var12.getBeginPosition();
      int var11 = var12.getPosition() - var10;
      this.preparedLog = new ByteArray(var12.getByteArray(), var10, var11);
   }

   public String toString() {
      return null;
   }
}
