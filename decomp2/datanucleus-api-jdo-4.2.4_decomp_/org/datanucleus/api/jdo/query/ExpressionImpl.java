package org.datanucleus.api.jdo.query;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.JDOException;
import javax.jdo.query.BooleanExpression;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.VariableExpression;

public class ExpressionImpl implements javax.jdo.query.Expression {
   protected Expression queryExpr;
   protected ExpressionType exprType;

   public ExpressionImpl(PersistableExpression parent, String name) {
      this.exprType = ExpressionType.PATH;
      List<String> tuples = new ArrayList();
      if (parent != null) {
         Expression parentQueryExpr = ((ExpressionImpl)parent).getQueryExpression();
         if (parentQueryExpr instanceof PrimaryExpression) {
            tuples.addAll(((PrimaryExpression)parentQueryExpr).getTuples());
            tuples.add(name);
            this.queryExpr = new PrimaryExpression(tuples);
         } else {
            tuples.add(name);
            this.queryExpr = new PrimaryExpression(parentQueryExpr, tuples);
         }
      } else {
         tuples.add(name);
         this.queryExpr = new PrimaryExpression(tuples);
      }

   }

   public ExpressionImpl(Class cls, String name, ExpressionType type) {
      this.exprType = ExpressionType.PATH;
      if (type != ExpressionType.PARAMETER && type != ExpressionType.VARIABLE) {
         throw new JDOException("Should not have called this constructor of ExpressionImpl!");
      } else {
         this.exprType = type;
         if (this.exprType == ExpressionType.PARAMETER) {
            this.queryExpr = new ParameterExpression(name, cls);
         } else if (this.exprType == ExpressionType.VARIABLE) {
            this.queryExpr = new VariableExpression(name, cls);
         }

      }
   }

   public ExpressionImpl(Expression queryExpr) {
      this.exprType = ExpressionType.PATH;
      this.queryExpr = queryExpr;
   }

   public Expression getQueryExpression() {
      return this.queryExpr;
   }

   public boolean isParameter() {
      return this.exprType == ExpressionType.PARAMETER;
   }

   public boolean isVariable() {
      return this.exprType == ExpressionType.VARIABLE;
   }

   public BooleanExpression eq(javax.jdo.query.Expression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_EQ, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression eq(Object t) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = new Literal(t);
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_EQ, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression ne(javax.jdo.query.Expression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_NOTEQ, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression ne(Object t) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = new Literal(t);
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_NOTEQ, rightQueryExpr);
      return new BooleanExpressionImpl(queryExpr);
   }

   public BooleanExpression instanceOf(Class cls) {
      throw new UnsupportedOperationException("instanceOf not yet supported");
   }

   public javax.jdo.query.Expression cast(Class cls) {
      throw new UnsupportedOperationException("cast not yet supported");
   }

   public NumericExpression count() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "count", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression countDistinct() {
      List<Expression> args = new ArrayList();
      args.add(new DyadicExpression(Expression.OP_DISTINCT, this.queryExpr));
      Expression invokeExpr = new InvokeExpression((Expression)null, "count", args);
      return new NumericExpressionImpl(invokeExpr);
   }
}
