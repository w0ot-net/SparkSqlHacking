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
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public final class PurgeOperation extends PhysicalPageOperation {
   protected int slot;
   protected int num_rows;
   protected int[] recordIds;
   protected transient ByteArray preparedLog;

   PurgeOperation(RawTransaction var1, BasePage var2, int var3, int var4, int[] var5, boolean var6) throws StandardException {
      super(var2);
      this.slot = var3;
      this.num_rows = var4;
      this.recordIds = var5;

      try {
         this.writeOptionalDataToBuffer(var1, var6);
      } catch (IOException var8) {
         throw StandardException.newException("XSDA4.S", var8, new Object[0]);
      }
   }

   public PurgeOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeInt((DataOutput)var1, this.slot);
      CompressedNumber.writeInt((DataOutput)var1, this.num_rows);

      for(int var2 = 0; var2 < this.num_rows; ++var2) {
         CompressedNumber.writeInt((DataOutput)var1, this.recordIds[var2]);
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.slot = CompressedNumber.readInt((DataInput)var1);
      this.num_rows = CompressedNumber.readInt((DataInput)var1);
      this.recordIds = new int[this.num_rows];

      for(int var2 = 0; var2 < this.num_rows; ++var2) {
         this.recordIds[var2] = CompressedNumber.readInt((DataInput)var1);
      }

   }

   public int getTypeFormatId() {
      return 106;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      for(int var4 = this.num_rows - 1; var4 >= 0; --var4) {
         this.page.purgeRecord(var2, this.slot + var4, this.recordIds[var4]);
      }

   }

   public void undoMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException {
      for(int var5 = 0; var5 < this.num_rows; ++var5) {
         var2.storeRecord(var3, this.slot + var5, true, var4);
      }

      var2.setAuxObject((AuxObject)null);
   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException {
      this.undoMe(var1, var2, var3, var4);
   }

   public ByteArray getPreparedLog() {
      return this.preparedLog;
   }

   private void writeOptionalDataToBuffer(RawTransaction var1, boolean var2) throws StandardException, IOException {
      DynamicByteArrayOutputStream var3 = var1.getLogBuffer();
      int var4 = var3.getPosition();

      for(int var5 = 0; var5 < this.num_rows; ++var5) {
         if (var2) {
            this.page.logRecord(var5 + this.slot, 0, this.recordIds[var5], (FormatableBitSet)null, var3, (RecordHandle)null);
         } else {
            this.page.logRecord(var5 + this.slot, 2, this.recordIds[var5], (FormatableBitSet)null, var3, (RecordHandle)null);
         }
      }

      int var6 = var3.getPosition() - var4;
      var3.setPosition(var4);
      this.preparedLog = new ByteArray(var3.getByteArray(), var4, var6);
   }

   public String toString() {
      return null;
   }
}
