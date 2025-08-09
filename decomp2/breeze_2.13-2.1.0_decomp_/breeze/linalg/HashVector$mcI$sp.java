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

public class HashVector$mcI$sp extends HashVector implements Vector$mcI$sp {
   public final OpenAddressHashArray array$mcI$sp;

   public DenseVector toDenseVector(final ClassTag cm) {
      return Vector$mcI$sp.toDenseVector$(this, cm);
   }

   public DenseVector toDenseVector$mcI$sp(final ClassTag cm) {
      return Vector$mcI$sp.toDenseVector$mcI$sp$(this, cm);
   }

   public int[] toArray(final ClassTag cm) {
      return Vector$mcI$sp.toArray$(this, cm);
   }

   public int[] toArray$mcI$sp(final ClassTag cm) {
      return Vector$mcI$sp.toArray$mcI$sp$(this, cm);
   }

   public Vector toVector(final ClassTag cm) {
      return Vector$mcI$sp.toVector$(this, cm);
   }

   public Vector toVector$mcI$sp(final ClassTag cm) {
      return Vector$mcI$sp.toVector$mcI$sp$(this, cm);
   }

   public Vector padTo(final int len, final int elem, final ClassTag cm) {
      return Vector$mcI$sp.padTo$(this, len, elem, cm);
   }

   public Vector padTo$mcI$sp(final int len, final int elem, final ClassTag cm) {
      return Vector$mcI$sp.padTo$mcI$sp$(this, len, elem, cm);
   }

   public boolean exists(final Function1 f) {
      return Vector$mcI$sp.exists$(this, f);
   }

   public boolean exists$mcI$sp(final Function1 f) {
      return Vector$mcI$sp.exists$mcI$sp$(this, f);
   }

   public boolean forall(final Function1 f) {
      return Vector$mcI$sp.forall$(this, f);
   }

   public boolean forall$mcI$sp(final Function1 f) {
      return Vector$mcI$sp.forall$mcI$sp$(this, f);
   }

   public Object fold(final Object z, final Function2 op) {
      return Vector$mcI$sp.fold$(this, z, op);
   }

   public Object fold$mcI$sp(final Object z, final Function2 op) {
      return Vector$mcI$sp.fold$mcI$sp$(this, z, op);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return Vector$mcI$sp.foldLeft$(this, z, op);
   }

   public Object foldLeft$mcI$sp(final Object z, final Function2 op) {
      return Vector$mcI$sp.foldLeft$mcI$sp$(this, z, op);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return Vector$mcI$sp.foldRight$(this, z, op);
   }

   public Object foldRight$mcI$sp(final Object z, final Function2 op) {
      return Vector$mcI$sp.foldRight$mcI$sp$(this, z, op);
   }

   public Object reduce(final Function2 op) {
      return Vector$mcI$sp.reduce$(this, op);
   }

   public Object reduce$mcI$sp(final Function2 op) {
      return Vector$mcI$sp.reduce$mcI$sp$(this, op);
   }

   public Object reduceLeft(final Function2 op) {
      return Vector$mcI$sp.reduceLeft$(this, op);
   }

   public Object reduceLeft$mcI$sp(final Function2 op) {
      return Vector$mcI$sp.reduceLeft$mcI$sp$(this, op);
   }

   public Object reduceRight(final Function2 op) {
      return Vector$mcI$sp.reduceRight$(this, op);
   }

   public Object reduceRight$mcI$sp(final Function2 op) {
      return Vector$mcI$sp.reduceRight$mcI$sp$(this, op);
   }

   public Vector scan(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector$mcI$sp.scan$(this, z, op, cm, cm1);
   }

   public Vector scan$mcI$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector$mcI$sp.scan$mcI$sp$(this, z, op, cm, cm1);
   }

   public Vector scanLeft(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcI$sp.scanLeft$(this, z, op, cm1);
   }

   public Vector scanLeft$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcI$sp.scanLeft$mcI$sp$(this, z, op, cm1);
   }

   public Vector scanRight(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcI$sp.scanRight$(this, z, op, cm1);
   }

   public Vector scanRight$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$mcI$sp.scanRight$mcI$sp$(this, z, op, cm1);
   }

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

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor$mcII$sp.findAll$(this, f);
   }

   public IndexedSeq findAll$mcI$sp(final Function1 f) {
      return QuasiTensor$mcII$sp.findAll$mcI$sp$(this, f);
   }

   public OpenAddressHashArray array$mcI$sp() {
      return this.array$mcI$sp;
   }

   public OpenAddressHashArray array() {
      return this.array$mcI$sp();
   }

   public int apply(final int i) {
      return this.apply$mcI$sp(i);
   }

   public int apply$mcI$sp(final int i) {
      return this.array().apply$mcI$sp(i);
   }

   public void update(final int i, final int v) {
      this.update$mcI$sp(i, v);
   }

   public void update$mcI$sp(final int i, final int v) {
      this.array().update$mcI$sp(i, v);
   }

   public int default() {
      return this.default$mcI$sp();
   }

   public int default$mcI$sp() {
      return this.array().defaultValue$mcI$sp();
   }

   public HashVector copy() {
      return this.copy$mcI$sp();
   }

   public HashVector copy$mcI$sp() {
      return new HashVector$mcI$sp(this.array().copy$mcI$sp());
   }

   public HashVector repr() {
      return this.repr$mcI$sp();
   }

   public HashVector repr$mcI$sp() {
      return this;
   }

   public int[] data() {
      return this.data$mcI$sp();
   }

   public int[] data$mcI$sp() {
      return this.array().data$mcI$sp();
   }

   public boolean specInstance$() {
      return true;
   }

   public HashVector$mcI$sp(final OpenAddressHashArray array$mcI$sp) {
      super((OpenAddressHashArray)null);
      this.array$mcI$sp = array$mcI$sp;
   }
}
