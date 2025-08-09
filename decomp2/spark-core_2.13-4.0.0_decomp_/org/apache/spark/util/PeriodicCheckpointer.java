package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.Iterable;
import scala.collection.mutable.ArrayDeque;
import scala.collection.mutable.Queue;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mcA\u0002\u000f\u001e\u0003\u0003yR\u0005\u0003\u00054\u0001\t\u0015\r\u0011\"\u00016\u0011!I\u0004A!A!\u0002\u00131\u0004\u0002\u0003\u001e\u0001\u0005\u000b\u0007I\u0011A\u001e\t\u0011\u0001\u0003!\u0011!Q\u0001\nqBQ!\u0011\u0001\u0005\u0002\tCqA\u0015\u0001C\u0002\u0013%1\u000b\u0003\u0004]\u0001\u0001\u0006I\u0001\u0016\u0005\b;\u0002\u0011\r\u0011\"\u0003T\u0011\u0019q\u0006\u0001)A\u0005)\"9q\f\u0001a\u0001\n\u0013)\u0004b\u00021\u0001\u0001\u0004%I!\u0019\u0005\u0007O\u0002\u0001\u000b\u0015\u0002\u001c\t\u000b!\u0004A\u0011A5\t\u000b1\u0004a\u0011C7\t\u000bA\u0004a\u0011C9\t\u000bY\u0004a\u0011C<\t\u000be\u0004a\u0011\u0003>\t\u000bq\u0004a\u0011C?\t\u000f\u0005\u001d\u0002\u0001\"\u0001\u0002*!9\u00111\u0006\u0001\u0005\u0002\u0005%\u0002bBA\u0017\u0001\u0011\u0005\u0011\u0011\u0006\u0005\b\u0003_\u0001A\u0011AA\u0019\u0011\u001d\tI\u0004\u0001C\u0005\u0003S9\u0001\"a\u000f\u001e\u0011\u0003y\u0012Q\b\u0004\b9uA\taHA \u0011\u0019\t\u0015\u0004\"\u0001\u0002B!9\u0011\u0011H\r\u0005\u0002\u0005\r#\u0001\u0006)fe&|G-[2DQ\u0016\u001c7\u000e]8j]R,'O\u0003\u0002\u001f?\u0005!Q\u000f^5m\u0015\t\u0001\u0013%A\u0003ta\u0006\u00148N\u0003\u0002#G\u00051\u0011\r]1dQ\u0016T\u0011\u0001J\u0001\u0004_J<WC\u0001\u0014H'\r\u0001q%\f\t\u0003Q-j\u0011!\u000b\u0006\u0002U\u0005)1oY1mC&\u0011A&\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u00059\nT\"A\u0018\u000b\u0005Az\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005Iz#a\u0002'pO\u001eLgnZ\u0001\u0013G\",7m\u001b9pS:$\u0018J\u001c;feZ\fGn\u0001\u0001\u0016\u0003Y\u0002\"\u0001K\u001c\n\u0005aJ#aA%oi\u0006\u00192\r[3dWB|\u0017N\u001c;J]R,'O^1mA\u0005\u00111oY\u000b\u0002yA\u0011QHP\u0007\u0002?%\u0011qh\b\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\u0004g\u000e\u0004\u0013A\u0002\u001fj]&$h\bF\u0002D!F\u00032\u0001\u0012\u0001F\u001b\u0005i\u0002C\u0001$H\u0019\u0001!Q\u0001\u0013\u0001C\u0002%\u0013\u0011\u0001V\t\u0003\u00156\u0003\"\u0001K&\n\u00051K#a\u0002(pi\"Lgn\u001a\t\u0003Q9K!aT\u0015\u0003\u0007\u0005s\u0017\u0010C\u00034\u000b\u0001\u0007a\u0007C\u0003;\u000b\u0001\u0007A(A\bdQ\u0016\u001c7\u000e]8j]R\fV/Z;f+\u0005!\u0006cA+[\u000b6\taK\u0003\u0002X1\u00069Q.\u001e;bE2,'BA-*\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u00037Z\u0013Q!U;fk\u0016\f\u0001c\u00195fG.\u0004x.\u001b8u#V,W/\u001a\u0011\u0002\u001dA,'o]5ti\u0016$\u0017+^3vK\u0006y\u0001/\u001a:tSN$X\rZ)vKV,\u0007%A\u0006va\u0012\fG/Z\"pk:$\u0018aD;qI\u0006$XmQ8v]R|F%Z9\u0015\u0005\t,\u0007C\u0001\u0015d\u0013\t!\u0017F\u0001\u0003V]&$\bb\u00024\f\u0003\u0003\u0005\rAN\u0001\u0004q\u0012\n\u0014\u0001D;qI\u0006$XmQ8v]R\u0004\u0013AB;qI\u0006$X\r\u0006\u0002cU\")1.\u0004a\u0001\u000b\u00069a.Z<ECR\f\u0017AC2iK\u000e\\\u0007o\\5oiR\u0011!M\u001c\u0005\u0006_:\u0001\r!R\u0001\u0005I\u0006$\u0018-\u0001\bjg\u000eCWmY6q_&tG/\u001a3\u0015\u0005I,\bC\u0001\u0015t\u0013\t!\u0018FA\u0004C_>dW-\u00198\t\u000b=|\u0001\u0019A#\u0002\u000fA,'o]5tiR\u0011!\r\u001f\u0005\u0006_B\u0001\r!R\u0001\nk:\u0004XM]:jgR$\"AY>\t\u000b=\f\u0002\u0019A#\u0002%\u001d,Go\u00115fG.\u0004x.\u001b8u\r&dWm\u001d\u000b\u0004}\u0006\u0015\u0002#B@\u0002\u0010\u0005Ua\u0002BA\u0001\u0003\u0017qA!a\u0001\u0002\n5\u0011\u0011Q\u0001\u0006\u0004\u0003\u000f!\u0014A\u0002\u001fs_>$h(C\u0001+\u0013\r\ti!K\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\t\"a\u0005\u0003\u0011%#XM]1cY\u0016T1!!\u0004*!\u0011\t9\"a\b\u000f\t\u0005e\u00111\u0004\t\u0004\u0003\u0007I\u0013bAA\u000fS\u00051\u0001K]3eK\u001aLA!!\t\u0002$\t11\u000b\u001e:j]\u001eT1!!\b*\u0011\u0015y'\u00031\u0001F\u0003A)h\u000e]3sg&\u001cH\u000fR1uCN+G\u000fF\u0001c\u0003Q!W\r\\3uK\u0006cGn\u00115fG.\u0004x.\u001b8ug\u0006YB-\u001a7fi\u0016\fE\u000e\\\"iK\u000e\\\u0007o\\5oiN\u0014U\u000f\u001e'bgR\fQcZ3u\u00032d7\t[3dWB|\u0017N\u001c;GS2,7/\u0006\u0002\u00024A)\u0001&!\u000e\u0002\u0016%\u0019\u0011qG\u0015\u0003\u000b\u0005\u0013(/Y=\u0002)I,Wn\u001c<f\u0007\",7m\u001b9pS:$h)\u001b7f\u0003Q\u0001VM]5pI&\u001c7\t[3dWB|\u0017N\u001c;feB\u0011A)G\n\u00043\u001djCCAA\u001f)\u0015\u0011\u0017QIA%\u0011\u001d\t9e\u0007a\u0001\u0003+\tab\u00195fG.\u0004x.\u001b8u\r&dW\rC\u0004\u0002Lm\u0001\r!!\u0014\u0002\t\r|gN\u001a\t\u0005\u0003\u001f\n9&\u0004\u0002\u0002R)!\u00111JA*\u0015\r\t)&I\u0001\u0007Q\u0006$wn\u001c9\n\t\u0005e\u0013\u0011\u000b\u0002\u000e\u0007>tg-[4ve\u0006$\u0018n\u001c8"
)
public abstract class PeriodicCheckpointer implements Logging {
   private final int checkpointInterval;
   private final SparkContext sc;
   private final Queue checkpointQueue;
   private final Queue persistedQueue;
   private int updateCount;
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

