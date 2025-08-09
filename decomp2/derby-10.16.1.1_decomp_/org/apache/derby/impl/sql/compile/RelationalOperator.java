package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.sql.compile.ExpressionClassBuilderInterface;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

interface RelationalOperator {
   int EQUALS_RELOP = 1;
   int NOT_EQUALS_RELOP = 2;
   int GREATER_THAN_RELOP = 3;
   int GREATER_EQUALS_RELOP = 4;
   int LESS_THAN_RELOP = 5;
   int LESS_EQUALS_RELOP = 6;
   int IS_NULL_RELOP = 7;
   int IS_NOT_NULL_RELOP = 8;

   ColumnReference getColumnOperand(Optimizable var1, int var2);

   ColumnReference getColumnOperand(Optimizable var1);

   ValueNode getOperand(ColumnReference var1, int var2, boolean var3);

   ValueNode getExpressionOperand(int var1, int var2, Optimizable var3);

   void generateExpressionOperand(Optimizable var1, int var2, ExpressionClassBuilderInterface var3, MethodBuilder var4) throws StandardException;

   boolean selfComparison(ColumnReference var1) throws StandardException;

   boolean usefulStartKey(Optimizable var1);

   boolean usefulStopKey(Optimizable var1);

   int getStartOperator(Optimizable var1);

   int getStopOperator(Optimizable var1);

   void generateAbsoluteColumnId(MethodBuilder var1, Optimizable var2);

   void generateRelativeColumnId(MethodBuilder var1, Optimizable var2);

   void generateOperator(MethodBuilder var1, Optimizable var2);

   void generateQualMethod(ExpressionClassBuilderInterface var1, MethodBuilder var2, Optimizable var3) throws StandardException;

   void generateOrderedNulls(MethodBuilder var1);

   void generateNegate(MethodBuilder var1, Optimizable var2);

   boolean orderedNulls();

   boolean isQualifier(Optimizable var1, boolean var2) throws StandardException;

   int getOperator();

   int getOrderableVariantType(Optimizable var1) throws StandardException;

   boolean compareWithKnownConstant(Optimizable var1, boolean var2);

   DataValueDescriptor getCompareValue(Optimizable var1) throws StandardException;

   boolean equalsComparisonWithConstantExpression(Optimizable var1);

   RelationalOperator getTransitiveSearchClause(ColumnReference var1) throws StandardException;
}
