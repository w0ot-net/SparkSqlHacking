package org.datanucleus.api.jdo.query;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.query.BooleanExpression;
import javax.jdo.query.CharacterExpression;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.PersistableExpression;
import javax.jdo.query.StringExpression;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;

public class StringExpressionImpl extends ComparableExpressionImpl implements StringExpression {
   public StringExpressionImpl(PersistableExpression parent, String name) {
      super(parent, name);
   }

   public StringExpressionImpl(Expression queryExpr) {
      super(queryExpr);
   }

   public StringExpressionImpl(Class cls, String name, ExpressionType type) {
      super(cls, name, type);
   }

   public StringExpression add(javax.jdo.query.Expression expr) {
      Expression leftQueryExpr = this.queryExpr;
      Expression rightQueryExpr = ((ExpressionImpl)expr).getQueryExpression();
      Expression queryExpr = new DyadicExpression(leftQueryExpr, Expression.OP_ADD, rightQueryExpr);
      return new StringExpressionImpl(queryExpr);
   }

   public CharacterExpression charAt(int pos) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(pos));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "charAt", args);
      return new CharacterExpressionImpl(invokeExpr);
   }

   public CharacterExpression charAt(NumericExpression pos) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)pos).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "charAt", args);
      return new CharacterExpressionImpl(invokeExpr);
   }

   public BooleanExpression endsWith(String str) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(str));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "endsWith", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression endsWith(StringExpression expr) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)expr).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "endsWith", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression equalsIgnoreCase(String str) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(str));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "equalsIgnoreCase", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression equalsIgnoreCase(StringExpression expr) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)expr).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "equalsIgnoreCase", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public NumericExpression indexOf(String str, int pos) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(str));
      args.add(new Literal(pos));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "indexOf", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression indexOf(String str, NumericExpression pos) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(str));
      args.add(((ExpressionImpl)pos).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "indexOf", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression indexOf(String str) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(str));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "indexOf", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression indexOf(StringExpression expr, int pos) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)expr).getQueryExpression());
      args.add(new Literal(pos));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "indexOf", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression indexOf(StringExpression expr, NumericExpression pos) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)expr).getQueryExpression());
      args.add(((ExpressionImpl)pos).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "indexOf", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression indexOf(StringExpression expr) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)expr).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "indexOf", args);
      return new NumericExpressionImpl(invokeExpr);
   }

   public NumericExpression length() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "length", (List)null);
      return new NumericExpressionImpl(invokeExpr);
   }

   public BooleanExpression matches(StringExpression expr) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)expr).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "matches", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression matches(String str) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(str));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "matches", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression startsWith(String str) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(str));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "startsWith", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public BooleanExpression startsWith(StringExpression expr) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)expr).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "startsWith", args);
      return new BooleanExpressionImpl(invokeExpr);
   }

   public StringExpression substring(int startPos, int endPos) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(startPos));
      args.add(new Literal(endPos));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "substring", args);
      return new StringExpressionImpl(invokeExpr);
   }

   public StringExpression substring(int pos) {
      List<Expression> args = new ArrayList();
      args.add(new Literal(pos));
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "substring", args);
      return new StringExpressionImpl(invokeExpr);
   }

   public StringExpression substring(NumericExpression startPos, NumericExpression endPos) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)startPos).getQueryExpression());
      args.add(((ExpressionImpl)endPos).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "substring", args);
      return new StringExpressionImpl(invokeExpr);
   }

   public StringExpression substring(NumericExpression pos) {
      List<Expression> args = new ArrayList();
      args.add(((ExpressionImpl)pos).getQueryExpression());
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "substring", args);
      return new StringExpressionImpl(invokeExpr);
   }

   public StringExpression toLowerCase() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "toLowerCase", (List)null);
      return new StringExpressionImpl(invokeExpr);
   }

   public StringExpression toUpperCase() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "toUpperCase", (List)null);
      return new StringExpressionImpl(invokeExpr);
   }

   public StringExpression trim() {
      Expression invokeExpr = new InvokeExpression(this.queryExpr, "trim", (List)null);
      return new StringExpressionImpl(invokeExpr);
   }
}
