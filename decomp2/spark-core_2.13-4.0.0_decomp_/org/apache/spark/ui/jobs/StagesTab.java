package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.scheduler.SchedulingMode$;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.status.api.v1.StageStatus;
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
   bytes = "\u0006\u0005\r4Q\u0001E\t\u0001'mA\u0001\u0002\t\u0001\u0003\u0006\u0004%\tA\t\u0005\tM\u0001\u0011\t\u0011)A\u0005G!Aq\u0005\u0001BC\u0002\u0013\u0005\u0001\u0006\u0003\u00050\u0001\t\u0005\t\u0015!\u0003*\u0011\u0015\u0001\u0004\u0001\"\u00012\u0011\u001d1\u0004A1A\u0005\u0002]BaA\u0011\u0001!\u0002\u0013A\u0004bB\"\u0001\u0005\u0004%\t\u0001\u0012\u0005\u0007\u0011\u0002\u0001\u000b\u0011B#\t\u000f%\u0003!\u0019!C\u0001\u0015\"1a\n\u0001Q\u0001\n-Cqa\u0014\u0001C\u0002\u0013\u0005!\n\u0003\u0004Q\u0001\u0001\u0006Ia\u0013\u0005\u0006#\u0002!\tA\u0013\u0005\u0006%\u0002!\ta\u0015\u0002\n'R\fw-Z:UC\nT!AE\n\u0002\t)|'m\u001d\u0006\u0003)U\t!!^5\u000b\u0005Y9\u0012!B:qCJ\\'B\u0001\r\u001a\u0003\u0019\t\u0007/Y2iK*\t!$A\u0002pe\u001e\u001c\"\u0001\u0001\u000f\u0011\u0005uqR\"A\n\n\u0005}\u0019\"AC*qCJ\\W+\u0013+bE\u00061\u0001/\u0019:f]R\u001c\u0001!F\u0001$!\tiB%\u0003\u0002&'\t91\u000b]1sWVK\u0015a\u00029be\u0016tG\u000fI\u0001\u0006gR|'/Z\u000b\u0002SA\u0011!&L\u0007\u0002W)\u0011A&F\u0001\u0007gR\fG/^:\n\u00059Z#AD!qaN#\u0018\r^;t'R|'/Z\u0001\u0007gR|'/\u001a\u0011\u0002\rqJg.\u001b;?)\r\u0011D'\u000e\t\u0003g\u0001i\u0011!\u0005\u0005\u0006A\u0015\u0001\ra\t\u0005\u0006O\u0015\u0001\r!K\u0001\u0003g\u000e,\u0012\u0001\u000f\t\u0004sqrT\"\u0001\u001e\u000b\u0003m\nQa]2bY\u0006L!!\u0010\u001e\u0003\r=\u0003H/[8o!\ty\u0004)D\u0001\u0016\u0013\t\tUC\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH/A\u0002tG\u0002\nAaY8oMV\tQ\t\u0005\u0002@\r&\u0011q)\u0006\u0002\n'B\f'o[\"p]\u001a\fQaY8oM\u0002\n1b[5mY\u0016s\u0017M\u00197fIV\t1\n\u0005\u0002:\u0019&\u0011QJ\u000f\u0002\b\u0005>|G.Z1o\u00031Y\u0017\u000e\u001c7F]\u0006\u0014G.\u001a3!\u0003E!\bN]3bI\u0012+X\u000e]#oC\ndW\rZ\u0001\u0013i\"\u0014X-\u00193Ek6\u0004XI\\1cY\u0016$\u0007%A\bjg\u001a\u000b\u0017N]*dQ\u0016$W\u000f\\3s\u0003EA\u0017M\u001c3mK.KG\u000e\u001c*fcV,7\u000f\u001e\u000b\u0003)^\u0003\"!O+\n\u0005YS$\u0001B+oSRDQ\u0001W\bA\u0002e\u000bqA]3rk\u0016\u001cH\u000f\u0005\u0002[C6\t1L\u0003\u0002];\u0006!\u0001\u000e\u001e;q\u0015\tqv,A\u0004tKJ4H.\u001a;\u000b\u0003\u0001\fqA[1lCJ$\u0018-\u0003\u0002c7\n\u0011\u0002\n\u001e;q'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u\u0001"
)
public class StagesTab extends SparkUITab {
   private final SparkUI parent;
   private final AppStatusStore store;
   private final Option sc;
   private final SparkConf conf;
   private final boolean killEnabled;
   private final boolean threadDumpEnabled;

   public SparkUI parent() {
      return this.parent;
   }

   public AppStatusStore store() {
      return this.store;
   }

   public Option sc() {
      return this.sc;
   }

   public SparkConf conf() {
      return this.conf;
   }

   public boolean killEnabled() {
      return this.killEnabled;
   }

   public boolean threadDumpEnabled() {
      return this.threadDumpEnabled;
   }

   public boolean isFairScheduler() {
      return this.sc().isDefined() && this.store().environmentInfo().sparkProperties().contains(new Tuple2(package$.MODULE$.SCHEDULER_MODE().key(), SchedulingMode$.MODULE$.FAIR().toString()));
   }

   public void handleKillRequest(final HttpServletRequest request) {
      if (this.killEnabled() && this.parent().securityManager().checkModifyPermissions(request.getRemoteUser())) {
         .MODULE$.apply(request.getParameter("id")).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$handleKillRequest$1(x$1))).foreach((JFunction1.mcVI.sp)(id) -> this.store().asOption(() -> this.store().lastStageAttempt(id)).foreach((stage) -> {
               $anonfun$handleKillRequest$4(this, id, stage);
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
      x$2.cancelStage(id$1, "killed via the Web UI");
   }

   // $FF: synthetic method
   public static final void $anonfun$handleKillRequest$4(final StagesTab $this, final int id$1, final StageData stage) {
      label24: {
         StageStatus status = stage.status();
         StageStatus var4 = StageStatus.ACTIVE;
         if (status == null) {
            if (var4 == null) {
               break label24;
            }
         } else if (status.equals(var4)) {
            break label24;
         }

         StageStatus var5 = StageStatus.PENDING;
         if (status == null) {
            if (var5 == null) {
               break label24;
            }
         } else if (status.equals(var5)) {
            break label24;
         }

         return;
      }

      $this.sc().foreach((x$2) -> {
         $anonfun$handleKillRequest$5(id$1, x$2);
         return BoxedUnit.UNIT;
      });
      Thread.sleep(100L);
   }

   public StagesTab(final SparkUI parent, final AppStatusStore store) {
      super(parent, "stages");
      this.parent = parent;
      this.store = store;
      this.sc = parent.sc();
      this.conf = parent.conf();
      this.killEnabled = parent.killEnabled();
      this.threadDumpEnabled = parent.sc().isDefined() && BoxesRunTime.unboxToBoolean(parent.conf().get(UI$.MODULE$.UI_THREAD_DUMPS_ENABLED()));
      this.attachPage(new AllStagesPage(this));
      this.attachPage(new StagePage(this, store));
      this.attachPage(new PoolPage(this));
      if (this.threadDumpEnabled()) {
         this.attachPage(new TaskThreadDumpPage(this, this.sc()));
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
