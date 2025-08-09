package breeze.linalg;

import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import breeze.util.ArrayUtil$;
import breeze.util.ReflectionUtil$;
import breeze.util.Sorting$;
import java.lang.invoke.SerializedLambda;
import scala.math.package.;
import scala.reflect.ClassTag;

public class VectorBuilder$mcJ$sp extends VectorBuilder {
   private static final long serialVersionUID = 1L;
   public long[] _data$mcJ$sp;
   public final Semiring ring$mcJ$sp;
   private final int[] _index;
   private final int used;

   public long[] _data$mcJ$sp() {
      return this._data$mcJ$sp;
   }

   public long[] _data() {
      return this._data$mcJ$sp();
   }

   public void _data$mcJ$sp_$eq(final long[] x$1) {
      this._data$mcJ$sp = x$1;
   }

   public void _data_$eq(final long[] x$1) {
      this._data$mcJ$sp_$eq(x$1);
   }

   public long[] data() {
      return this.data$mcJ$sp();
   }

   public long[] data$mcJ$sp() {
      return this._data();
   }

   public VectorBuilder repr() {
      return this.repr$mcJ$sp();
   }

   public VectorBuilder repr$mcJ$sp() {
      return this;
   }

   public long apply(final int i) {
      return this.apply$mcJ$sp(i);
   }

   public long apply$mcJ$sp(final int i) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      int off = 0;

      long acc;
      for(acc = this.ring$mcJ$sp.zero$mcJ$sp(); off < this.breeze$linalg$VectorBuilder$$used(); ++off) {
         if (this.breeze$linalg$VectorBuilder$$_index()[off] == i) {
            acc = this.ring$mcJ$sp.$plus$mcJ$sp(acc, this._data()[off]);
         }
      }

