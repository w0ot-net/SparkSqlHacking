package org.datanucleus.store.rdbms.sql.method;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.ExpressionUtils;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;

public class StringMatchesDerbyMethod extends StringMatchesMethod {
   protected BooleanExpression getExpressionForStringExpressionInput(SQLExpression expr, SQLExpression argExpr, SQLExpression escapeExpr) {
      List funcArgs = new ArrayList();
      funcArgs.add(expr);
      funcArgs.add(argExpr);
      JavaTypeMapping m = this.exprFactory.getMappingForType(BigInteger.class, false);
      SQLExpression one = ExpressionUtils.getLiteralForOne(this.stmt);
      return (new NumericExpression(this.stmt, m, "NUCLEUS_MATCHES", funcArgs)).eq(one);
   }
}
