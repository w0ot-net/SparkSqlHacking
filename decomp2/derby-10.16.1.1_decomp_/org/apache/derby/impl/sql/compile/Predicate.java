package org.apache.derby.impl.sql.compile;

import java.util.HashSet;
import java.util.Set;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public final class Predicate extends QueryTreeNode implements OptimizablePredicate, Comparable {
   AndNode andNode;
   boolean pushable;
   JBitSet referencedSet;
   int equivalenceClass = -1;
   int indexPosition;
   protected boolean startKey;
   protected boolean stopKey;
   protected boolean isQualifier;
   private Set searchClauses;
   private boolean scoped;

   Predicate(AndNode var1, JBitSet var2, ContextManager var3) {
      super(var3);
      this.andNode = var1;
      this.pushable = false;
      this.referencedSet = var2;
      this.scoped = false;
   }

   public JBitSet getReferencedMap() {
      return this.referencedSet;
   }

   public boolean hasSubquery() {
      return !this.pushable;
   }

   public boolean hasMethodCall() {
      return !this.pushable;
   }

   public void markStartKey() {
      this.startKey = true;
   }

   public boolean isStartKey() {
      return this.startKey;
   }

   public void markStopKey() {
      this.stopKey = true;
   }

   public boolean isStopKey() {
      return this.stopKey;
   }

   public void markQualifier() {
      this.isQualifier = true;
   }

   public boolean isQualifier() {
      return this.isQualifier;
   }

   public boolean compareWithKnownConstant(Optimizable var1, boolean var2) {
      boolean var3 = false;
      RelationalOperator var4 = this.getRelop();
      if (!this.isRelationalOpPredicate()) {
         return false;
      } else {
         if (var4.compareWithKnownConstant(var1, var2)) {
            var3 = true;
         }

         return var3;
      }
   }

   public int hasEqualOnColumnList(int[] var1, Optimizable var2) throws StandardException {
      RelationalOperator var3 = this.getRelop();
      if (!this.isRelationalOpPredicate()) {
         return -1;
      } else if (var3.getOperator() != 1) {
         return -1;
      } else {
         for(int var4 = 0; var4 < var1.length; ++var4) {
            ColumnReference var5 = var3.getColumnOperand(var2, var1[var4]);
            if (var5 != null && !var3.selfComparison(var5)) {
               return var4;
            }
         }

         return -1;
      }
   }

   public DataValueDescriptor getCompareValue(Optimizable var1) throws StandardException {
      RelationalOperator var2 = this.getRelop();
      return var2.getCompareValue(var1);
   }

   public boolean equalsComparisonWithConstantExpression(Optimizable var1) {
      boolean var2 = false;
      if (this.isRelationalOpPredicate()) {
         var2 = this.getRelop().equalsComparisonWithConstantExpression(var1);
      }

      return var2;
   }

   public double selectivity(Optimizable var1) throws StandardException {
      return this.andNode.getLeftOperand().selectivity(var1);
   }

   public int getIndexPosition() {
      return this.indexPosition;
   }

   public int compareTo(Predicate var1) {
      int var3 = var1.getIndexPosition();
      if (this.indexPosition < var3) {
         return -1;
      } else if (this.indexPosition > var3) {
         return 1;
      } else {
         boolean var4 = false;
         boolean var5 = false;
         boolean var6 = true;
         boolean var7 = true;
         if (this.isRelationalOpPredicate() || this.isInListProbePredicate()) {
            int var8 = ((RelationalOperator)this.andNode.getLeftOperand()).getOperator();
            var4 = var8 == 1 || var8 == 7;
            var6 = var8 == 2 || var8 == 8;
         }

         if (var1.isRelationalOpPredicate() || var1.isInListProbePredicate()) {
            int var10 = ((RelationalOperator)var1.getAndNode().getLeftOperand()).getOperator();
            var5 = var10 == 1 || var10 == 7;
            var7 = var10 == 2 || var10 == 8;
         }

         boolean var11 = var4 && !var5 || !var6 && var7;
         if (var11) {
            return -1;
         } else {
            boolean var9 = var5 && !var4 || !var7 && var6;
            return var9 ? 1 : 0;
         }
      }
   }

   AndNode getAndNode() {
      return this.andNode;
   }

   void setAndNode(AndNode var1) {
      this.andNode = var1;
   }

   boolean getPushable() {
      return this.pushable;
   }

   void setPushable(boolean var1) {
      this.pushable = var1;
   }

   JBitSet getReferencedSet() {
      return this.referencedSet;
   }

   void setEquivalenceClass(int var1) {
      this.equivalenceClass = var1;
   }

   int getEquivalenceClass() {
      return this.equivalenceClass;
   }

   void categorize() throws StandardException {
      this.pushable = this.andNode.categorize(this.referencedSet, false);
   }

   RelationalOperator getRelop() {
      return this.andNode.getLeftOperand() instanceof RelationalOperator ? (RelationalOperator)this.andNode.getLeftOperand() : null;
   }

   final boolean isOrList() {
      return this.andNode.getLeftOperand() instanceof OrNode;
   }

   final boolean isStoreQualifier() {
      return this.andNode.getLeftOperand() instanceof RelationalOperator || this.andNode.getLeftOperand() instanceof OrNode;
   }

   final boolean isPushableOrClause(Optimizable var1) throws StandardException {
      boolean var2 = true;
      if (this.andNode.getLeftOperand() instanceof OrNode) {
         OrNode var4;
         for(ValueNode var3 = this.andNode.getLeftOperand(); var3 instanceof OrNode; var3 = var4.getRightOperand()) {
            var4 = (OrNode)var3;
            if (!(var4.getLeftOperand() instanceof RelationalOperator)) {
               return false;
            }

            if (!((RelationalOperator)var4.getLeftOperand()).isQualifier(var1, true)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   boolean transitiveSearchClauseAdded(RelationalOperator var1) {
      return this.searchClauses != null && this.searchClauses.contains(var1.getOperator());
   }

   void setTransitiveSearchClauseAdded(RelationalOperator var1) {
      if (this.searchClauses == null) {
         this.searchClauses = new HashSet();
      }

      this.searchClauses.add(var1.getOperator());
   }

   int getStartOperator(Optimizable var1) {
      return this.andNode.getLeftOperand() instanceof InListOperatorNode ? 1 : this.getRelop().getStartOperator(var1);
   }

   int getStopOperator(Optimizable var1) {
      return this.andNode.getLeftOperand() instanceof InListOperatorNode ? -1 : this.getRelop().getStopOperator(var1);
   }

   void setIndexPosition(int var1) {
      this.indexPosition = var1;
   }

   void clearScanFlags() {
      this.startKey = false;
      this.stopKey = false;
      this.isQualifier = false;
   }

   void generateExpressionOperand(Optimizable var1, int var2, ExpressionClassBuilder var3, MethodBuilder var4) throws StandardException {
      this.getRelop().generateExpressionOperand(var1, var2, var3, var4);
   }

   public String toString() {
      return "";
   }

   String binaryRelOpColRefsToString() {
      if (!(this.getAndNode().getLeftOperand() instanceof BinaryRelationalOperatorNode)) {
         return "";
      } else {
         StringBuilder var1 = new StringBuilder();
         BinaryRelationalOperatorNode var2 = (BinaryRelationalOperatorNode)this.getAndNode().getLeftOperand();
         if (var2.getLeftOperand() instanceof ColumnReference) {
            var1.append(((ColumnReference)var2.getLeftOperand()).getTableName());
            var1.append('.');
            var1.append(((ColumnReference)var2.getLeftOperand()).getColumnName());
         } else {
            var1.append("<expr>");
         }

         var1.append(' ');
         var1.append(var2.operator);
         var1.append(' ');
         if (var2.getRightOperand() instanceof ColumnReference) {
            String var10001 = ((ColumnReference)var2.getRightOperand()).getTableName();
            var1.append(var10001 + "." + ((ColumnReference)var2.getRightOperand()).getColumnName());
         } else {
            var1.append("<expr>");
         }

         return var1.toString();
      }
   }

   void printSubNodes(int var1) {
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.andNode != null) {
         this.andNode = (AndNode)this.andNode.accept(var1);
      }

   }

   void copyFields(Predicate var1) {
      this.equivalenceClass = var1.getEquivalenceClass();
      this.indexPosition = var1.getIndexPosition();
      this.startKey = var1.isStartKey();
      this.stopKey = var1.isStopKey();
      this.isQualifier = var1.isQualifier();
      this.searchClauses = var1.searchClauses;
   }

   protected boolean pushableToSubqueries() throws StandardException {
      if (!this.isJoinPredicate()) {
         return false;
      } else {
         BinaryRelationalOperatorNode var1 = (BinaryRelationalOperatorNode)this.getAndNode().getLeftOperand();
         JBitSet var2 = new JBitSet(this.getReferencedSet().size());
         BaseTableNumbersVisitor var3 = new BaseTableNumbersVisitor(var2);
         var1.getLeftOperand().accept(var3);
         if (var2.getFirstSetBit() == -1) {
            return false;
         } else {
            var2.clearAll();
            var1.getRightOperand().accept(var3);
            return var2.getFirstSetBit() != -1;
         }
      }
   }

   protected boolean isJoinPredicate() {
      if (!(this.getAndNode().getLeftOperand() instanceof BinaryRelationalOperatorNode)) {
         return false;
      } else {
         BinaryRelationalOperatorNode var1 = (BinaryRelationalOperatorNode)this.getAndNode().getLeftOperand();
         return var1.getLeftOperand() instanceof ColumnReference && var1.getRightOperand() instanceof ColumnReference && ((ColumnReference)var1.getLeftOperand()).getTableNumber() != ((ColumnReference)var1.getRightOperand()).getTableNumber();
      }
   }

   protected Predicate getPredScopedForResultSet(JBitSet var1, ResultSetNode var2, int[] var3) throws StandardException {
      if (!(this.getAndNode().getLeftOperand() instanceof BinaryRelationalOperatorNode)) {
         return this;
      } else {
         BooleanConstantNode var4 = new BooleanConstantNode(true, this.getContextManager());
         BinaryRelationalOperatorNode var5 = (BinaryRelationalOperatorNode)this.getAndNode().getLeftOperand();
         BinaryRelationalOperatorNode var6 = new BinaryRelationalOperatorNode(var5.kind, var5.getScopedOperand(-1, var1, var2, var3), var5.getScopedOperand(1, var1, var2, var3), var5.getForQueryRewrite(), this.getContextManager());
         var6.bindComparisonOperator();
         AndNode var7 = new AndNode(var6, var4, this.getContextManager());
         var7.postBindFixup();
         JBitSet var8 = new JBitSet(var2.getReferencedTableMap().size());
         var7.categorize(var8, false);
         Predicate var9 = new Predicate(var7, var8, this.getContextManager());
         var9.clearScanFlags();
         var9.copyFields(this);
         var9.setPushable(this.getPushable());
         var9.markAsScopedForPush();
         return var9;
      }
   }

   protected void markAsScopedForPush() {
      this.scoped = true;
   }

   protected boolean isScopedForPush() {
      return this.scoped;
   }

   protected boolean remapScopedPred() {
      if (!this.scoped) {
         return false;
      } else {
         BinaryRelationalOperatorNode var1 = (BinaryRelationalOperatorNode)this.andNode.getLeftOperand();
         ValueNode var2 = var1.getLeftOperand();
         if (var2 instanceof ColumnReference && ((ColumnReference)var2).isScoped()) {
            ((ColumnReference)var2).remapColumnReferences();
         } else {
            var2 = var1.getRightOperand();
            if (var2 instanceof ColumnReference && ((ColumnReference)var2).isScoped()) {
               ((ColumnReference)var2).remapColumnReferences();
            }
         }

         return true;
      }
   }

   protected boolean isScopedToSourceResultSet() throws StandardException {
      if (!this.scoped) {
         return false;
      } else {
         BinaryRelationalOperatorNode var1 = (BinaryRelationalOperatorNode)this.andNode.getLeftOperand();
         ValueNode var2 = var1.getLeftOperand();
         if (!(var2 instanceof ColumnReference)) {
            return false;
         } else {
            ColumnReference var4 = (ColumnReference)var2;
            if (var4.isScoped()) {
               ValueNode var6 = var4.getSource().getExpression();
               return var6 instanceof VirtualColumnNode || var6 instanceof ColumnReference;
            } else {
               var2 = var1.getRightOperand();
               if (!(var2 instanceof ColumnReference)) {
                  return false;
               } else {
                  var4 = (ColumnReference)var2;
                  ValueNode var3 = var4.getSource().getExpression();
                  return var3 instanceof VirtualColumnNode || var3 instanceof ColumnReference;
               }
            }
         }
      }
   }

   protected boolean isRelationalOpPredicate() {
      return this.andNode.getLeftOperand().isRelationalOperator();
   }

   protected boolean isInListProbePredicate() {
      return this.andNode.getLeftOperand().isInListProbeNode();
   }

   protected InListOperatorNode getSourceInList() {
      return this.getSourceInList(false);
   }

   protected InListOperatorNode getSourceInList(boolean var1) {
      ValueNode var2 = this.andNode.getLeftOperand();
      if (this.isInListProbePredicate()) {
         return ((BinaryRelationalOperatorNode)var2).getInListOp();
      } else if (var1) {
         return null;
      } else {
         return var2 instanceof InListOperatorNode ? (InListOperatorNode)var2 : null;
      }
   }
}
