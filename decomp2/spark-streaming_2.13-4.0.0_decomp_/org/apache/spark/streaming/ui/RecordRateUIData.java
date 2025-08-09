package org.apache.spark.streaming.ui;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000553QAC\u0006\u0001\u0017UA\u0001\u0002\b\u0001\u0003\u0006\u0004%\tA\b\u0005\ti\u0001\u0011\t\u0011)A\u0005?!)Q\u0007\u0001C\u0001m!9!\b\u0001b\u0001\n\u0003Y\u0004BB \u0001A\u0003%A\bC\u0004A\u0001\t\u0007I\u0011A!\t\r)\u0003\u0001\u0015!\u0003C\u0011\u001dY\u0005A1A\u0005\u0002mBa\u0001\u0014\u0001!\u0002\u0013a$\u0001\u0005*fG>\u0014HMU1uKVKE)\u0019;b\u0015\taQ\"\u0001\u0002vS*\u0011abD\u0001\ngR\u0014X-Y7j]\u001eT!\u0001E\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005I\u0019\u0012AB1qC\u000eDWMC\u0001\u0015\u0003\ry'oZ\n\u0003\u0001Y\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0017\u0001\u00023bi\u0006\u001c\u0001!F\u0001 !\r\u0001\u0003f\u000b\b\u0003C\u0019r!AI\u0013\u000e\u0003\rR!\u0001J\u000f\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0012BA\u0014\u0019\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000b\u0016\u0003\u0007M+\u0017O\u0003\u0002(1A!q\u0003\f\u00182\u0013\ti\u0003D\u0001\u0004UkBdWM\r\t\u0003/=J!\u0001\r\r\u0003\t1{gn\u001a\t\u0003/IJ!a\r\r\u0003\r\u0011{WO\u00197f\u0003\u0015!\u0017\r^1!\u0003\u0019a\u0014N\\5u}Q\u0011q'\u000f\t\u0003q\u0001i\u0011a\u0003\u0005\u00069\r\u0001\raH\u0001\u0004CZ<W#\u0001\u001f\u0011\u0007]i\u0014'\u0003\u0002?1\t1q\n\u001d;j_:\fA!\u0019<hA\u0005aam\u001c:nCR$X\rZ!wOV\t!\t\u0005\u0002D\u000f:\u0011A)\u0012\t\u0003EaI!A\u0012\r\u0002\rA\u0013X\rZ3g\u0013\tA\u0015J\u0001\u0004TiJLgn\u001a\u0006\u0003\rb\tQBZ8s[\u0006$H/\u001a3Bm\u001e\u0004\u0013aA7bq\u0006!Q.\u0019=!\u0001"
)
public class RecordRateUIData {
   private final Seq data;
   private final Option avg;
   private final String formattedAvg;
   private final Option max;

   public Seq data() {
      return this.data;
   }

   public Option avg() {
      return this.avg;
   }

   public String formattedAvg() {
      return this.formattedAvg;
   }

   public Option max() {
      return this.max;
   }

   // $FF: synthetic method
   public static final double $anonfun$avg$2(final Tuple2 x$3) {
      return x$3._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final String $anonfun$formattedAvg$1(final double x$4) {
      return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%.2f"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(x$4)}));
   }

   // $FF: synthetic method
   public static final double $anonfun$max$2(final Tuple2 x$5) {
      return x$5._2$mcD$sp();
   }

   public RecordRateUIData(final Seq data) {
      this.data = data;
      this.avg = (Option)(data.isEmpty() ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(((IterableOnceOps)data.map((x$3) -> BoxesRunTime.boxToDouble($anonfun$avg$2(x$3)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)) / (double)data.size())));
      this.formattedAvg = (String)this.avg().map((x$4) -> $anonfun$formattedAvg$1(BoxesRunTime.unboxToDouble(x$4))).getOrElse(() -> "-");
      this.max = (Option)(data.isEmpty() ? scala.None..MODULE$ : new Some(((IterableOnceOps)data.map((x$5) -> BoxesRunTime.boxToDouble($anonfun$max$2(x$5)))).max(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
