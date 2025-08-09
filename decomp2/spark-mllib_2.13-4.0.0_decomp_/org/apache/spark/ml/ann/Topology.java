package org.apache.spark.ml.ann;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2\u0001b\u0001\u0003\u0011\u0002G\u0005AA\u0004\u0005\u0006E\u00011\ta\t\u0005\u0006E\u00011\t\u0001\r\u0002\t)>\u0004x\u000e\\8hs*\u0011QAB\u0001\u0004C:t'BA\u0004\t\u0003\tiGN\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h'\r\u0001q\"\u0006\t\u0003!Mi\u0011!\u0005\u0006\u0002%\u0005)1oY1mC&\u0011A#\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005YybBA\f\u001e\u001d\tAB$D\u0001\u001a\u0015\tQ2$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0011\u0012B\u0001\u0010\u0012\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001I\u0011\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005y\t\u0012!B7pI\u0016dGC\u0001\u0013)!\t)c%D\u0001\u0005\u0013\t9CAA\u0007U_B|Gn\\4z\u001b>$W\r\u001c\u0005\u0006S\u0005\u0001\rAK\u0001\bo\u0016Lw\r\u001b;t!\tYc&D\u0001-\u0015\tic!\u0001\u0004mS:\fGnZ\u0005\u0003_1\u0012aAV3di>\u0014HC\u0001\u00132\u0011\u0015\u0011$\u00011\u00014\u0003\u0011\u0019X-\u001a3\u0011\u0005A!\u0014BA\u001b\u0012\u0005\u0011auN\\4"
)
public interface Topology extends Serializable {
   TopologyModel model(final Vector weights);

   TopologyModel model(final long seed);
}
