package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class LockTableNode extends MiscellaneousStatementNode {
   private TableName tableName;
   private boolean exclusiveMode;
   private long conglomerateNumber;
   private TableDescriptor lockTableDescriptor;

   LockTableNode(TableName var1, boolean var2, ContextManager var3) {
      super(var3);
      this.tableName = var1;
      this.exclusiveMode = var2;
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      return "LOCK TABLE";
   }

   public void bindStatement() throws StandardException {
      CompilerContext var1 = this.getCompilerContext();
      String var4 = this.tableName.getSchemaName();
      SchemaDescriptor var3 = this.getSchemaDescriptor(var4);
      if (var3.isSystemSchema()) {
         throw StandardException.newException("42X62", new Object[]{this.statementToString(), var4});
      } else {
         this.lockTableDescriptor = this.getTableDescriptor(this.tableName.getTableName(), var3);
         if (this.lockTableDescriptor == null) {
            TableName var5 = this.resolveTableToSynonym(this.tableName);
            if (var5 == null) {
               throw StandardException.newException("42X05", new Object[]{this.tableName});
            }

            this.tableName = var5;
            var3 = this.getSchemaDescriptor(this.tableName.getSchemaName());
            this.lockTableDescriptor = this.getTableDescriptor(var5.getTableName(), var3);
            if (this.lockTableDescriptor == null) {
               throw StandardException.newException("42X05", new Object[]{this.tableName});
            }
         }

         if (this.lockTableDescriptor.getTableType() == 3) {
            throw StandardException.newException("42995", new Object[0]);
         } else {
            this.conglomerateNumber = this.lockTableDescriptor.getHeapConglomerateId();
            ConglomerateDescriptor var2 = this.lockTableDescriptor.getConglomerateDescriptor(this.conglomerateNumber);
            var1.createDependency(this.lockTableDescriptor);
            var1.createDependency(var2);
            if (this.isPrivilegeCollectionRequired()) {
               var1.pushCurrentPrivType(0);
               var1.addRequiredTablePriv(this.lockTableDescriptor);
               var1.popCurrentPrivType();
            }

         }
      }
   }

   public boolean referencesSessionSchema() throws StandardException {
      return isSessionSchema(this.lockTableDescriptor.getSchemaName());
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getLockTableConstantAction(this.tableName.getFullTableName(), this.conglomerateNumber, this.exclusiveMode);
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.tableName != null) {
         this.tableName = (TableName)this.tableName.accept(var1);
      }

   }
}
