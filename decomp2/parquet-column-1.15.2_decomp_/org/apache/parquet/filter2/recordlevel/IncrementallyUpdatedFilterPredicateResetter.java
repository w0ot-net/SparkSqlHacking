package org.apache.parquet.filter2.recordlevel;

import java.util.Objects;

public final class IncrementallyUpdatedFilterPredicateResetter implements IncrementallyUpdatedFilterPredicate.Visitor {
   private static final IncrementallyUpdatedFilterPredicateResetter INSTANCE = new IncrementallyUpdatedFilterPredicateResetter();

   public static void reset(IncrementallyUpdatedFilterPredicate pred) {
      ((IncrementallyUpdatedFilterPredicate)Objects.requireNonNull(pred, "pred cannot be null")).accept(INSTANCE);
   }

   private IncrementallyUpdatedFilterPredicateResetter() {
   }

   public boolean visit(IncrementallyUpdatedFilterPredicate.ValueInspector p) {
      p.reset();
      return false;
   }

   public boolean visit(IncrementallyUpdatedFilterPredicate.And and) {
      and.getLeft().accept(this);
      and.getRight().accept(this);
      return false;
   }

   public boolean visit(IncrementallyUpdatedFilterPredicate.Or or) {
      or.getLeft().accept(this);
      or.getRight().accept(this);
      return false;
   }
}
