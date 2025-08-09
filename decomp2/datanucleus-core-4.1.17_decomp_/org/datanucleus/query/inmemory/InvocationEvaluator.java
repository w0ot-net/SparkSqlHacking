package org.datanucleus.query.inmemory;

import org.datanucleus.query.expression.InvokeExpression;

public interface InvocationEvaluator {
   Object evaluate(InvokeExpression var1, Object var2, InMemoryExpressionEvaluator var3);
}
