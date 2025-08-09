package org.apache.commons.math3.optim.linear;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.math3.optim.OptimizationData;

public class LinearConstraintSet implements OptimizationData {
   private final Set linearConstraints = new LinkedHashSet();

   public LinearConstraintSet(LinearConstraint... constraints) {
      for(LinearConstraint c : constraints) {
         this.linearConstraints.add(c);
      }

   }

   public LinearConstraintSet(Collection constraints) {
      this.linearConstraints.addAll(constraints);
   }

   public Collection getConstraints() {
      return Collections.unmodifiableSet(this.linearConstraints);
   }
}
