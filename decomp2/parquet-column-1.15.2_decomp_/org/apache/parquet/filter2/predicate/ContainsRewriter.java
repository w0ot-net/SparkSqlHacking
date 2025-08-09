package org.apache.parquet.filter2.predicate;

import java.util.Objects;

public final class ContainsRewriter implements FilterPredicate.Visitor {
   private static final ContainsRewriter INSTANCE = new ContainsRewriter();

   public static FilterPredicate rewrite(FilterPredicate pred) {
      Objects.requireNonNull(pred, "pred cannot be null");
      return (FilterPredicate)pred.accept(INSTANCE);
   }

   private ContainsRewriter() {
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
      FilterPredicate left;
      if (and.getLeft() instanceof Operators.And) {
         left = this.visit((Operators.And)and.getLeft());
      } else if (and.getLeft() instanceof Operators.Or) {
         left = this.visit((Operators.Or)and.getLeft());
      } else if (and.getLeft() instanceof Operators.Contains) {
         left = and.getLeft();
      } else {
         left = and.getLeft();
      }

      FilterPredicate right;
      if (and.getRight() instanceof Operators.And) {
         right = this.visit((Operators.And)and.getRight());
      } else if (and.getRight() instanceof Operators.Or) {
         right = this.visit((Operators.Or)and.getRight());
      } else if (and.getRight() instanceof Operators.Contains) {
         right = and.getRight();
      } else {
         right = and.getRight();
      }

      if (left instanceof Operators.Contains && right instanceof Operators.Contains && ((Operators.Contains)left).getColumn().getColumnPath().equals(((Operators.Contains)right).getColumn().getColumnPath())) {
         return ((Operators.Contains)left).and(right);
      } else {
         return left == and.getLeft() && right == and.getRight() ? and : new Operators.And(left, right);
      }
   }

   public FilterPredicate visit(Operators.Or or) {
      FilterPredicate left;
      if (or.getLeft() instanceof Operators.And) {
         left = this.visit((Operators.And)or.getLeft());
      } else if (or.getLeft() instanceof Operators.Or) {
         left = this.visit((Operators.Or)or.getLeft());
      } else if (or.getLeft() instanceof Operators.Contains) {
         left = or.getLeft();
      } else {
         left = or.getLeft();
      }

      FilterPredicate right;
      if (or.getRight() instanceof Operators.And) {
         right = this.visit((Operators.And)or.getRight());
      } else if (or.getRight() instanceof Operators.Or) {
         right = this.visit((Operators.Or)or.getRight());
      } else if (or.getRight() instanceof Operators.Contains) {
         right = or.getRight();
      } else {
         right = or.getRight();
      }

      if (left instanceof Operators.Contains && right instanceof Operators.Contains && ((Operators.Contains)left).getColumn().getColumnPath().equals(((Operators.Contains)right).getColumn().getColumnPath())) {
         return ((Operators.Contains)left).or(right);
      } else {
         return left == or.getLeft() && right == or.getRight() ? or : new Operators.Or(left, right);
      }
   }

   public FilterPredicate visit(Operators.Not not) {
      throw new IllegalStateException("Not predicate should be rewritten before being evaluated by ContainsRewriter");
   }

   public FilterPredicate visit(Operators.UserDefined udp) {
      return udp;
   }

   public FilterPredicate visit(Operators.LogicalNotUserDefined udp) {
      return udp;
   }
}
