package org.apache.spark.streaming.ui;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeUnit;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.ArrowAssoc.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014Qa\u0003\u0007\u0001\u0019YA\u0001\"\b\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\u0006c\u0001!\tA\r\u0005\u0006m\u0001!\ta\u000e\u0005\u0006\u0013\u0002!\tA\u0013\u0005\b\u001b\u0002\u0011\r\u0011\"\u0001O\u0011\u0019\u0011\u0006\u0001)A\u0005\u001f\"91\u000b\u0001b\u0001\n\u0003!\u0006BB/\u0001A\u0003%Q\u000bC\u0004_\u0001\t\u0007I\u0011\u0001(\t\r}\u0003\u0001\u0015!\u0003P\u0005Yi\u0015\u000e\u001c7jg\u0016\u001cwN\u001c3t'R\fG/V%ECR\f'BA\u0007\u000f\u0003\t)\u0018N\u0003\u0002\u0010!\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003#I\tQa\u001d9be.T!a\u0005\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0012aA8sON\u0011\u0001a\u0006\t\u00031mi\u0011!\u0007\u0006\u00025\u0005)1oY1mC&\u0011A$\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\t\u0011\fG/Y\u0002\u0001!\r\u0001\u0003f\u000b\b\u0003C\u0019r!AI\u0013\u000e\u0003\rR!\u0001\n\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0012BA\u0014\u001a\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000b\u0016\u0003\u0007M+\u0017O\u0003\u0002(3A!\u0001\u0004\f\u0018/\u0013\ti\u0013D\u0001\u0004UkBdWM\r\t\u00031=J!\u0001M\r\u0003\t1{gnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005M*\u0004C\u0001\u001b\u0001\u001b\u0005a\u0001\"B\u000f\u0003\u0001\u0004y\u0012\u0001\u0004;j[\u0016d\u0017N\\3ECR\fGC\u0001\u001d>!\r\u0001\u0003&\u000f\t\u000511r#\b\u0005\u0002\u0019w%\u0011A(\u0007\u0002\u0007\t>,(\r\\3\t\u000by\u001a\u0001\u0019A \u0002\tUt\u0017\u000e\u001e\t\u0003\u0001\u001ek\u0011!\u0011\u0006\u0003\u0005\u000e\u000b!bY8oGV\u0014(/\u001a8u\u0015\t!U)\u0001\u0003vi&d'\"\u0001$\u0002\t)\fg/Y\u0005\u0003\u0011\u0006\u0013\u0001\u0002V5nKVs\u0017\u000e^\u0001\u000eQ&\u001cHo\\4sC6$\u0015\r^1\u0015\u0005-c\u0005c\u0001\u0011)u!)a\b\u0002a\u0001\u007f\u0005\u0019\u0011M^4\u0016\u0003=\u00032\u0001\u0007)/\u0013\t\t\u0016D\u0001\u0004PaRLwN\\\u0001\u0005CZ<\u0007%\u0001\u0007g_Jl\u0017\r\u001e;fI\u00063x-F\u0001V!\t1&L\u0004\u0002X1B\u0011!%G\u0005\u00033f\ta\u0001\u0015:fI\u00164\u0017BA.]\u0005\u0019\u0019FO]5oO*\u0011\u0011,G\u0001\u000eM>\u0014X.\u0019;uK\u0012\feo\u001a\u0011\u0002\u00075\f\u00070\u0001\u0003nCb\u0004\u0003"
)
public class MillisecondsStatUIData {
   private final Seq data;
   private final Option avg;
   private final String formattedAvg;
   private final Option max;

   public Seq timelineData(final TimeUnit unit) {
      return (Seq)this.data.map((x) -> .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(x._1$mcJ$sp())), BoxesRunTime.boxToDouble(UIUtils$.MODULE$.convertToTimeUnit(x._2$mcJ$sp(), unit))));
   }

   public Seq histogramData(final TimeUnit unit) {
      return (Seq)this.data.map((x) -> BoxesRunTime.boxToDouble($anonfun$histogramData$1(unit, x)));
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
   public static final double $anonfun$histogramData$1(final TimeUnit unit$2, final Tuple2 x) {
      return UIUtils$.MODULE$.convertToTimeUnit(x._2$mcJ$sp(), unit$2);
   }

   // $FF: synthetic method
   public static final long $anonfun$avg$1(final Tuple2 x$1) {
      return x$1._2$mcJ$sp();
   }

   // $FF: synthetic method
   public static final long $anonfun$max$1(final Tuple2 x$2) {
      return x$2._2$mcJ$sp();
   }

   public MillisecondsStatUIData(final Seq data) {
      this.data = data;
      this.avg = (Option)(data.isEmpty() ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(((IterableOnceOps)data.map((x$1) -> BoxesRunTime.boxToLong($anonfun$avg$1(x$1)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$)) / (long)data.size())));
      this.formattedAvg = StreamingPage$.MODULE$.formatDurationOption(this.avg());
      this.max = (Option)(data.isEmpty() ? scala.None..MODULE$ : new Some(((IterableOnceOps)data.map((x$2) -> BoxesRunTime.boxToLong($anonfun$max$1(x$2)))).max(scala.math.Ordering.Long..MODULE$)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
