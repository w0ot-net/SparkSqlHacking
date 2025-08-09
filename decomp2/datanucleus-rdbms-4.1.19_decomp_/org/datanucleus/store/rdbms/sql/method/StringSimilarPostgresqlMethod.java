package org.datanucleus.store.rdbms.sql.method;

import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.adapter.BaseDatastoreAdapter;
import org.datanucleus.store.rdbms.sql.SQLText;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.CharacterLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;

public class StringSimilarPostgresqlMethod extends StringMatchesMethod {
   public static final Expression.DyadicOperator OP_SIMILAR_TO = new Expression.DyadicOperator("SIMILAR TO", 3, false);

   protected BooleanExpression getExpressionForStringExpressionInput(SQLExpression expr, SQLExpression regExpr, SQLExpression escapeExpr) {
      return this.getBooleanLikeExpression(expr, regExpr, escapeExpr);
   }

   protected BooleanExpression getBooleanLikeExpression(SQLExpression expr, SQLExpression regExpr, SQLExpression escapeExpr) {
      BooleanExpression similarToExpr = new BooleanExpression(this.stmt, this.exprFactory.getMappingForType(Boolean.TYPE, false));
      SQLText sql = similarToExpr.toSQLText();
      sql.clearStatement();
      if (OP_SIMILAR_TO.isHigherThanLeftSide(expr.getLowestOperator())) {
         sql.append("(").append(expr).append(")");
      } else {
         sql.append(expr);
      }

      sql.append(" SIMILAR TO ");
      if (OP_SIMILAR_TO.isHigherThanRightSide(regExpr.getLowestOperator())) {
         sql.append("(").append(regExpr).append(")");
      } else {
         sql.append(regExpr);
      }

      BaseDatastoreAdapter dba = (BaseDatastoreAdapter)this.stmt.getRDBMSManager().getDatastoreAdapter();
      if (escapeExpr != null) {
         if (escapeExpr instanceof CharacterLiteral) {
            String chr = "" + ((CharacterLiteral)escapeExpr).getValue();
            if (chr.equals(dba.getEscapeCharacter())) {
               sql.append(dba.getEscapePatternExpression());
            } else {
               sql.append(" ESCAPE " + escapeExpr);
            }
         } else {
            sql.append(" ESCAPE " + escapeExpr);
         }
      } else {
         sql.append(" " + dba.getEscapePatternExpression());
      }

      return similarToExpr;
   }
}
