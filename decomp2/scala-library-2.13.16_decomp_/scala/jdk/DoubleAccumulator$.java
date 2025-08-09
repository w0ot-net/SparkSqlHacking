package scala.jdk;

import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;
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

public final class DoubleAccumulator$ implements SpecificIterableFactory, Serializable {
   public static final DoubleAccumulator$ MODULE$ = new DoubleAccumulator$();
   private static final double[] scala$jdk$DoubleAccumulator$$emptyDoubleArray;
   private static final double[][] scala$jdk$DoubleAccumulator$$emptyDoubleArrayArray;

   static {
      DoubleAccumulator$ var10000 = MODULE$;
      scala$jdk$DoubleAccumulator$$emptyDoubleArray = new double[0];
      scala$jdk$DoubleAccumulator$$emptyDoubleArrayArray = new double[0][];
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

   public double[] scala$jdk$DoubleAccumulator$$emptyDoubleArray() {
      return scala$jdk$DoubleAccumulator$$emptyDoubleArray;
   }

   public double[][] scala$jdk$DoubleAccumulator$$emptyDoubleArrayArray() {
      return scala$jdk$DoubleAccumulator$$emptyDoubleArrayArray;
   }

   public SpecificIterableFactory toJavaDoubleAccumulator(final DoubleAccumulator$ ia) {
      return this;
   }

   public Supplier supplier() {
      return () -> new DoubleAccumulator();
   }

   public ObjDoubleConsumer adder() {
      return (ac, a) -> ac.addOne(a);
   }

   public BiConsumer boxedAdder() {
      return (ac, a) -> $anonfun$boxedAdder$1(ac, BoxesRunTime.unboxToDouble(a));
   }

   public BiConsumer merger() {
      return (a1, a2) -> a1.drain(a2);
   }

   private DoubleAccumulator fromArray(final double[] a) {
      DoubleAccumulator r = new DoubleAccumulator();

      for(int i = 0; i < a.length; ++i) {
         r.addOne(a[i]);
      }

      return r;
   }

   public DoubleAccumulator fromSpecific(final IterableOnce it) {
      if (it instanceof DoubleAccumulator) {
         return (DoubleAccumulator)it;
      } else if (it instanceof ArraySeq.ofDouble) {
         ArraySeq.ofDouble var2 = (ArraySeq.ofDouble)it;
         return this.fromArray(var2.unsafeArray());
      } else if (it instanceof scala.collection.mutable.ArraySeq.ofDouble) {
         scala.collection.mutable.ArraySeq.ofDouble var3 = (scala.collection.mutable.ArraySeq.ofDouble)it;
         return this.fromArray(var3.array());
      } else {
         return (DoubleAccumulator)Growable.addAll$(new DoubleAccumulator(), it);
      }
   }

   public DoubleAccumulator empty() {
      return new DoubleAccumulator();
   }

   public DoubleAccumulator newBuilder() {
      return new DoubleAccumulator();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DoubleAccumulator$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$boxedAdder$1(final DoubleAccumulator ac, final double a) {
      ac.addOne(a);
   }

   private DoubleAccumulator$() {
   }
}
