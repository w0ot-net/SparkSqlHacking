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

public class CSCMatrix$mcI$sp extends CSCMatrix implements Matrix$mcI$sp {
   public int[] _data$mcI$sp;
   public final Zero evidence$1$mcI$sp;
   private final int[] _rowIndices;

   public final int apply(final Tuple2 i) {
      return Matrix$mcI$sp.apply$(this, i);
   }

   public final int apply$mcI$sp(final Tuple2 i) {
      return Matrix$mcI$sp.apply$mcI$sp$(this, i);
   }

   public final void update(final Tuple2 i, final int e) {
      Matrix$mcI$sp.update$(this, i, e);
   }

   public final void update$mcI$sp(final Tuple2 i, final int e) {
      Matrix$mcI$sp.update$mcI$sp$(this, i, e);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcI$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcI$sp.map$mcI$sp$(this, fn, canMapValues);
   }

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

   public int apply(final int row, final int col) {
      return this.apply$mcI$sp(row, col);
   }

   public int apply$mcI$sp(final int row, final int col) {
      if (row < this.rows() && col < this.cols() && row >= 0 && col >= 0) {
         int ind = this.breeze$linalg$CSCMatrix$$locate(row, col);
         return ind < 0 ? this.zero$mcI$sp() : this.data$mcI$sp()[ind];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void update(final int row, final int col, final int v) {
      this.update$mcI$sp(row, col, v);
   }

   public void update$mcI$sp(final int row, final int col, final int v) {
      if (row < this.rows() && col < this.cols() && row >= 0 && col >= 0) {
         int ind = this.breeze$linalg$CSCMatrix$$locate(row, col);
         if (ind >= 0) {
            this.data$mcI$sp()[ind] = v;
         } else if (v != this.zero$mcI$sp()) {
            int insertPos = ~ind;
            this.used_$eq(this.used() + 1);
            if (this.used() > this.data$mcI$sp().length) {
               int newLength = this.data$mcI$sp().length == 0 ? 4 : (this.data$mcI$sp().length < 1024 ? this.data$mcI$sp().length * 2 : (this.data$mcI$sp().length < 2048 ? this.data$mcI$sp().length + 1024 : (this.data$mcI$sp().length < 4096 ? this.data$mcI$sp().length + 2048 : (this.data$mcI$sp().length < 8192 ? this.data$mcI$sp().length + 4096 : (this.data$mcI$sp().length < 16384 ? this.data$mcI$sp().length + 8192 : this.data$mcI$sp().length + 16384)))));
               int[] newIndex = Arrays.copyOf(this.rowIndices(), newLength);
               int[] newData = (int[])ArrayUtil$.MODULE$.copyOf(this.data$mcI$sp(), newLength);
               System.arraycopy(this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos, newIndex, insertPos + 1, this.used() - insertPos - 1);
               System.arraycopy(this.data$mcI$sp(), insertPos, newData, insertPos + 1, this.used() - insertPos - 1);
               this.breeze$linalg$CSCMatrix$$_rowIndices_$eq(newIndex);
               this._data_$eq(newData);
            } else if (this.used() - insertPos > 1) {
               System.arraycopy(this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos, this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos + 1, this.used() - insertPos - 1);
               System.arraycopy(this.data$mcI$sp(), insertPos, this.data$mcI$sp(), insertPos + 1, this.used() - insertPos - 1);
            }

            this.rowIndices()[insertPos] = row;
            this.data$mcI$sp()[insertPos] = v;
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
      return this.repr$mcI$sp();
   }

   public CSCMatrix repr$mcI$sp() {
      return this;
   }

   public int zero() {
      return this.zero$mcI$sp();
   }

   public int zero$mcI$sp() {
      return ((Zero).MODULE$.implicitly(this.evidence$1$mcI$sp)).zero$mcI$sp();
   }

   public void use(final CSCMatrix matrix) {
      this.use$mcI$sp(matrix);
   }

   public void use$mcI$sp(final CSCMatrix matrix) {
      this.use$mcI$sp(matrix.data$mcI$sp(), matrix.colPtrs(), matrix.rowIndices(), matrix.used());
   }

   public void use(final int[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
      this.use$mcI$sp(data, colPtrs, rowIndices, used);
   }

   public void use$mcI$sp(final int[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
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
      return this.copy$mcI$sp();
   }

   public CSCMatrix copy$mcI$sp() {
      return new CSCMatrix$mcI$sp((int[])ArrayUtil$.MODULE$.copyOf(this._data(), this.activeSize()), this.rows(), this.cols(), (int[])this.colPtrs().clone(), this.activeSize(), (int[])this.breeze$linalg$CSCMatrix$$_rowIndices().clone(), this.evidence$1$mcI$sp);
   }

   public SparseVector flatten(final View view) {
      return this.flatten$mcI$sp(view);
   }

   public SparseVector flatten$mcI$sp(final View view) {
      Object var2;
      if (View.Require$.MODULE$.equals(view)) {
         int[] indices = new int[this.data$mcI$sp().length];
         int j = 0;

         for(int ind = 0; j < this.cols(); ++j) {
            for(int ip = this.colPtrs()[j]; ip < this.colPtrs()[j + 1]; ++ind) {
               int i = this.rowIndices()[ip];
               indices[ind] = i * this.rows() + j;
               ++ip;
            }
         }

         var2 = new SparseVector$mcI$sp(indices, this.data$mcI$sp(), this.activeSize(), this.rows() * this.cols(), this.evidence$1$mcI$sp);
      } else if (View.Copy$.MODULE$.equals(view)) {
         ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcI$sp());
         SparseVector sv = SparseVector$.MODULE$.zeros$mIc$sp(this.rows() * this.cols(), man, this.evidence$1$mcI$sp);

         for(int j = 0; j < this.cols(); ++j) {
            for(int ip = this.colPtrs()[j]; ip < this.colPtrs()[j + 1]; ++ip) {
               int i = this.rowIndices()[ip];
               sv.update$mcI$sp(i * this.cols() + j, this.data$mcI$sp()[ip]);
            }
         }

         var2 = sv;
      } else {
         if (!View.Prefer$.MODULE$.equals(view)) {
            throw new MatchError(view);
         }

         var2 = this.flatten$mcI$sp(View.Require$.MODULE$);
      }

      return (SparseVector)var2;
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix$mcI$sp(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcI$sp(final ClassTag cm, final Zero zero) {
      return this.toDense$mcI$sp();
   }

   public DenseMatrix toDense() {
      return this.toDense$mcI$sp();
   }

   public DenseMatrix toDense$mcI$sp() {
      ClassTag ctg = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data$mcI$sp());
      DenseMatrix res = DenseMatrix$.MODULE$.zeros$mIc$sp(this.rows(), this.cols(), ctg, this.evidence$1$mcI$sp);

      for(int i = 0; i < this.cols(); ++i) {
         for(int j = this.colPtrs()[i]; j < this.colPtrs()[i + 1]; ++j) {
            res.update$mcI$sp(this.rowIndices()[j], i, this.data$mcI$sp()[j]);
         }
      }

      return res;
   }

   public boolean specInstance$() {
      return true;
   }

   public CSCMatrix$mcI$sp(final int[] _data$mcI$sp, final int rows, final int cols, final int[] colPtrs, final int used, final int[] _rowIndices, final Zero evidence$1$mcI$sp) {
      this._data$mcI$sp = _data$mcI$sp;
      this.evidence$1$mcI$sp = evidence$1$mcI$sp;
      this._rowIndices = _rowIndices;
      super(_data$mcI$sp, rows, cols, colPtrs, used, _rowIndices, evidence$1$mcI$sp);
   }

   public CSCMatrix$mcI$sp(final int[] data$mcI$sp, final int rows, final int cols, final int[] colPtrs, final int[] rowIndices, final Zero evidence$2$mcI$sp) {
      this(data$mcI$sp, rows, cols, colPtrs, data$mcI$sp.length, rowIndices, evidence$2$mcI$sp);
   }
}
