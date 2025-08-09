package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Tuple15$ implements Serializable {
   public static final Tuple15$ MODULE$ = new Tuple15$();

   public final String toString() {
      return "Tuple15";
   }

   public Tuple15 apply(final Object _1, final Object _2, final Object _3, final Object _4, final Object _5, final Object _6, final Object _7, final Object _8, final Object _9, final Object _10, final Object _11, final Object _12, final Object _13, final Object _14, final Object _15) {
      return new Tuple15(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15);
   }

   public Option unapply(final Tuple15 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple15(x$0._1(), x$0._2(), x$0._3(), x$0._4(), x$0._5(), x$0._6(), x$0._7(), x$0._8(), x$0._9(), x$0._10(), x$0._11(), x$0._12(), x$0._13(), x$0._14(), x$0._15())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Tuple15$.class);
   }

   private Tuple15$() {
   }
}
