package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.io.StreamStorable;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.RowSource;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLRef;
import org.apache.derby.shared.common.error.StandardException;

class MergeResultSet extends NoRowsResultSetImpl {
   private NoPutResultSet _drivingLeftJoin;
   private MergeConstantAction _constants;
   private ExecRow _row;
   private long _rowCount;
   private TemporaryRowHolderImpl[] _thenRows;
   private BackingStoreHashtable _subjectRowIDs;
   private int _numOpens;

   MergeResultSet(NoPutResultSet var1, Activation var2) throws StandardException {
      super(var2);
      this._drivingLeftJoin = var1;
      this._constants = (MergeConstantAction)var2.getConstantAction();
      this._thenRows = new TemporaryRowHolderImpl[this._constants.matchingClauseCount()];
   }

   public final long modifiedRowCount() {
      return this._rowCount + RowUtil.getRowCountBase();
   }

   public void open() throws StandardException {
      this.setup();
      if (this._numOpens++ == 0) {
         this._drivingLeftJoin.openCore();
      } else {
         this._drivingLeftJoin.reopenCore();
      }

      boolean var1 = this.collectAffectedRows();
      if (!var1) {
         this.activation.addWarning(StandardException.newWarning("02000", new Object[0]));
      }

      int var2 = this._constants.matchingClauseCount();

      for(int var3 = 0; var3 < var2; ++var3) {
         this._constants.getMatchingClause(var3).executeConstantAction(this.activation, this._thenRows[var3]);
      }

      this.cleanUp();
      this.endTime = this.getCurrentTimeMillis();
   }

   void setup() throws StandardException {
      super.setup();
      int var1 = this._constants.matchingClauseCount();

      for(int var2 = 0; var2 < var1; ++var2) {
         this._constants.getMatchingClause(var2).init();
      }

      this._rowCount = 0L;
   }

   public void close() throws StandardException {
      this.close(false);
   }

   public void cleanUp() throws StandardException {
      int var1 = this._constants.matchingClauseCount();

      for(int var2 = 0; var2 < var1; ++var2) {
         TemporaryRowHolderImpl var3 = this._thenRows[var2];
         if (var3 != null) {
            var3.close();
            this._thenRows[var2] = null;
         }

         this._constants.getMatchingClause(var2).cleanUp();
      }

      if (this._drivingLeftJoin != null) {
         this._drivingLeftJoin.close();
      }

      if (this._subjectRowIDs != null) {
         this._subjectRowIDs.close();
         this._subjectRowIDs = null;
      }

      this._numOpens = 0;
   }

   public void finish() throws StandardException {
      if (this._drivingLeftJoin != null) {
         this._drivingLeftJoin.finish();
      }

      super.finish();
   }

   boolean collectAffectedRows() throws StandardException {
      boolean var2 = false;

      while(true) {
         this._row = this._drivingLeftJoin.getNextRowCore();
         if (this._row == null) {
            return var2;
         }

         var2 = true;
         DataValueDescriptor var1 = this._row.getColumn(this._row.nColumns());
         SQLRef var3 = null;
         boolean var4 = false;
         if (var1 != null && !var1.isNull()) {
            var4 = true;
            var3 = new SQLRef((RowLocation)var1.getObject());
            this._row.setColumn(this._row.nColumns(), var3);
         }

         MatchingClauseConstantAction var5 = null;
         int var6 = this._constants.matchingClauseCount();

         int var7;
         for(var7 = 0; var7 < var6; ++var7) {
            MatchingClauseConstantAction var8 = this._constants.getMatchingClause(var7);
            boolean var9 = false;
            switch (var8.clauseType()) {
               case 1:
               case 2:
                  var9 = true;
            }

            boolean var10 = var4 == var9;
            if (var10 && var8.evaluateRefinementClause(this.activation)) {
               var5 = var8;
               break;
            }
         }

         if (var5 != null) {
            if (var3 != null) {
               this.addSubjectRow(var3);
            }

            for(int var11 = 0; var11 < this._row.nColumns(); ++var11) {
               DataValueDescriptor var12 = this._row.getColumn(var11 + 1);
               if (var12 instanceof StreamStorable && var12.hasStream()) {
                  this._row.setColumn(var11 + 1, var12.cloneValue(true));
               }
            }

            this._thenRows[var7] = var5.bufferThenRow(this.activation, this._thenRows[var7], this._row);
            ++this._rowCount;
         }
      }
   }

   private void addSubjectRow(SQLRef var1) throws StandardException {
      if (this._subjectRowIDs == null) {
         this.createSubjectRowIDhashtable();
      }

      if (this._subjectRowIDs.get(var1) != null) {
         throw StandardException.newException("21000.S.1", new Object[0]);
      } else {
         DataValueDescriptor[] var2 = new DataValueDescriptor[]{var1};
         this._subjectRowIDs.putRow(true, var2, (RowLocation)null);
      }
   }

   private void createSubjectRowIDhashtable() throws StandardException {
      int[] var1 = new int[]{0};
      this._subjectRowIDs = new BackingStoreHashtable(this.getActivation().getLanguageConnectionContext().getTransactionExecute(), (RowSource)null, var1, false, -1L, -1L, -1, -1.0F, false, false);
   }
}
