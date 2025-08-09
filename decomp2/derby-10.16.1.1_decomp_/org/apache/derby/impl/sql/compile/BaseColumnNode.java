package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class BaseColumnNode extends ValueNode {
   private String columnName;
   private TableName tableName;

   BaseColumnNode(String var1, TableName var2, DataTypeDescriptor var3, ContextManager var4) throws StandardException {
      super(var4);
      this.columnName = var1;
      this.tableName = var2;
      this.setType(var3);
   }

   public String toString() {
      return "";
   }

   String getColumnName() {
      return this.columnName;
   }

   String getTableName() {
      return this.tableName != null ? this.tableName.getTableName() : null;
   }

   String getSchemaName() throws StandardException {
      return this.tableName != null ? this.tableName.getSchemaName() : null;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      throw StandardException.newException("42Z50", new Object[]{this.nodeHeader()});
   }

   protected int getOrderableVariantType() {
      return 1;
   }

   boolean isEquivalent(ValueNode var1) {
      if (!this.isSameNodeKind(var1)) {
         return false;
      } else {
         BaseColumnNode var2 = (BaseColumnNode)var1;
         return var2.tableName.equals(this.tableName) && var2.columnName.equals(this.columnName);
      }
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.tableName != null) {
         this.tableName = (TableName)this.tableName.accept(var1);
      }

   }
}
