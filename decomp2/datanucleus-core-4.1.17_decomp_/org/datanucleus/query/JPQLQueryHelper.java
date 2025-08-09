package org.datanucleus.query;

import java.util.List;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.VariableExpression;

public class JPQLQueryHelper {
   static final String[] SINGLE_STRING_KEYWORDS = new String[]{"SELECT", "UPDATE", "DELETE", "FROM", "WHERE", "GROUP BY", "HAVING", "ORDER BY"};
   static final String[] RESERVED_IDENTIFIERS = new String[]{"SELECT", "FROM", "WHERE", "UPDATE", "DELETE", "JOIN", "OUTER", "INNER", "LEFT", "GROUP", "BY", "HAVING", "FETCH", "DISTINCT", "OBJECT", "NULL", "TRUE", "FALSE", "NOT", "AND", "OR", "BETWEEN", "LIKE", "IN", "AS", "UNKNOWN", "EMPTY", "MEMBER", "OF", "IS", "AVG", "MAX", "MIN", "SUM", "COUNT", "ORDER", "ASC", "DESC", "MOD", "UPPER", "LOWER", "TRIM", "POSITION", "CHARACTER_LENGTH", "CHAR_LENGTH", "BIT_LENGTH", "CURRENT_TIME", "CURRENT_DATE", "CURRENT_TIMESTAMP", "NEW", "EXISTS", "ALL", "ANY", "SOME"};

   public static boolean isKeyword(String name) {
      for(int i = 0; i < SINGLE_STRING_KEYWORDS.length; ++i) {
         if (name.equalsIgnoreCase(SINGLE_STRING_KEYWORDS[i])) {
            return true;
         }
      }

      return false;
   }

   public static boolean isReservedIdentifier(String name) {
      for(int i = 0; i < RESERVED_IDENTIFIERS.length; ++i) {
         if (name.equalsIgnoreCase(RESERVED_IDENTIFIERS[i])) {
            return true;
         }
      }

      return false;
   }

