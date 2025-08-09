package org.apache.spark.deploy.history;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerExecutorAdded;
import org.apache.spark.scheduler.SparkListenerExecutorRemoved;
import org.apache.spark.scheduler.SparkListenerJobEnd;
import org.apache.spark.scheduler.SparkListenerJobStart;
import org.apache.spark.scheduler.SparkListenerStageSubmitted;
import org.apache.spark.scheduler.SparkListenerTaskStart;
import org.apache.spark.storage.RDDInfo;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055d!B\u0010!\u0001\u0011R\u0003\"B\u001b\u0001\t\u00039\u0004bB\u001d\u0001\u0005\u0004%IA\u000f\u0005\u0007)\u0002\u0001\u000b\u0011B\u001e\t\u000fU\u0003!\u0019!C\u0005-\"1Q\f\u0001Q\u0001\n]CqA\u0018\u0001C\u0002\u0013%!\b\u0003\u0004`\u0001\u0001\u0006Ia\u000f\u0005\bA\u0002\u0011\r\u0011\"\u0003b\u0011\u0019A\u0007\u0001)A\u0005E\"9\u0011\u000e\u0001a\u0001\n\u0013Q\u0007bB6\u0001\u0001\u0004%I\u0001\u001c\u0005\u0007e\u0002\u0001\u000b\u0015\u0002.\t\u000fM\u0004\u0001\u0019!C\u0005U\"9A\u000f\u0001a\u0001\n\u0013)\bBB<\u0001A\u0003&!\fC\u0004y\u0001\u0001\u0007I\u0011\u00026\t\u000fe\u0004\u0001\u0019!C\u0005u\"1A\u0010\u0001Q!\niCa! \u0001\u0005\u0002\u0001r\bBB@\u0001\t\u0003\u0001c\u0010\u0003\u0005\u0002\u0002\u0001!\t\u0001IA\u0002\u0011\u001d\t9\u0001\u0001C\u0001AyD\u0001\"!\u0003\u0001\t\u0003\u0001\u00131\u0002\u0005\b\u0003\u001f\u0001A\u0011IA\t\u0011\u001d\ti\u0002\u0001C!\u0003?Aq!a\u000b\u0001\t\u0003\ni\u0003C\u0004\u0002:\u0001!\t%a\u000f\t\u000f\u0005\u001d\u0003\u0001\"\u0011\u0002J!9\u0011Q\u000b\u0001\u0005B\u0005]\u0003bBA2\u0001\u0011\u0005\u0013Q\r\u0002\u0018\u0005\u0006\u001c\u0018nY#wK:$h)\u001b7uKJ\u0014U/\u001b7eKJT!!\t\u0012\u0002\u000f!L7\u000f^8ss*\u00111\u0005J\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005\u00152\u0013!B:qCJ\\'BA\u0014)\u0003\u0019\t\u0007/Y2iK*\t\u0011&A\u0002pe\u001e\u001c2\u0001A\u00162!\tas&D\u0001.\u0015\tqC%A\u0005tG\",G-\u001e7fe&\u0011\u0001'\f\u0002\u000e'B\f'o\u001b'jgR,g.\u001a:\u0011\u0005I\u001aT\"\u0001\u0011\n\u0005Q\u0002#AE#wK:$h)\u001b7uKJ\u0014U/\u001b7eKJ\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002qA\u0011!\u0007A\u0001\u0010Y&4XMS8c)>\u001cF/Y4fgV\t1\b\u0005\u0003=\u0007\u0016KU\"A\u001f\u000b\u0005yz\u0014aB7vi\u0006\u0014G.\u001a\u0006\u0003\u0001\u0006\u000b!bY8mY\u0016\u001cG/[8o\u0015\u0005\u0011\u0015!B:dC2\f\u0017B\u0001#>\u0005\u001dA\u0015m\u001d5NCB\u0004\"AR$\u000e\u0003\u0005K!\u0001S!\u0003\u0007%sG\u000fE\u0002K#\u0016s!aS(\u0011\u00051\u000bU\"A'\u000b\u000593\u0014A\u0002\u001fs_>$h(\u0003\u0002Q\u0003\u00061\u0001K]3eK\u001aL!AU*\u0003\u0007M+GO\u0003\u0002Q\u0003\u0006\u0001B.\u001b<f\u0015>\u0014Gk\\*uC\u001e,7\u000fI\u0001\rgR\fw-\u001a+p)\u0006\u001c8n]\u000b\u0002/B!AhQ#Y!\ra\u0014LW\u0005\u0003%v\u0002\"AR.\n\u0005q\u000b%\u0001\u0002'p]\u001e\fQb\u001d;bO\u0016$v\u000eV1tWN\u0004\u0013aC:uC\u001e,Gk\u001c*E\tN\fAb\u001d;bO\u0016$vN\u0015#Eg\u0002\nab\u00187jm\u0016,\u00050Z2vi>\u00148/F\u0001c!\ra4-Z\u0005\u0003Iv\u0012q\u0001S1tQN+G\u000f\u0005\u0002KM&\u0011qm\u0015\u0002\u0007'R\u0014\u0018N\\4\u0002\u001f}c\u0017N^3Fq\u0016\u001cW\u000f^8sg\u0002\n\u0011\u0002^8uC2TuNY:\u0016\u0003i\u000bQ\u0002^8uC2TuNY:`I\u0015\fHCA7q!\t1e.\u0003\u0002p\u0003\n!QK\\5u\u0011\u001d\t8\"!AA\u0002i\u000b1\u0001\u001f\u00132\u0003)!x\u000e^1m\u0015>\u00147\u000fI\u0001\fi>$\u0018\r\\*uC\u001e,7/A\bu_R\fGn\u0015;bO\u0016\u001cx\fJ3r)\tig\u000fC\u0004r\u001d\u0005\u0005\t\u0019\u0001.\u0002\u0019Q|G/\u00197Ti\u0006<Wm\u001d\u0011\u0002\u0015Q|G/\u00197UCN\\7/\u0001\bu_R\fG\u000eV1tWN|F%Z9\u0015\u00055\\\bbB9\u0012\u0003\u0003\u0005\rAW\u0001\fi>$\u0018\r\u001c+bg.\u001c\b%\u0001\u0005mSZ,'j\u001c2t+\u0005I\u0015A\u00037jm\u0016\u001cF/Y4fg\u0006IA.\u001b<f)\u0006\u001c8n]\u000b\u0003\u0003\u000b\u00012AS)[\u0003!a\u0017N^3S\t\u0012\u001b\u0018!\u00047jm\u0016,\u00050Z2vi>\u00148/\u0006\u0002\u0002\u000eA\u0019!*U3\u0002\u0015=t'j\u001c2Ti\u0006\u0014H\u000fF\u0002n\u0003'Aq!!\u0006\u0019\u0001\u0004\t9\"\u0001\u0005k_\n\u001cF/\u0019:u!\ra\u0013\u0011D\u0005\u0004\u00037i#!F*qCJ\\G*[:uK:,'OS8c'R\f'\u000f^\u0001\t_:TuNY#oIR\u0019Q.!\t\t\u000f\u0005\r\u0012\u00041\u0001\u0002&\u00051!n\u001c2F]\u0012\u00042\u0001LA\u0014\u0013\r\tI#\f\u0002\u0014'B\f'o\u001b'jgR,g.\u001a:K_\n,e\u000eZ\u0001\u0011_:\u001cF/Y4f'V\u0014W.\u001b;uK\u0012$2!\\A\u0018\u0011\u001d\t\tD\u0007a\u0001\u0003g\tab\u001d;bO\u0016\u001cVOY7jiR,G\rE\u0002-\u0003kI1!a\u000e.\u0005m\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8feN#\u0018mZ3Tk\nl\u0017\u000e\u001e;fI\u0006YqN\u001c+bg.\u001cF/\u0019:u)\ri\u0017Q\b\u0005\b\u0003\u007fY\u0002\u0019AA!\u0003%!\u0018m]6Ti\u0006\u0014H\u000fE\u0002-\u0003\u0007J1!!\u0012.\u0005Y\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8feR\u000b7o[*uCJ$\u0018aD8o\u000bb,7-\u001e;pe\u0006#G-\u001a3\u0015\u00075\fY\u0005C\u0004\u0002Nq\u0001\r!a\u0014\u0002\u001b\u0015DXmY;u_J\fE\rZ3e!\ra\u0013\u0011K\u0005\u0004\u0003'j#AG*qCJ\\G*[:uK:,'/\u0012=fGV$xN]!eI\u0016$\u0017!E8o\u000bb,7-\u001e;peJ+Wn\u001c<fIR\u0019Q.!\u0017\t\u000f\u0005mS\u00041\u0001\u0002^\u0005yQ\r_3dkR|'OU3n_Z,G\rE\u0002-\u0003?J1!!\u0019.\u0005q\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0016CXmY;u_J\u0014V-\\8wK\u0012\fAb\u0019:fCR,g)\u001b7uKJ$\"!a\u001a\u0011\u0007I\nI'C\u0002\u0002l\u0001\u00121\"\u0012<f]R4\u0015\u000e\u001c;fe\u0002"
)
public class BasicEventFilterBuilder extends SparkListener implements EventFilterBuilder {
   private final HashMap liveJobToStages = new HashMap();
   private final HashMap stageToTasks = new HashMap();
   private final HashMap stageToRDDs = new HashMap();
   private final HashSet _liveExecutors = new HashSet();
   private long totalJobs = 0L;
   private long totalStages = 0L;
   private long totalTasks = 0L;

