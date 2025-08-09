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
   bytes = "\u0006\u0005=4Q!\u0003\u0006\u0001\u0019QA\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\t]\u0001\u0011\t\u0011)A\u0005_!Aa\b\u0001B\u0002B\u0003-q\b\u0003\u0005F\u0001\t\r\t\u0015a\u0003G\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0015y\u0005\u0001\"\u0011Q\u0011\u0015I\u0006\u0001\"\u0011[\u0011\u0015y\u0006\u0001\"\u0011a\u0005E1E.\u0019;NCB\u0004X\r\u001a#TiJ,\u0017-\u001c\u0006\u0003\u00171\tq\u0001Z:ue\u0016\fWN\u0003\u0002\u000e\u001d\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sOV\u0019Q\u0003\f\u000f\u0014\u0005\u00011\u0002cA\f\u001955\t!\"\u0003\u0002\u001a\u0015\t9Ai\u0015;sK\u0006l\u0007CA\u000e\u001d\u0019\u0001!Q!\b\u0001C\u0002}\u0011\u0011!V\u0002\u0001#\t\u0001c\u0005\u0005\u0002\"I5\t!EC\u0001$\u0003\u0015\u00198-\u00197b\u0013\t)#EA\u0004O_RD\u0017N\\4\u0011\u0005\u0005:\u0013B\u0001\u0015#\u0005\r\te._\u0001\u0007a\u0006\u0014XM\u001c;\u0011\u0007]A2\u0006\u0005\u0002\u001cY\u0011)Q\u0006\u0001b\u0001?\t\tA+A\u0006gY\u0006$X*\u00199Gk:\u001c\u0007\u0003B\u00111WIJ!!\r\u0012\u0003\u0013\u0019+hn\u0019;j_:\f\u0004cA\u001a<59\u0011A'\u000f\b\u0003kaj\u0011A\u000e\u0006\u0003oy\ta\u0001\u0010:p_Rt\u0014\"A\u0012\n\u0005i\u0012\u0013a\u00029bG.\fw-Z\u0005\u0003yu\u0012A\"\u0013;fe\u0006\u0014G.Z(oG\u0016T!A\u000f\u0012\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002A\u0007.j\u0011!\u0011\u0006\u0003\u0005\n\nqA]3gY\u0016\u001cG/\u0003\u0002E\u0003\nA1\t\\1tgR\u000bw-\u0001\u0006fm&$WM\\2fII\u00022\u0001Q\"\u001b\u0003\u0019a\u0014N\\5u}Q\u0019\u0011*\u0014(\u0015\u0007)[E\n\u0005\u0003\u0018\u0001-R\u0002\"\u0002 \u0006\u0001\by\u0004\"B#\u0006\u0001\b1\u0005\"B\u0015\u0006\u0001\u0004Q\u0003\"\u0002\u0018\u0006\u0001\u0004y\u0013\u0001\u00043fa\u0016tG-\u001a8dS\u0016\u001cX#A)\u0011\u0007M\u0012F+\u0003\u0002T{\t!A*[:ua\t)v\u000bE\u0002\u00181Y\u0003\"aG,\u0005\u0013a3\u0011\u0011!A\u0001\u0006\u0003y\"aA0%c\u0005i1\u000f\\5eK\u0012+(/\u0019;j_:,\u0012a\u0017\t\u00039vk\u0011\u0001D\u0005\u0003=2\u0011\u0001\u0002R;sCRLwN\\\u0001\bG>l\u0007/\u001e;f)\t\t'\u000eE\u0002\"E\u0012L!a\u0019\u0012\u0003\r=\u0003H/[8o!\r)\u0007NG\u0007\u0002M*\u0011qMD\u0001\u0004e\u0012$\u0017BA5g\u0005\r\u0011F\t\u0012\u0005\u0006W\"\u0001\r\u0001\\\u0001\nm\u0006d\u0017\u000e\u001a+j[\u0016\u0004\"\u0001X7\n\u00059d!\u0001\u0002+j[\u0016\u0004"
)
public class FlatMappedDStream extends DStream {
   private final DStream parent;
   private final Function1 flatMapFunc;
   private final ClassTag evidence$2;

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public Option compute(final Time validTime) {
      return this.parent.getOrCompute(validTime).map((x$1) -> x$1.flatMap(this.flatMapFunc, this.evidence$2));
   }

   public FlatMappedDStream(final DStream parent, final Function1 flatMapFunc, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(parent.ssc(), evidence$2);
      this.parent = parent;
      this.flatMapFunc = flatMapFunc;
      this.evidence$2 = evidence$2;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
