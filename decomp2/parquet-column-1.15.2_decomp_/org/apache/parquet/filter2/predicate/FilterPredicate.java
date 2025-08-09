package org.apache.parquet.filter2.predicate;

public interface FilterPredicate {
   Object accept(Visitor var1);

   public interface Visitor {
      Object visit(Operators.Eq var1);

      Object visit(Operators.NotEq var1);

      Object visit(Operators.Lt var1);

      Object visit(Operators.LtEq var1);

      Object visit(Operators.Gt var1);

      Object visit(Operators.GtEq var1);

      default Object visit(Operators.In in) {
         throw new UnsupportedOperationException("visit in is not supported.");
      }

      default Object visit(Operators.NotIn notIn) {
         throw new UnsupportedOperationException("visit NotIn is not supported.");
      }

      default Object visit(Operators.Contains contains) {
         throw new UnsupportedOperationException("visit Contains is not supported.");
      }

      Object visit(Operators.And var1);

      Object visit(Operators.Or var1);

      Object visit(Operators.Not var1);

      Object visit(Operators.UserDefined var1);

      Object visit(Operators.LogicalNotUserDefined var1);
   }
}
