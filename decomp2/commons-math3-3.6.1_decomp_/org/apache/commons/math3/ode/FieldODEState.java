package org.apache.commons.math3.ode;

import [Lorg.apache.commons.math3.RealFieldElement;;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.util.MathArrays;

public class FieldODEState {
   private final RealFieldElement time;
   private final RealFieldElement[] state;
   private final RealFieldElement[][] secondaryState;

   public FieldODEState(RealFieldElement time, RealFieldElement[] state) {
      this(time, state, (RealFieldElement[][])null);
   }

   public FieldODEState(RealFieldElement time, RealFieldElement[] state, RealFieldElement[][] secondaryState) {
      this.time = time;
      this.state = (RealFieldElement[])((RealFieldElement;)state).clone();
      this.secondaryState = this.copy(time.getField(), secondaryState);
   }

   protected RealFieldElement[][] copy(Field field, RealFieldElement[][] original) {
      if (original == null) {
         return (RealFieldElement[][])null;
      } else {
         T[][] copied = (T[][])((RealFieldElement[][])MathArrays.buildArray(field, original.length, -1));

         for(int i = 0; i < original.length; ++i) {
            copied[i] = (RealFieldElement[])original[i].clone();
         }

         return copied;
      }
   }

   public RealFieldElement getTime() {
      return this.time;
   }

   public int getStateDimension() {
      return this.state.length;
   }

   public RealFieldElement[] getState() {
      return (RealFieldElement[])this.state.clone();
   }

   public int getNumberOfSecondaryStates() {
      return this.secondaryState == null ? 0 : this.secondaryState.length;
   }

   public int getSecondaryStateDimension(int index) {
      return index == 0 ? this.state.length : this.secondaryState[index - 1].length;
   }

   public RealFieldElement[] getSecondaryState(int index) {
      return index == 0 ? (RealFieldElement[])this.state.clone() : (RealFieldElement[])this.secondaryState[index - 1].clone();
   }
}
