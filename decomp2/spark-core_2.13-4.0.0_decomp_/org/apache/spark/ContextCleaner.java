package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.broadcast.BroadcastManager;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.ReliableRDDCheckpointData$;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.shuffle.api.ShuffleDriverComponents;
import org.apache.spark.util.AccumulatorContext$;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tud!B\u00181\u0001A2\u0004\u0002C\"\u0001\u0005\u0003\u0005\u000b\u0011B#\t\u0011%\u0003!\u0011!Q\u0001\n)CQA\u0015\u0001\u0005\u0002MCqa\u0016\u0001C\u0002\u0013%\u0001\f\u0003\u0004e\u0001\u0001\u0006I!\u0017\u0005\bK\u0002\u0011\r\u0011\"\u0003g\u0011\u0019y\u0007\u0001)A\u0005O\"9\u0001\u000f\u0001b\u0001\n\u0013\t\bBB>\u0001A\u0003%!\u000fC\u0004}\u0001\t\u0007I\u0011B?\t\u000f\u0005\u0015\u0001\u0001)A\u0005}\"I\u0011q\u0001\u0001C\u0002\u0013%\u0011\u0011\u0002\u0005\t\u0003#\u0001\u0001\u0015!\u0003\u0002\f!I\u00111\u0003\u0001C\u0002\u0013%\u0011Q\u0003\u0005\t\u0003;\u0001\u0001\u0015!\u0003\u0002\u0018!I\u0011q\u0004\u0001C\u0002\u0013%\u0011\u0011\u0005\u0005\t\u0003S\u0001\u0001\u0015!\u0003\u0002$!I\u00111\u0006\u0001C\u0002\u0013%\u0011\u0011\u0005\u0005\t\u0003[\u0001\u0001\u0015!\u0003\u0002$!I\u0011q\u0006\u0001A\u0002\u0013%\u0011\u0011\u0005\u0005\n\u0003c\u0001\u0001\u0019!C\u0005\u0003gA\u0001\"a\u0010\u0001A\u0003&\u00111\u0005\u0005\b\u0003\u0013\u0002A\u0011AA&\u0011\u001d\t\t\u0006\u0001C\u0001\u0003'Bq!!\u0016\u0001\t\u0003\t\u0019\u0006C\u0004\u0002X\u0001!\t!!\u0017\t\u000f\u0005\u0005\u0005\u0001\"\u0001\u0002\u0004\"9\u0011\u0011\u0015\u0001\u0005\u0002\u0005\r\u0006bBAb\u0001\u0011\u0005\u0011Q\u0019\u0005\b\u0003;\u0004A\u0011AAp\u0011\u001d\tY\u0010\u0001C\u0001\u0003{DqA!\u0005\u0001\t\u0013\u0011\u0019\u0002C\u0004\u0003$\u0001!I!a\u0015\t\u000f\t\u0015\u0002\u0001\"\u0001\u0003(!9!\u0011\u0007\u0001\u0005\u0002\tM\u0002b\u0002B\u001e\u0001\u0011\u0005!Q\b\u0005\b\u0005\u000b\u0002A\u0011\u0001B$\u0011\u001d\u0011y\u0005\u0001C\u0001\u0005#BqA!\u0016\u0001\t\u0003\u00119\u0006C\u0004\u0003\\\u0001!IA!\u0018\t\u000f\t\u0015\u0004\u0001\"\u0003\u0003h\u001d9!q\u000e\u0019\t\n\tEdAB\u00181\u0011\u0013\u0011\u0019\b\u0003\u0004SW\u0011\u0005!Q\u000f\u0005\n\u0005oZ#\u0019!C\u0005\u0005sB\u0001Ba\u001f,A\u0003%\u00111\u001f\u0002\u000f\u0007>tG/\u001a=u\u00072,\u0017M\\3s\u0015\t\t$'A\u0003ta\u0006\u00148N\u0003\u00024i\u00051\u0011\r]1dQ\u0016T\u0011!N\u0001\u0004_J<7c\u0001\u00018{A\u0011\u0001hO\u0007\u0002s)\t!(A\u0003tG\u0006d\u0017-\u0003\u0002=s\t1\u0011I\\=SK\u001a\u0004\"AP!\u000e\u0003}R!\u0001\u0011\u0019\u0002\u0011%tG/\u001a:oC2L!AQ \u0003\u000f1{wmZ5oO\u0006\u00111oY\u0002\u0001!\t1u)D\u00011\u0013\tA\u0005G\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH/A\ftQV4g\r\\3Ee&4XM]\"p[B|g.\u001a8ugB\u00111\nU\u0007\u0002\u0019*\u0011QJT\u0001\u0004CBL'BA(1\u0003\u001d\u0019\b.\u001e4gY\u0016L!!\u0015'\u0003/MCWO\u001a4mK\u0012\u0013\u0018N^3s\u0007>l\u0007o\u001c8f]R\u001c\u0018A\u0002\u001fj]&$h\bF\u0002U+Z\u0003\"A\u0012\u0001\t\u000b\r\u001b\u0001\u0019A#\t\u000b%\u001b\u0001\u0019\u0001&\u0002\u001fI,g-\u001a:f]\u000e,')\u001e4gKJ,\u0012!\u0017\t\u00045~\u000bW\"A.\u000b\u0005qk\u0016\u0001B;uS2T\u0011AX\u0001\u0005U\u00064\u0018-\u0003\u0002a7\n\u00191+\u001a;\u0011\u0005\u0019\u0013\u0017BA21\u0005a\u0019E.Z1okB$\u0016m]6XK\u0006\\'+\u001a4fe\u0016t7-Z\u0001\u0011e\u00164WM]3oG\u0016\u0014UO\u001a4fe\u0002\naB]3gKJ,gnY3Rk\u0016,X-F\u0001h!\rAWnN\u0007\u0002S*\u0011!n[\u0001\u0004e\u00164'B\u00017^\u0003\u0011a\u0017M\\4\n\u00059L'A\u0004*fM\u0016\u0014XM\\2f#V,W/Z\u0001\u0010e\u00164WM]3oG\u0016\fV/Z;fA\u0005IA.[:uK:,'o]\u000b\u0002eB\u00191O\u001e=\u000e\u0003QT!!^.\u0002\u0015\r|gnY;se\u0016tG/\u0003\u0002xi\n)2i\u001c8dkJ\u0014XM\u001c;MS:\\W\rZ)vKV,\u0007C\u0001$z\u0013\tQ\bGA\bDY\u0016\fg.\u001a:MSN$XM\\3s\u0003)a\u0017n\u001d;f]\u0016\u00148\u000fI\u0001\u000fG2,\u0017M\\5oORC'/Z1e+\u0005q\bcA@\u0002\u00025\t1.C\u0002\u0002\u0004-\u0014a\u0001\u00165sK\u0006$\u0017aD2mK\u0006t\u0017N\\4UQJ,\u0017\r\u001a\u0011\u0002#A,'/[8eS\u000e<5iU3sm&\u001cW-\u0006\u0002\u0002\fA\u00191/!\u0004\n\u0007\u0005=AO\u0001\rTG\",G-\u001e7fI\u0016CXmY;u_J\u001cVM\u001d<jG\u0016\f!\u0003]3sS>$\u0017nY$D'\u0016\u0014h/[2fA\u0005\u0011\u0002/\u001a:j_\u0012L7mR\"J]R,'O^1m+\t\t9\u0002E\u00029\u00033I1!a\u0007:\u0005\u0011auN\\4\u0002'A,'/[8eS\u000e<5)\u00138uKJ4\u0018\r\u001c\u0011\u0002'\tdwnY6P]\u000ecW-\u00198vaR\u000b7o[:\u0016\u0005\u0005\r\u0002c\u0001\u001d\u0002&%\u0019\u0011qE\u001d\u0003\u000f\t{w\u000e\\3b]\u0006!\"\r\\8dW>s7\t\\3b]V\u0004H+Y:lg\u0002\n!D\u00197pG.|en\u00155vM\u001adWm\u00117fC:,\b\u000fV1tWN\f1D\u00197pG.|en\u00155vM\u001adWm\u00117fC:,\b\u000fV1tWN\u0004\u0013aB:u_B\u0004X\rZ\u0001\fgR|\u0007\u000f]3e?\u0012*\u0017\u000f\u0006\u0003\u00026\u0005m\u0002c\u0001\u001d\u00028%\u0019\u0011\u0011H\u001d\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003{)\u0012\u0011!a\u0001\u0003G\t1\u0001\u001f\u00132\u0003!\u0019Ho\u001c9qK\u0012\u0004\u0003f\u0001\f\u0002DA\u0019\u0001(!\u0012\n\u0007\u0005\u001d\u0013H\u0001\u0005w_2\fG/\u001b7f\u00039\tG\u000f^1dQ2K7\u000f^3oKJ$B!!\u000e\u0002N!1\u0011qJ\fA\u0002a\f\u0001\u0002\\5ti\u0016tWM]\u0001\u0006gR\f'\u000f\u001e\u000b\u0003\u0003k\tAa\u001d;pa\u0006)\"/Z4jgR,'O\u0015#E\r>\u00148\t\\3b]V\u0004H\u0003BA\u001b\u00037Bq!!\u0018\u001b\u0001\u0004\ty&A\u0002sI\u0012\u0004D!!\u0019\u0002pA1\u00111MA4\u0003Wj!!!\u001a\u000b\u0007\u0005u\u0003'\u0003\u0003\u0002j\u0005\u0015$a\u0001*E\tB!\u0011QNA8\u0019\u0001!A\"!\u001d\u0002\\\u0005\u0005\t\u0011!B\u0001\u0003g\u00121a\u0018\u00132#\u0011\t)(a\u001f\u0011\u0007a\n9(C\u0002\u0002ze\u0012qAT8uQ&tw\rE\u00029\u0003{J1!a :\u0005\r\te._\u0001\u001ee\u0016<\u0017n\u001d;fe\u0006\u001b7-^7vY\u0006$xN\u001d$pe\u000ecW-\u00198vaR!\u0011QGAC\u0011\u001d\t9i\u0007a\u0001\u0003\u0013\u000b\u0011!\u0019\u0019\u0007\u0003\u0017\u000b9*!(\u0011\u0011\u00055\u0015\u0011SAK\u00037k!!a$\u000b\u0005q\u0003\u0014\u0002BAJ\u0003\u001f\u0013Q\"Q2dk6,H.\u0019;peZ\u0013\u0004\u0003BA7\u0003/#A\"!'\u0002\u0006\u0006\u0005\t\u0011!B\u0001\u0003g\u00121a\u0018\u00133!\u0011\ti'!(\u0005\u0019\u0005}\u0015QQA\u0001\u0002\u0003\u0015\t!a\u001d\u0003\u0007}#3'A\rsK\u001eL7\u000f^3s'\",hM\u001a7f\r>\u00148\t\\3b]V\u0004H\u0003BA\u001b\u0003KCq!a*\u001d\u0001\u0004\tI+A\ttQV4g\r\\3EKB,g\u000eZ3oGf\u0004\u0004\"a+\u00024\u0006e\u0016q\u0018\t\n\r\u00065\u0016\u0011WA\\\u0003{K1!a,1\u0005E\u0019\u0006.\u001e4gY\u0016$U\r]3oI\u0016t7-\u001f\t\u0005\u0003[\n\u0019\f\u0002\u0007\u00026\u0006\u0015\u0016\u0011!A\u0001\u0006\u0003\t\u0019HA\u0002`IQ\u0002B!!\u001c\u0002:\u0012a\u00111XAS\u0003\u0003\u0005\tQ!\u0001\u0002t\t\u0019q\fJ\u001b\u0011\t\u00055\u0014q\u0018\u0003\r\u0003\u0003\f)+!A\u0001\u0002\u000b\u0005\u00111\u000f\u0002\u0004?\u00122\u0014a\u0007:fO&\u001cH/\u001a:Ce>\fGmY1ti\u001a{'o\u00117fC:,\b/\u0006\u0003\u0002H\u0006eG\u0003BA\u001b\u0003\u0013Dq!a3\u001e\u0001\u0004\ti-A\u0005ce>\fGmY1tiB1\u0011qZAj\u0003/l!!!5\u000b\u0007\u0005-\u0007'\u0003\u0003\u0002V\u0006E'!\u0003\"s_\u0006$7-Y:u!\u0011\ti'!7\u0005\u000f\u0005mWD1\u0001\u0002t\t\tA+A\u0012sK\u001eL7\u000f^3s%\u0012#5\t[3dWB|\u0017N\u001c;ECR\fgi\u001c:DY\u0016\fg.\u001e9\u0016\t\u0005\u0005\u0018\u0011 \u000b\u0007\u0003k\t\u0019/a<\t\u000f\u0005uc\u00041\u0001\u0002fB\"\u0011q]Av!\u0019\t\u0019'a\u001a\u0002jB!\u0011QNAv\t1\ti/a9\u0002\u0002\u0003\u0005)\u0011AA:\u0005\ryFe\u000e\u0005\b\u0003ct\u0002\u0019AAz\u0003!\u0001\u0018M]3oi&#\u0007c\u0001\u001d\u0002v&\u0019\u0011q_\u001d\u0003\u0007%sG\u000fB\u0004\u0002\\z\u0011\r!a\u001d\u0002?I,w-[:uKJ\u001c\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u001a{'o\u00117fC:,\b\u000f\u0006\u0004\u00026\u0005}(1\u0001\u0005\u0007\u0005\u0003y\u0002\u0019A\u001c\u0002\u001b1L7\u000f^3oKJ|uO\\3s\u0011\u001d\tye\ba\u0001\u0005\u000b\u0001BAa\u0002\u0003\u000e5\u0011!\u0011\u0002\u0006\u0004\u0005\u0017\u0001\u0014!C:dQ\u0016$W\u000f\\3s\u0013\u0011\u0011yA!\u0003\u0003\u001bM\u0003\u0018M]6MSN$XM\\3s\u0003I\u0011XmZ5ti\u0016\u0014hi\u001c:DY\u0016\fg.\u001e9\u0015\r\u0005U\"Q\u0003B\r\u0011\u0019\u00119\u0002\ta\u0001o\u0005\u0001rN\u00196fGR4uN]\"mK\u0006tW\u000f\u001d\u0005\b\u00057\u0001\u0003\u0019\u0001B\u000f\u0003\u0011!\u0018m]6\u0011\u0007\u0019\u0013y\"C\u0002\u0003\"A\u00121b\u00117fC:,\b\u000fV1tW\u0006a1.Z3q\u00072,\u0017M\\5oO\u0006aAm\\\"mK\u0006tW\u000f\u001d*E\tR1\u0011Q\u0007B\u0015\u0005[AqAa\u000b#\u0001\u0004\t\u00190A\u0003sI\u0012LE\rC\u0004\u00030\t\u0002\r!a\t\u0002\u0011\tdwnY6j]\u001e\f\u0001\u0003Z8DY\u0016\fg.\u001e9TQV4g\r\\3\u0015\r\u0005U\"Q\u0007B\u001d\u0011\u001d\u00119d\ta\u0001\u0003g\f\u0011b\u001d5vM\u001adW-\u00133\t\u000f\t=2\u00051\u0001\u0002$\u0005\u0011Bm\\\"mK\u0006tW\u000f\u001d\"s_\u0006$7-Y:u)\u0019\t)Da\u0010\u0003D!9!\u0011\t\u0013A\u0002\u0005]\u0011a\u00032s_\u0006$7-Y:u\u0013\u0012DqAa\f%\u0001\u0004\t\u0019#\u0001\be_\u000ecW-\u00198va\u0006\u001b7-^7\u0015\r\u0005U\"\u0011\nB'\u0011\u001d\u0011Y%\na\u0001\u0003/\tQ!Y2d\u0013\u0012DqAa\f&\u0001\u0004\t\u0019#A\te_\u000ecW-\u00198DQ\u0016\u001c7\u000e]8j]R$B!!\u000e\u0003T!9!1\u0006\u0014A\u0002\u0005M\u0018\u0001\u00063p\u00072,\u0017M\\*qCJ\\G*[:uK:,'\u000f\u0006\u0003\u00026\te\u0003bBA(O\u0001\u0007!QA\u0001\u0011EJ|\u0017\rZ2bgRl\u0015M\\1hKJ,\"Aa\u0018\u0011\t\u0005='\u0011M\u0005\u0005\u0005G\n\tN\u0001\tCe>\fGmY1ti6\u000bg.Y4fe\u00061R.\u00199PkR\u0004X\u000f\u001e+sC\u000e\\WM]'bgR,'/\u0006\u0002\u0003jA\u0019aIa\u001b\n\u0007\t5\u0004G\u0001\fNCB|U\u000f\u001e9viR\u0013\u0018mY6fe6\u000b7\u000f^3s\u00039\u0019uN\u001c;fqR\u001cE.Z1oKJ\u0004\"AR\u0016\u0014\u0005-:DC\u0001B9\u0003Y\u0011VIR0R+\u0016+Vi\u0018)P\u00192{F+S'F\u001fV#VCAAz\u0003]\u0011VIR0R+\u0016+Vi\u0018)P\u00192{F+S'F\u001fV#\u0006\u0005"
)
public class ContextCleaner implements Logging {
   private final SparkContext sc;
   private final ShuffleDriverComponents shuffleDriverComponents;
   private final Set referenceBuffer;
   private final ReferenceQueue referenceQueue;
   private final ConcurrentLinkedQueue listeners;
   private final Thread cleaningThread;
   private final ScheduledExecutorService periodicGCService;
   private final long periodicGCInterval;
   private final boolean blockOnCleanupTasks;
   private final boolean blockOnShuffleCleanupTasks;
   private volatile boolean stopped;
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

