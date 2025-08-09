package org.apache.spark.metrics.source;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I<Qa\u0006\r\t\u0002\r2Q!\n\r\t\u0002\u0019BQ\u0001M\u0001\u0005\u0002EBqAM\u0001C\u0002\u0013\u00053\u0007\u0003\u0004@\u0003\u0001\u0006I\u0001\u000e\u0005\b\u0001\u0006\u0011\r\u0011\"\u0011B\u0011\u0019Y\u0015\u0001)A\u0005\u0005\"9A*\u0001b\u0001\n\u0003i\u0005BB)\u0002A\u0003%a\nC\u0004S\u0003\t\u0007I\u0011A'\t\rM\u000b\u0001\u0015!\u0003O\u0011\u001d!\u0016A1A\u0005\u00025Ca!V\u0001!\u0002\u0013q\u0005b\u0002,\u0002\u0005\u0004%\t!\u0014\u0005\u0007/\u0006\u0001\u000b\u0011\u0002(\t\u000fa\u000b!\u0019!C\u0001\u001b\"1\u0011,\u0001Q\u0001\n9CQAW\u0001\u0005\u0002mCQaX\u0001\u0005\u0002\u0001DQAZ\u0001\u0005\u0002\u001dDQ![\u0001\u0005\u0002)DQ\u0001\\\u0001\u0005\u00025DQa\\\u0001\u0005\u0002A\f!\u0003S5wK\u000e\u000bG/\u00197pO6+GO]5dg*\u0011\u0011DG\u0001\u0007g>,(oY3\u000b\u0005ma\u0012aB7fiJL7m\u001d\u0006\u0003;y\tQa\u001d9be.T!a\b\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0013aA8sO\u000e\u0001\u0001C\u0001\u0013\u0002\u001b\u0005A\"A\u0005%jm\u0016\u001c\u0015\r^1m_\u001elU\r\u001e:jGN\u001c2!A\u0014.!\tA3&D\u0001*\u0015\u0005Q\u0013!B:dC2\f\u0017B\u0001\u0017*\u0005\u0019\te.\u001f*fMB\u0011AEL\u0005\u0003_a\u0011aaU8ve\u000e,\u0017A\u0002\u001fj]&$h\bF\u0001$\u0003)\u0019x.\u001e:dK:\u000bW.Z\u000b\u0002iA\u0011Q\u0007\u0010\b\u0003mi\u0002\"aN\u0015\u000e\u0003aR!!\u000f\u0012\u0002\rq\u0012xn\u001c;?\u0013\tY\u0014&\u0001\u0004Qe\u0016$WMZ\u0005\u0003{y\u0012aa\u0015;sS:<'BA\u001e*\u0003-\u0019x.\u001e:dK:\u000bW.\u001a\u0011\u0002\u001d5,GO]5d%\u0016<\u0017n\u001d;ssV\t!\t\u0005\u0002D\u00136\tAI\u0003\u0002\u001c\u000b*\u0011aiR\u0001\tG>$\u0017\r[1mK*\t\u0001*A\u0002d_6L!A\u0013#\u0003\u001d5+GO]5d%\u0016<\u0017n\u001d;ss\u0006yQ.\u001a;sS\u000e\u0014VmZ5tiJL\b%A\rN\u000bR\u0013\u0016jQ0Q\u0003J#\u0016\nV%P\u001dN{f)\u0012+D\u0011\u0016#U#\u0001(\u0011\u0005\r{\u0015B\u0001)E\u0005\u001d\u0019u.\u001e8uKJ\f!$T#U%&\u001bu\fU!S)&#\u0016j\u0014(T?\u001a+Ek\u0011%F\t\u0002\nq#T#U%&\u001buLR%M\u000bN{F)S*D\u001fZ+%+\u0012#\u000215+EKU%D?\u001aKE*R*`\t&\u001b6i\u0014,F%\u0016#\u0005%\u0001\fN\u000bR\u0013\u0016jQ0G\u00132+ulQ!D\u0011\u0016{\u0006*\u0013+T\u0003]iU\t\u0016*J\u0007~3\u0015\nT#`\u0007\u0006\u001b\u0005*R0I\u0013R\u001b\u0006%\u0001\rN\u000bR\u0013\u0016jQ0I\u0013Z+ul\u0011'J\u000b:#vlQ!M\u0019N\u000b\u0011$T#U%&\u001bu\fS%W\u000b~\u001bE*S#O)~\u001b\u0015\t\u0014'TA\u0005\tS*\u0012+S\u0013\u000e{\u0006+\u0011*B\u00192+Ej\u0018'J'RKejR0K\u001f\n{6iT+O)\u0006\u0011S*\u0012+S\u0013\u000e{\u0006+\u0011*B\u00192+Ej\u0018'J'RKejR0K\u001f\n{6iT+O)\u0002\nQA]3tKR$\u0012\u0001\u0018\t\u0003QuK!AX\u0015\u0003\tUs\u0017\u000e^\u0001\u001bS:\u001c'/Z7f]R4U\r^2iK\u0012\u0004\u0016M\u001d;ji&|gn\u001d\u000b\u00039\u0006DQA\u0019\nA\u0002\r\f\u0011A\u001c\t\u0003Q\u0011L!!Z\u0015\u0003\u0007%sG/\u0001\rj]\u000e\u0014X-\\3oi\u001aKG.Z:ESN\u001cwN^3sK\u0012$\"\u0001\u00185\t\u000b\t\u001c\u0002\u0019A2\u0002-%t7M]3nK:$h)\u001b7f\u0007\u0006\u001c\u0007.\u001a%jiN$\"\u0001X6\t\u000b\t$\u0002\u0019A2\u00021%t7M]3nK:$\b*\u001b<f\u00072LWM\u001c;DC2d7\u000f\u0006\u0002]]\")!-\u0006a\u0001G\u0006\u0001\u0013N\\2sK6,g\u000e\u001e)be\u0006dG.\u001a7MSN$\u0018N\\4K_\n\u001cu.\u001e8u)\ta\u0016\u000fC\u0003c-\u0001\u00071\r"
)
public final class HiveCatalogMetrics {
   public static void incrementParallelListingJobCount(final int n) {
      HiveCatalogMetrics$.MODULE$.incrementParallelListingJobCount(n);
   }

