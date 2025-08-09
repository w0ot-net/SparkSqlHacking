package breeze.linalg.operators;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.Transpose;
import breeze.linalg.package$;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.RangeExtender$;
import scala.collection.immutable.;
import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015aaB\u0005\u000b!\u0003\r\t!\u0005\u0005\u0006?\u0001!\t\u0001\t\u0005\u0006I\u0001!\u0019!\n\u0005\u0006\u001d\u0002!\u0019a\u0014\u0005\u00063\u0002!\u0019A\u0017\u0005\u0006G\u0002!\u0019\u0001\u001a\u0005\u0006U\u0002!\u0019a\u001b\u0005\u0006c\u0002!\u0019A\u001d\u0005\u0006s\u0002!\u0019A\u001f\u0002\u0017\t\u0016t7/Z'biJL\u0007pX*mS\u000eLgnZ(qg*\u00111\u0002D\u0001\n_B,'/\u0019;peNT!!\u0004\b\u0002\r1Lg.\u00197h\u0015\u0005y\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\t\u0001\u0011\u0002\u0004\b\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005eQR\"\u0001\u0006\n\u0005mQ!A\b#f]N,W*\u0019;sSb|6\u000b\\5dS:<w\n]:`\u0019><\bK]5p!\tIR$\u0003\u0002\u001f\u0015\tAB)\u001a8tK6\u000bGO]5y?R\u0013\u0018M^3sg\u0006dw\n]:\u0002\r\u0011Jg.\u001b;%)\u0005\t\u0003CA\n#\u0013\t\u0019CC\u0001\u0003V]&$\u0018aC2b]Nc\u0017nY3D_2,\"AJ\u001a\u0016\u0003\u001d\u0002b\u0001K\u0016.y![U\"A\u0015\u000b\u0005)b\u0011aB:vaB|'\u000f^\u0005\u0003Y%\u0012\u0011bQ1o'2L7-\u001a\u001a\u0011\u00079z\u0013'D\u0001\r\u0013\t\u0001DBA\u0006EK:\u001cX-T1ue&D\bC\u0001\u001a4\u0019\u0001!Q\u0001\u000e\u0002C\u0002U\u0012\u0011AV\t\u0003me\u0002\"aE\u001c\n\u0005a\"\"a\u0002(pi\"Lgn\u001a\t\u0003'iJ!a\u000f\u000b\u0003\u0007\u0005s\u0017P\u0004\u0002>\u000b:\u0011ah\u0011\b\u0003\u007f\tk\u0011\u0001\u0011\u0006\u0003\u0003B\ta\u0001\u0010:p_Rt\u0014\"A\u000b\n\u0005\u0011#\u0012a\u00029bG.\fw-Z\u0005\u0003\r\u001e\u000bA\u0002J2pY>tGeY8m_:T!\u0001\u0012\u000b\u0011\u0005MI\u0015B\u0001&\u0015\u0005\rIe\u000e\u001e\t\u0004]1\u000b\u0014BA'\r\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0002\u0017\r\fgn\u00157jG\u0016\u0014vn^\u000b\u0003!R+\u0012!\u0015\t\u0007Q-\u0012\u0006\nP+\u0011\u00079z3\u000b\u0005\u00023)\u0012)Ag\u0001b\u0001kA\u0019aF\u0016-\n\u0005]c!!\u0003+sC:\u001c\bo\\:f!\rqCjU\u0001\rG\u0006t7\u000b\\5dKJ{wo]\u000b\u00037~+\u0012\u0001\u0018\t\u0007Q-j\u0006\rP/\u0011\u00079zc\f\u0005\u00023?\u0012)A\u0007\u0002b\u0001kA\u0011Q(Y\u0005\u0003E\u001e\u0013QAU1oO\u0016\fAbY1o'2L7-Z\"pYN,\"!Z5\u0016\u0003\u0019\u0004b\u0001K\u0016hy\u0001<\u0007c\u0001\u00180QB\u0011!'\u001b\u0003\u0006i\u0015\u0011\r!N\u0001\u0014G\u0006t7\u000b\\5dK\u000e{Gn]!oIJ{wo]\u000b\u0003YB,\u0012!\u001c\t\u0007Q-r\u0007\r\u00198\u0011\u00079zs\u000e\u0005\u00023a\u0012)AG\u0002b\u0001k\u0005\t2-\u00198TY&\u001cW\rU1si>37i\u001c7\u0016\u0005M<X#\u0001;\u0011\r!ZS\u000f\u0019%y!\rqsF\u001e\t\u0003e]$Q\u0001N\u0004C\u0002U\u00022A\f'w\u0003E\u0019\u0017M\\*mS\u000e,\u0007+\u0019:u\u001f\u001a\u0014vn^\u000b\u0003w~,\u0012\u0001 \t\bQ-j\b\nYA\u0001!\rqsF \t\u0003e}$Q\u0001\u000e\u0005C\u0002U\u0002BA\f,\u0002\u0004A\u0019a\u0006\u0014@"
)
public interface DenseMatrix_SlicingOps extends DenseMatrix_SlicingOps_LowPrio, DenseMatrix_TraversalOps {
   // $FF: synthetic method
   static CanSlice2 canSliceCol$(final DenseMatrix_SlicingOps $this) {
      return $this.canSliceCol();
   }

