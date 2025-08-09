package org.apache.derby.impl.store.raw.xact;

import java.util.ArrayList;
import javax.transaction.xa.Xid;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.store.access.xa.XAResourceManager;
import org.apache.derby.iapi.store.access.xa.XAXactId;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

public class XactXAResourceManager implements XAResourceManager {
   private TransactionTable transaction_table;
   private RawStoreFactory rsf;

   public XactXAResourceManager(RawStoreFactory var1, TransactionTable var2) {
      this.rsf = var1;
      this.transaction_table = var2;
   }

   public void commit(ContextManager var1, Xid var2, boolean var3) throws StandardException {
      Transaction var4 = this.rsf.findUserTransaction(var1, "UserTransaction");
      if (var4 == null) {
         throw StandardException.newException("XSAX0.S", new Object[0]);
      } else {
         var4.xa_commit(var3);
      }
   }

   public ContextManager find(Xid var1) {
      return this.transaction_table.findTransactionContextByGlobalId(new GlobalXactId(var1.getFormatId(), var1.getGlobalTransactionId(), var1.getBranchQualifier()));
   }

   public void forget(ContextManager var1, Xid var2) throws StandardException {
      this.rsf.findUserTransaction(var1, "UserTransaction");
      throw StandardException.newException("XSAX0.S", new Object[0]);
   }

   public Xid[] recover(int var1) throws StandardException {
      XAXactId[] var5;
      if ((var1 & 16777216) != 0) {
         final ArrayList var3 = new ArrayList();
         TransactionTable.EntryVisitor var4 = new TransactionTable.EntryVisitor() {
            public boolean visit(TransactionTableEntry var1) {
               Xact var2 = var1.getXact();
               if (var2.isPrepared()) {
                  GlobalXactId var3x = (GlobalXactId)var2.getGlobalId();
                  var3.add(new XAXactId(var3x.getFormat_Id(), var3x.getGlobalTransactionId(), var3x.getBranchQualifier()));
               }

               return true;
            }
         };
         this.transaction_table.visitEntries(var4);
         var5 = new XAXactId[var3.size()];
         var5 = (XAXactId[])var3.toArray(var5);
      } else {
         var5 = new XAXactId[0];
      }

      return var5;
   }

   public void rollback(ContextManager var1, Xid var2) throws StandardException {
      Transaction var3 = this.rsf.findUserTransaction(var1, "UserTransaction");
      if (var3 == null) {
         throw StandardException.newException("XSAX0.S", new Object[0]);
      } else {
         var3.xa_rollback();
      }
   }
}
