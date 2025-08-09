package org.apache.spark.streaming.api.java;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDD.;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.dstream.DStream;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055d\u0001B\u000b\u0017\u0001\rB\u0001B\u0010\u0001\u0003\u0006\u0004%\ta\u0010\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005\u0001\"Aa\t\u0001BC\u0002\u0013\rq\t\u0003\u0005O\u0001\t\u0005\t\u0015!\u0003I\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u0015!\u0006\u0001\"\u0011V\u0011\u0015i\u0006\u0001\"\u0001_\u0011\u0015q\u0007\u0001\"\u0001p\u0011\u0015\u0001\b\u0001\"\u0001p\u0011\u0015\u0001\b\u0001\"\u0001r\u0011\u0015Q\b\u0001\"\u0001|\u0011\u001d\t)\u0001\u0001C\u0001\u0003\u000fAq!!\u0002\u0001\t\u0003\t\u0019\u0002C\u0004\u0002\u001c\u0001!\t!!\b\t\u000f\u0005\r\u0002\u0001\"\u0001\u0002&\u001d9\u0011\u0011\u0007\f\t\u0002\u0005MbAB\u000b\u0017\u0011\u0003\t)\u0004\u0003\u0004P#\u0011\u0005\u0011\u0011\n\u0005\b\u0003\u0017\nB1AA'\u0011%\t\u0019'EA\u0001\n\u0013\t)GA\u0006KCZ\fGi\u0015;sK\u0006l'BA\f\u0019\u0003\u0011Q\u0017M^1\u000b\u0005eQ\u0012aA1qS*\u00111\u0004H\u0001\ngR\u0014X-Y7j]\u001eT!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\u0002\u0001+\t!3f\u0005\u0002\u0001KA)aeJ\u00158q5\ta#\u0003\u0002)-\t9\u0012IY:ue\u0006\u001cGOS1wC\u0012\u001bFO]3b[2K7.\u001a\t\u0003U-b\u0001\u0001B\u0003-\u0001\t\u0007QFA\u0001U#\tqC\u0007\u0005\u00020e5\t\u0001GC\u00012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0004GA\u0004O_RD\u0017N\\4\u0011\u0005=*\u0014B\u0001\u001c1\u0005\r\te.\u001f\t\u0004M\u0001I\u0003cA\u001d=S5\t!H\u0003\u0002\u0018w)\u0011\u0011\u0004H\u0005\u0003{i\u0012qAS1wCJ#E)A\u0004egR\u0014X-Y7\u0016\u0003\u0001\u00032!Q\"*\u001b\u0005\u0011%B\u0001 \u001b\u0013\t!%IA\u0004E'R\u0014X-Y7\u0002\u0011\u0011\u001cHO]3b[\u0002\n\u0001b\u00197bgN$\u0016mZ\u000b\u0002\u0011B\u0019\u0011\nT\u0015\u000e\u0003)S!a\u0013\u0019\u0002\u000fI,g\r\\3di&\u0011QJ\u0013\u0002\t\u00072\f7o\u001d+bO\u0006I1\r\\1tgR\u000bw\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005E\u001bFCA\u001cS\u0011\u00151U\u0001q\u0001I\u0011\u0015qT\u00011\u0001A\u0003\u001d9(/\u00199S\t\u0012#\"\u0001\u000f,\t\u000b]3\u0001\u0019\u0001-\u0002\u0007I$G\rE\u0002Z7&j\u0011A\u0017\u0006\u0003/rI!\u0001\u0018.\u0003\u0007I#E)\u0001\u0004gS2$XM\u001d\u000b\u0003o}CQ\u0001Y\u0004A\u0002\u0005\f\u0011A\u001a\t\u0005E\u0016Ls-D\u0001d\u0015\t!'(\u0001\u0005gk:\u001cG/[8o\u0013\t17M\u0001\u0005Gk:\u001cG/[8o!\tAG.D\u0001j\u0015\tQ7.\u0001\u0003mC:<'\"A\f\n\u00055L'a\u0002\"p_2,\u0017M\\\u0001\u0006G\u0006\u001c\u0007.\u001a\u000b\u0002o\u00059\u0001/\u001a:tSN$HCA\u001cs\u0011\u0015\u0019(\u00021\u0001u\u00031\u0019Ho\u001c:bO\u0016dUM^3m!\t)\b0D\u0001w\u0015\t9H$A\u0004ti>\u0014\u0018mZ3\n\u0005e4(\u0001D*u_J\fw-\u001a'fm\u0016d\u0017aB2p[B,H/\u001a\u000b\u0003qqDQ!`\u0006A\u0002y\f\u0011B^1mS\u0012$\u0016.\\3\u0011\u0007}\f\t!D\u0001\u001b\u0013\r\t\u0019A\u0007\u0002\u0005)&lW-\u0001\u0004xS:$wn\u001e\u000b\u0004o\u0005%\u0001bBA\u0006\u0019\u0001\u0007\u0011QB\u0001\u000fo&tGm\\<EkJ\fG/[8o!\ry\u0018qB\u0005\u0004\u0003#Q\"\u0001\u0003#ve\u0006$\u0018n\u001c8\u0015\u000b]\n)\"a\u0006\t\u000f\u0005-Q\u00021\u0001\u0002\u000e!9\u0011\u0011D\u0007A\u0002\u00055\u0011!D:mS\u0012,G)\u001e:bi&|g.A\u0003v]&|g\u000eF\u00028\u0003?Aa!!\t\u000f\u0001\u00049\u0014\u0001\u0002;iCR\f1B]3qCJ$\u0018\u000e^5p]R\u0019q'a\n\t\u000f\u0005%r\u00021\u0001\u0002,\u0005ia.^7QCJ$\u0018\u000e^5p]N\u00042aLA\u0017\u0013\r\ty\u0003\r\u0002\u0004\u0013:$\u0018a\u0003&bm\u0006$5\u000b\u001e:fC6\u0004\"AJ\t\u0014\u000bE\t9$!\u0010\u0011\u0007=\nI$C\u0002\u0002<A\u0012a!\u00118z%\u00164\u0007\u0003BA \u0003\u000bj!!!\u0011\u000b\u0007\u0005\r3.\u0001\u0002j_&!\u0011qIA!\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\t\u0019$A\u0006ge>lGi\u0015;sK\u0006lW\u0003BA(\u0003/\"B!!\u0015\u0002`Q!\u00111KA-!\u00111\u0003!!\u0016\u0011\u0007)\n9\u0006B\u0003-'\t\u0007Q\u0006C\u0005\u0002\\M\t\t\u0011q\u0001\u0002^\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\t%c\u0015Q\u000b\u0005\u0007}M\u0001\r!!\u0019\u0011\t\u0005\u001b\u0015QK\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003O\u00022\u0001[A5\u0013\r\tY'\u001b\u0002\u0007\u001f\nTWm\u0019;"
)
public class JavaDStream extends AbstractJavaDStreamLike {
   private final DStream dstream;
   private final ClassTag classTag;

