package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.MapOutputTrackerMaster;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.CallSite;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed!\u0002\f\u0018\u0001ey\u0002\"\u0003\u0013\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014-\u0011%i\u0003A!A!\u0002\u0013qs\bC\u0005A\u0001\t\u0005\t\u0015!\u0003'\u0003\"I!\t\u0001B\u0001B\u0003%1i\u0014\u0005\n!\u0002\u0011\t\u0011)A\u0005MEC\u0011B\u0015\u0001\u0003\u0002\u0003\u0006IaU-\t\u0011i\u0003!Q1A\u0005\u0002mC\u0001\u0002\u001a\u0001\u0003\u0002\u0003\u0006I\u0001\u0018\u0005\tW\u0002\u0011\t\u0011)A\u0005Y\"Iq\u000e\u0001B\u0001B\u0003%a\u0005\u001d\u0005\u0006c\u0002!\tA\u001d\u0005\t\u0003'\u0001\u0001\u0015)\u0003\u0002\u0016!I\u0011Q\u0004\u0001C\u0002\u0013\u0005\u0011q\u0004\u0005\t\u0003c\u0001\u0001\u0015!\u0003\u0002\"!9\u00111\u0007\u0001\u0005B\u0005U\u0002bBA$\u0001\u0011\u0005\u0011\u0011\n\u0005\b\u0003#\u0002A\u0011AA*\u0011\u001d\ty\u0006\u0001C\u0001\u0003CBq!!\u001a\u0001\t\u0003\t9\u0007C\u0004\u0002j\u0001!\t!a\u001b\t\u000f\u0005M\u0004\u0001\"\u0011\u0002v\ty1\u000b[;gM2,W*\u00199Ti\u0006<WM\u0003\u0002\u00193\u0005I1o\u00195fIVdWM\u001d\u0006\u00035m\tQa\u001d9be.T!\u0001H\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0012aA8sON\u0011\u0001\u0001\t\t\u0003C\tj\u0011aF\u0005\u0003G]\u0011Qa\u0015;bO\u0016\f!!\u001b3\u0004\u0001A\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t\u0019\u0011J\u001c;\n\u0005\u0011\u0012\u0013a\u0001:eIB\u0012qF\u000e\t\u0004aI\"T\"A\u0019\u000b\u00055J\u0012BA\u001a2\u0005\r\u0011F\t\u0012\t\u0003kYb\u0001\u0001B\u00058\u0005\u0005\u0005\t\u0011!B\u0001q\t\u0019q\fJ\u0019\u0012\u0005eb\u0004CA\u0014;\u0013\tY\u0004FA\u0004O_RD\u0017N\\4\u0011\u0005\u001dj\u0014B\u0001 )\u0005\r\te._\u0005\u0003[\t\n\u0001B\\;n)\u0006\u001c8n]\u0005\u0003\u0001\n\nq\u0001]1sK:$8\u000fE\u0002E\u0019\u0002r!!\u0012&\u000f\u0005\u0019KU\"A$\u000b\u0005!+\u0013A\u0002\u001fs_>$h(C\u0001*\u0013\tY\u0005&A\u0004qC\u000e\\\u0017mZ3\n\u00055s%\u0001\u0002'jgRT!a\u0013\u0015\n\u0005\t\u0013\u0013A\u00034jeN$(j\u001c2JI&\u0011\u0001KI\u0001\tG\u0006dGnU5uKB\u0011AkV\u0007\u0002+*\u0011a+G\u0001\u0005kRLG.\u0003\u0002Y+\nA1)\u00197m'&$X-\u0003\u0002SE\u0005Q1\u000f[;gM2,G)\u001a9\u0016\u0003q\u0003D!\u00182gSB)alX1fQ6\t\u0011$\u0003\u0002a3\t\t2\u000b[;gM2,G)\u001a9f]\u0012,gnY=\u0011\u0005U\u0012G!C2\t\u0003\u0003\u0005\tQ!\u00019\u0005\ryFEM\u0001\fg\",hM\u001a7f\t\u0016\u0004\b\u0005\u0005\u00026M\u0012Iq\rCA\u0001\u0002\u0003\u0015\t\u0001\u000f\u0002\u0004?\u0012\u001a\u0004CA\u001bj\t%Q\u0007\"!A\u0001\u0002\u000b\u0005\u0001HA\u0002`IQ\na#\\1q\u001fV$\b/\u001e;Ue\u0006\u001c7.\u001a:NCN$XM\u001d\t\u0003=6L!A\\\r\u0003-5\u000b\u0007oT;uaV$HK]1dW\u0016\u0014X*Y:uKJ\f\u0011C]3t_V\u00148-\u001a)s_\u001aLG.Z%e\u0013\ty'%\u0001\u0004=S:LGO\u0010\u000b\rgR,(p\u001f?~}\u0006=\u0011\u0011\u0003\t\u0003C\u0001AQ\u0001J\u0006A\u0002\u0019BQ!L\u0006A\u0002Y\u0004$a^=\u0011\u0007A\u0012\u0004\u0010\u0005\u00026s\u0012Iq'^A\u0001\u0002\u0003\u0015\t\u0001\u000f\u0005\u0006\u0001.\u0001\rA\n\u0005\u0006\u0005.\u0001\ra\u0011\u0005\u0006!.\u0001\rA\n\u0005\u0006%.\u0001\ra\u0015\u0005\u00065.\u0001\ra \u0019\t\u0003\u0003\t)!!\u0003\u0002\u000eAAalXA\u0002\u0003\u000f\tY\u0001E\u00026\u0003\u000b!\u0011b\u0019@\u0002\u0002\u0003\u0005)\u0011\u0001\u001d\u0011\u0007U\nI\u0001B\u0005h}\u0006\u0005\t\u0011!B\u0001qA\u0019Q'!\u0004\u0005\u0013)t\u0018\u0011!A\u0001\u0006\u0003A\u0004\"B6\f\u0001\u0004a\u0007\"B8\f\u0001\u00041\u0013!D0nCB\u001cF/Y4f\u0015>\u00147\u000f\u0005\u0003E\u0019\u0006]\u0001cA\u0011\u0002\u001a%\u0019\u00111D\f\u0003\u0013\u0005\u001bG/\u001b<f\u0015>\u0014\u0017!\u00059f]\u0012Lgn\u001a)beRLG/[8ogV\u0011\u0011\u0011\u0005\t\u0006\u0003G\tiCJ\u0007\u0003\u0003KQA!a\n\u0002*\u00059Q.\u001e;bE2,'bAA\u0016Q\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005=\u0012Q\u0005\u0002\b\u0011\u0006\u001c\bnU3u\u0003I\u0001XM\u001c3j]\u001e\u0004\u0016M\u001d;ji&|gn\u001d\u0011\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u000e\u0011\t\u0005e\u0012\u0011\t\b\u0005\u0003w\ti\u0004\u0005\u0002GQ%\u0019\u0011q\b\u0015\u0002\rA\u0013X\rZ3g\u0013\u0011\t\u0019%!\u0012\u0003\rM#(/\u001b8h\u0015\r\ty\u0004K\u0001\r[\u0006\u00048\u000b^1hK*{'m]\u000b\u0003\u0003\u0017\u0002R\u0001RA'\u0003/I1!a\u0014O\u0005\r\u0019V-]\u0001\rC\u0012$\u0017i\u0019;jm\u0016TuN\u0019\u000b\u0005\u0003+\nY\u0006E\u0002(\u0003/J1!!\u0017)\u0005\u0011)f.\u001b;\t\u000f\u0005u\u0013\u00031\u0001\u0002\u0018\u0005\u0019!n\u001c2\u0002\u001fI,Wn\u001c<f\u0003\u000e$\u0018N^3K_\n$B!!\u0016\u0002d!9\u0011Q\f\nA\u0002\u0005]\u0011a\u00058v[\u00063\u0018-\u001b7bE2,w*\u001e;qkR\u001cX#\u0001\u0014\u0002\u0017%\u001c\u0018I^1jY\u0006\u0014G.Z\u000b\u0003\u0003[\u00022aJA8\u0013\r\t\t\b\u000b\u0002\b\u0005>|G.Z1o\u0003U1\u0017N\u001c3NSN\u001c\u0018N\\4QCJ$\u0018\u000e^5p]N$\"!a\u001e\u0011\t\u0011\u000biE\n"
)
public class ShuffleMapStage extends Stage {
   private final ShuffleDependency shuffleDep;
   private final MapOutputTrackerMaster mapOutputTrackerMaster;
   private List _mapStageJobs;
   private final HashSet pendingPartitions;

