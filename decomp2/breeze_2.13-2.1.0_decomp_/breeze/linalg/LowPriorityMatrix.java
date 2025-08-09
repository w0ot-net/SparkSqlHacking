package breeze.linalg;

import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanSlice2;
import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.immutable.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005UdaB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006-\u0001!\ta\u0006\u0005\u00067\u0001!\u0019\u0001\b\u0005\u00067\u0002!\u0019\u0001\u0018\u0005\u0006S\u0002!\u0019A\u001b\u0005\u0006y\u0002!\u0019! \u0005\b\u00037\u0001A1AA\u000f\u0011\u001d\t9\u0004\u0001C\u0002\u0003sAq!!\u0017\u0001\t\u0007\tYFA\tM_^\u0004&/[8sSRLX*\u0019;sSbT!a\u0003\u0007\u0002\r1Lg.\u00197h\u0015\u0005i\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0005\u0001\u0001\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u00021A\u0011\u0011#G\u0005\u00035I\u0011A!\u00168ji\u0006I2-\u00198TY&\u001cW\rV3og>\u0014(i\\8mK\u0006t'k\\<t+\ti\"\u0006F\u0002\u001f\u0017N\u0003ba\b\u0012%gqBU\"\u0001\u0011\u000b\u0005\u0005R\u0011aB:vaB|'\u000f^\u0005\u0003G\u0001\u0012\u0011bQ1o'2L7-\u001a\u001a\u0011\u0007\u00152\u0003&D\u0001\u000b\u0013\t9#B\u0001\u0004NCR\u0014\u0018\u000e\u001f\t\u0003S)b\u0001\u0001B\u0003,\u0005\t\u0007AFA\u0001W#\ti\u0003\u0007\u0005\u0002\u0012]%\u0011qF\u0005\u0002\b\u001d>$\b.\u001b8h!\t\t\u0012'\u0003\u00023%\t\u0019\u0011I\\=\u0011\t\u0015\"d'O\u0005\u0003k)\u0011a\u0001V3og>\u0014\bCA\t8\u0013\tA$CA\u0002J]R\u0004\"!\u0005\u001e\n\u0005m\u0012\"a\u0002\"p_2,\u0017M\u001c\b\u0003{\u0015s!AP\"\u000f\u0005}\u0012U\"\u0001!\u000b\u0005\u0005s\u0011A\u0002\u001fs_>$h(C\u0001\u0014\u0013\t!%#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0019;\u0015\u0001\u0004\u0013d_2|g\u000eJ2pY>t'B\u0001#\u0013!\u0015)\u0013J\u000e\u001c)\u0013\tQ%BA\u0006TY&\u001cW-T1ue&D\bb\u0002'\u0003\u0003\u0003\u0005\u001d!T\u0001\fKZLG-\u001a8dK\u0012\u0012\u0014\u0007E\u0002O#\"j\u0011a\u0014\u0006\u0003!2\tA!\\1uQ&\u0011!k\u0014\u0002\t'\u0016l\u0017N]5oO\"9AKAA\u0001\u0002\b)\u0016aC3wS\u0012,gnY3%eI\u00022AV-)\u001b\u00059&B\u0001-\u0013\u0003\u001d\u0011XM\u001a7fGRL!AW,\u0003\u0011\rc\u0017m]:UC\u001e\f\u0011dY1o'2L7-\u001a+f]N|'OQ8pY\u0016\fgnQ8mgV\u0011Q,\u0019\u000b\u0004=\u000e4\u0007CB\u0010#?r\u001a$\rE\u0002&M\u0001\u0004\"!K1\u0005\u000b-\u001a!\u0019\u0001\u0017\u0011\u000b\u0015JeG\u000e1\t\u000f\u0011\u001c\u0011\u0011!a\u0002K\u0006YQM^5eK:\u001cW\r\n\u001a4!\rq\u0015\u000b\u0019\u0005\bO\u000e\t\t\u0011q\u0001i\u0003-)g/\u001b3f]\u000e,GE\r\u001b\u0011\u0007YK\u0006-A\u0010dC:\u001cF.[2f)\u0016t7o\u001c:C_>dW-\u00198S_^\u001c\u0018I\u001c3D_2,\"a[8\u0015\u000714\u0018\u0010\u0005\u0004 E5\u001cd\u0007\u001d\t\u0004K\u0019r\u0007CA\u0015p\t\u0015YCA1\u0001-!\u0011)\u0013o\u001d8\n\u0005IT!aC*mS\u000e,g+Z2u_J\u0004B!\u0005;7m%\u0011QO\u0005\u0002\u0007)V\u0004H.\u001a\u001a\t\u000f]$\u0011\u0011!a\u0002q\u0006YQM^5eK:\u001cW\r\n\u001a6!\rq\u0015K\u001c\u0005\bu\u0012\t\t\u0011q\u0001|\u0003-)g/\u001b3f]\u000e,GE\r\u001c\u0011\u0007YKf.A\u0010dC:\u001cF.[2f%><\u0018I\u001c3UK:\u001cxN\u001d\"p_2,\u0017M\\\"pYN,2A`A\u0003)\u0015y\u0018qBA\u000b!!y\"%!\u00017g\u0005\u001d\u0001\u0003B\u0013'\u0003\u0007\u00012!KA\u0003\t\u0015YSA1\u0001-!\u0015)\u0013\u0011BA\u0007\u0013\r\tYA\u0003\u0002\n)J\fgn\u001d9pg\u0016\u0004R!J9t\u0003\u0007A\u0011\"!\u0005\u0006\u0003\u0003\u0005\u001d!a\u0005\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#g\u000e\t\u0005\u001dF\u000b\u0019\u0001C\u0005\u0002\u0018\u0015\t\t\u0011q\u0001\u0002\u001a\u0005YQM^5eK:\u001cW\r\n\u001a9!\u00111\u0016,a\u0001\u0002A\r\fgn\u00157jG\u0016$VM\\:pe\n{w\u000e\\3b]J{wo]!oI\u000e{Gn]\u000b\u0005\u0003?\t9\u0003\u0006\u0004\u0002\"\u0005-\u0012\u0011\u0007\t\t?\t\n\u0019cM\u001a\u0002*A!QEJA\u0013!\rI\u0013q\u0005\u0003\u0006W\u0019\u0011\r\u0001\f\t\u0007K%3d'!\n\t\u0013\u00055b!!AA\u0004\u0005=\u0012aC3wS\u0012,gnY3%ee\u0002BAT)\u0002&!I\u00111\u0007\u0004\u0002\u0002\u0003\u000f\u0011QG\u0001\fKZLG-\u001a8dK\u0012\u001a\u0004\u0007\u0005\u0003W3\u0006\u0015\u0012!J2b]Nc\u0017nY3UK:\u001cxN\u001d\"p_2,\u0017M\u001c*poN\fe\u000eZ,fSJ$7i\u001c7t+\u0011\tY$a\u0011\u0015\r\u0005u\u0012QJA*!%y\"%a\u00104\u0003\u000b\nY\u0005\u0005\u0003&M\u0005\u0005\u0003cA\u0015\u0002D\u0011)1f\u0002b\u0001YA!Q(a\u00127\u0013\r\tIe\u0012\u0002\u0004'\u0016\f\bCB\u0013JmY\n\t\u0005C\u0005\u0002P\u001d\t\t\u0011q\u0001\u0002R\u0005YQM^5eK:\u001cW\rJ\u001a2!\u0011q\u0015+!\u0011\t\u0013\u0005Us!!AA\u0004\u0005]\u0013aC3wS\u0012,gnY3%gI\u0002BAV-\u0002B\u0005)3-\u00198TY&\u001cWmV3je\u0012\u0014vn^:B]\u0012$VM\\:pe\n{w\u000e\\3b]\u000e{Gn]\u000b\u0005\u0003;\n)\u0007\u0006\u0004\u0002`\u0005%\u0014q\u000e\t\n?\t\n\t'!\u00124\u0003O\u0002B!\n\u0014\u0002dA\u0019\u0011&!\u001a\u0005\u000b-B!\u0019\u0001\u0017\u0011\r\u0015JeGNA2\u0011%\tY\u0007CA\u0001\u0002\b\ti'A\u0006fm&$WM\\2fIM\u001a\u0004\u0003\u0002(R\u0003GB\u0011\"!\u001d\t\u0003\u0003\u0005\u001d!a\u001d\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$3\u0007\u000e\t\u0005-f\u000b\u0019\u0007"
)
public interface LowPriorityMatrix {
   // $FF: synthetic method
   static CanSlice2 canSliceTensorBooleanRows$(final LowPriorityMatrix $this, final Semiring evidence$21, final ClassTag evidence$22) {
      return $this.canSliceTensorBooleanRows(evidence$21, evidence$22);
   }

