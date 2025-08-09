package org.apache.spark.status;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.spark.SparkConf;
import org.apache.spark.metrics.source.Source;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dd!B\u0015+\u00011\u0012\u0004\"B!\u0001\t\u0003\u0019\u0005b\u0002$\u0001\u0005\u0004%\u0019e\u0012\u0005\u0007#\u0002\u0001\u000b\u0011\u0002%\t\u000fI\u0003!\u0019!C!'\"1A\f\u0001Q\u0001\nQCq!\u0018\u0001C\u0002\u0013\u0005a\f\u0003\u0004c\u0001\u0001\u0006Ia\u0018\u0005\bG\u0002\u0011\r\u0011\"\u0001_\u0011\u0019!\u0007\u0001)A\u0005?\"9Q\r\u0001b\u0001\n\u00031\u0007B\u00026\u0001A\u0003%q\rC\u0004l\u0001\t\u0007I\u0011\u00014\t\r1\u0004\u0001\u0015!\u0003h\u0011\u001di\u0007A1A\u0005\u0002\u0019DaA\u001c\u0001!\u0002\u00139\u0007bB8\u0001\u0005\u0004%\tA\u001a\u0005\u0007a\u0002\u0001\u000b\u0011B4\t\u000fE\u0004!\u0019!C\u0001M\"1!\u000f\u0001Q\u0001\n\u001dDqa\u001d\u0001C\u0002\u0013\u0005a\r\u0003\u0004u\u0001\u0001\u0006Ia\u001a\u0005\bk\u0002\u0011\r\u0011\"\u0001g\u0011\u00191\b\u0001)A\u0005O\"9q\u000f\u0001b\u0001\n\u00031\u0007B\u0002=\u0001A\u0003%q\rC\u0004z\u0001\t\u0007I\u0011\u00014\t\ri\u0004\u0001\u0015!\u0003h\u0011\u001dY\bA1A\u0005\u0002\u0019Dq!!\u0004\u0001A\u0003%q\r\u0003\u0005\u0002\u0012\u0001\u0011\r\u0011\"\u0001g\u0011\u001d\tI\u0002\u0001Q\u0001\n\u001dD\u0001\"!\b\u0001\u0005\u0004%\tA\u001a\u0005\b\u0003?\u0001\u0001\u0015!\u0003h\u0011!\t\t\u0003\u0001b\u0001\n\u00031\u0007bBA\u0012\u0001\u0001\u0006IaZ\u0004\t\u0003KQ\u0003\u0012\u0001\u0017\u0002(\u00199\u0011F\u000bE\u0001Y\u0005%\u0002BB!&\t\u0003\tY\u0003C\u0004\u0002.\u0015\"\t!a\f\t\u000f\u0005ES\u0005\"\u0001\u0002T\ty\u0011\t\u001d9Ti\u0006$Xo]*pkJ\u001cWM\u0003\u0002,Y\u000511\u000f^1ukNT!!\f\u0018\u0002\u000bM\u0004\u0018M]6\u000b\u0005=\u0002\u0014AB1qC\u000eDWMC\u00012\u0003\ry'oZ\n\u0004\u0001MJ\u0004C\u0001\u001b8\u001b\u0005)$\"\u0001\u001c\u0002\u000bM\u001c\u0017\r\\1\n\u0005a*$AB!osJ+g\r\u0005\u0002;\u007f5\t1H\u0003\u0002={\u000511o\\;sG\u0016T!A\u0010\u0017\u0002\u000f5,GO]5dg&\u0011\u0001i\u000f\u0002\u0007'>,(oY3\u0002\rqJg.\u001b;?\u0007\u0001!\u0012\u0001\u0012\t\u0003\u000b\u0002i\u0011AK\u0001\u000f[\u0016$(/[2SK\u001eL7\u000f\u001e:z+\u0005A\u0005CA%P\u001b\u0005Q%B\u0001 L\u0015\taU*\u0001\u0005d_\u0012\f\u0007.\u00197f\u0015\u0005q\u0015aA2p[&\u0011\u0001K\u0013\u0002\u000f\u001b\u0016$(/[2SK\u001eL7\u000f\u001e:z\u0003=iW\r\u001e:jGJ+w-[:uef\u0004\u0013AC:pkJ\u001cWMT1nKV\tA\u000b\u0005\u0002V56\taK\u0003\u0002X1\u0006!A.\u00198h\u0015\u0005I\u0016\u0001\u00026bm\u0006L!a\u0017,\u0003\rM#(/\u001b8h\u0003-\u0019x.\u001e:dK:\u000bW.\u001a\u0011\u0002\u0017)|'\rR;sCRLwN\\\u000b\u0002?B\u0011Q\tY\u0005\u0003C*\u00121BS8c\tV\u0014\u0018\r^5p]\u0006a!n\u001c2EkJ\fG/[8oA\u0005a!j\u0014\"`\tV\u0013\u0016\tV%P\u001d\u0006i!j\u0014\"`\tV\u0013\u0016\tV%P\u001d\u0002\nQBR!J\u0019\u0016#ul\u0015+B\u000f\u0016\u001bV#A4\u0011\u0005%C\u0017BA5K\u0005\u001d\u0019u.\u001e8uKJ\faBR!J\u0019\u0016#ul\u0015+B\u000f\u0016\u001b\u0006%\u0001\bT\u0017&\u0003\u0006+\u0012#`'R\u000bu)R*\u0002\u001fM[\u0015\n\u0015)F\t~\u001bF+Q$F'\u0002\n\u0001cQ(N!2+E+\u0012#`'R\u000bu)R*\u0002#\r{U\n\u0015'F)\u0016#ul\u0015+B\u000f\u0016\u001b\u0006%\u0001\bT+\u000e\u001bU)\u0012#F\t~SuJQ*\u0002\u001fM+6iQ#F\t\u0016#uLS(C'\u0002\n1BR!J\u0019\u0016#uLS(C'\u0006aa)Q%M\u000b\u0012{&j\u0014\"TA\u0005y1iT'Q\u0019\u0016#V\tR0U\u0003N[5+\u0001\tD\u001f6\u0003F*\u0012+F\t~#\u0016iU&TA\u0005aa)Q%M\u000b\u0012{F+Q*L'\u0006ia)Q%M\u000b\u0012{F+Q*L'\u0002\nAbS%M\u0019\u0016#u\fV!T\u0017N\u000bQbS%M\u0019\u0016#u\fV!T\u0017N\u0003\u0013!D*L\u0013B\u0003V\tR0U\u0003N[5+\u0001\bT\u0017&\u0003\u0006+\u0012#`)\u0006\u001b6j\u0015\u0011\u0002+\tc\u0015iQ&M\u0013N#V\tR0F1\u0016\u001bU\u000bV(S'\"RA$`A\u0001\u0003\u0007\t9!!\u0003\u0011\u0005Qr\u0018BA@6\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t\t)!A\u000fvg\u0016\u0004S\r_2mk\u0012,G-\u0012=fGV$xN]:!S:\u001cH/Z1e\u0003\u0015\u0019\u0018N\\2fC\t\tY!A\u00034]Er\u0003'\u0001\fC\u0019\u0006\u001b5\nT%T)\u0016#u,\u0012-F\u0007V#vJU*!Q)iR0!\u0001\u0002\u0004\u0005\u001d\u0011\u0011B\u0001\u0018+:\u0013E*Q\"L\u0019&\u001bF+\u0012#`\u000bb+5)\u0016+P%NC#BH?\u0002\u0002\u0005U\u0011qAA\u0005C\t\t9\"A\u0010vg\u0016\u0004SO\\3yG2,H-\u001a3Fq\u0016\u001cW\u000f^8sg\u0002Jgn\u001d;fC\u0012\f\u0001$\u0016(C\u0019\u0006\u001b5\nT%T)\u0016#u,\u0012-F\u0007V#vJU*!Q)yR0!\u0001\u0002\u0016\u0005\u001d\u0011\u0011B\u0001\u0013\u000bb\u001bE*\u0016#F\t~+\u0005,R\"V)>\u00136+A\nF1\u000ecU\u000bR#E?\u0016CViQ+U\u001fJ\u001b\u0006%\u0001\u000bV\u001d\u0016C6\tT+E\u000b\u0012{V\tW#D+R{%kU\u0001\u0016+:+\u0005l\u0011'V\t\u0016#u,\u0012-F\u0007V#vJU*!\u0003=\t\u0005\u000f]*uCR,8oU8ve\u000e,\u0007CA#&'\t)3\u0007\u0006\u0002\u0002(\u0005Qq-\u001a;D_VtG/\u001a:\u0015\r\u0005E\u0012QGA')\r9\u00171\u0007\u0005\u0006\r\u001e\u0002\u001d\u0001\u0013\u0005\b\u0003o9\u0003\u0019AA\u001d\u0003\u0019\u0001(/\u001a4jqB!\u00111HA%\u001d\u0011\ti$!\u0012\u0011\u0007\u0005}R'\u0004\u0002\u0002B)\u0019\u00111\t\"\u0002\rq\u0012xn\u001c;?\u0013\r\t9%N\u0001\u0007!J,G-\u001a4\n\u0007m\u000bYEC\u0002\u0002HUBq!a\u0014(\u0001\u0004\tI$\u0001\u0003oC6,\u0017\u0001D2sK\u0006$XmU8ve\u000e,G\u0003BA+\u00037\u0002B\u0001NA,\t&\u0019\u0011\u0011L\u001b\u0003\r=\u0003H/[8o\u0011\u001d\ti\u0006\u000ba\u0001\u0003?\nAaY8oMB!\u0011\u0011MA2\u001b\u0005a\u0013bAA3Y\tI1\u000b]1sW\u000e{gN\u001a"
)
public class AppStatusSource implements Source {
   private final MetricRegistry metricRegistry = new MetricRegistry();
   private final String sourceName = "appStatus";
   private final JobDuration jobDuration = new JobDuration(new AtomicLong(0L));
   private final JobDuration JOB_DURATION = (JobDuration)this.metricRegistry().register(MetricRegistry.name("jobDuration", new String[0]), this.jobDuration());
   private final Counter FAILED_STAGES;
   private final Counter SKIPPED_STAGES;
   private final Counter COMPLETED_STAGES;
   private final Counter SUCCEEDED_JOBS;
   private final Counter FAILED_JOBS;
   private final Counter COMPLETED_TASKS;
   private final Counter FAILED_TASKS;
   private final Counter KILLED_TASKS;
   private final Counter SKIPPED_TASKS;
   /** @deprecated */
   private final Counter BLACKLISTED_EXECUTORS;
   /** @deprecated */
   private final Counter UNBLACKLISTED_EXECUTORS;
   private final Counter EXCLUDED_EXECUTORS;
   private final Counter UNEXCLUDED_EXECUTORS;

