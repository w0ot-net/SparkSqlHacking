package org.apache.spark.deploy.worker;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.ApplicationDescription;
import org.apache.spark.deploy.Command;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.deploy.ExecutorState$;
import org.apache.spark.deploy.StandaloneResourceUtils$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.util.ShutdownHookManager$;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.logging.FileAppender;
import org.apache.spark.util.logging.FileAppender$;
import org.slf4j.Logger;
import org.sparkproject.guava.io.FileWriteMode;
import org.sparkproject.guava.io.Files;
import scala.Enumeration;
import scala.Function0;
import scala.None;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.collection.IterableOnce;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tUd!\u0002#F\u0001\u001d{\u0005\u0002\u0003/\u0001\u0005\u000b\u0007I\u0011\u00010\t\u0011)\u0004!\u0011!Q\u0001\n}C\u0001b\u001b\u0001\u0003\u0006\u0004%\t\u0001\u001c\u0005\ta\u0002\u0011\t\u0011)A\u0005[\"A\u0011\u000f\u0001BC\u0002\u0013\u0005!\u000f\u0003\u0005x\u0001\t\u0005\t\u0015!\u0003t\u0011!A\bA!b\u0001\n\u0003a\u0007\u0002C=\u0001\u0005\u0003\u0005\u000b\u0011B7\t\u0011i\u0004!Q1A\u0005\u00021D\u0001b\u001f\u0001\u0003\u0002\u0003\u0006I!\u001c\u0005\t\r\u0002\u0011)\u0019!C\u0001y\"I\u0011q\u0001\u0001\u0003\u0002\u0003\u0006I! \u0005\n\u0003\u0013\u0001!Q1A\u0005\u0002yC\u0011\"a\u0003\u0001\u0005\u0003\u0005\u000b\u0011B0\t\u0013\u00055\u0001A!b\u0001\n\u0003q\u0006\"CA\b\u0001\t\u0005\t\u0015!\u0003`\u0011%\t\t\u0002\u0001BC\u0002\u0013\u0005a\fC\u0005\u0002\u0014\u0001\u0011\t\u0011)A\u0005?\"I\u0011Q\u0003\u0001\u0003\u0006\u0004%\t\u0001\u001c\u0005\n\u0003/\u0001!\u0011!Q\u0001\n5D\u0011\"!\u0007\u0001\u0005\u000b\u0007I\u0011\u00010\t\u0013\u0005m\u0001A!A!\u0002\u0013y\u0006BCA\u000f\u0001\t\u0015\r\u0011\"\u0001\u0002 !Q\u0011\u0011\u0007\u0001\u0003\u0002\u0003\u0006I!!\t\t\u0015\u0005M\u0002A!b\u0001\n\u0003\ty\u0002\u0003\u0006\u00026\u0001\u0011\t\u0011)A\u0005\u0003CA\u0011\"a\u000e\u0001\u0005\u000b\u0007I\u0011\u00010\t\u0013\u0005e\u0002A!A!\u0002\u0013y\u0006BCA\u001e\u0001\t\u0005\t\u0015!\u0003\u0002>!Q\u0011Q\t\u0001\u0003\u0006\u0004%\t!a\u0012\t\u0015\u0005m\u0003A!A!\u0002\u0013\tI\u0005\u0003\u0006\u0002^\u0001\u0011\t\u0019!C\u0001\u0003?B!\"!\u001d\u0001\u0005\u0003\u0007I\u0011AA:\u0011)\ty\b\u0001B\u0001B\u0003&\u0011\u0011\r\u0005\n\u0003\u0013\u0003!Q1A\u0005\u00021D\u0011\"a#\u0001\u0005\u0003\u0005\u000b\u0011B7\t\u0015\u00055\u0005A!b\u0001\n\u0003\ty\t\u0003\u0006\u0002$\u0002\u0011\t\u0011)A\u0005\u0003#Cq!!*\u0001\t\u0003\t9\u000bC\u0005\u0002T\u0002\u0011\r\u0011\"\u0003\u0002V\"A\u0011\u0011\u001d\u0001!\u0002\u0013\t9\u000eC\u0005\u0002d\u0002\u0001\r\u0011\"\u0003\u0002f\"I\u0011Q\u001e\u0001A\u0002\u0013%\u0011q\u001e\u0005\t\u0003g\u0004\u0001\u0015)\u0003\u0002h\"I\u0011Q\u001f\u0001A\u0002\u0013%\u0011q\u001f\u0005\n\u0003\u007f\u0004\u0001\u0019!C\u0005\u0005\u0003A\u0001B!\u0002\u0001A\u0003&\u0011\u0011 \u0005\n\u0005\u000f\u0001\u0001\u0019!C\u0005\u0005\u0013A\u0011Ba\u0007\u0001\u0001\u0004%IA!\b\t\u0011\t\u0005\u0002\u0001)Q\u0005\u0005\u0017A\u0011Ba\t\u0001\u0001\u0004%IA!\u0003\t\u0013\t\u0015\u0002\u00011A\u0005\n\t\u001d\u0002\u0002\u0003B\u0016\u0001\u0001\u0006KAa\u0003\t\u0011\t5\u0002A1A\u0005\n1DqAa\f\u0001A\u0003%Q\u000eC\u0005\u00032\u0001\u0001\r\u0011\"\u0003\u00034!I!Q\u0007\u0001A\u0002\u0013%!q\u0007\u0005\b\u0005w\u0001\u0001\u0015)\u0003Q\u0011!\u0011i\u0004\u0001C\u0001\u000b\n}\u0002b\u0002B!\u0001\u0011%!1\t\u0005\t\u0005\u0013\u0002A\u0011A#\u0003@!A!1\n\u0001\u0005\u0002\u0015\u0013i\u0005C\u0004\u0003T\u0001!IAa\u0010\b\u0015\tUS)!A\t\u0002\u001d\u00139FB\u0005E\u000b\u0006\u0005\t\u0012A$\u0003Z!9\u0011QU!\u0005\u0002\tm\u0003\"\u0003B/\u0003F\u0005I\u0011\u0001B0\u00059)\u00050Z2vi>\u0014(+\u001e8oKJT!AR$\u0002\r]|'o[3s\u0015\tA\u0015*\u0001\u0004eKBdw.\u001f\u0006\u0003\u0015.\u000bQa\u001d9be.T!\u0001T'\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0015aA8sON\u0019\u0001\u0001\u0015,\u0011\u0005E#V\"\u0001*\u000b\u0003M\u000bQa]2bY\u0006L!!\u0016*\u0003\r\u0005s\u0017PU3g!\t9&,D\u0001Y\u0015\tI\u0016*\u0001\u0005j]R,'O\\1m\u0013\tY\u0006LA\u0004M_\u001e<\u0017N\\4\u0002\u000b\u0005\u0004\b/\u00133\u0004\u0001U\tq\f\u0005\u0002aO:\u0011\u0011-\u001a\t\u0003EJk\u0011a\u0019\u0006\u0003Iv\u000ba\u0001\u0010:p_Rt\u0014B\u00014S\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001.\u001b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0019\u0014\u0016AB1qa&#\u0007%\u0001\u0004fq\u0016\u001c\u0017\nZ\u000b\u0002[B\u0011\u0011K\\\u0005\u0003_J\u00131!\u00138u\u0003\u001d)\u00070Z2JI\u0002\nq!\u00199q\t\u0016\u001c8-F\u0001t!\t!X/D\u0001H\u0013\t1xI\u0001\fBaBd\u0017nY1uS>tG)Z:de&\u0004H/[8o\u0003!\t\u0007\u000f\u001d#fg\u000e\u0004\u0013!B2pe\u0016\u001c\u0018AB2pe\u0016\u001c\b%\u0001\u0004nK6|'/_\u0001\b[\u0016lwN]=!+\u0005i\bc\u0001@\u0002\u00045\tqPC\u0002\u0002\u0002%\u000b1A\u001d9d\u0013\r\t)a \u0002\u000f%B\u001cWI\u001c3q_&tGOU3g\u0003\u001d9xN]6fe\u0002\n\u0001b^8sW\u0016\u0014\u0018\nZ\u0001\no>\u00148.\u001a:JI\u0002\n1b^3c+&\u001c6\r[3nK\u0006aq/\u001a2VSN\u001b\u0007.Z7fA\u0005!\u0001n\\:u\u0003\u0015Awn\u001d;!\u0003%9XMY+j!>\u0014H/\u0001\u0006xK\n,\u0016\u000eU8si\u0002\nQ\u0002];cY&\u001c\u0017\t\u001a3sKN\u001c\u0018A\u00049vE2L7-\u00113ee\u0016\u001c8\u000fI\u0001\ngB\f'o\u001b%p[\u0016,\"!!\t\u0011\t\u0005\r\u0012QF\u0007\u0003\u0003KQA!a\n\u0002*\u0005\u0011\u0011n\u001c\u0006\u0003\u0003W\tAA[1wC&!\u0011qFA\u0013\u0005\u00111\u0015\u000e\\3\u0002\u0015M\u0004\u0018M]6I_6,\u0007%A\u0006fq\u0016\u001cW\u000f^8s\t&\u0014\u0018\u0001D3yK\u000e,Ho\u001c:ESJ\u0004\u0013!C<pe.,'/\u0016:m\u0003)9xN]6feV\u0013H\u000eI\u0001\u0005G>tg\r\u0005\u0003\u0002@\u0005\u0005S\"A%\n\u0007\u0005\r\u0013JA\u0005Ta\u0006\u00148nQ8oM\u0006a\u0011\r\u001d9M_\u000e\fG\u000eR5sgV\u0011\u0011\u0011\n\t\u0006\u0003\u0017\n)f\u0018\b\u0005\u0003\u001b\n\tFD\u0002c\u0003\u001fJ\u0011aU\u0005\u0004\u0003'\u0012\u0016a\u00029bG.\fw-Z\u0005\u0005\u0003/\nIFA\u0002TKFT1!a\u0015S\u00035\t\u0007\u000f\u001d'pG\u0006dG)\u001b:tA\u0005)1\u000f^1uKV\u0011\u0011\u0011\r\t\u0005\u0003G\nIGD\u0002u\u0003KJ1!a\u001aH\u00035)\u00050Z2vi>\u00148\u000b^1uK&!\u00111NA7\u0005\u00151\u0016\r\\;f\u0013\r\tyG\u0015\u0002\f\u000b:,X.\u001a:bi&|g.A\u0005ti\u0006$Xm\u0018\u0013fcR!\u0011QOA>!\r\t\u0016qO\u0005\u0004\u0003s\u0012&\u0001B+oSRD\u0011\"! \"\u0003\u0003\u0005\r!!\u0019\u0002\u0007a$\u0013'\u0001\u0004ti\u0006$X\r\t\u0015\u0004E\u0005\r\u0005cA)\u0002\u0006&\u0019\u0011q\u0011*\u0003\u0011Y|G.\u0019;jY\u0016\fAA\u001d9JI\u0006)!\u000f]%eA\u0005I!/Z:pkJ\u001cWm]\u000b\u0003\u0003#\u0003b\u0001YAJ?\u0006]\u0015bAAKS\n\u0019Q*\u00199\u0011\t\u0005e\u0015qT\u0007\u0003\u00037S1!!(J\u0003!\u0011Xm]8ve\u000e,\u0017\u0002BAQ\u00037\u00131CU3t_V\u00148-Z%oM>\u0014X.\u0019;j_:\f!B]3t_V\u00148-Z:!\u0003\u0019a\u0014N\\5u}QA\u0013\u0011VAW\u0003_\u000b\t,a-\u00026\u0006]\u0016\u0011XA^\u0003{\u000by,!1\u0002D\u0006\u0015\u0017qYAe\u0003\u0017\fi-a4\u0002RB\u0019\u00111\u0016\u0001\u000e\u0003\u0015CQ\u0001X\u0014A\u0002}CQa[\u0014A\u00025DQ!]\u0014A\u0002MDQ\u0001_\u0014A\u00025DQA_\u0014A\u00025DQAR\u0014A\u0002uDa!!\u0003(\u0001\u0004y\u0006BBA\u0007O\u0001\u0007q\f\u0003\u0004\u0002\u0012\u001d\u0002\ra\u0018\u0005\u0007\u0003+9\u0003\u0019A7\t\r\u0005eq\u00051\u0001`\u0011\u001d\tib\na\u0001\u0003CAq!a\r(\u0001\u0004\t\t\u0003\u0003\u0004\u00028\u001d\u0002\ra\u0018\u0005\b\u0003w9\u0003\u0019AA\u001f\u0011\u001d\t)e\na\u0001\u0003\u0013Bq!!\u0018(\u0001\u0004\t\t\u0007\u0003\u0004\u0002\n\u001e\u0002\r!\u001c\u0005\n\u0003\u001b;\u0003\u0013!a\u0001\u0003#\u000baAZ;mY&#WCAAl!\u0011\tI.a8\u000e\u0005\u0005m'\u0002BAo\u0003S\tA\u0001\\1oO&\u0019\u0001.a7\u0002\u000f\u0019,H\u000e\\%eA\u0005aqo\u001c:lKJ$\u0006N]3bIV\u0011\u0011q\u001d\t\u0005\u00033\fI/\u0003\u0003\u0002l\u0006m'A\u0002+ie\u0016\fG-\u0001\tx_J\\WM\u001d+ie\u0016\fGm\u0018\u0013fcR!\u0011QOAy\u0011%\tihKA\u0001\u0002\u0004\t9/A\u0007x_J\\WM\u001d+ie\u0016\fG\rI\u0001\baJ|7-Z:t+\t\tI\u0010\u0005\u0003\u0002Z\u0006m\u0018\u0002BA\u007f\u00037\u0014q\u0001\u0015:pG\u0016\u001c8/A\u0006qe>\u001cWm]:`I\u0015\fH\u0003BA;\u0005\u0007A\u0011\"! /\u0003\u0003\u0005\r!!?\u0002\u0011A\u0014xnY3tg\u0002\nab\u001d;e_V$\u0018\t\u001d9f]\u0012,'/\u0006\u0002\u0003\fA!!Q\u0002B\f\u001b\t\u0011yA\u0003\u0003\u0003\u0012\tM\u0011a\u00027pO\u001eLgn\u001a\u0006\u0004\u0005+I\u0015\u0001B;uS2LAA!\u0007\u0003\u0010\taa)\u001b7f\u0003B\u0004XM\u001c3fe\u0006\u00112\u000f\u001e3pkR\f\u0005\u000f]3oI\u0016\u0014x\fJ3r)\u0011\t)Ha\b\t\u0013\u0005u\u0014'!AA\u0002\t-\u0011aD:uI>,H/\u00119qK:$WM\u001d\u0011\u0002\u001dM$H-\u001a:s\u0003B\u0004XM\u001c3fe\u0006\u00112\u000f\u001e3feJ\f\u0005\u000f]3oI\u0016\u0014x\fJ3r)\u0011\t)H!\u000b\t\u0013\u0005uD'!AA\u0002\t-\u0011aD:uI\u0016\u0014(/\u00119qK:$WM\u001d\u0011\u0002;\u0015CViQ+U\u001fJ{F+\u0012*N\u0013:\u000bE+R0U\u00136+u*\u0016+`\u001bN\u000ba$\u0012-F\u0007V#vJU0U\u000bJk\u0015JT!U\u000b~#\u0016*T#P+R{Vj\u0015\u0011\u0002\u0019MDW\u000f\u001e3po:Dun\\6\u0016\u0003A\u000b\u0001c\u001d5vi\u0012|wO\u001c%p_.|F%Z9\u0015\t\u0005U$\u0011\b\u0005\t\u0003{J\u0014\u0011!a\u0001!\u0006i1\u000f[;uI><h\u000eS8pW\u0002\nQa\u001d;beR$\"!!\u001e\u0002\u0017-LG\u000e\u001c)s_\u000e,7o\u001d\u000b\u0005\u0003k\u0012)\u0005\u0003\u0004\u0003Hq\u0002\raX\u0001\b[\u0016\u001c8/Y4f\u0003\u0011Y\u0017\u000e\u001c7\u0002'M,(m\u001d;jiV$XMV1sS\u0006\u0014G.Z:\u0015\u0007}\u0013y\u0005\u0003\u0004\u0003Ry\u0002\raX\u0001\tCJ<W/\\3oi\u0006\u0019b-\u001a;dQ\u0006sGMU;o\u000bb,7-\u001e;pe\u0006qQ\t_3dkR|'OU;o]\u0016\u0014\bcAAV\u0003N\u0011\u0011\t\u0015\u000b\u0003\u0005/\nA\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n\u0014(\u0006\u0002\u0003b)\"\u0011\u0011\u0013B2W\t\u0011)\u0007\u0005\u0003\u0003h\tETB\u0001B5\u0015\u0011\u0011YG!\u001c\u0002\u0013Ut7\r[3dW\u0016$'b\u0001B8%\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\tM$\u0011\u000e\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class ExecutorRunner implements Logging {
   private final String appId;
   private final int execId;
   private final ApplicationDescription appDesc;
   private final int cores;
   private final int memory;
   private final RpcEndpointRef worker;
   private final String workerId;
   private final String webUiScheme;
   private final String host;
   private final int webUiPort;
   private final String publicAddress;
   private final File sparkHome;
   private final File executorDir;
   private final String workerUrl;
   private final SparkConf conf;
   private final Seq appLocalDirs;
   private volatile Enumeration.Value state;
   private final int rpId;
   private final Map resources;
   private final String org$apache$spark$deploy$worker$ExecutorRunner$$fullId;
   private Thread workerThread;
   private Process process;
   private FileAppender stdoutAppender;
   private FileAppender stderrAppender;
   private final int EXECUTOR_TERMINATE_TIMEOUT_MS;
   private Object shutdownHook;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Map $lessinit$greater$default$19() {
      return ExecutorRunner$.MODULE$.$lessinit$greater$default$19();
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public String appId() {
      return this.appId;
   }

   public int execId() {
      return this.execId;
   }

   public ApplicationDescription appDesc() {
      return this.appDesc;
   }

   public int cores() {
      return this.cores;
   }

   public int memory() {
      return this.memory;
   }

   public RpcEndpointRef worker() {
      return this.worker;
   }

   public String workerId() {
      return this.workerId;
   }

   public String webUiScheme() {
      return this.webUiScheme;
   }

   public String host() {
      return this.host;
   }

   public int webUiPort() {
      return this.webUiPort;
   }

   public String publicAddress() {
      return this.publicAddress;
   }

   public File sparkHome() {
      return this.sparkHome;
   }

   public File executorDir() {
      return this.executorDir;
   }

   public String workerUrl() {
      return this.workerUrl;
   }

   public Seq appLocalDirs() {
      return this.appLocalDirs;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public void state_$eq(final Enumeration.Value x$1) {
      this.state = x$1;
   }

   public int rpId() {
      return this.rpId;
   }

   public Map resources() {
      return this.resources;
   }

   public String org$apache$spark$deploy$worker$ExecutorRunner$$fullId() {
      return this.org$apache$spark$deploy$worker$ExecutorRunner$$fullId;
   }

   private Thread workerThread() {
      return this.workerThread;
   }

   private void workerThread_$eq(final Thread x$1) {
      this.workerThread = x$1;
   }

   private Process process() {
      return this.process;
   }

   private void process_$eq(final Process x$1) {
      this.process = x$1;
   }

   private FileAppender stdoutAppender() {
      return this.stdoutAppender;
   }

   private void stdoutAppender_$eq(final FileAppender x$1) {
      this.stdoutAppender = x$1;
   }

   private FileAppender stderrAppender() {
      return this.stderrAppender;
   }

   private void stderrAppender_$eq(final FileAppender x$1) {
      this.stderrAppender = x$1;
   }

   private int EXECUTOR_TERMINATE_TIMEOUT_MS() {
      return this.EXECUTOR_TERMINATE_TIMEOUT_MS;
   }

   private Object shutdownHook() {
      return this.shutdownHook;
   }

   private void shutdownHook_$eq(final Object x$1) {
      this.shutdownHook = x$1;
   }

   public void start() {
      this.workerThread_$eq(new Thread() {
         // $FF: synthetic field
         private final ExecutorRunner $outer;

         public void run() {
            this.$outer.org$apache$spark$deploy$worker$ExecutorRunner$$fetchAndRunExecutor();
         }

         public {
            if (ExecutorRunner.this == null) {
               throw null;
            } else {
               this.$outer = ExecutorRunner.this;
            }
         }
      });
      this.workerThread().start();
      this.shutdownHook_$eq(ShutdownHookManager$.MODULE$.addShutdownHook((JFunction0.mcV.sp)() -> {
         label23: {
            label22: {
               Enumeration.Value var10000 = this.state();
               Enumeration.Value var1 = ExecutorState$.MODULE$.LAUNCHING();
               if (var10000 == null) {
                  if (var1 == null) {
                     break label22;
                  }
               } else if (var10000.equals(var1)) {
                  break label22;
               }

               var10000 = this.state();
               Enumeration.Value var2 = ExecutorState$.MODULE$.RUNNING();
               if (var10000 == null) {
                  if (var2 != null) {
                     break label23;
                  }
               } else if (!var10000.equals(var2)) {
                  break label23;
               }
            }

            this.state_$eq(ExecutorState$.MODULE$.FAILED());
         }

         this.killProcess("Worker shutting down");
      }));
   }

   private void killProcess(final String message) {
      Option exitCode = .MODULE$;
      if (this.process() != null) {
         this.logInfo((Function0)(() -> "Killing process!"));
         if (this.stdoutAppender() != null) {
            this.stdoutAppender().stop();
         }

         if (this.stderrAppender() != null) {
            this.stderrAppender().stop();
         }

         exitCode = Utils$.MODULE$.terminateProcess(this.process(), (long)this.EXECUTOR_TERMINATE_TIMEOUT_MS());
         if (exitCode.isEmpty()) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to terminate process: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PROCESS..MODULE$, this.process())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{". This process will likely be orphaned."})))).log(scala.collection.immutable.Nil..MODULE$))));
         }
      }

      try {
         this.worker().send(new DeployMessages.ExecutorStateChanged(this.appId(), this.execId(), this.state(), new Some(message), exitCode));
      } catch (IllegalStateException var4) {
         this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var4.getMessage())})))), var4);
      }

   }

   public void kill() {
      if (this.workerThread() != null) {
         this.workerThread().interrupt();
         this.workerThread_$eq((Thread)null);
         this.state_$eq(ExecutorState$.MODULE$.KILLED());

         try {
            ShutdownHookManager$.MODULE$.removeShutdownHook(this.shutdownHook());
         } catch (IllegalStateException var2) {
            None var10000 = .MODULE$;
         }

      }
   }

   public String substituteVariables(final String argument) {
      switch (argument == null ? 0 : argument.hashCode()) {
         case -1983484972:
            if ("{{CORES}}".equals(argument)) {
               return Integer.toString(this.cores());
            }
            break;
         case -591890398:
            if ("{{RESOURCE_PROFILE_ID}}".equals(argument)) {
               return Integer.toString(this.rpId());
            }
            break;
         case -433289593:
            if ("{{EXECUTOR_ID}}".equals(argument)) {
               return Integer.toString(this.execId());
            }
            break;
         case -160836466:
            if ("{{WORKER_URL}}".equals(argument)) {
               return this.workerUrl();
            }
            break;
         case 304759001:
            if ("{{APP_ID}}".equals(argument)) {
               return this.appId();
            }
            break;
         case 1147236979:
            if ("{{HOSTNAME}}".equals(argument)) {
               return this.host();
            }
      }

      return argument;
   }

   public void org$apache$spark$deploy$worker$ExecutorRunner$$fetchAndRunExecutor() {
      try {
         Option resourceFileOpt = StandaloneResourceUtils$.MODULE$.prepareResourcesFile(package$.MODULE$.SPARK_EXECUTOR_PREFIX(), this.resources(), this.executorDir());
         Seq arguments = (Seq)this.appDesc().command().arguments().$plus$plus((IterableOnce)resourceFileOpt.map((f) -> new scala.collection.immutable..colon.colon("--resourcesFile", new scala.collection.immutable..colon.colon(f.getAbsolutePath(), scala.collection.immutable.Nil..MODULE$))).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty()));
         Seq subsOpts = (Seq)this.appDesc().command().javaOpts().map((x$1) -> Utils$.MODULE$.substituteAppNExecIds(x$1, this.appId(), Integer.toString(this.execId())));
         String x$3 = this.appDesc().command().copy$default$1();
         scala.collection.Map x$4 = this.appDesc().command().copy$default$3();
         Seq x$5 = this.appDesc().command().copy$default$4();
         Seq x$6 = this.appDesc().command().copy$default$5();
         Command subsCommand = this.appDesc().command().copy(x$3, arguments, x$4, x$5, x$6, subsOpts);
         ProcessBuilder builder = CommandUtils$.MODULE$.buildProcessBuilder(subsCommand, new SecurityManager(this.conf, SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3()), this.memory(), this.sparkHome().getAbsolutePath(), (argument) -> this.substituteVariables(argument), CommandUtils$.MODULE$.buildProcessBuilder$default$6(), CommandUtils$.MODULE$.buildProcessBuilder$default$7());
         List command = builder.command();
         String redactedCommand = Utils$.MODULE$.redactCommandLineArgs(this.conf, scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(command).asScala().toSeq()).mkString("\"", "\" \"", "\"");
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Launch command: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COMMAND..MODULE$, redactedCommand)})))));
         builder.directory(this.executorDir());
         builder.environment().put("SPARK_EXECUTOR_DIRS", this.appLocalDirs().mkString(File.pathSeparator));
         builder.environment().put("SPARK_LAUNCH_WITH_SCALA", "0");
         String baseUrl = BoxesRunTime.unboxToBoolean(this.conf.get(UI$.MODULE$.UI_REVERSE_PROXY())) ? scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.conf.get(UI$.MODULE$.UI_REVERSE_PROXY_URL().key(), "")), "/") + "/proxy/" + this.workerId() + "/logPage/?appId=" + this.appId() + "&executorId=" + this.execId() + "&logType=" : this.webUiScheme() + this.publicAddress() + ":" + this.webUiPort() + "/logPage/?appId=" + this.appId() + "&executorId=" + this.execId() + "&logType=";
         builder.environment().put("SPARK_LOG_URL_STDERR", baseUrl + "stderr");
         builder.environment().put("SPARK_LOG_URL_STDOUT", baseUrl + "stdout");
         this.process_$eq(builder.start());
         String header = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Spark Executor Command: %s\n%s\n\n"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{redactedCommand, scala.collection.StringOps..MODULE$.$times$extension(scala.Predef..MODULE$.augmentString("="), 40)}));
         File stdout = new File(this.executorDir(), "stdout");
         this.stdoutAppender_$eq(FileAppender$.MODULE$.apply(this.process().getInputStream(), stdout, this.conf, true));
         File stderr = new File(this.executorDir(), "stderr");
         Files.asCharSink(stderr, StandardCharsets.UTF_8, new FileWriteMode[0]).write(header);
         this.stderrAppender_$eq(FileAppender$.MODULE$.apply(this.process().getErrorStream(), stderr, this.conf, true));
         this.state_$eq(ExecutorState$.MODULE$.RUNNING());
         this.worker().send(new DeployMessages.ExecutorStateChanged(this.appId(), this.execId(), this.state(), .MODULE$, .MODULE$));
         int exitCode = this.process().waitFor();
         this.state_$eq(ExecutorState$.MODULE$.EXITED());
         String message = "Command exited with code " + exitCode;
         this.worker().send(new DeployMessages.ExecutorStateChanged(this.appId(), this.execId(), this.state(), new Some(message), new Some(BoxesRunTime.boxToInteger(exitCode))));
      } catch (InterruptedException var22) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Runner thread for executor ", " interrupted"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, this.org$apache$spark$deploy$worker$ExecutorRunner$$fullId())})))));
         this.state_$eq(ExecutorState$.MODULE$.KILLED());
         this.killProcess("Runner thread for executor " + this.org$apache$spark$deploy$worker$ExecutorRunner$$fullId() + " interrupted");
      } catch (Exception var23) {
         this.logError((Function0)(() -> "Error running executor"), var23);
         this.state_$eq(ExecutorState$.MODULE$.FAILED());
         this.killProcess("Error running executor: " + var23);
      }

   }

   public ExecutorRunner(final String appId, final int execId, final ApplicationDescription appDesc, final int cores, final int memory, final RpcEndpointRef worker, final String workerId, final String webUiScheme, final String host, final int webUiPort, final String publicAddress, final File sparkHome, final File executorDir, final String workerUrl, final SparkConf conf, final Seq appLocalDirs, final Enumeration.Value state, final int rpId, final Map resources) {
      this.appId = appId;
      this.execId = execId;
      this.appDesc = appDesc;
      this.cores = cores;
      this.memory = memory;
      this.worker = worker;
      this.workerId = workerId;
      this.webUiScheme = webUiScheme;
      this.host = host;
      this.webUiPort = webUiPort;
      this.publicAddress = publicAddress;
      this.sparkHome = sparkHome;
      this.executorDir = executorDir;
      this.workerUrl = workerUrl;
      this.conf = conf;
      this.appLocalDirs = appLocalDirs;
      this.state = state;
      this.rpId = rpId;
      this.resources = resources;
      super();
      Logging.$init$(this);
      this.org$apache$spark$deploy$worker$ExecutorRunner$$fullId = appId + "/" + execId;
      this.workerThread = null;
      this.process = null;
      this.stdoutAppender = null;
      this.stderrAppender = null;
      this.EXECUTOR_TERMINATE_TIMEOUT_MS = 10000;
      this.shutdownHook = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
