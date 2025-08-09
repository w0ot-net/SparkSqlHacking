package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.util.Localiser;

public class StringEqualsMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() == 1) {
         StringExpression strExpr1 = (StringExpression)expr;
         StringExpression strExpr2 = (StringExpression)args.get(0);
         return strExpr1.eq(strExpr2);
      } else {
         throw new NucleusException(Localiser.msg("060003", new Object[]{"endsWith", "StringExpression", 0, "StringExpression/CharacterExpression/ParameterLiteral"}));
      }
   }
}
