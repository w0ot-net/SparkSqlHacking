package org.apache.spark.mllib.optimization;

import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Vector;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U2A\u0001B\u0003\u0001!!)Q\u0003\u0001C\u0001-!)\u0001\u0004\u0001C!3!)\u0001\u0004\u0001C!_\t!B*Z1tiN\u000bX/\u0019:fg\u001e\u0013\u0018\rZ5f]RT!AB\u0004\u0002\u0019=\u0004H/[7ju\u0006$\u0018n\u001c8\u000b\u0005!I\u0011!B7mY&\u0014'B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0004\u0001M\u0011\u0001!\u0005\t\u0003%Mi\u0011!B\u0005\u0003)\u0015\u0011\u0001b\u0012:bI&,g\u000e^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003]\u0001\"A\u0005\u0001\u0002\u000f\r|W\u000e];uKR!!$K\u0016.!\u0011Yb\u0004\t\u0014\u000e\u0003qQ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?q\u0011a\u0001V;qY\u0016\u0014\u0004CA\u0011%\u001b\u0005\u0011#BA\u0012\b\u0003\u0019a\u0017N\\1mO&\u0011QE\t\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u0005m9\u0013B\u0001\u0015\u001d\u0005\u0019!u.\u001e2mK\")!F\u0001a\u0001A\u0005!A-\u0019;b\u0011\u0015a#\u00011\u0001'\u0003\u0015a\u0017MY3m\u0011\u0015q#\u00011\u0001!\u0003\u001d9X-[4iiN$RA\n\u00192eMBQAK\u0002A\u0002\u0001BQ\u0001L\u0002A\u0002\u0019BQAL\u0002A\u0002\u0001BQ\u0001N\u0002A\u0002\u0001\n1bY;n\u000fJ\fG-[3oi\u0002"
)
public class LeastSquaresGradient extends Gradient {
   public Tuple2 compute(final Vector data, final double label, final Vector weights) {
      double diff = BLAS$.MODULE$.dot(data, weights) - label;
      double loss = diff * diff / (double)2.0F;
      Vector gradient = data.copy();
      BLAS$.MODULE$.scal(diff, gradient);
      return new Tuple2(gradient, BoxesRunTime.boxToDouble(loss));
   }

   public double compute(final Vector data, final double label, final Vector weights, final Vector cumGradient) {
      double diff = BLAS$.MODULE$.dot(data, weights) - label;
      BLAS$.MODULE$.axpy(diff, data, cumGradient);
      return diff * diff / (double)2.0F;
   }
}