   default CanSlice2 canSliceCol() {
      return new CanSlice2() {
         public DenseVector apply(final DenseMatrix m, final .colon.colon ignored, final int colWNegative) {
            if (colWNegative >= -m.cols() && colWNegative < m.cols()) {
               int col = colWNegative < 0 ? colWNegative + m.cols() : colWNegative;
               DenseVector var10000;
               if (!m.isTranspose()) {
                  Object x$1 = m.data();
                  int x$2 = m.rows();
                  int x$3 = col * m.majorStride() + m.offset();
                  int x$4 = 1;
                  var10000 = DenseVector$.MODULE$.create(x$1, x$3, 1, x$2);
               } else {
                  Object x$5 = m.data();
                  int x$6 = m.rows();
                  int x$7 = m.offset() + col;
                  int x$8 = m.majorStride();
                  var10000 = DenseVector$.MODULE$.create(x$5, x$7, x$8, x$6);
               }

               return var10000;
            } else {
               throw new ArrayIndexOutOfBoundsException("Column must be in bounds for slice!");
            }
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceRow$(final DenseMatrix_SlicingOps $this) {
      return $this.canSliceRow();
   }

   default CanSlice2 canSliceRow() {
      return new CanSlice2() {
         // $FF: synthetic field
         private final DenseMatrix_SlicingOps $outer;

         public Transpose apply(final DenseMatrix m, final int rowWNegative, final .colon.colon ignored) {
            return (Transpose)((ImmutableNumericOps)this.$outer.canSliceCol().apply(m.t(HasOps$.MODULE$.canTranspose_DM()), scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(rowWNegative))).t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()));
         }

         public {
            if (DenseMatrix_SlicingOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrix_SlicingOps.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceRows$(final DenseMatrix_SlicingOps $this) {
      return $this.canSliceRows();
   }

   default CanSlice2 canSliceRows() {
      return new CanSlice2() {
         // $FF: synthetic field
         private final DenseMatrix_SlicingOps $outer;

         public DenseMatrix apply(final DenseMatrix m, final Range rowsWNegative, final .colon.colon ignored) {
            Range rows = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(package$.MODULE$.RangeToRangeExtender(rowsWNegative), m.rows());
            DenseMatrix var10000;
            if (rows.isEmpty()) {
               var10000 = DenseMatrix$.MODULE$.create(0, m.cols(), m.data(), 0, 0, DenseMatrix$.MODULE$.create$default$6());
            } else if (!m.isTranspose()) {
               int left$macro$1 = rows.step();
               int right$macro$2 = 1;
               if (left$macro$1 != 1) {
                  throw new IllegalArgumentException((new StringBuilder(117)).append("requirement failed: Sorry, we can't support row ranges with step sizes other than 1: ").append("rows.step == 1 (").append(left$macro$1).append(" ").append("!=").append(" ").append(1).append(")").toString());
               }

               int first = rows.head();
               boolean cond$macro$3 = rows.last() < m.rows();
               if (!cond$macro$3) {
                  throw new IllegalArgumentException("requirement failed: rows.last.<(m.rows)");
               }

               if (rows.last() >= m.rows()) {
                  throw new IndexOutOfBoundsException((new StringBuilder(45)).append("Row slice of ").append(rows).append(" was bigger than matrix rows of ").append(m.rows()).toString());
               }

               var10000 = DenseMatrix$.MODULE$.create(rows.length(), m.cols(), m.data(), m.offset() + first, m.majorStride(), DenseMatrix$.MODULE$.create$default$6());
            } else {
               var10000 = (DenseMatrix)((ImmutableNumericOps)this.$outer.canSliceCols().apply(m.t(HasOps$.MODULE$.canTranspose_DM()), scala.package..MODULE$.$colon$colon(), rows)).t(HasOps$.MODULE$.canTranspose_DM());
            }

            return var10000;
         }

         public {
            if (DenseMatrix_SlicingOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrix_SlicingOps.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceCols$(final DenseMatrix_SlicingOps $this) {
      return $this.canSliceCols();
   }

   default CanSlice2 canSliceCols() {
      return new CanSlice2() {
         // $FF: synthetic field
         private final DenseMatrix_SlicingOps $outer;

         public DenseMatrix apply(final DenseMatrix m, final .colon.colon ignored, final Range colsWNegative) {
            Range cols = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(package$.MODULE$.RangeToRangeExtender(colsWNegative), m.cols());
            DenseMatrix var10000;
            if (cols.isEmpty()) {
               var10000 = DenseMatrix$.MODULE$.create(m.rows(), 0, m.data(), 0, m.rows(), DenseMatrix$.MODULE$.create$default$6());
            } else if (!m.isTranspose()) {
               int first = cols.head();
               if (cols.last() >= m.cols()) {
                  throw new IndexOutOfBoundsException((new StringBuilder(45)).append("Col slice of ").append(cols).append(" was bigger than matrix cols of ").append(m.cols()).toString());
               }

               var10000 = DenseMatrix$.MODULE$.create(m.rows(), cols.length(), m.data(), m.offset() + first * m.majorStride(), m.majorStride() * cols.step(), DenseMatrix$.MODULE$.create$default$6());
            } else {
               var10000 = (DenseMatrix)((ImmutableNumericOps)this.$outer.canSliceRows().apply(m.t(HasOps$.MODULE$.canTranspose_DM()), cols, scala.package..MODULE$.$colon$colon())).t(HasOps$.MODULE$.canTranspose_DM());
            }

            return var10000;
         }

         public {
            if (DenseMatrix_SlicingOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrix_SlicingOps.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceColsAndRows$(final DenseMatrix_SlicingOps $this) {
      return $this.canSliceColsAndRows();
   }

   default CanSlice2 canSliceColsAndRows() {
      return new CanSlice2() {
         // $FF: synthetic field
         private final DenseMatrix_SlicingOps $outer;

         public DenseMatrix apply(final DenseMatrix m, final Range rowsWNegative, final Range colsWNegative) {
            Range rows = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(package$.MODULE$.RangeToRangeExtender(rowsWNegative), m.rows());
            Range cols = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(package$.MODULE$.RangeToRangeExtender(colsWNegative), m.cols());
            DenseMatrix var10000;
            if (!rows.isEmpty() && !cols.isEmpty()) {
               if (!m.isTranspose()) {
                  int left$macro$1 = rows.step();
                  int right$macro$2 = 1;
                  if (left$macro$1 != 1) {
                     throw new IllegalArgumentException((new StringBuilder(145)).append("requirement failed: Sorry, we can't support row ranges with step sizes other than 1 for non transposed matrices: ").append("rows.step == 1 (").append(left$macro$1).append(" ").append("!=").append(" ").append(1).append(")").toString());
                  }

                  int first = cols.head();
                  if (rows.last() >= m.rows()) {
                     throw new IndexOutOfBoundsException((new StringBuilder(45)).append("Row slice of ").append(rows).append(" was bigger than matrix rows of ").append(m.rows()).toString());
                  }

                  if (cols.last() >= m.cols()) {
                     throw new IndexOutOfBoundsException((new StringBuilder(45)).append("Col slice of ").append(cols).append(" was bigger than matrix cols of ").append(m.cols()).toString());
                  }

                  var10000 = DenseMatrix$.MODULE$.create(rows.length(), cols.length(), m.data(), m.offset() + first * m.majorStride() + rows.head(), m.majorStride() * cols.step(), DenseMatrix$.MODULE$.create$default$6());
               } else {
                  int left$macro$3 = cols.step();
                  int right$macro$4 = 1;
                  if (left$macro$3 != 1) {
                     throw new IllegalArgumentException((new StringBuilder(141)).append("requirement failed: Sorry, we can't support col ranges with step sizes other than 1 for transposed matrices: ").append("cols.step == 1 (").append(left$macro$3).append(" ").append("!=").append(" ").append(1).append(")").toString());
                  }

                  var10000 = (DenseMatrix)((ImmutableNumericOps)this.$outer.canSliceColsAndRows().apply(m.t(HasOps$.MODULE$.canTranspose_DM()), cols, rows)).t(HasOps$.MODULE$.canTranspose_DM());
               }
            } else {
               var10000 = DenseMatrix$.MODULE$.create(rows.size(), cols.size(), m.data(), 0, 0, DenseMatrix$.MODULE$.create$default$6());
            }

            return var10000;
         }

         public {
            if (DenseMatrix_SlicingOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrix_SlicingOps.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSlicePartOfCol$(final DenseMatrix_SlicingOps $this) {
      return $this.canSlicePartOfCol();
   }

   default CanSlice2 canSlicePartOfCol() {
      return new CanSlice2() {
         public DenseVector apply(final DenseMatrix m, final Range rowsWNegative, final int colWNegative) {
            Range rows = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(package$.MODULE$.RangeToRangeExtender(rowsWNegative), m.rows());
            if (colWNegative >= -m.cols() && colWNegative < m.cols()) {
               int col = colWNegative < 0 ? colWNegative + m.cols() : colWNegative;
               DenseVector var10000;
               if (rows.isEmpty()) {
                  var10000 = DenseVector$.MODULE$.create(m.data(), 0, 0, 0);
               } else if (!m.isTranspose()) {
                  if (rows.last() >= m.rows()) {
                     throw new IndexOutOfBoundsException((new StringBuilder(45)).append("Row slice of ").append(rows).append(" was bigger than matrix rows of ").append(m.rows()).toString());
                  }

                  var10000 = DenseVector$.MODULE$.create(m.data(), col * m.majorStride() + m.offset() + rows.head(), rows.step(), rows.length());
               } else {
                  var10000 = DenseVector$.MODULE$.create(m.data(), m.offset() + col + rows.head() * m.majorStride(), m.majorStride() * rows.step(), rows.length());
               }

               return var10000;
            } else {
               throw new ArrayIndexOutOfBoundsException("Row must be in bounds for slice!");
            }
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSlicePartOfRow$(final DenseMatrix_SlicingOps $this) {
      return $this.canSlicePartOfRow();
   }

   default CanSlice2 canSlicePartOfRow() {
      return new CanSlice2() {
         // $FF: synthetic field
         private final DenseMatrix_SlicingOps $outer;

         public Transpose apply(final DenseMatrix m, final int rowWNegative, final Range colsWNegative) {
            return (Transpose)((ImmutableNumericOps)this.$outer.canSlicePartOfCol().apply(m.t(HasOps$.MODULE$.canTranspose_DM()), colsWNegative, BoxesRunTime.boxToInteger(rowWNegative))).t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()));
         }

         public {
            if (DenseMatrix_SlicingOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrix_SlicingOps.this;
            }
         }
      };
   }

   static void $init$(final DenseMatrix_SlicingOps $this) {
   }
}
