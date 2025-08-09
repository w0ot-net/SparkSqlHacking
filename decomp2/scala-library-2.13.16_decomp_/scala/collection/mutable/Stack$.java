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
import scala.collection.SeqFactory;
import scala.collection.StrictOptimizedSeqFactory;
import scala.math.Integral;
import scala.runtime.ModuleSerializationProxy;

public final class Stack$ implements StrictOptimizedSeqFactory {
   public static final Stack$ MODULE$ = new Stack$();
   private static final long serialVersionUID = 3L;

   static {
      Stack$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
   }

   public scala.collection.SeqOps fill(final int n, final Function0 elem) {
      return StrictOptimizedSeqFactory.fill$(this, n, elem);
   }

   public scala.collection.SeqOps tabulate(final int n, final Function1 f) {
      return StrictOptimizedSeqFactory.tabulate$(this, n, f);
   }

   public scala.collection.SeqOps concat(final scala.collection.immutable.Seq xss) {
      return StrictOptimizedSeqFactory.concat$(this, xss);
   }

   public final scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      return SeqFactory.unapplySeq$(this, x);
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

   public Factory iterableFactory() {
      return IterableFactory.iterableFactory$(this);
   }

   public int $lessinit$greater$default$1() {
      return 16;
   }

   public Stack from(final IterableOnce source) {
      Stack var10000 = this.empty();
      if (var10000 == null) {
         throw null;
      } else {
         return (Stack)var10000.addAll(source);
      }
   }

   public Stack empty() {
      return new Stack(16);
   }

   public Builder newBuilder() {
      return new GrowableBuilder(this.empty());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Stack$.class);
   }

   private Stack$() {
   }
}