   private Set referenceBuffer() {
      return this.referenceBuffer;
   }

   private ReferenceQueue referenceQueue() {
      return this.referenceQueue;
   }

   private ConcurrentLinkedQueue listeners() {
      return this.listeners;
   }

   private Thread cleaningThread() {
      return this.cleaningThread;
   }

   private ScheduledExecutorService periodicGCService() {
      return this.periodicGCService;
   }

   private long periodicGCInterval() {
      return this.periodicGCInterval;
   }

   private boolean blockOnCleanupTasks() {
      return this.blockOnCleanupTasks;
   }

   private boolean blockOnShuffleCleanupTasks() {
      return this.blockOnShuffleCleanupTasks;
   }

   private boolean stopped() {
      return this.stopped;
   }

   private void stopped_$eq(final boolean x$1) {
      this.stopped = x$1;
   }

   public void attachListener(final CleanerListener listener) {
      this.listeners().add(listener);
   }

   public void start() {
      this.cleaningThread().setDaemon(true);
      this.cleaningThread().setName("Spark Context Cleaner");
      this.cleaningThread().start();
      this.periodicGCService().scheduleAtFixedRate(() -> System.gc(), this.periodicGCInterval(), this.periodicGCInterval(), TimeUnit.SECONDS);
   }

   public void stop() {
      this.stopped_$eq(true);
      synchronized(this){}

      try {
         this.cleaningThread().interrupt();
      } catch (Throwable var3) {
         throw var3;
      }

      this.cleaningThread().join();
      this.periodicGCService().shutdown();
   }

