package org.apache.spark.streaming;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.streaming.dstream.DStream;
import org.apache.spark.streaming.dstream.InputDStream;
import org.apache.spark.streaming.dstream.ReceiverInputDStream;
import org.apache.spark.streaming.scheduler.Job;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArraySeq;
import scala.collection.parallel.immutable.ParVector;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tme!B\u001a5\u0005Qb\u0004\"\u0002,\u0001\t\u00039\u0006b\u0002.\u0001\u0001\u0004%Ia\u0017\u0005\bo\u0002\u0001\r\u0011\"\u0003y\u0011\u0019y\u0007\u0001)Q\u00059\"9a\u0010\u0001a\u0001\n\u0013y\b\"CA\n\u0001\u0001\u0007I\u0011BA\u000b\u0011!\t\t\u0002\u0001Q!\n\u0005\u0005\u0001\"CA\r\u0001\u0001\u0007I\u0011BA\u000e\u0011%\ty\u0004\u0001a\u0001\n\u0013\t\t\u0005\u0003\u0005\u0002F\u0001\u0001\u000b\u0015BA\u000f\u0011%\ty\u0005\u0001a\u0001\n\u0003\t\t\u0006C\u0005\u0002Z\u0001\u0001\r\u0011\"\u0001\u0002\\!A\u0011q\f\u0001!B\u0013\t\u0019\u0006C\u0005\u0002b\u0001\u0001\r\u0011\"\u0001\u0002d!I\u00111\u000e\u0001A\u0002\u0013\u0005\u0011Q\u000e\u0005\t\u0003c\u0002\u0001\u0015)\u0003\u0002f!I\u00111\u000f\u0001A\u0002\u0013\u0005\u0011Q\u000f\u0005\n\u0003{\u0002\u0001\u0019!C\u0001\u0003\u007fB\u0001\"a!\u0001A\u0003&\u0011q\u000f\u0005\n\u0003\u000b\u0003\u0001\u0019!C\u0001\u0003kB\u0011\"a\"\u0001\u0001\u0004%\t!!#\t\u0011\u00055\u0005\u0001)Q\u0005\u0003oB\u0011\"a$\u0001\u0001\u0004%\t!!\u0015\t\u0013\u0005E\u0005\u00011A\u0005\u0002\u0005M\u0005\u0002CAL\u0001\u0001\u0006K!a\u0015\t\u0013\u0005e\u0005\u00011A\u0005\n\u0005m\u0005\"CAO\u0001\u0001\u0007I\u0011BAP\u0011!\t\u0019\u000b\u0001Q!\n\u0005e\u0002bBAT\u0001\u0011\u0005\u0011\u0011\u0016\u0005\b\u0003_\u0003A\u0011AAY\u0011\u001d\t)\f\u0001C\u0001\u0003oCq!!/\u0001\t\u0003\tY\fC\u0004\u0002H\u0002!\t!!3\t\u000f\u0005=\u0007\u0001\"\u0001\u0002R\"9\u0011Q\u001b\u0001\u0005\u0002\u0005]\u0007bBAt\u0001\u0011\u0005\u0011\u0011\u001e\u0005\b\u0003s\u0004A\u0011AA~\u0011\u001d\u0011i\u0001\u0001C\u0001\u0005\u001fAqA!\b\u0001\t\u0003\u0011y\u0002C\u0004\u00032\u0001!\t!a'\t\u000f\tM\u0002\u0001\"\u0001\u0002\u001c!9!Q\u0007\u0001\u0005\u0002\t]\u0002b\u0002B%\u0001\u0011\u0005!1\n\u0005\b\u0005\u001f\u0002A\u0011\u0001B)\u0011\u001d\u0011)\u0006\u0001C\u0001\u0005/BqAa\u0017\u0001\t\u0003\t9\fC\u0004\u0003^\u0001!\t!a.\t\u000f\t}\u0003\u0001\"\u0001\u0003b!9!1\r\u0001\u0005\n\t\u0015\u0004b\u0002BF\u0001\u0011%!Q\u0012\u0002\r\tN#(/Z1n\u000fJ\f\u0007\u000f\u001b\u0006\u0003kY\n\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005]B\u0014!B:qCJ\\'BA\u001d;\u0003\u0019\t\u0007/Y2iK*\t1(A\u0002pe\u001e\u001cB\u0001A\u001fD!B\u0011a(Q\u0007\u0002\u007f)\t\u0001)A\u0003tG\u0006d\u0017-\u0003\u0002C\u007f\t1\u0011I\\=SK\u001a\u0004\"\u0001R'\u000f\u0005\u0015[eB\u0001$K\u001b\u00059%B\u0001%J\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001!\n\u00051{\u0014a\u00029bG.\fw-Z\u0005\u0003\u001d>\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001T \u0011\u0005E#V\"\u0001*\u000b\u0005M3\u0014\u0001C5oi\u0016\u0014h.\u00197\n\u0005U\u0013&a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003a\u0003\"!\u0017\u0001\u000e\u0003Q\nA\"\u001b8qkR\u001cFO]3b[N,\u0012\u0001\u0018\t\u0004;\n$W\"\u00010\u000b\u0005}\u0003\u0017aB7vi\u0006\u0014G.\u001a\u0006\u0003C~\n!bY8mY\u0016\u001cG/[8o\u0013\t\u0019gL\u0001\u0005BeJ\f\u0017pU3ra\t)W\u000eE\u0002gS.l\u0011a\u001a\u0006\u0003QR\nq\u0001Z:ue\u0016\fW.\u0003\u0002kO\na\u0011J\u001c9vi\u0012\u001bFO]3b[B\u0011A.\u001c\u0007\u0001\t%qG!!A\u0001\u0002\u000b\u0005\u0001OA\u0002`IE\nQ\"\u001b8qkR\u001cFO]3b[N\u0004\u0013CA9u!\tq$/\u0003\u0002t\u007f\t9aj\u001c;iS:<\u0007C\u0001 v\u0013\t1xHA\u0002B]f\f\u0001#\u001b8qkR\u001cFO]3b[N|F%Z9\u0015\u0005ed\bC\u0001 {\u0013\tYxH\u0001\u0003V]&$\bbB?\u0004\u0003\u0003\u0005\r\u0001X\u0001\u0004q\u0012\n\u0014!D8viB,Ho\u0015;sK\u0006l7/\u0006\u0002\u0002\u0002A!QLYA\u0002a\u0011\t)!!\u0004\u0011\u000b\u0019\f9!a\u0003\n\u0007\u0005%qMA\u0004E'R\u0014X-Y7\u0011\u00071\fi\u0001\u0002\u0006\u0002\u0010\u001d\t\t\u0011!A\u0003\u0002A\u00141a\u0018\u00133\u00039yW\u000f\u001e9viN#(/Z1ng\u0002\n\u0011c\\;uaV$8\u000b\u001e:fC6\u001cx\fJ3r)\rI\u0018q\u0003\u0005\t{\u001a\t\t\u00111\u0001\u0002\u0002\u0005!\u0012N\u001c9viN#(/Z1n\u001d\u0006lW-\u00118e\u0013\u0012+\"!!\b\u0011\u000b\u0011\u000by\"a\t\n\u0007\u0005\u0005rJA\u0002TKF\u0004rAPA\u0013\u0003S\tI$C\u0002\u0002(}\u0012a\u0001V;qY\u0016\u0014\u0004\u0003BA\u0016\u0003gqA!!\f\u00020A\u0011aiP\u0005\u0004\u0003cy\u0014A\u0002)sK\u0012,g-\u0003\u0003\u00026\u0005]\"AB*ue&twMC\u0002\u00022}\u00022APA\u001e\u0013\r\tid\u0010\u0002\u0004\u0013:$\u0018\u0001G5oaV$8\u000b\u001e:fC6t\u0015-\\3B]\u0012LEi\u0018\u0013fcR\u0019\u00110a\u0011\t\u0011uL\u0011\u0011!a\u0001\u0003;\tQ#\u001b8qkR\u001cFO]3b[:\u000bW.Z!oI&#\u0005\u0005K\u0002\u000b\u0003\u0013\u00022APA&\u0013\r\tie\u0010\u0002\tm>d\u0017\r^5mK\u0006\u0001\"/Z7f[\n,'\u000fR;sCRLwN\\\u000b\u0003\u0003'\u00022!WA+\u0013\r\t9\u0006\u000e\u0002\t\tV\u0014\u0018\r^5p]\u0006!\"/Z7f[\n,'\u000fR;sCRLwN\\0%KF$2!_A/\u0011!iH\"!AA\u0002\u0005M\u0013!\u0005:f[\u0016l'-\u001a:EkJ\fG/[8oA\u0005!2\r[3dWB|\u0017N\u001c;J]B\u0013xn\u001a:fgN,\"!!\u001a\u0011\u0007y\n9'C\u0002\u0002j}\u0012qAQ8pY\u0016\fg.\u0001\rdQ\u0016\u001c7\u000e]8j]RLe\u000e\u0015:pOJ,7o]0%KF$2!_A8\u0011!ix\"!AA\u0002\u0005\u0015\u0014!F2iK\u000e\\\u0007o\\5oi&s\u0007K]8he\u0016\u001c8\u000fI\u0001\tu\u0016\u0014x\u000eV5nKV\u0011\u0011q\u000f\t\u00043\u0006e\u0014bAA>i\t!A+[7f\u00031QXM]8US6,w\fJ3r)\rI\u0018\u0011\u0011\u0005\t{J\t\t\u00111\u0001\u0002x\u0005I!0\u001a:p)&lW\rI\u0001\ngR\f'\u000f\u001e+j[\u0016\fQb\u001d;beR$\u0016.\\3`I\u0015\fHcA=\u0002\f\"AQ0FA\u0001\u0002\u0004\t9(\u0001\u0006ti\u0006\u0014H\u000fV5nK\u0002\nQBY1uG\"$UO]1uS>t\u0017!\u00052bi\u000eDG)\u001e:bi&|gn\u0018\u0013fcR\u0019\u00110!&\t\u0011uD\u0012\u0011!a\u0001\u0003'\naBY1uG\"$UO]1uS>t\u0007%\u0001\u0007ok6\u0014VmY3jm\u0016\u00148/\u0006\u0002\u0002:\u0005\u0001b.^7SK\u000e,\u0017N^3sg~#S-\u001d\u000b\u0004s\u0006\u0005\u0006\u0002C?\u001c\u0003\u0003\u0005\r!!\u000f\u0002\u001b9,XNU3dK&4XM]:!Q\ra\u0012\u0011J\u0001\u0006gR\f'\u000f\u001e\u000b\u0004s\u0006-\u0006bBAW;\u0001\u0007\u0011qO\u0001\u0005i&lW-A\u0004sKN$\u0018M\u001d;\u0015\u0007e\f\u0019\fC\u0004\u0002.z\u0001\r!a\u001e\u0002\tM$x\u000e\u001d\u000b\u0002s\u0006Q1/\u001a;D_:$X\r\u001f;\u0015\u0007e\fi\fC\u0004\u0002@\u0002\u0002\r!!1\u0002\u0007M\u001c8\rE\u0002Z\u0003\u0007L1!!25\u0005A\u0019FO]3b[&twmQ8oi\u0016DH/\u0001\ttKR\u0014\u0015\r^2i\tV\u0014\u0018\r^5p]R\u0019\u00110a3\t\u000f\u00055\u0017\u00051\u0001\u0002T\u0005AA-\u001e:bi&|g.\u0001\u0005sK6,WNY3s)\rI\u00181\u001b\u0005\b\u0003\u001b\u0014\u0003\u0019AA*\u00039\tG\rZ%oaV$8\u000b\u001e:fC6$2!_Am\u0011\u001d\tYn\ta\u0001\u0003;\f1\"\u001b8qkR\u001cFO]3b[B\"\u0011q\\Ar!\u00111\u0017.!9\u0011\u00071\f\u0019\u000fB\u0006\u0002f\u0006e\u0017\u0011!A\u0001\u0006\u0003\u0001(aA0%i\u0005y\u0011\r\u001a3PkR\u0004X\u000f^*ue\u0016\fW\u000eF\u0002z\u0003WDq!!<%\u0001\u0004\ty/\u0001\u0007pkR\u0004X\u000f^*ue\u0016\fW\u000e\r\u0003\u0002r\u0006U\b#\u00024\u0002\b\u0005M\bc\u00017\u0002v\u0012Y\u0011q_Av\u0003\u0003\u0005\tQ!\u0001q\u0005\ryF%N\u0001\u0010O\u0016$\u0018J\u001c9viN#(/Z1ngR\u0011\u0011Q \t\u0006}\u0005}(1A\u0005\u0004\u0005\u0003y$!B!se\u0006L\b\u0007\u0002B\u0003\u0005\u0013\u0001BAZ5\u0003\bA\u0019AN!\u0003\u0005\u0015\t-Q%!A\u0001\u0002\u000b\u0005\u0001OA\u0002`IY\n\u0001cZ3u\u001fV$\b/\u001e;TiJ,\u0017-\\:\u0015\u0005\tE\u0001#\u0002 \u0002\u0000\nM\u0001\u0007\u0002B\u000b\u00053\u0001RAZA\u0004\u0005/\u00012\u0001\u001cB\r\t)\u0011YBJA\u0001\u0002\u0003\u0015\t\u0001\u001d\u0002\u0004?\u0012:\u0014aF4fiJ+7-Z5wKJLe\u000e];u'R\u0014X-Y7t)\t\u0011\t\u0003E\u0003?\u0003\u007f\u0014\u0019\u0003\r\u0003\u0003&\t5\u0002#\u00024\u0003(\t-\u0012b\u0001B\u0015O\n!\"+Z2fSZ,'/\u00138qkR$5\u000b\u001e:fC6\u00042\u0001\u001cB\u0017\t)\u0011ycJA\u0001\u0002\u0003\u0015\t\u0001\u001d\u0002\u0004?\u0012B\u0014aD4fi:+XNU3dK&4XM]:\u0002/\u001d,G/\u00138qkR\u001cFO]3b[:\u000bW.Z!oI&#\u0015\u0001D4f]\u0016\u0014\u0018\r^3K_\n\u001cH\u0003\u0002B\u001d\u0005\u000f\u0002R\u0001RA\u0010\u0005w\u0001BA!\u0010\u0003D5\u0011!q\b\u0006\u0004\u0005\u0003\"\u0014!C:dQ\u0016$W\u000f\\3s\u0013\u0011\u0011)Ea\u0010\u0003\u0007){'\rC\u0004\u0002.*\u0002\r!a\u001e\u0002\u001b\rdW-\u0019:NKR\fG-\u0019;b)\rI(Q\n\u0005\b\u0003[[\u0003\u0019AA<\u0003Q)\b\u000fZ1uK\u000eCWmY6q_&tG\u000fR1uCR\u0019\u0011Pa\u0015\t\u000f\u00055F\u00061\u0001\u0002x\u0005\u00192\r\\3be\u000eCWmY6q_&tG\u000fR1uCR\u0019\u0011P!\u0017\t\u000f\u00055V\u00061\u0001\u0002x\u0005)\"/Z:u_J,7\t[3dWB|\u0017N\u001c;ECR\f\u0017\u0001\u0003<bY&$\u0017\r^3\u0002C\u001d,G/T1y\u0013:\u0004X\u000f^*ue\u0016\fWNU3nK6\u0014WM\u001d#ve\u0006$\u0018n\u001c8\u0015\u0005\u0005M\u0013aC<sSR,wJ\u00196fGR$2!\u001fB4\u0011\u001d\u0011I'\ra\u0001\u0005W\n1a\\8t!\u0011\u0011iGa\u001e\u000e\u0005\t=$\u0002\u0002B9\u0005g\n!![8\u000b\u0005\tU\u0014\u0001\u00026bm\u0006LAA!\u001f\u0003p\t\u0011rJ\u00196fGR|U\u000f\u001e9viN#(/Z1nQ\u0015\t$Q\u0010BE!\u0015q$q\u0010BB\u0013\r\u0011\ti\u0010\u0002\u0007i\"\u0014xn^:\u0011\t\t5$QQ\u0005\u0005\u0005\u000f\u0013yGA\u0006J\u001f\u0016C8-\u001a9uS>t7E\u0001BB\u0003)\u0011X-\u00193PE*,7\r\u001e\u000b\u0004s\n=\u0005b\u0002BIe\u0001\u0007!1S\u0001\u0004_&\u001c\b\u0003\u0002B7\u0005+KAAa&\u0003p\t\trJ\u00196fGRLe\u000e];u'R\u0014X-Y7)\u000bI\u0012iH!#"
)
public final class DStreamGraph implements Serializable, Logging {
   private ArraySeq inputStreams;
   private ArraySeq outputStreams;
   private volatile Seq inputStreamNameAndID;
   private Duration rememberDuration;
   private boolean checkpointInProgress;
   private Time zeroTime;
   private Time startTime;
   private Duration batchDuration;
   private volatile int numReceivers;
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

