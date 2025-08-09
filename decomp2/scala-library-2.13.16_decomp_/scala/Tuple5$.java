package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Tuple5$ implements Serializable {
   public static final Tuple5$ MODULE$ = new Tuple5$();

   public final String toString() {
      return "Tuple5";
   }

   public Tuple5 apply(final Object _1, final Object _2, final Object _3, final Object _4, final Object _5) {
      return new Tuple5(_1, _2, _3, _4, _5);
   }

   public Option unapply(final Tuple5 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple5(x$0._1(), x$0._2(), x$0._3(), x$0._4(), x$0._5())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Tuple5$.class);
   }

   private Tuple5$() {
   }
}
