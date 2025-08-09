package org.apache.spark.mllib.random;

import org.apache.spark.util.random.XORShiftRandom;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193Aa\u0002\u0005\u0001'!)\u0011\u0005\u0001C\u0001E!9\u0011\u0002\u0001b\u0001\n\u0013!\u0003B\u0002\u0017\u0001A\u0003%Q\u0005C\u0003.\u0001\u0011\u0005c\u0006C\u00039\u0001\u0011\u0005\u0013\bC\u0003D\u0001\u0011\u0005#EA\fTi\u0006tG-\u0019:e\u001d>\u0014X.\u00197HK:,'/\u0019;pe*\u0011\u0011BC\u0001\u0007e\u0006tGm\\7\u000b\u0005-a\u0011!B7mY&\u0014'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0004\u0001M\u0019\u0001\u0001\u0006\u000e\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g!\rYBDH\u0007\u0002\u0011%\u0011Q\u0004\u0003\u0002\u0014%\u0006tGm\\7ECR\fw)\u001a8fe\u0006$xN\u001d\t\u0003+}I!\u0001\t\f\u0003\r\u0011{WO\u00197f\u0003\u0019a\u0014N\\5u}Q\t1\u0005\u0005\u0002\u001c\u0001U\tQ\u0005\u0005\u0002'U5\tqE\u0003\u0002\nQ)\u0011\u0011\u0006D\u0001\u0005kRLG.\u0003\u0002,O\tq\u0001l\u0014*TQ&4GOU1oI>l\u0017a\u0002:b]\u0012|W\u000eI\u0001\n]\u0016DHOV1mk\u0016$\u0012A\b\u0015\u0004\tA2\u0004CA\u00195\u001b\u0005\u0011$BA\u001a\r\u0003)\tgN\\8uCRLwN\\\u0005\u0003kI\u0012QaU5oG\u0016\f\u0013aN\u0001\u0006c9\nd\u0006M\u0001\bg\u0016$8+Z3e)\tQT\b\u0005\u0002\u0016w%\u0011AH\u0006\u0002\u0005+:LG\u000fC\u0003?\u000b\u0001\u0007q(\u0001\u0003tK\u0016$\u0007CA\u000bA\u0013\t\teC\u0001\u0003M_:<\u0007fA\u00031m\u0005!1m\u001c9zQ\r1\u0001G\u000e\u0015\u0004\u0001A2\u0004"
)
public class StandardNormalGenerator implements RandomDataGenerator {
   private final XORShiftRandom random = new XORShiftRandom();

   private XORShiftRandom random() {
      return this.random;
   }

   public double nextValue() {
      return this.random().nextGaussian();
   }

   public void setSeed(final long seed) {
      this.random().setSeed(seed);
   }

   public StandardNormalGenerator copy() {
      return new StandardNormalGenerator();
   }
}
