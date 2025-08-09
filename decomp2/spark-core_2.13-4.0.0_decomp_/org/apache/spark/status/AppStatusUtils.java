package org.apache.spark.status;

import org.apache.spark.status.api.v1.TaskData;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a<a\u0001D\u0007\t\u0002=)bAB\f\u000e\u0011\u0003y\u0001\u0004C\u0003 \u0003\u0011\u0005\u0011\u0005C\u0004#\u0003\t\u0007I\u0011B\u0012\t\rQ\n\u0001\u0015!\u0003%\u0011\u0015)\u0014\u0001\"\u00037\u0011\u0015!\u0015\u0001\"\u0001F\u0011\u0015Q\u0015\u0001\"\u0001L\u0011\u0015!\u0015\u0001\"\u0001N\u0011\u0015Q\u0015\u0001\"\u0001[\u0011\u0015\t\u0016\u0001\"\u0003_\u0011\u0015\u0001\u0017\u0001\"\u0001b\u00039\t\u0005\u000f]*uCR,8/\u0016;jYNT!AD\b\u0002\rM$\u0018\r^;t\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<\u0007C\u0001\f\u0002\u001b\u0005i!AD!qaN#\u0018\r^;t+RLGn]\n\u0003\u0003e\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003U\tA\u0003V!T\u0017~3\u0015JT%T\u0011\u0016#ul\u0015+B)\u0016\u001bV#\u0001\u0013\u0011\u0007\u0015RC&D\u0001'\u0015\t9\u0003&A\u0005j[6,H/\u00192mK*\u0011\u0011fG\u0001\u000bG>dG.Z2uS>t\u0017BA\u0016'\u0005\r\u0019V\r\u001e\t\u0003[Ij\u0011A\f\u0006\u0003_A\nA\u0001\\1oO*\t\u0011'\u0001\u0003kCZ\f\u0017BA\u001a/\u0005\u0019\u0019FO]5oO\u0006)B+Q*L?\u001aKe*S*I\u000b\u0012{6\u000bV!U\u000bN\u0003\u0013AD5t)\u0006\u001c8NR5oSNDW\r\u001a\u000b\u0003oi\u0002\"A\u0007\u001d\n\u0005eZ\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006w\u0015\u0001\r\u0001P\u0001\u0005i\u0006\u001c8\u000e\u0005\u0002>\u00056\taH\u0003\u0002@\u0001\u0006\u0011a/\r\u0006\u0003\u00036\t1!\u00199j\u0013\t\u0019eH\u0001\u0005UCN\\G)\u0019;b\u00039\u00198\r[3ek2,'\u000fR3mCf$\"AR%\u0011\u0005i9\u0015B\u0001%\u001c\u0005\u0011auN\\4\t\u000bm2\u0001\u0019\u0001\u001f\u0002#\u001d,G\u000f^5oOJ+7/\u001e7u)&lW\r\u0006\u0002G\u0019\")1h\u0002a\u0001yQ9aI\u0014)S)ZC\u0006\"B(\t\u0001\u00041\u0015A\u00037bk:\u001c\u0007\u000eV5nK\")\u0011\u000b\u0003a\u0001\r\u0006Qa-\u001a;dQN#\u0018M\u001d;\t\u000bMC\u0001\u0019\u0001$\u0002\u0011\u0011,(/\u0019;j_:DQ!\u0016\u0005A\u0002\u0019\u000bq\u0002Z3tKJL\u0017\r\\5{KRKW.\u001a\u0005\u0006/\"\u0001\rAR\u0001\u000eg\u0016\u0014\u0018.\u00197ju\u0016$\u0016.\\3\t\u000beC\u0001\u0019\u0001$\u0002\u000fI,h\u000eV5nKR!ai\u0017/^\u0011\u0015y\u0015\u00021\u0001G\u0011\u0015\t\u0016\u00021\u0001G\u0011\u0015\u0019\u0016\u00021\u0001G)\t1u\fC\u0003<\u0015\u0001\u0007A(A\thKR\fV/\u00198uS2,7OV1mk\u0016$2AY9t!\r\u00197N\u001c\b\u0003I&t!!\u001a5\u000e\u0003\u0019T!a\u001a\u0011\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0012B\u00016\u001c\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001\\7\u0003\u0015%sG-\u001a=fIN+\u0017O\u0003\u0002k7A\u0011!d\\\u0005\u0003an\u0011a\u0001R8vE2,\u0007\"\u0002:\f\u0001\u0004\u0011\u0017A\u0002<bYV,7\u000fC\u0003u\u0017\u0001\u0007Q/A\u0005rk\u0006tG/\u001b7fgB\u0019!D\u001e8\n\u0005]\\\"!B!se\u0006L\b"
)
public final class AppStatusUtils {
   public static IndexedSeq getQuantilesValue(final IndexedSeq values, final double[] quantiles) {
      return AppStatusUtils$.MODULE$.getQuantilesValue(values, quantiles);
   }

   public static long gettingResultTime(final long launchTime, final long fetchStart, final long duration) {
      return AppStatusUtils$.MODULE$.gettingResultTime(launchTime, fetchStart, duration);
   }

   public static long schedulerDelay(final long launchTime, final long fetchStart, final long duration, final long deserializeTime, final long serializeTime, final long runTime) {
      return AppStatusUtils$.MODULE$.schedulerDelay(launchTime, fetchStart, duration, deserializeTime, serializeTime, runTime);
   }

   public static long gettingResultTime(final TaskData task) {
      return AppStatusUtils$.MODULE$.gettingResultTime(task);
   }

   public static long schedulerDelay(final TaskData task) {
      return AppStatusUtils$.MODULE$.schedulerDelay(task);
   }
}