   private ArraySeq inputStreams() {
      return this.inputStreams;
   }

   private void inputStreams_$eq(final ArraySeq x$1) {
      this.inputStreams = x$1;
   }

   private ArraySeq outputStreams() {
      return this.outputStreams;
   }

   private void outputStreams_$eq(final ArraySeq x$1) {
      this.outputStreams = x$1;
   }

   private Seq inputStreamNameAndID() {
      return this.inputStreamNameAndID;
   }

   private void inputStreamNameAndID_$eq(final Seq x$1) {
      this.inputStreamNameAndID = x$1;
   }

   public Duration rememberDuration() {
      return this.rememberDuration;
   }

   public void rememberDuration_$eq(final Duration x$1) {
      this.rememberDuration = x$1;
   }

   public boolean checkpointInProgress() {
      return this.checkpointInProgress;
   }

   public void checkpointInProgress_$eq(final boolean x$1) {
      this.checkpointInProgress = x$1;
   }

   public Time zeroTime() {
      return this.zeroTime;
   }

   public void zeroTime_$eq(final Time x$1) {
      this.zeroTime = x$1;
   }

   public Time startTime() {
      return this.startTime;
   }

   public void startTime_$eq(final Time x$1) {
      this.startTime = x$1;
   }

   public Duration batchDuration() {
      return this.batchDuration;
   }

