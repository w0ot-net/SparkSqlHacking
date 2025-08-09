package org.apache.spark.status;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.scheduler.StageInfo;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.status.api.v1.StageStatus;
import org.apache.spark.status.api.v1.TaskMetrics;
import org.apache.spark.storage.RDDInfo;
import org.apache.spark.ui.SparkUI$;
import org.apache.spark.util.collection.OpenHashSet;
import org.apache.spark.util.collection.OpenHashSet$mcI$sp;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.HashSet;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\ted\u0001\u0002!B\t)C\u0001b\u0014\u0001\u0003\u0002\u0004%\t\u0001\u0015\u0005\t/\u0002\u0011\t\u0019!C\u00011\"A\u0011\r\u0001B\u0001B\u0003&\u0011\u000bC\u0003c\u0001\u0011\u00051\rC\u0004g\u0001\u0001\u0007I\u0011A4\t\u000fM\u0004\u0001\u0019!C\u0001i\"1a\u000f\u0001Q!\n!Dqa\u001e\u0001A\u0002\u0013\u0005\u0001\u0010\u0003\u0005\u0000\u0001\u0001\u0007I\u0011AA\u0001\u0011\u001d\t)\u0001\u0001Q!\neD\u0001B\u0011\u0001A\u0002\u0013\u0005\u0011q\u0001\u0005\n\u00033\u0001\u0001\u0019!C\u0001\u00037A\u0001\"a\b\u0001A\u0003&\u0011\u0011\u0002\u0005\n\u0003C\u0001\u0001\u0019!C\u0001\u0003GA\u0011\"!\u0011\u0001\u0001\u0004%\t!a\u0011\t\u0011\u0005\u001d\u0003\u0001)Q\u0005\u0003KA\u0011\"!\u0013\u0001\u0001\u0004%\t!a\u0013\t\u0013\u00055\u0003\u00011A\u0005\u0002\u0005=\u0003\u0002CA*\u0001\u0001\u0006K!a\u000b\t\u0013\u0005U\u0003\u00011A\u0005\u0002\u0005]\u0003\"CA-\u0001\u0001\u0007I\u0011AA.\u0011\u001d\ty\u0006\u0001Q!\nqD\u0011\"!\u0019\u0001\u0001\u0004%\t!a\u0016\t\u0013\u0005\r\u0004\u00011A\u0005\u0002\u0005\u0015\u0004bBA5\u0001\u0001\u0006K\u0001 \u0005\n\u0003W\u0002\u0001\u0019!C\u0001\u0003/B\u0011\"!\u001c\u0001\u0001\u0004%\t!a\u001c\t\u000f\u0005M\u0004\u0001)Q\u0005y\"I\u0011Q\u000f\u0001C\u0002\u0013\u0005\u0011q\u000f\u0005\t\u0003\u000f\u0003\u0001\u0015!\u0003\u0002z!I\u0011\u0011\u0012\u0001A\u0002\u0013\u0005\u0011q\u000b\u0005\n\u0003\u0017\u0003\u0001\u0019!C\u0001\u0003\u001bCq!!%\u0001A\u0003&A\u0010C\u0005\u0002\u0014\u0002\u0001\r\u0011\"\u0001\u0002\u0016\"I\u0011Q\u0014\u0001A\u0002\u0013\u0005\u0011q\u0014\u0005\t\u0003G\u0003\u0001\u0015)\u0003\u0002\u0018\"I\u0011Q\u0015\u0001A\u0002\u0013\u0005\u0011q\u0015\u0005\n\u0003_\u0003\u0001\u0019!C\u0001\u0003cC\u0001\"!.\u0001A\u0003&\u0011\u0011\u0016\u0005\n\u0003o\u0003\u0001\u0019!C\u0001\u0003sC\u0011\"!0\u0001\u0001\u0004%\t!a0\t\u0011\u0005\r\u0007\u0001)Q\u0005\u0003wC\u0011\"!2\u0001\u0001\u0004%\t!a2\t\u0013\u0005=\u0007\u00011A\u0005\u0002\u0005E\u0007\u0002CAk\u0001\u0001\u0006K!!3\t\u0013\u0005]\u0007A1A\u0005\u0002\u0005e\u0007\u0002CAw\u0001\u0001\u0006I!a7\t\u0013\u0005=\bA1A\u0005\u0002\u0005E\b\u0002CA|\u0001\u0001\u0006I!a=\t\u0013\u0005e\b\u00011A\u0005\u0002\u0005m\b\"\u0003B\u0002\u0001\u0001\u0007I\u0011\u0001B\u0003\u0011!\u0011I\u0001\u0001Q!\n\u0005u\b\"\u0003B\u0006\u0001\t\u0007I\u0011\u0001B\u0007\u0011!\u0011Y\u0002\u0001Q\u0001\n\t=\u0001B\u0003B\u000f\u0001!\u0015\r\u0011\"\u0001\u0003 !I!q\u0005\u0001A\u0002\u0013\u0005!\u0011\u0006\u0005\n\u0005c\u0001\u0001\u0019!C\u0001\u0005gA\u0001Ba\u000e\u0001A\u0003&!1\u0006\u0005\n\u0005\u0003\u0002!\u0019!C\u0001\u0005\u0007B\u0001Ba\u0017\u0001A\u0003%!Q\t\u0005\b\u0005;\u0002A\u0011\u0001B0\u0011\u001d\u0011)\u0007\u0001C\u0001\u0005OBqAa\u001c\u0001\t#\u0012\tHA\u0005MSZ,7\u000b^1hK*\u0011!iQ\u0001\u0007gR\fG/^:\u000b\u0005\u0011+\u0015!B:qCJ\\'B\u0001$H\u0003\u0019\t\u0007/Y2iK*\t\u0001*A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001\u0017B\u0011A*T\u0007\u0002\u0003&\u0011a*\u0011\u0002\u000b\u0019&4X-\u00128uSRL\u0018\u0001B5oM>,\u0012!\u0015\t\u0003%Vk\u0011a\u0015\u0006\u0003)\u000e\u000b\u0011b]2iK\u0012,H.\u001a:\n\u0005Y\u001b&!C*uC\u001e,\u0017J\u001c4p\u0003!IgNZ8`I\u0015\fHCA-`!\tQV,D\u0001\\\u0015\u0005a\u0016!B:dC2\f\u0017B\u00010\\\u0005\u0011)f.\u001b;\t\u000f\u0001\u0014\u0011\u0011!a\u0001#\u0006\u0019\u0001\u0010J\u0019\u0002\u000b%tgm\u001c\u0011\u0002\rqJg.\u001b;?)\t!W\r\u0005\u0002M\u0001!)q\n\u0002a\u0001#\u0006!!n\u001c2t+\u0005A\u0007cA5oa6\t!N\u0003\u0002lY\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003[n\u000b!bY8mY\u0016\u001cG/[8o\u0013\ty'NA\u0002TKF\u0004\"\u0001T9\n\u0005I\f%a\u0002'jm\u0016TuNY\u0001\tU>\u00147o\u0018\u0013fcR\u0011\u0011,\u001e\u0005\bA\u001a\t\t\u00111\u0001i\u0003\u0015QwNY:!\u0003\u0019QwNY%egV\t\u0011\u0010E\u0002jurL!a\u001f6\u0003\u0007M+G\u000f\u0005\u0002[{&\u0011ap\u0017\u0002\u0004\u0013:$\u0018A\u00036pE&#7o\u0018\u0013fcR\u0019\u0011,a\u0001\t\u000f\u0001L\u0011\u0011!a\u0001s\u00069!n\u001c2JIN\u0004SCAA\u0005!\u0011\tY!!\u0006\u000e\u0005\u00055!\u0002BA\b\u0003#\t!A^\u0019\u000b\u0007\u0005M\u0011)A\u0002ba&LA!a\u0006\u0002\u000e\tY1\u000b^1hKN#\u0018\r^;t\u0003)\u0019H/\u0019;vg~#S-\u001d\u000b\u00043\u0006u\u0001\u0002\u00031\r\u0003\u0003\u0005\r!!\u0003\u0002\u000fM$\u0018\r^;tA\u0005YA-Z:de&\u0004H/[8o+\t\t)\u0003E\u0003[\u0003O\tY#C\u0002\u0002*m\u0013aa\u00149uS>t\u0007\u0003BA\u0017\u0003wqA!a\f\u00028A\u0019\u0011\u0011G.\u000e\u0005\u0005M\"bAA\u001b\u0013\u00061AH]8pizJ1!!\u000f\\\u0003\u0019\u0001&/\u001a3fM&!\u0011QHA \u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011H.\u0002\u001f\u0011,7o\u0019:jaRLwN\\0%KF$2!WA#\u0011!\u0001w\"!AA\u0002\u0005\u0015\u0012\u0001\u00043fg\u000e\u0014\u0018\u000e\u001d;j_:\u0004\u0013AD:dQ\u0016$W\u000f\\5oOB{w\u000e\\\u000b\u0003\u0003W\t!c]2iK\u0012,H.\u001b8h!>|Gn\u0018\u0013fcR\u0019\u0011,!\u0015\t\u0011\u0001\u0014\u0012\u0011!a\u0001\u0003W\tqb]2iK\u0012,H.\u001b8h!>|G\u000eI\u0001\fC\u000e$\u0018N^3UCN\\7/F\u0001}\u0003=\t7\r^5wKR\u000b7o[:`I\u0015\fHcA-\u0002^!9\u0001-FA\u0001\u0002\u0004a\u0018\u0001D1di&4X\rV1tWN\u0004\u0013AD2p[BdW\r^3e)\u0006\u001c8n]\u0001\u0013G>l\u0007\u000f\\3uK\u0012$\u0016m]6t?\u0012*\u0017\u000fF\u0002Z\u0003OBq\u0001\u0019\r\u0002\u0002\u0003\u0007A0A\bd_6\u0004H.\u001a;fIR\u000b7o[:!\u0003-1\u0017-\u001b7fIR\u000b7o[:\u0002\u001f\u0019\f\u0017\u000e\\3e)\u0006\u001c8n]0%KF$2!WA9\u0011\u001d\u00017$!AA\u0002q\fABZ1jY\u0016$G+Y:lg\u0002\n\u0001cY8na2,G/\u001a3J]\u0012L7-Z:\u0016\u0005\u0005e\u0004#BA>\u0003\u0007cXBAA?\u0015\ri\u0017q\u0010\u0006\u0004\u0003\u0003\u001b\u0015\u0001B;uS2LA!!\"\u0002~\tYq\n]3o\u0011\u0006\u001c\bnU3u\u0003E\u0019w.\u001c9mKR,G-\u00138eS\u000e,7\u000fI\u0001\fW&dG.\u001a3UCN\\7/A\blS2dW\r\u001a+bg.\u001cx\fJ3r)\rI\u0016q\u0012\u0005\bA\u0002\n\t\u00111\u0001}\u00031Y\u0017\u000e\u001c7fIR\u000b7o[:!\u00035Y\u0017\u000e\u001c7fIN+X.\\1ssV\u0011\u0011q\u0013\t\b\u0003[\tI*a\u000b}\u0013\u0011\tY*a\u0010\u0003\u00075\u000b\u0007/A\tlS2dW\rZ*v[6\f'/_0%KF$2!WAQ\u0011!\u00017%!AA\u0002\u0005]\u0015AD6jY2,GmU;n[\u0006\u0014\u0018\u0010I\u0001\u0010M&\u00148\u000f\u001e'bk:\u001c\u0007\u000eV5nKV\u0011\u0011\u0011\u0016\t\u00045\u0006-\u0016bAAW7\n!Aj\u001c8h\u0003M1\u0017N]:u\u0019\u0006,hn\u00195US6,w\fJ3r)\rI\u00161\u0017\u0005\tA\u001a\n\t\u00111\u0001\u0002*\u0006\u0001b-\u001b:ti2\u000bWO\\2i)&lW\rI\u0001\u0010Y>\u001c\u0017\r\\5usN+X.\\1ssV\u0011\u00111\u0018\t\t\u0003[\tI*a\u000b\u0002*\u0006\u0019Bn\\2bY&$\u0018pU;n[\u0006\u0014\u0018p\u0018\u0013fcR\u0019\u0011,!1\t\u0011\u0001L\u0013\u0011!a\u0001\u0003w\u000b\u0001\u0003\\8dC2LG/_*v[6\f'/\u001f\u0011\u0002\u000f5,GO]5dgV\u0011\u0011\u0011\u001a\t\u0005\u0003\u0017\tY-\u0003\u0003\u0002N\u00065!a\u0003+bg.lU\r\u001e:jGN\f1\"\\3ue&\u001c7o\u0018\u0013fcR\u0019\u0011,a5\t\u0011\u0001d\u0013\u0011!a\u0001\u0003\u0013\f\u0001\"\\3ue&\u001c7\u000fI\u0001\u0012Kb,7-\u001e;peN+X.\\1sS\u0016\u001cXCAAn!!\ti.a9\u0002,\u0005\u001dXBAAp\u0015\r\t\t\u000f\\\u0001\b[V$\u0018M\u00197f\u0013\u0011\t)/a8\u0003\u000f!\u000b7\u000f['baB\u0019A*!;\n\u0007\u0005-\u0018I\u0001\rMSZ,W\t_3dkR|'o\u0015;bO\u0016\u001cV/\\7bef\f!#\u001a=fGV$xN]*v[6\f'/[3tA\u00051\u0012m\u0019;jm\u0016$\u0016m]6t!\u0016\u0014X\t_3dkR|'/\u0006\u0002\u0002tB9\u0011Q\\A{\u0003Wa\u0018\u0002BAN\u0003?\fq#Y2uSZ,G+Y:lgB+'/\u0012=fGV$xN\u001d\u0011\u0002#\u0015D8\r\\;eK\u0012,\u00050Z2vi>\u00148/\u0006\u0002\u0002~B)\u0011.a@\u0002,%\u0019!\u0011\u00016\u0003\u000f!\u000b7\u000f[*fi\u0006)R\r_2mk\u0012,G-\u0012=fGV$xN]:`I\u0015\fHcA-\u0003\b!A\u0001mMA\u0001\u0002\u0004\ti0\u0001\nfq\u000edW\u000fZ3e\u000bb,7-\u001e;peN\u0004\u0013a\u00059fC.,\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001cXC\u0001B\b!\u0011\u0011\tBa\u0006\u000e\u0005\tM!b\u0001B\u000b\u0007\u0006AQ\r_3dkR|'/\u0003\u0003\u0003\u001a\tM!aD#yK\u000e,Ho\u001c:NKR\u0014\u0018nY:\u0002)A,\u0017m[#yK\u000e,Ho\u001c:NKR\u0014\u0018nY:!\u0003]\u0019\b/Z2vY\u0006$\u0018n\u001c8Ti\u0006<WmU;n[\u0006\u0014\u00180\u0006\u0002\u0003\"A\u0019AJa\t\n\u0007\t\u0015\u0012IA\u000eMSZ,7\u000b]3dk2\fG/[8o'R\fw-Z*v[6\f'/_\u0001\tG2,\u0017M\\5oOV\u0011!1\u0006\t\u00045\n5\u0012b\u0001B\u00187\n9!i\\8mK\u0006t\u0017\u0001D2mK\u0006t\u0017N\\4`I\u0015\fHcA-\u00036!A\u0001-OA\u0001\u0002\u0004\u0011Y#A\u0005dY\u0016\fg.\u001b8hA!\u001a!Ha\u000f\u0011\u0007i\u0013i$C\u0002\u0003@m\u0013\u0001B^8mCRLG.Z\u0001\u000bg\u00064X\r\u001a+bg.\u001cXC\u0001B#!\u0011\u00119Ea\u0016\u000e\u0005\t%#\u0002\u0002B&\u0005\u001b\na!\u0019;p[&\u001c'\u0002\u0002B(\u0005#\n!bY8oGV\u0014(/\u001a8u\u0015\u0011\t\tIa\u0015\u000b\u0005\tU\u0013\u0001\u00026bm\u0006LAA!\u0017\u0003J\ti\u0011\t^8nS\u000eLe\u000e^3hKJ\f1b]1wK\u0012$\u0016m]6tA\u0005yQ\r_3dkR|'oU;n[\u0006\u0014\u0018\u0010\u0006\u0003\u0002h\n\u0005\u0004b\u0002B2{\u0001\u0007\u00111F\u0001\u000bKb,7-\u001e;pe&#\u0017!\u0002;p\u0003BLGC\u0001B5!\u0011\tYAa\u001b\n\t\t5\u0014Q\u0002\u0002\n'R\fw-\u001a#bi\u0006\f\u0001\u0002Z8Va\u0012\fG/\u001a\u000b\u0003\u0005g\u00022A\u0017B;\u0013\r\u00119h\u0017\u0002\u0004\u0003:L\b"
)
public class LiveStage extends LiveEntity {
   private LiveSpeculationStageSummary speculationStageSummary;
   private StageInfo info;
   private Seq jobs;
   private Set jobIds;
   private StageStatus status;
   private Option description;
   private String schedulingPool;
   private int activeTasks;
   private int completedTasks;
   private int failedTasks;
   private final OpenHashSet completedIndices;
   private int killedTasks;
   private Map killedSummary;
   private long firstLaunchTime;
   private Map localitySummary;
   private TaskMetrics metrics;
   private final HashMap executorSummaries;
   private final scala.collection.mutable.Map activeTasksPerExecutor;
   private HashSet excludedExecutors;
   private final ExecutorMetrics peakExecutorMetrics;
   private volatile boolean cleaning;
   private final AtomicInteger savedTasks;
   private volatile boolean bitmap$0;

