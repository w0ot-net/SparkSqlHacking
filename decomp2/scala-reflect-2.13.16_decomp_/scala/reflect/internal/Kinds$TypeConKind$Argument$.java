package scala.reflect.internal;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;

public class Kinds$TypeConKind$Argument$ implements Serializable {
   // $FF: synthetic field
   private final Kinds.TypeConKind$ $outer;

   public final String toString() {
      return "Argument";
   }

   public Kinds$TypeConKind$Argument apply(final int variance, final Kinds.Kind kind, final Symbols.Symbol sym) {
      return new Kinds$TypeConKind$Argument(this.$outer, variance, kind, sym);
   }

   public Option unapply(final Kinds$TypeConKind$Argument x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(new Variance(x$0.variance()), x$0.kind())));
   }

   public Kinds$TypeConKind$Argument$(final Kinds.TypeConKind$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
