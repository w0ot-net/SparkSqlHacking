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

public class VectorBuilder$mcD$sp extends VectorBuilder {
   private static final long serialVersionUID = 1L;
   public double[] _data$mcD$sp;
   public final Semiring ring$mcD$sp;
   private final int[] _index;
   private final int used;

   public double[] _data$mcD$sp() {
      return this._data$mcD$sp;
   }

   public double[] _data() {
      return this._data$mcD$sp();
   }

   public void _data$mcD$sp_$eq(final double[] x$1) {
      this._data$mcD$sp = x$1;
   }

   public void _data_$eq(final double[] x$1) {
      this._data$mcD$sp_$eq(x$1);
   }

   public double[] data() {
      return this.data$mcD$sp();
   }

   public double[] data$mcD$sp() {
      return this._data();
   }

   public VectorBuilder repr() {
      return this.repr$mcD$sp();
   }

   public VectorBuilder repr$mcD$sp() {
      return this;
   }

   public double apply(final int i) {
      return this.apply$mcD$sp(i);
   }

   public double apply$mcD$sp(final int i) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      int off = 0;

      double acc;
      for(acc = this.ring$mcD$sp.zero$mcD$sp(); off < this.breeze$linalg$VectorBuilder$$used(); ++off) {
         if (this.breeze$linalg$VectorBuilder$$_index()[off] == i) {
            acc = this.ring$mcD$sp.$plus$mcD$sp(acc, this._data()[off]);
         }
      }

