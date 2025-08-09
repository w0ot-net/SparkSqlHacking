package org.apache.derby.impl.store.raw.xact;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.store.access.GlobalXact;
import org.apache.derby.iapi.store.raw.GlobalTransactionId;

public class GlobalXactId extends GlobalXact implements GlobalTransactionId {
   GlobalXactId(int var1, byte[] var2, byte[] var3) {
      this.format_id = var1;
      this.global_id = (byte[])(([B)var2).clone();
      this.branch_id = (byte[])(([B)var3).clone();
   }

   public GlobalXactId() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.format_id);
      var1.write(this.global_id.length);
      if (this.global_id.length > 0) {
         var1.write(this.global_id);
      }

      var1.write(this.branch_id.length);
      if (this.branch_id.length > 0) {
         var1.write(this.branch_id);
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.format_id = var1.readInt();
      int var2 = var1.read();
      this.global_id = new byte[var2];
      if (var2 > 0) {
         var1.read(this.global_id);
      }

      var2 = var1.read();
      this.branch_id = new byte[var2];
      if (var2 > 0) {
         var1.read(this.branch_id);
      }

   }

   public int getTypeFormatId() {
      return 328;
   }

   int getFormat_Id() {
      return this.format_id;
   }

   byte[] getGlobalTransactionId() {
      return this.global_id;
   }

   byte[] getBranchQualifier() {
      return this.branch_id;
   }
}
