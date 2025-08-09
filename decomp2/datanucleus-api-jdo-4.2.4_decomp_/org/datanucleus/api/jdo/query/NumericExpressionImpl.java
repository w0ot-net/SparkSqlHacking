package org.datanucleus.api.jdo.query;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.PersistableExpression;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;

public class NumericExpressionImpl extends ComparableExpressionImpl implements NumericExpression {
   public NumericExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public NumericExpressionImpl(Expression queryExpr) {
      super(queryExpr);
   }

   public NumericExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public NumericExpression add(javax.jdo.query.Expression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_ADD, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression add(Number num) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = new Literal(num);
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_ADD, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression mul(javax.jdo.query.Expression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_MUL, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression mul(Number num) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = new Literal(num);
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_MUL, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression sub(javax.jdo.query.Expression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_SUB, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression sub(Number num) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = new Literal(num);
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_SUB, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression div(javax.jdo.query.Expression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_DIV, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression div(Number num) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = new Literal(num);
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_DIV, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression mod(javax.jdo.query.Expression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_MOD, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression mod(Number num) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = new Literal(num);
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_MOD, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression avg() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "avg", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression sum() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "sum", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression abs() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "abs", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression sqrt() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "sqrt", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression acos() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "acos", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression asin() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "asin", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression atan() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "atan", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression cos() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "cos", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression sin() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "sin", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression tan() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "tan", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression exp() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "exp", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression log() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "log", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression ceil() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "ceil", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression floor() {
      List<Expression> args = new ArrayList();
      args.add(this.queryExpr);
      Expression invokeExpr = new InvokeExpression((Expression)null, "floor", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression neg() {
      Expression queryExpr = new DyadicExpression(Expression.OP_NEG, this.queryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression com() {
      Expression queryExpr = new DyadicExpression(Expression.OP_COM, this.queryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression bAnd(NumericExpression bitExpr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)bitExpr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_BIT_AND, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression bOr(NumericExpression bitExpr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)bitExpr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_BIT_OR, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }

   public NumericExpression bXor(NumericExpression bitExpr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)bitExpr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_BIT_XOR, rightQueryExpr);
      return new NumericExpressionImpl(queryExpr);
   }
}
