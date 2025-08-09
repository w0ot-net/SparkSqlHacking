package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecRowBuilder;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class WindowResultSet extends NoPutResultSetImpl {
   private GeneratedMethod restriction = null;
   public NoPutResultSet source = null;
   public long restrictionTime;
   private FormatableBitSet referencedColumns;
   private ExecRow allocatedRow;
   private long rownumber;

   WindowResultSet(Activation var1, NoPutResultSet var2, int var3, int var4, int var5, GeneratedMethod var6, double var7, double var9) throws StandardException {
      super(var1, var4, var7, var9);
      this.restriction = var6;
      this.source = var2;
      this.rownumber = 0L;
      ExecPreparedStatement var11 = var1.getPreparedStatement();
      this.allocatedRow = ((ExecRowBuilder)var11.getSavedObject(var3)).build(var1.getExecutionFactory());
      if (var5 != -1) {
         this.referencedColumns = (FormatableBitSet)var11.getSavedObject(var5);
      }

      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.source.openCore();
      this.isOpen = true;
      this.rownumber = 0L;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public void reopenCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.source.reopenCore();
      this.rownumber = 0L;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         Object var1 = null;
         ExecRow var2 = null;
         boolean var3 = false;
         long var5 = 0L;
         this.beginTime = this.getCurrentTimeMillis();
         if (!this.isOpen) {
            throw StandardException.newException("XCL16.S", new Object[]{"next"});
         } else {
            Object var7 = null;

            do {
               var8 = this.source.getNextRowCore();
               if (var8 != null) {
                  ++this.rownumber;
                  ExecRow var10 = this.getAllocatedRow();
                  this.populateFromSourceRow(var8, var10);
                  this.setCurrentRow(var10);
                  DataValueDescriptor var4 = (DataValueDescriptor)(this.restriction == null ? null : this.restriction.invoke(this.activation));
                  this.restrictionTime += this.getElapsedMillis(var5);
                  var3 = var4 == null || !var4.isNull() && var4.getBoolean();
                  if (!var3) {
                     ++this.rowsFiltered;
                     this.clearCurrentRow();
                  }

                  ++this.rowsSeen;
                  var2 = this.currentRow;
               } else {
                  this.clearCurrentRow();
                  var2 = null;
               }
            } while(var8 != null && !var3);

            this.nextTime += this.getElapsedMillis(this.beginTime);
            return var2;
         }
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

   public void populateFromSourceRow(ExecRow var1, ExecRow var2) throws StandardException {
      int var3 = 1;

      try {
         DataValueDescriptor[] var4 = var2.getRowArray();

         for(int var5 = 0; var5 < var4.length; ++var5) {
            if (this.referencedColumns != null && !this.referencedColumns.get(var5)) {
               var4[var5].setValue(this.rownumber);
            } else {
               var2.setColumn(var5 + 1, var1.getColumn(var3));
               ++var3;
            }
         }

      } catch (StandardException var6) {
         throw var6;
      } catch (Throwable var7) {
         throw StandardException.unexpectedUserException(var7);
      }
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.source.getTimeSpent(1) : var2;
   }

   private ExecRow getAllocatedRow() throws StandardException {
      return this.allocatedRow;
   }
}