   public void registerRDDForCleanup(final RDD rdd) {
      this.registerForCleanup(rdd, new CleanRDD(rdd.id()));
   }

   public void registerAccumulatorForCleanup(final AccumulatorV2 a) {
      this.registerForCleanup(a, new CleanAccum(a.id()));
   }

   public void registerShuffleForCleanup(final ShuffleDependency shuffleDependency) {
      this.registerForCleanup(shuffleDependency, new CleanShuffle(shuffleDependency.shuffleId()));
   }

   public void registerBroadcastForCleanup(final Broadcast broadcast) {
      this.registerForCleanup(broadcast, new CleanBroadcast(broadcast.id()));
   }

   public void registerRDDCheckpointDataForCleanup(final RDD rdd, final int parentId) {
      this.registerForCleanup(rdd, new CleanCheckpoint(parentId));
   }

   public void registerSparkListenerForCleanup(final Object listenerOwner, final SparkListener listener) {
      this.registerForCleanup(listenerOwner, new CleanSparkListener(listener));
   }

   private void registerForCleanup(final Object objectForCleanup, final CleanupTask task) {
      this.referenceBuffer().add(new CleanupTaskWeakReference(task, objectForCleanup, this.referenceQueue()));
   }

   public void org$apache$spark$ContextCleaner$$keepCleaning() {
      Utils$.MODULE$.tryOrStopSparkContext(this.sc, (JFunction0.mcV.sp)() -> {
         while(!this.stopped()) {
            try {
               Option reference = .MODULE$.apply(this.referenceQueue().remove((long)ContextCleaner$.MODULE$.org$apache$spark$ContextCleaner$$REF_QUEUE_POLL_TIMEOUT())).map((x$1) -> (CleanupTaskWeakReference)x$1);
               synchronized(this){}

               try {
                  reference.foreach((ref) -> {
                     $anonfun$keepCleaning$3(this, ref);
                     return BoxedUnit.UNIT;
                  });
               } catch (Throwable var9) {
                  throw var9;
               }
            } catch (Throwable var10) {
               if (var10 instanceof InterruptedException && this.stopped()) {
                  BoxedUnit var11 = BoxedUnit.UNIT;
               } else {
                  if (!(var10 instanceof Exception)) {
                     throw var10;
                  }

                  Exception var6 = (Exception)var10;
                  this.logError((Function0)(() -> "Error in cleaning thread"), var6);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }
            }
         }

      });
   }

