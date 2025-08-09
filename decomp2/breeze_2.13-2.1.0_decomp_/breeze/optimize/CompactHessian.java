package breeze.optimize;

import breeze.collection.mutable.RingBuffer;
import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.diag$;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.Ring$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005m3A!\u0004\b\u0001'!A!\u0005\u0001B\u0001B\u0003%1\u0005\u0003\u0005*\u0001\t\u0005\t\u0015!\u0003+\u0011!)\u0004A!A!\u0002\u0013Q\u0003\u0002\u0003\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014\t\u0011]\u0002!\u0011!Q\u0001\naBQa\u000f\u0001\u0005\u0002qBQa\u000f\u0001\u0005\u0002\tCQ\u0001\u0012\u0001\u0005\u0002\u0015CQA\u0012\u0001\u0005\u0004\u001dCQa\u0014\u0001\u0005\u0002ACQ!\u0016\u0001\u0005\u0002YC\u0001\"\u0017\u0001\t\u0006\u0004%\tA\u0017\u0002\u000f\u0007>l\u0007/Y2u\u0011\u0016\u001c8/[1o\u0015\ty\u0001#\u0001\u0005paRLW.\u001b>f\u0015\u0005\t\u0012A\u00022sK\u0016TXm\u0001\u0001\u0014\u0007\u0001!\"\u0004\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VM\u001a\t\u00047y\u0001S\"\u0001\u000f\u000b\u0005u\u0001\u0012A\u00027j]\u0006dw-\u0003\u0002 9\tQa*^7fe&\u001cw\n]:\u0011\u0005\u0005\u0002Q\"\u0001\b\u0002\u00035\u00032a\u0007\u0013'\u0013\t)CDA\u0006EK:\u001cX-T1ue&D\bCA\u000b(\u0013\tAcC\u0001\u0004E_V\u0014G.Z\u0001\u00023B\u00191\u0006\r\u001a\u000e\u00031R!!\f\u0018\u0002\u000f5,H/\u00192mK*\u0011q\u0006E\u0001\u000bG>dG.Z2uS>t\u0017BA\u0019-\u0005)\u0011\u0016N\\4Ck\u001a4WM\u001d\t\u00047M2\u0013B\u0001\u001b\u001d\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0002\u0003M\u000bQa]5h[\u0006\f\u0011!\u001c\t\u0003+eJ!A\u000f\f\u0003\u0007%sG/\u0001\u0004=S:LGO\u0010\u000b\u0007Aurt\bQ!\t\u000b\t2\u0001\u0019A\u0012\t\u000b%2\u0001\u0019\u0001\u0016\t\u000bU2\u0001\u0019\u0001\u0016\t\u000bY2\u0001\u0019\u0001\u0014\t\u000b]2\u0001\u0019\u0001\u001d\u0015\u0005\u0001\u001a\u0005\"B\u001c\b\u0001\u0004A\u0014\u0001\u0002:faJ,\u0012\u0001I\u0001\u001cG>dG.Z2uS>twJ\u001a,fGR|'o\u001d+p\u001b\u0006$(/\u001b=\u0015\u0005\rB\u0005\"B%\n\u0001\u0004Q\u0015\u0001B2pY2\u00042aS'3\u001b\u0005a%BA\u0018\u0017\u0013\tqEJA\u0002TKF\fq!\u001e9eCR,G\rF\u0002!#NCQA\u0015\u0006A\u0002I\n\u0011!\u001f\u0005\u0006)*\u0001\rAM\u0001\u0002g\u00061A\u0005^5nKN$\"AM,\t\u000ba[\u0001\u0019\u0001\u001a\u0002\u0003Y\f\u0011AT\u000b\u0002G\u0001"
)
public class CompactHessian implements NumericOps {
   private DenseMatrix N;
   private final DenseMatrix M;
   private final RingBuffer Y;
   private final RingBuffer S;
   private final double sigma;
   private final int m;
   private volatile boolean bitmap$0;

   public final Object $plus(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$plus$(this, b, op);
   }

