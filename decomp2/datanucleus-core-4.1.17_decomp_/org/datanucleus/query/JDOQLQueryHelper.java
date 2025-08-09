package org.datanucleus.query;

import java.util.Iterator;
import java.util.List;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.VariableExpression;

public class JDOQLQueryHelper {
   static final String[] SINGLE_STRING_KEYWORDS = new String[]{"SELECT", "UNIQUE", "INTO", "FROM", "EXCLUDE", "SUBCLASSES", "WHERE", "VARIABLES", "PARAMETERS", "GROUP", "ORDER", "BY", "RANGE"};
   static final String[] SINGLE_STRING_KEYWORDS_LOWERCASE = new String[]{"select", "unique", "into", "from", "exclude", "subclasses", "where", "variables", "parameters", "group", "order", "by", "range"};

   public static boolean isKeyword(String name) {
      for(int i = 0; i < SINGLE_STRING_KEYWORDS.length; ++i) {
         if (name.equals(SINGLE_STRING_KEYWORDS[i]) || name.equals(SINGLE_STRING_KEYWORDS_LOWERCASE[i])) {
            return true;
         }
      }

      if (!name.equals("IMPORT") && !name.equals("import")) {
         return false;
      } else {
         return true;
      }
   }

   public static boolean isKeywordExtended(String name) {
      for(int i = 0; i < SINGLE_STRING_KEYWORDS.length; ++i) {
         if (name.equals(SINGLE_STRING_KEYWORDS[i]) || name.equals(SINGLE_STRING_KEYWORDS_LOWERCASE[i])) {
            return true;
         }
      }

      if (!name.equals("DELETE") && !name.equals("delete")) {
         if (!name.equals("UPDATE") && !name.equals("update")) {
            if (!name.equals("SET") && !name.equals("set")) {
               if (!name.equals("IMPORT") && !name.equals("import")) {
                  return false;
               } else {
                  return true;
               }
            } else {
               return true;
            }
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   public static boolean isValidJavaIdentifierForJDOQL(String s) {
      int len = s.length();
      if (len < 1) {
         return false;
      } else if (s.equals("this")) {
         return false;
      } else {
         char[] c = new char[len];
         s.getChars(0, len, c, 0);
         if (!Character.isJavaIdentifierStart(c[0])) {
            return false;
         } else {
            for(int i = 1; i < len; ++i) {
               if (!Character.isJavaIdentifierPart(c[i])) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public static String getJDOQLForExpression(Expression expr) {
      if (expr instanceof DyadicExpression) {
         DyadicExpression dyExpr = (DyadicExpression)expr;
         Expression left = dyExpr.getLeft();
         Expression right = dyExpr.getRight();
         StringBuilder str = new StringBuilder("(");
         if (dyExpr.getOperator() == Expression.OP_DISTINCT) {
            str.append("DISTINCT ");
         }

         if (left != null) {
            str.append(getJDOQLForExpression(left));
         }

         if (dyExpr.getOperator() == Expression.OP_AND) {
            str.append(" && ");
         } else if (dyExpr.getOperator() == Expression.OP_OR) {
            str.append(" || ");
         } else if (dyExpr.getOperator() == Expression.OP_BIT_AND) {
            str.append(" & ");
         } else if (dyExpr.getOperator() == Expression.OP_BIT_OR) {
            str.append(" | ");
         } else if (dyExpr.getOperator() == Expression.OP_BIT_XOR) {
            str.append(" ^ ");
         } else if (dyExpr.getOperator() == Expression.OP_ADD) {
            str.append(" + ");
         } else if (dyExpr.getOperator() == Expression.OP_SUB) {
            str.append(" - ");
         } else if (dyExpr.getOperator() == Expression.OP_MUL) {
            str.append(" * ");
         } else if (dyExpr.getOperator() == Expression.OP_DIV) {
            str.append(" / ");
         } else if (dyExpr.getOperator() == Expression.OP_EQ) {
            str.append(" == ");
         } else if (dyExpr.getOperator() == Expression.OP_GT) {
            str.append(" > ");
         } else if (dyExpr.getOperator() == Expression.OP_LT) {
            str.append(" < ");
         } else if (dyExpr.getOperator() == Expression.OP_GTEQ) {
            str.append(" >= ");
         } else if (dyExpr.getOperator() == Expression.OP_LTEQ) {
            str.append(" <= ");
         } else if (dyExpr.getOperator() == Expression.OP_NOTEQ) {
            str.append(" != ");
         } else if (dyExpr.getOperator() != Expression.OP_DISTINCT) {
            throw new UnsupportedOperationException("Dont currently support operator " + dyExpr.getOperator() + " in JDOQL conversion");
         }

         if (right != null) {
            str.append(getJDOQLForExpression(right));
         }

         str.append(")");
         return str.toString();
      } else if (expr instanceof PrimaryExpression) {
         PrimaryExpression primExpr = (PrimaryExpression)expr;
         return primExpr.getLeft() != null ? getJDOQLForExpression(primExpr.getLeft()) + "." + primExpr.getId() : primExpr.getId();
      } else if (expr instanceof ParameterExpression) {
         ParameterExpression paramExpr = (ParameterExpression)expr;
         return paramExpr.getId() != null ? ":" + paramExpr.getId() : "?" + paramExpr.getPosition();
      } else if (expr instanceof VariableExpression) {
         VariableExpression varExpr = (VariableExpression)expr;
         return varExpr.getId();
      } else if (expr instanceof InvokeExpression) {
         InvokeExpression invExpr = (InvokeExpression)expr;
         StringBuilder str = new StringBuilder();
         if (invExpr.getLeft() != null) {
            str.append(getJDOQLForExpression(invExpr.getLeft())).append(".");
         }

         str.append(invExpr.getOperation());
         str.append("(");
         List<Expression> args = invExpr.getArguments();
         if (args != null) {
            Iterator<Expression> iter = args.iterator();

            while(iter.hasNext()) {
               str.append(getJDOQLForExpression((Expression)iter.next()));
               if (iter.hasNext()) {
                  str.append(",");
               }
            }
         }

         str.append(")");
         return str.toString();
      } else if (expr instanceof Literal) {
         Literal litExpr = (Literal)expr;
         Object value = litExpr.getLiteral();
         if (!(value instanceof String) && !(value instanceof Character)) {
            if (value instanceof Boolean) {
               return (Boolean)value ? "TRUE" : "FALSE";
            } else {
               return litExpr.getLiteral() == null ? "null" : litExpr.getLiteral().toString();
            }
         } else {
            return "'" + value.toString() + "'";
         }
      } else if (expr instanceof VariableExpression) {
         VariableExpression varExpr = (VariableExpression)expr;
         return varExpr.getId();
      } else {
         throw new UnsupportedOperationException("Dont currently support " + expr.getClass().getName() + " in JDOQLHelper");
      }
   }
}
