package breeze.linalg;

import breeze.collection.mutable.SparseArray;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Predef.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public class SparseVector$mcD$sp extends SparseVector implements VectorLike$mcD$sp {
   private static final long serialVersionUID = 1L;
   public final SparseArray array$mcD$sp;
   public final Zero zero$mcD$sp;

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcD$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcD$sp.map$mcD$sp$(this, fn, canMapValues);
   }

   public void foreach(final Function1 fn) {
      VectorLike$mcD$sp.foreach$(this, fn);
   }

   public void foreach$mcD$sp(final Function1 fn) {
      VectorLike$mcD$sp.foreach$mcD$sp$(this, fn);
   }

   public Object apply(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike$mcID$sp.apply$(this, a, b, c, slice, canSlice);
   }

   public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike$mcID$sp.apply$mcI$sp$(this, a, b, c, slice, canSlice);
   }

   public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcID$sp.mapPairs$(this, f, bf);
   }

   public Object mapPairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcID$sp.mapPairs$mcID$sp$(this, f, bf);
   }

   public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcID$sp.mapActivePairs$(this, f, bf);
   }

   public Object mapActivePairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcID$sp.mapActivePairs$mcID$sp$(this, f, bf);
   }

   public Object mapValues(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcID$sp.mapValues$(this, f, bf);
   }

   public Object mapValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcID$sp.mapValues$mcD$sp$(this, f, bf);
   }

   public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcID$sp.mapActiveValues$(this, f, bf);
   }

   public Object mapActiveValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcID$sp.mapActiveValues$mcD$sp$(this, f, bf);
   }

   public void foreachKey(final Function1 fn) {
      TensorLike$mcID$sp.foreachKey$(this, fn);
   }

   public void foreachKey$mcI$sp(final Function1 fn) {
      TensorLike$mcID$sp.foreachKey$mcI$sp$(this, fn);
   }

   public void foreachPair(final Function2 fn) {
      TensorLike$mcID$sp.foreachPair$(this, fn);
   }

   public void foreachPair$mcID$sp(final Function2 fn) {
      TensorLike$mcID$sp.foreachPair$mcID$sp$(this, fn);
   }

   public void foreachValue(final Function1 fn) {
      TensorLike$mcID$sp.foreachValue$(this, fn);
   }

   public void foreachValue$mcD$sp(final Function1 fn) {
      TensorLike$mcID$sp.foreachValue$mcD$sp$(this, fn);
   }

   public boolean forall(final Function2 fn) {
      return TensorLike$mcID$sp.forall$(this, (Function2)fn);
   }

   public boolean forall$mcID$sp(final Function2 fn) {
      return TensorLike$mcID$sp.forall$mcID$sp$(this, fn);
   }

   public boolean forall(final Function1 fn) {
      return TensorLike$mcID$sp.forall$(this, (Function1)fn);
   }

   public boolean forall$mcD$sp(final Function1 fn) {
      return TensorLike$mcID$sp.forall$mcD$sp$(this, fn);
   }

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor$mcID$sp.findAll$(this, f);
   }

   public IndexedSeq findAll$mcD$sp(final Function1 f) {
      return QuasiTensor$mcID$sp.findAll$mcD$sp$(this, f);
   }

   public SparseArray array$mcD$sp() {
      return this.array$mcD$sp;
   }

   public SparseArray array() {
      return this.array$mcD$sp();
   }

   public double[] data() {
      return this.data$mcD$sp();
   }

   public double[] data$mcD$sp() {
      return this.array().data$mcD$sp();
   }

   public SparseVector repr() {
      return this.repr$mcD$sp();
   }

   public SparseVector repr$mcD$sp() {
      return this;
   }

   public double apply(final int i) {
      return this.apply$mcD$sp(i);
   }

   public double apply$mcD$sp(final int i) {
      if (i >= 0 && i < this.size()) {
         return this.array().apply$mcD$sp(i);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [0,").append(this.size()).append(")").toString());
      }
   }

   public void update(final int i, final double v) {
      this.update$mcD$sp(i, v);
   }

   public void update$mcD$sp(final int i, final double v) {
      if (i >= 0 && i < this.size()) {
         this.array().update$mcD$sp(i, v);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [0,").append(this.size()).append(")").toString());
      }
   }

   public final double otherApply(final int i) {
      return this.otherApply$mcD$sp(i);
   }

   public final double otherApply$mcD$sp(final int i) {
      return this.apply$mcD$sp(i);
   }

   public double default() {
      return this.default$mcD$sp();
   }

   public double default$mcD$sp() {
      return this.zero$mcD$sp.zero$mcD$sp();
   }

   public SparseVector copy() {
      return this.copy$mcD$sp();
   }

   public SparseVector copy$mcD$sp() {
      return new SparseVector$mcD$sp((int[])ArrayUtil$.MODULE$.copyOf(this.index(), this.index().length), (double[])ArrayUtil$.MODULE$.copyOf(this.data$mcD$sp(), this.index().length), this.activeSize(), this.size(), this.zero$mcD$sp);
   }

   public void use(final int[] index, final double[] data, final int activeSize) {
      this.use$mcD$sp(index, data, activeSize);
   }

   public void use$mcD$sp(final int[] index, final double[] data, final int activeSize) {
      .MODULE$.require(activeSize <= this.size(), () -> "Can't have more elements in the array than length!");
      .MODULE$.require(activeSize >= 0, () -> "activeSize must be non-negative");
      .MODULE$.require(data.length >= activeSize, () -> "activeSize must be no greater than array length...");
      this.array().use$mcD$sp(index, data, activeSize);
   }

   public double valueAt(final int i) {
      return this.valueAt$mcD$sp(i);
   }

   public double valueAt$mcD$sp(final int i) {
      return this.data$mcD$sp()[i];
   }

   public CSCMatrix asCscRow(final ClassTag man) {
      return this.asCscRow$mcD$sp(man);
   }

   public CSCMatrix asCscRow$mcD$sp(final ClassTag man) {
      Object var10000;
      if (this.index().length == 0) {
         var10000 = CSCMatrix$.MODULE$.zeros$mDc$sp(1, this.length(), man, this.zero$mcD$sp);
      } else {
         IntRef ii = IntRef.create(0);
         int[] nIndex = (int[])scala.Array..MODULE$.tabulate(this.length() + 1, (JFunction1.mcII.sp)(cp) -> {
            int var10000;
            if (ii.elem < this.used() && cp == this.index()[ii.elem]) {
               ++ii.elem;
               var10000 = ii.elem - 1;
            } else {
               var10000 = ii.elem;
            }

            return var10000;
         }, scala.reflect.ClassTag..MODULE$.Int());
         .MODULE$.assert(ii.elem == this.used());
         var10000 = new CSCMatrix$mcD$sp(this.data$mcD$sp(), 1, this.length(), nIndex, this.activeSize(), (int[])scala.Array..MODULE$.fill(this.data$mcD$sp().length, (JFunction0.mcI.sp)() -> 0, scala.reflect.ClassTag..MODULE$.Int()), this.zero$mcD$sp);
      }

      return (CSCMatrix)var10000;
   }

   public CSCMatrix asCscColumn(final ClassTag man) {
      return this.asCscColumn$mcD$sp(man);
   }

   public CSCMatrix asCscColumn$mcD$sp(final ClassTag man) {
      return (CSCMatrix)(this.index().length == 0 ? CSCMatrix$.MODULE$.zeros$mDc$sp(this.length(), 1, man, this.zero$mcD$sp) : new CSCMatrix$mcD$sp((double[])this.data$mcD$sp().clone(), this.length(), 1, new int[]{0, this.used()}, this.activeSize(), this.index(), this.zero$mcD$sp));
   }

   public boolean specInstance$() {
      return true;
   }

   public SparseVector$mcD$sp(final SparseArray array$mcD$sp, final Zero zero$mcD$sp) {
      super((SparseArray)null, zero$mcD$sp);
      this.array$mcD$sp = array$mcD$sp;
      this.zero$mcD$sp = zero$mcD$sp;
   }

   public SparseVector$mcD$sp(final int[] index, final double[] data$mcD$sp, final int activeSize, final int length, final Zero value$mcD$sp) {
      this(new SparseArray(index, data$mcD$sp, activeSize, length, value$mcD$sp.zero()), value$mcD$sp);
   }

   public SparseVector$mcD$sp(final int[] index, final double[] data$mcD$sp, final int length, final Zero value$mcD$sp) {
      this(index, data$mcD$sp, index.length, length, value$mcD$sp);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
