package org.apache.derby.impl.sql.execute;

import java.lang.reflect.Constructor;
import org.apache.derby.iapi.services.io.Storable;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.execute.ExecAggregator;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.UserDataValue;
import org.apache.derby.shared.common.error.StandardException;

class GenericAggregator {
   private final AggregatorInfo aggInfo;
   int aggregatorColumnId;
   private int inputColumnId;
   private int resultColumnId;
   private final ClassFactory cf;
   private ExecAggregator cachedAggregator;

   GenericAggregator(AggregatorInfo var1, ClassFactory var2) {
      this.aggInfo = var1;
      this.aggregatorColumnId = var1.getAggregatorColNum();
      this.inputColumnId = var1.getInputColNum();
      this.resultColumnId = var1.getOutputColNum();
      this.cf = var2;
   }

   void initialize(ExecRow var1) throws StandardException {
      UserDataValue var2 = (UserDataValue)var1.getColumn(this.aggregatorColumnId + 1);
      ExecAggregator var3 = (ExecAggregator)var2.getObject();
      if (var3 == null) {
         var3 = this.getAggregatorInstance();
         var2.setValue(var3);
      }

   }

   void accumulate(ExecRow var1, ExecRow var2) throws StandardException {
      Object var3 = null;
      DataValueDescriptor var4 = var2.getColumn(this.aggregatorColumnId + 1);
      DataValueDescriptor var5 = var1.getColumn(this.inputColumnId + 1);
      this.accumulate(var5, var4);
   }

   void accumulate(Object[] var1, Object[] var2) throws StandardException {
      Object var3 = null;
      DataValueDescriptor var4 = (DataValueDescriptor)var2[this.aggregatorColumnId];
      DataValueDescriptor var5 = (DataValueDescriptor)var1[this.inputColumnId];
      this.accumulate(var5, var4);
   }

   void accumulate(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      ExecAggregator var3 = (ExecAggregator)var2.getObject();
      if (var3 == null) {
         var3 = this.getAggregatorInstance();
      }

      var3.accumulate(var1, this);
   }

   void merge(ExecRow var1, ExecRow var2) throws StandardException {
      DataValueDescriptor var3 = var2.getColumn(this.aggregatorColumnId + 1);
      DataValueDescriptor var4 = var1.getColumn(this.aggregatorColumnId + 1);
      this.merge((Storable)var4, (Storable)var3);
   }

   void merge(Object[] var1, Object[] var2) throws StandardException {
      DataValueDescriptor var3 = (DataValueDescriptor)var2[this.aggregatorColumnId];
      DataValueDescriptor var4 = (DataValueDescriptor)var1[this.aggregatorColumnId];
      this.merge((Storable)var4, (Storable)var3);
   }

   boolean finish(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(this.resultColumnId + 1);
      DataValueDescriptor var3 = var1.getColumn(this.aggregatorColumnId + 1);
      ExecAggregator var4 = (ExecAggregator)var3.getObject();
      if (var4 == null) {
         var4 = this.getAggregatorInstance();
      }

      DataValueDescriptor var5 = var4.getResult();
      if (var5 == null) {
         var2.setToNull();
      } else {
         var2.setValue(var5);
      }

      return var4.didEliminateNulls();
   }

   ExecAggregator getAggregatorInstance() throws StandardException {
      ExecAggregator var1;
      if (this.cachedAggregator == null) {
         try {
            Class var2 = this.cf.loadApplicationClass(this.aggInfo.getAggregatorClassName());
            Constructor var3 = var2.getConstructor();
            Object var4 = var3.newInstance();
            var1 = (ExecAggregator)var4;
            this.cachedAggregator = var1;
            var1.setup(this.cf, this.aggInfo.getAggregateName(), this.aggInfo.getResultDescription().getColumnInfo(0).getType());
         } catch (Exception var5) {
            throw StandardException.unexpectedUserException(var5);
         }
      } else {
         var1 = this.cachedAggregator.newAggregator();
      }

      return var1;
   }

   int getColumnId() {
      return this.aggregatorColumnId;
   }

   DataValueDescriptor getInputColumnValue(ExecRow var1) throws StandardException {
      return var1.getColumn(this.inputColumnId + 1);
   }

   void merge(Storable var1, Storable var2) throws StandardException {
      ExecAggregator var3 = (ExecAggregator)((UserDataValue)var1).getObject();
      ExecAggregator var4 = (ExecAggregator)((UserDataValue)var2).getObject();
      var4.merge(var3);
   }

   AggregatorInfo getAggregatorInfo() {
      return this.aggInfo;
   }
}