   public void batchDuration_$eq(final Duration x$1) {
      this.batchDuration = x$1;
   }

   private int numReceivers() {
      return this.numReceivers;
   }

   private void numReceivers_$eq(final int x$1) {
      this.numReceivers = x$1;
   }

   public synchronized void start(final Time time) {
      .MODULE$.require(this.zeroTime() == null, () -> "DStream graph computation already started");
      this.zeroTime_$eq(time);
      this.startTime_$eq(time);
      this.outputStreams().foreach((x$1) -> {
         $anonfun$start$2(this, x$1);
         return BoxedUnit.UNIT;
      });
      this.outputStreams().foreach((x$2) -> {
         $anonfun$start$3(this, x$2);
         return BoxedUnit.UNIT;
      });
      this.outputStreams().foreach((x$3) -> {
         $anonfun$start$4(x$3);
         return BoxedUnit.UNIT;
      });
      this.numReceivers_$eq(this.inputStreams().count((x$4) -> BoxesRunTime.boxToBoolean($anonfun$start$5(x$4))));
      this.inputStreamNameAndID_$eq(((IterableOnceOps)this.inputStreams().map((is) -> new Tuple2(is.name(), BoxesRunTime.boxToInteger(is.id())))).toSeq());
      (new ParVector(this.inputStreams().toVector())).foreach((x$5) -> {
         $anonfun$start$7(x$5);
         return BoxedUnit.UNIT;
      });
   }

