package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public class ColumnReference extends ValueNode {
   public static final int MERGE_UNKNOWN = 0;
   public static final int MERGE_SOURCE = 1;
   public static final int MERGE_TARGET = 2;
   private String _columnName;
   private TableName _qualifiedTableName;
   private int tableNumber;
   private int columnNumber;
   private ResultColumn source;
   private ResultColumn _origSource;
   private String origName;
   private int _origTableNumber = -1;
   private int _origColumnNumber = -1;
   private int tableNumberBeforeFlattening = -1;
   private int columnNumberBeforeFlattening = -1;
   private boolean replacesAggregate;
   private boolean replacesWindowFunctionCall;
   private int nestingLevel = -1;
   private int sourceLevel = -1;
   private boolean scoped;
   private ArrayList remaps;
   private int _mergeTableID = 0;

   ColumnReference(String var1, TableName var2, int var3, int var4, ContextManager var5) {
      super(var5);
      this._columnName = var1;
      this._qualifiedTableName = var2;
      this.setBeginOffset(var3);
      this.setEndOffset(var4);
      this.tableNumber = -1;
      this.remaps = null;
   }

   ColumnReference(String var1, TableName var2, ContextManager var3) {
      super(var3);
      this._columnName = var1;
      this._qualifiedTableName = var2;
      this.tableNumber = -1;
      this.remaps = null;
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   boolean getCorrelated() {
      return this.sourceLevel != this.nestingLevel;
   }

   void setNestingLevel(int var1) {
      this.nestingLevel = var1;
   }

   private int getNestingLevel() {
      return this.nestingLevel;
   }

   void setSourceLevel(int var1) {
      this.sourceLevel = var1;
   }

   int getSourceLevel() {
      return this.sourceLevel;
   }

   void markGeneratedToReplaceAggregate() {
      this.replacesAggregate = true;
   }

   void markGeneratedToReplaceWindowFunctionCall() {
      this.replacesWindowFunctionCall = true;
   }

   boolean getGeneratedToReplaceAggregate() {
      return this.replacesAggregate;
   }

   boolean getGeneratedToReplaceWindowFunctionCall() {
      return this.replacesWindowFunctionCall;
   }

   ValueNode getClone() throws StandardException {
      ColumnReference var1 = new ColumnReference(this._columnName, this._qualifiedTableName, this.getContextManager());
      var1.copyFields(this);
      return var1;
   }

   void copyFields(ColumnReference var1) throws StandardException {
      super.copyFields(var1);
      this.setQualifiedTableName(var1.getQualifiedTableName());
      this.tableNumber = var1.getTableNumber();
      this.columnNumber = var1.getColumnNumber();
      this.source = var1.getSource();
      this.nestingLevel = var1.getNestingLevel();
      this.sourceLevel = var1.getSourceLevel();
      this.replacesAggregate = var1.getGeneratedToReplaceAggregate();
      this.replacesWindowFunctionCall = var1.getGeneratedToReplaceWindowFunctionCall();
      this.scoped = var1.isScoped();
      this.copyTagsFrom(var1);
      if (var1._mergeTableID != 0) {
         this.setMergeTableID(var1.getMergeTableID());
      }

   }

   ColumnReference bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      if (var1.size() == 0) {
         throw StandardException.newException("42X15", new Object[]{this._columnName});
      } else {
         ResultColumn var4 = var1.bindColumnReference(this);
         if (var4 == null) {
            throw StandardException.newException("42X04", new Object[]{this.getSQLColumnName()});
         } else {
            return this;
         }
      }
   }

   String getSQLColumnName() {
      if (this._qualifiedTableName == null) {
         return this._columnName;
      } else {
         String var10000 = this._qualifiedTableName.toString();
         return var10000 + "." + this._columnName;
      }
   }

   public String getColumnName() {
      return this._columnName;
   }

   int getTableNumber() {
      return this.tableNumber;
   }

   void setTableNumber(int var1) {
      this.tableNumber = var1;
   }

   String getTableName() {
      return this._qualifiedTableName != null ? this._qualifiedTableName.getTableName() : null;
   }

   String getSourceTableName() {
      return this.source != null ? this.source.getTableName() : null;
   }

   String getSourceSchemaName() throws StandardException {
      return this.source != null ? this.source.getSchemaName() : null;
   }

   public boolean updatableByCursor() {
      return this.source != null ? this.source.updatableByCursor() : false;
   }

   public TableName getQualifiedTableName() {
      return this._qualifiedTableName;
   }

   void setQualifiedTableName(TableName var1) {
      this._qualifiedTableName = var1;
   }

   int getColumnNumber() {
      return this.columnNumber;
   }

   void setColumnNumber(int var1) {
      this.columnNumber = var1;
   }

   ResultColumn getSource() {
      return this.source;
   }

   void setSource(ResultColumn var1) {
      this.source = var1;
   }

   ValueNode putAndsOnTop() throws StandardException {
      BooleanConstantNode var2 = new BooleanConstantNode(true, this.getContextManager());
      BinaryRelationalOperatorNode var1 = new BinaryRelationalOperatorNode(0, this, var2, false, this.getContextManager());
      ((BinaryComparisonOperatorNode)var1).bindComparisonOperator();
      AndNode var3 = new AndNode(var1, var2, this.getContextManager());
      var3.postBindFixup();
      return var3;
   }

   boolean categorize(JBitSet var1, boolean var2) {
      var1.set(this.tableNumber);
      return !this.replacesAggregate && !this.replacesWindowFunctionCall && (this.source.getExpression() instanceof ColumnReference || this.source.getExpression() instanceof VirtualColumnNode || this.source.getExpression() instanceof ConstantNode);
   }

   void remapColumnReferences() {
      ValueNode var1 = this.source.getExpression();
      if (var1 instanceof VirtualColumnNode || var1 instanceof ColumnReference) {
         if (this.scoped && this._origSource != null) {
            if (this.remaps == null) {
               this.remaps = new ArrayList();
            }

            this.remaps.add(new RemapInfo(this.columnNumber, this.tableNumber, this._columnName, this.source));
         } else {
            this._origSource = this.source;
            this.origName = this._columnName;
            this._origColumnNumber = this.columnNumber;
            this._origTableNumber = this.tableNumber;
         }

         this.source = this.getSourceResultColumn();
         this._columnName = this.source.getName();
         this.columnNumber = this.source.getExpression() instanceof VirtualColumnNode ? this.source.getVirtualColumnId() : this.source.getColumnPosition();
         if (this.source.getExpression() instanceof ColumnReference) {
            ColumnReference var2 = (ColumnReference)this.source.getExpression();
            this.tableNumber = var2.getTableNumber();
         }

      }
   }

   void unRemapColumnReferences() {
      if (this._origSource != null) {
         if (this.remaps != null && !this.remaps.isEmpty()) {
            RemapInfo var1 = (RemapInfo)this.remaps.remove(this.remaps.size() - 1);
            this.source = var1.getSource();
            this._columnName = var1.getColumnName();
            this.tableNumber = var1.getTableNumber();
            this.columnNumber = var1.getColumnNumber();
            if (this.remaps.isEmpty()) {
               this.remaps = null;
            }
         } else {
            this.source = this._origSource;
            this._origSource = null;
            this._columnName = this.origName;
            this.origName = null;
            this.tableNumber = this._origTableNumber;
            this.columnNumber = this._origColumnNumber;
         }

      }
   }

   protected boolean hasBeenRemapped() {
      return this._origSource != null;
   }

   ResultColumn getSourceResultColumn() {
      return this.source.getExpression().getSourceResultColumn();
   }

   ValueNode remapColumnReferencesToExpressions() throws StandardException {
      ResultColumn var2 = this.source;
      if (!this.source.isRedundant()) {
         return this;
      } else {
         ResultColumn var3;
         for(ResultColumn var1 = this.source; var1 != null && var1.isRedundant(); var1 = var3) {
            var3 = var1.getExpression().getSourceResultColumn();
            if (var3 != null && var3.isRedundant()) {
               var2 = var3;
            }
         }

         if (var2.getExpression() instanceof VirtualColumnNode) {
            VirtualColumnNode var8 = (VirtualColumnNode)var2.getExpression();
            ResultSetNode var4 = var8.getSourceResultSet();
            if (var4 instanceof FromTable) {
               FromTable var5 = (FromTable)var4;
               ResultColumnList var6 = var5.getResultColumns();
               if (this.tableNumberBeforeFlattening == -1) {
                  this.tableNumberBeforeFlattening = this.tableNumber;
                  this.columnNumberBeforeFlattening = this.columnNumber;
               }

               ResultColumn var7 = var6.getResultColumn(this.tableNumberBeforeFlattening, this.columnNumberBeforeFlattening, this._columnName);
               if (var7 == null) {
                  var7 = var6.getResultColumn(this._columnName);
               }

               this.tableNumber = var5.getTableNumber();
               this.columnNumber = var7.getExpression() instanceof VirtualColumnNode ? var7.getVirtualColumnId() : var7.getColumnPosition();
            }

            this.source = var2.getExpression().getSourceResultColumn();
            return this;
         } else {
            return var2.getExpression().getClone();
         }
      }
   }

   void getTablesReferenced(JBitSet var1) {
      if (var1.size() < this.tableNumber) {
         var1.grow(this.tableNumber);
      }

      if (this.tableNumber != -1) {
         var1.set(this.tableNumber);
      }

   }

   boolean isCloneable() {
      return true;
   }

   boolean constantExpression(PredicateList var1) {
      return var1.constantColumn(this);
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      int var3 = this.source.getResultSetNumber();
      if (this.source.isRedundant()) {
         this.source.generateExpression(var1, var2);
      } else {
         var1.pushColumnReference(var2, var3, this.source.getVirtualColumnId());
         var2.cast(this.getTypeCompiler().interfaceName());
      }
   }

   String getSchemaName() {
      return this._qualifiedTableName != null ? this._qualifiedTableName.getSchemaName() : null;
   }

   protected int getOrderableVariantType() {
      return 1;
   }

   boolean pointsToColumnReference() {
      return this.source.getExpression() instanceof ColumnReference;
   }

   DataTypeDescriptor getTypeServices() {
      return this.source == null ? super.getTypeServices() : this.source.getTypeServices();
   }

   protected ResultSetNode getSourceResultSet(int[] var1) throws StandardException {
      if (this.source == null) {
         return null;
      } else {
         ResultColumn var2 = this.getSource();
         ValueNode var3 = var2.getExpression();

         for(var1[0] = this.getColumnNumber(); var3 != null && (var2.isRedundant() || var3 instanceof ColumnReference); var3 = var2.getExpression()) {
            if (var3 instanceof ColumnReference) {
               var1[0] = ((ColumnReference)var3).getColumnNumber();
               var2 = ((ColumnReference)var3).getSource();
            }

            while(var2.isRedundant()) {
               var3 = var2.getExpression();
               if (var3 instanceof VirtualColumnNode) {
                  var2 = var3.getSourceResultColumn();
               } else {
                  if (!(var3 instanceof ColumnReference)) {
                     break;
                  }

                  var1[0] = ((ColumnReference)var3).getColumnNumber();
                  var2 = ((ColumnReference)var3).getSource();
               }
            }
         }

         if (var3 != null && var3 instanceof VirtualColumnNode) {
            return ((VirtualColumnNode)var3).getSourceResultSet();
         } else {
            var1[0] = -1;
            return null;
         }
      }
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (!this.isSameNodeKind(var1)) {
         return false;
      } else {
         ColumnReference var2 = (ColumnReference)var1;
         return this.tableNumber == var2.tableNumber && this._columnName.equals(var2.getColumnName());
      }
   }

   protected void markAsScoped() {
      this.scoped = true;
   }

   protected boolean isScoped() {
      return this.scoped;
   }

   void setMergeTableID(int var1) {
      if (this._mergeTableID != 0 && this._mergeTableID != var1) {
      }

      this._mergeTableID = var1;
   }

   private String prettyPrintMergeTableID(int var1) {
      switch (var1) {
         case 1 -> {
            return "SOURCE";
         }
         case 2 -> {
            return "TARGET";
         }
         default -> {
            return "UNKNOWN";
         }
      }
   }

   int getMergeTableID() {
      return this._mergeTableID;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this._qualifiedTableName != null) {
         this.setQualifiedTableName((TableName)this._qualifiedTableName.accept(var1));
      }

   }

   private static class RemapInfo {
      int colNum;
      int tableNum;
      String colName;
      ResultColumn source;

      RemapInfo(int var1, int var2, String var3, ResultColumn var4) {
         this.colNum = var1;
         this.tableNum = var2;
         this.colName = var3;
         this.source = var4;
      }

      int getColumnNumber() {
         return this.colNum;
      }

      int getTableNumber() {
         return this.tableNum;
      }

      String getColumnName() {
         return this.colName;
      }

      ResultColumn getSource() {
         return this.source;
      }

      void setColNumber(int var1) {
         this.colNum = var1;
      }

      void setTableNumber(int var1) {
         this.tableNum = var1;
      }

      void setColName(String var1) {
         this.colName = var1;
      }

      void setSource(ResultColumn var1) {
         this.source = var1;
      }
   }
}
