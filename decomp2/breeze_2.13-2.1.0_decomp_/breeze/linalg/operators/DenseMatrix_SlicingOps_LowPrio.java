package breeze.linalg.operators;

import breeze.linalg.DenseMatrix;
import breeze.linalg.SliceMatrix;
import breeze.linalg.Tensor;
import breeze.linalg.support.CanSlice2;
import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rba\u0002\u0004\b!\u0003\r\tA\u0004\u0005\u0006?\u0001!\t\u0001\t\u0005\u0006I\u0001!\u0019!\n\u0005\u0006C\u0002!\u0019A\u0019\u0005\u0006_\u0002!\u0019\u0001\u001d\u0005\b\u0003\u000f\u0001A1AA\u0005\u0005y!UM\\:f\u001b\u0006$(/\u001b=`'2L7-\u001b8h\u001fB\u001cx\fT8x!JLwN\u0003\u0002\t\u0013\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0003\u0015-\ta\u0001\\5oC2<'\"\u0001\u0007\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019R\u0001A\b\u00163q\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007C\u0001\f\u0018\u001b\u00059\u0011B\u0001\r\b\u0005]aun\u001e)sS>\u0014\u0018\u000e^=EK:\u001cX-T1ue&D\u0018\u0007\u0005\u0002\u00175%\u00111d\u0002\u0002\u000b\u000f\u0016tWM]5d\u001fB\u001c\bC\u0001\f\u001e\u0013\tqrAA\u0007UK:\u001cxN\u001d'poB\u0013\u0018n\\\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0005\u0002\"\u0001\u0005\u0012\n\u0005\r\n\"\u0001B+oSR\f\u0011cY1o'2L7-Z,fSJ$'k\\<t+\t13\u0007F\u0002(#f\u0003b\u0001K\u0016.y-sU\"A\u0015\u000b\u0005)J\u0011aB:vaB|'\u000f^\u0005\u0003Y%\u0012\u0011bQ1o'2L7-\u001a\u001a\u0011\u00079z\u0013'D\u0001\n\u0013\t\u0001\u0014BA\u0006EK:\u001cX-T1ue&D\bC\u0001\u001a4\u0019\u0001!Q\u0001\u000e\u0002C\u0002U\u0012\u0011AV\t\u0003me\u0002\"\u0001E\u001c\n\u0005a\n\"a\u0002(pi\"Lgn\u001a\t\u0003!iJ!aO\t\u0003\u0007\u0005s\u0017\u0010E\u0002>\u000b\"s!AP\"\u000f\u0005}\u0012U\"\u0001!\u000b\u0005\u0005k\u0011A\u0002\u001fs_>$h(C\u0001\u0013\u0013\t!\u0015#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0019;%aA*fc*\u0011A)\u0005\t\u0003!%K!AS\t\u0003\u0007%sGO\u0004\u0002>\u0019&\u0011QjR\u0001\rI\r|Gn\u001c8%G>dwN\u001c\t\u0006]=C\u0005*M\u0005\u0003!&\u00111b\u00157jG\u0016l\u0015\r\u001e:jq\"9!KAA\u0001\u0002\b\u0019\u0016AC3wS\u0012,gnY3%cA\u0019AkV\u0019\u000e\u0003US!AV\u0006\u0002\t5\fG\u000f[\u0005\u00031V\u0013\u0001bU3nSJLgn\u001a\u0005\b5\n\t\t\u0011q\u0001\\\u0003))g/\u001b3f]\u000e,GE\r\t\u00049~\u000bT\"A/\u000b\u0005y\u000b\u0012a\u0002:fM2,7\r^\u0005\u0003Av\u0013\u0001b\u00117bgN$\u0016mZ\u0001\u0012G\u0006t7\u000b\\5dK^+\u0017N\u001d3D_2\u001cXCA2h)\r!\u0017\u000e\u001c\t\u0007Q-*7\n\u00105\u0011\u00079zc\r\u0005\u00023O\u0012)Ag\u0001b\u0001kA)af\u0014%IM\"9!nAA\u0001\u0002\bY\u0017AC3wS\u0012,gnY3%gA\u0019Ak\u00164\t\u000f5\u001c\u0011\u0011!a\u0002]\u0006QQM^5eK:\u001cW\r\n\u001b\u0011\u0007q{f-A\rdC:\u001cF.[2f)\u0016t7o\u001c:C_>dW-\u00198S_^\u001cXCA9v)\u0011\u0011X0!\u0001\u0011\r!Z3O^&}!\rqs\u0006\u001e\t\u0003eU$Q\u0001\u000e\u0003C\u0002U\u0002BAL<Is&\u0011\u00010\u0003\u0002\u0007)\u0016t7o\u001c:\u0011\u0005AQ\u0018BA>\u0012\u0005\u001d\u0011un\u001c7fC:\u0004RAL(I\u0011RDqA \u0003\u0002\u0002\u0003\u000fq0\u0001\u0006fm&$WM\\2fIU\u00022\u0001V,u\u0011%\t\u0019\u0001BA\u0001\u0002\b\t)!\u0001\u0006fm&$WM\\2fIY\u00022\u0001X0u\u0003e\u0019\u0017M\\*mS\u000e,G+\u001a8t_J\u0014un\u001c7fC:\u001cu\u000e\\:\u0016\t\u0005-\u00111\u0003\u000b\u0007\u0003\u001b\t9\"!\b\u0011\u0011!Z\u0013qB&w\u0003+\u0001BAL\u0018\u0002\u0012A\u0019!'a\u0005\u0005\u000bQ*!\u0019A\u001b\u0011\r9z\u0005\nSA\t\u0011%\tI\"BA\u0001\u0002\b\tY\"\u0001\u0006fm&$WM\\2fI]\u0002B\u0001V,\u0002\u0012!I\u0011qD\u0003\u0002\u0002\u0003\u000f\u0011\u0011E\u0001\u000bKZLG-\u001a8dK\u0012B\u0004\u0003\u0002/`\u0003#\u0001"
)
public interface DenseMatrix_SlicingOps_LowPrio extends LowPriorityDenseMatrix1, TensorLowPrio {
   // $FF: synthetic method
   static CanSlice2 canSliceWeirdRows$(final DenseMatrix_SlicingOps_LowPrio $this, final Semiring evidence$1, final ClassTag evidence$2) {
      return $this.canSliceWeirdRows(evidence$1, evidence$2);
   }

