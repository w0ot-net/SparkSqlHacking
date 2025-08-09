package org.apache.spark.scheduler;

import org.apache.spark.TaskState$;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.errors.SparkCoreErrors$;
import scala.Enumeration;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005mg\u0001\u0002\u001b6\u0001yB\u0001\"\u0015\u0001\u0003\u0006\u0004%\tA\u0015\u0005\t-\u0002\u0011\t\u0011)A\u0005'\"Aq\u000b\u0001BC\u0002\u0013\u0005\u0001\f\u0003\u0005]\u0001\t\u0005\t\u0015!\u0003Z\u0011!i\u0006A!b\u0001\n\u0003A\u0006\u0002\u00030\u0001\u0005\u0003\u0005\u000b\u0011B-\t\u0011}\u0003!Q1A\u0005\u0002aC\u0001\u0002\u0019\u0001\u0003\u0002\u0003\u0006I!\u0017\u0005\tC\u0002\u0011)\u0019!C\u0001%\"A!\r\u0001B\u0001B\u0003%1\u000b\u0003\u0005d\u0001\t\u0015\r\u0011\"\u0001e\u0011!i\u0007A!A!\u0002\u0013)\u0007\u0002\u00038\u0001\u0005\u000b\u0007I\u0011\u00013\t\u0011=\u0004!\u0011!Q\u0001\n\u0015D\u0001\u0002\u001d\u0001\u0003\u0006\u0004%\t!\u001d\u0005\tu\u0002\u0011\t\u0011)A\u0005e\"A1\u0010\u0001BC\u0002\u0013\u0005A\u0010C\u0005\u0002\u0002\u0001\u0011\t\u0011)A\u0005{\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\u0002\u0001\u0011\u0005\u00111\u0004\u0005\t\u0003[\u0001\u0001\u0019!C\u0001%\"I\u0011q\u0006\u0001A\u0002\u0013\u0005\u0011\u0011\u0007\u0005\b\u0003{\u0001\u0001\u0015)\u0003T\u0011\u001d\ty\u0004\u0001C\u0001\u0003\u0003B\u0001\"a\u0014\u0001A\u0003&\u00111\t\u0005\t\u0003#\u0002A\u0011A\u001c\u0002T!9\u0011\u0011\f\u0001\u0005B\u0005m\u0003\u0002CA/\u0001\u0011\u0005Q'a\u0017\t\u0011\u0005}\u0003\u00011A\u0005\u0002IC\u0011\"!\u0019\u0001\u0001\u0004%\t!a\u0019\t\u000f\u0005\u001d\u0004\u0001)Q\u0005'\"A\u0011\u0011\u000e\u0001A\u0002\u0013\u0005A\u0010C\u0005\u0002l\u0001\u0001\r\u0011\"\u0001\u0002n!9\u0011\u0011\u000f\u0001!B\u0013i\b\u0002CA:\u0001\u0001\u0007I\u0011\u0001?\t\u0013\u0005U\u0004\u00011A\u0005\u0002\u0005]\u0004bBA>\u0001\u0001\u0006K! \u0005\t\u0003{\u0002\u0001\u0019!C\u0001y\"I\u0011q\u0010\u0001A\u0002\u0013\u0005\u0011\u0011\u0011\u0005\b\u0003\u000b\u0003\u0001\u0015)\u0003~\u0011!\t9\t\u0001C\u0001o\u0005%\u0005\u0002CAH\u0001\u0011\u0005q'!%\t\u0011\u0005M\u0006\u0001\"\u00018\u0003kCa!a.\u0001\t\u0003a\bBBA]\u0001\u0011\u0005A\u0010\u0003\u0004\u0002<\u0002!\t\u0001 \u0005\u0007\u0003{\u0003A\u0011\u0001?\t\r\u0005}\u0006\u0001\"\u0001e\u0011\u0019\t\t\r\u0001C\u0001I\"1\u00111\u0019\u0001\u0005\u0002IC\u0001\"!2\u0001\t\u00039\u0014q\u0019\u0002\t)\u0006\u001c8.\u00138g_*\u0011agN\u0001\ng\u000eDW\rZ;mKJT!\u0001O\u001d\u0002\u000bM\u0004\u0018M]6\u000b\u0005iZ\u0014AB1qC\u000eDWMC\u0001=\u0003\ry'oZ\u0002\u0001'\r\u0001q(\u0012\t\u0003\u0001\u000ek\u0011!\u0011\u0006\u0002\u0005\u0006)1oY1mC&\u0011A)\u0011\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0019seBA$M\u001d\tA5*D\u0001J\u0015\tQU(\u0001\u0004=e>|GOP\u0005\u0002\u0005&\u0011Q*Q\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0005KA\u0005DY>tW-\u00192mK*\u0011Q*Q\u0001\u0007i\u0006\u001c8.\u00133\u0016\u0003M\u0003\"\u0001\u0011+\n\u0005U\u000b%\u0001\u0002'p]\u001e\fq\u0001^1tW&#\u0007%A\u0003j]\u0012,\u00070F\u0001Z!\t\u0001%,\u0003\u0002\\\u0003\n\u0019\u0011J\u001c;\u0002\r%tG-\u001a=!\u00035\tG\u000f^3naRtU/\u001c2fe\u0006q\u0011\r\u001e;f[B$h*^7cKJ\u0004\u0013a\u00039beRLG/[8o\u0013\u0012\fA\u0002]1si&$\u0018n\u001c8JI\u0002\n!\u0002\\1v]\u000eDG+[7f\u0003-a\u0017-\u001e8dQRKW.\u001a\u0011\u0002\u0015\u0015DXmY;u_JLE-F\u0001f!\t1'N\u0004\u0002hQB\u0011\u0001*Q\u0005\u0003S\u0006\u000ba\u0001\u0015:fI\u00164\u0017BA6m\u0005\u0019\u0019FO]5oO*\u0011\u0011.Q\u0001\fKb,7-\u001e;pe&#\u0007%\u0001\u0003i_N$\u0018!\u00025pgR\u0004\u0013\u0001\u0004;bg.dunY1mSRLX#\u0001:\u0011\u0005M<hB\u0001;v\u001b\u0005)\u0014B\u0001<6\u00031!\u0016m]6M_\u000e\fG.\u001b;z\u0013\tA\u0018P\u0001\u0007UCN\\Gj\\2bY&$\u0018P\u0003\u0002wk\u0005iA/Y:l\u0019>\u001c\u0017\r\\5us\u0002\n1b\u001d9fGVd\u0017\r^5wKV\tQ\u0010\u0005\u0002A}&\u0011q0\u0011\u0002\b\u0005>|G.Z1o\u00031\u0019\b/Z2vY\u0006$\u0018N^3!\u0003\u0019a\u0014N\\5u}Q!\u0012qAA\u0005\u0003\u0017\ti!a\u0004\u0002\u0012\u0005M\u0011QCA\f\u00033\u0001\"\u0001\u001e\u0001\t\u000bE\u001b\u0002\u0019A*\t\u000b]\u001b\u0002\u0019A-\t\u000bu\u001b\u0002\u0019A-\t\u000b}\u001b\u0002\u0019A-\t\u000b\u0005\u001c\u0002\u0019A*\t\u000b\r\u001c\u0002\u0019A3\t\u000b9\u001c\u0002\u0019A3\t\u000bA\u001c\u0002\u0019\u0001:\t\u000bm\u001c\u0002\u0019A?\u0015%\u0005\u001d\u0011QDA\u0010\u0003C\t\u0019#!\n\u0002(\u0005%\u00121\u0006\u0005\u0006#R\u0001\ra\u0015\u0005\u0006/R\u0001\r!\u0017\u0005\u0006;R\u0001\r!\u0017\u0005\u0006CR\u0001\ra\u0015\u0005\u0006GR\u0001\r!\u001a\u0005\u0006]R\u0001\r!\u001a\u0005\u0006aR\u0001\rA\u001d\u0005\u0006wR\u0001\r!`\u0001\u0012O\u0016$H/\u001b8h%\u0016\u001cX\u000f\u001c;US6,\u0017!F4fiRLgn\u001a*fgVdG\u000fV5nK~#S-\u001d\u000b\u0005\u0003g\tI\u0004E\u0002A\u0003kI1!a\u000eB\u0005\u0011)f.\u001b;\t\u0011\u0005mb#!AA\u0002M\u000b1\u0001\u001f\u00132\u0003I9W\r\u001e;j]\u001e\u0014Vm];miRKW.\u001a\u0011\u0002\u0019\u0005\u001c7-^7vY\u0006\u0014G.Z:\u0016\u0005\u0005\r\u0003#\u0002$\u0002F\u0005%\u0013bAA$!\n\u00191+Z9\u0011\u0007Q\fY%C\u0002\u0002NU\u0012q\"Q2dk6,H.\u00192mK&sgm\\\u0001\u000e?\u0006\u001c7-^7vY\u0006\u0014G.Z:\u0002\u001fM,G/Q2dk6,H.\u00192mKN$B!a\r\u0002V!9\u0011q\u000b\u000eA\u0002\u0005\r\u0013a\u00048fo\u0006\u001b7-^7vY\u0006\u0014G.Z:\u0002\u000b\rdwN\\3\u0015\u0005\u0005\u001d\u0011AG2m_:,w+\u001b;i\u000b6\u0004H/_!dGVlW\u000f\\1cY\u0016\u001c\u0018A\u00034j]&\u001c\b\u000eV5nK\u0006qa-\u001b8jg\"$\u0016.\\3`I\u0015\fH\u0003BA\u001a\u0003KB\u0001\"a\u000f\u001f\u0003\u0003\u0005\raU\u0001\fM&t\u0017n\u001d5US6,\u0007%\u0001\u0004gC&dW\rZ\u0001\u000bM\u0006LG.\u001a3`I\u0015\fH\u0003BA\u001a\u0003_B\u0001\"a\u000f\"\u0003\u0003\u0005\r!`\u0001\bM\u0006LG.\u001a3!\u0003\u0019Y\u0017\u000e\u001c7fI\u0006Q1.\u001b7mK\u0012|F%Z9\u0015\t\u0005M\u0012\u0011\u0010\u0005\t\u0003w!\u0013\u0011!a\u0001{\u000691.\u001b7mK\u0012\u0004\u0013!\u00037bk:\u001c\u0007.\u001b8h\u00035a\u0017-\u001e8dQ&twm\u0018\u0013fcR!\u00111GAB\u0011!\tYdJA\u0001\u0002\u0004i\u0018A\u00037bk:\u001c\u0007.\u001b8hA\u0005\tR.\u0019:l\u000f\u0016$H/\u001b8h%\u0016\u001cX\u000f\u001c;\u0015\t\u0005M\u00121\u0012\u0005\u0007\u0003\u001bK\u0003\u0019A*\u0002\tQLW.Z\u0001\r[\u0006\u00148NR5oSNDW\r\u001a\u000b\u0007\u0003g\t\u0019*!-\t\u000f\u0005U%\u00061\u0001\u0002\u0018\u0006)1\u000f^1uKB!\u0011\u0011TAV\u001d\u0011\tY*a*\u000f\t\u0005u\u0015Q\u0015\b\u0005\u0003?\u000b\u0019KD\u0002I\u0003CK\u0011\u0001P\u0005\u0003umJ!\u0001O\u001d\n\u0007\u0005%v'A\u0005UCN\\7\u000b^1uK&!\u0011QVAX\u0005%!\u0016m]6Ti\u0006$XMC\u0002\u0002*^Ba!!$+\u0001\u0004\u0019\u0016a\u00047bk:\u001c\u0007nU;dG\u0016,G-\u001a3\u0015\u0005\u0005M\u0012!D4fiRLgn\u001a*fgVdG/\u0001\u0005gS:L7\u000f[3e\u0003)\u0019XoY2fgN4W\u000f\\\u0001\beVtg.\u001b8h\u0003\u0019\u0019H/\u0019;vg\u0006\u0011\u0011\u000eZ\u0001\tIV\u0014\u0018\r^5p]\u0006YA/[7f%Vtg.\u001b8h)\r\u0019\u0016\u0011\u001a\u0005\u0007\u0003\u0017\u001c\u0004\u0019A*\u0002\u0017\r,(O]3oiRKW.\u001a\u0015\u0004\u0001\u0005=\u0007\u0003BAi\u0003/l!!a5\u000b\u0007\u0005Uw'\u0001\u0006b]:|G/\u0019;j_:LA!!7\u0002T\naA)\u001a<fY>\u0004XM]!qS\u0002"
)
public class TaskInfo implements Cloneable {
   private final long taskId;
   private final int index;
   private final int attemptNumber;
   private final int partitionId;
   private final long launchTime;
   private final String executorId;
   private final String host;
   private final Enumeration.Value taskLocality;
   private final boolean speculative;
   private long gettingResultTime;
   private Seq _accumulables;
   private long finishTime;
   private boolean failed;
   private boolean killed;
   private boolean launching;

