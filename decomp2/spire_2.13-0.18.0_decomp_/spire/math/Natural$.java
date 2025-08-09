package spire.math;

import algebra.ring.CommutativeRig;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.math.BigInt;
import scala.math.BigInt.;
import scala.runtime.ModuleSerializationProxy;

public final class Natural$ implements NaturalInstances, Serializable {
   public static final Natural$ MODULE$ = new Natural$();
   private static final int denom;
   private static final Natural ten18;
   private static final Natural zero;
   private static final Natural one;
   private static CommutativeRig NaturalAlgebra;
   private static NumberTag NaturalTag;

   static {
      NaturalInstances.$init$(MODULE$);
      denom = UInt$.MODULE$.apply(1000000000);
      ten18 = MODULE$.apply(1000000000000000000L);
      zero = MODULE$.apply(0L);
      one = MODULE$.apply(1L);
   }

   public final CommutativeRig NaturalAlgebra() {
      return NaturalAlgebra;
   }

   public final NumberTag NaturalTag() {
      return NaturalTag;
   }

   public final void spire$math$NaturalInstances$_setter_$NaturalAlgebra_$eq(final CommutativeRig x$1) {
      NaturalAlgebra = x$1;
   }

   public final void spire$math$NaturalInstances$_setter_$NaturalTag_$eq(final NumberTag x$1) {
      NaturalTag = x$1;
   }

   public final int denom() {
      return denom;
   }

   public BigInt naturalToBigInt(final Natural n) {
      return n.toBigInt();
   }

   public Natural apply(final Seq us) {
      if (us.isEmpty()) {
         throw new IllegalArgumentException("invalid arguments");
      } else {
         return (Natural)((IterableOnceOps)us.tail()).foldLeft(new Natural.End(((UInt)us.head()).signed()), (n, u) -> $anonfun$apply$1(n, ((UInt)u).signed()));
      }
   }

   public Natural apply(final long n) {
      return (Natural)((n & 4294967295L) == n ? new Natural.End(UInt$.MODULE$.apply((int)n)) : new Natural.Digit(UInt$.MODULE$.apply((int)n), new Natural.End(UInt$.MODULE$.apply((int)(n >> 32)))));
   }

   public Natural apply(final BigInt n) {
      if (n.$less(.MODULE$.int2bigInt(0))) {
         throw new IllegalArgumentException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("negative numbers not allowed: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{n})));
      } else {
         return (Natural)(n.$less(.MODULE$.long2bigInt(4294967295L)) ? new Natural.End(UInt$.MODULE$.apply(n.toLong())) : new Natural.Digit(UInt$.MODULE$.apply(n.$amp(.MODULE$.long2bigInt(4294967295L)).toLong()), this.apply(n.$greater$greater(32))));
      }
   }

   private Natural ten18() {
      return ten18;
   }

   public Natural apply(final String s) {
      return this.parse$1(this.apply(0L), s, this.apply(1L));
   }

   public Natural zero() {
      return zero;
   }

   public Natural one() {
      return one;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Natural$.class);
   }

   // $FF: synthetic method
   public static final Natural.Digit $anonfun$apply$1(final Natural n, final int u) {
      return new Natural.Digit(u, n);
   }

   private final Natural parse$1(final Natural sofar, final String s, final Natural m) {
      while(s.length() > 18) {
         String p = s.substring(s.length() - 18, s.length());
         String r = s.substring(0, s.length() - 18);
         Natural var10000 = this.apply(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(p))).$times(m).$plus(sofar);
         m = m.$times(this.ten18());
         s = r;
         sofar = var10000;
      }

      return this.apply(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(s))).$times(m).$plus(sofar);
   }

   private Natural$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
