package breeze.linalg;

import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.java8.JFunction1;

public interface TensorLike$mcIF$sp extends TensorLike, QuasiTensor$mcIF$sp {
   // $FF: synthetic method
   static Object apply$(final TensorLike$mcIF$sp $this, final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return $this.apply(a, b, c, slice, canSlice);
   }

   default Object apply(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return this.apply$mcI$sp(a, b, c, slice, canSlice);
   }

   // $FF: synthetic method
   static Object apply$mcI$sp$(final TensorLike$mcIF$sp $this, final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return $this.apply$mcI$sp(a, b, c, slice, canSlice);
   }

   default Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return canSlice.apply(this.repr(), ((SeqOps)((SeqOps)slice.$plus$colon(BoxesRunTime.boxToInteger(c))).$plus$colon(BoxesRunTime.boxToInteger(b))).$plus$colon(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static Object mapPairs$(final TensorLike$mcIF$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs(f, bf);
   }

   default Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapPairs$mcIF$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapPairs$mcIF$sp$(final TensorLike$mcIF$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs$mcIF$sp(f, bf);
   }

   default Object mapPairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return bf.map(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapActivePairs$(final TensorLike$mcIF$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs(f, bf);
   }

   default Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapActivePairs$mcIF$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapActivePairs$mcIF$sp$(final TensorLike$mcIF$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs$mcIF$sp(f, bf);
   }

   default Object mapActivePairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return bf.mapActive(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapValues$(final TensorLike$mcIF$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues(f, bf);
   }

   default Object mapValues(final Function1 f, final CanMapValues bf) {
      return this.mapValues$mcF$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapValues$mcF$sp$(final TensorLike$mcIF$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues$mcF$sp(f, bf);
   }

   default Object mapValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return bf.map(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapActiveValues$(final TensorLike$mcIF$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues(f, bf);
   }

   default Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return this.mapActiveValues$mcF$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapActiveValues$mcF$sp$(final TensorLike$mcIF$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues$mcF$sp(f, bf);
   }

   default Object mapActiveValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return bf.mapActive(this.repr(), f);
   }

   // $FF: synthetic method
   static void foreachKey$(final TensorLike$mcIF$sp $this, final Function1 fn) {
      $this.foreachKey(fn);
   }

   default void foreachKey(final Function1 fn) {
      this.foreachKey$mcI$sp(fn);
   }

   // $FF: synthetic method
   static void foreachKey$mcI$sp$(final TensorLike$mcIF$sp $this, final Function1 fn) {
      $this.foreachKey$mcI$sp(fn);
   }

   default void foreachKey$mcI$sp(final Function1 fn) {
      this.keysIterator().foreach(fn);
   }

   // $FF: synthetic method
   static void foreachPair$(final TensorLike$mcIF$sp $this, final Function2 fn) {
      $this.foreachPair(fn);
   }

   default void foreachPair(final Function2 fn) {
      this.foreachPair$mcIF$sp(fn);
   }

   // $FF: synthetic method
   static void foreachPair$mcIF$sp$(final TensorLike$mcIF$sp $this, final Function2 fn) {
      $this.foreachPair$mcIF$sp(fn);
   }

   default void foreachPair$mcIF$sp(final Function2 fn) {
      this.foreachKey$mcI$sp((k) -> $anonfun$foreachPair$3(this, fn, BoxesRunTime.unboxToInt(k)));
   }

   // $FF: synthetic method
   static void foreachValue$(final TensorLike$mcIF$sp $this, final Function1 fn) {
      $this.foreachValue(fn);
   }

   default void foreachValue(final Function1 fn) {
      this.foreachValue$mcF$sp(fn);
   }

   // $FF: synthetic method
   static void foreachValue$mcF$sp$(final TensorLike$mcIF$sp $this, final Function1 fn) {
      $this.foreachValue$mcF$sp(fn);
   }

   default void foreachValue$mcF$sp(final Function1 fn) {
      this.foreachKey$mcI$sp((k) -> $anonfun$foreachValue$3(this, fn, BoxesRunTime.unboxToInt(k)));
   }

   // $FF: synthetic method
   static boolean forall$(final TensorLike$mcIF$sp $this, final Function2 fn) {
      return $this.forall(fn);
   }

   default boolean forall(final Function2 fn) {
      return this.forall$mcIF$sp(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcIF$sp$(final TensorLike$mcIF$sp $this, final Function2 fn) {
      return $this.forall$mcIF$sp(fn);
   }

   default boolean forall$mcIF$sp(final Function2 fn) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreachPair$mcIF$sp((k, v) -> {
            $anonfun$forall$5(fn, var2, BoxesRunTime.unboxToInt(k), BoxesRunTime.unboxToFloat(v));
            return BoxedUnit.UNIT;
         });
         var10000 = true;
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var10000 = var4.value$mcZ$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean forall$(final TensorLike$mcIF$sp $this, final Function1 fn) {
      return $this.forall(fn);
   }

   default boolean forall(final Function1 fn) {
      return this.forall$mcF$sp(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcF$sp$(final TensorLike$mcIF$sp $this, final Function1 fn) {
      return $this.forall$mcF$sp(fn);
   }

   default boolean forall$mcF$sp(final Function1 fn) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreachValue$mcF$sp((JFunction1.mcVF.sp)(v) -> {
            if (!fn.apply$mcZF$sp(v)) {
               throw new NonLocalReturnControl.mcZ.sp(var2, false);
            }
         });
         var10000 = true;
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var10000 = var4.value$mcZ$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static Object $anonfun$foreachPair$3(final TensorLike$mcIF$sp $this, final Function2 fn$9, final int k) {
      return fn$9.apply(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToFloat($this.apply$mcIF$sp(k)));
   }

   // $FF: synthetic method
   static Object $anonfun$foreachValue$3(final TensorLike$mcIF$sp $this, final Function1 fn$10, final int k) {
      return fn$10.apply(BoxesRunTime.boxToFloat($this.apply$mcIF$sp(k)));
   }

   // $FF: synthetic method
   static void $anonfun$forall$5(final Function2 fn$11, final Object nonLocalReturnKey1$3, final int k, final float v) {
      if (!BoxesRunTime.unboxToBoolean(fn$11.apply(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToFloat(v)))) {
         throw new NonLocalReturnControl.mcZ.sp(nonLocalReturnKey1$3, false);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
