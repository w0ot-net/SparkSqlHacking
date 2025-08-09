package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class DropTableNode extends DDLStatementNode {
   private long conglomerateNumber;
   private int dropBehavior;
   private TableDescriptor td;

   DropTableNode(TableName var1, int var2, ContextManager var3) throws StandardException {
      super(var1, var3);
      this.dropBehavior = var2;
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      return "DROP TABLE";
   }

   public void bindStatement() throws StandardException {
      CompilerContext var1 = this.getCompilerContext();
      this.td = this.getTableDescriptor();
      this.conglomerateNumber = this.td.getHeapConglomerateId();
      ConglomerateDescriptor var2 = this.td.getConglomerateDescriptor(this.conglomerateNumber);
      var1.createDependency(this.td);
      var1.createDependency(var2);
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.isSessionSchema(this.td.getSchemaDescriptor());
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getDropTableConstantAction(this.getFullName(), this.getRelativeName(), this.getSchemaDescriptor(this.td.getTableType() != 3, true), this.conglomerateNumber, this.td.getUUID(), this.dropBehavior);
   }
}
