package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public interface OptimizablePredicate {
   JBitSet getReferencedMap();

   boolean hasSubquery();

   boolean hasMethodCall();

   void markStartKey();

   boolean isStartKey();

   void markStopKey();

   boolean isStopKey();

   void markQualifier();

   boolean isQualifier();

   boolean compareWithKnownConstant(Optimizable var1, boolean var2);

   DataValueDescriptor getCompareValue(Optimizable var1) throws StandardException;

   boolean equalsComparisonWithConstantExpression(Optimizable var1);

   int hasEqualOnColumnList(int[] var1, Optimizable var2) throws StandardException;

   double selectivity(Optimizable var1) throws StandardException;

   int getIndexPosition();
}
