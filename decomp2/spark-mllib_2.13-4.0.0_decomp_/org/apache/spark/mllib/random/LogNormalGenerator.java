package org.apache.spark.mllib.random;

import org.apache.commons.math3.distribution.LogNormalDistribution;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3Aa\u0003\u0007\u0001/!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u00051\u0001\t\u0005\t\u0015!\u0003#\u0011!\u0011\u0004A!b\u0001\n\u00031\u0003\u0002\u0003\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0012\t\u000bY\u0002A\u0011A\u001c\t\u000fy\u0002!\u0019!C\u0005\u007f!1!\n\u0001Q\u0001\n\u0001CQa\u0013\u0001\u0005B1CQA\u0014\u0001\u0005B=CQ!\u0017\u0001\u0005Bi\u0013!\u0003T8h\u001d>\u0014X.\u00197HK:,'/\u0019;pe*\u0011QBD\u0001\u0007e\u0006tGm\\7\u000b\u0005=\u0001\u0012!B7mY&\u0014'BA\t\u0013\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0019B#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002+\u0005\u0019qN]4\u0004\u0001M\u0019\u0001\u0001\u0007\u0010\u0011\u0005eaR\"\u0001\u000e\u000b\u0003m\tQa]2bY\u0006L!!\b\u000e\u0003\r\u0005s\u0017PU3g!\ry\u0002EI\u0007\u0002\u0019%\u0011\u0011\u0005\u0004\u0002\u0014%\u0006tGm\\7ECR\fw)\u001a8fe\u0006$xN\u001d\t\u00033\rJ!\u0001\n\u000e\u0003\r\u0011{WO\u00197f\u0003\u0011iW-\u00198\u0016\u0003\tB3!\u0001\u0015/!\tIC&D\u0001+\u0015\tY\u0003#\u0001\u0006b]:|G/\u0019;j_:L!!\f\u0016\u0003\u000bMKgnY3\"\u0003=\nQ!\r\u00184]A\nQ!\\3b]\u0002B3A\u0001\u0015/\u0003\r\u0019H\u000f\u001a\u0015\u0004\u0007!r\u0013\u0001B:uI\u0002B3\u0001\u0002\u0015/\u0003\u0019a\u0014N\\5u}Q\u0019\u0001(O\u001e\u0011\u0005}\u0001\u0001\"B\u0013\u0006\u0001\u0004\u0011\u0003fA\u001d)]!)!'\u0002a\u0001E!\u001a1\b\u000b\u0018)\u0007\u0015Ac&A\u0002s]\u001e,\u0012\u0001\u0011\t\u0003\u0003\"k\u0011A\u0011\u0006\u0003\u0007\u0012\u000bA\u0002Z5tiJL'-\u001e;j_:T!!\u0012$\u0002\u000b5\fG\u000f[\u001a\u000b\u0005\u001d\u0013\u0012aB2p[6|gn]\u0005\u0003\u0013\n\u0013Q\u0003T8h\u001d>\u0014X.\u00197ESN$(/\u001b2vi&|g.\u0001\u0003s]\u001e\u0004\u0013!\u00038fqR4\u0016\r\\;f)\u0005\u0011\u0003f\u0001\u0005)]\u000591/\u001a;TK\u0016$GC\u0001)T!\tI\u0012+\u0003\u0002S5\t!QK\\5u\u0011\u0015!\u0016\u00021\u0001V\u0003\u0011\u0019X-\u001a3\u0011\u0005e1\u0016BA,\u001b\u0005\u0011auN\\4)\u0007%Ac&\u0001\u0003d_BLH#\u0001\u001d)\u0007)Ac\u0006K\u0002\u0001Q9\u0002"
)
public class LogNormalGenerator implements RandomDataGenerator {
   private final double mean;
   private final double std;
   private final LogNormalDistribution rng;

   public double mean() {
      return this.mean;
   }

   public double std() {
      return this.std;
   }

   private LogNormalDistribution rng() {
      return this.rng;
   }

   public double nextValue() {
      return this.rng().sample();
   }

   public void setSeed(final long seed) {
      this.rng().reseedRandomGenerator(seed);
   }

   public LogNormalGenerator copy() {
      return new LogNormalGenerator(this.mean(), this.std());
   }

   public LogNormalGenerator(final double mean, final double std) {
      this.mean = mean;
      this.std = std;
      this.rng = new LogNormalDistribution(mean, std);
   }
}
