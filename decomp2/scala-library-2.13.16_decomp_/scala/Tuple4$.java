package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Tuple4$ implements Serializable {
   public static final Tuple4$ MODULE$ = new Tuple4$();

   public final String toString() {
      return "Tuple4";
   }

   public Tuple4 apply(final Object _1, final Object _2, final Object _3, final Object _4) {
      return new Tuple4(_1, _2, _3, _4);
   }

   public Option unapply(final Tuple4 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple4(x$0._1(), x$0._2(), x$0._3(), x$0._4())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Tuple4$.class);
   }

   private Tuple4$() {
   }
}
