package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class VirtualColumnNode extends ValueNode {
   private ResultSetNode sourceResultSet;
   private ResultColumn sourceColumn;
   int columnId;
   private boolean correlated = false;

   VirtualColumnNode(ResultSetNode var1, ResultColumn var2, int var3, ContextManager var4) throws StandardException {
      super(var4);
      this.sourceResultSet = var1;
      this.sourceColumn = var2;
      this.columnId = var3;
      this.setType(var2.getTypeServices());
   }

   void printSubNodes(int var1) {
   }

   ResultSetNode getSourceResultSet() {
      return this.sourceResultSet;
   }

   ResultColumn getSourceColumn() {
      return this.sourceColumn;
   }

   String getTableName() {
      return this.sourceColumn.getTableName();
   }

   String getSchemaName() throws StandardException {
      return this.sourceColumn.getSchemaName();
   }

   public boolean updatableByCursor() {
      return this.sourceColumn.updatableByCursor();
   }

   ResultColumn getSourceResultColumn() {
      return this.sourceColumn;
   }

   void setCorrelated() {
      this.correlated = true;
   }

   boolean getCorrelated() {
      return this.correlated;
   }

   boolean isCloneable() {
      return true;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      int var3 = this.sourceColumn.getResultSetNumber();
      if (this.sourceColumn.isRedundant()) {
         this.sourceColumn.getExpression().generateExpression(var1, var2);
      } else {
         var1.pushColumnReference(var2, var3, this.sourceColumn.getVirtualColumnId());
         var2.cast(this.sourceColumn.getTypeCompiler().interfaceName());
      }
   }

   protected int getOrderableVariantType() throws StandardException {
      return this.sourceColumn.getOrderableVariantType();
   }

   public final DataTypeDescriptor getTypeServices() {
      return this.sourceColumn.getTypeServices();
   }

   public final void setType(DataTypeDescriptor var1) throws StandardException {
      this.sourceColumn.setType(var1);
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (this.isSameNodeKind(var1)) {
         VirtualColumnNode var2 = (VirtualColumnNode)var1;
         return this.sourceColumn.isEquivalent(var2.sourceColumn);
      } else {
         return false;
      }
   }
}
