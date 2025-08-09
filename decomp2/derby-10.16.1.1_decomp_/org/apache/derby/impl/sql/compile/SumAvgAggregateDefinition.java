package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.sql.compile.TypeCompilerFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class SumAvgAggregateDefinition implements AggregateDefinition {
   private boolean isSum;

   public SumAvgAggregateDefinition() {
   }

   public final DataTypeDescriptor getAggregator(DataTypeDescriptor var1, StringBuffer var2) {
      try {
         TypeId var3 = var1.getTypeId();
         CompilerContext var4 = (CompilerContext)QueryTreeNode.getContext("CompilerContext");
         TypeCompilerFactory var5 = var4.getTypeCompilerFactory();
         TypeCompiler var6 = var5.getTypeCompiler(var3);
         if (var3.isNumericTypeId()) {
            var2.append(this.getAggregatorClassName());
            DataTypeDescriptor var7 = var6.resolveArithmeticOperation(var1, var1, this.getOperator());
            return var7.getNullabilityType(true);
         }
      } catch (StandardException var8) {
      }

      return null;
   }

   private String getAggregatorClassName() {
      return this.isSum ? "org.apache.derby.impl.sql.execute.SumAggregator" : "org.apache.derby.impl.sql.execute.AvgAggregator";
   }

   protected String getOperator() {
      return this.isSum ? "sum" : "avg";
   }

   final void setSumOrAvg(boolean var1) {
      this.isSum = var1;
   }
}
