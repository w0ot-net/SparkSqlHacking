package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class DropViewNode extends DDLStatementNode {
   DropViewNode(TableName var1, ContextManager var2) {
      super(var1, var2);
   }

   String statementToString() {
      return "DROP VIEW";
   }

   public void bindStatement() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      CompilerContext var2 = this.getCompilerContext();
      TableDescriptor var3 = var1.getTableDescriptor(this.getRelativeName(), this.getSchemaDescriptor(), this.getLanguageConnectionContext().getTransactionCompile());
      if (var3 != null) {
         var2.createDependency(var3);
      }

   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getDropViewConstantAction(this.getFullName(), this.getRelativeName(), this.getSchemaDescriptor());
   }
}
