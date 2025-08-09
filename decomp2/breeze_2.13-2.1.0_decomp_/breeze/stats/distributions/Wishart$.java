package breeze.stats.distributions;

import breeze.linalg.DenseMatrix;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Wishart$ implements Serializable {
   public static final Wishart$ MODULE$ = new Wishart$();

   public final String toString() {
      return "Wishart";
   }

   public Wishart apply(final double df, final DenseMatrix scale, final RandBasis randBasis) {
      return new Wishart(df, scale, randBasis);
   }

   public Option unapply(final Wishart x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToDouble(x$0.df()), x$0.scale())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Wishart$.class);
   }

   private Wishart$() {
   }
}
