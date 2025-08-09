package org.apache.parquet.filter2.recordlevel;

import java.util.Objects;

public class IncrementallyUpdatedFilterPredicateEvaluator implements IncrementallyUpdatedFilterPredicate.Visitor {
   private static final IncrementallyUpdatedFilterPredicateEvaluator INSTANCE = new IncrementallyUpdatedFilterPredicateEvaluator();

   public static boolean evaluate(IncrementallyUpdatedFilterPredicate pred) {
      return ((IncrementallyUpdatedFilterPredicate)Objects.requireNonNull(pred, "pred cannot be null")).accept(INSTANCE);
   }

   private IncrementallyUpdatedFilterPredicateEvaluator() {
   }

   public boolean visit(IncrementallyUpdatedFilterPredicate.ValueInspector p) {
      if (!p.isKnown()) {
         p.updateNull();
      }

      return p.getResult();
   }

   public boolean visit(IncrementallyUpdatedFilterPredicate.And and) {
      return and.getLeft().accept(this) && and.getRight().accept(this);
   }

   public boolean visit(IncrementallyUpdatedFilterPredicate.Or or) {
      return or.getLeft().accept(this) || or.getRight().accept(this);
   }
}
