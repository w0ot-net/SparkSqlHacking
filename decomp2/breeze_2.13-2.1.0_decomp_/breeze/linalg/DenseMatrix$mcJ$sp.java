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

public final class DenseMatrix$mcJ$sp extends DenseMatrix implements Matrix$mcJ$sp {
   private static final long serialVersionUID = 1L;
   public final long[] data$mcJ$sp;

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

   public long[] data$mcJ$sp() {
      return this.data$mcJ$sp;
   }

   public long[] data() {
      return this.data$mcJ$sp();
   }

   public long apply(final int row, final int col) {
      return this.apply$mcJ$sp(row, col);
   }

   public long apply$mcJ$sp(final int row, final int col) {
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

   public void update(final int row, final int col, final long v) {
      this.update$mcJ$sp(row, col, v);
   }

   public void update$mcJ$sp(final int row, final int col, final long v) {
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

   public long[] toArray() {
      return this.toArray$mcJ$sp();
   }

   public long[] toArray$mcJ$sp() {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      long[] var10000;
      if (this.isContiguous() && !this.isTranspose()) {
         var10000 = (long[])ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.size());
      } else {
         long[] ret = (long[])man.newArray(this.rows() * this.cols());
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
      return this.toDenseVector$mcJ$sp();
   }

   public DenseVector toDenseVector$mcJ$sp() {
      return DenseVector$.MODULE$.apply$mJc$sp(this.toArray$mcJ$sp());
   }

   public DenseVector flatten(final View view) {
      return this.flatten$mcJ$sp(view);
   }

   public DenseVector flatten$mcJ$sp(final View view) {
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

            var3 = this.toDenseVector$mcJ$sp();
         }

         return var3;
      }
   }

   public DenseMatrix reshape(final int rows, final int cols, final View view) {
      return this.reshape$mcJ$sp(rows, cols, view);
   }

   public DenseMatrix reshape$mcJ$sp(final int rows, final int cols, final View view) {
      while(true) {
         int left$macro$1 = rows * cols;
         int right$macro$2 = ((DenseMatrix$mcJ$sp)this).size();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(67)).append("requirement failed: ").append(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Cannot reshape a (%d,%d) matrix to a (%d,%d) matrix!"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(((DenseMatrix$mcJ$sp)this).rows()), BoxesRunTime.boxToInteger(((DenseMatrix$mcJ$sp)this).cols()), BoxesRunTime.boxToInteger(rows), BoxesRunTime.boxToInteger(cols)}))).append(": ").append("rows.*(_cols) == DenseMatrix.this.size (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         }

         if (View.Require$.MODULE$.equals(view)) {
            if (!((DenseMatrix$mcJ$sp)this).breeze$linalg$DenseMatrix$$canReshapeView()) {
               throw new UnsupportedOperationException("Cannot make a view of this matrix.");
            }

            DenseMatrix$mcJ$sp var5 = new DenseMatrix$mcJ$sp(rows, cols, ((DenseMatrix$mcJ$sp)this).data(), ((DenseMatrix$mcJ$sp)this).offset(), ((DenseMatrix$mcJ$sp)this).isTranspose() ? cols : rows, ((DenseMatrix$mcJ$sp)this).isTranspose());
            return var5;
         }

         if (View.Copy$.MODULE$.equals(view)) {
            DenseMatrix result = new DenseMatrix$mcJ$sp(((DenseMatrix$mcJ$sp)this).rows(), ((DenseMatrix$mcJ$sp)this).cols(), (long[])ArrayUtil$.MODULE$.newArrayLike(((DenseMatrix$mcJ$sp)this).data(), ((DenseMatrix$mcJ$sp)this).size()));
            result.$colon$eq(this, HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM());
            view = View.Require$.MODULE$;
            cols = cols;
            rows = rows;
            this = result;
         } else {
            if (!View.Prefer$.MODULE$.equals(view)) {
               throw new MatchError(view);
            }

            view = View$.MODULE$.viewPreferenceFromBoolean(((DenseMatrix$mcJ$sp)this).breeze$linalg$DenseMatrix$$canReshapeView());
            cols = cols;
            rows = rows;
         }
      }
   }

   public DenseMatrix repr() {
      return this.repr$mcJ$sp();
   }

   public DenseMatrix repr$mcJ$sp() {
      return this;
   }

   public long valueAt(final int i) {
      return this.valueAt$mcJ$sp(i);
   }

   public long valueAt$mcJ$sp(final int i) {
      return this.data()[i];
   }

   public long valueAt(final int row, final int col) {
      return this.valueAt$mcJ$sp(row, col);
   }

