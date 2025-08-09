package org.datanucleus.store.rdbms.sql.method;

import java.lang.reflect.Array;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.ArrayLiteral;
import org.datanucleus.store.rdbms.sql.expression.BooleanLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.util.Localiser;

public class ArrayIsEmptyMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() > 0) {
         throw new NucleusException(Localiser.msg("060015", new Object[]{"isEmpty", "ArrayExpression"}));
      } else if (!(expr instanceof ArrayLiteral)) {
         SQLExpression sizeExpr = this.exprFactory.invokeMethod(this.stmt, "ARRAY", "size", expr, args);
         JavaTypeMapping mapping = this.exprFactory.getMappingForType(Integer.class, true);
         SQLExpression zeroExpr = this.exprFactory.newLiteral(this.stmt, mapping, 0);
         return sizeExpr.eq(zeroExpr);
      } else {
         Object arr = ((ArrayLiteral)expr).getValue();
         boolean isEmpty = arr == null || Array.getLength(arr) == 0;
         JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, false);
         return new BooleanLiteral(this.stmt, m, isEmpty ? Boolean.TRUE : Boolean.FALSE);
      }
   }
}
