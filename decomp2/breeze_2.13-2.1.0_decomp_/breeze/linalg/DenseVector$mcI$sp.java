package breeze.linalg;

import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.util.ArrayUtil$;
import breeze.util.RangeUtils$;
import breeze.util.ReflectionUtil$;
import scala.Function1;
import scala.Function2;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

public class DenseVector$mcI$sp extends DenseVector implements VectorLike$mcI$sp {
   private static final long serialVersionUID = 1L;
   public final int[] data$mcI$sp;

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcI$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcI$sp.map$mcI$sp$(this, fn, canMapValues);
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

   public int[] data$mcI$sp() {
      return this.data$mcI$sp;
   }

   public int[] data() {
      return this.data$mcI$sp();
   }

   public DenseVector repr() {
      return this.repr$mcI$sp();
   }

   public DenseVector repr$mcI$sp() {
      return this;
   }

   public int apply(final int i) {
      return this.apply$mcI$sp(i);
   }

   public int apply$mcI$sp(final int i) {
      if (i >= -this.size() && i < this.size()) {
         int trueI = i < 0 ? i + this.size() : i;
         return this.noOffsetOrStride() ? this.data()[trueI] : this.data()[this.offset() + trueI * this.stride()];
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [-").append(this.size()).append(",").append(this.size()).append(")").toString());
      }
   }

   public void update(final int i, final int v) {
      this.update$mcI$sp(i, v);
   }

   public void update$mcI$sp(final int i, final int v) {
      if (i >= -this.size() && i < this.size()) {
         int trueI = i < 0 ? i + this.size() : i;
         if (this.noOffsetOrStride()) {
            this.data()[trueI] = v;
         } else {
            this.data()[this.offset() + trueI * this.stride()] = v;
         }

      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [-").append(this.size()).append(",").append(this.size()).append(")").toString());
      }
   }

   public DenseVector copy() {
      return this.copy$mcI$sp();
   }

   public DenseVector copy$mcI$sp() {
      Object var10000;
      if (this.stride() == 1) {
         int[] newData = (int[])ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.length());
         var10000 = new DenseVector$mcI$sp(newData);
      } else {
         ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
         DenseVector r = new DenseVector$mcI$sp((int[])man.newArray(this.length()));
         r.$colon$eq(this, HasOps$.MODULE$.impl_OpSet_InPlace_DV_DV());
         var10000 = r;
      }

      return (DenseVector)var10000;
   }

   public int valueAt(final int i) {
      return this.valueAt$mcI$sp(i);
   }

   public int valueAt$mcI$sp(final int i) {
      return this.apply$mcI$sp(i);
   }

   public void foreach(final Function1 fn) {
      this.foreach$mcI$sp(fn);
   }

   public void foreach$mcI$sp(final Function1 fn) {
      if (this.stride() == 1) {
         int index$macro$2 = this.offset();

         for(int limit$macro$4 = this.offset() + this.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            fn.apply(BoxesRunTime.boxToInteger(this.data()[index$macro$2]));
         }
      } else {
         int i = this.offset();
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.length(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            fn.apply(BoxesRunTime.boxToInteger(this.data()[i]));
            i += this.stride();
         }
      }

   }

   public void foreach$mVc$sp(final Function1 fn) {
      this.foreach$mVcI$sp(fn);
   }

   public void foreach$mVcI$sp(final Function1 fn) {
      if (this.stride() == 1) {
         int index$macro$2 = this.offset();

         for(int limit$macro$4 = this.offset() + this.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            fn.apply$mcVI$sp(this.data()[index$macro$2]);
         }
      } else {
         int i = this.offset();
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.length(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            fn.apply$mcVI$sp(this.data()[i]);
            i += this.stride();
         }
      }

   }

   public DenseVector slice(final int start, final int end, final int stride) {
      return this.slice$mcI$sp(start, end, stride);
   }

   public DenseVector slice$mcI$sp(final int start, final int end, final int stride) {
      if (start <= end && start >= 0) {
         if (end <= this.length() && end >= 0) {
            int len = (end - start + stride - 1) / stride;
            return new DenseVector$mcI$sp(this.data(), start * this.stride() + this.offset(), stride * this.stride(), len);
         } else {
            throw new IllegalArgumentException((new StringBuilder(56)).append("End ").append(end).append("is out of bounds for slice of DenseVector of length ").append(this.length()).toString());
         }
      } else {
         throw new IllegalArgumentException((new StringBuilder(27)).append("Slice arguments ").append(start).append(", ").append(end).append(" invalid.").toString());
      }
   }

   public DenseMatrix toDenseMatrix() {
      return this.toDenseMatrix$mcI$sp();
   }

   public DenseMatrix toDenseMatrix$mcI$sp() {
      return this.copy$mcI$sp().asDenseMatrix$mcI$sp();
   }

   public DenseMatrix asDenseMatrix() {
      return this.asDenseMatrix$mcI$sp();
   }

   public DenseMatrix asDenseMatrix$mcI$sp() {
      return new DenseMatrix$mcI$sp(1, this.length(), this.data(), this.offset(), this.stride(), DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public int[] toArray(final ClassTag ct) {
      return this.toArray$mcI$sp(ct);
   }

   public int[] toArray$mcI$sp(final ClassTag ct) {
      int[] var10000;
      if (this.stride() == 1) {
         var10000 = (int[])ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.length());
      } else {
         int[] arr = (int[])ct.newArray(this.length());
         int i = 0;

         for(int off = this.offset(); i < this.length(); ++i) {
            arr[i] = this.data()[off];
            off += this.stride();
         }

         var10000 = arr;
      }

      return var10000;
   }

   public boolean overlaps(final DenseVector other) {
      return this.overlaps$mcI$sp(other);
   }

   public boolean overlaps$mcI$sp(final DenseVector other) {
      return this.data() == other.data$mcI$sp() && RangeUtils$.MODULE$.overlaps(this.breeze$linalg$DenseVector$$footprint(), other.breeze$linalg$DenseVector$$footprint());
   }

   public boolean specInstance$() {
      return true;
   }

   public DenseVector$mcI$sp(final int[] data$mcI$sp, final int offset, final int stride, final int length) {
      super((Object)null, offset, stride, length);
      this.data$mcI$sp = data$mcI$sp;
      Statics.releaseFence();
   }

   public DenseVector$mcI$sp(final int[] data$mcI$sp) {
      this(data$mcI$sp, 0, 1, data$mcI$sp.length);
   }

   public DenseVector$mcI$sp(final int[] data$mcI$sp, final int offset) {
      this(data$mcI$sp, offset, 1, data$mcI$sp.length);
   }

   public DenseVector$mcI$sp(final int length, final ClassTag man) {
      this((int[])man.newArray(length), 0, 1, length);
   }
}
