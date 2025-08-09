package org.apache.spark;

import java.io.Closeable;
import java.io.Serializable;
import java.util.Properties;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.memory.TaskMemoryManager;
import org.apache.spark.scheduler.Task;
import org.apache.spark.shuffle.FetchFailedException;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.TaskCompletionListener;
import org.apache.spark.util.TaskFailureListener;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tMv!B\u0017/\u0011\u0003)d!B\u001c/\u0011\u0003A\u0004\"B$\u0002\t\u0003A\u0005\"B%\u0002\t\u0003Q\u0005b\u0002BF\u0003\u0011\u0005\u0011q\u0007\u0005\t\u0005\u001b\u000b\u0001\u0015!\u0003\u0003\u0010\"A!QS\u0001\u0005\u00129\u00129\n\u0003\u0005\u0003\u001e\u0006!\tBLAr\u0011!\u0011y*\u0001C\u0001]\t\u0005\u0006\"\u0003BU\u0003\u0005\u0005I\u0011\u0002BV\r\u00159d&!\u0001M\u0011\u00159%\u0002\"\u0001K\u0011\u0015A&B\"\u0001Z\u0011\u0015i&B\"\u0001Z\u0011\u0015q&B\"\u0001Z\u0011\u0015y&B\"\u0001a\u0011\u0015y&\u0002\"\u0001j\u0011\u0015Y(B\"\u0001}\u0011\u0019Y(\u0002\"\u0001\u0002\u0004!A\u0011\u0011\u0004\u0006\u0005\u00029\nY\u0002C\u0004\u00026)1\t!a\u000e\t\u000f\u0005}\"B\"\u0001\u00028!9\u0011\u0011\t\u0006\u0007\u0002\u0005]\u0002bBA\"\u0015\u0019\u0005\u0011q\u0007\u0005\b\u0003\u000bRa\u0011AA\u001c\u0011\u001d\t9E\u0003D\u0001\u0003\u0013Bq!!\u0015\u000b\r\u0003\t\u0019\u0006C\u0004\u0002j)1\t!a\u000e\t\u000f\u0005u$B\"\u0001\u0002\u0000!9\u00111\u0014\u0006\u0007\u0002\u0005u\u0005bBAU\u0015\u0019\u0005\u00111\u0016\u0005\b\u0003\u0003Ta\u0011AAb\u0011!\t\tO\u0003D\u0001]\u0005\r\b\u0002CAs\u0015\u0019\u0005a&a:\t\u0011\u0005=(B\"\u0001/\u0003cD\u0001\"a@\u000b\r\u0003q#\u0011\u0001\u0005\t\u00057Qa\u0011\u0001\u0018\u0003\u001e!A!q\u0006\u0006\u0007\u00029\u0012\t\u0004\u0003\u0005\u00038)1\tA\fB\u001d\u0011!\u0011yD\u0003D\u0001]\t\u0005\u0003\u0002\u0003B\u0011\u0015\u0019\u0005aFa\u0012\t\u0011\t-#B\"\u0001/\u0005\u001bBqA!\u0016\u000b\r\u0003q\u0013\f\u0003\u0005\u0003X)1\tA\fB-\u0011!\u0011yG\u0003D\u0001]\tE\u0014a\u0003+bg.\u001cuN\u001c;fqRT!a\f\u0019\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0012\u0014AB1qC\u000eDWMC\u00014\u0003\ry'oZ\u0002\u0001!\t1\u0014!D\u0001/\u0005-!\u0016m]6D_:$X\r\u001f;\u0014\u0007\u0005It\b\u0005\u0002;{5\t1HC\u0001=\u0003\u0015\u00198-\u00197b\u0013\tq4H\u0001\u0004B]f\u0014VM\u001a\t\u0003\u0001\u0016k\u0011!\u0011\u0006\u0003\u0005\u000e\u000b!![8\u000b\u0003\u0011\u000bAA[1wC&\u0011a)\u0011\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003U\n1aZ3u)\u0005Y\u0005C\u0001\u001c\u000b'\rQ\u0011(\u0014\t\u0003\u001dZs!a\u0014+\u000f\u0005A\u001bV\"A)\u000b\u0005I#\u0014A\u0002\u001fs_>$h(C\u0001=\u0013\t)6(A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0019;&BA+<\u0003-I7oQ8na2,G/\u001a3\u0015\u0003i\u0003\"AO.\n\u0005q[$a\u0002\"p_2,\u0017M\\\u0001\tSN4\u0015-\u001b7fI\u0006i\u0011n]%oi\u0016\u0014(/\u001e9uK\u0012\f\u0011$\u00193e)\u0006\u001c8nQ8na2,G/[8o\u0019&\u001cH/\u001a8feR\u00111*\u0019\u0005\u0006E>\u0001\raY\u0001\tY&\u001cH/\u001a8feB\u0011AmZ\u0007\u0002K*\u0011aML\u0001\u0005kRLG.\u0003\u0002iK\n1B+Y:l\u0007>l\u0007\u000f\\3uS>tG*[:uK:,'/\u0006\u0002keR\u00111j\u001b\u0005\u0006YB\u0001\r!\\\u0001\u0002MB!!H\\&q\u0013\ty7HA\u0005Gk:\u001cG/[8ocA\u0011\u0011O\u001d\u0007\u0001\t\u0015\u0019\bC1\u0001u\u0005\u0005)\u0016CA;y!\tQd/\u0003\u0002xw\t9aj\u001c;iS:<\u0007C\u0001\u001ez\u0013\tQ8HA\u0002B]f\fa#\u00193e)\u0006\u001c8NR1jYV\u0014X\rT5ti\u0016tWM\u001d\u000b\u0003\u0017vDQAY\tA\u0002y\u0004\"\u0001Z@\n\u0007\u0005\u0005QMA\nUCN\\g)Y5mkJ,G*[:uK:,'\u000fF\u0002L\u0003\u000bAa\u0001\u001c\nA\u0002\u0005\u001d\u0001\u0003\u0003\u001e\u0002\n-\u000bi!a\u0005\n\u0007\u0005-1HA\u0005Gk:\u001cG/[8oeA\u0019a*a\u0004\n\u0007\u0005EqKA\u0005UQJ|w/\u00192mKB\u0019!(!\u0006\n\u0007\u0005]1H\u0001\u0003V]&$\u0018\u0001\u0006:v]R\u000b7o[,ji\"d\u0015n\u001d;f]\u0016\u00148/\u0006\u0003\u0002\u001e\u0005\u0005B\u0003BA\u0010\u0003K\u00012!]A\u0011\t\u0019\t\u0019c\u0005b\u0001i\n\tA\u000bC\u0004\u0002(M\u0001\r!!\u000b\u0002\tQ\f7o\u001b\t\u0007\u0003W\t\t$a\b\u000e\u0005\u00055\"bAA\u0018]\u0005I1o\u00195fIVdWM]\u0005\u0005\u0003g\tiC\u0001\u0003UCN\\\u0017aB:uC\u001e,\u0017\n\u001a\u000b\u0003\u0003s\u00012AOA\u001e\u0013\r\tid\u000f\u0002\u0004\u0013:$\u0018AE:uC\u001e,\u0017\t\u001e;f[B$h*^7cKJ\f1\u0002]1si&$\u0018n\u001c8JI\u0006ia.^7QCJ$\u0018\u000e^5p]N\fQ\"\u0019;uK6\u0004HOT;nE\u0016\u0014\u0018!\u0004;bg.\fE\u000f^3naRLE\r\u0006\u0002\u0002LA\u0019!(!\u0014\n\u0007\u0005=3H\u0001\u0003M_:<\u0017\u0001E4fi2{7-\u00197Qe>\u0004XM\u001d;z)\u0011\t)&!\u001a\u0011\t\u0005]\u0013q\f\b\u0005\u00033\nY\u0006\u0005\u0002Qw%\u0019\u0011QL\u001e\u0002\rA\u0013X\rZ3g\u0013\u0011\t\t'a\u0019\u0003\rM#(/\u001b8h\u0015\r\tif\u000f\u0005\b\u0003OR\u0002\u0019AA+\u0003\rYW-_\u0001\u0005GB,8\u000fK\u0003\u001c\u0003[\nI\b\u0005\u0003\u0002p\u0005UTBAA9\u0015\r\t\u0019HL\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA<\u0003c\u0012QaU5oG\u0016\f#!a\u001f\u0002\u000bMr3G\f\u0019\u0002\u0013I,7o\\;sG\u0016\u001cHCAAA!!\t9&a!\u0002V\u0005\u001d\u0015\u0002BAC\u0003G\u00121!T1q!\u0011\tI)a$\u000e\u0005\u0005-%bAAG]\u0005A!/Z:pkJ\u001cW-\u0003\u0003\u0002\u0012\u0006-%a\u0005*fg>,(oY3J]\u001a|'/\\1uS>t\u0007f\u0001\u000f\u0002\u0016B!\u0011qNAL\u0013\u0011\tI*!\u001d\u0003\u0011\u00153x\u000e\u001c<j]\u001e\fQB]3t_V\u00148-Z:K\u001b\u0006\u0004HCAAP!!\t\t+!*\u0002V\u0005\u001dUBAAR\u0015\t17)\u0003\u0003\u0002\u0006\u0006\r\u0006fA\u000f\u0002\u0016\u0006YA/Y:l\u001b\u0016$(/[2t)\t\ti\u000b\u0005\u0003\u00020\u0006UVBAAY\u0015\r\t\u0019LL\u0001\tKb,7-\u001e;pe&!\u0011qWAY\u0005-!\u0016m]6NKR\u0014\u0018nY:)\u0007y\tY\f\u0005\u0003\u0002p\u0005u\u0016\u0002BA`\u0003c\u0012A\u0002R3wK2|\u0007/\u001a:Ba&\f\u0011cZ3u\u001b\u0016$(/[2t'>,(oY3t)\u0011\t)-a7\u0011\u000b9\u000b9-a3\n\u0007\u0005%wKA\u0002TKF\u0004B!!4\u0002X6\u0011\u0011q\u001a\u0006\u0005\u0003#\f\u0019.\u0001\u0004t_V\u00148-\u001a\u0006\u0004\u0003+t\u0013aB7fiJL7m]\u0005\u0005\u00033\fyM\u0001\u0004T_V\u00148-\u001a\u0005\b\u0003;|\u0002\u0019AA+\u0003)\u0019x.\u001e:dK:\u000bW.\u001a\u0015\u0004?\u0005m\u0016!F6jY2$\u0016m]6JM&sG/\u001a:skB$X\r\u001a\u000b\u0003\u0003'\tQbZ3u\u0017&dGNU3bg>tGCAAu!\u0015Q\u00141^A+\u0013\r\tio\u000f\u0002\u0007\u001fB$\u0018n\u001c8\u0002#Q\f7o['f[>\u0014\u00180T1oC\u001e,'\u000f\u0006\u0002\u0002tB!\u0011Q_A~\u001b\t\t9PC\u0002\u0002z:\na!\\3n_JL\u0018\u0002BA\u007f\u0003o\u0014\u0011\u0003V1tW6+Wn\u001c:z\u001b\u0006t\u0017mZ3s\u0003M\u0011XmZ5ti\u0016\u0014\u0018iY2v[Vd\u0017\r^8s)\u0011\t\u0019Ba\u0001\t\u000f\t\u00151\u00051\u0001\u0003\b\u0005\t\u0011\r\r\u0004\u0003\n\tE!q\u0003\t\bI\n-!q\u0002B\u000b\u0013\r\u0011i!\u001a\u0002\u000e\u0003\u000e\u001cW/\\;mCR|'O\u0016\u001a\u0011\u0007E\u0014\t\u0002B\u0006\u0003\u0014\t\r\u0011\u0011!A\u0001\u0006\u0003!(aA0%cA\u0019\u0011Oa\u0006\u0005\u0017\te!1AA\u0001\u0002\u0003\u0015\t\u0001\u001e\u0002\u0004?\u0012\u0012\u0014AD:fi\u001a+Go\u00195GC&dW\r\u001a\u000b\u0005\u0003'\u0011y\u0002C\u0004\u0003\"\u0011\u0002\rAa\t\u0002\u0017\u0019,Go\u00195GC&dW\r\u001a\t\u0005\u0005K\u0011Y#\u0004\u0002\u0003()\u0019!\u0011\u0006\u0018\u0002\u000fMDWO\u001a4mK&!!Q\u0006B\u0014\u0005Q1U\r^2i\r\u0006LG.\u001a3Fq\u000e,\u0007\u000f^5p]\u0006yQ.\u0019:l\u0013:$XM\u001d:vaR,G\r\u0006\u0003\u0002\u0014\tM\u0002b\u0002B\u001bK\u0001\u0007\u0011QK\u0001\u0007e\u0016\f7o\u001c8\u0002\u001d5\f'o\u001b+bg.4\u0015-\u001b7fIR!\u00111\u0003B\u001e\u0011\u001d\u0011iD\na\u0001\u0003\u001b\tQ!\u001a:s_J\f\u0011#\\1sWR\u000b7o[\"p[BdW\r^3e)\u0011\t\u0019Ba\u0011\t\u000f\tur\u00051\u0001\u0003FA)!(a;\u0002\u000eU\u0011!\u0011\n\t\u0006u\u0005-(1E\u0001\u0013O\u0016$Hj\\2bYB\u0013x\u000e]3si&,7/\u0006\u0002\u0003PA!\u0011\u0011\u0015B)\u0013\u0011\u0011\u0019&a)\u0003\u0015A\u0013x\u000e]3si&,7/A\u0007j]R,'O];qi&\u0014G.Z\u0001\u0011a\u0016tG-\u001b8h\u0013:$XM\u001d:vaR$b!a\u0005\u0003\\\t5\u0004b\u0002B/W\u0001\u0007!qL\u0001\u0012i\"\u0014X-\u00193U_&sG/\u001a:skB$\b#\u0002\u001e\u0002l\n\u0005\u0004\u0003\u0002B2\u0005Sj!A!\u001a\u000b\u0007\t\u001d4)\u0001\u0003mC:<\u0017\u0002\u0002B6\u0005K\u0012a\u0001\u00165sK\u0006$\u0007b\u0002B\u001bW\u0001\u0007\u0011QK\u0001\u001eGJ,\u0017\r^3SKN|WO]2f+:Lg\u000e^3seV\u0004H/\u001b2msV!!1\u000fB<)\u0011\u0011)H!!\u0011\u0007E\u00149\bB\u0004\u0002$1\u0012\rA!\u001f\u0012\u0007U\u0014Y\bE\u0002A\u0005{J1Aa B\u0005%\u0019En\\:fC\ndW\r\u0003\u0005\u0003\u00042\"\t\u0019\u0001BC\u0003=\u0011Xm]8ve\u000e,')^5mI\u0016\u0014\b#\u0002\u001e\u0003\b\nU\u0014b\u0001BEw\tAAHY=oC6,g(\u0001\bhKR\u0004\u0016M\u001d;ji&|g.\u00133\u0002\u0017Q\f7o[\"p]R,\u0007\u0010\u001e\t\u0006\u0005G\u0012\tjS\u0005\u0005\u0005'\u0013)GA\u0006UQJ,\u0017\r\u001a'pG\u0006d\u0017AD:fiR\u000b7o[\"p]R,\u0007\u0010\u001e\u000b\u0005\u0003'\u0011I\n\u0003\u0004\u0003\u001c\u001a\u0001\raS\u0001\u0003i\u000e\fQ!\u001e8tKR\fQ!Z7qif$\"Aa)\u0011\u0007Y\u0012)+C\u0002\u0003(:\u0012q\u0002V1tW\u000e{g\u000e^3yi&k\u0007\u000f\\\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005[\u0003BAa\u0019\u00030&!!\u0011\u0017B3\u0005\u0019y%M[3di\u0002"
)
public abstract class TaskContext implements Serializable {
   public static int getPartitionId() {
      return TaskContext$.MODULE$.getPartitionId();
   }

