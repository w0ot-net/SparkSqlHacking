package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.Random;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005Q3Aa\u0001\u0003\u0001\u001b!)a\u0004\u0001C\u0001?!)\u0011\u0005\u0001C!E\ta\"+\u00198e_6\u0014En\\2l%\u0016\u0004H.[2bi&|g\u000eU8mS\u000eL(BA\u0003\u0007\u0003\u001d\u0019Ho\u001c:bO\u0016T!a\u0002\u0005\u0002\u000bM\u0004\u0018M]6\u000b\u0005%Q\u0011AB1qC\u000eDWMC\u0001\f\u0003\ry'oZ\u0002\u0001'\u0011\u0001a\u0002\u0006\r\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\t)b#D\u0001\u0005\u0013\t9BA\u0001\fCY>\u001c7NU3qY&\u001c\u0017\r^5p]B{G.[2z!\tIB$D\u0001\u001b\u0015\tYb!\u0001\u0005j]R,'O\\1m\u0013\ti\"DA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?)\u0005\u0001\u0003CA\u000b\u0001\u0003)\u0001(/[8sSRL'0\u001a\u000b\u0007GI\"\u0014h\u0011%\u0011\u0007\u0011bsF\u0004\u0002&U9\u0011a%K\u0007\u0002O)\u0011\u0001\u0006D\u0001\u0007yI|w\u000e\u001e \n\u0003EI!a\u000b\t\u0002\u000fA\f7m[1hK&\u0011QF\f\u0002\u0005\u0019&\u001cHO\u0003\u0002,!A\u0011Q\u0003M\u0005\u0003c\u0011\u0011aB\u00117pG.l\u0015M\\1hKJLE\rC\u00034\u0005\u0001\u0007q&\u0001\bcY>\u001c7.T1oC\u001e,'/\u00133\t\u000bU\u0012\u0001\u0019\u0001\u001c\u0002\u000bA,WM]:\u0011\u0007\u0011:t&\u0003\u00029]\t\u00191+Z9\t\u000bi\u0012\u0001\u0019A\u001e\u0002#A,WM]:SKBd\u0017nY1uK\u0012$v\u000eE\u0002=\u0003>j\u0011!\u0010\u0006\u0003}}\nq!\\;uC\ndWM\u0003\u0002A!\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\tk$a\u0002%bg\"\u001cV\r\u001e\u0005\u0006\t\n\u0001\r!R\u0001\bE2|7m[%e!\t)b)\u0003\u0002H\t\t9!\t\\8dW&#\u0007\"B%\u0003\u0001\u0004Q\u0015a\u00038v[J+\u0007\u000f\\5dCN\u0004\"aD&\n\u00051\u0003\"aA%oi\"\u0012\u0001A\u0014\t\u0003\u001fJk\u0011\u0001\u0015\u0006\u0003#\u001a\t!\"\u00198o_R\fG/[8o\u0013\t\u0019\u0006K\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e"
)
public class RandomBlockReplicationPolicy implements BlockReplicationPolicy, Logging {
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

   public List prioritize(final BlockManagerId blockManagerId, final Seq peers, final HashSet peersReplicatedTo, final BlockId blockId, final int numReplicas) {
      Random random = new Random(blockId.hashCode());
      this.logDebug((Function0)(() -> "Input peers : " + peers.mkString(", ")));
      List var10000;
      if (peers.size() > numReplicas) {
         var10000 = BlockReplicationUtils$.MODULE$.getRandomSample(peers, numReplicas, random);
      } else {
         if (peers.size() < numReplicas) {
            this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Expecting ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_REPLICAS..MODULE$, BoxesRunTime.boxToInteger(numReplicas))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"replicas with only ", " peer/s."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PEERS..MODULE$, BoxesRunTime.boxToInteger(peers.size()))}))))));
         }

         var10000 = ((IterableOnceOps)random.shuffle(peers, scala.collection.BuildFrom..MODULE$.buildFromIterableOps())).toList();
      }

      List prioritizedPeers = var10000;
      this.logDebug((Function0)(() -> "Prioritized peers : " + prioritizedPeers.mkString(", ")));
      return prioritizedPeers;
   }

   public RandomBlockReplicationPolicy() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