   public static void incrementHiveClientCalls(final int n) {
      HiveCatalogMetrics$.MODULE$.incrementHiveClientCalls(n);
   }

   public static void incrementFileCacheHits(final int n) {
      HiveCatalogMetrics$.MODULE$.incrementFileCacheHits(n);
   }

   public static void incrementFilesDiscovered(final int n) {
      HiveCatalogMetrics$.MODULE$.incrementFilesDiscovered(n);
   }

   public static void incrementFetchedPartitions(final int n) {
      HiveCatalogMetrics$.MODULE$.incrementFetchedPartitions(n);
   }

   public static void reset() {
      HiveCatalogMetrics$.MODULE$.reset();
   }

   public static Counter METRIC_PARALLEL_LISTING_JOB_COUNT() {
      return HiveCatalogMetrics$.MODULE$.METRIC_PARALLEL_LISTING_JOB_COUNT();
   }

   public static Counter METRIC_HIVE_CLIENT_CALLS() {
      return HiveCatalogMetrics$.MODULE$.METRIC_HIVE_CLIENT_CALLS();
   }

   public static Counter METRIC_FILE_CACHE_HITS() {
      return HiveCatalogMetrics$.MODULE$.METRIC_FILE_CACHE_HITS();
   }

   public static Counter METRIC_FILES_DISCOVERED() {
      return HiveCatalogMetrics$.MODULE$.METRIC_FILES_DISCOVERED();
   }

   public static Counter METRIC_PARTITIONS_FETCHED() {
      return HiveCatalogMetrics$.MODULE$.METRIC_PARTITIONS_FETCHED();
   }

   public static MetricRegistry metricRegistry() {
      return HiveCatalogMetrics$.MODULE$.metricRegistry();
   }

   public static String sourceName() {
      return HiveCatalogMetrics$.MODULE$.sourceName();
   }
}
