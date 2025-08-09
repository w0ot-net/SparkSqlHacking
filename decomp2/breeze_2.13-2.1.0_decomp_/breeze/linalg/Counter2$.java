package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.Counter2Ops;
import breeze.linalg.support.CanCollapseAxis;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorValues;
import breeze.math.Field;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Set;
import scala.collection.immutable.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class Counter2$ implements LowPriorityCounter2, Counter2Ops {
   public static final Counter2$ MODULE$ = new Counter2$();

   static {
      LowPriorityCounter2.$init$(MODULE$);
      Counter2Ops.$init$(MODULE$);
   }

   public CanCopy canCopy(final Zero evidence$1, final Semiring evidence$2) {
      return Counter2Ops.canCopy$(this, evidence$1, evidence$2);
   }

   public UFunc.InPlaceImpl2 addIntoVV(final Semiring evidence$3) {
      return Counter2Ops.addIntoVV$(this, evidence$3);
   }

   public UFunc.InPlaceImpl3 canAxpy(final Semiring evidence$4) {
      return Counter2Ops.canAxpy$(this, evidence$4);
   }

   public UFunc.UImpl2 addVV(final Semiring evidence$5, final Zero evidence$6) {
      return Counter2Ops.addVV$(this, evidence$5, evidence$6);
   }

   public UFunc.InPlaceImpl2 addIntoVS(final Semiring evidence$7) {
      return Counter2Ops.addIntoVS$(this, evidence$7);
   }

   public UFunc.UImpl2 addVS(final Semiring evidence$8, final Zero evidence$9) {
      return Counter2Ops.addVS$(this, evidence$8, evidence$9);
   }

   public UFunc.InPlaceImpl2 subIntoVV(final Ring evidence$10) {
      return Counter2Ops.subIntoVV$(this, evidence$10);
   }

   public UFunc.UImpl2 subVV(final Ring evidence$11, final Zero evidence$12) {
      return Counter2Ops.subVV$(this, evidence$11, evidence$12);
   }

   public UFunc.InPlaceImpl2 subIntoVS(final Ring evidence$13) {
      return Counter2Ops.subIntoVS$(this, evidence$13);
   }

   public UFunc.UImpl2 subVS(final Ring evidence$14, final Zero evidence$15) {
      return Counter2Ops.subVS$(this, evidence$14, evidence$15);
   }

   public UFunc.InPlaceImpl2 canMulIntoVV(final Semiring evidence$16) {
      return Counter2Ops.canMulIntoVV$(this, evidence$16);
   }

   public UFunc.UImpl2 canMulVV(final Semiring semiring) {
      return Counter2Ops.canMulVV$(this, semiring);
   }

   public UFunc.InPlaceImpl2 canMulIntoVS(final Semiring evidence$17) {
      return Counter2Ops.canMulIntoVS$(this, evidence$17);
   }

   public UFunc.InPlaceImpl2 canMulIntoVS_M(final Semiring evidence$18) {
      return Counter2Ops.canMulIntoVS_M$(this, evidence$18);
   }

   public UFunc.UImpl2 canMulVS(final Semiring semiring) {
      return Counter2Ops.canMulVS$(this, semiring);
   }

   public UFunc.UImpl2 canMulVS_M(final Semiring semiring) {
      return Counter2Ops.canMulVS_M$(this, semiring);
   }

   public UFunc.InPlaceImpl2 canDivIntoVV(final Field evidence$19) {
      return Counter2Ops.canDivIntoVV$(this, evidence$19);
   }

   public UFunc.UImpl2 canDivVV(final CanCopy copy, final Field semiring) {
      return Counter2Ops.canDivVV$(this, copy, semiring);
   }

   public UFunc.UImpl2 canDivVS(final CanCopy copy, final Field semiring) {
      return Counter2Ops.canDivVS$(this, copy, semiring);
   }

   public UFunc.InPlaceImpl2 canDivIntoVS(final Field evidence$20) {
      return Counter2Ops.canDivIntoVS$(this, evidence$20);
   }

   public UFunc.InPlaceImpl2 canSetIntoVV() {
      return Counter2Ops.canSetIntoVV$(this);
   }

   public UFunc.InPlaceImpl2 canSetIntoVS() {
      return Counter2Ops.canSetIntoVS$(this);
   }

   public UFunc.UImpl canNegate(final Ring ring) {
      return Counter2Ops.canNegate$(this, ring);
   }

   public UFunc.UImpl2 canMultiplyC2C1(final Semiring semiring) {
      return Counter2Ops.canMultiplyC2C1$(this, semiring);
   }

   public UFunc.UImpl2 canMultiplyC2C2(final Semiring semiring) {
      return Counter2Ops.canMultiplyC2C2$(this, semiring);
   }

   public Counter2Ops.CanZipMapValuesCounter2 zipMap(final Zero evidence$23, final Semiring evidence$24) {
      return Counter2Ops.zipMap$(this, evidence$23, evidence$24);
   }

   public CanCollapseAxis canCollapseRows(final ClassTag evidence$14, final Zero evidence$15, final Semiring evidence$16) {
      return LowPriorityCounter2.canCollapseRows$(this, evidence$14, evidence$15, evidence$16);
   }

   public CanCollapseAxis canCollapseCols(final ClassTag evidence$17, final Zero evidence$18, final Semiring evidence$19) {
      return LowPriorityCounter2.canCollapseCols$(this, evidence$17, evidence$18, evidence$19);
   }

   public Counter2 apply(final Zero evidence$1) {
      return new Counter2.Impl(new Counter2.CounterHashMap(evidence$1), evidence$1);
   }

   public Counter2 apply(final Seq values, final Semiring evidence$3, final Zero evidence$4) {
      return this.apply((IterableOnce)values.iterator(), evidence$3, evidence$4);
   }

   public Counter2 apply(final IterableOnce values, final Semiring evidence$5, final Zero evidence$6) {
      Counter2 rv = this.apply(evidence$6);
      values.iterator().foreach((x0$1) -> {
         $anonfun$apply$3(rv, evidence$5, x0$1);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public Counter2 count(final IterableOnce values) {
      Counter2 rv = this.apply(Zero$.MODULE$.IntZero());
      values.iterator().foreach((x0$1) -> {
         $anonfun$count$1(rv, x0$1);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public CanMapValues CanMapValuesCounter(final Semiring evidence$7, final Zero evidence$8) {
      return new CanMapValues.DenseCanMapValues(evidence$8) {
         private final Zero evidence$8$1;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public Counter2 map(final Counter2 from, final Function1 fn) {
            Counter2 rv = Counter2$.MODULE$.apply(this.evidence$8$1);
            from.iterator().withFilter((check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$map$1(check$ifrefutable$4))).foreach((x$4) -> {
               $anonfun$map$2(rv, fn, x$4);
               return BoxedUnit.UNIT;
            });
            return rv;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$map$1(final Tuple2 check$ifrefutable$4) {
            boolean var1;
            if (check$ifrefutable$4 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$map$2(final Counter2 rv$3, final Function1 fn$1, final Tuple2 x$4) {
            if (x$4 != null) {
               Tuple2 k = (Tuple2)x$4._1();
               Object v = x$4._2();
               rv$3.update(k, fn$1.apply(v));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$4);
            }
         }

         public {
            this.evidence$8$1 = evidence$8$1;
            CanMapValues.DenseCanMapValues.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanTraverseValues canIterateValues() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public boolean isTraversableAgain(final Counter2 from) {
            return true;
         }

         public CanTraverseValues.ValuesVisitor traverse(final Counter2 from, final CanTraverseValues.ValuesVisitor fn) {
            from.valuesIterator().foreach((v) -> {
               $anonfun$traverse$1(fn, v);
               return BoxedUnit.UNIT;
            });
            return fn;
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$1(final CanTraverseValues.ValuesVisitor fn$2, final Object v) {
            fn$2.visit(v);
         }

         public {
            CanTraverseValues.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return new CanTraverseKeyValuePairs() {
         public void traverse(final Counter2 from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
            from.activeIterator().withFilter((check$ifrefutable$5) -> BoxesRunTime.boxToBoolean($anonfun$traverse$2(check$ifrefutable$5))).foreach((x$5) -> {
               $anonfun$traverse$3(fn, x$5);
               return BoxedUnit.UNIT;
            });
         }

         public boolean isTraversableAgain(final Counter2 from) {
            return true;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$traverse$2(final Tuple2 check$ifrefutable$5) {
            boolean var1;
            if (check$ifrefutable$5 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$3(final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn$3, final Tuple2 x$5) {
            if (x$5 != null) {
               Tuple2 k = (Tuple2)x$5._1();
               Object v = x$5._2();
               fn$3.visit(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$5);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanSlice2 canSliceRow() {
      return new CanSlice2() {
         public Counter apply(final Counter2 from, final Object row, final .colon.colon unused) {
            return (Counter)from.innerGetOrElseUpdate(row, from.data());
         }
      };
   }

   public CanSlice2 canSliceCol() {
      return new CanSlice2() {
         public Counter apply(final Counter2 from, final .colon.colon x, final Object col) {
            return new Counter(from, col) {
               private final Counter2ProjectionMap data;
               private final Counter2 from$1;

               public Set keySet() {
                  return CounterLike.keySet$(this);
               }

               public Counter repr() {
                  return CounterLike.repr$(this);
               }

               public int size() {
                  return CounterLike.size$(this);
               }

               public int activeSize() {
                  return CounterLike.activeSize$(this);
               }

               public boolean isEmpty() {
                  return CounterLike.isEmpty$(this);
               }

               public boolean contains(final Object k) {
                  return CounterLike.contains$(this, k);
               }

               public Object apply(final Object k) {
                  return CounterLike.apply$(this, k);
               }

               public void update(final Object k, final Object v) {
                  CounterLike.update$(this, k, v);
               }

               public Option get(final Object k) {
                  return CounterLike.get$(this, k);
               }

               public Iterator keysIterator() {
                  return CounterLike.keysIterator$(this);
               }

               public Iterator valuesIterator() {
                  return CounterLike.valuesIterator$(this);
               }

               public Iterator iterator() {
                  return CounterLike.iterator$(this);
               }

               public Iterator activeIterator() {
                  return CounterLike.activeIterator$(this);
               }

               public Iterator activeValuesIterator() {
                  return CounterLike.activeValuesIterator$(this);
               }

               public Iterator activeKeysIterator() {
                  return CounterLike.activeKeysIterator$(this);
               }

               public String toString() {
                  return CounterLike.toString$(this);
               }

               public boolean equals(final Object p1) {
                  return CounterLike.equals$(this, p1);
               }

               public int hashCode() {
                  return CounterLike.hashCode$(this);
               }

               public Map toMap() {
                  return CounterLike.toMap$(this);
               }

               public double apply$mcID$sp(final int i) {
                  return TensorLike.apply$mcID$sp$(this, i);
               }

               public float apply$mcIF$sp(final int i) {
                  return TensorLike.apply$mcIF$sp$(this, i);
               }

               public int apply$mcII$sp(final int i) {
                  return TensorLike.apply$mcII$sp$(this, i);
               }

               public long apply$mcIJ$sp(final int i) {
                  return TensorLike.apply$mcIJ$sp$(this, i);
               }

               public void update$mcID$sp(final int i, final double v) {
                  TensorLike.update$mcID$sp$(this, i, v);
               }

               public void update$mcIF$sp(final int i, final float v) {
                  TensorLike.update$mcIF$sp$(this, i, v);
               }

               public void update$mcII$sp(final int i, final int v) {
                  TensorLike.update$mcII$sp$(this, i, v);
               }

               public void update$mcIJ$sp(final int i, final long v) {
                  TensorLike.update$mcIJ$sp$(this, i, v);
               }

               public TensorKeys keys() {
                  return TensorLike.keys$(this);
               }

               public TensorValues values() {
                  return TensorLike.values$(this);
               }

               public TensorPairs pairs() {
                  return TensorLike.pairs$(this);
               }

               public TensorActive active() {
                  return TensorLike.active$(this);
               }

               public Object apply(final Object slice, final CanSlice canSlice) {
                  return TensorLike.apply$(this, slice, canSlice);
               }

               public Object apply(final Object a, final Object b, final Object c, final Seq slice, final CanSlice canSlice) {
                  return TensorLike.apply$(this, a, b, c, slice, canSlice);
               }

               public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
                  return TensorLike.apply$mcI$sp$(this, a, b, c, slice, canSlice);
               }

               public Object apply(final Object slice1, final Object slice2, final CanSlice2 canSlice) {
                  return TensorLike.apply$(this, slice1, slice2, canSlice);
               }

               public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
                  return TensorLike.mapPairs$(this, f, bf);
               }

               public Object mapPairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
                  return TensorLike.mapPairs$mcID$sp$(this, f, bf);
               }

               public Object mapPairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
                  return TensorLike.mapPairs$mcIF$sp$(this, f, bf);
               }

               public Object mapPairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
                  return TensorLike.mapPairs$mcII$sp$(this, f, bf);
               }

               public Object mapPairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
                  return TensorLike.mapPairs$mcIJ$sp$(this, f, bf);
               }

               public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
                  return TensorLike.mapActivePairs$(this, f, bf);
               }

               public Object mapActivePairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
                  return TensorLike.mapActivePairs$mcID$sp$(this, f, bf);
               }

               public Object mapActivePairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
                  return TensorLike.mapActivePairs$mcIF$sp$(this, f, bf);
               }

               public Object mapActivePairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
                  return TensorLike.mapActivePairs$mcII$sp$(this, f, bf);
               }

               public Object mapActivePairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
                  return TensorLike.mapActivePairs$mcIJ$sp$(this, f, bf);
               }

               public Object mapValues(final Function1 f, final CanMapValues bf) {
                  return TensorLike.mapValues$(this, f, bf);
               }

               public Object mapValues$mcD$sp(final Function1 f, final CanMapValues bf) {
                  return TensorLike.mapValues$mcD$sp$(this, f, bf);
               }

               public Object mapValues$mcF$sp(final Function1 f, final CanMapValues bf) {
                  return TensorLike.mapValues$mcF$sp$(this, f, bf);
               }

               public Object mapValues$mcI$sp(final Function1 f, final CanMapValues bf) {
                  return TensorLike.mapValues$mcI$sp$(this, f, bf);
               }

               public Object mapValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
                  return TensorLike.mapValues$mcJ$sp$(this, f, bf);
               }

               public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
                  return TensorLike.mapActiveValues$(this, f, bf);
               }

               public Object mapActiveValues$mcD$sp(final Function1 f, final CanMapValues bf) {
                  return TensorLike.mapActiveValues$mcD$sp$(this, f, bf);
               }

               public Object mapActiveValues$mcF$sp(final Function1 f, final CanMapValues bf) {
                  return TensorLike.mapActiveValues$mcF$sp$(this, f, bf);
               }

               public Object mapActiveValues$mcI$sp(final Function1 f, final CanMapValues bf) {
                  return TensorLike.mapActiveValues$mcI$sp$(this, f, bf);
               }

               public Object mapActiveValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
                  return TensorLike.mapActiveValues$mcJ$sp$(this, f, bf);
               }

               public void foreachKey(final Function1 fn) {
                  TensorLike.foreachKey$(this, fn);
               }

               public void foreachKey$mcI$sp(final Function1 fn) {
                  TensorLike.foreachKey$mcI$sp$(this, fn);
               }

               public void foreachPair(final Function2 fn) {
                  TensorLike.foreachPair$(this, fn);
               }

               public void foreachPair$mcID$sp(final Function2 fn) {
                  TensorLike.foreachPair$mcID$sp$(this, fn);
               }

               public void foreachPair$mcIF$sp(final Function2 fn) {
                  TensorLike.foreachPair$mcIF$sp$(this, fn);
               }

               public void foreachPair$mcII$sp(final Function2 fn) {
                  TensorLike.foreachPair$mcII$sp$(this, fn);
               }

               public void foreachPair$mcIJ$sp(final Function2 fn) {
                  TensorLike.foreachPair$mcIJ$sp$(this, fn);
               }

               public void foreachValue(final Function1 fn) {
                  TensorLike.foreachValue$(this, fn);
               }

               public void foreachValue$mcD$sp(final Function1 fn) {
                  TensorLike.foreachValue$mcD$sp$(this, fn);
               }

               public void foreachValue$mcF$sp(final Function1 fn) {
                  TensorLike.foreachValue$mcF$sp$(this, fn);
               }

               public void foreachValue$mcI$sp(final Function1 fn) {
                  TensorLike.foreachValue$mcI$sp$(this, fn);
               }

               public void foreachValue$mcJ$sp(final Function1 fn) {
                  TensorLike.foreachValue$mcJ$sp$(this, fn);
               }

               public boolean forall(final Function2 fn) {
                  return TensorLike.forall$(this, (Function2)fn);
               }

               public boolean forall$mcID$sp(final Function2 fn) {
                  return TensorLike.forall$mcID$sp$(this, fn);
               }

               public boolean forall$mcIF$sp(final Function2 fn) {
                  return TensorLike.forall$mcIF$sp$(this, fn);
               }

               public boolean forall$mcII$sp(final Function2 fn) {
                  return TensorLike.forall$mcII$sp$(this, fn);
               }

               public boolean forall$mcIJ$sp(final Function2 fn) {
                  return TensorLike.forall$mcIJ$sp$(this, fn);
               }

               public boolean forall(final Function1 fn) {
                  return TensorLike.forall$(this, (Function1)fn);
               }

               public boolean forall$mcD$sp(final Function1 fn) {
                  return TensorLike.forall$mcD$sp$(this, fn);
               }

               public boolean forall$mcF$sp(final Function1 fn) {
                  return TensorLike.forall$mcF$sp$(this, fn);
               }

               public boolean forall$mcI$sp(final Function1 fn) {
                  return TensorLike.forall$mcI$sp$(this, fn);
               }

               public boolean forall$mcJ$sp(final Function1 fn) {
                  return TensorLike.forall$mcJ$sp$(this, fn);
               }

               public final Object $plus(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$plus$(this, b, op);
               }

               public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$eq$(this, b, op);
               }

               public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$plus$eq$(this, b, op);
               }

               public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$times$eq$(this, b, op);
               }

               public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$plus$eq$(this, b, op);
               }

               public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$times$eq$(this, b, op);
               }

               public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$minus$eq$(this, b, op);
               }

               public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$percent$eq$(this, b, op);
               }

               public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$percent$eq$(this, b, op);
               }

               public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$minus$eq$(this, b, op);
               }

               public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$div$eq$(this, b, op);
               }

               public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$eq$(this, b, op);
               }

               public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$div$eq$(this, b, op);
               }

               public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$less$(this, b, op);
               }

               public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$less$colon$eq$(this, b, op);
               }

               public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$greater$(this, b, op);
               }

               public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
                  return NumericOps.$greater$colon$eq$(this, b, op);
               }

               public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$amp$eq$(this, b, op);
               }

               public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$bar$eq$(this, b, op);
               }

               public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$colon$up$up$eq$(this, b, op);
               }

               public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$amp$eq$(this, b, op);
               }

               public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$bar$eq$(this, b, op);
               }

               public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
                  return NumericOps.$up$up$eq$(this, b, op);
               }

               public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
               }

               public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$colon$times$(this, b, op);
               }

               public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
               }

               public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
               }

               public final Object unary_$minus(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$minus$(this, op);
               }

               public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
               }

               public final Object $minus(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$minus$(this, b, op);
               }

               public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
               }

               public final Object $percent(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$percent$(this, b, op);
               }

               public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$colon$div$(this, b, op);
               }

               public final Object $div(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$div$(this, b, op);
               }

               public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$colon$up$(this, b, op);
               }

               public final Object dot(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.dot$(this, b, op);
               }

               public final Object unary_$bang(final UFunc.UImpl op) {
                  return ImmutableNumericOps.unary_$bang$(this, op);
               }

               public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
               }

               public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
               }

               public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
               }

               public final Object $amp(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$amp$(this, b, op);
               }

               public final Object $bar(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bar$(this, b, op);
               }

               public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$up$up$(this, b, op);
               }

               public final Object $times(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$times$(this, b, op);
               }

               public final Object t(final CanTranspose op) {
                  return ImmutableNumericOps.t$(this, op);
               }

               public Object $bslash(final Object b, final UFunc.UImpl2 op) {
                  return ImmutableNumericOps.$bslash$(this, b, op);
               }

               public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
                  return ImmutableNumericOps.t$(this, a, b, op, canSlice);
               }

               public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
                  return ImmutableNumericOps.t$(this, a, op, canSlice);
               }

               public IndexedSeq findAll(final Function1 f) {
                  return QuasiTensor.findAll$(this, f);
               }

               public IndexedSeq findAll$mcD$sp(final Function1 f) {
                  return QuasiTensor.findAll$mcD$sp$(this, f);
               }

               public IndexedSeq findAll$mcF$sp(final Function1 f) {
                  return QuasiTensor.findAll$mcF$sp$(this, f);
               }

               public IndexedSeq findAll$mcI$sp(final Function1 f) {
                  return QuasiTensor.findAll$mcI$sp$(this, f);
               }

               public IndexedSeq findAll$mcJ$sp(final Function1 f) {
                  return QuasiTensor.findAll$mcJ$sp$(this, f);
               }

               public Object default() {
                  return this.from$1.default();
               }

               public Counter2ProjectionMap data() {
                  return this.data;
               }

               public {
                  this.from$1 = from$1;
                  QuasiTensor.$init$(this);
                  ImmutableNumericOps.$init$(this);
                  NumericOps.$init$(this);
                  TensorLike.$init$(this);
                  CounterLike.$init$(this);
                  this.data = new Counter2ProjectionMap(from$1, col$1);
               }
            };
         }
      };
   }

   public CanCollapseAxis canMapRows(final Zero evidence$9, final Semiring evidence$10) {
      return new CanCollapseAxis(evidence$9) {
         private final Zero evidence$9$1;

         public Counter2 apply(final Counter2 from, final Axis._0$ axis, final Function1 f) {
            Counter2 result = Counter2$.MODULE$.apply(this.evidence$9$1);
            ((IterableOnceOps)from.keySet().map((x$6) -> x$6._2())).foreach((dom) -> (Counter)((NumericOps)result.apply(scala.package..MODULE$.$colon$colon(), dom, Counter2$.MODULE$.canSliceCol())).$colon$eq(f.apply(from.apply(scala.package..MODULE$.$colon$colon(), dom, Counter2$.MODULE$.canSliceCol())), Counter$.MODULE$.impl_OpSet_InPlace_C_C()));
            return result;
         }

         public {
            this.evidence$9$1 = evidence$9$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanCollapseAxis.HandHold handholdCanMapRows() {
      return new CanCollapseAxis.HandHold();
   }

   public CanCollapseAxis canMapCols(final ClassTag evidence$11, final Zero evidence$12, final Semiring evidence$13) {
      return new CanCollapseAxis(evidence$12) {
         private final Zero evidence$12$1;

         public Counter2 apply(final Counter2 from, final Axis._1$ axis, final Function1 f) {
            Counter2 result = Counter2$.MODULE$.apply(this.evidence$12$1);
            from.data().withFilter((check$ifrefutable$6) -> BoxesRunTime.boxToBoolean($anonfun$apply$6(check$ifrefutable$6))).foreach((x$7) -> {
               if (x$7 != null) {
                  Object dom = x$7._1();
                  Counter c = (Counter)x$7._2();
                  Counter var3 = (Counter)((NumericOps)result.apply(dom, scala.package..MODULE$.$colon$colon(), Counter2$.MODULE$.canSliceRow())).$colon$eq(f.apply(c), Counter$.MODULE$.impl_OpSet_InPlace_C_C());
                  return var3;
               } else {
                  throw new MatchError(x$7);
               }
            });
            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$6(final Tuple2 check$ifrefutable$6) {
            boolean var1;
            if (check$ifrefutable$6 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public {
            this.evidence$12$1 = evidence$12$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanCollapseAxis.HandHold handholdCanMapCols() {
      return new CanCollapseAxis.HandHold();
   }

   public ScalarOf scalarOf() {
      return ScalarOf$.MODULE$.dummy();
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$3(final Counter2 rv$1, final Semiring evidence$5$1, final Tuple3 x0$1) {
      if (x0$1 != null) {
         Object k1 = x0$1._1();
         Object k2 = x0$1._2();
         Object v = x0$1._3();
         rv$1.update(k1, k2, ((Semiring)scala.Predef..MODULE$.implicitly(evidence$5$1)).$plus(rv$1.apply(k1, k2), v));
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$count$1(final Counter2 rv$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object k1 = x0$1._1();
         Object k2 = x0$1._2();
         rv$2.update(k1, k2, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(rv$2.apply(k1, k2)) + 1));
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private Counter2$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