   default CanSlice2 canSliceWeirdRows(final Semiring evidence$1, final ClassTag evidence$2) {
      return new CanSlice2(evidence$1, evidence$2) {
         private final Semiring evidence$1$1;
         private final ClassTag evidence$2$1;

         public SliceMatrix apply(final DenseMatrix from, final Seq slice, final .colon.colon slice2) {
            return new SliceMatrix(from, slice.toIndexedSeq(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.cols()), this.evidence$1$1, this.evidence$2$1);
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            this.evidence$2$1 = evidence$2$1;
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceWeirdCols$(final DenseMatrix_SlicingOps_LowPrio $this, final Semiring evidence$3, final ClassTag evidence$4) {
      return $this.canSliceWeirdCols(evidence$3, evidence$4);
   }

   default CanSlice2 canSliceWeirdCols(final Semiring evidence$3, final ClassTag evidence$4) {
      return new CanSlice2(evidence$3, evidence$4) {
         private final Semiring evidence$3$1;
         private final ClassTag evidence$4$1;

         public SliceMatrix apply(final DenseMatrix from, final .colon.colon slice2, final Seq slice) {
            return new SliceMatrix(from, scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.rows()), slice.toIndexedSeq(), this.evidence$3$1, this.evidence$4$1);
         }

         public {
            this.evidence$3$1 = evidence$3$1;
            this.evidence$4$1 = evidence$4$1;
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceTensorBooleanRows$(final DenseMatrix_SlicingOps_LowPrio $this, final Semiring evidence$5, final ClassTag evidence$6) {
      return $this.canSliceTensorBooleanRows(evidence$5, evidence$6);
   }

   default CanSlice2 canSliceTensorBooleanRows(final Semiring evidence$5, final ClassTag evidence$6) {
      return new CanSlice2(evidence$5, evidence$6) {
         private final Semiring evidence$5$1;
         private final ClassTag evidence$6$1;

         public SliceMatrix apply(final DenseMatrix from, final Tensor rows, final .colon.colon cols) {
            return new SliceMatrix(from, rows.findAll((x$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(BoxesRunTime.unboxToBoolean(x$1)))), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.cols()), this.evidence$5$1, this.evidence$6$1);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$1(final boolean x$1) {
            return x$1;
         }

         public {
            this.evidence$5$1 = evidence$5$1;
            this.evidence$6$1 = evidence$6$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceTensorBooleanCols$(final DenseMatrix_SlicingOps_LowPrio $this, final Semiring evidence$7, final ClassTag evidence$8) {
      return $this.canSliceTensorBooleanCols(evidence$7, evidence$8);
   }

   default CanSlice2 canSliceTensorBooleanCols(final Semiring evidence$7, final ClassTag evidence$8) {
      return new CanSlice2(evidence$7, evidence$8) {
         private final Semiring evidence$7$1;
         private final ClassTag evidence$8$1;

         public SliceMatrix apply(final DenseMatrix from, final .colon.colon rows, final Tensor cols) {
            return new SliceMatrix(from, scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.rows()), cols.findAll((x$2) -> BoxesRunTime.boxToBoolean($anonfun$apply$2(BoxesRunTime.unboxToBoolean(x$2)))), this.evidence$7$1, this.evidence$8$1);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$2(final boolean x$2) {
            return x$2;
         }

         public {
            this.evidence$7$1 = evidence$7$1;
            this.evidence$8$1 = evidence$8$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final DenseMatrix_SlicingOps_LowPrio $this) {
   }
}