   public synchronized void restart(final Time time) {
      this.startTime_$eq(time);
   }

   public synchronized void stop() {
      (new ParVector(this.inputStreams().toVector())).foreach((x$6) -> {
         $anonfun$stop$1(x$6);
         return BoxedUnit.UNIT;
      });
   }

   public synchronized void setContext(final StreamingContext ssc) {
      this.outputStreams().foreach((x$7) -> {
         $anonfun$setContext$1(ssc, x$7);
         return BoxedUnit.UNIT;
      });
   }

   public synchronized void setBatchDuration(final Duration duration) {
      .MODULE$.require(this.batchDuration() == null, () -> "Batch duration already set as " + this.batchDuration() + ". Cannot set it again.");
      this.batchDuration_$eq(duration);
   }

   public synchronized void remember(final Duration duration) {
      .MODULE$.require(this.rememberDuration() == null, () -> "Remember duration already set as " + this.rememberDuration() + ". Cannot set it again.");
      this.rememberDuration_$eq(duration);
   }

   public synchronized void addInputStream(final InputDStream inputStream) {
      inputStream.setGraph(this);
      this.inputStreams_$eq((ArraySeq)this.inputStreams().$colon$plus(inputStream));
   }

   public synchronized void addOutputStream(final DStream outputStream) {
      outputStream.setGraph(this);
      this.outputStreams_$eq((ArraySeq)this.outputStreams().$colon$plus(outputStream));
   }

