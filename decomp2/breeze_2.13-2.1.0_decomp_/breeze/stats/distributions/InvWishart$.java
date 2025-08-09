package breeze.stats.distributions;

import breeze.linalg.DenseMatrix;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class InvWishart$ implements Serializable {
   public static final InvWishart$ MODULE$ = new InvWishart$();

   public final String toString() {
      return "InvWishart";
   }

   public InvWishart apply(final double df, final DenseMatrix scale, final RandBasis rand) {
      return new InvWishart(df, scale, rand);
   }

   public Option unapply(final InvWishart x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToDouble(x$0.df()), x$0.scale())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(InvWishart$.class);
   }

   private InvWishart$() {
   }
}
