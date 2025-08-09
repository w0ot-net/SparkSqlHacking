package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.StringDataValue;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

class ResultColumn extends ValueNode implements ResultColumnDescriptor, Comparable {
   private String _underlyingName;
   private String _derivedColumnName;
   private String _unqualifiedTableName;
   private String _unqualifiedSourceTableName;
   private String _sourceSchemaName;
   private ValueNode _expression;
   private ColumnDescriptor _columnDescriptor;
   private boolean _isGenerated;
   private boolean _isGeneratedForUnmatchedColumnInInsert;
   private boolean _isGroupingColumn;
   private boolean _isReferenced;
   private boolean _isRedundant;
   private boolean _isNameGenerated;
   private boolean _updated;
   private boolean _updatableByCursor;
   private boolean defaultColumn;
   private boolean wasDefault;
   private boolean rightOuterJoinUsingClause;
   private JoinNode joinResultSet = null;
   private boolean _autoincrementGenerated;
   private boolean _autoincrement;
   private int resultSetNumber = -1;
   private ColumnReference _reference;
   private int virtualColumnId;

   ResultColumn(ContextManager var1) {
      super(var1);
   }

   ResultColumn(String var1, ValueNode var2, ContextManager var3) throws StandardException {
      super(var3);
      this.setTypeExpressionAndDefault(var2);
      this._underlyingName = var1;
      this._derivedColumnName = this._underlyingName;
   }

   ResultColumn(ColumnReference var1, ValueNode var2, ContextManager var3) throws StandardException {
      super(var3);
      this.setTypeExpressionAndDefault(var2);
      this._underlyingName = var1.getColumnName();
      this._derivedColumnName = var1.getColumnName();
      this._reference = var1;
   }

   ResultColumn(ColumnDescriptor var1, ValueNode var2, ContextManager var3) throws StandardException {
      super(var3);
      this.setTypeExpressionAndDefault(var2);
      this._underlyingName = var1.getColumnName();
      this._derivedColumnName = this._underlyingName;
      this.setType(var1.getType());
      this._columnDescriptor = var1;
      this._autoincrement = var1.isAutoincrement();
   }

   ResultColumn(DataTypeDescriptor var1, ValueNode var2, ContextManager var3) throws StandardException {
      super(var3);
      this.setTypeExpressionAndDefault(var2);
      this.setType(var1);
      if (this._expression instanceof ColumnReference) {
         this._reference = (ColumnReference)var2;
      }

   }

   private void setTypeExpressionAndDefault(ValueNode var1) {
      this.setExpression(var1);
      if (var1 != null && var1 instanceof DefaultNode) {
         this.defaultColumn = true;
      }

   }

   boolean isRightOuterJoinUsingClause() {
      return this.rightOuterJoinUsingClause;
   }

   void setRightOuterJoinUsingClause(boolean var1) {
      this.rightOuterJoinUsingClause = var1;
   }

   JoinNode getJoinResultSet() {
      return this.joinResultSet;
   }

   void setJoinResultset(JoinNode var1) {
      this.joinResultSet = var1;
   }

   boolean isDefaultColumn() {
      return this.defaultColumn;
   }

   void setDefaultColumn(boolean var1) {
      this.defaultColumn = var1;
   }

   boolean wasDefaultColumn() {
      return this.wasDefault;
   }

   void setWasDefaultColumn(boolean var1) {
      this.wasDefault = var1;
   }

   boolean columnNameMatches(String var1) {
      return var1.equals(this._derivedColumnName) || var1.equals(this._underlyingName) || var1.equals(this.getSourceColumnName());
   }

   String getUnderlyingOrAliasName() {
      if (this.getSourceColumnName() != null) {
         return this.getSourceColumnName();
      } else {
         return this._underlyingName != null ? this._underlyingName : this._derivedColumnName;
      }
   }

   boolean isUpdatable() {
      return this._derivedColumnName == null || this._underlyingName.equals(this._derivedColumnName);
   }

   String getSourceColumnName() {
      return this._expression instanceof ColumnReference ? ((ColumnReference)this._expression).getColumnName() : null;
   }

   public String getName() {
      return this._derivedColumnName;
   }

   String getSchemaName() throws StandardException {
      if (this._columnDescriptor != null && this._columnDescriptor.getTableDescriptor() != null) {
         return this._columnDescriptor.getTableDescriptor().getSchemaName();
      } else {
         return this._expression != null ? this._expression.getSchemaName() : null;
      }
   }

