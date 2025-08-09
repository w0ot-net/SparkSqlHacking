package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.AccessPath;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.ExpressionClassBuilderInterface;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class PredicateList extends QueryTreeNodeVector implements OptimizablePredicateList {
   private int numberOfStartPredicates;
   private int numberOfStopPredicates;
   private int numberOfQualifiers;
   private static final int QUALIFIER_ORDER_EQUALS = 0;
   private static final int QUALIFIER_ORDER_OTHER_RELOP = 1;
   private static final int QUALIFIER_ORDER_NOT_EQUALS = 2;
   private static final int QUALIFIER_ORDER_NON_QUAL = 3;
   private static final int QUALIFIER_ORDER_OR_CLAUSE = 4;
   private static final int QUALIFIER_NUM_CATEGORIES = 5;

   PredicateList(ContextManager var1) {
      super(Predicate.class, var1);
   }

   public OptimizablePredicate getOptPredicate(int var1) {
      return (OptimizablePredicate)this.elementAt(var1);
   }

   public final void removeOptPredicate(int var1) throws StandardException {
      Predicate var2 = (Predicate)this.removeElementAt(var1);
      if (var2.isStartKey()) {
         --this.numberOfStartPredicates;
      }

      if (var2.isStopKey()) {
         --this.numberOfStopPredicates;
      }

      if (var2.isQualifier()) {
         --this.numberOfQualifiers;
      }

   }

   public final void removeOptPredicate(OptimizablePredicate var1) {
      this.removeElement((Predicate)var1);
      if (var1.isStartKey()) {
         --this.numberOfStartPredicates;
      }

      if (var1.isStopKey()) {
         --this.numberOfStopPredicates;
      }

      if (var1.isQualifier()) {
         --this.numberOfQualifiers;
      }

   }

   public void addOptPredicate(OptimizablePredicate var1) {
      this.addElement((Predicate)var1);
      if (var1.isStartKey()) {
         ++this.numberOfStartPredicates;
      }

      if (var1.isStopKey()) {
         ++this.numberOfStopPredicates;
      }

      if (var1.isQualifier()) {
         ++this.numberOfQualifiers;
      }

   }

   public void addOptPredicate(OptimizablePredicate var1, int var2) {
      this.insertElementAt((Predicate)var1, var2);
      if (var1.isStartKey()) {
         ++this.numberOfStartPredicates;
      }

      if (var1.isStopKey()) {
         ++this.numberOfStopPredicates;
      }

      if (var1.isQualifier()) {
         ++this.numberOfQualifiers;
      }

   }

   public boolean useful(Optimizable var1, ConglomerateDescriptor var2) throws StandardException {
      boolean var3 = false;
      if (!var2.isIndex()) {
         return false;
      } else {
         for(Predicate var5 : this) {
            RelationalOperator var6 = var5.getRelop();
            InListOperatorNode var7 = var5.getSourceInList();
            boolean var8 = var7 != null;
            if ((var8 || var6 != null) && (var8 || var6.usefulStartKey(var1) || var6.usefulStopKey(var1))) {
               ColumnReference var9 = null;
               if (var8) {
                  if (var7.getLeftOperand() instanceof ColumnReference) {
                     var9 = (ColumnReference)var7.getLeftOperand();
                     if (var9.getColumnNumber() != var2.getIndexDescriptor().baseColumnPositions()[0]) {
                        var9 = null;
                     }
                  }
               } else {
                  var9 = var6.getColumnOperand(var1, var2.getIndexDescriptor().baseColumnPositions()[0]);
               }

               if (var9 != null && (!var8 || !var7.selfReference(var9)) && (var8 || !var6.selfComparison(var9))) {
                  var3 = true;
                  break;
               }
            }
         }

         return var3;
      }
   }

   public void pushUsefulPredicates(Optimizable var1) throws StandardException {
      AccessPath var2 = var1.getTrulyTheBestAccessPath();
      this.orderUsefulPredicates(var1, var2.getConglomerateDescriptor(), true, var2.getNonMatchingIndexScan(), var2.getCoveringIndexScan());
   }

   public void classify(Optimizable var1, ConglomerateDescriptor var2) throws StandardException {
      this.orderUsefulPredicates(var1, var2, false, false, false);
   }

   public void markAllPredicatesQualifiers() {
      for(Predicate var2 : this) {
         var2.markQualifier();
      }

      this.numberOfQualifiers = this.size();
   }

   public int hasEqualityPredicateOnOrderedColumn(Optimizable var1, int var2, boolean var3) throws StandardException {
      int var5 = this.size();

      for(int var6 = 0; var6 < var5; ++var6) {
         Predicate var8 = (Predicate)this.elementAt(var6);
         if (!var8.getReferencedMap().hasSingleBitSet()) {
            AndNode var7 = var8.getAndNode();
            ValueNode var4 = var7.getLeftOperand();
            if (var4.optimizableEqualityNode(var1, var2, var3)) {
               return var6;
            }
         }
      }

      return -1;
   }

   public boolean hasOptimizableEqualityPredicate(Optimizable var1, int var2, boolean var3) throws StandardException {
      for(Predicate var5 : this) {
         AndNode var6 = var5.getAndNode();
         ValueNode var7 = var6.getLeftOperand();
         if (var7.optimizableEqualityNode(var1, var2, var3)) {
            return true;
         }
      }

      return false;
   }

   public boolean hasOptimizableEquijoin(Optimizable var1, int var2) throws StandardException {
      for(Predicate var4 : this) {
         if (!var4.isScopedForPush()) {
            AndNode var5 = var4.getAndNode();
            ValueNode var6 = var5.getLeftOperand();
            if (var6.optimizableEqualityNode(var1, var2, false) && ((RelationalOperator)var6).isQualifier(var1, false) && !var4.getReferencedMap().hasSingleBitSet()) {
               return true;
            }
         }
      }

      return false;
   }

   public void putOptimizableEqualityPredicateFirst(Optimizable var1, int var2) throws StandardException {
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         Predicate var5 = (Predicate)this.elementAt(var4);
         AndNode var6 = var5.getAndNode();
         ValueNode var7 = var6.getLeftOperand();
         if (var7.optimizableEqualityNode(var1, var2, false)) {
            if (var4 != 0) {
               this.removeElementAt(var4);
               this.insertElementAt(var5, 0);
            }

            return;
         }
      }

   }

   private void orderUsefulPredicates(Optimizable var1, ConglomerateDescriptor var2, boolean var3, boolean var4, boolean var5) throws StandardException {
      int var8 = this.size();
      Predicate[] var9 = new Predicate[var8];
      int var10 = 0;

      for(Predicate var12 : this) {
         var12.clearScanFlags();
      }

      if (var2 == null || !var2.isIndex() || var4 && var5) {
         Predicate[] var32 = new Predicate[var8];

         for(int var36 = 0; var36 < var8; ++var36) {
            Predicate var39 = (Predicate)this.elementAt(var36);
            if (!var39.isRelationalOpPredicate()) {
               if (!var39.isPushableOrClause(var1)) {
                  continue;
               }
            } else if (!var39.getRelop().isQualifier(var1, var3)) {
               continue;
            }

            var39.markQualifier();
            if (var3 && var1.pushOptPredicate(var39)) {
               var32[var36] = var39;
            }
         }

         for(int var37 = var8 - 1; var37 >= 0; --var37) {
            if (var32[var37] != null) {
               this.removeOptPredicate(var32[var37]);
            }
         }

      } else {
         int[] var6 = var2.getIndexDescriptor().baseColumnPositions();
         boolean[] var7 = var2.getIndexDescriptor().isAscending();
         boolean var31 = var3 && var1.getTrulyTheBestAccessPath().getJoinStrategy().isHashJoin();

         for(Predicate var13 : this) {
            ColumnReference var14 = null;
            RelationalOperator var16 = var13.getRelop();
            InListOperatorNode var17 = var13.getSourceInList();
            boolean var18 = var17 != null;
            if ((var18 || var16 != null && var16.isQualifier(var1, var3)) && (!var31 || !var13.isInListProbePredicate())) {
               int var15;
               for(var15 = 0; var15 < var6.length; ++var15) {
                  if (var18) {
                     if (var17.getLeftOperand() instanceof ColumnReference) {
                        var14 = (ColumnReference)var17.getLeftOperand();
                        if (var1.getTableNumber() == var14.getTableNumber() && var14.getColumnNumber() == var6[var15] && !var17.selfReference(var14)) {
                           if (var13.isInListProbePredicate() && var15 > 0) {
                              var14 = null;
                           }
                        } else {
                           var14 = null;
                        }
                     }
                  } else {
                     var14 = var16.getColumnOperand(var1, var6[var15]);
                  }

                  if (var14 != null) {
                     break;
                  }
               }

               if (var14 != null) {
                  var13.setIndexPosition(var15);
                  var9[var10++] = var13;
               }
            }
         }

         if (var10 != 0) {
            if (var9.length > var10) {
               Predicate[] var34 = new Predicate[var10];
               System.arraycopy(var9, 0, var34, 0, var10);
               var9 = var34;
            }

            Arrays.sort(var9);
            int var35 = -1;
            boolean var38 = false;
            int var40 = -1;
            boolean var41 = false;
            boolean var42 = false;
            int var43 = -1;
            int var44 = -1;
            boolean var19 = false;
            boolean var20 = false;

            for(int var21 = 0; var21 < var10; ++var21) {
               Predicate var22 = var9[var21];
               int var23 = var22.getIndexPosition();
               boolean var24 = false;
               RelationalOperator var25 = var22.getRelop();
               int var26 = -1;
               boolean var27 = var22.getSourceInList() != null;
               if (var25 != null) {
                  var26 = var25.getOperator();
               }

               if (var35 != var23) {
                  if (var23 - var35 > 1) {
                     var38 = true;
                  } else if (var26 == 1 || var26 == 7) {
                     var44 = var23;
                  }

                  if (!var38 && !var20 && (var27 || var25.usefulStartKey(var1) && var7[var23] || var25.usefulStopKey(var1) && !var7[var23])) {
                     var22.markStartKey();
                     var35 = var23;
                     var24 = true;
                     var20 = var22.getStartOperator(var1) == -1;
                  }
               }

               if (var40 != var23) {
                  if (var23 - var40 > 1) {
                     var41 = true;
                  }

                  if (!var41 && !var19 && (var27 || var25.usefulStopKey(var1) && var7[var23] || var25.usefulStartKey(var1) && !var7[var23])) {
                     var22.markStopKey();
                     var40 = var23;
                     var24 = true;
                     var19 = var22.getStopOperator(var1) == 1;
                  }
               }

               if (!var27 && (!var24 || var42 && var23 != var43)) {
                  var22.markQualifier();
               }

               if (var44 != var23 && var43 == -1 && var26 != 1 && var26 != 7) {
                  var42 = true;
                  var43 = var23;
               }

               if (var3) {
                  if (!var27 || var24) {
                     Predicate var28;
                     if (var27 && !var22.isInListProbePredicate()) {
                        AndNode var29 = new AndNode(var22.getAndNode().getLeftOperand(), var22.getAndNode().getRightOperand(), this.getContextManager());
                        var29.copyFields(var22.getAndNode());
                        Predicate var30 = new Predicate(var29, var22.getReferencedSet(), this.getContextManager());
                        var30.copyFields(var22);
                        var28 = var30;
                     } else {
                        var28 = var22;
                     }

                     if (var1.pushOptPredicate(var28) && (!var27 || var22.isInListProbePredicate())) {
                        this.removeOptPredicate(var22);
                     }
                  }
               } else {
                  this.removeOptPredicate(var22);
                  this.addOptPredicate(var22, var21);
               }
            }

         }
      }
   }

   void addPredicate(Predicate var1) throws StandardException {
      if (var1.isStartKey()) {
         ++this.numberOfStartPredicates;
      }

      if (var1.isStopKey()) {
         ++this.numberOfStopPredicates;
      }

      if (var1.isQualifier()) {
         ++this.numberOfQualifiers;
      }

      this.addElement(var1);
   }

   protected void transferNonQualifiers(Optimizable var1, PredicateList var2) throws StandardException {
      for(int var3 = this.size() - 1; var3 >= 0; --var3) {
         Predicate var4 = (Predicate)this.elementAt(var3);
         if (!var4.isRelationalOpPredicate() || !var4.getRelop().isQualifier(var1, false)) {
            var4.clearScanFlags();
            this.removeElementAt(var3);
            var2.addElement(var4);
         }
      }

      this.markAllPredicatesQualifiers();
   }

   void categorize() throws StandardException {
      for(Predicate var2 : this) {
         var2.categorize();
      }

   }

   void eliminateBooleanTrueAndBooleanTrue() {
      for(int var1 = this.size() - 1; var1 >= 0; --var1) {
         AndNode var2 = ((Predicate)this.elementAt(var1)).getAndNode();
         if (var2.getLeftOperand().isBooleanTrue() && var2.getRightOperand().isBooleanTrue()) {
            this.removeElementAt(var1);
         }
      }

   }

   ValueNode restoreConstantPredicates() throws StandardException {
      AndNode var2 = null;
      Object var3 = null;

      for(int var4 = this.size() - 1; var4 >= 0; --var4) {
         AndNode var1 = ((Predicate)this.elementAt(var4)).getAndNode();
         if (var1.isConstantExpression()) {
            this.removeElementAt(var4);
            if (!var1.getLeftOperand().isBooleanTrue() || !var1.getRightOperand().isBooleanTrue()) {
               if (var1.getLeftOperand().isBooleanFalse()) {
                  var2 = var1;
               }

               if (var3 != null) {
                  var1.setRightOperand((ValueNode)var3);
                  if (((ValueNode)var3).getTypeServices().isNullable()) {
                     var1.setNullability(true);
                  }
               }

               var3 = var1;
            }
         }
      }

      if (var3 != null && ((AndNode)var3).getRightOperand().isBooleanTrue()) {
         var3 = ((AndNode)var3).getLeftOperand();
      } else if (var2 != null) {
         var3 = var2.getLeftOperand();
      }

      return (ValueNode)var3;
   }

   ValueNode restorePredicates() throws StandardException {
      AndNode var2 = null;
      Object var3 = null;
      int var4 = this.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         AndNode var1 = ((Predicate)this.elementAt(var5)).getAndNode();
         if (!var1.getLeftOperand().isBooleanTrue() || !var1.getRightOperand().isBooleanTrue()) {
            if (var1.getLeftOperand().isBooleanFalse()) {
               var2 = var1;
            }

            if (var3 != null) {
               var1.setRightOperand((ValueNode)var3);
               if (((ValueNode)var3).getTypeServices().isNullable()) {
                  var1.setNullability(true);
               }
            }

            var3 = var1;
         }
      }

      if (var3 != null && ((AndNode)var3).getRightOperand().isBooleanTrue()) {
         var3 = ((AndNode)var3).getLeftOperand();
      } else if (var2 != null) {
         var3 = var2.getLeftOperand();
      }

      this.removeAllElements();
      return (ValueNode)var3;
   }

   void remapColumnReferencesToExpressions() throws StandardException {
      for(Predicate var2 : this) {
         var2.setAndNode((AndNode)var2.getAndNode().remapColumnReferencesToExpressions());
      }

   }

   void pullExpressions(int var1, ValueNode var2) throws StandardException {
      if (var2 != null) {
         AndNode var4 = (AndNode)var2;
         BooleanConstantNode var7 = new BooleanConstantNode(true, this.getContextManager());

         while(var4.getRightOperand() instanceof AndNode) {
            AndNode var3 = var4;
            var4 = (AndNode)var4.getRightOperand();
            var3.setRightOperand((ValueNode)null);
            var3.setRightOperand(var7);
            JBitSet var5 = new JBitSet(var1);
            Predicate var6 = new Predicate(var3, var5, this.getContextManager());
            this.addPredicate(var6);
         }

         JBitSet var8 = new JBitSet(var1);
         Predicate var9 = new Predicate(var4, var8, this.getContextManager());
         this.addPredicate(var9);
      }

   }

   void xorReferencedSet(JBitSet var1) {
      for(Predicate var3 : this) {
         var3.getReferencedSet().xor(var1);
      }

   }

   private void countScanFlags() {
      for(Predicate var2 : this) {
         if (var2.isStartKey()) {
            ++this.numberOfStartPredicates;
         }

         if (var2.isStopKey()) {
            ++this.numberOfStopPredicates;
         }

         if (var2.isQualifier()) {
            ++this.numberOfQualifiers;
         }
      }

   }

   private static boolean isConstantOrParameterNode(ValueNode var0) {
      return var0 instanceof ConstantNode || var0 instanceof ParameterNode;
   }

   void pushExpressionsIntoSelect(SelectNode var1, boolean var2) throws StandardException {
      for(int var3 = this.size() - 1; var3 >= 0; --var3) {
         Predicate var4 = (Predicate)this.elementAt(var3);
         CollectNodesVisitor var5 = new CollectNodesVisitor(ColumnReference.class);
         var4.getAndNode().accept(var5);
         List var6 = var5.getList();
         boolean var7 = var6.size() > 0;
         if (var7) {
            for(ColumnReference var9 : var6) {
               if (!var9.pointsToColumnReference()) {
                  var7 = false;
                  break;
               }
            }
         }

         if (var7) {
            if (var2) {
               AndNode var17 = var4.getAndNode();
               BinaryRelationalOperatorNode var11 = null;
               InListOperatorNode var12 = null;
               ColumnReference var10;
               if (var17.getLeftOperand() instanceof BinaryRelationalOperatorNode) {
                  var11 = (BinaryRelationalOperatorNode)var17.getLeftOperand();
                  if (!(var11.getLeftOperand() instanceof ColumnReference) || !isConstantOrParameterNode(var11.getRightOperand())) {
                     continue;
                  }

                  var10 = (ColumnReference)var11.getLeftOperand();
               } else {
                  if (!(var17.getLeftOperand() instanceof InListOperatorNode)) {
                     continue;
                  }

                  var12 = (InListOperatorNode)var17.getLeftOperand();
                  if (!(var12.getLeftOperand() instanceof ColumnReference) || !var12.getRightOperandList().isConstantExpression()) {
                     continue;
                  }

                  var10 = (ColumnReference)var12.getLeftOperand();
               }

               ColumnReference var13 = var1.findColumnReferenceInResult(var10.getColumnName());
               if (var13 == null) {
                  continue;
               }

               Object var18;
               if (var17.getLeftOperand() instanceof BinaryRelationalOperatorNode) {
                  var12 = var11.getInListOp();
                  if (var12 != null) {
                     var12 = var12.shallowCopy();
                     var12.setLeftOperand(var13);
                  }

                  BinaryRelationalOperatorNode var14 = new BinaryRelationalOperatorNode(var11.kind, var13, var11.getRightOperand(), var12, var11.getForQueryRewrite(), this.getContextManager());
                  var14.bindComparisonOperator();
                  var18 = var14;
               } else {
                  InListOperatorNode var20 = new InListOperatorNode(var13, var12.getRightOperandList(), this.getContextManager());
                  var20.setType(var12.getTypeServices());
                  var18 = var20;
               }

               BooleanConstantNode var21 = new BooleanConstantNode(true, this.getContextManager());
               AndNode var15 = new AndNode((ValueNode)var18, var21, this.getContextManager());
               var15.postBindFixup();
               JBitSet var16 = new JBitSet(var1.getReferencedTableMap().size());
               var4 = new Predicate(var15, var16, this.getContextManager());
            } else {
               if (var4.isStartKey()) {
                  --this.numberOfStartPredicates;
               }

               if (var4.isStopKey()) {
                  --this.numberOfStopPredicates;
               }

               if (var4.isQualifier()) {
                  --this.numberOfQualifiers;
               }

               var4.clearScanFlags();
               this.removeElementAt(var3);
            }

            var1.pushExpressionsIntoSelect(var4);
         }
      }

   }

   void markReferencedColumns() throws StandardException {
      CollectNodesVisitor var1 = new CollectNodesVisitor(ColumnReference.class);

      for(Predicate var3 : this) {
         var3.getAndNode().accept(var1);
      }

      for(ColumnReference var6 : var1.getList()) {
         ResultColumn var4 = var6.getSource();
         if (var4 != null) {
            var4.markAllRCsInChainReferenced();
         }
      }

   }

   void checkTopPredicatesForEqualsConditions(int var1, boolean[] var2, int[] var3, JBitSet[] var4, boolean var5) throws StandardException {
      for(Predicate var7 : this) {
         var7.getAndNode().checkTopPredicatesForEqualsConditions(var1, var2, var3, var4, var5);
      }

   }

   boolean allPushable() {
      for(Predicate var2 : this) {
         if (!var2.getPushable()) {
            return false;
         }
      }

      return true;
   }

   boolean allReference(FromBaseTable var1) {
      int var2 = var1.getTableNumber();

      for(Predicate var4 : this) {
         if (!var4.getReferencedSet().get(var2)) {
            return false;
         }
      }

      return true;
   }

   PredicateList getPushablePredicates(JBitSet var1) throws StandardException {
      PredicateList var2 = null;

      for(int var3 = this.size() - 1; var3 >= 0; --var3) {
         Predicate var4 = (Predicate)this.elementAt(var3);
         if (var4.getPushable()) {
            JBitSet var5 = var4.getReferencedSet();
            if (var1.contains(var5)) {
               if (var2 == null) {
                  var2 = new PredicateList(this.getContextManager());
               }

               var2.addPredicate(var4);
               RemapCRsVisitor var6 = new RemapCRsVisitor(true);
               var4.getAndNode().accept(var6);
               this.removeElementAt(var3);
            }
         }
      }

      return var2;
   }

   void decrementLevel(FromList var1, int var2) {
      int[] var3 = var1.getTableNumbers();

      for(Predicate var5 : this) {
         ColumnReference var6 = null;
         ColumnReference var7 = null;
         ValueNode var8 = var5.getAndNode().getLeftOperand();
         if (var8 instanceof BinaryOperatorNode var9) {
            if (var9.getLeftOperand() instanceof ColumnReference) {
               var6 = (ColumnReference)var9.getLeftOperand();
            }

            if (var9.getRightOperand() instanceof ColumnReference) {
               var7 = (ColumnReference)var9.getRightOperand();
            }
         } else if (var8 instanceof UnaryOperatorNode var11) {
            if (var11.getOperand() instanceof ColumnReference) {
               var6 = (ColumnReference)var11.getOperand();
            }
         }

         if (var6 != null) {
            int var12 = var6.getTableNumber();

            for(int var10 = 0; var10 < var3.length; ++var10) {
               if (var3[var10] == var12) {
                  var6.setSourceLevel(var6.getSourceLevel() - var2);
                  break;
               }
            }
         }

         if (var7 != null) {
            int var13 = var7.getTableNumber();

            for(int var14 = 0; var14 < var3.length; ++var14) {
               if (var3[var14] == var13) {
                  var7.setSourceLevel(var7.getSourceLevel() - var2);
                  break;
               }
            }
         }
      }

   }

   void joinClauseTransitiveClosure(int var1, FromList var2, CompilerContext var3) throws StandardException {
      if (var2.size() >= 3) {
         PredicateList[] var4 = new PredicateList[var1];

         for(int var5 = 0; var5 < var1; ++var5) {
            var4[var5] = new PredicateList(this.getContextManager());
         }

         for(Predicate var6 : this) {
            ValueNode var7 = var6.getAndNode().getLeftOperand();
            if (var7.isBinaryEqualsOperatorNode()) {
               BinaryRelationalOperatorNode var8 = (BinaryRelationalOperatorNode)var7;
               ValueNode var9 = var8.getLeftOperand();
               ValueNode var10 = var8.getRightOperand();
               if (var9 instanceof ColumnReference && var10 instanceof ColumnReference) {
                  ColumnReference var11 = (ColumnReference)var9;
                  ColumnReference var12 = (ColumnReference)var10;
                  if (var11.getSourceLevel() == var12.getSourceLevel() && var11.getTableNumber() != var12.getTableNumber() && !var2.tableNumberIsNotExists(var11.getTableNumber()) && !var2.tableNumberIsNotExists(var12.getTableNumber())) {
                     var4[var11.getTableNumber()].addElement(var6);
                     var4[var12.getTableNumber()].addElement(var6);
                  }
               }
            }
         }

         for(int var39 = 0; var39 < var1; ++var39) {
            PredicateList var40 = var4[var39];
            if (var40.size() != 0) {
               ArrayList var41 = new ArrayList();

               for(int var42 = var40.size() - 1; var42 >= 0; --var42) {
                  Predicate var45 = (Predicate)var40.elementAt(var42);
                  if (var45.getEquivalenceClass() != -1) {
                     var40.removeElementAt(var42);
                     var41.add(var45);
                  }
               }

               for(int var43 = 0; var43 < var41.size(); ++var43) {
                  var40.insertElementAt((Predicate)var41.get(var43), 0);
               }

               label156:
               for(int var44 = 0; var44 < var40.size(); ++var44) {
                  ColumnReference var46 = null;
                  int var48 = var39;
                  Predicate var16 = (Predicate)var40.elementAt(var44);
                  if (var16.getEquivalenceClass() == -1) {
                     var16.setEquivalenceClass(var3.getNextEquivalenceClass());
                  }

                  int var15 = var16.getEquivalenceClass();
                  BinaryRelationalOperatorNode var17 = (BinaryRelationalOperatorNode)var16.getAndNode().getLeftOperand();
                  ColumnReference var18 = (ColumnReference)var17.getLeftOperand();
                  ColumnReference var19 = (ColumnReference)var17.getRightOperand();
                  int var13;
                  int var14;
                  ColumnReference var47;
                  int var49;
                  if (var18.getTableNumber() == var39) {
                     var13 = var18.getColumnNumber();
                     var49 = var19.getTableNumber();
                     var14 = var19.getColumnNumber();
                     var47 = var18;
                  } else {
                     var13 = var19.getColumnNumber();
                     var49 = var18.getTableNumber();
                     var14 = var18.getColumnNumber();
                     var47 = var19;
                  }

                  PredicateList var20 = var4[var49];
                  Iterator var21 = var20.iterator();

                  while(true) {
                     Predicate var22;
                     int var23;
                     int var24;
                     while(true) {
                        if (!var21.hasNext()) {
                           continue label156;
                        }

                        var22 = (Predicate)var21.next();
                        if (var22.getEquivalenceClass() == -1 || var22.getEquivalenceClass() == var15) {
                           BinaryRelationalOperatorNode var25 = (BinaryRelationalOperatorNode)var22.getAndNode().getLeftOperand();
                           ColumnReference var26 = (ColumnReference)var25.getLeftOperand();
                           ColumnReference var27 = (ColumnReference)var25.getRightOperand();
                           if (var26.getTableNumber() == var49) {
                              if (var26.getColumnNumber() == var14) {
                                 var23 = var27.getTableNumber();
                                 var24 = var27.getColumnNumber();
                                 break;
                              }
                           } else if (var27.getColumnNumber() == var14) {
                              var23 = var26.getTableNumber();
                              var24 = var26.getColumnNumber();
                              break;
                           }
                        }
                     }

                     if (var48 != var23 || var13 != var24) {
                        var22.setEquivalenceClass(var15);
                        Predicate var30 = null;
                        PredicateList var31 = var4[var23];

                        int var32;
                        for(var32 = 0; var32 < var31.size(); ++var32) {
                           var30 = (Predicate)var31.elementAt(var32);
                           if (var30.getEquivalenceClass() == -1 || var30.getEquivalenceClass() == var15) {
                              BinaryRelationalOperatorNode var33 = (BinaryRelationalOperatorNode)var30.getAndNode().getLeftOperand();
                              ColumnReference var34 = (ColumnReference)var33.getLeftOperand();
                              ColumnReference var35 = (ColumnReference)var33.getRightOperand();
                              int var28;
                              int var29;
                              if (var34.getTableNumber() == var23) {
                                 if (var34.getColumnNumber() != var24) {
                                    continue;
                                 }

                                 var28 = var35.getTableNumber();
                                 var29 = var35.getColumnNumber();
                                 var46 = var34;
                              } else {
                                 if (var35.getColumnNumber() != var24) {
                                    continue;
                                 }

                                 var28 = var34.getTableNumber();
                                 var29 = var34.getColumnNumber();
                                 var46 = var35;
                              }

                              if (var28 == var48 && var29 == var13) {
                                 break;
                              }
                           }
                        }

                        if (var32 != var31.size()) {
                           var30.setEquivalenceClass(var15);
                        } else {
                           BinaryRelationalOperatorNode var50 = new BinaryRelationalOperatorNode(0, var47.getClone(), var46.getClone(), false, this.getContextManager());
                           var50.bindComparisonOperator();
                           BooleanConstantNode var51 = new BooleanConstantNode(true, this.getContextManager());
                           AndNode var52 = new AndNode(var50, var51, this.getContextManager());
                           var52.postBindFixup();
                           JBitSet var36 = new JBitSet(var1);
                           var52.categorize(var36, false);
                           Predicate var37 = new Predicate(var52, var36, this.getContextManager());
                           var37.setEquivalenceClass(var15);
                           this.addPredicate(var37);
                           if (var44 != var40.size() - 1) {
                              var40.insertElementAt(var37, var44 + 1);
                           } else {
                              var40.addElement(var37);
                           }

                           if (var40 != var31) {
                              var31.addElement(var37);
                           }
                        }
                     }
                  }
               }
            }
         }

      }
   }

   void searchClauseTransitiveClosure(int var1, boolean var2) throws StandardException {
      PredicateList var3 = new PredicateList(this.getContextManager());
      PredicateList var4 = new PredicateList(this.getContextManager());
      BinaryRelationalOperatorNode var5 = null;
      int var6 = this.size();

      for(int var7 = 0; var7 < var6; ++var7) {
         Predicate var9 = (Predicate)this.elementAt(var7);
         AndNode var8 = var9.getAndNode();
         if (var9.isRelationalOpPredicate()) {
            RelationalOperator var10 = (RelationalOperator)var8.getLeftOperand();
            if (((ValueNode)var10).isBinaryEqualsOperatorNode()) {
               BinaryRelationalOperatorNode var11 = (BinaryRelationalOperatorNode)var10;
               var5 = var11;
               ValueNode var12 = var11.getLeftOperand();
               ValueNode var13 = var11.getRightOperand();
               if (var12 instanceof ColumnReference && var13 instanceof ColumnReference) {
                  ColumnReference var14 = (ColumnReference)var12;
                  ColumnReference var15 = (ColumnReference)var13;
                  if (var14.getSourceLevel() == var15.getSourceLevel() && var14.getTableNumber() != var15.getTableNumber()) {
                     var3.addElement(var9);
                  }
                  continue;
               }
            }

            if (var10 instanceof UnaryComparisonOperatorNode) {
               if (((UnaryComparisonOperatorNode)var10).getOperand() instanceof ColumnReference) {
                  var4.addElement(var9);
               }
            } else if (var10 instanceof BinaryComparisonOperatorNode) {
               BinaryComparisonOperatorNode var33 = (BinaryComparisonOperatorNode)var10;
               ValueNode var36 = var33.getLeftOperand();
               ValueNode var38 = var33.getRightOperand();
               if (var36 instanceof ColumnReference && isConstantOrParameterNode(var38)) {
                  var4.addElement(var9);
               } else if (isConstantOrParameterNode(var36) && var38 instanceof ColumnReference) {
                  var8.setLeftOperand(var33.getSwappedEquivalent());
                  var4.addElement(var9);
               }
            }
         }
      }

      if (var3.size() != 0 && var4.size() != 0) {
         label142:
         for(int var27 = 0; var27 < var4.size(); ++var27) {
            DataValueDescriptor var31 = null;
            RelationalOperator var32 = (RelationalOperator)((Predicate)var4.elementAt(var27)).getAndNode().getLeftOperand();
            ColumnReference var29;
            if (var32 instanceof UnaryComparisonOperatorNode) {
               var29 = (ColumnReference)((UnaryComparisonOperatorNode)var32).getOperand();
            } else {
               var29 = (ColumnReference)((BinaryComparisonOperatorNode)var32).getLeftOperand();
               if (((BinaryComparisonOperatorNode)var32).getRightOperand() instanceof ConstantNode) {
                  ConstantNode var34 = (ConstantNode)((BinaryComparisonOperatorNode)var32).getRightOperand();
                  var31 = var34.getValue();
               } else {
                  var31 = null;
               }
            }

            int var35 = var29.getTableNumber();
            int var37 = var29.getColumnNumber();
            Iterator var39 = var3.iterator();

            while(true) {
               ColumnReference var18;
               Predicate var40;
               while(true) {
                  if (!var39.hasNext()) {
                     continue label142;
                  }

                  var40 = (Predicate)var39.next();
                  if (!var40.transitiveSearchClauseAdded(var32)) {
                     BinaryRelationalOperatorNode var41 = (BinaryRelationalOperatorNode)var40.getAndNode().getLeftOperand();
                     ColumnReference var16 = (ColumnReference)var41.getLeftOperand();
                     ColumnReference var17 = (ColumnReference)var41.getRightOperand();
                     if (var16.getTableNumber() == var35 && var16.getColumnNumber() == var37) {
                        var18 = var17;
                        break;
                     }

                     if (var17.getTableNumber() == var35 && var17.getColumnNumber() == var37) {
                        var18 = var16;
                        break;
                     }
                  }
               }

               var40.setTransitiveSearchClauseAdded(var32);
               boolean var19 = false;

               for(Predicate var23 : var4) {
                  DataValueDescriptor var24 = null;
                  RelationalOperator var21 = (RelationalOperator)var23.getAndNode().getLeftOperand();
                  ColumnReference var20;
                  if (var21 instanceof UnaryComparisonOperatorNode) {
                     var20 = (ColumnReference)((UnaryComparisonOperatorNode)var21).getOperand();
                  } else {
                     var20 = (ColumnReference)((BinaryComparisonOperatorNode)var21).getLeftOperand();
                     if (((BinaryComparisonOperatorNode)var21).getRightOperand() instanceof ConstantNode) {
                        ConstantNode var25 = (ConstantNode)((BinaryComparisonOperatorNode)var21).getRightOperand();
                        var24 = var25.getValue();
                     } else {
                        var24 = null;
                     }
                  }

                  if (var20.getTableNumber() == var18.getTableNumber() && var20.getColumnNumber() == var18.getColumnNumber() && (var24 != null && var31 != null && var24.compare(var31) == 0 || var24 == null && var31 == null) && var21.getOperator() == var32.getOperator() && var21.getClass().getName().equals(var32.getClass().getName())) {
                     var19 = true;
                     break;
                  }
               }

               if (!var19) {
                  OperatorNode var42 = (OperatorNode)var32.getTransitiveSearchClause((ColumnReference)var18.getClone());
                  if (var42 instanceof BinaryComparisonOperatorNode) {
                     ((BinaryComparisonOperatorNode)var42).bindComparisonOperator();
                  } else {
                     ((UnaryComparisonOperatorNode)var42).bindComparisonOperator();
                  }

                  BooleanConstantNode var43 = new BooleanConstantNode(true, this.getContextManager());
                  AndNode var44 = new AndNode(var42, var43, this.getContextManager());
                  var44.postBindFixup();
                  JBitSet var45 = new JBitSet(var1);
                  var44.categorize(var45, false);
                  Predicate var26 = new Predicate(var44, var45, this.getContextManager());
                  this.addPredicate(var26);
                  var4.addElement(var26);
               }
            }
         }

         if (!var2) {
            for(int var28 = this.size() - 1; var28 >= 0; --var28) {
               Predicate var30 = (Predicate)this.elementAt(var28);
               if (var30.transitiveSearchClauseAdded(var5)) {
                  this.removeElementAt(var28);
               }
            }

         }
      }
   }

   void removeRedundantPredicates() {
      int var1 = this.size() - 1;

      while(var1 >= 0) {
         Predicate var2 = (Predicate)this.elementAt(var1);
         int var3 = var2.getEquivalenceClass();
         if (var3 == -1) {
            --var1;
         } else {
            for(int var4 = var1 - 1; var4 >= 0; --var4) {
               Predicate var5 = (Predicate)this.elementAt(var4);
               if (var5.getEquivalenceClass() == var3) {
                  if (var5.isStartKey()) {
                     var2.markStartKey();
                  }

                  if (var5.isStopKey()) {
                     var2.markStopKey();
                  }

                  if ((var5.isStartKey() || var5.isStopKey()) && var5.isQualifier() && !var2.isQualifier()) {
                     var2.markQualifier();
                     ++this.numberOfQualifiers;
                  }

                  if (var5.isQualifier()) {
                     --this.numberOfQualifiers;
                  }

                  this.removeElementAt(var4);
                  --var1;
               }
            }

            --var1;
         }
      }

   }

   public void transferPredicates(OptimizablePredicateList var1, JBitSet var2, Optimizable var3) throws StandardException {
      PredicateList var5 = (PredicateList)var1;

      for(int var6 = this.size() - 1; var6 >= 0; --var6) {
         Predicate var4 = (Predicate)this.elementAt(var6);
         if (var2.contains(var4.getReferencedSet())) {
            if (var4.isStartKey()) {
               --this.numberOfStartPredicates;
            }

            if (var4.isStopKey()) {
               --this.numberOfStopPredicates;
            }

            if (var4.isQualifier()) {
               --this.numberOfQualifiers;
            }

            var4.clearScanFlags();
            var5.addPredicate(var4);
            this.removeElementAt(var6);
         }
      }

      AccessPath var7 = var3.getTrulyTheBestAccessPath();
      var5.orderUsefulPredicates(var3, var7.getConglomerateDescriptor(), false, var7.getNonMatchingIndexScan(), var7.getCoveringIndexScan());
      var5.countScanFlags();
   }

   public void transferAllPredicates(OptimizablePredicateList var1) throws StandardException {
      PredicateList var2 = (PredicateList)var1;

      for(Predicate var4 : this) {
         var4.clearScanFlags();
         var2.addPredicate(var4);
      }

      this.removeAllElements();
      this.numberOfStartPredicates = 0;
      this.numberOfStopPredicates = 0;
      this.numberOfQualifiers = 0;
   }

   public void copyPredicatesToOtherList(OptimizablePredicateList var1) throws StandardException {
      for(int var2 = 0; var2 < this.size(); ++var2) {
         var1.addOptPredicate(this.getOptPredicate(var2));
      }

   }

   public boolean isRedundantPredicate(int var1) {
      Predicate var2 = (Predicate)this.elementAt(var1);
      if (var2.getEquivalenceClass() == -1) {
         return false;
      } else {
         for(int var3 = 0; var3 < var1; ++var3) {
            if (((Predicate)this.elementAt(var3)).getEquivalenceClass() == var2.getEquivalenceClass()) {
               return true;
            }
         }

         return false;
      }
   }

   public void setPredicatesAndProperties(OptimizablePredicateList var1) throws StandardException {
      PredicateList var2 = (PredicateList)var1;
      var2.removeAllElements();

      for(int var3 = 0; var3 < this.size(); ++var3) {
         var2.addOptPredicate(this.getOptPredicate(var3));
      }

      var2.numberOfStartPredicates = this.numberOfStartPredicates;
      var2.numberOfStopPredicates = this.numberOfStopPredicates;
      var2.numberOfQualifiers = this.numberOfQualifiers;
   }

   public int startOperator(Optimizable var1) {
      int var2 = -1;
      int var3 = this.size();

      for(int var4 = var3 - 1; var4 >= 0; --var4) {
         Predicate var5 = (Predicate)this.elementAt(var4);
         if (var5.isStartKey()) {
            var2 = var5.getStartOperator(var1);
            break;
         }
      }

      return var2;
   }

   public void generateStopKey(ExpressionClassBuilderInterface var1, MethodBuilder var2, Optimizable var3) throws StandardException {
      ExpressionClassBuilder var4 = (ExpressionClassBuilder)var1;
      if (this.numberOfStopPredicates != 0) {
         MethodBuilder var5 = var4.newExprFun();
         LocalField var6 = this.generateIndexableRow(var4, this.numberOfStopPredicates);
         int var7 = 0;

         for(Predicate var9 : this) {
            if (var9.isStopKey()) {
               this.generateSetColumn(var4, var5, var7, var9, var3, var6, false);
               ++var7;
            }
         }

         this.finishKey(var4, var2, var5, var6);
      } else {
         var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      }
   }

   public int stopOperator(Optimizable var1) {
      int var2 = -1;
      int var3 = this.size();

      for(int var4 = var3 - 1; var4 >= 0; --var4) {
         Predicate var5 = (Predicate)this.elementAt(var4);
         if (var5.isStopKey()) {
            var2 = var5.getStopOperator(var1);
            break;
         }
      }

      return var2;
   }

   private void generateSingleQualifierCode(MethodBuilder var1, Optimizable var2, boolean var3, ExpressionClassBuilder var4, RelationalOperator var5, LocalField var6, int var7, int var8) throws StandardException {
      var1.getField(var6);
      var1.pushThis();
      var1.callMethod((short)182, var4.getBaseClassName(), "getExecutionFactory", "org.apache.derby.iapi.sql.execute.ExecutionFactory", 0);
      if (var3) {
         var5.generateAbsoluteColumnId(var1, var2);
      } else {
         var5.generateRelativeColumnId(var1, var2);
      }

      var5.generateOperator(var1, var2);
      var5.generateQualMethod(var4, var1, var2);
      var4.pushThisAsActivation(var1);
      var5.generateOrderedNulls(var1);
      var5.generateNegate(var1, var2);
      var5.generateNegate(var1, var2);
      var1.push(var5.getOrderableVariantType(var2));
      var1.callMethod((short)185, "org.apache.derby.iapi.sql.execute.ExecutionFactory", "getQualifier", "org.apache.derby.iapi.store.access.Qualifier", 8);
      var1.push(var7);
      var1.push(var8);
      var1.callMethod((short)184, var4.getBaseClassName(), "setQualifier", "void", 4);
   }

   void generateInListValues(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      for(int var3 = this.size() - 1; var3 >= 0; --var3) {
         Predicate var4 = (Predicate)this.elementAt(var3);
         if (var4.isInListProbePredicate()) {
            this.removeOptPredicate(var4);
            InListOperatorNode var5 = var4.getSourceInList();
            var2.getField(var5.generateListAsArray(var1, var2));
            if (var5.sortDescending()) {
               var2.push((int)2);
            } else if (!var5.isOrdered()) {
               var2.push((int)1);
            } else {
               var2.push((int)3);
            }

            return;
         }
      }

   }

   public void generateQualifiers(ExpressionClassBuilderInterface var1, MethodBuilder var2, Optimizable var3, boolean var4) throws StandardException {
      String var5 = "org.apache.derby.iapi.store.access.Qualifier[][]";
      if (this.numberOfQualifiers == 0) {
         var2.pushNull(var5);
      } else {
         ExpressionClassBuilder var6 = (ExpressionClassBuilder)var1;
         MethodBuilder var7 = var6.getConstructor();
         MethodBuilder var8 = var6.getExecuteMethod();
         LocalField var9 = var6.newFieldDeclaration(2, var5);
         var8.getField(var9);
         var8.callMethod((short)184, var6.getBaseClassName(), "reinitializeQualifiers", "void", 1);
         int var10 = 0;

         for(int var11 = 0; var11 < this.numberOfQualifiers; ++var11) {
            if (((Predicate)this.elementAt(var11)).isOrList()) {
               ++var10;
            }
         }

         var7.pushNewArray("org.apache.derby.iapi.store.access.Qualifier[]", var10 + 1);
         var7.setField(var9);
         var7.getField(var9);
         var7.push((int)0);
         var7.push(this.numberOfQualifiers - var10);
         var7.callMethod((short)184, var6.getBaseClassName(), "allocateQualArray", "void", 3);
         this.orderQualifiers();
         int var20 = 0;
         int var12 = this.size();
         boolean var13 = false;

         for(int var14 = 0; var14 < var12; ++var14) {
            Predicate var15 = (Predicate)this.elementAt(var14);
            if (var15.isQualifier()) {
               if (var15.isOrList()) {
                  var13 = true;
                  break;
               }

               this.generateSingleQualifierCode(var7, var3, var4, var6, var15.getRelop(), var9, 0, var20);
               ++var20;
            }
         }

         if (var13) {
            int var21 = 1;

            for(int var22 = var20; var22 < var12; ++var21) {
               Predicate var16 = (Predicate)this.elementAt(var22);
               ArrayList var17 = new ArrayList();

               OrNode var19;
               for(ValueNode var18 = var16.getAndNode().getLeftOperand(); var18 instanceof OrNode; var18 = var19.getRightOperand()) {
                  var19 = (OrNode)var18;
                  if (var19.getLeftOperand() instanceof RelationalOperator) {
                     var17.add((RelationalOperator)var19.getLeftOperand());
                  }
               }

               var7.getField(var9);
               var7.push(var21);
               var7.push(var17.size());
               var7.callMethod((short)184, var6.getBaseClassName(), "allocateQualArray", "void", 3);

               for(int var23 = 0; var23 < var17.size(); ++var23) {
                  this.generateSingleQualifierCode(var7, var3, var4, var6, (RelationalOperator)var17.get(var23), var9, var21, var23);
               }

               ++var20;
               ++var22;
            }
         }

         var2.getField(var9);
      }
   }

   private void orderQualifiers() {
      PredicateList[] var1 = new PredicateList[5];

      for(int var2 = var1.length - 1; var2 >= 0; --var2) {
         var1[var2] = new PredicateList(this.getContextManager());
      }

      for(Predicate var4 : this) {
         if (!var4.isQualifier()) {
            var1[3].addElement(var4);
         } else {
            AndNode var5 = var4.getAndNode();
            if (!(var5.getLeftOperand() instanceof OrNode)) {
               RelationalOperator var6 = (RelationalOperator)var5.getLeftOperand();
               int var7 = var6.getOperator();
               switch (var7) {
                  case 1:
                  case 7:
                     var1[0].addElement(var4);
                     break;
                  case 2:
                  case 8:
                     var1[2].addElement(var4);
                     break;
                  case 3:
                  case 4:
                  case 5:
                  case 6:
                  default:
                     var1[1].addElement(var4);
               }
            } else {
               var1[4].addElement(var4);
            }
         }
      }

      int var8 = 0;

      for(int var9 = 0; var9 < 5; ++var9) {
         for(int var10 = 0; var10 < var1[var9].size(); ++var10) {
            this.setElementAt((Predicate)var1[var9].elementAt(var10), var8++);
         }
      }

   }

   public void generateStartKey(ExpressionClassBuilderInterface var1, MethodBuilder var2, Optimizable var3) throws StandardException {
      ExpressionClassBuilder var4 = (ExpressionClassBuilder)var1;
      if (this.numberOfStartPredicates != 0) {
         MethodBuilder var5 = var4.newExprFun();
         LocalField var6 = this.generateIndexableRow(var4, this.numberOfStartPredicates);
         int var7 = 0;

         for(Predicate var9 : this) {
            if (var9.isStartKey()) {
               this.generateSetColumn(var4, var5, var7, var9, var3, var6, true);
               ++var7;
            }
         }

         this.finishKey(var4, var2, var5, var6);
      } else {
         var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      }
   }

   public boolean sameStartStopPosition() throws StandardException {
      if (this.numberOfStartPredicates != this.numberOfStopPredicates) {
         return false;
      } else {
         for(Predicate var2 : this) {
            if (var2.isStartKey() && !var2.isStopKey() || var2.isStopKey() && !var2.isStartKey()) {
               return false;
            }

            if (var2.getAndNode().getLeftOperand() instanceof InListOperatorNode) {
               return false;
            }
         }

         return true;
      }
   }

   private LocalField generateIndexableRow(ExpressionClassBuilder var1, int var2) {
      MethodBuilder var3 = var1.getConstructor();
      var1.pushGetExecutionFactoryExpression(var3);
      var3.push(var2);
      var3.callMethod((short)185, "org.apache.derby.iapi.sql.execute.ExecutionFactory", "getIndexableRow", "org.apache.derby.iapi.sql.execute.ExecIndexRow", 1);
      LocalField var4 = var1.newFieldDeclaration(2, "org.apache.derby.iapi.sql.execute.ExecIndexRow");
      var3.setField(var4);
      return var4;
   }

   private void generateSetColumn(ExpressionClassBuilder var1, MethodBuilder var2, int var3, Predicate var4, Optimizable var5, LocalField var6, boolean var7) throws StandardException {
      boolean var9 = false;
      MethodBuilder var8;
      if (var4.compareWithKnownConstant(var5, false)) {
         var9 = true;
         var8 = var1.getConstructor();
      } else {
         var8 = var2;
      }

      int[] var10 = var5.getTrulyTheBestAccessPath().getConglomerateDescriptor().getIndexDescriptor().baseColumnPositions();
      boolean[] var11 = var5.getTrulyTheBestAccessPath().getConglomerateDescriptor().getIndexDescriptor().isAscending();
      boolean var12 = var4.getAndNode().getLeftOperand() instanceof InListOperatorNode;
      var8.getField(var6);
      var8.push(var3 + 1);
      if (var12) {
         var4.getSourceInList().generateStartStopKey(var11[var3], var7, var1, var8);
      } else {
         var4.generateExpressionOperand(var5, var10[var3], var1, var8);
      }

      var8.upCast("org.apache.derby.iapi.types.DataValueDescriptor");
      var8.callMethod((short)185, "org.apache.derby.iapi.sql.Row", "setColumn", "void", 2);
      if (!var12) {
         RelationalOperator var13 = var4.getRelop();
         boolean var14 = var13.orderedNulls();
         if (!var14 && !var13.getColumnOperand(var5).getTypeServices().isNullable()) {
            if (var9) {
               var14 = true;
            } else {
               ValueNode var15 = var13.getExpressionOperand(var5.getTableNumber(), var10[var3], (FromTable)var5);
               if (var15 instanceof ColumnReference) {
                  var14 = !((ColumnReference)var15).getTypeServices().isNullable();
               }
            }
         }

         if (var14) {
            var8.getField(var6);
            var8.push(var3);
            var8.callMethod((short)185, "org.apache.derby.iapi.sql.execute.ExecIndexRow", "orderedNulls", "void", 1);
         }
      }

   }

   private void finishKey(ExpressionClassBuilder var1, MethodBuilder var2, MethodBuilder var3, LocalField var4) {
      var3.getField(var4);
      var3.methodReturn();
      var3.complete();
      var1.pushMethodReference(var2, var3);
   }

   boolean constantColumn(ColumnReference var1) {
      boolean var2 = false;

      for(Predicate var4 : this) {
         RelationalOperator var5 = var4.getRelop();
         if (var4.isRelationalOpPredicate()) {
            if (var5.getOperator() == 1) {
               ValueNode var7 = var5.getOperand(var1, var4.getReferencedSet().size(), true);
               if (var7 != null && var7.isConstantExpression()) {
                  var2 = true;
                  break;
               }
            } else if (var5.getOperator() == 7) {
               ColumnReference var6 = (ColumnReference)var5.getOperand(var1, var4.getReferencedSet().size(), false);
               if (var6 != null) {
                  var2 = true;
               }
            }
         }
      }

      return var2;
   }

   public void adjustForSortElimination(RequiredRowOrdering var1) throws StandardException {
      if (var1 != null) {
         OrderByList var2 = (OrderByList)var1;

         for(Predicate var4 : this) {
            if (var4.isInListProbePredicate()) {
               BinaryRelationalOperatorNode var5 = (BinaryRelationalOperatorNode)var4.getRelop();
               if (var2.requiresDescending((ColumnReference)var5.getLeftOperand(), var4.getReferencedSet().size())) {
                  var4.getSourceInList(true).markSortDescending();
               }
            }
         }

      }
   }

   public double selectivity(Optimizable var1) throws StandardException {
      TableDescriptor var2 = var1.getTableDescriptor();
      ConglomerateDescriptor[] var3 = var2.getConglomerateDescriptors();
      int var4 = this.size();
      int var5 = var3.length;
      if (var5 == 1) {
         return (double)-1.0F;
      } else if (var4 == 0) {
         return (double)-1.0F;
      } else {
         boolean var6 = true;
         PredicateList var7 = new PredicateList(this.getContextManager());

         for(int var8 = 0; var8 < var4; ++var8) {
            if (!this.isRedundantPredicate(var8)) {
               var7.addOptPredicate((OptimizablePredicate)this.elementAt(var8));
            }
         }

         int var17 = var7.size();
         PredicateWrapperList[] var9 = new PredicateWrapperList[var5];

         for(int var10 = 0; var10 < var5; ++var10) {
            ConglomerateDescriptor var11 = var3[var10];
            if (var11.isIndex() && var2.statisticsExist(var11)) {
               int[] var12 = var11.getIndexDescriptor().baseColumnPositions();

               for(int var13 = 0; var13 < var17; ++var13) {
                  Predicate var14 = (Predicate)var7.elementAt(var13);
                  int var15 = var14.hasEqualOnColumnList(var12, var1);
                  if (var15 >= 0) {
                     var6 = false;
                     if (var9[var10] == null) {
                        var9[var10] = new PredicateWrapperList(var17);
                     }

                     PredicateWrapper var16 = new PredicateWrapper(var15, var14, var13);
                     var9[var10].insert(var16);
                  }
               }
            }
         }

         if (var6) {
            return (double)-1.0F;
         } else {
            boolean var18 = true;

            for(int var19 = 0; var19 < var5; ++var19) {
               if (var9[var19] != null) {
                  var9[var19].retainLeadingContiguous();
               }
            }

            this.calculateWeight(var9, var17);
            double var20 = (double)1.0F;
            ArrayList var21 = new ArrayList();

            do {
               var21.clear();
               int var22 = this.chooseLongestMatch(var9, var21, var17);
               if (var22 == -1) {
                  break;
               }

               var20 *= var2.selectivityForConglomerate(var3[var22], var21.size());

               for(int var23 = 0; var23 < var21.size(); ++var23) {
                  Predicate var24 = (Predicate)var21.get(var23);
                  var7.removeOptPredicate(var24);
               }
            } while(var7.size() != 0);

            if (var7.size() != 0) {
               var20 *= var7.selectivityNoStatistics(var1);
            }

            return var20;
         }
      }
   }

   private void calculateWeight(PredicateWrapperList[] var1, int var2) {
      int[] var3 = new int[var2];

      for(int var4 = 0; var4 < var1.length; ++var4) {
         if (var1[var4] != null) {
            for(int var5 = 0; var5 < var1[var4].size(); ++var5) {
               int var10001 = var1[var4].elementAt(var5).getPredicateID();
               var3[var10001] += var2 - var5;
            }
         }
      }

      for(int var7 = 0; var7 < var1.length; ++var7) {
         int var8 = 0;
         if (var1[var7] != null) {
            for(int var6 = 0; var6 < var1[var7].size(); ++var6) {
               var8 += var3[var1[var7].elementAt(var6).getPredicateID()];
            }

            var1[var7].setWeight(var8);
         }
      }

   }

   private int chooseLongestMatch(PredicateWrapperList[] var1, List var2, int var3) {
      int var4 = 0;
      int var5 = 0;
      int var6 = -1;

      for(int var7 = 0; var7 < var1.length; ++var7) {
         if (var1[var7] != null && var1[var7].uniqueSize() != 0) {
            if (var1[var7].uniqueSize() > var4) {
               var4 = var1[var7].uniqueSize();
               var6 = var7;
               var5 = var1[var7].getWeight();
            }

            if (var1[var7].uniqueSize() == var4 && var1[var7].getWeight() <= var5) {
               var6 = var7;
               var4 = var1[var7].uniqueSize();
               var5 = var1[var7].getWeight();
            }
         }
      }

      if (var6 == -1) {
         return -1;
      } else {
         PredicateWrapperList var12 = var1[var6];
         List var8 = var12.createLeadingUnique();

         for(int var9 = 0; var9 < var8.size(); ++var9) {
            Predicate var10 = ((PredicateWrapper)var8.get(var9)).getPredicate();
            var2.add(var10);

            for(int var11 = 0; var11 < var1.length; ++var11) {
               if (var1[var11] != null) {
                  var12 = var1[var11];
                  var12.removeElement(var10);
               }
            }
         }

         for(int var14 = 0; var14 < var1.length; ++var14) {
            if (var1[var14] != null) {
               var1[var14].retainLeadingContiguous();
            }
         }

         this.calculateWeight(var1, var3);
         return var6;
      }
   }

   private double selectivityNoStatistics(Optimizable var1) throws StandardException {
      double var2 = (double)1.0F;

      for(int var4 = 0; var4 < this.size(); ++var4) {
         OptimizablePredicate var5 = (OptimizablePredicate)this.elementAt(var4);
         var2 *= var5.selectivity(var1);
      }

      return var2;
   }

   private static class PredicateWrapper {
      int indexPosition;
      Predicate pred;
      int predicateID;

      PredicateWrapper(int var1, Predicate var2, int var3) {
         this.indexPosition = var1;
         this.pred = var2;
         this.predicateID = var3;
      }

      int getIndexPosition() {
         return this.indexPosition;
      }

      Predicate getPredicate() {
         return this.pred;
      }

      int getPredicateID() {
         return this.predicateID;
      }

      boolean before(PredicateWrapper var1) {
         return this.indexPosition < var1.getIndexPosition();
      }

      boolean contiguous(PredicateWrapper var1) {
         int var2 = var1.getIndexPosition();
         return this.indexPosition == var2 || this.indexPosition - var2 == 1 || this.indexPosition - var2 == -1;
      }
   }

   private static class PredicateWrapperList {
      private final ArrayList pwList;
      int numPreds;
      int numDuplicates;
      int weight;

      PredicateWrapperList(int var1) {
         this.pwList = new ArrayList(var1);
      }

      void removeElement(Predicate var1) {
         for(int var2 = this.numPreds - 1; var2 >= 0; --var2) {
            Predicate var3 = this.elementAt(var2).getPredicate();
            if (var3 == var1) {
               this.removeElementAt(var2);
            }
         }

      }

      void removeElementAt(int var1) {
         if (var1 < this.numPreds - 1) {
            PredicateWrapper var2 = this.elementAt(var1 + 1);
            if (var2.getIndexPosition() == var1) {
               --this.numDuplicates;
            }
         }

         this.pwList.remove(var1);
         --this.numPreds;
      }

      PredicateWrapper elementAt(int var1) {
         return (PredicateWrapper)this.pwList.get(var1);
      }

      void insert(PredicateWrapper var1) {
         int var2;
         for(var2 = 0; var2 < this.pwList.size(); ++var2) {
            if (var1.getIndexPosition() == this.elementAt(var2).getIndexPosition()) {
               ++this.numDuplicates;
            }

            if (var1.before(this.elementAt(var2))) {
               break;
            }
         }

         ++this.numPreds;
         this.pwList.add(var2, var1);
      }

      int size() {
         return this.numPreds;
      }

      int uniqueSize() {
         return this.numPreds > 0 ? this.numPreds - this.numDuplicates : 0;
      }

      void retainLeadingContiguous() {
         if (!this.pwList.isEmpty()) {
            if (this.elementAt(0).getIndexPosition() != 0) {
               this.pwList.clear();
               this.numPreds = this.numDuplicates = 0;
            } else {
               int var1;
               for(var1 = 0; var1 < this.numPreds - 1 && this.elementAt(var1).contiguous(this.elementAt(var1 + 1)); ++var1) {
               }

               for(int var2 = this.numPreds - 1; var2 > var1; --var2) {
                  if (this.elementAt(var2).getIndexPosition() == this.elementAt(var2 - 1).getIndexPosition()) {
                     --this.numDuplicates;
                  }

                  this.pwList.remove(var2);
               }

               this.numPreds = var1 + 1;
            }
         }
      }

      private List createLeadingUnique() {
         if (this.numPreds == 0) {
            return null;
         } else {
            int var1 = this.elementAt(0).getIndexPosition();
            if (var1 != 0) {
               return null;
            } else {
               ArrayList var2 = new ArrayList();
               var2.add(this.elementAt(0));

               for(int var3 = 1; var3 < this.numPreds; ++var3) {
                  if (this.elementAt(var3).getIndexPosition() != var1) {
                     var1 = this.elementAt(var3).getIndexPosition();
                     var2.add(this.elementAt(var3));
                  }
               }

               return var2;
            }
         }
      }

      void setWeight(int var1) {
         this.weight = var1;
      }

      int getWeight() {
         return this.weight;
      }
   }
}
