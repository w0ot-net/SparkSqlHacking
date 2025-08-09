package org.sparkproject.jpmml.model.visitors;

import java.util.List;
import java.util.ListIterator;
import org.sparkproject.dmg.pmml.CompoundPredicate;
import org.sparkproject.dmg.pmml.HasPredicate;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Predicate;
import org.sparkproject.dmg.pmml.VisitorAction;

public abstract class PredicateFilterer extends AbstractVisitor {
   public abstract Predicate filter(Predicate var1);

   public void filterAll(List predicates) {
      ListIterator<Predicate> it = predicates.listIterator();

      while(it.hasNext()) {
         it.set(this.filter((Predicate)it.next()));
      }

   }

   public VisitorAction visit(PMMLObject object) {
      if (object instanceof HasPredicate) {
         HasPredicate<?> hasPredicate = (HasPredicate)object;
         hasPredicate.setPredicate(this.filter(hasPredicate.getPredicate()));
      }

      return super.visit(object);
   }

   public VisitorAction visit(CompoundPredicate compoundPredicate) {
      if (compoundPredicate.hasPredicates()) {
         this.filterAll(compoundPredicate.getPredicates());
      }

      return super.visit(compoundPredicate);
   }
}