   private HashMap liveJobToStages() {
      return this.liveJobToStages;
   }

   private HashMap stageToTasks() {
      return this.stageToTasks;
   }

   private HashMap stageToRDDs() {
      return this.stageToRDDs;
   }

   private HashSet _liveExecutors() {
      return this._liveExecutors;
   }

   private long totalJobs() {
      return this.totalJobs;
   }

   private void totalJobs_$eq(final long x$1) {
      this.totalJobs = x$1;
   }

   private long totalStages() {
      return this.totalStages;
   }

   private void totalStages_$eq(final long x$1) {
      this.totalStages = x$1;
   }

   private long totalTasks() {
      return this.totalTasks;
   }

   private void totalTasks_$eq(final long x$1) {
      this.totalTasks = x$1;
   }

   public Set liveJobs() {
      return this.liveJobToStages().keySet().toSet();
   }

   public Set liveStages() {
      return this.stageToRDDs().keySet().toSet();
   }

   public Set liveTasks() {
      return ((IterableOnceOps)this.stageToTasks().values().flatten(.MODULE$.$conforms())).toSet();
   }

   public Set liveRDDs() {
      return ((IterableOnceOps)this.stageToRDDs().values().flatten(.MODULE$.$conforms())).toSet();
   }

   public Set liveExecutors() {
      return this._liveExecutors().toSet();
   }

