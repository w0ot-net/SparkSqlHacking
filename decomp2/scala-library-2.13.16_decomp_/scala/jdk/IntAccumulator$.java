package scala.jdk;

import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import scala.Function0;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.SpecificIterableFactory;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Growable;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class IntAccumulator$ implements SpecificIterableFactory, Serializable {
   public static final IntAccumulator$ MODULE$ = new IntAccumulator$();
   private static final int[] scala$jdk$IntAccumulator$$emptyIntArray;
   private static final int[][] scala$jdk$IntAccumulator$$emptyIntArrayArray;

   static {
      IntAccumulator$ var10000 = MODULE$;
      scala$jdk$IntAccumulator$$emptyIntArray = new int[0];
      scala$jdk$IntAccumulator$$emptyIntArrayArray = new int[0][];
   }

   public Object apply(final Seq xs) {
      return SpecificIterableFactory.apply$(this, xs);
   }

   public Object fill(final int n, final Function0 elem) {
      return SpecificIterableFactory.fill$(this, n, elem);
   }

   public Factory specificIterableFactory() {
      return SpecificIterableFactory.specificIterableFactory$(this);
   }

   public int[] scala$jdk$IntAccumulator$$emptyIntArray() {
      return scala$jdk$IntAccumulator$$emptyIntArray;
   }

   public int[][] scala$jdk$IntAccumulator$$emptyIntArrayArray() {
      return scala$jdk$IntAccumulator$$emptyIntArrayArray;
   }

   public SpecificIterableFactory toJavaIntegerAccumulator(final IntAccumulator$ ia) {
      return this;
   }

   public Supplier supplier() {
      return () -> new IntAccumulator();
   }

   public ObjIntConsumer adder() {
      return (ac, a) -> ac.addOne(a);
   }

   public BiConsumer boxedAdder() {
      return (ac, a) -> $anonfun$boxedAdder$1(ac, BoxesRunTime.unboxToInt(a));
   }

   public BiConsumer merger() {
      return (a1, a2) -> a1.drain(a2);
   }

   private IntAccumulator fromArray(final int[] a) {
      IntAccumulator r = new IntAccumulator();

      for(int i = 0; i < a.length; ++i) {
         r.addOne(a[i]);
      }

      return r;
   }

   public IntAccumulator fromSpecific(final IterableOnce it) {
      if (it instanceof IntAccumulator) {
         return (IntAccumulator)it;
      } else if (it instanceof ArraySeq.ofInt) {
         ArraySeq.ofInt var2 = (ArraySeq.ofInt)it;
         return this.fromArray(var2.unsafeArray());
      } else if (it instanceof scala.collection.mutable.ArraySeq.ofInt) {
         scala.collection.mutable.ArraySeq.ofInt var3 = (scala.collection.mutable.ArraySeq.ofInt)it;
         return this.fromArray(var3.array());
      } else {
         return (IntAccumulator)Growable.addAll$(new IntAccumulator(), it);
      }
   }

   public IntAccumulator empty() {
      return new IntAccumulator();
   }

   public IntAccumulator newBuilder() {
      return new IntAccumulator();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IntAccumulator$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$boxedAdder$1(final IntAccumulator ac, final int a) {
      ac.addOne(a);
   }

   private IntAccumulator$() {
   }
}
