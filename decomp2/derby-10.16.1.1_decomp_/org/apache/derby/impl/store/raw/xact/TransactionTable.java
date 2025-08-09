package org.apache.derby.impl.store.raw.xact;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.store.access.TransactionInfo;
import org.apache.derby.iapi.store.raw.GlobalTransactionId;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.store.raw.xact.TransactionId;

public class TransactionTable implements Formatable {
   private final ConcurrentHashMap trans = new ConcurrentHashMap();
   private TransactionId largestUpdateXactId;

   private TransactionTableEntry findTransactionEntry(TransactionId var1) {
      return (TransactionTableEntry)this.trans.get(var1);
   }

   void visitEntries(EntryVisitor var1) {
      for(Object var3 : this.trans.values()) {
         if (!var1.visit((TransactionTableEntry)var3)) {
            break;
         }
      }

   }

   void add(Xact var1, boolean var2) {
      TransactionId var3 = var1.getId();
      TransactionTableEntry var4 = new TransactionTableEntry(var1, var3, 0, var2 ? 4 : 0);
      synchronized(this) {
         this.trans.put(var3, var4);
      }
   }

   boolean remove(TransactionId var1) {
      TransactionTableEntry var2 = (TransactionTableEntry)this.trans.remove(var1);
      return var2 == null || var2.needExclusion();
   }

   public void addUpdateTransaction(TransactionId var1, RawTransaction var2, int var3) {
      synchronized(this) {
         TransactionTableEntry var5 = this.findTransactionEntry(var1);
         if (var5 != null) {
            var5.updateTransactionStatus((Xact)var2, var3, 1);
         } else {
            var5 = new TransactionTableEntry((Xact)var2, var1, var3, 7);
            this.trans.put(var1, var5);
         }

         if (XactId.compare(var5.getXid(), this.largestUpdateXactId) > 0L) {
            this.largestUpdateXactId = var5.getXid();
         }

      }
   }

   void removeUpdateTransaction(TransactionId var1) {
      synchronized(this) {
         TransactionTableEntry var3 = this.findTransactionEntry(var1);
         var3.removeUpdateTransaction();
         if (var3.isRecovery()) {
            this.remove(var1);
         }

      }
   }

   void prepareTransaction(TransactionId var1) {
      TransactionTableEntry var2 = this.findTransactionEntry(var1);
      var2.prepareTransaction();
   }

   public ContextManager findTransactionContextByGlobalId(GlobalXactId var1) {
      for(TransactionTableEntry var3 : this.trans.values()) {
         GlobalTransactionId var4 = var3.getGid();
         if (var4 != null && var4.equals(var1)) {
            return var3.getXact().getContextManager();
         }
      }

      return null;
   }

   boolean hasActiveUpdateTransaction() {
      synchronized(this) {
         for(TransactionTableEntry var3 : this.trans.values()) {
            if (var3.isUpdate()) {
               return true;
            }
         }

         return false;
      }
   }

   public int getTypeFormatId() {
      return 262;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      synchronized(this) {
         int var3 = 0;

         for(TransactionTableEntry var5 : this.trans.values()) {
            if (var5.isUpdate()) {
               ++var3;
            }
         }

         CompressedNumber.writeInt((DataOutput)var1, var3);
         if (var3 > 0) {
            boolean var9 = false;

            for(TransactionTableEntry var6 : this.trans.values()) {
               if (var6.isUpdate()) {
                  var1.writeObject(var6);
               }
            }
         }

      }
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      int var2 = CompressedNumber.readInt((DataInput)var1);
      if (var2 != 0) {
         for(int var3 = 0; var3 < var2; ++var3) {
            TransactionTableEntry var4 = (TransactionTableEntry)var1.readObject();
            this.trans.put(var4.getXid(), var4);
            if (var4.isUpdate() && XactId.compare(var4.getXid(), this.largestUpdateXactId) > 0L) {
               this.largestUpdateXactId = var4.getXid();
            }
         }

      }
   }

   public TransactionId largestUpdateXactId() {
      return this.largestUpdateXactId;
   }

