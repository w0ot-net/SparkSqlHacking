package org.apache.spark.api.python;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkFiles$;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.CollectionAccumulator;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class PythonWorkerUtils$ implements Logging {
   public static final PythonWorkerUtils$ MODULE$ = new PythonWorkerUtils$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
   }

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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public void writeUTF(final String str, final DataOutputStream dataOut) {
      byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
      this.writeBytes(bytes, dataOut);
   }

   public void writeBytes(final byte[] bytes, final DataOutputStream dataOut) {
      dataOut.writeInt(bytes.length);
      dataOut.write(bytes);
   }

   public void writePythonVersion(final String pythonVer, final DataOutputStream dataOut) {
      this.writeUTF(pythonVer, dataOut);
   }

   public void writeSparkFiles(final Option jobArtifactUUID, final Set pythonIncludes, final DataOutputStream dataOut) {
      String root = (String)jobArtifactUUID.map((uuid) -> (new File(SparkFiles$.MODULE$.getRootDirectory(), uuid)).getAbsolutePath()).getOrElse(() -> SparkFiles$.MODULE$.getRootDirectory());
      this.writeUTF(root, dataOut);
      dataOut.writeInt(pythonIncludes.size());
      pythonIncludes.foreach((include) -> {
         $anonfun$writeSparkFiles$3(dataOut, include);
         return BoxedUnit.UNIT;
      });
   }

   public void writeBroadcasts(final Seq broadcastVars, final PythonWorker worker, final SparkEnv env, final DataOutputStream dataOut) {
      scala.collection.mutable.Set oldBids = PythonRDD$.MODULE$.getWorkerBroadcasts(worker);
      Set newBids = ((IterableOnceOps)broadcastVars.map((x$1) -> BoxesRunTime.boxToLong($anonfun$writeBroadcasts$1(x$1)))).toSet();
      scala.collection.mutable.Set toRemove = (scala.collection.mutable.Set)oldBids.diff(newBids);
      Set addedBids = (Set)newBids.diff(oldBids);
      int cnt = toRemove.size() + addedBids.size();
      boolean needsDecryptionServer = env.serializerManager().encryptionEnabled() && addedBids.nonEmpty();
      dataOut.writeBoolean(needsDecryptionServer);
      dataOut.writeInt(cnt);
      if (needsDecryptionServer) {
         Seq idsAndFiles = (Seq)broadcastVars.flatMap((broadcast) -> {
            if (!oldBids.contains(BoxesRunTime.boxToLong(broadcast.id()))) {
               oldBids.add(BoxesRunTime.boxToLong(broadcast.id()));
               return new Some(new Tuple2(BoxesRunTime.boxToLong(broadcast.id()), ((PythonBroadcast)broadcast.value()).path()));
            } else {
               return .MODULE$;
            }
         });
         EncryptedPythonBroadcastServer server = new EncryptedPythonBroadcastServer(env, idsAndFiles);
         dataOut.writeInt(server.port());
         this.logTrace((Function0)(() -> "broadcast decryption server setup on " + server.port()));
         this.writeUTF(server.secret(), dataOut);
         sendBidsToRemove$1(toRemove, dataOut, oldBids);
         idsAndFiles.foreach((x0$1) -> {
            $anonfun$writeBroadcasts$5(dataOut, x0$1);
            return BoxedUnit.UNIT;
         });
         dataOut.flush();
      } else {
         sendBidsToRemove$1(toRemove, dataOut, oldBids);
         broadcastVars.foreach((broadcast) -> {
            if (!oldBids.contains(BoxesRunTime.boxToLong(broadcast.id()))) {
               dataOut.writeLong(broadcast.id());
               MODULE$.writeUTF(((PythonBroadcast)broadcast.value()).path(), dataOut);
               return BoxesRunTime.boxToBoolean(oldBids.add(BoxesRunTime.boxToLong(broadcast.id())));
            } else {
               return BoxedUnit.UNIT;
            }
         });
      }

      dataOut.flush();
   }

   public void writePythonFunction(final PythonFunction func, final DataOutputStream dataOut) {
      this.writeBytes((byte[])func.command().toArray(scala.reflect.ClassTag..MODULE$.Byte()), dataOut);
   }

   public String readUTF(final DataInputStream dataIn) {
      return this.readUTF(dataIn.readInt(), dataIn);
   }

   public String readUTF(final int length, final DataInputStream dataIn) {
      return new String(this.readBytes(length, dataIn), StandardCharsets.UTF_8);
   }

   public byte[] readBytes(final DataInputStream dataIn) {
      return this.readBytes(dataIn.readInt(), dataIn);
   }

   public byte[] readBytes(final int length, final DataInputStream dataIn) {
      if (length == 0) {
         return scala.Array..MODULE$.emptyByteArray();
      } else {
         byte[] obj = new byte[length];
         dataIn.readFully(obj);
         return obj;
      }
   }

   public void receiveAccumulatorUpdates(final Option maybeAccumulator, final DataInputStream dataIn) {
      int numAccumulatorUpdates = dataIn.readInt();
      scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), numAccumulatorUpdates).foreach$mVc$sp((JFunction1.mcVI.sp)(x$2) -> {
         byte[] update = MODULE$.readBytes(dataIn);
         maybeAccumulator.foreach((x$3) -> {
            $anonfun$receiveAccumulatorUpdates$2(update, x$3);
            return BoxedUnit.UNIT;
         });
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$writeSparkFiles$3(final DataOutputStream dataOut$1, final String include) {
      MODULE$.writeUTF(include, dataOut$1);
   }

   // $FF: synthetic method
   public static final long $anonfun$writeBroadcasts$1(final Broadcast x$1) {
      return x$1.id();
   }

   private static final void sendBidsToRemove$1(final scala.collection.mutable.Set toRemove$1, final DataOutputStream dataOut$2, final scala.collection.mutable.Set oldBids$1) {
      toRemove$1.foreach((JFunction1.mcZJ.sp)(bid) -> {
         dataOut$2.writeLong(-bid - 1L);
         return oldBids$1.remove(BoxesRunTime.boxToLong(bid));
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$writeBroadcasts$5(final DataOutputStream dataOut$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         long id = x0$1._1$mcJ$sp();
         dataOut$2.writeLong(id);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$receiveAccumulatorUpdates$2(final byte[] update$1, final CollectionAccumulator x$3) {
      x$3.add(update$1);
   }

   private PythonWorkerUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