      return acc;
   }

   public void update(final int i, final double v) {
      this.update$mcD$sp(i, v);
   }

   public void update$mcD$sp(final int i, final double v) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      boolean marked = false;

      for(int off = 0; off < this.breeze$linalg$VectorBuilder$$used(); ++off) {
         if (this.breeze$linalg$VectorBuilder$$_index()[off] == i) {
            if (!marked) {
               this._data()[off] = v;
            } else {
               this._data()[off] = this.ring$mcD$sp.zero$mcD$sp();
            }

            marked = true;
         }
      }

   }

   public void add(final int i, final double v) {
      this.add$mcD$sp(i, v);
   }

   public void add$mcD$sp(final int i, final double v) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      if (this._data().length <= this.breeze$linalg$VectorBuilder$$used()) {
         this.breeze$linalg$VectorBuilder$$reallocate(.MODULE$.max(this._data().length * 2, 1));
      }

      this._data()[this.breeze$linalg$VectorBuilder$$used()] = v;
      this.breeze$linalg$VectorBuilder$$_index()[this.breeze$linalg$VectorBuilder$$used()] = i;
      this.breeze$linalg$VectorBuilder$$used_$eq(this.breeze$linalg$VectorBuilder$$used() + 1);
   }

   public double default() {
      return this.default$mcD$sp();
   }

   public double default$mcD$sp() {
      return this.ring$mcD$sp.zero$mcD$sp();
   }

   public VectorBuilder copy() {
      return this.copy$mcD$sp();
   }

   public VectorBuilder copy$mcD$sp() {
      return new VectorBuilder$mcD$sp((int[])ArrayUtil$.MODULE$.copyOf(this.index(), this.index().length), (double[])ArrayUtil$.MODULE$.copyOf(this.data$mcD$sp(), this.index().length), this.activeSize(), this.size(), this.ring$mcD$sp);
   }

   public VectorBuilder zerosLike() {
      return this.zerosLike$mcD$sp();
   }

   public VectorBuilder zerosLike$mcD$sp() {
      return new VectorBuilder$mcD$sp(new int[0], (double[])ArrayUtil$.MODULE$.newArrayLike(this.data$mcD$sp(), 0), 0, this.size(), this.ring$mcD$sp);
   }

   public HashVector toHashVector() {
      return this.toHashVector$mcD$sp();
   }

   public HashVector toHashVector$mcD$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcD$sp());
      HashVector hv = HashVector$.MODULE$.zeros$mDc$sp(this.length(), man, Zero$.MODULE$.zeroFromSemiring(this.ring$mcD$sp));

      for(int i = 0; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
         hv.update$mcD$sp(this.index()[i], this.ring$mcD$sp.$plus$mcD$sp(hv.apply$mcD$sp(this.index()[i]), this.data$mcD$sp()[i]));
      }

      return hv;
   }

   public DenseVector toDenseVector() {
      return this.toDenseVector$mcD$sp();
   }

   public DenseVector toDenseVector$mcD$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcD$sp());
      DenseVector hv = DenseVector$.MODULE$.zeros$mDc$sp(this.length(), man, Zero$.MODULE$.zeroFromSemiring(this.ring$mcD$sp));

      for(int i = 0; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
         hv.update$mcD$sp(this.index()[i], this.ring$mcD$sp.$plus$mcD$sp(hv.apply$mcD$sp(this.index()[i]), this.data$mcD$sp()[i]));
      }

      return hv;
   }

   public SparseVector toSparseVector() {
      return this.toSparseVector$mcD$sp();
   }

   public SparseVector toSparseVector$mcD$sp() {
      return this.toSparseVector$mcD$sp(this.toSparseVector$default$1(), this.toSparseVector$default$2());
   }

   public SparseVector toSparseVector(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      return this.toSparseVector$mcD$sp(alreadySorted, keysAlreadyUnique);
   }

   public SparseVector toSparseVector$mcD$sp(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      int[] index = this.index();
      double[] values = this.data$mcD$sp();
      if (alreadySorted && keysAlreadyUnique) {
         return new SparseVector$mcD$sp(index, values, this.breeze$linalg$VectorBuilder$$used(), this.length(), Zero$.MODULE$.zeroFromSemiring(this.ring$mcD$sp));
      } else {
         int[] outIndex = (int[])ArrayUtil$.MODULE$.copyOf(index, this.breeze$linalg$VectorBuilder$$used());
         double[] outValues = (double[])ArrayUtil$.MODULE$.copyOf(values, this.breeze$linalg$VectorBuilder$$used());
         if (!alreadySorted) {
            Sorting$.MODULE$.indirectSort$mDc$sp((int[])outIndex, outValues, 0, this.breeze$linalg$VectorBuilder$$used());
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
                  outValues[out] = this.ring$mcD$sp.$plus$mcD$sp(outValues[out], outValues[i]);
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

         return new SparseVector$mcD$sp(outIndex, outValues, out, this.length(), Zero$.MODULE$.zeroFromSemiring(this.ring$mcD$sp));
      }
   }

   public void use(final int[] index, final double[] data, final int activeSize) {
      this.use$mcD$sp(index, data, activeSize);
   }

   public void use$mcD$sp(final int[] index, final double[] data, final int activeSize) {
      scala.Predef..MODULE$.require(activeSize >= 0, () -> "activeSize must be non-negative");
      scala.Predef..MODULE$.require(data.length >= activeSize, () -> "activeSize must be no greater than array length...");
      this._data_$eq(data);
      this.breeze$linalg$VectorBuilder$$_index_$eq(index);
      this.breeze$linalg$VectorBuilder$$used_$eq(activeSize);
   }

   public double valueAt(final int i) {
      return this.valueAt$mcD$sp(i);
   }

   public double valueAt$mcD$sp(final int i) {
      return this.data$mcD$sp()[i];
   }

   public Vector toVector() {
      return this.toVector$mcD$sp();
   }

   public Vector toVector$mcD$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      return (Vector)(this.size() >= 40 && this.activeSize() <= this.size() / 4 ? this.toSparseVector$mcD$sp() : this.toDenseVector$mcD$sp());
   }

   public boolean specInstance$() {
      return true;
   }

   public VectorBuilder$mcD$sp(final int[] _index, final double[] _data$mcD$sp, final int used, final int length, final Semiring ring$mcD$sp) {
      this._data$mcD$sp = _data$mcD$sp;
      this.ring$mcD$sp = ring$mcD$sp;
      this._index = _index;
      this.used = used;
      super(_index, _data$mcD$sp, used, length, ring$mcD$sp);
   }

   public VectorBuilder$mcD$sp(final int length, final int initialNonZero, final Semiring ring$mcD$sp, final ClassTag man) {
      this(new int[initialNonZero], (double[])man.newArray(initialNonZero), 0, length, ring$mcD$sp);
   }

   public VectorBuilder$mcD$sp(final Semiring ring$mcD$sp, final ClassTag man, final Zero zero$mcD$sp) {
      this(-1, VectorBuilder$.MODULE$.$lessinit$greater$default$2(), ring$mcD$sp, man);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
