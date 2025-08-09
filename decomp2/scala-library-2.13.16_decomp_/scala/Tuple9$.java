package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Tuple9$ implements Serializable {
   public static final Tuple9$ MODULE$ = new Tuple9$();

   public final String toString() {
      return "Tuple9";
   }

   public Tuple9 apply(final Object _1, final Object _2, final Object _3, final Object _4, final Object _5, final Object _6, final Object _7, final Object _8, final Object _9) {
      return new Tuple9(_1, _2, _3, _4, _5, _6, _7, _8, _9);
   }

   public Option unapply(final Tuple9 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple9(x$0._1(), x$0._2(), x$0._3(), x$0._4(), x$0._5(), x$0._6(), x$0._7(), x$0._8(), x$0._9())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Tuple9$.class);
   }

   private Tuple9$() {
   }
}
