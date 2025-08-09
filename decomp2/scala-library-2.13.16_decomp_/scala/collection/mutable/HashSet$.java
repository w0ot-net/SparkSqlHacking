package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.Factory;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.math.Integral;
import scala.runtime.ModuleSerializationProxy;

public final class HashSet$ implements IterableFactory {
   public static final HashSet$ MODULE$ = new HashSet$();
   private static final long serialVersionUID = 3L;

   static {
      HashSet$ var10000 = MODULE$;
   }

   public Object apply(final scala.collection.immutable.Seq elems) {
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

   public Object concat(final scala.collection.immutable.Seq xss) {
      return IterableFactory.concat$(this, xss);
   }

   public Factory iterableFactory() {
      return IterableFactory.iterableFactory$(this);
   }

   public HashSet from(final IterableOnce it) {
      int k = it.knownSize();
      int cap = k > 0 ? (int)((double)(k + 1) / (double)0.75F) : 16;
      return (new HashSet(cap, (double)0.75F)).addAll(it);
   }

   public HashSet empty() {
      return new HashSet();
   }

   public Builder newBuilder() {
      double newBuilder_loadFactor = (double)0.75F;
      int newBuilder_initialCapacity = 16;
      return new GrowableBuilder(newBuilder_initialCapacity, newBuilder_loadFactor) {
         public void sizeHint(final int size) {
            ((HashSet)this.elems()).sizeHint(size);
         }
      };
   }

   public Builder newBuilder(final int initialCapacity, final double loadFactor) {
      return new GrowableBuilder(initialCapacity, loadFactor) {
         public void sizeHint(final int size) {
            ((HashSet)this.elems()).sizeHint(size);
         }
      };
   }

   public final double defaultLoadFactor() {
      return (double)0.75F;
   }

   public final int defaultInitialCapacity() {
      return 16;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HashSet$.class);
   }

   private HashSet$() {
   }
}
