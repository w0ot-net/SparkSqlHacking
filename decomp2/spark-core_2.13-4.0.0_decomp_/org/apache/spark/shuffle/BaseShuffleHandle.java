package org.apache.spark.shuffle;

import org.apache.spark.ShuffleDependency;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2Q!\u0002\u0004\u0001\u00119A\u0011\u0002\u0006\u0001\u0003\u0002\u0003\u0006IA\u0006\u000f\t\u0011u\u0001!Q1A\u0005\u0002yA\u0001\u0002\u000e\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\u0006k\u0001!\tA\u000e\u0002\u0012\u0005\u0006\u001cXm\u00155vM\u001adW\rS1oI2,'BA\u0004\t\u0003\u001d\u0019\b.\u001e4gY\u0016T!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\u000b\u0005\u001f\u0015z#g\u0005\u0002\u0001!A\u0011\u0011CE\u0007\u0002\r%\u00111C\u0002\u0002\u000e'\",hM\u001a7f\u0011\u0006tG\r\\3\u0002\u0013MDWO\u001a4mK&#7\u0001\u0001\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0004\u0013:$\u0018B\u0001\u000b\u0013\u0003)!W\r]3oI\u0016t7-_\u000b\u0002?A)\u0001%I\u0012/c5\t\u0001\"\u0003\u0002#\u0011\t\t2\u000b[;gM2,G)\u001a9f]\u0012,gnY=\u0011\u0005\u0011*C\u0002\u0001\u0003\u0006M\u0001\u0011\ra\n\u0002\u0002\u0017F\u0011\u0001f\u000b\t\u0003/%J!A\u000b\r\u0003\u000f9{G\u000f[5oOB\u0011q\u0003L\u0005\u0003[a\u00111!\u00118z!\t!s\u0006B\u00031\u0001\t\u0007qEA\u0001W!\t!#\u0007B\u00034\u0001\t\u0007qEA\u0001D\u0003-!W\r]3oI\u0016t7-\u001f\u0011\u0002\rqJg.\u001b;?)\r9\u0004(\u000f\t\u0006#\u0001\u0019c&\r\u0005\u0006)\u0011\u0001\rA\u0006\u0005\u0006;\u0011\u0001\ra\b"
)
public class BaseShuffleHandle extends ShuffleHandle {
   private final ShuffleDependency dependency;

   public ShuffleDependency dependency() {
      return this.dependency;
   }

   public BaseShuffleHandle(final int shuffleId, final ShuffleDependency dependency) {
      super(shuffleId);
      this.dependency = dependency;
   }
}
