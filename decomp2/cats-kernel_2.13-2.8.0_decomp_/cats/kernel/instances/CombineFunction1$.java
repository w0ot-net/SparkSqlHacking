package cats.kernel.instances;

import cats.kernel.Semigroup;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class CombineFunction1$ implements Serializable {
   public static final CombineFunction1$ MODULE$ = new CombineFunction1$();

   public final String toString() {
      return "CombineFunction1";
   }

   public CombineFunction1 apply(final Function1 left, final Function1 right, final Semigroup semiB) {
      return new CombineFunction1(left, right, semiB);
   }

   public Option unapply(final CombineFunction1 x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.left(), x$0.right(), x$0.semiB())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CombineFunction1$.class);
   }

   private CombineFunction1$() {
   }
}
