package breeze.optimize;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class Tolerance$ extends AbstractFunction2 implements Serializable {
   public static final Tolerance$ MODULE$ = new Tolerance$();

   public double $lessinit$greater$default$1() {
      return 1.0E-5;
   }

   public double $lessinit$greater$default$2() {
      return 1.0E-6;
   }

   public final String toString() {
      return "Tolerance";
   }

   public Tolerance apply(final double fvalTolerance, final double gvalTolerance) {
      return new Tolerance(fvalTolerance, gvalTolerance);
   }

   public double apply$default$1() {
      return 1.0E-5;
   }

   public double apply$default$2() {
      return 1.0E-6;
   }

   public Option unapply(final Tolerance x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.fvalTolerance(), x$0.gvalTolerance())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Tolerance$.class);
   }

   private Tolerance$() {
   }
}
