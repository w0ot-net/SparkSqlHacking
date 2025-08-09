package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class RISetChecker {
   private GenericRIChecker[] checkers;
   LanguageConnectionContext lcc;

   public RISetChecker(LanguageConnectionContext var1, TransactionController var2, FKInfo[] var3) throws StandardException {
      if (var3 != null) {
         this.checkers = new GenericRIChecker[var3.length];
         this.lcc = var1;

         for(int var4 = 0; var4 < var3.length; ++var4) {
            this.checkers[var4] = (GenericRIChecker)(var3[var4].type == 1 ? new ForeignKeyRIChecker(var1, var2, var3[var4]) : new ReferencedKeyRIChecker(var1, var2, var3[var4]));
         }

      }
   }

   void reopen() throws StandardException {
   }

   public void doPKCheck(Activation var1, ExecRow var2, boolean var3, int var4) throws StandardException {
      if (this.checkers != null) {
         for(GenericRIChecker var8 : this.checkers) {
            if (var8 instanceof ReferencedKeyRIChecker) {
               var8.doCheck(var1, var2, var3, var4);
            }
         }

      }
   }

   public void postCheck() throws StandardException {
      if (this.checkers != null) {
         for(int var1 = 0; var1 < this.checkers.length; ++var1) {
            this.postCheck(var1);
         }

      }
   }

   public void postCheck(int var1) throws StandardException {
      if (this.checkers != null) {
         if (this.checkers[var1] instanceof ReferencedKeyRIChecker) {
            ((ReferencedKeyRIChecker)this.checkers[var1]).postCheck();
         }

      }
   }

   public void doFKCheck(Activation var1, ExecRow var2) throws StandardException {
      if (this.checkers != null) {
         for(int var3 = 0; var3 < this.checkers.length; ++var3) {
            if (this.checkers[var3] instanceof ForeignKeyRIChecker) {
               this.checkers[var3].doCheck(var1, var2, false, 0);
            }
         }

      }
   }

   public void doRICheck(Activation var1, int var2, ExecRow var3, boolean var4, int var5) throws StandardException {
      this.checkers[var2].doCheck(var1, var3, var4, var5);
   }

   public void close() throws StandardException {
      if (this.checkers != null) {
         for(int var1 = 0; var1 < this.checkers.length; ++var1) {
            this.checkers[var1].close();
         }

      }
   }
}
