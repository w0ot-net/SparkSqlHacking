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

public class VectorBuilder$mcI$sp extends VectorBuilder {
   private static final long serialVersionUID = 1L;
   public int[] _data$mcI$sp;
   public final Semiring ring$mcI$sp;
   private final int[] _index;
   private final int used;

   public int[] _data$mcI$sp() {
      return this._data$mcI$sp;
   }

   public int[] _data() {
      return this._data$mcI$sp();
   }

   public void _data$mcI$sp_$eq(final int[] x$1) {
      this._data$mcI$sp = x$1;
   }

   public void _data_$eq(final int[] x$1) {
      this._data$mcI$sp_$eq(x$1);
   }

   public int[] data() {
      return this.data$mcI$sp();
   }

   public int[] data$mcI$sp() {
      return this._data();
   }

   public VectorBuilder repr() {
      return this.repr$mcI$sp();
   }

   public VectorBuilder repr$mcI$sp() {
      return this;
   }

   public int apply(final int i) {
      return this.apply$mcI$sp(i);
   }

   public int apply$mcI$sp(final int i) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      int off = 0;

      int acc;
      for(acc = this.ring$mcI$sp.zero$mcI$sp(); off < this.breeze$linalg$VectorBuilder$$used(); ++off) {
         if (this.breeze$linalg$VectorBuilder$$_index()[off] == i) {
            acc = this.ring$mcI$sp.$plus$mcI$sp(acc, this._data()[off]);
         }
      }