   public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$eq$(this, b, op);
   }

   public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$plus$eq$(this, b, op);
   }

   public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$times$eq$(this, b, op);
   }

   public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$plus$eq$(this, b, op);
   }

   public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$times$eq$(this, b, op);
   }

   public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$minus$eq$(this, b, op);
   }

   public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$percent$eq$(this, b, op);
   }

   public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$percent$eq$(this, b, op);
   }

   public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$minus$eq$(this, b, op);
   }

   public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$div$eq$(this, b, op);
   }

   public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$eq$(this, b, op);
   }

   public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$div$eq$(this, b, op);
   }

   public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$less$(this, b, op);
   }

   public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$eq$(this, b, op);
   }

   public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$greater$(this, b, op);
   }

   public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$eq$(this, b, op);
   }

   public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$amp$eq$(this, b, op);
   }

   public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$bar$eq$(this, b, op);
   }

   public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$up$eq$(this, b, op);
   }

   public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$amp$eq$(this, b, op);
   }

   public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$bar$eq$(this, b, op);
   }

   public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$up$up$eq$(this, b, op);
   }

   public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
   }

   public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$colon$times$(this, b, op);
   }

   public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
   }

   public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
   }

   public final Object unary_$minus(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$minus$(this, op);
   }

   public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
   }

   public final Object $minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$(this, b, op);
   }

   public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
   }

   public final Object $percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$(this, b, op);
   }

   public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$colon$div$(this, b, op);
   }

   public final Object $div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$(this, b, op);
   }

   public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$colon$up$(this, b, op);
   }

   public final Object dot(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.dot$(this, b, op);
   }

   public final Object unary_$bang(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$bang$(this, op);
   }

   public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
   }

   public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
   }

   public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
   }

   public final Object $amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$(this, b, op);
   }

   public final Object $bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$(this, b, op);
   }

   public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$(this, b, op);
   }

   public final Object $times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$(this, b, op);
   }

   public final Object t(final CanTranspose op) {
      return ImmutableNumericOps.t$(this, op);
   }

   public Object $bslash(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bslash$(this, b, op);
   }

   public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
      return ImmutableNumericOps.t$(this, a, b, op, canSlice);
   }

   public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
      return ImmutableNumericOps.t$(this, a, op, canSlice);
   }

   public CompactHessian repr() {
      return this;
   }

   public DenseMatrix collectionOfVectorsToMatrix(final Seq coll) {
      return (DenseMatrix)DenseMatrix$.MODULE$.tabulate$mDc$sp(coll.size(), BoxesRunTime.unboxToInt(coll.headOption().map((x$1) -> BoxesRunTime.boxToInteger($anonfun$collectionOfVectorsToMatrix$1(x$1))).getOrElse((JFunction0.mcI.sp)() -> 0)), (JFunction2.mcDII.sp)(x0$1, x1$1) -> {
         Tuple2.mcII.sp var5 = new Tuple2.mcII.sp(x0$1, x1$1);
         if (var5 != null) {
            int i = ((Tuple2)var5)._1$mcI$sp();
            int j = ((Tuple2)var5)._2$mcI$sp();
            double var3 = ((DenseVector)coll.apply(i)).apply$mcD$sp(j);
            return var3;
         } else {
            throw new MatchError(var5);
         }
      }, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
   }

   public CompactHessian updated(final DenseVector y, final DenseVector s) {
      double yTs = BoxesRunTime.unboxToDouble(y.dot(s, HasOps$.MODULE$.canDotD()));
      if (yTs < 1.0E-10) {
         return this;
      } else {
         RingBuffer S = (RingBuffer)this.S.$colon$plus(s);
         RingBuffer Y = (RingBuffer)this.Y.$colon$plus(y);
         double sigma = BoxesRunTime.unboxToDouble(y.dot(y, HasOps$.MODULE$.canDotD())) / yTs;
         int k = Y.size();
         DenseMatrix D = (DenseMatrix)diag$.MODULE$.apply(DenseVector$.MODULE$.tabulate(k, (JFunction1.mcDI.sp)(i) -> BoxesRunTime.unboxToDouble(((ImmutableNumericOps)S.apply(i)).dot(Y.apply(i), HasOps$.MODULE$.canDotD())), .MODULE$.Double()), diag$.MODULE$.diagDVDMImpl(.MODULE$.Double(), Zero$.MODULE$.DoubleZero()));
         DenseMatrix L = (DenseMatrix)DenseMatrix$.MODULE$.tabulate$mDc$sp(k, k, (JFunction2.mcDII.sp)(i, j) -> i > j ? BoxesRunTime.unboxToDouble(((ImmutableNumericOps)S.apply(i)).dot(Y.apply(j), HasOps$.MODULE$.canDotD())) : (double)0.0F, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         DenseMatrix SM = this.collectionOfVectorsToMatrix(S);
         DenseMatrix STS = (DenseMatrix)((ImmutableNumericOps)SM.$times(SM.t(HasOps$.MODULE$.canTranspose_DM()), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD())).$times(BoxesRunTime.boxToDouble(sigma), HasOps$.MODULE$.op_DM_S_Double_OpMulMatrix());
         DenseMatrix M = DenseMatrix$.MODULE$.vertcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{STS, L}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero()), DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)L.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)D.unary_$minus(HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(DenseMatrix$.MODULE$.scalarOf(), Ring$.MODULE$.ringD(), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar()))}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero())}), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         CompactHessian newB = new CompactHessian(M, Y, S, sigma, this.m);
         return newB;
      }
   }

   public DenseVector $times(final DenseVector v) {
      DenseVector var10000;
      if (this.Y.size() == 0) {
         var10000 = v;
      } else {
         DenseMatrix nTv = (DenseMatrix)((ImmutableNumericOps)this.N().t(HasOps$.MODULE$.canTranspose_DM())).$times(v.toDenseMatrix$mcD$sp().t(HasOps$.MODULE$.canTranspose_DM()), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
         DenseVector u = ((DenseMatrix)this.N().$times(this.M.$bslash(nTv, HasOps$.MODULE$.impl_OpSolveMatrixBy_DMD_DMD_eq_DMD()), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD())).toDenseVector$mcD$sp();
         var10000 = (DenseVector)((ImmutableNumericOps)v.$times(BoxesRunTime.boxToDouble(this.sigma), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix())).$minus(u, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
      }

      return var10000;
   }

   private DenseMatrix N$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.N = DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)((ImmutableNumericOps)this.collectionOfVectorsToMatrix(this.S).t(HasOps$.MODULE$.canTranspose_DM())).$times(BoxesRunTime.boxToDouble(this.sigma), HasOps$.MODULE$.op_DM_S_Double_OpMulMatrix()), (DenseMatrix)this.collectionOfVectorsToMatrix(this.Y).t(HasOps$.MODULE$.canTranspose_DM())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.N;
   }

   public DenseMatrix N() {
      return !this.bitmap$0 ? this.N$lzycompute() : this.N;
   }

   // $FF: synthetic method
   public static final int $anonfun$collectionOfVectorsToMatrix$1(final DenseVector x$1) {
      return x$1.size();
   }

   public CompactHessian(final DenseMatrix M, final RingBuffer Y, final RingBuffer S, final double sigma, final int m) {
      this.M = M;
      this.Y = Y;
      this.S = S;
      this.sigma = sigma;
      this.m = m;
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
   }

   public CompactHessian(final int m) {
      this((DenseMatrix)null, new RingBuffer(m), new RingBuffer(m), (double)1.0F, m);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
