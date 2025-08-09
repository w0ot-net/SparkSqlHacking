package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class NormalizeResultSet extends NoPutResultSetImpl implements CursorResultSet {
   public NoPutResultSet source;
   private ExecRow normalizedRow;
   private int numCols;
   private int startCol;
   private final DataValueDescriptor[] cachedDestinations;
   private ResultDescription resultDescription;
   private DataTypeDescriptor[] desiredTypes;

   public NormalizeResultSet(NoPutResultSet var1, Activation var2, int var3, int var4, double var5, double var7, boolean var9) throws StandardException {
      super(var2, var3, var5, var7);
      this.source = var1;
      this.resultDescription = (ResultDescription)var2.getPreparedStatement().getSavedObject(var4);
      this.numCols = this.resultDescription.getColumnCount();
      this.startCol = computeStartColumn(var9, this.resultDescription);
      this.normalizedRow = var2.getExecutionFactory().getValueRow(this.numCols);
      this.cachedDestinations = new DataValueDescriptor[this.numCols];
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.source.openCore();
      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
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
         Object var1 = null;
         ExecRow var2 = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (!this.isOpen) {
            throw StandardException.newException("XCL16.S", new Object[]{"next"});
         } else {
            ExecRow var3 = this.source.getNextRowCore();
            if (var3 != null) {
               var2 = this.normalizeRow(var3);
               ++this.rowsSeen;
            }

            this.setCurrentRow(var2);
            this.nextTime += this.getElapsedMillis(this.beginTime);
            return var2;
         }
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.currentRow = null;
         this.source.close();
         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.source.getTimeSpent(1) : var2;
   }

   public RowLocation getRowLocation() throws StandardException {
      return ((CursorResultSet)this.source).getRowLocation();
   }

   public ExecRow getCurrentRow() {
      return this.currentRow;
   }

   public static int computeStartColumn(boolean var0, ResultDescription var1) {
      int var2 = var1.getColumnCount();
      return var0 ? (var2 - 1) / 2 + 1 : 1;
   }

   public static DataValueDescriptor normalizeColumn(DataTypeDescriptor var0, ExecRow var1, int var2, DataValueDescriptor var3, ResultDescription var4) throws StandardException {
      DataValueDescriptor var5 = var1.getColumn(var2);

      try {
         DataValueDescriptor var6 = var0.normalize(var5, var3);
         return var6;
      } catch (StandardException var8) {
         if (var8.getMessageId().startsWith("23502")) {
            ResultColumnDescriptor var7 = var4.getColumnDescriptor(var2);
            throw StandardException.newException("23502", new Object[]{var7.getName()});
         } else {
            throw var8;
         }
      }
   }

   private ExecRow normalizeRow(ExecRow var1) throws StandardException {
      int var2 = this.resultDescription.getColumnCount();

      for(int var3 = 1; var3 <= var2; ++var3) {
         DataValueDescriptor var4 = var1.getColumn(var3);
         if (var4 != null) {
            DataValueDescriptor var5;
            if (var3 < this.startCol) {
               var5 = var4;
            } else {
               var5 = normalizeColumn(this.getDesiredType(var3), var1, var3, this.getCachedDestination(var3), this.resultDescription);
            }

            this.normalizedRow.setColumn(var3, var5);
         }
      }

      return this.normalizedRow;
   }

   private DataValueDescriptor getCachedDestination(int var1) throws StandardException {
      int var2 = var1 - 1;
      if (this.cachedDestinations[var2] == null) {
         this.cachedDestinations[var2] = this.getDesiredType(var1).getNull();
      }

      return this.cachedDestinations[var2];
   }

   private DataTypeDescriptor getDesiredType(int var1) {
      if (this.desiredTypes == null) {
         this.desiredTypes = this.fetchResultTypes(this.resultDescription);
      }

      return this.desiredTypes[var1 - 1];
   }

   private DataTypeDescriptor[] fetchResultTypes(ResultDescription var1) {
      int var2 = var1.getColumnCount();
      DataTypeDescriptor[] var3 = new DataTypeDescriptor[var2];

      for(int var4 = 1; var4 <= var2; ++var4) {
         ResultColumnDescriptor var5 = var1.getColumnDescriptor(var4);
         DataTypeDescriptor var6 = var5.getType();
         var3[var4 - 1] = var6;
      }

      return var3;
   }

   public void updateRow(ExecRow var1, RowChanger var2) throws StandardException {
      this.source.updateRow(var1, var2);
   }

   public void markRowAsDeleted() throws StandardException {
      this.source.markRowAsDeleted();
   }
}
