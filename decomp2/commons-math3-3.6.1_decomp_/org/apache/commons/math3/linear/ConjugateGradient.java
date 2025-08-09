package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.util.IterationManager;

public class ConjugateGradient extends PreconditionedIterativeLinearSolver {
   public static final String OPERATOR = "operator";
   public static final String VECTOR = "vector";
   private boolean check;
   private final double delta;

   public ConjugateGradient(int maxIterations, double delta, boolean check) {
      super(maxIterations);
      this.delta = delta;
      this.check = check;
   }

   public ConjugateGradient(IterationManager manager, double delta, boolean check) throws NullArgumentException {
      super(manager);
      this.delta = delta;
      this.check = check;
   }

   public final boolean getCheck() {
      return this.check;
   }

   public RealVector solveInPlace(RealLinearOperator a, RealLinearOperator m, RealVector b, RealVector x0) throws NullArgumentException, NonPositiveDefiniteOperatorException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
      checkParameters(a, m, b, x0);
      IterationManager manager = this.getIterationManager();
      manager.resetIterationCount();
      double rmax = this.delta * b.getNorm();
      RealVector bro = RealVector.unmodifiableRealVector(b);
      manager.incrementIterationCount();
      RealVector x = x0;
      RealVector xro = RealVector.unmodifiableRealVector(x0);
      RealVector p = x0.copy();
      RealVector q = a.operate(p);
      RealVector r = b.combine((double)1.0F, (double)-1.0F, q);
      RealVector rro = RealVector.unmodifiableRealVector(r);
      double rnorm = r.getNorm();
      RealVector z;
      if (m == null) {
         z = r;
      } else {
         z = null;
      }

      IterativeLinearSolverEvent evt = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), xro, bro, rro, rnorm);
      manager.fireInitializationEvent(evt);
      if (rnorm <= rmax) {
         manager.fireTerminationEvent(evt);
         return x0;
      } else {
         double rhoPrev = (double)0.0F;

         do {
            manager.incrementIterationCount();
            IterativeLinearSolverEvent var28 = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), xro, bro, rro, rnorm);
            manager.fireIterationStartedEvent(var28);
            if (m != null) {
               z = m.operate(r);
            }

            double rhoNext = r.dotProduct(z);
            if (this.check && rhoNext <= (double)0.0F) {
               NonPositiveDefiniteOperatorException e = new NonPositiveDefiniteOperatorException();
               ExceptionContext context = e.getContext();
               context.setValue("operator", m);
               context.setValue("vector", r);
               throw e;
            }

            if (manager.getIterations() == 2) {
               p.setSubVector(0, z);
            } else {
               p.combineToSelf(rhoNext / rhoPrev, (double)1.0F, z);
            }

            q = a.operate(p);
            double pq = p.dotProduct(q);
            if (this.check && pq <= (double)0.0F) {
               NonPositiveDefiniteOperatorException e = new NonPositiveDefiniteOperatorException();
               ExceptionContext context = e.getContext();
               context.setValue("operator", a);
               context.setValue("vector", p);
               throw e;
            }

            double alpha = rhoNext / pq;
            x.combineToSelf((double)1.0F, alpha, p);
            r.combineToSelf((double)1.0F, -alpha, q);
            rhoPrev = rhoNext;
            rnorm = r.getNorm();
            var29 = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), xro, bro, rro, rnorm);
            manager.fireIterationPerformedEvent(var29);
         } while(!(rnorm <= rmax));

         manager.fireTerminationEvent(var29);
         return x;
      }
   }
}
