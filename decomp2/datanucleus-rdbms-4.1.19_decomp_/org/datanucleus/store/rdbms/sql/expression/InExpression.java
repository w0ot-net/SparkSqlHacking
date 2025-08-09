package org.datanucleus.store.rdbms.sql.expression;

public class InExpression extends BooleanExpression {
   boolean negated = false;
   SQLExpression expr;
   SQLExpression[] exprs;

   public InExpression(SQLExpression expr, SQLExpression[] exprs) {
      super(expr.getSQLStatement(), expr.getSQLStatement().getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false));
      this.expr = expr;
      this.exprs = exprs;
      this.setStatement();
   }

   public BooleanExpression not() {
      this.negated = !this.negated;
      this.setStatement();
      return this;
   }

   protected void setStatement() {
      this.st.clearStatement();
      this.st.append(this.expr);
      if (this.negated) {
         this.st.append(" NOT");
      }

      this.st.append(" IN(");

      for(int i = 0; i < this.exprs.length; ++i) {
         if (i > 0) {
            this.st.append(",");
         }

         this.st.append(this.exprs[i]);
      }

      this.st.append(")");
   }
}
