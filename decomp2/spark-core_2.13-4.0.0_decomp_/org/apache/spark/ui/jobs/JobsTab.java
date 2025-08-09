package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.JobExecutionStatus;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.config.package$;
import org.apache.spark.scheduler.SchedulingMode$;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.ui.SparkUI;
import org.apache.spark.ui.SparkUITab;
import scala.Option;
import scala.Tuple2;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4Q!\u0004\b\u0001!aA\u0001\"\b\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\tE\u0001\u0011\t\u0011)A\u0005G!)\u0011\u0006\u0001C\u0001U!9q\u0006\u0001b\u0001\n\u0003\u0001\u0004BB\u001e\u0001A\u0003%\u0011\u0007C\u0004=\u0001\t\u0007I\u0011A\u001f\t\r\u0005\u0003\u0001\u0015!\u0003?\u0011\u001d\u0011\u0005A1A\u0005\u0002\rCaa\u0012\u0001!\u0002\u0013!\u0005\"\u0002%\u0001\t\u0003\u0019\u0005\"B%\u0001\t\u0003Q\u0005\"\u0002,\u0001\t\u00039&a\u0002&pEN$\u0016M\u0019\u0006\u0003\u001fA\tAA[8cg*\u0011\u0011CE\u0001\u0003k&T!a\u0005\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005U1\u0012AB1qC\u000eDWMC\u0001\u0018\u0003\ry'oZ\n\u0003\u0001e\u0001\"AG\u000e\u000e\u0003AI!\u0001\b\t\u0003\u0015M\u0003\u0018M]6V\u0013R\u000b'-\u0001\u0004qCJ,g\u000e^\u0002\u0001!\tQ\u0002%\u0003\u0002\"!\t91\u000b]1sWVK\u0015!B:u_J,\u0007C\u0001\u0013(\u001b\u0005)#B\u0001\u0014\u0013\u0003\u0019\u0019H/\u0019;vg&\u0011\u0001&\n\u0002\u000f\u0003B\u00048\u000b^1ukN\u001cFo\u001c:f\u0003\u0019a\u0014N\\5u}Q\u00191&\f\u0018\u0011\u00051\u0002Q\"\u0001\b\t\u000bu\u0019\u0001\u0019A\u0010\t\u000b\t\u001a\u0001\u0019A\u0012\u0002\u0005M\u001cW#A\u0019\u0011\u0007I*t'D\u00014\u0015\u0005!\u0014!B:dC2\f\u0017B\u0001\u001c4\u0005\u0019y\u0005\u000f^5p]B\u0011\u0001(O\u0007\u0002%%\u0011!H\u0005\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\u0004g\u000e\u0004\u0013\u0001B2p]\u001a,\u0012A\u0010\t\u0003q}J!\u0001\u0011\n\u0003\u0013M\u0003\u0018M]6D_:4\u0017!B2p]\u001a\u0004\u0013aC6jY2,e.\u00192mK\u0012,\u0012\u0001\u0012\t\u0003e\u0015K!AR\u001a\u0003\u000f\t{w\u000e\\3b]\u0006a1.\u001b7m\u000b:\f'\r\\3eA\u0005y\u0011n\u001d$bSJ\u001c6\r[3ek2,'/\u0001\u0007hKR\u001c\u0006/\u0019:l+N,'/F\u0001L!\ta5K\u0004\u0002N#B\u0011ajM\u0007\u0002\u001f*\u0011\u0001KH\u0001\u0007yI|w\u000e\u001e \n\u0005I\u001b\u0014A\u0002)sK\u0012,g-\u0003\u0002U+\n11\u000b\u001e:j]\u001eT!AU\u001a\u0002#!\fg\u000e\u001a7f\u0017&dGNU3rk\u0016\u001cH\u000f\u0006\u0002Y7B\u0011!'W\u0005\u00035N\u0012A!\u00168ji\")A\f\u0004a\u0001;\u00069!/Z9vKN$\bC\u00010f\u001b\u0005y&B\u00011b\u0003\u0011AG\u000f\u001e9\u000b\u0005\t\u001c\u0017aB:feZdW\r\u001e\u0006\u0002I\u00069!.Y6beR\f\u0017B\u00014`\u0005IAE\u000f\u001e9TKJ4H.\u001a;SKF,Xm\u001d;"
)
public class JobsTab extends SparkUITab {
   private final SparkUI parent;
   private final AppStatusStore store;
   private final Option sc;
   private final SparkConf conf;
   private final boolean killEnabled;

   public Option sc() {
      return this.sc;
   }

   public SparkConf conf() {
      return this.conf;
   }

   public boolean killEnabled() {
      return this.killEnabled;
   }

   public boolean isFairScheduler() {
      return this.sc().isDefined() && this.store.environmentInfo().sparkProperties().contains(new Tuple2(package$.MODULE$.SCHEDULER_MODE().key(), SchedulingMode$.MODULE$.FAIR().toString()));
   }

   public String getSparkUser() {
      return this.parent.getSparkUser();
   }

   public void handleKillRequest(final HttpServletRequest request) {
      if (this.killEnabled() && this.parent.securityManager().checkModifyPermissions(request.getRemoteUser())) {
         .MODULE$.apply(request.getParameter("id")).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$handleKillRequest$1(x$1))).foreach((JFunction1.mcVI.sp)(id) -> this.store.asOption(() -> this.store.job(id)).foreach((job) -> {
               $anonfun$handleKillRequest$4(this, id, job);
               return BoxedUnit.UNIT;
            }));
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$handleKillRequest$1(final String x$1) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   public static final void $anonfun$handleKillRequest$5(final int id$1, final SparkContext x$2) {
      x$2.cancelJob(id$1, "killed via Web UI");
   }

   // $FF: synthetic method
   public static final void $anonfun$handleKillRequest$4(final JobsTab $this, final int id$1, final JobData job) {
      label14: {
         JobExecutionStatus var10000 = job.status();
         JobExecutionStatus var3 = JobExecutionStatus.RUNNING;
         if (var10000 == null) {
            if (var3 == null) {
               break label14;
            }
         } else if (var10000.equals(var3)) {
            break label14;
         }

         return;
      }

      $this.sc().foreach((x$2) -> {
         $anonfun$handleKillRequest$5(id$1, x$2);
         return BoxedUnit.UNIT;
      });
      Thread.sleep(100L);
   }

   public JobsTab(final SparkUI parent, final AppStatusStore store) {
      super(parent, "jobs");
      this.parent = parent;
      this.store = store;
      this.sc = parent.sc();
      this.conf = parent.conf();
      this.killEnabled = parent.killEnabled();
      this.attachPage(new AllJobsPage(this, store));
      this.attachPage(new JobPage(this, store));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
