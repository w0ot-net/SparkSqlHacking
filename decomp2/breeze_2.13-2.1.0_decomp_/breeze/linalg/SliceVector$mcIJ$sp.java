package breeze.linalg;

import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public class SliceVector$mcIJ$sp extends SliceVector implements VectorLike$mcJ$sp {
   public final Tensor tensor$mcIJ$sp;
   private final ClassTag evidence$1;

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

   public Tensor tensor$mcIJ$sp() {
      return this.tensor$mcIJ$sp;
   }

   public Tensor tensor() {
      return this.tensor$mcIJ$sp();
   }

   public long apply(final int i) {
      return this.apply$mcJ$sp(i);
   }

   public long apply$mcJ$sp(final int i) {
      return this.tensor().apply$mcIJ$sp(BoxesRunTime.unboxToInt(this.slices().apply(i)));
   }

   public void update(final int i, final long v) {
      this.update$mcJ$sp(i, v);
   }

   public void update$mcJ$sp(final int i, final long v) {
      this.tensor().update$mcIJ$sp(BoxesRunTime.unboxToInt(this.slices().apply(i)), v);
   }

   public DenseVector copy() {
      return this.copy$mcJ$sp();
   }

   public DenseVector copy$mcJ$sp() {
      return (DenseVector)DenseVector$.MODULE$.apply((Seq)this.slices().map((JFunction1.mcJI.sp)(i) -> this.tensor().apply$mcIJ$sp(i)), this.breeze$linalg$SliceVector$$evidence$1);
   }

   public SliceVector repr() {
      return this.repr$mcIJ$sp();
   }

   public SliceVector repr$mcIJ$sp() {
      return this;
   }

   public boolean specInstance$() {
      return true;
   }

   public SliceVector$mcIJ$sp(final Tensor tensor$mcIJ$sp, final IndexedSeq slices, final ClassTag evidence$1) {
      super((Tensor)null, slices, evidence$1);
      this.tensor$mcIJ$sp = tensor$mcIJ$sp;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
