package org.apache.derby.impl.store.raw.xact;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.GlobalTransactionId;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;

public class BeginXact implements Loggable {
   protected int transactionStatus;
   protected GlobalTransactionId xactId;

   public BeginXact(GlobalTransactionId var1, int var2) {
      this.xactId = var1;
      this.transactionStatus = var2;
   }

   public BeginXact() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.transactionStatus);
      var1.writeObject(this.xactId);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.transactionStatus = var1.readInt();
      this.xactId = (GlobalTransactionId)var1.readObject();
   }

   public int getTypeFormatId() {
      return 169;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) {
      RawTransaction var4 = (RawTransaction)var1;
      if (var2 != null) {
         var4.setFirstLogInstant(var2);
         var4.addUpdateTransaction(this.transactionStatus);
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
      short var1 = 257;
      return var1;
   }

   public String toString() {
      return null;
   }

   public GlobalTransactionId getGlobalId() {
      return this.xactId;
   }
}
