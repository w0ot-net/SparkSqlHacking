package breeze.linalg.operators;

import breeze.linalg.SliceMatrix;
import breeze.linalg.SliceVector;
import breeze.linalg.Tensor;
import breeze.linalg.Transpose;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005ea\u0002\u0005\n!\u0003\r\t\u0001\u0005\u0005\u00067\u0001!\t\u0001\b\u0005\u0006A\u0001!\u0019!\t\u0005\u0006\u001f\u0002!\u0019\u0001\u0015\u0005\u0006I\u0002!\u0019!\u001a\u0005\u0006k\u0002!\u0019A\u001e\u0005\b\u0003W\u0001A1AA\u0017\u0011\u001d\t\u0019\u0006\u0001C\u0002\u0003+\u0012Q\u0002V3og>\u0014Hj\\<Qe&|'B\u0001\u0006\f\u0003%y\u0007/\u001a:bi>\u00148O\u0003\u0002\r\u001b\u00051A.\u001b8bY\u001eT\u0011AD\u0001\u0007EJ,WM_3\u0004\u0001M\u0019\u0001!E\f\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g!\tA\u0012$D\u0001\n\u0013\tQ\u0012B\u0001\u0006HK:,'/[2PaN\fa\u0001J5oSR$C#A\u000f\u0011\u0005Iq\u0012BA\u0010\u0014\u0005\u0011)f.\u001b;\u0002/\r\fgn\u00157jG\u0016$VM\\:pe~\u001bV-]0u_~\u0013T\u0003\u0002\u00120sq\"\"a\t \u0011\r\u0011:\u0013&L\u0017<\u001b\u0005)#B\u0001\u0014\f\u0003\u001d\u0019X\u000f\u001d9peRL!\u0001K\u0013\u0003\u0013\r\u000bgn\u00157jG\u0016\u0014\u0004\u0003\u0002\u0016,[aj\u0011aC\u0005\u0003Y-\u0011a\u0001V3og>\u0014\bC\u0001\u00180\u0019\u0001!Q\u0001\r\u0002C\u0002E\u0012\u0011aS\t\u0003eU\u0002\"AE\u001a\n\u0005Q\u001a\"a\u0002(pi\"Lgn\u001a\t\u0003%YJ!aN\n\u0003\u0007\u0005s\u0017\u0010\u0005\u0002/s\u0011)!H\u0001b\u0001c\t\ta\u000b\u0005\u0002/y\u0011)QH\u0001b\u0001c\t\u0019!+Z:\t\u000b}\u0012\u00019\u0001!\u0002\u0011M,\u0017o\u00157jG\u0016\u0004R\u0001J!*\u0007nJ!AQ\u0013\u0003\u0011\r\u000bgn\u00157jG\u0016\u00042\u0001\u0012'.\u001d\t)%J\u0004\u0002G\u00136\tqI\u0003\u0002I\u001f\u00051AH]8pizJ\u0011\u0001F\u0005\u0003\u0017N\tq\u0001]1dW\u0006<W-\u0003\u0002N\u001d\n\u00191+Z9\u000b\u0005-\u001b\u0012AD2b]Nc\u0017nY3UK:\u001cxN]\u000b\u0004#V;FC\u0001*]!\u0015!\u0013i\u0015-Z!\u0011Q3\u0006\u0016,\u0011\u00059*F!\u0002\u0019\u0004\u0005\u0004\t\u0004C\u0001\u0018X\t\u0015Q4A1\u00012!\r!E\n\u0016\t\u0005Ui#f+\u0003\u0002\\\u0017\tY1\u000b\\5dKZ+7\r^8s\u0011\u001di6!!AA\u0004y\u000b!\"\u001a<jI\u0016t7-\u001a\u00132!\ry&MV\u0007\u0002A*\u0011\u0011mE\u0001\be\u00164G.Z2u\u0013\t\u0019\u0007M\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003U\u0019\u0017M\\*mS\u000e,G+\u001a8t_J\u0014un\u001c7fC:,2A\u001a6m)\t9'\u000fE\u0003%\u0003\"l\u0017\u000f\u0005\u0003+W%\\\u0007C\u0001\u0018k\t\u0015\u0001DA1\u00012!\tqC\u000eB\u0003;\t\t\u0007\u0011\u0007\u0005\u0003+W%t\u0007C\u0001\np\u0013\t\u00018CA\u0004C_>dW-\u00198\u0011\t)R\u0016n\u001b\u0005\bg\u0012\t\t\u0011q\u0001u\u0003))g/\u001b3f]\u000e,GE\r\t\u0004?\n\\\u0017aD2b]Nc\u0017nY3UK:\u001cxN\u001d\u001a\u0016\r]t\u00181AA\u0005)\u0015A\u0018QCA\u0013!%!s%_A\u0006\u0003\u001b\ty\u0001E\u0003+Wi\f9\u0001E\u0003\u0013wv\f\t!\u0003\u0002}'\t1A+\u001e9mKJ\u0002\"A\f@\u0005\u000b},!\u0019A\u0019\u0003\u0005-\u000b\u0004c\u0001\u0018\u0002\u0004\u00111\u0011QA\u0003C\u0002E\u0012!a\u0013\u001a\u0011\u00079\nI\u0001B\u0003;\u000b\t\u0007\u0011\u0007E\u0002E\u0019v\u0004B\u0001\u0012'\u0002\u0002AA!&!\u0005~\u0003\u0003\t9!C\u0002\u0002\u0014-\u00111b\u00157jG\u0016l\u0015\r\u001e:jq\"I\u0011qC\u0003\u0002\u0002\u0003\u000f\u0011\u0011D\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004CBA\u000e\u0003C\t9!\u0004\u0002\u0002\u001e)\u0019\u0011qD\u0007\u0002\t5\fG\u000f[\u0005\u0005\u0003G\tiB\u0001\u0005TK6L'/\u001b8h\u0011%\t9#BA\u0001\u0002\b\tI#\u0001\u0006fm&$WM\\2fIQ\u0002Ba\u00182\u0002\b\u0005\u00192-\u00198TY&\u001cW\rV3og>\u0014(gX\"SgVA\u0011qFA\u001d\u0003{\t\t\u0005\u0006\u0004\u00022\u0005\u001d\u0013Q\n\t\u000bI\u001d\n\u0019$a\u0011\u0002<\u0005\u0015\u0003C\u0002\u0016,\u0003k\ty\u0004\u0005\u0004\u0013w\u0006]\u00121\b\t\u0004]\u0005eB!B@\u0007\u0005\u0004\t\u0004c\u0001\u0018\u0002>\u00111\u0011Q\u0001\u0004C\u0002E\u00022ALA!\t\u0015QdA1\u00012!\u0011!E*a\u000e\u0011\r)R\u0016QGA \u0011%\tIEBA\u0001\u0002\b\tY%\u0001\u0006fm&$WM\\2fIU\u0002b!a\u0007\u0002\"\u0005}\u0002\"CA(\r\u0005\u0005\t9AA)\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0005?\n\fy$A\ndC:\u001cF.[2f)\u0016t7o\u001c:3?\u000e\u001b(+\u0006\u0005\u0002X\u0005\u0005\u0014QMA5)\u0019\tI&!\u001e\u0002|AQAeJA.\u0003?\nY'!\u001c\u0011\r)Z\u0013QLA4!\u0019\u001120a\u0018\u0002dA\u0019a&!\u0019\u0005\u000b}<!\u0019A\u0019\u0011\u00079\n)\u0007\u0002\u0004\u0002\u0006\u001d\u0011\r!\r\t\u0004]\u0005%D!\u0002\u001e\b\u0005\u0004\t\u0004\u0003\u0002#M\u0003G\u0002RAKA8\u0003gJ1!!\u001d\f\u0005%!&/\u00198ta>\u001cX\r\u0005\u0004+5\u0006u\u0013q\r\u0005\n\u0003o:\u0011\u0011!a\u0002\u0003s\n!\"\u001a<jI\u0016t7-\u001a\u00138!\u0019\tY\"!\t\u0002h!I\u0011QP\u0004\u0002\u0002\u0003\u000f\u0011qP\u0001\u000bKZLG-\u001a8dK\u0012B\u0004\u0003B0c\u0003O\u0002"
)
public interface TensorLowPrio extends GenericOps {
   // $FF: synthetic method
   static CanSlice2 canSliceTensor_Seq_to_2$(final TensorLowPrio $this, final CanSlice seqSlice) {
      return $this.canSliceTensor_Seq_to_2(seqSlice);
   }

