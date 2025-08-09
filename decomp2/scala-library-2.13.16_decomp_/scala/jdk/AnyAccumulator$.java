package scala.jdk;

import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.Factory;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.math.Integral;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class AnyAccumulator$ implements SeqFactory {
   public static final AnyAccumulator$ MODULE$ = new AnyAccumulator$();
   private static final Object[] scala$jdk$AnyAccumulator$$emptyAnyRefArray;
   private static final Object[][] scala$jdk$AnyAccumulator$$emptyAnyRefArrayArray;
   private static final long[] scala$jdk$AnyAccumulator$$emptyLongArray;

   static {
      AnyAccumulator$ var10000 = MODULE$;
      var10000 = MODULE$;
      scala$jdk$AnyAccumulator$$emptyAnyRefArray = new Object[0];
      scala$jdk$AnyAccumulator$$emptyAnyRefArrayArray = new Object[0][];
      scala$jdk$AnyAccumulator$$emptyLongArray = new long[0];
   }

   public final SeqOps unapplySeq(final SeqOps x) {
      return SeqFactory.unapplySeq$(this, x);
   }

   public Object apply(final Seq elems) {
      return IterableFactory.apply$(this, elems);
   }

   public Object iterate(final Object start, final int len, final Function1 f) {
      return IterableFactory.iterate$(this, start, len, f);
   }

   public Object unfold(final Object init, final Function1 f) {
      return IterableFactory.unfold$(this, init, f);
   }

   public Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(this, start, end, evidence$3);
   }

   public Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(this, start, end, step, evidence$4);
   }

   public Object fill(final int n, final Function0 elem) {
      return IterableFactory.fill$(this, n, elem);
   }

   public Object fill(final int n1, final int n2, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, elem);
   }

   public Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, n3, elem);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, n3, n4, elem);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, n3, n4, n5, elem);
   }

   public Object tabulate(final int n, final Function1 f) {
      return IterableFactory.tabulate$(this, n, f);
   }

   public Object tabulate(final int n1, final int n2, final Function2 f) {
      return IterableFactory.tabulate$(this, n1, n2, f);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      return IterableFactory.tabulate$(this, n1, n2, n3, f);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      return IterableFactory.tabulate$(this, n1, n2, n3, n4, f);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      return IterableFactory.tabulate$(this, n1, n2, n3, n4, n5, f);
   }

   public Object concat(final Seq xss) {
      return IterableFactory.concat$(this, xss);
   }

   public Factory iterableFactory() {
      return IterableFactory.iterableFactory$(this);
   }

   public Object[] scala$jdk$AnyAccumulator$$emptyAnyRefArray() {
      return scala$jdk$AnyAccumulator$$emptyAnyRefArray;
   }

   public Object[][] scala$jdk$AnyAccumulator$$emptyAnyRefArrayArray() {
      return scala$jdk$AnyAccumulator$$emptyAnyRefArrayArray;
   }

   public long[] scala$jdk$AnyAccumulator$$emptyLongArray() {
      return scala$jdk$AnyAccumulator$$emptyLongArray;
   }

   public Supplier supplier() {
      return () -> new AnyAccumulator();
   }

   public BiConsumer adder() {
      return (ac, a) -> ac.addOne(a);
   }

   public ObjIntConsumer unboxedIntAdder() {
      return (ac, a) -> ac.addOne(BoxesRunTime.boxToInteger(a));
   }

   public ObjLongConsumer unboxedLongAdder() {
      return (ac, a) -> ac.addOne(BoxesRunTime.boxToLong(a));
   }

   public ObjDoubleConsumer unboxedDoubleAdder() {
      return (ac, a) -> ac.addOne(BoxesRunTime.boxToDouble(a));
   }

   public BiConsumer merger() {
      return (a1, a2) -> a1.drain(a2);
   }

   public AnyAccumulator from(final IterableOnce source) {
      return source instanceof AnyAccumulator ? (AnyAccumulator)source : (AnyAccumulator)Growable.addAll$(new AnyAccumulator(), source);
   }

   public AnyAccumulator empty() {
      return new AnyAccumulator();
   }

   public Builder newBuilder() {
      return new AnyAccumulator();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AnyAccumulator$.class);
   }

   private AnyAccumulator$() {
   }
}
