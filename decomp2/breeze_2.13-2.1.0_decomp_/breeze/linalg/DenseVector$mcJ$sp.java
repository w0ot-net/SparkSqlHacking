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

public class DenseVector$mcJ$sp extends DenseVector implements VectorLike$mcJ$sp {
   private static final long serialVersionUID = 1L;
   public final long[] data$mcJ$sp;

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcJ$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcJ$sp.map$mcJ$sp$(this, fn, canMapValues);
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

   public long[] data$mcJ$sp() {
      return this.data$mcJ$sp;
   }

   public long[] data() {
      return this.data$mcJ$sp();
   }

   public DenseVector repr() {
      return this.repr$mcJ$sp();
   }

   public DenseVector repr$mcJ$sp() {
      return this;
   }

   public long apply(final int i) {
      return this.apply$mcJ$sp(i);
   }

   public long apply$mcJ$sp(final int i) {
      if (i >= -this.size() && i < this.size()) {
         int trueI = i < 0 ? i + this.size() : i;
         return this.noOffsetOrStride() ? this.data()[trueI] : this.data()[this.offset() + trueI * this.stride()];
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [-").append(this.size()).append(",").append(this.size()).append(")").toString());
      }
   }

   public void update(final int i, final long v) {
      this.update$mcJ$sp(i, v);
   }

   public void update$mcJ$sp(final int i, final long v) {
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
      return this.copy$mcJ$sp();
   }

   public DenseVector copy$mcJ$sp() {
      Object var10000;
      if (this.stride() == 1) {
         long[] newData = (long[])ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.length());
         var10000 = new DenseVector$mcJ$sp(newData);
      } else {
         ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
         DenseVector r = new DenseVector$mcJ$sp((long[])man.newArray(this.length()));
         r.$colon$eq(this, HasOps$.MODULE$.impl_OpSet_InPlace_DV_DV());
         var10000 = r;
      }

      return (DenseVector)var10000;
   }

   public long valueAt(final int i) {
      return this.valueAt$mcJ$sp(i);
   }

   public long valueAt$mcJ$sp(final int i) {
      return this.apply$mcJ$sp(i);
   }

   public void foreach(final Function1 fn) {
      this.foreach$mcJ$sp(fn);
   }

   public void foreach$mcJ$sp(final Function1 fn) {
      if (this.stride() == 1) {
         int index$macro$2 = this.offset();

         for(int limit$macro$4 = this.offset() + this.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            fn.apply(BoxesRunTime.boxToLong(this.data()[index$macro$2]));
         }
      } else {
         int i = this.offset();
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.length(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            fn.apply(BoxesRunTime.boxToLong(this.data()[i]));
            i += this.stride();
         }
      }

   }

   public void foreach$mVc$sp(final Function1 fn) {
      this.foreach$mVcJ$sp(fn);
   }

   public void foreach$mVcJ$sp(final Function1 fn) {
      if (this.stride() == 1) {
         int index$macro$2 = this.offset();

         for(int limit$macro$4 = this.offset() + this.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            fn.apply$mcVJ$sp(this.data()[index$macro$2]);
         }
      } else {
         int i = this.offset();
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.length(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            fn.apply$mcVJ$sp(this.data()[i]);
            i += this.stride();
         }
      }

   }

   public DenseVector slice(final int start, final int end, final int stride) {
      return this.slice$mcJ$sp(start, end, stride);
   }

   public DenseVector slice$mcJ$sp(final int start, final int end, final int stride) {
      if (start <= end && start >= 0) {
         if (end <= this.length() && end >= 0) {
            int len = (end - start + stride - 1) / stride;
            return new DenseVector$mcJ$sp(this.data(), start * this.stride() + this.offset(), stride * this.stride(), len);
         } else {
            throw new IllegalArgumentException((new StringBuilder(56)).append("End ").append(end).append("is out of bounds for slice of DenseVector of length ").append(this.length()).toString());
         }
      } else {
         throw new IllegalArgumentException((new StringBuilder(27)).append("Slice arguments ").append(start).append(", ").append(end).append(" invalid.").toString());
      }
   }

   public DenseMatrix toDenseMatrix() {
      return this.toDenseMatrix$mcJ$sp();
   }

   public DenseMatrix toDenseMatrix$mcJ$sp() {
      return this.copy$mcJ$sp().asDenseMatrix$mcJ$sp();
   }

   public DenseMatrix asDenseMatrix() {
      return this.asDenseMatrix$mcJ$sp();
   }

   public DenseMatrix asDenseMatrix$mcJ$sp() {
      return new DenseMatrix$mcJ$sp(1, this.length(), this.data(), this.offset(), this.stride(), DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public long[] toArray(final ClassTag ct) {
      return this.toArray$mcJ$sp(ct);
   }

   public long[] toArray$mcJ$sp(final ClassTag ct) {
      long[] var10000;
      if (this.stride() == 1) {
         var10000 = (long[])ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.length());
      } else {
         long[] arr = (long[])ct.newArray(this.length());
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
      return this.overlaps$mcJ$sp(other);
   }

   public boolean overlaps$mcJ$sp(final DenseVector other) {
      return this.data() == other.data$mcJ$sp() && RangeUtils$.MODULE$.overlaps(this.breeze$linalg$DenseVector$$footprint(), other.breeze$linalg$DenseVector$$footprint());
   }

   public boolean specInstance$() {
      return true;
   }

   public DenseVector$mcJ$sp(final long[] data$mcJ$sp, final int offset, final int stride, final int length) {
      super((Object)null, offset, stride, length);
      this.data$mcJ$sp = data$mcJ$sp;
      Statics.releaseFence();
   }

   public DenseVector$mcJ$sp(final long[] data$mcJ$sp) {
      this(data$mcJ$sp, 0, 1, data$mcJ$sp.length);
   }

   public DenseVector$mcJ$sp(final long[] data$mcJ$sp, final int offset) {
      this(data$mcJ$sp, offset, 1, data$mcJ$sp.length);
   }

   public DenseVector$mcJ$sp(final int length, final ClassTag man) {
      this((long[])man.newArray(length), 0, 1, length);
   }
}
