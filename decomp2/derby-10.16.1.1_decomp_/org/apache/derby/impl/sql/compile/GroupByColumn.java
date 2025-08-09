package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class GroupByColumn extends OrderedColumn {
   private ValueNode columnExpression;

   GroupByColumn(ValueNode var1, ContextManager var2) {
      super(var2);
      this.columnExpression = var1;
   }

   void printSubNodes(int var1) {
   }

   String getColumnName() {
      return this.columnExpression.getColumnName();
   }

   void bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      int var4 = this.orReliability(16384);
      this.columnExpression = this.columnExpression.bindExpression(var1, var2, var3);
      this.getCompilerContext().setReliability(var4);
      if (this.columnExpression.isParameterNode()) {
         throw StandardException.newException("42Y36", new Object[]{this.columnExpression});
      } else {
         TypeId var5 = this.columnExpression.getTypeId();
         if (!var5.orderable(this.getClassFactory())) {
            throw StandardException.newException("X0X67.S", new Object[]{var5.getSQLTypeName()});
         }
      }
   }

   ValueNode getColumnExpression() {
      return this.columnExpression;
   }

   void setColumnExpression(ValueNode var1) {
      this.columnExpression = var1;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.columnExpression != null) {
         this.columnExpression = (ValueNode)this.columnExpression.accept(var1);
      }

   }
}