   public synchronized InputDStream[] getInputStreams() {
      return (InputDStream[])this.inputStreams().toArray(scala.reflect.ClassTag..MODULE$.apply(InputDStream.class));
   }

   public synchronized DStream[] getOutputStreams() {
      return (DStream[])this.outputStreams().toArray(scala.reflect.ClassTag..MODULE$.apply(DStream.class));
   }

   public synchronized ReceiverInputDStream[] getReceiverInputStreams() {
      return (ReceiverInputDStream[])((IterableOnceOps)((StrictOptimizedIterableOps)this.inputStreams().filter((x$8) -> BoxesRunTime.boxToBoolean($anonfun$getReceiverInputStreams$1(x$8)))).map((x$9) -> (ReceiverInputDStream)x$9)).toArray(scala.reflect.ClassTag..MODULE$.apply(ReceiverInputDStream.class));
   }

   public int getNumReceivers() {
      return this.numReceivers();
   }

   public Seq getInputStreamNameAndID() {
      return this.inputStreamNameAndID();
   }

   public Seq generateJobs(final Time time) {
      this.logDebug((Function0)(() -> "Generating jobs for time " + time));
      synchronized(this){}

      Seq var4;
      try {
         var4 = ((IterableOnceOps)this.outputStreams().flatMap((outputStream) -> {
            Option jobOption = outputStream.generateJob(time);
            jobOption.foreach((x$10) -> {
               $anonfun$generateJobs$3(outputStream, x$10);
               return BoxedUnit.UNIT;
            });
            return jobOption;
         })).toSeq();
      } catch (Throwable var6) {
         throw var6;
      }

      this.logDebug((Function0)(() -> {
         int var10000 = var4.length();
         return "Generated " + var10000 + " jobs for time " + time;
      }));
      return var4;
   }

