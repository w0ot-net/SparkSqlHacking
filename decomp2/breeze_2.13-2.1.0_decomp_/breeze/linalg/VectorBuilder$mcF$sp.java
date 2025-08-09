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

public class VectorBuilder$mcF$sp extends VectorBuilder {
   private static final long serialVersionUID = 1L;
   public float[] _data$mcF$sp;
   public final Semiring ring$mcF$sp;
   private final int[] _index;
   private final int used;

   public float[] _data$mcF$sp() {
      return this._data$mcF$sp;
   }

   public float[] _data() {
      return this._data$mcF$sp();
   }

   public void _data$mcF$sp_$eq(final float[] x$1) {
      this._data$mcF$sp = x$1;
   }

   public void _data_$eq(final float[] x$1) {
      this._data$mcF$sp_$eq(x$1);
   }

   public float[] data() {
      return this.data$mcF$sp();
   }

   public float[] data$mcF$sp() {
      return this._data();
   }

   public VectorBuilder repr() {
      return this.repr$mcF$sp();
   }

   public VectorBuilder repr$mcF$sp() {
      return this;
   }

   public float apply(final int i) {
      return this.apply$mcF$sp(i);
   }

   public float apply$mcF$sp(final int i) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      int off = 0;

      float acc;
      for(acc = this.ring$mcF$sp.zero$mcF$sp(); off < this.breeze$linalg$VectorBuilder$$used(); ++off) {
         if (this.breeze$linalg$VectorBuilder$$_index()[off] == i) {
            acc = this.ring$mcF$sp.$plus$mcF$sp(acc, this._data()[off]);
         }
      }