   public long taskId() {
      return this.taskId;
   }

   public int index() {
      return this.index;
   }

   public int attemptNumber() {
      return this.attemptNumber;
   }

   public int partitionId() {
      return this.partitionId;
   }

   public long launchTime() {
      return this.launchTime;
   }

   public String executorId() {
      return this.executorId;
   }

   public String host() {
      return this.host;
   }

   public Enumeration.Value taskLocality() {
      return this.taskLocality;
   }

   public boolean speculative() {
      return this.speculative;
   }

   public long gettingResultTime() {
      return this.gettingResultTime;
   }

   public void gettingResultTime_$eq(final long x$1) {
      this.gettingResultTime = x$1;
   }

   public Seq accumulables() {
      return this._accumulables;
   }

   public void setAccumulables(final Seq newAccumulables) {
      this._accumulables = newAccumulables;
   }

   public TaskInfo clone() {
      return (TaskInfo)super.clone();
   }

   public TaskInfo cloneWithEmptyAccumulables() {
      TaskInfo cloned = this.clone();
      cloned.setAccumulables(.MODULE$);
      return cloned;
   }

   public long finishTime() {
      return this.finishTime;
   }

   public void finishTime_$eq(final long x$1) {
      this.finishTime = x$1;
   }

   public boolean failed() {
      return this.failed;
   }