      return acc;
   }

   public void update(final int i, final int v) {
      this.update$mcI$sp(i, v);
   }

   public void update$mcI$sp(final int i, final int v) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      boolean marked = false;

      for(int off = 0; off < this.breeze$linalg$VectorBuilder$$used(); ++off) {
         if (this.breeze$linalg$VectorBuilder$$_index()[off] == i) {
            if (!marked) {
               this._data()[off] = v;
            } else {
               this._data()[off] = this.ring$mcI$sp.zero$mcI$sp();
            }

            marked = true;
         }
      }

   }

   public void add(final int i, final int v) {
      this.add$mcI$sp(i, v);
   }

   public void add$mcI$sp(final int i, final int v) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      if (this._data().length <= this.breeze$linalg$VectorBuilder$$used()) {
         this.breeze$linalg$VectorBuilder$$reallocate(.MODULE$.max(this._data().length * 2, 1));
      }

      this._data()[this.breeze$linalg$VectorBuilder$$used()] = v;
      this.breeze$linalg$VectorBuilder$$_index()[this.breeze$linalg$VectorBuilder$$used()] = i;
      this.breeze$linalg$VectorBuilder$$used_$eq(this.breeze$linalg$VectorBuilder$$used() + 1);
   }

   public int default() {
      return this.default$mcI$sp();
   }

   public int default$mcI$sp() {
      return this.ring$mcI$sp.zero$mcI$sp();
   }

   public VectorBuilder copy() {
      return this.copy$mcI$sp();
   }

   public VectorBuilder copy$mcI$sp() {
      return new VectorBuilder$mcI$sp((int[])ArrayUtil$.MODULE$.copyOf(this.index(), this.index().length), (int[])ArrayUtil$.MODULE$.copyOf(this.data$mcI$sp(), this.index().length), this.activeSize(), this.size(), this.ring$mcI$sp);
   }

   public VectorBuilder zerosLike() {
      return this.zerosLike$mcI$sp();
   }

   public VectorBuilder zerosLike$mcI$sp() {
      return new VectorBuilder$mcI$sp(new int[0], (int[])ArrayUtil$.MODULE$.newArrayLike(this.data$mcI$sp(), 0), 0, this.size(), this.ring$mcI$sp);
   }

   public HashVector toHashVector() {
      return this.toHashVector$mcI$sp();
   }

   public HashVector toHashVector$mcI$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcI$sp());
      HashVector hv = HashVector$.MODULE$.zeros$mIc$sp(this.length(), man, Zero$.MODULE$.zeroFromSemiring(this.ring$mcI$sp));

      for(int i = 0; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
         hv.update$mcI$sp(this.index()[i], this.ring$mcI$sp.$plus$mcI$sp(hv.apply$mcI$sp(this.index()[i]), this.data$mcI$sp()[i]));
      }

      return hv;
   }

   public DenseVector toDenseVector() {
      return this.toDenseVector$mcI$sp();
   }

   public DenseVector toDenseVector$mcI$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcI$sp());
      DenseVector hv = DenseVector$.MODULE$.zeros$mIc$sp(this.length(), man, Zero$.MODULE$.zeroFromSemiring(this.ring$mcI$sp));

      for(int i = 0; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
         hv.update$mcI$sp(this.index()[i], this.ring$mcI$sp.$plus$mcI$sp(hv.apply$mcI$sp(this.index()[i]), this.data$mcI$sp()[i]));
      }

      return hv;
   }

   public SparseVector toSparseVector() {
      return this.toSparseVector$mcI$sp();
   }

   public SparseVector toSparseVector$mcI$sp() {
      return this.toSparseVector$mcI$sp(this.toSparseVector$default$1(), this.toSparseVector$default$2());
   }

   public SparseVector toSparseVector(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      return this.toSparseVector$mcI$sp(alreadySorted, keysAlreadyUnique);
   }

   public SparseVector toSparseVector$mcI$sp(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      int[] index = this.index();
      int[] values = this.data$mcI$sp();
      if (alreadySorted && keysAlreadyUnique) {
         return new SparseVector$mcI$sp(index, values, this.breeze$linalg$VectorBuilder$$used(), this.length(), Zero$.MODULE$.zeroFromSemiring(this.ring$mcI$sp));
      } else {
         int[] outIndex = (int[])ArrayUtil$.MODULE$.copyOf(index, this.breeze$linalg$VectorBuilder$$used());
         int[] outValues = (int[])ArrayUtil$.MODULE$.copyOf(values, this.breeze$linalg$VectorBuilder$$used());
         if (!alreadySorted) {
            Sorting$.MODULE$.indirectSort$mIc$sp((int[])outIndex, outValues, 0, this.breeze$linalg$VectorBuilder$$used());
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
                  outValues[out] = this.ring$mcI$sp.$plus$mcI$sp(outValues[out], outValues[i]);
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

         return new SparseVector$mcI$sp(outIndex, outValues, out, this.length(), Zero$.MODULE$.zeroFromSemiring(this.ring$mcI$sp));
      }
   }

   public void use(final int[] index, final int[] data, final int activeSize) {
      this.use$mcI$sp(index, data, activeSize);
   }

   public void use$mcI$sp(final int[] index, final int[] data, final int activeSize) {
      scala.Predef..MODULE$.require(activeSize >= 0, () -> "activeSize must be non-negative");
      scala.Predef..MODULE$.require(data.length >= activeSize, () -> "activeSize must be no greater than array length...");
      this._data_$eq(data);
      this.breeze$linalg$VectorBuilder$$_index_$eq(index);
      this.breeze$linalg$VectorBuilder$$used_$eq(activeSize);
   }

   public int valueAt(final int i) {
      return this.valueAt$mcI$sp(i);
   }

   public int valueAt$mcI$sp(final int i) {
      return this.data$mcI$sp()[i];
   }

   public Vector toVector() {
      return this.toVector$mcI$sp();
   }

   public Vector toVector$mcI$sp() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      return (Vector)(this.size() >= 40 && this.activeSize() <= this.size() / 4 ? this.toSparseVector$mcI$sp() : this.toDenseVector$mcI$sp());
   }

   public boolean specInstance$() {
      return true;
   }

   public VectorBuilder$mcI$sp(final int[] _index, final int[] _data$mcI$sp, final int used, final int length, final Semiring ring$mcI$sp) {
      this._data$mcI$sp = _data$mcI$sp;
      this.ring$mcI$sp = ring$mcI$sp;
      this._index = _index;
      this.used = used;
      super(_index, _data$mcI$sp, used, length, ring$mcI$sp);
   }

   public VectorBuilder$mcI$sp(final int length, final int initialNonZero, final Semiring ring$mcI$sp, final ClassTag man) {
      this(new int[initialNonZero], (int[])man.newArray(initialNonZero), 0, length, ring$mcI$sp);
   }

   public VectorBuilder$mcI$sp(final Semiring ring$mcI$sp, final ClassTag man, final Zero zero$mcI$sp) {
      this(-1, VectorBuilder$.MODULE$.$lessinit$greater$default$2(), ring$mcI$sp, man);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
