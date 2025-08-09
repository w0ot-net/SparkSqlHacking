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

public class CSCMatrix$mcJ$sp extends CSCMatrix implements Matrix$mcJ$sp {
   public long[] _data$mcJ$sp;
   public final Zero evidence$1$mcJ$sp;
   private final int[] _rowIndices;

   public final long apply(final Tuple2 i) {
      return Matrix$mcJ$sp.apply$(this, i);
   }

   public final long apply$mcJ$sp(final Tuple2 i) {
      return Matrix$mcJ$sp.apply$mcJ$sp$(this, i);
   }

   public final void update(final Tuple2 i, final long e) {
      Matrix$mcJ$sp.update$(this, i, e);
   }

   public final void update$mcJ$sp(final Tuple2 i, final long e) {
      Matrix$mcJ$sp.update$mcJ$sp$(this, i, e);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcJ$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcJ$sp.map$mcJ$sp$(this, fn, canMapValues);
   }

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

   public long apply(final int row, final int col) {
      return this.apply$mcJ$sp(row, col);
   }

   public long apply$mcJ$sp(final int row, final int col) {
      if (row < this.rows() && col < this.cols() && row >= 0 && col >= 0) {
         int ind = this.breeze$linalg$CSCMatrix$$locate(row, col);
         return ind < 0 ? this.zero$mcJ$sp() : this.data$mcJ$sp()[ind];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void update(final int row, final int col, final long v) {
      this.update$mcJ$sp(row, col, v);
   }

   public void update$mcJ$sp(final int row, final int col, final long v) {
      if (row < this.rows() && col < this.cols() && row >= 0 && col >= 0) {
         int ind = this.breeze$linalg$CSCMatrix$$locate(row, col);
         if (ind >= 0) {
            this.data$mcJ$sp()[ind] = v;
         } else if (v != this.zero$mcJ$sp()) {
            int insertPos = ~ind;
            this.used_$eq(this.used() + 1);
            if (this.used() > this.data$mcJ$sp().length) {
               int newLength = this.data$mcJ$sp().length == 0 ? 4 : (this.data$mcJ$sp().length < 1024 ? this.data$mcJ$sp().length * 2 : (this.data$mcJ$sp().length < 2048 ? this.data$mcJ$sp().length + 1024 : (this.data$mcJ$sp().length < 4096 ? this.data$mcJ$sp().length + 2048 : (this.data$mcJ$sp().length < 8192 ? this.data$mcJ$sp().length + 4096 : (this.data$mcJ$sp().length < 16384 ? this.data$mcJ$sp().length + 8192 : this.data$mcJ$sp().length + 16384)))));
               int[] newIndex = Arrays.copyOf(this.rowIndices(), newLength);
               long[] newData = (long[])ArrayUtil$.MODULE$.copyOf(this.data$mcJ$sp(), newLength);
               System.arraycopy(this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos, newIndex, insertPos + 1, this.used() - insertPos - 1);
               System.arraycopy(this.data$mcJ$sp(), insertPos, newData, insertPos + 1, this.used() - insertPos - 1);
               this.breeze$linalg$CSCMatrix$$_rowIndices_$eq(newIndex);
               this._data_$eq(newData);
            } else if (this.used() - insertPos > 1) {
               System.arraycopy(this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos, this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos + 1, this.used() - insertPos - 1);
               System.arraycopy(this.data$mcJ$sp(), insertPos, this.data$mcJ$sp(), insertPos + 1, this.used() - insertPos - 1);
            }

            this.rowIndices()[insertPos] = row;
            this.data$mcJ$sp()[insertPos] = v;
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
      return this.repr$mcJ$sp();
   }

   public CSCMatrix repr$mcJ$sp() {
      return this;
   }

   public long zero() {
      return this.zero$mcJ$sp();
   }

   public long zero$mcJ$sp() {
      return ((Zero).MODULE$.implicitly(this.evidence$1$mcJ$sp)).zero$mcJ$sp();
   }

   public void use(final CSCMatrix matrix) {
      this.use$mcJ$sp(matrix);
   }

   public void use$mcJ$sp(final CSCMatrix matrix) {
      this.use$mcJ$sp(matrix.data$mcJ$sp(), matrix.colPtrs(), matrix.rowIndices(), matrix.used());
   }

   public void use(final long[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
      this.use$mcJ$sp(data, colPtrs, rowIndices, used);
   }

   public void use$mcJ$sp(final long[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
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
      return this.copy$mcJ$sp();
   }

   public CSCMatrix copy$mcJ$sp() {
      return new CSCMatrix$mcJ$sp((long[])ArrayUtil$.MODULE$.copyOf(this._data(), this.activeSize()), this.rows(), this.cols(), (int[])this.colPtrs().clone(), this.activeSize(), (int[])this.breeze$linalg$CSCMatrix$$_rowIndices().clone(), this.evidence$1$mcJ$sp);
   }

   public SparseVector flatten(final View view) {
      return this.flatten$mcJ$sp(view);
   }

   public SparseVector flatten$mcJ$sp(final View view) {
      Object var2;
      if (View.Require$.MODULE$.equals(view)) {
         int[] indices = new int[this.data$mcJ$sp().length];
         int j = 0;

         for(int ind = 0; j < this.cols(); ++j) {
            for(int ip = this.colPtrs()[j]; ip < this.colPtrs()[j + 1]; ++ind) {
               int i = this.rowIndices()[ip];
               indices[ind] = i * this.rows() + j;
               ++ip;
            }
         }

         var2 = new SparseVector$mcJ$sp(indices, this.data$mcJ$sp(), this.activeSize(), this.rows() * this.cols(), this.evidence$1$mcJ$sp);
      } else if (View.Copy$.MODULE$.equals(view)) {
         ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcJ$sp());
         SparseVector sv = SparseVector$.MODULE$.zeros$mJc$sp(this.rows() * this.cols(), man, this.evidence$1$mcJ$sp);

         for(int j = 0; j < this.cols(); ++j) {
            for(int ip = this.colPtrs()[j]; ip < this.colPtrs()[j + 1]; ++ip) {
               int i = this.rowIndices()[ip];
               sv.update$mcJ$sp(i * this.cols() + j, this.data$mcJ$sp()[ip]);
            }
         }

         var2 = sv;
      } else {
         if (!View.Prefer$.MODULE$.equals(view)) {
            throw new MatchError(view);
         }

         var2 = this.flatten$mcJ$sp(View.Require$.MODULE$);
      }

      return (SparseVector)var2;
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix$mcJ$sp(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcJ$sp(final ClassTag cm, final Zero zero) {
      return this.toDense$mcJ$sp();
   }

   public DenseMatrix toDense() {
      return this.toDense$mcJ$sp();
   }

   public DenseMatrix toDense$mcJ$sp() {
      ClassTag ctg = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcJ$sp());
      DenseMatrix res = DenseMatrix$.MODULE$.zeros$mJc$sp(this.rows(), this.cols(), ctg, this.evidence$1$mcJ$sp);

      for(int i = 0; i < this.cols(); ++i) {
         for(int j = this.colPtrs()[i]; j < this.colPtrs()[i + 1]; ++j) {
            res.update$mcJ$sp(this.rowIndices()[j], i, this.data$mcJ$sp()[j]);
         }
      }

      return res;
   }

   public boolean specInstance$() {
      return true;
   }

   public CSCMatrix$mcJ$sp(final long[] _data$mcJ$sp, final int rows, final int cols, final int[] colPtrs, final int used, final int[] _rowIndices, final Zero evidence$1$mcJ$sp) {
      this._data$mcJ$sp = _data$mcJ$sp;
      this.evidence$1$mcJ$sp = evidence$1$mcJ$sp;
      this._rowIndices = _rowIndices;
      super(_data$mcJ$sp, rows, cols, colPtrs, used, _rowIndices, evidence$1$mcJ$sp);
   }

   public CSCMatrix$mcJ$sp(final long[] data$mcJ$sp, final int rows, final int cols, final int[] colPtrs, final int[] rowIndices, final Zero evidence$2$mcJ$sp) {
      this(data$mcJ$sp, rows, cols, colPtrs, data$mcJ$sp.length, rowIndices, evidence$2$mcJ$sp);
   }
}
