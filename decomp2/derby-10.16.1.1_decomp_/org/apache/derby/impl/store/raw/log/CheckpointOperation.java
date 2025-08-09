package org.apache.derby.impl.store.raw.log;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public class CheckpointOperation implements Loggable {
   protected long redoLWM;
   protected long undoLWM;
   protected Formatable transactionTable;

   public CheckpointOperation(long var1, long var3, Formatable var5) {
      this.redoLWM = var1;
      this.undoLWM = var3;
      this.transactionTable = var5;
   }

   public CheckpointOperation() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      CompressedNumber.writeLong((DataOutput)var1, this.redoLWM);
      CompressedNumber.writeLong((DataOutput)var1, this.undoLWM);
      CompressedNumber.writeInt((DataOutput)var1, 0);
      if (this.transactionTable == null) {
         CompressedNumber.writeInt((DataOutput)var1, 0);
      } else {
         CompressedNumber.writeInt((DataOutput)var1, 1);
         var1.writeObject(this.transactionTable);
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.redoLWM = CompressedNumber.readLong((DataInput)var1);
      this.undoLWM = CompressedNumber.readLong((DataInput)var1);
      int var2 = CompressedNumber.readInt((DataInput)var1);
      int var3 = CompressedNumber.readInt((DataInput)var1);
      if (var3 == 1) {
         this.transactionTable = (Formatable)var1.readObject();
      } else {
         this.transactionTable = (Formatable)null;
      }

   }

   public int getTypeFormatId() {
      return 263;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException {
      if (((RawTransaction)var1).inRollForwardRecovery()) {
         ((RawTransaction)var1).checkpointInRollForwardRecovery(var2, this.redoLWM, this.undoLWM);
      }

   }

   public ByteArray getPreparedLog() {
      return (ByteArray)null;
   }

   public boolean needsRedo(Transaction var1) {
      return ((RawTransaction)var1).inRollForwardRecovery();
   }

   public void releaseResource(Transaction var1) {
   }

   public int group() {
      return 256;
   }

   public long redoLWM() {
      return this.redoLWM;
   }

   public long undoLWM() {
      return this.undoLWM;
   }

   public Formatable getTransactionTable() {
      return this.transactionTable;
   }

   public String toString() {
      return null;
   }
}
