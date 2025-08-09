package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005ha\u0002\t\u0012!\u0003\r\tA\u0006\u0005\u0006G\u0001!\t\u0001\n\u0003\u0006Q\u0001\u0011\t!\u000b\u0004\bg\u0001\u0001\n1%\u00015\u0011\u001514A\"\u00018\u0011\u0015i5A\"\u0001O\u0011\u0015\u0019\u0006\u0001b\u0001U\u0011\u0015A\b\u0001b\u0001z\u0011\u001d\t)\u0006\u0001C\u0002\u0003/Bq!!\u001e\u0001\t\u0007\t9\bC\u0004\u0002\u000e\u0002!\u0019!a$\t\u000f\u0005}\u0005\u0001b\u0001\u0002\"\"9\u0011q\u0017\u0001\u0005\u0004\u0005e\u0006bBAb\u0001\u0011\r\u0011Q\u0019\u0005\b\u0003\u001b\u0004A1AAh\u0011\u001d\t9\u000e\u0001C\u0002\u00033\u0014QCV3di>\u0014\u0018N_3e%\u0016$WoY3V\rVt7M\u0003\u0002\u0013'\u00051A.\u001b8bY\u001eT\u0011\u0001F\u0001\u0007EJ,WM_3\u0004\u0001M\u0019\u0001aF\u000f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g!\tq\u0012%D\u0001 \u0015\t\u00013#A\u0004hK:,'/[2\n\u0005\tz\"!B+Gk:\u001c\u0017A\u0002\u0013j]&$H\u0005F\u0001&!\tAb%\u0003\u0002(3\t!QK\\5u\u0005\ty\u0005/\u0005\u0002+[A\u0011\u0001dK\u0005\u0003Ye\u0011qAT8uQ&twME\u0002/;A2Aa\f\u0001\u0001[\taAH]3gS:,W.\u001a8u}A\u0011\u0001$M\u0005\u0003ee\u0011\u0011bU5oO2,Go\u001c8\u0003\u001fY+7\r^8sSj,\u0007*\u001a7qKJ,\"!\u000e \u0014\u0005\r9\u0012!\u0003>fe>\u001cH*[6f)\tA\u0004\nE\u0002:uqj\u0011!E\u0005\u0003wE\u00111\u0002R3og\u00164Vm\u0019;peB\u0011QH\u0010\u0007\u0001\t%y4\u0001)A\u0001\u0002\u000b\u0007\u0001IA\u0001U#\tQ\u0013\t\u0005\u0002\u0019\u0005&\u00111)\u0007\u0002\u0004\u0003:L\bF\u0001 F!\tAb)\u0003\u0002H3\tY1\u000f]3dS\u0006d\u0017N_3e\u0011\u0015IE\u00011\u0001K\u0003\raWM\u001c\t\u00031-K!\u0001T\r\u0003\u0007%sG/A\u0004d_6\u0014\u0017N\\3\u0015\u0007qz\u0015\u000bC\u0003Q\u000b\u0001\u0007A(A\u0001y\u0011\u0015\u0011V\u00011\u0001=\u0003\u0005I\u0018!\u0004<fGR|'/\u001b>f%><8/\u0006\u0002VCR!akY6o!\u00119\u0006L\u00172\u000e\u0003\u0001I!!W\u0011\u0003\t%k\u0007\u000f\u001c\t\u0005smk&-\u0003\u0002]#\ty!I]8bI\u000e\f7\u000f^3e%><8\u000fE\u0002:=\u0002L!aX\t\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\t\u0003{\u0005$Qa\u0010\u0004C\u0002\u0001\u00032!\u000f\u001ea\u0011\u001d!g!!AA\u0004\u0015\f!\"\u001a<jI\u0016t7-\u001a\u00132!\r1\u0017\u000eY\u0007\u0002O*\u0011\u0001.G\u0001\be\u00164G.Z2u\u0013\tQwM\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u0015ag\u0001q\u0001n\u0003\u0019AW\r\u001c9feB\u0019qk\u00011\t\u000b=4\u00019\u00019\u0002\r\t\f7/Z(q!\u0015\tHo\u001e2c\u001d\tq\"/\u0003\u0002t?\u0005)QKR;oG&\u0011QO\u001e\u0002\r\u0013:\u0004F.Y2f\u00136\u0004HN\r\u0006\u0003g~\u0001\"a\u0016\u0002\u0002\u001dY,7\r^8sSj,'k\\<teU\u0019!0a\u0001\u0015\u000fm\fY$!\u0011\u0002RA1q\u000b @\u0002:}L!!`\u0011\u0003\u000b%k\u0007\u000f\u001c\u001a\u0011\u000beZv0!\u000f\u0011\ter\u0016\u0011\u0001\t\u0004{\u0005\rA!C \bA\u0003\u0005\tQ1\u0001AQ-\t\u0019!RA\u0004\u00037\t)#a\f2\u0013\r\nI!a\u0003\u0002\u0010\u00055ab\u0001\r\u0002\f%\u0019\u0011QB\r\u0002\r\u0011{WO\u00197fc\u0019!\u0013\u0011CA\r59!\u00111CA\r\u001b\t\t)BC\u0002\u0002\u0018U\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000e2\u0013\r\ni\"a\b\u0002$\u0005\u0005bb\u0001\r\u0002 %\u0019\u0011\u0011E\r\u0002\u000b\u0019cw.\u0019;2\r\u0011\n\t\"!\u0007\u001bc%\u0019\u0013qEA\u0015\u0003[\tYCD\u0002\u0019\u0003SI1!a\u000b\u001a\u0003\u0011auN\\42\r\u0011\n\t\"!\u0007\u001bc%\u0019\u0013\u0011GA\u001a\u0003o\t)DD\u0002\u0019\u0003gI1!!\u000e\u001a\u0003\rIe\u000e^\u0019\u0007I\u0005E\u0011\u0011\u0004\u000e\u0011\teR\u0014\u0011\u0001\u0005\n\u0003{9\u0011\u0011!a\u0002\u0003\u007f\t!\"\u001a<jI\u0016t7-\u001a\u00133!\u00111\u0017.!\u0001\t\u0013\u0005\rs!!AA\u0004\u0005\u0015\u0013AC3wS\u0012,gnY3%gA1\u0011qIA'\u0003\u0003i!!!\u0013\u000b\u0007\u0005-3#A\u0004ti>\u0014\u0018mZ3\n\t\u0005=\u0013\u0011\n\u0002\u00055\u0016\u0014x\u000e\u0003\u0004p\u000f\u0001\u000f\u00111\u000b\t\t/r\f\t!!\u0001\u0002\u0002\u0005!b/Z2u_JL'0Z\"pYN|Fi\\;cY\u0016$B!!\u0017\u0002rA1q\u000bWA.\u0003W\u0002r!OA/\u0003C\nI'C\u0002\u0002`E\u0011!C\u0011:pC\u0012\u001c\u0017m\u001d;fI\u000e{G.^7ogB!\u0011HXA2!\rA\u0012QM\u0005\u0004\u0003OJ\"A\u0002#pk\ndW\r\u0005\u0003:u\u0005\r\u0004#B\u001d\u0002n\u0005%\u0014bAA8#\tIAK]1ogB|7/\u001a\u0005\u0007Y\"\u0001\u001d!a\u001d\u0011\t]\u001b\u00111M\u0001\u0014m\u0016\u001cGo\u001c:ju\u0016\u001cu\u000e\\:`\r2|\u0017\r\u001e\u000b\u0005\u0003s\nI\t\u0005\u0004X1\u0006m\u0014q\u0011\t\bs\u0005u\u0013QPAC!\u0011Id,a \u0011\u0007a\t\t)C\u0002\u0002\u0004f\u0011QA\u00127pCR\u0004B!\u000f\u001e\u0002\u0000A)\u0011(!\u001c\u0002\u0006\"1A.\u0003a\u0002\u0003\u0017\u0003BaV\u0002\u0002\u0000\u0005\tb/Z2u_JL'0Z\"pYN|\u0016J\u001c;\u0015\t\u0005E\u00151\u0014\t\u0007/b\u000b\u0019*!'\u0011\u000fe\ni&!&\u0002\u0018B\u0019\u0011H\u0018&\u0011\u0007eR$\nE\u0003:\u0003[\n9\n\u0003\u0004m\u0015\u0001\u000f\u0011Q\u0014\t\u0004/\u000eQ\u0015A\u0005<fGR|'/\u001b>f\u0007>d7o\u0018'p]\u001e$B!a)\u00024B1q\u000bWAS\u0003c\u0003r!OA/\u0003O\u000by\u000b\u0005\u0003:=\u0006%\u0006c\u0001\r\u0002,&\u0019\u0011QV\r\u0003\t1{gn\u001a\t\u0005si\nI\u000bE\u0003:\u0003[\ny\u000b\u0003\u0004m\u0017\u0001\u000f\u0011Q\u0017\t\u0005/\u000e\tI+A\u000bwK\u000e$xN]5{K\u000e{Gn\u001d\u001a`\t>,(\r\\3\u0015\t\u0005m\u0016Q\u0018\t\t/r\fY&!\u001b\u0002b!9\u0011q\u0018\u0007A\u0004\u0005\u0005\u0017!B5na2\u0014\u0004\u0003C,}\u0003G\n\u0019'a\u0019\u0002)Y,7\r^8sSj,7i\u001c7te}3En\\1u)\u0011\t9-!3\u0011\u0011]c\u00181PAC\u0003{Bq!a0\u000e\u0001\b\tY\r\u0005\u0005Xy\u0006}\u0014qPA@\u0003I1Xm\u0019;pe&TXmQ8mgJz\u0016J\u001c;\u0015\t\u0005E\u00171\u001b\t\t/r\f\u0019*a&\u0002\u0016\"9\u0011q\u0018\bA\u0004\u0005U\u0007#B,}\u0015*S\u0015a\u0005<fGR|'/\u001b>f\u0007>d7OM0M_:<G\u0003BAn\u0003;\u0004\u0002b\u0016?\u0002&\u0006=\u0016q\u0015\u0005\b\u0003\u007f{\u00019AAp!!9F0!+\u0002*\u0006%\u0006"
)
public interface VectorizedReduceUFunc extends UFunc {
   // $FF: synthetic method
   static UFunc.UImpl vectorizeRows$(final VectorizedReduceUFunc $this, final ClassTag evidence$1, final VectorizeHelper helper, final UFunc.InPlaceImpl2 baseOp) {
      return $this.vectorizeRows(evidence$1, helper, baseOp);
   }

