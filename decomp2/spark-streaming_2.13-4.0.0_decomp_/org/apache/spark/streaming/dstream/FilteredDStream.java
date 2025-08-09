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
   bytes = "\u0006\u0005\u001d4Q\u0001C\u0005\u0001\u0017MA\u0001\u0002\u000b\u0001\u0003\u0002\u0003\u0006I!\u0006\u0005\tS\u0001\u0011\t\u0011)A\u0005U!A\u0001\u0007\u0001B\u0002B\u0003-\u0011\u0007C\u00038\u0001\u0011\u0005\u0001\bC\u0003?\u0001\u0011\u0005s\bC\u0003R\u0001\u0011\u0005#\u000bC\u0003X\u0001\u0011\u0005\u0003LA\bGS2$XM]3e\tN#(/Z1n\u0015\tQ1\"A\u0004egR\u0014X-Y7\u000b\u00051i\u0011!C:ue\u0016\fW.\u001b8h\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<WC\u0001\u000b\u001c'\t\u0001Q\u0003E\u0002\u0017/ei\u0011!C\u0005\u00031%\u0011q\u0001R*ue\u0016\fW\u000e\u0005\u0002\u001b71\u0001A!\u0002\u000f\u0001\u0005\u0004q\"!\u0001+\u0004\u0001E\u0011q$\n\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\b\u001d>$\b.\u001b8h!\t\u0001c%\u0003\u0002(C\t\u0019\u0011I\\=\u0002\rA\f'/\u001a8u\u0003)1\u0017\u000e\u001c;fe\u001a+hn\u0019\t\u0005A-JR&\u0003\u0002-C\tIa)\u001e8di&|g.\r\t\u0003A9J!aL\u0011\u0003\u000f\t{w\u000e\\3b]\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007I*\u0014$D\u00014\u0015\t!\u0014%A\u0004sK\u001adWm\u0019;\n\u0005Y\u001a$\u0001C\"mCN\u001cH+Y4\u0002\rqJg.\u001b;?)\rID(\u0010\u000b\u0003um\u00022A\u0006\u0001\u001a\u0011\u0015\u0001D\u0001q\u00012\u0011\u0015AC\u00011\u0001\u0016\u0011\u0015IC\u00011\u0001+\u00031!W\r]3oI\u0016t7-[3t+\u0005\u0001\u0005cA!J\u0019:\u0011!i\u0012\b\u0003\u0007\u001ak\u0011\u0001\u0012\u0006\u0003\u000bv\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0012\n\u0005!\u000b\u0013a\u00029bG.\fw-Z\u0005\u0003\u0015.\u0013A\u0001T5ti*\u0011\u0001*\t\u0019\u0003\u001b>\u00032AF\fO!\tQr\nB\u0005Q\u000b\u0005\u0005\t\u0011!B\u0001=\t\u0019q\fJ\u0019\u0002\u001bMd\u0017\u000eZ3EkJ\fG/[8o+\u0005\u0019\u0006C\u0001+V\u001b\u0005Y\u0011B\u0001,\f\u0005!!UO]1uS>t\u0017aB2p[B,H/\u001a\u000b\u00033\n\u00042\u0001\t.]\u0013\tY\u0016E\u0001\u0004PaRLwN\u001c\t\u0004;\u0002LR\"\u00010\u000b\u0005}k\u0011a\u0001:eI&\u0011\u0011M\u0018\u0002\u0004%\u0012#\u0005\"B2\b\u0001\u0004!\u0017!\u0003<bY&$G+[7f!\t!V-\u0003\u0002g\u0017\t!A+[7f\u0001"
)
public class FilteredDStream extends DStream {
   private final DStream parent;
   private final Function1 filterFunc;

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public Option compute(final Time validTime) {
      return this.parent.getOrCompute(validTime).map((x$1) -> x$1.filter(this.filterFunc));
   }

   public FilteredDStream(final DStream parent, final Function1 filterFunc, final ClassTag evidence$1) {
      super(parent.ssc(), evidence$1);
      this.parent = parent;
      this.filterFunc = filterFunc;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
