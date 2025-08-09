package breeze.linalg;

import breeze.util.Isomorphism;
import scala.Tuple2;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime.;

public class DenseVector$TupleIsomorphisms$pdoubleIsVector$ implements Isomorphism {
   public static final DenseVector$TupleIsomorphisms$pdoubleIsVector$ MODULE$ = new DenseVector$TupleIsomorphisms$pdoubleIsVector$();

   static {
      Isomorphism.$init$(MODULE$);
   }

   public Isomorphism reverse() {
      return Isomorphism.reverse$(this);
   }

   public DenseVector forward(final Tuple2 t) {
      return (DenseVector)DenseVector$.MODULE$.apply(.MODULE$.wrapDoubleArray(new double[]{t._1$mcD$sp(), t._2$mcD$sp()}), scala.reflect.ClassTag..MODULE$.Double());
   }

   public Tuple2 backward(final DenseVector t) {
      int left$macro$1 = t.size();
      int right$macro$2 = 2;
      if (left$macro$1 != 2) {
         throw new AssertionError((new StringBuilder(47)).append("assertion failed: ").append("t.size == 2 (").append(left$macro$1).append(" ").append("!=").append(" ").append(2).append(")").toString());
      } else {
         return new Tuple2.mcDD.sp(t.apply$mcD$sp(0), t.apply$mcD$sp(1));
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DenseVector$TupleIsomorphisms$pdoubleIsVector$.class);
   }
}
