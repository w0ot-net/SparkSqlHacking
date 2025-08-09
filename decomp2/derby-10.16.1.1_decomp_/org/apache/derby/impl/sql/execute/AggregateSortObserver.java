package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.UserDataValue;
import org.apache.derby.shared.common.error.StandardException;

class AggregateSortObserver extends BasicSortObserver {
   private final GenericAggregator[] aggsToProcess;
   private final GenericAggregator[] aggsToInitialize;
   private int firstAggregatorColumn;

   public AggregateSortObserver(boolean var1, GenericAggregator[] var2, GenericAggregator[] var3, ExecRow var4) {
      super(var1, false, var4, true);
      this.aggsToProcess = var2;
      this.aggsToInitialize = var3;
      if (var3.length > 0) {
         this.firstAggregatorColumn = var3[0].aggregatorColumnId;
      }

   }

   public DataValueDescriptor[] insertNonDuplicateKey(DataValueDescriptor[] var1) throws StandardException {
      DataValueDescriptor[] var2 = super.insertNonDuplicateKey(var1);
      if (this.aggsToInitialize.length > 0 && var2[this.firstAggregatorColumn].isNull()) {
         for(int var3 = 0; var3 < this.aggsToInitialize.length; ++var3) {
            GenericAggregator var4 = this.aggsToInitialize[var3];
            UserDataValue var5 = (UserDataValue)var2[var4.aggregatorColumnId];
            var5.setValue(var4.getAggregatorInstance());
            var4.accumulate((Object[])var2, (Object[])var2);
         }
      }

      return var2;
   }

   public DataValueDescriptor[] insertDuplicateKey(DataValueDescriptor[] var1, DataValueDescriptor[] var2) throws StandardException {
      if (this.aggsToProcess.length == 0) {
         return null;
      } else {
         for(int var3 = 0; var3 < this.aggsToProcess.length; ++var3) {
            GenericAggregator var4 = this.aggsToProcess[var3];
            if (var1[var4.getColumnId()].isNull()) {
               var4.accumulate((Object[])var1, (Object[])var2);
            } else {
               var4.merge((Object[])var1, (Object[])var2);
            }
         }

         return null;
      }
   }
}
