package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
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
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.math.Integral;
import scala.runtime.ModuleSerializationProxy;

public final class Queue$ implements StrictOptimizedSeqFactory {
   public static final Queue$ MODULE$ = new Queue$();
   private static final long serialVersionUID = 3L;

   static {
      Queue$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
   }

   public scala.collection.SeqOps fill(final int n, final Function0 elem) {
      return StrictOptimizedSeqFactory.fill$(this, n, elem);
   }

   public scala.collection.SeqOps tabulate(final int n, final Function1 f) {
      return StrictOptimizedSeqFactory.tabulate$(this, n, f);
   }

   public scala.collection.SeqOps concat(final Seq xss) {
      return StrictOptimizedSeqFactory.concat$(this, xss);
   }

   public final scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      return SeqFactory.unapplySeq$(this, x);
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

   public Builder newBuilder() {
      return (new ListBuffer()).mapResult((x) -> new Queue(Nil$.MODULE$, x));
   }

   public Queue from(final IterableOnce source) {
      if (source instanceof Queue) {
         return (Queue)source;
      } else {
         List$ var10000 = List$.MODULE$;
         List list = Nil$.MODULE$.prependedAll(source);
         return (Queue)(list.isEmpty() ? Queue.EmptyQueue$.MODULE$ : new Queue(Nil$.MODULE$, list));
      }
   }

   public Queue empty() {
      return Queue.EmptyQueue$.MODULE$;
   }

   public Queue apply(final Seq xs) {
      return new Queue(Nil$.MODULE$, xs.toList());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Queue$.class);
   }

   private Queue$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
