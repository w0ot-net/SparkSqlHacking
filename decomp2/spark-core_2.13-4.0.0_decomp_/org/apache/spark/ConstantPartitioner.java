package org.apache.spark;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r2Q\u0001B\u0003\u0001\u000b-AQ\u0001\u0005\u0001\u0005\u0002IAQ\u0001\u0006\u0001\u0005BUAQ\u0001\b\u0001\u0005Bu\u00111cQ8ogR\fg\u000e\u001e)beRLG/[8oKJT!AB\u0004\u0002\u000bM\u0004\u0018M]6\u000b\u0005!I\u0011AB1qC\u000eDWMC\u0001\u000b\u0003\ry'oZ\n\u0003\u00011\u0001\"!\u0004\b\u000e\u0003\u0015I!aD\u0003\u0003\u0017A\u000b'\u000f^5uS>tWM]\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t1\u0003\u0005\u0002\u000e\u0001\u0005ia.^7QCJ$\u0018\u000e^5p]N,\u0012A\u0006\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0004\u0013:$\u0018\u0001D4fiB\u000b'\u000f^5uS>tGC\u0001\f\u001f\u0011\u0015y2\u00011\u0001!\u0003\rYW-\u001f\t\u0003/\u0005J!A\t\r\u0003\u0007\u0005s\u0017\u0010"
)
public class ConstantPartitioner extends Partitioner {
   public int numPartitions() {
      return 1;
   }

   public int getPartition(final Object key) {
      return 0;
   }
}
