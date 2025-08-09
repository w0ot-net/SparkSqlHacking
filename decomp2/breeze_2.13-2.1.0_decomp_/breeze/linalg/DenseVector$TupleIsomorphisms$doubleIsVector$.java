package breeze.linalg;

import breeze.util.Isomorphism;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime.;

public class DenseVector$TupleIsomorphisms$doubleIsVector$ implements Isomorphism {
   public static final DenseVector$TupleIsomorphisms$doubleIsVector$ MODULE$ = new DenseVector$TupleIsomorphisms$doubleIsVector$();

   static {
      Isomorphism.$init$(MODULE$);
   }

   public Isomorphism reverse() {
      return Isomorphism.reverse$(this);
   }

   public DenseVector forward(final double t) {
      return (DenseVector)DenseVector$.MODULE$.apply(.MODULE$.wrapDoubleArray(new double[]{t}), scala.reflect.ClassTag..MODULE$.Double());
   }

   public double backward(final DenseVector t) {
      int left$macro$1 = t.size();
      int right$macro$2 = 1;
      if (left$macro$1 != 1) {
         throw new AssertionError((new StringBuilder(47)).append("assertion failed: ").append("t.size == 1 (").append(left$macro$1).append(" ").append("!=").append(" ").append(1).append(")").toString());
      } else {
         return t.apply$mcD$sp(0);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DenseVector$TupleIsomorphisms$doubleIsVector$.class);
   }
}
