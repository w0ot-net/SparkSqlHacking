package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class DropTriggerNode extends DDLStatementNode {
   private TableDescriptor td;

   DropTriggerNode(TableName var1, ContextManager var2) {
      super(var1, var2);
   }

   String statementToString() {
      return "DROP TRIGGER";
   }

   public void bindStatement() throws StandardException {
      CompilerContext var1 = this.getCompilerContext();
      DataDictionary var2 = this.getDataDictionary();
      SchemaDescriptor var3 = this.getSchemaDescriptor();
      TriggerDescriptor var4 = null;
      if (var3.getUUID() != null) {
         var4 = var2.getTriggerDescriptor(this.getRelativeName(), var3);
      }

      if (var4 == null) {
         throw StandardException.newException("42X94", new Object[]{"TRIGGER", this.getFullName()});
      } else {
         this.td = var4.getTableDescriptor();
         var1.createDependency(this.td);
         var1.createDependency(var4);
      }
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getDropTriggerConstantAction(this.getSchemaDescriptor(), this.getRelativeName(), this.td.getUUID());
   }
}
