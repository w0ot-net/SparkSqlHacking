package scala.reflect.internal;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;

public class Kinds$Kind$StringState$ implements Serializable {
   // $FF: synthetic field
   private final Kinds.Kind$ $outer;

   public Kinds$Kind$StringState empty() {
      return new Kinds$Kind$StringState(this.$outer, .MODULE$);
   }

   public Kinds$Kind$StringState apply(final Seq tokens) {
      return new Kinds$Kind$StringState(this.$outer, tokens);
   }

   public Option unapply(final Kinds$Kind$StringState x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.tokens()));
   }

   public Kinds$Kind$StringState$(final Kinds.Kind$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
