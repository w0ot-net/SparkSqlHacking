package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.sql.expression.CharacterExpression;
import org.datanucleus.store.rdbms.sql.expression.ParameterLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.util.Localiser;

public class StringConcat1Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() == 1) {
         SQLExpression otherExpr = (SQLExpression)args.get(0);
         if (!(otherExpr instanceof StringExpression) && !(otherExpr instanceof CharacterExpression) && !(otherExpr instanceof ParameterLiteral)) {
            throw new NucleusException(Localiser.msg("060003", new Object[]{"concat", "StringExpression", 0, "StringExpression/CharacterExpression/Parameter"}));
         } else {
            ArrayList funcArgs = new ArrayList();
            funcArgs.add(expr);
            funcArgs.add(otherExpr);
            return new StringExpression(expr, Expression.OP_CONCAT, otherExpr);
         }
      } else {
         throw new NucleusException(Localiser.msg("060003", new Object[]{"concat", "StringExpression", 0, "StringExpression/CharacterExpression/Parameter"}));
      }
   }
}