   public void doCleanupRDD(final int rddId, final boolean blocking) {
      try {
         this.logDebug((Function0)(() -> "Cleaning RDD " + rddId));
         this.sc.unpersistRDD(rddId, blocking);
         scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.listeners()).asScala().foreach((x$2) -> {
            $anonfun$doCleanupRDD$2(rddId, x$2);
            return BoxedUnit.UNIT;
         });
         this.logDebug((Function0)(() -> "Cleaned RDD " + rddId));
      } catch (Exception var4) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error cleaning RDD ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD_ID..MODULE$, BoxesRunTime.boxToInteger(rddId))})))), var4);
      }

   }

   public void doCleanupShuffle(final int shuffleId, final boolean blocking) {
      try {
         if (this.mapOutputTrackerMaster().containsShuffle(shuffleId)) {
            this.logDebug((Function0)(() -> "Cleaning shuffle " + shuffleId));
            this.shuffleDriverComponents.removeShuffle(shuffleId, blocking);
            this.mapOutputTrackerMaster().unregisterShuffle(shuffleId);
            scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.listeners()).asScala().foreach((x$3) -> {
               $anonfun$doCleanupShuffle$2(shuffleId, x$3);
               return BoxedUnit.UNIT;
            });
            this.logDebug((Function0)(() -> "Cleaned shuffle " + shuffleId));
         } else {
            this.logDebug((Function0)(() -> "Asked to cleanup non-existent shuffle (maybe it was already removed)"));
         }
      } catch (Exception var4) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error cleaning shuffle ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId))})))), var4);
      }

   }

   public void doCleanupBroadcast(final long broadcastId, final boolean blocking) {
      try {
         this.logDebug((Function0)(() -> "Cleaning broadcast " + broadcastId));
         this.broadcastManager().unbroadcast(broadcastId, true, blocking);
         scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.listeners()).asScala().foreach((x$4) -> {
            $anonfun$doCleanupBroadcast$2(broadcastId, x$4);
            return BoxedUnit.UNIT;
         });
         this.logDebug((Function0)(() -> "Cleaned broadcast " + broadcastId));
      } catch (Exception var5) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error cleaning broadcast ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST_ID..MODULE$, BoxesRunTime.boxToLong(broadcastId))})))), var5);
      }

   }

   public void doCleanupAccum(final long accId, final boolean blocking) {
      try {
         this.logDebug((Function0)(() -> "Cleaning accumulator " + accId));
         AccumulatorContext$.MODULE$.remove(accId);
         scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.listeners()).asScala().foreach((x$5) -> {
            $anonfun$doCleanupAccum$2(accId, x$5);
            return BoxedUnit.UNIT;
         });
         this.logDebug((Function0)(() -> "Cleaned accumulator " + accId));
      } catch (Exception var5) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error cleaning accumulator ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ACCUMULATOR_ID..MODULE$, BoxesRunTime.boxToLong(accId))})))), var5);
      }

   }

   public void doCleanCheckpoint(final int rddId) {
      try {
         this.logDebug((Function0)(() -> "Cleaning rdd checkpoint data " + rddId));
         ReliableRDDCheckpointData$.MODULE$.cleanCheckpoint(this.sc, rddId);
         scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.listeners()).asScala().foreach((x$6) -> {
            $anonfun$doCleanCheckpoint$2(rddId, x$6);
            return BoxedUnit.UNIT;
         });
         this.logDebug((Function0)(() -> "Cleaned rdd checkpoint data " + rddId));
      } catch (Exception var3) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error cleaning rdd checkpoint data ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD_ID..MODULE$, BoxesRunTime.boxToInteger(rddId))})))), var3);
      }

   }

   public void doCleanSparkListener(final SparkListener listener) {
      try {
         this.logDebug((Function0)(() -> "Cleaning Spark listener " + listener));
         this.sc.listenerBus().removeListener(listener);
         this.logDebug((Function0)(() -> "Cleaned Spark listener " + listener));
      } catch (Exception var3) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error cleaning Spark listener ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LISTENER..MODULE$, listener)})))), var3);
      }

   }

   private BroadcastManager broadcastManager() {
      return this.sc.env().broadcastManager();
   }

   private MapOutputTrackerMaster mapOutputTrackerMaster() {
      return (MapOutputTrackerMaster)this.sc.env().mapOutputTracker();
   }

   // $FF: synthetic method
   public static final void $anonfun$keepCleaning$3(final ContextCleaner $this, final CleanupTaskWeakReference ref) {
      $this.logDebug((Function0)(() -> "Got cleaning task " + ref.task()));
      $this.referenceBuffer().remove(ref);
      CleanupTask var3 = ref.task();
      if (var3 instanceof CleanRDD var4) {
         int rddId = var4.rddId();
         $this.doCleanupRDD(rddId, $this.blockOnCleanupTasks());
         BoxedUnit var22 = BoxedUnit.UNIT;
      } else if (var3 instanceof CleanShuffle var6) {
         int shuffleId = var6.shuffleId();
         $this.doCleanupShuffle(shuffleId, $this.blockOnShuffleCleanupTasks());
         BoxedUnit var21 = BoxedUnit.UNIT;
      } else if (var3 instanceof CleanBroadcast var8) {
         long broadcastId = var8.broadcastId();
         $this.doCleanupBroadcast(broadcastId, $this.blockOnCleanupTasks());
         BoxedUnit var20 = BoxedUnit.UNIT;
      } else if (var3 instanceof CleanAccum var11) {
         long accId = var11.accId();
         $this.doCleanupAccum(accId, $this.blockOnCleanupTasks());
         BoxedUnit var19 = BoxedUnit.UNIT;
      } else if (var3 instanceof CleanCheckpoint var14) {
         int rddId = var14.rddId();
         $this.doCleanCheckpoint(rddId);
         BoxedUnit var18 = BoxedUnit.UNIT;
      } else if (var3 instanceof CleanSparkListener var16) {
         SparkListener listener = var16.listener();
         $this.doCleanSparkListener(listener);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$doCleanupRDD$2(final int rddId$1, final CleanerListener x$2) {
      x$2.rddCleaned(rddId$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$doCleanupShuffle$2(final int shuffleId$1, final CleanerListener x$3) {
      x$3.shuffleCleaned(shuffleId$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$doCleanupBroadcast$2(final long broadcastId$1, final CleanerListener x$4) {
      x$4.broadcastCleaned(broadcastId$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$doCleanupAccum$2(final long accId$1, final CleanerListener x$5) {
      x$5.accumCleaned(accId$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$doCleanCheckpoint$2(final int rddId$2, final CleanerListener x$6) {
      x$6.checkpointCleaned((long)rddId$2);
   }

   public ContextCleaner(final SparkContext sc, final ShuffleDriverComponents shuffleDriverComponents) {
      this.sc = sc;
      this.shuffleDriverComponents = shuffleDriverComponents;
      Logging.$init$(this);
      this.referenceBuffer = Collections.newSetFromMap(new ConcurrentHashMap());
      this.referenceQueue = new ReferenceQueue();
      this.listeners = new ConcurrentLinkedQueue();
      this.cleaningThread = new Thread() {
         // $FF: synthetic field
         private final ContextCleaner $outer;

         public void run() {
            this.$outer.org$apache$spark$ContextCleaner$$keepCleaning();
         }

         public {
            if (ContextCleaner.this == null) {
               throw null;
            } else {
               this.$outer = ContextCleaner.this;
            }
         }
      };
      this.periodicGCService = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("context-cleaner-periodic-gc");
      this.periodicGCInterval = BoxesRunTime.unboxToLong(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.CLEANER_PERIODIC_GC_INTERVAL()));
      this.blockOnCleanupTasks = BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.CLEANER_REFERENCE_TRACKING_BLOCKING()));
      this.blockOnShuffleCleanupTasks = BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.CLEANER_REFERENCE_TRACKING_BLOCKING_SHUFFLE()));
      this.stopped = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
