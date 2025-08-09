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

public class DenseVector$mcF$sp extends DenseVector implements VectorLike$mcF$sp {
   private static final long serialVersionUID = 1L;
   public final float[] data$mcF$sp;

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcF$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcF$sp.map$mcF$sp$(this, fn, canMapValues);
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

   public float[] data$mcF$sp() {
      return this.data$mcF$sp;
   }

   public float[] data() {
      return this.data$mcF$sp();
   }

   public DenseVector repr() {
      return this.repr$mcF$sp();
   }

   public DenseVector repr$mcF$sp() {
      return this;
   }

   public float apply(final int i) {
      return this.apply$mcF$sp(i);
   }

   public float apply$mcF$sp(final int i) {
      if (i >= -this.size() && i < this.size()) {
         int trueI = i < 0 ? i + this.size() : i;
         return this.noOffsetOrStride() ? this.data()[trueI] : this.data()[this.offset() + trueI * this.stride()];
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [-").append(this.size()).append(",").append(this.size()).append(")").toString());
      }
   }

   public void update(final int i, final float v) {
      this.update$mcF$sp(i, v);
   }

   public void update$mcF$sp(final int i, final float v) {
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
      return this.copy$mcF$sp();
   }

   public DenseVector copy$mcF$sp() {
      Object var10000;
      if (this.stride() == 1) {
         float[] newData = (float[])ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.length());
         var10000 = new DenseVector$mcF$sp(newData);
      } else {
         ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
         DenseVector r = new DenseVector$mcF$sp((float[])man.newArray(this.length()));
         r.$colon$eq(this, HasOps$.MODULE$.impl_OpSet_InPlace_DV_DV());
         var10000 = r;
      }

      return (DenseVector)var10000;
   }

   public float valueAt(final int i) {
      return this.valueAt$mcF$sp(i);
   }

   public float valueAt$mcF$sp(final int i) {
      return this.apply$mcF$sp(i);
   }

   public void foreach(final Function1 fn) {
      this.foreach$mcF$sp(fn);
   }

   public void foreach$mcF$sp(final Function1 fn) {
      if (this.stride() == 1) {
         int index$macro$2 = this.offset();

         for(int limit$macro$4 = this.offset() + this.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            fn.apply(BoxesRunTime.boxToFloat(this.data()[index$macro$2]));
         }
      } else {
         int i = this.offset();
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.length(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            fn.apply(BoxesRunTime.boxToFloat(this.data()[i]));
            i += this.stride();
         }
      }

   }

   public void foreach$mVc$sp(final Function1 fn) {
      this.foreach$mVcF$sp(fn);
   }

   public void foreach$mVcF$sp(final Function1 fn) {
      if (this.stride() == 1) {
         int index$macro$2 = this.offset();

         for(int limit$macro$4 = this.offset() + this.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            fn.apply$mcVF$sp(this.data()[index$macro$2]);
         }
      } else {
         int i = this.offset();
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.length(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            fn.apply$mcVF$sp(this.data()[i]);
            i += this.stride();
         }
      }

   }

   public DenseVector slice(final int start, final int end, final int stride) {
      return this.slice$mcF$sp(start, end, stride);
   }

   public DenseVector slice$mcF$sp(final int start, final int end, final int stride) {
      if (start <= end && start >= 0) {
         if (end <= this.length() && end >= 0) {
            int len = (end - start + stride - 1) / stride;
            return new DenseVector$mcF$sp(this.data(), start * this.stride() + this.offset(), stride * this.stride(), len);
         } else {
            throw new IllegalArgumentException((new StringBuilder(56)).append("End ").append(end).append("is out of bounds for slice of DenseVector of length ").append(this.length()).toString());
         }
      } else {
         throw new IllegalArgumentException((new StringBuilder(27)).append("Slice arguments ").append(start).append(", ").append(end).append(" invalid.").toString());
      }
   }

   public DenseMatrix toDenseMatrix() {
      return this.toDenseMatrix$mcF$sp();
   }

   public DenseMatrix toDenseMatrix$mcF$sp() {
      return this.copy$mcF$sp().asDenseMatrix$mcF$sp();
   }

   public DenseMatrix asDenseMatrix() {
      return this.asDenseMatrix$mcF$sp();
   }

   public DenseMatrix asDenseMatrix$mcF$sp() {
      return new DenseMatrix$mcF$sp(1, this.length(), this.data(), this.offset(), this.stride(), DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public float[] toArray(final ClassTag ct) {
      return this.toArray$mcF$sp(ct);
   }

   public float[] toArray$mcF$sp(final ClassTag ct) {
      float[] var10000;
      if (this.stride() == 1) {
         var10000 = (float[])ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.length());
      } else {
         float[] arr = (float[])ct.newArray(this.length());
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
      return this.overlaps$mcF$sp(other);
   }

   public boolean overlaps$mcF$sp(final DenseVector other) {
      return this.data() == other.data$mcF$sp() && RangeUtils$.MODULE$.overlaps(this.breeze$linalg$DenseVector$$footprint(), other.breeze$linalg$DenseVector$$footprint());
   }

   public boolean specInstance$() {
      return true;
   }

   public DenseVector$mcF$sp(final float[] data$mcF$sp, final int offset, final int stride, final int length) {
      super((Object)null, offset, stride, length);
      this.data$mcF$sp = data$mcF$sp;
      Statics.releaseFence();
   }

   public DenseVector$mcF$sp(final float[] data$mcF$sp) {
      this(data$mcF$sp, 0, 1, data$mcF$sp.length);
   }

   public DenseVector$mcF$sp(final float[] data$mcF$sp, final int offset) {
      this(data$mcF$sp, offset, 1, data$mcF$sp.length);
   }

   public DenseVector$mcF$sp(final int length, final ClassTag man) {
      this((float[])man.newArray(length), 0, 1, length);
   }
}
