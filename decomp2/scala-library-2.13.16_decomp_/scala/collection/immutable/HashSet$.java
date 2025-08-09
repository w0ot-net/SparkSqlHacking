package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.Factory;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.mutable.ReusableBuilder;
import scala.math.Integral;
import scala.runtime.ModuleSerializationProxy;

public final class HashSet$ implements IterableFactory {
   public static final HashSet$ MODULE$ = new HashSet$();
   private static final long serialVersionUID = 3L;
   private static final transient HashSet EmptySet;

   static {
      HashSet$ var10000 = MODULE$;
      EmptySet = new HashSet(SetNode$.MODULE$.empty());
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

   private final HashSet EmptySet() {
      return EmptySet;
   }

   public HashSet empty() {
      return this.EmptySet();
   }

   public HashSet from(final IterableOnce source) {
      if (source instanceof HashSet) {
         return (HashSet)source;
      } else {
         return source.knownSize() == 0 ? this.empty() : (HashSet)(new HashSetBuilder()).addAll(source).result();
      }
   }

   public ReusableBuilder newBuilder() {
      return new HashSetBuilder();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HashSet$.class);
   }

   private HashSet$() {
   }
}