   default UFunc.UImpl vectorizeRows(final ClassTag evidence$1, final VectorizeHelper helper, final UFunc.InPlaceImpl2 baseOp) {
      return new UFunc.UImpl(helper, baseOp) {
         private final VectorizeHelper helper$1;
         private final UFunc.InPlaceImpl2 baseOp$1;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public DenseVector apply(final BroadcastedRows v) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            DenseVector result = this.helper$1.zerosLike(mat.rows());
            int index$macro$2 = 0;

            for(int limit$macro$4 = mat.cols(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               this.baseOp$1.apply(result, mat.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), HasOps$.MODULE$.canSliceCol()));
            }

            return result;
         }

         public {
            this.helper$1 = helper$1;
            this.baseOp$1 = baseOp$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 vectorizeRows2$(final VectorizedReduceUFunc $this, final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return $this.vectorizeRows2(evidence$2, evidence$3, baseOp);
   }

   default UFunc.UImpl2 vectorizeRows2(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return new UFunc.UImpl2(evidence$2, evidence$3, baseOp) {
         private final ClassTag evidence$2$1;
         private final Zero evidence$3$1;
         private final UFunc.UImpl2 baseOp$2;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final BroadcastedRows v, final DenseVector dv) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            int left$macro$1 = dv.length();
            int right$macro$2 = mat.cols();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(98)).append("requirement failed: Vector length must be same as number of columns!: ").append("dv.length == mat.cols (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseMatrix var10000;
               if (!mat.isTranspose()) {
                  DenseMatrix res = DenseMatrix$.MODULE$.zeros(mat.rows(), mat.cols(), this.evidence$2$1, this.evidence$3$1);
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = mat.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     Object b = dv.apply(index$macro$9);
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = mat.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        res.update(index$macro$4, index$macro$9, this.baseOp$2.apply(mat.apply(index$macro$4, index$macro$9), b));
                     }
                  }

                  var10000 = res;
               } else {
                  DenseMatrix res = (DenseMatrix)DenseMatrix$.MODULE$.zeros(mat.cols(), mat.rows(), this.evidence$2$1, this.evidence$3$1).t(HasOps$.MODULE$.canTranspose_DM());
                  int index$macro$19 = 0;

                  for(int limit$macro$21 = mat.rows(); index$macro$19 < limit$macro$21; ++index$macro$19) {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = mat.cols(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ((i, j) -> res.update(i, j, this.baseOp$2.apply(mat.apply(i, j), dv.apply(j)))).apply$mcVII$sp(index$macro$19, index$macro$14);
                     }
                  }

                  var10000 = res;
               }

