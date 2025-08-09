package org.apache.spark.scheduler;

import java.util.Map;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.DeterministicLevel$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.CallSite;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015gA\u0002\u0016,\u0003\u0003Y3\u0007\u0003\u0005A\u0001\t\u0015\r\u0011\"\u0001C\u0011!1\u0005A!A!\u0002\u0013\u0019\u0005\u0002C$\u0001\u0005\u000b\u0007I\u0011\u0001%\t\u0011M\u0003!\u0011!Q\u0001\n%C\u0001b\u0017\u0001\u0003\u0006\u0004%\tA\u0011\u0005\t9\u0002\u0011\t\u0011)A\u0005\u0007\"AQ\f\u0001BC\u0002\u0013\u0005a\f\u0003\u0005n\u0001\t\u0005\t\u0015!\u0003`\u0011!q\u0007A!b\u0001\n\u0003\u0011\u0005\u0002C8\u0001\u0005\u0003\u0005\u000b\u0011B\"\t\u0011A\u0004!Q1A\u0005\u0002ED\u0001\u0002\u001f\u0001\u0003\u0002\u0003\u0006IA\u001d\u0005\ts\u0002\u0011)\u0019!C\u0001\u0005\"A!\u0010\u0001B\u0001B\u0003%1\tC\u0003|\u0001\u0011\u0005A\u0010\u0003\u0005\u0002\u0012\u0001\u0011\r\u0011\"\u0001C\u0011\u001d\t\u0019\u0002\u0001Q\u0001\n\rC\u0011\"!\u0006\u0001\u0005\u0004%\t!a\u0006\t\u0011\u0005%\u0002\u0001)A\u0005\u00033A\u0001\"a\u000b\u0001\u0001\u0004%IA\u0011\u0005\n\u0003[\u0001\u0001\u0019!C\u0005\u0003_Aq!a\u000f\u0001A\u0003&1\tC\u0004\u0002>\u0001!\ta\u000b\"\t\u0013\u0005}\u0002A1A\u0005\u0002\u0005\u0005\u0003\u0002CA*\u0001\u0001\u0006I!a\u0011\t\u0013\u0005U\u0003A1A\u0005\u0002\u0005\u0005\u0003\u0002CA,\u0001\u0001\u0006I!a\u0011\t\u0013\u0005e\u0003\u00011A\u0005\n\u0005m\u0003\"CA2\u0001\u0001\u0007I\u0011BA3\u0011!\tI\u0007\u0001Q!\n\u0005u\u0003\"CA6\u0001\t\u0007I\u0011AA\f\u0011!\ti\u0007\u0001Q\u0001\n\u0005e\u0001\u0002CA8\u0001\u0011\u00051&!\u001d\t\u000f\u0005M\u0004\u0001\"\u0001\u0002v!I\u0011Q\u0012\u0001\u0012\u0002\u0013\u0005\u0011q\u0012\u0005\b\u0003K\u0003A\u0011AA9\u0011\u001d\t9\u000b\u0001C\u0001\u00037Bq!!+\u0001\t\u000b\nY\u000bC\u0004\u0002.\u0002!)%a,\t\u000f\u0005m\u0006A\"\u0001\u0002>\"9\u0011\u0011\u0019\u0001\u0005\u0002\u0005\r'!B*uC\u001e,'B\u0001\u0017.\u0003%\u00198\r[3ek2,'O\u0003\u0002/_\u0005)1\u000f]1sW*\u0011\u0001'M\u0001\u0007CB\f7\r[3\u000b\u0003I\n1a\u001c:h'\r\u0001AG\u000f\t\u0003kaj\u0011A\u000e\u0006\u0002o\u0005)1oY1mC&\u0011\u0011H\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\u0005mrT\"\u0001\u001f\u000b\u0005uj\u0013\u0001C5oi\u0016\u0014h.\u00197\n\u0005}b$a\u0002'pO\u001eLgnZ\u0001\u0003S\u0012\u001c\u0001!F\u0001D!\t)D)\u0003\u0002Fm\t\u0019\u0011J\u001c;\u0002\u0007%$\u0007%A\u0002sI\u0012,\u0012!\u0013\u0019\u0003\u0015F\u00032aS'P\u001b\u0005a%BA$.\u0013\tqEJA\u0002S\t\u0012\u0003\"\u0001U)\r\u0001\u0011I!\u000bBA\u0001\u0002\u0003\u0015\t\u0001\u0016\u0002\u0004?\u0012\n\u0014\u0001\u0002:eI\u0002\n\"!\u0016-\u0011\u0005U2\u0016BA,7\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!N-\n\u0005i3$aA!os\u0006Aa.^7UCN\\7/A\u0005ok6$\u0016m]6tA\u00059\u0001/\u0019:f]R\u001cX#A0\u0011\u0007\u0001D7N\u0004\u0002bM:\u0011!-Z\u0007\u0002G*\u0011A-Q\u0001\u0007yI|w\u000e\u001e \n\u0003]J!a\u001a\u001c\u0002\u000fA\f7m[1hK&\u0011\u0011N\u001b\u0002\u0005\u0019&\u001cHO\u0003\u0002hmA\u0011A\u000eA\u0007\u0002W\u0005A\u0001/\u0019:f]R\u001c\b%\u0001\u0006gSJ\u001cHOS8c\u0013\u0012\f1BZ5sgRTuNY%eA\u0005A1-\u00197m'&$X-F\u0001s!\t\u0019h/D\u0001u\u0015\t)X&\u0001\u0003vi&d\u0017BA<u\u0005!\u0019\u0015\r\u001c7TSR,\u0017!C2bY2\u001c\u0016\u000e^3!\u0003E\u0011Xm]8ve\u000e,\u0007K]8gS2,\u0017\nZ\u0001\u0013e\u0016\u001cx.\u001e:dKB\u0013xNZ5mK&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\u000eWvt\u0018qAA\u0005\u0003\u0017\ti!a\u0004\t\u000b\u0001{\u0001\u0019A\"\t\u000b\u001d{\u0001\u0019A@1\t\u0005\u0005\u0011Q\u0001\t\u0005\u00176\u000b\u0019\u0001E\u0002Q\u0003\u000b!\u0011B\u0015@\u0002\u0002\u0003\u0005)\u0011\u0001+\t\u000bm{\u0001\u0019A\"\t\u000bu{\u0001\u0019A0\t\u000b9|\u0001\u0019A\"\t\u000bA|\u0001\u0019\u0001:\t\u000be|\u0001\u0019A\"\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t\u00039qW/\u001c)beRLG/[8og\u0002\naA[8c\u0013\u0012\u001cXCAA\r!\u0015\tY\"!\nD\u001b\t\tiB\u0003\u0003\u0002 \u0005\u0005\u0012aB7vi\u0006\u0014G.\u001a\u0006\u0004\u0003G1\u0014AC2pY2,7\r^5p]&!\u0011qEA\u000f\u0005\u001dA\u0015m\u001d5TKR\fqA[8c\u0013\u0012\u001c\b%A\u0007oKb$\u0018\t\u001e;f[B$\u0018\nZ\u0001\u0012]\u0016DH/\u0011;uK6\u0004H/\u00133`I\u0015\fH\u0003BA\u0019\u0003o\u00012!NA\u001a\u0013\r\t)D\u000e\u0002\u0005+:LG\u000f\u0003\u0005\u0002:U\t\t\u00111\u0001D\u0003\rAH%M\u0001\u000f]\u0016DH/\u0011;uK6\u0004H/\u00133!\u0003A9W\r\u001e(fqR\fE\u000f^3naRLE-\u0001\u0003oC6,WCAA\"!\u0011\t)%!\u0014\u000f\t\u0005\u001d\u0013\u0011\n\t\u0003EZJ1!a\u00137\u0003\u0019\u0001&/\u001a3fM&!\u0011qJA)\u0005\u0019\u0019FO]5oO*\u0019\u00111\n\u001c\u0002\u000b9\fW.\u001a\u0011\u0002\u000f\u0011,G/Y5mg\u0006AA-\u001a;bS2\u001c\b%A\u0006`Y\u0006$Xm\u001d;J]\u001a|WCAA/!\ra\u0017qL\u0005\u0004\u0003CZ#!C*uC\u001e,\u0017J\u001c4p\u0003=yF.\u0019;fgRLeNZ8`I\u0015\fH\u0003BA\u0019\u0003OB\u0011\"!\u000f\u001e\u0003\u0003\u0005\r!!\u0018\u0002\u0019}c\u0017\r^3ti&sgm\u001c\u0011\u0002!\u0019\f\u0017\u000e\\3e\u0003R$X-\u001c9u\u0013\u0012\u001c\u0018!\u00054bS2,G-\u0011;uK6\u0004H/\u00133tA\u0005i1\r\\3be\u001a\u000b\u0017\u000e\\;sKN$\"!!\r\u0002'5\f7.\u001a(foN#\u0018mZ3BiR,W\u000e\u001d;\u0015\r\u0005E\u0012qOA>\u0011\u0019\tIH\ta\u0001\u0007\u00061b.^7QCJ$\u0018\u000e^5p]N$vnQ8naV$X\rC\u0005\u0002~\t\u0002\n\u00111\u0001\u0002\u0000\u00059B/Y:l\u0019>\u001c\u0017\r\\5usB\u0013XMZ3sK:\u001cWm\u001d\t\u0006A\u0006\u0005\u0015QQ\u0005\u0004\u0003\u0007S'aA*fcB)\u0001-!!\u0002\bB\u0019A.!#\n\u0007\u0005-5F\u0001\u0007UCN\\Gj\\2bi&|g.A\u000fnC.,g*Z<Ti\u0006<W-\u0011;uK6\u0004H\u000f\n3fM\u0006,H\u000e\u001e\u00133+\t\t\tJ\u000b\u0003\u0002\u0000\u0005M5FAAK!\u0011\t9*!)\u000e\u0005\u0005e%\u0002BAN\u0003;\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005}e'\u0001\u0006b]:|G/\u0019;j_:LA!a)\u0002\u001a\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u00029%t7M]3bg\u0016\fE\u000f^3naRLEm\u00148GSJ\u001cHoU6ja\u0006QA.\u0019;fgRLeNZ8\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012aQ\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005E\u0016q\u0017\t\u0004k\u0005M\u0016bAA[m\t9!i\\8mK\u0006t\u0007BBA]O\u0001\u0007\u0001,A\u0003pi\",'/A\u000bgS:$W*[:tS:<\u0007+\u0019:uSRLwN\\:\u0015\u0005\u0005}\u0006\u0003\u00021\u0002\u0002\u000e\u000bq\"[:J]\u0012,G/\u001a:nS:\fG/Z\u000b\u0003\u0003c\u0003"
)
public abstract class Stage implements Logging {
   private final int id;
   private final RDD rdd;
   private final int numTasks;
   private final List parents;
   private final int firstJobId;
   private final CallSite callSite;
   private final int resourceProfileId;
   private final int numPartitions;
   private final HashSet jobIds;
   private int nextAttemptId;
   private final String name;
   private final String details;
   private StageInfo _latestInfo;
   private final HashSet failedAttemptIds;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public int id() {
      return this.id;
   }

