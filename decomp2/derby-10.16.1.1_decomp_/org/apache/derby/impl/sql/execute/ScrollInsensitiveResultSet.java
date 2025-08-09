package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.RowSource;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.iapi.types.SQLInteger;
import org.apache.derby.shared.common.error.StandardException;

public class ScrollInsensitiveResultSet extends NoPutResultSetImpl implements CursorResultSet {
   public NoPutResultSet source;
   private int sourceRowWidth;
   private BackingStoreHashtable ht;
   private ExecRow resultRow;
   private int positionInSource;
   private int currentPosition;
   private int lastPosition;
   private boolean seenFirst;
   private boolean seenLast;
   private boolean beforeFirst = true;
   private boolean afterLast;
   public int numFromHashTable;
   public int numToHashTable;
   private long maxRows;
   private boolean keepAfterCommit;
   private int extraColumns;
   private SQLInteger positionInHashTable;
   private CursorResultSet target;
   private boolean needsRepositioning;
   private static final int POS_ROWLOCATION = 1;
   private static final int POS_ROWDELETED = 2;
   private static final int POS_ROWUPDATED = 3;
   private static final int LAST_EXTRA_COLUMN = 3;

   public ScrollInsensitiveResultSet(NoPutResultSet var1, Activation var2, int var3, int var4, double var5, double var7) throws StandardException {
      super(var2, var3, var5, var7);
      this.source = var1;
      this.sourceRowWidth = var4;
      this.keepAfterCommit = var2.getResultSetHoldability();
      this.maxRows = var2.getMaxRows();
      this.positionInHashTable = new SQLInteger();
      this.needsRepositioning = false;
      if (this.isForUpdate()) {
         this.target = ((CursorActivation)var2).getTargetResultSet();
         this.extraColumns = 4;
      } else {
         this.target = null;
         this.extraColumns = 1;
      }

      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.source.openCore();
      this.isOpen = true;
      ++this.numOpens;
      int[] var1 = new int[]{0};
      this.ht = new BackingStoreHashtable(this.getTransactionController(), (RowSource)null, var1, false, -1L, -1L, -1, -1.0F, false, this.keepAfterCommit);
      this.lastPosition = 0;
      this.needsRepositioning = false;
      this.numFromHashTable = 0;
      this.numToHashTable = 0;
      this.positionInSource = 0;
      this.seenFirst = false;
      this.seenLast = false;
      this.maxRows = this.activation.getMaxRows();
      this.openTime += this.getElapsedMillis(this.beginTime);
      this.setBeforeFirstRow();
   }

   public void reopenCore() throws StandardException {
      boolean var1 = true;
      this.beginTime = this.getCurrentTimeMillis();
      this.setBeforeFirstRow();
   }

