package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class OrderByNode extends SingleChildResultSetNode {
   OrderByList orderByList;

   OrderByNode(ResultSetNode var1, OrderByList var2, Properties var3, ContextManager var4) throws StandardException {
      super(var1, var3, var4);
      this.orderByList = var2;
      ResultColumnList var5 = var1.getResultColumns().copyListAndObjects();
      this.setResultColumns(var1.getResultColumns());
      var1.setResultColumns(var5);
      this.getResultColumns().genVirtualColumnNodes(this, var5);
   }

   void printSubNodes(int var1) {
   }

   ResultColumnDescriptor[] makeResultDescriptors() {
      return this.childResult.makeResultDescriptors();
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.getCostEstimate() == null) {
         this.setCostEstimate(this.childResult.getFinalCostEstimate());
      }

      this.orderByList.generate(var1, var2, this.childResult);
      this.setResultSetNumber(this.orderByList.getResultSetNumber());
      this.getResultColumns().setResultSetNumber(this.getResultSetNumber());
   }
}