   public RDD rdd() {
      return this.rdd;
   }

   public int numTasks() {
      return this.numTasks;
   }

   public List parents() {
      return this.parents;
   }

   public int firstJobId() {
      return this.firstJobId;
   }

   public CallSite callSite() {
      return this.callSite;
   }

   public int resourceProfileId() {
      return this.resourceProfileId;
   }

   public int numPartitions() {
      return this.numPartitions;
   }

   public HashSet jobIds() {
      return this.jobIds;
   }

   private int nextAttemptId() {
      return this.nextAttemptId;
   }

   private void nextAttemptId_$eq(final int x$1) {
      this.nextAttemptId = x$1;
   }

   public int getNextAttemptId() {
      return this.nextAttemptId();
   }

   public String name() {
      return this.name;
   }

   public String details() {
      return this.details;
   }

   private StageInfo _latestInfo() {
      return this._latestInfo;
   }

   private void _latestInfo_$eq(final StageInfo x$1) {
      this._latestInfo = x$1;
   }

   public HashSet failedAttemptIds() {
      return this.failedAttemptIds;
   }

   public void clearFailures() {
      this.failedAttemptIds().clear();
   }

   public void makeNewStageAttempt(final int numPartitionsToCompute, final Seq taskLocalityPreferences) {
      TaskMetrics metrics = new TaskMetrics();
      metrics.register(this.rdd().sparkContext());
      this._latestInfo_$eq(StageInfo$.MODULE$.fromStage(this, this.nextAttemptId(), new Some(BoxesRunTime.boxToInteger(numPartitionsToCompute)), metrics, taskLocalityPreferences, this.resourceProfileId()));
      this.nextAttemptId_$eq(this.nextAttemptId() + 1);
   }

