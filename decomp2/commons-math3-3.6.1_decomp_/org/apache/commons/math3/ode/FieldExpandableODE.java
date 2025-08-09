package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.util.MathArrays;

public class FieldExpandableODE {
   private final FirstOrderFieldDifferentialEquations primary;
   private List components;
   private FieldEquationsMapper mapper;

   public FieldExpandableODE(FirstOrderFieldDifferentialEquations primary) {
      this.primary = primary;
      this.components = new ArrayList();
      this.mapper = new FieldEquationsMapper((FieldEquationsMapper)null, primary.getDimension());
   }

   public FieldEquationsMapper getMapper() {
      return this.mapper;
   }

   public int addSecondaryEquations(FieldSecondaryEquations secondary) {
      this.components.add(secondary);
      this.mapper = new FieldEquationsMapper(this.mapper, secondary.getDimension());
      return this.components.size();
   }

   public void init(RealFieldElement t0, RealFieldElement[] y0, RealFieldElement finalTime) {
      int index = 0;
      T[] primary0 = (T[])this.mapper.extractEquationData(index, y0);
      this.primary.init(t0, primary0, finalTime);

      while(true) {
         ++index;
         if (index >= this.mapper.getNumberOfEquations()) {
            return;
         }

         T[] secondary0 = (T[])this.mapper.extractEquationData(index, y0);
         ((FieldSecondaryEquations)this.components.get(index - 1)).init(t0, primary0, secondary0, finalTime);
      }
   }

   public RealFieldElement[] computeDerivatives(RealFieldElement t, RealFieldElement[] y) throws MaxCountExceededException, DimensionMismatchException {
      T[] yDot = (T[])((RealFieldElement[])MathArrays.buildArray(t.getField(), this.mapper.getTotalDimension()));
      int index = 0;
      T[] primaryState = (T[])this.mapper.extractEquationData(index, y);
      T[] primaryStateDot = (T[])this.primary.computeDerivatives(t, primaryState);
      this.mapper.insertEquationData(index, primaryStateDot, yDot);

      while(true) {
         ++index;
         if (index >= this.mapper.getNumberOfEquations()) {
            return yDot;
         }

         T[] componentState = (T[])this.mapper.extractEquationData(index, y);
         T[] componentStateDot = (T[])((FieldSecondaryEquations)this.components.get(index - 1)).computeDerivatives(t, primaryState, primaryStateDot, componentState);
         this.mapper.insertEquationData(index, componentStateDot, yDot);
      }
   }
}
