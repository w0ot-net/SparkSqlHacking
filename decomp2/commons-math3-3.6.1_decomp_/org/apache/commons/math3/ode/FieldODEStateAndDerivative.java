package org.apache.commons.math3.ode;

import [Lorg.apache.commons.math3.RealFieldElement;;
import org.apache.commons.math3.RealFieldElement;

public class FieldODEStateAndDerivative extends FieldODEState {
   private final RealFieldElement[] derivative;
   private final RealFieldElement[][] secondaryDerivative;

   public FieldODEStateAndDerivative(RealFieldElement time, RealFieldElement[] state, RealFieldElement[] derivative) {
      this(time, state, derivative, (RealFieldElement[][])null, (RealFieldElement[][])null);
   }

   public FieldODEStateAndDerivative(RealFieldElement time, RealFieldElement[] state, RealFieldElement[] derivative, RealFieldElement[][] secondaryState, RealFieldElement[][] secondaryDerivative) {
      super(time, state, secondaryState);
      this.derivative = (RealFieldElement[])((RealFieldElement;)derivative).clone();
      this.secondaryDerivative = this.copy(time.getField(), secondaryDerivative);
   }

   public RealFieldElement[] getDerivative() {
      return (RealFieldElement[])this.derivative.clone();
   }

   public RealFieldElement[] getSecondaryDerivative(int index) {
      return index == 0 ? (RealFieldElement[])this.derivative.clone() : (RealFieldElement[])this.secondaryDerivative[index - 1].clone();
   }
}
