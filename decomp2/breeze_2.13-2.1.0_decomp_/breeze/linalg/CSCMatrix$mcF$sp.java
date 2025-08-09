package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import breeze.util.ReflectionUtil$;
import java.util.Arrays;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ClassTag;

public class CSCMatrix$mcF$sp extends CSCMatrix implements Matrix$mcF$sp {
   public float[] _data$mcF$sp;
   public final Zero evidence$1$mcF$sp;
   private final int[] _rowIndices;

   public final float apply(final Tuple2 i) {
      return Matrix$mcF$sp.apply$(this, i);
   }

   public final float apply$mcF$sp(final Tuple2 i) {
      return Matrix$mcF$sp.apply$mcF$sp$(this, i);
   }

   public final void update(final Tuple2 i, final float e) {
      Matrix$mcF$sp.update$(this, i, e);
   }

   public final void update$mcF$sp(final Tuple2 i, final float e) {
      Matrix$mcF$sp.update$mcF$sp$(this, i, e);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcF$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcF$sp.map$mcF$sp$(this, fn, canMapValues);
   }

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

   public float apply(final int row, final int col) {
      return this.apply$mcF$sp(row, col);
   }

   public float apply$mcF$sp(final int row, final int col) {
      if (row < this.rows() && col < this.cols() && row >= 0 && col >= 0) {
         int ind = this.breeze$linalg$CSCMatrix$$locate(row, col);
         return ind < 0 ? this.zero$mcF$sp() : this.data$mcF$sp()[ind];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void update(final int row, final int col, final float v) {
      this.update$mcF$sp(row, col, v);
   }

   public void update$mcF$sp(final int row, final int col, final float v) {
      if (row < this.rows() && col < this.cols() && row >= 0 && col >= 0) {
         int ind = this.breeze$linalg$CSCMatrix$$locate(row, col);
         if (ind >= 0) {
            this.data$mcF$sp()[ind] = v;
         } else if (v != this.zero$mcF$sp()) {
            int insertPos = ~ind;
            this.used_$eq(this.used() + 1);
            if (this.used() > this.data$mcF$sp().length) {
               int newLength = this.data$mcF$sp().length == 0 ? 4 : (this.data$mcF$sp().length < 1024 ? this.data$mcF$sp().length * 2 : (this.data$mcF$sp().length < 2048 ? this.data$mcF$sp().length + 1024 : (this.data$mcF$sp().length < 4096 ? this.data$mcF$sp().length + 2048 : (this.data$mcF$sp().length < 8192 ? this.data$mcF$sp().length + 4096 : (this.data$mcF$sp().length < 16384 ? this.data$mcF$sp().length + 8192 : this.data$mcF$sp().length + 16384)))));
               int[] newIndex = Arrays.copyOf(this.rowIndices(), newLength);
               float[] newData = (float[])ArrayUtil$.MODULE$.copyOf(this.data$mcF$sp(), newLength);
               System.arraycopy(this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos, newIndex, insertPos + 1, this.used() - insertPos - 1);
               System.arraycopy(this.data$mcF$sp(), insertPos, newData, insertPos + 1, this.used() - insertPos - 1);
               this.breeze$linalg$CSCMatrix$$_rowIndices_$eq(newIndex);
               this._data_$eq(newData);
            } else if (this.used() - insertPos > 1) {
               System.arraycopy(this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos, this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos + 1, this.used() - insertPos - 1);
               System.arraycopy(this.data$mcF$sp(), insertPos, this.data$mcF$sp(), insertPos + 1, this.used() - insertPos - 1);
            }

            this.rowIndices()[insertPos] = row;
            this.data$mcF$sp()[insertPos] = v;
            int index$macro$2 = col + 1;

            for(int end$macro$3 = this.cols(); index$macro$2 <= end$macro$3; ++index$macro$2) {
               ++this.colPtrs()[index$macro$2];
            }
         }

      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public CSCMatrix repr() {
      return this.repr$mcF$sp();
   }

   public CSCMatrix repr$mcF$sp() {
      return this;
   }

   public float zero() {
      return this.zero$mcF$sp();
   }

   public float zero$mcF$sp() {
      return ((Zero).MODULE$.implicitly(this.evidence$1$mcF$sp)).zero$mcF$sp();
   }

   public void use(final CSCMatrix matrix) {
      this.use$mcF$sp(matrix);
   }

   public void use$mcF$sp(final CSCMatrix matrix) {
      this.use$mcF$sp(matrix.data$mcF$sp(), matrix.colPtrs(), matrix.rowIndices(), matrix.used());
   }

   public void use(final float[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
      this.use$mcF$sp(data, colPtrs, rowIndices, used);
   }

   public void use$mcF$sp(final float[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
      int left$macro$1 = colPtrs.length;
      int right$macro$2 = this.colPtrs().length;
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(64)).append("requirement failed: ").append("colPtrs.length == this.colPtrs.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         boolean cond$macro$3 = used >= 0;
         if (!cond$macro$3) {
            throw new IllegalArgumentException("requirement failed: used.>=(0)");
         } else {
            boolean cond$macro$4 = data.length >= used;
            if (!cond$macro$4) {
               throw new IllegalArgumentException("requirement failed: data.length.>=(used)");
            } else {
               boolean cond$macro$5 = rowIndices.length >= used;
               if (!cond$macro$5) {
                  throw new IllegalArgumentException("requirement failed: rowIndices.length.>=(used)");
               } else {
                  this._data_$eq(data);
                  System.arraycopy(colPtrs, 0, this.colPtrs(), 0, colPtrs.length);
                  this.breeze$linalg$CSCMatrix$$_rowIndices_$eq(rowIndices);
                  this.used_$eq(used);
               }
            }
         }
      }
   }

   public CSCMatrix copy() {
      return this.copy$mcF$sp();
   }

   public CSCMatrix copy$mcF$sp() {
      return new CSCMatrix$mcF$sp((float[])ArrayUtil$.MODULE$.copyOf(this._data(), this.activeSize()), this.rows(), this.cols(), (int[])this.colPtrs().clone(), this.activeSize(), (int[])this.breeze$linalg$CSCMatrix$$_rowIndices().clone(), this.evidence$1$mcF$sp);
   }

   public SparseVector flatten(final View view) {
      return this.flatten$mcF$sp(view);
   }

   public SparseVector flatten$mcF$sp(final View view) {
      Object var2;
      if (View.Require$.MODULE$.equals(view)) {
         int[] indices = new int[this.data$mcF$sp().length];
         int j = 0;

         for(int ind = 0; j < this.cols(); ++j) {
            for(int ip = this.colPtrs()[j]; ip < this.colPtrs()[j + 1]; ++ind) {
               int i = this.rowIndices()[ip];
               indices[ind] = i * this.rows() + j;
               ++ip;
            }
         }

         var2 = new SparseVector$mcF$sp(indices, this.data$mcF$sp(), this.activeSize(), this.rows() * this.cols(), this.evidence$1$mcF$sp);
      } else if (View.Copy$.MODULE$.equals(view)) {
         ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcF$sp());
         SparseVector sv = SparseVector$.MODULE$.zeros$mFc$sp(this.rows() * this.cols(), man, this.evidence$1$mcF$sp);

         for(int j = 0; j < this.cols(); ++j) {
            for(int ip = this.colPtrs()[j]; ip < this.colPtrs()[j + 1]; ++ip) {
               int i = this.rowIndices()[ip];
               sv.update$mcF$sp(i * this.cols() + j, this.data$mcF$sp()[ip]);
            }
         }

         var2 = sv;
      } else {
         if (!View.Prefer$.MODULE$.equals(view)) {
            throw new MatchError(view);
         }

         var2 = this.flatten$mcF$sp(View.Require$.MODULE$);
      }

      return (SparseVector)var2;
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix$mcF$sp(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcF$sp(final ClassTag cm, final Zero zero) {
      return this.toDense$mcF$sp();
   }

   public DenseMatrix toDense() {
      return this.toDense$mcF$sp();
   }

   public DenseMatrix toDense$mcF$sp() {
      ClassTag ctg = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcF$sp());
      DenseMatrix res = DenseMatrix$.MODULE$.zeros$mFc$sp(this.rows(), this.cols(), ctg, this.evidence$1$mcF$sp);

      for(int i = 0; i < this.cols(); ++i) {
         for(int j = this.colPtrs()[i]; j < this.colPtrs()[i + 1]; ++j) {
            res.update$mcF$sp(this.rowIndices()[j], i, this.data$mcF$sp()[j]);
         }
      }

      return res;
   }

   public boolean specInstance$() {
      return true;
   }

   public CSCMatrix$mcF$sp(final float[] _data$mcF$sp, final int rows, final int cols, final int[] colPtrs, final int used, final int[] _rowIndices, final Zero evidence$1$mcF$sp) {
      this._data$mcF$sp = _data$mcF$sp;
      this.evidence$1$mcF$sp = evidence$1$mcF$sp;
      this._rowIndices = _rowIndices;
      super(_data$mcF$sp, rows, cols, colPtrs, used, _rowIndices, evidence$1$mcF$sp);
   }

   public CSCMatrix$mcF$sp(final float[] data$mcF$sp, final int rows, final int cols, final int[] colPtrs, final int[] rowIndices, final Zero evidence$2$mcF$sp) {
      this(data$mcF$sp, rows, cols, colPtrs, data$mcF$sp.length, rowIndices, evidence$2$mcF$sp);
   }
}