   String getTableName() {
      if (this._unqualifiedTableName != null) {
         return this._unqualifiedTableName;
      } else {
         return this._columnDescriptor != null && this._columnDescriptor.getTableDescriptor() != null ? this._columnDescriptor.getTableDescriptor().getName() : this._expression.getTableName();
      }
   }

   public String getSourceTableName() {
      return this._unqualifiedSourceTableName;
   }

   public String getSourceSchemaName() {
      return this._sourceSchemaName;
   }

   void clearTableName() {
      if (this._expression instanceof ColumnReference) {
         ((ColumnReference)this._expression).setQualifiedTableName((TableName)null);
      }

   }

   public DataTypeDescriptor getType() {
      return this.getTypeServices();
   }

   public int getColumnPosition() {
      return this._columnDescriptor != null ? this._columnDescriptor.getPosition() : this.virtualColumnId;
   }

   void setExpression(ValueNode var1) {
      this._expression = var1;
   }

   ValueNode getExpression() {
      return this._expression;
   }

   void setExpressionToNullNode() throws StandardException {
      this.setExpression(this.getNullNode(this.getTypeServices()));
   }

   void setName(String var1) {
      if (this._underlyingName == null) {
         this._underlyingName = var1;
      }

      this._derivedColumnName = var1;
   }

   boolean isNameGenerated() {
      return this._isNameGenerated;
   }

   void setNameGenerated(boolean var1) {
      this._isNameGenerated = var1;
   }

   void setResultSetNumber(int var1) {
      this.resultSetNumber = var1;
   }

   public int getResultSetNumber() {
      return this.resultSetNumber;
   }

   void adjustVirtualColumnId(int var1) {
      this.virtualColumnId += var1;
   }

   void setVirtualColumnId(int var1) {
      this.virtualColumnId = var1;
   }

   int getVirtualColumnId() {
      return this.virtualColumnId;
   }

   void collapseVirtualColumnIdGap(int var1) {
      if (this._columnDescriptor == null && this.virtualColumnId > var1) {
         --this.virtualColumnId;
      }

   }

   void guaranteeColumnName() throws StandardException {
      if (this._derivedColumnName == null) {
         this._derivedColumnName = "SQLCol" + this.getCompilerContext().getNextColumnNumber();
         this._isNameGenerated = true;
      }

   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   ResultColumn bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      if (this._expression.requiresTypeFromContext() && this.getTypeServices() != null) {
         this._expression.setType(this.getTypeServices());
      }

      if (this._expression.getTableName() == null) {
         var1.isJoinColumnForRightOuterJoin(this);
      }

      this.setExpression(this._expression.bindExpression(var1, var2, var3));
      if (this._expression instanceof ColumnReference) {
         this._autoincrement = ((ColumnReference)this._expression).getSource().isAutoincrement();
      }

      return this;
   }

   void bindResultColumnByPosition(TableDescriptor var1, int var2) throws StandardException {
      ColumnDescriptor var3 = var1.getColumnDescriptor(var2);
      if (var3 == null) {
         String var4 = "";
         String var5 = var1.getSchemaName();
         if (var5 != null) {
            var4 = var4 + var5 + ".";
         }

         var4 = var4 + var1.getName();
         throw StandardException.newException("42X06", new Object[]{var4});
      } else {
         this.setColumnDescriptor(var1, var3);
         this.setVirtualColumnId(var2);
      }
   }

   void bindResultColumnByName(TableDescriptor var1, int var2) throws StandardException {
      ColumnDescriptor var3 = var1.getColumnDescriptor(this._derivedColumnName);
      if (var3 == null) {
         String var4 = "";
         String var5 = var1.getSchemaName();
         if (var5 != null) {
            var4 = var4 + var5 + ".";
         }

         var4 = var4 + var1.getName();
         throw StandardException.newException("42X14", new Object[]{this._derivedColumnName, var4});
      } else {
         this.setColumnDescriptor(var1, var3);
         this.setVirtualColumnId(var2);
         if (this.isPrivilegeCollectionRequired()) {
            this.getCompilerContext().addRequiredColumnPriv(var3);
         }

      }
   }

   void typeUntypedNullExpression(ResultColumn var1) throws StandardException {
      TypeId var2 = var1.getTypeId();
      if (var2 == null) {
         throw StandardException.newException("42X07", new Object[0]);
      } else {
         if (this._expression instanceof UntypedNullConstantNode) {
            this.setExpression(this.getNullNode(var1.getTypeServices()));
         } else if (this._expression instanceof ColumnReference && this._expression.getTypeServices() == null) {
            this._expression.setType(var1.getType());
         }

      }
   }

