package scala.reflect.internal;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;

public class Kinds$Kind$Text$ extends AbstractFunction1 implements Serializable {
   // $FF: synthetic field
   private final Kinds.Kind$ $outer;

   public final String toString() {
      return "Text";
   }

   public Kinds$Kind$Text apply(final String value) {
      return new Kinds$Kind$Text(this.$outer, value);
   }

   public Option unapply(final Kinds$Kind$Text x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.value()));
   }

   public Kinds$Kind$Text$(final Kinds.Kind$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