      return acc;
   }

   public void update(final int i, final float v) {
      this.update$mcF$sp(i, v);
   }

   public void update$mcF$sp(final int i, final float v) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      boolean marked = false;

      for(int off = 0; off < this.breeze$linalg$VectorBuilder$$used(); ++off) {
         if (this.breeze$linalg$VectorBuilder$$_index()[off] == i) {
            if (!marked) {
               this._data()[off] = v;
            } else {
               this._data()[off] = this.ring$mcF$sp.zero$mcF$sp();
            }

            marked = true;
         }
      }

   }

   public void add(final int i, final float v) {
      this.add$mcF$sp(i, v);
   }

   public void add$mcF$sp(final int i, final float v) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      if (this._data().length <= this.breeze$linalg$VectorBuilder$$used()) {
         this.breeze$linalg$VectorBuilder$$reallocate(.MODULE$.max(this._data().length * 2, 1));
      }

      this._data()[this.breeze$linalg$VectorBuilder$$used()] = v;
      this.breeze$linalg$VectorBuilder$$_index()[this.breeze$linalg$VectorBuilder$$used()] = i;
      this.breeze$linalg$VectorBuilder$$used_$eq(this.breeze$linalg$VectorBuilder$$used() + 1);
   }

   public float default() {
      return this.default$mcF$sp();
   }

   public float default$mcF$sp() {
      return this.ring$mcF$sp.zero$mcF$sp();
   }

   public VectorBuilder copy() {
      return this.copy$mcF$sp();
   }

   public VectorBuilder copy$mcF$sp() {
      return new VectorBuilder$mcF$sp((int[])ArrayUtil$.MODULE$.copyOf(this.index(), this.index().length), (float[])ArrayUtil$.MODULE$.copyOf(this.data$mcF$sp(), this.index().length), this.activeSize(), this.size(), this.ring$mcF$sp);
   }

   public VectorBuilder zerosLike() {
      return this.zerosLike$mcF$sp();
   }

   public VectorBuilder zerosLike$mcF$sp() {
      return new VectorBuilder$mcF$sp(new int[0], (float[])ArrayUtil$.MODULE$.newArrayLike(this.data$mcF$sp(), 0), 0, this.size(), this.ring$mcF$sp);
   }

   public HashVector toHashVector() {
      return this.toHashVector$mcF$sp();
   }

   public HashVector toHashVector$mcF$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcF$sp());
      HashVector hv = HashVector$.MODULE$.zeros$mFc$sp(this.length(), man, Zero$.MODULE$.zeroFromSemiring(this.ring$mcF$sp));

      for(int i = 0; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
         hv.update$mcF$sp(this.index()[i], this.ring$mcF$sp.$plus$mcF$sp(hv.apply$mcF$sp(this.index()[i]), this.data$mcF$sp()[i]));
      }

      return hv;
   }

   public DenseVector toDenseVector() {
      return this.toDenseVector$mcF$sp();
   }

   public DenseVector toDenseVector$mcF$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcF$sp());
      DenseVector hv = DenseVector$.MODULE$.zeros$mFc$sp(this.length(), man, Zero$.MODULE$.zeroFromSemiring(this.ring$mcF$sp));

      for(int i = 0; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
         hv.update$mcF$sp(this.index()[i], this.ring$mcF$sp.$plus$mcF$sp(hv.apply$mcF$sp(this.index()[i]), this.data$mcF$sp()[i]));
      }

      return hv;
   }

   public SparseVector toSparseVector() {
      return this.toSparseVector$mcF$sp();
   }

   public SparseVector toSparseVector$mcF$sp() {
      return this.toSparseVector$mcF$sp(this.toSparseVector$default$1(), this.toSparseVector$default$2());
   }

   public SparseVector toSparseVector(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      return this.toSparseVector$mcF$sp(alreadySorted, keysAlreadyUnique);
   }

   public SparseVector toSparseVector$mcF$sp(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      int[] index = this.index();
      float[] values = this.data$mcF$sp();
      if (alreadySorted && keysAlreadyUnique) {
         return new SparseVector$mcF$sp(index, values, this.breeze$linalg$VectorBuilder$$used(), this.length(), Zero$.MODULE$.zeroFromSemiring(this.ring$mcF$sp));
      } else {
         int[] outIndex = (int[])ArrayUtil$.MODULE$.copyOf(index, this.breeze$linalg$VectorBuilder$$used());
         float[] outValues = (float[])ArrayUtil$.MODULE$.copyOf(values, this.breeze$linalg$VectorBuilder$$used());
         if (!alreadySorted) {
            Sorting$.MODULE$.indirectSort$mFc$sp((int[])outIndex, outValues, 0, this.breeze$linalg$VectorBuilder$$used());
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
                  outValues[out] = this.ring$mcF$sp.$plus$mcF$sp(outValues[out], outValues[i]);
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

         return new SparseVector$mcF$sp(outIndex, outValues, out, this.length(), Zero$.MODULE$.zeroFromSemiring(this.ring$mcF$sp));
      }
   }

   public void use(final int[] index, final float[] data, final int activeSize) {
      this.use$mcF$sp(index, data, activeSize);
   }

   public void use$mcF$sp(final int[] index, final float[] data, final int activeSize) {
      scala.Predef..MODULE$.require(activeSize >= 0, () -> "activeSize must be non-negative");
      scala.Predef..MODULE$.require(data.length >= activeSize, () -> "activeSize must be no greater than array length...");
      this._data_$eq(data);
      this.breeze$linalg$VectorBuilder$$_index_$eq(index);
      this.breeze$linalg$VectorBuilder$$used_$eq(activeSize);
   }

   public float valueAt(final int i) {
      return this.valueAt$mcF$sp(i);
   }

   public float valueAt$mcF$sp(final int i) {
      return this.data$mcF$sp()[i];
   }

   public Vector toVector() {
      return this.toVector$mcF$sp();
   }

   public Vector toVector$mcF$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      return (Vector)(this.size() >= 40 && this.activeSize() <= this.size() / 4 ? this.toSparseVector$mcF$sp() : this.toDenseVector$mcF$sp());
   }

   public boolean specInstance$() {
      return true;
   }

   public VectorBuilder$mcF$sp(final int[] _index, final float[] _data$mcF$sp, final int used, final int length, final Semiring ring$mcF$sp) {
      this._data$mcF$sp = _data$mcF$sp;
      this.ring$mcF$sp = ring$mcF$sp;
      this._index = _index;
      this.used = used;
      super(_index, _data$mcF$sp, used, length, ring$mcF$sp);
   }

   public VectorBuilder$mcF$sp(final int length, final int initialNonZero, final Semiring ring$mcF$sp, final ClassTag man) {
      this(new int[initialNonZero], (float[])man.newArray(initialNonZero), 0, length, ring$mcF$sp);
   }

   public VectorBuilder$mcF$sp(final Semiring ring$mcF$sp, final ClassTag man, final Zero zero$mcF$sp) {
      this(-1, VectorBuilder$.MODULE$.$lessinit$greater$default$2(), ring$mcF$sp, man);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
