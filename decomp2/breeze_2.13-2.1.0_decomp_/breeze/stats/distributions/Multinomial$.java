package breeze.stats.distributions;

import breeze.generic.UFunc;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Multinomial$ implements Serializable {
   public static final Multinomial$ MODULE$ = new Multinomial$();

   public Multinomial apply(final Object params, final Function1 ev, final UFunc.UImpl sumImpl, final RandBasis rand) {
      return new Multinomial(params, ev, sumImpl, rand);
   }

   public Option unapply(final Multinomial x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.params()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Multinomial$.class);
   }

   private Multinomial$() {
   }
}
