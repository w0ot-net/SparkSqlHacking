package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Tuple1$ implements Serializable {
   public static final Tuple1$ MODULE$ = new Tuple1$();

   public final String toString() {
      return "Tuple1";
   }

   public Tuple1 apply(final Object _1) {
      return new Tuple1(_1);
   }

   public Option unapply(final Tuple1 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0._1()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Tuple1$.class);
   }

   public Tuple1 apply$mDc$sp(final double _1) {
      return new Tuple1$mcD$sp(_1);
   }

   public Tuple1 apply$mIc$sp(final int _1) {
      return new Tuple1$mcI$sp(_1);
   }

   public Tuple1 apply$mJc$sp(final long _1) {
      return new Tuple1$mcJ$sp(_1);
   }

   public Option unapply$mDc$sp(final Tuple1 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0._1$mcD$sp()));
   }

   public Option unapply$mIc$sp(final Tuple1 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0._1$mcI$sp()));
   }

   public Option unapply$mJc$sp(final Tuple1 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0._1$mcJ$sp()));
   }

   private Tuple1$() {
   }
}
