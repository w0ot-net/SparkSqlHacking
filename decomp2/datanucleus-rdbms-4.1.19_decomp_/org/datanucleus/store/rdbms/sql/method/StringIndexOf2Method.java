package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.sql.expression.CharacterExpression;
import org.datanucleus.store.rdbms.sql.expression.ExpressionUtils;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.ParameterLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.util.Localiser;

public class StringIndexOf2Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 2) {
         SQLExpression substrExpr = (SQLExpression)args.get(0);
         if (!(substrExpr instanceof StringExpression) && !(substrExpr instanceof CharacterExpression) && !(substrExpr instanceof ParameterLiteral)) {
            throw new NucleusException(Localiser.msg("060003", new Object[]{"indexOf", "StringExpression", 0, "StringExpression/CharacterExpression/ParameterLiteral"}));
         } else {
            SQLExpression one = ExpressionUtils.getLiteralForOne(this.stmt);
            ArrayList funcArgs = new ArrayList();
            funcArgs.add(expr);
            funcArgs.add(substrExpr);
            if (args.size() == 2) {
               SQLExpression fromExpr = (SQLExpression)args.get(1);
               if (!(fromExpr instanceof NumericExpression)) {
                  throw new NucleusException(Localiser.msg("060003", new Object[]{"indexOf", "StringExpression", 1, "NumericExpression"}));
               }

               funcArgs.add(fromExpr.add(one));
            }

            NumericExpression locateExpr = new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "INSTR", funcArgs);
            return (new NumericExpression(locateExpr, Expression.OP_SUB, one)).encloseInParentheses();
         }
      } else {
         throw new NucleusException(Localiser.msg("060003", new Object[]{"indexOf", "StringExpression", 0, "StringExpression/CharacterExpression/ParameterLiteral"}));
      }
   }
}
