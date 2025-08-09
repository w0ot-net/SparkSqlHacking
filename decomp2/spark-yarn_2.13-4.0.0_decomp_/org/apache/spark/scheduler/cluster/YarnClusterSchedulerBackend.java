package org.apache.spark.scheduler.cluster;

import org.apache.hadoop.yarn.api.records.ApplicationAttemptId;
import org.apache.spark.SparkContext;
import org.apache.spark.deploy.yarn.ApplicationMaster$;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import org.apache.spark.scheduler.cluster.SchedulerBackendUtils.;
import org.apache.spark.util.YarnContainerInfoHelper$;
import scala.Option;
import scala.Some;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3Q\u0001C\u0005\u0001\u001bMA\u0001\u0002\u0004\u0001\u0003\u0002\u0003\u0006I!\u0007\u0005\t;\u0001\u0011\t\u0011)A\u0005=!)!\u0005\u0001C\u0001G!)q\u0005\u0001C!Q!)q\u0006\u0001C!a!)a\u0007\u0001C!o!)\u0011\n\u0001C!o\tY\u0012,\u0019:o\u00072,8\u000f^3s'\u000eDW\rZ;mKJ\u0014\u0015mY6f]\u0012T!AC\u0006\u0002\u000f\rdWo\u001d;fe*\u0011A\"D\u0001\ng\u000eDW\rZ;mKJT!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\n\u0003\u0001Q\u0001\"!\u0006\f\u000e\u0003%I!aF\u0005\u0003)e\u000b'O\\*dQ\u0016$W\u000f\\3s\u0005\u0006\u001c7.\u001a8e\u0007\u0001\u0001\"AG\u000e\u000e\u0003-I!\u0001H\u0006\u0003#Q\u000b7o[*dQ\u0016$W\u000f\\3s\u00136\u0004H.\u0001\u0002tGB\u0011q\u0004I\u0007\u0002\u001b%\u0011\u0011%\u0004\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u0011*c\u0005\u0005\u0002\u0016\u0001!)Ab\u0001a\u00013!)Qd\u0001a\u0001=\u0005)1\u000f^1siR\t\u0011\u0006\u0005\u0002+[5\t1FC\u0001-\u0003\u0015\u00198-\u00197b\u0013\tq3F\u0001\u0003V]&$\u0018\u0001B:u_B$\"!K\u0019\t\u000bI*\u0001\u0019A\u001a\u0002\u0011\u0015D\u0018\u000e^\"pI\u0016\u0004\"A\u000b\u001b\n\u0005UZ#aA%oi\u0006\u0001r-\u001a;Ee&4XM\u001d'pOV\u0013Hn]\u000b\u0002qA\u0019!&O\u001e\n\u0005iZ#AB(qi&|g\u000e\u0005\u0003=\u0007\u001a3eBA\u001fB!\tq4&D\u0001@\u0015\t\u0001\u0005$\u0001\u0004=e>|GOP\u0005\u0003\u0005.\na\u0001\u0015:fI\u00164\u0017B\u0001#F\u0005\ri\u0015\r\u001d\u0006\u0003\u0005.\u0002\"\u0001P$\n\u0005!+%AB*ue&tw-A\nhKR$%/\u001b<fe\u0006#HO]5ckR,7\u000f"
)
public class YarnClusterSchedulerBackend extends YarnSchedulerBackend {
   private final SparkContext sc;

   public void start() {
      ApplicationAttemptId attemptId = ApplicationMaster$.MODULE$.getAttemptId();
      this.bindToYarn(attemptId.getApplicationId(), new Some(attemptId));
      super.start();
      this.totalExpectedExecutors_$eq(.MODULE$.getInitialTargetExecutorNumber(this.sc.conf(), .MODULE$.getInitialTargetExecutorNumber$default$2()));
      this.startBindings();
   }

   public void stop(final int exitCode) {
      this.yarnSchedulerEndpoint().signalDriverStop(exitCode);
      super.stop();
   }

   public Option getDriverLogUrls() {
      return YarnContainerInfoHelper$.MODULE$.getLogUrls(this.sc.hadoopConfiguration(), scala.None..MODULE$);
   }

   public Option getDriverAttributes() {
      return YarnContainerInfoHelper$.MODULE$.getAttributes(this.sc.hadoopConfiguration(), scala.None..MODULE$);
   }

   public YarnClusterSchedulerBackend(final TaskSchedulerImpl scheduler, final SparkContext sc) {
      super(scheduler, sc);
      this.sc = sc;
   }
}