   public void failed_$eq(final boolean x$1) {
      this.failed = x$1;
   }

   public boolean killed() {
      return this.killed;
   }

   public void killed_$eq(final boolean x$1) {
      this.killed = x$1;
   }

   public boolean launching() {
      return this.launching;
   }

   public void launching_$eq(final boolean x$1) {
      this.launching = x$1;
   }

   public void markGettingResult(final long time) {
      this.gettingResultTime_$eq(time);
   }

   public void markFinished(final Enumeration.Value state, final long time) {
      boolean var10001;
      label35: {
         label34: {
            scala.Predef..MODULE$.assert(time > 0L);
            this.finishTime_$eq(time);
            Enumeration.Value var4 = TaskState$.MODULE$.FAILED();
            if (state == null) {
               if (var4 == null) {
                  break label34;
               }
            } else if (state.equals(var4)) {
               break label34;
            }

            var10001 = false;
            break label35;
         }

         var10001 = true;
      }

      label27: {
         label26: {
            this.failed_$eq(var10001);
            Enumeration.Value var5 = TaskState$.MODULE$.KILLED();
            if (state == null) {
               if (var5 == null) {
                  break label26;
               }
            } else if (state.equals(var5)) {
               break label26;
            }

            var10001 = false;
            break label27;
         }

         var10001 = true;
      }

      this.killed_$eq(var10001);
   }

