package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class DropIndexNode extends DDLStatementNode {
   private ConglomerateDescriptor cd;
   private TableDescriptor td;

   DropIndexNode(TableName var1, ContextManager var2) {
      super(var1, var2);
   }

   String statementToString() {
      return "DROP INDEX";
   }

   public void bindStatement() throws StandardException {
      CompilerContext var1 = this.getCompilerContext();
      DataDictionary var2 = this.getDataDictionary();
      SchemaDescriptor var3 = this.getSchemaDescriptor();
      if (var3.getUUID() != null) {
         this.cd = var2.getConglomerateDescriptor(this.getRelativeName(), var3, false);
      }

      if (this.cd == null) {
         throw StandardException.newException("42X65", new Object[]{this.getFullName()});
      } else {
         this.td = this.getTableDescriptor(this.cd.getTableID());
         if (this.cd.isConstraint()) {
            ConstraintDescriptor var4 = var2.getConstraintDescriptor(this.td, this.cd.getUUID());
            if (var4 != null) {
               String var5 = var4.getConstraintName();
               throw StandardException.newException("42X84", new Object[]{this.getFullName(), var5});
            }
         }

         var1.createDependency(this.td);
         var1.createDependency(this.cd);
      }
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getDropIndexConstantAction(this.getFullName(), this.getRelativeName(), this.getRelativeName(), this.getSchemaDescriptor().getSchemaName(), this.td.getUUID(), this.td.getHeapConglomerateId());
   }
}
