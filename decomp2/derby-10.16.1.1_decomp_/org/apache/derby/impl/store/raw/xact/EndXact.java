package org.apache.derby.impl.store.raw.xact;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.GlobalTransactionId;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;

public class EndXact implements Loggable {
   private int transactionStatus;
   private GlobalTransactionId xactId;

   public EndXact(GlobalTransactionId var1, int var2) {
      this.xactId = var1;
      this.transactionStatus = var2;
   }

   public EndXact() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.xactId);
      CompressedNumber.writeInt((DataOutput)var1, this.transactionStatus);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.xactId = (GlobalTransactionId)var1.readObject();
      this.transactionStatus = CompressedNumber.readInt((DataInput)var1);
   }

   public int getTypeFormatId() {
      return 102;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) {
      if ((this.transactionStatus & 2) == 0) {
         ((RawTransaction)var1).removeUpdateTransaction();
      } else {
         ((RawTransaction)var1).prepareTransaction();
      }

   }

   public ByteArray getPreparedLog() {
      return (ByteArray)null;
   }

   public boolean needsRedo(Transaction var1) {
      return true;
   }

   public void releaseResource(Transaction var1) {
   }

   public int group() {
      int var1 = 256;
      if ((this.transactionStatus & 4) != 0) {
         var1 |= 18;
      } else if ((this.transactionStatus & 1) != 0) {
         var1 |= 34;
      } else if ((this.transactionStatus & 2) != 0) {
         var1 |= 64;
      }

      return var1;
   }

   public String toString() {
      return null;
   }
}
