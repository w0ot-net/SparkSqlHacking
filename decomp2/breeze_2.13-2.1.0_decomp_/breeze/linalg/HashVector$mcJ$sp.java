package breeze.linalg;

import breeze.collection.mutable.OpenAddressHashArray;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import scala.Function1;
import scala.Function2;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;

public class HashVector$mcJ$sp extends HashVector implements VectorLike$mcJ$sp {
   public final OpenAddressHashArray array$mcJ$sp;

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

   public OpenAddressHashArray array$mcJ$sp() {
      return this.array$mcJ$sp;
   }

   public OpenAddressHashArray array() {
      return this.array$mcJ$sp();
   }

   public long apply(final int i) {
      return this.apply$mcJ$sp(i);
   }

   public long apply$mcJ$sp(final int i) {
      return this.array().apply$mcJ$sp(i);
   }

   public void update(final int i, final long v) {
      this.update$mcJ$sp(i, v);
   }

   public void update$mcJ$sp(final int i, final long v) {
      this.array().update$mcJ$sp(i, v);
   }

   public long default() {
      return this.default$mcJ$sp();
   }

   public long default$mcJ$sp() {
      return this.array().defaultValue$mcJ$sp();
   }

   public HashVector copy() {
      return this.copy$mcJ$sp();
   }

   public HashVector copy$mcJ$sp() {
      return new HashVector$mcJ$sp(this.array().copy$mcJ$sp());
   }

   public HashVector repr() {
      return this.repr$mcJ$sp();
   }

   public HashVector repr$mcJ$sp() {
      return this;
   }

   public long[] data() {
      return this.data$mcJ$sp();
   }

   public long[] data$mcJ$sp() {
      return this.array().data$mcJ$sp();
   }

   public boolean specInstance$() {
      return true;
   }

   public HashVector$mcJ$sp(final OpenAddressHashArray array$mcJ$sp) {
      super((OpenAddressHashArray)null);
      this.array$mcJ$sp = array$mcJ$sp;
   }
}
