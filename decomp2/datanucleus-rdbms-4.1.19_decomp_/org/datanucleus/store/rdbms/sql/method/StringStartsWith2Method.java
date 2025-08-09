package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.CharacterExpression;
import org.datanucleus.store.rdbms.sql.expression.ExpressionUtils;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.ParameterLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.util.Localiser;

public class StringStartsWith2Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 2) {
         SQLExpression one = ExpressionUtils.getLiteralForOne(this.stmt);
         ArrayList funcArgs = new ArrayList();
         SQLExpression substrExpr = (SQLExpression)args.get(0);
         if (!(substrExpr instanceof StringExpression) && !(substrExpr instanceof CharacterExpression) && !(substrExpr instanceof ParameterLiteral)) {
            throw new NucleusException(Localiser.msg("060003", new Object[]{"startsWith", "StringExpression", 0, "StringExpression/CharacterExpression/Parameter"}));
         } else if (args.size() == 2) {
            NumericExpression numExpr = (NumericExpression)args.get(1);
            funcArgs.add(substrExpr);
            funcArgs.add(expr);
            return new BooleanExpression(new StringExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "CHARINDEX", funcArgs), Expression.OP_EQ, one.add(numExpr));
         } else {
            funcArgs.add(substrExpr);
            funcArgs.add(expr);
            return new BooleanExpression(new StringExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "CHARINDEX", funcArgs), Expression.OP_EQ, one);
         }
      } else {
         throw new NucleusException(Localiser.msg("060003", new Object[]{"startsWith", "StringExpression", 0, "StringExpression/CharacterExpression/Parameter"}));
      }
   }
}
