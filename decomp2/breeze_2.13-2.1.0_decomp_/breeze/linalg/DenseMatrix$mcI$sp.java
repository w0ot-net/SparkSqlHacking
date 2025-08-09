package breeze.linalg;

import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanMapValues;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import breeze.util.ReflectionUtil$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;

public final class DenseMatrix$mcI$sp extends DenseMatrix implements Matrix$mcI$sp {
   private static final long serialVersionUID = 1L;
   public final int[] data$mcI$sp;

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

   public int[] data$mcI$sp() {
      return this.data$mcI$sp;
   }

   public int[] data() {
      return this.data$mcI$sp();
   }

   public int apply(final int row, final int col) {
      return this.apply$mcI$sp(row, col);
   }

   public int apply$mcI$sp(final int row, final int col) {
      if (row >= -this.rows() && row < this.rows()) {
         if (col >= -this.cols() && col < this.cols()) {
            int trueRow = row < 0 ? row + this.rows() : row;
            int trueCol = col < 0 ? col + this.cols() : col;
            return this.data()[this.linearIndex(trueRow, trueCol)];
         } else {
            throw new IndexOutOfBoundsException((new StringBuilder(19)).append(new Tuple2.mcII.sp(row, col)).append(" not in [-").append(this.rows()).append(",").append(this.rows()).append(") x [-").append(this.cols()).append(",").append(this.cols()).append(")").toString());
         }
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(19)).append(new Tuple2.mcII.sp(row, col)).append(" not in [-").append(this.rows()).append(",").append(this.rows()).append(") x [-").append(this.cols()).append(",").append(this.cols()).append(")").toString());
      }
   }

   public void update(final int row, final int col, final int v) {
      this.update$mcI$sp(row, col, v);
   }

   public void update$mcI$sp(final int row, final int col, final int v) {
      if (row >= -this.rows() && row < this.rows()) {
         if (col >= -this.cols() && col < this.cols()) {
            int trueRow = row < 0 ? row + this.rows() : row;
            int trueCol = col < 0 ? col + this.cols() : col;
            this.data()[this.linearIndex(trueRow, trueCol)] = v;
         } else {
            throw new IndexOutOfBoundsException((new StringBuilder(19)).append(new Tuple2.mcII.sp(row, col)).append(" not in [-").append(this.rows()).append(",").append(this.rows()).append(") x [-").append(this.cols()).append(",").append(this.cols()).append(")").toString());
         }
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(19)).append(new Tuple2.mcII.sp(row, col)).append(" not in [-").append(this.rows()).append(",").append(this.rows()).append(") x [-").append(this.cols()).append(",").append(this.cols()).append(")").toString());
      }
   }

   public int[] toArray() {
      return this.toArray$mcI$sp();
   }

   public int[] toArray$mcI$sp() {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      int[] var10000;
      if (this.isContiguous() && !this.isTranspose()) {
         var10000 = (int[])ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.size());
      } else {
         int[] ret = (int[])man.newArray(this.rows() * this.cols());
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = this.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ((i, j) -> ret[i * this.rows() + j] = this.data()[this.linearIndex(j, i)]).apply$mcVII$sp(index$macro$7, index$macro$2);
            }
         }

         var10000 = ret;
      }

      return var10000;
   }

   public DenseVector toDenseVector() {
      return this.toDenseVector$mcI$sp();
   }

   public DenseVector toDenseVector$mcI$sp() {
      return DenseVector$.MODULE$.apply$mIc$sp(this.toArray$mcI$sp());
   }

   public DenseVector flatten(final View view) {
      return this.flatten$mcI$sp(view);
   }

   public DenseVector flatten$mcI$sp(final View view) {
      while(true) {
         DenseVector var3;
         if (View.Require$.MODULE$.equals(view)) {
            if (!this.breeze$linalg$DenseMatrix$$canFlattenView()) {
               throw new UnsupportedOperationException("Cannot make a view of this matrix.");
            }

            var3 = DenseVector$.MODULE$.create(this.data(), this.offset(), 1, this.rows() * this.cols());
         } else {
            if (!View.Copy$.MODULE$.equals(view)) {
               if (View.Prefer$.MODULE$.equals(view)) {
                  view = View$.MODULE$.viewPreferenceFromBoolean(this.breeze$linalg$DenseMatrix$$canFlattenView());
                  continue;
               }

               throw new MatchError(view);
            }

            var3 = this.toDenseVector$mcI$sp();
         }

         return var3;
      }
   }

   public DenseMatrix reshape(final int rows, final int cols, final View view) {
      return this.reshape$mcI$sp(rows, cols, view);
   }

   public DenseMatrix reshape$mcI$sp(final int rows, final int cols, final View view) {
      while(true) {
         int left$macro$1 = rows * cols;
         int right$macro$2 = ((DenseMatrix$mcI$sp)this).size();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(67)).append("requirement failed: ").append(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Cannot reshape a (%d,%d) matrix to a (%d,%d) matrix!"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(((DenseMatrix$mcI$sp)this).rows()), BoxesRunTime.boxToInteger(((DenseMatrix$mcI$sp)this).cols()), BoxesRunTime.boxToInteger(rows), BoxesRunTime.boxToInteger(cols)}))).append(": ").append("rows.*(_cols) == DenseMatrix.this.size (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         }

         if (View.Require$.MODULE$.equals(view)) {
            if (!((DenseMatrix$mcI$sp)this).breeze$linalg$DenseMatrix$$canReshapeView()) {
               throw new UnsupportedOperationException("Cannot make a view of this matrix.");
            }

            DenseMatrix$mcI$sp var5 = new DenseMatrix$mcI$sp(rows, cols, ((DenseMatrix$mcI$sp)this).data(), ((DenseMatrix$mcI$sp)this).offset(), ((DenseMatrix$mcI$sp)this).isTranspose() ? cols : rows, ((DenseMatrix$mcI$sp)this).isTranspose());
            return var5;
         }

         if (View.Copy$.MODULE$.equals(view)) {
            DenseMatrix result = new DenseMatrix$mcI$sp(((DenseMatrix$mcI$sp)this).rows(), ((DenseMatrix$mcI$sp)this).cols(), (int[])ArrayUtil$.MODULE$.newArrayLike(((DenseMatrix$mcI$sp)this).data(), ((DenseMatrix$mcI$sp)this).size()));
            result.$colon$eq(this, HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM());
            view = View.Require$.MODULE$;
            cols = cols;
            rows = rows;
            this = result;
         } else {
            if (!View.Prefer$.MODULE$.equals(view)) {
               throw new MatchError(view);
            }

            view = View$.MODULE$.viewPreferenceFromBoolean(((DenseMatrix$mcI$sp)this).breeze$linalg$DenseMatrix$$canReshapeView());
            cols = cols;
            rows = rows;
         }
      }
   }

   public DenseMatrix repr() {
      return this.repr$mcI$sp();
   }

   public DenseMatrix repr$mcI$sp() {
      return this;
   }

   public int valueAt(final int i) {
      return this.valueAt$mcI$sp(i);
   }

   public int valueAt$mcI$sp(final int i) {
      return this.data()[i];
   }

   public int valueAt(final int row, final int col) {
      return this.valueAt$mcI$sp(row, col);
   }

   public int valueAt$mcI$sp(final int row, final int col) {
      return this.apply$mcI$sp(row, col);
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix$mcI$sp(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcI$sp(final ClassTag cm, final Zero zero) {
      DenseMatrix result = DenseMatrix$.MODULE$.create$mIc$sp(this.rows(), this.cols(), (int[])cm.newArray(this.size()), zero);
      result.$colon$eq(this, HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM());
      return result;
   }

   public DenseMatrix copy() {
      return this.copy$mcI$sp();
   }

   public DenseMatrix copy$mcI$sp() {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      DenseMatrix result = DenseMatrix$.MODULE$.create$mIc$sp(this.rows(), this.cols(), (int[])man.newArray(this.size()), this.breeze$linalg$DenseMatrix$$dontNeedZero());
      result.$colon$eq(this, HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM());
      return result;
   }

   public DenseMatrix delete(final int row, final Axis._0$ axis) {
      return this.delete$mcI$sp(row, axis);
   }

   public DenseMatrix delete$mcI$sp(final int row, final Axis._0$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      boolean cond$macro$1 = row >= 0 && row < this.rows();
      if (!cond$macro$1) {
         throw new IllegalArgumentException((new StringBuilder(64)).append("requirement failed: ").append((new StringBuilder(28)).append("row ").append(row).append(" is not in bounds: [0, ").append(this.rows()).append(")").toString()).append(": ").append("row.>=(0).&&(row.<(DenseMatrix.this.rows))").toString());
      } else {
         return row == 0 ? ((DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), this.rows()), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows())).copy$mcI$sp() : (row == this.rows() - 1 ? ((DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.rows() - 1), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows())).copy$mcI$sp() : DenseMatrix$.MODULE$.vertcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), row), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()), (DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(row + 1), this.rows()), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows())}), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM(), man, this.breeze$linalg$DenseMatrix$$dontNeedZero()));
      }
   }

   public DenseMatrix delete(final int col, final Axis._1$ axis) {
      return this.delete$mcI$sp(col, axis);
   }

   public DenseMatrix delete$mcI$sp(final int col, final Axis._1$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      boolean cond$macro$1 = col >= 0 && col < this.cols();
      if (!cond$macro$1) {
         throw new IllegalArgumentException((new StringBuilder(64)).append("requirement failed: ").append((new StringBuilder(28)).append("col ").append(col).append(" is not in bounds: [0, ").append(this.cols()).append(")").toString()).append(": ").append("col.>=(0).&&(col.<(DenseMatrix.this.cols))").toString());
      } else {
         return col == 0 ? ((DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), this.cols()), HasOps$.MODULE$.canSliceCols())).copy$mcI$sp() : (col == this.cols() - 1 ? ((DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.cols() - 1), HasOps$.MODULE$.canSliceCols())).copy$mcI$sp() : DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), col), HasOps$.MODULE$.canSliceCols()), (DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(col + 1), this.cols()), HasOps$.MODULE$.canSliceCols())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM(), man, this.breeze$linalg$DenseMatrix$$dontNeedZero()));
      }
   }

   public DenseMatrix delete(final Seq rows, final Axis._0$ axis) {
      return this.delete$mcI$sp(rows, axis);
   }

   public DenseMatrix delete$mcI$sp(final Seq rows, final Axis._0$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      DenseMatrix var10000;
      if (rows.isEmpty()) {
         var10000 = this.copy$mcI$sp();
      } else if (rows.size() == 1) {
         var10000 = this.delete$mcI$sp(BoxesRunTime.unboxToInt(rows.apply(0)), axis);
      } else {
         Seq sorted = (Seq)rows.sorted(scala.math.Ordering.Int..MODULE$);
         boolean cond$macro$1 = BoxesRunTime.unboxToInt(sorted.head()) >= 0 && BoxesRunTime.unboxToInt(sorted.last()) < this.rows();
         if (!cond$macro$1) {
            throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: ").append((new StringBuilder(29)).append("row ").append(rows).append(" are not in bounds: [0, ").append(this.rows()).append(")").toString()).append(": ").append("sorted.head.>=(0).&&(sorted.last.<(this.rows))").toString());
         }

         IntRef last = IntRef.create(0);
         Builder matrices = breeze.collection.compat.package$.MODULE$.arraySeqBuilder(scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
         sorted.foreach((JFunction1.mcVI.sp)(index) -> {
            boolean cond$macro$2 = index >= last.elem;
            if (!cond$macro$2) {
               throw new AssertionError("assertion failed: index.>=(last)");
            } else {
               if (index != last.elem) {
                  matrices.$plus$eq(this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(last.elem), index), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()));
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               last.elem = index + 1;
            }
         });
         if (last.elem != this.rows()) {
            matrices.$plus$eq(this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(last.elem), this.rows()), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()));
         } else {
            BoxedUnit var8 = BoxedUnit.UNIT;
         }

         var10000 = DenseMatrix$.MODULE$.vertcat((Seq)matrices.result(), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM(), man, this.breeze$linalg$DenseMatrix$$dontNeedZero());
      }

      return var10000;
   }

   public DenseMatrix delete(final Seq cols, final Axis._1$ axis) {
      return this.delete$mcI$sp(cols, axis);
   }

   public DenseMatrix delete$mcI$sp(final Seq cols, final Axis._1$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      DenseMatrix var10000;
      if (cols.isEmpty()) {
         var10000 = this.copy$mcI$sp();
      } else if (cols.size() == 1) {
         var10000 = this.delete$mcI$sp(BoxesRunTime.unboxToInt(cols.apply(0)), axis);
      } else {
         Seq sorted = (Seq)cols.sorted(scala.math.Ordering.Int..MODULE$);
         boolean cond$macro$1 = BoxesRunTime.unboxToInt(sorted.head()) >= 0 && BoxesRunTime.unboxToInt(sorted.last()) < this.cols();
         if (!cond$macro$1) {
            throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: ").append((new StringBuilder(29)).append("col ").append(cols).append(" are not in bounds: [0, ").append(this.cols()).append(")").toString()).append(": ").append("sorted.head.>=(0).&&(sorted.last.<(this.cols))").toString());
         }

         IntRef last = IntRef.create(0);
         Builder matrices = breeze.collection.compat.package$.MODULE$.arraySeqBuilder(scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
         sorted.foreach((JFunction1.mcVI.sp)(index) -> {
            boolean cond$macro$2 = index >= last.elem;
            if (!cond$macro$2) {
               throw new AssertionError("assertion failed: index.>=(last)");
            } else {
               if (index != last.elem) {
                  matrices.$plus$eq(this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(last.elem), index), HasOps$.MODULE$.canSliceCols()));
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               last.elem = index + 1;
            }
         });
         if (last.elem != this.cols()) {
            matrices.$plus$eq(this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(last.elem), this.cols()), HasOps$.MODULE$.canSliceCols()));
         } else {
            BoxedUnit var8 = BoxedUnit.UNIT;
         }

         var10000 = DenseMatrix$.MODULE$.horzcat((Seq)matrices.result(), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM(), man, this.breeze$linalg$DenseMatrix$$dontNeedZero());
      }

      return var10000;
   }

   public boolean overlaps(final DenseMatrix other) {
      return this.overlaps$mcI$sp(other);
   }

   public boolean overlaps$mcI$sp(final DenseMatrix other) {
      boolean var10000;
      if (this.data() == other.data$mcI$sp()) {
         int astart = this.offset();
         int aend = this.offset() + this.breeze$linalg$DenseMatrix$$footprint();
         int bstart = other.offset();
         int bend = other.offset() + other.breeze$linalg$DenseMatrix$$footprint();
         if (scala.package..MODULE$.Range().apply(astart, aend).contains(bstart) || scala.package..MODULE$.Range().apply(astart, aend).contains(bend) || scala.package..MODULE$.Range().apply(bstart, bend).contains(astart) || scala.package..MODULE$.Range().apply(bstart, bend).contains(aend)) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   public boolean specInstance$() {
      return true;
   }

   public DenseMatrix$mcI$sp(final int rows, final int cols, final int[] data$mcI$sp, final int offset, final int majorStride, final boolean isTranspose) {
      super(rows, cols, (Object)null, offset, majorStride, isTranspose);
      this.data$mcI$sp = data$mcI$sp;
      if (isTranspose && scala.math.package..MODULE$.abs(majorStride) < cols && majorStride != 0) {
         throw new IndexOutOfBoundsException((new StringBuilder(61)).append("MajorStride == ").append(majorStride).append(" is smaller than cols == ").append(cols).append(", which is impossible").toString());
      } else if (!isTranspose && scala.math.package..MODULE$.abs(majorStride) < rows && majorStride != 0) {
         throw new IndexOutOfBoundsException((new StringBuilder(61)).append("MajorStride == ").append(majorStride).append(" is smaller than rows == ").append(rows).append(", which is impossible").toString());
      } else if (rows < 0) {
         throw new IndexOutOfBoundsException((new StringBuilder(38)).append("Rows must be larger than zero. It was ").append(rows).toString());
      } else if (cols < 0) {
         throw new IndexOutOfBoundsException((new StringBuilder(38)).append("Cols must be larger than zero. It was ").append(cols).toString());
      } else if (offset < 0) {
         throw new IndexOutOfBoundsException((new StringBuilder(40)).append("Offset must be larger than zero. It was ").append(offset).toString());
      } else {
         if (majorStride > 0) {
            if (scala.runtime.ScalaRunTime..MODULE$.array_length(this.data()) < this.linearIndex(rows - 1, cols - 1)) {
               throw new IndexOutOfBoundsException((new StringBuilder(57)).append("Storage array has size ").append(scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.genericArrayOps(this.data()))).append(" but indices can grow as large as ").append(this.linearIndex(rows - 1, cols - 1)).toString());
            }
         } else if (majorStride < 0) {
            if (scala.runtime.ScalaRunTime..MODULE$.array_length(this.data()) < this.linearIndex(rows - 1, 0)) {
               throw new IndexOutOfBoundsException((new StringBuilder(57)).append("Storage array has size ").append(scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.genericArrayOps(this.data()))).append(" but indices can grow as large as ").append(this.linearIndex(rows - 1, cols - 1)).toString());
            }

            if (this.linearIndex(0, cols - 1) < 0) {
               throw new IndexOutOfBoundsException((new StringBuilder(84)).append("Storage array has negative stride ").append(majorStride).append(" and offset ").append(offset).append(" which can result in negative indices.").toString());
            }
         }

         DenseMatrix$.MODULE$.breeze$linalg$DenseMatrix$$init();
      }
   }

   public DenseMatrix$mcI$sp(final int rows, final int cols, final ClassTag man) {
      this(rows, cols, (int[])man.newArray(rows * cols), 0, rows, DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public DenseMatrix$mcI$sp(final int rows, final int cols, final int[] data$mcI$sp, final int offset) {
      this(rows, cols, data$mcI$sp, offset, rows, DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public DenseMatrix$mcI$sp(final int rows, final int cols, final int[] data$mcI$sp) {
      this(rows, cols, data$mcI$sp, 0, rows, DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public DenseMatrix$mcI$sp(final int rows, final int[] data$mcI$sp, final int offset) {
      int left$macro$1 = data$mcI$sp.length % rows;
      int right$macro$2 = 0;
      if (left$macro$1 != 0) {
         throw new AssertionError((new StringBuilder(60)).append("assertion failed: ").append("data.length.%(rows) == 0 (").append(left$macro$1).append(" ").append("!=").append(" ").append(0).append(")").toString());
      } else {
         this(rows, data$mcI$sp.length / rows, data$mcI$sp, offset);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
