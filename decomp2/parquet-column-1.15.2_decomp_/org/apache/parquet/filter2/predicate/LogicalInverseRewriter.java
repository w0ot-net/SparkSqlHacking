package org.apache.parquet.filter2.predicate;

import java.util.Objects;

public final class LogicalInverseRewriter implements FilterPredicate.Visitor {
   private static final LogicalInverseRewriter INSTANCE = new LogicalInverseRewriter();

   public static FilterPredicate rewrite(FilterPredicate pred) {
      Objects.requireNonNull(pred, "pred cannot be null");
      return (FilterPredicate)pred.accept(INSTANCE);
   }

   private LogicalInverseRewriter() {
   }

   public FilterPredicate visit(Operators.Eq eq) {
      return eq;
   }

   public FilterPredicate visit(Operators.NotEq notEq) {
      return notEq;
   }

   public FilterPredicate visit(Operators.Lt lt) {
      return lt;
   }

   public FilterPredicate visit(Operators.LtEq ltEq) {
      return ltEq;
   }

   public FilterPredicate visit(Operators.Gt gt) {
      return gt;
   }

   public FilterPredicate visit(Operators.GtEq gtEq) {
      return gtEq;
   }

   public FilterPredicate visit(Operators.In in) {
      return in;
   }

   public FilterPredicate visit(Operators.NotIn notIn) {
      return notIn;
   }

   public FilterPredicate visit(Operators.Contains contains) {
      return contains;
   }

   public FilterPredicate visit(Operators.And and) {
      return FilterApi.and((FilterPredicate)and.getLeft().accept(this), (FilterPredicate)and.getRight().accept(this));
   }

   public FilterPredicate visit(Operators.Or or) {
      return FilterApi.or((FilterPredicate)or.getLeft().accept(this), (FilterPredicate)or.getRight().accept(this));
   }

   public FilterPredicate visit(Operators.Not not) {
      return LogicalInverter.invert((FilterPredicate)not.getPredicate().accept(this));
   }

   public FilterPredicate visit(Operators.UserDefined udp) {
      return udp;
   }

   public FilterPredicate visit(Operators.LogicalNotUserDefined udp) {
      return udp;
   }
}
