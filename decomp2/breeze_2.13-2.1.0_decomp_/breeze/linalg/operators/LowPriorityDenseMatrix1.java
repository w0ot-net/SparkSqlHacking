package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.Axis;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.Transpose;
import breeze.linalg.support.CanCollapseAxis;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eaaB\u0003\u0007!\u0003\r\t!\u0004\u0005\u0006)\u0001!\t!\u0006\u0005\u00063\u0001!\u0019A\u0007\u0005\u0006\u0015\u0002!\u0019a\u0013\u0005\u00067\u0002!\u0019\u0001\u0018\u0002\u0018\u0019><\bK]5pe&$\u0018\u0010R3og\u0016l\u0015\r\u001e:jqFR!a\u0002\u0005\u0002\u0013=\u0004XM]1u_J\u001c(BA\u0005\u000b\u0003\u0019a\u0017N\\1mO*\t1\"\u0001\u0004ce\u0016,'0Z\u0002\u0001'\t\u0001a\u0002\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Y\u0001\"aD\f\n\u0005a\u0001\"\u0001B+oSR\f!cY1o\u0007>dG.\u00199tKJ{wo]0E\u001bV\u00191\u0004\u000b\u001f\u0015\u0005q\u0011\u0005cB\u000f!EEB4HP\u0007\u0002=)\u0011q\u0004C\u0001\bgV\u0004\bo\u001c:u\u0013\t\tcDA\bDC:\u001cu\u000e\u001c7baN,\u0017\t_5t!\r\u0019CEJ\u0007\u0002\u0011%\u0011Q\u0005\u0003\u0002\f\t\u0016t7/Z'biJL\u0007\u0010\u0005\u0002(Q1\u0001A!B\u0015\u0003\u0005\u0004Q#!\u0001,\u0012\u0005-r\u0003CA\b-\u0013\ti\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=y\u0013B\u0001\u0019\u0011\u0005\r\te.\u001f\b\u0003eUr!aI\u001a\n\u0005QB\u0011\u0001B!ySNL!AN\u001c\u0002\u0005}\u0003$B\u0001\u001b\t!\r\u0019\u0013HJ\u0005\u0003u!\u00111\u0002R3og\u00164Vm\u0019;peB\u0011q\u0005\u0010\u0003\u0006{\t\u0011\rA\u000b\u0002\u0002%B\u00191eP!\n\u0005\u0001C!!\u0003+sC:\u001c\bo\\:f!\r\u0019\u0013h\u000f\u0005\b\u0007\n\t\t\u0011q\u0001E\u0003-)g/\u001b3f]\u000e,GeN\u001a\u0011\u0007\u0015C5(D\u0001G\u0015\t9\u0005#A\u0004sK\u001adWm\u0019;\n\u0005%3%\u0001C\"mCN\u001cH+Y4\u0002%\r\fgnQ8mY\u0006\u00048/Z\"pYN|F)T\u000b\u0004\u0019B3FCA'Y!\u001di\u0002ET)U+^\u00032a\t\u0013P!\t9\u0003\u000bB\u0003*\u0007\t\u0007!F\u0004\u00023%&\u00111kN\u0001\u0003?F\u00022aI\u001dP!\t9c\u000bB\u0003>\u0007\t\u0007!\u0006E\u0002$sUCq!W\u0002\u0002\u0002\u0003\u000f!,A\u0006fm&$WM\\2fI]\"\u0004cA#I+\u00069\u0012.\u001c9m?>\u00038+\u001a;`\u0013:\u0004F.Y2f?\u0012ku,T\u000b\u0003;.,\u0012A\u0018\t\u0006?\u000eL\u00171\u0003\b\u0003A\u0006l\u0011AB\u0005\u0003E\u001a\tQa\u00149TKRL!\u0001Z3\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\u001c\u001a\n\u0005\u0019<'!B+Gk:\u001c'B\u00015\u000b\u0003\u001d9WM\\3sS\u000e\u00042a\t\u0013k!\t93\u000eB\u0005*\t\u0001\u0006\t\u0011!b\u0001U!:1.\u001c9{\u007f\u0006%\u0001CA\bo\u0013\ty\u0007CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012reR\u001chBA\bs\u0013\t\u0019\b#\u0001\u0004E_V\u0014G.Z\u0019\u0005IUL\u0018C\u0004\u0002ws6\tqO\u0003\u0002y\u0019\u00051AH]8pizJ\u0011!E\u0019\u0006Gmdh0 \b\u0003\u001fqL!! \t\u0002\u0007%sG/\r\u0003%kf\f\u0012'C\u0012\u0002\u0002\u0005\r\u0011qAA\u0003\u001d\ry\u00111A\u0005\u0004\u0003\u000b\u0001\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u0013vsF\t\u0014bIA\u0006\u0003\u001b\t\t\"a\u0004\u000f\u0007=\ti!C\u0002\u0002\u0010A\tA\u0001T8oOF\"A%^=\u0012!\u0011\u0019\u0013Q\u00036\n\u0007\u0005]\u0001B\u0001\u0004NCR\u0014\u0018\u000e\u001f"
)
public interface LowPriorityDenseMatrix1 {
   // $FF: synthetic method
   static CanCollapseAxis canCollapseRows_DM$(final LowPriorityDenseMatrix1 $this, final ClassTag evidence$73) {
      return $this.canCollapseRows_DM(evidence$73);
   }

