package org.apache.derby.impl.store.raw.log;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.RePreparable;
import org.apache.derby.iapi.store.raw.Undoable;
import org.apache.derby.iapi.store.raw.xact.TransactionId;
import org.apache.derby.shared.common.error.StandardException;

public class LogRecord implements Formatable {
   private TransactionId xactId;
   private Loggable op;
   private int group;
   transient ObjectInput input;
   private static final int formatLength = FormatIdUtil.getFormatIdByteLength(129);

   public void writeExternal(ObjectOutput var1) throws IOException {
      CompressedNumber.writeInt((DataOutput)var1, this.group);
      var1.writeObject(this.xactId);
      var1.writeObject(this.op);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.group = CompressedNumber.readInt((DataInput)var1);
      this.input = var1;
      this.xactId = null;
      this.op = null;
   }

   public int getTypeFormatId() {
      return 129;
   }

   public void setValue(TransactionId var1, Loggable var2) {
      this.xactId = var1;
      this.op = var2;
      this.group = var2.group();
   }

   public static int formatOverhead() {
      return formatLength;
   }

   public static int maxGroupStoredSize() {
      return 4;
   }

   public static int maxTransactionIdStoredSize(TransactionId var0) {
      return var0.getMaxStoredSize();
   }

   public static int getStoredSize(int var0, TransactionId var1) {
      return formatLength + CompressedNumber.sizeInt(var0) + FormatIdUtil.getFormatIdByteLength(0);
   }

   public TransactionId getTransactionId() throws IOException, ClassNotFoundException {
      if (this.xactId != null) {
         return this.xactId;
      } else {
         Object var1 = this.input.readObject();
         this.xactId = (TransactionId)var1;
         return this.xactId;
      }
   }

   public Loggable getLoggable() throws IOException, ClassNotFoundException {
      if (this.op != null) {
         return this.op;
      } else {
         if (this.xactId == null) {
            this.xactId = (TransactionId)this.input.readObject();
         }

         Object var1 = this.input.readObject();
         this.op = (Loggable)var1;
         this.input = null;
         return this.op;
      }
   }

   public RePreparable getRePreparable() throws IOException, ClassNotFoundException {
      return (RePreparable)this.getLoggable();
   }

   public void skipLoggable() throws StandardException {
      if (this.op == null) {
         try {
            if (this.xactId == null) {
               this.xactId = (TransactionId)this.input.readObject();
            }

            if (this.op == null) {
               this.op = (Loggable)this.input.readObject();
            }

         } catch (ClassNotFoundException var2) {
            throw StandardException.newException("XSLA3.D", var2, new Object[0]);
         } catch (IOException var3) {
            throw StandardException.newException("XSLA3.D", var3, new Object[0]);
         }
      }
   }

   public Undoable getUndoable() throws IOException, ClassNotFoundException {
      if (this.op == null) {
         this.getLoggable();
      }

      return this.op instanceof Undoable ? (Undoable)this.op : null;
   }

   public boolean isCLR() {
      return (this.group & 4) != 0;
   }

   public boolean isFirst() {
      return (this.group & 1) != 0;
   }

   public boolean isComplete() {
      return (this.group & 2) != 0;
   }

   public boolean isPrepare() {
      return (this.group & 64) != 0;
   }

   public boolean requiresPrepareLocks() {
      return (this.group & 128) != 0;
   }

   public boolean isCommit() {
      return (this.group & 16) != 0;
   }

   public boolean isAbort() {
      return (this.group & 32) != 0;
   }

   public int group() {
      return this.group;
   }

   public boolean isChecksum() {
      return (this.group & 2048) != 0;
   }
}
