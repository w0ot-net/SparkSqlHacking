package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class RowCountResultSet extends NoPutResultSetImpl implements CursorResultSet {
   final NoPutResultSet source;
   private final boolean runTimeStatsOn;
   private long offset;
   private long fetchFirst;
   private final GeneratedMethod offsetMethod;
   private final GeneratedMethod fetchFirstMethod;
   private final boolean hasJDBClimitClause;
   private boolean virginal;
   private long rowsFetched;

   RowCountResultSet(NoPutResultSet var1, Activation var2, int var3, GeneratedMethod var4, GeneratedMethod var5, boolean var6, double var7, double var9) throws StandardException {
      super(var2, var3, var7, var9);
      this.offsetMethod = var4;
      this.fetchFirstMethod = var5;
      this.hasJDBClimitClause = var6;
      this.source = var1;
      this.virginal = true;
      this.rowsFetched = 0L;
      this.runTimeStatsOn = this.getLanguageConnectionContext().getRunTimeStatisticsMode();
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      boolean var1 = true;
      this.beginTime = this.getCurrentTimeMillis();
      this.source.openCore();
      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public void reopenCore() throws StandardException {
      boolean var1 = true;
      this.beginTime = this.getCurrentTimeMillis();
      this.source.reopenCore();
      this.virginal = true;
      this.rowsFetched = 0L;
      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecRow var1 = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (this.virginal) {
            if (this.offsetMethod != null) {
               DataValueDescriptor var2 = (DataValueDescriptor)this.offsetMethod.invoke(this.activation);
               if (!var2.isNotNull().getBoolean()) {
                  throw StandardException.newException("2201Z", new Object[]{"OFFSET"});
               }

               this.offset = var2.getLong();
               if (this.offset < 0L) {
                  throw StandardException.newException("2201X", new Object[]{Long.toString(this.offset)});
               }

               this.offset = var2.getLong();
            } else {
               this.offset = 0L;
            }

            if (this.fetchFirstMethod != null) {
               DataValueDescriptor var5 = (DataValueDescriptor)this.fetchFirstMethod.invoke(this.activation);
               if (!var5.isNotNull().getBoolean()) {
                  throw StandardException.newException("2201Z", new Object[]{"FETCH FIRST/NEXT"});
               }

               this.fetchFirst = var5.getLong();
               if (this.hasJDBClimitClause && this.fetchFirst == 0L) {
                  this.fetchFirst = Long.MAX_VALUE;
               }

               if (this.fetchFirst < 1L) {
                  throw StandardException.newException("2201W", new Object[]{Long.toString(this.fetchFirst)});
               }
            }

            if (this.offset > 0L) {
               this.virginal = false;
               long var6 = this.offset;

               while(true) {
                  var1 = this.source.getNextRowCore();
                  --var6;
                  if (var1 == null || var6 < 0L) {
                     break;
                  }

                  ++this.rowsFiltered;
               }
            } else if (this.fetchFirstMethod != null && this.rowsFetched >= this.fetchFirst) {
               var1 = null;
            } else {
               var1 = this.source.getNextRowCore();
            }
         } else if (this.fetchFirstMethod != null && this.rowsFetched >= this.fetchFirst) {
            var1 = null;
         } else {
            var1 = this.source.getNextRowCore();
         }

         if (var1 != null) {
            ++this.rowsFetched;
            ++this.rowsSeen;
         }

         this.setCurrentRow(var1);
         if (this.runTimeStatsOn) {
            if (!this.isTopResultSet) {
               StatementContext var7 = this.activation.getLanguageConnectionContext().getStatementContext();
               this.subqueryTrackingArray = var7.getSubqueryTrackingArray();
            }

            this.nextTime += this.getElapsedMillis(this.beginTime);
         }

         return var1;
      }
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.source.getTimeSpent(1) : var2;
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         this.source.close();
         super.close();
      }

      this.virginal = true;
      this.rowsFetched = 0L;
      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public void finish() throws StandardException {
      this.source.finish();
      this.finishAndRTS();
   }

   public final void clearCurrentRow() {
      this.currentRow = null;
      this.activation.clearCurrentRow(this.resultSetNumber);
      this.source.clearCurrentRow();
   }

   public RowLocation getRowLocation() throws StandardException {
      return ((CursorResultSet)this.source).getRowLocation();
   }

   public ExecRow getCurrentRow() throws StandardException {
      return ((CursorResultSet)this.source).getCurrentRow();
   }

   public boolean isForUpdate() {
      return this.source.isForUpdate();
   }

   public ProjectRestrictResultSet getUnderlyingProjectRestrictRS() {
      return this.source instanceof ProjectRestrictResultSet ? (ProjectRestrictResultSet)this.source : null;
   }
}
