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
   bytes = "\u0006\u0005Y4QAC\u0006\u0001\u001bUA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\t_\u0001\u0011\t\u0011)A\u0005a!A\u0001\t\u0001B\u0001B\u0003%\u0011\t\u0003\u0005E\u0001\t\r\t\u0015a\u0003F\u0011!Y\u0005AaA!\u0002\u0017a\u0005\"B'\u0001\t\u0003q\u0005\"\u0002,\u0001\t\u0003:\u0006\"\u00021\u0001\t\u0003\n\u0007\"\u00024\u0001\t\u0003:'!F'baB\u000b'\u000f^5uS>tW\r\u001a#TiJ,\u0017-\u001c\u0006\u0003\u00195\tq\u0001Z:ue\u0016\fWN\u0003\u0002\u000f\u001f\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sOV\u0019a#L\u000f\u0014\u0005\u00019\u0002c\u0001\r\u001a75\t1\"\u0003\u0002\u001b\u0017\t9Ai\u0015;sK\u0006l\u0007C\u0001\u000f\u001e\u0019\u0001!QA\b\u0001C\u0002\u0001\u0012\u0011!V\u0002\u0001#\t\ts\u0005\u0005\u0002#K5\t1EC\u0001%\u0003\u0015\u00198-\u00197b\u0013\t13EA\u0004O_RD\u0017N\\4\u0011\u0005\tB\u0013BA\u0015$\u0005\r\te._\u0001\u0007a\u0006\u0014XM\u001c;\u0011\u0007aIB\u0006\u0005\u0002\u001d[\u0011)a\u0006\u0001b\u0001A\t\tA+A\u0006nCB\u0004\u0016M\u001d;Gk:\u001c\u0007\u0003\u0002\u00122g}J!AM\u0012\u0003\u0013\u0019+hn\u0019;j_:\f\u0004c\u0001\u001b=Y9\u0011QG\u000f\b\u0003mej\u0011a\u000e\u0006\u0003q}\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0013\n\u0005m\u001a\u0013a\u00029bG.\fw-Z\u0005\u0003{y\u0012\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003w\r\u00022\u0001\u000e\u001f\u001c\u0003Q\u0001(/Z:feZ,\u0007+\u0019:uSRLwN\\5oOB\u0011!EQ\u0005\u0003\u0007\u000e\u0012qAQ8pY\u0016\fg.\u0001\u0006fm&$WM\\2fIE\u00022AR%-\u001b\u00059%B\u0001%$\u0003\u001d\u0011XM\u001a7fGRL!AS$\u0003\u0011\rc\u0017m]:UC\u001e\f!\"\u001a<jI\u0016t7-\u001a\u00133!\r1\u0015jG\u0001\u0007y%t\u0017\u000e\u001e \u0015\t=\u001bF+\u0016\u000b\u0004!F\u0013\u0006\u0003\u0002\r\u0001YmAQ\u0001\u0012\u0004A\u0004\u0015CQa\u0013\u0004A\u00041CQA\u000b\u0004A\u0002-BQa\f\u0004A\u0002ABQ\u0001\u0011\u0004A\u0002\u0005\u000bA\u0002Z3qK:$WM\\2jKN,\u0012\u0001\u0017\t\u0004ie[\u0016B\u0001.?\u0005\u0011a\u0015n\u001d;1\u0005qs\u0006c\u0001\r\u001a;B\u0011AD\u0018\u0003\n?\u001e\t\t\u0011!A\u0003\u0002\u0001\u00121a\u0018\u00132\u00035\u0019H.\u001b3f\tV\u0014\u0018\r^5p]V\t!\r\u0005\u0002dI6\tQ\"\u0003\u0002f\u001b\tAA)\u001e:bi&|g.A\u0004d_6\u0004X\u000f^3\u0015\u0005!\f\bc\u0001\u0012jW&\u0011!n\t\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u00071|7$D\u0001n\u0015\tqw\"A\u0002sI\u0012L!\u0001]7\u0003\u0007I#E\tC\u0003s\u0013\u0001\u00071/A\u0005wC2LG\rV5nKB\u00111\r^\u0005\u0003k6\u0011A\u0001V5nK\u0002"
)
public class MapPartitionedDStream extends DStream {
   private final DStream parent;
   private final Function1 mapPartFunc;
   private final boolean preservePartitioning;
   private final ClassTag evidence$2;

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public Option compute(final Time validTime) {
      return this.parent.getOrCompute(validTime).map((x$1) -> x$1.mapPartitions(this.mapPartFunc, this.preservePartitioning, this.evidence$2));
   }

   public MapPartitionedDStream(final DStream parent, final Function1 mapPartFunc, final boolean preservePartitioning, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(parent.ssc(), evidence$2);
      this.parent = parent;
      this.mapPartFunc = mapPartFunc;
      this.preservePartitioning = preservePartitioning;
      this.evidence$2 = evidence$2;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