   public boolean hasRollbackFirstTransaction() {
      for(TransactionTableEntry var2 : this.trans.values()) {
         if (var2 != null && var2.isRecovery() && (var2.getTransactionStatus() & 16) != 0) {
            return true;
         }
      }

      return false;
   }

   public boolean hasPreparedRecoveredXact() {
      return this.hasPreparedXact(true);
   }

   public boolean hasPreparedXact() {
      return this.hasPreparedXact(false);
   }

   private boolean hasPreparedXact(boolean var1) {
      for(TransactionTableEntry var3 : this.trans.values()) {
         if (var3 != null && (var3.getTransactionStatus() & 2) != 0) {
            if (!var1) {
               return true;
            }

            if (var3.isRecovery()) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean getMostRecentRollbackFirstTransaction(RawTransaction var1) {
      if (this.trans.isEmpty()) {
         return this.findAndAssumeTransaction((TransactionId)null, var1);
      } else {
         TransactionId var2 = null;

         for(TransactionTableEntry var4 : this.trans.values()) {
            if (var4 != null && var4.isUpdate() && var4.isRecovery() && (var4.getTransactionStatus() & 16) != 0 && (var2 == null || XactId.compare(var2, var4.getXid()) < 0L)) {
               var2 = var4.getXid();
            }
         }

         if (var2 == null) {
            return this.findAndAssumeTransaction(var2, var1);
         } else {
            this.findAndAssumeTransaction(var2, var1);
            return true;
         }
      }
   }

   public boolean getMostRecentTransactionForRollback(RawTransaction var1) {
      TransactionId var2 = null;
      if (!this.trans.isEmpty()) {
         for(TransactionTableEntry var4 : this.trans.values()) {
            if (var4 != null && var4.isUpdate() && var4.isRecovery() && !var4.isPrepared() && (var2 == null || XactId.compare(var2, var4.getXid()) < 0L)) {
               var2 = var4.getXid();
            }
         }
      }

      return this.findAndAssumeTransaction(var2, var1);
   }

   public boolean getMostRecentPreparedRecoveredXact(RawTransaction var1) {
      TransactionTableEntry var2 = null;
      if (!this.trans.isEmpty()) {
         TransactionId var3 = null;
         Object var4 = null;

         for(TransactionTableEntry var6 : this.trans.values()) {
            if (var6 != null && var6.isRecovery() && var6.isPrepared() && (var3 == null || XactId.compare(var3, var6.getXid()) < 0L)) {
               var2 = var6;
               var3 = var6.getXid();
               GlobalTransactionId var7 = var6.getGid();
            }
         }

         if (var2 != null) {
            TransactionTableEntry var10000 = (TransactionTableEntry)this.trans.remove(var1.getId());
            ((Xact)var1).assumeGlobalXactIdentity(var2);
            var2.unsetRecoveryStatus();
         }
      }

      return var2 != null;
   }

   public LogInstant getFirstLogInstant() {
      LogInstant var1 = null;

      for(TransactionTableEntry var3 : this.trans.values()) {
         if (var3.isUpdate() && (var1 == null || var3.getFirstLog().lessThan(var1))) {
            var1 = var3.getFirstLog();
         }
      }

      return var1;
   }

   boolean findAndAssumeTransaction(TransactionId var1, RawTransaction var2) {
      TransactionTableEntry var3 = null;
      if (var1 != null && !this.trans.isEmpty()) {
         var3 = this.findTransactionEntry(var1);
      }

      ((Xact)var2).assumeIdentity(var3);
      return var3 != null;
   }

   public TransactionInfo[] getTransactionInfo() {
      if (this.trans.isEmpty()) {
         return null;
      } else {
         ArrayList var1 = new ArrayList();

         for(TransactionTableEntry var3 : this.trans.values()) {
            var1.add((TransactionTableEntry)var3.clone());
         }

         return (TransactionInfo[])var1.toArray(new TransactionTableEntry[var1.size()]);
      }
   }

   public String toString() {
      return null;
   }

   interface EntryVisitor {
      boolean visit(TransactionTableEntry var1);
   }
}
