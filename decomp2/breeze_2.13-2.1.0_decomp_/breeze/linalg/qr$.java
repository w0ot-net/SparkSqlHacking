package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.storage.Zero$;
import dev.ludovic.netlib.lapack.LAPACK;
import org.netlib.util.intW;
import scala.Tuple2;
import scala.math.package.;

public final class qr$ implements UFunc {
   public static final qr$ MODULE$ = new qr$();

   static {
      UFunc.$init$(MODULE$);
   }

   public final Object apply(final Object v, final UFunc.UImpl impl) {
      return UFunc.apply$(this, v, impl);
   }

   public final double apply$mDDc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDDc$sp$(this, v, impl);
   }

   public final float apply$mDFc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDFc$sp$(this, v, impl);
   }

   public final int apply$mDIc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDIc$sp$(this, v, impl);
   }

   public final double apply$mFDc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFDc$sp$(this, v, impl);
   }

   public final float apply$mFFc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFFc$sp$(this, v, impl);
   }

   public final int apply$mFIc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFIc$sp$(this, v, impl);
   }

   public final double apply$mIDc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIDc$sp$(this, v, impl);
   }

   public final float apply$mIFc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIFc$sp$(this, v, impl);
   }

   public final int apply$mIIc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIIc$sp$(this, v, impl);
   }

   public final Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$(this, v1, v2, impl);
   }

   public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIIc$sp$(this, v1, v2, impl);
   }

   public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$(this, v1, v2, v3, impl);
   }

   public final double apply$mDDDc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDDFc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDDIc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mDFDc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDFFc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDFIc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mDIDc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDIFc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDIIc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFDDc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFDFc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFDIc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFFDc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFFFc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFFIc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFIDc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFIFc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFIIc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIDDc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIDFc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIDIc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIFDc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIFFc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIFIc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIIDc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIIFc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIIIc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIIc$sp$(this, v1, v2, v3, impl);
   }

   public final Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return UFunc.apply$(this, v1, v2, v3, v4, impl);
   }

   public final Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return UFunc.inPlace$(this, v, impl);
   }

   public final Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return UFunc.inPlace$(this, v, v2, impl);
   }

   public final Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return UFunc.inPlace$(this, v, v2, v3, impl);
   }

   public final Object withSink(final Object s) {
      return UFunc.withSink$(this, s);
   }

   public Tuple2 breeze$linalg$qr$$doQr(final DenseMatrix M, final boolean skipQ, final QRMode mode) {
      DenseMatrix A = M.copy$mcD$sp();
      int m = A.rows();
      int n = A.cols();
      int mn = .MODULE$.min(m, n);
      double[] tau = new double[mn];
      double[] work = new double[1];
      intW info = new intW(0);
      LAPACK.getInstance().dgeqrf(m, n, A.data$mcD$sp(), m, tau, work, -1, info);
      int lwork = info.val != 0 ? n : (int)work[0];
      double[] workspace = new double[lwork];
      LAPACK.getInstance().dgeqrf(m, n, A.data$mcD$sp(), m, tau, workspace, lwork, info);
      if (info.val > 0) {
         throw new NotConvergedException(NotConvergedException.Iterations$.MODULE$, NotConvergedException$.MODULE$.$lessinit$greater$default$2());
      } else if (info.val < 0) {
         throw new IllegalArgumentException();
      } else {
         Tuple2 var10000;
         if (skipQ) {
            int index$macro$7 = 0;

            for(int limit$macro$9 = mn; index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = min$.MODULE$.apply$mIIIc$sp(index$macro$7, A.cols(), min$.MODULE$.minImpl2_Int()); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  A.update$mcD$sp(index$macro$7, index$macro$2, (double)0.0F);
               }
            }

            var10000 = new Tuple2((Object)null, A.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), mn), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()));
         } else {
            label84: {
               label83: {
                  CompleteQR$ var18 = CompleteQR$.MODULE$;
                  if (mode == null) {
                     if (var18 != null) {
                        break label83;
                     }
                  } else if (!mode.equals(var18)) {
                     break label83;
                  }

                  if (m > n) {
                     var27 = DenseMatrix$.MODULE$.zeros$mDc$sp(m, m, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                     break label84;
                  }
               }

               var27 = DenseMatrix$.MODULE$.zeros$mDc$sp(m, n, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            }

            DenseMatrix Q;
            label75: {
               label74: {
                  Q = var27;
                  CompleteQR$ var20 = CompleteQR$.MODULE$;
                  if (mode == null) {
                     if (var20 != null) {
                        break label74;
                     }
                  } else if (!mode.equals(var20)) {
                     break label74;
                  }

                  if (m > n) {
                     var28 = m;
                     break label75;
                  }
               }

               var28 = mn;
            }

            int mc = var28;
            ((NumericOps)Q.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n), HasOps$.MODULE$.canSliceCols())).$colon$eq(A, HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet());
            LAPACK.getInstance().dorgqr(m, mc, mn, Q.data$mcD$sp(), m, tau, work, -1, info);
            int lwork1 = info.val != 0 ? n : (int)work[0];
            workspace = new double[lwork1];
            LAPACK.getInstance().dorgqr(m, mc, mn, Q.data$mcD$sp(), m, tau, workspace, lwork1, info);
            if (info.val > 0) {
               throw new NotConvergedException(NotConvergedException.Iterations$.MODULE$, NotConvergedException$.MODULE$.$lessinit$greater$default$2());
            }

            if (info.val < 0) {
               throw new IllegalArgumentException();
            }

            int index$macro$17 = 0;

            for(int limit$macro$19 = mc; index$macro$17 < limit$macro$19; ++index$macro$17) {
               int index$macro$12 = 0;

               for(int limit$macro$14 = min$.MODULE$.apply$mIIIc$sp(index$macro$17, A.cols(), min$.MODULE$.minImpl2_Int()); index$macro$12 < limit$macro$14; ++index$macro$12) {
                  A.update$mcD$sp(index$macro$17, index$macro$12, (double)0.0F);
               }
            }

            var10000 = new Tuple2(Q.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), mc), HasOps$.MODULE$.canSliceCols()), A.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), mc), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()));
         }

         return var10000;
      }
   }

   public Tuple2 breeze$linalg$qr$$doQr_Float(final DenseMatrix M, final boolean skipQ, final QRMode mode) {
      DenseMatrix A = M.copy$mcF$sp();
      int m = A.rows();
      int n = A.cols();
      int mn = .MODULE$.min(m, n);
      float[] tau = new float[mn];
      float[] work = new float[1];
      intW info = new intW(0);
      LAPACK.getInstance().sgeqrf(m, n, A.data$mcF$sp(), m, tau, work, -1, info);
      int lwork = info.val != 0 ? n : (int)work[0];
      float[] workspace = new float[lwork];
      LAPACK.getInstance().sgeqrf(m, n, A.data$mcF$sp(), m, tau, workspace, lwork, info);
      if (info.val > 0) {
         throw new NotConvergedException(NotConvergedException.Iterations$.MODULE$, NotConvergedException$.MODULE$.$lessinit$greater$default$2());
      } else if (info.val < 0) {
         throw new IllegalArgumentException();
      } else {
         Tuple2 var10000;
         if (skipQ) {
            int index$macro$7 = 0;

            for(int limit$macro$9 = mn; index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = min$.MODULE$.apply$mIIIc$sp(index$macro$7, A.cols(), min$.MODULE$.minImpl2_Int()); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  A.update$mcF$sp(index$macro$7, index$macro$2, 0.0F);
               }
            }

            var10000 = new Tuple2((Object)null, A.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), mn), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()));
         } else {
            label84: {
               label83: {
                  CompleteQR$ var18 = CompleteQR$.MODULE$;
                  if (mode == null) {
                     if (var18 != null) {
                        break label83;
                     }
                  } else if (!mode.equals(var18)) {
                     break label83;
                  }

                  if (m > n) {
                     var27 = DenseMatrix$.MODULE$.zeros$mFc$sp(m, m, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
                     break label84;
                  }
               }

               var27 = DenseMatrix$.MODULE$.zeros$mFc$sp(m, n, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            }

            DenseMatrix Q;
            label75: {
               label74: {
                  Q = var27;
                  CompleteQR$ var20 = CompleteQR$.MODULE$;
                  if (mode == null) {
                     if (var20 != null) {
                        break label74;
                     }
                  } else if (!mode.equals(var20)) {
                     break label74;
                  }

                  if (m > n) {
                     var28 = m;
                     break label75;
                  }
               }

               var28 = mn;
            }

            int mc = var28;
            ((NumericOps)Q.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n), HasOps$.MODULE$.canSliceCols())).$colon$eq(A, HasOps$.MODULE$.dm_dm_UpdateOp_Float_OpSet());
            LAPACK.getInstance().sorgqr(m, mc, mn, Q.data$mcF$sp(), m, tau, work, -1, info);
            int lwork1 = info.val != 0 ? n : (int)work[0];
            workspace = new float[lwork1];
            LAPACK.getInstance().sorgqr(m, mc, mn, Q.data$mcF$sp(), m, tau, workspace, lwork1, info);
            if (info.val > 0) {
               throw new NotConvergedException(NotConvergedException.Iterations$.MODULE$, NotConvergedException$.MODULE$.$lessinit$greater$default$2());
            }

            if (info.val < 0) {
               throw new IllegalArgumentException();
            }

            int index$macro$17 = 0;

            for(int limit$macro$19 = mc; index$macro$17 < limit$macro$19; ++index$macro$17) {
               int index$macro$12 = 0;

               for(int limit$macro$14 = min$.MODULE$.apply$mIIIc$sp(index$macro$17, A.cols(), min$.MODULE$.minImpl2_Int()); index$macro$12 < limit$macro$14; ++index$macro$12) {
                  A.update$mcF$sp(index$macro$17, index$macro$12, 0.0F);
               }
            }

            var10000 = new Tuple2(Q.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), mc), HasOps$.MODULE$.canSliceCols()), A.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), mc), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()));
         }

         return var10000;
      }
   }

   private qr$() {
   }
}