   public void launchSucceeded() {
      this.launching_$eq(false);
   }

   public boolean gettingResult() {
      return this.gettingResultTime() != 0L;
   }

   public boolean finished() {
      return this.finishTime() != 0L;
   }

   public boolean successful() {
      return this.finished() && !this.failed() && !this.killed();
   }

   public boolean running() {
      return !this.finished();
   }

   public String status() {
      if (this.running()) {
         return this.gettingResult() ? "GET RESULT" : "RUNNING";
      } else if (this.failed()) {
         return "FAILED";
      } else if (this.killed()) {
         return "KILLED";
      } else {
         return this.successful() ? "SUCCESS" : "UNKNOWN";
      }
   }

   public String id() {
      int var10000 = this.index();
      return var10000 + "." + this.attemptNumber();
   }

   public long duration() {
      if (!this.finished()) {
         throw SparkCoreErrors$.MODULE$.durationCalledOnUnfinishedTaskError();
      } else {
         return this.finishTime() - this.launchTime();
      }
   }

   public long timeRunning(final long currentTime) {
      return currentTime - this.launchTime();
   }

   public TaskInfo(final long taskId, final int index, final int attemptNumber, final int partitionId, final long launchTime, final String executorId, final String host, final Enumeration.Value taskLocality, final boolean speculative) {
      this.taskId = taskId;
      this.index = index;
      this.attemptNumber = attemptNumber;
      this.partitionId = partitionId;
      this.launchTime = launchTime;
      this.executorId = executorId;
      this.host = host;
      this.taskLocality = taskLocality;
      this.speculative = speculative;
      this.gettingResultTime = 0L;
      this._accumulables = .MODULE$;
      this.finishTime = 0L;
      this.failed = false;
      this.killed = false;
      this.launching = true;
   }

   public TaskInfo(final long taskId, final int index, final int attemptNumber, final long launchTime, final String executorId, final String host, final Enumeration.Value taskLocality, final boolean speculative) {
      this(taskId, index, attemptNumber, -1, launchTime, executorId, host, taskLocality, speculative);
   }
}
