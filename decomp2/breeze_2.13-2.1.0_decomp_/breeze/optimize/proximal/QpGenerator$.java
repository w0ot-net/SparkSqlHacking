package breeze.optimize.proximal;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.NumericOps;
import breeze.linalg.operators.HasOps$;
import breeze.math.Semiring$;
import breeze.stats.distributions.Rand$;
import breeze.storage.Zero$;
import scala.Tuple6;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

public final class QpGenerator$ {
   public static final QpGenerator$ MODULE$ = new QpGenerator$();

   public DenseMatrix getGram(final int nGram) {
      DenseMatrix hrand = (DenseMatrix)DenseMatrix$.MODULE$.rand(nGram, nGram, Rand$.MODULE$.gaussian((double)0.0F, (double)1.0F), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseMatrix hrandt = (DenseMatrix)hrand.t(HasOps$.MODULE$.canTranspose_DM());
      DenseMatrix hposdef = (DenseMatrix)hrandt.$times(hrand, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
      DenseMatrix H = (DenseMatrix)((NumericOps)hposdef.t(HasOps$.MODULE$.canTranspose_DM())).$plus(hposdef, HasOps$.MODULE$.op_DM_DM_Double_OpAdd());
      return H;
   }

   public Tuple6 apply(final int nGram, final int nEqualities) {
      DenseVector en = DenseVector$.MODULE$.ones$mDc$sp(nGram, .MODULE$.Double(), Semiring$.MODULE$.semiringD());
      DenseVector zn = DenseVector$.MODULE$.zeros$mDc$sp(nGram, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseMatrix A = (DenseMatrix)DenseMatrix$.MODULE$.rand(nEqualities, nGram, DenseMatrix$.MODULE$.rand$default$3(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector b = (DenseVector)A.$times(en, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD());
      DenseVector q = (DenseVector)DenseVector$.MODULE$.rand(nGram, DenseVector$.MODULE$.rand$default$2(), .MODULE$.Double());
      DenseVector lb = zn.copy$mcD$sp();
      DenseVector ub = (DenseVector)en.$times$colon$times(BoxesRunTime.boxToDouble((double)10.0F), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar());
      DenseMatrix H = this.getGram(nGram);
      return new Tuple6(A, b, lb, ub, q, H);
   }

   private QpGenerator$() {
   }
}