   public Seq makeNewStageAttempt$default$2() {
      return (Seq).MODULE$.Seq().empty();
   }

   public void increaseAttemptIdOnFirstSkip() {
      if (this.nextAttemptId() == 0) {
         this.nextAttemptId_$eq(1);
      }
   }

   public StageInfo latestInfo() {
      return this._latestInfo();
   }

   public final int hashCode() {
      return this.id();
   }

   public final boolean equals(final Object other) {
      if (!(other instanceof Stage var4)) {
         return false;
      } else {
         return var4 != null && var4.id() == this.id();
      }
   }

   public abstract Seq findMissingPartitions();

   public boolean isIndeterminate() {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = this.rdd().outputDeterministicLevel();
         Enumeration.Value var1 = DeterministicLevel$.MODULE$.INDETERMINATE();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public Stage(final int id, final RDD rdd, final int numTasks, final List parents, final int firstJobId, final CallSite callSite, final int resourceProfileId) {
      this.id = id;
      this.rdd = rdd;
      this.numTasks = numTasks;
      this.parents = parents;
      this.firstJobId = firstJobId;
      this.callSite = callSite;
      this.resourceProfileId = resourceProfileId;
      Logging.$init$(this);
      this.numPartitions = rdd.partitions().length;
      this.jobIds = new HashSet();
      this.nextAttemptId = 0;
      this.name = callSite.shortForm();
      this.details = callSite.longForm();
      int x$2 = this.nextAttemptId();
      Option x$4 = StageInfo$.MODULE$.fromStage$default$3();
      TaskMetrics x$5 = StageInfo$.MODULE$.fromStage$default$4();
      Seq x$6 = StageInfo$.MODULE$.fromStage$default$5();
      this._latestInfo = StageInfo$.MODULE$.fromStage(this, x$2, x$4, x$5, x$6, resourceProfileId);
      this.failedAttemptIds = new HashSet();
   }
}