   public void onJobStart(final SparkListenerJobStart jobStart) {
      this.totalJobs_$eq(this.totalJobs() + 1L);
      this.totalStages_$eq(this.totalStages() + (long)jobStart.stageIds().length());
      this.liveJobToStages().$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(jobStart.jobId())), jobStart.stageIds().toSet()));
   }

   public void onJobEnd(final SparkListenerJobEnd jobEnd) {
      Iterable stages = (Iterable)this.liveJobToStages().getOrElse(BoxesRunTime.boxToInteger(jobEnd.jobId()), () -> (Seq)scala.package..MODULE$.Seq().empty());
      this.liveJobToStages().$minus$eq(BoxesRunTime.boxToInteger(jobEnd.jobId()));
      this.stageToTasks().$minus$minus$eq(stages);
      this.stageToRDDs().$minus$minus$eq(stages);
   }

   public void onStageSubmitted(final SparkListenerStageSubmitted stageSubmitted) {
      int stageId = stageSubmitted.stageInfo().stageId();
      this.stageToRDDs().put(BoxesRunTime.boxToInteger(stageId), ((IterableOnceOps)stageSubmitted.stageInfo().rddInfos().map((x$1) -> BoxesRunTime.boxToInteger($anonfun$onStageSubmitted$1(x$1)))).toSet());
      this.stageToTasks().getOrElseUpdate(BoxesRunTime.boxToInteger(stageId), () -> new HashSet());
   }

   public void onTaskStart(final SparkListenerTaskStart taskStart) {
      this.totalTasks_$eq(this.totalTasks() + 1L);
      this.stageToTasks().get(BoxesRunTime.boxToInteger(taskStart.stageId())).foreach((tasks) -> (scala.collection.mutable.Set)tasks.$plus$eq(BoxesRunTime.boxToLong(taskStart.taskInfo().taskId())));
   }

   public void onExecutorAdded(final SparkListenerExecutorAdded executorAdded) {
      this._liveExecutors().$plus$eq(executorAdded.executorId());
   }

   public void onExecutorRemoved(final SparkListenerExecutorRemoved executorRemoved) {
      this._liveExecutors().$minus$eq(executorRemoved.executorId());
   }

   public EventFilter createFilter() {
      EventFilter.FilterStatistics stats = new EventFilter.FilterStatistics(this.totalJobs(), (long)this.liveJobs().size(), this.totalStages(), (long)this.liveStages().size(), this.totalTasks(), (long)this.liveTasks().size());
      return new BasicEventFilter(stats, this.liveJobs(), this.liveStages(), this.liveTasks(), this.liveRDDs(), this.liveExecutors());
   }

   // $FF: synthetic method
   public static final int $anonfun$onStageSubmitted$1(final RDDInfo x$1) {
      return x$1.id();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
