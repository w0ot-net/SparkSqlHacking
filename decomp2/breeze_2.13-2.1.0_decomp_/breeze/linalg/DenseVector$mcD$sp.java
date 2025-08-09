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

public class DenseVector$mcD$sp extends DenseVector implements VectorLike$mcD$sp {
   private static final long serialVersionUID = 1L;
   public final double[] data$mcD$sp;

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcD$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcD$sp.map$mcD$sp$(this, fn, canMapValues);
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

   public boolean forall(final Function1 fn) {
      return TensorLike$mcID$sp.forall$(this, (Function1)fn);
   }

   public boolean forall$mcD$sp(final Function1 fn) {
      return TensorLike$mcID$sp.forall$mcD$sp$(this, fn);
   }

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor$mcID$sp.findAll$(this, f);
   }

   public IndexedSeq findAll$mcD$sp(final Function1 f) {
      return QuasiTensor$mcID$sp.findAll$mcD$sp$(this, f);
   }

   public double[] data$mcD$sp() {
      return this.data$mcD$sp;
   }

   public double[] data() {
      return this.data$mcD$sp();
   }

   public DenseVector repr() {
      return this.repr$mcD$sp();
   }

   public DenseVector repr$mcD$sp() {
      return this;
   }

   public double apply(final int i) {
      return this.apply$mcD$sp(i);
   }

   public double apply$mcD$sp(final int i) {
      if (i >= -this.size() && i < this.size()) {
         int trueI = i < 0 ? i + this.size() : i;
         return this.noOffsetOrStride() ? this.data()[trueI] : this.data()[this.offset() + trueI * this.stride()];
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [-").append(this.size()).append(",").append(this.size()).append(")").toString());
      }
   }

   public void update(final int i, final double v) {
      this.update$mcD$sp(i, v);
   }

   public void update$mcD$sp(final int i, final double v) {
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
      return this.copy$mcD$sp();
   }

   public DenseVector copy$mcD$sp() {
      Object var10000;
      if (this.stride() == 1) {
         double[] newData = (double[])ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.length());
         var10000 = new DenseVector$mcD$sp(newData);
      } else {
         ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
         DenseVector r = new DenseVector$mcD$sp((double[])man.newArray(this.length()));
         r.$colon$eq(this, HasOps$.MODULE$.impl_OpSet_InPlace_DV_DV());
         var10000 = r;
      }

      return (DenseVector)var10000;
   }

   public double valueAt(final int i) {
      return this.valueAt$mcD$sp(i);
   }

   public double valueAt$mcD$sp(final int i) {
      return this.apply$mcD$sp(i);
   }

   public void foreach(final Function1 fn) {
      this.foreach$mcD$sp(fn);
   }

   public void foreach$mcD$sp(final Function1 fn) {
      if (this.stride() == 1) {
         int index$macro$2 = this.offset();

         for(int limit$macro$4 = this.offset() + this.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            fn.apply(BoxesRunTime.boxToDouble(this.data()[index$macro$2]));
         }
      } else {
         int i = this.offset();
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.length(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            fn.apply(BoxesRunTime.boxToDouble(this.data()[i]));
            i += this.stride();
         }
      }

   }

   public void foreach$mVc$sp(final Function1 fn) {
      this.foreach$mVcD$sp(fn);
   }

   public void foreach$mVcD$sp(final Function1 fn) {
      if (this.stride() == 1) {
         int index$macro$2 = this.offset();

         for(int limit$macro$4 = this.offset() + this.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            fn.apply$mcVD$sp(this.data()[index$macro$2]);
         }
      } else {
         int i = this.offset();
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.length(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            fn.apply$mcVD$sp(this.data()[i]);
            i += this.stride();
         }
      }

   }

   public DenseVector slice(final int start, final int end, final int stride) {
      return this.slice$mcD$sp(start, end, stride);
   }

   public DenseVector slice$mcD$sp(final int start, final int end, final int stride) {
      if (start <= end && start >= 0) {
         if (end <= this.length() && end >= 0) {
            int len = (end - start + stride - 1) / stride;
            return new DenseVector$mcD$sp(this.data(), start * this.stride() + this.offset(), stride * this.stride(), len);
         } else {
            throw new IllegalArgumentException((new StringBuilder(56)).append("End ").append(end).append("is out of bounds for slice of DenseVector of length ").append(this.length()).toString());
         }
      } else {
         throw new IllegalArgumentException((new StringBuilder(27)).append("Slice arguments ").append(start).append(", ").append(end).append(" invalid.").toString());
      }
   }

   public DenseMatrix toDenseMatrix() {
      return this.toDenseMatrix$mcD$sp();
   }

   public DenseMatrix toDenseMatrix$mcD$sp() {
      return this.copy$mcD$sp().asDenseMatrix$mcD$sp();
   }

   public DenseMatrix asDenseMatrix() {
      return this.asDenseMatrix$mcD$sp();
   }

   public DenseMatrix asDenseMatrix$mcD$sp() {
      return new DenseMatrix$mcD$sp(1, this.length(), this.data(), this.offset(), this.stride(), DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public double[] toArray(final ClassTag ct) {
      return this.toArray$mcD$sp(ct);
   }

   public double[] toArray$mcD$sp(final ClassTag ct) {
      double[] var10000;
      if (this.stride() == 1) {
         var10000 = (double[])ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.length());
      } else {
         double[] arr = (double[])ct.newArray(this.length());
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
      return this.overlaps$mcD$sp(other);
   }

   public boolean overlaps$mcD$sp(final DenseVector other) {
      return this.data() == other.data$mcD$sp() && RangeUtils$.MODULE$.overlaps(this.breeze$linalg$DenseVector$$footprint(), other.breeze$linalg$DenseVector$$footprint());
   }

   public boolean specInstance$() {
      return true;
   }

   public DenseVector$mcD$sp(final double[] data$mcD$sp, final int offset, final int stride, final int length) {
      super((Object)null, offset, stride, length);
      this.data$mcD$sp = data$mcD$sp;
      Statics.releaseFence();
   }

   public DenseVector$mcD$sp(final double[] data$mcD$sp) {
      this(data$mcD$sp, 0, 1, data$mcD$sp.length);
   }

   public DenseVector$mcD$sp(final double[] data$mcD$sp, final int offset) {
      this(data$mcD$sp, offset, 1, data$mcD$sp.length);
   }

   public DenseVector$mcD$sp(final int length, final ClassTag man) {
      this((double[])man.newArray(length), 0, 1, length);
   }
}
