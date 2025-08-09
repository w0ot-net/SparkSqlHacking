package breeze.linalg;

import breeze.collection.mutable.OpenAddressHashArray;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import scala.Function1;
import scala.Function2;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;

public class HashVector$mcF$sp extends HashVector implements Vector$mcF$sp {
   public final OpenAddressHashArray array$mcF$sp;

   public DenseVector toDenseVector(final ClassTag cm) {
      return Vector$mcF$sp.toDenseVector$(this, cm);
   }

   public DenseVector toDenseVector$mcF$sp(final ClassTag cm) {
      return Vector$mcF$sp.toDenseVector$mcF$sp$(this, cm);
   }

   public float[] toArray(final ClassTag cm) {
      return Vector$mcF$sp.toArray$(this, cm);
   }

   public float[] toArray$mcF$sp(final ClassTag cm) {
      return Vector$mcF$sp.toArray$mcF$sp$(this, cm);
   }

   public Vector toVector(final ClassTag cm) {
      return Vector$mcF$sp.toVector$(this, cm);
   }

   public Vector toVector$mcF$sp(final ClassTag cm) {
      return Vector$mcF$sp.toVector$mcF$sp$(this, cm);
   }

   public Vector padTo(final int len, final float elem, final ClassTag cm) {
      return Vector$mcF$sp.padTo$(this, len, elem, cm);
   }

   public Vector padTo$mcF$sp(final int len, final float elem, final ClassTag cm) {
      return Vector$mcF$sp.padTo$mcF$sp$(this, len, elem, cm);
   }

   public boolean exists(final Function1 f) {
      return Vector$mcF$sp.exists$(this, f);
   }

   public boolean exists$mcF$sp(final Function1 f) {
      return Vector$mcF$sp.exists$mcF$sp$(this, f);
   }

   public boolean forall(final Function1 f) {
      return Vector$mcF$sp.forall$(this, f);
   }

   public boolean forall$mcF$sp(final Function1 f) {
      return Vector$mcF$sp.forall$mcF$sp$(this, f);
   }

   public Object fold(final Object z, final Function2 op) {
      return Vector$mcF$sp.fold$(this, z, op);
   }

   public Object fold$mcF$sp(final Object z, final Function2 op) {
      return Vector$mcF$sp.fold$mcF$sp$(this, z, op);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return Vector$mcF$sp.foldLeft$(this, z, op);
   }

   public Object foldLeft$mcF$sp(final Object z, final Function2 op) {
      return Vector$mcF$sp.foldLeft$mcF$sp$(this, z, op);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return Vector$mcF$sp.foldRight$(this, z, op);
   }

   public Object foldRight$mcF$sp(final Object z, final Function2 op) {
      return Vector$mcF$sp.foldRight$mcF$sp$(this, z, op);
   }

   public Object reduce(final Function2 op) {
      return Vector$mcF$sp.reduce$(this, op);
   }

   public Object reduce$mcF$sp(final Function2 op) {
      return Vector$mcF$sp.reduce$mcF$sp$(this, op);
   }

   public Object reduceLeft(final Function2 op) {
      return Vector$mcF$sp.reduceLeft$(this, op);
   }

   public Object reduceLeft$mcF$sp(final Function2 op) {
      return Vector$mcF$sp.reduceLeft$mcF$sp$(this, op);
   }

   public Object reduceRight(final Function2 op) {
      return Vector$mcF$sp.reduceRight$(this, op);
   }

   public Object reduceRight$mcF$sp(final Function2 op) {
      return Vector$mcF$sp.reduceRight$mcF$sp$(this, op);
   }

   public Vector scan(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector$mcF$sp.scan$(this, z, op, cm, cm1);
   }

   public Vector scan$mcF$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector$mcF$sp.scan$mcF$sp$(this, z, op, cm, cm1);
   }

   public Vector scanLeft(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcF$sp.scanLeft$(this, z, op, cm1);
   }

   public Vector scanLeft$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcF$sp.scanLeft$mcF$sp$(this, z, op, cm1);
   }

   public Vector scanRight(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcF$sp.scanRight$(this, z, op, cm1);
   }

   public Vector scanRight$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcF$sp.scanRight$mcF$sp$(this, z, op, cm1);
   }

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

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor$mcIF$sp.findAll$(this, f);
   }

   public IndexedSeq findAll$mcF$sp(final Function1 f) {
      return QuasiTensor$mcIF$sp.findAll$mcF$sp$(this, f);
   }

   public OpenAddressHashArray array$mcF$sp() {
      return this.array$mcF$sp;
   }

   public OpenAddressHashArray array() {
      return this.array$mcF$sp();
   }

   public float apply(final int i) {
      return this.apply$mcF$sp(i);
   }

   public float apply$mcF$sp(final int i) {
      return this.array().apply$mcF$sp(i);
   }

   public void update(final int i, final float v) {
      this.update$mcF$sp(i, v);
   }

   public void update$mcF$sp(final int i, final float v) {
      this.array().update$mcF$sp(i, v);
   }

   public float default() {
      return this.default$mcF$sp();
   }

   public float default$mcF$sp() {
      return this.array().defaultValue$mcF$sp();
   }

   public HashVector copy() {
      return this.copy$mcF$sp();
   }

   public HashVector copy$mcF$sp() {
      return new HashVector$mcF$sp(this.array().copy$mcF$sp());
   }

   public HashVector repr() {
      return this.repr$mcF$sp();
   }

   public HashVector repr$mcF$sp() {
      return this;
   }

   public float[] data() {
      return this.data$mcF$sp();
   }

   public float[] data$mcF$sp() {
      return this.array().data$mcF$sp();
   }

   public boolean specInstance$() {
      return true;
   }

   public HashVector$mcF$sp(final OpenAddressHashArray array$mcF$sp) {
      super((OpenAddressHashArray)null);
      this.array$mcF$sp = array$mcF$sp;
   }
}
