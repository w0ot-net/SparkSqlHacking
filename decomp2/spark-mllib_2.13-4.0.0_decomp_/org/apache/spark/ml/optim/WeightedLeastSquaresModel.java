package org.apache.spark.ml.optim;

import java.io.Serializable;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.BLAS.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3Qa\u0003\u0007\u0001\u001dYA\u0001B\u000b\u0001\u0003\u0006\u0004%\ta\u000b\u0005\te\u0001\u0011\t\u0011)A\u0005Y!A1\u0007\u0001BC\u0002\u0013\u0005A\u0007\u0003\u00059\u0001\t\u0005\t\u0015!\u00036\u0011!I\u0004A!b\u0001\n\u0003Y\u0003\u0002\u0003\u001e\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0017\t\u0011m\u0002!Q1A\u0005\u0002qB\u0001\u0002\u0011\u0001\u0003\u0002\u0003\u0006I!\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\u0013\u0002!\tA\u0013\u0002\u001a/\u0016Lw\r\u001b;fI2+\u0017m\u001d;TcV\f'/Z:N_\u0012,GN\u0003\u0002\u000e\u001d\u0005)q\u000e\u001d;j[*\u0011q\u0002E\u0001\u0003[2T!!\u0005\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005M!\u0012AB1qC\u000eDWMC\u0001\u0016\u0003\ry'oZ\n\u0004\u0001]i\u0002C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"AB!osJ+g\r\u0005\u0002\u001fO9\u0011q$\n\b\u0003A\u0011j\u0011!\t\u0006\u0003E\r\na\u0001\u0010:p_Rt4\u0001A\u0005\u00025%\u0011a%G\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0013F\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002'3\u0005a1m\\3gM&\u001c\u0017.\u001a8ugV\tA\u0006\u0005\u0002.a5\taF\u0003\u00020\u001d\u00051A.\u001b8bY\u001eL!!\r\u0018\u0003\u0017\u0011+gn]3WK\u000e$xN]\u0001\u000eG>,gMZ5dS\u0016tGo\u001d\u0011\u0002\u0013%tG/\u001a:dKB$X#A\u001b\u0011\u0005a1\u0014BA\u001c\u001a\u0005\u0019!u.\u001e2mK\u0006Q\u0011N\u001c;fe\u000e,\u0007\u000f\u001e\u0011\u0002\u0017\u0011L\u0017mZ%om\u0006#x+Q\u0001\rI&\fw-\u00138w\u0003R<\u0016\tI\u0001\u0011_\nTWm\u0019;jm\u0016D\u0015n\u001d;pef,\u0012!\u0010\t\u00041y*\u0014BA \u001a\u0005\u0015\t%O]1z\u0003Ey'M[3di&4X\rS5ti>\u0014\u0018\u0010I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b\r+ei\u0012%\u0011\u0005\u0011\u0003Q\"\u0001\u0007\t\u000b)J\u0001\u0019\u0001\u0017\t\u000bMJ\u0001\u0019A\u001b\t\u000beJ\u0001\u0019\u0001\u0017\t\u000bmJ\u0001\u0019A\u001f\u0002\u000fA\u0014X\rZ5diR\u0011Qg\u0013\u0005\u0006\u0019*\u0001\r!T\u0001\tM\u0016\fG/\u001e:fgB\u0011QFT\u0005\u0003\u001f:\u0012aAV3di>\u0014\b"
)
public class WeightedLeastSquaresModel implements Serializable {
   private final DenseVector coefficients;
   private final double intercept;
   private final DenseVector diagInvAtWA;
   private final double[] objectiveHistory;

   public DenseVector coefficients() {
      return this.coefficients;
   }

   public double intercept() {
      return this.intercept;
   }

   public DenseVector diagInvAtWA() {
      return this.diagInvAtWA;
   }

   public double[] objectiveHistory() {
      return this.objectiveHistory;
   }

   public double predict(final Vector features) {
      return .MODULE$.dot(this.coefficients(), features) + this.intercept();
   }

   public WeightedLeastSquaresModel(final DenseVector coefficients, final double intercept, final DenseVector diagInvAtWA, final double[] objectiveHistory) {
      this.coefficients = coefficients;
      this.intercept = intercept;
      this.diagInvAtWA = diagInvAtWA;
      this.objectiveHistory = objectiveHistory;
   }
}
