package breeze.optimize.linear;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.MatrixSingularException;
import breeze.linalg.NumericOps;
import breeze.linalg.TensorLike;
import breeze.linalg.Vector;
import breeze.linalg.any$;
import breeze.linalg.diag$;
import breeze.linalg.max$;
import breeze.linalg.package$;
import breeze.linalg.operators.HasOps$;
import breeze.math.Ring$;
import breeze.math.Semiring$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple3;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class InteriorPoint$ {
   public static final InteriorPoint$ MODULE$ = new InteriorPoint$();
   private static final double TOLERANCE = 1.0E-18;

   public double TOLERANCE() {
      return TOLERANCE;
   }

   public DenseVector minimize(final DenseMatrix A, final DenseVector b, final DenseVector c, final DenseVector x0, final double tol) {
      int m = A.rows();
      int n = A.cols();
      DenseVector x = DenseVector$.MODULE$.zeros$mDc$sp(n, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      x.$plus$eq(x0, HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
      if (((TensorLike)((ImmutableNumericOps)A.$times(x0, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$minus(b, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double())).values().exists((JFunction1.mcZD.sp)(x$1) -> x$1 > (double)0)) {
         x.$colon$eq(this.phase1(A, b, c, x0), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      DenseVector s = DenseVector$.MODULE$.ones$mDc$sp(m, .MODULE$.Double(), Semiring$.MODULE$.semiringD());
      DenseVector z = DenseVector$.MODULE$.ones$mDc$sp(m, .MODULE$.Double(), Semiring$.MODULE$.semiringD());
      boolean converged = false;
      double lastGap = Double.POSITIVE_INFINITY;

      while(!converged) {
         try {
            Tuple3 var18 = this.computeAffineScalingDir(A, b, c, x, s, z);
            if (var18 == null) {
               throw new MatchError(var18);
            }

            DenseVector zAff = (DenseVector)var18._1();
            DenseVector xAff = (DenseVector)var18._2();
            DenseVector sAff = (DenseVector)var18._3();
            Tuple3 var8 = new Tuple3(zAff, xAff, sAff);
            DenseVector zAff = (DenseVector)var8._1();
            DenseVector xAff = (DenseVector)var8._2();
            DenseVector sAff = (DenseVector)var8._3();
            double scaleX = this.lineSearch(s, sAff);
            double scaleZ = this.lineSearch(z, zAff);
            double sigma = scala.math.package..MODULE$.pow(BoxesRunTime.unboxToDouble(((ImmutableNumericOps)s.$plus(sAff.$times(BoxesRunTime.boxToDouble(scaleX), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix()), HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double())).dot(z.$plus(zAff.$times(BoxesRunTime.boxToDouble(scaleZ), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix()), HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double()), HasOps$.MODULE$.canDotD())) / BoxesRunTime.unboxToDouble(s.dot(z, HasOps$.MODULE$.canDotD())), (double)3.0F);
            Tuple3 var32 = this.computeCenteringCorrectorDir(A, b, c, x, s, z, sAff, zAff, sigma);
            if (var32 == null) {
               throw new MatchError(var32);
            }

            DenseVector zCC = (DenseVector)var32._1();
            DenseVector xCC = (DenseVector)var32._2();
            DenseVector sCC = (DenseVector)var32._3();
            Tuple3 var7 = new Tuple3(zCC, xCC, sCC);
            DenseVector zCC = (DenseVector)var7._1();
            DenseVector xCC = (DenseVector)var7._2();
            DenseVector sCC = (DenseVector)var7._3();
            DenseVector dz = (DenseVector)zAff.$plus$eq(zCC, HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
            DenseVector dx = (DenseVector)xAff.$plus$eq(xCC, HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
            DenseVector ds = (DenseVector)sAff.$plus$eq(sCC, HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
            double scaleXF = this.lineSearch(s, ds);
            double scaleZF = this.lineSearch(z, dz);
            package$.MODULE$.axpy(BoxesRunTime.boxToDouble(0.99 * scaleXF), dx, x, HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_DV_Double());
            package$.MODULE$.axpy(BoxesRunTime.boxToDouble(0.99 * scaleXF), ds, s, HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_DV_Double());
            package$.MODULE$.axpy(BoxesRunTime.boxToDouble(0.99 * scaleZF), dz, z, HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_DV_Double());
            double gap = BoxesRunTime.unboxToDouble(c.dot(x, HasOps$.MODULE$.canDotD())) + BoxesRunTime.unboxToDouble(b.dot(z, HasOps$.MODULE$.canDotD()));
            converged = scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(gap)) < tol;
            if (gap > lastGap) {
               package$.MODULE$.axpy(BoxesRunTime.boxToDouble(-(0.99 * scaleXF)), dx, x, HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_DV_Double());
            }

            lastGap = gap;
         } catch (MatrixSingularException var49) {
            converged = true;
         }
      }

      return x;
   }

   public double minimize$default$5() {
      return this.TOLERANCE();
   }

   private DenseVector phase1(final DenseMatrix A, final DenseVector b, final DenseVector c, final DenseVector x0) {
      double s = BoxesRunTime.unboxToDouble(max$.MODULE$.apply(((ImmutableNumericOps)A.$times(x0, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$minus(b, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), max$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))) + 1.0E-7;
      DenseMatrix newA = DenseMatrix$.MODULE$.zeros$mDc$sp(A.rows() + 1, A.cols() + 1, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      ((NumericOps)newA.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), A.rows()), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), A.cols()), HasOps$.MODULE$.canSliceColsAndRows())).$colon$eq(A, HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet());
      ((NumericOps)newA.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), A.rows() + 1), BoxesRunTime.boxToInteger(A.cols()), HasOps$.MODULE$.canSlicePartOfCol())).$colon$eq(BoxesRunTime.boxToDouble((double)-1.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
      DenseVector newB = DenseVector$.MODULE$.tabulate$mDc$sp(b.size() + 1, (JFunction1.mcDI.sp)(i) -> i < b.size() ? b.apply$mcD$sp(i) : (double)0.0F, .MODULE$.Double());
      DenseVector newC = DenseVector$.MODULE$.zeros$mDc$sp(c.size() + 1, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      newC.update$mcD$sp(c.size(), (double)1.0F);
      DenseVector newX = DenseVector$.MODULE$.tabulate$mDc$sp(x0.size() + 1, (JFunction1.mcDI.sp)(i) -> i < x0.size() ? x0.apply$mcD$sp(i) : s, .MODULE$.Double());
      if (BoxesRunTime.unboxToBoolean(any$.MODULE$.apply(((NumericOps)((ImmutableNumericOps)newA.$times(newX, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$minus(newB, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double())).$greater$colon$greater(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_DV_S_eq_BV_comparison_Double_OpGT()), HasOps$.MODULE$.impl_any_BV_eq_Boolean()))) {
         throw new RuntimeException("Problem seems to be infeasible!");
      } else {
         DenseVector r = this.minimize(newA, newB, newC, newX, this.minimize$default$5());
         if (r.apply$mcD$sp(x0.size()) > 1.0E-8) {
            scala.Predef..MODULE$.println((new StringBuilder(34)).append("Problem appears to be infeasible: ").append(r.apply$mcD$sp(x0.size())).toString());
         }

         return r.slice$mcD$sp(0, x0.size(), r.slice$default$3());
      }
   }

   private double lineSearch(final DenseVector x, final Vector dx) {
      double alpha;
      for(alpha = (double)1.0F; ((Vector)x.$plus(dx.$times(BoxesRunTime.boxToDouble(alpha), HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpMulMatrix()), HasOps$.MODULE$.impl_Op_DV_V_eq_V_Double_OpAdd())).valuesIterator().exists((JFunction1.mcZD.sp)(x$4) -> x$4 < (double)0); alpha *= 0.8) {
      }

      return alpha;
   }

   private Tuple3 computeAffineScalingDir(final DenseMatrix A, final DenseVector b, final DenseVector c, final DenseVector x, final DenseVector s, final DenseVector z) {
      DenseMatrix XiZ = (DenseMatrix)diag$.MODULE$.apply(z.$div$colon$div(s, HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpDiv()), diag$.MODULE$.diagDVDMImpl(.MODULE$.Double(), Zero$.MODULE$.DoubleZero()));
      DenseMatrix AtXiZ = (DenseMatrix)((ImmutableNumericOps)A.t(HasOps$.MODULE$.canTranspose_DM())).$times(XiZ, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
      DenseVector rx = (DenseVector)((ImmutableNumericOps)((NumericOps)A.$times(x, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$plus(s, HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double())).$minus(b, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
      DenseVector rz = (DenseVector)((NumericOps)((ImmutableNumericOps)A.t(HasOps$.MODULE$.canTranspose_DM())).$times(z, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$plus(c, HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double());
      DenseMatrix mat = (DenseMatrix)AtXiZ.$times(A, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
      DenseVector dx = (DenseVector)mat.$bslash(((ImmutableNumericOps)((ImmutableNumericOps)((ImmutableNumericOps)A.t(HasOps$.MODULE$.canTranspose_DM())).$times(z, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$minus(rz, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double())).$minus(AtXiZ.$times(rx, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), HasOps$.MODULE$.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD());
      DenseVector ds = (DenseVector)((ImmutableNumericOps)rx.unary_$minus(HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(DenseVector$.MODULE$.DV_scalarOf(), Ring$.MODULE$.ringD(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()))).$minus(A.$times(dx, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
      DenseVector dz = (DenseVector)((ImmutableNumericOps)z.unary_$minus(HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(DenseVector$.MODULE$.DV_scalarOf(), Ring$.MODULE$.ringD(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()))).$minus(XiZ.$times(ds, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
      return new Tuple3(dz, dx, ds);
   }

   private Tuple3 computeCenteringCorrectorDir(final DenseMatrix A, final DenseVector b, final DenseVector c, final DenseVector x, final DenseVector s, final DenseVector z, final DenseVector dsaff, final DenseVector dzaff, final double sigma) {
      int n = A.cols();
      int m = A.rows();
      DenseMatrix mat = DenseMatrix$.MODULE$.vertcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{DenseMatrix$.MODULE$.zeros$mDc$sp(m, m, .MODULE$.Double(), Zero$.MODULE$.DoubleZero()), A, DenseMatrix$.MODULE$.eye$mDc$sp(m, .MODULE$.Double(), Zero$.MODULE$.DoubleZero(), Semiring$.MODULE$.semiringD())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero()), DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)A.t(HasOps$.MODULE$.canTranspose_DM()), DenseMatrix$.MODULE$.zeros$mDc$sp(n, n + m, .MODULE$.Double(), Zero$.MODULE$.DoubleZero())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero()), DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)diag$.MODULE$.apply(s, diag$.MODULE$.diagDVDMImpl(.MODULE$.Double(), Zero$.MODULE$.DoubleZero())), DenseMatrix$.MODULE$.zeros$mDc$sp(m, n, .MODULE$.Double(), Zero$.MODULE$.DoubleZero()), (DenseMatrix)diag$.MODULE$.apply(z, diag$.MODULE$.diagDVDMImpl(.MODULE$.Double(), Zero$.MODULE$.DoubleZero()))}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero())}), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      ((NumericOps)diag$.MODULE$.apply(mat, diag$.MODULE$.diagDMDVImpl())).$plus$eq(BoxesRunTime.boxToDouble(1.0E-20), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpAdd());
      DenseVector r = DenseVector$.MODULE$.zeros$mDc$sp(m + n + m, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      r.slice$mcD$sp(m + n, m + n + m, r.slice$default$3()).$minus$eq(((ImmutableNumericOps)dsaff.$times$colon$times(dzaff, HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpMulScalar())).$minus(BoxesRunTime.boxToDouble(sigma / (double)m * BoxesRunTime.unboxToDouble(s.dot(z, HasOps$.MODULE$.canDotD()))), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpSub()), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Double());
      DenseVector sol = (DenseVector)mat.$bslash(r, HasOps$.MODULE$.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD());
      return new Tuple3(sol.slice$mcD$sp(0, m, sol.slice$default$3()), sol.slice$mcD$sp(m, n + m, sol.slice$default$3()), sol.slice$mcD$sp(n + m, n + m + m, sol.slice$default$3()));
   }

   private InteriorPoint$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
