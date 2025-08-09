package org.apache.spark.mllib.optimization;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import scala.Tuple2;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U2Aa\u0001\u0003\u0001\u001f!)A\u0003\u0001C\u0001+!)q\u0003\u0001C!1\tIA*M+qI\u0006$XM\u001d\u0006\u0003\u000b\u0019\tAb\u001c9uS6L'0\u0019;j_:T!a\u0002\u0005\u0002\u000b5dG.\u001b2\u000b\u0005%Q\u0011!B:qCJ\\'BA\u0006\r\u0003\u0019\t\u0007/Y2iK*\tQ\"A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001!A\u0011\u0011CE\u0007\u0002\t%\u00111\u0003\u0002\u0002\b+B$\u0017\r^3s\u0003\u0019a\u0014N\\5u}Q\ta\u0003\u0005\u0002\u0012\u0001\u000591m\\7qkR,GCB\r)U1r3\u0007\u0005\u0003\u001b;})S\"A\u000e\u000b\u0003q\tQa]2bY\u0006L!AH\u000e\u0003\rQ+\b\u000f\\33!\t\u00013%D\u0001\"\u0015\t\u0011c!\u0001\u0004mS:\fGnZ\u0005\u0003I\u0005\u0012aAV3di>\u0014\bC\u0001\u000e'\u0013\t93D\u0001\u0004E_V\u0014G.\u001a\u0005\u0006S\t\u0001\raH\u0001\u000bo\u0016Lw\r\u001b;t\u001f2$\u0007\"B\u0016\u0003\u0001\u0004y\u0012\u0001C4sC\u0012LWM\u001c;\t\u000b5\u0012\u0001\u0019A\u0013\u0002\u0011M$X\r]*ju\u0016DQa\f\u0002A\u0002A\nA!\u001b;feB\u0011!$M\u0005\u0003em\u00111!\u00138u\u0011\u0015!$\u00011\u0001&\u0003!\u0011Xm\u001a)be\u0006l\u0007"
)
public class L1Updater extends Updater {
   public Tuple2 compute(final Vector weightsOld, final Vector gradient, final double stepSize, final int iter, final double regParam) {
      double thisIterStepSize = stepSize / .MODULE$.sqrt((double)iter);
      breeze.linalg.Vector brzWeights = weightsOld.asBreeze().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
      breeze.linalg.package..MODULE$.axpy(BoxesRunTime.boxToDouble(-thisIterStepSize), gradient.asBreeze(), brzWeights, breeze.linalg.operators.HasOps..MODULE$.impl_scaleAdd_InPlace_V_S_V_Double());
      double shrinkageVal = regParam * thisIterStepSize;
      int i = 0;

      for(int len = brzWeights.length(); i < len; ++i) {
         double wi = brzWeights.apply$mcID$sp(i);
         brzWeights.update$mcID$sp(i, .MODULE$.signum(wi) * .MODULE$.max((double)0.0F, .MODULE$.abs(wi) - shrinkageVal));
      }

      return new Tuple2(Vectors$.MODULE$.fromBreeze(brzWeights), BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(breeze.linalg.norm..MODULE$.apply(brzWeights, BoxesRunTime.boxToDouble((double)1.0F), breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.canIterateValues_V(), breeze.linalg.norm..MODULE$.scalarNorm_Double()))) * regParam));
   }
}
