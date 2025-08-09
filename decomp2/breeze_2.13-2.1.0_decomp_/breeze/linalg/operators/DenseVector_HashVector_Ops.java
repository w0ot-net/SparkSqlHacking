package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.HashVector;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A4qAC\u0006\u0011\u0002\u0007\u0005!\u0003C\u0003$\u0001\u0011\u0005A\u0005C\u0004)\u0001\t\u0007I1A\u0015\t\u000fy\u0002!\u0019!C\u0002\u007f!9a\t\u0001b\u0001\n\u00079\u0005b\u0002(\u0001\u0005\u0004%\u0019a\u0014\u0005\b-\u0002\u0011\r\u0011b\u0001X\u0011\u001d9\u0007A1A\u0005\u0004!DqA\u001b\u0001C\u0002\u0013\r1\u000eC\u0004n\u0001\t\u0007I1\u00018\u00035\u0011+gn]3WK\u000e$xN]0ICNDg+Z2u_J|v\n]:\u000b\u00051i\u0011!C8qKJ\fGo\u001c:t\u0015\tqq\"\u0001\u0004mS:\fGn\u001a\u0006\u0002!\u00051!M]3fu\u0016\u001c\u0001aE\u0003\u0001'ei\u0002\u0005\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00035mi\u0011aC\u0005\u00039-\u0011!bR3oKJL7m\u00149t!\tQb$\u0003\u0002 \u0017\tqA)\u001a8tKZ+7\r^8s\u001fB\u001c\bC\u0001\u000e\"\u0013\t\u00113BA\nICNDg+Z2u_J,\u0005\u0010]1oI>\u00038/\u0001\u0004%S:LG\u000f\n\u000b\u0002KA\u0011ACJ\u0005\u0003OU\u0011A!\u00168ji\u0006\t\u0013.\u001c9m?N\u001c\u0017\r\\3BI\u0012|\u0016J\u001c)mC\u000e,w\f\u0012,`)~CekX%oiV\t!\u0006E\u0003,_UB4H\u0004\u0002-[5\tQ\"\u0003\u0002/\u001b\u0005A1oY1mK\u0006#G-\u0003\u00021c\ta\u0011J\u001c)mC\u000e,\u0017*\u001c9mg%\u0011!g\r\u0002\u0006+\u001a+hn\u0019\u0006\u0003i=\tqaZ3oKJL7\rE\u0002-maJ!aN\u0007\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u0003)eJ!AO\u000b\u0003\u0007%sG\u000fE\u0002-yaJ!!P\u0007\u0003\u0015!\u000b7\u000f\u001b,fGR|'/\u0001\u0013j[Bdwl]2bY\u0016\fE\rZ0J]Bc\u0017mY3`\tZ{Fk\u0018%W?\u0012{WO\u00197f+\u0005\u0001\u0005#B\u00160\u0003\n+\u0005c\u0001\u00177\u0005B\u0011AcQ\u0005\u0003\tV\u0011a\u0001R8vE2,\u0007c\u0001\u0017=\u0005\u0006\u0019\u0013.\u001c9m?N\u001c\u0017\r\\3BI\u0012|\u0016J\u001c)mC\u000e,w\f\u0012,`)~Cek\u0018$m_\u0006$X#\u0001%\u0011\u000b-z\u0013JS'\u0011\u000712$\n\u0005\u0002\u0015\u0017&\u0011A*\u0006\u0002\u0006\r2|\u0017\r\u001e\t\u0004YqR\u0015AI5na2|6oY1mK\u0006#GmX%o!2\f7-Z0E-~#v\f\u0013,`\u0019>tw-F\u0001Q!\u0015Ys&\u0015*V!\racG\u0015\t\u0003)MK!\u0001V\u000b\u0003\t1{gn\u001a\t\u0004Yq\u0012\u0016AH5na2|v\n]'vY&sg.\u001a:`\tZ{\u0006JV0fc~\u001bv,\u00138u+\u0005A\u0006#B-fkmBdB\u0001.d\u001d\tY&M\u0004\u0002]C:\u0011Q\fY\u0007\u0002=*\u0011q,E\u0001\u0007yI|w\u000e\u001e \n\u0003AI!AD\b\n\u00051i\u0011B\u00013\f\u0003)y\u0005/T;m\u0013:tWM]\u0005\u0003MF\u0012Q!S7qYJ\n\u0011%[7qY~{\u0005/T;m\u0013:tWM]0E-~CekX3r?N{Fi\\;cY\u0016,\u0012!\u001b\t\u00063\u0016\fUIQ\u0001!S6\u0004HnX(q\u001bVd\u0017J\u001c8fe~#ek\u0018%W?\u0016\fxlU0GY>\fG/F\u0001m!\u0015IV-S'K\u0003}IW\u000e\u001d7`\u001fBlU\u000f\\%o]\u0016\u0014x\f\u0012,`\u0011Z{V-]0T?2{gnZ\u000b\u0002_B)\u0011,Z)V%\u0002"
)
public interface DenseVector_HashVector_Ops extends DenseVectorOps, HashVectorExpandOps {
   void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Int_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Double_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Float_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Long_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Long_$eq(final UFunc.UImpl2 x$1);

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Int();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Double();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Float();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_T_HV_Long();

   UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Int();

   UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Double();

   UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Float();

   UFunc.UImpl2 impl_OpMulInner_DV_HV_eq_S_Long();

