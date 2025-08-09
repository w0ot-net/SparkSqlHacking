package org.apache.parquet.filter2.predicate;

import java.util.Objects;

public final class LogicalInverter implements FilterPredicate.Visitor {
   private static final LogicalInverter INSTANCE = new LogicalInverter();

   public static FilterPredicate invert(FilterPredicate pred) {
      Objects.requireNonNull(pred, "pred cannot be null");
      return (FilterPredicate)pred.accept(INSTANCE);
   }

   private LogicalInverter() {
   }

   public FilterPredicate visit(Operators.Eq eq) {
      return new Operators.NotEq(eq.getColumn(), eq.getValue());
   }

   public FilterPredicate visit(Operators.NotEq notEq) {
      return new Operators.Eq(notEq.getColumn(), notEq.getValue());
   }

   public FilterPredicate visit(Operators.Lt lt) {
      return new Operators.GtEq(lt.getColumn(), lt.getValue());
   }

   public FilterPredicate visit(Operators.LtEq ltEq) {
      return new Operators.Gt(ltEq.getColumn(), ltEq.getValue());
   }

   public FilterPredicate visit(Operators.Gt gt) {
      return new Operators.LtEq(gt.getColumn(), gt.getValue());
   }

   public FilterPredicate visit(Operators.GtEq gtEq) {
      return new Operators.Lt(gtEq.getColumn(), gtEq.getValue());
   }

   public FilterPredicate visit(Operators.In in) {
      return new Operators.NotIn(in.getColumn(), in.getValues());
   }

   public FilterPredicate visit(Operators.NotIn notIn) {
      return new Operators.In(notIn.getColumn(), notIn.getValues());
   }

   public FilterPredicate visit(Operators.Contains contains) {
      return contains.not();
   }

   public FilterPredicate visit(Operators.And and) {
      return new Operators.Or((FilterPredicate)and.getLeft().accept(this), (FilterPredicate)and.getRight().accept(this));
   }

   public FilterPredicate visit(Operators.Or or) {
      return new Operators.And((FilterPredicate)or.getLeft().accept(this), (FilterPredicate)or.getRight().accept(this));
   }

   public FilterPredicate visit(Operators.Not not) {
      return not.getPredicate();
   }

   public FilterPredicate visit(Operators.UserDefined udp) {
      return new Operators.LogicalNotUserDefined(udp);
   }

   public FilterPredicate visit(Operators.LogicalNotUserDefined udp) {
      return udp.getUserDefined();
   }
}
