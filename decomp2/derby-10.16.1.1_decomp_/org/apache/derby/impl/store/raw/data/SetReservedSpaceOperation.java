package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.shared.common.error.StandardException;

public final class SetReservedSpaceOperation extends PageBasicOperation {
   protected int doMeSlot;
   protected int recordId;
   protected int newValue;
   protected int oldValue;

   SetReservedSpaceOperation(BasePage var1, int var2, int var3, int var4, int var5) {
      super(var1);
      this.doMeSlot = var2;
      this.recordId = var3;
      this.newValue = var4;
      this.oldValue = var5;
   }

   public SetReservedSpaceOperation() {
   }

   public int getTypeFormatId() {
      return 287;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeInt((DataOutput)var1, this.doMeSlot);
      CompressedNumber.writeInt((DataOutput)var1, this.recordId);
      CompressedNumber.writeInt((DataOutput)var1, this.newValue);
      CompressedNumber.writeInt((DataOutput)var1, this.oldValue);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.doMeSlot = CompressedNumber.readInt((DataInput)var1);
      this.recordId = CompressedNumber.readInt((DataInput)var1);
      this.newValue = CompressedNumber.readInt((DataInput)var1);
      this.oldValue = CompressedNumber.readInt((DataInput)var1);
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException {
      this.page.setReservedSpace(var2, this.doMeSlot, this.newValue);
   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException, IOException {
      int var5 = var2.findRecordById(this.recordId, 0);
      this.page.setReservedSpace(var3, var5, this.oldValue);
   }

   public String toString() {
      return null;
   }
}
