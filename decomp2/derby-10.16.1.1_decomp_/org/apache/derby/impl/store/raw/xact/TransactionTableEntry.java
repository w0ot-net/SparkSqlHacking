package org.apache.derby.impl.store.raw.xact;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.store.access.TransactionInfo;
import org.apache.derby.iapi.store.raw.GlobalTransactionId;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.TransactionId;

public class TransactionTableEntry implements Formatable, TransactionInfo, Cloneable {
   private TransactionId xid;
   private GlobalTransactionId gid;
   private LogInstant firstLog;
   private LogInstant lastLog;
   private int transactionStatus;
   private transient Xact myxact;
   private transient boolean update;
   private transient boolean recovery;
   private transient boolean needExclusion;
   private boolean isClone;
   private transient LanguageConnectionContext lcc;
   static final int UPDATE = 1;
   static final int RECOVERY = 2;
   static final int EXCLUDE = 4;

   TransactionTableEntry(Xact var1, TransactionId var2, int var3, int var4) {
      this.myxact = var1;
      this.xid = var2;
      this.transactionStatus = var3;
      this.update = (var4 & 1) != 0;
      this.needExclusion = (var4 & 4) != 0;
      this.recovery = (var4 & 2) != 0;
      if (this.recovery) {
         this.gid = var1.getGlobalId();
         this.firstLog = var1.getFirstLogInstant();
         this.lastLog = var1.getLastLogInstant();
      }

   }

   public TransactionTableEntry() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.xid);
      var1.writeObject(this.myxact.getGlobalId());
      var1.writeObject(this.myxact.getFirstLogInstant());
      var1.writeObject(this.myxact.getLastLogInstant());
      var1.writeInt(this.transactionStatus);
   }

   public void readExternal(ObjectInput var1) throws ClassNotFoundException, IOException {
      this.xid = (TransactionId)var1.readObject();
      this.gid = (GlobalTransactionId)var1.readObject();
      this.firstLog = (LogInstant)var1.readObject();
      this.lastLog = (LogInstant)var1.readObject();
      this.transactionStatus = var1.readInt();
      this.update = true;
      this.recovery = true;
      this.needExclusion = true;
   }

   void setXact(Xact var1) {
      this.myxact = var1;
   }

   public int getTypeFormatId() {
      return 261;
   }

   public String toString() {
      return null;
   }

   void updateTransactionStatus(Xact var1, int var2, int var3) {
      this.update = (var3 & 1) != 0;
   }

   void removeUpdateTransaction() {
      this.update = false;
      this.transactionStatus = 0;
   }

   void unsetRecoveryStatus() {
      this.firstLog = null;
      this.recovery = false;
   }

   void prepareTransaction() {
      this.transactionStatus |= 2;
   }

   TransactionId getXid() {
      return this.xid;
   }

   public final GlobalTransactionId getGid() {
      if (this.gid != null) {
         return this.gid;
      } else {
         return this.myxact != null ? this.myxact.getGlobalId() : null;
      }
   }

   LogInstant getFirstLog() {
      if (this.firstLog != null) {
         return this.firstLog;
      } else {
         return this.myxact != null ? this.myxact.getFirstLogInstant() : null;
      }
   }

   LogInstant getLastLog() {
      if (this.lastLog != null) {
         return this.lastLog;
      } else {
         return this.myxact != null ? this.myxact.getLastLogInstant() : null;
      }
   }

   public final Xact getXact() {
      return this.myxact;
   }

   int getTransactionStatus() {
      return this.transactionStatus;
   }

   boolean isUpdate() {
      return this.update;
   }

   boolean isRecovery() {
      return this.recovery;
   }

   boolean isPrepared() {
      return (this.transactionStatus & 2) != 0;
   }

   public boolean needExclusion() {
      return this.needExclusion;
   }

   public String getTransactionIdString() {
      TransactionId var1 = this.myxact.getIdNoCheck();
      return var1 == null ? "CLOSED" : var1.toString();
   }

   public String getGlobalTransactionIdString() {
      GlobalTransactionId var1 = this.myxact.getGlobalId();
      return var1 == null ? null : var1.toString();
   }

   public String getUsernameString() {
      this.getlcc();
      return this.lcc == null ? null : this.lcc.getSessionUserId();
   }

   public String getTransactionTypeString() {
      if (this.myxact == null) {
         return null;
      } else {
         return this.myxact.getTransName() != null ? this.myxact.getTransName() : this.myxact.getContextId();
      }
   }

   public String getTransactionStatusString() {
      return this.myxact == null ? null : this.myxact.getState();
   }

   public String getStatementTextString() {
      this.getlcc();
      if (this.lcc != null) {
         StatementContext var1 = this.lcc.getStatementContext();
         if (var1 != null) {
            return var1.getStatementText();
         }
      }

      return null;
   }

   public String getFirstLogInstantString() {
      LogInstant var1 = this.myxact == null ? null : this.myxact.getFirstLogInstant();
      return var1 == null ? null : var1.toString();
   }

   private void getlcc() {
      if (this.lcc == null && this.myxact != null && this.myxact.xc != null) {
         XactContext var1 = this.myxact.xc;
         this.lcc = (LanguageConnectionContext)var1.getContextManager().getContext("LanguageConnectionContext");
      }

   }

   protected Object clone() {
      try {
         Object var1 = super.clone();
         ((TransactionTableEntry)var1).isClone = true;
         return var1;
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }
}