   public long valueAt$mcJ$sp(final int row, final int col) {
      return this.apply$mcJ$sp(row, col);
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix$mcJ$sp(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcJ$sp(final ClassTag cm, final Zero zero) {
      DenseMatrix result = DenseMatrix$.MODULE$.create$mJc$sp(this.rows(), this.cols(), (long[])cm.newArray(this.size()), zero);
      result.$colon$eq(this, HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM());
      return result;
   }

   public DenseMatrix copy() {
      return this.copy$mcJ$sp();
   }

   public DenseMatrix copy$mcJ$sp() {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      DenseMatrix result = DenseMatrix$.MODULE$.create$mJc$sp(this.rows(), this.cols(), (long[])man.newArray(this.size()), this.breeze$linalg$DenseMatrix$$dontNeedZero());
      result.$colon$eq(this, HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM());
      return result;
   }

   public DenseMatrix delete(final int row, final Axis._0$ axis) {
      return this.delete$mcJ$sp(row, axis);
   }

   public DenseMatrix delete$mcJ$sp(final int row, final Axis._0$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      boolean cond$macro$1 = row >= 0 && row < this.rows();
      if (!cond$macro$1) {
         throw new IllegalArgumentException((new StringBuilder(64)).append("requirement failed: ").append((new StringBuilder(28)).append("row ").append(row).append(" is not in bounds: [0, ").append(this.rows()).append(")").toString()).append(": ").append("row.>=(0).&&(row.<(DenseMatrix.this.rows))").toString());
      } else {
         return row == 0 ? ((DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), this.rows()), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows())).copy$mcJ$sp() : (row == this.rows() - 1 ? ((DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.rows() - 1), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows())).copy$mcJ$sp() : DenseMatrix$.MODULE$.vertcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), row), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()), (DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(row + 1), this.rows()), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows())}), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM(), man, this.breeze$linalg$DenseMatrix$$dontNeedZero()));
      }
   }

   public DenseMatrix delete(final int col, final Axis._1$ axis) {
      return this.delete$mcJ$sp(col, axis);
   }

   public DenseMatrix delete$mcJ$sp(final int col, final Axis._1$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      boolean cond$macro$1 = col >= 0 && col < this.cols();
      if (!cond$macro$1) {
         throw new IllegalArgumentException((new StringBuilder(64)).append("requirement failed: ").append((new StringBuilder(28)).append("col ").append(col).append(" is not in bounds: [0, ").append(this.cols()).append(")").toString()).append(": ").append("col.>=(0).&&(col.<(DenseMatrix.this.cols))").toString());
      } else {
         return col == 0 ? ((DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), this.cols()), HasOps$.MODULE$.canSliceCols())).copy$mcJ$sp() : (col == this.cols() - 1 ? ((DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.cols() - 1), HasOps$.MODULE$.canSliceCols())).copy$mcJ$sp() : DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), col), HasOps$.MODULE$.canSliceCols()), (DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(col + 1), this.cols()), HasOps$.MODULE$.canSliceCols())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM(), man, this.breeze$linalg$DenseMatrix$$dontNeedZero()));
      }
   }

   public DenseMatrix delete(final Seq rows, final Axis._0$ axis) {
      return this.delete$mcJ$sp(rows, axis);
   }

   public DenseMatrix delete$mcJ$sp(final Seq rows, final Axis._0$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      DenseMatrix var10000;
      if (rows.isEmpty()) {
         var10000 = this.copy$mcJ$sp();
      } else if (rows.size() == 1) {
         var10000 = this.delete$mcJ$sp(BoxesRunTime.unboxToInt(rows.apply(0)), axis);
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
      return this.delete$mcJ$sp(cols, axis);
   }

   public DenseMatrix delete$mcJ$sp(final Seq cols, final Axis._1$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      DenseMatrix var10000;
      if (cols.isEmpty()) {
         var10000 = this.copy$mcJ$sp();
      } else if (cols.size() == 1) {
         var10000 = this.delete$mcJ$sp(BoxesRunTime.unboxToInt(cols.apply(0)), axis);
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
      return this.overlaps$mcJ$sp(other);
   }

   public boolean overlaps$mcJ$sp(final DenseMatrix other) {
      boolean var10000;
      if (this.data() == other.data$mcJ$sp()) {
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

   public DenseMatrix$mcJ$sp(final int rows, final int cols, final long[] data$mcJ$sp, final int offset, final int majorStride, final boolean isTranspose) {
      super(rows, cols, (Object)null, offset, majorStride, isTranspose);
      this.data$mcJ$sp = data$mcJ$sp;
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

   public DenseMatrix$mcJ$sp(final int rows, final int cols, final ClassTag man) {
      this(rows, cols, (long[])man.newArray(rows * cols), 0, rows, DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public DenseMatrix$mcJ$sp(final int rows, final int cols, final long[] data$mcJ$sp, final int offset) {
      this(rows, cols, data$mcJ$sp, offset, rows, DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public DenseMatrix$mcJ$sp(final int rows, final int cols, final long[] data$mcJ$sp) {
      this(rows, cols, data$mcJ$sp, 0, rows, DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public DenseMatrix$mcJ$sp(final int rows, final long[] data$mcJ$sp, final int offset) {
      int left$macro$1 = data$mcJ$sp.length % rows;
      int right$macro$2 = 0;
      if (left$macro$1 != 0) {
         throw new AssertionError((new StringBuilder(60)).append("assertion failed: ").append("data.length.%(rows) == 0 (").append(left$macro$1).append(" ").append("!=").append(" ").append(0).append(")").toString());
      } else {
         this(rows, data$mcJ$sp.length / rows, data$mcJ$sp, offset);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
