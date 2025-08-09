package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class OnceResultSet extends NoPutResultSetImpl {
   public static final int DO_CARDINALITY_CHECK = 1;
   public static final int NO_CARDINALITY_CHECK = 2;
   public static final int UNIQUE_CARDINALITY_CHECK = 3;
   private ExecRow rowWithNulls;
   private StatementContext statementContext;
   public NoPutResultSet source;
   private GeneratedMethod emptyRowFun;
   private int cardinalityCheck;
   public int subqueryNumber;
   public int pointOfAttachment;

   public OnceResultSet(NoPutResultSet var1, Activation var2, GeneratedMethod var3, int var4, int var5, int var6, int var7, double var8, double var10) {
      super(var2, var5, var8, var10);
      this.source = var1;
      this.emptyRowFun = var3;
      this.cardinalityCheck = var4;
      this.subqueryNumber = var6;
      this.pointOfAttachment = var7;
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

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecRow var1 = null;
         ExecRow var2 = null;
         ExecRow var3 = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen) {
            var1 = this.source.getNextRowCore();
            if (var1 != null) {
               switch (this.cardinalityCheck) {
                  case 1:
                  case 2:
                     var1 = var1.getClone();
                     if (this.cardinalityCheck == 1) {
                        ExecRow var11 = this.source.getNextRowCore();
                        if (var11 != null) {
                           this.close();
                           StandardException var12 = StandardException.newException("21000", new Object[0]);
                           throw var12;
                        }
                     }

                     var3 = var1;
                     break;
                  case 3:
                     var1 = var1.getClone();
                     var2 = this.source.getNextRowCore();

                     for(DataValueDescriptor var4 = var1.getColumn(1); var2 != null; var2 = this.source.getNextRowCore()) {
                        DataValueDescriptor var5 = var2.getColumn(1);
                        if (!var4.compare(2, var5, true, true)) {
                           this.close();
                           StandardException var6 = StandardException.newException("21000", new Object[0]);
                           throw var6;
                        }
                     }

                     var3 = var1;
               }
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