   default CanSlice2 canSliceTensorBooleanRows(final Semiring evidence$21, final ClassTag evidence$22) {
      return new CanSlice2(evidence$21, evidence$22) {
         private final Semiring evidence$21$1;
         private final ClassTag evidence$22$1;

         public SliceMatrix apply(final Matrix from, final Tensor rows, final .colon.colon cols) {
            Range cols = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.cols());
            return new SliceMatrix(from, SliceUtils$.MODULE$.mapRowSeq(rows.findAll((x$2) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(BoxesRunTime.unboxToBoolean(x$2)))), from.rows()), cols, this.evidence$21$1, this.evidence$22$1);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$1(final boolean x$2) {
            return x$2;
         }

         public {
            this.evidence$21$1 = evidence$21$1;
            this.evidence$22$1 = evidence$22$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceTensorBooleanCols$(final LowPriorityMatrix $this, final Semiring evidence$23, final ClassTag evidence$24) {
      return $this.canSliceTensorBooleanCols(evidence$23, evidence$24);
   }

   default CanSlice2 canSliceTensorBooleanCols(final Semiring evidence$23, final ClassTag evidence$24) {
      return new CanSlice2(evidence$23, evidence$24) {
         private final Semiring evidence$23$1;
         private final ClassTag evidence$24$1;

         public SliceMatrix apply(final Matrix from, final .colon.colon rowsx, final Tensor cols) {
            Range rows = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.rows());
            return new SliceMatrix(from, rows, SliceUtils$.MODULE$.mapColumnSeq(cols.findAll((x$3) -> BoxesRunTime.boxToBoolean($anonfun$apply$2(BoxesRunTime.unboxToBoolean(x$3)))), from.cols()), this.evidence$23$1, this.evidence$24$1);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$2(final boolean x$3) {
            return x$3;
         }

         public {
            this.evidence$23$1 = evidence$23$1;
            this.evidence$24$1 = evidence$24$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceTensorBooleanRowsAndCol$(final LowPriorityMatrix $this, final Semiring evidence$25, final ClassTag evidence$26) {
      return $this.canSliceTensorBooleanRowsAndCol(evidence$25, evidence$26);
   }

   default CanSlice2 canSliceTensorBooleanRowsAndCol(final Semiring evidence$25, final ClassTag evidence$26) {
      return new CanSlice2(evidence$26) {
         private final ClassTag evidence$26$1;

         public SliceVector apply(final Matrix from, final Tensor sliceRows, final int sliceCol) {
            IndexedSeq rows = SliceUtils$.MODULE$.mapRowSeq(sliceRows.findAll((x$4) -> BoxesRunTime.boxToBoolean($anonfun$apply$3(BoxesRunTime.unboxToBoolean(x$4)))), from.rows());
            int col = SliceUtils$.MODULE$.mapColumn(sliceCol, from.cols());
            return new SliceVector(from, (IndexedSeq)rows.map((row) -> $anonfun$apply$4(col, BoxesRunTime.unboxToInt(row))), this.evidence$26$1);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$3(final boolean x$4) {
            return x$4;
         }

         // $FF: synthetic method
         public static final Tuple2 $anonfun$apply$4(final int col$2, final int row) {
            return new Tuple2.mcII.sp(row, col$2);
         }

         public {
            this.evidence$26$1 = evidence$26$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceRowAndTensorBooleanCols$(final LowPriorityMatrix $this, final Semiring evidence$27, final ClassTag evidence$28) {
      return $this.canSliceRowAndTensorBooleanCols(evidence$27, evidence$28);
   }

   default CanSlice2 canSliceRowAndTensorBooleanCols(final Semiring evidence$27, final ClassTag evidence$28) {
      return new CanSlice2(evidence$28) {
         private final ClassTag evidence$28$1;

         public Transpose apply(final Matrix from, final int sliceRow, final Tensor sliceCols) {
            int row = SliceUtils$.MODULE$.mapRow(sliceRow, from.rows());
            IndexedSeq cols = SliceUtils$.MODULE$.mapColumnSeq(sliceCols.findAll((x$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$5(BoxesRunTime.unboxToBoolean(x$5)))), from.cols());
            return (Transpose)(new SliceVector(from, (IndexedSeq)cols.map((col) -> $anonfun$apply$6(row, BoxesRunTime.unboxToInt(col))), this.evidence$28$1)).t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()));
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$5(final boolean x$5) {
            return x$5;
         }

         // $FF: synthetic method
         public static final Tuple2 $anonfun$apply$6(final int row$2, final int col) {
            return new Tuple2.mcII.sp(row$2, col);
         }

         public {
            this.evidence$28$1 = evidence$28$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceTensorBooleanRowsAndCols$(final LowPriorityMatrix $this, final Semiring evidence$29, final ClassTag evidence$30) {
      return $this.canSliceTensorBooleanRowsAndCols(evidence$29, evidence$30);
   }

   default CanSlice2 canSliceTensorBooleanRowsAndCols(final Semiring evidence$29, final ClassTag evidence$30) {
      return new CanSlice2(evidence$29, evidence$30) {
         private final Semiring evidence$29$1;
         private final ClassTag evidence$30$1;

         public SliceMatrix apply(final Matrix from, final Tensor sliceRows, final Tensor sliceCols) {
            IndexedSeq rows = SliceUtils$.MODULE$.mapRowSeq(sliceRows.findAll((x$6) -> BoxesRunTime.boxToBoolean($anonfun$apply$7(BoxesRunTime.unboxToBoolean(x$6)))), from.rows());
            IndexedSeq cols = SliceUtils$.MODULE$.mapColumnSeq(sliceCols.findAll((x$7) -> BoxesRunTime.boxToBoolean($anonfun$apply$8(BoxesRunTime.unboxToBoolean(x$7)))), from.cols());
            return new SliceMatrix(from, rows, cols, this.evidence$29$1, this.evidence$30$1);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$7(final boolean x$6) {
            return x$6;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$8(final boolean x$7) {
            return x$7;
         }

         public {
            this.evidence$29$1 = evidence$29$1;
            this.evidence$30$1 = evidence$30$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceTensorBooleanRowsAndWeirdCols$(final LowPriorityMatrix $this, final Semiring evidence$31, final ClassTag evidence$32) {
      return $this.canSliceTensorBooleanRowsAndWeirdCols(evidence$31, evidence$32);
   }

   default CanSlice2 canSliceTensorBooleanRowsAndWeirdCols(final Semiring evidence$31, final ClassTag evidence$32) {
      return new CanSlice2(evidence$31, evidence$32) {
         private final Semiring evidence$31$1;
         private final ClassTag evidence$32$1;

         public SliceMatrix apply(final Matrix from, final Tensor sliceRows, final Seq sliceCols) {
            IndexedSeq rows = SliceUtils$.MODULE$.mapRowSeq(sliceRows.findAll((x$8) -> BoxesRunTime.boxToBoolean($anonfun$apply$9(BoxesRunTime.unboxToBoolean(x$8)))), from.rows());
            IndexedSeq cols = SliceUtils$.MODULE$.mapColumnSeq(sliceCols, from.cols());
            return new SliceMatrix(from, rows, cols, this.evidence$31$1, this.evidence$32$1);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$9(final boolean x$8) {
            return x$8;
         }

         public {
            this.evidence$31$1 = evidence$31$1;
            this.evidence$32$1 = evidence$32$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceWeirdRowsAndTensorBooleanCols$(final LowPriorityMatrix $this, final Semiring evidence$33, final ClassTag evidence$34) {
      return $this.canSliceWeirdRowsAndTensorBooleanCols(evidence$33, evidence$34);
   }

   default CanSlice2 canSliceWeirdRowsAndTensorBooleanCols(final Semiring evidence$33, final ClassTag evidence$34) {
      return new CanSlice2(evidence$33, evidence$34) {
         private final Semiring evidence$33$1;
         private final ClassTag evidence$34$1;

         public SliceMatrix apply(final Matrix from, final Seq sliceRows, final Tensor sliceCols) {
            IndexedSeq rows = SliceUtils$.MODULE$.mapRowSeq(sliceRows, from.rows());
            IndexedSeq cols = SliceUtils$.MODULE$.mapColumnSeq(sliceCols.findAll((x$9) -> BoxesRunTime.boxToBoolean($anonfun$apply$10(BoxesRunTime.unboxToBoolean(x$9)))), from.cols());
            return new SliceMatrix(from, rows, cols, this.evidence$33$1, this.evidence$34$1);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$10(final boolean x$9) {
            return x$9;
         }

         public {
            this.evidence$33$1 = evidence$33$1;
            this.evidence$34$1 = evidence$34$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final LowPriorityMatrix $this) {
   }
}