   default CanSlice2 canSliceTensor_Seq_to_2(final CanSlice seqSlice) {
      return new CanSlice2(seqSlice) {
         private final CanSlice seqSlice$1;

         public Object apply(final Tensor from, final Object slice, final Object slice2) {
            return this.seqSlice$1.apply(from, .MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{slice, slice2})));
         }

         public {
            this.seqSlice$1 = seqSlice$1;
         }
      };
   }

   // $FF: synthetic method
   static CanSlice canSliceTensor$(final TensorLowPrio $this, final ClassTag evidence$1) {
      return $this.canSliceTensor(evidence$1);
   }

   default CanSlice canSliceTensor(final ClassTag evidence$1) {
      return new CanSlice(evidence$1) {
         private final ClassTag evidence$1$1;

         public SliceVector apply(final Tensor from, final Seq slice) {
            return new SliceVector(from, slice.toIndexedSeq(), this.evidence$1$1);
         }

         public {
            this.evidence$1$1 = evidence$1$1;
         }
      };
   }

   // $FF: synthetic method
   static CanSlice canSliceTensorBoolean$(final TensorLowPrio $this, final ClassTag evidence$2) {
      return $this.canSliceTensorBoolean(evidence$2);
   }

   default CanSlice canSliceTensorBoolean(final ClassTag evidence$2) {
      return new CanSlice(evidence$2) {
         private final ClassTag evidence$2$1;

         public SliceVector apply(final Tensor from, final Tensor slice) {
            return new SliceVector(from, slice.findAll((x$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(BoxesRunTime.unboxToBoolean(x$1)))), this.evidence$2$1);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$1(final boolean x$1) {
            return x$1;
         }

         public {
            this.evidence$2$1 = evidence$2$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceTensor2$(final TensorLowPrio $this, final Semiring evidence$3, final ClassTag evidence$4) {
      return $this.canSliceTensor2(evidence$3, evidence$4);
   }

   default CanSlice2 canSliceTensor2(final Semiring evidence$3, final ClassTag evidence$4) {
      return new CanSlice2(evidence$3, evidence$4) {
         private final Semiring evidence$3$1;
         private final ClassTag evidence$4$1;

         public SliceMatrix apply(final Tensor from, final Seq slice, final Seq slice2) {
            return new SliceMatrix(from, slice.toIndexedSeq(), slice2.toIndexedSeq(), this.evidence$3$1, this.evidence$4$1);
         }

         public {
            this.evidence$3$1 = evidence$3$1;
            this.evidence$4$1 = evidence$4$1;
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceTensor2_CRs$(final TensorLowPrio $this, final Semiring evidence$5, final ClassTag evidence$6) {
      return $this.canSliceTensor2_CRs(evidence$5, evidence$6);
   }

   default CanSlice2 canSliceTensor2_CRs(final Semiring evidence$5, final ClassTag evidence$6) {
      return new CanSlice2(evidence$6) {
         private final ClassTag evidence$6$1;

         public SliceVector apply(final Tensor from, final Seq slice, final Object slice2) {
            return new SliceVector(from, ((IterableOnceOps)slice.map((k1) -> new Tuple2(k1, slice2))).toIndexedSeq(), this.evidence$6$1);
         }

         public {
            this.evidence$6$1 = evidence$6$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceTensor2_CsR$(final TensorLowPrio $this, final Semiring evidence$7, final ClassTag evidence$8) {
      return $this.canSliceTensor2_CsR(evidence$7, evidence$8);
   }

   default CanSlice2 canSliceTensor2_CsR(final Semiring evidence$7, final ClassTag evidence$8) {
      return new CanSlice2(evidence$8) {
         private final ClassTag evidence$8$1;

         public Transpose apply(final Tensor from, final Object slice, final Seq slice2) {
            return (Transpose)(new SliceVector(from, ((IterableOnceOps)slice2.map((k2) -> new Tuple2(slice, k2))).toIndexedSeq(), this.evidence$8$1)).t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()));
         }

         public {
            this.evidence$8$1 = evidence$8$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final TensorLowPrio $this) {
   }
}