   public static TaskContext get() {
      return TaskContext$.MODULE$.get();
   }

   public abstract boolean isCompleted();

   public abstract boolean isFailed();

   public abstract boolean isInterrupted();

   public abstract TaskContext addTaskCompletionListener(final TaskCompletionListener listener);

   public TaskContext addTaskCompletionListener(final Function1 f) {
      return this.addTaskCompletionListener(new TaskCompletionListener(f) {
         private final Function1 f$1;

         public void onTaskCompletion(final TaskContext context) {
            this.f$1.apply(context);
         }

         public {
            this.f$1 = f$1;
         }
      });
   }

   public abstract TaskContext addTaskFailureListener(final TaskFailureListener listener);

   public TaskContext addTaskFailureListener(final Function2 f) {
      return this.addTaskFailureListener(new TaskFailureListener(f) {
         private final Function2 f$2;

         public void onTaskFailure(final TaskContext context, final Throwable error) {
            this.f$2.apply(context, error);
         }

         public {
            this.f$2 = f$2;
         }
      });
   }

   public Object runTaskWithListeners(final Task task) {
      try {
         this.killTaskIfInterrupted();
         task.runTask(this);
      } catch (Throwable var12) {
         Throwable e = var12;

         try {
            this.markTaskFailed(e);
         } catch (Throwable var11) {
            var12.addSuppressed(var11);
         }

         try {
            this.markTaskCompleted(new Some(e));
         } catch (Throwable var10) {
            var12.addSuppressed(var10);
         }

         throw var12;
      } finally {
         this.markTaskCompleted(.MODULE$);
      }

      return this;
   }

