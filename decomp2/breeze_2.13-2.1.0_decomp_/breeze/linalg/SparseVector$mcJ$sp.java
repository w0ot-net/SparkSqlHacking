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

public class SparseVector$mcJ$sp extends SparseVector implements VectorLike$mcJ$sp {
   private static final long serialVersionUID = 1L;
   public final SparseArray array$mcJ$sp;
   public final Zero zero$mcJ$sp;

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcJ$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcJ$sp.map$mcJ$sp$(this, fn, canMapValues);
   }

   public void foreach(final Function1 fn) {
      VectorLike$mcJ$sp.foreach$(this, fn);
   }

   public void foreach$mcJ$sp(final Function1 fn) {
      VectorLike$mcJ$sp.foreach$mcJ$sp$(this, fn);
   }

   public Object apply(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike$mcIJ$sp.apply$(this, a, b, c, slice, canSlice);
   }

   public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike$mcIJ$sp.apply$mcI$sp$(this, a, b, c, slice, canSlice);
   }

   public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcIJ$sp.mapPairs$(this, f, bf);
   }

   public Object mapPairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcIJ$sp.mapPairs$mcIJ$sp$(this, f, bf);
   }

   public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcIJ$sp.mapActivePairs$(this, f, bf);
   }

   public Object mapActivePairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcIJ$sp.mapActivePairs$mcIJ$sp$(this, f, bf);
   }

   public Object mapValues(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcIJ$sp.mapValues$(this, f, bf);
   }

   public Object mapValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcIJ$sp.mapValues$mcJ$sp$(this, f, bf);
   }

   public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcIJ$sp.mapActiveValues$(this, f, bf);
   }

   public Object mapActiveValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcIJ$sp.mapActiveValues$mcJ$sp$(this, f, bf);
   }

   public void foreachKey(final Function1 fn) {
      TensorLike$mcIJ$sp.foreachKey$(this, fn);
   }

   public void foreachKey$mcI$sp(final Function1 fn) {
      TensorLike$mcIJ$sp.foreachKey$mcI$sp$(this, fn);
   }

   public void foreachPair(final Function2 fn) {
      TensorLike$mcIJ$sp.foreachPair$(this, fn);
   }

   public void foreachPair$mcIJ$sp(final Function2 fn) {
      TensorLike$mcIJ$sp.foreachPair$mcIJ$sp$(this, fn);
   }

   public void foreachValue(final Function1 fn) {
      TensorLike$mcIJ$sp.foreachValue$(this, fn);
   }

   public void foreachValue$mcJ$sp(final Function1 fn) {
      TensorLike$mcIJ$sp.foreachValue$mcJ$sp$(this, fn);
   }

   public boolean forall(final Function2 fn) {
      return TensorLike$mcIJ$sp.forall$(this, (Function2)fn);
   }

   public boolean forall$mcIJ$sp(final Function2 fn) {
      return TensorLike$mcIJ$sp.forall$mcIJ$sp$(this, fn);
   }

   public boolean forall(final Function1 fn) {
      return TensorLike$mcIJ$sp.forall$(this, (Function1)fn);
   }

   public boolean forall$mcJ$sp(final Function1 fn) {
      return TensorLike$mcIJ$sp.forall$mcJ$sp$(this, fn);
   }

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor$mcIJ$sp.findAll$(this, f);
   }

   public IndexedSeq findAll$mcJ$sp(final Function1 f) {
      return QuasiTensor$mcIJ$sp.findAll$mcJ$sp$(this, f);
   }

   public SparseArray array$mcJ$sp() {
      return this.array$mcJ$sp;
   }

   public SparseArray array() {
      return this.array$mcJ$sp();
   }

   public long[] data() {
      return this.data$mcJ$sp();
   }

   public long[] data$mcJ$sp() {
      return this.array().data$mcJ$sp();
   }

   public SparseVector repr() {
      return this.repr$mcJ$sp();
   }

   public SparseVector repr$mcJ$sp() {
      return this;
   }

   public long apply(final int i) {
      return this.apply$mcJ$sp(i);
   }

   public long apply$mcJ$sp(final int i) {
      if (i >= 0 && i < this.size()) {
         return this.array().apply$mcJ$sp(i);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [0,").append(this.size()).append(")").toString());
      }
   }

   public void update(final int i, final long v) {
      this.update$mcJ$sp(i, v);
   }

   public void update$mcJ$sp(final int i, final long v) {
      if (i >= 0 && i < this.size()) {
         this.array().update$mcJ$sp(i, v);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [0,").append(this.size()).append(")").toString());
      }
   }

   public final long otherApply(final int i) {
      return this.otherApply$mcJ$sp(i);
   }

   public final long otherApply$mcJ$sp(final int i) {
      return this.apply$mcJ$sp(i);
   }

   public long default() {
      return this.default$mcJ$sp();
   }

   public long default$mcJ$sp() {
      return this.zero$mcJ$sp.zero$mcJ$sp();
   }

   public SparseVector copy() {
      return this.copy$mcJ$sp();
   }

   public SparseVector copy$mcJ$sp() {
      return new SparseVector$mcJ$sp((int[])ArrayUtil$.MODULE$.copyOf(this.index(), this.index().length), (long[])ArrayUtil$.MODULE$.copyOf(this.data$mcJ$sp(), this.index().length), this.activeSize(), this.size(), this.zero$mcJ$sp);
   }

   public void use(final int[] index, final long[] data, final int activeSize) {
      this.use$mcJ$sp(index, data, activeSize);
   }

   public void use$mcJ$sp(final int[] index, final long[] data, final int activeSize) {
      .MODULE$.require(activeSize <= this.size(), () -> "Can't have more elements in the array than length!");
      .MODULE$.require(activeSize >= 0, () -> "activeSize must be non-negative");
      .MODULE$.require(data.length >= activeSize, () -> "activeSize must be no greater than array length...");
      this.array().use$mcJ$sp(index, data, activeSize);
   }

   public long valueAt(final int i) {
      return this.valueAt$mcJ$sp(i);
   }

   public long valueAt$mcJ$sp(final int i) {
      return this.data$mcJ$sp()[i];
   }

   public CSCMatrix asCscRow(final ClassTag man) {
      return this.asCscRow$mcJ$sp(man);
   }

   public CSCMatrix asCscRow$mcJ$sp(final ClassTag man) {
      Object var10000;
      if (this.index().length == 0) {
         var10000 = CSCMatrix$.MODULE$.zeros$mJc$sp(1, this.length(), man, this.zero$mcJ$sp);
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
         var10000 = new CSCMatrix$mcJ$sp(this.data$mcJ$sp(), 1, this.length(), nIndex, this.activeSize(), (int[])scala.Array..MODULE$.fill(this.data$mcJ$sp().length, (JFunction0.mcI.sp)() -> 0, scala.reflect.ClassTag..MODULE$.Int()), this.zero$mcJ$sp);
      }

      return (CSCMatrix)var10000;
   }

   public CSCMatrix asCscColumn(final ClassTag man) {
      return this.asCscColumn$mcJ$sp(man);
   }

   public CSCMatrix asCscColumn$mcJ$sp(final ClassTag man) {
      return (CSCMatrix)(this.index().length == 0 ? CSCMatrix$.MODULE$.zeros$mJc$sp(this.length(), 1, man, this.zero$mcJ$sp) : new CSCMatrix$mcJ$sp((long[])this.data$mcJ$sp().clone(), this.length(), 1, new int[]{0, this.used()}, this.activeSize(), this.index(), this.zero$mcJ$sp));
   }

   public boolean specInstance$() {
      return true;
   }

   public SparseVector$mcJ$sp(final SparseArray array$mcJ$sp, final Zero zero$mcJ$sp) {
      super((SparseArray)null, zero$mcJ$sp);
      this.array$mcJ$sp = array$mcJ$sp;
      this.zero$mcJ$sp = zero$mcJ$sp;
   }

   public SparseVector$mcJ$sp(final int[] index, final long[] data$mcJ$sp, final int activeSize, final int length, final Zero value$mcJ$sp) {
      this(new SparseArray(index, data$mcJ$sp, activeSize, length, value$mcJ$sp.zero()), value$mcJ$sp);
   }

   public SparseVector$mcJ$sp(final int[] index, final long[] data$mcJ$sp, final int length, final Zero value$mcJ$sp) {
      this(index, data$mcJ$sp, index.length, length, value$mcJ$sp);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