               return var10000;
            }
         }

         public {
            this.evidence$2$1 = evidence$2$1;
            this.evidence$3$1 = evidence$3$1;
            this.baseOp$2 = baseOp$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl vectorizeCols_Double$(final VectorizedReduceUFunc $this, final VectorizeHelper helper) {
      return $this.vectorizeCols_Double(helper);
   }

   default UFunc.UImpl vectorizeCols_Double(final VectorizeHelper helper) {
      return new UFunc.UImpl(helper) {
         private final VectorizeHelper helper$2;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public Transpose apply(final BroadcastedColumns v) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            DenseVector res = this.helper$2.zerosLike$mcD$sp(mat.cols());
            if (!mat.isTranspose()) {
               double[] d = mat.data$mcD$sp();
               int index$macro$7 = 0;

               for(int limit$macro$9 = mat.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  double r = res.apply$mcD$sp(index$macro$7);
                  int baseOff = mat.offset() + index$macro$7 * mat.majorStride();
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = mat.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     r = this.helper$2.combine$mcD$sp(r, d[baseOff + index$macro$2]);
                  }

                  res.update$mcD$sp(index$macro$7, r);
               }
            } else {
               int index$macro$17 = 0;

               for(int limit$macro$19 = mat.rows(); index$macro$17 < limit$macro$19; ++index$macro$17) {
                  int index$macro$12 = 0;

                  for(int limit$macro$14 = mat.cols(); index$macro$12 < limit$macro$14; ++index$macro$12) {
                     res.update$mcD$sp(index$macro$12, this.helper$2.combine$mcD$sp(res.apply$mcD$sp(index$macro$12), mat.apply$mcD$sp(index$macro$17, index$macro$12)));
                  }
               }
            }

            return (Transpose)res.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()));
         }

         public {
            this.helper$2 = helper$2;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl vectorizeCols_Float$(final VectorizedReduceUFunc $this, final VectorizeHelper helper) {
      return $this.vectorizeCols_Float(helper);
   }

   default UFunc.UImpl vectorizeCols_Float(final VectorizeHelper helper) {
      return new UFunc.UImpl(helper) {
         private final VectorizeHelper helper$3;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public Transpose apply(final BroadcastedColumns v) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            DenseVector res = this.helper$3.zerosLike$mcF$sp(mat.cols());
            if (!mat.isTranspose()) {
               float[] d = mat.data$mcF$sp();
               int index$macro$7 = 0;

               for(int limit$macro$9 = mat.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  float r = res.apply$mcF$sp(index$macro$7);
                  int baseOff = mat.offset() + index$macro$7 * mat.majorStride();
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = mat.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     r = this.helper$3.combine$mcF$sp(r, d[baseOff + index$macro$2]);
                  }

                  res.update$mcF$sp(index$macro$7, r);
               }
            } else {
               int index$macro$17 = 0;

               for(int limit$macro$19 = mat.rows(); index$macro$17 < limit$macro$19; ++index$macro$17) {
                  int index$macro$12 = 0;

                  for(int limit$macro$14 = mat.cols(); index$macro$12 < limit$macro$14; ++index$macro$12) {
                     res.update$mcF$sp(index$macro$12, this.helper$3.combine$mcF$sp(res.apply$mcF$sp(index$macro$12), mat.apply$mcF$sp(index$macro$17, index$macro$12)));
                  }
               }
            }

            return (Transpose)res.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()));
         }

         public {
            this.helper$3 = helper$3;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl vectorizeCols_Int$(final VectorizedReduceUFunc $this, final VectorizeHelper helper) {
      return $this.vectorizeCols_Int(helper);
   }

   default UFunc.UImpl vectorizeCols_Int(final VectorizeHelper helper) {
      return new UFunc.UImpl(helper) {
         private final VectorizeHelper helper$4;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public Transpose apply(final BroadcastedColumns v) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            DenseVector res = this.helper$4.zerosLike$mcI$sp(mat.cols());
            if (!mat.isTranspose()) {
               int[] d = mat.data$mcI$sp();
               int index$macro$7 = 0;

               for(int limit$macro$9 = mat.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int r = res.apply$mcI$sp(index$macro$7);
                  int baseOff = mat.offset() + index$macro$7 * mat.majorStride();
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = mat.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     r = this.helper$4.combine$mcI$sp(r, d[baseOff + index$macro$2]);
                  }

                  res.update$mcI$sp(index$macro$7, r);
               }
            } else {
               int index$macro$17 = 0;

               for(int limit$macro$19 = mat.rows(); index$macro$17 < limit$macro$19; ++index$macro$17) {
                  int index$macro$12 = 0;

                  for(int limit$macro$14 = mat.cols(); index$macro$12 < limit$macro$14; ++index$macro$12) {
                     res.update$mcI$sp(index$macro$12, this.helper$4.combine$mcI$sp(res.apply$mcI$sp(index$macro$12), mat.apply$mcI$sp(index$macro$17, index$macro$12)));
                  }
               }
            }

            return (Transpose)res.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()));
         }

         public {
            this.helper$4 = helper$4;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl vectorizeCols_Long$(final VectorizedReduceUFunc $this, final VectorizeHelper helper) {
      return $this.vectorizeCols_Long(helper);
   }

   default UFunc.UImpl vectorizeCols_Long(final VectorizeHelper helper) {
      return new UFunc.UImpl(helper) {
         private final VectorizeHelper helper$5;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public Transpose apply(final BroadcastedColumns v) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            DenseVector res = this.helper$5.zerosLike$mcJ$sp(mat.cols());
            if (!mat.isTranspose()) {
               long[] d = mat.data$mcJ$sp();
               int index$macro$7 = 0;

               for(int limit$macro$9 = mat.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  long r = res.apply$mcJ$sp(index$macro$7);
                  int baseOff = mat.offset() + index$macro$7 * mat.majorStride();
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = mat.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     r = this.helper$5.combine$mcJ$sp(r, d[baseOff + index$macro$2]);
                  }

                  res.update$mcJ$sp(index$macro$7, r);
               }
            } else {
               int index$macro$17 = 0;

               for(int limit$macro$19 = mat.rows(); index$macro$17 < limit$macro$19; ++index$macro$17) {
                  int index$macro$12 = 0;

                  for(int limit$macro$14 = mat.cols(); index$macro$12 < limit$macro$14; ++index$macro$12) {
                     res.update$mcJ$sp(index$macro$12, this.helper$5.combine$mcJ$sp(res.apply$mcJ$sp(index$macro$12), mat.apply$mcJ$sp(index$macro$17, index$macro$12)));
                  }
               }
            }

            return (Transpose)res.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()));
         }

         public {
            this.helper$5 = helper$5;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 vectorizeCols2_Double$(final VectorizedReduceUFunc $this, final UFunc.UImpl2 impl2) {
      return $this.vectorizeCols2_Double(impl2);
   }

   default UFunc.UImpl2 vectorizeCols2_Double(final UFunc.UImpl2 impl2) {
      return new UFunc.UImpl2(impl2) {
         private final UFunc.UImpl2 impl2$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final BroadcastedColumns v, final DenseVector dv) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            int left$macro$1 = dv.length();
            int right$macro$2 = mat.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(95)).append("requirement failed: Vector length must be same as number of rows!: ").append("dv.length == mat.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseMatrix var10000;
               if (!mat.isTranspose()) {
                  DenseMatrix res = DenseMatrix$.MODULE$.zeros$mDc$sp(mat.rows(), mat.cols(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  double[] d = mat.data$mcD$sp();
                  double[] rd = res.data$mcD$sp();
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = mat.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     int baseOff = mat.offset() + index$macro$9 * mat.majorStride();
                     int rBaseOff = index$macro$9 * mat.majorStride();
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = mat.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        rd[rBaseOff + index$macro$4] = this.impl2$1.apply$mcDDD$sp(d[baseOff + index$macro$4], dv.apply$mcD$sp(index$macro$4));
                     }
                  }

                  var10000 = res;
               } else {
                  DenseMatrix res = (DenseMatrix)DenseMatrix$.MODULE$.zeros$mDc$sp(mat.cols(), mat.rows(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()).t(HasOps$.MODULE$.canTranspose_DM());
                  int index$macro$19 = 0;

                  for(int limit$macro$21 = mat.rows(); index$macro$19 < limit$macro$21; ++index$macro$19) {
                     double cmp = dv.apply$mcD$sp(index$macro$19);
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = mat.cols(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                        res.update$mcD$sp(index$macro$19, index$macro$14, this.impl2$1.apply$mcDDD$sp(mat.apply$mcD$sp(index$macro$19, index$macro$14), cmp));
                     }
                  }

                  var10000 = ((DenseMatrix)res.t(HasOps$.MODULE$.canTranspose_DM())).copy$mcD$sp();
               }

               return var10000;
            }
         }

         public {
            this.impl2$1 = impl2$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 vectorizeCols2_Float$(final VectorizedReduceUFunc $this, final UFunc.UImpl2 impl2) {
      return $this.vectorizeCols2_Float(impl2);
   }

   default UFunc.UImpl2 vectorizeCols2_Float(final UFunc.UImpl2 impl2) {
      return new UFunc.UImpl2(impl2) {
         private final UFunc.UImpl2 impl2$2;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final BroadcastedColumns v, final DenseVector dv) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            int left$macro$1 = dv.length();
            int right$macro$2 = mat.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(95)).append("requirement failed: Vector length must be same as number of rows!: ").append("dv.length == mat.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseMatrix var10000;
               if (!mat.isTranspose()) {
                  DenseMatrix res = DenseMatrix$.MODULE$.zeros$mFc$sp(mat.rows(), mat.cols(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
                  float[] d = mat.data$mcF$sp();
                  float[] rd = res.data$mcF$sp();
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = mat.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     int baseOff = mat.offset() + index$macro$9 * mat.majorStride();
                     int rBaseOff = index$macro$9 * mat.majorStride();
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = mat.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        rd[rBaseOff + index$macro$4] = this.impl2$2.apply$mcFFF$sp(d[baseOff + index$macro$4], dv.apply$mcF$sp(index$macro$4));
                     }
                  }

                  var10000 = res;
               } else {
                  DenseMatrix res = (DenseMatrix)DenseMatrix$.MODULE$.zeros$mFc$sp(mat.cols(), mat.rows(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero()).t(HasOps$.MODULE$.canTranspose_DM());
                  int index$macro$19 = 0;

                  for(int limit$macro$21 = mat.rows(); index$macro$19 < limit$macro$21; ++index$macro$19) {
                     float cmp = dv.apply$mcF$sp(index$macro$19);
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = mat.cols(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                        res.update$mcF$sp(index$macro$19, index$macro$14, this.impl2$2.apply$mcFFF$sp(mat.apply$mcF$sp(index$macro$19, index$macro$14), cmp));
                     }
                  }

                  var10000 = ((DenseMatrix)res.t(HasOps$.MODULE$.canTranspose_DM())).copy$mcF$sp();
               }

               return var10000;
            }
         }

         public {
            this.impl2$2 = impl2$2;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 vectorizeCols2_Int$(final VectorizedReduceUFunc $this, final UFunc.UImpl2 impl2) {
      return $this.vectorizeCols2_Int(impl2);
   }

   default UFunc.UImpl2 vectorizeCols2_Int(final UFunc.UImpl2 impl2) {
      return new UFunc.UImpl2(impl2) {
         private final UFunc.UImpl2 impl2$3;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final BroadcastedColumns v, final DenseVector dv) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            int left$macro$1 = dv.length();
            int right$macro$2 = mat.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(95)).append("requirement failed: Vector length must be same as number of rows!: ").append("dv.length == mat.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseMatrix var10000;
               if (!mat.isTranspose()) {
                  DenseMatrix res = DenseMatrix$.MODULE$.zeros$mIc$sp(mat.rows(), mat.cols(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
                  int[] d = mat.data$mcI$sp();
                  int[] rd = res.data$mcI$sp();
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = mat.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     int baseOff = mat.offset() + index$macro$9 * mat.majorStride();
                     int rBaseOff = index$macro$9 * mat.majorStride();
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = mat.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        rd[rBaseOff + index$macro$4] = this.impl2$3.apply$mcIII$sp(d[baseOff + index$macro$4], dv.apply$mcI$sp(index$macro$4));
                     }
                  }

                  var10000 = res;
               } else {
                  DenseMatrix res = (DenseMatrix)DenseMatrix$.MODULE$.zeros$mIc$sp(mat.cols(), mat.rows(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero()).t(HasOps$.MODULE$.canTranspose_DM());
                  int index$macro$19 = 0;

                  for(int limit$macro$21 = mat.rows(); index$macro$19 < limit$macro$21; ++index$macro$19) {
                     int cmp = dv.apply$mcI$sp(index$macro$19);
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = mat.cols(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                        res.update$mcI$sp(index$macro$19, index$macro$14, this.impl2$3.apply$mcIII$sp(mat.apply$mcI$sp(index$macro$19, index$macro$14), cmp));
                     }
                  }

                  var10000 = ((DenseMatrix)res.t(HasOps$.MODULE$.canTranspose_DM())).copy$mcI$sp();
               }

               return var10000;
            }
         }

         public {
            this.impl2$3 = impl2$3;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 vectorizeCols2_Long$(final VectorizedReduceUFunc $this, final UFunc.UImpl2 impl2) {
      return $this.vectorizeCols2_Long(impl2);
   }

   default UFunc.UImpl2 vectorizeCols2_Long(final UFunc.UImpl2 impl2) {
      return new UFunc.UImpl2(impl2) {
         private final UFunc.UImpl2 impl2$4;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final BroadcastedColumns v, final DenseVector dv) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            int left$macro$1 = dv.length();
            int right$macro$2 = mat.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(95)).append("requirement failed: Vector length must be same as number of rows!: ").append("dv.length == mat.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseMatrix var10000;
               if (!mat.isTranspose()) {
                  DenseMatrix res = DenseMatrix$.MODULE$.zeros$mJc$sp(mat.rows(), mat.cols(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
                  long[] d = mat.data$mcJ$sp();
                  long[] rd = res.data$mcJ$sp();
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = mat.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     int baseOff = mat.offset() + index$macro$9 * mat.majorStride();
                     int rBaseOff = index$macro$9 * mat.majorStride();
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = mat.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        rd[rBaseOff + index$macro$4] = BoxesRunTime.unboxToLong(this.impl2$4.apply(BoxesRunTime.boxToLong(d[baseOff + index$macro$4]), BoxesRunTime.boxToLong(dv.apply$mcJ$sp(index$macro$4))));
                     }
                  }

                  var10000 = res;
               } else {
                  DenseMatrix res = (DenseMatrix)DenseMatrix$.MODULE$.zeros$mJc$sp(mat.cols(), mat.rows(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero()).t(HasOps$.MODULE$.canTranspose_DM());
                  int index$macro$19 = 0;

                  for(int limit$macro$21 = mat.rows(); index$macro$19 < limit$macro$21; ++index$macro$19) {
                     long cmp = dv.apply$mcJ$sp(index$macro$19);
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = mat.cols(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                        res.update$mcJ$sp(index$macro$19, index$macro$14, BoxesRunTime.unboxToLong(this.impl2$4.apply(BoxesRunTime.boxToLong(mat.apply$mcJ$sp(index$macro$19, index$macro$14)), BoxesRunTime.boxToLong(cmp))));
                     }
                  }

                  var10000 = ((DenseMatrix)res.t(HasOps$.MODULE$.canTranspose_DM())).copy$mcJ$sp();
               }

               return var10000;
            }
         }

         public {
            this.impl2$4 = impl2$4;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 vectorizeRows2$mDc$sp$(final VectorizedReduceUFunc $this, final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return $this.vectorizeRows2$mDc$sp(evidence$2, evidence$3, baseOp);
   }

   default UFunc.UImpl2 vectorizeRows2$mDc$sp(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return new UFunc.UImpl2(evidence$2, evidence$3, baseOp) {
         private final ClassTag evidence$2$2;
         private final Zero evidence$3$2;
         private final UFunc.UImpl2 baseOp$3;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final BroadcastedRows v, final DenseVector dv) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            int left$macro$1 = dv.length();
            int right$macro$2 = mat.cols();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(98)).append("requirement failed: Vector length must be same as number of columns!: ").append("dv.length == mat.cols (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseMatrix var10000;
               if (!mat.isTranspose()) {
                  DenseMatrix res = DenseMatrix$.MODULE$.zeros$mDc$sp(mat.rows(), mat.cols(), this.evidence$2$2, this.evidence$3$2);
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = mat.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     double b = dv.apply$mcD$sp(index$macro$9);
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = mat.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        res.update$mcD$sp(index$macro$4, index$macro$9, this.baseOp$3.apply$mcDDD$sp(mat.apply$mcD$sp(index$macro$4, index$macro$9), b));
                     }
                  }

                  var10000 = res;
               } else {
                  DenseMatrix res = (DenseMatrix)DenseMatrix$.MODULE$.zeros$mDc$sp(mat.cols(), mat.rows(), this.evidence$2$2, this.evidence$3$2).t(HasOps$.MODULE$.canTranspose_DM());
                  int index$macro$19 = 0;

                  for(int limit$macro$21 = mat.rows(); index$macro$19 < limit$macro$21; ++index$macro$19) {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = mat.cols(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ((i, j) -> res.update$mcD$sp(i, j, this.baseOp$3.apply$mcDDD$sp(mat.apply$mcD$sp(i, j), dv.apply$mcD$sp(j)))).apply$mcVII$sp(index$macro$19, index$macro$14);
                     }
                  }

                  var10000 = res;
               }

               return var10000;
            }
         }

         public {
            this.evidence$2$2 = evidence$2$2;
            this.evidence$3$2 = evidence$3$2;
            this.baseOp$3 = baseOp$3;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 vectorizeRows2$mFc$sp$(final VectorizedReduceUFunc $this, final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return $this.vectorizeRows2$mFc$sp(evidence$2, evidence$3, baseOp);
   }

   default UFunc.UImpl2 vectorizeRows2$mFc$sp(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return new UFunc.UImpl2(evidence$2, evidence$3, baseOp) {
         private final ClassTag evidence$2$3;
         private final Zero evidence$3$3;
         private final UFunc.UImpl2 baseOp$4;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final BroadcastedRows v, final DenseVector dv) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            int left$macro$1 = dv.length();
            int right$macro$2 = mat.cols();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(98)).append("requirement failed: Vector length must be same as number of columns!: ").append("dv.length == mat.cols (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseMatrix var10000;
               if (!mat.isTranspose()) {
                  DenseMatrix res = DenseMatrix$.MODULE$.zeros$mFc$sp(mat.rows(), mat.cols(), this.evidence$2$3, this.evidence$3$3);
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = mat.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     float b = dv.apply$mcF$sp(index$macro$9);
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = mat.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        res.update$mcF$sp(index$macro$4, index$macro$9, this.baseOp$4.apply$mcFFF$sp(mat.apply$mcF$sp(index$macro$4, index$macro$9), b));
                     }
                  }

                  var10000 = res;
               } else {
                  DenseMatrix res = (DenseMatrix)DenseMatrix$.MODULE$.zeros$mFc$sp(mat.cols(), mat.rows(), this.evidence$2$3, this.evidence$3$3).t(HasOps$.MODULE$.canTranspose_DM());
                  int index$macro$19 = 0;

                  for(int limit$macro$21 = mat.rows(); index$macro$19 < limit$macro$21; ++index$macro$19) {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = mat.cols(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ((i, j) -> res.update$mcF$sp(i, j, this.baseOp$4.apply$mcFFF$sp(mat.apply$mcF$sp(i, j), dv.apply$mcF$sp(j)))).apply$mcVII$sp(index$macro$19, index$macro$14);
                     }
                  }

                  var10000 = res;
               }

               return var10000;
            }
         }

         public {
            this.evidence$2$3 = evidence$2$3;
            this.evidence$3$3 = evidence$3$3;
            this.baseOp$4 = baseOp$4;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 vectorizeRows2$mIc$sp$(final VectorizedReduceUFunc $this, final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return $this.vectorizeRows2$mIc$sp(evidence$2, evidence$3, baseOp);
   }

   default UFunc.UImpl2 vectorizeRows2$mIc$sp(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return new UFunc.UImpl2(evidence$2, evidence$3, baseOp) {
         private final ClassTag evidence$2$4;
         private final Zero evidence$3$4;
         private final UFunc.UImpl2 baseOp$5;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final BroadcastedRows v, final DenseVector dv) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            int left$macro$1 = dv.length();
            int right$macro$2 = mat.cols();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(98)).append("requirement failed: Vector length must be same as number of columns!: ").append("dv.length == mat.cols (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseMatrix var10000;
               if (!mat.isTranspose()) {
                  DenseMatrix res = DenseMatrix$.MODULE$.zeros$mIc$sp(mat.rows(), mat.cols(), this.evidence$2$4, this.evidence$3$4);
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = mat.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     int b = dv.apply$mcI$sp(index$macro$9);
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = mat.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        res.update$mcI$sp(index$macro$4, index$macro$9, this.baseOp$5.apply$mcIII$sp(mat.apply$mcI$sp(index$macro$4, index$macro$9), b));
                     }
                  }

                  var10000 = res;
               } else {
                  DenseMatrix res = (DenseMatrix)DenseMatrix$.MODULE$.zeros$mIc$sp(mat.cols(), mat.rows(), this.evidence$2$4, this.evidence$3$4).t(HasOps$.MODULE$.canTranspose_DM());
                  int index$macro$19 = 0;

                  for(int limit$macro$21 = mat.rows(); index$macro$19 < limit$macro$21; ++index$macro$19) {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = mat.cols(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ((i, j) -> res.update$mcI$sp(i, j, this.baseOp$5.apply$mcIII$sp(mat.apply$mcI$sp(i, j), dv.apply$mcI$sp(j)))).apply$mcVII$sp(index$macro$19, index$macro$14);
                     }
                  }

                  var10000 = res;
               }

               return var10000;
            }
         }

         public {
            this.evidence$2$4 = evidence$2$4;
            this.evidence$3$4 = evidence$3$4;
            this.baseOp$5 = baseOp$5;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 vectorizeRows2$mJc$sp$(final VectorizedReduceUFunc $this, final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return $this.vectorizeRows2$mJc$sp(evidence$2, evidence$3, baseOp);
   }

   default UFunc.UImpl2 vectorizeRows2$mJc$sp(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return new UFunc.UImpl2(evidence$2, evidence$3, baseOp) {
         private final ClassTag evidence$2$5;
         private final Zero evidence$3$5;
         private final UFunc.UImpl2 baseOp$6;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final BroadcastedRows v, final DenseVector dv) {
            DenseMatrix mat = (DenseMatrix)v.underlying();
            int left$macro$1 = dv.length();
            int right$macro$2 = mat.cols();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(98)).append("requirement failed: Vector length must be same as number of columns!: ").append("dv.length == mat.cols (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseMatrix var10000;
               if (!mat.isTranspose()) {
                  DenseMatrix res = DenseMatrix$.MODULE$.zeros$mJc$sp(mat.rows(), mat.cols(), this.evidence$2$5, this.evidence$3$5);
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = mat.cols(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     long b = dv.apply$mcJ$sp(index$macro$9);
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = mat.rows(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        res.update$mcJ$sp(index$macro$4, index$macro$9, BoxesRunTime.unboxToLong(this.baseOp$6.apply(BoxesRunTime.boxToLong(mat.apply$mcJ$sp(index$macro$4, index$macro$9)), BoxesRunTime.boxToLong(b))));
                     }
                  }

                  var10000 = res;
               } else {
                  DenseMatrix res = (DenseMatrix)DenseMatrix$.MODULE$.zeros$mJc$sp(mat.cols(), mat.rows(), this.evidence$2$5, this.evidence$3$5).t(HasOps$.MODULE$.canTranspose_DM());
                  int index$macro$19 = 0;

                  for(int limit$macro$21 = mat.rows(); index$macro$19 < limit$macro$21; ++index$macro$19) {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = mat.cols(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ((i, j) -> res.update$mcJ$sp(i, j, BoxesRunTime.unboxToLong(this.baseOp$6.apply(BoxesRunTime.boxToLong(mat.apply$mcJ$sp(i, j)), BoxesRunTime.boxToLong(dv.apply$mcJ$sp(j)))))).apply$mcVII$sp(index$macro$19, index$macro$14);
                     }
                  }

                  var10000 = res;
               }

               return var10000;
            }
         }

         public {
            this.evidence$2$5 = evidence$2$5;
            this.evidence$3$5 = evidence$3$5;
            this.baseOp$6 = baseOp$6;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final VectorizedReduceUFunc $this) {
   }

   public interface VectorizeHelper {
      DenseVector zerosLike(final int len);

      Object combine(final Object x, final Object y);

      // $FF: synthetic method
      static DenseVector zerosLike$mcZ$sp$(final VectorizeHelper $this, final int len) {
         return $this.zerosLike$mcZ$sp(len);
      }

      default DenseVector zerosLike$mcZ$sp(final int len) {
         return this.zerosLike(len);
      }

      // $FF: synthetic method
      static DenseVector zerosLike$mcB$sp$(final VectorizeHelper $this, final int len) {
         return $this.zerosLike$mcB$sp(len);
      }

      default DenseVector zerosLike$mcB$sp(final int len) {
         return this.zerosLike(len);
      }

      // $FF: synthetic method
      static DenseVector zerosLike$mcC$sp$(final VectorizeHelper $this, final int len) {
         return $this.zerosLike$mcC$sp(len);
      }

      default DenseVector zerosLike$mcC$sp(final int len) {
         return this.zerosLike(len);
      }

      // $FF: synthetic method
      static DenseVector zerosLike$mcD$sp$(final VectorizeHelper $this, final int len) {
         return $this.zerosLike$mcD$sp(len);
      }

      default DenseVector zerosLike$mcD$sp(final int len) {
         return this.zerosLike(len);
      }

      // $FF: synthetic method
      static DenseVector zerosLike$mcF$sp$(final VectorizeHelper $this, final int len) {
         return $this.zerosLike$mcF$sp(len);
      }

      default DenseVector zerosLike$mcF$sp(final int len) {
         return this.zerosLike(len);
      }

      // $FF: synthetic method
      static DenseVector zerosLike$mcI$sp$(final VectorizeHelper $this, final int len) {
         return $this.zerosLike$mcI$sp(len);
      }

      default DenseVector zerosLike$mcI$sp(final int len) {
         return this.zerosLike(len);
      }

      // $FF: synthetic method
      static DenseVector zerosLike$mcJ$sp$(final VectorizeHelper $this, final int len) {
         return $this.zerosLike$mcJ$sp(len);
      }

      default DenseVector zerosLike$mcJ$sp(final int len) {
         return this.zerosLike(len);
      }

      // $FF: synthetic method
      static DenseVector zerosLike$mcS$sp$(final VectorizeHelper $this, final int len) {
         return $this.zerosLike$mcS$sp(len);
      }

      default DenseVector zerosLike$mcS$sp(final int len) {
         return this.zerosLike(len);
      }

      // $FF: synthetic method
      static DenseVector zerosLike$mcV$sp$(final VectorizeHelper $this, final int len) {
         return $this.zerosLike$mcV$sp(len);
      }

      default DenseVector zerosLike$mcV$sp(final int len) {
         return this.zerosLike(len);
      }

      // $FF: synthetic method
      static boolean combine$mcZ$sp$(final VectorizeHelper $this, final boolean x, final boolean y) {
         return $this.combine$mcZ$sp(x, y);
      }

      default boolean combine$mcZ$sp(final boolean x, final boolean y) {
         return BoxesRunTime.unboxToBoolean(this.combine(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y)));
      }

      // $FF: synthetic method
      static byte combine$mcB$sp$(final VectorizeHelper $this, final byte x, final byte y) {
         return $this.combine$mcB$sp(x, y);
      }

      default byte combine$mcB$sp(final byte x, final byte y) {
         return BoxesRunTime.unboxToByte(this.combine(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
      }

      // $FF: synthetic method
      static char combine$mcC$sp$(final VectorizeHelper $this, final char x, final char y) {
         return $this.combine$mcC$sp(x, y);
      }

      default char combine$mcC$sp(final char x, final char y) {
         return BoxesRunTime.unboxToChar(this.combine(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y)));
      }

      // $FF: synthetic method
      static double combine$mcD$sp$(final VectorizeHelper $this, final double x, final double y) {
         return $this.combine$mcD$sp(x, y);
      }

      default double combine$mcD$sp(final double x, final double y) {
         return BoxesRunTime.unboxToDouble(this.combine(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
      }

      // $FF: synthetic method
      static float combine$mcF$sp$(final VectorizeHelper $this, final float x, final float y) {
         return $this.combine$mcF$sp(x, y);
      }

      default float combine$mcF$sp(final float x, final float y) {
         return BoxesRunTime.unboxToFloat(this.combine(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
      }

      // $FF: synthetic method
      static int combine$mcI$sp$(final VectorizeHelper $this, final int x, final int y) {
         return $this.combine$mcI$sp(x, y);
      }

      default int combine$mcI$sp(final int x, final int y) {
         return BoxesRunTime.unboxToInt(this.combine(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
      }

      // $FF: synthetic method
      static long combine$mcJ$sp$(final VectorizeHelper $this, final long x, final long y) {
         return $this.combine$mcJ$sp(x, y);
      }

      default long combine$mcJ$sp(final long x, final long y) {
         return BoxesRunTime.unboxToLong(this.combine(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
      }

      // $FF: synthetic method
      static short combine$mcS$sp$(final VectorizeHelper $this, final short x, final short y) {
         return $this.combine$mcS$sp(x, y);
      }

      default short combine$mcS$sp(final short x, final short y) {
         return BoxesRunTime.unboxToShort(this.combine(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
      }

      // $FF: synthetic method
      static void combine$mcV$sp$(final VectorizeHelper $this, final BoxedUnit x, final BoxedUnit y) {
         $this.combine$mcV$sp(x, y);
      }

      default void combine$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         this.combine(x, y);
      }

      // $FF: synthetic method
      VectorizedReduceUFunc breeze$linalg$VectorizedReduceUFunc$VectorizeHelper$$$outer();
   }
}
