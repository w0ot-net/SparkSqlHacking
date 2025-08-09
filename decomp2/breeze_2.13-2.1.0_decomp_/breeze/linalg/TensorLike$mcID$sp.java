package breeze.linalg;

import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public interface TensorLike$mcID$sp extends TensorLike, QuasiTensor$mcID$sp {
   // $FF: synthetic method
   static Object apply$(final TensorLike$mcID$sp $this, final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return $this.apply(a, b, c, slice, canSlice);
   }

   default Object apply(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return this.apply$mcI$sp(a, b, c, slice, canSlice);
   }

   // $FF: synthetic method
   static Object apply$mcI$sp$(final TensorLike$mcID$sp $this, final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return $this.apply$mcI$sp(a, b, c, slice, canSlice);
   }

   default Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return canSlice.apply(this.repr(), ((SeqOps)((SeqOps)slice.$plus$colon(BoxesRunTime.boxToInteger(c))).$plus$colon(BoxesRunTime.boxToInteger(b))).$plus$colon(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static Object mapPairs$(final TensorLike$mcID$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs(f, bf);
   }

   default Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapPairs$mcID$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapPairs$mcID$sp$(final TensorLike$mcID$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs$mcID$sp(f, bf);
   }

   default Object mapPairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return bf.map(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapActivePairs$(final TensorLike$mcID$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs(f, bf);
   }

   default Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapActivePairs$mcID$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapActivePairs$mcID$sp$(final TensorLike$mcID$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs$mcID$sp(f, bf);
   }

   default Object mapActivePairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return bf.mapActive(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapValues$(final TensorLike$mcID$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues(f, bf);
   }

   default Object mapValues(final Function1 f, final CanMapValues bf) {
      return this.mapValues$mcD$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapValues$mcD$sp$(final TensorLike$mcID$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues$mcD$sp(f, bf);
   }

   default Object mapValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return bf.map(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapActiveValues$(final TensorLike$mcID$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues(f, bf);
   }

   default Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return this.mapActiveValues$mcD$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapActiveValues$mcD$sp$(final TensorLike$mcID$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues$mcD$sp(f, bf);
   }

   default Object mapActiveValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return bf.mapActive(this.repr(), f);
   }

   // $FF: synthetic method
   static void foreachKey$(final TensorLike$mcID$sp $this, final Function1 fn) {
      $this.foreachKey(fn);
   }

   default void foreachKey(final Function1 fn) {
      this.foreachKey$mcI$sp(fn);
   }

   // $FF: synthetic method
   static void foreachKey$mcI$sp$(final TensorLike$mcID$sp $this, final Function1 fn) {
      $this.foreachKey$mcI$sp(fn);
   }

   default void foreachKey$mcI$sp(final Function1 fn) {
      this.keysIterator().foreach(fn);
   }

   // $FF: synthetic method
   static void foreachPair$(final TensorLike$mcID$sp $this, final Function2 fn) {
      $this.foreachPair(fn);
   }

   default void foreachPair(final Function2 fn) {
      this.foreachPair$mcID$sp(fn);
   }

   // $FF: synthetic method
   static void foreachPair$mcID$sp$(final TensorLike$mcID$sp $this, final Function2 fn) {
      $this.foreachPair$mcID$sp(fn);
   }

   default void foreachPair$mcID$sp(final Function2 fn) {
      this.foreachKey$mcI$sp((k) -> $anonfun$foreachPair$2(this, fn, BoxesRunTime.unboxToInt(k)));
   }

   // $FF: synthetic method
   static void foreachValue$(final TensorLike$mcID$sp $this, final Function1 fn) {
      $this.foreachValue(fn);
   }

   default void foreachValue(final Function1 fn) {
      this.foreachValue$mcD$sp(fn);
   }

   // $FF: synthetic method
   static void foreachValue$mcD$sp$(final TensorLike$mcID$sp $this, final Function1 fn) {
      $this.foreachValue$mcD$sp(fn);
   }

   default void foreachValue$mcD$sp(final Function1 fn) {
      this.foreachKey$mcI$sp((k) -> $anonfun$foreachValue$2(this, fn, BoxesRunTime.unboxToInt(k)));
   }

   // $FF: synthetic method
   static boolean forall$(final TensorLike$mcID$sp $this, final Function2 fn) {
      return $this.forall(fn);
   }

   default boolean forall(final Function2 fn) {
      return this.forall$mcID$sp(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcID$sp$(final TensorLike$mcID$sp $this, final Function2 fn) {
      return $this.forall$mcID$sp(fn);
   }

   default boolean forall$mcID$sp(final Function2 fn) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreachPair$mcID$sp((JFunction2.mcVID.sp)(k, v) -> {
            if (!fn.apply$mcZID$sp(k, v)) {
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
   static boolean forall$(final TensorLike$mcID$sp $this, final Function1 fn) {
      return $this.forall(fn);
   }

   default boolean forall(final Function1 fn) {
      return this.forall$mcD$sp(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcD$sp$(final TensorLike$mcID$sp $this, final Function1 fn) {
      return $this.forall$mcD$sp(fn);
   }

   default boolean forall$mcD$sp(final Function1 fn) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreachValue$mcD$sp((JFunction1.mcVD.sp)(v) -> {
            if (!fn.apply$mcZD$sp(v)) {
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
   static Object $anonfun$foreachPair$2(final TensorLike$mcID$sp $this, final Function2 fn$5, final int k) {
      return fn$5.apply(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToDouble($this.apply$mcID$sp(k)));
   }

   // $FF: synthetic method
   static Object $anonfun$foreachValue$2(final TensorLike$mcID$sp $this, final Function1 fn$6, final int k) {
      return fn$6.apply(BoxesRunTime.boxToDouble($this.apply$mcID$sp(k)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
