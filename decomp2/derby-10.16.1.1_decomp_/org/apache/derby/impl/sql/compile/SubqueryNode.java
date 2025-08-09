package org.apache.derby.impl.sql.compile;

import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class SubqueryNode extends ValueNode {
   ResultSetNode resultSet;
   int subqueryType;
   boolean underTopAndNode;
   boolean preprocessed;
   boolean distinctExpression;
   boolean whereSubquery;
   ValueNode leftOperand;
   boolean pushedNewPredicate;
   boolean havingSubquery = false;
   BinaryComparisonOperatorNode parentComparisonOperator;
   private BooleanConstantNode trueNode;
   private int subqueryNumber = -1;
   private int pointOfAttachment = -1;
   private boolean foundCorrelation;
   private boolean doneCorrelationCheck;
   private boolean foundVariant;
   private boolean doneInvariantCheck;
   private OrderByList orderByList;
   private ValueNode offset;
   private ValueNode fetchFirst;
   private boolean hasJDBClimitClause;
   static final int NOTIMPLEMENTED_SUBQUERY = -1;
   static final int FROM_SUBQUERY = 0;
   static final int IN_SUBQUERY = 1;
   static final int NOT_IN_SUBQUERY = 2;
   static final int EQ_ANY_SUBQUERY = 3;
   static final int EQ_ALL_SUBQUERY = 4;
   static final int NE_ANY_SUBQUERY = 5;
   static final int NE_ALL_SUBQUERY = 6;
   static final int GT_ANY_SUBQUERY = 7;
   static final int GT_ALL_SUBQUERY = 8;
   static final int GE_ANY_SUBQUERY = 9;
   static final int GE_ALL_SUBQUERY = 10;
   static final int LT_ANY_SUBQUERY = 11;
   static final int LT_ALL_SUBQUERY = 12;
   static final int LE_ANY_SUBQUERY = 13;
   static final int LE_ALL_SUBQUERY = 14;
   static final int EXISTS_SUBQUERY = 15;
   static final int NOT_EXISTS_SUBQUERY = 16;
   static final int EXPRESSION_SUBQUERY = 17;

   SubqueryNode(ResultSetNode var1, int var2, ValueNode var3, OrderByList var4, ValueNode var5, ValueNode var6, boolean var7, ContextManager var8) {
      super(var8);
      this.resultSet = var1;
      this.subqueryType = var2;
      this.orderByList = var4;
      this.offset = var5;
      this.fetchFirst = var6;
      this.hasJDBClimitClause = var7;
      this.underTopAndNode = false;
      this.leftOperand = var3;
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   ResultSetNode getResultSet() {
      return this.resultSet;
   }

   int getSubqueryType() {
      return this.subqueryType;
   }

   void setSubqueryType(int var1) {
      this.subqueryType = var1;
   }

   void setPointOfAttachment(int var1) throws StandardException {
      if (!this.isMaterializable()) {
         this.pointOfAttachment = var1;
      }

   }

   boolean getUnderTopAndNode() {
      return this.underTopAndNode;
   }

   int getPointOfAttachment() {
      return this.pointOfAttachment;
   }

   boolean getPreprocessed() {
      return this.preprocessed;
   }

   void setParentComparisonOperator(BinaryComparisonOperatorNode var1) {
      this.parentComparisonOperator = var1;
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.resultSet.referencesSessionSchema();
   }

   ValueNode remapColumnReferencesToExpressions() throws StandardException {
      if (this.resultSet instanceof SelectNode) {
         ResultColumnList var1 = this.resultSet.getResultColumns();
         SelectNode var2 = (SelectNode)this.resultSet;
         PredicateList var3 = var2.getWherePredicates();
         var1.remapColumnReferencesToExpressions();
         var3.remapColumnReferencesToExpressions();
      }

      return this;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.checkReliability(32, "42Z91");
      ResultColumnList var4 = this.resultSet.getResultColumns();
      if (this.subqueryType != 15 && var4.visibleSize() != 1) {
         throw StandardException.newException("42X39", new Object[0]);
      } else {
         this.resultSet.verifySelectStarSubquery(var1, this.subqueryType);
         if (this.subqueryType == 15) {
            this.resultSet = this.resultSet.setResultToBooleanTrueNode(true);
         }

         CompilerContext var5 = this.getCompilerContext();
         var5.pushCurrentPrivType(0);
         this.resultSet = this.resultSet.bindNonVTITables(this.getDataDictionary(), var1);
         this.resultSet = this.resultSet.bindVTITables(var1);
         if (this.subqueryNumber == -1) {
            this.subqueryNumber = var5.getNextSubqueryNumber();
         }

         this.resultSet.rejectParameters();
         if (this.subqueryType == 15) {
            this.resultSet.bindTargetExpressions(var1);
            this.resultSet.bindUntypedNullsToResultColumns((ResultColumnList)null);
            this.resultSet = this.resultSet.setResultToBooleanTrueNode(false);
         }

         if (this.leftOperand != null) {
            this.leftOperand = this.leftOperand.bindExpression(var1, var2, var3);
         }

         if (this.orderByList != null) {
            this.orderByList.pullUpOrderByColumns(this.resultSet);
         }

         this.resultSet.bindExpressions(var1);
         this.resultSet.bindResultColumns(var1);
         if (this.orderByList != null) {
            this.orderByList.bindOrderByColumns(this.resultSet);
         }

         bindOffsetFetch(this.offset, this.fetchFirst);
         this.resultSet.bindUntypedNullsToResultColumns((ResultColumnList)null);
         var4 = this.resultSet.getResultColumns();
         if (this.leftOperand != null && this.leftOperand.requiresTypeFromContext()) {
            this.leftOperand.setType(((ResultColumn)var4.elementAt(0)).getTypeServices());
         }

         this.setDataTypeServices(var4);
         var2.addSubqueryNode(this);
         var5.popCurrentPrivType();
         return this;
      }
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      if (this.preprocessed) {
         return this;
      } else {
         this.preprocessed = true;
         Object var6 = this;
         boolean var7;
         if (this.orderByList != null) {
            var7 = true;
            if (this.orderByList.size() > 1) {
               this.orderByList.removeDupColumns();
            }

            this.resultSet.pushOrderByList(this.orderByList);
            this.orderByList = null;
         } else {
            var7 = false;
         }

         this.resultSet = this.resultSet.preprocess(var1, (GroupByList)null, (FromList)null);
         if (this.leftOperand != null) {
            this.leftOperand = this.leftOperand.preprocess(var1, var2, var3, var4);
         }

         if (this.resultSet instanceof SelectNode && ((SelectNode)this.resultSet).hasDistinct()) {
            ((SelectNode)this.resultSet).clearDistinct();
            if (this.subqueryType == 17) {
               this.distinctExpression = true;
            }
         }

         if ((this.isIN() || this.isANY()) && this.resultSet.returnsAtMostOneRow() && !this.hasCorrelatedCRs()) {
            this.changeToCorrespondingExpressionType();
         }

         boolean var5 = this.resultSet instanceof RowResultSetNode && this.underTopAndNode && !this.havingSubquery && !var7 && this.offset == null && this.fetchFirst == null && !this.isWhereExistsAnyInWithWhereSubquery() && this.parentComparisonOperator != null;
         if (var5) {
            this.leftOperand = this.parentComparisonOperator.getLeftOperand();
            RowResultSetNode var14 = (RowResultSetNode)this.resultSet;
            FromList var15 = new FromList(this.getContextManager());
            var3.removeElement(this);
            if (var14.subquerys.size() != 0) {
               var15.addElement(var14);
               var2.destructiveAppend(var15);
            }

            var3.destructiveAppend(var14.subquerys);
            return this.getNewJoinCondition(this.leftOperand, this.getRightOperand());
         } else {
            boolean var8 = this.isNOT_EXISTS() || this.canAllBeFlattened();
            var5 = this.resultSet instanceof SelectNode && !((SelectNode)this.resultSet).hasWindows() && !var7 && this.offset == null && this.fetchFirst == null && this.underTopAndNode && !this.havingSubquery && !this.isWhereExistsAnyInWithWhereSubquery() && (this.isIN() || this.isANY() || this.isEXISTS() || var8 || this.parentComparisonOperator != null);
            if (var5) {
               SelectNode var9 = (SelectNode)this.resultSet;
               if (!var9.hasAggregatesInSelectList() && var9.havingClause == null) {
                  ValueNode var10 = this.leftOperand;
                  boolean var11 = this.subqueryType == 1 || this.subqueryType == 3;
                  var11 = var11 && (this.leftOperand instanceof ConstantNode || this.leftOperand instanceof ColumnReference || this.leftOperand.requiresTypeFromContext());
                  if (this.parentComparisonOperator != null) {
                     this.leftOperand = this.parentComparisonOperator.getLeftOperand();
                  }

                  if (!var8 && var9.uniqueSubquery(var11)) {
                     return this.flattenToNormalJoin(var1, var2, var3, var4);
                  }

                  if ((this.isIN() || this.isANY() || this.isEXISTS() || var8) && (this.leftOperand == null || this.leftOperand.categorize(new JBitSet(var1), false)) && var9.getWherePredicates().allPushable()) {
                     FromBaseTable var12 = this.singleFromBaseTable(var9.getFromList());
                     if (var12 != null && (!var8 || var9.getWherePredicates().allReference(var12) && this.rightOperandFlattenableToNotExists(var1, var12))) {
                        return this.flattenToExistsJoin(var1, var2, var3, var4, var8);
                     }
                  }

                  this.leftOperand = var10;
               }
            }

            this.resultSet.pushQueryExpressionSuffix();
            this.resultSet.pushOffsetFetchFirst(this.offset, this.fetchFirst, this.hasJDBClimitClause);
            if (this.leftOperand != null) {
               var6 = this.pushNewPredicate(var1);
               this.pushedNewPredicate = true;
            } else if (this.isEXISTS() || this.isNOT_EXISTS()) {
               var6 = this.genIsNullTree(this.isEXISTS());
               this.subqueryType = 15;
            }

            this.isInvariant();
            this.hasCorrelatedCRs();
            if (this.parentComparisonOperator != null) {
               this.parentComparisonOperator.setRightOperand((ValueNode)var6);
               return this.parentComparisonOperator;
            } else {
               return (ValueNode)var6;
            }
         }
      }
   }

   private FromBaseTable singleFromBaseTable(FromList var1) {
      FromBaseTable var2 = null;
      if (var1.size() == 1) {
         FromTable var3 = (FromTable)var1.elementAt(0);
         if (var3 instanceof FromBaseTable) {
            var2 = (FromBaseTable)var3;
         } else if (var3 instanceof ProjectRestrictNode) {
            ResultSetNode var4 = ((ProjectRestrictNode)var3).getChildResult();
            if (var4 instanceof FromBaseTable) {
               var2 = (FromBaseTable)var4;
            }
         }
      }

      return var2;
   }

   private boolean rightOperandFlattenableToNotExists(int var1, FromBaseTable var2) throws StandardException {
      boolean var3 = true;
      if (this.leftOperand != null) {
         JBitSet var4 = new JBitSet(var1);
         this.getRightOperand().categorize(var4, false);
         var3 = var4.get(var2.getTableNumber());
      }

      return var3;
   }

   private boolean canAllBeFlattened() throws StandardException {
      boolean var1 = false;
      if (this.isNOT_IN() || this.isALL()) {
         var1 = !this.leftOperand.getTypeServices().isNullable() && !this.getRightOperand().getTypeServices().isNullable();
      }

      return var1;
   }

   private ValueNode flattenToNormalJoin(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      SelectNode var5 = (SelectNode)this.resultSet;
      FromList var6 = var5.getFromList();
      int[] var7 = var6.getTableNumbers();
      var3.removeElement(this);
      var5.decrementLevel(1);
      var2.destructiveAppend(var6);
      var4.destructiveAppend(var5.getWherePredicates());
      var3.destructiveAppend(var5.getWhereSubquerys());
      var3.destructiveAppend(var5.getSelectSubquerys());
      if (this.leftOperand == null) {
         return new BooleanConstantNode(true, this.getContextManager());
      } else {
         ValueNode var8 = this.getRightOperand();
         if (var8 instanceof ColumnReference) {
            ColumnReference var9 = (ColumnReference)var8;
            int var10 = var9.getTableNumber();

            for(int var11 = 0; var11 < var7.length; ++var11) {
               if (var10 == var7[var11]) {
                  var9.setSourceLevel(var9.getSourceLevel() - 1);
                  break;
               }
            }
         }

         return this.getNewJoinCondition(this.leftOperand, var8);
      }
   }

   private ValueNode flattenToExistsJoin(int var1, FromList var2, SubqueryList var3, PredicateList var4, boolean var5) throws StandardException {
      SelectNode var6 = (SelectNode)this.resultSet;
      var6.getFromList().genExistsBaseTables(this.resultSet.getReferencedTableMap(), var2, var5);
      return this.flattenToNormalJoin(var1, var2, var3, var4);
   }

   private ValueNode getRightOperand() {
      ResultColumn var1 = (ResultColumn)this.resultSet.getResultColumns().elementAt(0);
      return var1.getExpression();
   }

   private boolean isInvariant() throws StandardException {
      if (this.doneInvariantCheck) {
         return !this.foundVariant;
      } else {
         this.doneInvariantCheck = true;
         HasVariantValueNodeVisitor var1 = new HasVariantValueNodeVisitor();
         this.resultSet.accept(var1);
         this.foundVariant = var1.hasVariant();
         return !this.foundVariant;
      }
   }

   boolean hasCorrelatedCRs() throws StandardException {
      if (this.doneCorrelationCheck) {
         return this.foundCorrelation;
      } else {
         this.doneCorrelationCheck = true;
         ResultSetNode var1 = this.resultSet;
         ResultColumnList var2 = null;
         if (this.pushedNewPredicate) {
            var1 = ((ProjectRestrictNode)this.resultSet).getChildResult();
            var2 = var1.getResultColumns();
            if (var2.size() > 1) {
               ResultColumnList var3 = new ResultColumnList(this.getContextManager());
               var3.addResultColumn(var2.getResultColumn(1));
               var1.setResultColumns(var3);
            }
         }

         HasCorrelatedCRsVisitor var4 = new HasCorrelatedCRsVisitor();
         var1.accept(var4);
         this.foundCorrelation = var4.hasCorrelatedCRs();
         if (this.pushedNewPredicate && var2.size() > 1) {
            var1.setResultColumns(var2);
         }

         return this.foundCorrelation;
      }
   }

   private UnaryComparisonOperatorNode pushNewPredicate(int var1) throws StandardException {
      IsNullNode var7 = null;
      ContextManager var9 = this.getContextManager();
      this.resultSet = this.resultSet.ensurePredicateList(var1);
      ResultColumnList var6 = this.resultSet.getResultColumns();
      ResultColumnList var10 = var6.copyListAndObjects();
      var10.genVirtualColumnNodes(this.resultSet, var6);
      this.resultSet = new ProjectRestrictNode(this.resultSet, var10, (ValueNode)null, (PredicateList)null, (SubqueryList)null, (SubqueryList)null, (Properties)null, var9);
      ResultColumn var5 = (ResultColumn)var10.elementAt(0);
      ValueNode var8 = var5.getExpression();
      BinaryComparisonOperatorNode var11 = this.getNewJoinCondition(this.leftOperand, var8);
      Object var12 = var11;
      if (this.isNOT_IN() || this.isALL()) {
         boolean var13 = this.leftOperand.getTypeServices().isNullable();
         boolean var14 = var8.getTypeServices().isNullable();
         if (var13 || var14) {
            BooleanConstantNode var15 = new BooleanConstantNode(false, var9);
            OrNode var16 = new OrNode(var11, var15, var9);
            var16.postBindFixup();
            var12 = var16;
            if (var13) {
               IsNullNode var17 = new IsNullNode(this.leftOperand, false, var9);
               ((UnaryComparisonOperatorNode)var17).bindComparisonOperator();
               var16 = new OrNode(var17, var16, var9);
               var16.postBindFixup();
               var12 = var16;
            }

            if (var14) {
               IsNullNode var20 = new IsNullNode(var8, false, var9);
               ((UnaryComparisonOperatorNode)var20).bindComparisonOperator();
               var16 = new OrNode(var20, (ValueNode)var12, var9);
               var16.postBindFixup();
               var12 = var16;
            }
         }
      }

      AndNode var2 = new AndNode((ValueNode)var12, this.getTrueNode(), var9);
      JBitSet var3 = new JBitSet(var1);
      var2.postBindFixup();
      Predicate var4 = new Predicate(var2, var3, var9);
      var4.categorize();
      this.resultSet = this.resultSet.addNewPredicate(var4);
      this.leftOperand = null;
      var5.setType(this.getTypeServices());
      var5.setExpression(this.getTrueNode());
      switch (this.subqueryType) {
         case 1:
         case 3:
         case 5:
         case 7:
         case 9:
         case 11:
         case 13:
            var7 = new IsNullNode(this, true, var9);
            break;
         case 2:
         case 4:
         case 6:
         case 8:
         case 10:
         case 12:
         case 14:
            var7 = new IsNullNode(this, false, var9);
      }

      ((UnaryComparisonOperatorNode)var7).bindComparisonOperator();
      return var7;
   }

   private BinaryComparisonOperatorNode getNewJoinCondition(ValueNode var1, ValueNode var2) throws StandardException {
      int var3 = this.subqueryType;
      if (this.subqueryType == 17) {
         int var4 = -1;
         if (this.parentComparisonOperator.isRelationalOperator()) {
            RelationalOperator var5 = (RelationalOperator)this.parentComparisonOperator;
            var4 = var5.getOperator();
         }

         if (var4 == 1) {
            var3 = 3;
         } else if (var4 == 2) {
            var3 = 5;
         } else if (var4 == 6) {
            var3 = 13;
         } else if (var4 == 5) {
            var3 = 11;
         } else if (var4 == 4) {
            var3 = 9;
         } else if (var4 == 3) {
            var3 = 7;
         }
      }

      boolean var7 = false;
      byte var8 = -1;
      switch (var3) {
         case 1:
         case 2:
         case 3:
         case 6:
            var8 = 0;
            break;
         case 4:
         case 5:
            var8 = 5;
            break;
         case 7:
         case 14:
            var8 = 2;
            break;
         case 8:
         case 13:
            var8 = 3;
            break;
         case 9:
         case 12:
            var8 = 1;
            break;
         case 10:
         case 11:
            var8 = 4;
      }

      BinaryRelationalOperatorNode var6 = new BinaryRelationalOperatorNode(var8, var1, var2, false, this.getContextManager());
      ((BinaryComparisonOperatorNode)var6).bindComparisonOperator();
      return var6;
   }

   ValueNode eliminateNots(boolean var1) throws StandardException {
      Object var2 = this;
      if (var1) {
         switch (this.subqueryType) {
            case 1:
            case 3:
               this.subqueryType = 2;
            case 2:
            case 16:
            default:
               break;
            case 4:
               this.subqueryType = 5;
               break;
            case 5:
               this.subqueryType = 4;
               break;
            case 6:
               this.subqueryType = 3;
               break;
            case 7:
               this.subqueryType = 14;
               break;
            case 8:
               this.subqueryType = 13;
               break;
            case 9:
               this.subqueryType = 12;
               break;
            case 10:
               this.subqueryType = 11;
               break;
            case 11:
               this.subqueryType = 10;
               break;
            case 12:
               this.subqueryType = 9;
               break;
            case 13:
               this.subqueryType = 8;
               break;
            case 14:
               this.subqueryType = 7;
               break;
            case 15:
               this.subqueryType = 16;
               break;
            case 17:
               var2 = this.genEqualsFalseTree();
         }
      }

      return (ValueNode)var2;
   }

   ValueNode changeToCNF(boolean var1) throws StandardException {
      this.underTopAndNode = var1;
      return this;
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      return var2 ? false : this.isMaterializable();
   }

   public boolean isMaterializable() throws StandardException {
      boolean var1 = this.subqueryType == 17 && !this.hasCorrelatedCRs() && this.isInvariant();
      if (var1 && this.resultSet instanceof SelectNode) {
         SelectNode var2 = (SelectNode)this.resultSet;
         FromList var3 = var2.getFromList();
         var3.setLevel(0);
      }

      return var1;
   }

   void optimize(DataDictionary var1, double var2) throws StandardException {
      this.resultSet = this.resultSet.optimize(var1, (PredicateList)null, var2);
   }

   void modifyAccessPaths() throws StandardException {
      this.resultSet = this.resultSet.modifyAccessPaths();
   }

   protected int getOrderableVariantType() throws StandardException {
      if (this.isInvariant()) {
         return !this.hasCorrelatedCRs() && this.subqueryType == 17 ? 2 : 1;
      } else {
         return 0;
      }
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      CompilerContext var3 = this.getCompilerContext();
      ActivationClassBuilder var5 = (ActivationClassBuilder)var1;
      String var4;
      if (this.subqueryType == 17) {
         var4 = "getOnceResultSet";
      } else {
         var4 = "getAnyResultSet";
      }

      CostEstimate var6 = this.resultSet.getFinalCostEstimate();
      String var7 = this.getTypeCompiler().interfaceName();
      MethodBuilder var8 = var5.newGeneratedFun(var7, 4);
      LocalField var9 = var5.newFieldDeclaration(2, "org.apache.derby.iapi.sql.execute.NoPutResultSet");
      ResultSetNode var10 = null;
      if (!this.isMaterializable()) {
         MethodBuilder var11 = var5.getExecuteMethod();
         if (this.pushedNewPredicate && !this.hasCorrelatedCRs()) {
            var10 = ((ProjectRestrictNode)this.resultSet).getChildResult();
            LocalField var12 = var5.newFieldDeclaration(2, "org.apache.derby.iapi.sql.execute.NoPutResultSet");
            var8.getField(var12);
            var8.conditionalIfNull();
            MaterializeSubqueryNode var13 = new MaterializeSubqueryNode(var12, this.getContextManager());
            ((ResultSetNode)var13).setCostEstimate(this.resultSet.getFinalCostEstimate());
            ((ProjectRestrictNode)this.resultSet).setChildResult(var13);
            var10.generate(var5, var8);
            var8.startElseCode();
            var8.getField(var12);
            var8.completeConditional();
            var8.setField(var12);
            var11.pushNull("org.apache.derby.iapi.sql.execute.NoPutResultSet");
            var11.setField(var12);
         }

         var11.pushNull("org.apache.derby.iapi.sql.execute.NoPutResultSet");
         var11.setField(var9);
         var8.getField(var9);
         var8.conditionalIfNull();
      }

      var5.pushGetResultSetFactoryExpression(var8);
      this.resultSet.generate(var5, var8);
      int var15 = var3.getNextResultSetNumber();
      this.resultSet.getResultColumns().setResultSetNumber(var15);
      this.resultSet.getResultColumns().generateNulls(var5, var8);
      byte var14;
      if (this.subqueryType == 17) {
         byte var16;
         if (this.distinctExpression) {
            var16 = 3;
         } else if (this.resultSet.returnsAtMostOneRow()) {
            var16 = 2;
         } else {
            var16 = 1;
         }

         var8.push((int)var16);
         var14 = 8;
      } else {
         var14 = 7;
      }

      var8.push(var15);
      var8.push(this.subqueryNumber);
      var8.push(this.pointOfAttachment);
      var8.push(var6.rowCount());
      var8.push(var6.getEstimatedCost());
      var8.callMethod((short)185, (String)null, var4, "org.apache.derby.iapi.sql.execute.NoPutResultSet", var14);
      if (!this.isMaterializable()) {
         if (this.pushedNewPredicate && !this.hasCorrelatedCRs()) {
            ((ProjectRestrictNode)this.resultSet).setChildResult(var10);
         }

         var8.startElseCode();
         var8.getField(var9);
         var8.completeConditional();
      }

      var8.setField(var9);
      var8.getField(var9);
      var8.callMethod((short)185, (String)null, "openCore", "void", 0);
      var8.getField(var9);
      var8.callMethod((short)185, (String)null, "getNextRowCore", "org.apache.derby.iapi.sql.execute.ExecRow", 0);
      var8.push((int)1);
      var8.callMethod((short)185, "org.apache.derby.iapi.sql.Row", "getColumn", "org.apache.derby.iapi.types.DataValueDescriptor", 1);
      var8.cast(var7);
      if (this.isMaterializable()) {
         var8.getField(var9);
         var8.callMethod((short)185, "org.apache.derby.iapi.sql.ResultSet", "close", "void", 0);
      }

      var8.methodReturn();
      var8.complete();
      if (this.isMaterializable()) {
         LocalField var17 = this.generateMaterialization(var5, var8, var7);
         var2.getField(var17);
      } else {
         var2.pushThis();
         var2.callMethod((short)182, (String)null, var8.getName(), var7, 0);
      }

   }

   private LocalField generateMaterialization(ActivationClassBuilder var1, MethodBuilder var2, String var3) {
      MethodBuilder var4 = var1.getExecuteMethod();
      LocalField var5 = var1.newFieldDeclaration(2, var3);
      var4.pushThis();
      var4.callMethod((short)182, (String)null, var2.getName(), var3, 0);
      var4.setField(var5);
      return var5;
   }

   private BooleanConstantNode getTrueNode() throws StandardException {
      if (this.trueNode == null) {
         this.trueNode = new BooleanConstantNode(true, this.getContextManager());
      }

      return this.trueNode;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (var1 instanceof HasCorrelatedCRsVisitor && this.doneCorrelationCheck) {
         ((HasCorrelatedCRsVisitor)var1).setHasCorrelatedCRs(this.foundCorrelation);
      } else {
         if (this.resultSet != null) {
            this.resultSet = (ResultSetNode)this.resultSet.accept(var1);
         }

         if (this.leftOperand != null) {
            this.leftOperand = (ValueNode)this.leftOperand.accept(var1);
         }

      }
   }

   private boolean isIN() {
      return this.subqueryType == 1;
   }

   private boolean isNOT_IN() {
      return this.subqueryType == 2;
   }

   private boolean isANY() {
      switch (this.subqueryType) {
         case 3:
         case 5:
         case 7:
         case 9:
         case 11:
         case 13:
            return true;
         case 4:
         case 6:
         case 8:
         case 10:
         case 12:
         default:
            return false;
      }
   }

   private boolean isALL() {
      switch (this.subqueryType) {
         case 4:
         case 6:
         case 8:
         case 10:
         case 12:
         case 14:
            return true;
         case 5:
         case 7:
         case 9:
         case 11:
         case 13:
         default:
            return false;
      }
   }

   private boolean isEXISTS() {
      return this.subqueryType == 15;
   }

   private boolean isNOT_EXISTS() {
      return this.subqueryType == 16;
   }

   private void changeToCorrespondingExpressionType() throws StandardException {
      byte var1 = -1;
      switch (this.subqueryType) {
         case 1:
         case 3:
            var1 = 0;
         case 2:
         case 4:
         case 6:
         case 8:
         case 10:
         case 12:
         default:
            break;
         case 5:
            var1 = 5;
            break;
         case 7:
            var1 = 2;
            break;
         case 9:
            var1 = 1;
            break;
         case 11:
            var1 = 4;
            break;
         case 13:
            var1 = 3;
      }

      BinaryRelationalOperatorNode var2 = new BinaryRelationalOperatorNode(var1, this.leftOperand, this, false, this.getContextManager());
      this.subqueryType = 17;
      this.setDataTypeServices(this.resultSet.getResultColumns());
      this.parentComparisonOperator = var2;
      this.parentComparisonOperator.bindComparisonOperator();
      this.leftOperand = null;
   }

   private void setDataTypeServices(ResultColumnList var1) throws StandardException {
      DataTypeDescriptor var2;
      if (this.subqueryType == 17) {
         var2 = ((ResultColumn)var1.elementAt(0)).getTypeServices();
      } else {
         var2 = this.getTrueNode().getTypeServices();
      }

      this.setType(var2.getNullabilityType(true));
   }

   boolean isEquivalent(ValueNode var1) {
      return false;
   }

   public boolean isHavingSubquery() {
      return this.havingSubquery;
   }

   public void setHavingSubquery(boolean var1) {
      this.havingSubquery = var1;
   }

   boolean isWhereSubquery() {
      return this.whereSubquery;
   }

   void setWhereSubquery(boolean var1) {
      this.whereSubquery = var1;
   }

   boolean isWhereExistsAnyInWithWhereSubquery() throws StandardException {
      if (this.isWhereSubquery() && (this.isEXISTS() || this.isANY() || this.isIN())) {
         if (this.resultSet instanceof SelectNode) {
            SelectNode var1 = (SelectNode)this.resultSet;
            if (var1.originalWhereClauseHadSubqueries) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public OrderByList getOrderByList() {
      return this.orderByList;
   }

   public ValueNode getOffset() {
      return this.offset;
   }

   public ValueNode getFetchFirst() {
      return this.fetchFirst;
   }

   public boolean hasJDBClimitClause() {
      return this.hasJDBClimitClause;
   }
}