   public StageInfo info() {
      return this.info;
   }

   public void info_$eq(final StageInfo x$1) {
      this.info = x$1;
   }

   public Seq jobs() {
      return this.jobs;
   }

   public void jobs_$eq(final Seq x$1) {
      this.jobs = x$1;
   }

   public Set jobIds() {
      return this.jobIds;
   }

   public void jobIds_$eq(final Set x$1) {
      this.jobIds = x$1;
   }

   public StageStatus status() {
      return this.status;
   }

   public void status_$eq(final StageStatus x$1) {
      this.status = x$1;
   }

   public Option description() {
      return this.description;
   }

   public void description_$eq(final Option x$1) {
      this.description = x$1;
   }

   public String schedulingPool() {
      return this.schedulingPool;
   }

   public void schedulingPool_$eq(final String x$1) {
      this.schedulingPool = x$1;
   }

   public int activeTasks() {
      return this.activeTasks;
   }

   public void activeTasks_$eq(final int x$1) {
      this.activeTasks = x$1;
   }

   public int completedTasks() {
      return this.completedTasks;
   }

   public void completedTasks_$eq(final int x$1) {
      this.completedTasks = x$1;
   }

   public int failedTasks() {
      return this.failedTasks;
   }

   public void failedTasks_$eq(final int x$1) {
      this.failedTasks = x$1;
   }

