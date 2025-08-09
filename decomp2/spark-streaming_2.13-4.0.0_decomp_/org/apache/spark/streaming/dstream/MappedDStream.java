package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000514Q!\u0003\u0006\u0001\u0019QA\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\t]\u0001\u0011\t\u0011)A\u0005_!A!\u0007\u0001B\u0002B\u0003-1\u0007\u0003\u0005:\u0001\t\r\t\u0015a\u0003;\u0011\u0015Y\u0004\u0001\"\u0001=\u0011\u0015\u0019\u0005\u0001\"\u0011E\u0011\u00151\u0006\u0001\"\u0011X\u0011\u0015a\u0006\u0001\"\u0011^\u00055i\u0015\r\u001d9fI\u0012\u001bFO]3b[*\u00111\u0002D\u0001\bIN$(/Z1n\u0015\tia\"A\u0005tiJ,\u0017-\\5oO*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014x-F\u0002\u0016Yq\u0019\"\u0001\u0001\f\u0011\u0007]A\"$D\u0001\u000b\u0013\tI\"BA\u0004E'R\u0014X-Y7\u0011\u0005maB\u0002\u0001\u0003\u0006;\u0001\u0011\ra\b\u0002\u0002+\u000e\u0001\u0011C\u0001\u0011'!\t\tC%D\u0001#\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!I\u0014\n\u0005!\u0012#aA!os\u00061\u0001/\u0019:f]R\u00042a\u0006\r,!\tYB\u0006B\u0003.\u0001\t\u0007qDA\u0001U\u0003\u001di\u0017\r\u001d$v]\u000e\u0004B!\t\u0019,5%\u0011\u0011G\t\u0002\n\rVt7\r^5p]F\n!\"\u001a<jI\u0016t7-\u001a\u00132!\r!tgK\u0007\u0002k)\u0011aGI\u0001\be\u00164G.Z2u\u0013\tATG\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003))g/\u001b3f]\u000e,GE\r\t\u0004i]R\u0012A\u0002\u001fj]&$h\bF\u0002>\u0003\n#2AP A!\u00119\u0002a\u000b\u000e\t\u000bI*\u00019A\u001a\t\u000be*\u00019\u0001\u001e\t\u000b%*\u0001\u0019\u0001\u0016\t\u000b9*\u0001\u0019A\u0018\u0002\u0019\u0011,\u0007/\u001a8eK:\u001c\u0017.Z:\u0016\u0003\u0015\u00032A\u0012(R\u001d\t9EJ\u0004\u0002I\u00176\t\u0011J\u0003\u0002K=\u00051AH]8pizJ\u0011aI\u0005\u0003\u001b\n\nq\u0001]1dW\u0006<W-\u0003\u0002P!\n!A*[:u\u0015\ti%\u0005\r\u0002S)B\u0019q\u0003G*\u0011\u0005m!F!C+\u0007\u0003\u0003\u0005\tQ!\u0001 \u0005\ryF%M\u0001\u000eg2LG-\u001a#ve\u0006$\u0018n\u001c8\u0016\u0003a\u0003\"!\u0017.\u000e\u00031I!a\u0017\u0007\u0003\u0011\u0011+(/\u0019;j_:\fqaY8naV$X\r\u0006\u0002_OB\u0019\u0011eX1\n\u0005\u0001\u0014#AB(qi&|g\u000eE\u0002cKji\u0011a\u0019\u0006\u0003I:\t1A\u001d3e\u0013\t17MA\u0002S\t\u0012CQ\u0001\u001b\u0005A\u0002%\f\u0011B^1mS\u0012$\u0016.\\3\u0011\u0005eS\u0017BA6\r\u0005\u0011!\u0016.\\3"
)
public class MappedDStream extends DStream {
   private final DStream parent;
   private final Function1 mapFunc;
   private final ClassTag evidence$2;

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public Option compute(final Time validTime) {
      return this.parent.getOrCompute(validTime).map((x$1) -> x$1.map(this.mapFunc, this.evidence$2));
   }

   public MappedDStream(final DStream parent, final Function1 mapFunc, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(parent.ssc(), evidence$2);
      this.parent = parent;
      this.mapFunc = mapFunc;
      this.evidence$2 = evidence$2;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
