package org.apache.spark.mllib.optimization;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2qAA\u0002\u0011\u0002G\u0005a\u0002C\u0003\"\u0001\u0019\u0005!EA\u0005PaRLW.\u001b>fe*\u0011A!B\u0001\r_B$\u0018.\\5{CRLwN\u001c\u0006\u0003\r\u001d\tQ!\u001c7mS\nT!\u0001C\u0005\u0002\u000bM\u0004\u0018M]6\u000b\u0005)Y\u0011AB1qC\u000eDWMC\u0001\r\u0003\ry'oZ\u0002\u0001'\r\u0001q\"\u0006\t\u0003!Mi\u0011!\u0005\u0006\u0002%\u0005)1oY1mC&\u0011A#\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005YqbBA\f\u001d\u001d\tA2$D\u0001\u001a\u0015\tQR\"\u0001\u0004=e>|GOP\u0005\u0002%%\u0011Q$E\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0002E\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002\u001e#\u0005Aq\u000e\u001d;j[&TX\rF\u0002$S]\u0002\"\u0001J\u0014\u000e\u0003\u0015R!AJ\u0003\u0002\r1Lg.\u00197h\u0013\tASE\u0001\u0004WK\u000e$xN\u001d\u0005\u0006U\u0005\u0001\raK\u0001\u0005I\u0006$\u0018\rE\u0002-_Ej\u0011!\f\u0006\u0003]\u001d\t1A\u001d3e\u0013\t\u0001TFA\u0002S\t\u0012\u0003B\u0001\u0005\u001a5G%\u00111'\u0005\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005A)\u0014B\u0001\u001c\u0012\u0005\u0019!u.\u001e2mK\")\u0001(\u0001a\u0001G\u0005q\u0011N\\5uS\u0006dw+Z5hQR\u001c\b"
)
public interface Optimizer extends Serializable {
   Vector optimize(final RDD data, final Vector initialWeights);
}