      return acc;
   }

   public void update(final int i, final long v) {
      this.update$mcJ$sp(i, v);
   }

   public void update$mcJ$sp(final int i, final long v) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      boolean marked = false;

      for(int off = 0; off < this.breeze$linalg$VectorBuilder$$used(); ++off) {
         if (this.breeze$linalg$VectorBuilder$$_index()[off] == i) {
            if (!marked) {
               this._data()[off] = v;
            } else {
               this._data()[off] = this.ring$mcJ$sp.zero$mcJ$sp();
            }

            marked = true;
         }
      }

   }

   public void add(final int i, final long v) {
      this.add$mcJ$sp(i, v);
   }

   public void add$mcJ$sp(final int i, final long v) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      if (this._data().length <= this.breeze$linalg$VectorBuilder$$used()) {
         this.breeze$linalg$VectorBuilder$$reallocate(.MODULE$.max(this._data().length * 2, 1));
      }

      this._data()[this.breeze$linalg$VectorBuilder$$used()] = v;
      this.breeze$linalg$VectorBuilder$$_index()[this.breeze$linalg$VectorBuilder$$used()] = i;
      this.breeze$linalg$VectorBuilder$$used_$eq(this.breeze$linalg$VectorBuilder$$used() + 1);
   }

   public long default() {
      return this.default$mcJ$sp();
   }

   public long default$mcJ$sp() {
      return this.ring$mcJ$sp.zero$mcJ$sp();
   }

   public VectorBuilder copy() {
      return this.copy$mcJ$sp();
   }

   public VectorBuilder copy$mcJ$sp() {
      return new VectorBuilder$mcJ$sp((int[])ArrayUtil$.MODULE$.copyOf(this.index(), this.index().length), (long[])ArrayUtil$.MODULE$.copyOf(this.data$mcJ$sp(), this.index().length), this.activeSize(), this.size(), this.ring$mcJ$sp);
   }

   public VectorBuilder zerosLike() {
      return this.zerosLike$mcJ$sp();
   }

   public VectorBuilder zerosLike$mcJ$sp() {
      return new VectorBuilder$mcJ$sp(new int[0], (long[])ArrayUtil$.MODULE$.newArrayLike(this.data$mcJ$sp(), 0), 0, this.size(), this.ring$mcJ$sp);
   }

   public HashVector toHashVector() {
      return this.toHashVector$mcJ$sp();
   }

   public HashVector toHashVector$mcJ$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcJ$sp());
      HashVector hv = HashVector$.MODULE$.zeros$mJc$sp(this.length(), man, Zero$.MODULE$.zeroFromSemiring(this.ring$mcJ$sp));

      for(int i = 0; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
         hv.update$mcJ$sp(this.index()[i], this.ring$mcJ$sp.$plus$mcJ$sp(hv.apply$mcJ$sp(this.index()[i]), this.data$mcJ$sp()[i]));
      }

      return hv;
   }

   public DenseVector toDenseVector() {
      return this.toDenseVector$mcJ$sp();
   }

   public DenseVector toDenseVector$mcJ$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcJ$sp());
      DenseVector hv = DenseVector$.MODULE$.zeros$mJc$sp(this.length(), man, Zero$.MODULE$.zeroFromSemiring(this.ring$mcJ$sp));

      for(int i = 0; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
         hv.update$mcJ$sp(this.index()[i], this.ring$mcJ$sp.$plus$mcJ$sp(hv.apply$mcJ$sp(this.index()[i]), this.data$mcJ$sp()[i]));
      }

      return hv;
   }

   public SparseVector toSparseVector() {
      return this.toSparseVector$mcJ$sp();
   }

   public SparseVector toSparseVector$mcJ$sp() {
      return this.toSparseVector$mcJ$sp(this.toSparseVector$default$1(), this.toSparseVector$default$2());
   }

   public SparseVector toSparseVector(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      return this.toSparseVector$mcJ$sp(alreadySorted, keysAlreadyUnique);
   }

   public SparseVector toSparseVector$mcJ$sp(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      int[] index = this.index();
      long[] values = this.data$mcJ$sp();
      if (alreadySorted && keysAlreadyUnique) {
         return new SparseVector$mcJ$sp(index, values, this.breeze$linalg$VectorBuilder$$used(), this.length(), Zero$.MODULE$.zeroFromSemiring(this.ring$mcJ$sp));
      } else {
         int[] outIndex = (int[])ArrayUtil$.MODULE$.copyOf(index, this.breeze$linalg$VectorBuilder$$used());
         long[] outValues = (long[])ArrayUtil$.MODULE$.copyOf(values, this.breeze$linalg$VectorBuilder$$used());
         if (!alreadySorted) {
            Sorting$.MODULE$.indirectSort$mJc$sp((int[])outIndex, outValues, 0, this.breeze$linalg$VectorBuilder$$used());
         }

         if (outIndex.length > 0) {
            if (outIndex[this.breeze$linalg$VectorBuilder$$used() - 1] >= this.length()) {
               throw new IndexOutOfBoundsException((new StringBuilder(25)).append("Index ").append(index[this.breeze$linalg$VectorBuilder$$used() - 1]).append(" exceeds dimension ").append(this.length()).toString());
            }

            if (outIndex[0] < 0) {
               throw new IndexOutOfBoundsException((new StringBuilder(21)).append("Index ").append(outIndex[0]).append(" is less than 0").toString());
            }
         }

         int i = 1;
         int out = 0;
         if (keysAlreadyUnique) {
            out = this.breeze$linalg$VectorBuilder$$used();
         } else {
            for(; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
               if (outIndex[out] == outIndex[i]) {
                  outValues[out] = this.ring$mcJ$sp.$plus$mcJ$sp(outValues[out], outValues[i]);
               } else {
                  ++out;
                  outIndex[out] = outIndex[i];
                  outValues[out] = outValues[i];
               }
            }

            if (outIndex.length > 0) {
               ++out;
            }
         }

         return new SparseVector$mcJ$sp(outIndex, outValues, out, this.length(), Zero$.MODULE$.zeroFromSemiring(this.ring$mcJ$sp));
      }
   }

   public void use(final int[] index, final long[] data, final int activeSize) {
      this.use$mcJ$sp(index, data, activeSize);
   }

   public void use$mcJ$sp(final int[] index, final long[] data, final int activeSize) {
      scala.Predef..MODULE$.require(activeSize >= 0, () -> "activeSize must be non-negative");
      scala.Predef..MODULE$.require(data.length >= activeSize, () -> "activeSize must be no greater than array length...");
      this._data_$eq(data);
      this.breeze$linalg$VectorBuilder$$_index_$eq(index);
      this.breeze$linalg$VectorBuilder$$used_$eq(activeSize);
   }

   public long valueAt(final int i) {
      return this.valueAt$mcJ$sp(i);
   }

   public long valueAt$mcJ$sp(final int i) {
      return this.data$mcJ$sp()[i];
   }

   public Vector toVector() {
      return this.toVector$mcJ$sp();
   }

   public Vector toVector$mcJ$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      return (Vector)(this.size() >= 40 && this.activeSize() <= this.size() / 4 ? this.toSparseVector$mcJ$sp() : this.toDenseVector$mcJ$sp());
   }

   public boolean specInstance$() {
      return true;
   }

   public VectorBuilder$mcJ$sp(final int[] _index, final long[] _data$mcJ$sp, final int used, final int length, final Semiring ring$mcJ$sp) {
      this._data$mcJ$sp = _data$mcJ$sp;
      this.ring$mcJ$sp = ring$mcJ$sp;
      this._index = _index;
      this.used = used;
      super(_index, _data$mcJ$sp, used, length, ring$mcJ$sp);
   }

   public VectorBuilder$mcJ$sp(final int length, final int initialNonZero, final Semiring ring$mcJ$sp, final ClassTag man) {
      this(new int[initialNonZero], (long[])man.newArray(initialNonZero), 0, length, ring$mcJ$sp);
   }

   public VectorBuilder$mcJ$sp(final Semiring ring$mcJ$sp, final ClassTag man, final Zero zero$mcJ$sp) {
      this(-1, VectorBuilder$.MODULE$.$lessinit$greater$default$2(), ring$mcJ$sp, man);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