   public static JavaDStream fromDStream(final DStream dstream, final ClassTag evidence$1) {
      return JavaDStream$.MODULE$.fromDStream(dstream, evidence$1);
   }

   public DStream dstream() {
      return this.dstream;
   }

   public ClassTag classTag() {
      return this.classTag;
   }

   public JavaRDD wrapRDD(final RDD rdd) {
      return .MODULE$.fromRDD(rdd, this.classTag());
   }

   public JavaDStream filter(final Function f) {
      return JavaDStream$.MODULE$.fromDStream(this.dstream().filter((x) -> BoxesRunTime.boxToBoolean($anonfun$filter$1(f, x))), this.classTag());
   }

   public JavaDStream cache() {
      return JavaDStream$.MODULE$.fromDStream(this.dstream().cache(), this.classTag());
   }

   public JavaDStream persist() {
      return JavaDStream$.MODULE$.fromDStream(this.dstream().persist(), this.classTag());
   }

   public JavaDStream persist(final StorageLevel storageLevel) {
      return JavaDStream$.MODULE$.fromDStream(this.dstream().persist(storageLevel), this.classTag());
   }

   public JavaRDD compute(final Time validTime) {
      Option var3 = this.dstream().compute(validTime);
      if (var3 instanceof Some var4) {
         RDD rdd = (RDD)var4.value();
         return new JavaRDD(rdd, this.classTag());
      } else if (scala.None..MODULE$.equals(var3)) {
         return null;
      } else {
         throw new MatchError(var3);
      }
   }

   public JavaDStream window(final Duration windowDuration) {
      return JavaDStream$.MODULE$.fromDStream(this.dstream().window(windowDuration), this.classTag());
   }

   public JavaDStream window(final Duration windowDuration, final Duration slideDuration) {
      return JavaDStream$.MODULE$.fromDStream(this.dstream().window(windowDuration, slideDuration), this.classTag());
   }

   public JavaDStream union(final JavaDStream that) {
      return JavaDStream$.MODULE$.fromDStream(this.dstream().union(that.dstream()), this.classTag());
   }

   public JavaDStream repartition(final int numPartitions) {
      return JavaDStream$.MODULE$.fromDStream(this.dstream().repartition(numPartitions), this.classTag());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filter$1(final Function f$1, final Object x) {
      return (Boolean)f$1.call(x);
   }

   public JavaDStream(final DStream dstream, final ClassTag classTag) {
      this.dstream = dstream;
      this.classTag = classTag;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
