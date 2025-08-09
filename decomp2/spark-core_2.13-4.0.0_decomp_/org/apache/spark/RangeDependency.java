package org.apache.spark;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.rdd.RDD;
import scala.collection.immutable.List;
import scala.package.;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u000553Aa\u0002\u0005\u0001\u001f!A1\u0005\u0001B\u0001B\u0003%A\u0005\u0003\u0005*\u0001\t\u0005\t\u0015!\u0003+\u0011!i\u0003A!A!\u0002\u0013Q\u0003\u0002\u0003\u0018\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016\t\u000b=\u0002A\u0011\u0001\u0019\t\u000bY\u0002A\u0011I\u001c\u0003\u001fI\u000bgnZ3EKB,g\u000eZ3oGfT!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\u0002\u0001+\t\u0001rc\u0005\u0002\u0001#A\u0019!cE\u000b\u000e\u0003!I!\u0001\u0006\u0005\u0003!9\u000b'O]8x\t\u0016\u0004XM\u001c3f]\u000eL\bC\u0001\f\u0018\u0019\u0001!Q\u0001\u0007\u0001C\u0002e\u0011\u0011\u0001V\t\u00035\u0001\u0002\"a\u0007\u0010\u000e\u0003qQ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?q\u0011qAT8uQ&tw\r\u0005\u0002\u001cC%\u0011!\u0005\b\u0002\u0004\u0003:L\u0018a\u0001:eIB\u0019QeJ\u000b\u000e\u0003\u0019R!a\t\u0005\n\u0005!2#a\u0001*E\t\u00069\u0011N\\*uCJ$\bCA\u000e,\u0013\taCDA\u0002J]R\f\u0001b\\;u'R\f'\u000f^\u0001\u0007Y\u0016tw\r\u001e5\u0002\rqJg.\u001b;?)\u0015\t$g\r\u001b6!\r\u0011\u0002!\u0006\u0005\u0006G\u0015\u0001\r\u0001\n\u0005\u0006S\u0015\u0001\rA\u000b\u0005\u0006[\u0015\u0001\rA\u000b\u0005\u0006]\u0015\u0001\rAK\u0001\u000bO\u0016$\b+\u0019:f]R\u001cHC\u0001\u001dE!\rI\u0014I\u000b\b\u0003u}r!a\u000f \u000e\u0003qR!!\u0010\b\u0002\rq\u0012xn\u001c;?\u0013\u0005i\u0012B\u0001!\u001d\u0003\u001d\u0001\u0018mY6bO\u0016L!AQ\"\u0003\t1K7\u000f\u001e\u0006\u0003\u0001rAQ!\u0012\u0004A\u0002)\n1\u0002]1si&$\u0018n\u001c8JI\"\u0012\u0001a\u0012\t\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015\"\t!\"\u00198o_R\fG/[8o\u0013\ta\u0015J\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e"
)
public class RangeDependency extends NarrowDependency {
   private final int inStart;
   private final int outStart;
   private final int length;

   public List getParents(final int partitionId) {
      return (List)(partitionId >= this.outStart && partitionId < this.outStart + this.length ? (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{partitionId - this.outStart + this.inStart})) : scala.collection.immutable.Nil..MODULE$);
   }

   public RangeDependency(final RDD rdd, final int inStart, final int outStart, final int length) {
      super(rdd);
      this.inStart = inStart;
      this.outStart = outStart;
      this.length = length;
   }
}
