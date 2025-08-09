package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3A\u0001C\u0005\u0001)!A\u0001\u0006\u0001B\u0001B\u0003%\u0011\u0006\u0003\u0005.\u0001\t\u0005\t\u0015!\u0003/\u0011!\u0019\u0004AaA!\u0002\u0017!\u0004\"\u0002\u001e\u0001\t\u0003Y\u0004\"B!\u0001\t\u0003\u0012\u0005\"\u0002$\u0001\t\u0003\u0012\u0005\"B$\u0001\t\u0003B%\u0001F\"p]N$\u0018M\u001c;J]B,H\u000fR*ue\u0016\fWN\u0003\u0002\u000b\u0017\u00059Am\u001d;sK\u0006l'B\u0001\u0007\u000e\u0003%\u0019HO]3b[&twM\u0003\u0002\u000f\u001f\u0005)1\u000f]1sW*\u0011\u0001#E\u0001\u0007CB\f7\r[3\u000b\u0003I\t1a\u001c:h\u0007\u0001)\"!\u0006\u000f\u0014\u0005\u00011\u0002cA\f\u001955\t\u0011\"\u0003\u0002\u001a\u0013\ta\u0011J\u001c9vi\u0012\u001bFO]3b[B\u00111\u0004\b\u0007\u0001\t\u0015i\u0002A1\u0001\u001f\u0005\u0005!\u0016CA\u0010&!\t\u00013%D\u0001\"\u0015\u0005\u0011\u0013!B:dC2\f\u0017B\u0001\u0013\"\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\t\u0014\n\u0005\u001d\n#aA!os\u0006!ql]:d!\tQ3&D\u0001\f\u0013\ta3B\u0001\tTiJ,\u0017-\\5oO\u000e{g\u000e^3yi\u0006\u0019!\u000f\u001a3\u0011\u0007=\n$$D\u00011\u0015\tiS\"\u0003\u00023a\t\u0019!\u000b\u0012#\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u00026qii\u0011A\u000e\u0006\u0003o\u0005\nqA]3gY\u0016\u001cG/\u0003\u0002:m\tA1\t\\1tgR\u000bw-\u0001\u0004=S:LGO\u0010\u000b\u0004y}\u0002ECA\u001f?!\r9\u0002A\u0007\u0005\u0006g\u0011\u0001\u001d\u0001\u000e\u0005\u0006Q\u0011\u0001\r!\u000b\u0005\u0006[\u0011\u0001\rAL\u0001\u0006gR\f'\u000f\u001e\u000b\u0002\u0007B\u0011\u0001\u0005R\u0005\u0003\u000b\u0006\u0012A!\u00168ji\u0006!1\u000f^8q\u0003\u001d\u0019w.\u001c9vi\u0016$\"!\u0013'\u0011\u0007\u0001Re&\u0003\u0002LC\t1q\n\u001d;j_:DQ!T\u0004A\u00029\u000b\u0011B^1mS\u0012$\u0016.\\3\u0011\u0005)z\u0015B\u0001)\f\u0005\u0011!\u0016.\\3"
)
public class ConstantInputDStream extends InputDStream {
   private final RDD rdd;

   public void start() {
   }

   public void stop() {
   }

   public Option compute(final Time validTime) {
      return new Some(this.rdd);
   }

   public ConstantInputDStream(final StreamingContext _ssc, final RDD rdd, final ClassTag evidence$1) {
      super(_ssc, evidence$1);
      this.rdd = rdd;
      .MODULE$.require(rdd != null, () -> "parameter rdd null is illegal, which will lead to NPE in the following transformation");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
