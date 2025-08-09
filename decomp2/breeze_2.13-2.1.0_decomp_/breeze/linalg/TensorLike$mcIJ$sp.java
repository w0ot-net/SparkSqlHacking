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

public interface TensorLike$mcIJ$sp extends TensorLike, QuasiTensor$mcIJ$sp {
   // $FF: synthetic method
   static Object apply$(final TensorLike$mcIJ$sp $this, final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return $this.apply(a, b, c, slice, canSlice);
   }

   default Object apply(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return this.apply$mcI$sp(a, b, c, slice, canSlice);
   }

   // $FF: synthetic method
   static Object apply$mcI$sp$(final TensorLike$mcIJ$sp $this, final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return $this.apply$mcI$sp(a, b, c, slice, canSlice);
   }

   default Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return canSlice.apply(this.repr(), ((SeqOps)((SeqOps)slice.$plus$colon(BoxesRunTime.boxToInteger(c))).$plus$colon(BoxesRunTime.boxToInteger(b))).$plus$colon(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static Object mapPairs$(final TensorLike$mcIJ$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs(f, bf);
   }

   default Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapPairs$mcIJ$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapPairs$mcIJ$sp$(final TensorLike$mcIJ$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs$mcIJ$sp(f, bf);
   }

   default Object mapPairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return bf.map(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapActivePairs$(final TensorLike$mcIJ$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs(f, bf);
   }

   default Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapActivePairs$mcIJ$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapActivePairs$mcIJ$sp$(final TensorLike$mcIJ$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs$mcIJ$sp(f, bf);
   }

   default Object mapActivePairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return bf.mapActive(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapValues$(final TensorLike$mcIJ$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues(f, bf);
   }

   default Object mapValues(final Function1 f, final CanMapValues bf) {
      return this.mapValues$mcJ$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapValues$mcJ$sp$(final TensorLike$mcIJ$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues$mcJ$sp(f, bf);
   }

   default Object mapValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return bf.map(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapActiveValues$(final TensorLike$mcIJ$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues(f, bf);
   }

   default Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return this.mapActiveValues$mcJ$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapActiveValues$mcJ$sp$(final TensorLike$mcIJ$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues$mcJ$sp(f, bf);
   }

   default Object mapActiveValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return bf.mapActive(this.repr(), f);
   }

   // $FF: synthetic method
   static void foreachKey$(final TensorLike$mcIJ$sp $this, final Function1 fn) {
      $this.foreachKey(fn);
   }

   default void foreachKey(final Function1 fn) {
      this.foreachKey$mcI$sp(fn);
   }

   // $FF: synthetic method
   static void foreachKey$mcI$sp$(final TensorLike$mcIJ$sp $this, final Function1 fn) {
      $this.foreachKey$mcI$sp(fn);
   }

   default void foreachKey$mcI$sp(final Function1 fn) {
      this.keysIterator().foreach(fn);
   }

   // $FF: synthetic method
   static void foreachPair$(final TensorLike$mcIJ$sp $this, final Function2 fn) {
      $this.foreachPair(fn);
   }

   default void foreachPair(final Function2 fn) {
      this.foreachPair$mcIJ$sp(fn);
   }

   // $FF: synthetic method
   static void foreachPair$mcIJ$sp$(final TensorLike$mcIJ$sp $this, final Function2 fn) {
      $this.foreachPair$mcIJ$sp(fn);
   }

   default void foreachPair$mcIJ$sp(final Function2 fn) {
      this.foreachKey$mcI$sp((k) -> $anonfun$foreachPair$5(this, fn, BoxesRunTime.unboxToInt(k)));
   }

   // $FF: synthetic method
   static void foreachValue$(final TensorLike$mcIJ$sp $this, final Function1 fn) {
      $this.foreachValue(fn);
   }

   default void foreachValue(final Function1 fn) {
      this.foreachValue$mcJ$sp(fn);
   }

   // $FF: synthetic method
   static void foreachValue$mcJ$sp$(final TensorLike$mcIJ$sp $this, final Function1 fn) {
      $this.foreachValue$mcJ$sp(fn);
   }

   default void foreachValue$mcJ$sp(final Function1 fn) {
      this.foreachKey$mcI$sp((k) -> $anonfun$foreachValue$5(this, fn, BoxesRunTime.unboxToInt(k)));
   }

   // $FF: synthetic method
   static boolean forall$(final TensorLike$mcIJ$sp $this, final Function2 fn) {
      return $this.forall(fn);
   }

   default boolean forall(final Function2 fn) {
      return this.forall$mcIJ$sp(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcIJ$sp$(final TensorLike$mcIJ$sp $this, final Function2 fn) {
      return $this.forall$mcIJ$sp(fn);
   }

   default boolean forall$mcIJ$sp(final Function2 fn) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreachPair$mcIJ$sp((JFunction2.mcVIJ.sp)(k, v) -> {
            if (!fn.apply$mcZIJ$sp(k, v)) {
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
   static boolean forall$(final TensorLike$mcIJ$sp $this, final Function1 fn) {
      return $this.forall(fn);
   }

   default boolean forall(final Function1 fn) {
      return this.forall$mcJ$sp(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcJ$sp$(final TensorLike$mcIJ$sp $this, final Function1 fn) {
      return $this.forall$mcJ$sp(fn);
   }

   default boolean forall$mcJ$sp(final Function1 fn) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreachValue$mcJ$sp((JFunction1.mcVJ.sp)(v) -> {
            if (!fn.apply$mcZJ$sp(v)) {
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
   static Object $anonfun$foreachPair$5(final TensorLike$mcIJ$sp $this, final Function2 fn$17, final int k) {
      return fn$17.apply(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToLong($this.apply$mcIJ$sp(k)));
   }

   // $FF: synthetic method
   static Object $anonfun$foreachValue$5(final TensorLike$mcIJ$sp $this, final Function1 fn$18, final int k) {
      return fn$18.apply(BoxesRunTime.boxToLong($this.apply$mcIJ$sp(k)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
