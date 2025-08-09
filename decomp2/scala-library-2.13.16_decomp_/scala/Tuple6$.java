package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Tuple6$ implements Serializable {
   public static final Tuple6$ MODULE$ = new Tuple6$();

   public final String toString() {
      return "Tuple6";
   }

   public Tuple6 apply(final Object _1, final Object _2, final Object _3, final Object _4, final Object _5, final Object _6) {
      return new Tuple6(_1, _2, _3, _4, _5, _6);
   }

   public Option unapply(final Tuple6 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple6(x$0._1(), x$0._2(), x$0._3(), x$0._4(), x$0._5(), x$0._6())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Tuple6$.class);
   }

   private Tuple6$() {
   }
}
