package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.shared.common.error.StandardException;

public class AnyResultSet extends NoPutResultSetImpl {
   private ExecRow rowWithNulls;
   private StatementContext statementContext;
   public final NoPutResultSet source;
   private GeneratedMethod emptyRowFun;
   public int subqueryNumber;
   public int pointOfAttachment;

   public AnyResultSet(NoPutResultSet var1, Activation var2, GeneratedMethod var3, int var4, int var5, int var6, double var7, double var9) {
      super(var2, var4, var7, var9);
      this.source = var1;
      this.emptyRowFun = var3;
      this.subqueryNumber = var5;
      this.pointOfAttachment = var6;
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      if (this.isOpen) {
         this.reopenCore();
      } else {
         this.beginTime = this.getCurrentTimeMillis();
         this.source.openCore();
         if (this.statementContext == null) {
            this.statementContext = this.getLanguageConnectionContext().getStatementContext();
         }

         this.statementContext.setSubqueryResultSet(this.subqueryNumber, this, this.activation.getNumSubqueries());
         ++this.numOpens;
         this.isOpen = true;
         this.openTime += this.getElapsedMillis(this.beginTime);
      }
   }

   public void reopenCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.source.reopenCore();
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public void finish() throws StandardException {
      this.source.finish();
      this.finishAndRTS();
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         Object var1 = null;
         Object var2 = null;
         ExecRow var3 = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen) {
            ExecRow var4 = this.source.getNextRowCore();
            if (var4 != null) {
               var3 = var4;
            } else if (this.rowWithNulls == null) {
               this.rowWithNulls = (ExecRow)this.emptyRowFun.invoke(this.activation);
               var3 = this.rowWithNulls;
            } else {
               var3 = this.rowWithNulls;
            }
         }

         this.setCurrentRow(var3);
         ++this.rowsSeen;
         this.nextTime += this.getElapsedMillis(this.beginTime);
         return var3;
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         this.source.close();
         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public int getPointOfAttachment() {
      return this.pointOfAttachment;
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.source.getTimeSpent(1) : var2;
   }
}
