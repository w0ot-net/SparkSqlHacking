package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Tuple21$ implements Serializable {
   public static final Tuple21$ MODULE$ = new Tuple21$();

   public final String toString() {
      return "Tuple21";
   }

   public Tuple21 apply(final Object _1, final Object _2, final Object _3, final Object _4, final Object _5, final Object _6, final Object _7, final Object _8, final Object _9, final Object _10, final Object _11, final Object _12, final Object _13, final Object _14, final Object _15, final Object _16, final Object _17, final Object _18, final Object _19, final Object _20, final Object _21) {
      return new Tuple21(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
   }

   public Option unapply(final Tuple21 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple21(x$0._1(), x$0._2(), x$0._3(), x$0._4(), x$0._5(), x$0._6(), x$0._7(), x$0._8(), x$0._9(), x$0._10(), x$0._11(), x$0._12(), x$0._13(), x$0._14(), x$0._15(), x$0._16(), x$0._17(), x$0._18(), x$0._19(), x$0._20(), x$0._21())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Tuple21$.class);
   }

   private Tuple21$() {
   }
}
