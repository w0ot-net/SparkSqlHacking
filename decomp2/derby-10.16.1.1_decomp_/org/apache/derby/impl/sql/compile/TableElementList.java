package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.apache.derby.catalog.DefaultInfo;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.DefaultInfoImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.ProviderInfo;
import org.apache.derby.iapi.sql.depend.ProviderList;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.impl.sql.execute.ColumnInfo;
import org.apache.derby.impl.sql.execute.ConstraintConstantAction;
import org.apache.derby.impl.sql.execute.ConstraintInfo;
import org.apache.derby.impl.sql.execute.IndexConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class TableElementList extends QueryTreeNodeVector {
   private int numColumns;
   private TableDescriptor td;

   public TableElementList(ContextManager var1) {
      super(TableElementNode.class, var1);
   }

   void addTableElement(TableElementNode var1) {
      this.addElement(var1);
      if (var1 instanceof ColumnDefinitionNode || var1.getElementType() == 7) {
         ++this.numColumns;
      }

   }

   void setCollationTypesOnCharacterStringColumns(SchemaDescriptor var1) throws StandardException {
      for(TableElementNode var3 : this) {
         if (var3 instanceof ColumnDefinitionNode) {
            this.setCollationTypeOnCharacterStringColumn(var1, (ColumnDefinitionNode)var3);
         }
      }

   }

   void setCollationTypeOnCharacterStringColumn(SchemaDescriptor var1, ColumnDefinitionNode var2) throws StandardException {
      int var3 = var1.getCollationType();
      DataTypeDescriptor var4 = var2.getType();
      if (var4 == null) {
         if (!var2.hasGenerationClause()) {
            throw StandardException.newException("42XA9", new Object[]{var2.getColumnName()});
         }
      } else if (var4.getTypeId().isStringTypeId()) {
         var2.setCollationType(var3);
      }

   }

   void validate(DDLStatementNode var1, DataDictionary var2, TableDescriptor var3) throws StandardException {
      this.td = var3;
      int var4 = 0;
      int var5 = this.size();
      HashSet var6 = new HashSet(var5 + 2, 0.999F);
      HashSet var7 = new HashSet(var5 + 2, 0.999F);
      ArrayList var8 = new ArrayList();
      if (var3 != null) {
         ConstraintDescriptorList var9 = var2.getConstraintDescriptors(var3);
         if (var9 != null) {
            for(int var11 = 0; var11 < var9.size(); ++var11) {
               ConstraintDescriptor var10 = var9.elementAt(var11);
               if (var10.getConstraintType() == 2 || var10.getConstraintType() == 3) {
                  var8.add(var10);
               }
            }
         }
      }

      int var18 = 0;
      if (var1 instanceof CreateTableNode) {
         var18 = ((CreateTableNode)var1).tableType;
      }

      for(TableElementNode var20 : this) {
         if (var20 instanceof ColumnDefinitionNode var12) {
            if (var18 == 3 && (var12.getType().getTypeId().isLongConcatableTypeId() || var12.getType().getTypeId().isUserDefinedTypeId())) {
               throw StandardException.newException("42962", new Object[]{var12.getColumnName()});
            }

            this.checkForDuplicateColumns(var1, var6, var12.getColumnName());
            var12.checkUserType(var3);
            var12.bindAndValidateDefault(var2, var3);
            var12.validateAutoincrement(var2, var3, var18);
            if (var20 instanceof ModifyColumnNode) {
               ModifyColumnNode var13 = (ModifyColumnNode)var12;
               var13.checkExistingConstraints(var3);
               var13.useExistingCollation(var3);
            } else if (var12.isAutoincrementColumn()) {
               ++var4;
            }
         } else if (var20.getElementType() == 7) {
            String var22 = var20.getName();
            if (var3.getColumnDescriptor(var22) == null) {
               throw StandardException.newException("42X14", new Object[]{var22, var3.getQualifiedName()});
            }
            break;
         }

         if (var20.hasConstraint()) {
            ConstraintDefinitionNode var21 = (ConstraintDefinitionNode)var20;
            var21.bind(var1, var2);
            if (var21.getConstraintType() == 2 || var21.getConstraintType() == 3) {
               String var14 = null;
               String[] var15 = null;

               for(int var16 = 0; var16 < var8.size(); ++var16) {
                  Object var23 = var8.get(var16);
                  if (var23 instanceof ConstraintDefinitionNode) {
                     ConstraintDefinitionNode var17 = (ConstraintDefinitionNode)var23;
                     var14 = var17.getConstraintMoniker();
                     var15 = var17.getColumnList().getColumnNames();
                  } else if (var23 instanceof ConstraintDescriptor) {
                     ConstraintDescriptor var28 = (ConstraintDescriptor)var23;
                     var14 = var28.getConstraintName();
                     var15 = var28.getColumnDescriptors().getColumnNames();
                  }

                  if (this.columnsMatch(var21.getColumnList().getColumnNames(), var15)) {
                     throw StandardException.newException("42Z93", new Object[]{var21.getConstraintMoniker(), var14});
                  }
               }

               var8.add(var21);
            }

            this.checkForDuplicateConstraintNames(var1, var7, var21.getConstraintMoniker());
            if (var21.getConstraintType() == 5 || var21.getConstraintType() == 7) {
               String var24 = var21.getConstraintMoniker();
               if (var24 != null) {
                  String var25 = var21.getDropSchemaName();
                  SchemaDescriptor var26 = var25 == null ? var3.getSchemaDescriptor() : this.getSchemaDescriptor(var25);
                  ConstraintDescriptor var27 = var2.getConstraintDescriptorByName(var3, var26, var24, false);
                  if (var27 == null) {
                     Object[] var10001 = new Object[2];
                     String var10004 = var26.getSchemaName();
                     var10001[0] = var10004 + "." + var24;
                     var10001[1] = var3.getQualifiedName();
                     throw StandardException.newException("42X86", var10001);
                  }

                  this.getCompilerContext().createDependency(var27);
               }
            }

            if (var21.hasPrimaryKeyConstraint()) {
               this.verifyUniqueColumnList(var1, var21);
            } else if (var21.hasUniqueKeyConstraint()) {
               this.verifyUniqueColumnList(var1, var21);
               if (!var2.checkVersion(160, (String)null)) {
                  this.checkForNullColumns(var21, var3);
               }
            } else if (var21.hasForeignKeyConstraint()) {
               this.verifyUniqueColumnList(var1, var21);
            }
         }
      }

      if (var4 > 1 || var4 > 0 && var3 != null && var3.tableHasAutoincrement()) {
         throw StandardException.newException("428C1", new Object[0]);
      }
   }

   public void validatePrimaryKeyNullability() throws StandardException {
      for(TableElementNode var2 : this) {
         if (var2.hasConstraint()) {
            ConstraintDefinitionNode var3 = (ConstraintDefinitionNode)var2;
            if (var3.hasPrimaryKeyConstraint()) {
               if (this.td == null) {
                  this.setColumnListToNotNull(var3);
               } else {
                  this.checkForNullColumns(var3, this.td);
               }
            }
         }
      }

   }

   int countConstraints(int var1) {
      int var2 = 0;

      for(TableElementNode var4 : this) {
         if (var4 instanceof ConstraintDefinitionNode && ((ConstraintDefinitionNode)var4).getConstraintType() == var1) {
            ++var2;
         }
      }

      return var2;
   }

   int countGenerationClauses() {
      int var1 = 0;

      for(TableElementNode var3 : this) {
         if (var3 instanceof ColumnDefinitionNode && ((ColumnDefinitionNode)var3).hasGenerationClause()) {
            ++var1;
         }
      }

      return var1;
   }

   int countNumberOfColumns() {
      return this.numColumns;
   }

   int genColumnInfos(ColumnInfo[] var1) throws StandardException {
      int var2 = 0;
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         if (((TableElementNode)this.elementAt(var4)).getElementType() == 7) {
            String var9 = ((TableElementNode)this.elementAt(var4)).getName();
            var1[var4] = new ColumnInfo(var9, this.td.getColumnDescriptor(var9).getType(), (DataValueDescriptor)null, (DefaultInfo)null, (ProviderInfo[])null, (UUID)null, (UUID)null, 1, 0L, 0L, false, 0L);
            break;
         }

         if (!(this.elementAt(var4) instanceof ColumnDefinitionNode)) {
            ++var2;
         } else {
            ColumnDefinitionNode var5 = (ColumnDefinitionNode)this.elementAt(var4);
            ProviderList var6 = null;
            ProviderInfo[] var7 = null;
            if (var5.hasGenerationClause()) {
               var6 = var5.getGenerationClauseNode().getAuxiliaryProviderList();
            }

            if (var6 != null && var6.size() > 0) {
               DependencyManager var8 = this.getDataDictionary().getDependencyManager();
               var7 = var8.getPersistentProviderInfos(var6);
            }

            var1[var4 - var2] = new ColumnInfo(var5.getColumnName(), var5.getType(), var5.getDefaultValue(), var5.getDefaultInfo(), var7, (UUID)null, var5.getOldDefaultUUID(), var5.getAction(), var5.isAutoincrementColumn() ? var5.getAutoincrementStart() : 0L, var5.isAutoincrementColumn() ? var5.getAutoincrementIncrement() : 0L, var5.isAutoincrementColumn() ? var5.getAutoincrementCycle() : false, var5.isAutoincrementColumn() ? var5.getAutoinc_create_or_modify_Start_Increment() : -1L);
            if (var5.hasConstraint()) {
               ++var2;
            }
         }
      }

      return var2;
   }

   void appendNewColumnsToRCL(FromBaseTable var1) throws StandardException {
      int var2 = this.size();
      ResultColumnList var3 = var1.getResultColumns();
      TableName var4 = var1.getTableName();

      for(int var5 = 0; var5 < var2; ++var5) {
         if (this.elementAt(var5) instanceof ColumnDefinitionNode) {
            ColumnDefinitionNode var6 = (ColumnDefinitionNode)this.elementAt(var5);
            BaseColumnNode var8 = new BaseColumnNode(var6.getColumnName(), var4, var6.getType(), this.getContextManager());
            ResultColumn var7 = new ResultColumn(var6.getType(), var8, this.getContextManager());
            var7.setName(var6.getColumnName());
            var3.addElement(var7);
         }
      }

   }

   void bindAndValidateCheckConstraints(FromList var1) throws StandardException {
      FromBaseTable var2 = (FromBaseTable)var1.elementAt(0);
      CompilerContext var3 = this.getCompilerContext();
      ArrayList var4 = new ArrayList();

      for(TableElementNode var6 : this) {
         if (var6 instanceof ConstraintDefinitionNode var7) {
            if (var7.getConstraintType() == 4) {
               ValueNode var8 = var7.getCheckCondition();
               int var9 = var3.getReliability();

               try {
                  ProviderList var10 = new ProviderList();
                  ProviderList var11 = var3.getCurrentAuxiliaryProviderList();
                  var3.setCurrentAuxiliaryProviderList(var10);
                  var3.setReliability(18041);
                  var8 = var8.bindExpression(var1, (SubqueryList)null, var4);
                  if (!var4.isEmpty()) {
                     throw StandardException.newException("42Y01", new Object[]{var7.getConstraintText()});
                  }

                  var8 = var8.checkIsBoolean();
                  var7.setCheckCondition(var8);
                  if (var10.size() > 0) {
                     var7.setAuxiliaryProviderList(var10);
                  }

                  var3.setCurrentAuxiliaryProviderList(var11);
               } finally {
                  var3.setReliability(var9);
               }

               ResultColumnList var18 = var2.getResultColumns();
               int var19 = var18.countReferencedColumns();
               ResultColumnList var12 = new ResultColumnList(this.getContextManager());
               var18.copyReferencedColumnsToNewList(var12);
               if (var7.getColumnList() != null) {
                  String var13 = ((ResultColumn)var7.getColumnList().elementAt(0)).getName();
                  if (var19 > 1 || !var13.equals(((ResultColumn)var12.elementAt(0)).getName())) {
                     throw StandardException.newException("42621", new Object[]{var13});
                  }
               }

               var7.setColumnList(var12);
               var18.clearColumnReferences();
               var7.qualifyNames();
            }
         }
      }

   }

   void bindAndValidateGenerationClauses(SchemaDescriptor var1, FromList var2, FormatableBitSet var3, TableDescriptor var4) throws StandardException {
      FromBaseTable var5 = (FromBaseTable)var2.elementAt(0);
      ResultColumnList var6 = var5.getResultColumns();
      int var7 = var5.getResultColumns().size();
      this.findIllegalGenerationReferences(var2, var4);
      var3.grow(var7 + 1);
      CompilerContext var8 = this.getCompilerContext();
      ArrayList var9 = new ArrayList();

      for(TableElementNode var11 : this) {
         if (var11 instanceof ColumnDefinitionNode var12) {
            if (var12.hasGenerationClause()) {
               GenerationClauseNode var13 = var12.getGenerationClauseNode();
               int var15 = var8.getReliability();
               ProviderList var16 = var8.getCurrentAuxiliaryProviderList();

               try {
                  ProviderList var17 = new ProviderList();
                  var8.setCurrentAuxiliaryProviderList(var17);
                  var8.setReliability(30329);
                  ValueNode var14 = var13.bindExpression(var2, (SubqueryList)null, var9);
                  SelectNode.checkNoWindowFunctions(var13, "generation clause");
                  DataTypeDescriptor var18 = var14.getTypeServices();
                  DataTypeDescriptor var19 = var12.getType();
                  if (var19 == null) {
                     var12.setType(var18);
                     var6.getResultColumn(var12.getColumnName(), false).setType(var18);
                     this.setCollationTypeOnCharacterStringColumn(var1, var12);
                     var12.checkUserType(var5.getTableDescriptor());
                  } else {
                     TypeId var20 = var19.getTypeId();
                     TypeId var21 = var18.getTypeId();
                     if (!this.getTypeCompiler(var21).convertible(var20, false)) {
                        throw StandardException.newException("42XA0", new Object[]{var12.getName(), var21.getSQLTypeName()});
                     }
                  }

                  if (!var9.isEmpty()) {
                     throw StandardException.newException("42XA1", new Object[]{var12.getName()});
                  }

                  if (var17.size() > 0) {
                     var13.setAuxiliaryProviderList(var17);
                  }
               } finally {
                  var8.setCurrentAuxiliaryProviderList(var16);
                  var8.setReliability(var15);
               }

               ResultColumnList var26 = var5.getResultColumns();
               int var27 = var26.countReferencedColumns();
               int[] var28 = new int[var27];
               int var29 = var26.getPosition(var12.getColumnName(), 1);
               var3.set(var29);
               var26.recordColumnReferences(var28, 1);
               String[] var30 = new String[var27];

               for(int var22 = 0; var22 < var27; ++var22) {
                  var30[var22] = ((ResultColumn)var26.elementAt(var28[var22] - 1)).getName();
               }

               String var31 = this.getLanguageConnectionContext().getCurrentSchemaName();
               DefaultInfoImpl var23 = new DefaultInfoImpl(var13.getExpressionText(), var30, var31);
               var12.setDefaultInfo(var23);
               var26.clearColumnReferences();
            }
         }
      }

   }

   void findIllegalGenerationReferences(FromList var1, TableDescriptor var2) throws StandardException {
      ArrayList var3 = new ArrayList();
      HashSet var4 = new HashSet();
      if (var2 != null) {
         ColumnDescriptorList var5 = var2.getGeneratedColumns();
         int var6 = var5.size();

         for(int var7 = 0; var7 < var6; ++var7) {
            var4.add(var5.elementAt(var7).getColumnName());
         }
      }

      for(TableElementNode var15 : this) {
         if (var15 instanceof ColumnDefinitionNode var17) {
            if (var17.hasGenerationClause()) {
               var3.add(var17);
               var4.add(var17.getColumnName());
            }
         }
      }

      int var14 = var3.size();

      for(int var16 = 0; var16 < var14; ++var16) {
         ColumnDefinitionNode var18 = (ColumnDefinitionNode)var3.get(var16);
         GenerationClauseNode var8 = var18.getGenerationClauseNode();
         List var9 = var8.findReferencedColumns();
         int var10 = var9.size();

         for(int var11 = 0; var11 < var10; ++var11) {
            String var12 = ((ColumnReference)var9.get(var11)).getColumnName();
            if (var12 != null && var4.contains(var12)) {
               throw StandardException.newException("42XA4", new Object[]{var18.getColumnName()});
            }
         }
      }

   }

   void validateForeignKeysOnGenerationClauses(FromList var1, FormatableBitSet var2) throws StandardException {
      if (var2.getNumBitsSet() > 0) {
         FromBaseTable var3 = (FromBaseTable)var1.elementAt(0);
         ResultColumnList var4 = var3.getResultColumns();

         for(TableElementNode var6 : this) {
            if (var6 instanceof FKConstraintDefinitionNode) {
               FKConstraintDefinitionNode var7 = (FKConstraintDefinitionNode)var6;
               ConstraintInfo var8 = var7.getReferencedConstraintInfo();
               int var9 = var8.getReferentialActionDeleteRule();
               int var10 = var8.getReferentialActionUpdateRule();
               if (var10 != 1 && var10 != 2) {
                  throw StandardException.newException("XSCB3.S", new Object[0]);
               }

               if (var9 == 3 || var9 == 4) {
                  for(ResultColumn var12 : var7.getColumnList()) {
                     String var13 = var12.getName();
                     int var14 = var4.getPosition(var13, 1);
                     if (var2.isSet(var14)) {
                        throw StandardException.newException("42XA6", new Object[]{var13});
                     }
                  }
               }
            }
         }

      }
   }

   void genConstraintActions(boolean var1, ConstraintConstantAction[] var2, String var3, SchemaDescriptor var4, DataDictionary var5) throws StandardException {
      int var6 = 0;

      for(TableElementNode var8 : this) {
         String[] var9 = null;
         IndexConstantAction var10 = null;
         if (var8.hasConstraint() && !(var8 instanceof ColumnDefinitionNode)) {
            ConstraintDefinitionNode var11 = (ConstraintDefinitionNode)var8;
            if (var11.getColumnList() != null) {
               var9 = new String[var11.getColumnList().size()];
               var11.getColumnList().exportNames(var9);
            }

            int var12 = var11.getConstraintType();
            boolean[] var13 = var11.getCharacteristics();
            String var14 = var11.getConstraintText();
            String var15 = var11.getConstraintMoniker();
            if (var11.requiresBackingIndex()) {
               if (var11.constraintType == 3 && var5.checkVersion(160, (String)null)) {
                  boolean var16 = this.areColumnsNullable(var11, this.td);
                  boolean var17 = !var16;
                  var10 = this.genIndexAction(var1, var17, var16, var13[0], var13[1], (String)null, var11, var9, true, var4, var3, var12, var5);
               } else {
                  var10 = this.genIndexAction(var1, var11.requiresUniqueIndex(), false, var13[0], var13[1], (String)null, var11, var9, true, var4, var3, var12, var5);
               }
            }

            if (var12 == 5) {
               var2[var6] = this.getGenericConstantActionFactory().getDropConstraintConstantAction(var15, var11.getDropSchemaName(), var3, this.td.getUUID(), var4.getSchemaName(), var10, var11.getDropBehavior(), var11.getVerifyType());
            } else if (var12 == 7) {
               var2[var6] = this.getGenericConstantActionFactory().getAlterConstraintConstantAction(var15, var11.getDropSchemaName(), var13, var3, this.td.getUUID(), var4.getSchemaName(), var10);
            } else {
               ProviderList var20 = var11.getAuxiliaryProviderList();
               ConstraintInfo var21 = null;
               if (var11 instanceof FKConstraintDefinitionNode) {
                  var21 = ((FKConstraintDefinitionNode)var11).getReferencedConstraintInfo();
               }

               ProviderInfo[] var18;
               if (var20 != null && var20.size() > 0) {
                  DependencyManager var19 = var5.getDependencyManager();
                  var18 = var19.getPersistentProviderInfos(var20);
               } else {
                  var18 = new ProviderInfo[0];
               }

               var2[var6++] = this.getGenericConstantActionFactory().getCreateConstraintConstantAction(var15, var12, var13, var1, var3, this.td != null ? this.td.getUUID() : (UUID)null, var4.getSchemaName(), var9, var10, var14, var21, var18);
            }
         }
      }

   }

   private boolean columnsMatch(String[] var1, String[] var2) {
      if (var1.length != var2.length) {
         return false;
      } else {
         int var4 = var1.length;
         int var6 = var2.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            boolean var7 = false;

            for(int var5 = 0; var5 < var6; ++var5) {
               if (var1[var3].equals(var2[var5])) {
                  var7 = true;
                  break;
               }
            }

            if (!var7) {
               return false;
            }
         }

         return true;
      }
   }

   private IndexConstantAction genIndexAction(boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, String var6, ConstraintDefinitionNode var7, String[] var8, boolean var9, SchemaDescriptor var10, String var11, int var12, DataDictionary var13) throws StandardException {
      if (var6 == null) {
         var6 = var7.getBackingIndexName(var13);
      }

      if (var12 == 5) {
         return this.getGenericConstantActionFactory().getDropIndexConstantAction((String)null, var6, var11, var10.getSchemaName(), this.td.getUUID(), this.td.getHeapConglomerateId());
      } else {
         boolean[] var14 = new boolean[var8.length];

         for(int var15 = 0; var15 < var14.length; ++var15) {
            var14[var15] = true;
         }

         return this.getGenericConstantActionFactory().getCreateIndexConstantAction(var1, var2, var3, var4, var5, var12, "BTREE", var10.getSchemaName(), var6, var11, this.td != null ? this.td.getUUID() : (UUID)null, var8, var14, var9, var7.getBackingIndexUUID(), this.checkIndexPageSizeProperty(var7));
      }
   }

   private Properties checkIndexPageSizeProperty(ConstraintDefinitionNode var1) throws StandardException {
      Properties var2 = var1.getProperties();
      if (var2 == null) {
         var2 = new Properties();
      }

      if (var2.get("derby.storage.pageSize") == null && PropertyUtil.getServiceProperty(this.getLanguageConnectionContext().getTransactionCompile(), "derby.storage.pageSize") == null) {
         int var3 = 0;

         for(ResultColumn var5 : var1.getColumnList()) {
            String var6 = var5.getName();
            DataTypeDescriptor var7;
            if (this.td == null) {
               var7 = this.getColumnDataTypeDescriptor(var6);
            } else {
               var7 = this.getColumnDataTypeDescriptor(var6, this.td);
            }

            if (var7 != null) {
               var3 += var7.getTypeId().getApproximateLengthInBytes(var7);
            }
         }

         if (var3 > 1024) {
            var2.put("derby.storage.pageSize", "32768");
         }

         return var2;
      } else {
         return var2;
      }
   }

   private void checkForDuplicateColumns(DDLStatementNode var1, Set var2, String var3) throws StandardException {
      if (!var2.add(var3) && var1 instanceof CreateTableNode) {
         throw StandardException.newException("42X12", new Object[]{var3});
      }
   }

   private void checkForDuplicateConstraintNames(DDLStatementNode var1, Set var2, String var3) throws StandardException {
      if (var3 != null) {
         if (!var2.add(var3) && var1 instanceof CreateTableNode) {
            throw StandardException.newException("42X91", new Object[]{var3});
         }
      }
   }

   private void verifyUniqueColumnList(DDLStatementNode var1, ConstraintDefinitionNode var2) throws StandardException {
      if (var1 instanceof CreateTableNode) {
         String var3 = var2.getColumnList().verifyCreateConstraintColumnList(this);
         if (var3 != null) {
            throw StandardException.newException("42X93", new Object[]{var1.getRelativeName(), var3});
         }
      }

      String var4 = var2.getColumnList().verifyUniqueNames(false);
      if (var4 != null) {
         throw StandardException.newException("42X92", new Object[]{var4});
      }
   }

   private void setColumnListToNotNull(ConstraintDefinitionNode var1) {
      for(ResultColumn var3 : var1.getColumnList()) {
         this.findColumnDefinition(var3.getName()).setNullability(false);
      }

   }

   private boolean areColumnsNullable(ConstraintDefinitionNode var1, TableDescriptor var2) {
      for(ResultColumn var4 : var1.getColumnList()) {
         String var5 = var4.getName();
         DataTypeDescriptor var6 = var2 == null ? this.getColumnDataTypeDescriptor(var5) : this.getColumnDataTypeDescriptor(var5, var2);
         if (var6 != null && var6.isNullable()) {
            return true;
         }
      }

      return false;
   }

   private void checkForNullColumns(ConstraintDefinitionNode var1, TableDescriptor var2) throws StandardException {
      for(ResultColumn var4 : var1.getColumnList()) {
         DataTypeDescriptor var5 = var2 == null ? this.getColumnDataTypeDescriptor(var4.getName()) : this.getColumnDataTypeDescriptor(var4.getName(), var2);
         if (var5 != null && var5.isNullable()) {
            String var6 = this.getLanguageConnectionContext().getDataDictionary().checkVersion(160, (String)null) ? "42831.S.1" : "42831";
            throw StandardException.newException(var6, new Object[]{var4.getName()});
         }
      }

   }

   private DataTypeDescriptor getColumnDataTypeDescriptor(String var1) {
      ColumnDefinitionNode var2 = this.findColumnDefinition(var1);
      return var2 != null ? var2.getType() : null;
   }

   private DataTypeDescriptor getColumnDataTypeDescriptor(String var1, TableDescriptor var2) {
      ColumnDescriptor var3 = var2.getColumnDescriptor(var1);
      return var3 != null ? var3.getType() : this.getColumnDataTypeDescriptor(var1);
   }

   private ColumnDefinitionNode findColumnDefinition(String var1) {
      for(TableElementNode var3 : this) {
         if (var3 instanceof ColumnDefinitionNode var4) {
            if (var1.equals(var4.getName())) {
               return var4;
            }
         }
      }

      return null;
   }

   boolean containsColumnName(String var1) {
      return this.findColumnDefinition(var1) != null;
   }
}