   public OpenHashSet completedIndices() {
      return this.completedIndices;
   }

   public int killedTasks() {
      return this.killedTasks;
   }

   public void killedTasks_$eq(final int x$1) {
      this.killedTasks = x$1;
   }

   public Map killedSummary() {
      return this.killedSummary;
   }

   public void killedSummary_$eq(final Map x$1) {
      this.killedSummary = x$1;
   }

   public long firstLaunchTime() {
      return this.firstLaunchTime;
   }

   public void firstLaunchTime_$eq(final long x$1) {
      this.firstLaunchTime = x$1;
   }

   public Map localitySummary() {
      return this.localitySummary;
   }

   public void localitySummary_$eq(final Map x$1) {
      this.localitySummary = x$1;
   }

   public TaskMetrics metrics() {
      return this.metrics;
   }

   public void metrics_$eq(final TaskMetrics x$1) {
      this.metrics = x$1;
   }

   public HashMap executorSummaries() {
      return this.executorSummaries;
   }

   public scala.collection.mutable.Map activeTasksPerExecutor() {
      return this.activeTasksPerExecutor;
   }

   public HashSet excludedExecutors() {
      return this.excludedExecutors;
   }

   public void excludedExecutors_$eq(final HashSet x$1) {
      this.excludedExecutors = x$1;
   }

