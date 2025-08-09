package scala.math;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;
import scala.Function1;
import scala.Function2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Equiv$ implements LowPriorityEquiv, Serializable {
   public static final Equiv$ MODULE$ = new Equiv$();

   static {
      Equiv$ var10000 = MODULE$;
   }

   /** @deprecated */
   public Equiv universalEquiv() {
      return LowPriorityEquiv.universalEquiv$(this);
   }

   public Equiv reference() {
      return (x$1, x$2) -> x$1 == x$2;
   }

   public Equiv universal() {
      return (x$3, x$4) -> BoxesRunTime.equals(x$3, x$4);
   }

   public Equiv fromComparator(final Comparator cmp) {
      return (x, y) -> cmp.compare(x, y) == 0;
   }

   public Equiv fromFunction(final Function2 cmp) {
      return (x, y) -> BoxesRunTime.unboxToBoolean(cmp.apply(x, y));
   }

   public Equiv by(final Function1 f, final Equiv evidence$1) {
      return (x, y) -> evidence$1.equiv(f.apply(x), f.apply(y));
   }

   public Equiv apply(final Equiv evidence$2) {
      return evidence$2;
   }

   private final int optionSeed() {
      return 43;
   }

   private final int iterableSeed() {
      return 47;
   }

   public Equiv Option(final Equiv eqv) {
      return new Equiv.OptionEquiv(eqv);
   }

   public Equiv Tuple2(final Equiv eqv1, final Equiv eqv2) {
      return new Equiv.Tuple2Equiv(eqv1, eqv2);
   }

   public Equiv Tuple3(final Equiv eqv1, final Equiv eqv2, final Equiv eqv3) {
      return new Equiv.Tuple3Equiv(eqv1, eqv2, eqv3);
   }

   public Equiv Tuple4(final Equiv eqv1, final Equiv eqv2, final Equiv eqv3, final Equiv eqv4) {
      return new Equiv.Tuple4Equiv(eqv1, eqv2, eqv3, eqv4);
   }

   public Equiv Tuple5(final Equiv eqv1, final Equiv eqv2, final Equiv eqv3, final Equiv eqv4, final Equiv eqv5) {
      return new Equiv.Tuple5Equiv(eqv1, eqv2, eqv3, eqv4, eqv5);
   }

   public Equiv Tuple6(final Equiv eqv1, final Equiv eqv2, final Equiv eqv3, final Equiv eqv4, final Equiv eqv5, final Equiv eqv6) {
      return new Equiv.Tuple6Equiv(eqv1, eqv2, eqv3, eqv4, eqv5, eqv6);
   }

   public Equiv Tuple7(final Equiv eqv1, final Equiv eqv2, final Equiv eqv3, final Equiv eqv4, final Equiv eqv5, final Equiv eqv6, final Equiv eqv7) {
      return new Equiv.Tuple7Equiv(eqv1, eqv2, eqv3, eqv4, eqv5, eqv6, eqv7);
   }

   public Equiv Tuple8(final Equiv eqv1, final Equiv eqv2, final Equiv eqv3, final Equiv eqv4, final Equiv eqv5, final Equiv eqv6, final Equiv eqv7, final Equiv eqv8) {
      return new Equiv.Tuple8Equiv(eqv1, eqv2, eqv3, eqv4, eqv5, eqv6, eqv7, eqv8);
   }

   public Equiv Tuple9(final Equiv eqv1, final Equiv eqv2, final Equiv eqv3, final Equiv eqv4, final Equiv eqv5, final Equiv eqv6, final Equiv eqv7, final Equiv eqv8, final Equiv eqv9) {
      return new Equiv.Tuple9Equiv(eqv1, eqv2, eqv3, eqv4, eqv5, eqv6, eqv7, eqv8, eqv9);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Equiv$.class);
   }

   private Equiv$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
