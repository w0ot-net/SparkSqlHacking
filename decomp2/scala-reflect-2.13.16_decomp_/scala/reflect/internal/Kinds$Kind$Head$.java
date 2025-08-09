package scala.reflect.internal;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;

public class Kinds$Kind$Head$ extends AbstractFunction3 implements Serializable {
   // $FF: synthetic field
   private final Kinds.Kind$ $outer;

   public final String toString() {
      return "Head";
   }

   public Kinds$Kind$Head apply(final int order, final Option n, final Option alias) {
      return new Kinds$Kind$Head(this.$outer, order, n, alias);
   }

   public Option unapply(final Kinds$Kind$Head x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.order(), x$0.n(), x$0.alias())));
   }

   public Kinds$Kind$Head$(final Kinds.Kind$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