   public abstract int stageId();

   public abstract int stageAttemptNumber();

   public abstract int partitionId();

   public abstract int numPartitions();

   public abstract int attemptNumber();

   public abstract long taskAttemptId();

   public abstract String getLocalProperty(final String key);

   public abstract int cpus();

   @Evolving
   public abstract Map resources();

   @Evolving
   public abstract java.util.Map resourcesJMap();

   @DeveloperApi
   public abstract TaskMetrics taskMetrics();

   @DeveloperApi
   public abstract Seq getMetricsSources(final String sourceName);

   public abstract void killTaskIfInterrupted();

   public abstract Option getKillReason();

   public abstract TaskMemoryManager taskMemoryManager();

   public abstract void registerAccumulator(final AccumulatorV2 a);

   public abstract void setFetchFailed(final FetchFailedException fetchFailed);

   public abstract void markInterrupted(final String reason);

   public abstract void markTaskFailed(final Throwable error);

   public abstract void markTaskCompleted(final Option error);

   public abstract Option fetchFailed();

   public abstract Properties getLocalProperties();

   public abstract boolean interruptible();

   public abstract void pendingInterrupt(final Option threadToInterrupt, final String reason);

   public abstract Closeable createResourceUninterruptibly(final Function0 resourceBuilder);
}