   public int checkpointInterval() {
      return this.checkpointInterval;
   }

   public SparkContext sc() {
      return this.sc;
   }

   private Queue checkpointQueue() {
      return this.checkpointQueue;
   }

   private Queue persistedQueue() {
      return this.persistedQueue;
   }

   private int updateCount() {
      return this.updateCount;
   }

   private void updateCount_$eq(final int x$1) {
      this.updateCount = x$1;
   }

   public void update(final Object newData) {
      this.persist(newData);
      this.persistedQueue().enqueue(newData);

      while(this.persistedQueue().size() > 3) {
         Object dataToUnpersist = this.persistedQueue().dequeue();
         this.unpersist(dataToUnpersist);
      }

      this.updateCount_$eq(this.updateCount() + 1);
      if (this.checkpointInterval() != -1 && this.updateCount() % this.checkpointInterval() == 0 && this.sc().getCheckpointDir().nonEmpty()) {
         this.checkpoint(newData);
         this.checkpointQueue().enqueue(newData);
         boolean canDelete = true;

         while(this.checkpointQueue().size() > 1 && canDelete) {
            if (this.isCheckpointed(this.checkpointQueue().apply(1))) {
               this.removeCheckpointFile();
            } else {
               canDelete = false;
            }
         }

      }
   }

   public abstract void checkpoint(final Object data);

   public abstract boolean isCheckpointed(final Object data);

   public abstract void persist(final Object data);

   public abstract void unpersist(final Object data);

   public abstract Iterable getCheckpointFiles(final Object data);

   public void unpersistDataSet() {
      while(this.persistedQueue().nonEmpty()) {
         Object dataToUnpersist = this.persistedQueue().dequeue();
         this.unpersist(dataToUnpersist);
      }

   }

   public void deleteAllCheckpoints() {
      while(this.checkpointQueue().nonEmpty()) {
         this.removeCheckpointFile();
      }

   }

   public void deleteAllCheckpointsButLast() {
      while(this.checkpointQueue().size() > 1) {
         this.removeCheckpointFile();
      }

   }

   public String[] getAllCheckpointFiles() {
      return (String[])((ArrayDeque)this.checkpointQueue().flatMap((data) -> this.getCheckpointFiles(data))).toArray(.MODULE$.apply(String.class));
   }

   private void removeCheckpointFile() {
      Object old = this.checkpointQueue().dequeue();
      this.getCheckpointFiles(old).foreach((x$1) -> {
         $anonfun$removeCheckpointFile$1(this, x$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$removeCheckpointFile$1(final PeriodicCheckpointer $this, final String x$1) {
      PeriodicCheckpointer$.MODULE$.removeCheckpointFile(x$1, $this.sc().hadoopConfiguration());
   }

   public PeriodicCheckpointer(final int checkpointInterval, final SparkContext sc) {
      this.checkpointInterval = checkpointInterval;
      this.sc = sc;
      Logging.$init$(this);
      this.checkpointQueue = (Queue)scala.collection.mutable.Queue..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.persistedQueue = (Queue)scala.collection.mutable.Queue..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.updateCount = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