   public void clearMetadata(final Time time) {
      this.logDebug((Function0)(() -> "Clearing metadata for time " + time));
      synchronized(this){}

      try {
         this.outputStreams().foreach((x$11) -> {
            $anonfun$clearMetadata$2(time, x$11);
            return BoxedUnit.UNIT;
         });
      } catch (Throwable var4) {
         throw var4;
      }

      this.logDebug((Function0)(() -> "Cleared old metadata for time " + time));
   }

   public void updateCheckpointData(final Time time) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Updating checkpoint data for time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)})))));
      synchronized(this){}

      try {
         this.outputStreams().foreach((x$12) -> {
            $anonfun$updateCheckpointData$2(time, x$12);
            return BoxedUnit.UNIT;
         });
      } catch (Throwable var4) {
         throw var4;
      }

      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Updated checkpoint data for time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)})))));
   }

   public void clearCheckpointData(final Time time) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Clearing checkpoint data for time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)})))));
      synchronized(this){}

      try {
         this.outputStreams().foreach((x$13) -> {
            $anonfun$clearCheckpointData$2(time, x$13);
            return BoxedUnit.UNIT;
         });
      } catch (Throwable var4) {
         throw var4;
      }

      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cleared checkpoint data for time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)})))));
   }

   public void restoreCheckpointData() {
      this.logInfo((Function0)(() -> "Restoring checkpoint data"));
      synchronized(this){}

      try {
         this.outputStreams().foreach((x$14) -> {
            $anonfun$restoreCheckpointData$2(x$14);
            return BoxedUnit.UNIT;
         });
      } catch (Throwable var3) {
         throw var3;
      }

      this.logInfo((Function0)(() -> "Restored checkpoint data"));
   }

   public synchronized void validate() {
      .MODULE$.require(this.batchDuration() != null, () -> "Batch duration has not been set");
      .MODULE$.require(scala.collection.ArrayOps..MODULE$.nonEmpty$extension(.MODULE$.refArrayOps(this.getOutputStreams())), () -> "No output operations registered, so nothing to execute");
   }

   public Duration getMaxInputStreamRememberDuration() {
      return (Duration)((IterableOnceOps)((StrictOptimizedIterableOps)this.inputStreams().map((x$15) -> x$15.rememberDuration())).filter((x$16) -> BoxesRunTime.boxToBoolean($anonfun$getMaxInputStreamRememberDuration$2(x$16)))).maxBy((x$17) -> BoxesRunTime.boxToLong($anonfun$getMaxInputStreamRememberDuration$3(x$17)), scala.math.Ordering.Long..MODULE$);
   }

   private void writeObject(final ObjectOutputStream oos) throws IOException {
      org.apache.spark.util.Utils..MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.logDebug((Function0)(() -> "DStreamGraph.writeObject used"));
         synchronized(this){}

         try {
            this.checkpointInProgress_$eq(true);
            this.logDebug((Function0)(() -> "Enabled checkpoint mode"));
            oos.defaultWriteObject();
            this.checkpointInProgress_$eq(false);
            this.logDebug((Function0)(() -> "Disabled checkpoint mode"));
         } catch (Throwable var4) {
            throw var4;
         }

      });
   }

   private void readObject(final ObjectInputStream ois) throws IOException {
      org.apache.spark.util.Utils..MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.logDebug((Function0)(() -> "DStreamGraph.readObject used"));
         synchronized(this){}

         try {
            this.checkpointInProgress_$eq(true);
            ois.defaultReadObject();
            this.checkpointInProgress_$eq(false);
         } catch (Throwable var4) {
            throw var4;
         }

      });
   }

   // $FF: synthetic method
   public static final void $anonfun$start$2(final DStreamGraph $this, final DStream x$1) {
      x$1.initialize($this.zeroTime());
   }

   // $FF: synthetic method
   public static final void $anonfun$start$3(final DStreamGraph $this, final DStream x$2) {
      x$2.remember($this.rememberDuration());
   }

   // $FF: synthetic method
   public static final void $anonfun$start$4(final DStream x$3) {
      x$3.validateAtStart();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$start$5(final InputDStream x$4) {
      return x$4 instanceof ReceiverInputDStream;
   }

   // $FF: synthetic method
   public static final void $anonfun$start$7(final InputDStream x$5) {
      x$5.start();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$1(final InputDStream x$6) {
      x$6.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$setContext$1(final StreamingContext ssc$1, final DStream x$7) {
      x$7.setContext(ssc$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getReceiverInputStreams$1(final InputDStream x$8) {
      return x$8 instanceof ReceiverInputDStream;
   }

   // $FF: synthetic method
   public static final void $anonfun$generateJobs$3(final DStream outputStream$1, final Job x$10) {
      x$10.setCallSite(outputStream$1.creationSite());
   }

   // $FF: synthetic method
   public static final void $anonfun$clearMetadata$2(final Time time$2, final DStream x$11) {
      x$11.clearMetadata(time$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$updateCheckpointData$2(final Time time$3, final DStream x$12) {
      x$12.updateCheckpointData(time$3);
   }

   // $FF: synthetic method
   public static final void $anonfun$clearCheckpointData$2(final Time time$4, final DStream x$13) {
      x$13.clearCheckpointData(time$4);
   }

   // $FF: synthetic method
   public static final void $anonfun$restoreCheckpointData$2(final DStream x$14) {
      x$14.restoreCheckpointData();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getMaxInputStreamRememberDuration$2(final Duration x$16) {
      return x$16 != null;
   }

   // $FF: synthetic method
   public static final long $anonfun$getMaxInputStreamRememberDuration$3(final Duration x$17) {
      return x$17.milliseconds();
   }

   public DStreamGraph() {
      Logging.$init$(this);
      this.inputStreams = scala.collection.mutable.ArraySeq..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(InputDStream.class));
      this.outputStreams = scala.collection.mutable.ArraySeq..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(DStream.class));
      this.inputStreamNameAndID = scala.collection.immutable.Nil..MODULE$;
      this.rememberDuration = null;
      this.checkpointInProgress = false;
      this.zeroTime = null;
      this.startTime = null;
      this.batchDuration = null;
      this.numReceivers = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
