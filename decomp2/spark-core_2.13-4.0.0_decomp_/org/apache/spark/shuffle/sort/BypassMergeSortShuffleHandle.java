package org.apache.spark.shuffle.sort;

import org.apache.spark.ShuffleDependency;
import org.apache.spark.shuffle.BaseShuffleHandle;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2Q\u0001B\u0003\u0001\u0013=A\u0011b\n\u0001\u0003\u0002\u0003\u0006I\u0001K\u0016\t\u00139\u0002!\u0011!Q\u0001\n=\u001a\u0004\"\u0002\u001b\u0001\t\u0003)$\u0001\b\"za\u0006\u001c8/T3sO\u0016\u001cvN\u001d;TQV4g\r\\3IC:$G.\u001a\u0006\u0003\r\u001d\tAa]8si*\u0011\u0001\"C\u0001\bg\",hM\u001a7f\u0015\tQ1\"A\u0003ta\u0006\u00148N\u0003\u0002\r\u001b\u00051\u0011\r]1dQ\u0016T\u0011AD\u0001\u0004_J<Wc\u0001\t\u0018KM\u0011\u0001!\u0005\t\u0006%M)B\u0005J\u0007\u0002\u000f%\u0011Ac\u0002\u0002\u0012\u0005\u0006\u001cXm\u00155vM\u001adW\rS1oI2,\u0007C\u0001\f\u0018\u0019\u0001!Q\u0001\u0007\u0001C\u0002i\u0011\u0011aS\u0002\u0001#\tY\u0012\u0005\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SDA\u0004O_RD\u0017N\\4\u0011\u0005q\u0011\u0013BA\u0012\u001e\u0005\r\te.\u001f\t\u0003-\u0015\"QA\n\u0001C\u0002i\u0011\u0011AV\u0001\ng\",hM\u001a7f\u0013\u0012\u0004\"\u0001H\u0015\n\u0005)j\"aA%oi&\u0011q\u0005L\u0005\u0003[\u001d\u0011Qb\u00155vM\u001adW\rS1oI2,\u0017A\u00033fa\u0016tG-\u001a8dsB)\u0001'M\u000b%I5\t\u0011\"\u0003\u00023\u0013\t\t2\u000b[;gM2,G)\u001a9f]\u0012,gnY=\n\u00059\u001a\u0012A\u0002\u001fj]&$h\bF\u00027qe\u0002Ba\u000e\u0001\u0016I5\tQ\u0001C\u0003(\u0007\u0001\u0007\u0001\u0006C\u0003/\u0007\u0001\u0007q\u0006"
)
public class BypassMergeSortShuffleHandle extends BaseShuffleHandle {
   public BypassMergeSortShuffleHandle(final int shuffleId, final ShuffleDependency dependency) {
      super(shuffleId, dependency);
   }
}
