package org.apache.derby.impl.sql.compile;

import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.derby.catalog.DefaultInfo;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.DefaultInfoImpl;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.DefaultDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecRowBuilder;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.StoreCostController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class ResultColumnList extends QueryTreeNodeVector {
   protected boolean indexRow;
   protected long conglomerateId;
   int orderBySelect = 0;
   protected boolean forUpdate;
   private boolean countMismatchAllowed;
   private int initialListSize = 0;

   ResultColumnList(ContextManager var1) {
      super(ResultColumn.class, var1);
   }

   void addResultColumn(ResultColumn var1) {
      var1.setVirtualColumnId(this.size() + 1);
      this.addElement(var1);
   }

   void appendResultColumns(ResultColumnList var1, boolean var2) {
      int var3 = this.size();
      int var4 = var3 + 1;

      for(ResultColumn var6 : var1) {
         var6.setVirtualColumnId(var4);
         ++var4;
      }

      if (var2) {
         this.destructiveAppend(var1);
      } else {
         this.nondestructiveAppend(var1);
      }

   }

   ResultColumn getResultColumn(int var1) {
      if (var1 <= this.size()) {
         ResultColumn var2 = (ResultColumn)this.elementAt(var1 - 1);
         if (var2.getColumnPosition() == var1) {
            return var2;
         }
      }

      int var5 = this.size();

      for(int var3 = 0; var3 < var5; ++var3) {
         ResultColumn var4 = (ResultColumn)this.elementAt(var3);
         if (var4.getColumnPosition() == var1) {
            return var4;
         }
      }

      return null;
   }

   ResultColumn getResultColumn(int var1, ResultSetNode var2, int[] var3) throws StandardException {
      if (var1 == -1) {
         return null;
      } else {
         int[] var4 = new int[]{-1};

         for(int var5 = this.size() - 1; var5 >= 0; --var5) {
            ResultColumn var6 = (ResultColumn)this.elementAt(var5);
            if (var6.getExpression() instanceof ColumnReference) {
               ColumnReference var7 = (ColumnReference)var6.getExpression();
               if (var2 == var7.getSourceResultSet(var4) && var4[0] == var1) {
                  var3[0] = var5 + 1;
                  return var6;
               }
            }
         }

         return null;
      }
   }

   ResultColumn getOrderByColumn(int var1) {
      return var1 == 0 ? null : this.getResultColumn(var1);
   }

   ResultColumn getResultColumn(String var1) {
      return this.getResultColumn(var1, true);
   }

   ResultColumn getResultColumn(String var1, boolean var2) {
      for(ResultColumn var4 : this) {
         if (var1.equals(var4.getName())) {
            if (var2) {
               var4.setReferenced();
            }

            return var4;
         }
      }

      return null;
   }

   public ResultColumn getResultColumn(int var1, int var2, String var3) {
      int var4 = this.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ResultColumn var6 = (ResultColumn)this.elementAt(var5);
         ResultColumn var7 = var6;

         while(var7 != null) {
            ValueNode var8 = var7.getExpression();
            if (var8 instanceof VirtualColumnNode var9) {
               ResultSetNode var10 = var9.getSourceResultSet();
               if (var10 instanceof FromTable var11) {
                  if (var11.getTableNumber() == var1) {
                     ColumnDescriptor var12 = var7.getTableColumnDescriptor();
                     if (var12 != null && var12.getPosition() == var2 || var9.getSourceColumn().getColumnPosition() == var2) {
                        if (var3.equals(var9.getSourceColumn().getName())) {
                           var6.setReferenced();
                           return var6;
                        } else {
                           return null;
                        }
                     }

                     var7 = var9.getSourceColumn();
                  } else {
                     var7 = var9.getSourceColumn();
                  }
               } else {
                  var7 = null;
               }
            } else if (var8 instanceof ColumnReference var13) {
               if (var13.getTableNumber() == var1 && var13.getColumnNumber() == var2) {
                  var6.setReferenced();
                  return var6;
               }

               var7 = null;
            } else {
               var7 = null;
            }
         }
      }

      return null;
   }

   ResultColumn getResultColumn(String var1, String var2) {
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ResultColumn var5 = (ResultColumn)this.elementAt(var4);
         if ((var1 == null || var5.getTableName() != null && var1.equals(var5.getTableName())) && var2.equals(var5.getName())) {
            var5.setReferenced();
            return var5;
         }
      }

      return null;
   }

   ResultColumn getAtMostOneResultColumn(ColumnReference var1, String var2, boolean var3) throws StandardException {
      ResultColumn var4 = null;
      String var5 = var1.getColumnName();

      for(ResultColumn var7 : this) {
         if (var5.equals(var7.getName()) && (!var7.isGenerated() || var3)) {
            if (var4 != null) {
               throw StandardException.newException("42Y34", new Object[]{var5, var2});
            }

            var7.setReferenced();
            var4 = var7;
         }
      }

      return var4;
   }

   boolean columnsAreUpdatable() {
      for(ResultColumn var2 : this) {
         if (var2.isUpdatable()) {
            return true;
         }
      }

      return false;
   }

   ResultColumn getOrderByColumnToBind(String var1, TableName var2, int var3, OrderByColumn var4) throws StandardException {
      int var5 = this.size();
      ResultColumn var6 = null;

      for(int var8 = 0; var8 < var5; ++var8) {
         ResultColumn var7 = (ResultColumn)this.elementAt(var8);
         boolean var9;
         if (var2 != null) {
            ValueNode var10 = var7.getExpression();
            if (!(var10 instanceof ColumnReference)) {
               continue;
            }

            ColumnReference var11 = (ColumnReference)var10;
            if (!var2.equals(var11.getQualifiedTableName()) && var3 != var11.getTableNumber()) {
               continue;
            }

            var9 = var1.equals(var7.getSourceColumnName());
         } else {
            var9 = var7.columnNameMatches(var1);
         }

         if (var9) {
            if (var6 == null) {
               var6 = var7;
            } else {
               if (!var6.isEquivalent(var7)) {
                  throw StandardException.newException("42X79", new Object[]{var1});
               }

               if (var8 >= var5 - this.orderBySelect) {
                  this.removeElement(var7);
                  this.decOrderBySelect();
                  var4.clearAddedColumnOffset();
                  this.collapseVirtualColumnIdGap(var7.getColumnPosition());
                  break;
               }
            }
         }
      }

      return var6;
   }

   private void collapseVirtualColumnIdGap(int var1) {
      for(ResultColumn var3 : this) {
         var3.collapseVirtualColumnIdGap(var1);
      }

   }

   ResultColumn findResultColumnForOrderBy(String var1, TableName var2) throws StandardException {
      int var3 = this.size();
      ResultColumn var4 = null;

      for(int var6 = 0; var6 < var3; ++var6) {
         ResultColumn var5 = (ResultColumn)this.elementAt(var6);
         boolean var7;
         if (var2 != null) {
            ValueNode var8 = var5.getExpression();
            if (var8 == null || !(var8 instanceof ColumnReference)) {
               continue;
            }

            ColumnReference var9 = (ColumnReference)var8;
            if (!var2.equals(var9.getQualifiedTableName())) {
               continue;
            }

            var7 = var1.equals(var5.getSourceColumnName());
         } else {
            var7 = var5.columnNameMatches(var1);
         }

         if (var7) {
            if (var4 == null) {
               var4 = var5;
            } else {
               if (!var4.isEquivalent(var5)) {
                  throw StandardException.newException("42X79", new Object[]{var1});
               }

               if (var6 >= var3 - this.orderBySelect) {
               }
            }
         }
      }

      return var4;
   }

   void copyResultColumnNames(ResultColumnList var1) {
      int var2 = this.countMismatchAllowed ? var1.visibleSize() : this.visibleSize();

      for(int var3 = 0; var3 < var2; ++var3) {
         ResultColumn var4 = (ResultColumn)this.elementAt(var3);
         ResultColumn var5 = (ResultColumn)var1.elementAt(var3);
         var4.setName(var5.getName());
         var4.setNameGenerated(var5.isNameGenerated());
      }

   }

   void bindExpressions(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.expandAllsAndNameColumns(var1);
      int var4 = this.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ResultColumn var6 = (ResultColumn)this.elementAt(var5);
         var6 = var6.bindExpression(var1, var2, var3);
         this.setElementAt(var6, var5);
      }

   }

   void bindResultColumnsToExpressions() throws StandardException {
      for(ResultColumn var2 : this) {
         var2.bindResultColumnToExpression();
      }

   }

   void bindResultColumnsByName(TableDescriptor var1) throws StandardException {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ((ResultColumn)this.elementAt(var3)).bindResultColumnByName(var1, var3 + 1);
      }

   }

   FormatableBitSet bindResultColumnsByName(TableDescriptor var1, DMLStatementNode var2) throws StandardException {
      int var3 = this.size();
      FormatableBitSet var4 = new FormatableBitSet(var1.getNumberOfColumns());

      for(int var5 = 0; var5 < var3; ++var5) {
         ResultColumn var6 = (ResultColumn)this.elementAt(var5);
         var6.bindResultColumnByName(var1, var5 + 1);
         int var7 = var6.getColumnPosition() - 1;
         if (var2 != null && var4.isSet(var7)) {
            String var8 = var6.getName();
            if (var2 instanceof UpdateNode) {
               throw StandardException.newException("42X16", new Object[]{var8});
            }

            throw StandardException.newException("42X13", new Object[]{var8});
         }

         var4.set(var7);
      }

      return var4;
   }

   void bindResultColumnsByName(ResultColumnList var1, FromVTI var2, DMLStatementNode var3) throws StandardException {
      int var4 = this.size();
      HashSet var5 = new HashSet(var4 + 2, 0.999F);

      for(int var6 = 0; var6 < var4; ++var6) {
         ResultColumn var8 = (ResultColumn)this.elementAt(var6);
         String var9 = var8.getName();
         boolean var10 = !var5.add(var9);
         if (var10) {
            if (var3 instanceof UpdateNode) {
               throw StandardException.newException("42X16", new Object[]{var9});
            }

            throw StandardException.newException("42X13", new Object[]{var9});
         }

         ResultColumn var7 = var1.getResultColumn((String)null, var8.getName());
         if (var7 == null) {
            throw StandardException.newException("42X14", new Object[]{var8.getName(), var2.getMethodCall().getJavaClassName()});
         }

         ColumnDescriptor var11 = new ColumnDescriptor(var8.getName(), var7.getVirtualColumnId(), var7.getType(), (DataValueDescriptor)null, (DefaultInfo)null, (TableDescriptor)null, (UUID)null, 0L, 0L, false);
         var8.setColumnDescriptor((TableDescriptor)null, var11);
         var8.setVirtualColumnId(var6 + 1);
      }

   }

   void bindResultColumnsByPosition(TableDescriptor var1) throws StandardException {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ((ResultColumn)this.elementAt(var3)).bindResultColumnByPosition(var1, var3 + 1);
      }

   }

   void preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      int var5 = this.size();

      for(int var6 = 0; var6 < var5; ++var6) {
         ResultColumn var7 = (ResultColumn)this.elementAt(var6);
         this.setElementAt(var7.preprocess(var1, var2, var3, var4), var6);
      }

   }

   void checkStorableExpressions(ResultColumnList var1) throws StandardException {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ResultColumn var4 = (ResultColumn)var1.elementAt(var3);
         ((ResultColumn)this.elementAt(var3)).checkStorableExpression(var4);
      }

   }

   int[] getStreamStorableColIds(int var1) throws StandardException {
      int var2 = 0;
      boolean[] var3 = new boolean[var1];

      for(ResultColumn var5 : this) {
         if (var5.getTypeId().streamStorable()) {
            ColumnDescriptor var6 = var5.getTableColumnDescriptor();
            var3[var6.getPosition() - 1] = true;
         }
      }

      for(int var7 = 0; var7 < var3.length; ++var7) {
         if (var3[var7]) {
            ++var2;
         }
      }

      if (var2 == 0) {
         return null;
      } else {
         int[] var8 = new int[var2];
         int var9 = 0;

         for(int var10 = 0; var10 < var3.length; ++var10) {
            if (var3[var10]) {
               var8[var9++] = var10;
            }
         }

         return var8;
      }
   }

   void checkStorableExpressions() throws StandardException {
      for(ResultColumn var2 : this) {
         var2.checkStorableExpression();
      }

   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateCore(var1, var2, false);
   }

   void generateNulls(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateCore(var1, var2, true);
   }

   void generateCore(ExpressionClassBuilder var1, MethodBuilder var2, boolean var3) throws StandardException {
      MethodBuilder var4 = var1.newUserExprFun();
      this.generateEvaluatedRow(var1, var4, var3, false);
      var1.pushMethodReference(var2, var4);
   }

   void generateEvaluatedRow(ExpressionClassBuilder var1, MethodBuilder var2, boolean var3, boolean var4) throws StandardException {
      LocalField var5 = var1.newFieldDeclaration(2, "org.apache.derby.iapi.sql.execute.ExecRow");
      this.genCreateRow(var1, var5, "getValueRow", "org.apache.derby.iapi.sql.execute.ExecRow", this.size());
      int var7 = this.size();
      MethodBuilder var8 = var1.getConstructor();

      for(int var9 = 0; var9 < var7; ++var9) {
         ResultColumn var6 = (ResultColumn)this.elementAt(var9);
         if (!var3) {
            ValueNode var10 = var6.getExpression();
            if (var10 instanceof VirtualColumnNode && !((VirtualColumnNode)var10).getCorrelated()) {
               continue;
            }

            if (var6.getJoinResultSet() != null) {
               ResultColumnList var11 = var6.getJoinResultSet().getResultColumns();
               int var12 = var6.getJoinResultSet().getResultSetNumber();
               int var13 = -1;
               int var14 = -1;

               for(ResultColumn var16 : var11) {
                  if (var16.getName().equals(var6.getUnderlyingOrAliasName())) {
                     if (var16.isRightOuterJoinUsingClause()) {
                        var13 = var16.getVirtualColumnId();
                     } else {
                        var14 = var16.getVirtualColumnId();
                     }
                  }
               }

               var2.getField(var5);
               var2.push(var9 + 1);
               String var19 = this.getTypeCompiler(DataTypeDescriptor.getBuiltInDataTypeDescriptor(16).getTypeId()).interfaceName();
               String var20 = "org.apache.derby.iapi.types.DataValueDescriptor";
               var1.pushColumnReference(var2, var12, var14);
               var2.cast(var6.getTypeCompiler().interfaceName());
               var2.cast(var20);
               var2.callMethod((short)185, (String)null, "isNullOp", var19, 0);
               var2.cast("org.apache.derby.iapi.types.BooleanDataValue");
               var2.push(true);
               var2.callMethod((short)185, (String)null, "equals", "boolean", 1);
               var2.conditionalIf();
               var1.pushColumnReference(var2, var12, var13);
               var2.cast(var6.getTypeCompiler().interfaceName());
               var2.startElseCode();
               var1.pushColumnReference(var2, var12, var14);
               var2.cast(var6.getTypeCompiler().interfaceName());
               var2.completeConditional();
               var2.cast("org.apache.derby.iapi.types.DataValueDescriptor");
               var2.callMethod((short)185, "org.apache.derby.iapi.sql.Row", "setColumn", "void", 2);
               continue;
            }

            if (!var4 && var10 instanceof ColumnReference && !((ColumnReference)var10).getCorrelated()) {
               continue;
            }
         }

         if (var6.hasGenerationClause()) {
            ValueNode var17 = var6.getExpression();
            if (var17 != null && !(var17 instanceof VirtualColumnNode)) {
               continue;
            }
         }

         if (!var3 && var6.getExpression() instanceof ConstantNode && !((ConstantNode)var6.getExpression()).isNull() && !var8.statementNumHitLimit(1)) {
            var8.getField(var5);
            var8.push(var9 + 1);
            var6.generateExpression(var1, var8);
            var8.cast("org.apache.derby.iapi.types.DataValueDescriptor");
            var8.callMethod((short)185, "org.apache.derby.iapi.sql.Row", "setColumn", "void", 2);
         } else {
            var2.getField(var5);
            var2.push(var9 + 1);
            boolean var18 = true;
            if (var6.isAutoincrementGenerated()) {
               var2.pushThis();
               var2.push(var6.getColumnPosition());
               var2.push(var6.getTableColumnDescriptor().getAutoincInc());
               var2.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", "getSetAutoincrementValue", "org.apache.derby.iapi.types.DataValueDescriptor", 2);
               var18 = false;
            } else if (!var3 && (!(var6.getExpression() instanceof ConstantNode) || !((ConstantNode)var6.getExpression()).isNull())) {
               var6.generateExpression(var1, var2);
            } else {
               var2.getField(var5);
               var2.push(var9 + 1);
               var2.callMethod((short)185, "org.apache.derby.iapi.sql.Row", "getColumn", "org.apache.derby.iapi.types.DataValueDescriptor", 1);
               var1.generateNullWithExpress(var2, var6.getTypeCompiler(), var6.getTypeServices().getCollationType());
            }

            if (var18) {
               var2.cast("org.apache.derby.iapi.types.DataValueDescriptor");
            }

            var2.callMethod((short)185, "org.apache.derby.iapi.sql.Row", "setColumn", "void", 2);
         }
      }

      var2.getField(var5);
      var2.methodReturn();
      var2.complete();
   }

   public ExecRow buildEmptyRow() throws StandardException {
      int var1 = this.size();
      ExecRow var2 = this.getExecutionFactory().getValueRow(var1);
      int var3 = 1;

      for(ResultColumn var5 : this) {
         DataTypeDescriptor var6 = var5.getTypeServices();
         DataValueDescriptor var7 = var6.getNull();
         var2.setColumn(var3++, var7);
      }

      return var2;
   }

   public ExecRow buildEmptyIndexRow(TableDescriptor var1, ConglomerateDescriptor var2, StoreCostController var3, DataDictionary var4) throws StandardException {
      int[] var6 = var2.getIndexDescriptor().baseColumnPositions();
      ExecRow var7 = this.getExecutionFactory().getValueRow(var6.length + 1);

      for(int var8 = 0; var8 < var6.length; ++var8) {
         ColumnDescriptor var9 = var1.getColumnDescriptor(var6[var8]);
         DataTypeDescriptor var10 = var9.getType();
         DataValueDescriptor var11 = var10.getNull();
         var7.setColumn(var8 + 1, var11);
      }

      RowLocation var12 = var3.newRowLocationTemplate();
      var7.setColumn(var6.length + 1, var12);
      return var7;
   }

   ExecRowBuilder buildRowTemplate(FormatableBitSet var1, boolean var2) throws StandardException {
      int var3 = var1 == null ? this.size() : var1.getNumBitsSet();
      ExecRowBuilder var4 = new ExecRowBuilder(var3, this.indexRow);
      int var5 = var1 == null ? 0 : var1.anySetBit();

      for(ResultColumn var7 : this) {
         ValueNode var8 = var7.getExpression();
         if (var8 instanceof CurrentRowLocationNode) {
            var4.setColumn(var5 + 1, this.newRowLocationTemplate());
         } else {
            if (var2 && var8 instanceof VirtualColumnNode) {
               continue;
            }

            var4.setColumn(var5 + 1, var7.getType());
         }

         if (var1 == null) {
            ++var5;
         } else {
            var5 = var1.anySetBit(var5);
         }
      }

      return var4;
   }

   ExecRowBuilder buildRowTemplate() throws StandardException {
      return this.buildRowTemplate((FormatableBitSet)null, false);
   }

   private void genCreateRow(ExpressionClassBuilder var1, LocalField var2, String var3, String var4, int var5) throws StandardException {
      MethodBuilder var6 = var1.getConstructor();
      var1.pushGetExecutionFactoryExpression(var6);
      var6.push(var5);
      var6.callMethod((short)185, (String)null, var3, var4, 1);
      var6.setField(var2);
      var6.statementNumHitLimit(1);
   }

   private RowLocation newRowLocationTemplate() throws StandardException {
      LanguageConnectionContext var1 = this.getLanguageConnectionContext();
      DataDictionary var2 = var1.getDataDictionary();
      int var3 = var2.getCacheMode() == 1 ? 2 : 0;
      ConglomerateController var4 = var1.getTransactionCompile().openConglomerate(this.conglomerateId, false, 0, 6, var3);

      RowLocation var5;
      try {
         var5 = var4.newRowLocationTemplate();
      } finally {
         var4.close();
      }

      return var5;
   }

   ResultColumnDescriptor[] makeResultDescriptors() {
      ResultColumnDescriptor[] var1 = new ResultColumnDescriptor[this.size()];
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         var1[var3] = this.getExecutionFactory().getResultColumnDescriptor((ResultColumnDescriptor)this.elementAt(var3));
      }

      return var1;
   }

   void expandAllsAndNameColumns(FromList var1) throws StandardException {
      boolean var2 = false;

      for(int var5 = 0; var5 < this.size(); ++var5) {
         ResultColumn var6 = (ResultColumn)this.elementAt(var5);
         if (!(var6 instanceof AllResultColumn)) {
            var6.guaranteeColumnName();
         } else {
            var2 = true;
            TableName var7 = var6.getTableNameObject();
            TableName var4;
            if (var7 != null) {
               String var8 = var7.getSchemaName();
               String var9 = var7.getTableName();
               var4 = this.makeTableName(var8, var9);
            } else {
               var4 = null;
            }

            ResultColumnList var3 = var1.expandAll(var4);
            var3.nameAllResultColumns();
            this.removeElementAt(var5);

            for(int var12 = 0; var12 < var3.size(); ++var12) {
               this.insertElementAt((ResultColumn)var3.elementAt(var12), var5 + var12);
            }

            var5 += var3.size() - 1;
            this.markInitialSize();
         }
      }

      if (var2) {
         int var10 = this.size();

         for(int var11 = 0; var11 < var10; ++var11) {
            ((ResultColumn)this.elementAt(var11)).setVirtualColumnId(var11 + 1);
         }
      }

   }

   void nameAllResultColumns() throws StandardException {
      for(ResultColumn var2 : this) {
         var2.guaranteeColumnName();
      }

   }

   boolean columnTypesAndLengthsMatch() throws StandardException {
      for(ResultColumn var2 : this) {
         if (!var2.isGenerated() && !var2.columnTypeAndLengthMatch()) {
            return false;
         }
      }

      return true;
   }

   boolean columnTypesAndLengthsMatch(ResultColumnList var1) throws StandardException {
      boolean var2 = true;
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ResultColumn var5 = (ResultColumn)this.elementAt(var4);
         ResultColumn var6 = (ResultColumn)var1.elementAt(var4);
         if (!var5.isGenerated() && !var6.isGenerated() && !var5.columnTypeAndLengthMatch(var6)) {
            var2 = false;
         }
      }

      return var2;
   }

   boolean nopProjection(ResultColumnList var1) {
      if (this.size() != var1.size()) {
         return false;
      } else {
         int var2 = this.size();

         for(int var3 = 0; var3 < var2; ++var3) {
            ResultColumn var4 = (ResultColumn)this.elementAt(var3);
            ResultColumn var5;
            if (var4.getExpression() instanceof VirtualColumnNode) {
               var5 = ((VirtualColumnNode)var4.getExpression()).getSourceColumn();
            } else {
               if (!(var4.getExpression() instanceof ColumnReference)) {
                  return false;
               }

               var5 = ((ColumnReference)var4.getExpression()).getSource();
            }

            ResultColumn var6 = (ResultColumn)var1.elementAt(var3);
            if (var5 != var6) {
               return false;
            }
         }

         return true;
      }
   }

   ResultColumnList copyListAndObjects() throws StandardException {
      ResultColumnList var1 = new ResultColumnList(this.getContextManager());

      for(ResultColumn var3 : this) {
         var1.addResultColumn(var3.cloneMe());
      }

      var1.copyOrderBySelect(this);
      return var1;
   }

   void removeOrderByColumns() {
      int var1 = this.size() - 1;

      for(int var2 = 0; var2 < this.orderBySelect; --var1) {
         this.removeElementAt(var1);
         ++var2;
      }

      this.orderBySelect = 0;
   }

   void genVirtualColumnNodes(ResultSetNode var1, ResultColumnList var2) throws StandardException {
      this.genVirtualColumnNodes(var1, var2, true);
   }

   void genVirtualColumnNodes(ResultSetNode var1, ResultColumnList var2, boolean var3) throws StandardException {
      int var4 = this.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ResultColumn var6 = (ResultColumn)this.elementAt(var5);
         var6.setExpression(new VirtualColumnNode(var1, (ResultColumn)var2.elementAt(var5), var5 + 1, this.getContextManager()));
         if (var3) {
            var6.setReferenced();
         }
      }

   }

   void adjustVirtualColumnIds(int var1) {
      for(ResultColumn var3 : this) {
         var3.adjustVirtualColumnId(var1);
         VirtualColumnNode var10000 = (VirtualColumnNode)var3.getExpression();
         var10000.columnId += var1;
      }

   }

   void doProjection() throws StandardException {
      int var1 = 0;
      ResultColumnList var2 = new ResultColumnList(this.getContextManager());

      for(ResultColumn var4 : this) {
         if (!var4.isReferenced() && var4.getExpression() instanceof VirtualColumnNode && !((VirtualColumnNode)var4.getExpression()).getSourceColumn().isReferenced()) {
            var2.addElement(var4);
            ++var1;
         } else {
            if (var1 >= 1) {
               var4.adjustVirtualColumnId(-var1);
            }

            var4.setReferenced();
         }
      }

      for(int var5 = 0; var5 < var2.size(); ++var5) {
         this.removeElement((ResultColumn)var2.elementAt(var5));
      }

   }

   String verifyUniqueNames(boolean var1) throws StandardException {
      HashSet var2 = new HashSet(this.size() + 2, 0.999F);

      for(ResultColumn var4 : this) {
         if (var1 && var4.isNameGenerated()) {
            throw StandardException.newException("42908", new Object[0]);
         }

         String var5 = var4.getName();
         boolean var6 = !var2.add(var5);
         if (var6) {
            return var5;
         }
      }

      return null;
   }

   void propagateDCLInfo(ResultColumnList var1, String var2) throws StandardException {
      if (var1.size() != this.size() && !var1.getCountMismatchAllowed() && this.visibleSize() != var1.visibleSize()) {
         throw StandardException.newException("42X32", new Object[]{var2});
      } else {
         String var3 = var1.verifyUniqueNames(false);
         if (var3 != null) {
            throw StandardException.newException("42X33", new Object[]{var3});
         } else {
            this.copyResultColumnNames(var1);
         }
      }
   }

   void rejectParameters() throws StandardException {
      for(ResultColumn var2 : this) {
         var2.rejectParameter();
      }

   }

   void rejectXMLValues() throws StandardException {
      int var1 = this.size();

      for(int var2 = 1; var2 <= var1; ++var2) {
         if (var2 <= this.initialListSize) {
            ResultColumn var3 = this.getResultColumn(var2);
            if (var3 != null && var3.getType() != null && var3.getType().getTypeId().isXMLTypeId()) {
               throw StandardException.newException("42Z71", new Object[0]);
            }
         }
      }

   }

   void setResultSetNumber(int var1) {
      for(ResultColumn var3 : this) {
         var3.setResultSetNumber(var1);
      }

   }

   void setRedundant() {
      for(ResultColumn var2 : this) {
         var2.setRedundant();
      }

   }

   void checkColumnUpdateability(ExecPreparedStatement var1, String var2) throws StandardException {
      for(ResultColumn var4 : this) {
         if (var4.updated() && !var1.isUpdateColumn(var4.getName())) {
            throw StandardException.newException("42X31", new Object[]{var4.getName(), var2});
         }
      }

   }

   void setUnionResultExpression(ResultColumnList var1, int var2, int var3, String var4) throws StandardException {
      TableName var5 = new TableName((String)null, (String)null, this.getContextManager());
      int var6 = this.visibleSize();

      for(int var7 = 0; var7 < var6; ++var7) {
         ResultColumn var9 = (ResultColumn)this.elementAt(var7);
         ResultColumn var10 = (ResultColumn)var1.elementAt(var7);
         ValueNode var11 = var9.getExpression();
         ValueNode var12 = var10.getExpression();
         if (!var10.isAutoincrementGenerated() && var9.isAutoincrementGenerated()) {
            var9.resetAutoincrementGenerated();
         }

         TypeId var13 = var11.getTypeId();
         if (var13 != null) {
            TypeId var14 = var12.getTypeId();
            if (var14 != null) {
               ClassFactory var15 = this.getClassFactory();
               if (!this.unionCompatible(var11, var12)) {
                  throw StandardException.newException("42X61", new Object[]{var13.getSQLTypeName(), var14.getSQLTypeName(), var4});
               }

               DataTypeDescriptor var16 = var11.getTypeServices().getDominantType(var12.getTypeServices(), var15);
               ColumnReference var8 = new ColumnReference(var9.getName(), var5, this.getContextManager());
               var8.setType(var16);
               if (var11 instanceof ColumnReference) {
                  var8.copyFields((ColumnReference)var11);
               } else {
                  var8.setNestingLevel(var3);
                  var8.setSourceLevel(var3);
               }

               var8.setTableNumber(var2);
               var9.setExpression(var8);
               var9.setType(var9.getTypeServices().getDominantType(var10.getTypeServices(), var15));
               if (var9.getName() != null && !var9.isNameGenerated() && var10.getName() != null) {
                  if (var10.isNameGenerated()) {
                     var9.setName(var10.getName());
                     var9.setNameGenerated(true);
                  } else if (!var9.getName().equals(var10.getName())) {
                     var9.setName((String)null);
                     var9.guaranteeColumnName();
                     var9.setNameGenerated(true);
                  }
               }
            }
         }
      }

   }

   private boolean unionCompatible(ValueNode var1, ValueNode var2) throws StandardException {
      TypeId var3 = var1.getTypeId();
      TypeId var4 = var2.getTypeId();
      ClassFactory var5 = this.getClassFactory();
      if (!var1.getTypeCompiler().storable(var4, var5) && !var2.getTypeCompiler().storable(var3, var5)) {
         return false;
      } else {
         return var3.isBooleanTypeId() == var4.isBooleanTypeId();
      }
   }

   boolean isExactTypeAndLengthMatch(ResultColumnList var1) throws StandardException {
      int var2 = this.visibleSize();

      for(int var3 = 0; var3 < var2; ++var3) {
         ResultColumn var4 = (ResultColumn)this.elementAt(var3);
         ResultColumn var5 = (ResultColumn)var1.elementAt(var3);
         if (!var4.getTypeServices().isExactTypeAndLengthMatch(var5.getTypeServices())) {
            return false;
         }
      }

      return true;
   }

   public boolean updateOverlaps(int[] var1) {
      for(ResultColumn var3 : this) {
         if (var3.updated()) {
            int var4 = var3.getColumnPosition();

            for(int var5 = 0; var5 < var1.length; ++var5) {
               if (var1[var5] == var4) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   ResultColumn[] getSortedByPosition() {
      int var1 = this.size();
      ResultColumn[] var2 = new ResultColumn[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         var2[var3] = (ResultColumn)this.elementAt(var3);
      }

      Arrays.sort(var2);
      return var2;
   }

   public int[] sortMe() {
      ResultColumn[] var1 = this.getSortedByPosition();
      int[] var2 = new int[var1.length];

      for(int var3 = 0; var3 < var1.length; ++var3) {
         var2[var3] = var1[var3].getColumnPosition();
      }

      return var2;
   }

   ResultColumnList expandToAll(TableDescriptor var1, TableName var2) throws StandardException {
      ResultColumnList var5 = new ResultColumnList(this.getContextManager());
      ResultColumn[] var6 = this.getSortedByPosition();
      int var7 = 0;
      ColumnDescriptorList var8 = var1.getColumnDescriptorList();
      int var9 = var8.size();

      for(int var10 = 0; var10 < var9; ++var10) {
         ColumnDescriptor var4 = var8.elementAt(var10);
         ResultColumn var3;
         if (var7 < var6.length && var4.getPosition() == var6[var7].getColumnPosition()) {
            var3 = var6[var7];
            ++var7;
         } else {
            var3 = this.makeColumnReferenceFromName(var2, var4.getColumnName());
            var3.bindResultColumnByPosition(var1, var4.getPosition());
         }

         var5.addResultColumn(var3);
      }

      return var5;
   }

   void bindUntypedNullsToResultColumns(ResultColumnList var1) throws StandardException {
      if (var1 == null) {
         throw StandardException.newException("42X07", new Object[0]);
      } else {
         int var2 = this.size();

         for(int var3 = 0; var3 < var2; ++var3) {
            ResultColumn var4 = (ResultColumn)var1.elementAt(var3);
            ResultColumn var5 = (ResultColumn)this.elementAt(var3);
            var5.typeUntypedNullExpression(var4);
         }

      }
   }

   void markUpdated() {
      for(ResultColumn var2 : this) {
         var2.markUpdated();
      }

   }

   void markUpdatableByCursor() {
      for(ResultColumn var2 : this) {
         if (var2.getSourceTableName() != null && var2.getExpression() != null && var2.getExpression().getColumnName().equals(var2.getName())) {
            var2.markUpdatableByCursor();
         }
      }

   }

   String verifyCreateConstraintColumnList(TableElementList var1) {
      for(ResultColumn var3 : this) {
         String var4 = var3.getName();
         if (!var1.containsColumnName(var4)) {
            return var4;
         }
      }

      return null;
   }

   void exportNames(String[] var1) {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         var1[var3] = ((ResultColumn)this.elementAt(var3)).getName();
      }

   }

   ResultColumn findParentResultColumn(ResultColumn var1) {
      ResultColumn var2 = null;

      for(ResultColumn var4 : this) {
         if (var4.getExpression() instanceof ColumnReference) {
            ColumnReference var6 = (ColumnReference)var4.getExpression();
            if (var6.getSource() == var1) {
               var2 = var4;
               break;
            }
         } else if (var4.getExpression() instanceof VirtualColumnNode) {
            VirtualColumnNode var5 = (VirtualColumnNode)var4.getExpression();
            if (var5.getSourceColumn() == var1) {
               var2 = var4;
               break;
            }
         }
      }

      return var2;
   }

   void markUpdated(ResultColumnList var1) {
      for(ResultColumn var3 : var1) {
         ResultColumn var4 = this.getResultColumn(var3.getName());
         if (var4 != null) {
            var4.markUpdated();
         }
      }

   }

   void markColumnsInSelectListUpdatableByCursor(List var1) {
      this.commonCodeForUpdatableByCursor(var1, true);
   }

   private void commonCodeForUpdatableByCursor(List var1, boolean var2) {
      if (var1 != null && !var1.isEmpty()) {
         int var3 = var1.size();

         for(int var6 = 0; var6 < var3; ++var6) {
            String var5 = (String)var1.get(var6);
            ResultColumn var4 = this.getResultColumn(var5);
            if (var4 != null || !var2) {
               var4.markUpdatableByCursor();
            }
         }
      } else {
         this.markUpdatableByCursor();
      }

   }

   void markUpdatableByCursor(List var1) {
      this.commonCodeForUpdatableByCursor(var1, false);
   }

   boolean updatableByCursor(int var1) {
      return this.getResultColumn(var1).updatableByCursor();
   }

   boolean isCloneable() {
      boolean var1 = true;

      for(ResultColumn var3 : this) {
         if (!var3.getExpression().isCloneable()) {
            var1 = false;
            break;
         }
      }

      return var1;
   }

   void remapColumnReferencesToExpressions() throws StandardException {
      for(ResultColumn var2 : this) {
         if (var2.getExpression() != null) {
            var2.setExpression(var2.getExpression().remapColumnReferencesToExpressions());
         }
      }

   }

   void setIndexRow(long var1, boolean var3) {
      this.indexRow = true;
      this.conglomerateId = var1;
      this.forUpdate = var3;
   }

   boolean hasConsistentTypeInfo() throws StandardException {
      boolean var1 = true;
      return var1;
   }

   boolean containsAllResultColumn() {
      boolean var1 = false;
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         if (this.elementAt(var3) instanceof AllResultColumn) {
            var1 = true;
            break;
         }
      }

      return var1;
   }

   int countReferencedColumns() {
      int var1 = 0;

      for(ResultColumn var3 : this) {
         if (var3.isReferenced()) {
            ++var1;
         }
      }

      return var1;
   }

   void recordColumnReferences(int[] var1, int var2) {
      int var3 = 0;
      int var4 = this.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ResultColumn var6 = (ResultColumn)this.elementAt(var5);
         if (var6.isReferenced()) {
            var1[var3++] = var5 + var2;
         }
      }

   }

   int getPosition(String var1, int var2) {
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ResultColumn var5 = (ResultColumn)this.elementAt(var4);
         if (var1.equals(var5.getName())) {
            return var4 + var2;
         }
      }

      return -1;
   }

   void recordColumnReferences(boolean[] var1, JBitSet[] var2, int var3) {
      for(ResultColumn var5 : this) {
         if (var5.getExpression() instanceof ColumnReference) {
            int var6 = ((ColumnReference)var5.getExpression()).getColumnNumber();
            var1[var6] = true;
            var2[var3].set(var6);
         }
      }

   }

   int allTopCRsFromSameTable() {
      int var1 = -1;

      for(ResultColumn var3 : this) {
         ValueNode var4 = var3.getExpression();
         if (var4 instanceof ColumnReference var5) {
            if (var1 == -1) {
               var1 = var5.getTableNumber();
            } else if (var1 != var5.getTableNumber()) {
               return -1;
            }
         }
      }

      return var1;
   }

   void clearColumnReferences() {
      for(ResultColumn var2 : this) {
         if (var2.isReferenced()) {
            var2.setUnreferenced();
         }
      }

   }

   void copyReferencedColumnsToNewList(ResultColumnList var1) {
      for(ResultColumn var3 : this) {
         if (var3.isReferenced()) {
            var1.addElement(var3);
         }
      }

   }

   void copyColumnsToNewList(ResultColumnList var1, FormatableBitSet var2) {
      for(ResultColumn var4 : this) {
         if (var2.isSet(var4.getColumnPosition())) {
            var1.addElement(var4);
         }
      }

   }

   FormatableBitSet getColumnReferenceMap() {
      FormatableBitSet var1 = new FormatableBitSet(this.size());
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ResultColumn var4 = (ResultColumn)this.elementAt(var3);
         if (var4.isReferenced()) {
            var1.set(var3);
         }
      }

      return var1;
   }

   void pullVirtualIsReferenced() {
      for(ResultColumn var2 : this) {
         var2.pullVirtualIsReferenced();
      }

   }

   void clearTableNames() {
      for(ResultColumn var2 : this) {
         var2.clearTableName();
      }

   }

   protected void setCountMismatchAllowed(boolean var1) {
      this.countMismatchAllowed = var1;
   }

   protected boolean getCountMismatchAllowed() {
      return this.countMismatchAllowed;
   }

   int getTotalColumnSize() {
      int var1 = 0;

      for(ResultColumn var3 : this) {
         var1 += var3.getMaximumColumnSize();
      }

      return var1;
   }

   void createListFromResultSetMetaData(ResultSetMetaData var1, TableName var2, String var3) throws StandardException {
      try {
         int var4 = var1.getColumnCount();
         if (var4 <= 0) {
            throw StandardException.newException("42X57", new Object[]{var3, String.valueOf(var4)});
         } else {
            for(int var5 = 1; var5 <= var4; ++var5) {
               boolean var6 = var1.isNullable(var5) != 0;
               int var8 = var1.getColumnType(var5);
               TypeId var7;
               switch (var8) {
                  case 1111:
                  case 2000:
                     var7 = TypeId.getUserDefinedTypeId(var1.getColumnTypeName(var5));
                     break;
                  default:
                     var7 = TypeId.getBuiltInTypeId(var8);
               }

               if (var7 == null) {
                  throw StandardException.newException("42Y23", new Object[]{Integer.toString(var5)});
               }

               int var9;
               if (var7.variableLength()) {
                  var9 = var1.getColumnDisplaySize(var5);
               } else if (var8 != -1 && var8 != -4) {
                  var9 = 0;
               } else {
                  var9 = Integer.MAX_VALUE;
               }

               int var10 = var7.isDecimalTypeId() ? var1.getPrecision(var5) : 0;
               int var11 = var7.isDecimalTypeId() ? var1.getScale(var5) : 0;
               DataTypeDescriptor var12 = new DataTypeDescriptor(var7, var10, var11, var6, var9);
               this.addColumn(var2, var1.getColumnName(var5), var12);
            }

         }
      } catch (Throwable var13) {
         if (var13 instanceof StandardException) {
            throw (StandardException)var13;
         } else {
            throw StandardException.unexpectedUserException(var13);
         }
      }
   }

   public ResultColumn addColumn(TableName var1, String var2, DataTypeDescriptor var3) throws StandardException {
      BaseColumnNode var4 = new BaseColumnNode(var2, var1, var3, this.getContextManager());
      ResultColumn var5 = new ResultColumn(var2, var4, this.getContextManager());
      var5.setType(var3);
      this.addResultColumn(var5);
      return var5;
   }

   void addRCForRID() throws StandardException {
      CurrentRowLocationNode var2 = new CurrentRowLocationNode(this.getContextManager());
      ResultColumn var1 = new ResultColumn("", var2, this.getContextManager());
      var1.markGenerated();
      this.addResultColumn(var1);
   }

   void markAllUnreferenced() throws StandardException {
      for(ResultColumn var2 : this) {
         var2.setUnreferenced();
      }

   }

   boolean allExpressionsAreColumns(ResultSetNode var1) {
      for(ResultColumn var3 : this) {
         if (var3.isRightOuterJoinUsingClause()) {
            return false;
         }

         ValueNode var4 = var3.getExpression();
         if (!(var4 instanceof VirtualColumnNode) && !(var4 instanceof ColumnReference)) {
            return false;
         }

         if (var4 instanceof VirtualColumnNode var5) {
            if (var5.getSourceResultSet() != var1) {
               var5.setCorrelated();
               return false;
            }
         }

         if (var4 instanceof ColumnReference var6) {
            if (var6.getCorrelated()) {
               return false;
            }
         }
      }

      return true;
   }

   ColumnMapping mapSourceColumns() {
      int[] var1 = new int[this.size()];
      boolean[] var2 = new boolean[this.size()];
      HashMap var4 = new HashMap();
      int var5 = this.size();

      for(int var6 = 0; var6 < var5; ++var6) {
         ResultColumn var3 = (ResultColumn)this.elementAt(var6);
         if (var3.getExpression() instanceof VirtualColumnNode) {
            VirtualColumnNode var7 = (VirtualColumnNode)var3.getExpression();
            if (var7.getCorrelated()) {
               var1[var6] = -1;
            } else {
               ResultColumn var8 = var7.getSourceColumn();
               updateArrays(var1, var2, var4, var8, var6);
            }
         } else if (var3.isRightOuterJoinUsingClause()) {
            var1[var6] = -1;
         } else if (var3.getExpression() instanceof ColumnReference) {
            ColumnReference var10 = (ColumnReference)var3.getExpression();
            if (var10.getCorrelated()) {
               var1[var6] = -1;
            } else {
               ResultColumn var11 = var10.getSource();
               updateArrays(var1, var2, var4, var11, var6);
            }
         } else {
            var1[var6] = -1;
         }
      }

      ColumnMapping var9 = new ColumnMapping(var1, var2);
      return var9;
   }

   void setNullability(boolean var1) throws StandardException {
      for(ResultColumn var3 : this) {
         var3.setNullability(var1);
      }

   }

   FormatableBitSet getReferencedFormatableBitSet(boolean var1, boolean var2, boolean var3) {
      int var5 = 0;
      int var6 = this.size();
      FormatableBitSet var7 = new FormatableBitSet(var6);
      if (var1) {
         if (!var2) {
            return null;
         } else {
            for(int var10 = 0; var10 < var6; ++var10) {
               var7.set(var10);
            }

            return var7;
         }
      } else {
         int var4;
         for(var4 = 0; var4 < var6; ++var4) {
            ResultColumn var8 = (ResultColumn)this.elementAt(var4);
            if (var8.isReferenced()) {
               if (var3 && !(var8.getExpression() instanceof BaseColumnNode)) {
                  boolean var9 = !(var8.getExpression() instanceof BaseColumnNode) && !(var8.getExpression() instanceof CurrentRowLocationNode);
                  if (var9) {
                     continue;
                  }
               }

               var7.set(var4);
               ++var5;
            }
         }

         if (var5 == var4 && !var2) {
            return null;
         } else {
            return var7;
         }
      }
   }

   ResultColumnList compactColumns(boolean var1, boolean var2) throws StandardException {
      int var4 = 0;
      if (var1) {
         return this;
      } else {
         ResultColumnList var5 = new ResultColumnList(this.getContextManager());
         int var6 = this.size();

         int var3;
         for(var3 = 0; var3 < var6; ++var3) {
            ResultColumn var7 = (ResultColumn)this.elementAt(var3);
            if (var7.isReferenced()) {
               var5.addResultColumn(var7);
               ++var4;
            }
         }

         if (var4 == var3 && !var2) {
            return this;
         } else {
            return var5;
         }
      }
   }

   void removeJoinColumns(ResultColumnList var1) {
      for(ResultColumn var3 : var1) {
         String var4 = var3.getName();
         ResultColumn var5 = this.getResultColumn(var4);
         if (var5 != null) {
            this.removeElement(var5);
         }
      }

   }

   ResultColumnList getJoinColumns(ResultColumnList var1) throws StandardException {
      ResultColumnList var2 = new ResultColumnList(this.getContextManager());

      for(ResultColumn var4 : var1) {
         String var5 = var4.getName();
         ResultColumn var6 = this.getResultColumn(var5);
         if (var6 == null) {
            throw StandardException.newException("42X04", new Object[]{var5});
         }

         var2.addElement(var6);
      }

      return var2;
   }

   void resetVirtualColumnIds() {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         ((ResultColumn)this.elementAt(var2)).setVirtualColumnId(var2 + 1);
      }

   }

   boolean reusableResult() {
      for(ResultColumn var2 : this) {
         if (!(var2.getExpression() instanceof ConstantNode) && !(var2.getExpression() instanceof AggregateNode)) {
            return false;
         }
      }

      return true;
   }

   int[] getColumnPositions(TableDescriptor var1) throws StandardException {
      int var2 = this.size();
      int[] var3 = new int[var2];

      for(int var6 = 0; var6 < var2; ++var6) {
         ResultColumn var7 = (ResultColumn)this.elementAt(var6);
         String var4 = var7.getName();
         ColumnDescriptor var5 = var1.getColumnDescriptor(var4);
         if (var5 == null) {
            throw StandardException.newException("42X14", new Object[]{var4, var1.getQualifiedName()});
         }

         var3[var6] = var5.getPosition();
      }

      return var3;
   }

   String[] getColumnNames() {
      String[] var1 = new String[this.size()];
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ResultColumn var4 = (ResultColumn)this.elementAt(var3);
         var1[var3] = var4.getName();
      }

      return var1;
   }

   void replaceOrForbidDefaults(TableDescriptor var1, ResultColumnList var2, boolean var3) throws StandardException {
      int var4 = this.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ResultColumn var6 = (ResultColumn)this.elementAt(var5);
         if (var6.isDefaultColumn()) {
            if (!var3) {
               throw StandardException.newException("42Y85", new Object[0]);
            }

            ColumnDescriptor var7 = null;
            if (var2 == null) {
               var7 = var1.getColumnDescriptor(var5 + 1);
            } else if (var5 < var2.size()) {
               ResultColumn var8 = (ResultColumn)var2.elementAt(var5);
               var7 = var1.getColumnDescriptor(var8.getName());
            }

            if (var7 == null) {
               throw StandardException.newException("42X06", new Object[]{var1.getQualifiedName()});
            }

            if (var7.isAutoincrement()) {
               var6.setAutoincrementGenerated();
            }

            DefaultInfoImpl var9 = (DefaultInfoImpl)var7.getDefaultInfo();
            if (var9 != null && !var9.isGeneratedColumn()) {
               this.setDefault(var6, var7, var9);
            } else {
               var6.setExpression(new UntypedNullConstantNode(this.getContextManager()));
               var6.setWasDefaultColumn(true);
            }

            var6.setDefaultColumn(false);
         }
      }

   }

   void setDefault(ResultColumn var1, ColumnDescriptor var2, DefaultInfoImpl var3) throws StandardException {
      DefaultDescriptor var4 = var2.getDefaultDescriptor(this.getDataDictionary());
      this.getCompilerContext().createDependency(var4);
      var1.setExpression(DefaultNode.parseDefault(var3.getDefaultText(), this.getLanguageConnectionContext(), this.getCompilerContext()));
   }

   void checkForInvalidDefaults() throws StandardException {
      for(ResultColumn var2 : this) {
         if (!var2.isAutoincrementGenerated() && var2.isDefaultColumn()) {
            throw StandardException.newException("42Y85", new Object[0]);
         }
      }

   }

   void verifyAllOrderable() throws StandardException {
      for(ResultColumn var2 : this) {
         var2.verifyOrderable();
      }

   }

   public void populate(TableDescriptor var1, int[] var2) throws StandardException {
      if (var2 != null) {
         for(int var5 : var2) {
            String var4 = var1.getColumnDescriptor(var5).getColumnName();
            ResultColumn var6 = this.makeColumnFromName(var4);
            this.addResultColumn(var6);
         }

      }
   }

   private ResultColumn makeColumnFromName(String var1) throws StandardException {
      return new ResultColumn(var1, (ValueNode)null, this.getContextManager());
   }

   private ResultColumn makeColumnReferenceFromName(TableName var1, String var2) throws StandardException {
      ContextManager var3 = this.getContextManager();
      ResultColumn var4 = new ResultColumn(var2, new ColumnReference(var2, var1, var3), var3);
      return var4;
   }

   void forbidOverrides(ResultColumnList var1) throws StandardException {
      this.forbidOverrides(var1, false);
   }

   void forbidOverrides(ResultColumnList var1, boolean var2) throws StandardException {
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ResultColumn var5 = (ResultColumn)this.elementAt(var4);
         ResultColumn var6 = var1 == null ? null : (ResultColumn)var1.elementAt(var4);
         ColumnDescriptor var7 = var5.getTableColumnDescriptor();
         if (var7 != null && var7.hasGenerationClause()) {
            if (!var2 && var6 != null && !var6.hasGenerationClause() && !var6.wasDefaultColumn()) {
               throw StandardException.newException("42XA3", new Object[]{var5.getName()});
            }

            if (var6 != null) {
               var6.setColumnDescriptor(var7.getTableDescriptor(), var7);
            }
         }

         if (var7 != null && var7.isAutoincrement()) {
            if ((var6 == null || !var6.isAutoincrementGenerated()) && (!var7.isAutoincAlways() || !var2)) {
               if (var7.isAutoincAlways()) {
                  throw StandardException.newException("42Z23", new Object[]{var5.getName()});
               }
            } else {
               var6.setColumnDescriptor(var7.getTableDescriptor(), var7);
            }
         }
      }

   }

   void incOrderBySelect() {
      ++this.orderBySelect;
   }

   private void decOrderBySelect() {
      --this.orderBySelect;
   }

   int getOrderBySelect() {
      return this.orderBySelect;
   }

   public void copyOrderBySelect(ResultColumnList var1) {
      this.orderBySelect = var1.orderBySelect;
   }

   protected void markInitialSize() {
      this.initialListSize = this.size();
   }

   private int numGeneratedColumns() {
      int var1 = 0;
      int var2 = this.size();
      boolean var3 = false;

      for(int var4 = var2 - 1; var4 >= 0; --var4) {
         ResultColumn var5 = (ResultColumn)this.elementAt(var4);
         if (var5.isGenerated()) {
            ++var1;
         } else {
            var3 = true;
         }
      }

      return var1;
   }

   int numGeneratedColumnsForGroupBy() {
      int var1 = 0;
      int var2 = this.size();

      for(int var3 = var2 - 1; var3 >= 0; --var3) {
         ResultColumn var4 = (ResultColumn)this.elementAt(var3);
         if (var4.isGenerated() && var4.isGroupingColumn()) {
            ++var1;
         }
      }

      return var1;
   }

   void removeGeneratedGroupingColumns() {
      int var1 = this.size();

      for(int var2 = var1 - 1; var2 >= 0; --var2) {
         ResultColumn var3 = (ResultColumn)this.elementAt(var2);
         if (var3.isGenerated() && var3.isGroupingColumn()) {
            this.removeElementAt(var2);
         }
      }

   }

   int visibleSize() {
      return this.size() - this.orderBySelect - this.numGeneratedColumns();
   }

   public String toString() {
      return "";
   }

   private static boolean streamableType(ResultColumn var0) {
      DataTypeDescriptor var1 = var0.getType();
      TypeId var2 = TypeId.getBuiltInTypeId(var1.getTypeName());
      return var2 != null ? var2.streamStorable() : false;
   }

   private static void updateArrays(int[] var0, boolean[] var1, Map var2, ResultColumn var3, int var4) {
      int var5 = var3.getVirtualColumnId();
      var0[var4] = var5;
      if (streamableType(var3)) {
         Integer var6 = (Integer)var2.get(var5);
         if (var6 != null) {
            var1[var4] = true;
         } else {
            var2.put(var5, var4);
         }
      }

   }

   static class ColumnMapping {
      final int[] mapArray;
      final boolean[] cloneMap;

      private ColumnMapping(int[] var1, boolean[] var2) {
         this.mapArray = var1;
         this.cloneMap = var2;
      }
   }
}
