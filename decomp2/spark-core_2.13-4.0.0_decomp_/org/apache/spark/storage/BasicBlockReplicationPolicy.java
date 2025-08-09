package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.Random;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005Q3Aa\u0001\u0003\u0001\u001b!)a\u0004\u0001C\u0001?!)\u0011\u0005\u0001C!E\tY\")Y:jG\ncwnY6SKBd\u0017nY1uS>t\u0007k\u001c7jGfT!!\u0002\u0004\u0002\u000fM$xN]1hK*\u0011q\u0001C\u0001\u0006gB\f'o\u001b\u0006\u0003\u0013)\ta!\u00199bG\",'\"A\u0006\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001qA\u0003\u0007\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005U1R\"\u0001\u0003\n\u0005]!!A\u0006\"m_\u000e\\'+\u001a9mS\u000e\fG/[8o!>d\u0017nY=\u0011\u0005eaR\"\u0001\u000e\u000b\u0005m1\u0011\u0001C5oi\u0016\u0014h.\u00197\n\u0005uQ\"a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0001\u0002\"!\u0006\u0001\u0002\u0015A\u0014\u0018n\u001c:ji&TX\r\u0006\u0004$eQJ4\t\u0013\t\u0004I1zcBA\u0013+\u001d\t1\u0013&D\u0001(\u0015\tAC\"\u0001\u0004=e>|GOP\u0005\u0002#%\u00111\u0006E\u0001\ba\u0006\u001c7.Y4f\u0013\ticF\u0001\u0003MSN$(BA\u0016\u0011!\t)\u0002'\u0003\u00022\t\tq!\t\\8dW6\u000bg.Y4fe&#\u0007\"B\u001a\u0003\u0001\u0004y\u0013A\u00042m_\u000e\\W*\u00198bO\u0016\u0014\u0018\n\u001a\u0005\u0006k\t\u0001\rAN\u0001\u0006a\u0016,'o\u001d\t\u0004I]z\u0013B\u0001\u001d/\u0005\r\u0019V-\u001d\u0005\u0006u\t\u0001\raO\u0001\u0012a\u0016,'o\u001d*fa2L7-\u0019;fIR{\u0007c\u0001\u001fB_5\tQH\u0003\u0002?\u007f\u00059Q.\u001e;bE2,'B\u0001!\u0011\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003\u0005v\u0012q\u0001S1tQN+G\u000fC\u0003E\u0005\u0001\u0007Q)A\u0004cY>\u001c7.\u00133\u0011\u0005U1\u0015BA$\u0005\u0005\u001d\u0011En\\2l\u0013\u0012DQ!\u0013\u0002A\u0002)\u000b1B\\;n%\u0016\u0004H.[2bgB\u0011qbS\u0005\u0003\u0019B\u00111!\u00138uQ\t\u0001a\n\u0005\u0002P%6\t\u0001K\u0003\u0002R\r\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005M\u0003&\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public class BasicBlockReplicationPolicy implements BlockReplicationPolicy, Logging {
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
      this.logDebug((Function0)(() -> "Input peers : " + peers));
      this.logDebug((Function0)(() -> "BlockManagerId : " + blockManagerId));
      Random random = new Random(blockId.hashCode());
      if (!blockManagerId.topologyInfo().isEmpty() && numReplicas != 0) {
         boolean doneWithinRack = peersReplicatedTo.exists((x$3) -> BoxesRunTime.boxToBoolean($anonfun$prioritize$6(blockManagerId, x$3)));
         boolean doneOutsideRack = peersReplicatedTo.exists((p) -> BoxesRunTime.boxToBoolean($anonfun$prioritize$7(blockManagerId, p)));
         if (doneOutsideRack && doneWithinRack) {
            return BlockReplicationUtils$.MODULE$.getRandomSample(peers, numReplicas, random);
         } else {
            Tuple2 var11 = ((IterableOps)peers.filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$prioritize$8(blockManagerId, x$4)))).partition((x$5) -> BoxesRunTime.boxToBoolean($anonfun$prioritize$9(blockManagerId, x$5)));
            if (var11 != null) {
               Seq inRackPeers = (Seq)var11._1();
               Seq outOfRackPeers = (Seq)var11._2();
               Tuple2 var10 = new Tuple2(inRackPeers, outOfRackPeers);
               Seq inRackPeers = (Seq)var10._1();
               Seq outOfRackPeers = (Seq)var10._2();
               Seq peerWithinRack = (Seq)(doneWithinRack ? (Seq).MODULE$.Seq().empty() : (inRackPeers.isEmpty() ? (Seq).MODULE$.Seq().empty() : new scala.collection.immutable..colon.colon((BlockManagerId)inRackPeers.apply(random.nextInt(inRackPeers.size())), scala.collection.immutable.Nil..MODULE$)));
               Seq peerOutsideRack = (Seq)(!doneOutsideRack && numReplicas - peerWithinRack.size() > 0 ? (outOfRackPeers.isEmpty() ? (Seq).MODULE$.Seq().empty() : new scala.collection.immutable..colon.colon((BlockManagerId)outOfRackPeers.apply(random.nextInt(outOfRackPeers.size())), scala.collection.immutable.Nil..MODULE$)) : (Seq).MODULE$.Seq().empty());
               Seq priorityPeers = (Seq)peerWithinRack.$plus$plus(peerOutsideRack);
               int numRemainingPeers = numReplicas - priorityPeers.size();
               Object var10000;
               if (numRemainingPeers > 0) {
                  Seq rPeers = (Seq)peers.filter((p) -> BoxesRunTime.boxToBoolean($anonfun$prioritize$10(priorityPeers, p)));
                  var10000 = BlockReplicationUtils$.MODULE$.getRandomSample(rPeers, numRemainingPeers, random);
               } else {
                  var10000 = (Seq).MODULE$.Seq().empty();
               }

               Seq remainingPeers = (Seq)var10000;
               return ((IterableOnceOps)priorityPeers.$plus$plus(remainingPeers)).toList();
            } else {
               throw new MatchError(var11);
            }
         }
      } else {
         return BlockReplicationUtils$.MODULE$.getRandomSample(peers, numReplicas, random);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prioritize$6(final BlockManagerId blockManagerId$1, final BlockManagerId x$3) {
      boolean var3;
      label23: {
         Option var10000 = x$3.topologyInfo();
         Option var2 = blockManagerId$1.topologyInfo();
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prioritize$7(final BlockManagerId blockManagerId$1, final BlockManagerId p) {
      boolean var3;
      label25: {
         if (p.topologyInfo().isDefined()) {
            Option var10000 = p.topologyInfo();
            Option var2 = blockManagerId$1.topologyInfo();
            if (var10000 == null) {
               if (var2 != null) {
                  break label25;
               }
            } else if (!var10000.equals(var2)) {
               break label25;
            }
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prioritize$8(final BlockManagerId blockManagerId$1, final BlockManagerId x$4) {
      boolean var3;
      label23: {
         String var10000 = x$4.host();
         String var2 = blockManagerId$1.host();
         if (var10000 == null) {
            if (var2 != null) {
               break label23;
            }
         } else if (!var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prioritize$9(final BlockManagerId blockManagerId$1, final BlockManagerId x$5) {
      boolean var3;
      label23: {
         Option var10000 = x$5.topologyInfo();
         Option var2 = blockManagerId$1.topologyInfo();
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prioritize$10(final Seq priorityPeers$1, final BlockManagerId p) {
      return !priorityPeers$1.contains(p);
   }

   public BasicBlockReplicationPolicy() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
