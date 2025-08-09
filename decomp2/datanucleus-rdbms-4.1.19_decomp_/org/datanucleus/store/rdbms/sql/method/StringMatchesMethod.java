package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.adapter.BaseDatastoreAdapter;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.sql.SQLText;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.BooleanLiteral;
import org.datanucleus.store.rdbms.sql.expression.CharacterExpression;
import org.datanucleus.store.rdbms.sql.expression.CharacterLiteral;
import org.datanucleus.store.rdbms.sql.expression.ParameterLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLLiteral;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.RegularExpressionConverter;

public class StringMatchesMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() <= 2) {
         if (!(args.get(0) instanceof StringExpression) && !(args.get(0) instanceof ParameterLiteral)) {
            throw new NucleusException("Incorrect arguments for String.matches(StringExpression)");
         } else {
            SQLExpression likeExpr = (SQLExpression)args.get(0);
            if (!(likeExpr instanceof StringExpression) && !(likeExpr instanceof CharacterExpression) && !(likeExpr instanceof ParameterLiteral)) {
               throw new NucleusException(Localiser.msg("060003", new Object[]{"like/matches", "StringExpression", 0, "StringExpression/CharacterExpression/ParameterLiteral"}));
            } else {
               SQLExpression escapeExpr = null;
               if (args.size() > 1) {
                  escapeExpr = (SQLExpression)args.get(1);
               }

               if ((likeExpr instanceof StringLiteral || likeExpr instanceof ParameterLiteral) && likeExpr.isParameter()) {
                  this.stmt.getQueryGenerator().useParameterExpressionAsLiteral((SQLLiteral)likeExpr);
               }

               if (expr instanceof StringLiteral && likeExpr instanceof StringLiteral) {
                  String primary = (String)((StringLiteral)expr).getValue();
                  String pattern = (String)((StringLiteral)likeExpr).getValue();
                  return new BooleanLiteral(this.stmt, this.exprFactory.getMappingForType(Boolean.TYPE, false), primary.matches(pattern));
               } else if (expr instanceof StringLiteral) {
                  return this.getBooleanLikeExpression(expr, likeExpr, escapeExpr);
               } else if (expr instanceof StringExpression && likeExpr instanceof StringLiteral) {
                  String pattern = (String)((StringLiteral)likeExpr).getValue();
                  if (this.stmt.getQueryGenerator().getQueryLanguage().equalsIgnoreCase("JDOQL")) {
                     boolean caseSensitive = false;
                     if (pattern.startsWith("(?i)")) {
                        caseSensitive = true;
                        pattern = pattern.substring(4);
                     }

                     DatastoreAdapter dba = this.stmt.getDatastoreAdapter();
                     RegularExpressionConverter converter = new RegularExpressionConverter(dba.getPatternExpressionZeroMoreCharacters().charAt(0), dba.getPatternExpressionAnyCharacter().charAt(0), dba.getEscapeCharacter().charAt(0));
                     if (caseSensitive) {
                        SQLExpression patternExpr = this.exprFactory.newLiteral(this.stmt, likeExpr.getJavaTypeMapping(), converter.convert(pattern).toLowerCase());
                        return this.getBooleanLikeExpression(expr.invoke("toLowerCase", (List)null), patternExpr, escapeExpr);
                     } else {
                        SQLExpression patternExpr = this.exprFactory.newLiteral(this.stmt, likeExpr.getJavaTypeMapping(), converter.convert(pattern));
                        return this.getBooleanLikeExpression(expr, patternExpr, escapeExpr);
                     }
                  } else {
                     SQLExpression patternExpr = this.exprFactory.newLiteral(this.stmt, likeExpr.getJavaTypeMapping(), pattern);
                     return this.getBooleanLikeExpression(expr, patternExpr, escapeExpr);
                  }
               } else if (expr instanceof StringExpression) {
                  return this.getExpressionForStringExpressionInput(expr, likeExpr, escapeExpr);
               } else {
                  throw new NucleusException(Localiser.msg("060001", new Object[]{"matches", expr}));
               }
            }
         }
      } else {
         throw new NucleusException("Incorrect arguments for String.matches(StringExpression)");
      }
   }

   protected BooleanExpression getExpressionForStringExpressionInput(SQLExpression expr, SQLExpression regExpr, SQLExpression escapeExpr) {
      BooleanExpression likeExpr = this.getBooleanLikeExpression(expr, regExpr, escapeExpr);
      return likeExpr;
   }

   protected BooleanExpression getBooleanLikeExpression(SQLExpression expr, SQLExpression regExpr, SQLExpression escapeExpr) {
      BooleanExpression likeExpr = new BooleanExpression(this.stmt, this.exprFactory.getMappingForType(Boolean.TYPE, false));
      SQLText sql = likeExpr.toSQLText();
      sql.clearStatement();
      if (Expression.OP_LIKE.isHigherThanLeftSide(expr.getLowestOperator())) {
         sql.append("(").append(expr).append(")");
      } else {
         sql.append(expr);
      }

      sql.append(" LIKE ");
      if (Expression.OP_LIKE.isHigherThanRightSide(regExpr.getLowestOperator())) {
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

      return likeExpr;
   }
}
