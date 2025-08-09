package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.numerics.package$abs$absDoubleImpl$;
import breeze.numerics.package$signum$signumDoubleImpl$;
import breeze.stats.distributions.Rand$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IndexedSeqOps;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

public final class svdr$ implements UFunc {
   public static final svdr$ MODULE$ = new svdr$();

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

   public svd.SVD breeze$linalg$svdr$$doSVDR_Double(final DenseMatrix M, final int k, final int nOversamples, final int nIter) {
      boolean cond$macro$1 = k <= .MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(M.rows()), M.cols());
      if (!cond$macro$1) {
         throw new IllegalArgumentException("requirement failed: Number of singular values should be less than min(M.rows, M.cols): k.<=(scala.Predef.intWrapper(M.rows).min(M.cols))");
      } else {
         int nRandom = k + nOversamples;
         DenseMatrix Q = this.randomizedStateFinder(M, nRandom, nIter);
         DenseMatrix b = (DenseMatrix)((ImmutableNumericOps)Q.t(HasOps$.MODULE$.canTranspose_DM())).$times(M, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
         svd.SVD var12 = (svd.SVD)svd.reduced$.MODULE$.apply(b, svd$reduced$reduced_Svd_DM_Impl$.MODULE$);
         if (var12 != null) {
            DenseMatrix w2 = (DenseMatrix)var12.leftVectors();
            DenseVector _s = (DenseVector)var12.singularValues();
            DenseMatrix _v = (DenseMatrix)var12.rightVectors();
            Tuple3 var6 = new Tuple3(w2, _s, _v);
            DenseMatrix w2 = (DenseMatrix)var6._1();
            DenseVector _s = (DenseVector)var6._2();
            DenseMatrix _v = (DenseMatrix)var6._3();
            DenseMatrix _u = (DenseMatrix)Q.$times(w2, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
            Tuple2 var21 = this.flipSVDSigns(_u, _v);
            if (var21 != null) {
               DenseMatrix u = (DenseMatrix)var21._1();
               DenseMatrix v = (DenseMatrix)var21._2();
               Tuple2 var5 = new Tuple2(u, v);
               DenseMatrix u = (DenseMatrix)var5._1();
               DenseMatrix v = (DenseMatrix)var5._2();
               return new svd.SVD(u.apply(scala.package..MODULE$.$colon$colon(), .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), k), HasOps$.MODULE$.canSliceCols()), _s.apply(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), k), HasOps$.MODULE$.canSlice_DV_Range_eq_DV()), v.apply(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), k), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()));
            } else {
               throw new MatchError(var21);
            }
         } else {
            throw new MatchError(var12);
         }
      }
   }

   private int doSVDR_Double$default$3() {
      return 10;
   }

   private int doSVDR_Double$default$4() {
      return 0;
   }

   private DenseMatrix randomizedStateFinder(final DenseMatrix M, final int size, final int nIter) {
      DenseMatrix R = (DenseMatrix)DenseMatrix$.MODULE$.rand(M.cols(), size, Rand$.MODULE$.gaussian(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseMatrix Y = (DenseMatrix)M.$times(R, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
      int index$macro$2 = 0;

      for(int limit$macro$4 = nIter; index$macro$2 < limit$macro$4; ++index$macro$2) {
         Y.$colon$eq(M.$times(((ImmutableNumericOps)M.t(HasOps$.MODULE$.canTranspose_DM())).$times(Y, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD()), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD()), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet());
      }

      DenseMatrix q = (DenseMatrix)qr$reduced$justQ$.MODULE$.apply(Y, qr$reduced$justQ$.MODULE$.canJustQIfWeCanQR(qr$reduced$impl_reduced_DM_Double$.MODULE$));
      return q;
   }

   private Tuple2 flipSVDSigns(final DenseMatrix u, final DenseMatrix v) {
      DenseMatrix abs_u = (DenseMatrix)breeze.numerics.package.abs$.MODULE$.apply(u, HasOps$.MODULE$.fromLowOrderCanMapActiveValues(DenseMatrix$.MODULE$.scalarOf(), package$abs$absDoubleImpl$.MODULE$, HasOps$.MODULE$.canMapValues_DM$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double())));
      IndexedSeq max_abs_cols = .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), u.cols()).map((JFunction1.mcII.sp)(c) -> BoxesRunTime.unboxToInt(argmax$.MODULE$.apply(abs_u.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(c), HasOps$.MODULE$.canSliceCol()), argmax$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canTraverseKeyValuePairs()))));
      IndexedSeq signs = (IndexedSeq)((IndexedSeqOps)max_abs_cols.zipWithIndex()).map((e) -> BoxesRunTime.boxToDouble($anonfun$flipSVDSigns$2(u, e)));
      ((IterableOnceOps)signs.zipWithIndex()).foreach((s) -> {
         ((NumericOps)u.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(s._2$mcI$sp()), HasOps$.MODULE$.canSliceCol())).$colon$times$eq(BoxesRunTime.boxToDouble(s._1$mcD$sp()), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
         return (Transpose)((NumericOps)v.apply(BoxesRunTime.boxToInteger(s._2$mcI$sp()), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRow())).$colon$times$eq(BoxesRunTime.boxToDouble(s._1$mcD$sp()), HasOps$.MODULE$.impl_Op_InPlace_Tt_S_from_T_S(DenseVector$.MODULE$.DV_scalarOf(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar()));
      });
      return new Tuple2(u, v);
   }

   // $FF: synthetic method
   public static final double $anonfun$flipSVDSigns$2(final DenseMatrix u$1, final Tuple2 e) {
      return breeze.numerics.package.signum$.MODULE$.apply$mDDc$sp(u$1.apply$mcD$sp(e._1$mcI$sp(), e._2$mcI$sp()), package$signum$signumDoubleImpl$.MODULE$);
   }

   private svdr$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