   static void $init$(final DenseVector_HashVector_Ops $this) {
      $this.breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Int_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseVector dv, final int scalar, final HashVector hv) {
            int left$macro$1 = dv.length();
            int right$macro$2 = hv.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Vectors must have the same length: ").append("dv.length == hv.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = dv.data$mcI$sp();
               int[] bd = hv.data$mcI$sp();
               int[] bi = hv.index();
               int bsize = hv.iterableSize();
               if (scalar != 0) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = bsize; index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int aoff = dv.offset() + bi[index$macro$4] * dv.stride();
                     if (hv.isActive(index$macro$4)) {
                        ad[aoff] += scalar * bd[index$macro$4];
                     }
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry).MODULE$.implicitly(DenseVector_HashVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Double_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseVector dv, final double scalar, final HashVector hv) {
            int left$macro$1 = dv.length();
            int right$macro$2 = hv.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Vectors must have the same length: ").append("dv.length == hv.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = dv.data$mcD$sp();
               double[] bd = hv.data$mcD$sp();
               int[] bi = hv.index();
               int bsize = hv.iterableSize();
               if (scalar != (double)0) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = bsize; index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int aoff = dv.offset() + bi[index$macro$4] * dv.stride();
                     if (hv.isActive(index$macro$4)) {
                        ad[aoff] += scalar * bd[index$macro$4];
                     }
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry).MODULE$.implicitly(DenseVector_HashVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Float_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseVector dv, final float scalar, final HashVector hv) {
            int left$macro$1 = dv.length();
            int right$macro$2 = hv.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Vectors must have the same length: ").append("dv.length == hv.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = dv.data$mcF$sp();
               float[] bd = hv.data$mcF$sp();
               int[] bi = hv.index();
               int bsize = hv.iterableSize();
               if (scalar != (float)0) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = bsize; index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int aoff = dv.offset() + bi[index$macro$4] * dv.stride();
                     if (hv.isActive(index$macro$4)) {
                        ad[aoff] += scalar * bd[index$macro$4];
                     }
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry).MODULE$.implicitly(DenseVector_HashVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.Float(), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_scaleAdd_InPlace_DV_T_HV_Long_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseVector dv, final long scalar, final HashVector hv) {
            int left$macro$1 = dv.length();
            int right$macro$2 = hv.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Vectors must have the same length: ").append("dv.length == hv.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = dv.data$mcJ$sp();
               long[] bd = hv.data$mcJ$sp();
               int[] bi = hv.index();
               int bsize = hv.iterableSize();
               if (scalar != 0L) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = bsize; index$macro$4 < limit$macro$6; ++index$macro$4) {
                     int aoff = dv.offset() + bi[index$macro$4] * dv.stride();
                     if (hv.isActive(index$macro$4)) {
                        ad[aoff] += scalar * bd[index$macro$4];
                     }
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry).MODULE$.implicitly(DenseVector_HashVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.Long(), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Int_$eq(new UFunc.UImpl2() {
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

         public int apply(final DenseVector a, final HashVector b) {
            int result = 0;
            int[] bd = b.data$mcI$sp();
            int[] bi = b.index();
            int bsize = b.iterableSize();
            int[] adata = a.data$mcI$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int index$macro$2 = 0;

            for(int limit$macro$4 = bsize; index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (b.isActive(index$macro$2)) {
                  result += adata[aoff + bi[index$macro$2] * stride] * bd[index$macro$2];
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_HashVector_Ops.this.impl_OpMulInner_V_V_eq_S_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Double_$eq(new UFunc.UImpl2() {
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

         public double apply(final DenseVector a, final HashVector b) {
            double result = (double)0.0F;
            double[] bd = b.data$mcD$sp();
            int[] bi = b.index();
            int bsize = b.iterableSize();
            double[] adata = a.data$mcD$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int index$macro$2 = 0;

            for(int limit$macro$4 = bsize; index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (b.isActive(index$macro$2)) {
                  result += adata[aoff + bi[index$macro$2] * stride] * bd[index$macro$2];
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_HashVector_Ops.this.impl_OpMulInner_V_V_eq_S_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Float_$eq(new UFunc.UImpl2() {
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

         public float apply(final DenseVector a, final HashVector b) {
            float result = 0.0F;
            float[] bd = b.data$mcF$sp();
            int[] bi = b.index();
            int bsize = b.iterableSize();
            float[] adata = a.data$mcF$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int index$macro$2 = 0;

            for(int limit$macro$4 = bsize; index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (b.isActive(index$macro$2)) {
                  result += adata[aoff + bi[index$macro$2] * stride] * bd[index$macro$2];
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_HashVector_Ops.this.impl_OpMulInner_V_V_eq_S_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_HashVector_Ops$_setter_$impl_OpMulInner_DV_HV_eq_S_Long_$eq(new UFunc.UImpl2() {
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

         public long apply(final DenseVector a, final HashVector b) {
            long result = 0L;
            long[] bd = b.data$mcJ$sp();
            int[] bi = b.index();
            int bsize = b.iterableSize();
            long[] adata = a.data$mcJ$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int index$macro$2 = 0;

            for(int limit$macro$4 = bsize; index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (b.isActive(index$macro$2)) {
                  result += adata[aoff + bi[index$macro$2] * stride] * bd[index$macro$2];
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_HashVector_Ops.this.impl_OpMulInner_V_V_eq_S_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
   }
}
