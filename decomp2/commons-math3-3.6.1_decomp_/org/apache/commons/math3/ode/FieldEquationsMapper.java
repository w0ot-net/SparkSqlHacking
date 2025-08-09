package org.apache.commons.math3.ode;

import java.io.Serializable;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class FieldEquationsMapper implements Serializable {
   private static final long serialVersionUID = 20151114L;
   private final int[] start;

   FieldEquationsMapper(FieldEquationsMapper mapper, int dimension) {
      int index = mapper == null ? 0 : mapper.getNumberOfEquations();
      this.start = new int[index + 2];
      if (mapper == null) {
         this.start[0] = 0;
      } else {
         System.arraycopy(mapper.start, 0, this.start, 0, index + 1);
      }

      this.start[index + 1] = this.start[index] + dimension;
   }

   public int getNumberOfEquations() {
      return this.start.length - 1;
   }

   public int getTotalDimension() {
      return this.start[this.start.length - 1];
   }

   public RealFieldElement[] mapState(FieldODEState state) {
      T[] y = (T[])((RealFieldElement[])MathArrays.buildArray(state.getTime().getField(), this.getTotalDimension()));
      int index = 0;
      this.insertEquationData(index, state.getState(), y);

      while(true) {
         ++index;
         if (index >= this.getNumberOfEquations()) {
            return y;
         }

         this.insertEquationData(index, state.getSecondaryState(index), y);
      }
   }

   public RealFieldElement[] mapDerivative(FieldODEStateAndDerivative state) {
      T[] yDot = (T[])((RealFieldElement[])MathArrays.buildArray(state.getTime().getField(), this.getTotalDimension()));
      int index = 0;
      this.insertEquationData(index, state.getDerivative(), yDot);

      while(true) {
         ++index;
         if (index >= this.getNumberOfEquations()) {
            return yDot;
         }

         this.insertEquationData(index, state.getSecondaryDerivative(index), yDot);
      }
   }

   public FieldODEStateAndDerivative mapStateAndDerivative(RealFieldElement t, RealFieldElement[] y, RealFieldElement[] yDot) throws DimensionMismatchException {
      if (y.length != this.getTotalDimension()) {
         throw new DimensionMismatchException(y.length, this.getTotalDimension());
      } else if (yDot.length != this.getTotalDimension()) {
         throw new DimensionMismatchException(yDot.length, this.getTotalDimension());
      } else {
         int n = this.getNumberOfEquations();
         int index = 0;
         T[] state = (T[])this.extractEquationData(index, y);
         T[] derivative = (T[])this.extractEquationData(index, yDot);
         if (n < 2) {
            return new FieldODEStateAndDerivative(t, state, derivative);
         } else {
            T[][] secondaryState = (T[][])((RealFieldElement[][])MathArrays.buildArray(t.getField(), n - 1, -1));
            T[][] secondaryDerivative = (T[][])((RealFieldElement[][])MathArrays.buildArray(t.getField(), n - 1, -1));

            while(true) {
               ++index;
               if (index >= this.getNumberOfEquations()) {
                  return new FieldODEStateAndDerivative(t, state, derivative, secondaryState, secondaryDerivative);
               }

               secondaryState[index - 1] = this.extractEquationData(index, y);
               secondaryDerivative[index - 1] = this.extractEquationData(index, yDot);
            }
         }
      }
   }

   public RealFieldElement[] extractEquationData(int index, RealFieldElement[] complete) throws MathIllegalArgumentException, DimensionMismatchException {
      this.checkIndex(index);
      int begin = this.start[index];
      int end = this.start[index + 1];
      if (complete.length < end) {
         throw new DimensionMismatchException(complete.length, end);
      } else {
         int dimension = end - begin;
         T[] equationData = (T[])((RealFieldElement[])MathArrays.buildArray(complete[0].getField(), dimension));
         System.arraycopy(complete, begin, equationData, 0, dimension);
         return equationData;
      }
   }

   public void insertEquationData(int index, RealFieldElement[] equationData, RealFieldElement[] complete) throws DimensionMismatchException {
      this.checkIndex(index);
      int begin = this.start[index];
      int end = this.start[index + 1];
      int dimension = end - begin;
      if (complete.length < end) {
         throw new DimensionMismatchException(complete.length, end);
      } else if (equationData.length != dimension) {
         throw new DimensionMismatchException(equationData.length, dimension);
      } else {
         System.arraycopy(equationData, 0, complete, begin, dimension);
      }
   }

   private void checkIndex(int index) throws MathIllegalArgumentException {
      if (index < 0 || index > this.start.length - 2) {
         throw new MathIllegalArgumentException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN, new Object[]{index, 0, this.start.length - 2});
      }
   }
}
