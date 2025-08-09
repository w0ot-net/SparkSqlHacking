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

public final class ChainAllocPageOperation extends PhysicalPageOperation {
   protected long newAllocPageNum;
   protected long newAllocPageOffset;

   ChainAllocPageOperation(AllocPage var1, long var2, long var4) throws StandardException {
      super(var1);
      this.newAllocPageNum = var2;
      this.newAllocPageOffset = var4;
   }

   public ChainAllocPageOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeLong((DataOutput)var1, this.newAllocPageNum);
      CompressedNumber.writeLong((DataOutput)var1, this.newAllocPageOffset);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.newAllocPageNum = CompressedNumber.readLong((DataInput)var1);
      this.newAllocPageOffset = CompressedNumber.readLong((DataInput)var1);
   }

   public int getTypeFormatId() {
      return 97;
   }

   public final void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException {
      ((AllocPage)this.page).chainNextAllocPage(var2, this.newAllocPageNum, this.newAllocPageOffset);
   }

   public void undoMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) throws StandardException {
      ((AllocPage)var2).chainNextAllocPage(var3, -1L, 0L);
   }

   public void restoreMe(Transaction var1, BasePage var2, LogInstant var3, LimitObjectInput var4) {
   }

   public String toString() {
      return null;
   }
}
