package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.CharacterExpression;
import org.datanucleus.store.rdbms.sql.expression.ExpressionUtils;
import org.datanucleus.store.rdbms.sql.expression.ParameterLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLLiteral;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;
import org.datanucleus.util.Localiser;

public class StringEndsWithMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 2) {
         SQLExpression substrExpr = (SQLExpression)args.get(0);
         if (!(substrExpr instanceof StringExpression) && !(substrExpr instanceof CharacterExpression) && !(substrExpr instanceof ParameterLiteral)) {
            throw new NucleusException(Localiser.msg("060003", new Object[]{"endsWith", "StringExpression", 0, "StringExpression/CharacterExpression/ParameterLiteral"}));
         } else if (args.size() > 1) {
            if (substrExpr.isParameter()) {
               SQLLiteral substrLit = (SQLLiteral)substrExpr;
               this.stmt.getQueryGenerator().useParameterExpressionAsLiteral(substrLit);
               if (substrLit.getValue() == null) {
                  return new BooleanExpression(expr, Expression.OP_LIKE, ExpressionUtils.getEscapedPatternExpression(substrExpr));
               }
            }

            SQLExpression likeSubstrExpr = new StringLiteral(this.stmt, expr.getJavaTypeMapping(), '%', (String)null);
            return new BooleanExpression(expr, Expression.OP_LIKE, likeSubstrExpr.add(ExpressionUtils.getEscapedPatternExpression(substrExpr)));
         } else {
            if (substrExpr.isParameter()) {
               SQLLiteral substrLit = (SQLLiteral)substrExpr;
               this.stmt.getQueryGenerator().useParameterExpressionAsLiteral(substrLit);
               if (substrLit.getValue() == null) {
                  return new BooleanExpression(expr, Expression.OP_LIKE, ExpressionUtils.getEscapedPatternExpression(substrExpr));
               }
            }

            SQLExpression likeSubstrExpr = new StringLiteral(this.stmt, expr.getJavaTypeMapping(), '%', (String)null);
            return new BooleanExpression(expr, Expression.OP_LIKE, likeSubstrExpr.add(ExpressionUtils.getEscapedPatternExpression(substrExpr)));
         }
      } else {
         throw new NucleusException(Localiser.msg("060003", new Object[]{"endsWith", "StringExpression", 0, "StringExpression/CharacterExpression/ParameterLiteral"}));
      }
   }
}
