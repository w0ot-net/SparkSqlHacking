package org.apache.derby.impl.sql.compile;

import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.DefaultInfoImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.DefaultDescriptor;
import org.apache.derby.iapi.sql.dictionary.KeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class ModifyColumnNode extends ColumnDefinitionNode {
   int columnPosition = -1;
   UUID oldDefaultUUID;
   static final int K_MODIFY_COLUMN_TYPE = 0;
   static final int K_MODIFY_COLUMN_DEFAULT = 1;
   static final int K_MODIFY_COLUMN_CONSTRAINT = 2;
   static final int K_MODIFY_COLUMN_CONSTRAINT_NOT_NULL = 3;
   static final int K_DROP_COLUMN = 4;
   static final int K_MODIFY_COLUMN_GENERATED_ALWAYS = 5;
   static final int K_MODIFY_COLUMN_GENERATED_BY_DEFAULT = 6;
   final int kind;

   ModifyColumnNode(int var1, String var2, ValueNode var3, DataTypeDescriptor var4, long[] var5, ContextManager var6) throws StandardException {
      super(var2, var3, var4, var5, var6);
      this.kind = var1;
   }

   UUID getOldDefaultUUID() {
      return this.oldDefaultUUID;
   }

   int getColumnPosition() {
      return this.columnPosition;
   }

   void checkUserType(TableDescriptor var1) throws StandardException {
      if (this.kind == 0) {
         ColumnDescriptor var2 = var1.getColumnDescriptor(this.name);
         if (var2 == null) {
            throw StandardException.newException("42X14", new Object[]{this.name, var1.getName()});
         } else {
            DataTypeDescriptor var3 = var2.getType();
            this.setNullability(var3.isNullable());
            if (!var3.getTypeId().equals(this.getType().getTypeId())) {
               throw StandardException.newException("42Z15", new Object[]{this.name});
            } else {
               String var4 = this.getType().getTypeName();
               if (!var4.equals("VARCHAR") && !var4.equals("VARCHAR () FOR BIT DATA") && !var4.equals("BLOB") && !var4.equals("CLOB")) {
                  throw StandardException.newException("42Z16", new Object[0]);
               } else if (this.getType().getMaximumWidth() < var3.getMaximumWidth()) {
                  throw StandardException.newException("42Z17", new Object[]{this.name});
               }
            }
         }
      }
   }

   void checkExistingConstraints(TableDescriptor var1) throws StandardException {
      if (this.kind == 0 || this.kind == 2 || this.kind == 3) {
         DataDictionary var2 = this.getDataDictionary();
         ConstraintDescriptorList var3 = var2.getConstraintDescriptors(var1);
         int[] var4 = new int[]{this.columnPosition};

         for(int var5 = 0; var5 < var3.size(); ++var5) {
            ConstraintDescriptor var6 = var3.elementAt(var5);
            if (var6 instanceof KeyConstraintDescriptor && var6.columnIntersects(var4)) {
               int var7 = var6.getConstraintType();
               if (var7 == 6 && this.kind == 0) {
                  throw StandardException.newException("42Z18", new Object[]{this.name, var6.getConstraintName()});
               }

               if (!var2.checkVersion(160, (String)null) && this.kind == 2 && var6.getConstraintType() == 3) {
                  throw StandardException.newException("42Z20", new Object[]{this.name});
               }

               if (this.kind == 2 && var6.getConstraintType() == 2) {
                  String var9 = this.getLanguageConnectionContext().getDataDictionary().checkVersion(160, (String)null) ? "42Z20.S.1" : "42Z20";
                  throw StandardException.newException(var9, new Object[]{this.name});
               }

               ConstraintDescriptorList var8 = var2.getForeignKeys(var6.getUUID());
               if (var8.size() > 0) {
                  throw StandardException.newException("42Z19", new Object[]{this.name, var8.elementAt(0).getConstraintName()});
               }

               this.getCompilerContext().createDependency(var6);
            }
         }

      }
   }

   void useExistingCollation(TableDescriptor var1) throws StandardException {
      ColumnDescriptor var2 = var1.getColumnDescriptor(this.name);
      if (var2 == null) {
         throw StandardException.newException("42X14", new Object[]{this.name, var1.getName()});
      } else {
         if (this.getType() != null && this.getType().getTypeId().isStringTypeId()) {
            this.setCollationType(var2.getType().getCollationType());
         }

      }
   }

   int getAction() {
      switch (this.kind) {
         case 0:
            return 2;
         case 1:
            if (this.autoinc_create_or_modify_Start_Increment == 1L) {
               return 5;
            } else if (this.autoinc_create_or_modify_Start_Increment == 2L) {
               return 6;
            } else {
               if (this.autoinc_create_or_modify_Start_Increment == 4L) {
                  return 10;
               }

               return 7;
            }
         case 2:
            return 3;
         case 3:
            return 4;
         case 4:
            return 1;
         case 5:
            return 8;
         case 6:
            return 9;
         default:
            return 0;
      }
   }

   void bindAndValidateDefault(DataDictionary var1, TableDescriptor var2) throws StandardException {
      ColumnDescriptor var3 = var2.getColumnDescriptor(this.name);
      if (var3 == null) {
         throw StandardException.newException("42X14", new Object[]{this.name, var2.getName()});
      } else {
         DefaultDescriptor var4 = var3.getDefaultDescriptor(var1);
         this.oldDefaultUUID = var4 == null ? null : var4.getUUID();
         this.columnPosition = var3.getPosition();
         if (this.kind == 1) {
            if (this.keepCurrentDefault) {
               this.defaultInfo = (DefaultInfoImpl)var3.getDefaultInfo();
            } else if (var3.hasGenerationClause() || var3.isAutoincrement()) {
               throw StandardException.newException("42XA7", new Object[]{var3.getColumnName()});
            }

            if (this.autoinc_create_or_modify_Start_Increment == 1L) {
               this.autoincrementIncrement = var3.getAutoincInc();
               this.autoincrementCycle = var3.getAutoincCycle();
            }

            if (this.autoinc_create_or_modify_Start_Increment == 2L) {
               this.autoincrementStart = var3.getAutoincStart();
               this.autoincrementCycle = var3.getAutoincCycle();
            }

            if (this.autoinc_create_or_modify_Start_Increment == 4L) {
               this.autoincrementIncrement = var3.getAutoincInc();
               this.autoincrementStart = var3.getAutoincStart();
            }

            this.type = var3.getType();
            this.validateDefault(var1, var2);
         }
      }
   }

   private ColumnDescriptor getLocalColumnDescriptor(String var1, TableDescriptor var2) throws StandardException {
      ColumnDescriptor var3 = var2.getColumnDescriptor(var1);
      if (var3 == null) {
         throw StandardException.newException("42X14", new Object[]{var1, var2.getName()});
      } else {
         return var3;
      }
   }

   void validateAutoincrement(DataDictionary var1, TableDescriptor var2, int var3) throws StandardException {
      if (this.kind != 5 && this.kind != 6) {
         if (this.kind == 2) {
            ColumnDescriptor var5 = this.getLocalColumnDescriptor(this.name, var2);
            if (var5.isAutoincrement()) {
               throw StandardException.newException("42Z26", new Object[]{this.getColumnName()});
            }
         }

         if (this.autoincrementVerify) {
            ColumnDescriptor var6 = this.getLocalColumnDescriptor(this.name, var2);
            if (!var6.isAutoincrement()) {
               throw StandardException.newException("42837", new Object[]{var2.getQualifiedName(), this.name});
            }
         }

         if (this.isAutoincrement) {
            super.validateAutoincrement(var1, var2, var3);
            if (this.getType().isNullable()) {
               throw StandardException.newException("42Z27", new Object[]{this.getColumnName()});
            }
         }
      } else {
         ColumnDescriptor var4 = this.getLocalColumnDescriptor(this.name, var2);
         if (!var4.isAutoincrement()) {
            throw StandardException.newException("42Z29", new Object[]{this.getColumnName()});
         } else {
            if (this.kind == 6) {
               this.defaultInfo = createDefaultInfoOfAutoInc();
            }

         }
      }
   }
}
