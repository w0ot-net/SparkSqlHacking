package breeze.util;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.package.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public final class Iterators$ {
   public static final Iterators$ MODULE$ = new Iterators$();

   public Iterator fromProducer(final Function0 prod) {
      return .MODULE$.Iterator().continually(prod).takeWhile((x$1) -> BoxesRunTime.boxToBoolean($anonfun$fromProducer$1(x$1))).map((x$2) -> x$2.get());
   }

   public Iterator merge(final Seq iters, final Function2 compare) {
      return new Iterator(iters, compare) {
         private final Option[] heads;
         private final Seq iters$1;
         private final Function2 compare$1;

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public int count(final Function1 p) {
            return IterableOnceOps.count$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return IterableOnceOps.fold$(this, z, op);
         }

         public Object reduce(final Function2 op) {
            return IterableOnceOps.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         public int size() {
            return IterableOnceOps.size$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Object sum(final Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return IterableOnceOps.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return IterableOnceOps.min$(this, ord);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Object max(final Ordering ord) {
            return IterableOnceOps.max$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.maxBy$(this, f, cmp);
         }

         public Option maxByOption(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.maxByOption$(this, f, cmp);
         }

         public Object minBy(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.minBy$(this, f, cmp);
         }

         public Option minByOption(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.minByOption$(this, f, cmp);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final StringBuilder addString(final StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final StringBuilder addString(final StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         public Object to(final Factory factory) {
            return IterableOnceOps.to$(this, factory);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         private Option[] heads() {
            return this.heads;
         }

         public boolean hasNext() {
            return BoxesRunTime.unboxToBoolean(scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.booleanArrayOps((boolean[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.heads()), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$hasNext$1(x$3)), scala.reflect.ClassTag..MODULE$.Boolean())), BoxesRunTime.boxToBoolean(false), (x$4, x$5) -> BoxesRunTime.boxToBoolean($anonfun$hasNext$2(BoxesRunTime.unboxToBoolean(x$4), BoxesRunTime.unboxToBoolean(x$5)))));
         }

         public Object next() {
            Tuple2 top = (Tuple2)scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.heads()))), new Tuple2(scala.None..MODULE$, BoxesRunTime.boxToInteger(-1)), (headA, headB) -> {
               Tuple2 var4 = new Tuple2(headA, headB);
               Tuple2 var3;
               if (var4 != null) {
                  Tuple2 var5 = (Tuple2)var4._1();
                  Tuple2 var6 = (Tuple2)var4._2();
                  if (var5 != null) {
                     Option var7 = (Option)var5._1();
                     if (var7 instanceof Some) {
                        Some var8 = (Some)var7;
                        Object a = var8.value();
                        if (var6 != null) {
                           Option var10 = (Option)var6._1();
                           if (var10 instanceof Some) {
                              Some var11 = (Some)var10;
                              Object b = var11.value();
                              var3 = BoxesRunTime.unboxToInt(this.compare$1.apply(a, b)) <= 0 ? headA : headB;
                              return var3;
                           }
                        }
                     }
                  }
               }

               if (var4 != null) {
                  Tuple2 var13 = (Tuple2)var4._1();
                  Tuple2 var14 = (Tuple2)var4._2();
                  if (var13 != null) {
                     Option var15 = (Option)var13._1();
                     if (var15 instanceof Some && var14 != null) {
                        Option var16 = (Option)var14._1();
                        if (scala.None..MODULE$.equals(var16)) {
                           var3 = headA;
                           return var3;
                        }
                     }
                  }
               }

               if (var4 != null) {
                  Tuple2 var17 = (Tuple2)var4._1();
                  Tuple2 var18 = (Tuple2)var4._2();
                  if (var17 != null) {
                     Option var19 = (Option)var17._1();
                     if (scala.None..MODULE$.equals(var19) && var18 != null) {
                        Option var20 = (Option)var18._1();
                        if (var20 instanceof Some) {
                           var3 = headB;
                           return var3;
                        }
                     }
                  }
               }

               if (var4 != null) {
                  Tuple2 var21 = (Tuple2)var4._1();
                  Tuple2 var22 = (Tuple2)var4._2();
                  if (var21 != null) {
                     Option var23 = (Option)var21._1();
                     if (scala.None..MODULE$.equals(var23) && var22 != null) {
                        Option var24 = (Option)var22._1();
                        if (scala.None..MODULE$.equals(var24)) {
                           var3 = headA;
                           return var3;
                        }
                     }
                  }
               }

               if (var4 != null) {
                  throw new IllegalStateException(var4.toString());
               } else {
                  throw new MatchError(var4);
               }
            });
            this.heads()[top._2$mcI$sp()] = this.get((Iterator)this.iters$1.apply(top._2$mcI$sp()));
            return ((Option)top._1()).get();
         }

         private Option get(final Iterator iter) {
            return (Option)(iter.hasNext() ? new Some(iter.next()) : scala.None..MODULE$);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$hasNext$1(final Option x$3) {
            boolean var10000;
            label23: {
               None var1 = scala.None..MODULE$;
               if (x$3 == null) {
                  if (var1 != null) {
                     break label23;
                  }
               } else if (!x$3.equals(var1)) {
                  break label23;
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$hasNext$2(final boolean x$4, final boolean x$5) {
            return x$4 || x$5;
         }

         public {
            this.iters$1 = iters$1;
            this.compare$1 = compare$1;
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.heads = (Option[])((IterableOnceOps)iters$1.map((iter) -> this.get(iter))).toArray(scala.reflect.ClassTag..MODULE$.apply(Option.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fromProducer$1(final Option x$1) {
      return x$1.nonEmpty();
   }

   private Iterators$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