   public ExecutorMetrics peakExecutorMetrics() {
      return this.peakExecutorMetrics;
   }

   private LiveSpeculationStageSummary speculationStageSummary$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.speculationStageSummary = new LiveSpeculationStageSummary(this.info().stageId(), this.info().attemptNumber());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.speculationStageSummary;
   }

   public LiveSpeculationStageSummary speculationStageSummary() {
      return !this.bitmap$0 ? this.speculationStageSummary$lzycompute() : this.speculationStageSummary;
   }

   public boolean cleaning() {
      return this.cleaning;
   }

   public void cleaning_$eq(final boolean x$1) {
      this.cleaning = x$1;
   }

   public AtomicInteger savedTasks() {
      return this.savedTasks;
   }

   public LiveExecutorStageSummary executorSummary(final String executorId) {
      return (LiveExecutorStageSummary)this.executorSummaries().getOrElseUpdate(executorId, () -> new LiveExecutorStageSummary(this.info().stageId(), this.info().attemptNumber(), executorId));
   }

   public StageData toApi() {
      return new StageData(this.status(), this.info().stageId(), this.info().attemptNumber(), this.info().numTasks(), this.activeTasks(), this.completedTasks(), this.failedTasks(), this.killedTasks(), this.completedIndices().size(), this.info().submissionTime().map((x$3) -> $anonfun$toApi$1(BoxesRunTime.unboxToLong(x$3))), (Option)(this.firstLaunchTime() < Long.MAX_VALUE ? new Some(new Date(this.firstLaunchTime())) : .MODULE$), this.info().completionTime().map((x$4) -> $anonfun$toApi$2(BoxesRunTime.unboxToLong(x$4))), this.info().failureReason(), this.metrics().executorDeserializeTime(), this.metrics().executorDeserializeCpuTime(), this.metrics().executorRunTime(), this.metrics().executorCpuTime(), this.metrics().resultSize(), this.metrics().jvmGcTime(), this.metrics().resultSerializationTime(), this.metrics().memoryBytesSpilled(), this.metrics().diskBytesSpilled(), this.metrics().peakExecutionMemory(), this.metrics().inputMetrics().bytesRead(), this.metrics().inputMetrics().recordsRead(), this.metrics().outputMetrics().bytesWritten(), this.metrics().outputMetrics().recordsWritten(), this.metrics().shuffleReadMetrics().remoteBlocksFetched(), this.metrics().shuffleReadMetrics().localBlocksFetched(), this.metrics().shuffleReadMetrics().fetchWaitTime(), this.metrics().shuffleReadMetrics().remoteBytesRead(), this.metrics().shuffleReadMetrics().remoteBytesReadToDisk(), this.metrics().shuffleReadMetrics().localBytesRead(), this.metrics().shuffleReadMetrics().localBytesRead() + this.metrics().shuffleReadMetrics().remoteBytesRead(), this.metrics().shuffleReadMetrics().recordsRead(), this.metrics().shuffleReadMetrics().shufflePushReadMetrics().corruptMergedBlockChunks(), this.metrics().shuffleReadMetrics().shufflePushReadMetrics().mergedFetchFallbackCount(), this.metrics().shuffleReadMetrics().shufflePushReadMetrics().remoteMergedBlocksFetched(), this.metrics().shuffleReadMetrics().shufflePushReadMetrics().localMergedBlocksFetched(), this.metrics().shuffleReadMetrics().shufflePushReadMetrics().remoteMergedChunksFetched(), this.metrics().shuffleReadMetrics().shufflePushReadMetrics().localMergedChunksFetched(), this.metrics().shuffleReadMetrics().shufflePushReadMetrics().remoteMergedBytesRead(), this.metrics().shuffleReadMetrics().shufflePushReadMetrics().localMergedBytesRead(), this.metrics().shuffleReadMetrics().remoteReqsDuration(), this.metrics().shuffleReadMetrics().shufflePushReadMetrics().remoteMergedReqsDuration(), this.metrics().shuffleWriteMetrics().bytesWritten(), this.metrics().shuffleWriteMetrics().writeTime(), this.metrics().shuffleWriteMetrics().recordsWritten(), this.info().name(), this.description(), this.info().details(), this.schedulingPool(), (scala.collection.Seq)this.info().rddInfos().map((x$5) -> BoxesRunTime.boxToInteger($anonfun$toApi$3(x$5))), LiveEntityHelpers$.MODULE$.newAccumulatorInfos(this.info().accumulables().values()), .MODULE$, .MODULE$, .MODULE$, this.killedSummary(), this.info().resourceProfileId(), (new Some(this.peakExecutorMetrics())).filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$toApi$4(x$6))), .MODULE$, .MODULE$, this.info().isShufflePushEnabled(), this.info().shuffleMergerCount());
   }

   public Object doUpdate() {
      return new StageDataWrapper(this.toApi(), this.jobIds(), this.localitySummary());
   }

   // $FF: synthetic method
   public static final Date $anonfun$toApi$1(final long x$3) {
      return new Date(x$3);
   }

   // $FF: synthetic method
   public static final Date $anonfun$toApi$2(final long x$4) {
      return new Date(x$4);
   }

   // $FF: synthetic method
   public static final int $anonfun$toApi$3(final RDDInfo x$5) {
      return x$5.id();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$toApi$4(final ExecutorMetrics x$6) {
      return x$6.isSet();
   }

   public LiveStage(final StageInfo info) {
      this.info = info;
      super();
      this.jobs = (Seq)scala.collection.immutable.Nil..MODULE$;
      this.jobIds = (Set)scala.Predef..MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$);
      this.status = StageStatus.PENDING;
      this.description = .MODULE$;
      this.schedulingPool = SparkUI$.MODULE$.DEFAULT_POOL_NAME();
      this.activeTasks = 0;
      this.completedTasks = 0;
      this.failedTasks = 0;
      this.completedIndices = new OpenHashSet$mcI$sp(scala.reflect.ClassTag..MODULE$.Int());
      this.killedTasks = 0;
      this.killedSummary = (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      this.firstLaunchTime = Long.MAX_VALUE;
      this.localitySummary = (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      this.metrics = LiveEntityHelpers$.MODULE$.createMetrics(0L);
      this.executorSummaries = new HashMap();
      this.activeTasksPerExecutor = (new HashMap()).withDefaultValue(BoxesRunTime.boxToInteger(0));
      this.excludedExecutors = new HashSet();
      this.peakExecutorMetrics = new ExecutorMetrics();
      this.cleaning = false;
      this.savedTasks = new AtomicInteger(0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
