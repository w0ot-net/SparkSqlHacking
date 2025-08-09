package scala.jdk;

import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.ObjLongConsumer;
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

public final class LongAccumulator$ implements SpecificIterableFactory, Serializable {
   public static final LongAccumulator$ MODULE$ = new LongAccumulator$();
   private static final long[] scala$jdk$LongAccumulator$$emptyLongArray;
   private static final long[][] scala$jdk$LongAccumulator$$emptyLongArrayArray;

   static {
      LongAccumulator$ var10000 = MODULE$;
      scala$jdk$LongAccumulator$$emptyLongArray = new long[0];
      scala$jdk$LongAccumulator$$emptyLongArrayArray = new long[0][];
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

   public long[] scala$jdk$LongAccumulator$$emptyLongArray() {
      return scala$jdk$LongAccumulator$$emptyLongArray;
   }

   public long[][] scala$jdk$LongAccumulator$$emptyLongArrayArray() {
      return scala$jdk$LongAccumulator$$emptyLongArrayArray;
   }

   public SpecificIterableFactory toJavaLongAccumulator(final LongAccumulator$ ia) {
      return this;
   }

   public Supplier supplier() {
      return () -> new LongAccumulator();
   }

   public ObjLongConsumer adder() {
      return (ac, a) -> ac.addOne(a);
   }

   public BiConsumer boxedAdder() {
      return (ac, a) -> $anonfun$boxedAdder$1(ac, BoxesRunTime.unboxToLong(a));
   }

   public BiConsumer merger() {
      return (a1, a2) -> a1.drain(a2);
   }

   private LongAccumulator fromArray(final long[] a) {
      LongAccumulator r = new LongAccumulator();

      for(int i = 0; i < a.length; ++i) {
         r.addOne(a[i]);
      }

      return r;
   }

   public LongAccumulator fromSpecific(final IterableOnce it) {
      if (it instanceof LongAccumulator) {
         return (LongAccumulator)it;
      } else if (it instanceof ArraySeq.ofLong) {
         ArraySeq.ofLong var2 = (ArraySeq.ofLong)it;
         return this.fromArray(var2.unsafeArray());
      } else if (it instanceof scala.collection.mutable.ArraySeq.ofLong) {
         scala.collection.mutable.ArraySeq.ofLong var3 = (scala.collection.mutable.ArraySeq.ofLong)it;
         return this.fromArray(var3.array());
      } else {
         return (LongAccumulator)Growable.addAll$(new LongAccumulator(), it);
      }
   }

   public LongAccumulator empty() {
      return new LongAccumulator();
   }

   public LongAccumulator newBuilder() {
      return new LongAccumulator();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LongAccumulator$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$boxedAdder$1(final LongAccumulator ac, final long a) {
      ac.addOne(a);
   }

   private LongAccumulator$() {
   }
}
