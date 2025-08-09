package scala.collection.immutable;

import java.io.Serializable;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.runtime.ModuleSerializationProxy;

public final class $colon$colon$ implements Serializable {
   public static final $colon$colon$ MODULE$ = new $colon$colon$();

   public final String toString() {
      return "::";
   }

   public $colon$colon apply(final Object head, final List next) {
      return new $colon$colon(head, next);
   }

   public Option unapply(final $colon$colon x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2(x$0.head(), x$0.next())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy($colon$colon$.class);
   }

   private $colon$colon$() {
   }
}
