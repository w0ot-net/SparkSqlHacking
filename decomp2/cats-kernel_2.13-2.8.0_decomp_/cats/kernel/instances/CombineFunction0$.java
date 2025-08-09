package cats.kernel.instances;

import cats.kernel.Semigroup;
import java.io.Serializable;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class CombineFunction0$ implements Serializable {
   public static final CombineFunction0$ MODULE$ = new CombineFunction0$();

   public final String toString() {
      return "CombineFunction0";
   }

   public CombineFunction0 apply(final Function0 left, final Function0 right, final Semigroup semiA) {
      return new CombineFunction0(left, right, semiA);
   }

   public Option unapply(final CombineFunction0 x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.left(), x$0.right(), x$0.semiA())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CombineFunction0$.class);
   }

   private CombineFunction0$() {
   }
}
