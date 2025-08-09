package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Tuple3$ implements Serializable {
   public static final Tuple3$ MODULE$ = new Tuple3$();

   public final String toString() {
      return "Tuple3";
   }

   public Tuple3 apply(final Object _1, final Object _2, final Object _3) {
      return new Tuple3(_1, _2, _3);
   }

   public Option unapply(final Tuple3 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple3(x$0._1(), x$0._2(), x$0._3())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Tuple3$.class);
   }

   private Tuple3$() {
   }
}