   public ShuffleDependency shuffleDep() {
      return this.shuffleDep;
   }

   public HashSet pendingPartitions() {
      return this.pendingPartitions;
   }

   public String toString() {
      return "ShuffleMapStage " + super.id();
   }

   public Seq mapStageJobs() {
      return this._mapStageJobs;
   }

   public void addActiveJob(final ActiveJob job) {
      this._mapStageJobs = this._mapStageJobs.$colon$colon(job);
   }

   public void removeActiveJob(final ActiveJob job) {
      this._mapStageJobs = this._mapStageJobs.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$removeActiveJob$1(job, x$1)));
   }

   public int numAvailableOutputs() {
      return this.mapOutputTrackerMaster.getNumAvailableOutputs(this.shuffleDep().shuffleId());
   }

   public boolean isAvailable() {
      return this.numAvailableOutputs() == this.numPartitions();
   }

   public Seq findMissingPartitions() {
      return (Seq)this.mapOutputTrackerMaster.findMissingPartitions(this.shuffleDep().shuffleId()).getOrElse(() -> .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.numPartitions()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeActiveJob$1(final ActiveJob job$1, final ActiveJob x$1) {
      boolean var10000;
      label23: {
         if (x$1 == null) {
            if (job$1 != null) {
               break label23;
            }
         } else if (!x$1.equals(job$1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public ShuffleMapStage(final int id, final RDD rdd, final int numTasks, final List parents, final int firstJobId, final CallSite callSite, final ShuffleDependency shuffleDep, final MapOutputTrackerMaster mapOutputTrackerMaster, final int resourceProfileId) {
      super(id, rdd, numTasks, parents, firstJobId, callSite, resourceProfileId);
      this.shuffleDep = shuffleDep;
      this.mapOutputTrackerMaster = mapOutputTrackerMaster;
      this._mapStageJobs = scala.collection.immutable.Nil..MODULE$;
      this.pendingPartitions = new HashSet();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
