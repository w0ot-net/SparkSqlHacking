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

public class SliceVector$mcID$sp extends SliceVector implements Vector$mcD$sp {
   public final Tensor tensor$mcID$sp;
   private final ClassTag evidence$1;

   public DenseVector toDenseVector(final ClassTag cm) {
      return Vector$mcD$sp.toDenseVector$(this, cm);
   }

   public DenseVector toDenseVector$mcD$sp(final ClassTag cm) {
      return Vector$mcD$sp.toDenseVector$mcD$sp$(this, cm);
   }

   public double[] toArray(final ClassTag cm) {
      return Vector$mcD$sp.toArray$(this, cm);
   }

   public double[] toArray$mcD$sp(final ClassTag cm) {
      return Vector$mcD$sp.toArray$mcD$sp$(this, cm);
   }

   public Vector toVector(final ClassTag cm) {
      return Vector$mcD$sp.toVector$(this, cm);
   }

   public Vector toVector$mcD$sp(final ClassTag cm) {
      return Vector$mcD$sp.toVector$mcD$sp$(this, cm);
   }

   public Vector padTo(final int len, final double elem, final ClassTag cm) {
      return Vector$mcD$sp.padTo$(this, len, elem, cm);
   }

   public Vector padTo$mcD$sp(final int len, final double elem, final ClassTag cm) {
      return Vector$mcD$sp.padTo$mcD$sp$(this, len, elem, cm);
   }

   public boolean exists(final Function1 f) {
      return Vector$mcD$sp.exists$(this, f);
   }

   public boolean exists$mcD$sp(final Function1 f) {
      return Vector$mcD$sp.exists$mcD$sp$(this, f);
   }

   public boolean forall(final Function1 f) {
      return Vector$mcD$sp.forall$(this, f);
   }

   public boolean forall$mcD$sp(final Function1 f) {
      return Vector$mcD$sp.forall$mcD$sp$(this, f);
   }

   public Object fold(final Object z, final Function2 op) {
      return Vector$mcD$sp.fold$(this, z, op);
   }

   public Object fold$mcD$sp(final Object z, final Function2 op) {
      return Vector$mcD$sp.fold$mcD$sp$(this, z, op);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return Vector$mcD$sp.foldLeft$(this, z, op);
   }

   public Object foldLeft$mcD$sp(final Object z, final Function2 op) {
      return Vector$mcD$sp.foldLeft$mcD$sp$(this, z, op);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return Vector$mcD$sp.foldRight$(this, z, op);
   }

   public Object foldRight$mcD$sp(final Object z, final Function2 op) {
      return Vector$mcD$sp.foldRight$mcD$sp$(this, z, op);
   }

   public Object reduce(final Function2 op) {
      return Vector$mcD$sp.reduce$(this, op);
   }

   public Object reduce$mcD$sp(final Function2 op) {
      return Vector$mcD$sp.reduce$mcD$sp$(this, op);
   }

   public Object reduceLeft(final Function2 op) {
      return Vector$mcD$sp.reduceLeft$(this, op);
   }

   public Object reduceLeft$mcD$sp(final Function2 op) {
      return Vector$mcD$sp.reduceLeft$mcD$sp$(this, op);
   }

   public Object reduceRight(final Function2 op) {
      return Vector$mcD$sp.reduceRight$(this, op);
   }

   public Object reduceRight$mcD$sp(final Function2 op) {
      return Vector$mcD$sp.reduceRight$mcD$sp$(this, op);
   }

   public Vector scan(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector$mcD$sp.scan$(this, z, op, cm, cm1);
   }

   public Vector scan$mcD$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector$mcD$sp.scan$mcD$sp$(this, z, op, cm, cm1);
   }

   public Vector scanLeft(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcD$sp.scanLeft$(this, z, op, cm1);
   }

   public Vector scanLeft$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcD$sp.scanLeft$mcD$sp$(this, z, op, cm1);
   }

   public Vector scanRight(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcD$sp.scanRight$(this, z, op, cm1);
   }

   public Vector scanRight$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcD$sp.scanRight$mcD$sp$(this, z, op, cm1);
   }

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

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor$mcID$sp.findAll$(this, f);
   }

   public IndexedSeq findAll$mcD$sp(final Function1 f) {
      return QuasiTensor$mcID$sp.findAll$mcD$sp$(this, f);
   }

   public Tensor tensor$mcID$sp() {
      return this.tensor$mcID$sp;
   }

   public Tensor tensor() {
      return this.tensor$mcID$sp();
   }

   public double apply(final int i) {
      return this.apply$mcD$sp(i);
   }

   public double apply$mcD$sp(final int i) {
      return this.tensor().apply$mcID$sp(BoxesRunTime.unboxToInt(this.slices().apply(i)));
   }

   public void update(final int i, final double v) {
      this.update$mcD$sp(i, v);
   }

   public void update$mcD$sp(final int i, final double v) {
      this.tensor().update$mcID$sp(BoxesRunTime.unboxToInt(this.slices().apply(i)), v);
   }

   public DenseVector copy() {
      return this.copy$mcD$sp();
   }

   public DenseVector copy$mcD$sp() {
      return (DenseVector)DenseVector$.MODULE$.apply((Seq)this.slices().map((JFunction1.mcDI.sp)(i) -> this.tensor().apply$mcID$sp(i)), this.breeze$linalg$SliceVector$$evidence$1);
   }

   public SliceVector repr() {
      return this.repr$mcID$sp();
   }

   public SliceVector repr$mcID$sp() {
      return this;
   }

   public boolean specInstance$() {
      return true;
   }

   public SliceVector$mcID$sp(final Tensor tensor$mcID$sp, final IndexedSeq slices, final ClassTag evidence$1) {
      super((Tensor)null, slices, evidence$1);
      this.tensor$mcID$sp = tensor$mcID$sp;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
