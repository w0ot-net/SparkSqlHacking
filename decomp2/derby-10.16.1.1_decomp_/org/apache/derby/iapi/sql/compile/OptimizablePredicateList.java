package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public interface OptimizablePredicateList {
   int size();

   OptimizablePredicate getOptPredicate(int var1);

   void removeOptPredicate(int var1) throws StandardException;

   void addOptPredicate(OptimizablePredicate var1);

   boolean useful(Optimizable var1, ConglomerateDescriptor var2) throws StandardException;

   void pushUsefulPredicates(Optimizable var1) throws StandardException;

   void classify(Optimizable var1, ConglomerateDescriptor var2) throws StandardException;

   void markAllPredicatesQualifiers();

   int hasEqualityPredicateOnOrderedColumn(Optimizable var1, int var2, boolean var3) throws StandardException;

   boolean hasOptimizableEqualityPredicate(Optimizable var1, int var2, boolean var3) throws StandardException;

   boolean hasOptimizableEquijoin(Optimizable var1, int var2) throws StandardException;

   void putOptimizableEqualityPredicateFirst(Optimizable var1, int var2) throws StandardException;

   void transferPredicates(OptimizablePredicateList var1, JBitSet var2, Optimizable var3) throws StandardException;

   void transferAllPredicates(OptimizablePredicateList var1) throws StandardException;

   void copyPredicatesToOtherList(OptimizablePredicateList var1) throws StandardException;

   void setPredicatesAndProperties(OptimizablePredicateList var1) throws StandardException;

   boolean isRedundantPredicate(int var1);

   int startOperator(Optimizable var1);

   int stopOperator(Optimizable var1);

   void generateQualifiers(ExpressionClassBuilderInterface var1, MethodBuilder var2, Optimizable var3, boolean var4) throws StandardException;

   void generateStartKey(ExpressionClassBuilderInterface var1, MethodBuilder var2, Optimizable var3) throws StandardException;

   void generateStopKey(ExpressionClassBuilderInterface var1, MethodBuilder var2, Optimizable var3) throws StandardException;

   boolean sameStartStopPosition() throws StandardException;

   double selectivity(Optimizable var1) throws StandardException;

   void adjustForSortElimination(RequiredRowOrdering var1) throws StandardException;
}
