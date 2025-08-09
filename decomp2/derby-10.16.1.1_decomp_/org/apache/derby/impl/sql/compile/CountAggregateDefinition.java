package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.types.DataTypeDescriptor;

class CountAggregateDefinition implements AggregateDefinition {
   public CountAggregateDefinition() {
   }

   public final DataTypeDescriptor getAggregator(DataTypeDescriptor var1, StringBuffer var2) {
      var2.append("org.apache.derby.impl.sql.execute.CountAggregator");
      return DataTypeDescriptor.getBuiltInDataTypeDescriptor(4, false);
   }
}
