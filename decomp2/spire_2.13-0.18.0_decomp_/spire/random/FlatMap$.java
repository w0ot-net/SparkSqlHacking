package spire.random;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class FlatMap$ implements Serializable {
   public static final FlatMap$ MODULE$ = new FlatMap$();

   public final String toString() {
      return "FlatMap";
   }

   public FlatMap apply(final Op sub, final Function1 k) {
      return new FlatMap(sub, k);
   }

   public Option unapply(final FlatMap x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.sub(), x$0.k())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FlatMap$.class);
   }

   private FlatMap$() {
   }
}
