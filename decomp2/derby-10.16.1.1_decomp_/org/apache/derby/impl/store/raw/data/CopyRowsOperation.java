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

public class CopyRowsOperation extends PhysicalPageOperation {
   protected int num_rows;
   protected int destSlot;
   protected int[] recordIds;
   protected int[] reservedSpace;
   protected transient ByteArray preparedLog;

   CopyRowsOperation(RawTransaction var1, BasePage var2, BasePage var3, int var4, int var5, int var6, int[] var7) throws StandardException {
      super(var2);
      this.num_rows = var5;
      this.destSlot = var4;
      this.recordIds = var7;

      try {
         this.reservedSpace = new int[var5];

         for(int var8 = 0; var8 < var5; ++var8) {
            this.reservedSpace[var8] = var3.getReservedCount(var8 + var6);
         }

         this.writeOptionalDataToBuffer(var1, var3, var6);
      } catch (IOException var9) {
         throw StandardException.newException("XSDA4.S", var9, new Object[0]);
      }
   }

   public CopyRowsOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeInt((DataOutput)var1, this.num_rows);
      CompressedNumber.writeInt((DataOutput)var1, this.destSlot);

      for(int var2 = 0; var2 < this.num_rows; ++var2) {
         CompressedNumber.writeInt((DataOutput)var1, this.recordIds[var2]);
         CompressedNumber.writeInt((DataOutput)var1, this.reservedSpace[var2]);
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.num_rows = CompressedNumber.readInt((DataInput)var1);
      this.destSlot = CompressedNumber.readInt((DataInput)var1);
      this.recordIds = new int[this.num_rows];
      this.reservedSpace = new int[this.num_rows];

      for(int var2 = 0; var2 < this.num_rows; ++var2) {
         this.recordIds[var2] = CompressedNumber.readInt((DataInput)var1);
         this.reservedSpace[var2] = CompressedNumber.readInt((DataInput)var1);
      }

   }

   public int getTypeFormatId() {
      return 210;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      for(int var4 = 0; var4 < this.num_rows; ++var4) {
         this.page.storeRecord(var2, this.destSlot + var4, true, var3);
         if (this.reservedSpace[var4] > 0) {
            this.page.reserveSpaceForSlot(var2, this.destSlot + var4, this.reservedSpace[var4]);
         }
      }

   }

   public void undoMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException {
      for(int var6 = this.num_rows - 1; var6 >= 0; --var6) {
         int var5 = var2.findRecordById(this.recordIds[var6], var6);
         var2.purgeRecord(var3, var5, this.recordIds[var6]);
      }

      var2.setAuxObject((AuxObject)null);
   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException {
      this.undoMe(var1, var2, var3, var4);
   }

   public ByteArray getPreparedLog() {
      return this.preparedLog;
   }

   private void writeOptionalDataToBuffer(RawTransaction var1, BasePage var2, int var3) throws StandardException, IOException {
      DynamicByteArrayOutputStream var4 = var1.getLogBuffer();
      int var5 = var4.getPosition();
      int[] var6 = new int[this.num_rows];
      int var7 = var4.getPosition();

      for(int var8 = 0; var8 < this.num_rows; ++var8) {
         var2.logRecord(var8 + var3, 0, this.recordIds[var8], (FormatableBitSet)null, var4, (RecordHandle)null);
         var6[var8] = var4.getPosition() - var7;
         var7 = var4.getPosition();
         var6[var8] += this.reservedSpace[var8];
      }

      if (!this.page.spaceForCopy(this.num_rows, var6)) {
         throw StandardException.newException("XSDA3.S", new Object[0]);
      } else {
         int var9 = var4.getPosition() - var5;
         var4.setPosition(var5);
         this.preparedLog = new ByteArray(var4.getByteArray(), var5, var9);
      }
   }

   public String toString() {
      return null;
   }
}
