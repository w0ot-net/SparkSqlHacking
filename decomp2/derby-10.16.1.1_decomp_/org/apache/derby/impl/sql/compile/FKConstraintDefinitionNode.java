package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.impl.sql.execute.ConstraintInfo;
import org.apache.derby.shared.common.error.StandardException;

public final class FKConstraintDefinitionNode extends ConstraintDefinitionNode {
   TableName refTableName;
   ResultColumnList refRcl;
   SchemaDescriptor refTableSd;
   int refActionDeleteRule;
   int refActionUpdateRule;

   FKConstraintDefinitionNode(TableName var1, TableName var2, ResultColumnList var3, ResultColumnList var4, int[] var5, ContextManager var6) {
      super(var1, 6, var3, (Properties)null, (ValueNode)null, (String)null, 2, 5, var6);
      this.refRcl = var4;
      this.refTableName = var2;
      this.refActionDeleteRule = var5[0];
      this.refActionUpdateRule = var5[1];
   }

   void bind(DDLStatementNode var1, DataDictionary var2) throws StandardException {
      super.bind(var1, var2);
      this.refTableSd = this.getSchemaDescriptor(this.refTableName.getSchemaName());
      if (this.refTableSd.isSystemSchema()) {
         throw StandardException.newException("42Y08", new Object[0]);
      } else if (!this.refTableName.equals(var1.getObjectName())) {
         TableDescriptor var3 = this.getTableDescriptor(this.refTableName.getTableName(), this.refTableSd);
         if (var3 == null) {
            throw StandardException.newException("X0Y46.S", new Object[]{this.getConstraintMoniker(), this.refTableName.getTableName()});
         } else {
            this.getCompilerContext().pushCurrentPrivType(this.getPrivType());
            this.getCompilerContext().createDependency(var3);
            if (this.refRcl.size() == 0 && var3.getPrimaryKey() != null) {
               int[] var7 = var3.getPrimaryKey().getReferencedColumns();

               for(int var8 = 0; var8 < var7.length; ++var8) {
                  ColumnDescriptor var9 = var3.getColumnDescriptor(var7[var8]);
                  var9.setTableDescriptor(var3);
                  if (this.isPrivilegeCollectionRequired()) {
                     this.getCompilerContext().addRequiredColumnPriv(var9);
                  }
               }
            } else {
               for(ResultColumn var5 : this.refRcl) {
                  ColumnDescriptor var6 = var3.getColumnDescriptor(var5.getName());
                  if (var6 != null) {
                     var6.setTableDescriptor(var3);
                     if (this.isPrivilegeCollectionRequired()) {
                        this.getCompilerContext().addRequiredColumnPriv(var6);
                     }
                  }
               }
            }

            this.getCompilerContext().popCurrentPrivType();
         }
      }
   }

   ConstraintInfo getReferencedConstraintInfo() {
      return new ConstraintInfo(this.refTableName.getTableName(), this.refTableSd, this.refRcl.getColumnNames(), this.refActionDeleteRule, this.refActionUpdateRule);
   }

   public TableName getRefTableName() {
      return this.refTableName;
   }

   int getPrivType() {
      return 2;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.refTableName != null) {
         this.refTableName = (TableName)this.refTableName.accept(var1);
      }

   }
}