   default CanCollapseAxis canCollapseRows_DM(final ClassTag evidence$73) {
      return new CanCollapseAxis(evidence$73) {
         private final ClassTag evidence$73$1;

         public Transpose apply(final DenseMatrix from, final Axis._0$ axis, final Function1 f) {
            DenseVector result = new DenseVector(from.cols(), this.evidence$73$1);
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.cols(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               result.update(index$macro$2, f.apply(from.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), HasOps$.MODULE$.canSliceCol())));
            }

            return (Transpose)result.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()));
         }

         public {
            this.evidence$73$1 = evidence$73$1;
         }
      };
   }

   // $FF: synthetic method
   static CanCollapseAxis canCollapseCols_DM$(final LowPriorityDenseMatrix1 $this, final ClassTag evidence$74) {
      return $this.canCollapseCols_DM(evidence$74);
   }

   default CanCollapseAxis canCollapseCols_DM(final ClassTag evidence$74) {
      return new CanCollapseAxis(evidence$74) {
         private final ClassTag evidence$74$1;

         public DenseVector apply(final DenseMatrix from, final Axis._1$ axis, final Function1 f) {
            DenseVector result = new DenseVector(from.rows(), this.evidence$74$1);
            DenseMatrix t = (DenseMatrix)from.t(HasOps$.MODULE$.canTranspose_DM());
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               result.update(index$macro$2, f.apply(t.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), HasOps$.MODULE$.canSliceCol())));
            }

            return result;
         }

         public {
            this.evidence$74$1 = evidence$74$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$(final LowPriorityDenseMatrix1 $this) {
      return $this.impl_OpSet_InPlace_DM_M();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M() {
      return (a, b) -> {
         int left$macro$1 = a.rows();
         int right$macro$2 = b.rows();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrixs must have same number of rows: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            int left$macro$3 = a.cols();
            int right$macro$4 = b.cols();
            if (left$macro$3 != right$macro$4) {
               throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Matrixs must have same number of columns: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
            } else {
               Object ad = a.data();
               int index$macro$11 = 0;

               for(int limit$macro$13 = a.cols(); index$macro$11 < limit$macro$13; ++index$macro$11) {
                  int index$macro$6 = 0;

                  for(int limit$macro$8 = a.rows(); index$macro$6 < limit$macro$8; ++index$macro$6) {
                     scala.runtime.ScalaRunTime..MODULE$.array_update(ad, a.linearIndex(index$macro$6, index$macro$11), b.apply(index$macro$6, index$macro$11));
                  }
               }

            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mDc$sp$(final LowPriorityDenseMatrix1 $this) {
      return $this.impl_OpSet_InPlace_DM_M$mDc$sp();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mDc$sp() {
      return (a, b) -> {
         int left$macro$1 = a.rows();
         int right$macro$2 = b.rows();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrixs must have same number of rows: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            int left$macro$3 = a.cols();
            int right$macro$4 = b.cols();
            if (left$macro$3 != right$macro$4) {
               throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Matrixs must have same number of columns: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               int index$macro$11 = 0;

               for(int limit$macro$13 = a.cols(); index$macro$11 < limit$macro$13; ++index$macro$11) {
                  int index$macro$6 = 0;

                  for(int limit$macro$8 = a.rows(); index$macro$6 < limit$macro$8; ++index$macro$6) {
                     ad[a.linearIndex(index$macro$6, index$macro$11)] = b.apply$mcD$sp(index$macro$6, index$macro$11);
                  }
               }

            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mFc$sp$(final LowPriorityDenseMatrix1 $this) {
      return $this.impl_OpSet_InPlace_DM_M$mFc$sp();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mFc$sp() {
      return (a, b) -> {
         int left$macro$1 = a.rows();
         int right$macro$2 = b.rows();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrixs must have same number of rows: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            int left$macro$3 = a.cols();
            int right$macro$4 = b.cols();
            if (left$macro$3 != right$macro$4) {
               throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Matrixs must have same number of columns: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               int index$macro$11 = 0;

               for(int limit$macro$13 = a.cols(); index$macro$11 < limit$macro$13; ++index$macro$11) {
                  int index$macro$6 = 0;

                  for(int limit$macro$8 = a.rows(); index$macro$6 < limit$macro$8; ++index$macro$6) {
                     ad[a.linearIndex(index$macro$6, index$macro$11)] = b.apply$mcF$sp(index$macro$6, index$macro$11);
                  }
               }

            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mIc$sp$(final LowPriorityDenseMatrix1 $this) {
      return $this.impl_OpSet_InPlace_DM_M$mIc$sp();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mIc$sp() {
      return (a, b) -> {
         int left$macro$1 = a.rows();
         int right$macro$2 = b.rows();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrixs must have same number of rows: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            int left$macro$3 = a.cols();
            int right$macro$4 = b.cols();
            if (left$macro$3 != right$macro$4) {
               throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Matrixs must have same number of columns: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int index$macro$11 = 0;

               for(int limit$macro$13 = a.cols(); index$macro$11 < limit$macro$13; ++index$macro$11) {
                  int index$macro$6 = 0;

                  for(int limit$macro$8 = a.rows(); index$macro$6 < limit$macro$8; ++index$macro$6) {
                     ad[a.linearIndex(index$macro$6, index$macro$11)] = b.apply$mcI$sp(index$macro$6, index$macro$11);
                  }
               }

            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mJc$sp$(final LowPriorityDenseMatrix1 $this) {
      return $this.impl_OpSet_InPlace_DM_M$mJc$sp();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_DM_M$mJc$sp() {
      return (a, b) -> {
         int left$macro$1 = a.rows();
         int right$macro$2 = b.rows();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrixs must have same number of rows: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            int left$macro$3 = a.cols();
            int right$macro$4 = b.cols();
            if (left$macro$3 != right$macro$4) {
               throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Matrixs must have same number of columns: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               int index$macro$11 = 0;

               for(int limit$macro$13 = a.cols(); index$macro$11 < limit$macro$13; ++index$macro$11) {
                  int index$macro$6 = 0;

                  for(int limit$macro$8 = a.rows(); index$macro$6 < limit$macro$8; ++index$macro$6) {
                     ad[a.linearIndex(index$macro$6, index$macro$11)] = b.apply$mcJ$sp(index$macro$6, index$macro$11);
                  }
               }

            }
         }
      };
   }

   static void $init$(final LowPriorityDenseMatrix1 $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
