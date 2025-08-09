package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Properties;
import org.apache.spark.JobArtifactSet;
import org.apache.spark.util.CallSite;
import scala.MatchError;
import scala.Array.;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005A4Q!\u0006\f\u00011yA\u0001\"\n\u0001\u0003\u0006\u0004%\ta\n\u0005\tW\u0001\u0011\t\u0011)A\u0005Q!AA\u0006\u0001BC\u0002\u0013\u0005Q\u0006\u0003\u00053\u0001\t\u0005\t\u0015!\u0003/\u0011!\u0019\u0004A!b\u0001\n\u0003!\u0004\u0002C\u001e\u0001\u0005\u0003\u0005\u000b\u0011B\u001b\t\u0011q\u0002!Q1A\u0005\u0002uB\u0001\"\u0011\u0001\u0003\u0002\u0003\u0006IA\u0010\u0005\t\u0005\u0002\u0011)\u0019!C\u0001\u0007\"A\u0001\n\u0001B\u0001B\u0003%A\t\u0003\u0005J\u0001\t\u0015\r\u0011\"\u0001K\u0011!\u0011\u0006A!A!\u0002\u0013Y\u0005\"B*\u0001\t\u0003!\u0006b\u0002/\u0001\u0005\u0004%\ta\n\u0005\u0007;\u0002\u0001\u000b\u0011\u0002\u0015\t\u000fy\u0003!\u0019!C\u0001?\"1a\r\u0001Q\u0001\n\u0001Dqa\u001a\u0001A\u0002\u0013\u0005q\u0005C\u0004i\u0001\u0001\u0007I\u0011A5\t\r=\u0004\u0001\u0015)\u0003)\u0005%\t5\r^5wK*{'M\u0003\u0002\u00181\u0005I1o\u00195fIVdWM\u001d\u0006\u00033i\tQa\u001d9be.T!a\u0007\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0012aA8sON\u0011\u0001a\b\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\u0007\u0003:L(+\u001a4\u0002\u000b)|'-\u00133\u0004\u0001U\t\u0001\u0006\u0005\u0002!S%\u0011!&\t\u0002\u0004\u0013:$\u0018A\u00026pE&#\u0007%\u0001\u0006gS:\fGn\u0015;bO\u0016,\u0012A\f\t\u0003_Aj\u0011AF\u0005\u0003cY\u0011Qa\u0015;bO\u0016\f1BZ5oC2\u001cF/Y4fA\u0005A1-\u00197m'&$X-F\u00016!\t1\u0014(D\u00018\u0015\tA\u0004$\u0001\u0003vi&d\u0017B\u0001\u001e8\u0005!\u0019\u0015\r\u001c7TSR,\u0017!C2bY2\u001c\u0016\u000e^3!\u0003!a\u0017n\u001d;f]\u0016\u0014X#\u0001 \u0011\u0005=z\u0014B\u0001!\u0017\u0005-QuN\u0019'jgR,g.\u001a:\u0002\u00131L7\u000f^3oKJ\u0004\u0013!C1si&4\u0017m\u0019;t+\u0005!\u0005CA#G\u001b\u0005A\u0012BA$\u0019\u00059QuNY!si&4\u0017m\u0019;TKR\f!\"\u0019:uS\u001a\f7\r^:!\u0003)\u0001(o\u001c9feRLWm]\u000b\u0002\u0017B\u0011A\nU\u0007\u0002\u001b*\u0011\u0001H\u0014\u0006\u0002\u001f\u0006!!.\u0019<b\u0013\t\tVJ\u0001\u0006Qe>\u0004XM\u001d;jKN\f1\u0002\u001d:pa\u0016\u0014H/[3tA\u00051A(\u001b8jiz\"r!\u0016,X1fS6\f\u0005\u00020\u0001!)Q%\u0004a\u0001Q!)A&\u0004a\u0001]!)1'\u0004a\u0001k!)A(\u0004a\u0001}!)!)\u0004a\u0001\t\")\u0011*\u0004a\u0001\u0017\u0006ia.^7QCJ$\u0018\u000e^5p]N\faB\\;n!\u0006\u0014H/\u001b;j_:\u001c\b%\u0001\u0005gS:L7\u000f[3e+\u0005\u0001\u0007c\u0001\u0011bG&\u0011!-\t\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003A\u0011L!!Z\u0011\u0003\u000f\t{w\u000e\\3b]\u0006Ia-\u001b8jg\",G\rI\u0001\f]Vlg)\u001b8jg\",G-A\bok64\u0015N\\5tQ\u0016$w\fJ3r)\tQW\u000e\u0005\u0002!W&\u0011A.\t\u0002\u0005+:LG\u000fC\u0004o'\u0005\u0005\t\u0019\u0001\u0015\u0002\u0007a$\u0013'\u0001\u0007ok64\u0015N\\5tQ\u0016$\u0007\u0005"
)
public class ActiveJob {
   private final int jobId;
   private final Stage finalStage;
   private final CallSite callSite;
   private final JobListener listener;
   private final JobArtifactSet artifacts;
   private final Properties properties;
   private final int numPartitions;
   private final boolean[] finished;
   private int numFinished;

   public int jobId() {
      return this.jobId;
   }

   public Stage finalStage() {
      return this.finalStage;
   }

   public CallSite callSite() {
      return this.callSite;
   }

   public JobListener listener() {
      return this.listener;
   }

   public JobArtifactSet artifacts() {
      return this.artifacts;
   }

   public Properties properties() {
      return this.properties;
   }

   public int numPartitions() {
      return this.numPartitions;
   }

   public boolean[] finished() {
      return this.finished;
   }

   public int numFinished() {
      return this.numFinished;
   }

   public void numFinished_$eq(final int x$1) {
      this.numFinished = x$1;
   }

   public ActiveJob(final int jobId, final Stage finalStage, final CallSite callSite, final JobListener listener, final JobArtifactSet artifacts, final Properties properties) {
      this.jobId = jobId;
      this.finalStage = finalStage;
      this.callSite = callSite;
      this.listener = listener;
      this.artifacts = artifacts;
      this.properties = properties;
      int var10001;
      if (finalStage instanceof ResultStage var9) {
         var10001 = var9.partitions().length;
      } else {
         if (!(finalStage instanceof ShuffleMapStage)) {
            throw new MatchError(finalStage);
         }

         ShuffleMapStage var10 = (ShuffleMapStage)finalStage;
         var10001 = var10.numPartitions();
      }

      this.numPartitions = var10001;
      this.finished = (boolean[]).MODULE$.fill(this.numPartitions(), (JFunction0.mcZ.sp)() -> false, scala.reflect.ClassTag..MODULE$.Boolean());
      this.numFinished = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
