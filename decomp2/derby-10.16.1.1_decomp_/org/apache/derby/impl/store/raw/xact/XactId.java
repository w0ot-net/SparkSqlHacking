package org.apache.derby.impl.store.raw.xact;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.store.raw.xact.TransactionId;

public class XactId implements TransactionId {
   private long id;

   public XactId(long var1) {
      this.id = var1;
   }

   public XactId() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      CompressedNumber.writeLong((DataOutput)var1, this.id);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.id = CompressedNumber.readLong((DataInput)var1);
   }

   public int getTypeFormatId() {
      return 147;
   }

   public int getMaxStoredSize() {
      return FormatIdUtil.getFormatIdByteLength(147) + 8;
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else {
         try {
            XactId var2 = (XactId)var1;
            return this.id == var2.id;
         } catch (ClassCastException var3) {
            return false;
         }
      }
   }

   public int hashCode() {
      return (int)this.id;
   }

   public static long compare(TransactionId var0, TransactionId var1) {
      if (var0 != null && var1 != null) {
         XactId var2 = (XactId)var0;
         XactId var3 = (XactId)var1;
         return var2.id - var3.id;
      } else if (var0 == null) {
         return -1L;
      } else {
         return var1 == null ? 1L : 0L;
      }
   }

   protected long getId() {
      return this.id;
   }

   public String toString() {
      return Long.toString(this.id);
   }
}