   void setColumnDescriptor(TableDescriptor var1, ColumnDescriptor var2) throws StandardException {
      if (var2 != null) {
         this.setType(var2.getType());
      }

      this._columnDescriptor = var2;
      if (this._reference != null && this._reference.getTableName() != null && this._reference.getMergeTableID() == 0 && var1 != null && !var1.getName().equals(this._reference.getTableName())) {
         String var3 = var1.getName();
         String var4 = this._reference.getTableName();
         throw StandardException.newException("42X55", new Object[]{var3, var4});
      }
   }

   void bindResultColumnToExpression() throws StandardException {
      this.setType(this._expression.getTypeServices());
      if (this._expression instanceof ColumnReference) {
         ColumnReference var1 = (ColumnReference)this._expression;
         this._unqualifiedTableName = var1.getTableName();
         this._unqualifiedSourceTableName = var1.getSourceTableName();
         this._sourceSchemaName = var1.getSourceSchemaName();
      }

   }

   void setSourceTableName(String var1) {
      this._unqualifiedSourceTableName = var1;
   }

   void setSourceSchemaName(String var1) {
      this._sourceSchemaName = var1;
   }

   ResultColumn preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      if (this._expression == null) {
         return this;
      } else {
         this.setExpression(this._expression.preprocess(var1, var2, var3, var4));
         return this;
      }
   }

   void checkStorableExpression(ResultColumn var1) throws StandardException {
      this.checkStorableExpression((ValueNode)var1);
   }

   private void checkStorableExpression(ValueNode var1) throws StandardException {
      TypeId var2 = var1.getTypeId();
      if (!this.getTypeCompiler().storable(var2, this.getClassFactory())) {
         throw StandardException.newException("42821", new Object[]{this.getTypeId().getSQLTypeName(), var2.getSQLTypeName()});
      }
   }

   void checkStorableExpression() throws StandardException {
      this.checkStorableExpression(this.getExpression());
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this._expression.generateExpression(var1, var2);
   }

   boolean columnTypeAndLengthMatch() throws StandardException {
      if (this.getExpression().requiresTypeFromContext()) {
         return false;
      } else if (this.getTypeId().isXMLTypeId()) {
         return false;
      } else {
         DataTypeDescriptor var1 = this.getExpression().getTypeServices();
         if (!this.getTypeServices().isExactTypeAndLengthMatch(var1)) {
            return false;
         } else {
            return this.getTypeServices().isNullable() || !var1.isNullable();
         }
      }
   }

   boolean columnTypeAndLengthMatch(ResultColumn var1) throws StandardException {
      ValueNode var2 = var1.getExpression();
      DataTypeDescriptor var3 = this.getTypeServices();
      DataTypeDescriptor var4 = var1.getTypeServices();
      if ((var2 == null || !var2.requiresTypeFromContext()) && !this._expression.requiresTypeFromContext()) {
         if (var3.getTypeId().isXMLTypeId()) {
            return false;
         } else {
            if (!var3.getTypeId().equals(var4.getTypeId())) {
               if (var2 instanceof ConstantNode) {
                  ConstantNode var5 = (ConstantNode)var1.getExpression();
                  DataValueDescriptor var6 = var5.getValue();
                  DataValueDescriptor var7 = this.convertConstant(var3.getTypeId(), var3.getMaximumWidth(), var6);
                  if (var6 != var7 && var6 instanceof StringDataValue == (var7 instanceof StringDataValue)) {
                     var5.setValue(var7);
                     var5.setType(this.getTypeServices());
                     var1.bindResultColumnToExpression();
                     var4 = var1.getType();
                  }

                  if (var7 instanceof StringDataValue) {
                     var5.setCollationInfo(var3);
                     DataValueFactory var8 = this.getDataValueFactory();
                     StringDataValue var9 = ((StringDataValue)var7).getValue(var8.getCharacterCollator(var5.getTypeServices().getCollationType()));
                     var5.setValue(var9);
                  }
               }

               if (!var3.getTypeId().equals(var4.getTypeId())) {
                  return false;
               }
            }

            if (var3.getPrecision() != var4.getPrecision()) {
               return false;
            } else if (var3.getScale() != var4.getScale()) {
               return false;
            } else if (var3.getMaximumWidth() != var4.getMaximumWidth()) {
               return false;
            } else {
               return var3.isNullable() || !var4.isNullable() && !var1.isGeneratedForUnmatchedColumnInInsert();
            }
         }
      } else {
         return false;
      }
   }

   boolean isGenerated() {
      return this._isGenerated;
   }

   boolean isGeneratedForUnmatchedColumnInInsert() {
      return this._isGeneratedForUnmatchedColumnInInsert;
   }

   void markGenerated() {
      this._isGenerated = true;
      this._isReferenced = true;
   }

   void markGeneratedForUnmatchedColumnInInsert() {
      this._isGeneratedForUnmatchedColumnInInsert = true;
      this._isReferenced = true;
   }

   boolean isReferenced() {
      return this._isReferenced;
   }

   void setReferenced() {
      this._isReferenced = true;
   }

   void pullVirtualIsReferenced() {
      if (!this.isReferenced()) {
         ResultColumn var3;
         for(ValueNode var1 = this._expression; var1 != null && var1 instanceof VirtualColumnNode; var1 = var3.getExpression()) {
            VirtualColumnNode var2 = (VirtualColumnNode)var1;
            var3 = var2.getSourceColumn();
            if (var3.isReferenced()) {
               this.setReferenced();
               return;
            }
         }

      }
   }

   void setUnreferenced() {
      this._isReferenced = false;
   }

   void markAllRCsInChainReferenced() {
      this.setReferenced();

      ResultColumn var3;
      for(ValueNode var1 = this._expression; var1 instanceof VirtualColumnNode; var1 = var3.getExpression()) {
         VirtualColumnNode var2 = (VirtualColumnNode)var1;
         var3 = var2.getSourceColumn();
         var3.setReferenced();
      }

   }

   boolean isRedundant() {
      return this._isRedundant;
   }

   void setRedundant() {
      this._isRedundant = true;
   }

   void markAsGroupingColumn() {
      this._isGroupingColumn = true;
   }

   void rejectParameter() throws StandardException {
      if (this._expression != null && this._expression.isParameterNode()) {
         throw StandardException.newException("42X34", new Object[0]);
      }
   }

   public int compareTo(ResultColumn var1) {
      return this.getColumnPosition() - var1.getColumnPosition();
   }

   void markUpdated() {
      this._updated = true;
   }

   void markUpdatableByCursor() {
      this._updatableByCursor = true;
   }

   boolean updated() {
      return this._updated;
   }

   public boolean updatableByCursor() {
      return this._updatableByCursor;
   }

   ResultColumn cloneMe() throws StandardException {
      ValueNode var2;
      if (this._expression instanceof ColumnReference) {
         var2 = ((ColumnReference)this._expression).getClone();
      } else {
         var2 = this._expression;
      }

      ResultColumn var1;
      if (this._columnDescriptor != null) {
         var1 = new ResultColumn(this._columnDescriptor, this._expression, this.getContextManager());
         var1.setExpression(var2);
      } else {
         var1 = new ResultColumn(this.getName(), var2, this.getContextManager());
      }

      var1.setVirtualColumnId(this.getVirtualColumnId());
      var1.setName(this.getName());
      var1.setType(this.getTypeServices());
      var1.setNameGenerated(this.isNameGenerated());
      var1.setSourceTableName(this.getSourceTableName());
      var1.setSourceSchemaName(this.getSourceSchemaName());
      if (this.isGeneratedForUnmatchedColumnInInsert()) {
         var1.markGeneratedForUnmatchedColumnInInsert();
      }

      if (this.isReferenced()) {
         var1.setReferenced();
      }

      if (this.updated()) {
         var1.markUpdated();
      }

      if (this.updatableByCursor()) {
         var1.markUpdatableByCursor();
      }

      if (this.isAutoincrementGenerated()) {
         var1.setAutoincrementGenerated();
      }

      if (this.isAutoincrement()) {
         var1.setAutoincrement();
      }

      if (this.isGroupingColumn()) {
         var1.markAsGroupingColumn();
      }

      if (this.isRightOuterJoinUsingClause()) {
         var1.setRightOuterJoinUsingClause(true);
      }

      if (this.getJoinResultSet() != null) {
         var1.setJoinResultset(this.getJoinResultSet());
      }

      if (this.isGenerated()) {
         var1.markGenerated();
      }

      var1.copyTagsFrom(this);
      return var1;
   }

   int getMaximumColumnSize() {
      return this.getTypeServices().getTypeId().getApproximateLengthInBytes(this.getTypeServices());
   }

   public DataTypeDescriptor getTypeServices() {
      DataTypeDescriptor var1 = super.getTypeServices();
      if (var1 != null) {
         return var1;
      } else {
         return this.getExpression() != null ? this.getExpression().getTypeServices() : null;
      }
   }

   protected int getOrderableVariantType() throws StandardException {
      int var1;
      if (this.isAutoincrementGenerated()) {
         var1 = 0;
      } else if (this._expression != null) {
         var1 = this._expression.getOrderableVariantType();
      } else {
         var1 = 3;
      }

      switch (var1) {
         case 0:
            return 0;
         case 1:
         case 2:
            return 1;
         default:
            return 3;
      }
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this._expression != null) {
         this.setExpression((ValueNode)this._expression.accept(var1));
      }

   }

   void verifyOrderable() throws StandardException {
      if (!this.getTypeId().orderable(this.getClassFactory())) {
         throw StandardException.newException("X0X67.S", new Object[]{this.getTypeId().getSQLTypeName()});
      }
   }

   ColumnDescriptor getTableColumnDescriptor() {
      return this._columnDescriptor;
   }

   boolean isAutoincrementGenerated() {
      return this._autoincrementGenerated;
   }

   void setAutoincrementGenerated() {
      this._autoincrementGenerated = true;
   }

   void resetAutoincrementGenerated() {
      this._autoincrementGenerated = false;
   }

   public boolean isAutoincrement() {
      return this._autoincrement;
   }

   void setAutoincrement() {
      this._autoincrement = true;
   }

   public boolean isGroupingColumn() {
      return this._isGroupingColumn;
   }

   private DataValueDescriptor convertConstant(TypeId var1, int var2, DataValueDescriptor var3) throws StandardException {
      int var4 = var1.getTypeFormatId();
      DataValueFactory var5 = this.getDataValueFactory();
      switch (var4) {
         case 5:
         default:
            return var3;
         case 13:
            String var6 = var3.getString();
            int var7 = var6.length();
            if (var7 <= var2 && var4 == 13) {
               return var5.getVarcharDataValue(var6);
            } else {
               for(int var8 = var2; var8 < var7; ++var8) {
                  if (var6.charAt(var8) != ' ') {
                     String var9 = null;
                     if (var4 == 13) {
                        var9 = "VARCHAR";
                     }

                     throw StandardException.newException("22001", new Object[]{var9, StringUtil.formatForPrint(var6), String.valueOf(var2)});
                  }
               }

               if (var4 == 13) {
                  return var5.getVarcharDataValue(var6.substring(0, var2));
               }
            }
         case 230:
            return var5.getLongvarcharDataValue(var3.getString());
      }
   }

   public TableName getTableNameObject() {
      return null;
   }

   public ColumnReference getReference() {
      return this._reference;
   }

   ColumnDescriptor getColumnDescriptor() {
      return this._columnDescriptor;
   }

   BaseColumnNode getBaseColumnNode() {
      Object var1 = this._expression;

      while(true) {
         while(!(var1 instanceof ResultColumn)) {
            if (var1 instanceof ColumnReference) {
               var1 = ((ColumnReference)var1).getSource();
            } else {
               if (!(var1 instanceof VirtualColumnNode)) {
                  if (var1 instanceof BaseColumnNode) {
                     return (BaseColumnNode)var1;
                  }

                  return null;
               }

               var1 = ((VirtualColumnNode)var1).getSourceColumn();
            }
         }

         var1 = ((ResultColumn)var1)._expression;
      }
   }

   int getTableNumber() throws StandardException {
      if (this._expression instanceof ColumnReference) {
         return ((ColumnReference)this._expression).getTableNumber();
      } else if (this._expression instanceof VirtualColumnNode) {
         VirtualColumnNode var1 = (VirtualColumnNode)this._expression;
         return var1.getSourceResultSet() instanceof FromBaseTable ? ((FromBaseTable)var1.getSourceResultSet()).getTableNumber() : var1.getSourceColumn().getTableNumber();
      } else {
         return -1;
      }
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (this.isSameNodeKind(var1)) {
         ResultColumn var2 = (ResultColumn)var1;
         if (this._expression != null) {
            return this._expression.isEquivalent(var2._expression);
         }
      }

      return false;
   }

   public boolean hasGenerationClause() {
      return this._columnDescriptor != null && this._columnDescriptor.hasGenerationClause();
   }
}
