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

public class SparseVector$mcF$sp extends SparseVector implements VectorLike$mcF$sp {
   private static final long serialVersionUID = 1L;
   public final SparseArray array$mcF$sp;
   public final Zero zero$mcF$sp;

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcF$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcF$sp.map$mcF$sp$(this, fn, canMapValues);
   }

   public void foreach(final Function1 fn) {
      VectorLike$mcF$sp.foreach$(this, fn);
   }

   public void foreach$mcF$sp(final Function1 fn) {
      VectorLike$mcF$sp.foreach$mcF$sp$(this, fn);
   }

   public Object apply(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike$mcIF$sp.apply$(this, a, b, c, slice, canSlice);
   }

   public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike$mcIF$sp.apply$mcI$sp$(this, a, b, c, slice, canSlice);
   }

   public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcIF$sp.mapPairs$(this, f, bf);
   }

   public Object mapPairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcIF$sp.mapPairs$mcIF$sp$(this, f, bf);
   }

   public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcIF$sp.mapActivePairs$(this, f, bf);
   }

   public Object mapActivePairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcIF$sp.mapActivePairs$mcIF$sp$(this, f, bf);
   }

   public Object mapValues(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcIF$sp.mapValues$(this, f, bf);
   }

   public Object mapValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcIF$sp.mapValues$mcF$sp$(this, f, bf);
   }

   public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcIF$sp.mapActiveValues$(this, f, bf);
   }

   public Object mapActiveValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcIF$sp.mapActiveValues$mcF$sp$(this, f, bf);
   }

   public void foreachKey(final Function1 fn) {
      TensorLike$mcIF$sp.foreachKey$(this, fn);
   }

   public void foreachKey$mcI$sp(final Function1 fn) {
      TensorLike$mcIF$sp.foreachKey$mcI$sp$(this, fn);
   }

   public void foreachPair(final Function2 fn) {
      TensorLike$mcIF$sp.foreachPair$(this, fn);
   }

   public void foreachPair$mcIF$sp(final Function2 fn) {
      TensorLike$mcIF$sp.foreachPair$mcIF$sp$(this, fn);
   }

   public void foreachValue(final Function1 fn) {
      TensorLike$mcIF$sp.foreachValue$(this, fn);
   }

   public void foreachValue$mcF$sp(final Function1 fn) {
      TensorLike$mcIF$sp.foreachValue$mcF$sp$(this, fn);
   }

   public boolean forall(final Function2 fn) {
      return TensorLike$mcIF$sp.forall$(this, (Function2)fn);
   }

   public boolean forall$mcIF$sp(final Function2 fn) {
      return TensorLike$mcIF$sp.forall$mcIF$sp$(this, fn);
   }

   public boolean forall(final Function1 fn) {
      return TensorLike$mcIF$sp.forall$(this, (Function1)fn);
   }

   public boolean forall$mcF$sp(final Function1 fn) {
      return TensorLike$mcIF$sp.forall$mcF$sp$(this, fn);
   }

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor$mcIF$sp.findAll$(this, f);
   }

   public IndexedSeq findAll$mcF$sp(final Function1 f) {
      return QuasiTensor$mcIF$sp.findAll$mcF$sp$(this, f);
   }

   public SparseArray array$mcF$sp() {
      return this.array$mcF$sp;
   }

   public SparseArray array() {
      return this.array$mcF$sp();
   }

   public float[] data() {
      return this.data$mcF$sp();
   }

   public float[] data$mcF$sp() {
      return this.array().data$mcF$sp();
   }

   public SparseVector repr() {
      return this.repr$mcF$sp();
   }

   public SparseVector repr$mcF$sp() {
      return this;
   }

   public float apply(final int i) {
      return this.apply$mcF$sp(i);
   }

   public float apply$mcF$sp(final int i) {
      if (i >= 0 && i < this.size()) {
         return this.array().apply$mcF$sp(i);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [0,").append(this.size()).append(")").toString());
      }
   }

   public void update(final int i, final float v) {
      this.update$mcF$sp(i, v);
   }

   public void update$mcF$sp(final int i, final float v) {
      if (i >= 0 && i < this.size()) {
         this.array().update$mcF$sp(i, v);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [0,").append(this.size()).append(")").toString());
      }
   }

   public final float otherApply(final int i) {
      return this.otherApply$mcF$sp(i);
   }

   public final float otherApply$mcF$sp(final int i) {
      return this.apply$mcF$sp(i);
   }

   public float default() {
      return this.default$mcF$sp();
   }

   public float default$mcF$sp() {
      return this.zero$mcF$sp.zero$mcF$sp();
   }

   public SparseVector copy() {
      return this.copy$mcF$sp();
   }

   public SparseVector copy$mcF$sp() {
      return new SparseVector$mcF$sp((int[])ArrayUtil$.MODULE$.copyOf(this.index(), this.index().length), (float[])ArrayUtil$.MODULE$.copyOf(this.data$mcF$sp(), this.index().length), this.activeSize(), this.size(), this.zero$mcF$sp);
   }

   public void use(final int[] index, final float[] data, final int activeSize) {
      this.use$mcF$sp(index, data, activeSize);
   }

   public void use$mcF$sp(final int[] index, final float[] data, final int activeSize) {
      .MODULE$.require(activeSize <= this.size(), () -> "Can't have more elements in the array than length!");
      .MODULE$.require(activeSize >= 0, () -> "activeSize must be non-negative");
      .MODULE$.require(data.length >= activeSize, () -> "activeSize must be no greater than array length...");
      this.array().use$mcF$sp(index, data, activeSize);
   }

   public float valueAt(final int i) {
      return this.valueAt$mcF$sp(i);
   }

   public float valueAt$mcF$sp(final int i) {
      return this.data$mcF$sp()[i];
   }

   public CSCMatrix asCscRow(final ClassTag man) {
      return this.asCscRow$mcF$sp(man);
   }

   public CSCMatrix asCscRow$mcF$sp(final ClassTag man) {
      Object var10000;
      if (this.index().length == 0) {
         var10000 = CSCMatrix$.MODULE$.zeros$mFc$sp(1, this.length(), man, this.zero$mcF$sp);
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
         var10000 = new CSCMatrix$mcF$sp(this.data$mcF$sp(), 1, this.length(), nIndex, this.activeSize(), (int[])scala.Array..MODULE$.fill(this.data$mcF$sp().length, (JFunction0.mcI.sp)() -> 0, scala.reflect.ClassTag..MODULE$.Int()), this.zero$mcF$sp);
      }

      return (CSCMatrix)var10000;
   }

   public CSCMatrix asCscColumn(final ClassTag man) {
      return this.asCscColumn$mcF$sp(man);
   }

   public CSCMatrix asCscColumn$mcF$sp(final ClassTag man) {
      return (CSCMatrix)(this.index().length == 0 ? CSCMatrix$.MODULE$.zeros$mFc$sp(this.length(), 1, man, this.zero$mcF$sp) : new CSCMatrix$mcF$sp((float[])this.data$mcF$sp().clone(), this.length(), 1, new int[]{0, this.used()}, this.activeSize(), this.index(), this.zero$mcF$sp));
   }

   public boolean specInstance$() {
      return true;
   }

   public SparseVector$mcF$sp(final SparseArray array$mcF$sp, final Zero zero$mcF$sp) {
      super((SparseArray)null, zero$mcF$sp);
      this.array$mcF$sp = array$mcF$sp;
      this.zero$mcF$sp = zero$mcF$sp;
   }

   public SparseVector$mcF$sp(final int[] index, final float[] data$mcF$sp, final int activeSize, final int length, final Zero value$mcF$sp) {
      this(new SparseArray(index, data$mcF$sp, activeSize, length, value$mcF$sp.zero()), value$mcF$sp);
   }

   public SparseVector$mcF$sp(final int[] index, final float[] data$mcF$sp, final int length, final Zero value$mcF$sp) {
      this(index, data$mcF$sp, index.length, length, value$mcF$sp);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
