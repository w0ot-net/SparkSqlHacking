package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.execute.ExecAggregator;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.NumberDataValue;
import org.apache.derby.shared.common.error.StandardException;

public class SumAggregator extends OrderableAggregator {
   protected void accumulate(DataValueDescriptor var1) throws StandardException {
      if (this.value == null) {
         this.value = var1.cloneValue(false);
      } else {
         NumberDataValue var2 = (NumberDataValue)var1;
         NumberDataValue var3 = (NumberDataValue)this.value;
         this.value = var3.plus(var2, var3, var3);
      }

   }

   public ExecAggregator newAggregator() {
      return new SumAggregator();
   }

   public int getTypeFormatId() {
      return 154;
   }

   public String toString() {
      try {
         return "SumAggregator: " + this.value.getString();
      } catch (StandardException var2) {
         String var10000 = super.toString();
         return var10000 + ":" + var2.getMessage();
      }
   }
}
