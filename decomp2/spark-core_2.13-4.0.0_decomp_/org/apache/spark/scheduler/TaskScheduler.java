package org.apache.spark.scheduler;

import org.apache.spark.storage.BlockManagerId;
import scala.Enumeration;
import scala.Option;
import scala.Tuple2;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uh\u0001\u0003\f\u0018!\u0003\r\t!G\u0010\t\u000b\u0019\u0002A\u0011\u0001\u0015\t\u000f1\u0002!\u0019!C\u0005[!)a\u0007\u0001D\u0001o!)A\b\u0001D\u0001{!)\u0001\u000b\u0001D\u0001Q!)\u0011\u000b\u0001C\u0001Q!)!\u000b\u0001D\u0001'\"9\u0011\fAI\u0001\n\u0003Q\u0006\"B3\u0001\r\u00031\u0007\"\u00027\u0001\r\u0003i\u0007bBA\u0002\u0001\u0019\u0005\u0011Q\u0001\u0005\b\u0003\u001f\u0001a\u0011AA\t\u0011\u001d\tI\u0002\u0001D\u0001\u00037Aq!a\n\u0001\r\u0003\tI\u0003C\u0004\u0002,\u00011\t!!\f\t\u000f\u0005E\u0006\u0001\"\u0001\u00024\"9\u0011Q\u0017\u0001\u0007\u0002\u0005]\u0006bBAd\u0001\u0019\u0005\u0011\u0011\u001a\u0005\b\u00033\u0004a\u0011AAn\u0011\u001d\t9\u000f\u0001D\u0001\u0003SDq!a>\u0001\r\u0003\tIPA\u0007UCN\\7k\u00195fIVdWM\u001d\u0006\u00031e\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005iY\u0012!B:qCJ\\'B\u0001\u000f\u001e\u0003\u0019\t\u0007/Y2iK*\ta$A\u0002pe\u001e\u001c\"\u0001\u0001\u0011\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#A\u0015\u0011\u0005\u0005R\u0013BA\u0016#\u0005\u0011)f.\u001b;\u0002\u000b\u0005\u0004\b/\u00133\u0016\u00039\u0002\"a\f\u001b\u000e\u0003AR!!\r\u001a\u0002\t1\fgn\u001a\u0006\u0002g\u0005!!.\u0019<b\u0013\t)\u0004G\u0001\u0004TiJLgnZ\u0001\te>|G\u000fU8pYV\t\u0001\b\u0005\u0002:u5\tq#\u0003\u0002</\t!\u0001k\\8m\u00039\u00198\r[3ek2LgnZ'pI\u0016,\u0012A\u0010\t\u0003\u007f5s!\u0001Q&\u000f\u0005\u0005SeB\u0001\"J\u001d\t\u0019\u0005J\u0004\u0002E\u000f6\tQI\u0003\u0002GO\u00051AH]8pizJ\u0011AH\u0005\u00039uI!AG\u000e\n\u0005aI\u0012B\u0001'\u0018\u00039\u00196\r[3ek2LgnZ'pI\u0016L!AT(\u0003\u001dM\u001b\u0007.\u001a3vY&tw-T8eK*\u0011AjF\u0001\u0006gR\f'\u000f^\u0001\u000ea>\u001cHo\u0015;beRDun\\6\u0002\tM$x\u000e\u001d\u000b\u0003SQCq!V\u0004\u0011\u0002\u0003\u0007a+\u0001\u0005fq&$8i\u001c3f!\t\ts+\u0003\u0002YE\t\u0019\u0011J\u001c;\u0002\u001dM$x\u000e\u001d\u0013eK\u001a\fW\u000f\u001c;%cU\t1L\u000b\u0002W9.\nQ\f\u0005\u0002_G6\tqL\u0003\u0002aC\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003E\n\n!\"\u00198o_R\fG/[8o\u0013\t!wLA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1b];c[&$H+Y:lgR\u0011\u0011f\u001a\u0005\u0006Q&\u0001\r![\u0001\bi\u0006\u001c8nU3u!\tI$.\u0003\u0002l/\t9A+Y:l'\u0016$\u0018aD6jY2$\u0016m]6BiR,W\u000e\u001d;\u0015\t9\fh\u000f\u001f\t\u0003C=L!\u0001\u001d\u0012\u0003\u000f\t{w\u000e\\3b]\")!O\u0003a\u0001g\u00061A/Y:l\u0013\u0012\u0004\"!\t;\n\u0005U\u0014#\u0001\u0002'p]\u001eDQa\u001e\u0006A\u00029\fq\"\u001b8uKJ\u0014X\u000f\u001d;UQJ,\u0017\r\u001a\u0005\u0006s*\u0001\rA_\u0001\u0007e\u0016\f7o\u001c8\u0011\u0005m|hB\u0001?~!\t!%%\u0003\u0002\u007fE\u00051\u0001K]3eK\u001aL1!NA\u0001\u0015\tq(%A\nlS2d\u0017\t\u001c7UCN\\\u0017\t\u001e;f[B$8\u000fF\u0004*\u0003\u000f\tY!!\u0004\t\r\u0005%1\u00021\u0001W\u0003\u001d\u0019H/Y4f\u0013\u0012DQa^\u0006A\u00029DQ!_\u0006A\u0002i\f\u0011D\\8uS\u001aL\b+\u0019:uSRLwN\\\"p[BdW\r^5p]R)\u0011&a\u0005\u0002\u0016!1\u0011\u0011\u0002\u0007A\u0002YCa!a\u0006\r\u0001\u00041\u0016a\u00039beRLG/[8o\u0013\u0012\fqb]3u\t\u0006;5k\u00195fIVdWM\u001d\u000b\u0004S\u0005u\u0001bBA\u0010\u001b\u0001\u0007\u0011\u0011E\u0001\rI\u0006<7k\u00195fIVdWM\u001d\t\u0004s\u0005\r\u0012bAA\u0013/\taA)Q$TG\",G-\u001e7fe\u0006\u0011B-\u001a4bk2$\b+\u0019:bY2,G.[:n)\u00051\u0016!G3yK\u000e,Ho\u001c:IK\u0006\u0014HOY3biJ+7-Z5wK\u0012$\u0012B\\A\u0018\u0003g\ty(a$\t\r\u0005Er\u00021\u0001{\u0003\u0019)\u00070Z2JI\"9\u0011QG\bA\u0002\u0005]\u0012\u0001D1dGVlW\u000b\u001d3bi\u0016\u001c\b#B\u0011\u0002:\u0005u\u0012bAA\u001eE\t)\u0011I\u001d:bsB1\u0011%a\u0010t\u0003\u0007J1!!\u0011#\u0005\u0019!V\u000f\u001d7feA1\u0011QIA(\u0003+rA!a\u0012\u0002L9\u0019A)!\u0013\n\u0003\rJ1!!\u0014#\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0015\u0002T\t\u00191+Z9\u000b\u0007\u00055#\u0005\r\u0004\u0002X\u0005\u001d\u00141\u0010\t\t\u00033\ny&a\u0019\u0002z5\u0011\u00111\f\u0006\u0004\u0003;J\u0012\u0001B;uS2LA!!\u0019\u0002\\\ti\u0011iY2v[Vd\u0017\r^8s-J\u0002B!!\u001a\u0002h1\u0001A\u0001DA5\u0003g\t\t\u0011!A\u0003\u0002\u0005-$aA0%cE!\u0011QNA:!\r\t\u0013qN\u0005\u0004\u0003c\u0012#a\u0002(pi\"Lgn\u001a\t\u0004C\u0005U\u0014bAA<E\t\u0019\u0011I\\=\u0011\t\u0005\u0015\u00141\u0010\u0003\r\u0003{\n\u0019$!A\u0001\u0002\u000b\u0005\u00111\u000e\u0002\u0004?\u0012\u0012\u0004bBAA\u001f\u0001\u0007\u00111Q\u0001\u000fE2|7m['b]\u0006<WM]%e!\u0011\t))a#\u000e\u0005\u0005\u001d%bAAE3\u000591\u000f^8sC\u001e,\u0017\u0002BAG\u0003\u000f\u0013aB\u00117pG.l\u0015M\\1hKJLE\rC\u0004\u0002\u0012>\u0001\r!a%\u0002\u001f\u0015DXmY;u_J,\u0006\u000fZ1uKN\u0004\u0002\"!&\u0002 \u0006\r\u0016QU\u0007\u0003\u0003/SA!!'\u0002\u001c\u00069Q.\u001e;bE2,'bAAOE\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u0005\u0016q\u0013\u0002\u0004\u001b\u0006\u0004\b#B\u0011\u0002@Y3\u0006\u0003BAT\u0003[k!!!+\u000b\u0007\u0005-\u0016$\u0001\u0005fq\u0016\u001cW\u000f^8s\u0013\u0011\ty+!+\u0003\u001f\u0015CXmY;u_JlU\r\u001e:jGN\fQ\"\u00199qY&\u001c\u0017\r^5p]&#G#\u0001>\u0002)\u0015DXmY;u_J$UmY8n[&\u001c8/[8o)\u0015I\u0013\u0011XA_\u0011\u0019\tY,\u0005a\u0001u\u0006QQ\r_3dkR|'/\u00133\t\u000f\u0005}\u0016\u00031\u0001\u0002B\u0006\u0001B-Z2p[6L7o]5p]&sgm\u001c\t\u0004s\u0005\r\u0017bAAc/\tAR\t_3dkR|'\u000fR3d_6l\u0017n]:j_:LeNZ8\u00029\u001d,G/\u0012=fGV$xN\u001d#fG>lW.[:tS>t7\u000b^1uKR!\u00111ZAl!\u0015\t\u0013QZAi\u0013\r\tyM\t\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0007e\n\u0019.C\u0002\u0002V^\u0011\u0011$\u0012=fGV$xN\u001d#fG>lW.[:tS>t7\u000b^1uK\"1\u00111\u0018\nA\u0002i\fA\"\u001a=fGV$xN\u001d'pgR$R!KAo\u0003?Da!a/\u0014\u0001\u0004Q\bBB=\u0014\u0001\u0004\t\t\u000fE\u0002:\u0003GL1!!:\u0018\u0005I)\u00050Z2vi>\u0014Hj\\:t%\u0016\f7o\u001c8\u0002\u001b]|'o[3s%\u0016lwN^3e)\u001dI\u00131^Ax\u0003gDa!!<\u0015\u0001\u0004Q\u0018\u0001C<pe.,'/\u00133\t\r\u0005EH\u00031\u0001{\u0003\u0011Awn\u001d;\t\r\u0005UH\u00031\u0001{\u0003\u001diWm]:bO\u0016\fA#\u00199qY&\u001c\u0017\r^5p]\u0006#H/Z7qi&#GCAA~!\u0011\t\u0013Q\u001a>"
)
public interface TaskScheduler {
   void org$apache$spark$scheduler$TaskScheduler$_setter_$org$apache$spark$scheduler$TaskScheduler$$appId_$eq(final String x$1);

