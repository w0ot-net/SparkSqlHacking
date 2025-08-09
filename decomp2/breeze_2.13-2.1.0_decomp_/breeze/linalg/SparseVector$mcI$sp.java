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

public class SparseVector$mcI$sp extends SparseVector implements VectorLike$mcI$sp {
   private static final long serialVersionUID = 1L;
   public final SparseArray array$mcI$sp;
   public final Zero zero$mcI$sp;

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcI$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcI$sp.map$mcI$sp$(this, fn, canMapValues);
   }

   public void foreach(final Function1 fn) {
      VectorLike$mcI$sp.foreach$(this, fn);
   }

   public void foreach$mcI$sp(final Function1 fn) {
      VectorLike$mcI$sp.foreach$mcI$sp$(this, fn);
   }

   public Object apply(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike$mcII$sp.apply$(this, a, b, c, slice, canSlice);
   }

   public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike$mcII$sp.apply$mcI$sp$(this, a, b, c, slice, canSlice);
   }

   public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcII$sp.mapPairs$(this, f, bf);
   }

   public Object mapPairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcII$sp.mapPairs$mcII$sp$(this, f, bf);
   }

   public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcII$sp.mapActivePairs$(this, f, bf);
   }

   public Object mapActivePairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike$mcII$sp.mapActivePairs$mcII$sp$(this, f, bf);
   }

   public Object mapValues(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcII$sp.mapValues$(this, f, bf);
   }

   public Object mapValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcII$sp.mapValues$mcI$sp$(this, f, bf);
   }

   public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcII$sp.mapActiveValues$(this, f, bf);
   }

   public Object mapActiveValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike$mcII$sp.mapActiveValues$mcI$sp$(this, f, bf);
   }

   public void foreachKey(final Function1 fn) {
      TensorLike$mcII$sp.foreachKey$(this, fn);
   }

   public void foreachKey$mcI$sp(final Function1 fn) {
      TensorLike$mcII$sp.foreachKey$mcI$sp$(this, fn);
   }

   public void foreachPair(final Function2 fn) {
      TensorLike$mcII$sp.foreachPair$(this, fn);
   }

   public void foreachPair$mcII$sp(final Function2 fn) {
      TensorLike$mcII$sp.foreachPair$mcII$sp$(this, fn);
   }

   public void foreachValue(final Function1 fn) {
      TensorLike$mcII$sp.foreachValue$(this, fn);
   }

   public void foreachValue$mcI$sp(final Function1 fn) {
      TensorLike$mcII$sp.foreachValue$mcI$sp$(this, fn);
   }

   public boolean forall(final Function2 fn) {
      return TensorLike$mcII$sp.forall$(this, (Function2)fn);
   }

   public boolean forall$mcII$sp(final Function2 fn) {
      return TensorLike$mcII$sp.forall$mcII$sp$(this, fn);
   }

   public boolean forall(final Function1 fn) {
      return TensorLike$mcII$sp.forall$(this, (Function1)fn);
   }

   public boolean forall$mcI$sp(final Function1 fn) {
      return TensorLike$mcII$sp.forall$mcI$sp$(this, fn);
   }

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor$mcII$sp.findAll$(this, f);
   }

   public IndexedSeq findAll$mcI$sp(final Function1 f) {
      return QuasiTensor$mcII$sp.findAll$mcI$sp$(this, f);
   }

   public SparseArray array$mcI$sp() {
      return this.array$mcI$sp;
   }

   public SparseArray array() {
      return this.array$mcI$sp();
   }

   public int[] data() {
      return this.data$mcI$sp();
   }

   public int[] data$mcI$sp() {
      return this.array().data$mcI$sp();
   }

   public SparseVector repr() {
      return this.repr$mcI$sp();
   }

   public SparseVector repr$mcI$sp() {
      return this;
   }

   public int apply(final int i) {
      return this.apply$mcI$sp(i);
   }

   public int apply$mcI$sp(final int i) {
      if (i >= 0 && i < this.size()) {
         return this.array().apply$mcI$sp(i);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [0,").append(this.size()).append(")").toString());
      }
   }

   public void update(final int i, final int v) {
      this.update$mcI$sp(i, v);
   }

   public void update$mcI$sp(final int i, final int v) {
      if (i >= 0 && i < this.size()) {
         this.array().update$mcI$sp(i, v);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [0,").append(this.size()).append(")").toString());
      }
   }

   public final int otherApply(final int i) {
      return this.otherApply$mcI$sp(i);
   }

   public final int otherApply$mcI$sp(final int i) {
      return this.apply$mcI$sp(i);
   }

   public int default() {
      return this.default$mcI$sp();
   }

   public int default$mcI$sp() {
      return this.zero$mcI$sp.zero$mcI$sp();
   }

   public SparseVector copy() {
      return this.copy$mcI$sp();
   }

   public SparseVector copy$mcI$sp() {
      return new SparseVector$mcI$sp((int[])ArrayUtil$.MODULE$.copyOf(this.index(), this.index().length), (int[])ArrayUtil$.MODULE$.copyOf(this.data$mcI$sp(), this.index().length), this.activeSize(), this.size(), this.zero$mcI$sp);
   }

   public void use(final int[] index, final int[] data, final int activeSize) {
      this.use$mcI$sp(index, data, activeSize);
   }

   public void use$mcI$sp(final int[] index, final int[] data, final int activeSize) {
      .MODULE$.require(activeSize <= this.size(), () -> "Can't have more elements in the array than length!");
      .MODULE$.require(activeSize >= 0, () -> "activeSize must be non-negative");
      .MODULE$.require(data.length >= activeSize, () -> "activeSize must be no greater than array length...");
      this.array().use$mcI$sp(index, data, activeSize);
   }

   public int valueAt(final int i) {
      return this.valueAt$mcI$sp(i);
   }

   public int valueAt$mcI$sp(final int i) {
      return this.data$mcI$sp()[i];
   }

   public CSCMatrix asCscRow(final ClassTag man) {
      return this.asCscRow$mcI$sp(man);
   }

   public CSCMatrix asCscRow$mcI$sp(final ClassTag man) {
      Object var10000;
      if (this.index().length == 0) {
         var10000 = CSCMatrix$.MODULE$.zeros$mIc$sp(1, this.length(), man, this.zero$mcI$sp);
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
         var10000 = new CSCMatrix$mcI$sp(this.data$mcI$sp(), 1, this.length(), nIndex, this.activeSize(), (int[])scala.Array..MODULE$.fill(this.data$mcI$sp().length, (JFunction0.mcI.sp)() -> 0, scala.reflect.ClassTag..MODULE$.Int()), this.zero$mcI$sp);
      }

      return (CSCMatrix)var10000;
   }

   public CSCMatrix asCscColumn(final ClassTag man) {
      return this.asCscColumn$mcI$sp(man);
   }

   public CSCMatrix asCscColumn$mcI$sp(final ClassTag man) {
      return (CSCMatrix)(this.index().length == 0 ? CSCMatrix$.MODULE$.zeros$mIc$sp(this.length(), 1, man, this.zero$mcI$sp) : new CSCMatrix$mcI$sp((int[])this.data$mcI$sp().clone(), this.length(), 1, new int[]{0, this.used()}, this.activeSize(), this.index(), this.zero$mcI$sp));
   }

   public boolean specInstance$() {
      return true;
   }

   public SparseVector$mcI$sp(final SparseArray array$mcI$sp, final Zero zero$mcI$sp) {
      super((SparseArray)null, zero$mcI$sp);
      this.array$mcI$sp = array$mcI$sp;
      this.zero$mcI$sp = zero$mcI$sp;
   }

   public SparseVector$mcI$sp(final int[] index, final int[] data$mcI$sp, final int activeSize, final int length, final Zero value$mcI$sp) {
      this(new SparseArray(index, data$mcI$sp, activeSize, length, value$mcI$sp.zero()), value$mcI$sp);
   }

   public SparseVector$mcI$sp(final int[] index, final int[] data$mcI$sp, final int length, final Zero value$mcI$sp) {
      this(index, data$mcI$sp, index.length, length, value$mcI$sp);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
