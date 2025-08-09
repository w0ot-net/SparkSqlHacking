package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.sql.expression.CaseNumericExpression;
import org.datanucleus.store.rdbms.sql.expression.CharacterExpression;
import org.datanucleus.store.rdbms.sql.expression.ExpressionUtils;
import org.datanucleus.store.rdbms.sql.expression.IntegerLiteral;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.ParameterLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.util.Localiser;

public class StringIndexOf5Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 2) {
         SQLExpression substrExpr = (SQLExpression)args.get(0);
         if (!(substrExpr instanceof StringExpression) && !(substrExpr instanceof CharacterExpression) && !(substrExpr instanceof ParameterLiteral)) {
            throw new NucleusException(Localiser.msg("060003", new Object[]{"indexOf", "StringExpression", 0, "StringExpression/CharacterExpression/ParameterLiteral"}));
         } else {
            ArrayList funcArgs = new ArrayList();
            if (args.size() == 1) {
               funcArgs.add(expr);
               funcArgs.add(substrExpr);
               SQLExpression oneExpr = ExpressionUtils.getLiteralForOne(this.stmt);
               NumericExpression locateExpr = new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "STRPOS", funcArgs);
               return new NumericExpression(locateExpr, Expression.OP_SUB, oneExpr);
            } else {
               SQLExpression fromExpr = (SQLExpression)args.get(1);
               if (!(fromExpr instanceof NumericExpression)) {
                  throw new NucleusException(Localiser.msg("060003", new Object[]{"indexOf", "StringExpression", 1, "NumericExpression"}));
               } else {
                  ArrayList substrArgs = new ArrayList(1);
                  substrArgs.add(fromExpr);
                  SQLExpression strExpr = this.exprFactory.invokeMethod(this.stmt, "java.lang.String", "substring", expr, substrArgs);
                  funcArgs.add(strExpr);
                  funcArgs.add(substrExpr);
                  NumericExpression locateExpr = new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "STRPOS", funcArgs);
                  SQLExpression[] whenExprs = new SQLExpression[1];
                  NumericExpression zeroExpr = new IntegerLiteral(this.stmt, this.exprFactory.getMappingForType(Integer.class, false), 0, (String)null);
                  whenExprs[0] = locateExpr.gt(zeroExpr);
                  SQLExpression[] actionExprs = new SQLExpression[1];
                  SQLExpression oneExpr = ExpressionUtils.getLiteralForOne(this.stmt);
                  NumericExpression posExpr1 = new NumericExpression(locateExpr, Expression.OP_SUB, oneExpr);
                  actionExprs[0] = new NumericExpression(posExpr1, Expression.OP_ADD, fromExpr);
                  SQLExpression elseExpr = new IntegerLiteral(this.stmt, this.exprFactory.getMappingForType(Integer.class, false), -1, (String)null);
                  return new CaseNumericExpression(whenExprs, actionExprs, elseExpr);
               }
            }
         }
      } else {
         throw new NucleusException(Localiser.msg("060003", new Object[]{"indexOf", "StringExpression", 0, "StringExpression/CharacterExpression/ParameterLiteral"}));
      }
   }
}