   public static String getJPQLForExpression(Expression expr) {
      if (expr instanceof DyadicExpression) {
         DyadicExpression dyExpr = (DyadicExpression)expr;
         Expression left = dyExpr.getLeft();
         Expression right = dyExpr.getRight();
         StringBuilder str = new StringBuilder("(");
         if (left != null) {
            str.append(getJPQLForExpression(left));
         }

         if (right == null || !(right instanceof Literal) || ((Literal)right).getLiteral() != null || dyExpr.getOperator() != Expression.OP_EQ && dyExpr.getOperator() != Expression.OP_NOTEQ) {
            if (dyExpr.getOperator() == Expression.OP_AND) {
               str.append(" AND ");
            } else if (dyExpr.getOperator() == Expression.OP_OR) {
               str.append(" OR ");
            } else if (dyExpr.getOperator() == Expression.OP_ADD) {
               str.append(" + ");
            } else if (dyExpr.getOperator() == Expression.OP_SUB) {
               str.append(" - ");
            } else if (dyExpr.getOperator() == Expression.OP_MUL) {
               str.append(" * ");
            } else if (dyExpr.getOperator() == Expression.OP_DIV) {
               str.append(" / ");
            } else if (dyExpr.getOperator() == Expression.OP_EQ) {
               str.append(" = ");
            } else if (dyExpr.getOperator() == Expression.OP_GT) {
               str.append(" > ");
            } else if (dyExpr.getOperator() == Expression.OP_LT) {
               str.append(" < ");
            } else if (dyExpr.getOperator() == Expression.OP_GTEQ) {
               str.append(" >= ");
            } else if (dyExpr.getOperator() == Expression.OP_LTEQ) {
               str.append(" <= ");
            } else {
               if (dyExpr.getOperator() != Expression.OP_NOTEQ) {
                  throw new UnsupportedOperationException("Dont currently support operator " + dyExpr.getOperator() + " in JPQL conversion");
               }

               str.append(" <> ");
            }

            if (right != null) {
               str.append(getJPQLForExpression(right));
            }
         } else {
            str.append(dyExpr.getOperator() == Expression.OP_EQ ? " IS NULL" : " IS NOT NULL");
         }

         str.append(")");
         return str.toString();
      } else if (expr instanceof PrimaryExpression) {
         PrimaryExpression primExpr = (PrimaryExpression)expr;
         return primExpr.getId();
      } else if (expr instanceof ParameterExpression) {
         ParameterExpression paramExpr = (ParameterExpression)expr;
         return paramExpr.getId() != null ? ":" + paramExpr.getId() : "?" + paramExpr.getPosition();
      } else if (expr instanceof InvokeExpression) {
         InvokeExpression invExpr = (InvokeExpression)expr;
         Expression invoked = invExpr.getLeft();
         List<Expression> args = invExpr.getArguments();
         String method = invExpr.getOperation();
         if (method.equalsIgnoreCase("CURRENT_DATE")) {
            return "CURRENT_DATE";
         } else if (method.equalsIgnoreCase("CURRENT_TIME")) {
            return "CURRENT_TIME";
         } else if (method.equalsIgnoreCase("CURRENT_TIMESTAMP")) {
            return "CURRENT_TIMESTAMP";
         } else if (method.equalsIgnoreCase("length")) {
            StringBuilder str = new StringBuilder("LENGTH(");
            str.append(getJPQLForExpression(invoked));
            if (args != null && !args.isEmpty()) {
               Expression firstExpr = (Expression)args.get(0);
               str.append(",").append(getJPQLForExpression(firstExpr));
               if (args.size() == 2) {
                  Expression secondExpr = (Expression)args.get(1);
                  str.append(",").append(getJPQLForExpression(secondExpr));
               }
            }

            str.append(")");
            return str.toString();
         } else if (method.equals("toLowerCase")) {
            return "LOWER(" + getJPQLForExpression(invoked) + ")";
         } else if (method.equals("toUpperCase")) {
            return "UPPER(" + getJPQLForExpression(invoked) + ")";
         } else if (method.equalsIgnoreCase("isEmpty")) {
            StringBuilder str = new StringBuilder();
            str.append(getJPQLForExpression(invoked));
            str.append(" IS EMPTY");
            return str.toString();
         } else if (method.equalsIgnoreCase("indexOf")) {
            StringBuilder str = new StringBuilder("LOCATE(");
            str.append(getJPQLForExpression(invoked));
            Expression firstExpr = (Expression)args.get(0);
            str.append(",").append(getJPQLForExpression(firstExpr));
            if (args.size() > 1) {
               Expression secondExpr = (Expression)args.get(1);
               str.append(",").append(getJPQLForExpression(secondExpr));
            }

            str.append(")");
            return str.toString();
         } else if (method.equalsIgnoreCase("substring")) {
            StringBuilder str = new StringBuilder("SUBSTRING(");
            str.append(getJPQLForExpression(invoked));
            Expression firstExpr = (Expression)args.get(0);
            str.append(",").append(getJPQLForExpression(firstExpr));
            if (args.size() > 1) {
               Expression secondExpr = (Expression)args.get(1);
               str.append(",").append(getJPQLForExpression(secondExpr));
            }

            str.append(")");
            return str.toString();
         } else if (method.equalsIgnoreCase("trim")) {
            StringBuilder str = new StringBuilder("TRIM(BOTH ");
            str.append(getJPQLForExpression(invoked));
            if (args.size() > 0) {
               Expression trimChrExpr = (Expression)args.get(0);
               str.append(getJPQLForExpression(trimChrExpr));
            }

            str.append(" FROM ");
            str.append(getJPQLForExpression(invoked));
            str.append(")");
            return str.toString();
         } else if (method.equalsIgnoreCase("trimLeft")) {
            StringBuilder str = new StringBuilder("TRIM(LEADING ");
            str.append(getJPQLForExpression(invoked));
            if (args.size() > 0) {
               Expression trimChrExpr = (Expression)args.get(0);
               str.append(getJPQLForExpression(trimChrExpr));
            }

            str.append(" FROM ");
            str.append(getJPQLForExpression(invoked));
            str.append(")");
            return str.toString();
         } else if (method.equalsIgnoreCase("trimRight")) {
            StringBuilder str = new StringBuilder("TRIM(TRAILING ");
            str.append(getJPQLForExpression(invoked));
            if (args.size() > 0) {
               Expression trimChrExpr = (Expression)args.get(0);
               str.append(getJPQLForExpression(trimChrExpr));
            }

            str.append(" FROM ");
            str.append(getJPQLForExpression(invoked));
            str.append(")");
            return str.toString();
         } else if (method.equalsIgnoreCase("matches")) {
            StringBuilder str = new StringBuilder();
            str.append(getJPQLForExpression(invoked));
            str.append(" LIKE ");
            Expression firstExpr = (Expression)args.get(0);
            str.append(getJPQLForExpression(firstExpr));
            if (args.size() > 1) {
               Expression secondExpr = (Expression)args.get(1);
               str.append(" ESCAPE ").append(getJPQLForExpression(secondExpr));
            }

            return str.toString();
         } else if (method.equalsIgnoreCase("contains")) {
            StringBuilder str = new StringBuilder();
            Expression firstExpr = (Expression)args.get(0);
            str.append(getJPQLForExpression(firstExpr));
            str.append(" MEMBER OF ");
            str.append(getJPQLForExpression(invoked));
            return str.toString();
         } else if (method.equalsIgnoreCase("COUNT")) {
            Expression argExpr = (Expression)args.get(0);
            if (argExpr instanceof DyadicExpression && ((DyadicExpression)argExpr).getOperator() == Expression.OP_DISTINCT) {
               DyadicExpression dyExpr = (DyadicExpression)argExpr;
               return "COUNT(DISTINCT " + getJPQLForExpression(dyExpr.getLeft()) + ")";
            } else {
               return "COUNT(" + getJPQLForExpression(argExpr) + ")";
            }
         } else if (method.equalsIgnoreCase("COALESCE")) {
            StringBuilder str = new StringBuilder("COALESCE(");

            for(int i = 0; i < args.size(); ++i) {
               Expression argExpr = (Expression)args.get(i);
               str.append(getJPQLForExpression(argExpr));
               if (i < args.size() - 1) {
                  str.append(",");
               }
            }

            str.append(")");
            return str.toString();
         } else if (method.equalsIgnoreCase("NULLIF")) {
            StringBuilder str = new StringBuilder("NULLIF(");

            for(int i = 0; i < args.size(); ++i) {
               Expression argExpr = (Expression)args.get(i);
               str.append(getJPQLForExpression(argExpr));
               if (i < args.size() - 1) {
                  str.append(",");
               }
            }

            str.append(")");
            return str.toString();
         } else if (method.equalsIgnoreCase("ABS")) {
            Expression argExpr = (Expression)args.get(0);
            return "ABS(" + getJPQLForExpression(argExpr) + ")";
         } else if (method.equalsIgnoreCase("AVG")) {
            Expression argExpr = (Expression)args.get(0);
            return "AVG(" + getJPQLForExpression(argExpr) + ")";
         } else if (method.equalsIgnoreCase("MAX")) {
            Expression argExpr = (Expression)args.get(0);
            return "MAX(" + getJPQLForExpression(argExpr) + ")";
         } else if (method.equalsIgnoreCase("MIN")) {
            Expression argExpr = (Expression)args.get(0);
            return "MIN(" + getJPQLForExpression(argExpr) + ")";
         } else if (method.equalsIgnoreCase("SQRT")) {
            Expression argExpr = (Expression)args.get(0);
            return "SQRT(" + getJPQLForExpression(argExpr) + ")";
         } else if (method.equalsIgnoreCase("SUM")) {
            Expression argExpr = (Expression)args.get(0);
            return "SUM(" + getJPQLForExpression(argExpr) + ")";
         } else {
            throw new UnsupportedOperationException("Dont currently support InvokeExpression (" + invExpr + ") conversion into JPQL");
         }
      } else if (expr instanceof Literal) {
         Literal litExpr = (Literal)expr;
         Object value = litExpr.getLiteral();
         if (!(value instanceof String) && !(value instanceof Character)) {
            if (value instanceof Boolean) {
               return (Boolean)value ? "TRUE" : "FALSE";
            } else {
               return litExpr.getLiteral().toString();
            }
         } else {
            return "'" + value.toString() + "'";
         }
      } else if (expr instanceof VariableExpression) {
         VariableExpression varExpr = (VariableExpression)expr;
         return varExpr.getId();
      } else {
         throw new UnsupportedOperationException("Dont currently support " + expr.getClass().getName() + " in JPQLQueryHelper");
      }
   }
}
