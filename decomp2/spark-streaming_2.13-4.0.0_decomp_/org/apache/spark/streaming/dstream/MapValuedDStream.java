package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005]4QAC\u0006\u0001\u001bUA\u0001\u0002\r\u0001\u0003\u0002\u0003\u0006I!\r\u0005\tm\u0001\u0011\t\u0011)A\u0005o!A!\b\u0001B\u0002B\u0003-1\b\u0003\u0005B\u0001\t\r\t\u0015a\u0003C\u0011!\u0019\u0005AaA!\u0002\u0017!\u0005\"B#\u0001\t\u00031\u0005\"\u0002(\u0001\t\u0003z\u0005\"B1\u0001\t\u0003\u0012\u0007\"B4\u0001\t\u0003B'\u0001E'baZ\u000bG.^3e\tN#(/Z1n\u0015\taQ\"A\u0004egR\u0014X-Y7\u000b\u00059y\u0011!C:ue\u0016\fW.\u001b8h\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<W\u0003\u0002\f$i9\u001a\"\u0001A\f\u0011\u0007aI2$D\u0001\f\u0013\tQ2BA\u0004E'R\u0014X-Y7\u0011\tqy\u0012%L\u0007\u0002;)\ta$A\u0003tG\u0006d\u0017-\u0003\u0002!;\t1A+\u001e9mKJ\u0002\"AI\u0012\r\u0001\u0011)A\u0005\u0001b\u0001M\t\t1j\u0001\u0001\u0012\u0005\u001dR\u0003C\u0001\u000f)\u0013\tISDA\u0004O_RD\u0017N\\4\u0011\u0005qY\u0013B\u0001\u0017\u001e\u0005\r\te.\u001f\t\u0003E9\"Qa\f\u0001C\u0002\u0019\u0012\u0011!V\u0001\u0007a\u0006\u0014XM\u001c;\u0011\u0007aI\"\u0007\u0005\u0003\u001d?\u0005\u001a\u0004C\u0001\u00125\t\u0015)\u0004A1\u0001'\u0005\u00051\u0016\u0001D7baZ\u000bG.^3Gk:\u001c\u0007\u0003\u0002\u000f9g5J!!O\u000f\u0003\u0013\u0019+hn\u0019;j_:\f\u0014AC3wS\u0012,gnY3%cA\u0019AhP\u0011\u000e\u0003uR!AP\u000f\u0002\u000fI,g\r\\3di&\u0011\u0001)\u0010\u0002\t\u00072\f7o\u001d+bO\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u0007qz4'\u0001\u0006fm&$WM\\2fIM\u00022\u0001P .\u0003\u0019a\u0014N\\5u}Q\u0019q\tT'\u0015\t!K%j\u0013\t\u00061\u0001\t3'\f\u0005\u0006u\u0019\u0001\u001da\u000f\u0005\u0006\u0003\u001a\u0001\u001dA\u0011\u0005\u0006\u0007\u001a\u0001\u001d\u0001\u0012\u0005\u0006a\u0019\u0001\r!\r\u0005\u0006m\u0019\u0001\raN\u0001\rI\u0016\u0004XM\u001c3f]\u000eLWm]\u000b\u0002!B\u0019\u0011+\u0017/\u000f\u0005I;fBA*W\u001b\u0005!&BA+&\u0003\u0019a$o\\8u}%\ta$\u0003\u0002Y;\u00059\u0001/Y2lC\u001e,\u0017B\u0001.\\\u0005\u0011a\u0015n\u001d;\u000b\u0005ak\u0002GA/`!\rA\u0012D\u0018\t\u0003E}#\u0011\u0002Y\u0004\u0002\u0002\u0003\u0005)\u0011\u0001\u0014\u0003\u0007}#\u0013'A\u0007tY&$W\rR;sCRLwN\\\u000b\u0002GB\u0011A-Z\u0007\u0002\u001b%\u0011a-\u0004\u0002\t\tV\u0014\u0018\r^5p]\u000691m\\7qkR,GCA5s!\ra\"\u000e\\\u0005\u0003Wv\u0011aa\u00149uS>t\u0007cA7q75\taN\u0003\u0002p\u001f\u0005\u0019!\u000f\u001a3\n\u0005Et'a\u0001*E\t\")1/\u0003a\u0001i\u0006Ia/\u00197jIRKW.\u001a\t\u0003IVL!A^\u0007\u0003\tQKW.\u001a"
)
public class MapValuedDStream extends DStream {
   private final DStream parent;
   private final Function1 mapValueFunc;
   private final ClassTag evidence$1;
   private final ClassTag evidence$2;

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public Option compute(final Time validTime) {
      return this.parent.getOrCompute(validTime).map((x$1) -> {
         ClassTag x$2 = this.evidence$1;
         ClassTag x$3 = this.evidence$2;
         Null x$4 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(x$1);
         return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(x$1, x$2, x$3, (Ordering)null).mapValues(this.mapValueFunc);
      });
   }

   public MapValuedDStream(final DStream parent, final Function1 mapValueFunc, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3) {
      super(parent.ssc(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.parent = parent;
      this.mapValueFunc = mapValueFunc;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