   public ExecRow getAbsoluteRow(int var1) throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"absolute"});
      } else {
         this.attachStatementContext();
         if (var1 == 0) {
            this.setBeforeFirstRow();
            return null;
         } else if (this.seenLast && var1 > this.lastPosition) {
            return this.setAfterLastRow();
         } else if (var1 <= 0) {
            if (var1 < 0) {
               if (!this.seenLast) {
                  this.getLastRow();
               }

               int var4 = this.lastPosition + 1;
               return var4 + var1 > 0 ? this.getRowFromHashTable(var4 + var1) : this.setBeforeFirstRow();
            } else {
               this.currentRow = null;
               return null;
            }
         } else if (var1 <= this.positionInSource) {
            return this.getRowFromHashTable(var1);
         } else {
            int var2 = var1 - this.positionInSource;

            ExecRow var3;
            for(var3 = null; var2 > 0 && (var3 = this.getNextRowFromSource()) != null; --var2) {
            }

            if (var3 != null) {
               var3 = this.getRowFromHashTable(var1);
            }

            this.currentRow = var3;
            return var3;
         }
      }
   }

   public ExecRow getRelativeRow(int var1) throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"relative"});
      } else {
         this.attachStatementContext();
         if (var1 == 0) {
            return !this.beforeFirst && !this.afterLast && this.currentPosition != 0 ? this.getRowFromHashTable(this.currentPosition) : null;
         } else if (var1 > 0) {
            return this.getAbsoluteRow(this.currentPosition + var1);
         } else {
            return this.currentPosition + var1 < 0 ? this.setBeforeFirstRow() : this.getAbsoluteRow(this.currentPosition + var1);
         }
      }
   }

   public ExecRow setBeforeFirstRow() {
      this.currentPosition = 0;
      this.beforeFirst = true;
      this.afterLast = false;
      this.currentRow = null;
      return null;
   }

   public ExecRow getFirstRow() throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"first"});
      } else if (this.seenFirst) {
         return this.getRowFromHashTable(1);
      } else {
         this.attachStatementContext();
         return this.getNextRowCore();
      }
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecRow var1 = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (!this.isOpen) {
            throw StandardException.newException("XCL16.S", new Object[]{"next"});
         } else if (this.seenLast && this.currentPosition == this.lastPosition) {
            return this.setAfterLastRow();
         } else {
            if (this.currentPosition == this.positionInSource) {
               var1 = this.getNextRowFromSource();
               if (var1 != null) {
                  var1 = this.getRowFromHashTable(this.currentPosition);
               }
            } else if (this.currentPosition < this.positionInSource) {
               var1 = this.getRowFromHashTable(this.currentPosition + 1);
            } else {
               var1 = null;
            }

            if (var1 != null) {
               ++this.rowsSeen;
               this.afterLast = false;
            }

            this.setCurrentRow(var1);
            this.beforeFirst = false;
            this.nextTime += this.getElapsedMillis(this.beginTime);
            return var1;
         }
      }
   }

   public ExecRow getPreviousRow() throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"previous"});
      } else if (!this.beforeFirst && this.currentPosition != 0) {
         if (this.afterLast) {
            if (this.lastPosition == 0) {
               this.afterLast = false;
               this.beforeFirst = false;
               this.currentRow = null;
               return null;
            } else {
               return this.getRowFromHashTable(this.lastPosition);
            }
         } else {
            --this.currentPosition;
            if (this.currentPosition == 0) {
               this.setBeforeFirstRow();
               return null;
            } else {
               return this.getRowFromHashTable(this.currentPosition);
            }
         }
      } else {
         this.currentRow = null;
         return null;
      }
   }

   public ExecRow getLastRow() throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"next"});
      } else {
         if (!this.seenLast) {
            this.attachStatementContext();
            Object var1 = null;

            while(this.getNextRowFromSource() != null) {
            }
         }

         this.beforeFirst = false;
         this.afterLast = false;
         if (this.lastPosition == 0) {
            this.currentRow = null;
            return null;
         } else {
            return this.getRowFromHashTable(this.lastPosition);
         }
      }
   }

   public ExecRow setAfterLastRow() throws StandardException {
      if (!this.seenLast) {
         this.getLastRow();
      }

      if (this.lastPosition == 0) {
         this.currentPosition = 0;
         this.afterLast = false;
      } else {
         this.currentPosition = this.lastPosition + 1;
         this.afterLast = true;
      }

      this.beforeFirst = false;
      this.currentRow = null;
      return null;
   }

   public boolean checkRowPosition(int var1) throws StandardException {
      switch (var1) {
         case 101:
            if (!this.beforeFirst) {
               return false;
            } else if (this.seenFirst) {
               return true;
            } else {
               ExecRow var4 = this.getFirstRow();
               if (var4 == null) {
                  return false;
               }

               this.getPreviousRow();
               return true;
            }
         case 102:
            return this.currentPosition == 1;
         case 103:
            if (!this.beforeFirst && !this.afterLast && this.currentPosition != 0 && this.currentPosition >= this.positionInSource) {
               if (this.seenLast) {
                  return this.currentPosition == this.lastPosition;
               }

               int var2 = this.currentPosition;
               boolean var3 = this.getNextRowFromSource() == null;
               this.getRowFromHashTable(var2);
               return var3;
            }

            return false;
         case 104:
            return this.afterLast;
         default:
            return false;
      }
   }

   public int getRowNumber() {
      return this.currentRow == null ? 0 : this.currentPosition;
   }

   private ExecRow getNextRowFromSource() throws StandardException {
      ExecRow var1 = null;
      Object var2 = null;
      if (this.maxRows > 0L && this.maxRows == (long)this.positionInSource) {
         this.seenLast = true;
         this.lastPosition = this.positionInSource;
         this.afterLast = true;
         return null;
      } else {
         if (this.needsRepositioning) {
            this.positionInLastFetchedRow();
            this.needsRepositioning = false;
         }

         var1 = this.source.getNextRowCore();
         if (var1 != null) {
            this.seenFirst = true;
            this.beforeFirst = false;
            long var3 = this.getCurrentTimeMillis();
            if (this.resultRow == null) {
               this.resultRow = this.activation.getExecutionFactory().getValueRow(this.sourceRowWidth);
            }

            ++this.positionInSource;
            this.currentPosition = this.positionInSource;
            RowLocation var5 = null;
            if (this.source.isForUpdate()) {
               var5 = ((CursorResultSet)this.source).getRowLocation();
            }

            this.addRowToHashTable(var1, this.currentPosition, var5, false);
         } else {
            if (!this.seenLast) {
               this.lastPosition = this.positionInSource;
            }

            this.seenLast = true;
            if (this.positionInSource == 0) {
               this.afterLast = false;
            } else {
               this.afterLast = true;
               this.currentPosition = this.positionInSource + 1;
            }
         }

         return var1;
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.currentRow = null;
         this.source.close();
         if (this.ht != null) {
            this.ht.close();
            this.ht = null;
         }

         super.close();
      }

      this.setBeforeFirstRow();
      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public void finish() throws StandardException {
      this.source.finish();
      this.finishAndRTS();
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.source.getTimeSpent(1) : var2;
   }

   public RowLocation getRowLocation() throws StandardException {
      return ((CursorResultSet)this.source).getRowLocation();
   }

   public ExecRow getCurrentRow() throws StandardException {
      return this.isForUpdate() && this.isDeleted() ? null : this.currentRow;
   }

   private void addRowToHashTable(ExecRow var1, int var2, RowLocation var3, boolean var4) throws StandardException {
      DataValueDescriptor[] var5 = new DataValueDescriptor[this.sourceRowWidth + this.extraColumns];
      var5[0] = new SQLInteger(var2);
      if (this.isForUpdate()) {
         var5[1] = var3.cloneValue(false);
         var5[2] = new SQLBoolean(false);
         var5[3] = new SQLBoolean(var4);
      }

      DataValueDescriptor[] var6 = var1.getRowArray();
      System.arraycopy(var6, 0, var5, this.extraColumns, var6.length);
      this.ht.putRow(true, var5, (RowLocation)null);
      ++this.numToHashTable;
   }

   private ExecRow getRowFromHashTable(int var1) throws StandardException {
      this.positionInHashTable.setValue(var1);
      DataValueDescriptor[] var2 = this.getCurrentRowFromHashtable();
      DataValueDescriptor[] var3 = new DataValueDescriptor[var2.length - this.extraColumns];
      System.arraycopy(var2, this.extraColumns, var3, 0, var3.length);
      this.resultRow.setRowArray(var3);
      this.currentPosition = var1;
      ++this.numFromHashTable;
      if (this.resultRow != null) {
         this.beforeFirst = false;
         this.afterLast = false;
      }

      if (this.isForUpdate()) {
         RowLocation var4 = (RowLocation)var2[1];
         ((NoPutResultSet)this.target).setCurrentRow(this.resultRow);
         ((NoPutResultSet)this.target).positionScanAtRowLocation(var4);
         this.needsRepositioning = true;
      }

      this.setCurrentRow(this.resultRow);
      return this.resultRow;
   }

   private DataValueDescriptor[] getRowArrayFromHashTable(int var1) throws StandardException {
      this.positionInHashTable.setValue(var1);
      DataValueDescriptor[] var2 = this.getCurrentRowFromHashtable();
      DataValueDescriptor[] var3 = new DataValueDescriptor[var2.length - this.extraColumns];
      System.arraycopy(var2, this.extraColumns, var3, 0, var3.length);
      return var3;
   }

   private void positionInLastFetchedRow() throws StandardException {
      if (this.positionInSource > 0) {
         this.positionInHashTable.setValue(this.positionInSource);
         DataValueDescriptor[] var1 = this.getCurrentRowFromHashtable();
         RowLocation var2 = (RowLocation)var1[1];
         ((NoPutResultSet)this.target).positionScanAtRowLocation(var2);
         this.currentPosition = this.positionInSource;
      }

   }

   public void updateRow(ExecRow var1, RowChanger var2) throws StandardException {
      ProjectRestrictResultSet var3 = null;
      if (this.source instanceof ProjectRestrictResultSet) {
         var3 = (ProjectRestrictResultSet)this.source;
      } else if (this.source instanceof RowCountResultSet) {
         var3 = ((RowCountResultSet)this.source).getUnderlyingProjectRestrictRS();
      }

      this.positionInHashTable.setValue(this.currentPosition);
      DataValueDescriptor[] var4 = this.getCurrentRowFromHashtable();
      RowLocation var5 = (RowLocation)var4[1];
      int[] var6;
      if (var3 != null) {
         var6 = var3.getBaseProjectMapping();
      } else {
         int var7 = var4.length - 4;
         var6 = new int[var7];

         for(int var8 = 0; var8 < var7; ++var8) {
            var6[var8] = var8 + 1;
         }
      }

      ValueRow var11 = new ValueRow(var6.length);

      for(int var12 = 0; var12 < var6.length; ++var12) {
         int var9 = var2.findSelectedCol(var6[var12]);
         if (var9 > 0) {
            var11.setColumn(var12 + 1, var1.getColumn(var9));
         } else {
            var11.setColumn(var12 + 1, var4[4 + var12]);
         }
      }

      this.ht.remove(new SQLInteger(this.currentPosition));
      this.addRowToHashTable(var11, this.currentPosition, var5, true);
      DataValueDescriptor[] var13 = this.getRowArrayFromHashTable(this.currentPosition);

      for(int var14 = 0; var14 < var6.length; ++var14) {
         int var10 = var2.findSelectedCol(var6[var14]);
         if (var10 > 0) {
            var1.setColumn(var10, var13[var14]);
         }
      }

   }

   public void markRowAsDeleted() throws StandardException {
      this.positionInHashTable.setValue(this.currentPosition);
      DataValueDescriptor[] var1 = this.getCurrentRowFromHashtable();
      RowLocation var2 = (RowLocation)var1[1];
      this.ht.remove(new SQLInteger(this.currentPosition));
      ((SQLBoolean)var1[2]).setValue(true);

      for(int var3 = this.extraColumns; var3 < var1.length; ++var3) {
         var1[var3].setToNull();
      }

      this.ht.putRow(true, var1, (RowLocation)null);
   }

   public boolean isDeleted() throws StandardException {
      if (this.currentPosition <= this.positionInSource && this.currentPosition > 0) {
         this.positionInHashTable.setValue(this.currentPosition);
         DataValueDescriptor[] var1 = this.getCurrentRowFromHashtable();
         return var1[2].getBoolean();
      } else {
         return false;
      }
   }

   public boolean isUpdated() throws StandardException {
      if (this.currentPosition <= this.positionInSource && this.currentPosition > 0) {
         this.positionInHashTable.setValue(this.currentPosition);
         DataValueDescriptor[] var1 = this.getCurrentRowFromHashtable();
         return var1[3].getBoolean();
      } else {
         return false;
      }
   }

   public boolean isForUpdate() {
      return this.source.isForUpdate();
   }

   private DataValueDescriptor[] getCurrentRowFromHashtable() throws StandardException {
      return this.unpackHashValue(this.ht.get(this.positionInHashTable));
   }
}