   public static Option createSource(final SparkConf conf) {
      return AppStatusSource$.MODULE$.createSource(conf);
   }

   public static Counter getCounter(final String prefix, final String name, final MetricRegistry metricRegistry) {
      return AppStatusSource$.MODULE$.getCounter(prefix, name, metricRegistry);
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public String sourceName() {
      return this.sourceName;
   }

   public JobDuration jobDuration() {
      return this.jobDuration;
   }

   public JobDuration JOB_DURATION() {
      return this.JOB_DURATION;
   }

   public Counter FAILED_STAGES() {
      return this.FAILED_STAGES;
   }

   public Counter SKIPPED_STAGES() {
      return this.SKIPPED_STAGES;
   }

   public Counter COMPLETED_STAGES() {
      return this.COMPLETED_STAGES;
   }

   public Counter SUCCEEDED_JOBS() {
      return this.SUCCEEDED_JOBS;
   }

   public Counter FAILED_JOBS() {
      return this.FAILED_JOBS;
   }

   public Counter COMPLETED_TASKS() {
      return this.COMPLETED_TASKS;
   }

   public Counter FAILED_TASKS() {
      return this.FAILED_TASKS;
   }

   public Counter KILLED_TASKS() {
      return this.KILLED_TASKS;
   }

   public Counter SKIPPED_TASKS() {
      return this.SKIPPED_TASKS;
   }

   /** @deprecated */
   public Counter BLACKLISTED_EXECUTORS() {
      return this.BLACKLISTED_EXECUTORS;
   }

   /** @deprecated */
   public Counter UNBLACKLISTED_EXECUTORS() {
      return this.UNBLACKLISTED_EXECUTORS;
   }

   public Counter EXCLUDED_EXECUTORS() {
      return this.EXCLUDED_EXECUTORS;
   }

   public Counter UNEXCLUDED_EXECUTORS() {
      return this.UNEXCLUDED_EXECUTORS;
   }

   public AppStatusSource() {
      this.FAILED_STAGES = AppStatusSource$.MODULE$.getCounter("stages", "failedStages", this.metricRegistry());
      this.SKIPPED_STAGES = AppStatusSource$.MODULE$.getCounter("stages", "skippedStages", this.metricRegistry());
      this.COMPLETED_STAGES = AppStatusSource$.MODULE$.getCounter("stages", "completedStages", this.metricRegistry());
      this.SUCCEEDED_JOBS = AppStatusSource$.MODULE$.getCounter("jobs", "succeededJobs", this.metricRegistry());
      this.FAILED_JOBS = AppStatusSource$.MODULE$.getCounter("jobs", "failedJobs", this.metricRegistry());
      this.COMPLETED_TASKS = AppStatusSource$.MODULE$.getCounter("tasks", "completedTasks", this.metricRegistry());
      this.FAILED_TASKS = AppStatusSource$.MODULE$.getCounter("tasks", "failedTasks", this.metricRegistry());
      this.KILLED_TASKS = AppStatusSource$.MODULE$.getCounter("tasks", "killedTasks", this.metricRegistry());
      this.SKIPPED_TASKS = AppStatusSource$.MODULE$.getCounter("tasks", "skippedTasks", this.metricRegistry());
      this.BLACKLISTED_EXECUTORS = AppStatusSource$.MODULE$.getCounter("tasks", "blackListedExecutors", this.metricRegistry());
      this.UNBLACKLISTED_EXECUTORS = AppStatusSource$.MODULE$.getCounter("tasks", "unblackListedExecutors", this.metricRegistry());
      this.EXCLUDED_EXECUTORS = AppStatusSource$.MODULE$.getCounter("tasks", "excludedExecutors", this.metricRegistry());
      this.UNEXCLUDED_EXECUTORS = AppStatusSource$.MODULE$.getCounter("tasks", "unexcludedExecutors", this.metricRegistry());
   }
}
