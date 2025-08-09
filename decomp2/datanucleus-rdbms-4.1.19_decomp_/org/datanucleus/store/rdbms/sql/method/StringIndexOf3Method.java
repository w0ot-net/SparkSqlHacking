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

public class StringIndexOf3Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 2) {
         SQLExpression one = ExpressionUtils.getLiteralForOne(this.stmt);
         ArrayList funcArgs = new ArrayList();
         funcArgs.add(expr);
         List funcArgs2 = new ArrayList();
         SQLExpression substrExpr = (SQLExpression)args.get(0);
         if (!(substrExpr instanceof StringExpression) && !(substrExpr instanceof CharacterExpression) && !(substrExpr instanceof ParameterLiteral)) {
            throw new NucleusException(Localiser.msg("060003", new Object[]{"indexOf", "StringExpression", 0, "StringExpression/CharacterExpression/ParameterLiteral"}));
         } else {
            funcArgs2.add(substrExpr);
            List types = new ArrayList();
            types.add("VARCHAR(4000)");
            funcArgs.add(new StringExpression(this.stmt, this.getMappingForClass(String.class), "CAST", funcArgs2, types));
            if (args.size() == 2) {
               SQLExpression fromExpr = (SQLExpression)args.get(1);
               if (!(fromExpr instanceof NumericExpression)) {
                  throw new NucleusException(Localiser.msg("060003", new Object[]{"indexOf", "StringExpression", 1, "NumericExpression"}));
               }

               types = new ArrayList();
               types.add("BIGINT");
               List funcArgs3 = new ArrayList();
               funcArgs3.add(new NumericExpression(fromExpr, Expression.OP_ADD, one));
               funcArgs.add(new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "CAST", funcArgs3, types));
            }

            NumericExpression locateExpr = new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "LOCATE", funcArgs);
            return (new NumericExpression(locateExpr, Expression.OP_SUB, one)).encloseInParentheses();
         }
      } else {
         throw new NucleusException(Localiser.msg("060003", new Object[]{"indexOf", "StringExpression", 0, "StringExpression/CharacterExpression/ParameterLiteral"}));
      }
   }
}