   String org$apache$spark$scheduler$TaskScheduler$$appId();

   Pool rootPool();

   Enumeration.Value schedulingMode();

   void start();

   // $FF: synthetic method
   static void postStartHook$(final TaskScheduler $this) {
      $this.postStartHook();
   }

   default void postStartHook() {
   }

   void stop(final int exitCode);

   // $FF: synthetic method
   static int stop$default$1$(final TaskScheduler $this) {
      return $this.stop$default$1();
   }

   default int stop$default$1() {
      return 0;
   }

   void submitTasks(final TaskSet taskSet);

   boolean killTaskAttempt(final long taskId, final boolean interruptThread, final String reason);

   void killAllTaskAttempts(final int stageId, final boolean interruptThread, final String reason);

   void notifyPartitionCompletion(final int stageId, final int partitionId);

   void setDAGScheduler(final DAGScheduler dagScheduler);

   int defaultParallelism();

   boolean executorHeartbeatReceived(final String execId, final Tuple2[] accumUpdates, final BlockManagerId blockManagerId, final Map executorUpdates);

   // $FF: synthetic method
   static String applicationId$(final TaskScheduler $this) {
      return $this.applicationId();
   }

   default String applicationId() {
      return this.org$apache$spark$scheduler$TaskScheduler$$appId();
   }

   void executorDecommission(final String executorId, final ExecutorDecommissionInfo decommissionInfo);

   Option getExecutorDecommissionState(final String executorId);

   void executorLost(final String executorId, final ExecutorLossReason reason);

   void workerRemoved(final String workerId, final String host, final String message);

   Option applicationAttemptId();

   static void $init$(final TaskScheduler $this) {
      $this.org$apache$spark$scheduler$TaskScheduler$_setter_$org$apache$spark$scheduler$TaskScheduler$$appId_$eq("spark-application-" + System.currentTimeMillis());
   }
}
