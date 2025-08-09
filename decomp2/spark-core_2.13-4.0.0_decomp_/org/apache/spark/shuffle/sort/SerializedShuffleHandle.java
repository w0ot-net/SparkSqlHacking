package org.apache.spark.shuffle.sort;

import org.apache.spark.ShuffleDependency;
import org.apache.spark.shuffle.BaseShuffleHandle;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2Q\u0001B\u0003\u0001\u0013=A\u0011b\n\u0001\u0003\u0002\u0003\u0006I\u0001K\u0016\t\u00139\u0002!\u0011!Q\u0001\n=\u001a\u0004\"\u0002\u001b\u0001\t\u0003)$aF*fe&\fG.\u001b>fINCWO\u001a4mK\"\u000bg\u000e\u001a7f\u0015\t1q!\u0001\u0003t_J$(B\u0001\u0005\n\u0003\u001d\u0019\b.\u001e4gY\u0016T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\u000b\u0004!])3C\u0001\u0001\u0012!\u0015\u00112#\u0006\u0013%\u001b\u00059\u0011B\u0001\u000b\b\u0005E\u0011\u0015m]3TQV4g\r\\3IC:$G.\u001a\t\u0003-]a\u0001\u0001B\u0003\u0019\u0001\t\u0007!DA\u0001L\u0007\u0001\t\"aG\u0011\u0011\u0005qyR\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\u000f9{G\u000f[5oOB\u0011ADI\u0005\u0003Gu\u00111!\u00118z!\t1R\u0005B\u0003'\u0001\t\u0007!DA\u0001W\u0003%\u0019\b.\u001e4gY\u0016LE\r\u0005\u0002\u001dS%\u0011!&\b\u0002\u0004\u0013:$\u0018BA\u0014-\u0013\tisAA\u0007TQV4g\r\\3IC:$G.Z\u0001\u000bI\u0016\u0004XM\u001c3f]\u000eL\b#\u0002\u00192+\u0011\"S\"A\u0005\n\u0005IJ!!E*ik\u001a4G.\u001a#fa\u0016tG-\u001a8ds&\u0011afE\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007YB\u0014\b\u0005\u00038\u0001U!S\"A\u0003\t\u000b\u001d\u001a\u0001\u0019\u0001\u0015\t\u000b9\u001a\u0001\u0019A\u0018"
)
public class SerializedShuffleHandle extends BaseShuffleHandle {
   public SerializedShuffleHandle(final int shuffleId, final ShuffleDependency dependency) {
      super(shuffleId, dependency);
   }
}
