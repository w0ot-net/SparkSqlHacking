package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SequenceDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class DropSequenceNode extends DDLStatementNode {
   private TableName dropItem;

   DropSequenceNode(TableName var1, ContextManager var2) {
      super(var1, var2);
      this.dropItem = var1;
   }

   public String statementToString() {
      return "DROP ".concat(this.dropItem.getTableName());
   }

   public void bindStatement() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      String var2 = this.getRelativeName();
      SequenceDescriptor var3 = null;
      SchemaDescriptor var4 = this.getSchemaDescriptor();
      if (var4.getUUID() != null) {
         var3 = var1.getSequenceDescriptor(var4, var2);
      }

      if (var3 == null) {
         throw StandardException.newException("42Y55", new Object[]{this.statementToString(), var2});
      } else {
         this.getCompilerContext().createDependency(var3);
      }
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getDropSequenceConstantAction(this.getSchemaDescriptor(), this.getRelativeName());
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.dropItem != null) {
         this.dropItem = (TableName)this.dropItem.accept(var1);
      }

   }
}
