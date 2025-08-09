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

public class CSCMatrix$mcD$sp extends CSCMatrix implements Matrix$mcD$sp {
   public double[] _data$mcD$sp;
   public final Zero evidence$1$mcD$sp;
   private final int[] _rowIndices;

   public final double apply(final Tuple2 i) {
      return Matrix$mcD$sp.apply$(this, i);
   }

   public final double apply$mcD$sp(final Tuple2 i) {
      return Matrix$mcD$sp.apply$mcD$sp$(this, i);
   }

   public final void update(final Tuple2 i, final double e) {
      Matrix$mcD$sp.update$(this, i, e);
   }

   public final void update$mcD$sp(final Tuple2 i, final double e) {
      Matrix$mcD$sp.update$mcD$sp$(this, i, e);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcD$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcD$sp.map$mcD$sp$(this, fn, canMapValues);
   }

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

   public double apply(final int row, final int col) {
      return this.apply$mcD$sp(row, col);
   }

   public double apply$mcD$sp(final int row, final int col) {
      if (row < this.rows() && col < this.cols() && row >= 0 && col >= 0) {
         int ind = this.breeze$linalg$CSCMatrix$$locate(row, col);
         return ind < 0 ? this.zero$mcD$sp() : this.data$mcD$sp()[ind];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void update(final int row, final int col, final double v) {
      this.update$mcD$sp(row, col, v);
   }

   public void update$mcD$sp(final int row, final int col, final double v) {
      if (row < this.rows() && col < this.cols() && row >= 0 && col >= 0) {
         int ind = this.breeze$linalg$CSCMatrix$$locate(row, col);
         if (ind >= 0) {
            this.data$mcD$sp()[ind] = v;
         } else if (v != this.zero$mcD$sp()) {
            int insertPos = ~ind;
            this.used_$eq(this.used() + 1);
            if (this.used() > this.data$mcD$sp().length) {
               int newLength = this.data$mcD$sp().length == 0 ? 4 : (this.data$mcD$sp().length < 1024 ? this.data$mcD$sp().length * 2 : (this.data$mcD$sp().length < 2048 ? this.data$mcD$sp().length + 1024 : (this.data$mcD$sp().length < 4096 ? this.data$mcD$sp().length + 2048 : (this.data$mcD$sp().length < 8192 ? this.data$mcD$sp().length + 4096 : (this.data$mcD$sp().length < 16384 ? this.data$mcD$sp().length + 8192 : this.data$mcD$sp().length + 16384)))));
               int[] newIndex = Arrays.copyOf(this.rowIndices(), newLength);
               double[] newData = (double[])ArrayUtil$.MODULE$.copyOf(this.data$mcD$sp(), newLength);
               System.arraycopy(this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos, newIndex, insertPos + 1, this.used() - insertPos - 1);
               System.arraycopy(this.data$mcD$sp(), insertPos, newData, insertPos + 1, this.used() - insertPos - 1);
               this.breeze$linalg$CSCMatrix$$_rowIndices_$eq(newIndex);
               this._data_$eq(newData);
            } else if (this.used() - insertPos > 1) {
               System.arraycopy(this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos, this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos + 1, this.used() - insertPos - 1);
               System.arraycopy(this.data$mcD$sp(), insertPos, this.data$mcD$sp(), insertPos + 1, this.used() - insertPos - 1);
            }

            this.rowIndices()[insertPos] = row;
            this.data$mcD$sp()[insertPos] = v;
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
      return this.repr$mcD$sp();
   }

   public CSCMatrix repr$mcD$sp() {
      return this;
   }

   public double zero() {
      return this.zero$mcD$sp();
   }

   public double zero$mcD$sp() {
      return ((Zero).MODULE$.implicitly(this.evidence$1$mcD$sp)).zero$mcD$sp();
   }

   public void use(final CSCMatrix matrix) {
      this.use$mcD$sp(matrix);
   }

   public void use$mcD$sp(final CSCMatrix matrix) {
      this.use$mcD$sp(matrix.data$mcD$sp(), matrix.colPtrs(), matrix.rowIndices(), matrix.used());
   }

   public void use(final double[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
      this.use$mcD$sp(data, colPtrs, rowIndices, used);
   }

   public void use$mcD$sp(final double[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
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
      return this.copy$mcD$sp();
   }

   public CSCMatrix copy$mcD$sp() {
      return new CSCMatrix$mcD$sp((double[])ArrayUtil$.MODULE$.copyOf(this._data(), this.activeSize()), this.rows(), this.cols(), (int[])this.colPtrs().clone(), this.activeSize(), (int[])this.breeze$linalg$CSCMatrix$$_rowIndices().clone(), this.evidence$1$mcD$sp);
   }

   public SparseVector flatten(final View view) {
      return this.flatten$mcD$sp(view);
   }

   public SparseVector flatten$mcD$sp(final View view) {
      Object var2;
      if (View.Require$.MODULE$.equals(view)) {
         int[] indices = new int[this.data$mcD$sp().length];
         int j = 0;

         for(int ind = 0; j < this.cols(); ++j) {
            for(int ip = this.colPtrs()[j]; ip < this.colPtrs()[j + 1]; ++ind) {
               int i = this.rowIndices()[ip];
               indices[ind] = i * this.rows() + j;
               ++ip;
            }
         }

         var2 = new SparseVector$mcD$sp(indices, this.data$mcD$sp(), this.activeSize(), this.rows() * this.cols(), this.evidence$1$mcD$sp);
      } else if (View.Copy$.MODULE$.equals(view)) {
         ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcD$sp());
         SparseVector sv = SparseVector$.MODULE$.zeros$mDc$sp(this.rows() * this.cols(), man, this.evidence$1$mcD$sp);

         for(int j = 0; j < this.cols(); ++j) {
            for(int ip = this.colPtrs()[j]; ip < this.colPtrs()[j + 1]; ++ip) {
               int i = this.rowIndices()[ip];
               sv.update$mcD$sp(i * this.cols() + j, this.data$mcD$sp()[ip]);
            }
         }

         var2 = sv;
      } else {
         if (!View.Prefer$.MODULE$.equals(view)) {
            throw new MatchError(view);
         }

         var2 = this.flatten$mcD$sp(View.Require$.MODULE$);
      }

      return (SparseVector)var2;
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix$mcD$sp(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcD$sp(final ClassTag cm, final Zero zero) {
      return this.toDense$mcD$sp();
   }

   public DenseMatrix toDense() {
      return this.toDense$mcD$sp();
   }

   public DenseMatrix toDense$mcD$sp() {
      ClassTag ctg = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcD$sp());
      DenseMatrix res = DenseMatrix$.MODULE$.zeros$mDc$sp(this.rows(), this.cols(), ctg, this.evidence$1$mcD$sp);

      for(int i = 0; i < this.cols(); ++i) {
         for(int j = this.colPtrs()[i]; j < this.colPtrs()[i + 1]; ++j) {
            res.update$mcD$sp(this.rowIndices()[j], i, this.data$mcD$sp()[j]);
         }
      }

      return res;
   }

   public boolean specInstance$() {
      return true;
   }

   public CSCMatrix$mcD$sp(final double[] _data$mcD$sp, final int rows, final int cols, final int[] colPtrs, final int used, final int[] _rowIndices, final Zero evidence$1$mcD$sp) {
      this._data$mcD$sp = _data$mcD$sp;
      this.evidence$1$mcD$sp = evidence$1$mcD$sp;
      this._rowIndices = _rowIndices;
      super(_data$mcD$sp, rows, cols, colPtrs, used, _rowIndices, evidence$1$mcD$sp);
   }

   public CSCMatrix$mcD$sp(final double[] data$mcD$sp, final int rows, final int cols, final int[] colPtrs, final int[] rowIndices, final Zero evidence$2$mcD$sp) {
      this(data$mcD$sp, rows, cols, colPtrs, data$mcD$sp.length, rowIndices, evidence$2$mcD$sp);
   }
}
