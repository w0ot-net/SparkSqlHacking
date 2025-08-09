package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class RenameNode extends DDLStatementNode {
   protected TableName newTableName;
   protected String oldObjectName;
   protected String newObjectName;
   protected TableDescriptor td;
   private long conglomerateNumber;
   protected boolean usedAlterTable;
   protected int renamingWhat;

   RenameNode(Object var1, String var2, String var3, boolean var4, int var5, ContextManager var6) throws StandardException {
      super(var6);
      this.usedAlterTable = var4;
      this.renamingWhat = var5;
      switch (this.renamingWhat) {
         case 1:
            this.initAndCheck((TableName)var1);
            this.newTableName = this.makeTableName(this.getObjectName().getSchemaName(), var3);
            this.oldObjectName = null;
            this.newObjectName = this.newTableName.getTableName();
            break;
         case 2:
            if (var1 instanceof TableName var7) {
               ;
            } else {
               var7 = this.makeTableName((String)null, (String)var1);
            }

            this.initAndCheck(var7);
            this.oldObjectName = var2;
            this.newObjectName = var3;
            break;
         case 3:
            this.oldObjectName = var2;
            this.newObjectName = var3;
      }

   }

   public String toString() {
      return "";
   }

   String statementToString() {
      if (this.usedAlterTable) {
         return "ALTER TABLE";
      } else {
         switch (this.renamingWhat) {
            case 1 -> {
               return "RENAME TABLE";
            }
            case 2 -> {
               return "RENAME COLUMN";
            }
            case 3 -> {
               return "RENAME INDEX";
            }
            default -> {
               return "UNKNOWN";
            }
         }
      }
   }

   public void bindStatement() throws StandardException {
      CompilerContext var1 = this.getCompilerContext();
      DataDictionary var2 = this.getDataDictionary();
      SchemaDescriptor var4;
      if (this.renamingWhat == 3) {
         var4 = this.getSchemaDescriptor((String)null);
         ConglomerateDescriptor var6 = var2.getConglomerateDescriptor(this.oldObjectName, var4, false);
         if (var6 == null) {
            throw StandardException.newException("42X65", new Object[]{this.oldObjectName});
         }

         this.td = var2.getTableDescriptor(var6.getTableID());
         this.initAndCheck(this.makeTableName(this.td.getSchemaName(), this.td.getName()));
      } else {
         var4 = this.getSchemaDescriptor();
      }

      this.td = this.getTableDescriptor();
      if (this.td.getTableType() == 3) {
         throw StandardException.newException("42995", new Object[0]);
      } else {
         switch (this.renamingWhat) {
            case 1:
               TableDescriptor var8 = this.getTableDescriptor(this.newObjectName, var4);
               if (var8 != null) {
                  throw this.descriptorExistsException(var8, var4);
               }

               this.renameTableBind(var2);
               break;
            case 2:
               this.renameColumnBind(var2);
               break;
            case 3:
               ConglomerateDescriptor var7 = var2.getConglomerateDescriptor(this.newObjectName, var4, false);
               if (var7 != null) {
                  throw this.descriptorExistsException(var7, var4);
               }
         }

         this.conglomerateNumber = this.td.getHeapConglomerateId();
         ConglomerateDescriptor var3 = this.td.getConglomerateDescriptor(this.conglomerateNumber);
         var1.createDependency(this.td);
         var1.createDependency(var3);
      }
   }

   public boolean referencesSessionSchema() throws StandardException {
      if (isSessionSchema(this.td.getSchemaName())) {
         return true;
      } else {
         return this.renamingWhat == 1 && this.isSessionSchema(this.getSchemaDescriptor());
      }
   }

   private void renameTableBind(DataDictionary var1) throws StandardException {
      ConstraintDescriptorList var2 = var1.getConstraintDescriptors(this.td);
      int var3 = var2 == null ? 0 : var2.size();

      for(int var5 = 0; var5 < var3; ++var5) {
         ConstraintDescriptor var4 = var2.elementAt(var5);
         if (var4.getConstraintType() == 4) {
            throw StandardException.newException("X0Y25.S", new Object[]{"RENAME", this.td.getName(), "CONSTRAINT", var4.getConstraintName()});
         }
      }

   }

   private void renameColumnBind(DataDictionary var1) throws StandardException {
      ColumnDescriptor var2 = this.td.getColumnDescriptor(this.oldObjectName);
      if (var2 == null) {
         throw StandardException.newException("42X14", new Object[]{this.oldObjectName, this.getFullName()});
      } else {
         ColumnDescriptor var3 = this.td.getColumnDescriptor(this.newObjectName);
         if (var3 != null) {
            throw this.descriptorExistsException(var3, this.td);
         } else {
            ColumnDescriptorList var4 = this.td.getGeneratedColumns();
            int var5 = var4.size();

            for(int var6 = 0; var6 < var5; ++var6) {
               ColumnDescriptor var7 = var4.elementAt(var6);

               for(String var11 : var7.getDefaultInfo().getReferencedColumnNames()) {
                  if (this.oldObjectName.equals(var11)) {
                     throw StandardException.newException("42XA8", new Object[]{this.oldObjectName, var7.getColumnName()});
                  }
               }
            }

            ConstraintDescriptorList var13 = var1.getConstraintDescriptors(this.td);
            int var14 = var13 == null ? 0 : var13.size();

            for(int var18 = 0; var18 < var14; ++var18) {
               ConstraintDescriptor var15 = var13.elementAt(var18);
               if (var15.getConstraintType() == 4) {
                  ColumnDescriptorList var16 = var15.getColumnDescriptors();
                  int var17 = var16.size();

                  for(int var12 = 0; var12 < var17; ++var12) {
                     if (var16.elementAt(var12) == var2) {
                        throw StandardException.newException("42Z97", new Object[]{this.oldObjectName, var15.getConstraintName()});
                     }
                  }
               }
            }

         }
      }
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getRenameConstantAction(this.getFullName(), this.getRelativeName(), this.oldObjectName, this.newObjectName, this.getSchemaDescriptor(), this.td.getUUID(), this.usedAlterTable, this.renamingWhat);
   }

   private StandardException descriptorExistsException(TupleDescriptor var1, TupleDescriptor var2) {
      return StandardException.newException("X0Y32.S", new Object[]{var1.getDescriptorType(), var1.getDescriptorName(), var2.getDescriptorType(), var2.getDescriptorName()});
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.newTableName != null) {
         this.newTableName = (TableName)this.newTableName.accept(var1);
      }

   }
}
