package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Tuple12$ implements Serializable {
   public static final Tuple12$ MODULE$ = new Tuple12$();

   public final String toString() {
      return "Tuple12";
   }

   public Tuple12 apply(final Object _1, final Object _2, final Object _3, final Object _4, final Object _5, final Object _6, final Object _7, final Object _8, final Object _9, final Object _10, final Object _11, final Object _12) {
      return new Tuple12(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12);
   }

   public Option unapply(final Tuple12 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple12(x$0._1(), x$0._2(), x$0._3(), x$0._4(), x$0._5(), x$0._6(), x$0._7(), x$0._8(), x$0._9(), x$0._10(), x$0._11(), x$0._12())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Tuple12$.class);
   }

   private Tuple12$() {
   }
}
