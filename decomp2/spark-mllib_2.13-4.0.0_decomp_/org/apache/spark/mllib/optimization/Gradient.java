package org.apache.spark.mllib.optimization;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053Q\u0001B\u0003\u0002\u0002AAQa\t\u0001\u0005\u0002\u0011BQa\n\u0001\u0005\u0002!BQa\n\u0001\u0007\u0002m\u0012\u0001b\u0012:bI&,g\u000e\u001e\u0006\u0003\r\u001d\tAb\u001c9uS6L'0\u0019;j_:T!\u0001C\u0005\u0002\u000b5dG.\u001b2\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c\u0001aE\u0002\u0001#]\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007C\u0001\r!\u001d\tIbD\u0004\u0002\u001b;5\t1D\u0003\u0002\u001d\u001f\u00051AH]8pizJ\u0011\u0001F\u0005\u0003?M\tq\u0001]1dW\u0006<W-\u0003\u0002\"E\ta1+\u001a:jC2L'0\u00192mK*\u0011qdE\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0015\u0002\"A\n\u0001\u000e\u0003\u0015\tqaY8naV$X\r\u0006\u0003*k]J\u0004\u0003\u0002\n+YIJ!aK\n\u0003\rQ+\b\u000f\\33!\ti\u0003'D\u0001/\u0015\tys!\u0001\u0004mS:\fGnZ\u0005\u0003c9\u0012aAV3di>\u0014\bC\u0001\n4\u0013\t!4C\u0001\u0004E_V\u0014G.\u001a\u0005\u0006m\t\u0001\r\u0001L\u0001\u0005I\u0006$\u0018\rC\u00039\u0005\u0001\u0007!'A\u0003mC\n,G\u000eC\u0003;\u0005\u0001\u0007A&A\u0004xK&<\u0007\u000e^:\u0015\u000bIbTHP \t\u000bY\u001a\u0001\u0019\u0001\u0017\t\u000ba\u001a\u0001\u0019\u0001\u001a\t\u000bi\u001a\u0001\u0019\u0001\u0017\t\u000b\u0001\u001b\u0001\u0019\u0001\u0017\u0002\u0017\r,Xn\u0012:bI&,g\u000e\u001e"
)
public abstract class Gradient implements Serializable {
   public Tuple2 compute(final Vector data, final double label, final Vector weights) {
      Vector gradient = Vectors$.MODULE$.zeros(weights.size());
      double loss = this.compute(data, label, weights, gradient);
      return new Tuple2(gradient, BoxesRunTime.boxToDouble(loss));
   }

   public abstract double compute(final Vector data, final double label, final Vector weights, final Vector cumGradient);
}
