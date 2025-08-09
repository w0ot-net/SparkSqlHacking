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

public interface TensorLike$mcII$sp extends TensorLike, QuasiTensor$mcII$sp {
   // $FF: synthetic method
   static Object apply$(final TensorLike$mcII$sp $this, final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return $this.apply(a, b, c, slice, canSlice);
   }

   default Object apply(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return this.apply$mcI$sp(a, b, c, slice, canSlice);
   }

   // $FF: synthetic method
   static Object apply$mcI$sp$(final TensorLike$mcII$sp $this, final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return $this.apply$mcI$sp(a, b, c, slice, canSlice);
   }

   default Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return canSlice.apply(this.repr(), ((SeqOps)((SeqOps)slice.$plus$colon(BoxesRunTime.boxToInteger(c))).$plus$colon(BoxesRunTime.boxToInteger(b))).$plus$colon(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static Object mapPairs$(final TensorLike$mcII$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs(f, bf);
   }

   default Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapPairs$mcII$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapPairs$mcII$sp$(final TensorLike$mcII$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs$mcII$sp(f, bf);
   }

   default Object mapPairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return bf.map(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapActivePairs$(final TensorLike$mcII$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs(f, bf);
   }

   default Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapActivePairs$mcII$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapActivePairs$mcII$sp$(final TensorLike$mcII$sp $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs$mcII$sp(f, bf);
   }

   default Object mapActivePairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return bf.mapActive(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapValues$(final TensorLike$mcII$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues(f, bf);
   }

   default Object mapValues(final Function1 f, final CanMapValues bf) {
      return this.mapValues$mcI$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapValues$mcI$sp$(final TensorLike$mcII$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues$mcI$sp(f, bf);
   }

   default Object mapValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return bf.map(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapActiveValues$(final TensorLike$mcII$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues(f, bf);
   }

   default Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return this.mapActiveValues$mcI$sp(f, bf);
   }

   // $FF: synthetic method
   static Object mapActiveValues$mcI$sp$(final TensorLike$mcII$sp $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues$mcI$sp(f, bf);
   }

   default Object mapActiveValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return bf.mapActive(this.repr(), f);
   }

   // $FF: synthetic method
   static void foreachKey$(final TensorLike$mcII$sp $this, final Function1 fn) {
      $this.foreachKey(fn);
   }

   default void foreachKey(final Function1 fn) {
      this.foreachKey$mcI$sp(fn);
   }

   // $FF: synthetic method
   static void foreachKey$mcI$sp$(final TensorLike$mcII$sp $this, final Function1 fn) {
      $this.foreachKey$mcI$sp(fn);
   }

   default void foreachKey$mcI$sp(final Function1 fn) {
      this.keysIterator().foreach(fn);
   }

   // $FF: synthetic method
   static void foreachPair$(final TensorLike$mcII$sp $this, final Function2 fn) {
      $this.foreachPair(fn);
   }

   default void foreachPair(final Function2 fn) {
      this.foreachPair$mcII$sp(fn);
   }

   // $FF: synthetic method
   static void foreachPair$mcII$sp$(final TensorLike$mcII$sp $this, final Function2 fn) {
      $this.foreachPair$mcII$sp(fn);
   }

   default void foreachPair$mcII$sp(final Function2 fn) {
      this.foreachKey$mcI$sp((k) -> $anonfun$foreachPair$4(this, fn, BoxesRunTime.unboxToInt(k)));
   }

   // $FF: synthetic method
   static void foreachValue$(final TensorLike$mcII$sp $this, final Function1 fn) {
      $this.foreachValue(fn);
   }

   default void foreachValue(final Function1 fn) {
      this.foreachValue$mcI$sp(fn);
   }

   // $FF: synthetic method
   static void foreachValue$mcI$sp$(final TensorLike$mcII$sp $this, final Function1 fn) {
      $this.foreachValue$mcI$sp(fn);
   }

   default void foreachValue$mcI$sp(final Function1 fn) {
      this.foreachKey$mcI$sp((k) -> $anonfun$foreachValue$4(this, fn, BoxesRunTime.unboxToInt(k)));
   }

   // $FF: synthetic method
   static boolean forall$(final TensorLike$mcII$sp $this, final Function2 fn) {
      return $this.forall(fn);
   }

   default boolean forall(final Function2 fn) {
      return this.forall$mcII$sp(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcII$sp$(final TensorLike$mcII$sp $this, final Function2 fn) {
      return $this.forall$mcII$sp(fn);
   }

   default boolean forall$mcII$sp(final Function2 fn) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreachPair$mcII$sp((JFunction2.mcVII.sp)(k, v) -> {
            if (!fn.apply$mcZII$sp(k, v)) {
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
   static boolean forall$(final TensorLike$mcII$sp $this, final Function1 fn) {
      return $this.forall(fn);
   }

   default boolean forall(final Function1 fn) {
      return this.forall$mcI$sp(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcI$sp$(final TensorLike$mcII$sp $this, final Function1 fn) {
      return $this.forall$mcI$sp(fn);
   }

   default boolean forall$mcI$sp(final Function1 fn) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreachValue$mcI$sp((JFunction1.mcVI.sp)(v) -> {
            if (!fn.apply$mcZI$sp(v)) {
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
   static Object $anonfun$foreachPair$4(final TensorLike$mcII$sp $this, final Function2 fn$13, final int k) {
      return fn$13.apply(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToInteger($this.apply$mcII$sp(k)));
   }

   // $FF: synthetic method
   static Object $anonfun$foreachValue$4(final TensorLike$mcII$sp $this, final Function1 fn$14, final int k) {
      return fn$14.apply(BoxesRunTime.boxToInteger($this.apply$mcII$sp(k)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
